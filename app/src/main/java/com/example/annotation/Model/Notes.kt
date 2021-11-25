package com.example.annotation.Model

import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int ?= null,
    var title: String,
    var content: String,
    var date: String
)
