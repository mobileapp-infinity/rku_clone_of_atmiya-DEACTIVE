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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewConsultancyRequestPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.Hashtable;
import java.util.Map;

public class ReqViewConsultancyActivity extends AppCompatActivity implements View.OnClickListener{

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomTextView tvAcademicYearConsultancyReqView,tvNameProjectConsultanycRewView,tvConsultingAgencyView,tvRevenueConsultancyReqView,tvStatusConsultancyReqView;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack;
    RequestQueue queue;
    String CONSULTANCY_STATUS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_view_consultancy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.ID_VIEW_CONSULTANCY)){
            callViewSeedMoneydReq(getIntent().getStringExtra(IntentConstants.ID_VIEW_CONSULTANCY));
        }

    }

    private void initView(){


        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Consultancy Request Details");


        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvAcademicYearConsultancyReqView = findViewById(R.id.tvAcademicYearConsultancyReqView);
        tvNameProjectConsultanycRewView = findViewById(R.id.tvNameProjectConsultanycRewView);
        tvConsultingAgencyView = findViewById(R.id.tvConsultingAgencyView);
        tvRevenueConsultancyReqView = findViewById(R.id.tvRevenueConsultancyReqView);
        tvStatusConsultancyReqView = findViewById(R.id.tvStatusConsultancyReqView);

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



    private void callViewSeedMoneydReq(final String id){
        DialogUtils.showProgressDialog(ReqViewConsultancyActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_Consultancy_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                    ReqViewConsultancyRequestPojo reqViewConsultancyRequestPojo = gson.fromJson("{\"Data\":" + response + "}",ReqViewConsultancyRequestPojo.class);
                    if (reqViewConsultancyRequestPojo .getData() != null && reqViewConsultancyRequestPojo .getData().size() > 0){

                        CONSULTANCY_STATUS = getIntent().getStringExtra(IntentConstants.CONSULTANCY_STATUS);

                        tvAcademicYearConsultancyReqView.setText(reqViewConsultancyRequestPojo.getData().get(0).getYear_name());
                        tvNameProjectConsultanycRewView.setText(reqViewConsultancyRequestPojo.getData().get(0).getCon_project_name());
                        tvConsultingAgencyView.setText(String.valueOf(reqViewConsultancyRequestPojo.getData().get(0).getCon_contect_details()));
                        tvRevenueConsultancyReqView.setText(String.valueOf(reqViewConsultancyRequestPojo.getData().get(0).getCon_revenue_gerented()));
                        tvStatusConsultancyReqView.setText(CONSULTANCY_STATUS);

                    }else {
                        DialogUtils.Show_Toast(ReqViewConsultancyActivity.this, "No Data Available");
                        finish();
                    }
                }else {
                    DialogUtils.Show_Toast(ReqViewConsultancyActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqViewConsultancyActivity.this, "Please Try Again Later");
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
