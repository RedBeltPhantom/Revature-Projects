package com.reavature.beefcake.ui.healthprofile.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reavature.beefcake.auth_model.MemberDatabase
import com.reavature.beefcake.network.RetrofitAPIService
import com.reavature.beefcake.healthprofile_model.HealthProfileResponse
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception



/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

class HealthProfileViewModel : ViewModel() {



    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    //mutable properties - change to List because Room the object contains multiple properties such as ID, Room
    private val _userProperties = MutableLiveData<Response<HealthProfileResponse>>()

    //immutable properties // binds within the XML file
    val userProperties: MutableLiveData<Response<HealthProfileResponse>>
        get() = _userProperties

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * initializes the data right away
     */
    init {
        getUser()

    }

    @SuppressLint("LogNotTimber")
    private fun getUser() {


        viewModelScope.launch {
            try {

                _userProperties.value = RetrofitAPIService.instance2.getProfile("Bearer ${MemberDatabase.currentMember?.token}")

                _response.value = "success"

                Log.v("API STATUS", "SUCCESS, ${userProperties.value?.body()?.username} ")



            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.v("API STATUS", "FAIL")
            }

        }

    }




}