package com.ifeomai.apps.journalapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface JournalDao {

    @Query("SELECT * FROM journal ORDER BY updatedAt Desc")
    LiveData<List<JournalEntry>> loadMyNotes();

    @Insert
    void insertNote(JournalEntry journalEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNote(JournalEntry journalEntry);

    @Delete
    void deleteNote(JournalEntry taskEntry);
}
