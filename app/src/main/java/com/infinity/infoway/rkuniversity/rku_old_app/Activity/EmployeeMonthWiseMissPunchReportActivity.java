package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetEmployeeMonthWiseMisPunchReportPojo;
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

public class EmployeeMonthWiseMissPunchReportActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<GetEmployeeMonthWiseMisPunchReportPojo.DataBean> getEmployeeMonthWiseMissPunchReportPojoList;

//    LinearLayout ll_employee_month_wise_miss_punch_report,
//            ll_mail_emp_month_wise_miss_pinch_report,ll_no_data_found;


    //    String years[] = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    ArrayList<String> yearOpetions;
//    HashMap<String, Integer> hashMapYearOpetionAndID = new HashMap<>();

    Spinner spYearEmpMonthWiseMissPunchReport;

    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH);

    Spinner spBranchEmpMonthWiseMissPunchReport, spDepartmentEmpMonthWiseMissPunchReport;
    CustomBoldTextView tvLoadEmpMonthWiseMissPunchReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    String Branch_id = "0";
    String Departmet_id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_month_wise_miss_punch_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

//        spYearEmpMonthWiseMissPunchReport.setSelection(yearOpetions.indexOf(String.valueOf(currentYear)));

        getReportFilterYearApiCall();

        spBranchEmpMonthWiseMissPunchReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spDepartmentEmpMonthWiseMissPunchReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spYearEmpMonthWiseMissPunchReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    if (getEmployeeMonthWiseMissPunchReportPojoList != null && getEmployeeMonthWiseMissPunchReportPojoList.size() > 0) {
                        getEmployeeMonthWiseMissPunchReportPojoList.clear();
                    }
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
        txt_act.setText("Employee Month Wise Mis Punch Report");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

//        ll_employee_month_wise_miss_punch_report = findViewById(R.id.ll_employee_month_wise_miss_punch_report);
//        ll_mail_emp_month_wise_miss_pinch_report = findViewById(R.id.ll_mail_emp_month_wise_miss_pinch_report);
//        ll_no_data_found = findViewById(R.id.ll_no_data_found);

        spYearEmpMonthWiseMissPunchReport = findViewById(R.id.spYearEmpMonthWiseMissPunchReport);
