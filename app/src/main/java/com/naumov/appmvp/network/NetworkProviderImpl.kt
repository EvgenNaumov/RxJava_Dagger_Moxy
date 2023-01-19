package com.naumov.appmvp.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.naumov.appmvp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProviderImpl:INetworkProvider {

    override fun createGsonFactory(): Gson = GsonBuilder()
    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    .excludeFieldsWithoutExposeAnnotation()
    .create()

    override fun createRetrofit(): Retrofit = Retrofit.Builder()
    .client(createClient())
    .baseUrl(BuildConfig.SERVER_URL)
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(createGsonFactory()))
    .build()

    fun createClient(): OkHttpClient  = OkHttpClient.Builder().build()
}