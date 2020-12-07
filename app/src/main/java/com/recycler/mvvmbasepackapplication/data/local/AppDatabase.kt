package com.recycler.mvvmbasepackapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.recycler.mvvmbasepackapplication.data.local.model.StudentModel


@Database(
    entities = [StudentModel::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getYourDao(): StudentDao
}