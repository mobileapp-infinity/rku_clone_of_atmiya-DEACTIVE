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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewConsultancyRequestPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ReqConsultancyRequestActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ArrayList<String> academicYearOpetion = new ArrayList<>();
    ArrayList<String> typesOfStatus = new ArrayList<>();
    HashMap<Integer, String> hashMapStatus = new HashMap<>();

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    Spinner spAcademicYearConsultancyReq, spStatusConsultancyReq;
    CustomEditText etNameofConsultancyProjectConsultancyReq, etConsultingSponsoringAgencyConsaltancyReq,
            etRevenueGeneratedConsultancyReq;
    LinearLayout ll_submit_cancel_consaltancy_req, ll_update_consultancy_req;
    CustomBoldTextView tvSubmitConsultancyReq, tvCancelConsultancyReq, tvUpdateConsultancyReq,
            tvCancelConsaltancyReqUpdate;

    ReqViewConsultancyRequestPojo.DataBean dataBean;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_consultancy_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();
    }

    private void initView() {


        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Consaltancy Request");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);



        spAcademicYearConsultancyReq = findViewById(R.id.spAcademicYearConsultancyReq);
        spStatusConsultancyReq = findViewById(R.id.spStatusConsultancyReq);

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
//        spStatusConsultancyReq.setAdapter(statusAdapter); // this will set list of values to spinner

        SpinnerSimpleAdapter statusAdapter = new SpinnerSimpleAdapter(ReqConsultancyRequestActivity.this, typesOfStatus);
        spStatusConsultancyReq.setAdapter(statusAdapter);

        spStatusConsultancyReq.setSelection(typesOfStatus.indexOf(0));

        etNameofConsultancyProjectConsultancyReq = findViewById(R.id.etNameofConsultancyProjectConsultancyReq );
        etConsultingSponsoringAgencyConsaltancyReq = findViewById(R.id.etConsultingSponsoringAgencyConsaltancyReq);
        etRevenueGeneratedConsultancyReq = findViewById(R.id.etRevenueGeneratedConsultancyReq);

        ll_submit_cancel_consaltancy_req = findViewById(R.id.ll_submit_cancel_consaltancy_req);
        ll_update_consultancy_req = findViewById(R.id.ll_update_consultancy_req);

        tvSubmitConsultancyReq = findViewById(R.id.tvSubmitConsultancyReq);
        tvSubmitConsultancyReq.setOnClickListener(this);
        tvCancelConsultancyReq = findViewById(R.id.tvCancelConsultancyReq);
        tvCancelConsultancyReq.setOnClickListener(this);
        tvUpdateConsultancyReq = findViewById(R.id.tvUpdateConsultancyReq);
        tvUpdateConsultancyReq.setOnClickListener(this);
        tvCancelConsaltancyReqUpdate = findViewById(R.id.tvCancelConsaltancyReqUpdate);
        tvCancelConsaltancyReqUpdate.setOnClickListener(this);

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

        if (v.getId() == R.id.tvSubmitConsultancyReq){
            if (isValid()){
                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearConsultancyReq.getSelectedItemPosition())));
                String project_name = etNameofConsultancyProjectConsultancyReq.getText().toString();
                String contanct_detail = etConsultingSponsoringAgencyConsaltancyReq.getText().toString();
                String revenue_gen = etRevenueGeneratedConsultancyReq.getText().toString();
                String con_status = typesOfStatus.get(spStatusConsultancyReq.getSelectedItemPosition());
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();

                callSaveConsultancyReqaAPI(year_id,project_name,contanct_detail,revenue_gen,con_status,user_id,ip,emp_id);
            }
        }else if (v.getId() == R.id.tvCancelConsultancyReq){
            onBackPressed();
        }else if (v.getId() == R.id.tvUpdateConsultancyReq){
            if (isValid()){
                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearConsultancyReq.getSelectedItemPosition())));
                String project_name = etNameofConsultancyProjectConsultancyReq.getText().toString();
                String contanct_detail = etConsultingSponsoringAgencyConsaltancyReq.getText().toString();
                String revenue_gen = etRevenueGeneratedConsultancyReq.getText().toString();
                String con_status = typesOfStatus.get(spStatusConsultancyReq.getSelectedItemPosition());
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                if (dataBean != null){
                    callUpdateConsultancyReqaAPI(year_id,project_name,contanct_detail,revenue_gen,con_status,user_id,ip,emp_id,String.valueOf(dataBean.getId()));
                }
            }
        }else if (v.getId() == R.id.tvCancelConsaltancyReqUpdate){
            onBackPressed();
        }else if (v.getId() == R.id.iv_back){
            onBackPressed();
        }
    }

    private boolean isValid(){

        if (academicYearOpetion.get(spAcademicYearConsultancyReq.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etNameofConsultancyProjectConsultancyReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, "Enter Name of Consultancy Project");
            return false;
        } else if (TextUtils.isEmpty(etConsultingSponsoringAgencyConsaltancyReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, "Enter Consulting/Sponsoring Agency");
            return false;
        } else if (TextUtils.isEmpty(etRevenueGeneratedConsultancyReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, "Enter Revenue Generated ");
            return false;
        }else if (typesOfStatus.get(spStatusConsultancyReq.getSelectedItemPosition()).equalsIgnoreCase("Select Status")) {
            DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, "Select Status");
            return false;
        }
        return true;
    }


    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqConsultancyRequestActivity.this, "");
        String url = URLS.Get_YEAR;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

