package com.apps.moviebluff.data.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson



const val LOGIN_BASE_URL =  "http://192.168.1.2/MovieBluffServer/"


object LoginDbClient {

    fun getClient(): LoginInterface {
        val gson = GsonBuilder()
            .setLenient()
            .create()



        return Retrofit.Builder()
            .baseUrl(LOGIN_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LoginInterface::class.java)

    }
}