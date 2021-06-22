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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewseedMoneyPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ReqSeedReceivedFromUniversityActivity extends AppCompatActivity implements View.OnClickListener{


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;

    ArrayList<String> academicYearOpetion = new ArrayList<>();
    Spinner spAcademicYearSeedMoney;
    CustomEditText etDurationSeedMoney,etAmountOfSeedMoney,etPurposeSeedMoney;
    LinearLayout ll_submit_cancel_seed_money,ll_update_seed_money;
    CustomBoldTextView tvSubmitSeedMoney,tvCancelSeedMoney,tvUpdateSeedMoney,tvCancelSeedMoneyUpdate;
    ReqViewseedMoneyPojo.DataBean reqViewseedMoneyPojo;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_seed_received_from_university);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();
    }

    private void initView(){
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Request Seed Money");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        spAcademicYearSeedMoney = findViewById(R.id.spAcademicYearSeedMoney);
        etDurationSeedMoney = findViewById(R.id.etDurationSeedMoney);
        etAmountOfSeedMoney = findViewById(R.id.etAmountOfSeedMoney);
        etPurposeSeedMoney = findViewById(R.id.etPurposeSeedMoney);

        academicYearOpetion.add("Select Year");

        tvSubmitSeedMoney = findViewById(R.id.tvSubmitSeedMoney);
        tvSubmitSeedMoney.setOnClickListener(this);
        tvCancelSeedMoney = findViewById(R.id.tvCancelSeedMoney);
        tvCancelSeedMoney.setOnClickListener(this);
        tvUpdateSeedMoney = findViewById(R.id.tvUpdateSeedMoney);
        tvUpdateSeedMoney.setOnClickListener(this);
        tvCancelSeedMoneyUpdate = findViewById(R.id.tvCancelSeedMoneyUpdate);
        tvCancelSeedMoneyUpdate.setOnClickListener(this);

        ll_submit_cancel_seed_money = findViewById(R.id.ll_submit_cancel_seed_money);
        ll_update_seed_money = findViewById(R.id.ll_update_seed_money);

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
        if (view.getId() == R.id.iv_back){
            onBackPressed();
        }else if (view.getId() == R.id.tvSubmitSeedMoney){
            if (isValid()){

                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearSeedMoney.getSelectedItemPosition())));
                String amount = etAmountOfSeedMoney.getText().toString();
                String grant_duration = etDurationSeedMoney.getText().toString();
                String purpose = etPurposeSeedMoney.getText().toString();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();

                callSaveSeddMoneyAPI(year_id,amount,grant_duration,purpose,user_id,ip,emp_id);
            }
        }else if (view.getId() == R.id.tvCancelSeedMoney){
            onBackPressed();
        }else if (view.getId() == R.id.tvUpdateSeedMoney){
            if (isValid()){
                if (ll_update_seed_money.getVisibility() == View.VISIBLE){

                    if (reqViewseedMoneyPojo != null){

                        String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearSeedMoney.getSelectedItemPosition())));
                        String amount = etAmountOfSeedMoney.getText().toString();
                        String grant_duration = etDurationSeedMoney.getText().toString();
                        String purpose = etPurposeSeedMoney.getText().toString();
                        String user_id = mySharedPreferecesRKUOLD.getUserID();
                        String ip = "0";
                        String emp_id = mySharedPreferecesRKUOLD.getEmpID();

                        callUpdateSeddMoneyAPI(String.valueOf(reqViewseedMoneyPojo.getId()),year_id,amount,grant_duration,purpose,user_id,ip,emp_id);
                    }

                }
            }
        }else if (view.getId() == R.id.tvCancelSeedMoneyUpdate){
            onBackPressed();
        }
    }


    private boolean isValid(){

        if (academicYearOpetion.get(spAcademicYearSeedMoney.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etDurationSeedMoney.getText().toString())) {
            DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, "Enter Duration in month");
            return false;
        } else if (TextUtils.isEmpty(etAmountOfSeedMoney.getText().toString())) {
            DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, "Enter Amount of seed money (Rs.)");
            return false;
        } else if (TextUtils.isEmpty(etPurposeSeedMoney.getText().toString())) {
            DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, "Enter Purpose");
            return false;
        }

        return true;
    }


    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqSeedReceivedFromUniversityActivity.this, "");
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
//                            ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, academicYearOpetion);
//                            spAcademicYearSeedMoney.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqSeedReceivedFromUniversityActivity.this, academicYearOpetion);
                            spAcademicYearSeedMoney.setAdapter(academicYearAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_SEED_MONEY_POJO)){
                                ll_submit_cancel_seed_money.setVisibility(View.GONE);
                                ll_update_seed_money.setVisibility(View.VISIBLE);
                                reqViewseedMoneyPojo = (ReqViewseedMoneyPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_SEED_MONEY_POJO);

                                if (reqViewseedMoneyPojo != null){
                                    spAcademicYearSeedMoney.setSelection(academicYearOpetion.indexOf(reqViewseedMoneyPojo.getYear_name()));
                                    etDurationSeedMoney.setText(reqViewseedMoneyPojo.getSm_grant_duration());
                                    etAmountOfSeedMoney.setText(String.valueOf(reqViewseedMoneyPojo.getSm_amount()));
                                    etPurposeSeedMoney.setText(reqViewseedMoneyPojo.getSm_purpose());
                                }
                            }else {
                                spAcademicYearSeedMoney.setSelection(academicYearOpetion.indexOf(0));
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
                DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }


    private void callSaveSeddMoneyAPI(final String year_id, final String amount, final String grant_duration, final String purpose, final String user_id, final String ip, final String emp_id){


        DialogUtils.showProgressDialog(ReqSeedReceivedFromUniversityActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_Seed_money, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, responsePojo.getData().get(0).getMsg());

                    Intent intent = new Intent(ReqSeedReceivedFromUniversityActivity.this, ReqSeedMoneyListActivity.class);
                    setResult(RESULT_OK,intent);
                    onBackPressed();
//                    Toast.makeText(ReqSeedReceivedFromUniversityActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("year_id", year_id);
                params2.put("amount", amount);
                params2.put("grant_duration", grant_duration);
                params2.put("purpose", purpose);
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


    private void callUpdateSeddMoneyAPI(final String id,final String year_id, final String amount, final String grant_duration, final String purpose, final String user_id, final String ip, final String emp_id){


        DialogUtils.showProgressDialog(ReqSeedReceivedFromUniversityActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_Seed_money, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {

                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, responsePojo.getData().get(0).getMsg());

                    Intent intent = new Intent(ReqSeedReceivedFromUniversityActivity.this, ReqSeedMoneyListActivity.class);
                    setResult(RESULT_OK,intent);
                    onBackPressed();
//                    Toast.makeText(ReqSeedReceivedFromUniversityActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqSeedReceivedFromUniversityActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id",id);
                params2.put("year_id", year_id);
                params2.put("amount", amount);
                params2.put("grant_duration", grant_duration);
                params2.put("purpose", purpose);
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
