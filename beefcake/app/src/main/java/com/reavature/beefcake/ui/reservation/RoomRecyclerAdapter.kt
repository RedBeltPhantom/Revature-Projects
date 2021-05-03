package com.reavature.beefcake.ui.reservation

import android.annotation.SuppressLint
import android.graphics.Movie
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reavature.beefcake.auth_model.MemberDatabase
import com.reavature.beefcake.databinding.RoomListItemBinding
import com.reavature.beefcake.network.RetrofitAPIService
import com.reavature.beefcake.reservation_model.Reservation
import com.reavature.beefcake.room_model.RoomResponse
import com.reavature.beefcake.ui.reservation.fragments.RoomListFragmentDirections
import kotlinx.android.synthetic.main.room_list_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */


class RoomRecyclerAdapter(private val reservation: Reservation) :
    ListAdapter<RoomResponse, RoomRecyclerAdapter.RoomViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<RoomResponse>() {
        override fun areItemsTheSame(oldItem: RoomResponse, newItem: RoomResponse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RoomResponse, newItem: RoomResponse): Boolean {
            return oldItem.ID == newItem.ID



        }


    }

    class RoomViewHolder(var binding: RoomListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(roomResponse: RoomResponse) {

            binding.property = roomResponse
            binding.executePendingBindings()


        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoomRecyclerAdapter.RoomViewHolder {

        return RoomViewHolder(RoomListItemBinding.inflate(LayoutInflater.from(parent.context)))


    }


//    override fun getItemId(position: Int): Long {
//        return .getId()
//    }

    @SuppressLint("LogNotTimber")
    override fun onBindViewHolder(holder: RoomRecyclerAdapter.RoomViewHolder, position: Int) {


        val room = getItem(position)
        val ID = room.ID

              holder.binding.root.layoutParams =
                  LinearLayoutCompat.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                      .apply { setMargins(10, 30, 10, 30)
                      }
                                                                                      


        holder.itemView.btn_complete_reservation.setOnClickListener {

            Log.e("Information:", "status: $ID")

            val date = reservation.date
            val time = reservation.time

            //add a when statement to pass the correct ID
            Log.e("Information:", "status: $date $time + chicago,IL $ID")

            val jsonObject = JSONObject()
            jsonObject.put("date", date)
            jsonObject.put("time", time)
            jsonObject.put("roomID", ID)

//             Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()

//            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
            val requestBody =
                jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            CoroutineScope(Dispatchers.IO).launch {
                // Do the POST request and get response
                val response = RetrofitAPIService.instance2.createReservation(
                    requestBody,
                    "Bearer ${MemberDatabase.currentMember?.token.toString()}"
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.e(
                            "RETROFIT SUCCESSFUL",
                            "${response.code()} + ${response.body()?.time}, ${response.body()?.date},  ${response.body()?.LocationName}, ${response.body()?.resID}"
                        )
                        //This is the navigation action that goes to make reservation confirmation
                        val action =
                            RoomListFragmentDirections.actionRoomListFragmentToReservationConfirmationFragment(
                                response.body()?.date.toString(),
                                response.body()?.time.toString(),
                                response.body()?.roomID.toString(),
                                response.body()?.LocationName.toString(),
                                response.body()?.resID.toString()
                            )
                        holder.itemView.findNavController().navigate(action)

                    } else {
                        Log.e("RETROFIT_ERROR", response.code().toString())
                    }
                }
            }
        }
        holder.bind(room)
    }

}








