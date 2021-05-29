package com.example.p1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnCardListener {

    private static final String TAG = "MainActivity";

    private List<String> mCourseTitles;
    private List<String> courseTitles;

    private List<String> mCourseNames;
    private List<String> courseNames;

    private List<List<String>> mPreReqs;
    private List<List<String>> preReqs;

    private List<Integer> mCreditList;
    private List<Integer> creditList;

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
                courseNames = new ArrayList<>();
                creditList = new ArrayList<>();
                preReqs = new ArrayList<>();

                String input = charSequence.toString().toLowerCase();
                for (int j = 0; j < mCourseTitles.size(); ++j) {
                    String item = mCourseTitles.get(j).toLowerCase();
                    if (item.contains(input)) {
                        courseTitles.add(mCourseTitles.get(j));
                        courseNames.add(mCourseNames.get(j));
                        creditList.add(mCreditList.get(j));
                        preReqs.add(mPreReqs.get(j));
                    }
                }
                CustomAdapter newDisplay;
                if (input.length() != 0) {
                    newDisplay = new CustomAdapter(courseTitles, courseNames, MainActivity.this);
                }
                else {
                    newDisplay = new CustomAdapter(mCourseTitles, mCourseNames, MainActivity.this);
                }
                recyclerView.setAdapter(newDisplay);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        //init master
        mCourseTitles = new ArrayList<>();
        mCourseNames = new ArrayList<>();
        mCreditList = new ArrayList<>();
        mPreReqs = new ArrayList<>();

        //init copy
        courseTitles = new ArrayList<>();
        courseNames = new ArrayList<>();
        creditList = new ArrayList<>();
        preReqs = new ArrayList<>();

        //insert elements to master
        mCourseTitles.add("CS1100");
        mCourseNames.add("Freshman Leap Seminar");
        mCreditList.add(1);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(0).add("No Prerequisite courses");

        mCourseTitles.add("CS1301");
        mCourseNames.add("Introduction to Computing");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(1).add("No Prerequisite courses");

        mCourseTitles.add("CS1331");
        mCourseNames.add("Introduction to Object Oriented Programming");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(2).add("CS1301");

        mCourseTitles.add("CS1332");
        mCourseNames.add("Datacourses Structures and Algorithms for Applications");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(3).add("CS1331");

        mCourseTitles.add("CS2050");
        mCourseNames.add("Introduction to Discrete Mathematics for Computer Science");
        mCreditList.add(3);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(4).add("No Prerequisite courses");

        mCourseTitles.add("CS2110");
        mCourseNames.add("Computer Organization and Programming");
        mCreditList.add(4);
        mPreReqs.add(new ArrayList<String>());
        mPreReqs.get(5).add("CS1332");

        mCourseTitles.add("CS2340");
        mCourseNames.add("Objects and Design");
        mCreditList.add(4);
        mPreReqs.add(new ArrayList<String>());
        //to be added
        mPreReqs.get(6).add("");


        //transfer to copy
        courseTitles.addAll(mCourseTitles);
        courseNames.addAll(mCourseNames);
        creditList.addAll(mCreditList);
        preReqs.addAll(mPreReqs);


        recyclerView = findViewById(R.id.recycler_view);

        customAdapter = new CustomAdapter(courseTitles, courseNames, this);
        recyclerView.setAdapter(customAdapter);

        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onCardClick(int position) {
//        Log.d(TAG, "Position: " + position + ", Course Name : " + courseTitles.get(position) + ", Course Description: " + courseNames.get(position));
        Bundle bundle = new Bundle();
        bundle.putString("course_title", courseTitles.get(position));
        bundle.putString("course_name", courseNames.get(position));
        bundle.putInt("credit", creditList.get(position));
        bundle.putStringArrayList("prereq_list", (ArrayList<String>) preReqs.get(position));


        Intent intent = new Intent(this, second_activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}