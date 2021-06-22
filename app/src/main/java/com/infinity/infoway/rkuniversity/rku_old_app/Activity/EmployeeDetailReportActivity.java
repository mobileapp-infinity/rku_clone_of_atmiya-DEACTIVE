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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.EmployeeDetailReportPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.models.EmployeeDetailCountModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.EmployeeDetailStatusBranchAndCountModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class EmployeeDetailReportActivity extends AppCompatActivity implements View.OnClickListener{

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;
    ImageView ivBack;

    List<EmployeeDetailReportPojo.DataBean> employeeDetailsReportPojoList;


    ArrayList<String> employeeDetailBranch;
    ArrayList<String> employeeDetailType;


    LinearLayout ll_employee_detail_type, ll_employee_detail_header_and_value;

    ArrayList<EmployeeDetailCountModel> employeeDetailCountModelArrayList;
    ArrayList<EmployeeDetailStatusBranchAndCountModel> employeeDetailStatusBranchAndCountModelArrayList;

    Spinner spBranchEmpDetailsReport, spDepartmentEmpDetailsReport;
    CustomBoldTextView tvLoadEmpDetailsReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    String Branch_id = "0";
    String Departmet_id = "0";
    LinearLayout llMainAcademicReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getBranchApiCall();

        spBranchEmpDetailsReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spDepartmentEmpDetailsReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        marshMallowPermission = new MarshMallowPermission(EmployeeDetailReportActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Employee Details Report");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_employee_detail_type = findViewById(R.id.ll_employee_detail_type);
        ll_employee_detail_header_and_value = findViewById(R.id.ll_employee_detail_header_and_value);

        spBranchEmpDetailsReport = findViewById(R.id.spBranchEmpDetailsReport);
        spDepartmentEmpDetailsReport = findViewById(R.id.spDepartmentEmpDetailsReport);
        tvLoadEmpDetailsReport = findViewById(R.id.tvLoadEmpDetailsReport);
        tvLoadEmpDetailsReport.setOnClickListener(this);

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
        }else if (v.getId() == R.id.tvLoadEmpDetailsReport){
//            if (!TextUtils.isEmpty(Branch_id)) {
//                if (!TextUtils.isEmpty(Departmet_id)) {

                    try {
//                        ll_employee_detail_type.removeAllViewsInLayout();
//                        ll_employee_detail_header_and_value.removeAllViewsInLayout();
                        ll_employee_detail_type.removeAllViews();
                        ll_employee_detail_header_and_value.removeAllViews();

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    getEmployeeDetailsReport(Branch_id, Departmet_id, mySharedPreferecesRKUOLD.getUserID());
//                } else {
//                    Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    private void getBranchApiCall() {
        DialogUtils.showProgressDialog(EmployeeDetailReportActivity.this, "");

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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(EmployeeDetailReportActivity.this, branchOpetions);
                            spBranchEmpDetailsReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        }else{
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeDetailReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeDetailReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeDetailReportActivity.this, "Please Try Again Later");
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
            DialogUtils.showProgressDialog(EmployeeDetailReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(EmployeeDetailReportActivity.this, departmentOpetions);
                            spDepartmentEmpDetailsReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(EmployeeDetailReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeDetailReportActivity.this, "Please Try Again Later");
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

    private void getEmployeeDetailsReport(final String Branch_id, final String Departmet_id,
                                          final String user_id) {
        DialogUtils.showProgressDialog(EmployeeDetailReportActivity.this, "");

        StringRequest academicContributionReportRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_Detail_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        EmployeeDetailReportPojo employeeDetailReportPojo = gson.fromJson("{\"Data\":" + response + "}", EmployeeDetailReportPojo.class);
                        if (employeeDetailReportPojo.getData() != null && employeeDetailReportPojo.getData().size() > 0) {
                            employeeDetailsReportPojoList = employeeDetailReportPojo.getData();

                            employeeDetailBranch = new ArrayList<>();
                            employeeDetailType = new ArrayList<>();
                            employeeDetailCountModelArrayList = new ArrayList<>();
                            employeeDetailStatusBranchAndCountModelArrayList = new ArrayList<>();

                            for (int i = 0; i < employeeDetailsReportPojoList.size(); i++) {
                                if (!TextUtils.isEmpty(employeeDetailsReportPojoList.get(i).getBrm_code())) {
                                    employeeDetailBranch.add(employeeDetailsReportPojoList.get(i).getBrm_code());
                                }
//                                else {
//                                    employeeDetailBranch.add("-");
//                                }

                                if (!TextUtils.isEmpty(employeeDetailsReportPojoList.get(i).getEmployee_details())) {
                                    employeeDetailType.add(employeeDetailsReportPojoList.get(i).getEmployee_details());
                                }
//                                else {
//                                    employeeDetailType.add("-");
//                                }
                            }

                            employeeDetailBranch = new ArrayList<String>(new LinkedHashSet<String>(employeeDetailBranch));
                            employeeDetailType = new ArrayList<String>(new LinkedHashSet<String>(employeeDetailType));
                            employeeDetailType.add(0, "  ");

                            for (int i = 0; i < employeeDetailType.size(); i++) {

                                LinearLayout.LayoutParams acTypeLayoutParam = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                                CustomBoldTextView customBoldTextView = new CustomBoldTextView(EmployeeDetailReportActivity.this);
                                customBoldTextView.setLayoutParams(acTypeLayoutParam);
                                customBoldTextView.setPadding(4, 4, 4, 4);
                                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                customBoldTextView.setTextSize(15);
                                customBoldTextView.setGravity(Gravity.LEFT);
                                customBoldTextView.setBackground(ContextCompat.getDrawable(EmployeeDetailReportActivity.this,
                                        R.drawable.bg_header_ac_report));
                                customBoldTextView.setText(employeeDetailType.get(i));
                                ll_employee_detail_type.addView(customBoldTextView);
                            }

                            LinearLayout llAcHeaderAndValue = new LinearLayout(EmployeeDetailReportActivity.this);
                            llAcHeaderAndValue.setOrientation(LinearLayout.HORIZONTAL);
                            for (int i = 0; i < employeeDetailBranch.size(); i++) {
                                LinearLayout.LayoutParams acHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                                        130, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                                CustomBoldTextView customBoldTextView = new CustomBoldTextView(EmployeeDetailReportActivity.this);
                                customBoldTextView.setLayoutParams(acHeaderAndValueLayoutParam);
                                customBoldTextView.setPadding(4, 4, 4, 4);
                                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                customBoldTextView.setTextSize(15);
                                customBoldTextView.setLines(1);
                                customBoldTextView.setGravity(Gravity.CENTER);
                                customBoldTextView.setBackground(ContextCompat.getDrawable(EmployeeDetailReportActivity.this,
                                        R.drawable.bg_header_ac_report));
                                customBoldTextView.setText(employeeDetailBranch.get(i));

                                llAcHeaderAndValue.addView(customBoldTextView);

                            }

                            ll_employee_detail_header_and_value.addView(llAcHeaderAndValue);


                            for (int i = 1; i < employeeDetailType.size(); i++) {

                                EmployeeDetailStatusBranchAndCountModel employeeDetailStatusBranchAndCountModel = new EmployeeDetailStatusBranchAndCountModel();
                                HashMap<String, String> hashMapBranchAndValue = new HashMap<>();

                                employeeDetailStatusBranchAndCountModel.setEmpDetailStatus(employeeDetailType.get(i));

                                for (int k = 0; k < employeeDetailsReportPojoList.size(); k++) {
                                    if (employeeDetailType.get(i).trim().equalsIgnoreCase(employeeDetailsReportPojoList.get(k).getEmployee_details().trim())) {
                                        hashMapBranchAndValue.put(employeeDetailsReportPojoList.get(k).getBrm_code(), String.valueOf(employeeDetailsReportPojoList.get(k).getCount()));
                                    }
                                }

                                employeeDetailStatusBranchAndCountModel.setHashMapBranchAndCount(hashMapBranchAndValue);
                                employeeDetailStatusBranchAndCountModelArrayList.add(employeeDetailStatusBranchAndCountModel);
                            }


                            for (int i = 0; i < employeeDetailStatusBranchAndCountModelArrayList.size(); i++) {
                                EmployeeDetailCountModel employeeDetailCountModel = new EmployeeDetailCountModel();
                                ArrayList<String> branchValue = new ArrayList<>();

                                for (int k = 0; k < employeeDetailBranch.size(); k++) {

                                    String branch = employeeDetailStatusBranchAndCountModelArrayList.get(i).getHashMapBranchAndCount().get(employeeDetailBranch.get(k));

                                    if (!TextUtils.isEmpty(branch)) {
                                        branchValue.add(branch);
                                    } else {
                                        branchValue.add("-");
                                    }
                                    employeeDetailCountModel.setEmployeeDetailCountArrayList(branchValue);
                                }
                                employeeDetailCountModelArrayList.add(employeeDetailCountModel);
                            }

                            drawHeaderAndFillValue();

                        } else {
                            Toast.makeText(EmployeeDetailReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeDetailReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeDetailReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Branch_id", Branch_id);
                params2.put("Departmet_id", Departmet_id);
                params2.put("user_id", user_id);
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

//        for (int i = 1; i < employeeDetailType.size(); i++) {
//
//            EmployeeDetailCountModel employeeDetailCountModel = new EmployeeDetailCountModel();
//            ArrayList<String> branchCodeCountArrayList = new ArrayList<>();
//            String acTypeName = employeeDetailType.get(i);
//
//            for (int j = 0; j < employeeDetailsReportPojoList.size(); j++) {
//                if (employeeDetailsReportPojoList.get(j).getEmployee_details().equalsIgnoreCase(acTypeName)) {
//                    if (!String.valueOf(employeeDetailsReportPojoList.get(j).getCount()).equalsIgnoreCase("null") &&
//                            !String.valueOf(employeeDetailsReportPojoList.get(j).getCount()).equalsIgnoreCase("")) {
//                        branchCodeCountArrayList.add(String.valueOf(employeeDetailsReportPojoList.get(j).getCount()));
//                    } else {
//                        branchCodeCountArrayList.add("-");
//                    }
//                }
//            }
//            if (employeeDetailBranch.size() != branchCodeCountArrayList.size()) {
//                for (int k = branchCodeCountArrayList.size(); k <= employeeDetailBranch.size(); k++) {
//                    branchCodeCountArrayList.add("-");
//                }
//            }
//            employeeDetailCountModel.setEmployeeDetailCountArrayList(branchCodeCountArrayList);
//            employeeDetailCountModelArrayList.add(employeeDetailCountModel);
//        }


        for (int l = 0; l < employeeDetailCountModelArrayList.size(); l++) {

            LinearLayout llAcHeaderAndValue = new LinearLayout(EmployeeDetailReportActivity.this);
            llAcHeaderAndValue.setOrientation(LinearLayout.HORIZONTAL);

            for (int t = 0; t < employeeDetailCountModelArrayList.get(l).getEmployeeDetailCountArrayList().size(); t++) {

                LinearLayout.LayoutParams acHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                        130, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                CustomBoldTextView customBoldTextView = new CustomBoldTextView(EmployeeDetailReportActivity.this);
                customBoldTextView.setLayoutParams(acHeaderAndValueLayoutParam);
                customBoldTextView.setPadding(4, 4, 4, 4);
                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                customBoldTextView.setTextSize(15);
                customBoldTextView.setLines(1);
                customBoldTextView.setGravity(Gravity.CENTER);
                customBoldTextView.setBackground(ContextCompat.getDrawable(EmployeeDetailReportActivity.this,
                        R.drawable.bg_header_ac_report));
                customBoldTextView.setText(employeeDetailCountModelArrayList.get(l).getEmployeeDetailCountArrayList().get(t));

                llAcHeaderAndValue.addView(customBoldTextView);

            }

            ll_employee_detail_header_and_value.addView(llAcHeaderAndValue);
        }

    }



}
