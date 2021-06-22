package com.infinity.infoway.rkuniversity.student.student_activity;

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

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseStudentActivity;
    LinearLayout llStudentActivityList;
    LinearLayout llStudentActivityProgressbar;
    LinearLayout llNoDataFoundStudentActivity;
    RecyclerView rvStudentActivityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initView();
        getStudentActivityListApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentActivity.this);
        connectionDetector = new ConnectionDetector(StudentActivity.this);
        ivCloseStudentActivity = findViewById(R.id.ivCloseStudentActivity);
        ivCloseStudentActivity.setOnClickListener(this);
        llStudentActivityList = findViewById(R.id.llStudentActivityList);
        llStudentActivityProgressbar = findViewById(R.id.llStudentActivityProgressbar);
        llNoDataFoundStudentActivity = findViewById(R.id.llNoDataFoundStudentActivity);
        rvStudentActivityList = findViewById(R.id.rvStudentActivityList);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseStudentActivity) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getStudentActivityListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentActivityList.setVisibility(View.GONE);
            llStudentActivityProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundStudentActivity.setVisibility(View.GONE);
            ApiImplementer.getStudentActivityListApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getImDomainName(), new Callback<ArrayList<StudentActivityPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<StudentActivityPojo>> call, Response<ArrayList<StudentActivityPojo>> response) {
                    llStudentActivityProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        llStudentActivityList.setVisibility(View.VISIBLE);
                        rvStudentActivityList.setAdapter(new StudentActivityListAdapter(StudentActivity.this, response.body()));
                    } else {
                        llStudentActivityList.setVisibility(View.GONE);
                        llNoDataFoundStudentActivity.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<StudentActivityPojo>> call, Throwable t) {
                    llStudentActivityList.setVisibility(View.GONE);
                    llStudentActivityProgressbar.setVisibility(View.GONE);
                    llNoDataFoundStudentActivity.setVisibility(View.VISIBLE);
                    Toast.makeText(StudentActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}