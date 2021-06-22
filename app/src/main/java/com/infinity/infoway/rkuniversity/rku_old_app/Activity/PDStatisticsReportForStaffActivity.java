package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PDStatisticsReportForStaffPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class PDStatisticsReportForStaffActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SELECT_BRANCH = "Select Branch";
    private static final String SELECT_DEPARTMENT = "Select Department";


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<PDStatisticsReportForStaffPojo.DataBean> pdStatisticsReportForStaffPojoList;

    LinearLayout ll_mail_pd_statistics_report_for_staff,
            ll_pd_statistics_report_for_staff;

    ArrayList<String> academicYearOpetion = new ArrayList<>();
    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    Spinner spAcademicYearPDStatisticsReportForStaff;

//    ===============

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    Spinner spBranchPDStatistic, spDepartmentPDStatistic;
    CustomBoldTextView tvLoadPDStatisticsReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdstatistics_report_for_staff);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getBranchApiCall(true);

        spBranchPDStatistic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    getDepartmentApiCall(false, true, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(position))));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        spAcademicYearPDStatisticsReportForStaff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                if (position > 0) {
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }


    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("PD Statistics Report For Staff");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_mail_pd_statistics_report_for_staff = findViewById(R.id.ll_mail_pd_statistics_report_for_staff);
        ll_pd_statistics_report_for_staff = findViewById(R.id.ll_pd_statistics_report_for_staff);

        spBranchPDStatistic = findViewById(R.id.spBranchPDStatistic);
        spDepartmentPDStatistic = findViewById(R.id.spDepartmentPDStatistic);

        tvLoadPDStatisticsReport = findViewById(R.id.tvLoadPDStatisticsReport);
        tvLoadPDStatisticsReport.setOnClickListener(this);

        spAcademicYearPDStatisticsReportForStaff = findViewById(R.id.spAcademicYearPDStatisticsReportForStaff);
        academicYearOpetion.add("Select Year");


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

