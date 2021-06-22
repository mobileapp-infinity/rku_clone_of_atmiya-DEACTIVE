package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.AcademicContributionReportPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.models.AcTypeAndBranchCodeModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.AcademicContributionTypeBranchandCountModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class AcademicContributionReportActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;
    ImageView ivBack;
    List<AcademicContributionReportPojo.DataBean> academicContributionReportList;

    ArrayList<String> acBranchCode;
    ArrayList<String> acType;

    LinearLayout ll_ac_typ, ll_ac_header_and_value;

    ArrayList<AcTypeAndBranchCodeModel> acTypeAndBranchCodeModelArrayList;
    ArrayList<AcademicContributionTypeBranchandCountModel> academicContributionTypeBranchandCountModelsArrayList;


    Spinner spBranchAcademicContributionReport, spDepartmentAcademicContributionReport;
    CustomBoldTextView tvLoadAcademicContributionReport;

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
        setContentView(R.layout.activity_academic_contribution_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getBranchApiCall();

        spBranchAcademicContributionReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spDepartmentAcademicContributionReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        marshMallowPermission = new MarshMallowPermission(AcademicContributionReportActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Academic Contribution Summary Report");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_ac_typ = findViewById(R.id.ll_ac_typ);
        ll_ac_header_and_value = findViewById(R.id.ll_ac_header_and_value);

        spBranchAcademicContributionReport = findViewById(R.id.spBranchAcademicContributionReport);
        spDepartmentAcademicContributionReport = findViewById(R.id.spDepartmentAcademicContributionReport);

        tvLoadAcademicContributionReport = findViewById(R.id.tvLoadAcademicContributionReport);
        tvLoadAcademicContributionReport.setOnClickListener(this);

        llMainAcademicReport = findViewById(R.id.llMainAcademicReport);

        PackageInfo pInfo = null;
        assert pInfo != null;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tv_version = findViewById(R.id.tv_version);
        tv_version.setText(pInfo.versionName);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (v.getId() == R.id.tvLoadAcademicContributionReport) {
//            if (!TextUtils.isEmpty(Branch_id)) {
//                if (!TextUtils.isEmpty(Departmet_id)) {
            try {
//                        ll_ac_typ.removeAllViewsInLayout();
//                        ll_ac_header_and_value.removeAllViewsInLayout();
                ll_ac_typ.removeAllViews();
                ll_ac_header_and_value.removeAllViews();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            getAcademicContributionReport(Branch_id, Departmet_id, mySharedPreferecesRKUOLD.getUserID());
//                } else {
//                    Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Please select branch", Toast.LENGTH_SHORT).show();
//            }
        }
    }


    private void getBranchApiCall() {
        DialogUtils.showProgressDialog(AcademicContributionReportActivity.this, "");

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
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(AcademicContributionReportActivity.this, branchOpetions);
                            spBranchAcademicContributionReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(AcademicContributionReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AcademicContributionReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(AcademicContributionReportActivity.this, "Please Try Again Later");
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
            DialogUtils.showProgressDialog(AcademicContributionReportActivity.this, "");
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
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(AcademicContributionReportActivity.this, departmentOpetions);
                            spDepartmentAcademicContributionReport.setAdapter(departmentOpetionAdapter);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(AcademicContributionReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(AcademicContributionReportActivity.this, "Please Try Again Later");
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


    private void getAcademicContributionReport(final String Branch_id, final String Departmet_id,
                                               final String User_id) {
        DialogUtils.showProgressDialog(AcademicContributionReportActivity.this, "");

        StringRequest academicContributionReportRequest = new StringRequest(Request.Method.POST, URLS.Get_Academic_Contributon_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        AcademicContributionReportPojo academicContributionReportPojo = gson.fromJson("{\"Data\":" + response + "}", AcademicContributionReportPojo.class);
                        if (academicContributionReportPojo.getData() != null && academicContributionReportPojo.getData().size() > 0) {

                            acTypeAndBranchCodeModelArrayList = new ArrayList<>();
                            academicContributionTypeBranchandCountModelsArrayList = new ArrayList<>();
                            acBranchCode = new ArrayList<>();
                            acType = new ArrayList<>();
                            academicContributionReportList = academicContributionReportPojo.getData();

                            for (int i = 0; i < academicContributionReportList.size(); i++) {
                                if (!TextUtils.isEmpty(academicContributionReportList.get(i).getBrm_code())) {
                                    acBranchCode.add(academicContributionReportList.get(i).getBrm_code());
                                }
//                                else {
//                                    acBranchCode.add("-");
//                                }

                                if (!TextUtils.isEmpty(academicContributionReportList.get(i).getAcademic_Contribution())) {
                                    acType.add(academicContributionReportList.get(i).getAcademic_Contribution());
                                } else {
                                    acType.add("-");
                                }
                            }

                            acBranchCode = new ArrayList<String>(new LinkedHashSet<String>(acBranchCode));
                            acType = new ArrayList<String>(new LinkedHashSet<String>(acType));
                            acType.add(0, "Academic Contribution Type");

                            try {

                                for (int i = 0; i < acType.size(); i++) {

                                    LinearLayout.LayoutParams acTypeLayoutParam = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                                    CustomBoldTextView customBoldTextView = new CustomBoldTextView(AcademicContributionReportActivity.this);
                                    customBoldTextView.setLayoutParams(acTypeLayoutParam);
                                    customBoldTextView.setPadding(4, 4, 4, 4);
                                    customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                    customBoldTextView.setTextSize(15);
                                    customBoldTextView.setGravity(Gravity.CENTER_VERTICAL);
                                    customBoldTextView.setBackground(ContextCompat.getDrawable(AcademicContributionReportActivity.this,
                                            R.drawable.bg_header_ac_report));
                                    customBoldTextView.setText(acType.get(i));
                                    ll_ac_typ.addView(customBoldTextView);
                                }

                                LinearLayout llAcHeaderAndValue = new LinearLayout(AcademicContributionReportActivity.this);
                                llAcHeaderAndValue.setOrientation(LinearLayout.HORIZONTAL);
                                for (int i = 0; i < acBranchCode.size(); i++) {
                                    LinearLayout.LayoutParams acHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                                            130, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                                    CustomBoldTextView customBoldTextView = new CustomBoldTextView(AcademicContributionReportActivity.this);
                                    customBoldTextView.setLayoutParams(acHeaderAndValueLayoutParam);
                                    customBoldTextView.setPadding(4, 4, 4, 4);
                                    customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                    customBoldTextView.setTextSize(15);
                                    customBoldTextView.setLines(1);
                                    customBoldTextView.setGravity(Gravity.CENTER);
                                    customBoldTextView.setBackground(ContextCompat.getDrawable(AcademicContributionReportActivity.this,
                                            R.drawable.bg_header_ac_report));
                                    customBoldTextView.setText(acBranchCode.get(i));

                                    llAcHeaderAndValue.addView(customBoldTextView);
                                }

                                ll_ac_header_and_value.addView(llAcHeaderAndValue);


                                for (int i = 1; i < acType.size(); i++) {

                                    AcademicContributionTypeBranchandCountModel academicContributionTypeBranchandCountModel = new AcademicContributionTypeBranchandCountModel();
                                    HashMap<String, String> hashMapBranchAndValue = new HashMap<>();

                                    academicContributionTypeBranchandCountModel.setAcTypeName(acType.get(i));

                                    for (int k = 0; k < academicContributionReportList.size(); k++) {
                                        if (acType.get(i).trim().equalsIgnoreCase(academicContributionReportList.get(k).getAcademic_Contribution().trim())) {
                                            hashMapBranchAndValue.put(academicContributionReportList.get(k).getBrm_code(), String.valueOf(academicContributionReportList.get(k).getCount()));
                                        }
                                    }

                                    academicContributionTypeBranchandCountModel.setHashMapBranchAndCount(hashMapBranchAndValue);
                                    academicContributionTypeBranchandCountModelsArrayList.add(academicContributionTypeBranchandCountModel);
                                }


                                for (int i = 0; i < academicContributionTypeBranchandCountModelsArrayList.size(); i++) {
                                    AcTypeAndBranchCodeModel acTypeAndBranchCodeModel = new AcTypeAndBranchCodeModel();
                                    ArrayList<String> branchValue = new ArrayList<>();

                                    for (int k = 0; k < acBranchCode.size(); k++) {

                                        String branch = academicContributionTypeBranchandCountModelsArrayList.get(i).getHashMapBranchAndCount().get(acBranchCode.get(k));

                                        if (!TextUtils.isEmpty(branch)) {
                                            branchValue.add(branch);
                                        } else {
                                            branchValue.add("-");
                                        }
                                        acTypeAndBranchCodeModel.setBranchCodeArrayList(branchValue);
                                    }
                                    acTypeAndBranchCodeModelArrayList.add(acTypeAndBranchCodeModel);
                                }

                                drawHeaderAndFillValue();
                            } catch (Exception ex) {
                                Log.d("EXCEPTIONNNNNNN", ex.getMessage());
                            }
                        } else {
                            Toast.makeText(AcademicContributionReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(AcademicContributionReportActivity.this, "Exception:-" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AcademicContributionReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(AcademicContributionReportActivity.this, "Please Try Again Later");
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

//        for (int i = 1; i < acType.size(); i++) {
//
//            AcTypeAndBranchCodeModel acTypeAndBranchCodeModel = new AcTypeAndBranchCodeModel();
//            ArrayList<String> branchCodeCountArrayList = new ArrayList<>();
//            String acTypeName = acType.get(i);
//
//            for (int j = 0; j < academicContributionReportList.size(); j++) {
//                if (academicContributionReportList.get(j).getAcademic_Contribution().equalsIgnoreCase(acTypeName)) {
//                    if (!String.valueOf(academicContributionReportList.get(j).getCount()).equalsIgnoreCase("null") &&
//                            !String.valueOf(academicContributionReportList.get(j).getCount()).equalsIgnoreCase("")) {
//                        branchCodeCountArrayList.add(String.valueOf(academicContributionReportList.get(j).getCount()));
//                    } else {
//                        branchCodeCountArrayList.add("-");
//                    }
//                }
//            }
//            if (acBranchCode.size() != branchCodeCountArrayList.size()) {
//                for (int k = branchCodeCountArrayList.size(); k <= acBranchCode.size(); k++) {
//                    branchCodeCountArrayList.add("-");
//                }
//            }
//            acTypeAndBranchCodeModel.setBranchCodeArrayList(branchCodeCountArrayList);
//            acTypeAndBranchCodeModelArrayList.add(acTypeAndBranchCodeModel);
//        }


        for (int l = 0; l < acTypeAndBranchCodeModelArrayList.size(); l++) {

            LinearLayout llAcHeaderAndValue = new LinearLayout(AcademicContributionReportActivity.this);
            llAcHeaderAndValue.setOrientation(LinearLayout.HORIZONTAL);

            for (int t = 0; t < acTypeAndBranchCodeModelArrayList.get(l).getBranchCodeArrayList().size(); t++) {

                LinearLayout.LayoutParams acHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                        130, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                CustomBoldTextView customBoldTextView = new CustomBoldTextView(AcademicContributionReportActivity.this);
                customBoldTextView.setLayoutParams(acHeaderAndValueLayoutParam);
                customBoldTextView.setPadding(0, 4, 0, 4);
                customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                customBoldTextView.setTextSize(15);
                customBoldTextView.setLines(1);
                customBoldTextView.setGravity(Gravity.CENTER);
                customBoldTextView.setBackground(ContextCompat.getDrawable(AcademicContributionReportActivity.this,
                        R.drawable.bg_header_ac_report));
                customBoldTextView.setText(acTypeAndBranchCodeModelArrayList.get(l).getBranchCodeArrayList().get(t));

                llAcHeaderAndValue.addView(customBoldTextView);

            }

            ll_ac_header_and_value.addView(llAcHeaderAndValue);
        }
    }

}
