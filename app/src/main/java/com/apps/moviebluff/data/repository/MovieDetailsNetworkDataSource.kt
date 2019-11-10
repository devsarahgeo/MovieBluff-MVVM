package com.apps.moviebluff.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apps.moviebluff.data.POJO.MovieDetails
import com.apps.moviebluff.data.api.API_KEY
import com.apps.moviebluff.data.api.MovieDbInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsNetworkDataSource (private val apiService:MovieDbInterface,private val compositeDisposable: CompositeDisposable){

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState:LiveData<NetworkState>
    get() =_networkState

    private val downloadMovDetailsResp = MutableLiveData<MovieDetails>()
    val downloadMovResponse:LiveData<MovieDetails>
    get() = downloadMovDetailsResp

    fun fetchMovieDetails(movieId:Int){
        

        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId, API_KEY)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                           _networkState.postValue(NetworkState.LOADED)
                            downloadMovDetailsResp.postValue(it)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("Movierror",it.message)

                        }
                    )
            )

        }catch (ex:Exception){
            Log.e("Movierror",ex.message)

        }
    }
}