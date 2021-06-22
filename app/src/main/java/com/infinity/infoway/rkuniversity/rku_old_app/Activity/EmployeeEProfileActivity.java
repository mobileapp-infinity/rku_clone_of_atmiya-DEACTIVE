package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetEmployeEProfileDetailsPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetEmployeeIdWiseNameCommitteePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class EmployeeEProfileActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;

    AppCompatAutoCompleteTextView actEmployeeNameEmploeeEProfile;
    AppCompatImageView imClearEmployeeNameEmployeeEProfile;
    ArrayList<String> academicYearOpetion = new ArrayList<>();
    Spinner spAcademicYearEmployeeEProfile;

    ArrayList<String> employeeNameArrayListForAdapter;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_eprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();


        actEmployeeNameEmploeeEProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    imClearEmployeeNameEmployeeEProfile.setVisibility(View.VISIBLE);
                } else {
                    imClearEmployeeNameEmployeeEProfile.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


        spAcademicYearEmployeeEProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0){
                        getEmployeeEProfileDetails(mySharedPreferecesRKUOLD.getEmpID(),String.valueOf(spAcademicYearEmployeeEProfile.getSelectedItemPosition()));
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

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Employee E-Profile");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        actEmployeeNameEmploeeEProfile = findViewById(R.id.actEmployeeNameEmploeeEProfile);
        imClearEmployeeNameEmployeeEProfile = findViewById(R.id.imClearEmployeeNameEmployeeEProfile);
        imClearEmployeeNameEmployeeEProfile.setOnClickListener(this);

        spAcademicYearEmployeeEProfile = findViewById(R.id.spAcademicYearEmployeeEProfile);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.imClearEmployeeNameEmployeeEProfile) {
            actEmployeeNameEmploeeEProfile.setText("");
            imClearEmployeeNameEmployeeEProfile.setVisibility(View.GONE);
        }
    }

    private void getYearApiCall() {
        DialogUtils.showProgressDialog(EmployeeEProfileActivity.this, "");
        String url = URLS.Get_YEAR;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapAcademicYearAndID.put(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME),
                                        jsonArray.getJSONObject(i).getInt(ApiConstants.YEAR_ID));
                                academicYearOpetion.add(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME));
                            }
                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(EmployeeEProfileActivity.this, academicYearOpetion);
                            spAcademicYearEmployeeEProfile.setAdapter(academicYearAdapter);
                            spAcademicYearEmployeeEProfile.setSelection(academicYearOpetion.indexOf(1));
                            getEmployeeIdWiseNameAPI(mySharedPreferecesRKUOLD.getEmpID());
                        } else {
                            Toast.makeText(EmployeeEProfileActivity.this, "Academic year opetions empty", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.hideProgressDialog();
                    Toast.makeText(EmployeeEProfileActivity.this, "Response Null or Empty", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeEProfileActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
                finish();
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    private void getEmployeeIdWiseNameAPI(final String Emp_id) {

//        DialogUtils.showProgressDialog(EmployeeEProfileActivity.this, "");
        StringRequest getEmployyeIdWiseName = new StringRequest(Request.Method.POST, URLS.Get_employee_ID_Wise_Name, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {

                    try {
                        Gson gson = new Gson();
                        GetEmployeeIdWiseNameCommitteePojo getEmployeeIdWiseNameCommitteePojo = gson.fromJson("{\"Data\":" + response + "}", GetEmployeeIdWiseNameCommitteePojo.class);

                        if (getEmployeeIdWiseNameCommitteePojo != null && getEmployeeIdWiseNameCommitteePojo.getData().size() > 0) {

                            employeeNameArrayListForAdapter = new ArrayList<>();
                            for (int i = 0; i < getEmployeeIdWiseNameCommitteePojo.getData().size(); i++) {

                                employeeNameArrayListForAdapter.add(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());

                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployeeEProfileActivity.this, android.R.layout.simple_list_item_1, employeeNameArrayListForAdapter);
                            actEmployeeNameEmploeeEProfile.setAdapter(adapter);
                            actEmployeeNameEmploeeEProfile.setSelection(employeeNameArrayListForAdapter.indexOf(employeeNameArrayListForAdapter.get(0)));

                        } else {
                            DialogUtils.Show_Toast(EmployeeEProfileActivity.this, "Employee list null or empty");
                            DialogUtils.hideProgressDialog();
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(EmployeeEProfileActivity.this, response);
                    DialogUtils.hideProgressDialog();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeEProfileActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Emp_id", Emp_id);
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


    private void getEmployeeEProfileDetails(final String emp_id, final String year_id) {
        DialogUtils.showProgressDialog(EmployeeEProfileActivity.this, "");
        StringRequest getEmployeeEProfileDetails = new StringRequest(Request.Method.POST, URLS.Get_employee_e_profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {

                    try {
                        Gson gson = new Gson();
                        GetEmployeEProfileDetailsPojo getEmployeEProfileDetailsPojo = gson.fromJson("{\"Data\":" + response + "}", GetEmployeEProfileDetailsPojo.class);

                        if (getEmployeEProfileDetailsPojo != null && getEmployeEProfileDetailsPojo.getData().size() > 0) {


                        } else {
                            DialogUtils.Show_Toast(EmployeeEProfileActivity.this, "Employee EProfile is not available");
                            DialogUtils.hideProgressDialog();
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(EmployeeEProfileActivity.this, response);
                    DialogUtils.hideProgressDialog();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeEProfileActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_id", emp_id);
                params2.put("year_id", year_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getEmployeeEProfileDetails.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getEmployeeEProfileDetails);
    }

}
