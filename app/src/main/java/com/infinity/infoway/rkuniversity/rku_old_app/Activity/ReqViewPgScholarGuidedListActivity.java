package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

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
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewPgScholarGuidedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.Hashtable;
import java.util.Map;

public class ReqViewPgScholarGuidedListActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomTextView tvAcademicYearPGScholarView,tvNameofPGScholarsGuidedView,tvDomainOfResearchPGScholarGuideView,tvYearOfAwardedPGScholarGuidedreq,tvNameOfOtherGuidPGScholarGuidedView,
            tvYearofRegistrationPGScholarGuidedView,tvStatusPGScholrGuidedView;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_view_pg_scholar_guided_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.ID_VIEW_PG_SCHOLAR)){
            callViewPGScholarGuidedReq(getIntent().getStringExtra(IntentConstants.ID_VIEW_PG_SCHOLAR));
        }

    }

    private void initView(){

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("PG Scholar Guided Details");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvAcademicYearPGScholarView = findViewById(R.id.tvAcademicYearPGScholarView);
        tvNameofPGScholarsGuidedView = findViewById(R.id.tvNameofPGScholarsGuidedView);
        tvDomainOfResearchPGScholarGuideView = findViewById(R.id.tvDomainOfResearchPGScholarGuideView);
        tvYearOfAwardedPGScholarGuidedreq = findViewById(R.id.tvYearOfAwardedPGScholarGuidedreq);
        tvNameOfOtherGuidPGScholarGuidedView = findViewById(R.id.tvNameOfOtherGuidPGScholarGuidedView);
        tvYearofRegistrationPGScholarGuidedView = findViewById(R.id.tvYearofRegistrationPGScholarGuidedView);
        tvStatusPGScholrGuidedView = findViewById(R.id.tvStatusPGScholrGuidedView);


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
        }
    }


    private void callViewPGScholarGuidedReq(final String id){
        DialogUtils.showProgressDialog(ReqViewPgScholarGuidedListActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_PG_Scholars_Guided_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                    ReqViewPgScholarGuidedPojo reqViewPgScholarGuidedPojo = gson.fromJson("{\"Data\":" + response + "}",ReqViewPgScholarGuidedPojo.class);
                    if (reqViewPgScholarGuidedPojo.getData() != null){

                        tvAcademicYearPGScholarView.setText(reqViewPgScholarGuidedPojo.getData().get(0).getYear_name());
                        tvNameofPGScholarsGuidedView.setText(reqViewPgScholarGuidedPojo.getData().get(0).getPgs_scholar_name());

                        tvNameOfOtherGuidPGScholarGuidedView.setText(String.valueOf(reqViewPgScholarGuidedPojo.getData().get(0).getPgs_scholar_guide_name()));
                        tvDomainOfResearchPGScholarGuideView.setText(reqViewPgScholarGuidedPojo.getData().get(0).getPgs_research_title());
                        tvYearofRegistrationPGScholarGuidedView.setText(String.valueOf(reqViewPgScholarGuidedPojo.getData().get(0).getPgs_reg_year()));
                        tvYearOfAwardedPGScholarGuidedreq.setText(String.valueOf(reqViewPgScholarGuidedPojo.getData().get(0).getPgs_award_year()));
                        tvStatusPGScholrGuidedView.setText(String.valueOf(reqViewPgScholarGuidedPojo.getData().get(0).getPgs_status()));
                    }
                }else {
                    DialogUtils.Show_Toast(ReqViewPgScholarGuidedListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqViewPgScholarGuidedListActivity.this, "Please Try Again Later");
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
