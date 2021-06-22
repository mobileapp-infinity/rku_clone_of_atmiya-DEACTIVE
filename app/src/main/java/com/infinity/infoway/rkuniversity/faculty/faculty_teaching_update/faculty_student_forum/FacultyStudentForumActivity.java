package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_student_forum;

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

public class FacultyStudentForumActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;

    AppCompatImageView ivCloseFacultyStudentForumActivity;
    LinearLayout llFacultyStudentForumActivityList, llFacultyStudentForumActivityProgressbar, llNoDataFoundFacultyStudentForumActivity;
    RecyclerView rvFacultyStudentForumActivity;
    LinearLayoutManager layoutManager;
    LinearLayout llFacultyProgressbarStudentForumActivity;
    private int currentPageNo = 1;
    FacultyStudentForumAdapter facultyStudentForumAdapter;
    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentItemCount = 0;
    private int totalItemCount = 0;
    private int scrolledOutItemCount = 0;
    ArrayList<FacultyStudentForumPojo> facultyStudentForumPojoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_student_forum);
        initView();
        getStudentForumActivityDetailsApi(true);
        rvFacultyStudentForumActivity.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    getStudentForumActivityDetailsApi(false);

                }
            }
        });
    }

    private void initView() {

        mySharedPreferences = new MySharedPreferences(FacultyStudentForumActivity.this);
        connectionDetector = new ConnectionDetector(FacultyStudentForumActivity.this);
        ivCloseFacultyStudentForumActivity = findViewById(R.id.ivCloseFacultyStudentForumActivity);
        ivCloseFacultyStudentForumActivity.setOnClickListener(this);
        rvFacultyStudentForumActivity = findViewById(R.id.rvFacultyStudentForumActivity);
        layoutManager = new LinearLayoutManager(FacultyStudentForumActivity.this);
        rvFacultyStudentForumActivity.setLayoutManager(layoutManager);
        llFacultyStudentForumActivityList = findViewById(R.id.llFacultyStudentForumActivityList);
        llFacultyStudentForumActivityProgressbar = findViewById(R.id.llFacultyStudentForumActivityProgressbar);
        llNoDataFoundFacultyStudentForumActivity = findViewById(R.id.llNoDataFoundFacultyStudentForumActivity);
        llFacultyProgressbarStudentForumActivity = findViewById(R.id.llFacultyProgressbarStudentForumActivity);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyStudentForumActivity) {
            onBackPressed();
        }

    }

    private void getStudentForumActivityDetailsApi(boolean isProgressbarShowing) {
        if (connectionDetector.isConnectingToInternet()) {

            if (isProgressbarShowing) {
                llFacultyStudentForumActivityProgressbar.setVisibility(View.VISIBLE);
            } else {

                llFacultyProgressbarStudentForumActivity.setVisibility(View.VISIBLE);
            }

            ApiImplementer.getFacultyStudentForumActivityFacultyWiseApiImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getEmpYearId(),
                    mySharedPreferences.getEmpInstituteId(), String.valueOf(CommonUtil.ROW_PER_PAGE), String.valueOf(currentPageNo), new Callback<ArrayList<FacultyStudentForumPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<FacultyStudentForumPojo>> call, Response<ArrayList<FacultyStudentForumPojo>> response) {
                            if (connectionDetector.isConnectingToInternet()) {

                                if (isProgressbarShowing) {
                                    llFacultyStudentForumActivityProgressbar.setVisibility(View.GONE);
                                } else {

                                    llFacultyProgressbarStudentForumActivity.setVisibility(View.GONE);
                                }
                                try {
                                    if (response.isSuccessful() && response.body() != null) {

                                        llFacultyStudentForumActivityList.setVisibility(View.VISIBLE);
                                        llNoDataFoundFacultyStudentForumActivity.setVisibility(View.GONE);


                                        facultyStudentForumPojoArrayList.addAll(response.body()); //response body
                                        if (!response.body().isEmpty() && response.body().size() > 0) {
                                            if (currentPageNo == 1) {
                                                facultyStudentForumAdapter = new FacultyStudentForumAdapter(FacultyStudentForumActivity.this, facultyStudentForumPojoArrayList);
                                                rvFacultyStudentForumActivity.setAdapter(facultyStudentForumAdapter);

                                            } else {
                                                facultyStudentForumAdapter.notifyDataSetChanged();
                                            }
                                        } else {
                                            if (currentPageNo == 1) {
                                                llFacultyStudentForumActivityList.setVisibility(View.GONE);
                                                llFacultyStudentForumActivityProgressbar.setVisibility(View.GONE);
                                                llNoDataFoundFacultyStudentForumActivity.setVisibility(View.VISIBLE);

                                            } else {
                                                hasMoreData = false;
                                            }
                                        }
                                    } else {
                                        Toast.makeText(FacultyStudentForumActivity.this, "Response Code:- " + response.code(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception ex) {
                                    Toast.makeText(FacultyStudentForumActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(FacultyStudentForumActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ArrayList<FacultyStudentForumPojo>> call, Throwable t) {
                            llFacultyStudentForumActivityList.setVisibility(View.GONE);
                            llFacultyStudentForumActivityProgressbar.setVisibility(View.GONE);
                            llNoDataFoundFacultyStudentForumActivity.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultyStudentForumActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }


}
