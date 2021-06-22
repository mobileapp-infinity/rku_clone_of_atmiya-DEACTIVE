package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetAcademicContributionYearPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewPhdScholarGuidedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SavePhdScolarGuidedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ReqPhdScholarGuidedActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ArrayList<String> academicYearOpetion = new ArrayList<>();

    HashMap<Integer, String> hashMapStatus = new HashMap<>();
    ArrayList<String> typesOfStatus = new ArrayList<>();

//    ArrayList<String> yearOfRegistrationOption = new ArrayList<>();
//    ArrayList<String> yearOfAwardedOption = new ArrayList<>();

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    Spinner spAcademicYearPHDScholarGuided, spStatusPHDScholarGuided;

//    Spinner spYearofRegistrationPHDScholarGuided,spYearofAwardedPHDScholarGuided;

    CustomEditText etNameOfPHDScholarGuided, etNameoftheOtherPHDGuideScholar, etDepartmentPHDScholarGuided, etTitleOfResearchPHDScholarGuided;

    CustomBoldTextView tvSubmitPHDScholarGuided, tvCancelPHDScholarGuided, tvUpdatePDHScholarGuided, tvCancelPHDScholarGuidedUpdate;

    LinearLayout ll_submit_phd_scholar_guided, ll_update_phd_scholar_guided, llYearofAwarded;

    ReqViewPhdScholarGuidedPojo.DataBean dataBean;

    CustomEditText etYearofRegistrationPHDScholarGuided, etYearofAwardedPHDScholarGuided;


    String yearOfAwarded = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_phdscholar_guided);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();

        spAcademicYearPHDScholarGuided.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    String acYear[] = academicYearOpetion.get(position).split("-");
                    yearOfAwarded = acYear[0];
                    etYearofAwardedPHDScholarGuided.setText(yearOfAwarded);
                } else {
                    yearOfAwarded = "0";
                    etYearofAwardedPHDScholarGuided.setText(yearOfAwarded);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spStatusPHDScholarGuided.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {

                    if (position == 1 && typesOfStatus.get(position).trim().equalsIgnoreCase("Completed")) {

                        if (spAcademicYearPHDScholarGuided.getSelectedItemPosition() > 0){
                            String acYear[] = academicYearOpetion.get(spAcademicYearPHDScholarGuided.getSelectedItemPosition()).split("-");
                            yearOfAwarded = acYear[0];
                        }

                        llYearofAwarded.setVisibility(View.VISIBLE);
                        etYearofAwardedPHDScholarGuided.setText(yearOfAwarded);
                    } else {
                        yearOfAwarded = "0";
                        etYearofAwardedPHDScholarGuided.setText(yearOfAwarded);
                        llYearofAwarded.setVisibility(View.GONE);
                    }
                }else {
                    llYearofAwarded.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private boolean isValid() {

        if (academicYearOpetion.get(spAcademicYearPHDScholarGuided.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etNameOfPHDScholarGuided.getText().toString())) {
            DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Enter Name of PhD Scholars");
            return false;
        } else if (TextUtils.isEmpty(etDepartmentPHDScholarGuided.getText().toString())) {
            DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Enter Department");
            return false;
        } else if (TextUtils.isEmpty(etNameoftheOtherPHDGuideScholar.getText().toString())) {
            DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Enter Name of Other Guide");
            return false;
        } else if (TextUtils.isEmpty(etTitleOfResearchPHDScholarGuided.getText().toString())) {
            DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Enter Title/Domain of Research");
            return false;
        } else if (TextUtils.isEmpty(etYearofRegistrationPHDScholarGuided.getText().toString())) {
            DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Enter Year of Registration");
            return false;
        } else if (typesOfStatus.get(spStatusPHDScholarGuided.getSelectedItemPosition()).equalsIgnoreCase("Select Status")) {
            DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Select Status");
            return false;
        } else if (typesOfStatus.get(spStatusPHDScholarGuided.getSelectedItemPosition()).trim().equalsIgnoreCase("Completed") &&
                Integer.valueOf(etYearofAwardedPHDScholarGuided.getText().toString()) == 0) {
            DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Enter Year of Awarded");
            return false;
        }

        return true;
    }


    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());

        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Phd Scholar Guided");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);


        spAcademicYearPHDScholarGuided = findViewById(R.id.spAcademicYearPHDScholarGuided);
        spStatusPHDScholarGuided = findViewById(R.id.spStatusPHDScholarGuided);

//        spYearofRegistrationPHDScholarGuided = findViewById(R.id.spYearofRegistrationPHDScholarGuided);
//        yearOfRegistrationOption.add("Select Year of Registration");
//        spYearofAwardedPHDScholarGuided = findViewById(R.id.spYearofAwardedPHDScholarGuided);
//        yearOfAwardedOption.add("Select Year of Awarded");

        etYearofRegistrationPHDScholarGuided = findViewById(R.id.etYearofRegistrationPHDScholarGuided);
        etYearofAwardedPHDScholarGuided = findViewById(R.id.etYearofAwardedPHDScholarGuided);

        academicYearOpetion.add("Select Year");

        hashMapStatus.put(0, "Select Status");
        hashMapStatus.put(1, "Completed");
        hashMapStatus.put(2, "Ongoing");
        hashMapStatus.put(3, "Not Completed");

        typesOfStatus.add(hashMapStatus.get(0));
        typesOfStatus.add(hashMapStatus.get(1));
        typesOfStatus.add(hashMapStatus.get(2));
        typesOfStatus.add(hashMapStatus.get(3));

//        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, typesOfStatus);
//        spStatusPHDScholarGuided.setAdapter(statusAdapter); // this will set list of values to spinner

        SpinnerSimpleAdapter statusAdapter = new SpinnerSimpleAdapter(ReqPhdScholarGuidedActivity.this, typesOfStatus);
        spStatusPHDScholarGuided.setAdapter(statusAdapter);

        spStatusPHDScholarGuided.setSelection(typesOfStatus.indexOf(0));

        etNameOfPHDScholarGuided = findViewById(R.id.etNameOfPHDScholarGuided);
        etNameoftheOtherPHDGuideScholar = findViewById(R.id.etNameoftheOtherPHDGuideScholar);
//        etYearofRegistrationPHDScholarGuided = findViewById(R.id.etYearofRegistrationPHDScholarGuided);
        etDepartmentPHDScholarGuided = findViewById(R.id.etDepartmentPHDScholarGuided);
        etTitleOfResearchPHDScholarGuided = findViewById(R.id.etTitleOfResearchPHDScholarGuided);
//        etYearofAwardedPHDScholarGuided = findViewById(R.id.etYearofAwardedPHDScholarGuided);

        ll_submit_phd_scholar_guided = findViewById(R.id.ll_submit_phd_scholar_guided);
        ll_update_phd_scholar_guided = findViewById(R.id.ll_update_phd_scholar_guided);
        llYearofAwarded = findViewById(R.id.llYearofAwarded);

        tvSubmitPHDScholarGuided = findViewById(R.id.tvSubmitPHDScholarGuided);
        tvSubmitPHDScholarGuided.setOnClickListener(this);
        tvCancelPHDScholarGuided = findViewById(R.id.tvCancelPHDScholarGuided);
        tvCancelPHDScholarGuided.setOnClickListener(this);
        tvUpdatePDHScholarGuided = findViewById(R.id.tvUpdatePDHScholarGuided);
        tvUpdatePDHScholarGuided.setOnClickListener(this);
        tvCancelPHDScholarGuidedUpdate = findViewById(R.id.tvCancelPHDScholarGuidedUpdate);
        tvCancelPHDScholarGuidedUpdate.setOnClickListener(this);

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
        } else if (view.getId() == R.id.tvCancelPHDScholarGuided) {
            onBackPressed();
        } else if (view.getId() == R.id.tvCancelPHDScholarGuidedUpdate) {
            onBackPressed();
        } else if (view.getId() == R.id.tvSubmitPHDScholarGuided) {
            if (isValid()) {

                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPHDScholarGuided.getSelectedItemPosition())));
                String scholar_name = etNameOfPHDScholarGuided.getText().toString();
                String dept_name = etDepartmentPHDScholarGuided.getText().toString();
                String guide_name = etNameoftheOtherPHDGuideScholar.getText().toString();
                String thesis_title = etTitleOfResearchPHDScholarGuided.getText().toString();
                String reg_year = etYearofRegistrationPHDScholarGuided.getText().toString();
                String award_year = etYearofAwardedPHDScholarGuided.getText().toString();
                String status = typesOfStatus.get(spStatusPHDScholarGuided.getSelectedItemPosition());
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();

                callSavePhdScholarGuidedAPI(year_id, scholar_name, dept_name, guide_name, thesis_title, reg_year, award_year, status, user_id, ip, emp_id);

            }
        } else if (view.getId() == R.id.tvUpdatePDHScholarGuided) {

            if (isValid()) {
                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPHDScholarGuided.getSelectedItemPosition())));
                String scholar_name = etNameOfPHDScholarGuided.getText().toString();
                String dept_name = etDepartmentPHDScholarGuided.getText().toString();
                String guide_name = etNameoftheOtherPHDGuideScholar.getText().toString();
                String thesis_title = etTitleOfResearchPHDScholarGuided.getText().toString();
                String reg_year = etYearofRegistrationPHDScholarGuided.getText().toString();
                String award_year = etYearofAwardedPHDScholarGuided.getText().toString();
                String status = typesOfStatus.get(spStatusPHDScholarGuided.getSelectedItemPosition());
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();

                if (dataBean != null) {
                    callUpdatePhdScholarGuidedAPI(String.valueOf(dataBean.getId()), year_id, scholar_name, dept_name, guide_name, thesis_title, reg_year, award_year, status, user_id, ip, emp_id);
                }
            }
        }
    }


    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqPhdScholarGuidedActivity.this, "");
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

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqPhdScholarGuidedActivity.this, academicYearOpetion);
                            spAcademicYearPHDScholarGuided.setAdapter(academicYearAdapter);
                            callAcademicContributionAPI();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }


    private void callAcademicContributionAPI() {

//        DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.GET, URLS.Get_Academic_Contributions_year_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetAcademicContributionYearPojo getAcademicContributionYearPojo = gson.fromJson("{\"Data\":" + response + "}", GetAcademicContributionYearPojo.class);

                        if (getAcademicContributionYearPojo != null && getAcademicContributionYearPojo.getData().size() > 0) {

//                            for (int i = 0; i < getAcademicContributionYearPojo.getData().size(); i++) {
//                                yearOfRegistrationOption.add(String.valueOf(getAcademicContributionYearPojo.getData().get(i).getYear()));
//                                yearOfAwardedOption.add(String.valueOf(getAcademicContributionYearPojo.getData().get(i).getYear()));
//                            }

//                            SpinnerSimpleAdapter yearOfRegistrationAdapter = new SpinnerSimpleAdapter(ReqPhdScholarGuidedActivity.this, yearOfRegistrationOption);
//                            spYearofRegistrationPHDScholarGuided.setAdapter(yearOfRegistrationAdapter);
//
//                            SpinnerSimpleAdapter yearOfAwardedAdapter = new SpinnerSimpleAdapter(ReqPhdScholarGuidedActivity.this, yearOfAwardedOption);
//                            spYearofAwardedPHDScholarGuided.setAdapter(yearOfAwardedAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_PHD_SCHOLR_GUIDED_POJO)) {
                                dataBean = (ReqViewPhdScholarGuidedPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_PHD_SCHOLR_GUIDED_POJO);

                                if (dataBean != null) {
                                    try {

                                        ll_submit_phd_scholar_guided.setVisibility(View.GONE);
                                        ll_update_phd_scholar_guided.setVisibility(View.VISIBLE);

                                        spAcademicYearPHDScholarGuided.setSelection(academicYearOpetion.indexOf(String.valueOf(dataBean.getYear_name())));
                                        etNameOfPHDScholarGuided.setText(dataBean.getPhdsg_scholar_name());
                                        etNameoftheOtherPHDGuideScholar.setText(dataBean.getPhdsg_guide_name());
                                        etYearofRegistrationPHDScholarGuided.setText(String.valueOf(dataBean.getPhdsg_scholar_reg_year()));
//                                        spYearofRegistrationPHDScholarGuided.setSelection(yearOfRegistrationOption.indexOf(String.valueOf(dataBean.getPhdsg_scholar_reg_year())));
//                                    etYearofRegistrationPHDScholarGuided.setText(String.valueOf(dataBean.getPhdsg_scholar_reg_year()));
                                        spStatusPHDScholarGuided.setSelection(typesOfStatus.indexOf(dataBean.getPhdsg_status()));
                                        etDepartmentPHDScholarGuided.setText(dataBean.getPhdsg_dept_name());
                                        etTitleOfResearchPHDScholarGuided.setText(dataBean.getPhdsg_thesis_title());
                                        etYearofAwardedPHDScholarGuided.setText(String.valueOf(dataBean.getPhdsg_award_year()));
//                                        spYearofAwardedPHDScholarGuided.setSelection(yearOfAwardedOption.indexOf(String.valueOf(dataBean.getPhdsg_award_year())));
//                                    etYearofAwardedPHDScholarGuided.setText(String.valueOf(dataBean.getPhdsg_award_year()));

                                    } catch (Exception ex) {
                                        Toast.makeText(ReqPhdScholarGuidedActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }

                            } else {
                                spAcademicYearPHDScholarGuided.setSelection(0);
//                                spYearofRegistrationPHDScholarGuided.setSelection(0);
//                                spYearofAwardedPHDScholarGuided.setSelection(0);
                            }

                        } else {
                            Toast.makeText(ReqPhdScholarGuidedActivity.this, "Academic Contribution opetion null or empty", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(ReqPhdScholarGuidedActivity.this, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ReqPhdScholarGuidedActivity.this, "Response Empty or null", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);

    }


    private void callSavePhdScholarGuidedAPI(final String year_id, final String scholar_name, final String dept_name, final String guide_name,
                                             final String thesis_title, final String reg_year, final String award_year, final String status, final String user_id, final String ip, final String emp_id) {

        DialogUtils.showProgressDialog(ReqPhdScholarGuidedActivity.this, "");
        StringRequest savePhdGuidedrequest = new StringRequest(Request.Method.POST, URLS.Save_PhD_Scholars_Guided_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SavePhdScolarGuidedPojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SavePhdScolarGuidedPojo.class);

                    if (responsePojo.getData().get(0).getStatus() == 1){
                        DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, responsePojo.getData().get(0).getMsg());
                        Intent intent = new Intent(ReqPhdScholarGuidedActivity.this, ReqPhdScholarGuidedListActivity.class);
                        setResult(RESULT_OK, intent);
                        onBackPressed();
                    }else {
                        DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, responsePojo.getData().get(0).getMsg());
                    }
                    //  Toast.makeText(ReqPhdScholarGuidedActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("year_id", year_id);
                params2.put("scholar_name", scholar_name);
                params2.put("dept_name", dept_name);
                params2.put("guide_name", guide_name);
                params2.put("thesis_title", thesis_title);
                params2.put("reg_year", reg_year);
                params2.put("award_year", award_year);
                params2.put("status", status);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("emp_id", emp_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        savePhdGuidedrequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePhdGuidedrequest);
    }


    private void callUpdatePhdScholarGuidedAPI(final String id, final String year_id, final String scholar_name, final String dept_name, final String guide_name,
                                               final String thesis_title, final String reg_year, final String award_year, final String status, final String user_id, final String ip, final String emp_id) {

        DialogUtils.showProgressDialog(ReqPhdScholarGuidedActivity.this, "");
        StringRequest savePhdGuidedrequest = new StringRequest(Request.Method.POST, URLS.Update_PhD_Scholars_Guided_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SavePhdScolarGuidedPojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SavePhdScolarGuidedPojo.class);

                    if (responsePojo.getData().get(0).getStatus() == 1){
                        DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, responsePojo.getData().get(0).getMsg());
                        Intent intent = new Intent(ReqPhdScholarGuidedActivity.this, ReqPhdScholarGuidedListActivity.class);
                        setResult(RESULT_OK, intent);
                        onBackPressed();
                    }else {
                        DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, responsePojo.getData().get(0).getMsg());
                    }

                    // Toast.makeText(ReqPhdScholarGuidedActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPhdScholarGuidedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("year_id", year_id);
                params2.put("scholar_name", scholar_name);
                params2.put("dept_name", dept_name);
                params2.put("guide_name", guide_name);
                params2.put("thesis_title", thesis_title);
                params2.put("reg_year", reg_year);
                params2.put("award_year", award_year);
                params2.put("status", status);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("emp_id", emp_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        savePhdGuidedrequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePhdGuidedrequest);
    }


}
