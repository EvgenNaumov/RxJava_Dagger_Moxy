package com.naumov.appmvp.network

import com.google.gson.GsonBuilder
import com.naumov.appmvp.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {

    val userApi by lazy { createRetrofit().create(UsersApi::class.java) }

    private fun createGsonFactory() = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    fun createRetrofit()  = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(createGsonFactory()))
        .build()


}