//        if (branchOpetions.get(spBranchPDStatistic.getSelectedItemPosition()).equalsIgnoreCase("Select Branch")) {
//            DialogUtils.Show_Toast(PDStatisticsReportForStaffActivity.this, "Select Branch");
//            return false;
//        } else if (departmentOpetions.get(spDepartmentPDStatistic.getSelectedItemPosition()).equalsIgnoreCase("Select Department")) {
//            DialogUtils.Show_Toast(PDStatisticsReportForStaffActivity.this, "Select Department");
//            return false;
//        } else
        if (academicYearOpetion.get(spAcademicYearPDStatisticsReportForStaff.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(PDStatisticsReportForStaffActivity.this, "Select Academic Year");
            return false;
        }
        return true;

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.tvLoadPDStatisticsReport) {
            if (isValid()) {

                if (pdStatisticsReportForStaffPojoList != null &&
                        pdStatisticsReportForStaffPojoList.size() > 0) {
                    ll_pd_statistics_report_for_staff.removeAllViewsInLayout();
                    pdStatisticsReportForStaffPojoList.clear();
                }

                String Branch = String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(spBranchPDStatistic.getSelectedItemPosition())));
                String Department = String.valueOf(hashMapDepartmentAndID.get(departmentOpetions.get(spDepartmentPDStatistic.getSelectedItemPosition())));
                String Designation = "0";// ||
                String Academic_year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPDStatisticsReportForStaff.getSelectedItemPosition())));
                String User_id = mySharedPreferecesRKUOLD.getUserID();
                String Branch_payroll_or_main = "1";

                getPdStatisticsReportForStaffApi(Branch, Department, Designation, Academic_year,
                        User_id, Branch_payroll_or_main);
            }
        }
    }

    private void getBranchApiCall(final boolean isGetYearApiCall) {
        DialogUtils.showProgressDialog(PDStatisticsReportForStaffActivity.this, "");

        StringRequest getBranchRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Branch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

                            branchOpetions = new ArrayList<>();
                            branchOpetions.add(SELECT_BRANCH);
                            hashMapBranchOpetionAndID = new HashMap<>();
                            hashMapBranchOpetionAndID.put(SELECT_BRANCH,0);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapBranchOpetionAndID.put(jsonArray.getJSONObject(i).getString("brm_name"),
                                        jsonArray.getJSONObject(i).getInt("id"));
                                branchOpetions.add(jsonArray.getJSONObject(i).getString("brm_name"));
                            }
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(PDStatisticsReportForStaffActivity.this, branchOpetions);
                            spBranchPDStatistic.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(isGetYearApiCall, false, false, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(PDStatisticsReportForStaffActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PDStatisticsReportForStaffActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDStatisticsReportForStaffActivity.this, "Please Try Again Later");
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

    private void getDepartmentApiCall(final boolean isGetYearApiCall, final boolean isPdShow, final boolean isPdHide, final String branch_id) {

        if (isPdShow) {
            DialogUtils.showProgressDialog(PDStatisticsReportForStaffActivity.this, "");
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
                            departmentOpetions.add(SELECT_DEPARTMENT);
                            hashMapDepartmentAndID = new HashMap<>();
                            hashMapDepartmentAndID.put(SELECT_DEPARTMENT,0);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapDepartmentAndID.put(jsonArray.getJSONObject(i).getString("dep_name"),
                                        jsonArray.getJSONObject(i).getInt("id"));
                                departmentOpetions.add(jsonArray.getJSONObject(i).getString("dep_name"));
                            }
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(PDStatisticsReportForStaffActivity.this, departmentOpetions);
                            spDepartmentPDStatistic.setAdapter(departmentOpetionAdapter);
                            if (isGetYearApiCall) {
                                getYearApiCall();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(PDStatisticsReportForStaffActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDStatisticsReportForStaffActivity.this, "Please Try Again Later");
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


    private void getYearApiCall() {
//        DialogUtils.showProgressDialog(PDStatisticsReportForStaffActivity.this, "");
        String url = URLS.Get_YEAR;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapAcademicYearAndID.put(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME),
                                        jsonArray.getJSONObject(i).getInt(ApiConstants.YEAR_ID));
                                academicYearOpetion.add(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME));
                            }
                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(PDStatisticsReportForStaffActivity.this, academicYearOpetion);
                            spAcademicYearPDStatisticsReportForStaff.setAdapter(academicYearAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDStatisticsReportForStaffActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    private void getPdStatisticsReportForStaffApi(final String Branch, final String Department, final String Designation,
                                                  final String Academic_year, final String User_id, final String Branch_payroll_or_main) {

        DialogUtils.showProgressDialog(PDStatisticsReportForStaffActivity.this, "");

        StringRequest academicContributionReportRequest = new StringRequest(Request.Method.POST, URLS.Get_PD_Statistics_Report_for_Staff, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        PDStatisticsReportForStaffPojo pdStatisticsReportForStaffPojo = gson.fromJson("{\"Data\":" + response + "}", PDStatisticsReportForStaffPojo.class);
                        if (pdStatisticsReportForStaffPojo.getData() != null && pdStatisticsReportForStaffPojo.getData().size() > 0) {
                            pdStatisticsReportForStaffPojoList = pdStatisticsReportForStaffPojo.getData();
                            addRowDyanimically();
                        } else {
                            ll_mail_pd_statistics_report_for_staff.setVisibility(View.GONE);
                            Toast.makeText(PDStatisticsReportForStaffActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        ll_mail_pd_statistics_report_for_staff.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                } else {
                    ll_mail_pd_statistics_report_for_staff.setVisibility(View.GONE);
                    Toast.makeText(PDStatisticsReportForStaffActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ll_mail_pd_statistics_report_for_staff.setVisibility(View.GONE);
                DialogUtils.Show_Toast(PDStatisticsReportForStaffActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Branch", Branch);
                params2.put("Department", Department);
                params2.put("Designation", Designation);
                params2.put("Academic_year", Academic_year);
                params2.put("User_id", User_id);
                params2.put("Branch_payroll_or_main", Branch_payroll_or_main);
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


    void addRowDyanimically() {

        for (int l = 0; l < pdStatisticsReportForStaffPojoList.size(); l++) {

            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 13f);

            LinearLayout llPDStatisticsReportForStaffRow = new LinearLayout(PDStatisticsReportForStaffActivity.this);
            llPDStatisticsReportForStaffRow.setOrientation(LinearLayout.HORIZONTAL);
            llPDStatisticsReportForStaffRow.setLayoutParams(layoutParamsForll);

            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3);
            CustomBoldTextView tvForEmpName = new CustomBoldTextView(PDStatisticsReportForStaffActivity.this);
            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
            tvForEmpName.setTextSize(15);
            tvForEmpName.setPadding(4, 4, 4, 4);
            tvForEmpName.setGravity(Gravity.CENTER);
            tvForEmpName.setBackground(ContextCompat.getDrawable(PDStatisticsReportForStaffActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForEmpName.setText(String.valueOf(pdStatisticsReportForStaffPojoList.get(l).getEmp_name()));
            llPDStatisticsReportForStaffRow.addView(tvForEmpName);


            LinearLayout.LayoutParams teachingLearningHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
            CustomBoldTextView tvTeachingLearning = new CustomBoldTextView(PDStatisticsReportForStaffActivity.this);
            tvTeachingLearning.setLayoutParams(teachingLearningHeaderAndValueLayoutParam);
            tvTeachingLearning.setTextColor(getResources().getColor(R.color.black));
            tvTeachingLearning.setTextSize(15);
            tvTeachingLearning.setPadding(4, 4, 4, 4);
            tvTeachingLearning.setGravity(Gravity.CENTER);
            tvTeachingLearning.setBackground(ContextCompat.getDrawable(PDStatisticsReportForStaffActivity.this,
                    R.drawable.bg_header_ac_report));
            tvTeachingLearning.setText(String.valueOf(pdStatisticsReportForStaffPojoList.get(l).getTeaching_Learning()));
            llPDStatisticsReportForStaffRow.addView(tvTeachingLearning);

            LinearLayout.LayoutParams domainSpecificHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
            CustomBoldTextView tvDomainSpecific = new CustomBoldTextView(PDStatisticsReportForStaffActivity.this);
            tvDomainSpecific.setLayoutParams(domainSpecificHeaderAndValueLayoutParam);
            tvDomainSpecific.setTextColor(getResources().getColor(R.color.black));
            tvDomainSpecific.setTextSize(15);
            tvDomainSpecific.setPadding(4, 4, 4, 4);
            tvDomainSpecific.setGravity(Gravity.CENTER);
            tvDomainSpecific.setBackground(ContextCompat.getDrawable(PDStatisticsReportForStaffActivity.this,
                    R.drawable.bg_header_ac_report));
            tvDomainSpecific.setText(String.valueOf(pdStatisticsReportForStaffPojoList.get(l).getDomain_Specific()));
            llPDStatisticsReportForStaffRow.addView(tvDomainSpecific);

            LinearLayout.LayoutParams researchMethodHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
            CustomBoldTextView tvResearchMethod = new CustomBoldTextView(PDStatisticsReportForStaffActivity.this);
            tvResearchMethod.setLayoutParams(researchMethodHeaderAndValueLayoutParam);
            tvResearchMethod.setTextColor(getResources().getColor(R.color.black));
            tvResearchMethod.setTextSize(15);
            tvResearchMethod.setPadding(4, 4, 4, 4);
            tvResearchMethod.setGravity(Gravity.CENTER);
            tvResearchMethod.setBackground(ContextCompat.getDrawable(PDStatisticsReportForStaffActivity.this,
                    R.drawable.bg_header_ac_report));
            tvResearchMethod.setText(String.valueOf(pdStatisticsReportForStaffPojoList.get(l).getResearch_Method()));
            llPDStatisticsReportForStaffRow.addView(tvResearchMethod);

            LinearLayout.LayoutParams softSkillAndLeadershipHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
            CustomBoldTextView tvsoftSkillAndLeadership = new CustomBoldTextView(PDStatisticsReportForStaffActivity.this);
            tvsoftSkillAndLeadership.setLayoutParams(softSkillAndLeadershipHeaderAndValueLayoutParam);
            tvsoftSkillAndLeadership.setTextColor(getResources().getColor(R.color.black));
            tvsoftSkillAndLeadership.setTextSize(15);
            tvsoftSkillAndLeadership.setPadding(4, 4, 4, 4);
            tvsoftSkillAndLeadership.setGravity(Gravity.CENTER);
            tvsoftSkillAndLeadership.setBackground(ContextCompat.getDrawable(PDStatisticsReportForStaffActivity.this,
                    R.drawable.bg_header_ac_report));
            tvsoftSkillAndLeadership.setText(String.valueOf(pdStatisticsReportForStaffPojoList.get(l).getSoft_skills_Leadership()));
            llPDStatisticsReportForStaffRow.addView(tvsoftSkillAndLeadership);

            LinearLayout.LayoutParams utilizedBudgetHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
            CustomBoldTextView tvUtilizedBudget = new CustomBoldTextView(PDStatisticsReportForStaffActivity.this);
            tvUtilizedBudget.setLayoutParams(utilizedBudgetHeaderAndValueLayoutParam);
            tvUtilizedBudget.setTextColor(getResources().getColor(R.color.black));
            tvUtilizedBudget.setTextSize(15);
            tvUtilizedBudget.setPadding(4, 4, 4, 4);
            tvUtilizedBudget.setGravity(Gravity.CENTER);
            tvUtilizedBudget.setBackground(ContextCompat.getDrawable(PDStatisticsReportForStaffActivity.this,
                    R.drawable.bg_header_ac_report));
            tvUtilizedBudget.setText(String.valueOf(pdStatisticsReportForStaffPojoList.get(l).getUtilized_Budget()));
            llPDStatisticsReportForStaffRow.addView(tvUtilizedBudget);

            ll_pd_statistics_report_for_staff.addView(llPDStatisticsReportForStaffRow);


            if (l == pdStatisticsReportForStaffPojoList.size() - 1) {
                DialogUtils.hideProgressDialog();
                ll_mail_pd_statistics_report_for_staff.setVisibility(View.VISIBLE);
                DialogUtils.hideProgressDialog();
            }
        }
    }
}
