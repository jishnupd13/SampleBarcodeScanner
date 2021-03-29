package com.spellknight.barcodescanner.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spellknight.barcodescanner.data.local.model.StudentModel


@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(studentModel: StudentModel?):Long

    @Query("SELECT * FROM student")
    fun getAlphabetizedWords(): List<StudentModel?>?
}