package com.example.annotation.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.annotation.Model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deteleNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}