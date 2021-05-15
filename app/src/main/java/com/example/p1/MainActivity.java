package com.example.p1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnCardListener {

    private static final String TAG = "MainActivity";

    private List<String> mCourseTitles;
    private List<String> courseTitles;
    private List<String> mCourseDescriptions;
    private List<String> courseDescriptions;
    private List<List<String>> mPreReqs, mPostReqs;
    private List<List<String>> preReqs, postReqs;

    private EditText searchInput;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.search_bar);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                courseTitles = new ArrayList<>();
                courseDescriptions = new ArrayList<>();
                String input = charSequence.toString().toLowerCase();
                for (int j = 0; j < mCourseTitles.size(); ++j) {
                    String item = mCourseTitles.get(j).toLowerCase();
                    if (item.contains(input)) {
                        courseTitles.add(mCourseTitles.get(j));
                        courseDescriptions.add(mCourseDescriptions.get(j));
                    }
                }
                CustomAdapter newDisplay;
                if (input.length() != 0) {
                    newDisplay = new CustomAdapter(courseTitles, courseDescriptions, MainActivity.this);
                }
                else {
                    newDisplay = new CustomAdapter(mCourseTitles, mCourseDescriptions, MainActivity.this);
                }
                recyclerView.setAdapter(newDisplay);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        //init master
        mCourseTitles = new ArrayList<>();
        mCourseDescriptions = new ArrayList<>();
        mPreReqs = new ArrayList<>();
        mPostReqs = new ArrayList<>();

        //init copy
        courseTitles = new ArrayList<>();
        courseDescriptions = new ArrayList<>();
        preReqs = new ArrayList<>();
        postReqs = new ArrayList<>();

        //insert elements to master
        mCourseTitles.add("CS1100");
        mCourseDescriptions.add("Freshman Leap Seminar");
        mCourseTitles.add("CS1301");
        mCourseDescriptions.add("Introduction to Computing");
        mCourseTitles.add("CS1331");
        mCourseDescriptions.add("Introduction to Object Oriented Programming");
        mCourseTitles.add("CS1332");
        mCourseDescriptions.add("Data Structures and Algorithms for Applications");

        //transfer to copy
        courseTitles.addAll(mCourseTitles);
        courseDescriptions.addAll(mCourseDescriptions);


        recyclerView = findViewById(R.id.recycler_view);

        customAdapter = new CustomAdapter(courseTitles, courseDescriptions, this);
        recyclerView.setAdapter(customAdapter);

        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onCardClick(int position) {
        Log.d(TAG, "Position: " + position + ", Course Name : " + courseTitles.get(position) + ", Course Description: " + courseDescriptions.get(position));
        Bundle bundle = new Bundle();
        bundle.putString("course_name", courseTitles.get(position));
        bundle.putString("course_descript", courseDescriptions.get(position));

        Intent intent = new Intent(this, second_activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

//    public void launch1100(View view) {
//
//        Intent intent = new Intent(this, cs1100_activity.class);
//        startActivity(intent);
//    }

}