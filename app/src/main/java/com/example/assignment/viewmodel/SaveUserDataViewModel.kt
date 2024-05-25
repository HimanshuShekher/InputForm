package com.example.viewmodel

import android.app.Application
import android.content.Context
import android.service.autofill.UserData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.assignment.model.UserDataDatabase
import com.example.model.UserDataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SaveUserDataViewModel(application: Application) : AndroidViewModel(application) {



    private val _userData = MutableStateFlow<List<UserDataClass>>(emptyList())
    val userData: StateFlow<List<UserDataClass>> get() = _userData

    private val database by lazy {
        Room.databaseBuilder(application, UserDataDatabase::class.java, "contactDB")
            .allowMainThreadQueries() // This should generally be avoided
            .build()
    }
    fun insertUser(name:String,age:String,dob:String,address:String){
        viewModelScope.launch {
          database.userDataDao().insertUserData(UserDataClass(0,name,age,dob,address))
        }
    }

    fun retriveUser(){
        viewModelScope.launch {
          //  database.userDataDao().getUserData()

            val data = database.userDataDao().getUserData()
            _userData.value = data
        }
    }

}