package com.reavature.beefcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.reavature.beefcake.database.WorkoutViewModel
import com.reavature.beefcake.database.Workouts
import com.reavature.beefcake.databinding.FragmentFavoriteWorkoutsBinding
import com.reavature.beefcake.databinding.FragmentWorkoutRecyclerViewBinding
import com.reavature.beefcake.databinding.FragmentWorkoutsLayoutBinding
import kotlinx.android.synthetic.main.fragment_equipment_list.*
import kotlinx.android.synthetic.main.fragment_workouts_layout.*
import kotlinx.android.synthetic.main.fragment_workouts_layout.view.*

class FavoriteWorkouts : Fragment() {



//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentWorkoutRecyclerViewBinding.inflate(layoutInflater)
//        val view = inflater.inflate(R.layout.fragment_workouts_layout, container, false)
//
//        viewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
//        viewModel.addWorkouts(bicepWorkouts)
//        viewModel.addWorkouts(tricepWorkouts)
//        viewModel.addWorkouts(backWorkouts)
//        viewModel.addWorkouts(shoulderWorkouts)
//        viewModel.addWorkouts(chestWorkouts)
//        viewModel.addWorkouts(abWorkouts)
//        viewModel.addWorkouts(legWorkouts)
//
//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            muscleAdapter = MuscleAdapter(bicepWorkouts)
//            adapter = muscleAdapter
//        }
//
//
//        return binding.root
//    }
//
//}
}