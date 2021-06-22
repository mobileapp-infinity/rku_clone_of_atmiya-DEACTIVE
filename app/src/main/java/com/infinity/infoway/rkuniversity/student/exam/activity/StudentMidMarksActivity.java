package com.infinity.infoway.rkuniversity.student.exam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.student.exam.adapter.StudentMidMarksHeaderAdapter;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentMidMarksPojo;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentMidMarksActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivCloseStudentMidMarks;
    LinearLayout llStudentMidMarksList;
    RecyclerView rvStudentMidMarks;
    LinearLayout llMidMarksProgressbar;
    LinearLayout llNoDataFoundStudentMidMarks;
    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mid_marks);
        initView();
        getStudentMidMarksApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentMidMarksActivity.this);
        connectionDetector = new ConnectionDetector(StudentMidMarksActivity.this);
        ivCloseStudentMidMarks = findViewById(R.id.ivCloseStudentMidMarks);
        ivCloseStudentMidMarks.setOnClickListener(this);
        llStudentMidMarksList = findViewById(R.id.llStudentMidMarksList);
        rvStudentMidMarks = findViewById(R.id.rvStudentMidMarks);
        llMidMarksProgressbar = findViewById(R.id.llMidMarksProgressbar);
        llNoDataFoundStudentMidMarks = findViewById(R.id.llNoDataFoundStudentMidMarks);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseStudentMidMarks) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getStudentMidMarksApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentMidMarksList.setVisibility(View.GONE);
            llMidMarksProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundStudentMidMarks.setVisibility(View.GONE);
            ApiImplementer.getStudentMidMarksApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSmId(),
                    mySharedPreferences.getSwdDivisionId(), mySharedPreferences.getSwdYearId(), new Callback<ArrayList<StudentMidMarksPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<StudentMidMarksPojo>> call, Response<ArrayList<StudentMidMarksPojo>> response) {
                            llMidMarksProgressbar.setVisibility(View.GONE);
                            if (response.isSuccessful() && response.body() != null &&
                                    response.body().size() > 0) {
                                llStudentMidMarksList.setVisibility(View.VISIBLE);
                                llNoDataFoundStudentMidMarks.setVisibility(View.GONE);
                                rvStudentMidMarks.setAdapter(new StudentMidMarksHeaderAdapter(StudentMidMarksActivity.this, response.body()));
                            } else {
                                llStudentMidMarksList.setVisibility(View.GONE);
                                llNoDataFoundStudentMidMarks.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<StudentMidMarksPojo>> call, Throwable t) {
                            llStudentMidMarksList.setVisibility(View.GONE);
                            llMidMarksProgressbar.setVisibility(View.GONE);
                            llNoDataFoundStudentMidMarks.setVisibility(View.VISIBLE);
                            Toast.makeText(StudentMidMarksActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection, Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}