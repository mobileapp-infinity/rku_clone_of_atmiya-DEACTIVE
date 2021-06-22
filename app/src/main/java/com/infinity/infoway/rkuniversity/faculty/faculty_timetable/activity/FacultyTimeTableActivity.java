package com.infinity.infoway.rkuniversity.faculty.faculty_timetable.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.fragment.FacultyTimeTableFragment;
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.pojo.FacultyTimeTablePojo;
import com.infinity.infoway.rkuniversity.student.student_timetable.adapter.TimeTableViewPagerAdapter;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyTimeTableActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyTimeTable;
    TabLayout tlFacultyTimeTable;
    ViewPager vpFacultyTimeTable;
    LinearLayout llFacultyTimeTableTab;
    LinearLayout llFacultyTimetableProgressbar;
    LinearLayout llNoDataFoundFacultyTimetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_time_table);
        initView();
        getFacultyTimeTableApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyTimeTableActivity.this);
        connectionDetector = new ConnectionDetector(FacultyTimeTableActivity.this);
        ivCloseFacultyTimeTable = findViewById(R.id.ivCloseFacultyTimeTable);
        ivCloseFacultyTimeTable.setOnClickListener(this);

        llFacultyTimeTableTab = findViewById(R.id.llFacultyTimeTableTab);
        llFacultyTimetableProgressbar = findViewById(R.id.llFacultyTimetableProgressbar);
        llNoDataFoundFacultyTimetable = findViewById(R.id.llNoDataFoundFacultyTimetable);

        tlFacultyTimeTable = findViewById(R.id.tlFacultyTimeTable);
        vpFacultyTimeTable = findViewById(R.id.vpFacultyTimeTable);

        tlFacultyTimeTable.setupWithViewPager(vpFacultyTimeTable);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivCloseFacultyTimeTable) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }




    private void getFacultyTimeTableApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyTimeTableTab.setVisibility(View.GONE);
            llFacultyTimetableProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFacultyTimetable.setVisibility(View.GONE);
            ApiImplementer.getFacultyTimeTableApiImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getEmpYearId(), new Callback<ArrayList<FacultyTimeTablePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FacultyTimeTablePojo>> call, Response<ArrayList<FacultyTimeTablePojo>> response) {
                    try {
                        llFacultyTimetableProgressbar.setVisibility(View.GONE);
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            llFacultyTimeTableTab.setVisibility(View.VISIBLE);
                            llNoDataFoundFacultyTimetable.setVisibility(View.GONE);
                            TimeTableViewPagerAdapter adapter = new TimeTableViewPagerAdapter(getSupportFragmentManager());
                            ArrayList<FacultyTimeTablePojo> facultyTimeTablePojoArrayList = response.body();
                            boolean isFragmentAdded = false;
                            for (int i = 0; i < facultyTimeTablePojoArrayList.size(); i++) {
                                FacultyTimeTablePojo facultyTimeTablePojo = facultyTimeTablePojoArrayList.get(i);
                                if (!CommonUtil.checkIsEmptyOrNullCommon(facultyTimeTablePojo.getDayName())) {
                                    String dayName = "";
                                    if (facultyTimeTablePojo.getDayName().length() > 3) {
                                        dayName = facultyTimeTablePojo.getDayName().substring(0, 3);
                                    } else {
                                        dayName = facultyTimeTablePojo.getDayName();
                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(IntentConstants.FACULTY_TIME_TABLE_DAY_WISE_LIST, (Serializable) facultyTimeTablePojo.getInoutArray1());

                                    FacultyTimeTableFragment facultyTimeTableFragment = new FacultyTimeTableFragment();
                                    facultyTimeTableFragment.setArguments(bundle);
                                    adapter.addFragment(facultyTimeTableFragment, dayName);
                                    isFragmentAdded = true;
                                }
                            }
                            if (isFragmentAdded) {
                                vpFacultyTimeTable.setAdapter(adapter);
                            }
                        } else {
                            llFacultyTimeTableTab.setVisibility(View.GONE);
                            llNoDataFoundFacultyTimetable.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception ex) {
                        Toast.makeText(FacultyTimeTableActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FacultyTimeTablePojo>> call, Throwable t) {
                    llFacultyTimeTableTab.setVisibility(View.GONE);
                    llFacultyTimetableProgressbar.setVisibility(View.GONE);
                    llNoDataFoundFacultyTimetable.setVisibility(View.VISIBLE);
                    Toast.makeText(FacultyTimeTableActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}