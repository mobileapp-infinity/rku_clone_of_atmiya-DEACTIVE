package com.infinity.infoway.rkuniversity.student.exam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.infinity.infoway.rkuniversity.R;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivCloseExam;
    LinearLayout llExamSchedule, llExamResult, llExamCIAMarks, llMidResult, llMidMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
    }

    private void initView() {
        ivCloseExam = findViewById(R.id.ivCloseExam);
        ivCloseExam.setOnClickListener(this);
        llExamSchedule = findViewById(R.id.llExamSchedule);
        llExamSchedule.setOnClickListener(this);
        llExamResult = findViewById(R.id.llExamResult);
        llExamResult.setOnClickListener(this);
        llExamCIAMarks = findViewById(R.id.llExamCIAMarks);
        llExamCIAMarks.setOnClickListener(this);
        llMidResult = findViewById(R.id.llMidResult);
        llMidResult.setOnClickListener(this);
        llMidMarks = findViewById(R.id.llMidMarks);
        llMidMarks.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseExam) {
            onBackPressed();
        } else if (v.getId() == R.id.llExamSchedule) {
            Intent intent = new Intent(ExamActivity.this, ExaminationScheduleActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llExamResult) {
            Intent intent = new Intent(ExamActivity.this, StudentResultActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llExamCIAMarks) {
            Intent intent = new Intent(ExamActivity.this, CIAExamMarksActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llMidResult) {
            Intent intent = new Intent(ExamActivity.this, MidResultActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llMidMarks) {
            Intent intent = new Intent(ExamActivity.this, StudentMidMarksActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}