package com.infinity.infoway.rkuniversity.student.lesson_plan;

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

public class StudentLessonPlanActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    LinearLayout llStudentLessonPlan, llStudentLessonPlanProgressbar, llNoDataFoundStudentLessonPlan;
    RecyclerView rvStudentLessonPlan;
    AppCompatImageView ivCloseLessonPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_plan);
        initView();
        getStudentLessonPlanListApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentLessonPlanActivity.this);
        connectionDetector = new ConnectionDetector(StudentLessonPlanActivity.this);
        ivCloseLessonPlan = findViewById(R.id.ivCloseLessonPlan);
        ivCloseLessonPlan.setOnClickListener(this);
        llStudentLessonPlan = findViewById(R.id.llStudentLessonPlan);
        llStudentLessonPlanProgressbar = findViewById(R.id.llStudentLessonPlanProgressbar);
        llNoDataFoundStudentLessonPlan = findViewById(R.id.llNoDataFoundStudentLessonPlan);
        rvStudentLessonPlan = findViewById(R.id.rvStudentLessonPlan);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseLessonPlan) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getStudentLessonPlanListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentLessonPlanProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundStudentLessonPlan.setVisibility(View.GONE);
            llStudentLessonPlan.setVisibility(View.GONE);
            ApiImplementer.getStudentLessonPlanListApiImplementer(mySharedPreferences.getStudentId(), new Callback<ArrayList<StudentLessonPlanListPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<StudentLessonPlanListPojo>> call, Response<ArrayList<StudentLessonPlanListPojo>> response) {
                    llStudentLessonPlanProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        llStudentLessonPlan.setVisibility(View.VISIBLE);
                        rvStudentLessonPlan.setAdapter(new StudentLessonPlanListAdapter(StudentLessonPlanActivity.this, response.body()));
                    } else {
                        llStudentLessonPlan.setVisibility(View.GONE);
                        llNoDataFoundStudentLessonPlan.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<StudentLessonPlanListPojo>> call, Throwable t) {
                    llStudentLessonPlanProgressbar.setVisibility(View.GONE);
                    llStudentLessonPlan.setVisibility(View.GONE);
                    llNoDataFoundStudentLessonPlan.setVisibility(View.VISIBLE);
                    Toast.makeText(StudentLessonPlanActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again.", Toast.LENGTH_SHORT).show();
        }
    }


}