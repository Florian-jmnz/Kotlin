package com.example.tp1_appkotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SerieEntity(val fiche: Serie, @PrimaryKey val id: String)