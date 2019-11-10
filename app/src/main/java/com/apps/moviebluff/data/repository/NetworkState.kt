package com.apps.moviebluff.data.repository

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status:Status,val msg:String) {
    companion object{
        val LOADING:NetworkState
        val LOADED:NetworkState
        val ERROR:NetworkState
        val END_OF_LIST:NetworkState
        init {
            LOADING = NetworkState(Status.RUNNING,"Loading")
            LOADED=NetworkState(Status.SUCCESS,"Loaded")
            ERROR = NetworkState(Status.FAILED,"FAILED")
            END_OF_LIST = NetworkState(Status.FAILED,"Reached end of list")
        }
    }


}