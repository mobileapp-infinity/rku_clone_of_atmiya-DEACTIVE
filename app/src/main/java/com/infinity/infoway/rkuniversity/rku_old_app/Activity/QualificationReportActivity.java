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
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.QualificationReportPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.models.QualificationBranchCodeCouontModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.QualificationNameBranchAndCountModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class QualificationReportActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;
    ImageView ivBack;
    List<QualificationReportPojo.DataBean> qualificationReportPojoList;

    ArrayList<String> qualificationBranchCode;
    ArrayList<String> qualificationType;


    LinearLayout ll_qualification_type, ll_qualification_header_and_value;

    ArrayList<QualificationBranchCodeCouontModel> qualificationBranchCodeCouontModelArrayList;
    ArrayList<QualificationNameBranchAndCountModel> qualificationNameBranchAndCountModelArrayList;

    Spinner spBranchQualificationReport, spDepartmentQualificationReport;
    CustomBoldTextView tvLoadQualificationReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;

    String Branch_id = "0";
    String Departmet_id = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getBranchApiCall();

        spBranchQualificationReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spDepartmentQualificationReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        marshMallowPermission = new MarshMallowPermission(QualificationReportActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Qualification Summary Report");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_qualification_type = findViewById(R.id.ll_qualification_type);
        ll_qualification_header_and_value = findViewById(R.id.ll_qualification_header_and_value);

        spBranchQualificationReport = findViewById(R.id.spBranchQualificationReport);
        spDepartmentQualificationReport = findViewById(R.id.spDepartmentQualificationReport);
        tvLoadQualificationReport = findViewById(R.id.tvLoadQualificationReport);
        tvLoadQualificationReport.setOnClickListener(this);

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
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (v.getId() == R.id.tvLoadQualificationReport) {
//            if (!TextUtils.isEmpty(Branch_id)) {
//                if (!TextUtils.isEmpty(Departmet_id)) {

                    try {
//                        ll_qualification_type.removeAllViewsInLayout();
//                        ll_qualification_header_and_value.removeAllViewsInLayout();
                        ll_qualification_type.removeAllViews();
                        ll_qualification_header_and_value.removeAllViews();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    getQualificationReport(Branch_id, Departmet_id, mySharedPreferecesRKUOLD.getUserID());
//                } else {
//                    Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    private void getBranchApiCall() {
        DialogUtils.showProgressDialog(QualificationReportActivity.this, "");

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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(QualificationReportActivity.this, branchOpetions);
                            spBranchQualificationReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        }else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(QualificationReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(QualificationReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(QualificationReportActivity.this, "Please Try Again Later");
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
            DialogUtils.showProgressDialog(QualificationReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(QualificationReportActivity.this, departmentOpetions);
                            spDepartmentQualificationReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(QualificationReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(QualificationReportActivity.this, "Please Try Again Later");
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

    private void getQualificationReport(final String Branch_id, final String Department_id,
                                        final String User_id) {
        DialogUtils.showProgressDialog(QualificationReportActivity.this, "");

        StringRequest academicContributionReportRequest = new StringRequest(Request.Method.POST, URLS.Get_Qualification_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        QualificationReportPojo qualificationReportPojo = gson.fromJson("{\"Data\":" + response + "}", QualificationReportPojo.class);
                        if (qualificationReportPojo.getData() != null && qualificationReportPojo.getData().size() > 0) {
                            qualificationReportPojoList = qualificationReportPojo.getData();

                            qualificationBranchCode = new ArrayList<>();
                            qualificationType = new ArrayList<>();
                            qualificationBranchCodeCouontModelArrayList = new ArrayList<>();
                            qualificationNameBranchAndCountModelArrayList = new ArrayList<>();

                            for (int i = 0; i < qualificationReportPojoList.size(); i++) {
                                if (!TextUtils.isEmpty(qualificationReportPojoList.get(i).getBrm_code())) {
                                    qualificationBranchCode.add(qualificationReportPojoList.get(i).getBrm_code());
                                }
//                                else {
//                                    qualificationBranchCode.add("-");
//                                }

                                if (!TextUtils.isEmpty(qualificationReportPojoList.get(i).getQualification())) {
                                    qualificationType.add(qualificationReportPojoList.get(i).getQualification());
                                }
//                                else {
//                                    qualificationType.add("-");
//                                }

                            }

                            qualificationBranchCode = new ArrayList<String>(new LinkedHashSet<String>(qualificationBranchCode));
                            qualificationType = new ArrayList<String>(new LinkedHashSet<String>(qualificationType));
                            qualificationType.add(0, "Qualifications");

                            for (int i = 0; i < qualificationType.size(); i++) {

                                LinearLayout.LayoutParams qualificationTypeLayoutParam = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                                CustomBoldTextView customBoldTextView = new CustomBoldTextView(QualificationReportActivity.this);
                                customBoldTextView.setLayoutParams(qualificationTypeLayoutParam);
                                customBoldTextView.setPadding(4, 4, 4, 4);
                                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                customBoldTextView.setTextSize(15);
                                customBoldTextView.setGravity(Gravity.LEFT);
                                customBoldTextView.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
                                        R.drawable.bg_header_ac_report));
                                customBoldTextView.setText(qualificationType.get(i));
                                ll_qualification_type.addView(customBoldTextView);
                            }

                            LinearLayout llQualificationHeaderAndValue = new LinearLayout(QualificationReportActivity.this);
                            llQualificationHeaderAndValue.setOrientation(LinearLayout.HORIZONTAL);
                            for (int i = 0; i < qualificationBranchCode.size(); i++) {
                                LinearLayout.LayoutParams qualificationHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                                        200, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                                CustomBoldTextView customBoldTextView = new CustomBoldTextView(QualificationReportActivity.this);
                                customBoldTextView.setLayoutParams(qualificationHeaderAndValueLayoutParam);
                                customBoldTextView.setPadding(4, 4, 4, 4);
                                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                customBoldTextView.setTextSize(15);
                                customBoldTextView.setLines(1);
                                customBoldTextView.setGravity(Gravity.CENTER);
                                customBoldTextView.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
                                        R.drawable.bg_header_ac_report));
                                customBoldTextView.setText(qualificationBranchCode.get(i));

                                llQualificationHeaderAndValue.addView(customBoldTextView);

                            }

                            ll_qualification_header_and_value.addView(llQualificationHeaderAndValue);


                            for (int i = 1; i < qualificationType.size(); i++) {

                                QualificationNameBranchAndCountModel qualificationNameBranchAndCountModel = new QualificationNameBranchAndCountModel();
                                HashMap<String, String> hashMapBranchAndValue = new HashMap<>();

                                qualificationNameBranchAndCountModel.setQualificationName(qualificationType.get(i));

                                for (int k = 0; k < qualificationReportPojoList.size(); k++) {
                                    if (qualificationType.get(i).trim().equalsIgnoreCase(qualificationReportPojoList.get(k).getQualification().trim())) {
                                        hashMapBranchAndValue.put(qualificationReportPojoList.get(k).getBrm_code(), String.valueOf(qualificationReportPojoList.get(k).getCount()));
                                    }
                                }

                                qualificationNameBranchAndCountModel.setHashMapBranchAndCount(hashMapBranchAndValue);
                                qualificationNameBranchAndCountModelArrayList.add(qualificationNameBranchAndCountModel);
                            }


                            for (int i = 0; i < qualificationNameBranchAndCountModelArrayList.size(); i++) {
                                QualificationBranchCodeCouontModel qualificationBranchCodeCouontModel = new QualificationBranchCodeCouontModel();
                                ArrayList<String> branchValue = new ArrayList<>();

                                for (int k = 0; k < qualificationBranchCode.size(); k++) {

                                    String branch = qualificationNameBranchAndCountModelArrayList.get(i).getHashMapBranchAndCount().get(qualificationBranchCode.get(k));

                                    if (!TextUtils.isEmpty(branch)) {
                                        branchValue.add(branch);
                                    } else {
                                        branchValue.add("-");
                                    }
                                    qualificationBranchCodeCouontModel.setBranchCodeArrayList(branchValue);
                                }
                                qualificationBranchCodeCouontModelArrayList.add(qualificationBranchCodeCouontModel);
                            }

                            drawHeaderAndFillValue();

                        } else {
                            Toast.makeText(QualificationReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(QualificationReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(QualificationReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
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
        academicContributionReportRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(academicContributionReportRequest);
    }

    private void drawHeaderAndFillValue() {

//        for (int i = 1; i < qualificationType.size(); i++) {
//
//            QualificationBranchCodeCouontModel qualificationBranchCodeCouontModel = new QualificationBranchCodeCouontModel();
//            ArrayList<String> branchCodeCountArrayList = new ArrayList<>();
//            String acTypeName = qualificationType.get(i);
//
//            for (int j = 0; j < qualificationReportPojoList.size(); j++) {
//                if (qualificationReportPojoList.get(j).getQualification().equalsIgnoreCase(acTypeName)) {
//                    if (!String.valueOf(qualificationReportPojoList.get(j).getCount()).equalsIgnoreCase("null") &&
//                            !String.valueOf(qualificationReportPojoList.get(j).getCount()).equalsIgnoreCase("")) {
//                        branchCodeCountArrayList.add(String.valueOf(qualificationReportPojoList.get(j).getCount()));
//                    } else {
//                        branchCodeCountArrayList.add("-");
//                    }
//                }
//            }
//            if (qualificationBranchCode.size() != branchCodeCountArrayList.size()) {
//                for (int k = branchCodeCountArrayList.size(); k <= qualificationBranchCode.size(); k++) {
//                    branchCodeCountArrayList.add("-");
//                }
//            }
//            qualificationBranchCodeCouontModel.setBranchCodeArrayList(branchCodeCountArrayList);
//            qualificationBranchCodeCouontModelArrayList.add(qualificationBranchCodeCouontModel);
//        }


        for (int l = 0; l < qualificationBranchCodeCouontModelArrayList.size(); l++) {

            LinearLayout llAcHeaderAndValue = new LinearLayout(QualificationReportActivity.this);
            llAcHeaderAndValue.setOrientation(LinearLayout.HORIZONTAL);

            for (int t = 0; t < qualificationBranchCodeCouontModelArrayList.get(l).getBranchCodeArrayList().size(); t++) {

                LinearLayout.LayoutParams acHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                        200, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                CustomBoldTextView customBoldTextView = new CustomBoldTextView(QualificationReportActivity.this);
                customBoldTextView.setLayoutParams(acHeaderAndValueLayoutParam);
                customBoldTextView.setPadding(4, 4, 4, 4);
                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                customBoldTextView.setTextSize(15);
                customBoldTextView.setLines(1);
                customBoldTextView.setGravity(Gravity.CENTER);
                customBoldTextView.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
                        R.drawable.bg_header_ac_report));
                customBoldTextView.setText(qualificationBranchCodeCouontModelArrayList.get(l).getBranchCodeArrayList().get(t));

                llAcHeaderAndValue.addView(customBoldTextView);

            }

            ll_qualification_header_and_value.addView(llAcHeaderAndValue);
        }

    }


}


//////////================================
//package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
//
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//
//import androidx.core.content.ContextCompat;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//import androidx.appcompat.widget.Toolbar;
//
//import android.text.TextUtils;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.gson.Gson;
//import SpinnerSimpleAdapter;
//import com.infinity.infoway.rkuniversity.App.MarshMallowPermission;
//import com.infinity.infoway.rkuniversity.CommonCls.CustomBoldTextView;
//import com.infinity.infoway.rkuniversity.CommonCls.DialogUtils;
//import com.infinity.infoway.rkuniversity.CommonCls.MySharedPrefereces;
//import com.infinity.infoway.rkuniversity.CommonCls.URLS;
//import com.infinity.infoway.rkuniversity.Pojo.QualificationReportPojo;
//import com.infinity.infoway.rkuniversity.R;
//import com.infinity.infoway.rkuniversity.models.AcTypeAndBranchCodeModel;
//import com.infinity.infoway.rkuniversity.models.AcademicContributionTypeBranchandCountModel;
//import com.infinity.infoway.rkuniversity.models.QualificationBranchCodeCouontModel;
//import com.infinity.infoway.rkuniversity.models.QualificationNameBranchAndCountModel;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Map;
//
//public class QualificationReportActivity extends AppCompatActivity implements View.OnClickListener {
//
//    MySharedPrefereces mySharedPrefereces;
//    CustomBoldTextView tv_emp_code, txt_act, tv_version;
//    RequestQueue queue;
//    MarshMallowPermission marshMallowPermission;
//    ImageView ivBack;
//    List<QualificationReportPojo.DataBean> qualificationReportPojoList;
//
////    ArrayList<String> qualificationBranchCode;
////    ArrayList<String> qualificationType;
//
//
//    LinearLayout ll_qualification_header, ll_qualification_report_value;
//
////    ArrayList<QualificationBranchCodeCouontModel> qualificationBranchCodeCouontModelArrayList;
////    ArrayList<QualificationNameBranchAndCountModel> qualificationNameBranchAndCountModelArrayList;
//
//    Spinner spBranchQualificationReport, spDepartmentQualificationReport;
//    CustomBoldTextView tvLoadQualificationReport;
//
//    ArrayList<String> branchOpetions;
//    HashMap<String, Integer> hashMapBranchOpetionAndID;
//
//    ArrayList<String> departmentOpetions;
//    HashMap<String, Integer> hashMapDepartmentAndID;
//
//    String Branch_id = "";
//    String Departmet_id = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qualification_report);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        initView();
//        getBranchApiCall();
//
//        spBranchQualificationReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position > 0) {
//                    Branch_id = hashMapBranchOpetionAndID.get(branchOpetions.get(position).trim()).toString();
//                    getDepartmentApiCall(true, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(position))));
//                } else {
//                    Branch_id = "";
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        spDepartmentQualificationReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position > 0) {
//                    Departmet_id = hashMapDepartmentAndID.get(departmentOpetions.get(position).trim()).toString();
//                } else {
//                    Departmet_id = "";
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//    }
//
//    private void initView() {
//        marshMallowPermission = new MarshMallowPermission(QualificationReportActivity.this);
//        mySharedPrefereces = new MySharedPrefereces(getApplicationContext());
//        tv_emp_code = findViewById(R.id.tv_emp_code);
//        tv_emp_code.setText(mySharedPrefereces.getEmpCode());
//
//        queue = Volley.newRequestQueue(this);
//
//        txt_act = findViewById(R.id.txt_act);
//        txt_act.setText("Qualification Report");
//
//        ivBack = findViewById(R.id.iv_back);
//        ivBack.setOnClickListener(this);
//
//        ll_qualification_header = findViewById(R.id.ll_qualification_header);
//        ll_qualification_report_value = findViewById(R.id.ll_qualification_report_value);
//
//        spBranchQualificationReport = findViewById(R.id.spBranchQualificationReport);
//        spDepartmentQualificationReport = findViewById(R.id.spDepartmentQualificationReport);
//        tvLoadQualificationReport = findViewById(R.id.tvLoadQualificationReport);
//        tvLoadQualificationReport.setOnClickListener(this);
//
//        PackageInfo pInfo = null;
//        assert pInfo != null;
//
//        try {
//            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
//        tv_version.setText(pInfo.versionName);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.iv_back) {
//            onBackPressed();
//        } else if (v.getId() == R.id.tvLoadQualificationReport) {
//            if (!TextUtils.isEmpty(Branch_id)) {
//                if (!TextUtils.isEmpty(Departmet_id)) {
//                    ll_qualification_report_value.removeAllViewsInLayout();
//                    getQualificationReport(Branch_id, Departmet_id);
//                } else {
//                    Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void getBranchApiCall() {
//        DialogUtils.showProgressDialog(QualificationReportActivity.this, "");
//
//        StringRequest getBranchRequest = new StringRequest(Request.Method.GET, URLS.Get_Branch, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                DialogUtils.hideProgressDialog();
//                if (!TextUtils.isEmpty(response)) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response);
//
//                        if (jsonArray != null && jsonArray.length() > 0) {
//                            branchOpetions = new ArrayList<>();
//                            branchOpetions.add("Select Branch");
//                            hashMapBranchOpetionAndID = new HashMap<>();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                hashMapBranchOpetionAndID.put(jsonArray.getJSONObject(i).getString("brm_name"),
//                                        jsonArray.getJSONObject(i).getInt("id"));
//                                branchOpetions.add(jsonArray.getJSONObject(i).getString("brm_name"));
//                            }
//                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(QualificationReportActivity.this, branchOpetions);
//                            spBranchQualificationReport.setAdapter(branchOpetionAdapter);
//
//                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Toast.makeText(QualificationReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                DialogUtils.Show_Toast(QualificationReportActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
//                System.out.println("errorrrrrrrrrr " + error);
//                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
//            }
//        });
//        getBranchRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(getBranchRequest);
//    }
//
//    private void getDepartmentApiCall(boolean isPdShow, final boolean isPdHide, final String branch_id) {
//
//        if (isPdShow) {
//            DialogUtils.showProgressDialog(QualificationReportActivity.this, "");
//        }
//        StringRequest leaveBalanceRequest = new StringRequest(Request.Method.POST, URLS.Get_Department, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (isPdHide) {
//                    DialogUtils.hideProgressDialog();
//                }
//                if (!TextUtils.isEmpty(response)) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response);
//
//                        if (jsonArray != null && jsonArray.length() > 0) {
//                            departmentOpetions = new ArrayList<>();
//                            departmentOpetions.add("Select Department");
//                            hashMapDepartmentAndID = new HashMap<>();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                hashMapDepartmentAndID.put(jsonArray.getJSONObject(i).getString("dep_name"),
//                                        jsonArray.getJSONObject(i).getInt("id"));
//                                departmentOpetions.add(jsonArray.getJSONObject(i).getString("dep_name"));
//                            }
//                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(QualificationReportActivity.this, departmentOpetions);
//                            spDepartmentQualificationReport.setAdapter(departmentOpetionAdapter);
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    DialogUtils.Show_Toast(QualificationReportActivity.this, response);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                DialogUtils.Show_Toast(QualificationReportActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
//                System.out.println("errorrrrrrrrrr " + error);
//                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params2 = new Hashtable<>();
//                params2.put("branch_id", branch_id);
//                return params2;
//            }
//
//            @Override
//            public String getBodyContentType() {
////                return "application/json; charset=UTF-8";
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//        };
//        leaveBalanceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(leaveBalanceRequest);
//    }
//
//    private void getQualificationReport(final String Branch_id, final String Department_id) {
//        DialogUtils.showProgressDialog(QualificationReportActivity.this, "");
//
//        StringRequest academicContributionReportRequest = new StringRequest(Request.Method.POST, URLS.Get_Qualification_Report, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
//                if (!TextUtils.isEmpty(response)) {
//                    try {
//                        Gson gson = new Gson();
//                        QualificationReportPojo qualificationReportPojo = gson.fromJson("{\"Data\":" + response + "}", QualificationReportPojo.class);
//                        if (qualificationReportPojo.getData() != null && qualificationReportPojo.getData().size() > 0) {
//                            qualificationReportPojoList = qualificationReportPojo.getData();
//                            ll_qualification_header.setVisibility(View.VISIBLE);
//                            addRowDyanimically(qualificationReportPojoList);
//                        } else {
//                            ll_qualification_header.setVisibility(View.GONE);
//                            Toast.makeText(QualificationReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (Exception e) {
//                        ll_qualification_header.setVisibility(View.GONE);
//                        Toast.makeText(QualificationReportActivity.this, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//                } else {
//                    ll_qualification_header.setVisibility(View.GONE);
//                    Toast.makeText(QualificationReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                DialogUtils.Show_Toast(QualificationReportActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
//                System.out.println("errorrrrrrrrrr " + error);
//                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params2 = new Hashtable<>();
//                params2.put("Branch_id", Branch_id);
//                params2.put("Department_id", Department_id);
//                return params2;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                //                return "application/json; charset=UTF-8";
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//        };
//        academicContributionReportRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(academicContributionReportRequest);
//    }
//
//    private void addRowDyanimically(List<QualificationReportPojo.DataBean> qualificationReportPojoList) {
//
//        for (int l = 0; l < qualificationReportPojoList.size(); l++) {
//
//            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT, 20f);
//
//            LinearLayout llQualificationReportRow = new LinearLayout(QualificationReportActivity.this);
//            llQualificationReportRow.setOrientation(LinearLayout.HORIZONTAL);
//            llQualificationReportRow.setLayoutParams(layoutParamsForll);
//
//            LinearLayout.LayoutParams qualificationParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 6.0f);
//            CustomBoldTextView tvForQualification = new CustomBoldTextView(QualificationReportActivity.this);
//            tvForQualification.setLayoutParams(qualificationParam);
//            tvForQualification.setTextColor(getResources().getColor(R.color.black));
//            tvForQualification.setTextSize(15);
//            tvForQualification.setPadding(6, 6, 6, 6);
//            tvForQualification.setGravity(Gravity.CENTER);
//            tvForQualification.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            tvForQualification.setText(String.valueOf(qualificationReportPojoList.get(l).getQualification()));
//
//            llQualificationReportRow.addView(tvForQualification);
//
//
//            LinearLayout.LayoutParams sasLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
//            CustomBoldTextView tvSAS = new CustomBoldTextView(QualificationReportActivity.this);
//            tvSAS.setLayoutParams(sasLayoutParam);
//            tvSAS.setTextColor(getResources().getColor(R.color.black));
//            tvSAS.setTextSize(15);
//            tvSAS.setPadding(6, 6, 6, 6);
//            tvSAS.setGravity(Gravity.CENTER);
//            tvSAS.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            if (String.valueOf(qualificationReportPojoList.get(l).getSAS()).equalsIgnoreCase("null")||
//                    String.valueOf(qualificationReportPojoList.get(l).getSAS()).isEmpty()) {
//                tvSAS.setText("-");
//            } else {
//                tvSAS.setText(String.valueOf(qualificationReportPojoList.get(l).getSAS()));
//            }
//
//            llQualificationReportRow.addView(tvSAS);
//
//
//            LinearLayout.LayoutParams sdsLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
//            CustomBoldTextView tvSDS = new CustomBoldTextView(QualificationReportActivity.this);
//            tvSDS.setLayoutParams(sdsLayoutParam);
//            tvSDS.setTextColor(getResources().getColor(R.color.black));
//            tvSDS.setTextSize(15);
//            tvSDS.setPadding(6, 6, 6, 6);
//            tvSDS.setGravity(Gravity.CENTER);
//            tvSDS.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            if (String.valueOf(qualificationReportPojoList.get(l).getSDS()).equalsIgnoreCase("null") ||
//                    String.valueOf(qualificationReportPojoList.get(l).getSDS()).isEmpty()){
//                tvSDS.setText("-");
//            }else {
//                tvSDS.setText(String.valueOf(qualificationReportPojoList.get(l).getSDS()));
//            }
//
//            llQualificationReportRow.addView(tvSDS);
//
//
//            LinearLayout.LayoutParams soeLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
//            CustomBoldTextView tvSOE = new CustomBoldTextView(QualificationReportActivity.this);
//            tvSOE.setLayoutParams(soeLayoutParam);
//            tvSOE.setTextColor(getResources().getColor(R.color.black));
//            tvSOE.setTextSize(15);
//            tvSOE.setPadding(6, 6, 6, 6);
//            tvSOE.setGravity(Gravity.CENTER);
//            tvSOE.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            if (String.valueOf(qualificationReportPojoList.get(l).getSOE()).equalsIgnoreCase("null") ||
//                    String.valueOf(qualificationReportPojoList.get(l).getSOE()).isEmpty()){
//                tvSOE.setText("-");
//            }else{
//                tvSOE.setText(String.valueOf(qualificationReportPojoList.get(l).getSOE()));
//            }
//
//            llQualificationReportRow.addView(tvSOE);
//
//
//            LinearLayout.LayoutParams somLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
//            CustomBoldTextView tvSOM = new CustomBoldTextView(QualificationReportActivity.this);
//            tvSOM.setLayoutParams(somLayoutParam);
//            tvSOM.setTextColor(getResources().getColor(R.color.black));
//            tvSOM.setTextSize(15);
//            tvSOM.setPadding(6, 6, 6, 6);
//            tvSOM.setGravity(Gravity.CENTER);
//            tvSOM.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            if (String.valueOf(qualificationReportPojoList.get(l).getSOM()).equalsIgnoreCase("null") ||
//                    String.valueOf(qualificationReportPojoList.get(l).getSOM()).isEmpty()){
//                tvSOM.setText("-");
//            }else {
//                tvSOM.setText(String.valueOf(qualificationReportPojoList.get(l).getSOM()));
//            }
//
//            llQualificationReportRow.addView(tvSOM);
//
//            LinearLayout.LayoutParams sopLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
//            CustomBoldTextView tvSOP = new CustomBoldTextView(QualificationReportActivity.this);
//            tvSOP.setLayoutParams(sopLayoutParam );
//            tvSOP.setTextColor(getResources().getColor(R.color.black));
//            tvSOP.setTextSize(15);
//            tvSOP.setPadding(6, 6, 6, 6);
//            tvSOP.setGravity(Gravity.CENTER);
//            tvSOP.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            if (String.valueOf(qualificationReportPojoList.get(l).getSOP()).equalsIgnoreCase("null") ||
//                    String.valueOf(qualificationReportPojoList.get(l).getSOP()).isEmpty()){
//                tvSOP.setText("-");
//            }else {
//                tvSOP.setText(String.valueOf(qualificationReportPojoList.get(l).getSOP()));
//            }
//
//            llQualificationReportRow.addView(tvSOP);
//
//
//
//            LinearLayout.LayoutParams sosLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
//            CustomBoldTextView tvSOS = new CustomBoldTextView(QualificationReportActivity.this);
//            tvSOS.setLayoutParams(sosLayoutParam);
//            tvSOS.setTextColor(getResources().getColor(R.color.black));
//            tvSOS.setTextSize(15);
//            tvSOS.setPadding(6, 6, 6, 6);
//            tvSOS.setGravity(Gravity.CENTER);
//            tvSOS.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            if (String.valueOf(qualificationReportPojoList.get(l).getSOS()).equalsIgnoreCase("null") ||
//                    String.valueOf(qualificationReportPojoList.get(l).getSOS()).isEmpty()){
//                tvSOS.setText("-");
//            }else {
//                tvSOS.setText(String.valueOf(qualificationReportPojoList.get(l).getSOS()));
//            }
//
//            llQualificationReportRow.addView(tvSOS);
//
//
//
//            LinearLayout.LayoutParams sptLayoutParam = new LinearLayout.LayoutParams(
//                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
//            CustomBoldTextView tvSPT = new CustomBoldTextView(QualificationReportActivity.this);
//            tvSPT.setLayoutParams(sptLayoutParam);
//            tvSPT.setTextColor(getResources().getColor(R.color.black));
//            tvSPT.setTextSize(15);
//            tvSPT.setPadding(6, 6, 6, 6);
//            tvSPT.setGravity(Gravity.CENTER);
//            tvSPT.setBackground(ContextCompat.getDrawable(QualificationReportActivity.this,
//                    R.drawable.bg_header_ac_report));
//            if (String.valueOf(qualificationReportPojoList.get(l).getSPT()).equalsIgnoreCase("null") ||
//                    String.valueOf(qualificationReportPojoList.get(l).getSPT()).isEmpty()){
//                tvSPT.setText("-");
//            }else {
//                tvSPT.setText(String.valueOf(qualificationReportPojoList.get(l).getSPT()));
//            }
//
//            llQualificationReportRow.addView(tvSPT);
//
//            ll_qualification_report_value.addView(llQualificationReportRow);
//        }
//
//    }
//
//}