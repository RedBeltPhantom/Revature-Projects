package com.reavature.beefcake.database

import android.os.Parcelable
import android.widget.ImageButton
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "workouts_table")
data class Workouts(

   @PrimaryKey(autoGenerate = true)
   val id: Int,

   @ColumnInfo(name = "title of workout")
   val title: String,

   @ColumnInfo(name = "image of workout")
   val image: String,

   @ColumnInfo(name = "favorites")
   var favorite: Int
): Parcelable