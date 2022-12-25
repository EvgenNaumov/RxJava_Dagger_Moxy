package com.naumov.appmvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserEntity(
    val login: String,
    val description: String,
    val avatarUrl: String
) : Parcelable

