package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewGRantReceivedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.Hashtable;
import java.util.Map;

public class ReqViewGrantReceivedActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomTextView tvAcademicYearGrantReceivedView,tvNameofProjectGrantReceivedView,tvAgencyTypeGrantReceivedView,
            tvFundGrantReceivedView,tvStatusGrantReceivedView,tvInvestigatorReceivedView,
            tvYearAwardGrantReceivedView,tvAgencyNameGrantReceivedView,tvDurationNameGrantReceivedView;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_view_grant_received);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.ID_VIEW_GRANT_RECEIVED)){
                callViewGrantReceivedReq(getIntent().getStringExtra(IntentConstants.ID_VIEW_GRANT_RECEIVED));
        }

    }

    private void initView(){


        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Grant Received Details");


        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvAcademicYearGrantReceivedView = findViewById(R.id.tvAcademicYearGrantReceivedView);
        tvNameofProjectGrantReceivedView = findViewById(R.id.tvNameofProjectGrantReceivedView);
        tvAgencyTypeGrantReceivedView = findViewById(R.id.tvAgencyTypeGrantReceivedView);
        tvFundGrantReceivedView = findViewById(R.id.tvFundGrantReceivedView);
        tvStatusGrantReceivedView = findViewById(R.id.tvStatusGrantReceivedView);
        tvInvestigatorReceivedView = findViewById(R.id.tvInvestigatorReceivedView);
        tvYearAwardGrantReceivedView = findViewById(R.id.tvYearAwardGrantReceivedView);
        tvAgencyNameGrantReceivedView = findViewById(R.id.tvAgencyNameGrantReceivedView);
        tvDurationNameGrantReceivedView = findViewById(R.id.tvDurationNameGrantReceivedView);


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
        }
    }


    private void callViewGrantReceivedReq(final String id){
        DialogUtils.showProgressDialog(ReqViewGrantReceivedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_Grant_Received_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                    ReqViewGRantReceivedPojo reqViewGRantReceivedPojo= gson.fromJson("{\"Data\":" + response + "}",ReqViewGRantReceivedPojo.class);
                    if (reqViewGRantReceivedPojo.getData() != null){

                        tvAcademicYearGrantReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getYear_name());
                        tvNameofProjectGrantReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getGr_project_name());
                        tvAgencyTypeGrantReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getGr_agency_name());
                        tvFundGrantReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getGr_funds_provided());
                        tvStatusGrantReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getGr_status());
                        tvInvestigatorReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getGr_principal_investigator());
                        tvYearAwardGrantReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getGr_award_year());
                        tvAgencyNameGrantReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getGr_agency_name());
                        tvDurationNameGrantReceivedView.setText(reqViewGRantReceivedPojo.getData().get(0).getGr_project_duration());
                    }
                }else {
                    DialogUtils.Show_Toast(ReqViewGrantReceivedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqViewGrantReceivedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
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
