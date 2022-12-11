package com.example.tp1_appkotlin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository): ViewModel() {
    val movies = MutableStateFlow<List<Film>>(listOf())
    val detailMovie = MutableStateFlow(Film())
    val creditsMovie = MutableStateFlow<List<Cast>>(listOf())
    val favMovies = MutableStateFlow<List<FilmEntity>>(listOf())

    val series = MutableStateFlow<List<Serie>>(listOf())
    val creditsSerie = MutableStateFlow<List<CastTV>>(listOf())
    val detailSerie = MutableStateFlow(Serie())
    val favSeries = MutableStateFlow<List<SerieEntity>>(listOf())


    val acteurs = MutableStateFlow<List<Acteur>>(listOf())
    val favActeurs = MutableStateFlow<List<ActeurEntity>>(listOf())


    fun getFilmsInitiaux() {
        viewModelScope.launch {
            movies.value = repo.lastMovies()
        }
    }

    fun searchFilms(search: String) {
        viewModelScope.launch {
            movies.value = repo.searchFilms(search)
        }
    }

    fun getDetailFilm(id: String) {
        viewModelScope.launch {
            detailMovie.value = repo.detailFilm(id)
        }
    }

    fun getCreditsFilm(id: String) {
        viewModelScope.launch {
            creditsMovie.value = repo.creditsFilm(id)
        }
    }

    fun getFavFilms(){
        viewModelScope.launch {
            favMovies.value = repo.getFavFilms()
        }
    }
    fun addFavFilm(movie: Film){
        viewModelScope.launch {
            repo.isFavFilm(movie);
            val favMovies = repo.getFavFilms()
            val FilmsList = mutableListOf<Film>()
            movies.value.map { movie ->
                if(favMovies.any{isFavMovie ->
                        isFavMovie.id == movie.id.toString();
                    }){
                    val newFavMovie = movie.copy(isFav = true)
                    FilmsList.add(newFavMovie)
                }else{
                    FilmsList.add(movie)
                }
            }
            Log.i("NEW FAV", favMovies.toString())
            movies.value = FilmsList
            getFavFilms()
        }
    }

    fun deleteFavFilm(movie: Film){
        viewModelScope.launch {
            repo.notFavFilm(movie.id);
            val favMovies = repo.getFavFilms()

            val FilmsList = mutableListOf<Film>()

            movies.value.map { movie ->
                if(favMovies.any{isFav ->
                        isFav.id == movie.id.toString()
                    }){
                    FilmsList.add(movie.copy(isFav = true))
                }else{
                    FilmsList.add(movie.copy(isFav = false))
                }
            }
            Log.i("DELETED FAV", favMovies.toString())

            movies.value = FilmsList
            getFavFilms()
        }
    }


    fun getSeriesInitiales() {
        viewModelScope.launch {
            series.value = repo.lastseries()
        }
    }

    fun searchSeries(search: String) {
        viewModelScope.launch {
            series.value = repo.searchseries(search)
        }
    }

    fun getDetailSerie(id: String) {
        viewModelScope.launch {
            detailSerie.value = repo.detailSerie(id)
        }
    }

    fun getCreditsSerie(id: String) {
        viewModelScope.launch {
            creditsSerie.value = repo.creditsSerie(id)
        }
    }

    fun getFavSeries(){
        viewModelScope.launch {
            favSeries.value = repo.getFavSeries()
        }
    }
    fun addFavSerie(serie: Serie){
        viewModelScope.launch {
            repo.isFavSerie(serie);
            val favSeries = repo.getFavSeries()
            val SeriesList = mutableListOf<Serie>()
            series.value.map { serie ->
                if(favSeries.any{isFavSerie ->
                        isFavSerie.id == serie.id.toString();
                    }){
                    val newFavSerie = serie.copy(isFav = true)
                    SeriesList.add(newFavSerie)
                }else{
                    SeriesList.add(serie)
                }
            }
            Log.i("NEW FAV", favSeries.toString())
            series.value = SeriesList
            getFavSeries()
        }
    }

    fun deleteFavSerie(serie: Serie){
        viewModelScope.launch {
            repo.notFavSerie(serie.id);
            val favSeries = repo.getFavSeries()

            val SeriesList = mutableListOf<Serie>()

            series.value.map { serie ->
                if(favSeries.any{isFav ->
                        isFav.id == serie.id.toString()
                    }){
                    SeriesList.add(serie.copy(isFav = true))
                }else{
                    SeriesList.add(serie.copy(isFav = false))
                }
            }
            Log.i("DELETED FAV", favSeries.toString())

            series.value = SeriesList
            getFavSeries()
        }
    }



    fun getActeursInitiaux() {
        viewModelScope.launch {
            acteurs.value = repo.lastacteurs()
        }
    }

    fun getFavActeurs(){
        viewModelScope.launch {
            favActeurs.value = repo.getFavActeurs()
        }
    }

    fun addFavActeur(acteur: Acteur){
        viewModelScope.launch {
            repo.isFavActeur(acteur);
            val favActeurs = repo.getFavActeurs()
            val ActeursList = mutableListOf<Acteur>()
            acteurs.value.map { acteur ->
                if(favActeurs.any{isFavActeur ->
                        isFavActeur.id == acteur.id.toString();
                    }){
                    val newFavActeur = acteur.copy(isFav = true)
                    ActeursList.add(newFavActeur)
                }else{
                    ActeursList.add(acteur)
                }
            }
            Log.i("NEW FAV", favActeurs.toString())
            acteurs.value = ActeursList
            getFavActeurs()
        }
    }

    fun deleteFavActeur(acteur: Acteur){
        viewModelScope.launch {
            repo.notFavActeur(acteur.id);
            val favActeurs = repo.getFavActeurs()

            val ActeursList = mutableListOf<Acteur>()

            acteurs.value.map { acteur ->
                if(favActeurs.any{isFav ->
                        isFav.id == acteur.id.toString()
                    }){
                    ActeursList.add(acteur.copy(isFav = true))
                }else{
                    ActeursList.add(acteur.copy(isFav = false))
                }
            }
            Log.i("DELETED FAV", favActeurs.toString())
            acteurs.value = ActeursList
            getFavActeurs()
        }
    }


    init{
        getFavFilms()
        getFavSeries()
        getFavActeurs()
    }
}