package com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.adapter.CoOrdinatorLeaveApplictionViewPagerAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.fragments.FacultyCoOrdinatorPendingLeaveFragment;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.fragments.FacultyCoOrdinatorViewAllLeveFragment;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.adapter.HODLeaveApplicationViewPagerAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.fragments.HODPendingLeaveFragment;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.fragments.HODViewAllLeaveFragment;

public class FacultyCoOrdinatorLeaveApprovalActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivBackCoOrdinatorLeaveApplication;
    TabLayout tlLeaveApplication;
    ViewPager vpLeaveApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_co_ordinator_leave_approval2);
        initView();
    }

    private void initView() {

        ivBackCoOrdinatorLeaveApplication = findViewById(R.id.ivBackCoOrdinatorLeaveApplication);
        ivBackCoOrdinatorLeaveApplication.setOnClickListener(this);

        tlLeaveApplication = findViewById(R.id.tlLeaveApplication);
        vpLeaveApplication = findViewById(R.id.vpLeaveApplication);
        tlLeaveApplication.setupWithViewPager(vpLeaveApplication);

        setupViewPager(vpLeaveApplication);
    }

    private void setupViewPager(ViewPager viewPager) {
        CoOrdinatorLeaveApplictionViewPagerAdapter adapter = new CoOrdinatorLeaveApplictionViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FacultyCoOrdinatorPendingLeaveFragment.newInstance(), "    PENDING    ");
        adapter.addFragment(FacultyCoOrdinatorViewAllLeveFragment.newInstance(), "    VIEW ALL   ");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivBackCoOrdinatorLeaveApplication) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}