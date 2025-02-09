package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.User

@Database(entities = [User::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val roomDao : RoomDatabaseDao

}