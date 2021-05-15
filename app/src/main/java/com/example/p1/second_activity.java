package com.example.p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class second_activity extends AppCompatActivity {

    private Bundle bundle;
    private String courseName;
    private String courseDescription;
    private List<String> preReqList;
    private List<String> postReqList;

    private TextView mCourseName;
    private TextView mCourseDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            courseName = bundle.getString("course_name");
            courseDescription = bundle.getString("course_descript");
        }

        mCourseName = findViewById(R.id.title);
        mCourseDescription = findViewById(R.id.descript);

        mCourseName.setText(courseName);
        mCourseDescription.setText(courseDescription);
    }
}