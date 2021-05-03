package com.reavature.beefcake.database

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutsDao: WorkoutsDao) {

    val readAllData: LiveData<List<Workouts>> = workoutsDao.getAllWorkouts()

    suspend fun addAllWorkouts(arrayList: ArrayList<Workouts>) {
        workoutsDao.addAllWorkouts(arrayList)
    }

    suspend fun updateFavoriteWorkouts(workouts: Workouts) {
        workoutsDao.updateFavoriteWorkouts(workouts)
    }

}