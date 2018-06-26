package com.ifeomai.apps.journalapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "journal")
public class JournalEntry {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String owner;
    private String comment;
    private Date updatedAt;

    @Ignore
    public JournalEntry(String owner, String comment, Date updatedAt) {
        this.owner = owner;
        this.comment = comment;
        this.updatedAt = updatedAt;
    }

    public JournalEntry(int id, String owner, String comment, Date updatedAt) {
        this.id = id;
        this.owner = owner;
        this.comment = comment;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}
