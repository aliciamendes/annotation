package com.example.annotation.Repository

import androidx.lifecycle.LiveData
import com.example.annotation.Dao.UserDao
import com.example.annotation.Model.User

class UserRepository(val dao: UserDao) {
    fun getName(): String {
        return dao.getNameUser()
    }

    fun insertUser(user: User){
        dao.insertNameUser(user)
    }

}