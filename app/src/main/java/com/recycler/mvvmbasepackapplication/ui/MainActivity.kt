package com.recycler.mvvmbasepackapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.recycler.mvvmbasepackapplication.R
import com.recycler.mvvmbasepackapplication.databinding.ActivityMainBinding
import com.recycler.mvvmbasepackapplication.utils.Status
import com.recycler.mvvmbasepackapplication.utils.ToastUtils
import com.recycler.mvvmbasepackapplication.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        })
    }
}