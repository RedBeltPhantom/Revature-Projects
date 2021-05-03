package com.reavature.beefcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.reavature.beefcake.database.Equipment
import com.reavature.beefcake.databinding.FragmentEquipmentListBinding
class EquipmentList : Fragment() {
    private lateinit var data: ArrayList<Equipment>
    private lateinit var binding: FragmentEquipmentListBinding
    private lateinit var equipmentAdapter: EquipmentAdapter

    val ourEquipment = DataSource.createEquipmentList()

    companion object{
        lateinit var equipmentPosition: Equipment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEquipmentListBinding.inflate(layoutInflater)

        data = ourEquipment

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            equipmentAdapter = EquipmentAdapter(data)
            adapter = equipmentAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.show()
    }
}