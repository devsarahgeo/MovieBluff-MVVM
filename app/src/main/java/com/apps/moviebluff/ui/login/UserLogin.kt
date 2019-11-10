package com.apps.moviebluff.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
//import com.amazonaws.mobile.client.AWSMobileClient
//import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration
//import com.amazonaws.mobileconnectors.pinpoint.PinpointManager
import com.apps.moviebluff.R
import com.apps.moviebluff.data.POJO.Login
import com.apps.moviebluff.data.api.LoginDbClient
import com.apps.moviebluff.data.api.LoginInterface
import com.apps.moviebluff.databinding.ActivityUserLoginBinding
import com.apps.moviebluff.ui.util.SaveSettings
import com.apps.moviebluff.ui.util.hide
import com.apps.moviebluff.ui.util.show
import com.apps.moviebluff.ui.util.toast
import kotlinx.android.synthetic.main.activity_user_login.*
import okhttp3.ResponseBody
//import com.amazonaws.mobile.auth.ui.SignInUI;
import com.apps.moviebluff.ui.popular_movie.MainActivity


class UserLogin : AppCompatActivity(),AuthListener {
    private lateinit var loginrepo:AuthRepo
    private lateinit var loginViewModel: AuthViewModel
//    companion object {
//        private val TAG: String = this::class.java.simpleName
//        var pinpointManager: PinpointManager? = null
//
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService:LoginInterface=LoginDbClient.getClient()
        loginrepo=AuthRepo(apiService)
//        setContentView(R.layout.activity_user_login)
        val loginBinding: ActivityUserLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_login)
//        val loginViewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        loginViewModel= getViewModel()

        loginBinding.loginviewmodel = loginViewModel
        loginViewModel.authListener = this

    }

    private fun getViewModel(): AuthViewModel {
        return ViewModelProviders.of(this,object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return AuthViewModel(loginrepo) as T
            }

        })[AuthViewModel::class.java]

    }

    override fun onSuccess(loginDetails: LiveData<Login>) {
        progress_bar.hide()
        toast(loginDetails.value.toString())
        loginDetails.observe(this, Observer {
            toast(it.toString())
            val userId = it.user[0].user_id
            val username = it.user[0].first_name
            val saveSettings=SaveSettings(this)
            saveSettings.isLoggedOut()
            saveSettings.saveSettings(userId,username)
        })


    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }

    override fun onStarted() {
        progress_bar.show()

    }

}
