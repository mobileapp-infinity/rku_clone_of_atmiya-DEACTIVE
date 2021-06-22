package com.infinity.infoway.rkuniversity.student.home_work.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.student.home_work.adapter.StudentHomeWorkViewPagerAdapter;
import com.infinity.infoway.rkuniversity.student.home_work.pojo.StudentHomeWorkPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentHomeWorkActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseStudentHomeWork;
    TabLayout tlHomeWork;
    ViewPager vpHomeWork;
    LinearLayout llStudentHomeWorkTab;
    LinearLayout llHomeWorkProgressbar;
    LinearLayout llNoDataFoundHomeWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_work);
        initView();
        getStudentHomeWorkApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentHomeWorkActivity.this);
        connectionDetector = new ConnectionDetector(StudentHomeWorkActivity.this);
        ivCloseStudentHomeWork = findViewById(R.id.ivCloseStudentHomeWork);
        ivCloseStudentHomeWork.setOnClickListener(this);

        llStudentHomeWorkTab = findViewById(R.id.llStudentHomeWorkTab);
        llHomeWorkProgressbar = findViewById(R.id.llHomeWorkProgressbar);
        llNoDataFoundHomeWork = findViewById(R.id.llNoDataFoundHomeWork);

        tlHomeWork = findViewById(R.id.tlHomeWork);
        vpHomeWork = findViewById(R.id.vpHomeWork);

        tlHomeWork.setupWithViewPager(vpHomeWork);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivCloseStudentHomeWork) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getStudentHomeWorkApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentHomeWorkTab.setVisibility(View.GONE);
            llHomeWorkProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundHomeWork.setVisibility(View.GONE);
            ApiImplementer.getStudentHomeWorkApiImplementer(mySharedPreferences.getStudentId(),
                    mySharedPreferences.getSwdYearId(), mySharedPreferences.getSwdDivisionId(), new Callback<ArrayList<StudentHomeWorkPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<StudentHomeWorkPojo>> call, Response<ArrayList<StudentHomeWorkPojo>> response) {
                            try {
                                llHomeWorkProgressbar.setVisibility(View.GONE);
                                if (response.isSuccessful() && response.body() != null &&
                                        response.body().size() > 0) {
                                    llStudentHomeWorkTab.setVisibility(View.VISIBLE);
                                    llNoDataFoundHomeWork.setVisibility(View.GONE);
                                    StudentHomeWorkViewPagerAdapter studentHomeWorkViewPagerAdapter = new StudentHomeWorkViewPagerAdapter(getSupportFragmentManager());
                                    ArrayList<StudentHomeWorkPojo> studentHomeWorkPojoArrayList = response.body();
                                    boolean isFragmentAdded = false;
                                    for (int i = 0; i < studentHomeWorkPojoArrayList.size(); i++) {
                                        StudentHomeWorkPojo studentHomeWorkPojo = studentHomeWorkPojoArrayList.get(i);
                                        if (!CommonUtil.checkIsEmptyOrNullCommon(studentHomeWorkPojo.getDayName())) {
                                            String dayName = "";
                                            if (studentHomeWorkPojo.getDayName().length() > 3) {
                                                dayName = studentHomeWorkPojo.getDayName().substring(0, 3);
                                            } else {
                                                dayName = studentHomeWorkPojo.getDayName();
                                            }
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable(IntentConstants.STUDENT_HOME_WORK_DAY_WISE_LIST, (Serializable) studentHomeWorkPojo.getHomeworkArray());

                                            StudentHomeWorkFragment studentHomeWorkFragment = new StudentHomeWorkFragment();
                                            studentHomeWorkFragment.setArguments(bundle);
                                            studentHomeWorkViewPagerAdapter.addFragment(studentHomeWorkFragment, dayName);
                                            isFragmentAdded = true;
                                        }
                                    }
                                    if (isFragmentAdded) {
                                        vpHomeWork.setAdapter(studentHomeWorkViewPagerAdapter);
                                    }
                                } else {
                                    llStudentHomeWorkTab.setVisibility(View.GONE);
                                    llNoDataFoundHomeWork.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                Toast.makeText(StudentHomeWorkActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<StudentHomeWorkPojo>> call, Throwable t) {
                            llStudentHomeWorkTab.setVisibility(View.GONE);
                            llHomeWorkProgressbar.setVisibility(View.GONE);
                            llNoDataFoundHomeWork.setVisibility(View.VISIBLE);
                            Toast.makeText(StudentHomeWorkActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet Connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}