package com.naumov.appmvp.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface INetworkProvider {

    fun createGsonFactory():Gson
    fun createRetrofit():Retrofit
}