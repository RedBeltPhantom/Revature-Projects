package com.reavature.beefcake.ui.reservation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reavature.beefcake.auth_model.MemberDatabase
import com.reavature.beefcake.network.RetrofitAPIService
import com.reavature.beefcake.reservation_model.ReservationDB
import com.reavature.beefcake.room_model.RoomResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

class RoomListViewModel(): ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    //mutable properties - change to List because Room the object contains multiple properties such as ID, Room
    private val _properties = MutableLiveData<Response<List<RoomResponse>>>()

    //immutable properties
    val properties: MutableLiveData<Response<List<RoomResponse>>>
        get() = _properties

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response



    /**
     * initializes the data right away
     */
    init {
        getAllRooms()

    }

    @SuppressLint("LogNotTimber")
    private fun getAllRooms(){

        val jsonObject = JSONObject()

        jsonObject.put("date", ReservationDB.currentReservation?.date)
        jsonObject.put("time",ReservationDB.currentReservation?.time)
        jsonObject.put("equipment",ReservationDB.currentReservation?.equipment)

        Log.v("API STATUS", "SUCCESS ${ReservationDB.currentReservation?.date}, ${ReservationDB.currentReservation?.time},${ReservationDB.currentReservation?.equipment}")

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch {
            try {
                _properties.value  = RetrofitAPIService.instance2.getProperties(requestBody,"Bearer ${MemberDatabase.currentMember?.token.toString()}")

//               val error = "FAIL Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $"
//                if(properties.value?.body().toString() == error ){
//                    Log.v("FAILED TO BOOK", properties.value?.body().toString())
//                } else


                _response.value = "success"
                Log.v("API STATUS", properties.value?.body().toString())


            } catch (e: Exception)

            {
                _response.value = "Failure: ${e.message}"
                Log.v("API STATUS", "FAIL ${e.message}")
            }

        }

    }

}