package com.example.annotation.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.annotation.Model.User

@Dao
interface UserDao {

    @Query("SELECT nameUser FROM User")
    fun getNameUser(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertNameUser(user: User)

}
