package com.example.annotation.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.annotation.Dao.UserDao
import com.example.annotation.Model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    abstract fun myUserDao(): UserDao

    companion object{
        @Volatile
        var INSTANCE:UserDatabase ?= null

        fun getDatabaseInstance(context: Context):UserDatabase{
            val tempInstance = UserDatabase.INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context, UserDatabase::class.java, "User")
                    .allowMainThreadQueries()
                    .build()

                UserDatabase.INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }


    }
}