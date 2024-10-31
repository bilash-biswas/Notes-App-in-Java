package com.example.notesappjava.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesappjava.Dao.NotesDao;
import com.example.notesappjava.Database.NotesDatabase;
import com.example.notesappjava.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> getHighNotes;
    public LiveData<List<Notes>> getMediumNotes;
    public LiveData<List<Notes>> getLowNotes;

    public NotesRepository(Application application){
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
        getHighNotes = notesDao.getHighNotes();
        getMediumNotes = notesDao.getMediumNotes();
        getLowNotes = notesDao.getLowNotes();
    }

    public void insertNote(Notes... notes){
        notesDao.insertNote(notes);
    }

    public void deleteNote(int id){
        notesDao.deleteNote(id);
    }

    public void updateNote(Notes notes){
        notesDao.updateNote(notes);
    }

}
