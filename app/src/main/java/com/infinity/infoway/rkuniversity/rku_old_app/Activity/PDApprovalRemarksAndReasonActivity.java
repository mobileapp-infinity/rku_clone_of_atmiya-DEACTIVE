package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.PDApprovalRemarksAndReasonListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PDApprovalRemarksAndReasonListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.PDApprovalRemarksAndReasonListModel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class PDApprovalRemarksAndReasonActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rvDownloadDocumentPDApprovalAct;
    LinearLayout ll_pd_approval_remarks_and_reason_list;
    CustomBoldTextView tvEmployeeNamePDApprovalRemarksAct;
    CustomTextView tvReasonPDApprovalRemarksAct, tvPDFrameworkCreditPDApprovalRemarksAct,
            tvNoOfOnDutyLeavesPDApprovalRemarksAct, tvLeaveFromDatePDApprovalRemarksAct,
            tvLeaveToDatePDApprovalRemarksAct, tvRegistrationFeesPDApprovalRemarksAct,
            tvTransportationPDApprovalRemarksAct, tvAccommodationPDApprovalRemarksAct,
            tvLeaveExpensePDApprovalRemarksAct, tvTotalExpensePDApprovalRemarksAct,
            tvSanctionedExpensePDApprovalRemarksAct;

    RecyclerView.LayoutManager layoutManager;
    PDApprovalRemarksAndReasonListAdapter pdApprovalRemarksAndReasonListAdapter;
    ArrayList<PDApprovalRemarksAndReasonListModel> approvalRemarksAndReasonListModelArrayList;

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdapproval_remarks_and_reason);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.PD_APPROVAL_REMARK_ID)) {
            callViewPDApprovalRemarksAndReasonAPI(getIntent().getStringExtra(IntentConstants.PD_APPROVAL_REMARK_ID));
        } else {
            Toast.makeText(this, "Remarks or Reason id not found!!", Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {

        marshMallowPermission = new MarshMallowPermission(PDApprovalRemarksAndReasonActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("PD Application Remarks/Reason");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvEmployeeNamePDApprovalRemarksAct = findViewById(R.id.tvEmployeeNamePDApprovalRemarksAct);
        tvReasonPDApprovalRemarksAct = findViewById(R.id.tvReasonPDApprovalRemarksAct);
        tvPDFrameworkCreditPDApprovalRemarksAct = findViewById(R.id.tvPDFrameworkCreditPDApprovalRemarksAct);
        tvNoOfOnDutyLeavesPDApprovalRemarksAct = findViewById(R.id.tvNoOfOnDutyLeavesPDApprovalRemarksAct);
        tvLeaveFromDatePDApprovalRemarksAct = findViewById(R.id.tvLeaveFromDatePDApprovalRemarksAct);
        tvLeaveToDatePDApprovalRemarksAct = findViewById(R.id.tvLeaveToDatePDApprovalRemarksAct);
        tvRegistrationFeesPDApprovalRemarksAct = findViewById(R.id.tvRegistrationFeesPDApprovalRemarksAct);
        tvTransportationPDApprovalRemarksAct = findViewById(R.id.tvTransportationPDApprovalRemarksAct);
        tvAccommodationPDApprovalRemarksAct = findViewById(R.id.tvAccommodationPDApprovalRemarksAct);
        tvLeaveExpensePDApprovalRemarksAct = findViewById(R.id.tvLeaveExpensePDApprovalRemarksAct);
        tvTotalExpensePDApprovalRemarksAct = findViewById(R.id.tvTotalExpensePDApprovalRemarksAct);
        tvSanctionedExpensePDApprovalRemarksAct = findViewById(R.id.tvSanctionedExpensePDApprovalRemarksAct);

        ll_pd_approval_remarks_and_reason_list = findViewById(R.id.ll_pd_approval_remarks_and_reason_list);
        rvDownloadDocumentPDApprovalAct = findViewById(R.id.rvDownloadDocumentPDApprovalAct);

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
        }
    }

    private void callViewPDApprovalRemarksAndReasonAPI(final String id) {
        DialogUtils.showProgressDialog(PDApprovalRemarksAndReasonActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_i_hrhr_PD_approval_remark, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    PDApprovalRemarksAndReasonListPojo pdApprovalRemarksAndReasonListPojo = gson.fromJson("{\"Data\":" + response + "}", PDApprovalRemarksAndReasonListPojo.class);
                    if (pdApprovalRemarksAndReasonListPojo.getData() != null && pdApprovalRemarksAndReasonListPojo.getData().size() > 0) {

                        if (pdApprovalRemarksAndReasonListPojo.getData().size() == 1) {
                            ll_pd_approval_remarks_and_reason_list.setVisibility(View.GONE);

                            PDApprovalRemarksAndReasonListPojo.DataBean dataBean = pdApprovalRemarksAndReasonListPojo.getData().get(0);

                            tvEmployeeNamePDApprovalRemarksAct.setText(dataBean.getApprove_by());
                            tvReasonPDApprovalRemarksAct.setText(dataBean.getPdar_remarks());
                            tvPDFrameworkCreditPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPdar_credit()));
                            tvNoOfOnDutyLeavesPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPdar_duty_leaves()));
                            tvLeaveFromDatePDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_leave_from_date()));
                            tvLeaveToDatePDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_to_date()));
                            tvRegistrationFeesPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_registration_fees()));
                            tvTransportationPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_transportation()));
                            tvAccommodationPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_accommodation()));
                            tvLeaveExpensePDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_leave_expense()));
                            tvTotalExpensePDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_total_expense()));
                            tvSanctionedExpensePDApprovalRemarksAct.setText(String.valueOf(dataBean.getSanctioned_expense()));


                        } else {

                            try {
                                ll_pd_approval_remarks_and_reason_list.setVisibility(View.VISIBLE);
                                approvalRemarksAndReasonListModelArrayList = new ArrayList<>();

                                for (int i = 0; i < pdApprovalRemarksAndReasonListPojo.getData().size() - 1; i++) {
                                    PDApprovalRemarksAndReasonListModel pdApprovalRemarksAndReasonListModel = new PDApprovalRemarksAndReasonListModel();
                                    pdApprovalRemarksAndReasonListModel.setEmployeeName(String.valueOf(pdApprovalRemarksAndReasonListPojo.getData().get(i).getApprove_by()));
                                    pdApprovalRemarksAndReasonListModel.setReason(String.valueOf(pdApprovalRemarksAndReasonListPojo.getData().get(i).getPdar_remarks()));
                                    pdApprovalRemarksAndReasonListModel.setPdFrameworkCredit(String.valueOf(pdApprovalRemarksAndReasonListPojo.getData().get(i).getPdar_credit()));
                                    pdApprovalRemarksAndReasonListModel.setNoOfOnDutyLeaves(String.valueOf(pdApprovalRemarksAndReasonListPojo.getData().get(i).getPdar_duty_leaves()));

                                    approvalRemarksAndReasonListModelArrayList.add(pdApprovalRemarksAndReasonListModel);
                                }
                                layoutManager = new LinearLayoutManager(PDApprovalRemarksAndReasonActivity.this);
                                rvDownloadDocumentPDApprovalAct.setLayoutManager(layoutManager);
                                pdApprovalRemarksAndReasonListAdapter = new PDApprovalRemarksAndReasonListAdapter(PDApprovalRemarksAndReasonActivity.this, approvalRemarksAndReasonListModelArrayList);
                                rvDownloadDocumentPDApprovalAct.setAdapter(pdApprovalRemarksAndReasonListAdapter);

                                PDApprovalRemarksAndReasonListPojo.DataBean dataBean = pdApprovalRemarksAndReasonListPojo.getData().get(pdApprovalRemarksAndReasonListPojo.getData().size() - 1);

                                tvEmployeeNamePDApprovalRemarksAct.setText(dataBean.getApprove_by());
                                tvReasonPDApprovalRemarksAct.setText(dataBean.getPdar_remarks());
                                tvPDFrameworkCreditPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPdar_credit()));
                                tvNoOfOnDutyLeavesPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPdar_duty_leaves()));
                                tvLeaveFromDatePDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_leave_from_date()));
                                tvLeaveToDatePDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_to_date()));
                                tvRegistrationFeesPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_registration_fees()));
                                tvTransportationPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_transportation()));
                                tvAccommodationPDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_accommodation()));
                                tvLeaveExpensePDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_leave_expense()));
                                tvTotalExpensePDApprovalRemarksAct.setText(String.valueOf(dataBean.getPd_final_total_expense()));
                                tvSanctionedExpensePDApprovalRemarksAct.setText(String.valueOf(dataBean.getSanctioned_expense()));


                            } catch (Exception ex) {
                                Toast.makeText(PDApprovalRemarksAndReasonActivity.this, "Exception:-" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        Toast.makeText(PDApprovalRemarksAndReasonActivity.this, "Remarks or Reason is Empty or Null", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    DialogUtils.Show_Toast(PDApprovalRemarksAndReasonActivity.this, response);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDApprovalRemarksAndReasonActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("user_id", mySharedPreferecesRKUOLD.getUserID());
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
