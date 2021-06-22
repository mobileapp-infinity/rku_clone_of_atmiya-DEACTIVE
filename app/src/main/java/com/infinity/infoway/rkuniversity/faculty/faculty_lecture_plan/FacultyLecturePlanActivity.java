package com.infinity.infoway.rkuniversity.faculty.faculty_lecture_plan;

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

public class FacultyLecturePlanActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    LinearLayout llFacultyLecturePlanList, llFacultyLecturePlanProgressbar, llNoDataFoundFacultyLecturePlan;
    RecyclerView rvFacultyLecturePlan;
    AppCompatImageView ivCloseFacultyLecturePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_lecture_plan);
        initView();
        getFacultyLecturePlanApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyLecturePlanActivity.this);
        connectionDetector = new ConnectionDetector(FacultyLecturePlanActivity.this);
        ivCloseFacultyLecturePlan = findViewById(R.id.ivCloseFacultyLecturePlan);
        ivCloseFacultyLecturePlan.setOnClickListener(this);
        llFacultyLecturePlanList = findViewById(R.id.llFacultyLecturePlanList);
        llFacultyLecturePlanProgressbar = findViewById(R.id.llFacultyLecturePlanProgressbar);
        llNoDataFoundFacultyLecturePlan = findViewById(R.id.llNoDataFoundFacultyLecturePlan);
        rvFacultyLecturePlan = findViewById(R.id.rvFacultyLecturePlan);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyLecturePlan) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getFacultyLecturePlanApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyLecturePlanProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFacultyLecturePlan.setVisibility(View.GONE);
            llFacultyLecturePlanList.setVisibility(View.GONE);
            ApiImplementer.getEmployeeWiseLecturePlanningApiImplementer(mySharedPreferences.getEmpId(), new Callback<ArrayList<FacultyLecturePlanPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FacultyLecturePlanPojo>> call, Response<ArrayList<FacultyLecturePlanPojo>> response) {
                    llFacultyLecturePlanProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        llFacultyLecturePlanList.setVisibility(View.VISIBLE);
                        rvFacultyLecturePlan.setAdapter(new FacultyLecturePlanListAdapter(FacultyLecturePlanActivity.this, response.body()));
                    } else {
                        llFacultyLecturePlanList.setVisibility(View.GONE);
                        llNoDataFoundFacultyLecturePlan.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FacultyLecturePlanPojo>> call, Throwable t) {
                    llFacultyLecturePlanProgressbar.setVisibility(View.GONE);
                    llFacultyLecturePlanList.setVisibility(View.GONE);
                    llNoDataFoundFacultyLecturePlan.setVisibility(View.VISIBLE);
                    Toast.makeText(FacultyLecturePlanActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}