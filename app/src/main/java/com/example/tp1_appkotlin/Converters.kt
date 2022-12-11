package com.example.tp1_appkotlin

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class Converters(moshi: Moshi) {
    private val filmJsonadapter = moshi.adapter(Film::class.java)
    private val serieJsonadapter = moshi.adapter(Serie::class.java)
    private val acteurJsonadapter = moshi.adapter(Acteur::class.java)

    @TypeConverter
    fun StringToTmdbFilm(value: String): Film? {
        return filmJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbFilmToString(film: Film): String {
        return filmJsonadapter.toJson(film)
    }

    @TypeConverter
    fun StringToTmdbSerie(value: String): Serie? {
        return serieJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbSerieToString(serie: Serie): String {
        return serieJsonadapter.toJson(serie)
    }

    @TypeConverter
    fun StringToTmdbActeur(value: String): Acteur? {
        return acteurJsonadapter.fromJson(value)
    }

    @TypeConverter
    fun TmdbActeurToString(acteur: Acteur): String {
        return acteurJsonadapter.toJson(acteur)
    }
}