package com.ifeomai.apps.journalapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifeomai.apps.journalapp.Utils.LoginUtils;
import com.ifeomai.apps.journalapp.database.JEntry;

import java.util.Map;

public class NewJEntryActivity extends AppCompatActivity {

    private static final String TAG = "NewJEntryActivity";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private EditText mTitleField;
    private EditText mUpdatedAt;
    private EditText mDescriptionField;
    private FloatingActionButton mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_jentry);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        mTitleField = findViewById(R.id.field_title);
        mUpdatedAt = findViewById(R.id.field_updatedAt);
        mDescriptionField = findViewById(R.id.field_description);
        mSubmitButton = findViewById(R.id.fab_submit_post);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });
    }

    private void submitPost() {
        final String title = mTitleField.getText().toString();
        final String description = mDescriptionField.getText().toString();
        final String updatedAt = mUpdatedAt.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            mTitleField.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(description)) {
            mDescriptionField.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = LoginUtils.getUid();
        mDatabase.child("entries").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Add a try catch here in case user is not authenticated or writnig to the wrong node
                        //It should also catch and Firebase errors
                        //Do we ned o check if user is authenticated in our Firebase DB first?

                            // Write new post
                            writeNewPost(title, description, updatedAt);


                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }

    private void setEditingEnabled(boolean enabled) {
        mTitleField.setEnabled(enabled);
        mDescriptionField.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.INVISIBLE);
        }
    }

    // [START write_fan_out]
    private void writeNewPost(String title, String description, String updatedAt ) {
        // Create new post at /entries/$userid/$postid
       // String key = mDatabase.child("entries").child(LoginUtils.getUid()).push().getKey();
        JEntry post = new JEntry(title, description, updatedAt);
        Map<String, Object> postValues = post.toMap();
        mDatabase.child("entries").child(LoginUtils.getUid()).push().setValue(postValues);
/*

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/entries/" + LoginUtils.getUid() + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);*/
    }
    // [END write_fan_out]
}
