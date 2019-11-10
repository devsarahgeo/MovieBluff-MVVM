package com.apps.moviebluff.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.apps.moviebluff.data.POJO.Result
import com.apps.moviebluff.data.api.API_KEY
import com.apps.moviebluff.data.api.FIRST_PAGE
import com.apps.moviebluff.data.api.MovieDbInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(private val apiService:MovieDbInterface,private val compositeDisposable: CompositeDisposable):PageKeyedDataSource<Int,Result>() {
    private var page = FIRST_PAGE
    val networkState:MutableLiveData<NetworkState> = MutableLiveData()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(apiService.getPopularMovie(API_KEY,page)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    callback.onResult(it.results,null,page+1)
                    networkState.postValue(NetworkState.LOADED)
                },
                {
                    networkState.postValue(NetworkState.ERROR)
                    Log.e("MovieError",it.message)
                }
            )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(apiService.getPopularMovie(API_KEY,params.key)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    if(it.total_pages>params.key){
                        callback.onResult(it.results,params.key+1)
                        networkState.postValue(NetworkState.LOADED)
                    }else{
                        networkState.postValue(NetworkState.END_OF_LIST)
                    }
                },
                {
                    networkState.postValue(NetworkState.ERROR)
                    Log.e("MovieError",it.message)
                }
            )
        )

    }
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
    }
}