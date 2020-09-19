package com.example.cfttesttask.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PersonEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nickName: String = "",
    val password: String = "",
    val name: String = "",
    val secondName: String = "",
    val birthDate: String = ""
)