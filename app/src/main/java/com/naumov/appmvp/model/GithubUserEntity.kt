package com.naumov.appmvp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserEntity(val login: String) : Parcelable