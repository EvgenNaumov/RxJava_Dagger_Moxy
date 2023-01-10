package com.naumov.appmvp.network

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.naumov.appmvp.model.OwnerRepoEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepoDto(
    @Expose
    @SerializedName("id")
    val id: Long,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("forks_url")
    val forksUrl: String,
    @Expose
    @SerializedName("full_name")
    val fullName: String,
    @Expose
    @SerializedName("owner")
    val owner: OwnerRepoEntity

) : Parcelable