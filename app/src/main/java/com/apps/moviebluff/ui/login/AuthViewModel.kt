package com.apps.moviebluff.ui.login

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apps.moviebluff.data.POJO.Login
import com.apps.moviebluff.data.repository.NetworkState
import com.apps.moviebluff.ui.util.SaveSettings
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody

class AuthViewModel(val authRepo:AuthRepo):ViewModel() {
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    var authListener:AuthListener?=null
    val compositeDisposable = CompositeDisposable()

    var email:String=""
    var password:String=""
    var firstName:String=""

//    fun buRegister(view: View){
//        authListener?.onStarted()
//
//        if (firstName.isNullOrEmpty()){
//
//        }
//        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
//            authListener?.onFailure("Invalid email or Password")
//
//            return
//
//        }
//        authListener?.onSuccess()
//    }
    fun buLogin(view: View){
        authListener?.onStarted()

//        if (firstName.isNullOrEmpty()){
//
//        }
        if(email.isEmpty() || password.isEmpty()){
            authListener?.onFailure("Invalid email or Password")

            return

        }

        val loginDetails:LiveData<Login> by lazy {
            authRepo.fetchLoginDetails(compositeDisposable,email,password)

        }

    authListener?.onSuccess(loginDetails)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}