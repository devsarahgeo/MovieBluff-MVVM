package com.apps.moviebluff.data.api

import com.apps.moviebluff.data.POJO.Login
import com.apps.moviebluff.data.POJO.Register
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface LoginInterface {
    @FormUrlEncoded
    @POST("register.php")
    fun registerDetails(@Field("first_name") firstName:String, @Field("email")email:String, @Field("password")password:String): Response<Register>

    @FormUrlEncoded
    @POST("login.php")
//    fun loginDetails(@Query("op")op:String, @Field("email")email:String, @Field("password")password:String): Single<ResponseBody>
    fun loginDetails(@Field("email") email:String, @Field("password") password:String): Single<Login>

    @POST("secret.php")
    fun getToken(@Header("Authorization") authToken:String):Single<ResponseBody>
}