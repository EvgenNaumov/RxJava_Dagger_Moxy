package com.naumov.appmvp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class GithubUserEntity(
    val id:Long,
    val login: String,
    val description: String?,
    val avatarUrl: String?
) : Parcelable

