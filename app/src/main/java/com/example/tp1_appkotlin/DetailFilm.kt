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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun DetailFilm(viewModel: MainViewModel, navController: NavController, id : String) {
    val detailMovie by viewModel.detailMovie.collectAsState()
    val creditsMovie by viewModel.creditsMovie.collectAsState()

    if (creditsMovie.isEmpty()) viewModel.getCreditsFilm(id)

    viewModel.getDetailFilm(id)
    viewModel.getCreditsFilm(id)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = detailMovie.original_title
        )
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${detailMovie.poster_path}",
            contentDescription = detailMovie.original_title,
            modifier = Modifier
                .size(200.dp)
        )
        Text(
            text = " ( " + detailMovie.release_date + " ) "
        )
        Text(
            text = "Synopsis : "
        )
        Text(
            text = detailMovie.overview
        )
        Text(
            text = "Casting : "
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(creditsMovie) { acteur ->
                (
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            elevation = 20.dp
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                                    contentDescription = acteur.name,
                                    modifier = Modifier
                                        .size(100.dp)
                                )
                                Text(
                                    text = acteur.name
                                )
                            }
                        }
                        )
            }
        }
    }
}