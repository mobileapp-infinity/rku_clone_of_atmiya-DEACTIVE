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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewPgScholarGuidedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ReqPGScholarGuidedActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;

    Spinner spAcademicYearPGScholarGuided, spStatusPGScholarGuided,
            spYearofAwardedPGScholarGuided, spYearofRegistrationPGScholarGuided;

    CustomEditText etNameOfPGScholarGuided, etDomainOfresearchPGGuideScholar,
            etNameofOtherGuidePGScholarGuided;

    LinearLayout ll_submit_pg_scholar_guided, ll_update_pg_scholar_guided;

    CustomBoldTextView tvSubmitPGScholarGuided, tvCancelPGScholarGuided, tvUpdatePGScholarGuided,
            tvCancelPGScholarGuidedUpdate;

    ArrayList<String> academicYearOpetion = new ArrayList<>();

    ArrayList<String> yearOfAwardedOption = new ArrayList<>();
    ArrayList<String> yearOfRegistrationOption = new ArrayList<>();

    HashMap<Integer, String> hashMapStatus = new HashMap<>();
    ArrayList<String> typesOfStatus = new ArrayList<>();

    ReqViewPgScholarGuidedPojo.DataBean dataBean;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_pgscholar_guided);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();
    }


    private boolean isValid() {
        if (academicYearOpetion.get(spAcademicYearPGScholarGuided.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etNameOfPGScholarGuided.getText().toString())) {
            DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Enter Name of PG Scholars");
            return false;
        } else if (TextUtils.isEmpty(etDomainOfresearchPGGuideScholar.getText().toString())) {
            DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Enter Title of Research");
            return false;
        } else if (yearOfAwardedOption.get(spYearofAwardedPGScholarGuided.getSelectedItemPosition()).equalsIgnoreCase("Select Year of Awarded")) {
            DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Select Year of Awarded");
            return false;
        } else if (TextUtils.isEmpty(etNameofOtherGuidePGScholarGuided.getText().toString())) {
            DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Enter Name of Othe Guided");
            return false;
        } else if (yearOfRegistrationOption.get(spYearofRegistrationPGScholarGuided.getSelectedItemPosition()).equalsIgnoreCase("Select Year of Registration")) {
            DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Select Year of Registration");
            return false;
        } else if (typesOfStatus.get(spStatusPGScholarGuided.getSelectedItemPosition()).equalsIgnoreCase("Select Status")) {
            DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Select Status");
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
        txt_act.setText("PG Scholar Guided");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        academicYearOpetion.add("Select Year");

        spAcademicYearPGScholarGuided = findViewById(R.id.spAcademicYearPGScholarGuided);
        spStatusPGScholarGuided = findViewById(R.id.spStatusPGScholarGuided);

        spYearofAwardedPGScholarGuided = findViewById(R.id.spYearofAwardedPGScholarGuided);
        yearOfAwardedOption.add("Select Year of Awarded");

        spYearofRegistrationPGScholarGuided = findViewById(R.id.spYearofRegistrationPGScholarGuided);
        yearOfRegistrationOption.add("Select Year of Registration");

        hashMapStatus.put(0, "Select Status");
        hashMapStatus.put(1, "Completed");
        hashMapStatus.put(2, "Ongoing");
        hashMapStatus.put(3, "Not Completed");

        typesOfStatus.add(hashMapStatus.get(0));
        typesOfStatus.add(hashMapStatus.get(1));
        typesOfStatus.add(hashMapStatus.get(2));
        typesOfStatus.add(hashMapStatus.get(3));

//        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, typesOfStatus);
//        spStatusPGScholarGuided.setAdapter(statusAdapter); // this will set list of values to spinner

        SpinnerSimpleAdapter statusAdapter = new SpinnerSimpleAdapter(ReqPGScholarGuidedActivity.this, typesOfStatus);
        spStatusPGScholarGuided.setAdapter(statusAdapter);

        spStatusPGScholarGuided.setSelection(typesOfStatus.indexOf(0));

        etNameOfPGScholarGuided = findViewById(R.id.etNameOfPGScholarGuided);
        etDomainOfresearchPGGuideScholar = findViewById(R.id.etDomainOfresearchPGGuideScholar);
//        etYearofAwardedPGScholarGuided = findViewById(R.id.etYearofAwardedPGScholarGuided);
        etNameofOtherGuidePGScholarGuided = findViewById(R.id.etNameofOtherGuidePGScholarGuided);
//        etYearofRegistrationPGScholarGuided = findViewById(R.id.etYearofRegistrationPGScholarGuided);

        ll_submit_pg_scholar_guided = findViewById(R.id.ll_submit_pg_scholar_guided);
        ll_update_pg_scholar_guided = findViewById(R.id.ll_update_pg_scholar_guided);

        tvSubmitPGScholarGuided = findViewById(R.id.tvSubmitPGScholarGuided);
        tvSubmitPGScholarGuided.setOnClickListener(this);
        tvCancelPGScholarGuided = findViewById(R.id.tvCancelPGScholarGuided);
        tvCancelPGScholarGuided.setOnClickListener(this);
        tvUpdatePGScholarGuided = findViewById(R.id.tvUpdatePGScholarGuided);
        tvUpdatePGScholarGuided.setOnClickListener(this);
        tvCancelPGScholarGuidedUpdate = findViewById(R.id.tvCancelPGScholarGuidedUpdate);
        tvCancelPGScholarGuidedUpdate.setOnClickListener(this);

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
        } else if (v.getId() == R.id.tvSubmitPGScholarGuided) {
            if (isValid()) {
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String user_id = mySharedPreferecesRKUOLD.getEmpID();
                String ip = "0";
                String Year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPGScholarGuided.getSelectedItemPosition())));
                String Scholar_Name = etNameOfPGScholarGuided.getText().toString();
                String Scholar_Guide_Name = etNameofOtherGuidePGScholarGuided.getText().toString();
                String Research_Title = etDomainOfresearchPGGuideScholar.getText().toString();
                String Registration_Year =  yearOfRegistrationOption.get(spYearofRegistrationPGScholarGuided.getSelectedItemPosition());
                String Awarded_Year = yearOfAwardedOption.get(spYearofAwardedPGScholarGuided.getSelectedItemPosition());
                String Status = typesOfStatus.get(spStatusPGScholarGuided.getSelectedItemPosition());

                callPGScholarGuidedSubmitAPI(emp_id, user_id, ip, Year, Scholar_Name, Scholar_Guide_Name, Research_Title,
                        Registration_Year, Awarded_Year, Status);
            }
        } else if (v.getId() == R.id.tvCancelPGScholarGuided) {
            onBackPressed();
        } else if (v.getId() == R.id.tvUpdatePGScholarGuided) {
            if (isValid()) {
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String user_id = mySharedPreferecesRKUOLD.getEmpID();
                String ip = "0";
                String Year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPGScholarGuided.getSelectedItemPosition())));
                String Scholar_Name = etNameOfPGScholarGuided.getText().toString();
                String Scholar_Guide_Name = etNameofOtherGuidePGScholarGuided.getText().toString();
                String Research_Title = etDomainOfresearchPGGuideScholar.getText().toString();
                String Registration_Year = yearOfRegistrationOption.get(spYearofRegistrationPGScholarGuided.getSelectedItemPosition());
                String Awarded_Year = yearOfAwardedOption.get(spYearofAwardedPGScholarGuided.getSelectedItemPosition());
                String Status = typesOfStatus.get(spStatusPGScholarGuided.getSelectedItemPosition());
                if (dataBean != null) {
                    callPGScholarGuidedUpdateAPI(String.valueOf(dataBean.getId()), emp_id, user_id, ip, Year, Scholar_Name, Scholar_Guide_Name, Research_Title,
                            Registration_Year, Awarded_Year, Status);
                }
            }
        } else if (v.getId() == R.id.tvCancelPGScholarGuidedUpdate) {
            onBackPressed();
        }
    }


    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqPGScholarGuidedActivity.this, "");
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
//                            ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, academicYearOpetion);
//                            spAcademicYearPGScholarGuided.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqPGScholarGuidedActivity.this, academicYearOpetion);
                            spAcademicYearPGScholarGuided.setAdapter(academicYearAdapter);
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
                DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Please Try Again Later");
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
                                yearOfRegistrationOption.add(String.valueOf(getAcademicContributionYearPojo.getData().get(i).getYear()));
                            }

                            SpinnerSimpleAdapter yearOfAwardedAdapter = new SpinnerSimpleAdapter(ReqPGScholarGuidedActivity.this, yearOfAwardedOption);
                            spYearofAwardedPGScholarGuided.setAdapter(yearOfAwardedAdapter);

                            SpinnerSimpleAdapter yearOfRegistrationAdapter = new SpinnerSimpleAdapter(ReqPGScholarGuidedActivity.this, yearOfRegistrationOption);
                            spYearofRegistrationPGScholarGuided.setAdapter(yearOfRegistrationAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_PG_SCHOLAR_GUIDED_POJO)) {
                                ll_submit_pg_scholar_guided.setVisibility(View.GONE);
                                ll_update_pg_scholar_guided.setVisibility(View.VISIBLE);
                                dataBean = (ReqViewPgScholarGuidedPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_PG_SCHOLAR_GUIDED_POJO);

                                if (dataBean != null) {
                                    spAcademicYearPGScholarGuided.setSelection(academicYearOpetion.indexOf(dataBean.getYear_name()));
                                    etNameOfPGScholarGuided.setText(dataBean.getPgs_scholar_name());
                                    etNameofOtherGuidePGScholarGuided.setText(String.valueOf(dataBean.getPgs_scholar_guide_name()));
                                    etDomainOfresearchPGGuideScholar.setText(dataBean.getPgs_research_title());
                                    spYearofAwardedPGScholarGuided.setSelection(yearOfAwardedOption.indexOf(String.valueOf(dataBean.getPgs_award_year())));
                                    spYearofRegistrationPGScholarGuided.setSelection(yearOfRegistrationOption.indexOf(String.valueOf(dataBean.getPgs_reg_year())));
                                    spStatusPGScholarGuided.setSelection(typesOfStatus.indexOf(dataBean.getPgs_status()));
                                }

                            } else {
                                spAcademicYearPGScholarGuided.setSelection(0);
                                spYearofAwardedPGScholarGuided.setSelection(0);
                                spYearofRegistrationPGScholarGuided.setSelection(0);
                            }

                        }else {
                            Toast.makeText(ReqPGScholarGuidedActivity.this, "Academic Contribution opetion null or empty", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(ReqPGScholarGuidedActivity.this, "Exception:- "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ReqPGScholarGuidedActivity.this, "Response Empty or null", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);

    }


    private void callPGScholarGuidedSubmitAPI(final String emp_id, final String user_id, final String ip, final String Year,
                                              final String Scholar_Name, final String Scholar_Guide_Name, final String Research_Title,
                                              final String Registration_Year, final String Awarded_Year, final String Status) {

        DialogUtils.showProgressDialog(ReqPGScholarGuidedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_PG_Scholars_Guided_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqPGScholarGuidedActivity.this, ReqPgScholarGuidedListActivity.class);
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                    // Toast.makeText(ReqPGScholarGuidedActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_id", emp_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("Year", Year);
                params2.put("Scholar_Name", Scholar_Name);
                params2.put("Scholar_Guide_Name", Scholar_Guide_Name);
                params2.put("Research_Title", Research_Title);
                params2.put("Registration_Year", Registration_Year);
                params2.put("Awarded_Year", Awarded_Year);
                params2.put("Status", Status);
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


    private void callPGScholarGuidedUpdateAPI(final String id, final String emp_id, final String user_id, final String ip, final String Year,
                                              final String Scholar_Name, final String Scholar_Guide_Name, final String Research_Title,
                                              final String Registration_Year, final String Awarded_Year, final String Status) {

        DialogUtils.showProgressDialog(ReqPGScholarGuidedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_PG_Scholars_Guided_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, responsePojo.getData().get(0).getMsg());

                    Intent intent = new Intent(ReqPGScholarGuidedActivity.this, ReqPgScholarGuidedListActivity.class);
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                    //Toast.makeText(ReqPGScholarGuidedActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPGScholarGuidedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("emp_id", emp_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("Year", Year);
                params2.put("Scholar_Name", Scholar_Name);
                params2.put("Scholar_Guide_Name", Scholar_Guide_Name);
                params2.put("Research_Title", Research_Title);
                params2.put("Registration_Year", Registration_Year);
                params2.put("Awarded_Year", Awarded_Year);
                params2.put("Status", Status);
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
