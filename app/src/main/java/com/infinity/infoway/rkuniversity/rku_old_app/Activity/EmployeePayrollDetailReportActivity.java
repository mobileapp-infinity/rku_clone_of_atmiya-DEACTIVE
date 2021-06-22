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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.EmployeePayrollDetailReportPojo;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class EmployeePayrollDetailReportActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<EmployeePayrollDetailReportPojo.DataBean> employeePayrollDetailReportPojoList;

    LinearLayout ll_employee_payroll_detail_value, ll_employee_payroll_detail_header;

    Spinner spBranchEmpPayrollDetailsReport, spDepartmentEmpPayrollDetailsReport;
    CustomBoldTextView tvLoadEmpPayrollDetailsReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    String Branch_id = "";
    String Departmet_id = "";
    LinearLayout llMainAcademicReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_payroll_detail_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getBranchApiCall();

        spBranchEmpPayrollDetailsReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Branch_id = hashMapBranchOpetionAndID.get(branchOpetions.get(position).trim()).toString();
                    getDepartmentApiCall(true, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(position))));
                } else {
                    Branch_id = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDepartmentEmpPayrollDetailsReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Departmet_id = hashMapDepartmentAndID.get(departmentOpetions.get(position).trim()).toString();
                } else {
                    Departmet_id = "";
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
        txt_act.setText("Employee Payroll Details Report");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_employee_payroll_detail_value = findViewById(R.id.ll_employee_payroll_detail_value);
        ll_employee_payroll_detail_header = findViewById(R.id.ll_employee_payroll_detail_header);

        spBranchEmpPayrollDetailsReport = findViewById(R.id.spBranchEmpPayrollDetailsReport);
        spDepartmentEmpPayrollDetailsReport = findViewById(R.id.spDepartmentEmpPayrollDetailsReport);
        tvLoadEmpPayrollDetailsReport = findViewById(R.id.tvLoadEmpPayrollDetailsReport);
        tvLoadEmpPayrollDetailsReport.setOnClickListener(this);

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
        }else if (view.getId() == R.id.tvLoadEmpPayrollDetailsReport){
            if (!TextUtils.isEmpty(Branch_id)) {
                if (!TextUtils.isEmpty(Departmet_id)) {
                    ll_employee_payroll_detail_value.removeAllViewsInLayout();

                    getEmployeePayrollDetailsReportApi(Branch_id, Departmet_id);
                } else {
                    Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getBranchApiCall() {
        DialogUtils.showProgressDialog(EmployeePayrollDetailReportActivity.this, "");

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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(EmployeePayrollDetailReportActivity.this, branchOpetions);
                            spBranchEmpPayrollDetailsReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        }else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeePayrollDetailReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeePayrollDetailReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeePayrollDetailReportActivity.this, "Please Try Again Later");
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
            DialogUtils.showProgressDialog(EmployeePayrollDetailReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(EmployeePayrollDetailReportActivity.this, departmentOpetions);
                            spDepartmentEmpPayrollDetailsReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(EmployeePayrollDetailReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeePayrollDetailReportActivity.this, "Please Try Again Later");
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


    private void getEmployeePayrollDetailsReportApi(final String Branch_id, final String Department_id) {
        DialogUtils.showProgressDialog(EmployeePayrollDetailReportActivity.this, "");

        StringRequest employeePayrollDerailReportRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_Payroll_Details_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        EmployeePayrollDetailReportPojo employeePayrollDetailReportPojo = gson.fromJson("{\"Data\":" + response + "}", EmployeePayrollDetailReportPojo.class);
                        if (employeePayrollDetailReportPojo.getData() != null && employeePayrollDetailReportPojo.getData().size() > 0) {
                            employeePayrollDetailReportPojoList = employeePayrollDetailReportPojo.getData();
                            ll_employee_payroll_detail_header.setVisibility(View.VISIBLE);
                            addRowDyanimically();
                            DialogUtils.hideProgressDialog();
                        } else {
                            ll_employee_payroll_detail_header.setVisibility(View.GONE);
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeePayrollDetailReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        ll_employee_payroll_detail_header.setVisibility(View.GONE);
                        DialogUtils.hideProgressDialog();
                        Toast.makeText(EmployeePayrollDetailReportActivity.this, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    ll_employee_payroll_detail_header.setVisibility(View.GONE);
                    Toast.makeText(EmployeePayrollDetailReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ll_employee_payroll_detail_header.setVisibility(View.GONE);
                DialogUtils.Show_Toast(EmployeePayrollDetailReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Branch_id", Branch_id);
                params2.put("Department_id", Department_id);
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


        for (int l = 0; l < employeePayrollDetailReportPojoList.size(); l++) {

            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3);

            LinearLayout llPayrollDetailsReportRow = new LinearLayout(EmployeePayrollDetailReportActivity.this);
            llPayrollDetailsReportRow.setOrientation(LinearLayout.HORIZONTAL);
            llPayrollDetailsReportRow.setLayoutParams(layoutParamsForll);

            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.3f);
            CustomBoldTextView tvForEmpName = new CustomBoldTextView(EmployeePayrollDetailReportActivity.this);
            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
            tvForEmpName.setTextSize(15);
            tvForEmpName.setPadding(4, 4, 4, 4);
            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
            tvForEmpName.setBackground(ContextCompat.getDrawable(EmployeePayrollDetailReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForEmpName.setText(String.valueOf(employeePayrollDetailReportPojoList.get(l).getName_of_Employee()));

            llPayrollDetailsReportRow.addView(tvForEmpName);


            LinearLayout.LayoutParams scaleHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            CustomBoldTextView tvForScale = new CustomBoldTextView(EmployeePayrollDetailReportActivity.this);
            tvForScale.setLayoutParams(scaleHeaderAndValueLayoutParam);
            tvForScale.setTextColor(getResources().getColor(R.color.black));
            tvForScale.setTextSize(15);
            tvForScale.setPadding(4, 4, 4, 4);
            tvForScale.setGravity(Gravity.CENTER_VERTICAL);
            tvForScale.setBackground(ContextCompat.getDrawable(EmployeePayrollDetailReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForScale.setText(String.valueOf(employeePayrollDetailReportPojoList.get(l).getScale()));

            llPayrollDetailsReportRow.addView(tvForScale);


            LinearLayout.LayoutParams basicHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 0.7f);
            CustomBoldTextView tvForBasic = new CustomBoldTextView(EmployeePayrollDetailReportActivity.this);
            tvForBasic.setLayoutParams(basicHeaderAndValueLayoutParam);
            tvForBasic.setTextColor(getResources().getColor(R.color.black));
            tvForBasic.setTextSize(15);
            tvForBasic.setPadding(4, 4, 4, 4);
            tvForBasic.setGravity(Gravity.CENTER_VERTICAL);
            tvForBasic.setBackground(ContextCompat.getDrawable(EmployeePayrollDetailReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForBasic.setText(String.valueOf(employeePayrollDetailReportPojoList.get(l).getBasic()));

            llPayrollDetailsReportRow.addView(tvForBasic);


            ll_employee_payroll_detail_value.addView(llPayrollDetailsReportRow);
        }

    }

}
