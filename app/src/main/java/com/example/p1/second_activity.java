package com.example.p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class second_activity extends AppCompatActivity {

    private Bundle bundle;
    private String courseTitle;
    private String courseName;
    private String courseDescript;
    private int creditAmoount;
    private List<String> preReqList;

    private TextView mCourseTitle;
    private TextView mCourseName;
    private TextView mCourseDescript;
    private TextView mCreditAmount;
    private TextView mPreReqList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            courseTitle = bundle.getString("course_title");
            courseName = bundle.getString("course_name");
            courseDescript = bundle.getString("course_descript");
            creditAmoount = bundle.getInt("credit");
            preReqList = bundle.getStringArrayList("prereq_list");
        }

        mCourseTitle= findViewById(R.id.title);
        mCourseName = findViewById(R.id.name);
        mCourseDescript = findViewById(R.id.descript_text);
        mCreditAmount = findViewById(R.id.credit_text);
        mPreReqList = findViewById(R.id.prereq_text);

        mCourseTitle.setText(courseTitle);
        mCourseName.setText(courseName);
        mCourseDescript.setText(courseDescript);
        mCreditAmount.setText(creditAmoount + "");
        String ss1 = "";
        for (int i = 0; i < preReqList.size(); ++i) {
            ss1 += preReqList.get(i);
            if (i + 1 < preReqList.size()) {
                ss1 += ", ";
            }
        }
        mPreReqList.setText(ss1);
    }
}