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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Series(viewModel: MainViewModel, navController: NavController) {
    val series by viewModel.series.collectAsState()
    val favSeries by viewModel.favSeries.collectAsState()

    if (series.isEmpty()) viewModel.getSeriesInitiales()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier.padding(0.dp,0.dp,0.dp,50.dp)
    ) {
        items(series) { serie ->
            (
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable { navController.navigate("detailSerie/${serie.id}" ) },
                        elevation = 20.dp
                    ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(modifier = Modifier.height(200.dp)) {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                                        contentDescription = serie.name,
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
                                                if (serie.isFav) {
                                                    viewModel.deleteFavSerie(serie)
                                                } else {
                                                    viewModel.addFavSerie(serie)
                                                }
                                            }
                                        ) {
                                            if (serie.isFav || favSeries.any { isFavSerie ->
                                                    isFavSerie.id == serie.id.toString();
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
                                    text = serie.name,
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
fun FavorisSeries(viewModel: MainViewModel, navController: NavController) {
    val series by viewModel.favSeries.collectAsState()
    val favSeries by viewModel.favSeries.collectAsState()
    viewModel.getSeriesInitiales()
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp), modifier = Modifier.padding(0.dp,0.dp,0.dp,50.dp)) {
        items(series) { serie ->
            (
                    Card(
                        onClick = {
                            Log.i("ID FOCUSED", serie.id.toString())
                            navController.navigate("detailSerie/${serie.id}" )
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
                            Box(modifier = Modifier.height(200.dp)) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${serie.fiche.poster_path}",
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
                                            viewModel.deleteFavSerie(serie.fiche)
                                        }
                                    ) {
                                        if (serie.fiche.isFav || favSeries.any { isFavSerie ->
                                                isFavSerie.id == serie.id;
                                            }) {
                                            Icon(
                                                Icons.Filled.Favorite,
                                                "favorite",
                                                tint = Color.Red
                                            )
                                        } else {
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
                                        text = serie.fiche.name,
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