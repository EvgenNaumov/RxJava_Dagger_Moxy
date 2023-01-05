package com.naumov.appmvp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepoEntity(
    val id:Long,
    val login:String,
    val name:String,
    val forksUrl:String,
    val fullName:String
) : Parcelable
