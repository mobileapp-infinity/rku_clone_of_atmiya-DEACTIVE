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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewGRantReceivedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ReqGrantReceivedRequest extends AppCompatActivity implements View.OnClickListener {

    Spinner spAcademicYearGrantReceived, spTypesOfAgencyGrantReceived, spStatusGrantReceived,
            spYearofAwardGantReceived;

    CustomEditText etNameofProjectGrantReceived, etfundsProviderGrantreceived,
            etNameofPrincipleInvestigatorGantReceived,
            etNameofAgencyGantReceived, etDurationGantReceived;
    CustomBoldTextView tvSubmitGrantReceived, tvCancelGrantReceived, tvUpdateGrantReceivedUpdate, tvCancelGrantReceivedCancel;
    ArrayList<String> academicYearOpetion = new ArrayList<>();
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    HashMap<Integer, String> hashMapTypesOfagency = new HashMap<>();
    HashMap<Integer, String> hashMapStatus = new HashMap<>();
    ArrayList<String> typesOfAgency = new ArrayList<>();
    ArrayList<String> yearOfAwardedOption = new ArrayList<>();
    ArrayList<String> typesOfStatus = new ArrayList<>();
    LinearLayout ll_update_grantReceived_cancel,ll_submit_cancel_req_grant_received;
    ReqViewGRantReceivedPojo.DataBean dataBean;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_grant_received_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();
    }

    private boolean isValid() {
        if (academicYearOpetion.get(spAcademicYearGrantReceived.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etNameofProjectGrantReceived.getText().toString())) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Enter Name Of Project");
            return false;
        } else if (typesOfAgency.get(spTypesOfAgencyGrantReceived.getSelectedItemPosition()).equalsIgnoreCase("Select Types of Agency")) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Select Types of Agency");
            return false;
        } else if (TextUtils.isEmpty(etfundsProviderGrantreceived.getText().toString())) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Enter Funds Provided");
            return false;
        } else if (typesOfStatus.get(spStatusGrantReceived.getSelectedItemPosition()).equalsIgnoreCase("Select Status")) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Select Status");
            return false;
        } else if (TextUtils.isEmpty(etNameofPrincipleInvestigatorGantReceived.getText().toString())) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Enter Name of Principle Investigator");
            return false;
        } else if (yearOfAwardedOption.get(spYearofAwardGantReceived.getSelectedItemPosition()).equalsIgnoreCase("Select Year of Award")) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Select Year of Award");
            return false;
        } else if (TextUtils.isEmpty(etNameofAgencyGantReceived.getText().toString())) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Enter Name of Agency");
            return false;
        } else if (TextUtils.isEmpty(etDurationGantReceived.getText().toString())) {
            DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Enter Duration");
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
        txt_act.setText("Grant Received Request");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        academicYearOpetion.add("Select Year");

        spAcademicYearGrantReceived = findViewById(R.id.spAcademicYearGrantReceived);
        spTypesOfAgencyGrantReceived = findViewById(R.id.spTypesOfAgencyGrantReceived);
        spStatusGrantReceived = findViewById(R.id.spStatusGrantReceived);
        //  spLevelOfConference = findViewById(R.id.spLevelOfConference);

        spYearofAwardGantReceived = findViewById(R.id.spYearofAwardGantReceived);
        yearOfAwardedOption.add("Select Year of Award");

        hashMapTypesOfagency.put(0, "Select Types of Agency");
        hashMapTypesOfagency.put(1, "Govt");
        hashMapTypesOfagency.put(2, "Non-Govt");
        hashMapTypesOfagency.put(3, "Other");

        typesOfAgency.add("Select Types of Agency");
        typesOfAgency.add("Govt");
        typesOfAgency.add("Non-Govt");
        typesOfAgency.add("Other");

//        ArrayAdapter<String> typesOfAgencyAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, typesOfAgency);
//        spTypesOfAgencyGrantReceived.setAdapter(typesOfAgencyAdapter); // this will set list of values to spinner

        SpinnerSimpleAdapter typesOfAgencyAdapter = new SpinnerSimpleAdapter(ReqGrantReceivedRequest.this, typesOfAgency);
        spTypesOfAgencyGrantReceived.setAdapter(typesOfAgencyAdapter);

        spTypesOfAgencyGrantReceived.setSelection(typesOfAgency.indexOf(0));

        hashMapStatus.put(0, "Select Status");
        hashMapStatus.put(1, "Completed");
        hashMapStatus.put(2, "Ongoing");
        hashMapStatus.put(3, "Not Completed");

        typesOfStatus.add(hashMapStatus.get(0));
        typesOfStatus.add(hashMapStatus.get(1));
        typesOfStatus.add(hashMapStatus.get(2));
        typesOfStatus.add(hashMapStatus.get(3));


//        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, typesOfStatus);
//        spStatusGrantReceived.setAdapter(statusAdapter); // this will set list of values to spinner


        SpinnerSimpleAdapter statusAdapter = new SpinnerSimpleAdapter(ReqGrantReceivedRequest.this, typesOfStatus);
        spStatusGrantReceived.setAdapter(statusAdapter);

        spStatusGrantReceived.setSelection(typesOfStatus.indexOf(0));


        etNameofProjectGrantReceived = findViewById(R.id.etNameofProjectGrantReceived);
        etfundsProviderGrantreceived = findViewById(R.id.etfundsProviderGrantreceived);
        etNameofPrincipleInvestigatorGantReceived = findViewById(R.id.etNameofPrincipleInvestigatorGantReceived);
//        etYearofAwardGantReceived = findViewById(R.id.etYearofAwardGantReceived);
        etNameofAgencyGantReceived = findViewById(R.id.etNameofAgencyGantReceived);
        etDurationGantReceived = findViewById(R.id.etDurationGantReceived);


        ll_submit_cancel_req_grant_received= findViewById(R.id.ll_submit_cancel_req_grant_received);
        ll_update_grantReceived_cancel = findViewById(R.id.ll_update_grantReceived_cancel);

        tvSubmitGrantReceived = findViewById(R.id.tvSubmitGrantReceived);
        tvSubmitGrantReceived.setOnClickListener(this);
        tvCancelGrantReceived = findViewById(R.id.tvCancelGrantReceived);
        tvCancelGrantReceived.setOnClickListener(this);
        tvUpdateGrantReceivedUpdate = findViewById(R.id.tvUpdateGrantReceivedUpdate);
        tvUpdateGrantReceivedUpdate.setOnClickListener(this);
        tvCancelGrantReceivedCancel = findViewById(R.id.tvCancelGrantReceivedCancel);
        tvCancelGrantReceivedCancel.setOnClickListener(this);

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
        } else if (view.getId() == R.id.tvSubmitGrantReceived) {
            if (isValid()) {
                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearGrantReceived.getSelectedItemPosition())));
                String award_year =  yearOfAwardedOption.get(spYearofAwardGantReceived.getSelectedItemPosition());;
                String funds_provided = etfundsProviderGrantreceived.getText().toString();
                String priinv = etNameofPrincipleInvestigatorGantReceived.getText().toString();
                String priinvdpt = etNameofPrincipleInvestigatorGantReceived.getText().toString();
                String duration = etDurationGantReceived.getText().toString();
                String name = etNameofProjectGrantReceived.getText().toString();
                String status = typesOfStatus.get(spStatusGrantReceived.getSelectedItemPosition());
                String agency = typesOfAgency.get(spTypesOfAgencyGrantReceived.getSelectedItemPosition());
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                callGrantReceivedSubmitAPI(year_id, award_year, funds_provided, priinv, priinvdpt, duration, name, status, agency, user_id, ip, emp_id);
            }
        } else if (view.getId() == R.id.tvCancelGrantReceived) {
            onBackPressed();
        } else if (view.getId() == R.id.tvUpdateGrantReceivedUpdate) {
            if (isValid()) {
                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearGrantReceived.getSelectedItemPosition())));
                String award_year =    yearOfAwardedOption.get(spYearofAwardGantReceived.getSelectedItemPosition());
                String funds_provided = etfundsProviderGrantreceived.getText().toString();
                String priinv = etNameofPrincipleInvestigatorGantReceived.getText().toString();
                String priinvdpt = etNameofPrincipleInvestigatorGantReceived.getText().toString();
                String duration = etDurationGantReceived.getText().toString();
                String name = etNameofProjectGrantReceived.getText().toString();
                String status = typesOfStatus.get(spStatusGrantReceived.getSelectedItemPosition());
                String agency = typesOfAgency.get(spTypesOfAgencyGrantReceived.getSelectedItemPosition());
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                if (dataBean != null){
                    callGrantReceivedUpdateAPI(String.valueOf(dataBean.getId()),year_id, award_year, funds_provided, priinv, priinvdpt, duration, name, status, agency, user_id, ip, emp_id);
                }
            }
        } else if (view.getId() == R.id.tvCancelGrantReceivedCancel) {
            onBackPressed();
        }
    }


    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqGrantReceivedRequest.this, "");
        String url = URLS.Get_YEAR;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapAcademicYearAndID.put(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME),
                                        jsonArray.getJSONObject(i).getInt(ApiConstants.YEAR_ID));
                                academicYearOpetion.add(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME));
                            }

                            SpinnerSimpleAdapter academicYearAdapter= new SpinnerSimpleAdapter(ReqGrantReceivedRequest.this, academicYearOpetion);
                            spAcademicYearGrantReceived.setAdapter(academicYearAdapter);
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
                DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Please Try Again Later");
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

                            for (int i = 0 ; i < getAcademicContributionYearPojo.getData().size() ; i++){
                                yearOfAwardedOption.add(String.valueOf(getAcademicContributionYearPojo.getData().get(i).getYear()));
                            }

                            SpinnerSimpleAdapter yearfawardOptionAdapter = new SpinnerSimpleAdapter(ReqGrantReceivedRequest.this, yearOfAwardedOption);
                            spYearofAwardGantReceived.setAdapter(yearfawardOptionAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_GRANT_RECEIVED_POJO)){
                                ll_submit_cancel_req_grant_received.setVisibility(View.GONE);
                                ll_update_grantReceived_cancel.setVisibility(View.VISIBLE);
                                dataBean = (ReqViewGRantReceivedPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_GRANT_RECEIVED_POJO);

                                if (dataBean != null){
                                    spAcademicYearGrantReceived.setSelection(academicYearOpetion.indexOf(dataBean.getYear_name()));
                                    etNameofProjectGrantReceived.setText(dataBean.getGr_project_name());
                                    spTypesOfAgencyGrantReceived.setSelection(typesOfAgency.indexOf(dataBean.getGr_agency_name()));
                                    etfundsProviderGrantreceived.setText(dataBean.getGr_funds_provided());
                                    spStatusGrantReceived.setSelection(typesOfStatus.indexOf(dataBean.getGr_status()));
                                    etNameofPrincipleInvestigatorGantReceived.setText(dataBean.getGr_principal_investigator());
                                    spYearofAwardGantReceived.setSelection(yearOfAwardedOption.indexOf(String.valueOf(dataBean.getGr_award_year())));
//                                    etYearofAwardGantReceived.setText(dataBean.getGr_award_year());
                                    etNameofAgencyGantReceived.setText(dataBean.getGr_agency_name());
                                    etDurationGantReceived.setText(dataBean.getGr_project_duration());
                                }
                            }else {
                                spAcademicYearGrantReceived.setSelection(0);
                                spYearofAwardGantReceived.setSelection(0);
                            }

                        }else {
                            Toast.makeText(ReqGrantReceivedRequest.this, "Academic Contribution opetion null or empty", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(ReqGrantReceivedRequest.this, "Exception:- "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ReqGrantReceivedRequest.this, "Response Empty or null", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);

    }

    private void callGrantReceivedSubmitAPI(final String year_id, final String award_year, final String funds_provided, final String priinv, final String priinvdpt,
                                            final String duration, final String name, final String status, final String agency, final String user_id, final String ip, final String emp_id) {


        DialogUtils.showProgressDialog(ReqGrantReceivedRequest.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.SAVE_GRANT_RECEIVED_REQUEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, responsePojo.getData().get(0).getMsg());


                    Intent intent = new Intent(ReqGrantReceivedRequest.this, ReqGrantReceivedRequestListActivity.class);
                    setResult(RESULT_OK,intent);
                    onBackPressed();
                    //Toast.makeText(ReqGrantReceivedRequest.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("year_id", year_id);
                params2.put("award_year", award_year);
                params2.put("funds_provided", funds_provided);
                params2.put("principal_investigator", priinv);
                params2.put("type_of_agency", priinvdpt);
                params2.put("duration", duration);
                params2.put("name", name);
                params2.put("status", status);
                params2.put("agency_name", agency);
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
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);
    }


    private void callGrantReceivedUpdateAPI(final String id,final String year_id, final String award_year, final String funds_provided, final String priinv, final String priinvdpt,
                                            final String duration, final String name, final String status, final String agency, final String user_id, final String ip, final String emp_id) {

        DialogUtils.showProgressDialog(ReqGrantReceivedRequest.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_Grant_Received_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, responsePojo.getData().get(0).getMsg());

                    Intent intent = new Intent(ReqGrantReceivedRequest.this, ReqGrantReceivedRequestListActivity.class);
                    setResult(RESULT_OK,intent);
                    onBackPressed();
                   // Toast.makeText(ReqGrantReceivedRequest.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqGrantReceivedRequest.this, "Please Try Again Later");
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
                params2.put("award_year", award_year);
                params2.put("funds_provided", funds_provided);
                params2.put("principal_investigator", priinv);
                params2.put("type_of_agency", priinvdpt);
                params2.put("duration", duration);
                params2.put("name", name);
                params2.put("status", status);
                params2.put("agency_name", agency);
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
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);


    }

}
