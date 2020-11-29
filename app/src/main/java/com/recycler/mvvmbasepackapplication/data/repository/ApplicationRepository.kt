package com.recycler.mvvmbasepackapplication.data.repository

import com.recycler.mvvmbasepackapplication.data.remote.ApiHelper
import javax.inject.Inject

class ApplicationRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()
}