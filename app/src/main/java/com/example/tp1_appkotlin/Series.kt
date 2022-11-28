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
fun Series(viewModel: MainViewModel, navController: NavController) {
    val series by viewModel.series.collectAsState()

    if (series.isEmpty()) viewModel.getSeriesInitiales()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp)
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
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                                contentDescription = serie.name,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                            Text(
                                text = serie.name
                            )
                        }
                    }
                    )
        }
    }
}