package com.infinity.infoway.rkuniversity.student.student_syllabus;

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

public class StudentSyllabusActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseStudentSyllabus;
    RecyclerView rvStudentSyllabus;
    LinearLayout llStudentSyllabusList, llStudentSyllabusProgressbar, llNoDataFoundStudentSyllabus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_syllabus);
        initView();
        getStudentSyllabusListApiCall();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentSyllabusActivity.this);
        connectionDetector = new ConnectionDetector(StudentSyllabusActivity.this);
        ivCloseStudentSyllabus = findViewById(R.id.ivCloseStudentSyllabus);
        ivCloseStudentSyllabus.setOnClickListener(this);
        rvStudentSyllabus = findViewById(R.id.rvStudentSyllabus);
        llStudentSyllabusList = findViewById(R.id.llStudentSyllabusList);
        llStudentSyllabusProgressbar = findViewById(R.id.llStudentSyllabusProgressbar);
        llNoDataFoundStudentSyllabus = findViewById(R.id.llNoDataFoundStudentSyllabus);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseStudentSyllabus) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getStudentSyllabusListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentSyllabusList.setVisibility(View.GONE);
            llStudentSyllabusProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundStudentSyllabus.setVisibility(View.GONE);
            ApiImplementer.getStudentSyllabusListApiImplementer(mySharedPreferences.getStudentId(), new Callback<ArrayList<SyllabusListPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<SyllabusListPojo>> call, Response<ArrayList<SyllabusListPojo>> response) {
                    llStudentSyllabusProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().size() > 0) {
                        llStudentSyllabusList.setVisibility(View.VISIBLE);
                        rvStudentSyllabus.setAdapter(new StudentSyllabusListAdapter(StudentSyllabusActivity.this, response.body()));
                    } else {
                        llStudentSyllabusList.setVisibility(View.GONE);
                        llNoDataFoundStudentSyllabus.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<SyllabusListPojo>> call, Throwable t) {
                    llStudentSyllabusList.setVisibility(View.GONE);
                    llStudentSyllabusProgressbar.setVisibility(View.GONE);
                    llNoDataFoundStudentSyllabus.setVisibility(View.VISIBLE);
                    Toast.makeText(StudentSyllabusActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}