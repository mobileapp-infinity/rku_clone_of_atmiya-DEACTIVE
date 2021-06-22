package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.DailAttendanceReportPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.models.DailyAttendanceCountModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.DailyAttendanceStatusBranchAndCountModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class DailyAttendanceReportActivity extends AppCompatActivity implements View.OnClickListener{

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;
    ImageView ivBack;

    List<DailAttendanceReportPojo.DataBean> dailAttendanceReportPojoArrayList;

    ArrayList<String> attendanceStatusArrayList;
    ArrayList<String> attendancebranchArrayList;


    LinearLayout ll_attendance_status, ll_attendance_header_and_value;

    ArrayList<DailyAttendanceCountModel> dailyAttendanceCountModelArrayList;
    ArrayList<DailyAttendanceStatusBranchAndCountModel> attendanceStatusBranchAndCountModelArrayList;


    Spinner spBranchDailyAttendanceReport,spDepartmentDailyAttendanceReport;
    CustomBoldTextView tvLoadDailyAttendanceReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    String Branch_id = "0";
    String Departmet_id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_attendance_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getBranchApiCall();

        spBranchDailyAttendanceReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Branch_id = hashMapBranchOpetionAndID.get(branchOpetions.get(position).trim()).toString();
                    getDepartmentApiCall(true, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(position))));
                } else {
                    Branch_id = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDepartmentDailyAttendanceReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Departmet_id = hashMapDepartmentAndID.get(departmentOpetions.get(position).trim()).toString();
                }
                else {
                    Departmet_id = "0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initView() {
        marshMallowPermission = new MarshMallowPermission(DailyAttendanceReportActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Daily Attendance Report");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_attendance_status = findViewById(R.id.ll_attendance_status);
        ll_attendance_header_and_value = findViewById(R.id.ll_attendance_header_and_value);

        spBranchDailyAttendanceReport = findViewById(R.id.spBranchDailyAttendanceReport);
        spDepartmentDailyAttendanceReport = findViewById(R.id.spDepartmentDailyAttendanceReport);
        tvLoadDailyAttendanceReport = findViewById(R.id.tvLoadDailyAttendanceReport);
        tvLoadDailyAttendanceReport.setOnClickListener(this);

        PackageInfo pInfo = null;
        assert pInfo != null;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version.setText(pInfo.versionName);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back){
            onBackPressed();
        }else if (v.getId() == R.id.tvLoadDailyAttendanceReport){
//            if (!TextUtils.isEmpty(Branch_id)) {
//                if (!TextUtils.isEmpty(Departmet_id)) {
                    ll_attendance_status.removeAllViewsInLayout();
                    ll_attendance_header_and_value.removeAllViewsInLayout();

                    getDailyAttendanceReport(Branch_id, Departmet_id);
//                } else {
//                    Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    private void getBranchApiCall() {
        DialogUtils.showProgressDialog(DailyAttendanceReportActivity.this, "");

        StringRequest getBranchRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Branch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {
                            branchOpetions = new ArrayList<>();
                            branchOpetions.add("Select Branch");
                            hashMapBranchOpetionAndID = new HashMap<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapBranchOpetionAndID.put(jsonArray.getJSONObject(i).getString("brm_name"),
                                        jsonArray.getJSONObject(i).getInt("id"));
                                branchOpetions.add(jsonArray.getJSONObject(i).getString("brm_name"));
                            }
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(DailyAttendanceReportActivity.this, branchOpetions);
                            spBranchDailyAttendanceReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        }else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(DailyAttendanceReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(DailyAttendanceReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(DailyAttendanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("User_id", mySharedPreferecesRKUOLD.getUserID());
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getBranchRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getBranchRequest);
    }

    private void getDepartmentApiCall(boolean isPdShow, final boolean isPdHide, final String branch_id) {

        if (isPdShow) {
            DialogUtils.showProgressDialog(DailyAttendanceReportActivity.this, "");
        }
        StringRequest leaveBalanceRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Department, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (isPdHide) {
                    DialogUtils.hideProgressDialog();
                }
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {
                            departmentOpetions = new ArrayList<>();
                            departmentOpetions.add("Select Department");
                            hashMapDepartmentAndID = new HashMap<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapDepartmentAndID.put(jsonArray.getJSONObject(i).getString("dep_name"),
                                        jsonArray.getJSONObject(i).getInt("id"));
                                departmentOpetions.add(jsonArray.getJSONObject(i).getString("dep_name"));
                            }
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(DailyAttendanceReportActivity.this, departmentOpetions);
                            spDepartmentDailyAttendanceReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(DailyAttendanceReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(DailyAttendanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("User_id", mySharedPreferecesRKUOLD.getUserID());
                params2.put("Branch_id", branch_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        leaveBalanceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(leaveBalanceRequest);
    }

    private void getDailyAttendanceReport(final String Branch_id, final String Departmet_id) {
        DialogUtils.showProgressDialog(DailyAttendanceReportActivity.this, "");

        final StringRequest academicContributionReportRequest = new StringRequest(Request.Method.POST, URLS.Get_Daily_Attendance_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        DailAttendanceReportPojo dailAttendanceReportPojo = gson.fromJson("{\"Data\":" + response + "}", DailAttendanceReportPojo.class);
                        if (dailAttendanceReportPojo.getData() != null && dailAttendanceReportPojo.getData().size() > 0) {
                            dailAttendanceReportPojoArrayList = dailAttendanceReportPojo.getData();

                            attendanceStatusArrayList = new ArrayList<>();
                            attendancebranchArrayList = new ArrayList<>();
                            dailyAttendanceCountModelArrayList = new ArrayList<>();
                            attendanceStatusBranchAndCountModelArrayList = new ArrayList<>();

                            for (int i = 0; i < dailAttendanceReportPojoArrayList.size(); i++) {
                                if (!TextUtils.isEmpty(dailAttendanceReportPojoArrayList.get(i).getBrm_code())) {
                                    attendancebranchArrayList.add(dailAttendanceReportPojoArrayList.get(i).getBrm_code());
                                }
//                                else {
//                                    attendancebranchArrayList.add("-");
//                                }

                                if (!TextUtils.isEmpty(dailAttendanceReportPojoArrayList.get(i).getDaily_Attendance())) {
                                    attendanceStatusArrayList.add(dailAttendanceReportPojoArrayList.get(i).getDaily_Attendance());
                                }
//                                else {
//                                    attendanceStatusArrayList.add("-");
//                                }

                            }

                            attendancebranchArrayList = new ArrayList<String>(new LinkedHashSet<String>(attendancebranchArrayList));
                            attendanceStatusArrayList = new ArrayList<String>(new LinkedHashSet<String>(attendanceStatusArrayList));
                            attendanceStatusArrayList.add(0, "Daily Attendance");

                            for (int i = 0; i < attendanceStatusArrayList.size(); i++) {

                                LinearLayout.LayoutParams acTypeLayoutParam = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                                CustomBoldTextView customBoldTextView = new CustomBoldTextView(DailyAttendanceReportActivity.this);
                                customBoldTextView.setLayoutParams(acTypeLayoutParam);
                                customBoldTextView.setPadding(4, 4, 4, 4);
                                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                customBoldTextView.setTextSize(15);
                                customBoldTextView.setGravity(Gravity.LEFT);
                                customBoldTextView.setBackground(ContextCompat.getDrawable(DailyAttendanceReportActivity.this,
                                        R.drawable.bg_header_ac_report));
                                customBoldTextView.setText(attendanceStatusArrayList.get(i));
                                ll_attendance_status.addView(customBoldTextView);
                            }

                            LinearLayout llAcHeaderAndValue = new LinearLayout(DailyAttendanceReportActivity.this);
                            llAcHeaderAndValue.setOrientation(LinearLayout.HORIZONTAL);
                            for (int i = 0; i < attendancebranchArrayList.size(); i++) {
                                LinearLayout.LayoutParams acHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                                        130, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                                CustomBoldTextView customBoldTextView = new CustomBoldTextView(DailyAttendanceReportActivity.this);
                                customBoldTextView.setLayoutParams(acHeaderAndValueLayoutParam);
                                customBoldTextView.setPadding(4, 4, 4, 4);
                                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                customBoldTextView.setTextSize(15);
                                customBoldTextView.setLines(1);
                                customBoldTextView.setGravity(Gravity.CENTER);
                                customBoldTextView.setBackground(ContextCompat.getDrawable(DailyAttendanceReportActivity.this,
                                        R.drawable.bg_header_ac_report));
                                customBoldTextView.setText(attendancebranchArrayList.get(i));

                                llAcHeaderAndValue.addView(customBoldTextView);

                            }

                            ll_attendance_header_and_value.addView(llAcHeaderAndValue);

                            for (int i = 1; i < attendanceStatusArrayList.size(); i++) {

                                DailyAttendanceStatusBranchAndCountModel dailyAttendanceStatusBranchAndCountModel = new DailyAttendanceStatusBranchAndCountModel();
                                HashMap<String, String> hashMapBranchAndValue = new HashMap<>();

                                dailyAttendanceStatusBranchAndCountModel.setAttendanceStatus(attendanceStatusArrayList.get(i));

                                for (int k = 0; k < dailAttendanceReportPojoArrayList.size(); k++) {
                                    if (attendanceStatusArrayList.get(i).trim().equalsIgnoreCase(dailAttendanceReportPojoArrayList.get(k).getDaily_Attendance().trim())) {
                                        hashMapBranchAndValue.put(dailAttendanceReportPojoArrayList.get(k).getBrm_code(), String.valueOf(dailAttendanceReportPojoArrayList.get(k).getCount()));
                                    }
                                }

                                dailyAttendanceStatusBranchAndCountModel.setHashMapBranchAndCount(hashMapBranchAndValue);
                                attendanceStatusBranchAndCountModelArrayList.add(dailyAttendanceStatusBranchAndCountModel);
                            }


                            for (int i = 0; i < attendanceStatusBranchAndCountModelArrayList.size(); i++) {
                                DailyAttendanceCountModel dailyAttendanceCountModel = new DailyAttendanceCountModel();
                                ArrayList<String> branchValue = new ArrayList<>();

                                for (int k = 0; k < attendancebranchArrayList.size(); k++) {

                                    String branch = attendanceStatusBranchAndCountModelArrayList.get(i).getHashMapBranchAndCount().get(attendancebranchArrayList.get(k));

                                    if (!TextUtils.isEmpty(branch)) {
                                        branchValue.add(branch);
                                    } else {
                                        branchValue.add("-");
                                    }
                                    dailyAttendanceCountModel.setAttendanceCount(branchValue);
                                }
                                dailyAttendanceCountModelArrayList.add(dailyAttendanceCountModel);
                            }

                            drawHeaderAndFillValue();


                        } else {
                            Toast.makeText(DailyAttendanceReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(DailyAttendanceReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(DailyAttendanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Branch_id", Branch_id);
                params2.put("Departmet_id", Departmet_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

        };
        academicContributionReportRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(academicContributionReportRequest);
    }

    private void drawHeaderAndFillValue() {

//        for (int i = 1; i < attendanceStatusArrayList.size(); i++) {
//
//            DailyAttendanceCountModel dailyAttendanceCountModel = new DailyAttendanceCountModel();
//            ArrayList<String> branchCodeCountArrayList = new ArrayList<>();
//            String attendanceStatus = attendanceStatusArrayList.get(i);
//
//            for (int j = 0; j < dailAttendanceReportPojoArrayList.size(); j++) {
//                if (dailAttendanceReportPojoArrayList.get(j).getDaily_Attendance().equalsIgnoreCase(attendanceStatus)) {
//                    if (!String.valueOf(dailAttendanceReportPojoArrayList.get(j).getCount()).equalsIgnoreCase("null") &&
//                            !String.valueOf(dailAttendanceReportPojoArrayList.get(j).getCount()).equalsIgnoreCase("")) {
//                        branchCodeCountArrayList.add(String.valueOf(dailAttendanceReportPojoArrayList.get(j).getCount()));
//                    } else {
//                        branchCodeCountArrayList.add("-");
//                    }
//                }
//            }
//            if (attendancebranchArrayList.size() != branchCodeCountArrayList.size()) {
//                for (int k = branchCodeCountArrayList.size(); k <= attendancebranchArrayList.size(); k++) {
//                    branchCodeCountArrayList.add("-");
//                }
//            }
//            dailyAttendanceCountModel.setAttendanceCount(branchCodeCountArrayList);
//            dailyAttendanceCountModelArrayList.add(dailyAttendanceCountModel);
//        }


        for (int l = 0; l < dailyAttendanceCountModelArrayList.size(); l++) {

            LinearLayout llAcHeaderAndValue = new LinearLayout(DailyAttendanceReportActivity.this);
            llAcHeaderAndValue.setOrientation(LinearLayout.HORIZONTAL);

            for (int t = 0; t < dailyAttendanceCountModelArrayList.get(l).getAttendanceCount().size(); t++) {

                LinearLayout.LayoutParams acHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                        130, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                CustomBoldTextView customBoldTextView = new CustomBoldTextView(DailyAttendanceReportActivity.this);
                customBoldTextView.setLayoutParams(acHeaderAndValueLayoutParam);
                customBoldTextView.setPadding(4, 4, 4, 4);
                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                customBoldTextView.setTextSize(15);
                customBoldTextView.setLines(1);
                customBoldTextView.setGravity(Gravity.CENTER);
                customBoldTextView.setBackground(ContextCompat.getDrawable(DailyAttendanceReportActivity.this,
                        R.drawable.bg_header_ac_report));
                customBoldTextView.setText(dailyAttendanceCountModelArrayList.get(l).getAttendanceCount().get(t));
                llAcHeaderAndValue.addView(customBoldTextView);

            }

            ll_attendance_header_and_value.addView(llAcHeaderAndValue);
        }

    }

}
