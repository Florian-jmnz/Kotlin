package com.example.tp1_appkotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
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
import coil.compose.AsyncImage

@Composable
fun Acteurs(viewModel: MainViewModel) {
    val acteurs by viewModel.acteurs.collectAsState()
    val favActeurs by viewModel.favActeurs.collectAsState()

    if (acteurs.isEmpty()) viewModel.getActeursInitiaux()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier.padding(0.dp,0.dp,0.dp,50.dp)
    ) {
        items(acteurs) { acteur ->
            (
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(300.dp),
                        elevation = 20.dp
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .background(Color.Black)
                        ) {
                            Box(modifier = Modifier.height(200.dp)) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                                    contentDescription = acteur.name,
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
                                            if (acteur.isFav) {
                                                viewModel.deleteFavActeur(acteur)
                                            } else {
                                                viewModel.addFavActeur(acteur)
                                            }
                                        }
                                    ) {
                                        if (acteur.isFav || favActeurs.any { isFavActeur ->
                                                isFavActeur.id == acteur.id.toString()
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
                            Box(
                                modifier = Modifier
                                    .height(100.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .fillMaxWidth()
                                        .fillMaxHeight(),

                                    verticalArrangement = Arrangement.SpaceAround
                                ) {
                                    Text(
                                        text = acteur.name,
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
                        }
                    })
        }
    }
}

@Composable
fun FavorisActeurs(viewModel: MainViewModel) {

    val acteurs by viewModel.favActeurs.collectAsState()
    val favActeurs by viewModel.favActeurs.collectAsState()

    viewModel.getActeursInitiaux()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier.padding(0.dp,0.dp,0.dp,50.dp)
    ) {
        items(acteurs) { acteur ->
            Card(
                modifier = Modifier
                    .focusable()
                    .padding(10.dp)
                    .clickable { },
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(0.dp)
                        .background(Color.Black)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.height(200.dp)) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${acteur.fiche.profile_path}",
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .size(25.dp)
                                    .background(Color.White, CircleShape)
                                    .padding(3.dp),
                                onClick = {
                                    viewModel.deleteFavActeur(acteur.fiche)
                                }
                            ) {
                                if (acteur.fiche.isFav || favActeurs.any { isFavActeur ->
                                        isFavActeur.id == acteur.id
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
                    Box(
                        modifier = Modifier
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(0.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),

                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                acteur.fiche.name,
                                fontWeight = FontWeight.W900,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .width(200.dp)
                                    .padding(2.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}


