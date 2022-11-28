package com.example.tp1_appkotlin

import MainViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tp1_appkotlin.ui.theme.TP1AppKotlinTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val viewModel: MainViewModel by viewModels()
            TP1AppKotlinTheme {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    topBar = {
                        if (currentDestination !== null && currentDestination.route === "films") {
                            SearchView({ it -> viewModel.searchFilms(it) })
                        }
                        if (currentDestination !== null && currentDestination.route === "series") {
                            SearchView({ it -> viewModel.searchSeries(it) })
                        }
                    },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = "profil"
                            ) {
                                composable("profil") {
                                    ProfileScreen(windowSizeClass, navController)
                                }
                                composable("films") {
                                    Films(viewModel, navController)
                                }
                                composable("series") {
                                    Series(viewModel, navController)
                                }
                                composable("acteurs") {
                                    Acteurs(viewModel, navController)
                                }
                                composable("detailFilm/{id}") {
                                    navBackStackEntry ->
                                        var id = navBackStackEntry.arguments?.getString("id")
                                    DetailFilm(viewModel, navController, id!!)
                                }
                                composable("detailSerie/{id}") {
                                        navBackStackEntry ->
                                    var id = navBackStackEntry.arguments?.getString("id")
                                    DetailSerie(viewModel, navController, id!!)
                                }
                            }
                        }
                    },
                    bottomBar = {
                        if (currentDestination !== null && currentDestination.route !== "profil") {
                            BottomNavigation {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                items.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(
                                                screen.icon,
                                                contentDescription = screen.label
                                            )
                                        },
                                        label = { Text(screen.label) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(onSearch:(t:String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var texte by remember {
        mutableStateOf("")
    }
    TextField(
        value = texte,
        onValueChange = {  texte = it },

        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)

            )
        },
        trailingIcon = {
            if (texte != "") {
                IconButton(
                    onClick = {
                        texte = "" // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            backgroundColor = Color.Black,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            onSearch(texte)
        }),
    )
}


