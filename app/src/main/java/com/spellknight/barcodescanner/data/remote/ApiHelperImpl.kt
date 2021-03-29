package com.spellknight.barcodescanner.data.remote

import com.spellknight.barcodescanner.data.models.User
import retrofit2.Response
import javax.inject.Inject

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


class ApiHelperImpl
@Inject constructor(private val apiService: ApiService):ApiHelper
{
    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()
}