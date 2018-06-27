package com.ifeomai.apps.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ifeomai.apps.journalapp.Utils.LoginUtils;

public class MainActivity extends AppCompatActivity {

    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get an instance to our tv
        TextView mTextViewUserName = findViewById(R.id.text_view_user_name);

        //Use getIntent to retrieve passed values
        Intent intentOrigin = getIntent();
        mUserName = intentOrigin.getStringExtra(Intent.EXTRA_TEXT);
        if (intentOrigin.hasExtra(Intent.EXTRA_TEXT)){
            mTextViewUserName.setText(mUserName);
        }





       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                //Open sample List without MVP
                Intent intent = new Intent(this, ListEntriesActivity.class)
                        .putExtra(Intent.EXTRA_TEXT,mUserName);
                startActivity(intent);
                return true;
            case R.id.action_log_out:
                LoginUtils.signOut(findViewById(R.id.myCoordinatorLayout));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
