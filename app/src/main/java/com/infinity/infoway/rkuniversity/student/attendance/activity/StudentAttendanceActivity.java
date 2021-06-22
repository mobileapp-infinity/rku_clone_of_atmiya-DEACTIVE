package com.infinity.infoway.rkuniversity.student.attendance.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.student.attendance.adapter.AttendanceViewPagerAdapter;
import com.infinity.infoway.rkuniversity.student.attendance.fragment.LectureWiseAttendanceFragment;
import com.infinity.infoway.rkuniversity.student.attendance.fragment.SubjectWiseAttendanceFragment;

public class StudentAttendanceActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivBackAttendance;
    TabLayout tlMonthAndSubjectWiseAttendance;
    ViewPager vpAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        initView();
    }


    private void initView() {

        ivBackAttendance = findViewById(R.id.ivBackAttendance);
        ivBackAttendance.setOnClickListener(this);

        tlMonthAndSubjectWiseAttendance = findViewById(R.id.tlMonthAndSubjectWiseAttendance);
        vpAttendance = findViewById(R.id.vpAttendance);
        tlMonthAndSubjectWiseAttendance.setupWithViewPager(vpAttendance);


        setupViewPager(vpAttendance);
    }

    private void setupViewPager(ViewPager viewPager) {
        AttendanceViewPagerAdapter adapter = new AttendanceViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LectureWiseAttendanceFragment(), "Lecture Wise");
        adapter.addFragment(new SubjectWiseAttendanceFragment(), "Subject wise");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivBackAttendance) {
            onBackPressed();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}