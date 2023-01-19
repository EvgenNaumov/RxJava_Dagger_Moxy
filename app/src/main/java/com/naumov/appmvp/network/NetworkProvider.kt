package com.naumov.appmvp.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.naumov.appmvp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {

    val userApi by lazy { createRetrofit().create(UsersApi::class.java) }

    private fun createGsonFactory() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .client(creatClient())
        .baseUrl(BuildConfig.SERVER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(createGsonFactory()))
        .build()

    private fun creatClient(): OkHttpClient = OkHttpClient.Builder().build()
}