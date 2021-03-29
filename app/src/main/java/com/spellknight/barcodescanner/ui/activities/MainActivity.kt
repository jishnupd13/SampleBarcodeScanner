package com.spellknight.barcodescanner.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.spellknight.barcodescanner.R
import com.spellknight.barcodescanner.data.models.User
import com.spellknight.barcodescanner.data.preference.PreferenceHandler
import com.spellknight.barcodescanner.databinding.ActivityMainBinding
import com.spellknight.barcodescanner.ui.adapters.MainAdapter
import com.spellknight.barcodescanner.utils.Status
import com.spellknight.barcodescanner.utils.StylishToastUtils
import com.spellknight.barcodescanner.utils.ToastUtils
import com.spellknight.barcodescanner.viewmodels.MainViewModel
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