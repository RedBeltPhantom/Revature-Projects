package com.reavature.beefcake.ui.reservation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.reavature.beefcake.auth_model.MemberDatabase
import com.reavature.beefcake.databinding.FragmentReservationConfirmationBinding
import com.reavature.beefcake.reservation_model.ReservationDB
import java.text.SimpleDateFormat


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */


class ReservationConfirmationFragment : Fragment() {


    private var _binding: FragmentReservationConfirmationBinding? = null

    private val binding: FragmentReservationConfirmationBinding
                 get() = _binding!!

    @SuppressLint("SimpleDateFormat", "LogNotTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        _binding = FragmentReservationConfirmationBinding.inflate(inflater, container, false)

        val args: ReservationConfirmationFragmentArgs by navArgs()

        binding.tvDate.text = args.date
        binding.tvTime.text = args.time
        binding.tvLocation.text = args.location
        binding.tvResID.text = args.resID
        val location = args.location
        val roomID = args.roomID
        val resID = args.resID

        ReservationDB.checkForSameReservation(MemberDatabase.currentMember?.username.toString(),args.date,args.time)

        ReservationDB.sameReservation = ReservationDB.failedReservation[MemberDatabase.currentMember?.username]
        ReservationDB.sameReservation?.date = args.date
        ReservationDB.sameReservation?.time = args.time


        Log.v("Same Res Time & Date", "${ReservationDB.sameReservation}")
        Log.v("Same Res Time & Date", "${ReservationDB.sameReservation},  ${ReservationDB.sameReservation?.date},  ${ReservationDB.sameReservation?.time}")



        binding.btnGoHome.setOnClickListener {
            val action = ReservationConfirmationFragmentDirections.actionReservationConfirmationFragmentToMainMenu()
            findNavController().navigate(action)

        }

        if (args.date.isEmpty())
            binding.btnGoToCal.isVisible = false
        // Save booking details in calendar
        binding.btnGoToCal.setOnClickListener {
            val date = SimpleDateFormat("MM/d/yyyy-h:mmaa").parse("${args.date}-${args.time}")
            val calIntent = Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .putExtra(Events.TITLE, "Workout Room $roomID")
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date.time)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, date.time + 3600000)
                .putExtra(Events.EVENT_LOCATION, location)
                .putExtra(Events.DESCRIPTION, "Workout Room booking via Fit 4 U \n Confirmation ID: $resID") // Description
                .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
            startActivity(calIntent)
        }

        return binding.root
    }
}