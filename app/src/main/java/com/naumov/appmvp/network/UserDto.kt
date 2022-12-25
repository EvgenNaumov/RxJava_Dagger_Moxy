package com.naumov.appmvp.network

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDto(
    @Expose
    @SerializedName("id")
    val id: Long,
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String
)

