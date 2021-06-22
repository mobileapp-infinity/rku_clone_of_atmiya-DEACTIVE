package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.AllEmployeeAttendanceReportpojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AllEmployeeAttendanceReportActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<AllEmployeeAttendanceReportpojo.DataBean> allEmployeeAttendanceReportPojoList;

    Spinner spMonthAllEmpAttendanceReport, spYearAllEmpAttendanceReport;

    String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    ArrayList<String> monthOpetions = new ArrayList<>();
    HashMap<String, Integer> hashMapMonthOpetionAndID = new HashMap<>();

//    String years[] = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    ArrayList<String> yearOpetions;
//    HashMap<String, Integer> hashMapYearOpetionAndID = new HashMap<>();

    CustomBoldTextView tvLoadAllEmpAttendanceReport;

    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH);

    Spinner spBranchEmpAttendanceReport, spDepartmentEmpAttendanceReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    String Branch_id = "0";
    String Departmet_id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_employee_attendance_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

//        spMonthAllEmpAttendanceReport.setSelection(currentMonth + 1);
//        spYearAllEmpAttendanceReport.setSelection(yearOpetions.indexOf(String.valueOf(currentYear)));

       getReportFilterYearApiCall();

        spBranchEmpAttendanceReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spDepartmentEmpAttendanceReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Departmet_id = hashMapDepartmentAndID.get(departmentOpetions.get(position).trim()).toString();
                } else {
                    Departmet_id = "0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private boolean isValid() {

        if (monthOpetions.get(spMonthAllEmpAttendanceReport.getSelectedItemPosition()).equalsIgnoreCase("Select Month")) {
            DialogUtils.Show_Toast(AllEmployeeAttendanceReportActivity.this, "Select Month");
            return false;
        } else if (yearOpetions.get(spYearAllEmpAttendanceReport.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(AllEmployeeAttendanceReportActivity.this, "Select Year");
            return false;
        }
        return true;
    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());
        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("All Employee Attendance Report");
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        queue = Volley.newRequestQueue(this);

        spMonthAllEmpAttendanceReport = findViewById(R.id.spMonthAllEmpAttendanceReport);
        monthOpetions.add("Select Month");
        spYearAllEmpAttendanceReport = findViewById(R.id.spYearAllEmpAttendanceReport);

        for (int i = 0; i < months.length; i++) {
            monthOpetions.add(months[i]);
            hashMapMonthOpetionAndID.put(months[i], i + 1);
        }

        SpinnerSimpleAdapter monthOpetionAdapter = new SpinnerSimpleAdapter(AllEmployeeAttendanceReportActivity.this, monthOpetions);
        spMonthAllEmpAttendanceReport.setAdapter(monthOpetionAdapter);

//        for (int i = 0; i < years.length; i++) {
//            yearOpetions.add(years[i]);
//            hashMapYearOpetionAndID.put(years[i], i + 1);
//        }
//
//        SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(AllEmployeeAttendanceReportActivity.this, yearOpetions);
//        spYearAllEmpAttendanceReport.setAdapter(yearsOpetionAdapter);

        tvLoadAllEmpAttendanceReport = findViewById(R.id.tvLoadAllEmpAttendanceReport);
        tvLoadAllEmpAttendanceReport.setOnClickListener(this);

        spBranchEmpAttendanceReport = findViewById(R.id.spBranchEmpAttendanceReport);
        spDepartmentEmpAttendanceReport = findViewById(R.id.spDepartmentEmpAttendanceReport);

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
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.tvLoadAllEmpAttendanceReport) {
            if (isValid()) {
//                if (!TextUtils.isEmpty(Branch_id)) {
//                    if (!TextUtils.isEmpty(Departmet_id)) {
//                        String Emp_id = "0"; // 0 For only Report Module (As per talk with jonsi madam)
//                        String User_id = "1"; //1  ||
                String Emp_id = mySharedPreferecesRKUOLD.getEmpID(); // 0 For only Report Module (As per talk with jonsi madam)
                String User_id = mySharedPreferecesRKUOLD.getUserID();
                String Desgination_id = "0";// 0 ||
                String Month = String.valueOf(hashMapMonthOpetionAndID.get(monthOpetions.get(spMonthAllEmpAttendanceReport.getSelectedItemPosition())));
                String Year = yearOpetions.get(spYearAllEmpAttendanceReport.getSelectedItemPosition());

                getAllEmployeeAttendanceReportApi(Emp_id, User_id, Branch_id, Departmet_id, Desgination_id, Month, Year);
//                    } else {
//                        Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//                }
            }
        }
    }

    private void getReportFilterYearApiCall() {
        DialogUtils.showProgressDialog(AllEmployeeAttendanceReportActivity.this, "");

        StringRequest getBranchRequest = new StringRequest(Request.Method.POST, URLS.GET_FILTER_YEAR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray != null && jsonArray.length() > 0) {
                            yearOpetions = new ArrayList<>();
                            yearOpetions.add("Select Year");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                yearOpetions.add(jsonArray.getJSONObject(i).getString("year"));
                            }

                            SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(AllEmployeeAttendanceReportActivity.this, yearOpetions);
                            spYearAllEmpAttendanceReport.setAdapter(yearsOpetionAdapter);

                            getBranchApiCall(false,false);

                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(AllEmployeeAttendanceReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AllEmployeeAttendanceReportActivity.this, "Year empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(AllEmployeeAttendanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
            }
        });

        getBranchRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getBranchRequest);
    }

    private void getBranchApiCall(boolean isPdShow, final boolean isPdHide) {
        if (isPdShow){
            DialogUtils.showProgressDialog(AllEmployeeAttendanceReportActivity.this, "");
        }

        StringRequest getBranchRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Branch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (isPdHide){
                    DialogUtils.hideProgressDialog();
                }
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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(AllEmployeeAttendanceReportActivity.this, branchOpetions);
                            spBranchEmpAttendanceReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(AllEmployeeAttendanceReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AllEmployeeAttendanceReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(AllEmployeeAttendanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
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
            DialogUtils.showProgressDialog(AllEmployeeAttendanceReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(AllEmployeeAttendanceReportActivity.this, departmentOpetions);
                            spDepartmentEmpAttendanceReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(AllEmployeeAttendanceReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(AllEmployeeAttendanceReportActivity.this, "Please Try Again Later");
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

    private void getAllEmployeeAttendanceReportApi(final String Emp_id, final String User_id, final String Branch_id,
                                                   final String Department_id, final String Desgination_id,
                                                   final String Month, final String Year) {

        DialogUtils.showProgressDialog(AllEmployeeAttendanceReportActivity.this, "");

        StringRequest academicContributionReportRequest = new StringRequest(Request.Method.POST, URLS.Get_All_Employee_Attendance_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        AllEmployeeAttendanceReportpojo allEmployeeAttendanceReportpojo = gson.fromJson("{\"Data\":" + response + "}", AllEmployeeAttendanceReportpojo.class);
                        if (allEmployeeAttendanceReportpojo.getData() != null && allEmployeeAttendanceReportpojo.getData().size() > 0) {

                            allEmployeeAttendanceReportPojoList = allEmployeeAttendanceReportpojo.getData();

                            Intent intent = new Intent(AllEmployeeAttendanceReportActivity.this, AllEmployeeAttendaceReportDetailActivity.class);
                            intent.putExtra(IntentConstants.ALL_EMP_ATTENDANCE_REPORT, (Serializable) allEmployeeAttendanceReportPojoList);
                            startActivity(intent);

                        } else {
                            Toast.makeText(AllEmployeeAttendanceReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AllEmployeeAttendanceReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(AllEmployeeAttendanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Emp_id", Emp_id);
                params2.put("User_id", User_id);
                params2.put("Branch_id", Branch_id);
                params2.put("Department_id", Department_id);
                params2.put("Desgination_id", Desgination_id);
                params2.put("Month", Month);
                params2.put("Year", Year);
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

}
