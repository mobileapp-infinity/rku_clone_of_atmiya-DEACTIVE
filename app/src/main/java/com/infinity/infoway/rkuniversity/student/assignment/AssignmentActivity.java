package com.infinity.infoway.rkuniversity.student.assignment;

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

public class AssignmentActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseAssignment;
    RecyclerView rvStudentAssignmentList;
    LinearLayout llStudentAssignmentList, llStudentAssignmentProgressbar, llNoDataFoundStudentAssignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        initView();
        getStudentAssignmentListActivity();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(AssignmentActivity.this);
        connectionDetector = new ConnectionDetector(AssignmentActivity.this);
        ivCloseAssignment = findViewById(R.id.ivCloseAssignment);
        ivCloseAssignment.setOnClickListener(this);
        rvStudentAssignmentList = findViewById(R.id.rvStudentAssignmentList);
        llStudentAssignmentList = findViewById(R.id.llStudentAssignmentList);
        llStudentAssignmentProgressbar = findViewById(R.id.llStudentAssignmentProgressbar);
        llNoDataFoundStudentAssignment = findViewById(R.id.llNoDataFoundStudentAssignment);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseAssignment) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getStudentAssignmentListActivity() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentAssignmentList.setVisibility(View.GONE);
            llStudentAssignmentProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundStudentAssignment.setVisibility(View.GONE);
            ApiImplementer.getStudentAssignmentListApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(), new Callback<ArrayList<StudentAssignmentListPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<StudentAssignmentListPojo>> call, Response<ArrayList<StudentAssignmentListPojo>> response) {
                    llStudentAssignmentProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        llStudentAssignmentList.setVisibility(View.VISIBLE);
                        rvStudentAssignmentList.setAdapter(new StudentAssignmentListAdapter(AssignmentActivity.this, response.body()));
                    } else {
                        llStudentAssignmentList.setVisibility(View.GONE);
                        llNoDataFoundStudentAssignment.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<StudentAssignmentListPojo>> call, Throwable t) {
                    llStudentAssignmentList.setVisibility(View.GONE);
                    llStudentAssignmentProgressbar.setVisibility(View.GONE);
                    llNoDataFoundStudentAssignment.setVisibility(View.VISIBLE);
                    Toast.makeText(AssignmentActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}