package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_lab_or_lecture_plan_teaching_update;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyLabOrLecturePlanTeachingUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyLabOrLecture;
    LinearLayout llFacultyLabOrLecturePlan, llFacultyStudentLabOrLecturePlanProgressbar,
            llNoDataFoundFacultyLabOrLecturePlan;
    LinearLayout llFacultyPaginationProgressbarLabOrLecturePlan;
    LinearLayoutManager layoutManager;

    RecyclerView rvFacultyLabOrLecturePlan;
    FacultyLabOrLectureTeachingListAdapter facultyLabOrLectureTeachingListAdapter;
    ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo> facultyLabOrLecturePlanTeachingUpdatePojoArrayList = new ArrayList<>();
    private int currentPageNo = 1;
    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentItemCount = 0;
    private int totalItemCount = 0;
    private int scrolledOutItemCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_lab_or_lecure_plan_teaching_update);
        initView();
        getFacultyLabOrLecturePlanTeachingUpdateApiCall(true);
        rvFacultyLabOrLecturePlan.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                scrolledOutItemCount = layoutManager.findFirstVisibleItemPosition();

                if ((hasMoreData) && (isScrolling) && (currentItemCount + scrolledOutItemCount == totalItemCount)) {
                    isScrolling = false;
                    currentPageNo = currentPageNo + 1;
                    getFacultyLabOrLecturePlanTeachingUpdateApiCall(false);
                }
            }
        });
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyLabOrLecturePlanTeachingUpdateActivity.this);
        connectionDetector = new ConnectionDetector(FacultyLabOrLecturePlanTeachingUpdateActivity.this);
        ivCloseFacultyLabOrLecture = findViewById(R.id.ivCloseFacultyLabOrLecture);
        ivCloseFacultyLabOrLecture.setOnClickListener(this);
        rvFacultyLabOrLecturePlan = findViewById(R.id.rvFacultyLabOrLecturePlan);
        layoutManager = new LinearLayoutManager(FacultyLabOrLecturePlanTeachingUpdateActivity.this);
        rvFacultyLabOrLecturePlan.setLayoutManager(layoutManager);
        llFacultyLabOrLecturePlan = findViewById(R.id.llFacultyLabOrLecturePlan);
        llFacultyStudentLabOrLecturePlanProgressbar = findViewById(R.id.llFacultyStudentLabOrLecturePlanProgressbar);
        llNoDataFoundFacultyLabOrLecturePlan = findViewById(R.id.llNoDataFoundFacultyLabOrLecturePlan);
        llFacultyPaginationProgressbarLabOrLecturePlan = findViewById(R.id.llFacultyPaginationProgressbarLabOrLecturePlan);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyLabOrLecture) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getFacultyLabOrLecturePlanTeachingUpdateApiCall(boolean isProgressbarShowing) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isProgressbarShowing) {
                llFacultyStudentLabOrLecturePlanProgressbar.setVisibility(View.VISIBLE);
            } else {
                llFacultyPaginationProgressbarLabOrLecturePlan.setVisibility(View.VISIBLE);
            }
            ApiImplementer.getApprovedLecturePlanningFacultyWiseApiImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getEmpInstituteId(),
                    mySharedPreferences.getEmpYearId(), String.valueOf(CommonUtil.ROW_PER_PAGE), String.valueOf(currentPageNo), new Callback<ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo>> call, Response<ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo>> response) {
                            try {
                                if (isProgressbarShowing) {
                                    llFacultyStudentLabOrLecturePlanProgressbar.setVisibility(View.GONE);
                                } else {
                                    llFacultyPaginationProgressbarLabOrLecturePlan.setVisibility(View.GONE);
                                }
                                if (response.isSuccessful() && response.body() != null) {

                                    llFacultyLabOrLecturePlan.setVisibility(View.VISIBLE);
                                    llNoDataFoundFacultyLabOrLecturePlan.setVisibility(View.GONE);

                                    facultyLabOrLecturePlanTeachingUpdatePojoArrayList.addAll(response.body()); //response body
                                    if (!response.body().isEmpty() && response.body().size() > 0) {
                                        if (currentPageNo == 1) {
                                            facultyLabOrLectureTeachingListAdapter = new FacultyLabOrLectureTeachingListAdapter(FacultyLabOrLecturePlanTeachingUpdateActivity.this, facultyLabOrLecturePlanTeachingUpdatePojoArrayList);
                                            rvFacultyLabOrLecturePlan.setAdapter(facultyLabOrLectureTeachingListAdapter);
                                        } else {
                                            facultyLabOrLectureTeachingListAdapter.notifyDataSetChanged();
                                        }
                                    } else {
                                        if (currentPageNo == 1) {
                                            llFacultyLabOrLecturePlan.setVisibility(View.GONE);
                                            llFacultyStudentLabOrLecturePlanProgressbar.setVisibility(View.GONE);
                                            llNoDataFoundFacultyLabOrLecturePlan.setVisibility(View.VISIBLE);
                                        } else {
                                            hasMoreData = false;
                                        }
                                    }
                                } else {
                                    Toast.makeText(FacultyLabOrLecturePlanTeachingUpdateActivity.this, "Response Code:- " + response.code(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo>> call, Throwable t) {
                            llFacultyLabOrLecturePlan.setVisibility(View.GONE);
                            llFacultyStudentLabOrLecturePlanProgressbar.setVisibility(View.GONE);
                            llNoDataFoundFacultyLabOrLecturePlan.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultyLabOrLecturePlanTeachingUpdateActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}