//                            academicYearCommanModelArrayList = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                hashMapAcademicYearAndID.put(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME),
                                        jsonArray.getJSONObject(i).getInt(ApiConstants.YEAR_ID));

                                academicYearOpetion.add(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME));

                            }
                            SpinnerSimpleAdapter academicYearAdapter  = new SpinnerSimpleAdapter(ReqConsultancyRequestActivity.this, academicYearOpetion);
                            spAcademicYearConsultancyReq.setAdapter(academicYearAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_CONSULTANCY_POJO)){

                                ll_submit_cancel_consaltancy_req.setVisibility(View.GONE);
                                ll_update_consultancy_req.setVisibility(View.VISIBLE);
                                dataBean = (ReqViewConsultancyRequestPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_CONSULTANCY_POJO);

                                if (dataBean != null){
                                    spAcademicYearConsultancyReq.setSelection(academicYearOpetion.indexOf(dataBean.getYear_name()));
                                    etNameofConsultancyProjectConsultancyReq.setText(dataBean.getCon_project_name());
                                    etConsultingSponsoringAgencyConsaltancyReq.setText(String.valueOf(dataBean.getCon_contect_details()));
                                    etRevenueGeneratedConsultancyReq.setText(String.valueOf(dataBean.getCon_revenue_gerented()));
                                    spStatusConsultancyReq.setSelection(typesOfStatus.indexOf(dataBean.getCon_status()));
                                }
                                
                            }else {
                                spAcademicYearConsultancyReq.setSelection(academicYearOpetion.indexOf(0));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    private void callSaveConsultancyReqaAPI(final String year_id,final String project_name,final  String contact_detail,final String revenue_gen,
                                            final String con_status,final String user_id,final String ip,
                                            final String emp_id){

        DialogUtils.showProgressDialog(ReqConsultancyRequestActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_Consultancy_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, responsePojo.getData().get(0).getMsg());

                    Intent intent = new Intent(ReqConsultancyRequestActivity.this, ReqConsultancyRequestListActivity.class);
                    setResult(RESULT_OK,intent);
                    onBackPressed();
                   // Toast.makeText(ReqConsultancyRequestActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("year_id", year_id);
                params2.put("project_name", project_name);
                params2.put("contact_detail", contact_detail);
                params2.put("revenue_gen", revenue_gen);
                params2.put("con_status", con_status);
                params2.put("user_id", user_id);
                params2.put("ip",ip);
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


    private void callUpdateConsultancyReqaAPI(final String year_id,final String project_name,final  String contact_detail,final String revenue_gen,
                                            final String con_status,final String user_id,final String ip,
                                            final String emp_id,final String up_id){

        DialogUtils.showProgressDialog(ReqConsultancyRequestActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_Consultancy_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqConsultancyRequestActivity.this, ReqConsultancyRequestListActivity.class);
                    setResult(RESULT_OK,intent);
                    onBackPressed();
                   // Toast.makeText(ReqConsultancyRequestActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqConsultancyRequestActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("year_id", year_id);
                params2.put("project_name", project_name);
                params2.put("contact_detail", contact_detail);
                params2.put("revenue_gen", revenue_gen);
                params2.put("con_status", con_status);
                params2.put("user_id", user_id);
                params2.put("ip",ip);
                params2.put("emp_id", emp_id);
                params2.put("up_id",up_id);
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
