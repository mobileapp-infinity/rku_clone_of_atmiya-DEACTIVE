package com.infinity.infoway.rkuniversity.student.student_calender;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.event_calender.EventModel;
import com.infinity.infoway.rkuniversity.event_calender.GridAdapter;
import com.infinity.infoway.rkuniversity.event_calender.SlyCalendarData;
import com.infinity.infoway.rkuniversity.event_calender.SlyCalendarView;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentCalenderActivity extends AppCompatActivity implements GridAdapter.IGridAdapterData, View.OnClickListener {

    private SlyCalendarView customItsCustom;
    private SlyCalendarData slyCalendarData = new SlyCalendarData();
    private LinearLayout llNoEventFound;
    private RecyclerView rvEventList;
    private AppCompatTextView tvMsg;
    private LinearLayout llDynamicContect;
    private LinearLayout llBg;
    private LinearLayout llContentNoDataFound;
    private MySharedPreferences mySharedPreferences;
    private ConnectionDetector connectionDetector;
    private AppCompatImageView ivCloseLessonPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_calender);
        init();
        getCalendarEventsApiCall();
    }

    private void init() {
        connectionDetector = new ConnectionDetector(StudentCalenderActivity.this);
        llNoEventFound = findViewById(R.id.llNoEventFound);
        ivCloseLessonPlan = findViewById(R.id.ivCloseLessonPlan);
        ivCloseLessonPlan.setOnClickListener(this);
        tvMsg = findViewById(R.id.tvMsg);
        rvEventList = findViewById(R.id.rvEventList);
        customItsCustom = findViewById(R.id.customItsCustom);
        llDynamicContect = findViewById(R.id.llDynamicContect);
        llBg = findViewById(R.id.llBg);
        llContentNoDataFound = findViewById(R.id.llContentNoDataFound);
        mySharedPreferences = new MySharedPreferences(StudentCalenderActivity.this);
    }

    @Override
    public void gridAdapterData(ArrayList<EventModel.Array> eventDetailsArrayList) {
        if (eventDetailsArrayList != null) {
            if (eventDetailsArrayList.size() <= 0) {
                tvMsg.setText("No Events Organized on this day!");
                llNoEventFound.setVisibility(View.VISIBLE);
                rvEventList.setVisibility(View.GONE);
            } else {
                llNoEventFound.setVisibility(View.GONE);
                rvEventList.setVisibility(View.VISIBLE);
                StudentEventListAdapter studentEventListAdapter = new StudentEventListAdapter(StudentCalenderActivity.this, eventDetailsArrayList);
                rvEventList.setAdapter(studentEventListAdapter);
            }
        }
    }

    private void getCalendarEventsApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llDynamicContect.setVisibility(View.GONE);
            llBg.setVisibility(View.VISIBLE);
            llContentNoDataFound.setVisibility(View.GONE);
            ApiImplementer.getCalendarEventsApiImplementer(mySharedPreferences.getInstituteId(), new Callback<ArrayList<EventModel>>() {
                @Override
                public void onResponse(Call<ArrayList<EventModel>> call, Response<ArrayList<EventModel>> response) {
                    llBg.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        llDynamicContect.setVisibility(View.VISIBLE);
                        slyCalendarData.setSingle(false);
                        customItsCustom.setSlyCalendarData(StudentCalenderActivity.this, slyCalendarData, response.body());
                    } else {
                        llDynamicContect.setVisibility(View.GONE);
                        llContentNoDataFound.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<EventModel>> call, Throwable t) {
                    llDynamicContect.setVisibility(View.GONE);
                    llBg.setVisibility(View.GONE);
                    llContentNoDataFound.setVisibility(View.VISIBLE);
                    Toast.makeText(StudentCalenderActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(StudentCalenderActivity.this, "No internet connection Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseLessonPlan){
            onBackPressed();
        }
    }
}