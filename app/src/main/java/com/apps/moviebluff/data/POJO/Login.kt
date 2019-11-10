package com.apps.moviebluff.data.POJO

data class Login(
    val isSuccessful: String,
    val message: String,
    val user: List<User>
)