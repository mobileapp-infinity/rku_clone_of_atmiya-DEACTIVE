package com.infinity.infoway.rkuniversity.faculty.faculty_pending_attendance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyPendingAttendanceActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyPendingAttendance;
    LinearLayout llFacultyPendingAttendanceList, llFacultyPendingAttendanceProgressbar, llNoDataFoundPendingAttendance;
    RecyclerView rvFacultyPendingAttendance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_pending_attendance);
        initView();
        getFacultyPendingAttendanceApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyPendingAttendanceActivity.this);
        connectionDetector = new ConnectionDetector(FacultyPendingAttendanceActivity.this);
        ivCloseFacultyPendingAttendance = findViewById(R.id.ivCloseFacultyPendingAttendance);
        ivCloseFacultyPendingAttendance.setOnClickListener(this);
        rvFacultyPendingAttendance = findViewById(R.id.rvFacultyPendingAttendance);
        llFacultyPendingAttendanceList = findViewById(R.id.llFacultyPendingAttendanceList);
        llFacultyPendingAttendanceProgressbar = findViewById(R.id.llFacultyPendingAttendanceProgressbar);
        llNoDataFoundPendingAttendance = findViewById(R.id.llNoDataFoundPendingAttendance);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyPendingAttendance) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getFacultyPendingAttendanceApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyPendingAttendanceList.setVisibility(View.GONE);
            llFacultyPendingAttendanceProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundPendingAttendance.setVisibility(View.GONE);
            ApiImplementer.getFacultyPendingAttendanceApiImplementer(mySharedPreferences.getEmpId(),
                    mySharedPreferences.getEmpYearId(), new Callback<FacultyPendingAttendancePojo>() {
                        @Override
                        public void onResponse(Call<FacultyPendingAttendancePojo> call, Response<FacultyPendingAttendancePojo> response) {
                            llFacultyPendingAttendanceProgressbar.setVisibility(View.GONE);
                            if (response.isSuccessful() && response.body() != null &&
                                    response.body().getDetailsArrayList().size() > 0) {
                                llFacultyPendingAttendanceList.setVisibility(View.VISIBLE);
                                llNoDataFoundPendingAttendance.setVisibility(View.GONE);
                                rvFacultyPendingAttendance.setAdapter(new FacultyPendingAttendanceListAdapter(FacultyPendingAttendanceActivity.this, response.body().getDetailsArrayList()));
                            } else {
                                llFacultyPendingAttendanceList.setVisibility(View.GONE);
                                llNoDataFoundPendingAttendance.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<FacultyPendingAttendancePojo> call, Throwable t) {
                            llFacultyPendingAttendanceList.setVisibility(View.GONE);
                            llFacultyPendingAttendanceProgressbar.setVisibility(View.GONE);
                            llNoDataFoundPendingAttendance.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultyPendingAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_FACULTY_PENDING_ATTENDANCE_UPDATE_LIST) {
            getFacultyPendingAttendanceApiCall();
        }
    }
}