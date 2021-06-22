package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewCPDApplicationPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


public class ReqCPDApplicationActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act;
    CustomBoldTextView tv_version;
    RequestQueue queue;
    ArrayList<String> academicYearOpetion = new ArrayList<>();
    ImageView ivBack;

    String base64ApplicationFormPDF;
    String base64ConductionPDF;

    private Calendar fromDateCalendar;
    private Calendar toDateCalendar;

    Spinner spAcademicYearCPDReq;
    ImageView imvFromDateCPDReq, imvToDateCPDReq, imgUploadApplicationFormCPDReq, imgClearCPDApplicationFormReq,
            imgUploadConducationReportCPDReq, imgClearConducationReportCPDReq;

    CustomEditText etTitleofWorkshopCPDReq, etTotalParticipantsCPDReq, etFeedbackCPDReq;

    CustomTextView tvFromDateCPDReq, tvDateToCPDReq, tvChooseFileApplicationFormCPDReq,
            tvChooseFileConducationReportCPDReq;

    RelativeLayout rlUploadApplicationFormCPDReq, rlUploadConducationReportCPDReq;

    LinearLayout ll_submit_cancel_cpd_req, ll_update_cpd_req;

    CustomBoldTextView tvSubmitCPDReq, tvCancelCPDReq, tvUpdateCPDReq, tvCancelCPDUpdateReq;

    ReqViewCPDApplicationPojo.DataBean dataBean;

    int hashFileForCPDApplication = 0;
    int hashFileForConductionReport = 0;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();


    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(ReqCPDApplicationActivity.this)
                .anchorView(view)
                .gravity(Gravity.BOTTOM)
                .modal(true)
                .arrowColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .text(getString(R.string.app_name))
                .contentView(R.layout.tooltip_file_upload_size)
                .build();
        tooltip.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_cpdapplication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();
    }

    private boolean isValid() {
        if (academicYearOpetion.get(spAcademicYearCPDReq.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etTitleofWorkshopCPDReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Enter Title of workshop");
            return false;
        } else if (TextUtils.isEmpty(tvFromDateCPDReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Enter From Date");
            return false;
        } else if (TextUtils.isEmpty(tvDateToCPDReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Enter To Date");
            return false;
        } else if (!tvChooseFileApplicationFormCPDReq.getText().toString().contains(".pdf")) {
            DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Select Application Form");
            return false;
        } else if (TextUtils.isEmpty(etTotalParticipantsCPDReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Enter Total Participants");
            return false;
        } else if (!tvChooseFileConducationReportCPDReq.getText().toString().contains(".pdf")) {
            DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Select Conduction Report");
            return false;
        } else if (TextUtils.isEmpty(etFeedbackCPDReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Enter Feedback");
            return false;
        }
        return true;
    }


    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("CPD Application Form");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);


        fromDateCalendar = Calendar.getInstance();
        toDateCalendar = Calendar.getInstance();

        spAcademicYearCPDReq = findViewById(R.id.spAcademicYearCPDReq);

        imvFromDateCPDReq = findViewById(R.id.imvFromDateCPDReq);
        imvFromDateCPDReq.setOnClickListener(this);
        imvToDateCPDReq = findViewById(R.id.imvToDateCPDReq);
        imvToDateCPDReq.setOnClickListener(this);
        imgUploadApplicationFormCPDReq = findViewById(R.id.imgUploadApplicationFormCPDReq);
        imgUploadApplicationFormCPDReq.setOnClickListener(this);
        imgClearCPDApplicationFormReq = findViewById(R.id.imgClearCPDApplicationFormReq);
        imgClearCPDApplicationFormReq.setOnClickListener(this);
        imgUploadConducationReportCPDReq = findViewById(R.id.imgUploadConducationReportCPDReq);
        imgUploadConducationReportCPDReq.setOnClickListener(this);
        imgClearConducationReportCPDReq = findViewById(R.id.imgClearConducationReportCPDReq);
        imgClearConducationReportCPDReq.setOnClickListener(this);

        etTitleofWorkshopCPDReq = findViewById(R.id.etTitleofWorkshopCPDReq);
        etTotalParticipantsCPDReq = findViewById(R.id.etTotalParticipantsCPDReq);
        etFeedbackCPDReq = findViewById(R.id.etFeedbackCPDReq);

        tvFromDateCPDReq = findViewById(R.id.tvFromDateCPDReq);
        tvDateToCPDReq = findViewById(R.id.tvDateToCPDReq);
        tvChooseFileApplicationFormCPDReq = findViewById(R.id.tvChooseFileApplicationFormCPDReq);
        tvChooseFileConducationReportCPDReq = findViewById(R.id.tvChooseFileConducationReportCPDReq);

        rlUploadApplicationFormCPDReq = findViewById(R.id.rlUploadApplicationFormCPDReq);
        rlUploadApplicationFormCPDReq.setOnClickListener(this);
        rlUploadConducationReportCPDReq = findViewById(R.id.rlUploadConducationReportCPDReq);
        rlUploadConducationReportCPDReq.setOnClickListener(this);

        ll_submit_cancel_cpd_req = findViewById(R.id.ll_submit_cancel_cpd_req);
        ll_update_cpd_req = findViewById(R.id.ll_update_cpd_req);

        tvSubmitCPDReq = findViewById(R.id.tvSubmitCPDReq);
        tvSubmitCPDReq.setOnClickListener(this);
        tvCancelCPDReq = findViewById(R.id.tvCancelCPDReq);
        tvCancelCPDReq.setOnClickListener(this);
        tvUpdateCPDReq = findViewById(R.id.tvUpdateCPDReq);
        tvUpdateCPDReq.setOnClickListener(this);
        tvCancelCPDUpdateReq = findViewById(R.id.tvCancelCPDUpdateReq);
        tvCancelCPDUpdateReq.setOnClickListener(this);

        academicYearOpetion.add("Select Year");

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
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (v.getId() == R.id.tvCancelCPDReq || v.getId() == R.id.tvCancelCPDUpdateReq) {
            onBackPressed();
        } else if (v.getId() == R.id.tvSubmitCPDReq) {
            //save CPD Form
            if (isValid()) {

                String fromDate = tvFromDateCPDReq.getText().toString();
                String toDate = tvDateToCPDReq.getText().toString();
                if (compareDates(fromDate, toDate)) {


                    String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                    String user_id = mySharedPreferecesRKUOLD.getUserID();
                    String ip = "0";
                    String Year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearCPDReq.getSelectedItemPosition())));
                    String Workshop_Title = etTitleofWorkshopCPDReq.getText().toString();
                    String From_Date = fromDate;
                    String To_Date = toDate;
                    String CPD_Application_Form = base64ApplicationFormPDF;
                    String Participants_Total = etTotalParticipantsCPDReq.getText().toString();
                    String Conducation_Report = base64ConductionPDF;
                    String Feedback = etFeedbackCPDReq.getText().toString();

                    if (!TextUtils.isEmpty(base64ApplicationFormPDF) && !TextUtils.isEmpty(base64ConductionPDF)) {
                        callSaveCPDApplicationApi(emp_id, user_id, ip, Year, Workshop_Title, From_Date, To_Date, CPD_Application_Form, Participants_Total, Conducation_Report, Feedback);
                    }


                } else {
                    Toast.makeText(this, "To Date Cannot be less than from date", Toast.LENGTH_SHORT).show();
                }


            }
        } else if (v.getId() == R.id.tvUpdateCPDReq) {
            //update CPD Form


           if (isValid()){
               String fromDate = tvFromDateCPDReq.getText().toString();
               String toDate = tvDateToCPDReq.getText().toString();
               if (compareDates(fromDate, toDate)) {


                   String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                   String user_id = mySharedPreferecesRKUOLD.getUserID();
                   String ip = "0";
                   String Year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearCPDReq.getSelectedItemPosition())));
                   String Workshop_Title = etTitleofWorkshopCPDReq.getText().toString();
                   String From_Date = fromDate;
                   String To_Date = toDate;
                   String CPD_Application_Form = base64ApplicationFormPDF;
                   String Participants_Total = etTotalParticipantsCPDReq.getText().toString();
                   String Conducation_Report = base64ConductionPDF;
                   String Feedback;
                   if (!TextUtils.isEmpty(etFeedbackCPDReq.getText().toString())) {
                       Feedback = etFeedbackCPDReq.getText().toString();
                   } else {
                       Feedback = "";
                   }

                   if (hashFileForCPDApplication == 0 && hashFileForConductionReport == 0) {

                       callUpdateCPdApplicationApi(String.valueOf(dataBean.getId()), emp_id, user_id, ip, Year, Workshop_Title,
                               From_Date, To_Date, "", Participants_Total, "", Feedback,
                               String.valueOf(hashFileForCPDApplication), String.valueOf(hashFileForConductionReport));

                   } else if (hashFileForCPDApplication == 1 && hashFileForConductionReport == 0) {

                       if (!TextUtils.isEmpty(CPD_Application_Form)) {
                           callUpdateCPdApplicationApi(String.valueOf(dataBean.getId()), emp_id, user_id, ip, Year, Workshop_Title,
                                   From_Date, To_Date, CPD_Application_Form, Participants_Total, "", Feedback,
                                   String.valueOf(hashFileForCPDApplication), String.valueOf(hashFileForConductionReport));
                       }

                   } else if (hashFileForCPDApplication == 0 && hashFileForConductionReport == 1) {


                       if (!TextUtils.isEmpty(Conducation_Report)) {
                           callUpdateCPdApplicationApi(String.valueOf(dataBean.getId()), emp_id, user_id, ip, Year, Workshop_Title,
                                   From_Date, To_Date, "", Participants_Total, Conducation_Report, Feedback,
                                   String.valueOf(hashFileForCPDApplication), String.valueOf(hashFileForConductionReport));
                       }

                   } else {

                       if (!TextUtils.isEmpty(CPD_Application_Form) && !TextUtils.isEmpty(Conducation_Report)) {
                           callUpdateCPdApplicationApi(String.valueOf(dataBean.getId()), emp_id, user_id, ip, Year, Workshop_Title,
                                   From_Date, To_Date, CPD_Application_Form, Participants_Total, Conducation_Report, Feedback,
                                   String.valueOf(hashFileForCPDApplication), String.valueOf(hashFileForConductionReport));
                       }
                   }

               } else {
                   Toast.makeText(this, "To Date Cannot be less than from date", Toast.LENGTH_SHORT).show();
               }
           }


        } else if (v.getId() == R.id.rlUploadApplicationFormCPDReq || v.getId() == R.id.tvChooseFileApplicationFormCPDReq ||
                v.getId() == R.id.imgUploadApplicationFormCPDReq) {
            openFilePicker(IntentConstants.REQUEST_CODE_FOR_SELECT_APPLICATION_PDF);
        } else if (v.getId() == R.id.rlUploadConducationReportCPDReq || v.getId() == R.id.tvChooseFileConducationReportCPDReq ||
                v.getId() == R.id.imgUploadConducationReportCPDReq) {
            openFilePicker(IntentConstants.REQUEST_CODE_FOR_SELECT_CONDUCTION_REPORT_PDF);
        } else if (v.getId() == R.id.imgClearCPDApplicationFormReq) {
            tvChooseFileApplicationFormCPDReq.setClickable(true);
            rlUploadApplicationFormCPDReq.setClickable(true);
            tvChooseFileApplicationFormCPDReq.setText("Choose File");
            imgClearCPDApplicationFormReq.setVisibility(View.GONE);
            imgUploadApplicationFormCPDReq.setVisibility(View.VISIBLE);
            base64ApplicationFormPDF = "";
        } else if (v.getId() == R.id.imgClearConducationReportCPDReq) {
            tvChooseFileConducationReportCPDReq.setClickable(true);
            rlUploadConducationReportCPDReq.setClickable(true);
            tvChooseFileConducationReportCPDReq.setText("Choose File");
            imgClearConducationReportCPDReq.setVisibility(View.GONE);
            imgUploadConducationReportCPDReq.setVisibility(View.VISIBLE);
            base64ConductionPDF = "";
        } else if (v.getId() == R.id.imvFromDateCPDReq) {

            openFromDatePickerDialog();

        } else if (v.getId() == R.id.imvToDateCPDReq) {

            openToDatePickerDialog();
        } else if (v.getId() == R.id.iv_info) {
            showTooltip(v);
        }
    }

    private void callSaveCPDApplicationApi(final String emp_id, final String user_id, final String ip, final String Year,
                                           final String Workshop_Title, final String From_Date, final String To_Date,
                                           final String CPD_Application_Form, final String Participants_Total,
                                           final String Conducation_Report, final String Feedback) {

        DialogUtils.showProgressDialog(ReqCPDApplicationActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_CPD_Application_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqCPDApplicationActivity.this, ReqCPDApplicationListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_id", emp_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("Year", Year);
                params2.put("Workshop_Title", Workshop_Title);
                params2.put("From_Date", From_Date);
                params2.put("To_Date", To_Date);
                params2.put("CPD_Application_Form", CPD_Application_Form);
                params2.put("Participants_Total", Participants_Total);
                params2.put("Conducation_Report", Conducation_Report);
                params2.put("Feedback", Feedback);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == IntentConstants.REQUEST_CODE_FOR_SELECT_APPLICATION_PDF && resultCode == RESULT_OK) {
            //application pdf seleced
            try {
                String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                final File file = new File(filePath);

                if (file != null && file.length() > 0) {

                    if (file.length() > 2000000) { //2000000 == 1.5MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    } else {


                        tvChooseFileApplicationFormCPDReq.setClickable(false);
                        rlUploadApplicationFormCPDReq.setClickable(false);
                        imgUploadApplicationFormCPDReq.setVisibility(View.GONE);
                        imgClearCPDApplicationFormReq.setVisibility(View.VISIBLE);

                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64ApplicationFormPDF = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);
                        tvChooseFileApplicationFormCPDReq.setText(file.getName());
                        hashFileForCPDApplication = 1;
                    }


                } else {
                    DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } else if (data != null && requestCode == IntentConstants.REQUEST_CODE_FOR_SELECT_CONDUCTION_REPORT_PDF && resultCode == RESULT_OK) {
            //conduction report file selected
            try {

                String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                final File file = new File(filePath);

                if (file != null && file.length() > 0) {

                    if (file.length() > 2000000) { //2000000 == 1.5MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    } else {


                        tvChooseFileConducationReportCPDReq.setClickable(false);
                        rlUploadConducationReportCPDReq.setClickable(false);
                        imgUploadConducationReportCPDReq.setVisibility(View.GONE);
                        imgClearConducationReportCPDReq.setVisibility(View.VISIBLE);


                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64ConductionPDF = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);
                        tvChooseFileConducationReportCPDReq.setText(file.getName());
                        hashFileForConductionReport = 1;

                    }


                } else {
                    DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqCPDApplicationActivity.this, "");
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
//                            spAcademicYearCPDReq.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqCPDApplicationActivity.this, academicYearOpetion);
                            spAcademicYearCPDReq.setAdapter(academicYearAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_CPD_APPLICATION_FORM_POJO)) {
                                ll_submit_cancel_cpd_req.setVisibility(View.GONE);
                                ll_update_cpd_req.setVisibility(View.VISIBLE);
                                dataBean = (ReqViewCPDApplicationPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_CPD_APPLICATION_FORM_POJO);

                                if (dataBean != null) {
                                    spAcademicYearCPDReq.setSelection(academicYearOpetion.indexOf(dataBean.getYear_name()));
                                    etTitleofWorkshopCPDReq.setText(dataBean.getFdpo_workshop_name());
                                    tvFromDateCPDReq.setText(String.valueOf(dataBean.getFdpo_from_date()));
                                    tvDateToCPDReq.setText(String.valueOf(dataBean.getFdpo_to_date()));
                                    etTotalParticipantsCPDReq.setText(String.valueOf(dataBean.getFdpo_participants_number()));
                                    if (!TextUtils.isEmpty(String.valueOf(dataBean.getFdpo_feedback()))) {
                                        etFeedbackCPDReq.setText(String.valueOf(dataBean.getFdpo_feedback()));
                                    }

                                    rlUploadApplicationFormCPDReq.setClickable(true);
                                    imgUploadApplicationFormCPDReq.setVisibility(View.GONE);
                                    imgClearCPDApplicationFormReq.setVisibility(View.VISIBLE);
                                    tvChooseFileApplicationFormCPDReq.setText(dataBean.getFdpo_cpd_app_link());

                                    rlUploadConducationReportCPDReq.setClickable(true);
                                    imgUploadConducationReportCPDReq.setVisibility(View.GONE);
                                    imgClearConducationReportCPDReq.setVisibility(View.VISIBLE);
                                    tvChooseFileConducationReportCPDReq.setText(dataBean.getFdpo_cpd_app_link());

                                }
                            } else {
                                spAcademicYearCPDReq.setSelection(academicYearOpetion.indexOf(0));
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
                DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    private void openFilePicker(int requestCode) {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(requestCode)
                .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(false) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();
    }

    private void openFromDatePickerDialog() {
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                fromDateCalendar.set(Calendar.YEAR, year);
                fromDateCalendar.set(Calendar.MONTH, monthOfYear);
                fromDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat1 = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
                SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                tvFromDateCPDReq.setText(sdf.format(fromDateCalendar.getTime()) + "");

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(ReqCPDApplicationActivity.this, date1, fromDateCalendar
                .get(Calendar.YEAR), fromDateCalendar.get(Calendar.MONTH),
                fromDateCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void openToDatePickerDialog() {
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                toDateCalendar.set(Calendar.YEAR, year);
                toDateCalendar.set(Calendar.MONTH, monthOfYear);
                toDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat1 = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
                SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                tvDateToCPDReq.setText(sdf.format(toDateCalendar.getTime()) + "");

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(ReqCPDApplicationActivity.this, date1, toDateCalendar
                .get(Calendar.YEAR), toDateCalendar.get(Calendar.MONTH),
                toDateCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public static boolean compareDates(String d1, String d2) {
        try {
            // If you already have date objects then skip 1

            //1
            // Create 2 dates starts
            String myFormat1 = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);

            System.out.println("Date1" + sdf.format(date1));
            System.out.println("Date2" + sdf.format(date2));
            System.out.println();

            // Create 2 dates ends
            //1

            // Date object is having 3 methods namely after,before and equals for comparing
            // after() will return true if and only if date1 is after date 2
            if (date1.after(date2)) {
                System.out.println("Date1 is after Date2");
                return false;
            }
            // before() will return true if and only if date1 is before date2
            if (date1.before(date2)) {
                System.out.println("Date1 is before Date2");
                return true;
            }

            //equals() returns true if both the dates are equal
            if (date1.equals(date2)) {
                System.out.println("Date1 is equal Date2");
                return true;
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private void callUpdateCPdApplicationApi(final String id, final String emp_id, final String user_id, final String ip,
                                             final String Year, final String Workshop_Title,
                                             final String From_Date, final String To_Date,
                                             final String CPD_Application_Form, final String Participants_Total,
                                             final String Conducation_Report, final String Feedback,
                                             final String hashFileForCPDApplicationForm, final String hashFileForConductionReport) {

        DialogUtils.showProgressDialog(ReqCPDApplicationActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_CPD_Application_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    // Toast.makeText(ReqPublicationInJournalsActivity.this, response, Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqCPDApplicationActivity.this, ReqPublicationInJournalsListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqCPDApplicationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("emp_id", emp_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("Year", Year);
                params2.put("Workshop_Title", Workshop_Title);
                params2.put("From_Date", From_Date);
                params2.put("To_Date", To_Date);
                params2.put("CPD_Application_Form", CPD_Application_Form);
                params2.put("Participants_Total", Participants_Total);
                params2.put("Conducation_Report", Conducation_Report);
                params2.put("Feedback", Feedback);
                params2.put("hashfile", hashFileForConductionReport);
                params2.put("hasfile_cpd", hashFileForCPDApplicationForm);
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
