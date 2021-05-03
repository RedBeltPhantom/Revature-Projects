package com.reavature.beefcake.auth_model


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

import com.reavature.beefcake.database.Member

//data class AuthToken(
//    var AuthToken:String
//)

object MemberDatabase {

    var currentMember: Member? = null
    var memberList: MutableMap<String,Member> = mutableMapOf()


    fun addMemberToken(username:String,token:String):Boolean {
        memberList[username] = Member(username,token)
        return true
    }


}

