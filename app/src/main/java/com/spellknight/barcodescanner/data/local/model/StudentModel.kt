package com.spellknight.barcodescanner.data.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @NonNull
    @ColumnInfo(name = "student_name")
    var studentName:String?=null,
    @NonNull
    @ColumnInfo(name = "semester")
    var semester:String?=null
)