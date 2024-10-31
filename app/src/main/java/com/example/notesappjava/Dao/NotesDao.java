package com.example.notesappjava.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesappjava.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM notes WHERE priority = 3")
    LiveData<List<Notes>> getHighNotes();

    @Query("SELECT * FROM notes WHERE priority = 2")
    LiveData<List<Notes>> getMediumNotes();

    @Query("SELECT * FROM notes WHERE priority = 1")
    LiveData<List<Notes>> getLowNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Notes... notes);

    @Query("DELETE FROM notes WHERE id =:id")
    void deleteNote(int id);

    @Update
    void updateNote(Notes notes);
}
