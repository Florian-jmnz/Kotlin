package com.example.tp1_appkotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun DetailFilm(viewModel: MainViewModel, windowClass: WindowSizeClass, id : String) {
    val classeLargeur = windowClass.widthSizeClass

    val detailMovie by viewModel.detailMovie.collectAsState()
    val creditsMovie by viewModel.creditsMovie.collectAsState()

    if (creditsMovie.isEmpty()) viewModel.getCreditsFilm(id)

    viewModel.getDetailFilm(id)
    viewModel.getCreditsFilm(id)

    when (classeLargeur) {
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .height(800.dp),
            ) {
                Row(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = detailMovie.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W900,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .background(color = Color.Black)
                            .width(250.dp)
                            .padding(5.dp),
                    )
                    Text(
                        text = detailMovie.release_date,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.White,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .background(color = Color.Black)
                            .width(250.dp)
                            .padding(5.dp),
                    )
                }
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/${detailMovie.backdrop_path}",
                    contentDescription = detailMovie.title,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                Row(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Synopsis : ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .background(color = Color.Black)
                            .padding(5.dp),
                    )
                }
                Text(
                    text = detailMovie.overview,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(15.dp)
                        .padding(0.dp, 15.dp)
                )
                Row(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Casting : ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .background(color = Color.Black)
                            .padding(5.dp),
                    )
                }
                LazyHorizontalGrid(
                    rows = GridCells.Adaptive(minSize = 150.dp),
                    modifier = Modifier.height(216.5.dp)
                ) {
                    items(creditsMovie) { acteur ->
                        (
                                Card(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(300.dp)
                                        .padding(10.dp),
                                    elevation = 20.dp
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box() {
                                            AsyncImage(
                                                model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                                                contentDescription = acteur.name,
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                contentAlignment = Alignment.TopCenter
                                            ) {
                                                Text(
                                                    text = acteur.name,
                                                    textAlign = TextAlign.Center,
                                                    fontWeight = FontWeight.W800,
                                                    color = Color.White,
                                                    fontSize = 15.sp,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .background(Color.Black)
                                                        .padding(5.dp),
                                                )
                                            }
                                        }
                                    }
                                })
                    }
                }
            }
        }
        else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier
                            .background(color = Color.Black)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = detailMovie.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W900,
                            color = Color.White,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .background(color = Color.Black)
                                .width(250.dp)
                                .padding(5.dp),
                        )
                        Text(
                            text = detailMovie.release_date,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.White,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .background(color = Color.Black)
                                .width(550.dp)
                                .padding(5.dp),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(250.dp)
                                .background(Color.Black)
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${detailMovie.backdrop_path}",
                                contentDescription = detailMovie.title,
                                modifier = Modifier
                                    .height(200.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .width(550.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Synopsis : ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                    color = Color.White,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .background(color = Color.Black)
                                        .padding(5.dp),
                                )
                            }
                            Text(
                                text = detailMovie.overview,
                                fontSize = 14.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .padding(15.dp)
                                    .padding(0.dp, 15.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .height(300.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Casting : ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                    color = Color.White,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .background(color = Color.Black)
                                        .padding(5.dp),
                                )
                            }
                            LazyHorizontalGrid(
                                rows = GridCells.Adaptive(minSize = 150.dp),
                                modifier = Modifier.height(216.5.dp)
                            ) {
                                items(creditsMovie) { acteur ->
                                    (
                                            Card(
                                                modifier = Modifier
                                                    .width(150.dp)
                                                    .height(300.dp)
                                                    .padding(10.dp),
                                                elevation = 20.dp
                                            ) {
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Box() {
                                                        AsyncImage(
                                                            model = "https://image.tmdb.org/t/p/w500/${acteur.profile_path}",
                                                            contentDescription = acteur.name,
                                                            modifier = Modifier
                                                                .fillMaxHeight()
                                                        )
                                                        Box(
                                                            modifier = Modifier
                                                                .fillMaxWidth(),
                                                            contentAlignment = Alignment.TopCenter
                                                        ) {
                                                            Text(
                                                                text = acteur.name,
                                                                textAlign = TextAlign.Center,
                                                                fontWeight = FontWeight.W800,
                                                                color = Color.White,
                                                                fontSize = 15.sp,
                                                                modifier = Modifier
                                                                    .fillMaxWidth()
                                                                    .background(Color.Black)
                                                                    .padding(5.dp),
                                                            )
                                                        }
                                                    }
                                                }
                                            })
                                }
                            }
                        }
                    }
                }
            }
        }
    }