package com.reavature.beefcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.reavature.beefcake.databinding.FragmentMainMenuBinding
import com.reavature.beefcake.databinding.FragmentMakeReservationBinding
import com.reavature.beefcake.ui.reservation.fragments.MakeReservationFragmentDirections

class MainMenu : Fragment() {
    /**
     * Sets up your binding for your frag ment so you don't have to do binding. each time.
     */
    private var _binding: FragmentMainMenuBinding? = null

    /** This is the binding that retrieves the binding to reference the views,
     *  ONLY if it is not null. The piece of code from above helps us to do that that check  */
    private val binding: FragmentMainMenuBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)

        binding.btnReserve.setOnClickListener {

            val navigateAction =
                MainMenuDirections.actionMainMenu3ToMakeReservationFragment()
            findNavController().navigate(navigateAction)

        }

        binding.btnListEquip.setOnClickListener {

            val navigateAction =
                MainMenuDirections.actionMainMenuToEquipmentList2()
            findNavController().navigate(navigateAction)

        }

        binding.btnWorkout.setOnClickListener {

//            val navigateAction =
//                MainMenuDirections.actionMainMenuToWorkouts()
            findNavController().navigate(R.id.action_mainMenu_to_muscleGroups)

        }

        binding.btnHealthProfile.setOnClickListener {

            val navigateAction = MainMenuDirections.actionMainMenuToHealthProfile()
            findNavController().navigate(navigateAction)
        }

       return(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.show()
    }
}