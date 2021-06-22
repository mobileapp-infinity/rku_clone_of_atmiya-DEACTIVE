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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.EmployeeLateComingReportPojo;
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

public class EmployeeLateComingReportActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<EmployeeLateComingReportPojo.DataBean> emlpoyeeLateComingReportPojoList;

//    LinearLayout ll_employee_late_coming_report, ll_emp_late_coming_report_header;

    String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    ArrayList<String> monthOpetions = new ArrayList<>();
    HashMap<String, Integer> hashMapMonthOpetionAndID = new HashMap<>();

    //    String years[] = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    ArrayList<String> yearOpetions;
//    HashMap<String, Integer> hashMapYearOpetionAndID = new HashMap<>();


    Spinner spMonthEmpLateComingReport, spYearEmpLateComingReport;

    CustomBoldTextView tvLoadLateComingReport;
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH);

    Spinner spBranchEmpLateCommingReport, spDepartmentEmpLateCommingReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    String Branch_id = "0";
    String Departmet_id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_late_coming_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

//        spMonthEmpLateComingReport.setSelection(currentMonth + 1);
//        spYearEmpLateComingReport.setSelection(yearOpetions.indexOf(String.valueOf(currentYear)));

        getReportFilterYearApiCall();

        spBranchEmpLateCommingReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spDepartmentEmpLateCommingReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Employee Late Coming Report");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

//        ll_employee_late_coming_report = findViewById(R.id.ll_employee_late_coming_report);
//        ll_emp_late_coming_report_header = findViewById(R.id.ll_emp_late_coming_report_header);

        spMonthEmpLateComingReport = findViewById(R.id.spMonthEmpLateComingReport);
        monthOpetions.add("Select Month");
        spYearEmpLateComingReport = findViewById(R.id.spYearEmpLateComingReport);
//        yearOpetions.add("Select Year");

        spBranchEmpLateCommingReport = findViewById(R.id.spBranchEmpLateCommingReport);
        spDepartmentEmpLateCommingReport = findViewById(R.id.spDepartmentEmpLateCommingReport);

        for (int i = 0; i < months.length; i++) {
            monthOpetions.add(months[i]);
            hashMapMonthOpetionAndID.put(months[i], i + 1);
        }

        SpinnerSimpleAdapter monthOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLateComingReportActivity.this, monthOpetions);
        spMonthEmpLateComingReport.setAdapter(monthOpetionAdapter);

