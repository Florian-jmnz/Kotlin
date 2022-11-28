package com.example.tp1_appkotlin
import Acteur
import ActeurList
import CreditsMovie
import CreditsSerie
import Film
import FilmList
import Serie
import SerieList
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {

    //--------------------Films-----------------------//
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String, @Query("language") language: String): FilmList
    @GET("search/movie")
    suspend fun searchfilms(@Query("api_key") api_key: String, @Query("query") searchtext:String, @Query("language") language: String): FilmList
    @GET("movie/{id}")
    suspend fun detailFilm(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): Film
    @GET("movie/{id}/credits")
    suspend fun creditsFilm(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): CreditsMovie

    //--------------------Series-----------------------//
    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String, @Query("language") language: String): SerieList
    @GET("search/tv")
    suspend fun searchseries(@Query("api_key") api_key: String, @Query("query") searchtext:String, @Query("language") language: String): SerieList
    @GET("tv/{id}")
    suspend fun detailSerie(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): Serie
    @GET("tv/{id}/credits")
    suspend fun creditsSerie(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") language: String): CreditsSerie

    //--------------------Acteurs-----------------------//
    @GET("trending/person/week")
    suspend fun lastacteurs(@Query("api_key") api_key: String, @Query("language") language: String): ActeurList
}
