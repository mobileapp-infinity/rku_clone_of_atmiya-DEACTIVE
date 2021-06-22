package com.infinity.infoway.rkuniversity.faculty.faculty_rem_attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyRemAttendanceActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyRemAttendance;
    LinearLayout llRemAttendanceList, llFacultyRemAttendanceProgressbar, llNoDataFoundFacultyRemAttendance;
    RecyclerView rvFacultyRemAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_rem_attendance);
        initView();
        getFacultyRemAttendanceApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyRemAttendanceActivity.this);
        connectionDetector = new ConnectionDetector(FacultyRemAttendanceActivity.this);
        ivCloseFacultyRemAttendance = findViewById(R.id.ivCloseFacultyRemAttendance);
        ivCloseFacultyRemAttendance.setOnClickListener(this);
        rvFacultyRemAttendance = findViewById(R.id.rvFacultyRemAttendance);
        llRemAttendanceList = findViewById(R.id.llRemAttendanceList);
        llFacultyRemAttendanceProgressbar = findViewById(R.id.llFacultyRemAttendanceProgressbar);
        llNoDataFoundFacultyRemAttendance = findViewById(R.id.llNoDataFoundFacultyRemAttendance);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyRemAttendance) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getFacultyRemAttendanceApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llRemAttendanceList.setVisibility(View.GONE);
            llFacultyRemAttendanceProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFacultyRemAttendance.setVisibility(View.GONE);
            ApiImplementer.getFacultyRemAttendanceApiImplementer(mySharedPreferences.getEmpId(), new Callback<ArrayList<FacultyRemAttendancePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FacultyRemAttendancePojo>> call, Response<ArrayList<FacultyRemAttendancePojo>> response) {
                    llFacultyRemAttendanceProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().size() > 0) {
                        llRemAttendanceList.setVisibility(View.VISIBLE);
                        llNoDataFoundFacultyRemAttendance.setVisibility(View.GONE);
                        rvFacultyRemAttendance.setAdapter(new FacultyRemAttendanceListAdapter(FacultyRemAttendanceActivity.this, response.body()));
                    } else {
                        llRemAttendanceList.setVisibility(View.GONE);
                        llNoDataFoundFacultyRemAttendance.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FacultyRemAttendancePojo>> call, Throwable t) {
                    llRemAttendanceList.setVisibility(View.GONE);
                    llFacultyRemAttendanceProgressbar.setVisibility(View.GONE);
                    llNoDataFoundFacultyRemAttendance.setVisibility(View.VISIBLE);
                    Toast.makeText(FacultyRemAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}