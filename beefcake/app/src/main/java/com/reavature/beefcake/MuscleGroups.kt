package com.reavature.beefcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.reavature.beefcake.databinding.FragmentMuscleGroupsBinding
import kotlinx.android.synthetic.main.fragment_muscle_groups.view.*

class MuscleGroups : Fragment() {

    private lateinit var binding: FragmentMuscleGroupsBinding

    companion object{
        var musclePicked = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_muscle_groups, container, false)
        binding = FragmentMuscleGroupsBinding.inflate(layoutInflater)

        view.btnBiceps.setOnClickListener {
            musclePicked = 1
            findNavController().navigate(R.id.action_muscleGroups_to_workoutsEachMuscle)
        }
        view.btnTriceps.setOnClickListener {
            musclePicked = 2
            findNavController().navigate(R.id.action_muscleGroups_to_workoutsEachMuscle)
        }
        view.btnBack.setOnClickListener {
            musclePicked = 3
            findNavController().navigate(R.id.action_muscleGroups_to_workoutsEachMuscle)
        }
        view.btnShoulders.setOnClickListener {
            musclePicked = 4
            findNavController().navigate(R.id.action_muscleGroups_to_workoutsEachMuscle)
        }
        view.btnChest.setOnClickListener {
            musclePicked = 5
            findNavController().navigate(R.id.action_muscleGroups_to_workoutsEachMuscle)
        }
        view.btnAbs.setOnClickListener {
            musclePicked = 6
            findNavController().navigate(R.id.action_muscleGroups_to_workoutsEachMuscle)
        }
        view.btnLegs.setOnClickListener {
            musclePicked = 7
            findNavController().navigate(R.id.action_muscleGroups_to_workoutsEachMuscle)
        }
//        view.btnFavorites.setOnClickListener {
//            findNavController().navigate(R.id.action_muscleGroups_to_favoriteWorkouts)
//        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.show()
    }
}