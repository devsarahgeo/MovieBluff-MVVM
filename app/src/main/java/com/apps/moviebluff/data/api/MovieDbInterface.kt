package com.apps.moviebluff.data.api

import com.apps.moviebluff.data.POJO.MovieDetails
import com.apps.moviebluff.data.POJO.MoviesPopular
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbInterface {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey:String,@Query("page") page:Int):Single<MoviesPopular>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")id:Int, @Query("api_key") apiKey:String):Single<MovieDetails>
}