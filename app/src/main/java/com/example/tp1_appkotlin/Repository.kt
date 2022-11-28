package com.example.tp1_appkotlin

class Repository(private val tmdbAPI: TmdbAPI) {
    private val apikey = "d2ee8f9a0abe429c115a40452040c23a"
    val language = "fr"

    //--------------------Films-----------------------//
    suspend fun lastMovies() = tmdbAPI.lastmovies(api_key = apikey, language = language).results
    suspend fun searchFilms(searchtext:String) = tmdbAPI.searchfilms(api_key = apikey,searchtext = searchtext, language = language).results
    suspend fun detailFilm(id: String) = tmdbAPI.detailFilm(api_key = apikey,id = id, language = language)
    suspend fun creditsFilm(id: String) = tmdbAPI.creditsFilm(api_key = apikey,id = id, language = language).cast

    //--------------------Series-----------------------//
    suspend fun lastseries() = tmdbAPI.lastseries(api_key = apikey, language = language).results
    suspend fun searchseries(searchtext:String) = tmdbAPI.searchseries(api_key = apikey,searchtext = searchtext, language = language).results
    suspend fun detailSerie(id: String) = tmdbAPI.detailSerie(api_key = apikey,id = id, language = language)
    suspend fun creditsSerie(id: String) = tmdbAPI.creditsSerie(api_key = apikey,id = id, language = language).cast

    //--------------------Acteurs-----------------------//
    suspend fun lastacteurs() = tmdbAPI.lastacteurs(api_key = apikey, language = language).results
}
