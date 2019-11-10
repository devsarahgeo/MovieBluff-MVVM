package com.apps.moviebluff.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL =  "https://api.themoviedb.org/3/"
const val API_KEY = "5073d959f94eda560e3715136909a287"
const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"
const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

object MovieDbClient {

fun getClient(): MovieDbInterface {
val requestInterceptor = Interceptor{chain ->
    val url = chain.request()
        .url()
        .newBuilder()
        .addQueryParameter("apiKey", API_KEY)
        .build()

    val request = chain.request()
        .newBuilder()
        .url(url)
        .build()

    return@Interceptor chain.proceed(request)
}
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .connectTimeout(60,TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieDbInterface::class.java)

}
}