package com.apps.moviebluff.ui.singlemov_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.apps.moviebluff.R
import com.apps.moviebluff.data.POJO.MovieDetails
import com.apps.moviebluff.data.api.IMAGE_URL
import com.apps.moviebluff.data.api.MovieDbClient
import com.apps.moviebluff.data.api.MovieDbInterface
import com.apps.moviebluff.data.repository.NetworkState
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*

@Suppress("UNCHECKED_CAST")
class SingleMovie : AppCompatActivity() {
    private lateinit var moviesRepo:MovieDetailRepo
    private lateinit var viewModel:SingleMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
        val movieId:Int = intent.getIntExtra("movieId",1)
        val apiService:MovieDbInterface = MovieDbClient.getClient()
        moviesRepo = MovieDetailRepo(apiService)
        viewModel = getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })
        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if(it== NetworkState.LOADING) View.VISIBLE else View.GONE
            tvError.visibility = if(it==NetworkState.ERROR) View.VISIBLE else View.GONE
        })


    }

    private fun bindUI(it: MovieDetails) {
        tvMovname.text = it.originalTitle
        tvMovdate.text = it.releaseDate
        tvMovRuntime.text = it.runtime.toString()  + "minutes"
        val formatCurr = NumberFormat.getCurrencyInstance(Locale.US)
        tvBudget.text = formatCurr.format(it.budget)
        tvRating.text=it.voteAverage.toString()
        tvStatus.text = it.status.toString()
        for(i in 0..it.genres.size-1){
            val genre = it.genres[i].name + ","
            if(i==it.genres.size-1){
                tvGenre.text = genre

            }

        }
        val moviePostUrl = IMAGE_URL + it.posterPath
        Glide.with(this).load(moviePostUrl).into(ivPoster)

    }

    private fun getViewModel(movieId:Int):SingleMovieViewModel{
        return ViewModelProviders.of(this,object:ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SingleMovieViewModel(moviesRepo,movieId) as T
            }

        })[SingleMovieViewModel::class.java]
    }
}
