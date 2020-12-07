package com.recycler.mvvmbasepackapplication.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recycler.mvvmbasepackapplication.data.local.model.StudentModel
import com.recycler.mvvmbasepackapplication.data.models.User
import com.recycler.mvvmbasepackapplication.data.repository.ApplicationRepository
import com.recycler.mvvmbasepackapplication.utils.NetworkHelper
import com.recycler.mvvmbasepackapplication.utils.Resource
import kotlinx.coroutines.launch

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */

class MainViewModel   @ViewModelInject constructor(
    private val applicationRepository: ApplicationRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>> get() = _users

    private var insertItem=MutableLiveData<Long>()




    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                applicationRepository.getUsers().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }

    private fun insertStudent(studentModel: StudentModel){
        viewModelScope.launch {
           insertItem.postValue(applicationRepository.insertStudent(studentModel))
        }
    }
}