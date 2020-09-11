package com.example.gads_top20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements LearningLeaders.OnFragmentInteractionListener, SkillLeaders.OnFragmentInteractionListener{

    private static String sTopLearners;
    private static String sTopSkillIq;

    private ProgressBar mDataLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataLoading = findViewById(R.id.pb_data_laoding);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));//OnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleForm();
            }
        });


        collectingDataFromApi();  // Connecting to the internet and retrieving data



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void collectingDataFromApi() {
        String[] searchQuery = {"hours", "skilliq"};
        URL[] searchUrl = new URL[2];
        for (int i=0; i<2; i++){
            searchUrl[i] = ApiUtil.buildUrl(searchQuery[i]);
        }
        new QueryForResult().execute(searchUrl);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void openGoogleForm() {
        Intent intent = new Intent(this, SubmissionActivity.class);
        startActivity(intent);
    }

    public  class QueryForResult extends AsyncTask<URL, Void, String[]>{

        @Override
        protected String[] doInBackground(URL... urls) {
            String[] results = {null, null};
            int i=0;
            while (i<2) {
                try {
                    results[i] = ApiUtil.getJson(urls[i]);
                } catch (IOException e) {
                    Log.d("Error", e.getMessage());
                }
                i++;
            }
            return results;
        }

        @Override
        protected void onPostExecute(String[] stringsOfJson) {
            sTopLearners = stringsOfJson[0];
            sTopSkillIq = stringsOfJson[1];


            if (sTopLearners ==null && sTopSkillIq == null){
                Toast.makeText(MainActivity.this, "Oops no data loaded", Toast.LENGTH_SHORT).show();
            }else if (sTopLearners == null){
                Toast.makeText(MainActivity.this, "Error loading top learners", Toast.LENGTH_SHORT).show();
            }else if (sTopSkillIq == null){
                Toast.makeText(MainActivity.this, "Error loading top skills", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Data loaded successfully", Toast.LENGTH_SHORT).show();
                mDataLoading.setVisibility(View.INVISIBLE);

                DataManager dm = DataManager.getInstance();

                dm.getLearnerFromJson(sTopLearners);
                dm.getSkillIQFromJson(sTopSkillIq);

            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDataLoading.setVisibility(View.VISIBLE);
        }
    }
}
