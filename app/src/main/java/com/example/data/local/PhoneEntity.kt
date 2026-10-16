package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phones")
data class PhoneEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val brand: String,
    val year: Int,
    val priceUsd: Int,
    val category: String,
    val imageUrl: String?,
    val display: String,
    val chipset: String,
    val ram: String,
    val storage: String,
    val cameras: String,
    val battery: String,
    val os: String,
    val connectivity: String,
    val waterResistance: String,
    val weight: String,
    val prosJson: String,
    val consJson: String,
    val verdict: String,
    val verdictReasonEn: String,
    val verdictReasonAr: String,
    val valueScore: Float = 8.5f,
    val isFavorite: Boolean = false,
    val isLatest: Boolean = false,
    val accentColorHex: String = "#2563EB"
)
