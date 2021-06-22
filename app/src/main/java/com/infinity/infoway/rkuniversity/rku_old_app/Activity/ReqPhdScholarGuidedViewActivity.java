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
import android.widget.LinearLayout;

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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewPhdScholarGuidedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.Hashtable;
import java.util.Map;

public class ReqPhdScholarGuidedViewActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    RequestQueue queue;

    CustomTextView tvAcademicYearPhdScholarReq,tvNameofPhDScholarsGuidedReq,tvNameofOtherPhdScholarGuideReq,tvYearOfRegistrationPhdScholarGuidedreq,
            tvStatusPhdScholarGuidedView,tvDepartmentPhdscholarGuidedView,tvTitleDomainofResearchPhdScholrGuidedView,tvYearofAwardedPhdScholarGuidedView;

    LinearLayout llYearofAwardedViewPhdScolar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_phd_scholar_guided_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.ID_VIEW_PHD_SCHOLAR)){
            callViewPhdScholarGuidedAPI(getIntent().getStringExtra(IntentConstants.ID_VIEW_PHD_SCHOLAR));
        }

    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("PhD Scholars Guided Details");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);


        tvAcademicYearPhdScholarReq = findViewById(R.id.tvAcademicYearPhdScholarReq);
        tvNameofPhDScholarsGuidedReq = findViewById(R.id.tvNameofPhDScholarsGuidedReq);
        tvNameofOtherPhdScholarGuideReq = findViewById(R.id.tvNameofOtherPhdScholarGuideReq);
        tvYearOfRegistrationPhdScholarGuidedreq = findViewById(R.id.tvYearOfRegistrationPhdScholarGuidedreq);
        tvStatusPhdScholarGuidedView = findViewById(R.id.tvStatusPhdScholarGuidedView);
        tvDepartmentPhdscholarGuidedView = findViewById(R.id.tvDepartmentPhdscholarGuidedView);
        tvTitleDomainofResearchPhdScholrGuidedView = findViewById(R.id.tvTitleDomainofResearchPhdScholrGuidedView);
        tvYearofAwardedPhdScholarGuidedView = findViewById(R.id.tvYearofAwardedPhdScholarGuidedView);
        llYearofAwardedViewPhdScolar = findViewById(R.id.llYearofAwardedViewPhdScolar);

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


    private void callViewPhdScholarGuidedAPI(final String id){
        DialogUtils.showProgressDialog(ReqPhdScholarGuidedViewActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_PhD_Scholars_Guided_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                    ReqViewPhdScholarGuidedPojo reqPhdScholarGuidedListPojo = gson.fromJson("{\"Data\":" + response + "}", ReqViewPhdScholarGuidedPojo.class);
                    if (reqPhdScholarGuidedListPojo.getData() != null){
                        tvAcademicYearPhdScholarReq.setText(reqPhdScholarGuidedListPojo.getData().get(0).getYear_name());
                        tvNameofPhDScholarsGuidedReq.setText(reqPhdScholarGuidedListPojo.getData().get(0).getPhdsg_scholar_name());
                        tvNameofOtherPhdScholarGuideReq.setText(reqPhdScholarGuidedListPojo.getData().get(0).getPhdsg_guide_name());
                        tvYearOfRegistrationPhdScholarGuidedreq.setText(String.valueOf(reqPhdScholarGuidedListPojo.getData().get(0).getPhdsg_scholar_reg_year()));
                        tvStatusPhdScholarGuidedView.setText(reqPhdScholarGuidedListPojo.getData().get(0).getPhdsg_status());
                        tvDepartmentPhdscholarGuidedView.setText(reqPhdScholarGuidedListPojo.getData().get(0).getPhdsg_dept_name());
                        tvTitleDomainofResearchPhdScholrGuidedView.setText(reqPhdScholarGuidedListPojo.getData().get(0).getPhdsg_thesis_title());

                        if (!String.valueOf(reqPhdScholarGuidedListPojo.getData().get(0).getPhdsg_award_year()).trim().equalsIgnoreCase("0")){
                            tvYearofAwardedPhdScholarGuidedView.setText(String.valueOf(reqPhdScholarGuidedListPojo.getData().get(0).getPhdsg_award_year()));
                        }else {
                            llYearofAwardedViewPhdScolar.setVisibility(View.GONE);
                        }

                    }
                }else {
                    DialogUtils.Show_Toast(ReqPhdScholarGuidedViewActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPhdScholarGuidedViewActivity.this, "Please Try Again Later");
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
