package com.reavature.beefcake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.reavature.beefcake.MuscleGroups.Companion.musclePicked
import com.reavature.beefcake.database.Workouts
import kotlinx.android.synthetic.main.fragment_workout_recycler_view.*
import kotlinx.android.synthetic.main.fragment_workout_recycler_view.view.*
import kotlinx.android.synthetic.main.fragment_workouts_layout.*
import kotlinx.android.synthetic.main.fragment_workouts_layout.view.*

class WorkoutsEachMuscle : AppCompatActivity() {

    private lateinit var muscleAdapter: MuscleAdapter
    private lateinit var data: ArrayList<Workouts>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workouts_each_muscle)

        when(musclePicked) {
            1 ->    data = DataSource.createDataSetBiceps()
            2 ->    data = DataSource.createDataSetTriceps()
            3 ->    data = DataSource.createDataSetBack()
            4 ->    data = DataSource.createDataSetShoulders()
            5 ->    data = DataSource.createDataSetChest()
            6 ->    data = DataSource.createDataSetAbs()
            7 ->    data = DataSource.createDataSetLegs()
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@WorkoutsEachMuscle)
            muscleAdapter = MuscleAdapter(data)
            adapter = muscleAdapter
        }
    }

}