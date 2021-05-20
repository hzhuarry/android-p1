package com.example.p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class second_activity extends AppCompatActivity {

    private Bundle bundle;
    private String courseName;
    private String courseDescription;
    private int creditAmoount;
    private List<String> preReqList;
    private List<String> postReqList;

    private TextView mCourseName;
    private TextView mCourseDescription;
    private TextView mCreditAmount;
    private TextView mPreReqList;
    private TextView mPostReqList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            courseName = bundle.getString("course_name");
            courseDescription = bundle.getString("course_descript");
            creditAmoount = bundle.getInt("credit");
            preReqList = bundle.getStringArrayList("prereq list");
            postReqList = bundle.getStringArrayList("postreq list");
        }

        mCourseName = findViewById(R.id.title);
        mCourseDescription = findViewById(R.id.descript);
        mCreditAmount = findViewById(R.id.credit_text);
        mPreReqList = findViewById(R.id.prereq_insert);
        mPostReqList = findViewById(R.id.postreq_insert);

        mCourseName.setText(courseName);
        mCourseDescription.setText(courseDescription);
        mCreditAmount.setText(creditAmoount + "");
        String ss1 = "";
        for (int i = 0; i < preReqList.size(); ++i) {
            ss1 += preReqList.get(i);
            if (i + 1 < preReqList.size()) {
                ss1 += ", ";
            }
        }
        mPreReqList.setText(ss1);
        String ss2 = "";
        for (int i = 0; i < postReqList.size(); ++i) {
            ss2 += postReqList.get(i);
            if (i + 1 < postReqList.size()) {
                ss2 += ", ";
            }
        }
        mPostReqList.setText(ss2);
    }
}