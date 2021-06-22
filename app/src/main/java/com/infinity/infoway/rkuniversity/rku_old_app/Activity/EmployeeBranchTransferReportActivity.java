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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.EmployeeBranchTransferReportPojo;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class EmployeeBranchTransferReportActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<EmployeeBranchTransferReportPojo.DataBean> employeeBranchTansferReportPojoList;

    LinearLayout ll_employee_branch_transfer_report,
            ll_employee_branch_transfer_report_header;

    Spinner spBranchEmpBranchTransferReport, spDepartmentEmpBranchTransferReport;
    CustomBoldTextView tvLoadEmpBranchTransferReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    String Branch_id = "0";
    String Departmet_id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_branch_transfer_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getBranchApiCall();

        spBranchEmpBranchTransferReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spDepartmentEmpBranchTransferReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        txt_act.setText("Employee Branch Transfer Report");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_employee_branch_transfer_report = findViewById(R.id.ll_employee_branch_transfer_report);
        ll_employee_branch_transfer_report_header = findViewById(R.id.ll_employee_branch_transfer_report_header);

        spBranchEmpBranchTransferReport = findViewById(R.id.spBranchEmpBranchTransferReport);
        spDepartmentEmpBranchTransferReport = findViewById(R.id.spDepartmentEmpBranchTransferReport);
        tvLoadEmpBranchTransferReport = findViewById(R.id.tvLoadEmpBranchTransferReport);
        tvLoadEmpBranchTransferReport.setOnClickListener(this);

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
        } else if (view.getId() == R.id.tvLoadEmpBranchTransferReport) {
//            if (!TextUtils.isEmpty(Branch_id)) {
//                if (!TextUtils.isEmpty(Departmet_id)) {
            ll_employee_branch_transfer_report.removeAllViewsInLayout();

            getEmployeeBranchTransferReportApi(Branch_id, Departmet_id);
//                } else {
//                    Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    private void getBranchApiCall() {
        DialogUtils.showProgressDialog(EmployeeBranchTransferReportActivity.this, "");

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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(EmployeeBranchTransferReportActivity.this, branchOpetions);
                            spBranchEmpBranchTransferReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeBranchTransferReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeBranchTransferReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeBranchTransferReportActivity.this, "Please Try Again Later");
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
            DialogUtils.showProgressDialog(EmployeeBranchTransferReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(EmployeeBranchTransferReportActivity.this, departmentOpetions);
                            spDepartmentEmpBranchTransferReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(EmployeeBranchTransferReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeBranchTransferReportActivity.this, "Please Try Again Later");
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

    private void getEmployeeBranchTransferReportApi(final String Branch_id, final String department_id) {
        DialogUtils.showProgressDialog(EmployeeBranchTransferReportActivity.this, "");

        StringRequest employeePayrollDerailReportRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_Branch_Transfer_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        EmployeeBranchTransferReportPojo employeeBranchTransferReportPojo = gson.fromJson("{\"Data\":" + response + "}", EmployeeBranchTransferReportPojo.class);
                        if (employeeBranchTransferReportPojo.getData() != null && employeeBranchTransferReportPojo.getData().size() > 0) {
                            employeeBranchTansferReportPojoList = employeeBranchTransferReportPojo.getData();
                            ll_employee_branch_transfer_report_header.setVisibility(View.VISIBLE);
                            addRowDyanimically();
                            DialogUtils.hideProgressDialog();
                        } else {
                            ll_employee_branch_transfer_report_header.setVisibility(View.GONE);
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeBranchTransferReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        ll_employee_branch_transfer_report_header.setVisibility(View.GONE);
                        DialogUtils.hideProgressDialog();
                        Toast.makeText(EmployeeBranchTransferReportActivity.this, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    ll_employee_branch_transfer_report_header.setVisibility(View.GONE);
                    Toast.makeText(EmployeeBranchTransferReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ll_employee_branch_transfer_report_header.setVisibility(View.GONE);
                DialogUtils.Show_Toast(EmployeeBranchTransferReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Branch_id", Branch_id);
                params2.put("department_id", department_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
                //                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        employeePayrollDerailReportRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(employeePayrollDerailReportRequest);
    }

    void addRowDyanimically() {

        for (int l = 0; l < employeeBranchTansferReportPojoList.size(); l++) {

            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 7);

            LinearLayout llEmployeeBranchTRansferReportRow = new LinearLayout(EmployeeBranchTransferReportActivity.this);
            llEmployeeBranchTRansferReportRow.setOrientation(LinearLayout.HORIZONTAL);
            llEmployeeBranchTRansferReportRow.setLayoutParams(layoutParamsForll);

            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.6f);
            CustomBoldTextView tvForEmpName = new CustomBoldTextView(EmployeeBranchTransferReportActivity.this);
            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
            tvForEmpName.setTextSize(15);
            tvForEmpName.setPadding(4, 4, 4, 4);
            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
            tvForEmpName.setBackground(ContextCompat.getDrawable(EmployeeBranchTransferReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForEmpName.setText(String.valueOf(employeeBranchTansferReportPojoList.get(l).getName_of_Employee()));

            llEmployeeBranchTRansferReportRow.addView(tvForEmpName);


            LinearLayout.LayoutParams tvEffectiveDateHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.1f);
            CustomBoldTextView tvEffectiveDate = new CustomBoldTextView(EmployeeBranchTransferReportActivity.this);
            tvEffectiveDate.setLayoutParams(tvEffectiveDateHeaderAndValueLayoutParam);
            tvEffectiveDate.setTextColor(getResources().getColor(R.color.black));
            tvEffectiveDate.setTextSize(15);
            tvEffectiveDate.setPadding(4, 4, 4, 4);
            tvEffectiveDate.setGravity(Gravity.CENTER_VERTICAL);
            tvEffectiveDate.setBackground(ContextCompat.getDrawable(EmployeeBranchTransferReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvEffectiveDate.setText(String.valueOf(employeeBranchTansferReportPojoList.get(l).getEffective_Date()));

            llEmployeeBranchTRansferReportRow.addView(tvEffectiveDate);


            LinearLayout.LayoutParams tvOLDPayrollBranchHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.5f);
            CustomBoldTextView tvOLDPayrollBranch = new CustomBoldTextView(EmployeeBranchTransferReportActivity.this);
            tvOLDPayrollBranch.setLayoutParams(tvOLDPayrollBranchHeaderAndValueLayoutParam);
            tvOLDPayrollBranch.setTextColor(getResources().getColor(R.color.black));
            tvOLDPayrollBranch.setTextSize(15);
            tvOLDPayrollBranch.setPadding(4, 4, 4, 4);
            tvOLDPayrollBranch.setGravity(Gravity.CENTER_VERTICAL);
            tvOLDPayrollBranch.setBackground(ContextCompat.getDrawable(EmployeeBranchTransferReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvOLDPayrollBranch.setText(String.valueOf(employeeBranchTansferReportPojoList.get(l).getOLD_Payroll_Branch()));

            llEmployeeBranchTRansferReportRow.addView(tvOLDPayrollBranch);


            LinearLayout.LayoutParams tvForNEWPayrollBranchHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.5f);
            CustomBoldTextView tvForNEWPayrollBranch = new CustomBoldTextView(EmployeeBranchTransferReportActivity.this);
            tvForNEWPayrollBranch.setLayoutParams(tvForNEWPayrollBranchHeaderAndValueLayoutParam);
            tvForNEWPayrollBranch.setTextColor(getResources().getColor(R.color.black));
            tvForNEWPayrollBranch.setTextSize(15);
            tvForNEWPayrollBranch.setPadding(4, 4, 4, 4);
            tvForNEWPayrollBranch.setGravity(Gravity.CENTER_VERTICAL);
            tvForNEWPayrollBranch.setBackground(ContextCompat.getDrawable(EmployeeBranchTransferReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForNEWPayrollBranch.setText(String.valueOf(employeeBranchTansferReportPojoList.get(l).getNEW_Payroll_Branch()));

            llEmployeeBranchTRansferReportRow.addView(tvForNEWPayrollBranch);

            LinearLayout.LayoutParams tvForEnterDateHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.3f);
            CustomBoldTextView tvForEnterDate = new CustomBoldTextView(EmployeeBranchTransferReportActivity.this);
            tvForEnterDate.setLayoutParams(tvForEnterDateHeaderAndValueLayoutParam);
            tvForEnterDate.setTextColor(getResources().getColor(R.color.black));
            tvForEnterDate.setTextSize(15);
            tvForEnterDate.setPadding(4, 4, 4, 4);
            tvForEnterDate.setGravity(Gravity.CENTER_VERTICAL);
            tvForEnterDate.setBackground(ContextCompat.getDrawable(EmployeeBranchTransferReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForEnterDate.setText(String.valueOf(employeeBranchTansferReportPojoList.get(l).getEnter_Date()));

            llEmployeeBranchTRansferReportRow.addView(tvForEnterDate);

            ll_employee_branch_transfer_report.addView(llEmployeeBranchTRansferReportRow);
        }

    }
}
