package com.apps.moviebluff.ui.popular_movie

import android.content.Intent
import android.view.MenuItem
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.apps.moviebluff.R
import com.apps.moviebluff.data.POJO.Result
import com.apps.moviebluff.data.repository.NetworkState
import com.apps.moviebluff.ui.login.AuthListener
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(val movieRepo:MoviePagedListRepo): ViewModel() {
    var authListener:AuthListener?=null


    private val compositeDisposable = CompositeDisposable()
    val moviePagedList:LiveData<PagedList<Result>> by lazy{
        movieRepo.fetchPopularMoviesPagedList(compositeDisposable)
    }

    val networkState:LiveData<NetworkState> by lazy{
        movieRepo.getPopularNetworkState()
    }

    fun listisEmpty():Boolean{
        return moviePagedList.value?.isEmpty()?:true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }



}