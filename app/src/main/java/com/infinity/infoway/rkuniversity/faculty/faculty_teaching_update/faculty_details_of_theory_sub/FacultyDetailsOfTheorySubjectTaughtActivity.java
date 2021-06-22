package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_details_of_theory_sub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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


public class FacultyDetailsOfTheorySubjectTaughtActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;

    AppCompatImageView ivCloseFacultyDetailsOfTheorySubjectTaught;
    LinearLayout llFacultyDetailsOfTheorySubjectTaughtList, llFacultyDetailsOfTheorySubjectTaughtProgressbar, llNoDataFoundFacultyDetailsOfTheorySubjectTaught;
    RecyclerView rvFacultyDetailsOfTheorySubjectTaught;
    ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo> facultyDetailsOfTheorySubjectTaughtPojoArrayList = new ArrayList<>();
    private int currentPageNo = 1;
    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentItemCount = 0;
    private int totalItemCount = 0;
    private int scrolledOutItemCount = 0;
    LinearLayoutManager layoutManager;

    FacultyDetailsOfTheorySubjectTaughtAdapter facultyDetailsOfTheorySubjectTaughtAdapter;

    LinearLayout llFacultyProgressbarDetailsOfTheorySubject;
    ProgressBar pbFacultyDetailsOfTheorySubTaught;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details_of_theory_subject_taught);
        initView();
        getFacultyDetailsOfTheorySubjectTaughtListApiCall(true);
        rvFacultyDetailsOfTheorySubjectTaught.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    getFacultyDetailsOfTheorySubjectTaughtListApiCall(false);
                }
            }
        });
    }

    private void initView() {

        mySharedPreferences = new MySharedPreferences(FacultyDetailsOfTheorySubjectTaughtActivity.this);
        connectionDetector = new ConnectionDetector(FacultyDetailsOfTheorySubjectTaughtActivity.this);
        ivCloseFacultyDetailsOfTheorySubjectTaught = findViewById(R.id.ivCloseFacultyDetailsOfTheorySubjectTaught);
        ivCloseFacultyDetailsOfTheorySubjectTaught.setOnClickListener(this);
        rvFacultyDetailsOfTheorySubjectTaught = findViewById(R.id.rvFacultyDetailsOfTheorySubjectTaught);
        layoutManager = new LinearLayoutManager(FacultyDetailsOfTheorySubjectTaughtActivity.this);
        rvFacultyDetailsOfTheorySubjectTaught.setLayoutManager(layoutManager);
        llFacultyDetailsOfTheorySubjectTaughtList = findViewById(R.id.llFacultyDetailsOfTheorySubjectTaughtList);
        llFacultyDetailsOfTheorySubjectTaughtProgressbar = findViewById(R.id.llFacultyDetailsOfTheorySubjectTaughtProgressbar);
        llNoDataFoundFacultyDetailsOfTheorySubjectTaught = findViewById(R.id.llNoDataFoundFacultyDetailsOfTheorySubjectTaught);
        llFacultyProgressbarDetailsOfTheorySubject = findViewById(R.id.llFacultyProgressbarDetailsOfTheorySubject);
        pbFacultyDetailsOfTheorySubTaught = findViewById(R.id.pbFacultyDetailsOfTheorySubTaught);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyDetailsOfTheorySubjectTaught) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getFacultyDetailsOfTheorySubjectTaughtListApiCall(boolean isProgressbarShowing) {
        if (connectionDetector.isConnectingToInternet()) {

            if (isProgressbarShowing) {
                llFacultyDetailsOfTheorySubjectTaughtProgressbar.setVisibility(View.VISIBLE);
            } else {

                llFacultyProgressbarDetailsOfTheorySubject.setVisibility(View.VISIBLE);
            }

            ApiImplementer.getFacultyEmployeeAllocatedSubjectDetailsByIdApiImplementer(mySharedPreferences.getEmpId(), CommonUtil.ROW_PER_PAGE,
                    currentPageNo, new Callback<ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo>> call, Response<ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo>> response) {
                            if (connectionDetector.isConnectingToInternet()) {

                                if (isProgressbarShowing) {
                                    llFacultyDetailsOfTheorySubjectTaughtProgressbar.setVisibility(View.GONE);
                                } else {
                                    llFacultyProgressbarDetailsOfTheorySubject.setVisibility(View.GONE);
                                }
                                try {
                                    if (response.isSuccessful() && response.body() != null) {

                                        llFacultyDetailsOfTheorySubjectTaughtList.setVisibility(View.VISIBLE);
                                        llNoDataFoundFacultyDetailsOfTheorySubjectTaught.setVisibility(View.GONE);


                                        facultyDetailsOfTheorySubjectTaughtPojoArrayList.addAll(response.body()); //response body
                                        if (!response.body().isEmpty() && response.body().size() > 0) {
                                            if (currentPageNo == 1) {
                                                facultyDetailsOfTheorySubjectTaughtAdapter = new FacultyDetailsOfTheorySubjectTaughtAdapter(FacultyDetailsOfTheorySubjectTaughtActivity.this, facultyDetailsOfTheorySubjectTaughtPojoArrayList);
                                                rvFacultyDetailsOfTheorySubjectTaught.setAdapter(facultyDetailsOfTheorySubjectTaughtAdapter);

                                            } else {
                                                facultyDetailsOfTheorySubjectTaughtAdapter.notifyDataSetChanged();
                                            }
                                        } else {
                                            if (currentPageNo == 1) {
                                                llFacultyDetailsOfTheorySubjectTaughtList.setVisibility(View.GONE);
                                                llFacultyDetailsOfTheorySubjectTaughtProgressbar.setVisibility(View.GONE);
                                                llNoDataFoundFacultyDetailsOfTheorySubjectTaught.setVisibility(View.VISIBLE);

                                            } else {
                                                hasMoreData = false;
                                            }
                                        }
                                    } else {
                                        Toast.makeText(FacultyDetailsOfTheorySubjectTaughtActivity.this, "Response Code:- " + response.code(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception ex) {
                                    Toast.makeText(FacultyDetailsOfTheorySubjectTaughtActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(FacultyDetailsOfTheorySubjectTaughtActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo>> call, Throwable t) {
                            llFacultyDetailsOfTheorySubjectTaughtList.setVisibility(View.GONE);
                            llFacultyDetailsOfTheorySubjectTaughtProgressbar.setVisibility(View.GONE);
                            llNoDataFoundFacultyDetailsOfTheorySubjectTaught.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultyDetailsOfTheorySubjectTaughtActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}
