package com.reavature.beefcake.reservation_model


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

data class Reservation(
    var date: String,
    var time: String,
    var locationName:String,
    var roomid: String,
    var equipment: String

)

data class ReservationData(
    var date: String,
    var time: String,
    var equipment:String,
)

data class SameReservation(
    var date: String,
    var time: String,
)


object ReservationDB {


    var currentReservation: ReservationData? = null
    var sameReservation : SameReservation? = null

    var userReservation: MutableMap<String, ReservationData> = mutableMapOf()
    var failedReservation: MutableMap<String,SameReservation> = mutableMapOf()

    fun captureTimeAndDate(username: String, date: String, time: String, equipment: String):Boolean {
        userReservation[username] = ReservationData(date,time,equipment)
        return true
    }

//    userReservation.put(username,ReservationData(date,time,equipment))

    fun checkForSameReservation(username: String, date: String, time: String): Boolean {
        failedReservation[username] = SameReservation(date,time)
        return true
    }


}

