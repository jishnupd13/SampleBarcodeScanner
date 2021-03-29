package com.spellknight.barcodescanner.data.repository

import com.spellknight.barcodescanner.data.local.StudentDao
import com.spellknight.barcodescanner.data.local.model.StudentModel
import com.spellknight.barcodescanner.data.remote.ApiHelper
import javax.inject.Inject

/**
 * created By Jishnu P Dileep
 * 01-12-2020
 * */


class ApplicationRepository @Inject constructor(private val apiHelper: ApiHelper, private val studentDao: StudentDao) {

    suspend fun getUsers() =  apiHelper.getUsers()
    suspend fun insertStudent(studentModel: StudentModel):Long=studentDao.insert(studentModel)
}