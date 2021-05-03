package com.reavature.beefcake

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reavature.beefcake.database.Equipment
import com.reavature.beefcake.databinding.FragmentPopupWindowBinding

class popupAdapter(var items: Equipment){

    class ViewHolder(itemView: View){
        private var binding: FragmentPopupWindowBinding = FragmentPopupWindowBinding.bind(itemView)

//        val equipTitle = binding.equipName
//        val equipMuscle = binding.equipMuscle
//        val equipSafety = binding.equipSafety
//        val equipVariant = binding.equipVariety
//
//        fun bind(equipment: Equipment) {
//            equipTitle.text = equipment.equipment
//            equipMuscle.text = equipment.muscle
//            equipSafety.text = equipment.safety
//            equipVariant.text = equipment.variant
//        }
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_popup_window, parent, false)
        )
    }
}