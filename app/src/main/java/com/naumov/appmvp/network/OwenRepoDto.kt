package com.naumov.appmvp.network

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwenRepoDto(
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("id")
    val id: Long
) : Parcelable