//        yearOpetions.add("Select Year");
//
//        for (int i = 0; i < years.length; i++) {
//            yearOpetions.add(years[i]);
//            hashMapYearOpetionAndID.put(years[i], i + 1);
//        }
//
//        SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(EmployeeMonthWiseMissPunchReportActivity.this, yearOpetions);
//        spYearEmpMonthWiseMissPunchReport.setAdapter(yearsOpetionAdapter);
//        spYearEmpMonthWiseMissPunchReport.setSelection(yearOpetions.indexOf(1));

        spBranchEmpMonthWiseMissPunchReport = findViewById(R.id.spBranchEmpMonthWiseMissPunchReport);
        spDepartmentEmpMonthWiseMissPunchReport = findViewById(R.id.spDepartmentEmpMonthWiseMissPunchReport);
        tvLoadEmpMonthWiseMissPunchReport = findViewById(R.id.tvLoadEmpMonthWiseMissPunchReport);
        tvLoadEmpMonthWiseMissPunchReport.setOnClickListener(this);

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
        } else if (view.getId() == R.id.tvLoadEmpMonthWiseMissPunchReport) {
//            if (!TextUtils.isEmpty(Branch_id)) {
//                if (!TextUtils.isEmpty(Departmet_id)) {
            getEmployeeMonthWiseMissPunchReportApi(yearOpetions.get(spYearEmpMonthWiseMissPunchReport.getSelectedItemPosition()),
                    Branch_id, Departmet_id, mySharedPreferecesRKUOLD.getUserID());
//                } else {
//                    Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    private void getReportFilterYearApiCall() {
        DialogUtils.showProgressDialog(EmployeeMonthWiseMissPunchReportActivity.this, "");

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

                            SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(EmployeeMonthWiseMissPunchReportActivity.this, yearOpetions);
                            spYearEmpMonthWiseMissPunchReport.setAdapter(yearsOpetionAdapter);

                            getBranchApiCall(false, false);

                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeMonthWiseMissPunchReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeMonthWiseMissPunchReportActivity.this, "Year empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeMonthWiseMissPunchReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
            }
        });

        getBranchRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getBranchRequest);
    }


    private void getBranchApiCall(boolean isPdShow, final boolean isPdHide) {
        if (isPdShow) {
            DialogUtils.showProgressDialog(EmployeeMonthWiseMissPunchReportActivity.this, "");
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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(EmployeeMonthWiseMissPunchReportActivity.this, branchOpetions);
                            spBranchEmpMonthWiseMissPunchReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeMonthWiseMissPunchReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeMonthWiseMissPunchReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeMonthWiseMissPunchReportActivity.this, "Please Try Again Later");
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
            DialogUtils.showProgressDialog(EmployeeMonthWiseMissPunchReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(EmployeeMonthWiseMissPunchReportActivity.this, departmentOpetions);
                            spDepartmentEmpMonthWiseMissPunchReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(EmployeeMonthWiseMissPunchReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeMonthWiseMissPunchReportActivity.this, "Please Try Again Later");
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

    private void getEmployeeMonthWiseMissPunchReportApi(final String Year, final String Branch_id,
                                                        final String Department_id, final String User_id) {

        DialogUtils.showProgressDialog(EmployeeMonthWiseMissPunchReportActivity.this, "");
        StringRequest getEmployyeIdWiseName = new StringRequest(Request.Method.POST, URLS.Get_Employee_Monthwise_Mis_Punch_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetEmployeeMonthWiseMisPunchReportPojo getEmployeeMonthWiseMisPunchReportPojo = gson.fromJson("{\"Data\":" + response + "}", GetEmployeeMonthWiseMisPunchReportPojo.class);
                        if (getEmployeeMonthWiseMisPunchReportPojo != null && getEmployeeMonthWiseMisPunchReportPojo.getData().size() > 0) {
                            getEmployeeMonthWiseMissPunchReportPojoList = getEmployeeMonthWiseMisPunchReportPojo.getData();

                            Intent intent = new Intent(EmployeeMonthWiseMissPunchReportActivity.this, EmployeeMonthWiseMissPunchDetailReport.class);
                            intent.putExtra(IntentConstants.EMPLOYEE_MONTH_WISE_MIS_PUNCH_REPORT_DEATILS, (Serializable) getEmployeeMonthWiseMissPunchReportPojoList);
                            startActivity(intent);
                            //                            ll_no_data_found.setVisibility(View.GONE);
//                            addRowDyanimically();
                        } else {
//                            ll_mail_emp_month_wise_miss_pinch_report.setVisibility(View.GONE);
//                            ll_no_data_found.setVisibility(View.VISIBLE);

                            DialogUtils.Show_Toast(EmployeeMonthWiseMissPunchReportActivity.this, "No data found!");
                        }
                    } catch (Exception e) {

                        Toast.makeText(EmployeeMonthWiseMissPunchReportActivity.this, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    ll_mail_emp_month_wise_miss_pinch_report.setVisibility(View.GONE);
                    DialogUtils.Show_Toast(EmployeeMonthWiseMissPunchReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                ll_mail_emp_month_wise_miss_pinch_report.setVisibility(View.GONE);
                DialogUtils.Show_Toast(EmployeeMonthWiseMissPunchReportActivity.this, "Please Try Again Later");
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
                params2.put("Department_id", Department_id);
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


//    void addRowDyanimically() {
//
//        for (int l = 0; l < getEmployeeMonthWiseMissPunchReportPojoList.size(); l++) {
//
//            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT, 50f);
//
//            LinearLayout llEmployeeMonthWiseMissPunchReportRow = new LinearLayout(EmployeeMonthWiseMissPunchReportActivity.this);
//            llEmployeeMonthWiseMissPunchReportRow.setOrientation(LinearLayout.HORIZONTAL);
//            llEmployeeMonthWiseMissPunchReportRow.setLayoutParams(layoutParamsForll);
//
//            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT,8);
//            CustomBoldTextView tvForEmpName = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
//            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
//            tvForEmpName.setTextSize(15);
//            tvForEmpName.setPadding(4, 4, 4, 4);
//            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
//            tvForEmpName.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvForEmpName.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getEmployee_Name()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvForEmpName);
//
//
//            LinearLayout.LayoutParams janHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvJan = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvJan.setLayoutParams(janHeaderAndValueLayoutParam);
//            tvJan.setTextColor(getResources().getColor(R.color.black));
//            tvJan.setTextSize(15);
//            tvJan.setPadding(4, 4, 4, 4);
//            tvJan.setGravity(Gravity.CENTER);
//            tvJan.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvJan.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJAN()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvJan);
//
//            LinearLayout.LayoutParams febHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvfeb = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvfeb.setLayoutParams(febHeaderAndValueLayoutParam);
//            tvfeb.setTextColor(getResources().getColor(R.color.black));
//            tvfeb.setTextSize(15);
//            tvfeb.setPadding(4, 4, 4, 4);
//            tvfeb.setGravity(Gravity.CENTER);
//            tvfeb.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvfeb.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getFEB()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvfeb);
//
//            LinearLayout.LayoutParams marHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3.2f);
//            CustomBoldTextView tvMar = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvMar.setLayoutParams(marHeaderAndValueLayoutParam);
//            tvMar.setTextColor(getResources().getColor(R.color.black));
//            tvMar.setTextSize(15);
//            tvMar.setPadding(4, 4, 4, 4);
//            tvMar.setGravity(Gravity.CENTER);
//            tvMar.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvMar.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getMAR()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvMar);
//
//            LinearLayout.LayoutParams aprHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvApr = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvApr.setLayoutParams(aprHeaderAndValueLayoutParam);
//            tvApr.setTextColor(getResources().getColor(R.color.black));
//            tvApr.setTextSize(15);
//            tvApr.setPadding(4, 4, 4, 4);
//            tvApr.setGravity(Gravity.CENTER);
//            tvApr.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvApr.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAPI()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvApr);
//
//            LinearLayout.LayoutParams mayHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 4f);
//            CustomBoldTextView tvMay = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvMay.setLayoutParams(mayHeaderAndValueLayoutParam);
//            tvMay.setTextColor(getResources().getColor(R.color.black));
//            tvMay.setTextSize(15);
//            tvMay.setPadding(4, 4, 4, 4);
//            tvMay.setGravity(Gravity.CENTER);
//            tvMay.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvMay.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getMAY()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvMay);
//
//            LinearLayout.LayoutParams junHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvJun = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvJun.setLayoutParams(junHeaderAndValueLayoutParam);
//            tvJun.setTextColor(getResources().getColor(R.color.black));
//            tvJun.setTextSize(15);
//            tvJun.setPadding(4, 4, 4, 4);
//            tvJun.setGravity(Gravity.CENTER);
//            tvJun.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvJun.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJUN()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvJun);
//
//            LinearLayout.LayoutParams julHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvJul = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvJul.setLayoutParams(julHeaderAndValueLayoutParam);
//            tvJul.setTextColor(getResources().getColor(R.color.black));
//            tvJul.setTextSize(15);
//            tvJul.setPadding(4, 4, 4, 4);
//            tvJul.setGravity(Gravity.CENTER);
//            tvJul.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvJul.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJUL()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvJul);
//
//
//            LinearLayout.LayoutParams augHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvAug = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvAug.setLayoutParams(augHeaderAndValueLayoutParam);
//            tvAug.setTextColor(getResources().getColor(R.color.black));
//            tvAug.setTextSize(15);
//            tvAug.setPadding(4, 4, 4, 4);
//            tvAug.setGravity(Gravity.CENTER);
//            tvAug.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvAug.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAUG()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvAug);
//
//
//            LinearLayout.LayoutParams sepHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvSep = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvSep.setLayoutParams(sepHeaderAndValueLayoutParam);
//            tvSep.setTextColor(getResources().getColor(R.color.black));
//            tvSep.setTextSize(15);
//            tvSep.setPadding(4, 4, 4, 4);
//            tvSep.setGravity(Gravity.CENTER);
//            tvSep.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvSep.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getSEP()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvSep);
//
//
//            LinearLayout.LayoutParams octHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvOct = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvOct.setLayoutParams(octHeaderAndValueLayoutParam);
//            tvOct.setTextColor(getResources().getColor(R.color.black));
//            tvOct.setTextSize(15);
//            tvOct.setPadding(4, 4, 4, 4);
//            tvOct.setGravity(Gravity.CENTER);
//            tvOct.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvOct.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getOCT()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvOct);
//
//
//            LinearLayout.LayoutParams novHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3.3f);
//            CustomBoldTextView tvNov = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvNov.setLayoutParams(novHeaderAndValueLayoutParam);
//            tvNov.setTextColor(getResources().getColor(R.color.black));
//            tvNov.setTextSize(15);
//            tvNov.setPadding(4, 4, 4, 4);
//            tvNov.setGravity(Gravity.CENTER);
//            tvNov.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvNov.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getNOV()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvNov);
//
//
//            LinearLayout.LayoutParams decHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
//            CustomBoldTextView tvDec = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvDec.setLayoutParams(decHeaderAndValueLayoutParam);
//            tvDec.setTextColor(getResources().getColor(R.color.black));
//            tvDec.setTextSize(15);
//            tvDec.setPadding(4, 4, 4, 4);
//            tvDec.setGravity(Gravity.CENTER);
//            tvDec.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvDec.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getDEC()));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvDec);
//
//
//            int total = getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJAN() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getFEB() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getMAR() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAPI() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getMAY() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJUN() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJUL() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAUG() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getSEP() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAUG() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getNOV() +
//                        getEmployeeMonthWiseMissPunchReportPojoList.get(l).getDEC();
//
//            LinearLayout.LayoutParams totalHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 4.5f);
//            CustomBoldTextView tvTotal = new CustomBoldTextView(EmployeeMonthWiseMissPunchReportActivity.this);
//            tvTotal.setLayoutParams(totalHeaderAndValueLayoutParam);
//            tvTotal.setTextColor(getResources().getColor(R.color.black));
//            tvTotal.setTextSize(15);
//            tvTotal.setPadding(4, 4, 4, 4);
//            tvTotal.setGravity(Gravity.CENTER);
//            tvTotal.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvTotal.setText(String.valueOf(total));
//
//            llEmployeeMonthWiseMissPunchReportRow.addView(tvTotal);
//
//            ll_employee_month_wise_miss_punch_report.addView(llEmployeeMonthWiseMissPunchReportRow);
//
//
//            if (l == getEmployeeMonthWiseMissPunchReportPojoList.size() - 1){
//                DialogUtils.hideProgressDialog();
//                ll_mail_emp_month_wise_miss_pinch_report.setVisibility(View.VISIBLE);
//            }
//        }
//    }
}
