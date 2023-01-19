package com.naumov.appmvp.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.naumov.appmvp.BuildConfig
import com.naumov.appmvp.network.INetworkProvider
import com.naumov.appmvp.network.UsersApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl():String = "https://api.github.com/"

    @Provides
    fun userApi(@Named("baseUrl") baseUrl:String, gson:Gson):UsersApi = Retrofit.Builder()
        .client(createClient())
        .baseUrl(BuildConfig.SERVER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(UsersApi::class.java)

    @Singleton
    @Provides
    fun createClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun createGsonFactory():Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()
}