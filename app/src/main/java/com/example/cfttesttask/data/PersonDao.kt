package com.example.cfttesttask.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {

    @Insert
    fun addPerson(person: PersonEntity): Long

    @Query("SELECT * from person_table WHERE id = :id")
    fun getPerson(id: Long): PersonEntity

    @Query("SELECT * from person_table WHERE nickName = :nickName")
    fun getPerson(nickName: String): PersonEntity?

    @Query("SELECT id from person_table WHERE nickName = :nickName AND password = :password")
    fun getPersonId(nickName: String, password: String): Long?

    @Query("SELECT id from person_table WHERE nickName = :nickName")
    fun getPersonId(nickName: String): Long?
}