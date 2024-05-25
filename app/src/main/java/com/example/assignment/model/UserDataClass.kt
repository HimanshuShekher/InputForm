package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userdata")
class UserDataClass(
    @PrimaryKey(autoGenerate = true)
    val id :Long,
    val name: String,
    val age: String,
    val dob: String,
    val address: String) {
}