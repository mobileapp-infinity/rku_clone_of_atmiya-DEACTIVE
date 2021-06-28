package com.infinity.infoway.rkuniversity.faculty.faculty_student_messages;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.faculty.faculty_student_messages.adapter.StudentMsgAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_student_messages.pojo.StudentMsgListPojo;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentMessagesActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    Calendar calendar;
    AppCompatImageView ivCloseFacultyLeave;
    LinearLayout llFacultyLeaveList, llFacultyLeaveProgressbar, llNoDataFoundFacultyLeave;
    RecyclerView rvFacultyLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_messages);
        initView();
        getStudentMessages();
    }


    private void initView() {
        calendar = Calendar.getInstance();
        mySharedPreferences = new MySharedPreferences(StudentMessagesActivity.this);
        connectionDetector = new ConnectionDetector(StudentMessagesActivity.this);
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


    private void getStudentMessages() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyLeaveList.setVisibility(View.GONE);
            llFacultyLeaveProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFacultyLeave.setVisibility(View.GONE);
            ApiImplementer.getStudentMessagesApiImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getEmpInstituteId(), new Callback<ArrayList<StudentMsgListPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<StudentMsgListPojo>> call, Response<ArrayList<StudentMsgListPojo>> response) {
                    llFacultyLeaveProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().size() > 0) {
                        llFacultyLeaveList.setVisibility(View.VISIBLE);
                        llNoDataFoundFacultyLeave.setVisibility(View.GONE);
                        rvFacultyLeave.setAdapter(new StudentMsgAdapter(StudentMessagesActivity.this, response.body()));
                    } else {
                        llFacultyLeaveList.setVisibility(View.GONE);
                        llNoDataFoundFacultyLeave.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<StudentMsgListPojo>> call, Throwable t) {
                    llFacultyLeaveList.setVisibility(View.GONE);
                    llFacultyLeaveProgressbar.setVisibility(View.GONE);
                    llNoDataFoundFacultyLeave.setVisibility(View.VISIBLE);
                    Toast.makeText(StudentMessagesActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}