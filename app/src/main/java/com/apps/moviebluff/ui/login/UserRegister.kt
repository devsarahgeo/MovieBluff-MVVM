package com.apps.moviebluff.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.apps.moviebluff.R
import com.apps.moviebluff.data.POJO.Login
import com.apps.moviebluff.databinding.ActivityUserRegisterBinding
import com.apps.moviebluff.ui.util.toast
import okhttp3.ResponseBody

class UserRegister : AppCompatActivity(),AuthListener {
    override fun onSuccess(loginDetails: LiveData<Login>) {
        toast("Register Success")
    }

    override fun onFailure(message: String) {
        toast("Register Failed")
    }

    override fun onStarted() {
        toast("Register Started")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_user_register)
        val regBinding:ActivityUserRegisterBinding=DataBindingUtil.setContentView(this,R.layout.activity_user_register)
        val regViewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        regBinding.regviewmodel = regViewmodel

    }
}
