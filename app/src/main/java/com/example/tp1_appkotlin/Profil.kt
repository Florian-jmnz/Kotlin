package com.example.tp1_appkotlin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun nomPrenom() {
    Text(
        text = "Jimenez Florian",
        style = MaterialTheme.typography.h4,
    )
}

@Composable
fun contact(tel: String, mail: String) {
    Row {
        Icon(Icons.Rounded.Call, contentDescription = "Tel")
        Text(
            text = "  ${tel}",
        )
    }
    Row {
        Icon(Icons.Rounded.Email, contentDescription = "Email")
        Text(
            text = "  ${mail}",
        )
    }
}

@Composable
fun presentation() {
        Text(
            text = "Alternant CPAM Haute-Garonne et étudiant LP DReAM",
            textAlign = TextAlign.Center
            )
}

@Composable
fun monimage() {
    Image(
        painterResource(R.drawable.profil),
        contentDescription = "BeHere",
        modifier = Modifier
            .size(200.dp, 200.dp)
            .clip(RoundedCornerShape(1000.dp))
    )
}

@Composable
fun monButton(navController: NavController) {
    Button(onClick = {
        navController.navigate("films")
    }) {
        Text(text = "Films")
    }
}

@Composable
fun ProfileScreen(windowClass: WindowSizeClass, navController: NavController) {
    val classeLargeur = windowClass.widthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.Compact -> {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.TopCenter) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        monimage()
                        nomPrenom()
                        presentation()
                        Column(modifier = Modifier.padding(50.dp)) {
                            contact("06.34.65.19.34", "florian.jimenez@etu.iut-tlse3.fr")
                        }
                        monButton(navController)
                    }
                }
            }
        }
        else -> {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    monimage()
                    nomPrenom()
                    presentation()
                }
                Column(modifier = Modifier.padding(10.dp),horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        contact("0634651934", "florian.jimenez@etu.iut-tlse3.fr")
                    }
                    Column(
                        modifier = Modifier.padding(0.dp,25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        monButton(navController)
                    }
                }
            }
        }
    }
}
