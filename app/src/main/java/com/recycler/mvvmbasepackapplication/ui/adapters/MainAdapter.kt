package com.recycler.mvvmbasepackapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.recycler.mvvmbasepackapplication.R
import com.recycler.mvvmbasepackapplication.data.models.User
import com.recycler.mvvmbasepackapplication.databinding.ItemUserNameBinding

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


class MainAdapter(private val userList: List<User>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding: ItemUserNameBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user_name, parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       holder.binding.item= userList[position]
    }

    class MainViewHolder(itemUserNameBinding: ItemUserNameBinding) :
        RecyclerView.ViewHolder(itemUserNameBinding.root) {
        val binding = itemUserNameBinding
    }


}