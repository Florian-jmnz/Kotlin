package com.example.tp1_appkotlin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.People
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Films : Screen("films", "Films", icon = (Icons.Outlined.Movie))
    object Series : Screen("series", "Series", icon = (Icons.Outlined.OndemandVideo))
    object Acteurs : Screen("acteurs", "Acteurs", icon = (Icons.Outlined.People))
}

val items = listOf(
    Screen.Films,
    Screen.Series,
    Screen.Acteurs,
)

@Composable
fun Films(viewModel: MainViewModel, navController: NavController) {
    val movies by viewModel.movies.collectAsState()
    val favMovies by viewModel.favMovies.collectAsState()

    if (movies.isEmpty()) viewModel.getFilmsInitiaux()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier.padding(0.dp,0.dp,0.dp,50.dp)
    ) {
        items(movies) { movie ->
            (
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable { navController.navigate("detailFilm/${movie.id}" ) },
                        elevation = 20.dp
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                    contentDescription = movie.original_title,
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    IconButton(
                                        modifier = Modifier
                                            .size(25.dp)
                                            .background(Color.White, CircleShape)
                                            .padding(3.dp),
                                        onClick = {
                                            if (movie.isFav) {
                                                viewModel.deleteFavFilm(movie)
                                            } else {
                                                viewModel.addFavFilm(movie)
                                            }
                                        }
                                    ) {
                                        if (movie.isFav || favMovies.any { isFavMovie ->
                                                isFavMovie.id == movie.id.toString();
                                            }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                "favorite",
                                                tint = Color(0xFFCE1515)
                                            )
                                        } else {
                                            Icon(
                                                Icons.Filled.FavoriteBorder,
                                                "favorite",
                                                tint = Color(0xFFCE1515)

                                            )
                                        }
                                    }
                                }
                            }
                            Text(
                                movie.title,
                                fontWeight = FontWeight.W700,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .width(200.dp)
                                    .padding(2.dp),
                            )
                            Text(
                                text = movie.release_date,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .width(200.dp)
                                    .padding(2.dp),
                            )
                        }
                    }
                    )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavorisFilms(viewModel: MainViewModel, navController: NavController) {
    val movies by viewModel.favMovies.collectAsState()
    val favMovies by viewModel.favMovies.collectAsState()
    viewModel.getFilmsInitiaux()
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,50.dp)) {
        items(movies) { movie ->
            (
                    Card(
                        onClick = {
                            Log.i("ID FOCUSED", movie.id.toString())
                            navController.navigate("detailFilm/${movie.id}")
                        },
                        modifier = Modifier
                            .focusable()
                            .padding(10.dp)
                            .clickable { },
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier.height(200.dp)){
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${movie.fiche.poster_path}",
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.TopEnd
                                ) {
                                    IconButton(
                                        modifier = Modifier
                                            .size(25.dp)
                                            .background(Color.White, CircleShape)
                                            .padding(3.dp),
                                        onClick = {
                                            viewModel.deleteFavFilm(movie.fiche)
                                        }
                                    ) {
                                        if(movie.fiche.isFav || favMovies.any{isFavMovie ->
                                                isFavMovie.id == movie.id;
                                            }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                "favorite",
                                                tint = Color.Red
                                            )
                                        }else {
                                            Icon(
                                                Icons.Filled.FavoriteBorder,
                                                "favorite",
                                                tint = Color.Red
                                            )
                                        }
                                    }
                                }
                            }
                            Text(
                                movie.fiche.title,
                                fontWeight = FontWeight.W700,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .width(200.dp)
                                    .padding(2.dp),
                            )
                            Text(
                                text = movie.fiche.release_date,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .width(200.dp)
                                    .padding(2.dp),
                            )
                        }
                    })
        }
    }
}



