package com.reavature.beefcake.reservation_model


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */


data class ReservationResponse(
    val date: String,
    val time: String,
    val roomID: String,
    val LocationName: String,
    val resID:String
)
