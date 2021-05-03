package com.reavature.beefcake.ui.reservation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.reavature.beefcake.auth_model.MemberDatabase
import com.reavature.beefcake.databinding.FragmentMakeReservationBinding
import com.reavature.beefcake.reservation_model.Reservation
import com.reavature.beefcake.reservation_model.ReservationDB



/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

class MakeReservationFragment : Fragment() {

    lateinit var reservation : Reservation

    /** This is the view binding that is made to reference the views of the Fragment Home Page */
    private var _binding: FragmentMakeReservationBinding? = null

    /** This is the binding that retrieves the binding to reference the views */
    private val binding: FragmentMakeReservationBinding
        get() = _binding!!

    var reservationDate: String = ""

    var reservationTime: String = ""

    var reservationEquipment: String = ""

    @SuppressLint("LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentMakeReservationBinding.inflate(inflater, container, false)

        reservation = Reservation("","","","","")

        binding.calendarView.setOnDateChangeListener { calendarView, year, month , day ->



             reservationDate = "0${month + 1}/$day/$year"

//            Toast.makeText(this.requireContext(),"Selected date: $reservationDate" , Toast.LENGTH_SHORT).show()

        }

        ArrayAdapter.createFromResource(this.requireContext(), com.reavature.beefcake.R.array.time_arr, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                binding.spnSelectTime.adapter = adapter
            }

        binding.spnSelectTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("LogNotTimber")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {


                reservationTime = binding.spnSelectTime.selectedItem.toString()
                Log.v("Reservation Time & Date", reservationTime + reservationDate)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }


        ArrayAdapter.createFromResource(this.requireContext(), com.reavature.beefcake.R.array.equipment_arr, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                binding.spnSelectEquipment.adapter = adapter
            }

        binding.spnSelectEquipment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("LogNotTimber")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {


                reservationEquipment = binding.spnSelectEquipment.selectedItem.toString()
                Log.v("Reservation Time & Date", reservationTime + reservationDate + reservationEquipment)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }







        binding.btnFindRooms.setOnClickListener {

            Log.v("Reservation T&D onCLick", reservationTime + reservationDate + reservationEquipment)

//            Toast.makeText(context,"Date: $reservationDate, Time:$reservationTime", Toast.LENGTH_LONG).show()

         reservation.apply {
                date = reservationDate
                time = reservationTime
                equipment = reservationEquipment
                locationName = "chicago, IL"
                roomid = "1"
            }

            Log.v("Reservation Time & Date", reservation.date + reservation.time + reservation.locationName + reservation.roomid + reservation.equipment)


            ReservationDB.captureTimeAndDate(MemberDatabase.currentMember?.username.toString(),reservationDate,reservationTime,reservationEquipment)

            ReservationDB.currentReservation = ReservationDB.userReservation[MemberDatabase.currentMember?.username]
            ReservationDB.currentReservation?.date = reservationDate
            ReservationDB.currentReservation?.time = reservationTime
            ReservationDB.currentReservation?.equipment = reservationEquipment

            Log.v("Reservation Time & Date", "${ReservationDB.userReservation}")
            Log.v("Reservation Time & Date", "${ReservationDB.userReservation},  ${ReservationDB.currentReservation?.date},  ${ReservationDB.currentReservation?.time},${ReservationDB.currentReservation?.equipment} ")

            Log.v("Same Res Time & Date", "${ReservationDB.sameReservation},  ${ReservationDB.sameReservation?.date},  ${ReservationDB.sameReservation?.time}")

            if(reservation.date == ""){
                Toast.makeText(context, "Please select a date", Toast.LENGTH_LONG).show()
            } else if( ReservationDB.currentReservation?.date == ReservationDB.sameReservation?.date &&  ReservationDB.currentReservation?.time == ReservationDB.sameReservation?.time ){


                Toast.makeText(context, "You already have a reservation for this date and time", Toast.LENGTH_LONG).show()

            } else {
                val navigateAction =
                    MakeReservationFragmentDirections.actionMakeReservationFragmentToRoomListFragment(
                        reservationDate,
                        reservationTime,
                        reservationEquipment
                    )
                findNavController().navigate(navigateAction)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.show()
    }


}