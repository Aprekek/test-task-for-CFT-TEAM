package com.example.cfttesttask.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "PERSON_DB"
    }

    abstract fun personDao(): PersonDao
}