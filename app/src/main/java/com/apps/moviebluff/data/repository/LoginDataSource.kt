package com.apps.moviebluff.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apps.moviebluff.data.POJO.Login
import com.apps.moviebluff.data.api.LoginInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.lang.Exception


class LoginDataSource(private val apiService:LoginInterface,private val compositeDisposable: CompositeDisposable) {
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    private val loginResponse = MutableLiveData<Login>()
    val finalLoginResponse:LiveData<Login>
    get() = loginResponse

//    fun registerUser(){
//        try{
////            compositeDisposable.add(apiService.getRegisterDetails()
////                .subscribeOn(Schedulers.io())
////                .subscribe(
////                    {
////                        networkState.postValue(NetworkState.LOADING)
////
////
////                    },
////                    {
////                        networkState.postValue(NetworkState.ERROR)
////
////                        Log.e("MovieError",it.message)
////                    }
////                )
////            )
//        }catch (ex:Exception){
//
//        }
//    }
    var myToken:String?=null
    fun loginUser(email:String,password:String){
        try{
            compositeDisposable.add(
                apiService.loginDetails(email,password)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        loginResponse.postValue(it)

                        Log.i("email,pass", it.toString())

//                        Log.d("Token mess",it.token)
//                        myToken = it.token

                    },
                    {

                        Log.e("MyErrors here",it.message)
                        Log.i("me her:",loginResponse.value.toString())
                    }
                )
            )
        }catch (ex:Exception){
            Log.i("failed",loginResponse.value.toString())


        }
    }
}