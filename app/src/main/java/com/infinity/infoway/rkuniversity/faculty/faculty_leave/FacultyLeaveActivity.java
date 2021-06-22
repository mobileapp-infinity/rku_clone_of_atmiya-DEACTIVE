package com.infinity.infoway.rkuniversity.faculty.faculty_leave;

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
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyLeaveActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    Calendar calendar;
    AppCompatImageView ivCloseFacultyLeave;
    LinearLayout llFacultyLeaveList, llFacultyLeaveProgressbar, llNoDataFoundFacultyLeave;
    RecyclerView rvFacultyLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_leave);
        initView();
//        getFacultyLeaveListApiCall();
    }


    private void initView() {
        calendar = Calendar.getInstance();
        mySharedPreferences = new MySharedPreferences(FacultyLeaveActivity.this);
        connectionDetector = new ConnectionDetector(FacultyLeaveActivity.this);
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


//    private void getFacultyLeaveListApiCall() {
//        if (connectionDetector.isConnectingToInternet()) {
//            llFacultyLeaveList.setVisibility(View.GONE);
//            llFacultyLeaveProgressbar.setVisibility(View.VISIBLE);
//            llNoDataFoundFacultyLeave.setVisibility(View.GONE);
//            ApiImplementer.getFacultyLeaveApiImplementer(mySharedPreferences.getEmpNumber(), calendar.get(Calendar.YEAR), new Callback<ArrayList<FacultyLeavePojo>>() {
//                @Override
//                public void onResponse(Call<ArrayList<FacultyLeavePojo>> call, Response<ArrayList<FacultyLeavePojo>> response) {
//                    llFacultyLeaveProgressbar.setVisibility(View.GONE);
//                    if (response.isSuccessful() && response.body() != null &&
//                            response.body().size() > 0) {
//                        llFacultyLeaveList.setVisibility(View.VISIBLE);
//                        llNoDataFoundFacultyLeave.setVisibility(View.GONE);
//                        rvFacultyLeave.setAdapter(new FacultyLeaveAdapter(FacultyLeaveActivity.this, response.body()));
//                    } else {
//                        llFacultyLeaveList.setVisibility(View.GONE);
//                        llNoDataFoundFacultyLeave.setVisibility(View.VISIBLE);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ArrayList<FacultyLeavePojo>> call, Throwable t) {
//                    llFacultyLeaveList.setVisibility(View.GONE);
//                    llFacultyLeaveProgressbar.setVisibility(View.GONE);
//                    llNoDataFoundFacultyLeave.setVisibility(View.VISIBLE);
//                    Toast.makeText(FacultyLeaveActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
//        }
//    }
}