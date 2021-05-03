package com.reavature.beefcake.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutsDao {

    @Query("SELECT * FROM workouts_table")
    fun getAllWorkouts(): LiveData<List<Workouts>>

//    @Query("SELECT * FROM workouts_table WHERE favorites IS 17301515 ")
//    fun getFavoriteWorkouts(): List<Workouts>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllWorkouts(arrayList: ArrayList<Workouts>?)

    @Update
    suspend fun updateFavoriteWorkouts(workouts: Workouts)

}