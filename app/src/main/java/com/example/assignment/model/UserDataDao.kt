package com.example.assignment.model

import android.service.autofill.UserData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.model.UserDataClass
@Dao
interface UserDataDao {
    @Insert
    fun insertUserData(userData:UserDataClass)
    @Query("SELECT*FROM userdata")
    fun getUserData():List<UserDataClass>
}