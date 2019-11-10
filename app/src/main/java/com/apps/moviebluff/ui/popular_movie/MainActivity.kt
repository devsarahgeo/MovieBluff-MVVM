package com.apps.moviebluff.ui.popular_movie

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.apps.moviebluff.Fragments.FindFriends
import com.apps.moviebluff.Fragments.PopularMovies
import com.apps.moviebluff.R
import com.apps.moviebluff.ui.util.SaveSettings
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),FindFriends.OnFragmentInteractionListener,PopularMovies.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var movieRepo:MoviePagedListRepo
    private lateinit var mainViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveSettings = SaveSettings(this)
        saveSettings.chkisLoogedIn()
        if(!SaveSettings.isLogOut){
            saveSettings.loadSettings()
        }
        bottomNav.setOnNavigationItemSelectedListener(mNavigationItemSelected)
        replaceFragment(FindFriends())



    }
    private val mNavigationItemSelected =  BottomNavigationView.OnNavigationItemSelectedListener {item->
            when (item.getItemId()) {
                R.id.findFriends->{
                    println("friends")
                    replaceFragment(com.apps.moviebluff.Fragments.FindFriends())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Movies->{
                    println("movies")
                    replaceFragment(PopularMovies())

                    return@OnNavigationItemSelectedListener true
                }
                R.id.Message->{
                    println("message")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.MyProfile->{
                    println("myprofile")
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false
        }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.findFriends->{
//                println("friends")
//                replaceFragment(com.apps.moviebluff.Fragments.FindFriends())
//                return true
//            }
//            R.id.Movies->{
//                println("movies")
//                replaceFragment(PopularMovies())
//
//                return true
//            }
//            R.id.Message->{
//                println("message")
//                return true
//            }
//            R.id.MyProfile->{
//                println("myprofile")
//                return true
//            }
//        }
//        return false
//    }
    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }



}
