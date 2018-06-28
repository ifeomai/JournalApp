package com.ifeomai.apps.journalapp.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ifeomai.apps.journalapp.Utils.LoginUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Note{

private static final DatabaseReference  APP_DB = FirebaseDatabase.getInstance().getReference();
public List<NoteItem> Items() {
    return new ArrayList<NoteItem>();
    }
    public Query getMyJEntries(DatabaseReference databaseReference){
        //All my entries
        return APP_DB.child("entries").child(LoginUtils.getUid());

    }


    private static final int COUNT = 15;

    private static void addItem(NoteItem item) {
       //TODO: Create the method to add items to FireBase or DB Repo here
    }

public static class NoteItem {
    public String id;
    public String title;
    public String description;
    public String updatedAt;

    public NoteItem(){
        //default Constructor
    }
    public NoteItem(String description,String title, String updatedAt) {
        //this.owner = owner;
        this.title = title;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public NoteItem(String id ,String description,String title, String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("description", description);
        result.put("title", title);
        result.put("updatedAt", updatedAt);
        return result;

    }
    @Override
    public String toString() {
        return title;
    }

}

}
