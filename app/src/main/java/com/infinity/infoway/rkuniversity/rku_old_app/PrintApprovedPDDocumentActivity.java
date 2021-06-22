package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;

public class PrintApprovedPDDocumentActivity extends AppCompatActivity implements View.OnClickListener {

    CustomTextView tvFileNamePDApprovalDocView;
    ImageView iv_download_pd_approval_doc, iv_view_pd_approval_doc;

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;

    String FILE_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_approved_pddocument);
    }

    private void initView() {

        marshMallowPermission = new MarshMallowPermission(PrintApprovedPDDocumentActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Download Or View PD Approval Document");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvFileNamePDApprovalDocView = findViewById(R.id.tvFileNamePDApprovalDocView);
        iv_download_pd_approval_doc = findViewById(R.id.iv_download_pd_approval_doc);
        iv_download_pd_approval_doc.setOnClickListener(this);
        iv_view_pd_approval_doc = findViewById(R.id.iv_view_pd_approval_doc);
        iv_view_pd_approval_doc.setOnClickListener(this);

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

        if (v.getId() == R.id.iv_download_pd_approval_doc){

        }else if (v.getId() == R.id.iv_view_pd_approval_doc){

        }else if (v.getId() == R.id.iv_back){
            onBackPressed();
        }
    }

    private void getUrlOfPDApprovedDoc() {
        DialogUtils.showProgressDialog(PrintApprovedPDDocumentActivity.this, "");
        String url = URLS.Print_of_Approved_record_by_id_PD_Application;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PrintApprovedPDDocumentActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

}
