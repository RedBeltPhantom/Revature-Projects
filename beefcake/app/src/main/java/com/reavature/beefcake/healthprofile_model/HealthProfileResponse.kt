package com.reavature.beefcake.healthprofile_model


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */


//{
//    "username" : "abc",
//    "email" : "abc@mail.com",
//    "address" : "123 Street",
//    "phone" : 1234567890,
//    "body" : "mesomorph",
//    "height" : 72,
//    "weight" : 180,
//    "sex" : "male"
//}
data class HealthProfileResponse(
    var username: String,
    val password:String,
    val email: String,
    val address: String,
    val phone: String,
    val body: String,
    val height: String,
    val weight: String,
    val sex: String,
    val age: String
)
