package com.reavature.beefcake.ui.reservation.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.reavature.beefcake.databinding.FragmentRoomListBinding
import com.reavature.beefcake.reservation_model.Reservation
import com.reavature.beefcake.room_model.RoomResponse
import com.reavature.beefcake.ui.reservation.RoomRecyclerAdapter
import com.reavature.beefcake.ui.reservation.viewmodel.RoomListViewModel


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

open class RoomListFragment : Fragment() {

    private val viewModel: RoomListViewModel by lazy {
        ViewModelProvider(this).get(RoomListViewModel::class.java)
    }
    private lateinit var roomAdapter: RoomRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRoomListBinding.inflate(inflater, container, false)

        /** Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
         */
        binding.lifecycleOwner = this

        /** Giving the binding access to the OverviewViewModel
         */

        binding.viewModel = viewModel

        val args: RoomListFragmentArgs by navArgs()

        var reservationDate = args.ReservationDate

        var reservationTime = args.ReservationTime

        var reservationEquipment = args.ReservationEquipment

        roomAdapter = RoomRecyclerAdapter(
            Reservation(
                reservationDate,
                reservationTime,
                "Chicago,IL",
                "",
                reservationEquipment
            )
        )

//        binding.roomListRecycler.addItemDecoration(
//            DividerItemDecoration(
//                context,
//                LinearLayoutManager.VERTICAL
//            )
//        )

        binding.roomListRecycler.adapter = roomAdapter

        return binding.root
    }

}