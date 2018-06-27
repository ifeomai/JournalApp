package com.ifeomai.apps.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ListEntriesActivity extends AppCompatActivity {

    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_entries);

        TextView mTextViewLoginUser = findViewById(R.id.text_view_LogInUser);

        //Use getIntent to retrieve passed values
        Intent intentOrigin = getIntent();
        mUserName = intentOrigin.getStringExtra(Intent.EXTRA_TEXT);
        if (intentOrigin.hasExtra(Intent.EXTRA_TEXT)){
            mTextViewLoginUser.setText(mUserName);
        }

    }
}
