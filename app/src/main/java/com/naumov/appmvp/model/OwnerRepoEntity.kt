package com.naumov.appmvp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwnerRepoEntity (
    val login:String,
    val id:Long
    ):Parcelable