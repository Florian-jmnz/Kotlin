package com.example.tp1_appkotlin


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [FilmEntity::class, SerieEntity::class, ActeurEntity::class], version = 8)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}