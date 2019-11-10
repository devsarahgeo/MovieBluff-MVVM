package com.apps.moviebluff.ui.singlemov_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.apps.moviebluff.data.POJO.MovieDetails
import com.apps.moviebluff.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepo:MovieDetailRepo,movieId:Int): ViewModel() {

    val compositeDisposable = CompositeDisposable()
    val movieDetails:LiveData<MovieDetails> by lazy {
        movieRepo.fetchSingleMovieDetails(compositeDisposable,movieId)
    }

    val networkState:LiveData<NetworkState> by lazy {
        movieRepo.getMovieNetWorkstate()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}