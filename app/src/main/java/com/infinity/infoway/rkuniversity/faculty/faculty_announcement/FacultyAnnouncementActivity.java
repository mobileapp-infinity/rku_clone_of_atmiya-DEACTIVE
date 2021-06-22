package com.infinity.infoway.rkuniversity.faculty.faculty_announcement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.activity.FacultyDashboardActivity;
import com.infinity.infoway.rkuniversity.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyAnnouncementActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        View.OnClickListener, FacultyViewAllAnnouncementAdapter.IRemoveStudentNewsOrNotification {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    LinearLayout llAnnouncementProgressbar, llNoDataFoundFacultyAnnouncement, llFacultyAnnouncementList;
    RecyclerView rvAnnouncement;
    AppCompatImageView ivCloseAnnouncement;
    FacultyViewAllAnnouncementAdapter viewAllNewsOrNotificationAdapter;
    SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_announcement);
        initView();
        getAllNewsOrNotificationApiCall(false);
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyAnnouncementActivity.this);
        connectionDetector = new ConnectionDetector(FacultyAnnouncementActivity.this);
        ivCloseAnnouncement = findViewById(R.id.ivCloseAnnouncement);
        ivCloseAnnouncement.setOnClickListener(this);
        llAnnouncementProgressbar = findViewById(R.id.llAnnouncementProgressbar);
        llNoDataFoundFacultyAnnouncement = findViewById(R.id.llNoDataFoundFacultyAnnouncement);
        llFacultyAnnouncementList = findViewById(R.id.llFacultyAnnouncementList);
        rvAnnouncement = findViewById(R.id.rvAnnouncement);
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setEnabled(true);
        swipeContainer.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseAnnouncement) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FacultyAnnouncementActivity.this, FacultyDashboardActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void getAllNewsOrNotificationApiCall(boolean isPullToRefresh) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPullToRefresh) {
//                swipeContainer.setEnabled(true);
                swipeContainer.setRefreshing(true);
            }
            llAnnouncementProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFacultyAnnouncement.setVisibility(View.GONE);
            llFacultyAnnouncementList.setVisibility(View.GONE);
            ApiImplementer.getFacultyOrStudentNewsOrNotificationImplementer(mySharedPreferences.getLoginUserType() + "",
                    mySharedPreferences.getEmpUserStatus(), mySharedPreferences.getEmpId(), "0", "0",
                    "0", "0", mySharedPreferences.getEmpInstituteId(),
                    mySharedPreferences.getEmpYearId(), "0", new Callback<FacultyOrStudentNewsOrNotificationsPojo>() {
                        @Override
                        public void onResponse(Call<FacultyOrStudentNewsOrNotificationsPojo> call, Response<FacultyOrStudentNewsOrNotificationsPojo> response) {
                            llAnnouncementProgressbar.setVisibility(View.GONE);
                            if (isPullToRefresh) {
                                swipeContainer.setRefreshing(false);
//                                swipeContainer.setEnabled(false);
                            }
                            try {
                                if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                                    llFacultyAnnouncementList.setVisibility(View.VISIBLE);
                                    viewAllNewsOrNotificationAdapter = new FacultyViewAllAnnouncementAdapter(FacultyAnnouncementActivity.this, (ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data>) response.body().getTable());
                                    rvAnnouncement.setAdapter(viewAllNewsOrNotificationAdapter);
                                } else {
                                    llFacultyAnnouncementList.setVisibility(View.GONE);
                                    llNoDataFoundFacultyAnnouncement.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<FacultyOrStudentNewsOrNotificationsPojo> call, Throwable t) {
                            if (isPullToRefresh) {
                                swipeContainer.setRefreshing(false);
//                                swipeContainer.setEnabled(false);
                            }
                            llAnnouncementProgressbar.setVisibility(View.GONE);
                            llFacultyAnnouncementList.setVisibility(View.GONE);
                            llNoDataFoundFacultyAnnouncement.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultyAnnouncementActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onNotificationRemove(int removeIndex) {
        viewAllNewsOrNotificationAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRefresh() {
        getAllNewsOrNotificationApiCall(true);
    }
}