package com.reavature.beefcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reavature.beefcake.MuscleGroups.Companion.musclePicked
import com.reavature.beefcake.database.WorkoutViewModel
import com.reavature.beefcake.database.Workouts
import com.reavature.beefcake.databinding.FragmentWorkoutRecyclerViewBinding
import com.reavature.beefcake.databinding.FragmentWorkoutsLayoutBinding
import kotlinx.android.synthetic.main.fragment_workouts_layout.*

class WorkoutRecyclerViewFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkoutRecyclerViewBinding.inflate(layoutInflater)
        return binding.root
    }
}

