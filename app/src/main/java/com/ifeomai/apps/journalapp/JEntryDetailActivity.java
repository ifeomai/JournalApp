package com.ifeomai.apps.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ifeomai.apps.journalapp.database.JEntry;
import com.ifeomai.apps.journalapp.database.JEntryDao;

public class JEntryDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "JEntryDetailActivity";

    public static final String EXTRA_POST_KEY = "post_key";

    private DatabaseReference mDatabaseReference;
    private ValueEventListener mValueEventListener;
    private String mJEntryKey;
    private JEntry post;

    private TextView mTextViewTitle;
    private TextView mTextViewDescription;
    private TextView mTextViewUpdatedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jentry_detail);

        //set up the menu
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);


        // Get post key from intent
        mJEntryKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if (mJEntryKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }
       /* String toastMessage = "List Item was clicked #: "+ mJEntryKey ;
        Toast.makeText(getApplicationContext(),toastMessage , Toast.LENGTH_SHORT).show();
*/
        // Initialize Database
        mDatabaseReference = JEntryDao.mDbRef().child(mJEntryKey);
        // Initialize Views
        mTextViewTitle = findViewById(R.id.post_title);
        mTextViewDescription = findViewById(R.id.post_description);
        mTextViewUpdatedAt = findViewById(R.id.post_updated_at);

        FloatingActionButton fab = findViewById(R.id.fab_edit_post);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add new Detail
                Intent intent = new Intent(JEntryDetailActivity.this, NewJEntryActivity.class);
                intent.putExtra("item",post);
                intent.putExtra(JEntryDetailActivity.EXTRA_POST_KEY, mJEntryKey);

                startActivity(intent);

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                post = dataSnapshot.getValue(JEntry.class);
                // [START_EXCLUDE]
                mTextViewTitle.setText(post.title);
                mTextViewDescription.setText(post.description);
                mTextViewUpdatedAt.setText(post.updatedAt);
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(JEntryDetailActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mDatabaseReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mValueEventListener = postListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mValueEventListener != null) {
            mDatabaseReference.removeEventListener(mValueEventListener);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {


    }
}
