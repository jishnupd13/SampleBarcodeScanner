package com.recycler.mvvmbasepackapplication.data.repository

import com.recycler.mvvmbasepackapplication.data.remote.ApiHelper
import javax.inject.Inject

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


class ApplicationRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()
}