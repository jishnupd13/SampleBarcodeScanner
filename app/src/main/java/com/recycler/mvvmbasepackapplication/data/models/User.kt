package com.recycler.mvvmbasepackapplication.data.models

import com.squareup.moshi.Json

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


data class User(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "avatar")
    val avatar: String = ""
)