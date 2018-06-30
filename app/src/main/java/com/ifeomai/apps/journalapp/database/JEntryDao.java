package com.ifeomai.apps.journalapp.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifeomai.apps.journalapp.Utils.LoginUtils;

public class JEntryDao {
    private static FirebaseDatabase instance;

    public static FirebaseDatabase mFirebaseDatabase() {
        if (instance == null) {
            instance = FirebaseDatabase.getInstance();
            instance.setPersistenceEnabled(true);
        }
        return instance;
    }

    //This defines the database / JSON node that I exactly  want
    public static DatabaseReference mDbRef(){
        DatabaseReference dbRef = mFirebaseDatabase().getReference("entries").child(LoginUtils.getUid());
        dbRef.keepSynced(true);
        return dbRef;
    }




}
