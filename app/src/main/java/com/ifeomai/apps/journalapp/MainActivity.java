package com.ifeomai.apps.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.ifeomai.apps.journalapp.Utils.LoginUtils;
import com.ifeomai.apps.journalapp.database.JEntry;
import com.ifeomai.apps.journalapp.database.JEntryDao;
import com.ifeomai.apps.journalapp.viewholder.JEntryViewHolder;

//public class MainActivity extends AppCompatActivity implements JEntryGreenAdapter.ListItemClickListener  {
public class MainActivity extends AppCompatActivity{
    private String mUserName;
    private Toast mToast;
    private static final String TAG = MainActivity.class.getSimpleName();

    private FirebaseRecyclerAdapter mAdapter;
    private RecyclerView mRecycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleList = findViewById(R.id.rv_jentry_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleList.setLayoutManager(layoutManager);
        mRecycleList.setHasFixedSize(true);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecycleList.getContext(),
                layoutManager.getOrientation());
        mRecycleList.addItemDecoration(mDividerItemDecoration);


        //Here I want to use Fb RecyclerAdapter
        //Query query = JEntryDao.mDbRef.limitToLast(30);
        DatabaseReference databaseReference = JEntryDao.mDbRef();
        FirebaseRecyclerOptions<JEntry> options =
                new FirebaseRecyclerOptions.Builder<JEntry>()
                        .setQuery(databaseReference, JEntry.class)
                        .build();
        mAdapter = new FirebaseRecyclerAdapter<JEntry, JEntryViewHolder>(options) {
            @Override
            public JEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_jentry, parent, false);

                return new JEntryViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull JEntryViewHolder holder, int position, @NonNull JEntry model) {
                // Bind the Chat object to the ChatHolder
                // ...
                final DatabaseReference itemRef = getRef(position);
                // Set click listener for the whole JEntry view
                final String itemKey = itemRef.getKey();

                holder.bindJEntry(model);
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Load Detail for - " + itemKey);

                       // Launch JEntryDetailActivity
                        Intent intent = new Intent(getApplicationContext(), JEntryDetailActivity.class);
                        intent.putExtra(JEntryDetailActivity.EXTRA_POST_KEY, itemKey);
                        startActivity(intent);


                      /*   if (mToast != null) {
                            mToast.cancel();
                        }
                        String toastMessage = "List Item was clicked #: "+ DateConverter.toDate(DateConverter.toTimestamp(new Date()));
                       mToast= Toast.makeText(getApplicationContext(),toastMessage , Toast.LENGTH_SHORT);
                       mToast.show();*/
                    }
                });
            }
        };

        // Set the Adapter you created on mRecycleList
        mRecycleList.setAdapter(mAdapter);

     /*   //get an instance to our tv
        TextView mTextViewUserName = findViewById(R.id.text_view_user_name);

        //Use getIntent to retrieve passed values
        Intent intentOrigin = getIntent();
        mUserName = intentOrigin.getStringExtra(Intent.EXTRA_TEXT);
        if (intentOrigin.hasExtra(Intent.EXTRA_TEXT)){
            mTextViewUserName.setText(mUserName);
        }*/





       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add new Detail
                startActivity(new Intent(MainActivity.this, NewJEntryActivity.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override

    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
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
            case R.id.action_log_out:
                LoginUtils.signOut(findViewById(R.id.myCoordinatorLayout));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
/**/

/*// TODO: Note When I implement the or JEntryGreenAdapter FbRecyle Adapter in a new class, I'll have to override
// and uncomment this again
    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }*/
}
