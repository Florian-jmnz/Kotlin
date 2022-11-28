package com.example.tp1_appkotlin

import MainViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.People
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
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

    if (movies.isEmpty()) viewModel.getFilmsInitiaux()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp)
    ) {
        items(movies) { movie ->
            (
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("detailFilm/${movie.id}" ) },
                        elevation = 20.dp
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                contentDescription = movie.original_title,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                            Text(
                                text = movie.original_title + " ( " + movie.release_date + " ) "
                            )
                        }
                    }
                    )
        }
    }
}



