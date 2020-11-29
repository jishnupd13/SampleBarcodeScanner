package com.recycler.mvvmbasepackapplication.data.remote

import com.recycler.mvvmbasepackapplication.data.models.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}