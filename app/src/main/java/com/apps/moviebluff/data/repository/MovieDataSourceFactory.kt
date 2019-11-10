package com.apps.moviebluff.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.apps.moviebluff.data.POJO.Result
import com.apps.moviebluff.data.api.MovieDbInterface
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(private val apiService: MovieDbInterface, private val compositeDisposable: CompositeDisposable):DataSource.Factory<Int,Result>() {
    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()
    override fun create(): DataSource<Int, Result> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}