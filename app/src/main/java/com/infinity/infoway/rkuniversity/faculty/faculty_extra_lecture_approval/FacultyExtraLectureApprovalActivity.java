package com.infinity.infoway.rkuniversity.faculty.faculty_extra_lecture_approval;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.faculty.faculty_leave.FacultyLeaveAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_leave.FacultyLeavePojo;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyExtraLectureApprovalActivity extends AppCompatActivity implements View.OnClickListener, ExtraLectureApprovalListAdapter.IApproveRejectExtraLecture {

    static final String EXTRA_LECTURE_APPROVE = "1";
    static final String EXTRA_LECTURE_REJECT = "2";
    static final String EXTRA_LECTURE_PENDING = "0";

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyLeave;
    LinearLayout llFacultyLeaveList, llFacultyLeaveProgressbar, llNoDataFoundFacultyLeave;
    RecyclerView rvFacultyLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_extra_lecture_approval);
        initView();
        getExtraLeaveApprovalApiCall();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyExtraLectureApprovalActivity.this);
        connectionDetector = new ConnectionDetector(FacultyExtraLectureApprovalActivity.this);
        ivCloseFacultyLeave = findViewById(R.id.ivCloseFacultyLeave);
        ivCloseFacultyLeave.setOnClickListener(this);
        rvFacultyLeave = findViewById(R.id.rvFacultyLeave);
        llFacultyLeaveList = findViewById(R.id.llFacultyLeavetList);
        llFacultyLeaveProgressbar = findViewById(R.id.llFacultyLeaveProgressbar);
        llNoDataFoundFacultyLeave = findViewById(R.id.llNoDataFoundFacultyLeave);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyLeave) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getExtraLeaveApprovalApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyLeaveList.setVisibility(View.GONE);
            llFacultyLeaveProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFacultyLeave.setVisibility(View.GONE);
            ApiImplementer.getExtraLectureApprovalApiImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getEmpYearId(), new Callback<ArrayList<ExtraLectureApprovalPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<ExtraLectureApprovalPojo>> call, Response<ArrayList<ExtraLectureApprovalPojo>> response) {
                    llFacultyLeaveProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().size() > 0) {
                        llFacultyLeaveList.setVisibility(View.VISIBLE);
                        llNoDataFoundFacultyLeave.setVisibility(View.GONE);
                        rvFacultyLeave.setAdapter(new ExtraLectureApprovalListAdapter(FacultyExtraLectureApprovalActivity.this, response.body()));
                    } else {
                        llFacultyLeaveList.setVisibility(View.GONE);
                        llNoDataFoundFacultyLeave.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ExtraLectureApprovalPojo>> call, Throwable t) {
                    llFacultyLeaveList.setVisibility(View.GONE);
                    llFacultyLeaveProgressbar.setVisibility(View.GONE);
                    llNoDataFoundFacultyLeave.setVisibility(View.VISIBLE);
                    Toast.makeText(FacultyExtraLectureApprovalActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onApproveOrReject() {
        getExtraLeaveApprovalApiCall();
    }
}