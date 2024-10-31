package com.example.notesappjava.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesappjava.Model.Notes;
import com.example.notesappjava.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> getHighNotes;
    public LiveData<List<Notes>> getMediumNotes;
    public LiveData<List<Notes>> getLowNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;
        getHighNotes = repository.getHighNotes;
        getMediumNotes = repository.getMediumNotes;
        getLowNotes = repository.getLowNotes;
    }

    public void insertNote(Notes... notes){
        repository.insertNote(notes);
    }

    public void deleteNote(int id){
        repository.deleteNote(id);
    }

    public void updateNote(Notes notes){
        repository.updateNote(notes);
    }
}
