package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetPDApplicationListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqFDPGetEventCategoryPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqFDPGetEventTypePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class PDUpdateCreditActivity extends AppCompatActivity implements View.OnClickListener {

    CustomEditText etEventCreditPDUpdateCredit, etNameOfTheEventPDUpdateCredit,
            etCityOfTheEventPDUpdateCredit, etOrganizedByPDUpdateCredit;
    Spinner spEventCategoryPDUpdateCredit, spEventTypePDUpdateCredit;
    CustomBoldTextView tvUpdatePDUpdateCredit, tvCancelPDUpdateCredit;

    HashMap<String, Integer> hashMapEventTypeAndID = new HashMap<>();
    HashMap<String, Integer> hashMapEventCategoryAndID = new HashMap<>();

    ArrayList<String> eventTypeOpetion = new ArrayList<>();
    ArrayList<String> eventCategoryOpetion = new ArrayList<>();

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    GetPDApplicationListPojo.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdupdate_credit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getEventType("1", "0");

        if (getIntent().hasExtra(IntentConstants.PD_APPLICATION_LIST_ITEM)) {
            dataBean = (GetPDApplicationListPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.PD_APPLICATION_LIST_ITEM);
        }

    }

    private boolean isValid() {

        if (TextUtils.isEmpty(etEventCreditPDUpdateCredit.getText().toString())) {
            DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Enter Event Credit");
            return false;
        } else if (eventCategoryOpetion.get(spEventCategoryPDUpdateCredit.getSelectedItemPosition()).equalsIgnoreCase("Select Category")) {
            DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Select Category");
            return false;
        } else if (eventTypeOpetion.get(spEventTypePDUpdateCredit.getSelectedItemPosition()).equalsIgnoreCase("Select Type")) {
            DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Select Event Type");
            return false;
        } else if (TextUtils.isEmpty(etNameOfTheEventPDUpdateCredit.getText().toString())) {
            DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Enter Name of the event");
            return false;
        } else if (TextUtils.isEmpty(etCityOfTheEventPDUpdateCredit.getText().toString())) {
            DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Enter City of the event");
            return false;
        } else if (TextUtils.isEmpty(etOrganizedByPDUpdateCredit.getText().toString())) {
            DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Enter Name of Organized by");
            return false;
        }

        return true;
    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("PD Update");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        etEventCreditPDUpdateCredit = findViewById(R.id.etEventCreditPDUpdateCredit);
        etNameOfTheEventPDUpdateCredit = findViewById(R.id.etNameOfTheEventPDUpdateCredit);
        etCityOfTheEventPDUpdateCredit = findViewById(R.id.etCityOfTheEventPDUpdateCredit);
        etOrganizedByPDUpdateCredit = findViewById(R.id.etOrganizedByPDUpdateCredit);

        spEventCategoryPDUpdateCredit = findViewById(R.id.spEventCategoryPDUpdateCredit);
        eventCategoryOpetion.add("Select Category");
        spEventTypePDUpdateCredit = findViewById(R.id.spEventTypePDUpdateCredit);
        eventTypeOpetion.add("Select Type");

        tvUpdatePDUpdateCredit = findViewById(R.id.tvUpdatePDUpdateCredit);
        tvUpdatePDUpdateCredit.setOnClickListener(this);
        tvCancelPDUpdateCredit = findViewById(R.id.tvCancelPDUpdateCredit);
        tvCancelPDUpdateCredit.setOnClickListener(this);

    }


    private void getEventType(final String for_pd, final String for_fdp) {
        DialogUtils.showProgressDialog(PDUpdateCreditActivity.this, "");
        String url = URLS.Get_Event_Type;

        StringRequest requestGetYear = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        ReqFDPGetEventTypePojo reqFDPGetEventTypePojo = gson.fromJson("{\"Data\":" + response + "}", ReqFDPGetEventTypePojo.class);

                        if (reqFDPGetEventTypePojo != null && reqFDPGetEventTypePojo.getData().size() > 0) {

                            for (int i = 0; i < reqFDPGetEventTypePojo.getData().size(); i++) {
                                hashMapEventTypeAndID.put(reqFDPGetEventTypePojo.getData().get(i).getEv_type_name(),
                                        reqFDPGetEventTypePojo.getData().get(i).getId());
                                eventTypeOpetion.add(reqFDPGetEventTypePojo.getData().get(i).getEv_type_name());
                            }

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(PDUpdateCreditActivity.this, eventTypeOpetion);
                            spEventTypePDUpdateCredit.setAdapter(academicYearAdapter);
                            getEventCategory();
                        } else {
                            Toast.makeText(PDUpdateCreditActivity.this, "Evemt Type Empty", Toast.LENGTH_SHORT).show();
                            DialogUtils.hideProgressDialog();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PDUpdateCreditActivity.this, "Evemt Type Empty", Toast.LENGTH_SHORT).show();
                    DialogUtils.hideProgressDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("for_pd", for_pd);
                params2.put("for_fdp", for_fdp);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    private void getEventCategory() {
        // DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        String url = URLS.Get_Event_Category;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        ReqFDPGetEventCategoryPojo reqFDPGetEventCategoryPojo = gson.fromJson("{\"Data\":" + response + "}", ReqFDPGetEventCategoryPojo.class);
//                        JSONArray jsonArray = new JSONArray(response);

                        if (reqFDPGetEventCategoryPojo != null && reqFDPGetEventCategoryPojo.getData().size() > 0) {

                            for (int i = 0; i < reqFDPGetEventCategoryPojo.getData().size(); i++) {
                                hashMapEventCategoryAndID.put(reqFDPGetEventCategoryPojo.getData().get(i).getEv_category_name(),
                                        reqFDPGetEventCategoryPojo.getData().get(i).getId());
                                eventCategoryOpetion.add(reqFDPGetEventCategoryPojo.getData().get(i).getEv_category_name());
                            }
                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(PDUpdateCreditActivity.this, eventCategoryOpetion);
                            spEventCategoryPDUpdateCredit.setAdapter(academicYearAdapter);

                            if (dataBean != null) {

                                try {
                                    etEventCreditPDUpdateCredit.setText(String.valueOf(dataBean.getPd_event_credit()));
                                    spEventCategoryPDUpdateCredit.setSelection(eventCategoryOpetion.indexOf(dataBean.getEv_category_name()));
                                    spEventTypePDUpdateCredit.setSelection(eventTypeOpetion.indexOf(dataBean.getEv_type_name()));
                                    etNameOfTheEventPDUpdateCredit.setText(String.valueOf(dataBean.getEv_type_name()));
                                    etCityOfTheEventPDUpdateCredit.setText(String.valueOf(dataBean.getPd_event_city()));
                                    etOrganizedByPDUpdateCredit.setText(String.valueOf(dataBean.getPd_organized_by()));
                                } catch (Exception ex) {
                                    Toast.makeText(PDUpdateCreditActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        } else {
                            Toast.makeText(PDUpdateCreditActivity.this, "Event Category Empty", Toast.LENGTH_SHORT).show();
                            DialogUtils.hideProgressDialog();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PDUpdateCreditActivity.this, "Event Category Empty", Toast.LENGTH_SHORT).show();
                    DialogUtils.hideProgressDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back || v.getId() == R.id.tvCancelPDUpdateCredit) {
            onBackPressed();
        } else if (v.getId() == R.id.tvUpdatePDUpdateCredit) {

            if (isValid()) {
                if (dataBean != null) {
                    String id = String.valueOf(dataBean.getId());
                    String user_id = mySharedPreferecesRKUOLD.getUserID();
                    String ip = "0";
                    String pd_approve_credit = etEventCreditPDUpdateCredit.getText().toString();
                    String eventcategory = String.valueOf(hashMapEventCategoryAndID.get(eventCategoryOpetion.get(spEventCategoryPDUpdateCredit.getSelectedItemPosition())));
                    String eventtype = String.valueOf(hashMapEventTypeAndID.get(eventTypeOpetion.get(spEventTypePDUpdateCredit.getSelectedItemPosition())));
                    String eventname = etNameOfTheEventPDUpdateCredit.getText().toString();
                    String eventcity = etCityOfTheEventPDUpdateCredit.getText().toString();
                    String organizeby = etOrganizedByPDUpdateCredit.getText().toString();

                    callUpdatePDCreditAPI(id, user_id, ip, pd_approve_credit, eventcategory, eventtype,
                            eventname, eventcity, organizeby);

                } else {
                    Toast.makeText(this, "data bean null", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void callUpdatePDCreditAPI(final String id, final String user_id, final String ip,
                                       final String pd_approve_credit, final String eventcategory,
                                       final String eventtype, final String eventname, final String eventcity,
                                       final String organizeby) {

        DialogUtils.showProgressDialog(PDUpdateCreditActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.pd_update_creadit, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(PDUpdateCreditActivity.this, responsePojo.getData().get(0).getMsg());
                    finish();
                } else {
                    DialogUtils.Show_Toast(PDUpdateCreditActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDUpdateCreditActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("pd_approve_credit", pd_approve_credit);
                params2.put("eventcategory", eventcategory);
                params2.put("eventtype", eventtype);
                params2.put("eventname", eventname);
                params2.put("eventcity", eventcity);
                params2.put("organizeby", organizeby);
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
