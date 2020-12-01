package com.recycler.mvvmbasepackapplication.data.remote

import com.recycler.mvvmbasepackapplication.data.models.User
import retrofit2.Response
import retrofit2.http.GET

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}