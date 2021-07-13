package com.infinity.infoway.rkuniversity.student.student_timetable.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.student.student_timetable.adapter.TimeTableViewPagerAdapter;
import com.infinity.infoway.rkuniversity.student.student_timetable.fragment.TimeTableFragment;
import com.infinity.infoway.rkuniversity.student.student_timetable.pojo.StudentTimeTablePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentTimeTableActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseStudentTimeTable;
    TabLayout tlTimeTable;
    ViewPager vpTimeTable;
    LinearLayout llStudentTimetableTab;
    LinearLayout llTimetableProgressbar;
    LinearLayout llNoDataFoundTimetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_time_table);
        initView();
        getStudentTimeTableApiCall();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentTimeTableActivity.this);
        connectionDetector = new ConnectionDetector(StudentTimeTableActivity.this);
        ivCloseStudentTimeTable = findViewById(R.id.ivCloseStudentTimeTable);
        ivCloseStudentTimeTable.setOnClickListener(this);

        llStudentTimetableTab = findViewById(R.id.llStudentTimetableTab);
        llTimetableProgressbar = findViewById(R.id.llTimetableProgressbar);
        llNoDataFoundTimetable = findViewById(R.id.llNoDataFoundTimetable);

        tlTimeTable = findViewById(R.id.tlTimeTable);
        vpTimeTable = findViewById(R.id.vpTimeTable);

        tlTimeTable.setupWithViewPager(vpTimeTable);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivCloseStudentTimeTable) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getStudentTimeTableApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentTimetableTab.setVisibility(View.GONE);
            llTimetableProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundTimetable.setVisibility(View.GONE);
            ApiImplementer.getStudentTimetableApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getCourseId(), mySharedPreferences.getSwdDivisionId(),
                    mySharedPreferences.getShiftId(), mySharedPreferences.getSwdBatchId(), mySharedPreferences.getSwdYearId(), new Callback<ArrayList<StudentTimeTablePojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<StudentTimeTablePojo>> call, Response<ArrayList<StudentTimeTablePojo>> response) {
                            try {
                                llTimetableProgressbar.setVisibility(View.GONE);
                                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                                    llStudentTimetableTab.setVisibility(View.VISIBLE);
                                    llNoDataFoundTimetable.setVisibility(View.GONE);
                                    TimeTableViewPagerAdapter adapter = new TimeTableViewPagerAdapter(getSupportFragmentManager());
                                    ArrayList<StudentTimeTablePojo> studentTimeTablePojoArrayList = response.body();
                                    boolean isFragmentAdded = false;
                                    for (int i = 0; i < studentTimeTablePojoArrayList.size(); i++) {
                                        StudentTimeTablePojo studentTimeTablePojo = studentTimeTablePojoArrayList.get(i);
                                        if (!CommonUtil.checkIsEmptyOrNullCommon(studentTimeTablePojo.getDayName())) {
                                            String dayName = "";
                                            if (studentTimeTablePojo.getDayName().length() > 3) {
                                                dayName = studentTimeTablePojo.getDayName().substring(0, 3);
                                            } else {
                                                dayName = studentTimeTablePojo.getDayName();
                                            }
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable(IntentConstants.STUDENT_TIME_TABLE_DAY_WISE_LIST, (Serializable) studentTimeTablePojo.getInoutArray1());
                                            if (studentTimeTablePojo.getIsDisplayTime() != null) {
                                                bundle.putBoolean(IntentConstants.IS_DISPLAY_TIME, studentTimeTablePojo.getIsDisplayTime() == 1);
                                            }
                                            TimeTableFragment timeTableFragment = new TimeTableFragment();
                                            timeTableFragment.setArguments(bundle);
                                            adapter.addFragment(timeTableFragment, dayName);
                                            isFragmentAdded = true;
                                        }
                                    }
                                    if (isFragmentAdded) {
                                        vpTimeTable.setAdapter(adapter);
                                    }
                                } else {
                                    llStudentTimetableTab.setVisibility(View.GONE);
                                    llNoDataFoundTimetable.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                Toast.makeText(StudentTimeTableActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<StudentTimeTablePojo>> call, Throwable t) {
                            llStudentTimetableTab.setVisibility(View.GONE);
                            llTimetableProgressbar.setVisibility(View.GONE);
                            llNoDataFoundTimetable.setVisibility(View.VISIBLE);
                            Toast.makeText(StudentTimeTableActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet Connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}