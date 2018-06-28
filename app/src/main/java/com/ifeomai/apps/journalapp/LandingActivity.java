package com.ifeomai.apps.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ifeomai.apps.journalapp.database.JEntry;
import com.ifeomai.apps.journalapp.database.JEntryAdapter;
import com.ifeomai.apps.journalapp.database.JEntryDao;

import java.util.ArrayList;
import java.util.List;

public class LandingActivity extends AppCompatActivity {
    //TODO: 001 Firebase & Adapter instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDbRef;
    private ChildEventListener mChildEventListener;
    private JEntryAdapter mJEntryAdapter;

    private ListView mJEntryListView;

    //TODO : 001a

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //TODO: 002 Instantiate Firebase variables
        mFirebaseDatabase = JEntryDao.mFirebaseDatabase;
        mDbRef = JEntryDao.mDbRef;

        mJEntryListView = findViewById(R.id.listView_jEntry);

        //TODO 005: Initialize message ListView and its adapter
        List<JEntry> jEntries = new ArrayList<>();
        mJEntryAdapter = new JEntryAdapter(this, R.layout.item_jentry, jEntries);
        mJEntryListView.setAdapter(mJEntryAdapter);


        FloatingActionButton fab = findViewById(R.id.fab_add_new);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add new Detail
                 startActivity(new Intent(LandingActivity.this, NewJEntryActivity.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //TODO: 003 Instantiate the ChildRef Listener so it can auto update/refresh db data in realtime
        //THe Listener and methods also define what exactly should happen to the data
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                JEntry jEntry = dataSnapshot.getValue(JEntry.class);
                mJEntryAdapter.add(jEntry);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //if this is called it typically means you don't have db access

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        // TODO: 004 Add the Child Event Listener to the Database
        mDbRef.addChildEventListener(mChildEventListener);
    }
}
