package com.reavature.beefcake.network

import com.reavature.beefcake.auth_model.LoginResponse
import com.reavature.beefcake.auth_model.RegistrationResponse
import com.reavature.beefcake.healthprofile_model.HealthProfileResponse
import com.reavature.beefcake.reservation_model.ReservationResponse
import com.reavature.beefcake.room_model.RoomResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */


//Request
//{
//
//    "username" : "user",
//    "password" : "pass",
//    "email" : "abc@mail.com",
//    "address" : "123 Street",
//    "phone" : 1234567890,
//    "body" : "mesomorph",
//    "height" : 72,
//    "weight" : 180,
//    "sex" : "male"
//
//}

//Response

//{
//
//    "status" : 200,
//    "register" : "success"

//}

interface Methods_API {
    

    @POST("register")
    suspend fun createUser(@Body requestBody: RequestBody): Response<RegistrationResponse>

    @POST("login")
    suspend fun loginUser(
        @Body requestBody: RequestBody): Response<LoginResponse>

    @POST("profile")
    suspend fun getProfile(@Header("Authorization") token:String): Response<HealthProfileResponse>

    @POST("rooms")
    suspend fun getProperties( @Body requestBody:RequestBody ,@Header("Authorization") token:String,): Response<List<RoomResponse>>

    @POST("reserve")
    suspend fun createReservation( @Body requestBody:RequestBody ,@Header("Authorization") token:String,): Response<ReservationResponse>

    @POST("profileupdate")
    suspend fun updateProfile(@Body requestBody: RequestBody, @Header("Authorization") token:String): Response<HealthProfileResponse>
}

