package com.example.tp1_appkotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActeurEntity(val fiche: Acteur, @PrimaryKey val id: String)