//        for (int i = 0; i < years.length; i++) {
//            yearOpetions.add(years[i]);
//            hashMapYearOpetionAndID.put(years[i], i + 1);
//        }
//
//        SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLateComingReportActivity.this, yearOpetions);
//        spYearEmpLateComingReport.setAdapter(yearsOpetionAdapter);

        tvLoadLateComingReport = findViewById(R.id.tvLoadLateComingReport);
        tvLoadLateComingReport.setOnClickListener(this);

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


    private void getReportFilterYearApiCall() {
        DialogUtils.showProgressDialog(EmployeeLateComingReportActivity.this, "");

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

                            SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLateComingReportActivity.this, yearOpetions);
                            spYearEmpLateComingReport.setAdapter(yearsOpetionAdapter);

                            getBranchApiCall(false, false);

                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeLateComingReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeLateComingReportActivity.this, "Year empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeLateComingReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
            }
        });

        getBranchRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getBranchRequest);
    }


    private boolean isValid() {

        if (monthOpetions.get(spMonthEmpLateComingReport.getSelectedItemPosition()).equalsIgnoreCase("Select Month")) {
            DialogUtils.Show_Toast(EmployeeLateComingReportActivity.this, "Select Month");
            return false;
        } else if (yearOpetions.get(spYearEmpLateComingReport.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(EmployeeLateComingReportActivity.this, "Select Year");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.tvLoadLateComingReport) {
            if (isValid()) {
//                if (!TextUtils.isEmpty(Branch_id)) {
//                    if (!TextUtils.isEmpty(Departmet_id)) {
                String Month = String.valueOf(hashMapMonthOpetionAndID.get(monthOpetions.get(spMonthEmpLateComingReport.getSelectedItemPosition())));
                String Year = yearOpetions.get(spYearEmpLateComingReport.getSelectedItemPosition());
                String User_id = mySharedPreferecesRKUOLD.getUserID();
                getEmployeeLateComingReportApi(Branch_id, Departmet_id, Month, Year, User_id);
//                    } else {
//                        Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//                }

            }
        }
    }

    private void getBranchApiCall(boolean isPdShow, final boolean isPdHide) {
        if (isPdShow) {
            DialogUtils.showProgressDialog(EmployeeLateComingReportActivity.this, "");
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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLateComingReportActivity.this, branchOpetions);
                            spBranchEmpLateCommingReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeLateComingReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeLateComingReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeLateComingReportActivity.this, "Please Try Again Later");
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
            DialogUtils.showProgressDialog(EmployeeLateComingReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLateComingReportActivity.this, departmentOpetions);
                            spDepartmentEmpLateCommingReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(EmployeeLateComingReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeLateComingReportActivity.this, "Please Try Again Later");
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


    private void getEmployeeLateComingReportApi(final String branch_id, final String Department_id,
                                                final String Month, final String Year,
                                                final String User_id) {

        DialogUtils.showProgressDialog(EmployeeLateComingReportActivity.this, "");
        StringRequest getEmployyeIdWiseName = new StringRequest(Request.Method.POST, URLS.Get_Employee_Late_Coming_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        EmployeeLateComingReportPojo employeeLateComingReportPojo = gson.fromJson("{\"Data\":" + response + "}", EmployeeLateComingReportPojo.class);
                        if (employeeLateComingReportPojo.getData() != null && employeeLateComingReportPojo.getData().size() > 0) {
                            emlpoyeeLateComingReportPojoList = employeeLateComingReportPojo.getData();
                            Intent intent = new Intent(EmployeeLateComingReportActivity.this, EmployeeLateCommingDetailReportActivity.class);
                            intent.putExtra(IntentConstants.EMPLOYEE_LATE_COMMING_REPORT_DETAILS, (Serializable) emlpoyeeLateComingReportPojoList);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EmployeeLateComingReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(EmployeeLateComingReportActivity.this, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
//                    ll_emp_late_coming_report_header.setVisibility(View.GONE);
                    Toast.makeText(EmployeeLateComingReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                ll_emp_late_coming_report_header.setVisibility(View.GONE);
                DialogUtils.hideProgressDialog();
                DialogUtils.Show_Toast(EmployeeLateComingReportActivity.this, "Please Try Again Later");
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Month", Month);
                params2.put("Year", Year);
                params2.put("branch_id", branch_id);
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
//
//        for (int l = 0; l < emlpoyeeLateComingReportPojoList.size(); l++) {
//
//            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
//
//            LinearLayout llLateComingReportRow = new LinearLayout(EmployeeLateComingReportActivity.this);
//            llLateComingReportRow.setOrientation(LinearLayout.HORIZONTAL);
//            llLateComingReportRow.setLayoutParams(layoutParamsForll);
//
//            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.2f);
//            CustomBoldTextView tvForEmpName = new CustomBoldTextView(EmployeeLateComingReportActivity.this);
//            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
//            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
//            tvForEmpName.setTextSize(15);
//            tvForEmpName.setPadding(4, 4, 4, 4);
//            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
//            tvForEmpName.setBackground(ContextCompat.getDrawable(EmployeeLateComingReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvForEmpName.setText(String.valueOf(emlpoyeeLateComingReportPojoList.get(l).getName()));
//
//            llLateComingReportRow.addView(tvForEmpName);
//
//            LinearLayout.LayoutParams lateByHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 0.8f);
//            CustomBoldTextView tvForLateBy = new CustomBoldTextView(EmployeeLateComingReportActivity.this);
//            tvForLateBy.setLayoutParams(lateByHeaderAndValueLayoutParam);
//            tvForLateBy.setTextColor(getResources().getColor(R.color.black));
//            tvForLateBy.setTextSize(15);
//            tvForLateBy.setPadding(4, 4, 4, 4);
//            tvForLateBy.setGravity(Gravity.CENTER_VERTICAL);
//            tvForLateBy.setBackground(ContextCompat.getDrawable(EmployeeLateComingReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvForLateBy.setText(String.valueOf(emlpoyeeLateComingReportPojoList.get(l).getTotal_late_hours()));
//
//            llLateComingReportRow.addView(tvForLateBy);
//
//            ll_employee_late_coming_report.addView(llLateComingReportRow);
//        }
//
//    }

}
