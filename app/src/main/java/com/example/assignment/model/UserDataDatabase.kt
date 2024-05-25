package com.example.assignment.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.model.UserDataClass

@Database(entities = [UserDataClass::class], version = 1)
abstract class  UserDataDatabase:RoomDatabase() {
    abstract fun userDataDao():UserDataDao
}