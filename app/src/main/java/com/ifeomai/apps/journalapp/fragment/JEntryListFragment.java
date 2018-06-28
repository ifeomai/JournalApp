package com.ifeomai.apps.journalapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ifeomai.apps.journalapp.JEntryDetailActivity;
import com.ifeomai.apps.journalapp.R;
import com.ifeomai.apps.journalapp.database.JEntry;
import com.ifeomai.apps.journalapp.viewholder.JEntryViewHolder;

public abstract class JEntryListFragment extends Fragment {
    private static final String TAG = "PostListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]


    private FirebaseRecyclerAdapter<JEntry, JEntryViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;


    public JEntryListFragment() {}



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_entries, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

       mRecycler = rootView.findViewById(R.id.messages_list);
       mRecycler.setHasFixedSize(true);
        return rootView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);



        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<JEntry>()
                .setQuery(postsQuery, JEntry.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<JEntry, JEntryViewHolder>(options) {



            @Override
            public JEntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

                return new JEntryViewHolder(inflater.inflate(R.layout.item_jentry, viewGroup, false));

            }



            @Override

            protected void onBindViewHolder(JEntryViewHolder viewHolder, int position, final JEntry model) {

                final DatabaseReference postRef = getRef(position);



                // Set click listener for the whole post view

                final String postKey = postRef.getKey();

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        // Launch PostDetailActivity

                        Intent intent = new Intent(getActivity(), JEntryDetailActivity.class);

                        intent.putExtra(JEntryDetailActivity.EXTRA_POST_KEY, postKey);

                        startActivity(intent);

                    }

                });


            }

        };

        mRecycler.setAdapter(mAdapter);

    }


    @Override

    public void onStart() {

        super.onStart();

        if (mAdapter != null) {

            mAdapter.startListening();

        }

    }



    @Override

    public void onStop() {

        super.onStop();

        if (mAdapter != null) {

            mAdapter.stopListening();

        }

    }





    public String getUid() {

        return FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    //Rather than use an abstract class like in below, since this is just my listing, return only my entries
    public Query getMyJEntries(DatabaseReference databaseReference){
        //All my entries
        return databaseReference.child("entries").child(getUid());

    }

    public abstract Query getQuery(DatabaseReference databaseReference);
}
