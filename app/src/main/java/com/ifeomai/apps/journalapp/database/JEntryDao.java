package com.ifeomai.apps.journalapp.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifeomai.apps.journalapp.Utils.LoginUtils;

public class JEntryDao {
    public static FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    //This reference defines the database / JSON node that I exactly  want
    public static DatabaseReference mDbRef = mFirebaseDatabase.getReference("entries").child(LoginUtils.getUid());




}
