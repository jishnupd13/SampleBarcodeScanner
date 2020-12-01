package com.recycler.mvvmbasepackapplication.utils

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


class ToastUtils
@Inject constructor(context: Context)
{
    private var appContext=context
    fun showToast(msg:String){
        Toast.makeText(appContext,msg,Toast.LENGTH_LONG).show()
    }
}