package com.example.tp1_appkotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmEntity(val fiche: Film, @PrimaryKey val id: String)