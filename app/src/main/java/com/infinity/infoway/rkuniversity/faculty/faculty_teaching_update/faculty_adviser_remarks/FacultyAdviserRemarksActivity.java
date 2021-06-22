package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_adviser_remarks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyAdviserRemarksActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharedPreferences mySharedPreferences;
    private ConnectionDetector connectionDetector;
    private LinearLayout llFacultyAdviserRemarks, llFacultyAdviserRemarksProgressbar, llNoDataFoundFacultyAdviserRemarks;
    private ExpandableListView elvFacultyAdviserRemarks;
    private AppCompatImageView ivCloseFacultyAdviserRemarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_adviser_remarks);
        initView();
        getFacultyAdviserRemarksApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyAdviserRemarksActivity.this);
        connectionDetector = new ConnectionDetector(FacultyAdviserRemarksActivity.this);
        ivCloseFacultyAdviserRemarks = findViewById(R.id.ivCloseFacultyAdviserRemarks);
        ivCloseFacultyAdviserRemarks.setOnClickListener(this);
        llFacultyAdviserRemarks = findViewById(R.id.llFacultyAdviserRemarks);
        llFacultyAdviserRemarksProgressbar = findViewById(R.id.llFacultyAdviserRemarksProgressbar);
        llNoDataFoundFacultyAdviserRemarks = findViewById(R.id.llNoDataFoundFacultyAdviserRemarks);
        elvFacultyAdviserRemarks = findViewById(R.id.elvFacultyAdviserRemarks);
    }


    private void getFacultyAdviserRemarksApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyAdviserRemarks.setVisibility(View.GONE);
            llFacultyAdviserRemarksProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFacultyAdviserRemarks.setVisibility(View.GONE);
            ApiImplementer.facultyAdviserRemarksApiImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getEmpYearId(),
                    mySharedPreferences.getEmpInstituteId(), "0", "0", new Callback<ArrayList<FacultyAdviserRemarksListPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<FacultyAdviserRemarksListPojo>> call, Response<ArrayList<FacultyAdviserRemarksListPojo>> response) {
                            try {
                                llFacultyAdviserRemarksProgressbar.setVisibility(View.GONE);
                                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                                    llFacultyAdviserRemarks.setVisibility(View.VISIBLE);
                                    ArrayList<FacultyAdviserRemarksListPojo> facultyAdviserRemarksListPojoArrayList = response.body();
                                    HashMap<String, ArrayList<FacultyAdviserRemarksListPojo.FaDetailArray>> childHashMap = new HashMap<>();
                                    for (int i = 0; i < facultyAdviserRemarksListPojoArrayList.size(); i++) {
                                        String grpName = facultyAdviserRemarksListPojoArrayList.get(i).getFaCourseName();
                                        if (!CommonUtil.checkIsEmptyOrNullCommon(grpName)) {
                                            childHashMap.put(grpName, (ArrayList<FacultyAdviserRemarksListPojo.FaDetailArray>) facultyAdviserRemarksListPojoArrayList.get(i).getFaDetailArray());
                                        }
                                    }
                                    FacultyAdviserRemarksExpandableListAdapter facultyAdviserRemarksExpandableListAdapter = new FacultyAdviserRemarksExpandableListAdapter(
                                            FacultyAdviserRemarksActivity.this, facultyAdviserRemarksListPojoArrayList, childHashMap);
                                    elvFacultyAdviserRemarks.setAdapter(facultyAdviserRemarksExpandableListAdapter);
                                } else {
                                    llFacultyAdviserRemarks.setVisibility(View.GONE);
                                    llNoDataFoundFacultyAdviserRemarks.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<FacultyAdviserRemarksListPojo>> call, Throwable t) {
                            llFacultyAdviserRemarks.setVisibility(View.GONE);
                            llFacultyAdviserRemarksProgressbar.setVisibility(View.GONE);
                            llNoDataFoundFacultyAdviserRemarks.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultyAdviserRemarksActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyAdviserRemarks) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}