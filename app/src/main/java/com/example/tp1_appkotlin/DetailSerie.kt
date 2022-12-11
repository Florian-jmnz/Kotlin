package com.example.tp1_appkotlin

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
fun DetailSerie(viewModel: MainViewModel, navController: NavController, id : String){
    val detailSerie by viewModel.detailSerie.collectAsState()
    val creditsSerie by viewModel.creditsSerie.collectAsState()

    if (creditsSerie.isEmpty()) viewModel.getCreditsSerie(id)

    viewModel.getDetailSerie(id)
    viewModel.getCreditsSerie(id)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = detailSerie.original_name
        )
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${detailSerie.poster_path}",
            contentDescription = detailSerie.original_name,
            modifier = Modifier
                .size(200.dp)
        )
        Text(
            text = " ( " + detailSerie.first_air_date + " ) "
        )
        Text(
            text = "Synopsis : "
        )
        Text(
            text = detailSerie.overview
        )
        Text(
            text = "Casting : "
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(creditsSerie) { acteur ->
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
                                        .size(200.dp)
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