package com.reavature.beefcake.auth_model


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */


data class RegistrationResponse(
    var message:String
)

data class LoginResponse(
    var token:String
)



data class User(
    var RegUsername: String,
    var ReqPassword: String,
    var ReqEmail: String,
    var ReqAge: String,
    var ReqAddress: String,
    var ReqPhone: String,
    var ReqHeight: String,
    var ReqWeight: String,
    var ReqBody: String,
    var ReqSex: String,
)

