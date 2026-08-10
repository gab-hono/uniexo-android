package com.unicofrance.uniexo.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Container(
    @PrimaryKey val id: String,
    val longitude: Double,
    val latitude: Double,
    val label: String,
    val producingPlaceLabel: String,
    val description: String,
    val streamLabel: String,
    val streamColor: String,
    val iconUrl: String,
    val creationDatetime: Long,
)