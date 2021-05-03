package com.reavature.beefcake.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel(app: Application): AndroidViewModel(app) {

    var readAllData: LiveData<List<Workouts>>
    private val repository: WorkoutRepository

    init {
        val workoutsDao = WorkoutsDatabase.getInstance(app).workoutsDao()
        repository = WorkoutRepository(workoutsDao)
        readAllData = repository.readAllData
    }

    fun getAllWorkoutObservers(): LiveData<List<Workouts>> {
        return readAllData
    }

    fun getAllWorkouts() {
        readAllData
    }

    fun insertWorkoutInfo(arrayList: ArrayList<Workouts>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAllWorkouts(arrayList)
            getAllWorkouts()
        }
    }

    fun updateWorkoutInfo(workouts: Workouts) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteWorkouts(workouts)
            getAllWorkouts()
        }
    }
}