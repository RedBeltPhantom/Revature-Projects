package com.reavature.beefcake.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Equipment(
    @PrimaryKey
    val equipment: String,
    val muscle: String,
    val safety: String,
    val variant: String
)
