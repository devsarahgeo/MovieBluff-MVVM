package com.apps.moviebluff.ui.login

import androidx.lifecycle.LiveData
import com.apps.moviebluff.data.POJO.Login
import com.apps.moviebluff.data.api.LoginInterface
import com.apps.moviebluff.data.repository.LoginDataSource
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody

class AuthRepo(val apiService:LoginInterface) {
    lateinit var loginDataSource:LoginDataSource

    fun fetchLoginDetails(compositeDisposable:CompositeDisposable,email:String,password:String):LiveData<Login>{
        loginDataSource= LoginDataSource(apiService,compositeDisposable)
        loginDataSource.loginUser(email,password)
        return loginDataSource.finalLoginResponse
    }
}