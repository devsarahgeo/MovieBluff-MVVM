package com.apps.moviebluff.ui.singlemov_details

import androidx.lifecycle.LiveData
import com.apps.moviebluff.data.POJO.MovieDetails
import com.apps.moviebluff.data.api.MovieDbInterface
import com.apps.moviebluff.data.repository.MovieDetailsNetworkDataSource
import com.apps.moviebluff.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieDetailRepo(private val apiService:MovieDbInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable,movieId:Int):LiveData<MovieDetails>{
        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadMovResponse
    }

    fun getMovieNetWorkstate():LiveData<NetworkState>{
        return movieDetailsNetworkDataSource.networkState
    }

}