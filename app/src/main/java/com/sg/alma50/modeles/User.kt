package com.sg.alma50.modeles

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uid: String = "",
    val userName: String = "",
    val lastName: String = "",
    val email: String = "",
    val image: String = "",
  //  val mobile: Long = 0,
    val gender: String = "",
    val moto: String = "החיים זה מה שזה ...",
    val time: Timestamp?=null,
    val profileCompleted: Int = 0,
    val password:String=""
) : Parcelable

/*data class User(
    var userName: String = "",
    var fullName: String = "",
    var email: String = "",
    var profileImage: String = "",
    var dio: String = "",
    var uid: String = ""
)*/