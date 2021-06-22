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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.MonthWiseLateReportPojo;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MonthWiseLateReportActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<MonthWiseLateReportPojo.DataBean> getEmployeeMonthWiseReportPojoList;

    LinearLayout ll_mail_emp_month_wise_late_report,
            ll_employee_month_wise_late_report;


    //    String years[] = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    ArrayList<String> yearOpetions;
//    HashMap<String, Integer> hashMapYearOpetionAndID = new HashMap<>();

    CustomBoldTextView tvLoadEmpMonthWiseLateReport;
    Spinner spYearEmpMonthWiseLateReport,
            spBranchMonthWiseLateReport, spDepartmentMonthWiseLateReport;

    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH);

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_wise_late_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getReportFilterYearApiCall();
//        spYearEmpMonthWiseLateReport.setSelection(yearOpetions.indexOf(String.valueOf(currentYear)));

        spBranchMonthWiseLateReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    getDepartmentApiCall(true, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(position))));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Employee Month Wise Late Report");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_employee_month_wise_late_report = findViewById(R.id.ll_employee_month_wise_late_report);
        ll_mail_emp_month_wise_late_report = findViewById(R.id.ll_mail_emp_month_wise_late_report);

        spBranchMonthWiseLateReport = findViewById(R.id.spBranchMonthWiseLateReport);
        spDepartmentMonthWiseLateReport = findViewById(R.id.spDepartmentMonthWiseLateReport);
        spYearEmpMonthWiseLateReport = findViewById(R.id.spYearEmpMonthWiseLateReport);
//        yearOpetions.add("Select Year");
        tvLoadEmpMonthWiseLateReport = findViewById(R.id.tvLoadEmpMonthWiseLateReport);
        tvLoadEmpMonthWiseLateReport.setOnClickListener(this);

