package com.apps.moviebluff.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.apps.moviebluff.data.POJO.Result
import com.apps.moviebluff.data.api.MovieDbInterface
import com.apps.moviebluff.data.api.POST_PER_PAGE
import com.apps.moviebluff.data.repository.MovieDataSource
import com.apps.moviebluff.data.repository.MovieDataSourceFactory
import com.apps.moviebluff.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepo(private val apiService:MovieDbInterface) {

    lateinit var movieDataSourceFactory:MovieDataSourceFactory
    lateinit var moviePagedList:LiveData<PagedList<Result>>

    fun fetchPopularMoviesPagedList(compositeDisposable: CompositeDisposable):LiveData<PagedList<Result>>{
        movieDataSourceFactory = MovieDataSourceFactory(apiService,compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()
        moviePagedList = LivePagedListBuilder(movieDataSourceFactory,config).build()
        return moviePagedList
    }

    fun getPopularNetworkState():LiveData<NetworkState>{
        //getting network state as mutable livedata from movieslivedatasource and transforming network state to livedata
        return Transformations.switchMap<MovieDataSource,NetworkState>(
            movieDataSourceFactory.moviesLiveDataSource,MovieDataSource::networkState
        )
    }
}