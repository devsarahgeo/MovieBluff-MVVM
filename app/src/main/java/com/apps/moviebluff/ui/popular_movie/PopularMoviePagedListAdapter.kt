package com.apps.moviebluff.ui.popular_movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apps.moviebluff.R
import com.apps.moviebluff.data.POJO.Result
import com.apps.moviebluff.data.api.IMAGE_URL
import com.apps.moviebluff.data.repository.NetworkState
import com.apps.moviebluff.ui.singlemov_details.SingleMovie
import com.apps.moviebluff.ui.singlemov_details.SingleMovieViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.network_state_item.view.*
import kotlinx.android.synthetic.main.recycler_movie_list.view.*

class PopularMoviePagedListAdapter(val context:Context):PagedListAdapter<Result,RecyclerView.ViewHolder>(MovieDiffCallback()) {
    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2
    private  var networkState:NetworkState?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:View
        if(viewType==MOVIE_VIEW_TYPE){
            view = layoutInflater.inflate(R.layout.recycler_movie_list,parent,false)
            return MovieViewHolder(view)
        }else{
            view = layoutInflater.inflate(R.layout.network_state_item,parent,false)
            return NetworkStateViewholder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position)==MOVIE_VIEW_TYPE){
            getItem(position)?.let { (holder as MovieViewHolder).bind(it,context) }
        }else{
            networkState?.let { (holder as NetworkStateViewholder).bind(it) }

        }
    }
    private fun hasExtraRow():Boolean{
        return networkState!=null && networkState!= NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if(hasExtraRow() && position == itemCount - 1){
            NETWORK_VIEW_TYPE
        }else{
            MOVIE_VIEW_TYPE
        }
    }
    class MovieDiffCallback:DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }

    }

    class MovieViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(movie:Result,context:Context){
            itemView.tvTitle.text = movie.title
            itemView.tvRelDate.text = movie.release_date

            val moviePosterUrl = IMAGE_URL + movie.poster_path
            Glide.with(itemView.context).load(moviePosterUrl).into(itemView.ivPosterMain)

            itemView.setOnClickListener{
                val intent  = Intent(context,SingleMovie::class.java)
                intent.putExtra("movieId",movie.id)
                context.startActivity(intent)
            }
        }
    }

    class NetworkStateViewholder(view:View):RecyclerView.ViewHolder(view){
        fun bind(networkState: NetworkState){
            if(networkState!=null && networkState==NetworkState.LOADING){
                itemView.pb_item.visibility = View.VISIBLE
            }else{
                itemView.pb_item.visibility = View.GONE
            }
            if(networkState!=null && networkState== NetworkState.ERROR || networkState== NetworkState.END_OF_LIST){
                itemView.tvError_item.visibility=View.VISIBLE
                itemView.tvError_item.text=networkState.msg
            }else{
                itemView.tvError_item.visibility=View.GONE

            }
        }
    }
    fun setNetworkState(newNetworkState: NetworkState){
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if(hadExtraRow!=hasExtraRow){
            if(hadExtraRow){
                notifyItemRemoved(super.getItemCount())
            }else{
                notifyItemInserted(super.getItemCount())
            }
        }else if(hasExtraRow && previousState!=newNetworkState){
            notifyItemChanged(itemCount-1)
        }
    }
}