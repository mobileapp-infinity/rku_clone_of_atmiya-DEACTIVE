package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_adviser_remarks.FacultyAdviserRemarksActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_details_of_theory_sub.FacultyDetailsOfTheorySubjectTaughtActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_lab_or_lecture_plan_teaching_update.FacultyLabOrLecturePlanTeachingUpdateActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_student_forum.FacultyStudentForumActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_subject_wise_division_wise_total_theory_period_engaged.FacultySubWiseDivWiseTotTheoryPeriodEngagedActivity;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

public class FacultyTeachingUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyTeachingUpdate;
    LinearLayout llFacultyTeachingUpdateLabOrLecturePlan, llFacultyTeachingUpdateStudentForumActivity,
            llFacultyTeachingUpdateFacultyAdviserRemarks, llFacultyTeachingUpdateDetailsOfTheorySubject,
            llFacultyTeachingUpdateTotalTheoryPeriod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_teaching_update);
        initView();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyTeachingUpdateActivity.this);
        connectionDetector = new ConnectionDetector(FacultyTeachingUpdateActivity.this);
        ivCloseFacultyTeachingUpdate = findViewById(R.id.ivCloseFacultyTeachingUpdate);
        ivCloseFacultyTeachingUpdate.setOnClickListener(this);

        llFacultyTeachingUpdateLabOrLecturePlan = findViewById(R.id.llFacultyTeachingUpdateLabOrLecturePlan);
        llFacultyTeachingUpdateLabOrLecturePlan.setOnClickListener(this);
        llFacultyTeachingUpdateStudentForumActivity = findViewById(R.id.llFacultyTeachingUpdateStudentForumActivity);
        llFacultyTeachingUpdateStudentForumActivity.setOnClickListener(this);
        llFacultyTeachingUpdateFacultyAdviserRemarks = findViewById(R.id.llFacultyTeachingUpdateFacultyAdviserRemarks);
        llFacultyTeachingUpdateFacultyAdviserRemarks.setOnClickListener(this);
        llFacultyTeachingUpdateDetailsOfTheorySubject = findViewById(R.id.llFacultyTeachingUpdateDetailsOfTheorySubject);
        llFacultyTeachingUpdateDetailsOfTheorySubject.setOnClickListener(this);
        llFacultyTeachingUpdateTotalTheoryPeriod = findViewById(R.id.llFacultyTeachingUpdateTotalTheoryPeriod);
        llFacultyTeachingUpdateTotalTheoryPeriod.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyTeachingUpdate) {
            onBackPressed();
        } else if (v.getId() == R.id.llFacultyTeachingUpdateLabOrLecturePlan) {
            Intent intent = new Intent(FacultyTeachingUpdateActivity.this, FacultyLabOrLecturePlanTeachingUpdateActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llFacultyTeachingUpdateStudentForumActivity) {
            Intent intent = new Intent(FacultyTeachingUpdateActivity.this, FacultyStudentForumActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llFacultyTeachingUpdateFacultyAdviserRemarks) {
            Intent intent = new Intent(FacultyTeachingUpdateActivity.this, FacultyAdviserRemarksActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llFacultyTeachingUpdateDetailsOfTheorySubject) {
            Intent intent = new Intent(FacultyTeachingUpdateActivity.this, FacultyDetailsOfTheorySubjectTaughtActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llFacultyTeachingUpdateTotalTheoryPeriod) {
            Intent intent = new Intent(FacultyTeachingUpdateActivity.this, FacultySubWiseDivWiseTotTheoryPeriodEngagedActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}