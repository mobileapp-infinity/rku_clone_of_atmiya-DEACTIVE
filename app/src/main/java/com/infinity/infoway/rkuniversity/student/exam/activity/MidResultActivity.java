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
import com.infinity.infoway.rkuniversity.student.exam.adapter.StudentMidResultListAdapter;
import com.infinity.infoway.rkuniversity.student.exam.pojo.MidResultPojo;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MidResultActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivCloseMidResult;
    ConnectionDetector connectionDetector;
    LinearLayout llStudentMidResultList, llMidResultProgressbar, llNoDataFoundMidResult;
    RecyclerView rvMidResultStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_result);
        initView();
        getStudentMidResultListApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(MidResultActivity.this);
        connectionDetector = new ConnectionDetector(MidResultActivity.this);
        ivCloseMidResult = findViewById(R.id.ivCloseMidResult);
        ivCloseMidResult.setOnClickListener(this);
        rvMidResultStudent = findViewById(R.id.rvMidResultStudent);
        llStudentMidResultList = findViewById(R.id.llStudentMidResultList);
        llMidResultProgressbar = findViewById(R.id.llMidResultProgressbar);
        llNoDataFoundMidResult = findViewById(R.id.llNoDataFoundMidResult);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseMidResult) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getStudentMidResultListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentMidResultList.setVisibility(View.GONE);
            llMidResultProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundMidResult.setVisibility(View.GONE);
            ApiImplementer.getStudentMidResultApiImplementer(mySharedPreferences.getInstituteId(), mySharedPreferences.getDmId(), mySharedPreferences.getCourseId(),
                    mySharedPreferences.getSmId(), mySharedPreferences.getStudentId(), "0", new Callback<ArrayList<MidResultPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<MidResultPojo>> call, Response<ArrayList<MidResultPojo>> response) {
                            llMidResultProgressbar.setVisibility(View.GONE);
                            if (response.isSuccessful() && response.body() != null &&
                                    response.body().size() > 0) {
                                llStudentMidResultList.setVisibility(View.VISIBLE);
                                llNoDataFoundMidResult.setVisibility(View.GONE);
                                rvMidResultStudent.setAdapter(new StudentMidResultListAdapter(MidResultActivity.this, response.body()));
                            } else {
                                llStudentMidResultList.setVisibility(View.GONE);
                                llNoDataFoundMidResult.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<MidResultPojo>> call, Throwable t) {
                            llStudentMidResultList.setVisibility(View.GONE);
                            llMidResultProgressbar.setVisibility(View.GONE);
                            llNoDataFoundMidResult.setVisibility(View.VISIBLE);
                            Toast.makeText(MidResultActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection, Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}