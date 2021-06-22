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
import com.infinity.infoway.rkuniversity.student.exam.adapter.ResultListAdapter;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentReulstPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentResultActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivCloseResult;
    ConnectionDetector connectionDetector;
    LinearLayout llStudentResultMarksList, llResultMarksProgressbar, llNoDataFoundResultMarks;
    RecyclerView rvResultMarksStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
//        getStudentCIAMarksListApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentResultActivity.this);
        connectionDetector = new ConnectionDetector(StudentResultActivity.this);
        ivCloseResult = findViewById(R.id.ivCloseResultMarks);
        ivCloseResult.setOnClickListener(this);
        rvResultMarksStudent = findViewById(R.id.rvResultMarksStudent);
        llStudentResultMarksList = findViewById(R.id.llStudentResultMarksList);
        llResultMarksProgressbar = findViewById(R.id.llResultProgressbar);
        llNoDataFoundResultMarks = findViewById(R.id.llNoDataFoundResult);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseResultMarks) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


//    private void getStudentCIAMarksListApiCall() {
//
//        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getStudentEnrollmentNo())) {
//            if (connectionDetector.isConnectingToInternet()) {
//                llStudentResultMarksList.setVisibility(View.GONE);
//                llResultMarksProgressbar.setVisibility(View.VISIBLE);
//                llNoDataFoundResultMarks.setVisibility(View.GONE);
//                ApiImplementer.getStudentResultListApiImplementer(mySharedPreferences.getStudentEnrollmentNo(), new Callback<ArrayList<StudentReulstPojo>>() {
//                    @Override
//                    public void onResponse(Call<ArrayList<StudentReulstPojo>> call, Response<ArrayList<StudentReulstPojo>> response) {
//                        llResultMarksProgressbar.setVisibility(View.GONE);
//                        if (response.isSuccessful() && response.body() != null &&
//                                response.body().size() > 0) {
//                            llStudentResultMarksList.setVisibility(View.VISIBLE);
//                            llNoDataFoundResultMarks.setVisibility(View.GONE);
//                            rvResultMarksStudent.setAdapter(new ResultListAdapter(StudentResultActivity.this, response.body()));
//                        } else {
//                            llStudentResultMarksList.setVisibility(View.GONE);
//                            llNoDataFoundResultMarks.setVisibility(View.VISIBLE);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ArrayList<StudentReulstPojo>> call, Throwable t) {
//                        llStudentResultMarksList.setVisibility(View.GONE);
//                        llResultMarksProgressbar.setVisibility(View.GONE);
//                        llNoDataFoundResultMarks.setVisibility(View.VISIBLE);
//                        Toast.makeText(StudentResultActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            } else {
//                Toast.makeText(this, "No internet connection,please try again later", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            llStudentResultMarksList.setVisibility(View.GONE);
//            llResultMarksProgressbar.setVisibility(View.GONE);
//            llNoDataFoundResultMarks.setVisibility(View.VISIBLE);
//            Toast.makeText(this, "Enrollment no not generated!", Toast.LENGTH_SHORT).show();//As per talk with priyanka madam 21-12-2020 if enrollment no not found then display this massage
//        }
//
//    }

}
