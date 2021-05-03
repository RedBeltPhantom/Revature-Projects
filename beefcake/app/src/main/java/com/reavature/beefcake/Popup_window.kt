package com.reavature.beefcake

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reavature.beefcake.EquipmentList.Companion.equipmentPosition
import com.reavature.beefcake.database.Equipment
import com.reavature.beefcake.databinding.FragmentPopupWindowBinding


class Popup_window : Fragment() {

    private lateinit var data: Equipment
    private lateinit var binding: FragmentPopupWindowBinding
    //private lateinit var PopUpAdapter: popupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPopupWindowBinding.inflate(layoutInflater)

        data = equipmentPosition

        //Log.d("Position", data.toString())

        //PopUpAdapter = popupAdapter(data)

        binding.equipName.text = data.equipment
        binding.equipMuscle.text = data.muscle
        binding.equipSafety.text = data.safety
        binding.equipVariety.text = data.variant

        return binding.root
    }
}