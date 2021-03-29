package com.recycler.mvvmbasepackapplication.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.recycler.mvvmbasepackapplication.R
import com.recycler.mvvmbasepackapplication.data.models.User
import com.recycler.mvvmbasepackapplication.data.preference.PreferenceHandler
import com.recycler.mvvmbasepackapplication.databinding.ActivityMainBinding
import com.recycler.mvvmbasepackapplication.ui.adapters.MainAdapter
import com.recycler.mvvmbasepackapplication.utils.Status
import com.recycler.mvvmbasepackapplication.utils.StylishToastUtils
import com.recycler.mvvmbasepackapplication.utils.ToastUtils
import com.recycler.mvvmbasepackapplication.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    @Inject lateinit var logger: PreferenceHandler
    @Inject lateinit var toastUtils: ToastUtils
    @Inject lateinit var stylishToastUtils: StylishToastUtils



    private var userList= arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

   //     logger.getBoolean("hiii")

        toastUtils.showToast("Hello world")
        stylishToastUtils.showErrorToast("Hello world")

        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.userRecyclerView.visibility=View.VISIBLE
                    binding.appLoader.visibility=View.GONE

                    if(it.data!=null){

                        userList.addAll(it.data)
                        val mainAdapter=MainAdapter(userList)
                        val llm = LinearLayoutManager(this)
                        llm.orientation = LinearLayoutManager.VERTICAL
                        binding.userRecyclerView.layoutManager = llm
                        binding.userRecyclerView.adapter=mainAdapter
                    }

                }

                Status.ERROR -> {
                    binding.userRecyclerView.visibility=View.VISIBLE
                    binding.appLoader.visibility=View.GONE
                }

                Status.LOADING -> {
                    binding.userRecyclerView.visibility=View.GONE
                    binding.appLoader.visibility=View.VISIBLE
                }
            }
        })
    }
}