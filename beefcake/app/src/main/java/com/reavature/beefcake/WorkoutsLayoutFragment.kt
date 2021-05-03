package com.reavature.beefcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.reavature.beefcake.database.WorkoutViewModel
import com.reavature.beefcake.database.Workouts
import com.reavature.beefcake.databinding.FragmentWorkoutsLayoutBinding
import kotlinx.android.synthetic.main.fragment_workouts_layout.*
import kotlinx.android.synthetic.main.fragment_workouts_layout.view.*

class WorkoutsLayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_workouts_layout, container, false)
        return view
    }

}