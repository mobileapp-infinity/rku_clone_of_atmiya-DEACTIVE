package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.app.DatePickerDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetResignationUserWiseDetailsPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

public class RequestForResignationActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;

    CustomEditText etReasonForResignation, etNoticePeriod;
    CustomTextView tvResignationDate;
    ImageView ivCalenderResignation;

    LinearLayout ll_submit_cancel_resignation, ll_update_resignation;
    CustomBoldTextView tvSubmitResignation, tvCancelResignation;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_resignation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        callGetResignationWiseUserAPI(mySharedPreferecesRKUOLD.getUserID());
    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Request For Resignation");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        myCalendar = Calendar.getInstance();

        etReasonForResignation = findViewById(R.id.etReasonForResignation);
        etNoticePeriod = findViewById(R.id.etNoticePeriod);
        tvResignationDate = findViewById(R.id.tvResignationDate);
        ivCalenderResignation = findViewById(R.id.ivCalenderResignation);
        ivCalenderResignation.setOnClickListener(this);

        tvSubmitResignation = findViewById(R.id.tvSubmitResignation);
        tvSubmitResignation.setOnClickListener(this);
        tvCancelResignation = findViewById(R.id.tvCancelResignation);
        tvCancelResignation.setOnClickListener(this);

        ll_submit_cancel_resignation = findViewById(R.id.ll_submit_cancel_resignation);

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

    private boolean isValid() {

        if (TextUtils.isEmpty(etReasonForResignation.getText().toString().trim())) {
            DialogUtils.Show_Toast(RequestForResignationActivity.this, "Enter Reason");
            return false;
        } else if (TextUtils.isEmpty(tvResignationDate.getText().toString().trim())) {
            DialogUtils.Show_Toast(RequestForResignationActivity.this, "Enter Resignation Date");
            return false;
        } else if (TextUtils.isEmpty(etNoticePeriod.getText().toString().trim())) {
            DialogUtils.Show_Toast(RequestForResignationActivity.this, "Enter Notice Period");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back || view.getId() == R.id.tvCancelResignation) {
            onBackPressed();
        }  else if (view.getId() == R.id.tvSubmitResignation) {
            if (isValid()) {

                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String reason = etReasonForResignation.getText().toString();
                String resignation_date = tvResignationDate.getText().toString();
                String notie_period = etNoticePeriod.getText().toString();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";

                callRequestForResignationAPI(emp_id,reason,resignation_date,notie_period,user_id,ip);

            }
        } else if (view.getId() == R.id.ivCalenderResignation) {
            final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat1 = "dd/MM/yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
                    tvResignationDate.setText(sdf.format(myCalendar.getTime()) + "");

                }
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(RequestForResignationActivity.this, date1, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
//            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
    }



    private void callGetResignationWiseUserAPI(final String user_id){

        DialogUtils.showProgressDialog(RequestForResignationActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_resignation_user_wise_Details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {

                    Gson gson = new Gson();
                    GetResignationUserWiseDetailsPojo getResignationUserWiseDetailsPojo= gson.fromJson("{\"Data\":" + response + "}", GetResignationUserWiseDetailsPojo.class);

                    if (getResignationUserWiseDetailsPojo.getData() != null && getResignationUserWiseDetailsPojo.getData().size() > 0){
                        GetResignationUserWiseDetailsPojo.DataBean  dataBean = getResignationUserWiseDetailsPojo.getData().get(0);
                        ll_submit_cancel_resignation.setVisibility(View.GONE);

                        etReasonForResignation.setText(dataBean.getEmp_resi_reason());
                        tvResignationDate.setText(String.valueOf(dataBean.getEmp_resi_date()));
                        etNoticePeriod.setText(String.valueOf(dataBean.getEmp_notice_period()));

                        etReasonForResignation.setEnabled(false);
                        ivCalenderResignation.setEnabled(false);
                        etNoticePeriod.setEnabled(false);

                    }else {
                        etReasonForResignation.setEnabled(true);
                        ivCalenderResignation.setEnabled(true);
                        etNoticePeriod.setEnabled(true);
                        ll_submit_cancel_resignation.setVisibility(View.VISIBLE);
                    }

                } else {
                    etReasonForResignation.setEnabled(true);
                    ivCalenderResignation.setEnabled(true);
                    etNoticePeriod.setEnabled(true);
                    ll_submit_cancel_resignation.setVisibility(View.VISIBLE);
                    DialogUtils.Show_Toast(RequestForResignationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(RequestForResignationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                finish();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("user_id", user_id);
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



    private void callRequestForResignationAPI(final String emp_id, final String reason,
                                              final String resignation_date,
                                              final String notie_period, final String user_id,
                                              final String ip){

        DialogUtils.showProgressDialog(RequestForResignationActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Apply_resignation_process, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(RequestForResignationActivity.this, responsePojo.getData().get(0).getMsg());
                    onBackPressed();
//                    Toast.makeText(ReqSeedReceivedFromUniversityActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(RequestForResignationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(RequestForResignationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_id", emp_id);
                params2.put("reason", reason);
                params2.put("resignation_date", resignation_date);
                params2.put("notie_period", notie_period);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
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