//        for (int i = 0; i < years.length; i++) {
//            yearOpetions.add(years[i]);
//            hashMapYearOpetionAndID.put(years[i], i + 1);
//        }
//
//        SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(MonthWiseLateReportActivity.this, yearOpetions);
//        spYearEmpMonthWiseLateReport.setAdapter(yearsOpetionAdapter);
//        spYearEmpMonthWiseLateReport.setSelection(yearOpetions.indexOf(1));

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

    private boolean isValid() {
        if (branchOpetions.get(spBranchMonthWiseLateReport.getSelectedItemPosition()).equalsIgnoreCase("Select Branch")) {
            DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, "Select Branch");
            return false;
        } else if (departmentOpetions.get(spDepartmentMonthWiseLateReport.getSelectedItemPosition()).equalsIgnoreCase("Select Department")) {
            DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, "Select Department");
            return false;
        } else if (yearOpetions.get(spYearEmpMonthWiseLateReport.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, "Select Year");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.tvLoadEmpMonthWiseLateReport) {
            if (isValid()) {
                if (getEmployeeMonthWiseReportPojoList != null && getEmployeeMonthWiseReportPojoList.size() > 0) {
                    ll_employee_month_wise_late_report.removeAllViewsInLayout();
                    getEmployeeMonthWiseReportPojoList.clear();
                }

                String Year = yearOpetions.get(spYearEmpMonthWiseLateReport.getSelectedItemPosition());
                String Branch_id = hashMapBranchOpetionAndID.get(branchOpetions.get(spBranchMonthWiseLateReport.getSelectedItemPosition()).trim()).toString();
                String department_id = hashMapDepartmentAndID.get(departmentOpetions.get(spDepartmentMonthWiseLateReport.getSelectedItemPosition()).trim()).toString();
                String User_id = mySharedPreferecesRKUOLD.getUserID();

                getEmployeeMonthWiseReportApi(Year, Branch_id, department_id, User_id);
            }
        }
    }

    private void getReportFilterYearApiCall() {
        DialogUtils.showProgressDialog(MonthWiseLateReportActivity.this, "");

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

                            SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(MonthWiseLateReportActivity.this, yearOpetions);
                            spYearEmpMonthWiseLateReport.setAdapter(yearsOpetionAdapter);

                            getBranchApiCall(false, false);

                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(MonthWiseLateReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MonthWiseLateReportActivity.this, "Year empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
            }
        });

        getBranchRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getBranchRequest);
    }


    private void getBranchApiCall(boolean isPdShow, final boolean isPdHide) {
        if (isPdShow) {
            DialogUtils.showProgressDialog(MonthWiseLateReportActivity.this, "");
        }

        StringRequest getBranchRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Branch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (isPdHide) {
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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(MonthWiseLateReportActivity.this, branchOpetions);
                            spBranchMonthWiseLateReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(MonthWiseLateReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MonthWiseLateReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, "Please Try Again Later");
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
            DialogUtils.showProgressDialog(MonthWiseLateReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(MonthWiseLateReportActivity.this, departmentOpetions);
                            spDepartmentMonthWiseLateReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, "Please Try Again Later");
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

    private void getEmployeeMonthWiseReportApi(final String Year, final String Branch_id,
                                               final String department_id, final String User_id) {

        DialogUtils.showProgressDialog(MonthWiseLateReportActivity.this, "");
        StringRequest getEmployyeIdWiseName = new StringRequest(Request.Method.POST, URLS.Get_Employee_Monthwise_Late_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        MonthWiseLateReportPojo getEmployeeMonthWiseLateReport = gson.fromJson("{\"Data\":" + response + "}", MonthWiseLateReportPojo.class);
                        if (getEmployeeMonthWiseLateReport != null && getEmployeeMonthWiseLateReport.getData().size() > 0) {
                            getEmployeeMonthWiseReportPojoList = getEmployeeMonthWiseLateReport.getData();
                            addRowDyanimically();
                        } else {
                            ll_mail_emp_month_wise_late_report.setVisibility(View.GONE);
                            DialogUtils.hideProgressDialog();
                            DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, "No data found!");
                        }
                    } catch (Exception e) {
                        DialogUtils.hideProgressDialog();
                        Toast.makeText(MonthWiseLateReportActivity.this, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ll_mail_emp_month_wise_late_report.setVisibility(View.GONE);
                    DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ll_mail_emp_month_wise_late_report.setVisibility(View.GONE);
                DialogUtils.Show_Toast(MonthWiseLateReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Year", Year);
                params2.put("Branch_id", Branch_id);
                params2.put("department_id", department_id);
                params2.put("User_id", User_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getEmployyeIdWiseName.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getEmployyeIdWiseName);

    }

    void addRowDyanimically() {

        for (int l = 0; l < getEmployeeMonthWiseReportPojoList.size(); l++) {

            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 50f);

            LinearLayout llEmployeeMonthWiseMissPunchReportRow = new LinearLayout(MonthWiseLateReportActivity.this);
            llEmployeeMonthWiseMissPunchReportRow.setOrientation(LinearLayout.HORIZONTAL);
            llEmployeeMonthWiseMissPunchReportRow.setLayoutParams(layoutParamsForll);

            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 8);
            CustomBoldTextView tvForEmpName = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
            tvForEmpName.setTextSize(15);
            tvForEmpName.setPadding(4, 4, 4, 4);
            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
            tvForEmpName.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForEmpName.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getEmployee_Name()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvForEmpName);


            LinearLayout.LayoutParams janHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvJan = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvJan.setLayoutParams(janHeaderAndValueLayoutParam);
            tvJan.setTextColor(getResources().getColor(R.color.black));
            tvJan.setTextSize(15);
            tvJan.setPadding(4, 4, 4, 4);
            tvJan.setGravity(Gravity.CENTER);
            tvJan.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvJan.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getJAN()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvJan);

            LinearLayout.LayoutParams febHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvfeb = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvfeb.setLayoutParams(febHeaderAndValueLayoutParam);
            tvfeb.setTextColor(getResources().getColor(R.color.black));
            tvfeb.setTextSize(15);
            tvfeb.setPadding(4, 4, 4, 4);
            tvfeb.setGravity(Gravity.CENTER);
            tvfeb.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvfeb.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getFEB()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvfeb);

            LinearLayout.LayoutParams marHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3.2f);
            CustomBoldTextView tvMar = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvMar.setLayoutParams(marHeaderAndValueLayoutParam);
            tvMar.setTextColor(getResources().getColor(R.color.black));
            tvMar.setTextSize(15);
            tvMar.setPadding(4, 4, 4, 4);
            tvMar.setGravity(Gravity.CENTER);
            tvMar.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvMar.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getMAR()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvMar);

            LinearLayout.LayoutParams aprHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvApr = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvApr.setLayoutParams(aprHeaderAndValueLayoutParam);
            tvApr.setTextColor(getResources().getColor(R.color.black));
            tvApr.setTextSize(15);
            tvApr.setPadding(4, 4, 4, 4);
            tvApr.setGravity(Gravity.CENTER);
            tvApr.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvApr.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getAPI()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvApr);

            LinearLayout.LayoutParams mayHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 4f);
            CustomBoldTextView tvMay = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvMay.setLayoutParams(mayHeaderAndValueLayoutParam);
            tvMay.setTextColor(getResources().getColor(R.color.black));
            tvMay.setTextSize(15);
            tvMay.setPadding(4, 4, 4, 4);
            tvMay.setGravity(Gravity.CENTER);
            tvMay.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvMay.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getMAY()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvMay);

            LinearLayout.LayoutParams junHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvJun = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvJun.setLayoutParams(junHeaderAndValueLayoutParam);
            tvJun.setTextColor(getResources().getColor(R.color.black));
            tvJun.setTextSize(15);
            tvJun.setPadding(4, 4, 4, 4);
            tvJun.setGravity(Gravity.CENTER);
            tvJun.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvJun.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getJUN()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvJun);

            LinearLayout.LayoutParams julHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvJul = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvJul.setLayoutParams(julHeaderAndValueLayoutParam);
            tvJul.setTextColor(getResources().getColor(R.color.black));
            tvJul.setTextSize(15);
            tvJul.setPadding(4, 4, 4, 4);
            tvJul.setGravity(Gravity.CENTER);
            tvJul.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvJul.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getJUL()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvJul);


            LinearLayout.LayoutParams augHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvAug = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvAug.setLayoutParams(augHeaderAndValueLayoutParam);
            tvAug.setTextColor(getResources().getColor(R.color.black));
            tvAug.setTextSize(15);
            tvAug.setPadding(4, 4, 4, 4);
            tvAug.setGravity(Gravity.CENTER);
            tvAug.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvAug.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getAUG()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvAug);


            LinearLayout.LayoutParams sepHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvSep = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvSep.setLayoutParams(sepHeaderAndValueLayoutParam);
            tvSep.setTextColor(getResources().getColor(R.color.black));
            tvSep.setTextSize(15);
            tvSep.setPadding(4, 4, 4, 4);
            tvSep.setGravity(Gravity.CENTER);
            tvSep.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvSep.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getSEP()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvSep);


            LinearLayout.LayoutParams octHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvOct = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvOct.setLayoutParams(octHeaderAndValueLayoutParam);
            tvOct.setTextColor(getResources().getColor(R.color.black));
            tvOct.setTextSize(15);
            tvOct.setPadding(4, 4, 4, 4);
            tvOct.setGravity(Gravity.CENTER);
            tvOct.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvOct.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getOCT()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvOct);


            LinearLayout.LayoutParams novHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3.3f);
            CustomBoldTextView tvNov = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvNov.setLayoutParams(novHeaderAndValueLayoutParam);
            tvNov.setTextColor(getResources().getColor(R.color.black));
            tvNov.setTextSize(15);
            tvNov.setPadding(4, 4, 4, 4);
            tvNov.setGravity(Gravity.CENTER);
            tvNov.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvNov.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getNOV()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvNov);


            LinearLayout.LayoutParams decHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvDec = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvDec.setLayoutParams(decHeaderAndValueLayoutParam);
            tvDec.setTextColor(getResources().getColor(R.color.black));
            tvDec.setTextSize(15);
            tvDec.setPadding(4, 4, 4, 4);
            tvDec.setGravity(Gravity.CENTER);
            tvDec.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvDec.setText(String.valueOf(getEmployeeMonthWiseReportPojoList.get(l).getDEC()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvDec);


            int total = getEmployeeMonthWiseReportPojoList.get(l).getJAN() +
                    getEmployeeMonthWiseReportPojoList.get(l).getFEB() +
                    getEmployeeMonthWiseReportPojoList.get(l).getMAR() +
                    getEmployeeMonthWiseReportPojoList.get(l).getAPI() +
                    getEmployeeMonthWiseReportPojoList.get(l).getMAY() +
                    getEmployeeMonthWiseReportPojoList.get(l).getJUN() +
                    getEmployeeMonthWiseReportPojoList.get(l).getJUL() +
                    getEmployeeMonthWiseReportPojoList.get(l).getAUG() +
                    getEmployeeMonthWiseReportPojoList.get(l).getSEP() +
                    getEmployeeMonthWiseReportPojoList.get(l).getOCT() +
                    getEmployeeMonthWiseReportPojoList.get(l).getNOV() +
                    getEmployeeMonthWiseReportPojoList.get(l).getDEC();

            LinearLayout.LayoutParams totalHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 4.5f);
            CustomBoldTextView tvTotal = new CustomBoldTextView(MonthWiseLateReportActivity.this);
            tvTotal.setLayoutParams(totalHeaderAndValueLayoutParam);
            tvTotal.setTextColor(getResources().getColor(R.color.black));
            tvTotal.setTextSize(15);
            tvTotal.setPadding(4, 4, 4, 4);
            tvTotal.setGravity(Gravity.CENTER);
            tvTotal.setBackground(ContextCompat.getDrawable(MonthWiseLateReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvTotal.setText(String.valueOf(total));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvTotal);

            ll_employee_month_wise_late_report.addView(llEmployeeMonthWiseMissPunchReportRow);


            if (l == getEmployeeMonthWiseReportPojoList.size() - 1) {
                DialogUtils.hideProgressDialog();
                ll_mail_emp_month_wise_late_report.setVisibility(View.VISIBLE);
            }
        }


    }

}
