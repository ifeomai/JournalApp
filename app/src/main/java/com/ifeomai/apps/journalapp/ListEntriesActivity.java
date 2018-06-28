package com.ifeomai.apps.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ifeomai.apps.journalapp.fragment.MyJEntriesFragment;

public class ListEntriesActivity extends AppCompatActivity {

    private String mUserName;


    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

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

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new MyJEntriesFragment()
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.heading_my_top_posts)
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);








        // Button launches NewPostActivity
        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListEntriesActivity.this, NewJEntryActivity.class));
            }
        });
    }
}
