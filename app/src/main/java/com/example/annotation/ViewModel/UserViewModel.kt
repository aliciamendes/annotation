package com.example.annotation.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.annotation.Database.UserDatabase
import com.example.annotation.Model.User
import com.example.annotation.Repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: UserRepository

    init {
        val dao = UserDatabase.getDatabaseInstance(application).myUserDao()
        repository = UserRepository(dao)
    }

    fun addName(user: User){
        repository.insertUser(user)
    }

    fun getName():String = repository.getName()
}