package com.ifeomai.apps.journalapp.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyJEntriesFragment extends JEntryListFragment{
    public MyJEntriesFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("entries").child(getUid());
    }
}
