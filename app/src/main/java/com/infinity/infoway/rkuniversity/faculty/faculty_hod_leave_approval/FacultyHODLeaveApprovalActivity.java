package com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.adapter.HODLeaveApplicationViewPagerAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.fragments.HODPendingLeaveFragment;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.fragments.HODViewAllLeaveFragment;
import com.infinity.infoway.rkuniversity.student.leave_application.activity.LeaveApplicationActivity;
import com.infinity.infoway.rkuniversity.student.leave_application.adapter.LeaveApplicationViewPagerAdapter;
import com.infinity.infoway.rkuniversity.student.leave_application.fragment.ApplyILeaveFragment;
import com.infinity.infoway.rkuniversity.student.leave_application.fragment.LeaveHistoryFragment;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FacultyHODLeaveApprovalActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String HOD_STATUS_PENDING = "0";
    public static final String HOD_STATUS_APPROVE = "1";
    public static final String HOD_STATUS_REJECT = "2";
    AppCompatImageView ivBackHODLeaveApplication;
    TabLayout tlLeaveApplication;
    ViewPager vpLeaveApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_h_o_d_leave_approval);
        initView();
    }

    private void initView() {

        ivBackHODLeaveApplication = findViewById(R.id.ivBackHODLeaveApplication);
        ivBackHODLeaveApplication.setOnClickListener(this);

        tlLeaveApplication = findViewById(R.id.tlLeaveApplication);
        vpLeaveApplication = findViewById(R.id.vpLeaveApplication);
        tlLeaveApplication.setupWithViewPager(vpLeaveApplication);

        setupViewPager(vpLeaveApplication);
    }

    private void setupViewPager(ViewPager viewPager) {
        HODLeaveApplicationViewPagerAdapter adapter = new HODLeaveApplicationViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HODPendingLeaveFragment.newInstance(), "    PENDING    ");
        adapter.addFragment(HODViewAllLeaveFragment.newInstance(), "    VIEW ALL   ");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivBackHODLeaveApplication) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}