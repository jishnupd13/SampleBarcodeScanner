package com.recycler.mvvmbasepackapplication.data.remote

import com.recycler.mvvmbasepackapplication.data.models.User
import retrofit2.Response

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}