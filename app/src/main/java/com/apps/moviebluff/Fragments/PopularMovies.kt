package com.apps.moviebluff.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import com.apps.moviebluff.R
import com.apps.moviebluff.data.api.MovieDbClient
import com.apps.moviebluff.data.api.MovieDbInterface
import com.apps.moviebluff.data.repository.NetworkState
import com.apps.moviebluff.ui.popular_movie.MainActivityViewModel
import com.apps.moviebluff.ui.popular_movie.MoviePagedListRepo
import com.apps.moviebluff.ui.popular_movie.PopularMoviePagedListAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PopularMovies.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PopularMovies.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopularMovies : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mainViewModel: MainActivityViewModel
    lateinit var movieRepo: MoviePagedListRepo



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        val apiService: MovieDbInterface = MovieDbClient.getClient()
        movieRepo = MoviePagedListRepo(apiService)

        val movieAdapter = PopularMoviePagedListAdapter(context!!)

        val gridLayoutManager = GridLayoutManager(context,2)

        gridLayoutManager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if(viewType==movieAdapter.MOVIE_VIEW_TYPE){
                    return 1
                }else{
                    return 3
                }
            }

        }

        rvMovies.layoutManager = gridLayoutManager
        rvMovies.setHasFixedSize(true)
        rvMovies.adapter = movieAdapter

        mainViewModel= getViewModel()


        mainViewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)

        })
        mainViewModel.networkState.observe(this, Observer {
            pbMain.visibility = if(mainViewModel.listisEmpty() && it== NetworkState.LOADING) View.VISIBLE else View.GONE
            tvErrorMain.visibility = if(mainViewModel.listisEmpty() && it== NetworkState.ERROR) View.VISIBLE else View.GONE
        })

    }
    private fun getViewModel(): MainActivityViewModel {
        return ViewModelProviders.of(this,object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainActivityViewModel(movieRepo) as T
            }

        })[MainActivityViewModel::class.java]
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PopularMovies.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PopularMovies().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
