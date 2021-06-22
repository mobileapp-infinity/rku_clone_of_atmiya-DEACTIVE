package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewCommitteeByIdPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.Hashtable;
import java.util.Map;

public class ViewCommitteeDetailsActivity extends AppCompatActivity implements View.OnClickListener{


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomTextView tvNameOfCommittee,tvRolesandResponsibilitiesCoordinator,
            tvMainCoordinatorCommittee,tvRolesandResponsibilitiesMember;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_committee_details);

        initView();

        if (getIntent().hasExtra(IntentConstants.ID_VIEW_COMMITTEE_DETAILS)){
            callViewCommitteDetails(getIntent().getStringExtra(IntentConstants.ID_VIEW_COMMITTEE_DETAILS));
        }
    }



    private void initView(){


        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Committee Details");


        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvNameOfCommittee = findViewById(R.id.tvNameOfCommittee);
        tvRolesandResponsibilitiesCoordinator = findViewById(R.id.tvRolesandResponsibilitiesCoordinator);
        tvMainCoordinatorCommittee = findViewById(R.id.tvMainCoordinatorCommittee);
        tvRolesandResponsibilitiesMember = findViewById(R.id.tvRolesandResponsibilitiesMember);

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

    private void callViewCommitteDetails(final String id){
        DialogUtils.showProgressDialog(ViewCommitteeDetailsActivity.this, "");
        StringRequest viewCommitteDetails = new StringRequest(Request.Method.POST, URLS.View_by_id_Committee, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                    ViewCommitteeByIdPojo reqViewcommitteDetailsPojo = gson.fromJson("{\"Data\":" + response + "}",ViewCommitteeByIdPojo.class);
                    if (reqViewcommitteDetailsPojo.getData() != null && reqViewcommitteDetailsPojo.getData().size() > 0){

                        tvNameOfCommittee.setText(reqViewcommitteDetailsPojo.getData().get(0).getCommittee_name());
                        tvRolesandResponsibilitiesCoordinator.setText(reqViewcommitteDetailsPojo.getData().get(0).getRolls_responsibility_coordinatior());
                        tvMainCoordinatorCommittee.setText(String.valueOf(reqViewcommitteDetailsPojo.getData().get(0).getMain_coordinatior()));
                        tvRolesandResponsibilitiesMember.setText(reqViewcommitteDetailsPojo.getData().get(0).getRolls_ressponsibility_member());
                    }else {
                        DialogUtils.Show_Toast(ViewCommitteeDetailsActivity.this, "No Data Available");
                        finish();
                    }
                }else {
                    DialogUtils.Show_Toast(ViewCommitteeDetailsActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ViewCommitteeDetailsActivity.this, "Please Try Again Later");
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
        viewCommitteDetails.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(viewCommitteDetails);
    }

}
