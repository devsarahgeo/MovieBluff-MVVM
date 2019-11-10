package com.apps.moviebluff.ui.login

import androidx.lifecycle.LiveData
import com.apps.moviebluff.data.POJO.Login
import okhttp3.ResponseBody

interface AuthListener {
    fun onSuccess(loginDetails: LiveData<Login>)
    fun onFailure(message:String)
    fun onStarted()
}