package com.example.notesappjava.Model;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Notes implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "subTitle")
    private String subTitle;

    @ColumnInfo(name = "note")
    private String note;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "priority")
    private String priority;

    public Notes(String title, String subTitle, String note, String date, String priority) {
        this.title = title;
        this.subTitle = subTitle;
        this.note = note;
        this.date = date;
        this.priority = priority;
    }

    public Notes() {

    }

    // Getter and Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
