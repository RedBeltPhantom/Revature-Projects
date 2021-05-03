package com.reavature.beefcake

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.*
import androidx.recyclerview.widget.RecyclerView
import com.reavature.beefcake.EquipmentList.Companion.equipmentPosition
import com.reavature.beefcake.database.Equipment
import com.reavature.beefcake.databinding.EquipmentListLayoutBinding
import androidx.navigation.fragment.findNavController
import com.reavature.beefcake.databinding.FragmentPopupWindowBinding

class EquipmentAdapter(var items: ArrayList<Equipment>) : RecyclerView.Adapter<EquipmentAdapter.ViewHolder>() {

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        private var binding: EquipmentListLayoutBinding = EquipmentListLayoutBinding.bind(itemView)

        val equipmentTitle = binding.tvEquipName
        val equipmentMuscle = binding.tvEquipMuscle

        fun bind(equipment: Equipment){
            equipmentTitle.text = equipment.equipment
            equipmentMuscle.text = equipment.muscle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.equipment_list_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(holder) {
            else -> {
                holder.bind(items[position])

                holder.itemView.setOnClickListener {
                    equipmentPosition = items[position]
                    //Log.d("Position", equipmentPosition.toString())
                    holder.itemView.findNavController().navigate(R.id.action_equipmentList_to_popup_window)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}