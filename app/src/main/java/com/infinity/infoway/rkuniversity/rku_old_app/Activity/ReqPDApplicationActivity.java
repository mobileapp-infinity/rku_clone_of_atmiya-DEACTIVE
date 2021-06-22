package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.UploadMultipleDocumentPDAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.FDPSaveResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqFDPGetEventCategoryPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqFDPGetEventTypePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqPDEventRollPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewPDApplicationPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.AddMultipleFileModelPD;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;


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

public class ReqPDApplicationActivity extends AppCompatActivity implements View.OnClickListener, UploadMultipleDocumentPDAdapter.AddDeletePDFile {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;

    CustomTextView tvEmployeeNamePDApplication;

    CustomEditText etNameOfTheEventPDApplication, etOrganizedByPDApplication, etCityOfTheEventPDApplication,
            etEventCreditPDApplication, etObjectivePDApplication,
            etRegistrationFeesPDApplication, etTransportationPDApplication, etAccommodationPDApplication,
            etLeaveExpensePDApplication, etODPDApplication;

    Spinner spEventTypePDApplication, spEventCategoryPDApplication, spRoleOfApplicantIntheEventPDApplication;

    CustomTextView tvFromDatePDApplication, tvDateToPDApplication,
            tvLeaveFromDatePDApplication, tvLeaveToDatePDApplication;

    CustomTextView tvNoOfDayPDApplication;
    CustomTextView tvTotalExpensePDApplication;

    ImageView ivFromDatePDApplication, ivToDatePDApplication, ivLeavFromDatePDApplication,
            ivLeaveToDatePDApplication;

    LinearLayout ll_upload_document_pd_application,
            ll_submit_pd_application, ll_update_pd_application;

    CustomBoldTextView tvSubmitPDApplication, tvCancelPDApplication, tvUpdatePDApplication,
            tvCancelPDApplicationUpdateReq;

    RecyclerView rvUploadDocumentPDApplication;

    ArrayList<String> eventTypeOpetion = new ArrayList<>();
    ArrayList<String> eventCategoryOpetion = new ArrayList<>();
    ArrayList<String> rollOfApplicantsOpetion = new ArrayList<>();

    private Calendar fromDateCalendar;
    private Calendar toDateCalendar;

    private Calendar leaveFromDateCalendar;
    private Calendar leaveToDateCalendar;

    String dateFormatte = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormate = new SimpleDateFormat(dateFormatte, Locale.US);

    ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDArrayList;
    ArrayList<AddMultipleFileModelPD> selectMultipleFileModelPDArrayList;
    UploadMultipleDocumentPDAdapter uploadMultipleDocumentPDAdapter;
    RecyclerView.LayoutManager layoutManager;

    int selectTedFilePosition;
    String base64StringFile;
    boolean isFileSelected = false;
    boolean isFromUpdate = false;

    private boolean isPDDataSubmited = false;
    private boolean isPDFileUploaded = false;

    HashMap<String, Integer> hashMapEventTypeAndID = new HashMap<>();
    HashMap<String, Integer> hashMapEventCategoryAndID = new HashMap<>();
    HashMap<String, Integer> hashMapEventRoll = new HashMap<>();

    private String EVENT_TYPE_NAME = "";
    private String EVENT_CATEGORY_NAME = "";
    private String APPLICANT_ROLL_NAME = "";
    private String newRecordID;
    ArrayList<ViewPDApplicationPojo.DataBean> dataBeanArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_pdapplication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getEventType("1", "0");

        tvFromDatePDApplication.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateNoOfDays();
            }
        });

        tvDateToPDApplication.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateNoOfDays();
            }
        });


        etRegistrationFeesPDApplication.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateTotalExpences();
            }
        });

        etTransportationPDApplication.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateTotalExpences();
            }
        });

        etAccommodationPDApplication.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateTotalExpences();
            }
        });

        etLeaveExpensePDApplication.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateTotalExpences();
            }
        });

    }

    private boolean isValid() {
        if (TextUtils.isEmpty(tvEmployeeNamePDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Employee Name Empty");
            return false;
        } else if (TextUtils.isEmpty(etNameOfTheEventPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Name of the event");
            return false;
        } else if (eventTypeOpetion.get(spEventTypePDApplication.getSelectedItemPosition()).equalsIgnoreCase("Select Type")) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Select Event Type");
            return false;
        } else if (TextUtils.isEmpty(etOrganizedByPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Name of Organized by");
            return false;
        } else if (eventCategoryOpetion.get(spEventCategoryPDApplication.getSelectedItemPosition()).equalsIgnoreCase("Select Category")) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Select Category");
            return false;
        } else if (TextUtils.isEmpty(etCityOfTheEventPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter City of the event");
            return false;
        } else if (TextUtils.isEmpty(etEventCreditPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter event credit");
            return false;
        } else if (TextUtils.isEmpty(tvFromDatePDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter From Date");
            return false;
        } else if (TextUtils.isEmpty(tvDateToPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter To Date");
            return false;
        } else if (rollOfApplicantsOpetion.get(spRoleOfApplicantIntheEventPDApplication.getSelectedItemPosition()).equalsIgnoreCase("Select Role")) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Select Role");
            return false;
        } else if (TextUtils.isEmpty(etObjectivePDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Objectives");
            return false;
        } else if (TextUtils.isEmpty(tvLeaveFromDatePDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Leave From Date");
            return false;
        } else if (TextUtils.isEmpty(tvLeaveToDatePDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Leave To Date");
            return false;
        } else if (TextUtils.isEmpty(etRegistrationFeesPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Registration Fees");
            return false;
        } else if (TextUtils.isEmpty(etTransportationPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Tranportation");
            return false;
        } else if (TextUtils.isEmpty(etAccommodationPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Accommodation");
            return false;
        } else if (TextUtils.isEmpty(etLeaveExpensePDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Leave Expense");
            return false;
        } else if (TextUtils.isEmpty(etODPDApplication.getText().toString())) {
            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter OD");
            return false;
        }

        //below code is not in use

//        else if (TextUtils.isEmpty(tvTotalExpensePDApplication.getText().toString())) {
//            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Total Expense Empty");
//            return false;
//        }

//        else if (TextUtils.isEmpty(tvNoOfDayPDApplication.getText().toString())) {
//            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter No. of Day");
//            return false;
//        }

        return true;
    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("PD Application");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        fromDateCalendar = Calendar.getInstance();
        toDateCalendar = Calendar.getInstance();
        leaveFromDateCalendar = Calendar.getInstance();
        leaveToDateCalendar = Calendar.getInstance();

        tvEmployeeNamePDApplication = findViewById(R.id.tvEmployeeNamePDApplication);
        tvEmployeeNamePDApplication.setText(mySharedPreferecesRKUOLD.getFullName());

        etNameOfTheEventPDApplication = findViewById(R.id.etNameOfTheEventPDApplication);
        etOrganizedByPDApplication = findViewById(R.id.etOrganizedByPDApplication);
        etCityOfTheEventPDApplication = findViewById(R.id.etCityOfTheEventPDApplication);
        etEventCreditPDApplication = findViewById(R.id.etEventCreditPDApplication);

        etObjectivePDApplication = findViewById(R.id.etObjectivePDApplication);
        etRegistrationFeesPDApplication = findViewById(R.id.etRegistrationFeesPDApplication);
        etTransportationPDApplication = findViewById(R.id.etTransportationPDApplication);
        etAccommodationPDApplication = findViewById(R.id.etAccommodationPDApplication);
        etLeaveExpensePDApplication = findViewById(R.id.etLeaveExpensePDApplication);
        etODPDApplication = findViewById(R.id.etODPDApplication);

        spEventTypePDApplication = findViewById(R.id.spEventTypePDApplication);
        eventTypeOpetion.add("Select Type");
        spEventCategoryPDApplication = findViewById(R.id.spEventCategoryPDApplication);
        eventCategoryOpetion.add("Select Category");
        spRoleOfApplicantIntheEventPDApplication = findViewById(R.id.spRoleOfApplicantIntheEventPDApplication);
        rollOfApplicantsOpetion.add("Select Role");

        tvFromDatePDApplication = findViewById(R.id.tvFromDatePDApplication);

        tvDateToPDApplication = findViewById(R.id.tvDateToPDApplication);
        tvNoOfDayPDApplication = findViewById(R.id.tvNoOfDayPDApplication);
//        tvNoOfDayPDApplication.setOnClickListener(this);
        tvLeaveFromDatePDApplication = findViewById(R.id.tvLeaveFromDatePDApplication);
        tvLeaveToDatePDApplication = findViewById(R.id.tvLeaveToDatePDApplication);
        tvTotalExpensePDApplication = findViewById(R.id.tvTotalExpensePDApplication);
//        tvTotalExpensePDApplication.setOnClickListener(this);

        ivFromDatePDApplication = findViewById(R.id.ivFromDatePDApplication);
        ivFromDatePDApplication.setOnClickListener(this);
        ivToDatePDApplication = findViewById(R.id.ivToDatePDApplication);
        ivToDatePDApplication.setOnClickListener(this);

        ivLeavFromDatePDApplication = findViewById(R.id.ivLeavFromDatePDApplication);
        ivLeavFromDatePDApplication.setOnClickListener(this);
        ivLeaveToDatePDApplication = findViewById(R.id.ivLeaveToDatePDApplication);
        ivLeaveToDatePDApplication.setOnClickListener(this);

        ll_upload_document_pd_application = findViewById(R.id.ll_upload_document_pd_application);
        ll_submit_pd_application = findViewById(R.id.ll_submit_pd_application);
        ll_update_pd_application = findViewById(R.id.ll_update_pd_application);

        tvSubmitPDApplication = findViewById(R.id.tvSubmitPDApplication);
        tvSubmitPDApplication.setOnClickListener(this);
        tvCancelPDApplication = findViewById(R.id.tvCancelPDApplication);
        tvCancelPDApplication.setOnClickListener(this);
        tvUpdatePDApplication = findViewById(R.id.tvUpdatePDApplication);
        tvUpdatePDApplication.setOnClickListener(this);
        tvCancelPDApplicationUpdateReq = findViewById(R.id.tvCancelPDApplicationUpdateReq);
        tvCancelPDApplicationUpdateReq.setOnClickListener(this);

        rvUploadDocumentPDApplication = findViewById(R.id.rvUploadDocumentPDApplication);


        addMultipleFileModelPDArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rvUploadDocumentPDApplication.setLayoutManager(layoutManager);

        AddMultipleFileModelPD addMultipleFileModelPD = new AddMultipleFileModelPD();
        addMultipleFileModelPD.setFileName("No File Chosen");
        addMultipleFileModelPDArrayList.add(addMultipleFileModelPD);

        uploadMultipleDocumentPDAdapter = new UploadMultipleDocumentPDAdapter(this, addMultipleFileModelPDArrayList);

        rvUploadDocumentPDApplication.setAdapter(uploadMultipleDocumentPDAdapter);


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

//        else if (v.getId() == R.id.tvNoOfDayPDApplication) {
//
//            if (TextUtils.isEmpty(tvFromDatePDApplication.getText().toString())) {
//                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter From Date");
//            } else if (TextUtils.isEmpty(tvDateToPDApplication.getText().toString())) {
//                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter To Date");
//            } else if (compareDates(tvFromDatePDApplication.getText().toString(), tvDateToPDApplication.getText().toString())) {
//                Toast.makeText(this, "from date Can not be greater than to date", Toast.LENGTH_SHORT).show();
//            } else {
//                try {
//                    Date dateBefore = simpleDateFormate.parse(tvFromDatePDApplication.getText().toString());
//                    Date dateAfter = simpleDateFormate.parse(tvDateToPDApplication.getText().toString());
//                    long difference = dateAfter.getTime() - dateBefore.getTime();
//                    float daysBetween = (difference / (1000 * 60 * 60 * 24));
//                    /* You can also convert the milliseconds to days using this method
//                     * float daysBetween =
//                     *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
//                     */
//                    tvNoOfDayPDApplication.setText(String.valueOf(daysBetween));
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        else if (v.getId() == R.id.tvTotalExpensePDApplication) {
//
//            if (TextUtils.isEmpty(etRegistrationFeesPDApplication.getText().toString())) {
//                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Registration Fees");
//            } else if (TextUtils.isEmpty(etTransportationPDApplication.getText().toString())) {
//                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Tranportation");
//            } else if (TextUtils.isEmpty(etAccommodationPDApplication.getText().toString())) {
//                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Accommodation");
//            } else if (TextUtils.isEmpty(etLeaveExpensePDApplication.getText().toString())) {
//                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Leave Expense");
//            } else {
//
//                int regFee = Integer.parseInt(etRegistrationFeesPDApplication.getText().toString());
//                int tranportation = Integer.parseInt(etTransportationPDApplication.getText().toString());
//                int accommodation = Integer.parseInt(etAccommodationPDApplication.getText().toString());
//                int leaveExpense = Integer.parseInt(etLeaveExpensePDApplication.getText().toString());
//
//                String totalExpense = String.valueOf(regFee + tranportation + accommodation + leaveExpense);
//
//                tvTotalExpensePDApplication.setText(totalExpense);
//            }
//        }
        if (v.getId() == R.id.ivFromDatePDApplication) {
            openFromDatePickerDialog();
        } else if (v.getId() == R.id.ivToDatePDApplication) {
            openToDatePickerDialog();
        } else if (v.getId() == R.id.ivLeavFromDatePDApplication) {
            openLeaveFromDatePickerDialog();
        } else if (v.getId() == R.id.ivLeaveToDatePDApplication) {
            openLeaveToDatePickerDialog();
        } else if (v.getId() == R.id.tvSubmitPDApplication) {

            if (!isPDDataSubmited) {
                if (isValid()) {
                    String fromDate = tvFromDatePDApplication.getText().toString();
                    String toDate = tvDateToPDApplication.getText().toString();
                    String leaveFromDate = tvLeaveFromDatePDApplication.getText().toString();
                    String leaveToDate = tvLeaveToDatePDApplication.getText().toString();
                    if (compareDates(fromDate, toDate) && compareDates(leaveFromDate, leaveToDate)) {

                        String id = "0";
                        String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                        String user_id = mySharedPreferecesRKUOLD.getUserID();
                        String ip = "0";
                        String pd_event_name = etNameOfTheEventPDApplication.getText().toString();
                        String pd_event_type_id = String.valueOf(hashMapEventTypeAndID.get(eventTypeOpetion.get(spEventTypePDApplication.getSelectedItemPosition())));
                        String pd_organized_by = etOrganizedByPDApplication.getText().toString();
                        String pd_event_cate_id = String.valueOf(hashMapEventCategoryAndID.get(eventCategoryOpetion.get(spEventCategoryPDApplication.getSelectedItemPosition())));
                        String pd_event_credit = etEventCreditPDApplication.getText().toString();
                        String pd_event_city = etCityOfTheEventPDApplication.getText().toString();
                        String From_Date = tvFromDatePDApplication.getText().toString();
                        String To_Date = tvDateToPDApplication.getText().toString();
                        String pd_role_of_applicant = String.valueOf(hashMapEventRoll.get(rollOfApplicantsOpetion.get(spRoleOfApplicantIntheEventPDApplication.getSelectedItemPosition())));
                        String pd_event_description = etObjectivePDApplication.getText().toString();
                        String pd_registration_fees = etRegistrationFeesPDApplication.getText().toString();
                        String pd_transportation = etTransportationPDApplication.getText().toString();
                        String pd_accommodation = etAccommodationPDApplication.getText().toString();
                        String pd_leave_expense = etLeaveExpensePDApplication.getText().toString();
                        String pd_leave_from_date = tvLeaveFromDatePDApplication.getText().toString();
                        String pd_leave_to_date = tvLeaveToDatePDApplication.getText().toString();
                        String pd_no_od_leaves = etODPDApplication.getText().toString();

                        callSavePDApplicationAPI(id, emp_id, user_id, ip, pd_event_name, pd_event_type_id, pd_organized_by, pd_event_cate_id,
                                pd_event_credit, pd_event_city, From_Date, To_Date, pd_role_of_applicant, pd_event_description,
                                pd_registration_fees, pd_transportation, pd_accommodation, pd_leave_expense, pd_leave_from_date, pd_leave_to_date, pd_no_od_leaves);

                    } else {
                        Toast.makeText(this, "from date Can not be greater than to date", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else if (v.getId() == R.id.tvUpdatePDApplication) {

            if (isValid()) {
                if (isFileSelected) {

                    if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                        String fromDate = tvFromDatePDApplication.getText().toString();
                        String toDate = tvDateToPDApplication.getText().toString();
                        String leaveFromDate = tvLeaveFromDatePDApplication.getText().toString();
                        String leaveToDate = tvLeaveToDatePDApplication.getText().toString();
                        if (compareDates(fromDate, toDate) && compareDates(leaveFromDate, leaveToDate)) {

                            String id = String.valueOf(dataBeanArrayList.get(0).getId());
                            String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                            String user_id = mySharedPreferecesRKUOLD.getUserID();
                            String ip = "0";
                            String pd_event_name = etNameOfTheEventPDApplication.getText().toString();
                            String pd_event_type_id = String.valueOf(hashMapEventTypeAndID.get(eventTypeOpetion.get(spEventTypePDApplication.getSelectedItemPosition())));
                            String pd_organized_by = etOrganizedByPDApplication.getText().toString();
                            String pd_event_cate_id = String.valueOf(hashMapEventCategoryAndID.get(eventCategoryOpetion.get(spEventCategoryPDApplication.getSelectedItemPosition())));
                            String pd_event_credit = etEventCreditPDApplication.getText().toString();
                            String pd_event_city = etCityOfTheEventPDApplication.getText().toString();
                            String From_Date = tvFromDatePDApplication.getText().toString();
                            String To_Date = tvDateToPDApplication.getText().toString();
                            String pd_role_of_applicant = String.valueOf(hashMapEventRoll.get(rollOfApplicantsOpetion.get(spRoleOfApplicantIntheEventPDApplication.getSelectedItemPosition())));
                            String pd_event_description = etObjectivePDApplication.getText().toString();
                            String pd_registration_fees = etRegistrationFeesPDApplication.getText().toString();
                            String pd_transportation = etTransportationPDApplication.getText().toString();
                            String pd_accommodation = etAccommodationPDApplication.getText().toString();
                            String pd_leave_expense = etLeaveExpensePDApplication.getText().toString();
                            String pd_leave_from_date = tvLeaveFromDatePDApplication.getText().toString();
                            String pd_leave_to_date = tvLeaveToDatePDApplication.getText().toString();
                            String pd_no_od_leaves = etODPDApplication.getText().toString();

                            callSavePDApplicationAPI(id, emp_id, user_id, ip, pd_event_name, pd_event_type_id, pd_organized_by, pd_event_cate_id,
                                    pd_event_credit, pd_event_city, From_Date, To_Date, pd_role_of_applicant, pd_event_description,
                                    pd_registration_fees, pd_transportation, pd_accommodation, pd_leave_expense, pd_leave_from_date, pd_leave_to_date, pd_no_od_leaves);


                        } else {
                            Toast.makeText(this, "To Date Cannot be less than from date", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Can't Update", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Select File", Toast.LENGTH_SHORT).show();
                }
            }


        } else if (v.getId() == R.id.iv_back || v.getId() == R.id.tvCancelPDApplicationUpdateReq ||
                v.getId() == R.id.tvCancelPDApplication) {
            onBackPressed();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT && resultCode == RESULT_OK) {

            try {

                String filePath = intent.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                final File file = new File(filePath);

                if (file != null && file.length() > 0) {

                    if (file.length() > 2000000) { //2000000bytes == 2MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    } else {

                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64StringFile = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);
                        selectMultipleFileModelPDArrayList.get(selectTedFilePosition).setFileName(file.getName());
                        uploadMultipleDocumentPDAdapter = new UploadMultipleDocumentPDAdapter(this, selectMultipleFileModelPDArrayList);
                        rvUploadDocumentPDApplication.setAdapter(uploadMultipleDocumentPDAdapter);

                        String id = newRecordID;
                        String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                        String user_id = mySharedPreferecesRKUOLD.getUserID();
                        String ip = "0";
                        String File_Name = base64StringFile;
                        String File_Title = file.getName();

                        callUploadPDDocumentAPI(id, emp_id, user_id, ip, File_Name, File_Title);

                        for (int i = 0; i < selectMultipleFileModelPDArrayList.size(); i++) {
                            if (selectMultipleFileModelPDArrayList.get(i).getFileName().equalsIgnoreCase("No file chosen")) {
                                isFileSelected = false;
                                break;
                            } else {
                                isFileSelected = true;
                            }
                        }

                    }

                } else {
                    DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
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
                tvFromDatePDApplication.setText(sdf.format(fromDateCalendar.getTime()) + "");


            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(ReqPDApplicationActivity.this, date1, fromDateCalendar
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
                tvDateToPDApplication.setText(sdf.format(toDateCalendar.getTime()) + "");

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(ReqPDApplicationActivity.this, date1, toDateCalendar
                .get(Calendar.YEAR), toDateCalendar.get(Calendar.MONTH),
                toDateCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void openLeaveFromDatePickerDialog() {
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                fromDateCalendar.set(Calendar.YEAR, year);
                fromDateCalendar.set(Calendar.MONTH, monthOfYear);
                fromDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat1 = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
                tvLeaveFromDatePDApplication.setText(sdf.format(fromDateCalendar.getTime()) + "");


            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(ReqPDApplicationActivity.this, date1, fromDateCalendar
                .get(Calendar.YEAR), fromDateCalendar.get(Calendar.MONTH),
                fromDateCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void openLeaveToDatePickerDialog() {


        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                toDateCalendar.set(Calendar.YEAR, year);
                toDateCalendar.set(Calendar.MONTH, monthOfYear);
                toDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat1 = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
                tvLeaveToDatePDApplication.setText(sdf.format(toDateCalendar.getTime()) + "");

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(ReqPDApplicationActivity.this, date1, toDateCalendar
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

    private void getEventType(final String for_pd, final String for_fdp) {
        DialogUtils.showProgressDialog(ReqPDApplicationActivity.this, "");
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

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqPDApplicationActivity.this, eventTypeOpetion);
                            spEventTypePDApplication.setAdapter(academicYearAdapter);

                            spEventTypePDApplication.setSelection(eventTypeOpetion.indexOf(0));
                            getEventCategory();
                        } else {
                            Toast.makeText(ReqPDApplicationActivity.this, "Evemt Type Empty", Toast.LENGTH_SHORT).show();
                            DialogUtils.hideProgressDialog();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ReqPDApplicationActivity.this, "Evemt Type Empty", Toast.LENGTH_SHORT).show();
                    DialogUtils.hideProgressDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Please Try Again Later");
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
//                DialogUtils.hideProgressDialog();
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
                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqPDApplicationActivity.this, eventCategoryOpetion);
                            spEventCategoryPDApplication.setAdapter(academicYearAdapter);
                            spEventCategoryPDApplication.setSelection(eventCategoryOpetion.indexOf(0));
                            getRollOfApplicant();
                        } else {
                            Toast.makeText(ReqPDApplicationActivity.this, "Event Category Empty", Toast.LENGTH_SHORT).show();
                            DialogUtils.hideProgressDialog();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ReqPDApplicationActivity.this, "Event Category Empty", Toast.LENGTH_SHORT).show();
                    DialogUtils.hideProgressDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    private void getRollOfApplicant() {
//        DialogUtils.showProgressDialog(ReqPDApplicationActivity.this, "");
        String url = URLS.Get_Event_Role;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        ReqPDEventRollPojo reqPDEventRollPojo = gson.fromJson("{\"Data\":" + response + "}", ReqPDEventRollPojo.class);

                        if (reqPDEventRollPojo != null && reqPDEventRollPojo.getData().size() > 0) {

                            for (int i = 0; i < reqPDEventRollPojo.getData().size(); i++) {
                                hashMapEventRoll.put(reqPDEventRollPojo.getData().get(i).getEv_role_name(),
                                        reqPDEventRollPojo.getData().get(i).getId());
                                rollOfApplicantsOpetion.add(reqPDEventRollPojo.getData().get(i).getEv_role_name());
                            }

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqPDApplicationActivity.this, rollOfApplicantsOpetion);
                            spRoleOfApplicantIntheEventPDApplication.setAdapter(academicYearAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_PD_APPLICATION_POJO)) {
                                try {

                                    isFileSelected = false;
                                    isFromUpdate = true;
                                    ll_submit_pd_application.setVisibility(View.GONE);
                                    ll_update_pd_application.setVisibility(View.VISIBLE);

                                    dataBeanArrayList = (ArrayList<ViewPDApplicationPojo.DataBean>) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_PD_APPLICATION_POJO);

                                    ViewPDApplicationPojo.DataBean dataBean = dataBeanArrayList.get(0);

                                    newRecordID = String.valueOf(dataBean.getId());

                                    EVENT_TYPE_NAME = getIntent().getStringExtra(IntentConstants.PD_EVENT_TYPE_NAME_VIEW_PD);
                                    EVENT_CATEGORY_NAME = getIntent().getStringExtra(IntentConstants.PD_EVENT_CATEGORY_NAME_VIEW_PD);
                                    APPLICANT_ROLL_NAME = getIntent().getStringExtra(IntentConstants.PD_APPLICANT_ROLLL_NAME_VIEW_PD);

                                    etNameOfTheEventPDApplication.setText(dataBean.getPd_event_name());
                                    spEventTypePDApplication.setSelection(eventTypeOpetion.indexOf(EVENT_TYPE_NAME));
                                    etOrganizedByPDApplication.setText(dataBean.getPd_organized_by());
                                    spEventCategoryPDApplication.setSelection(eventCategoryOpetion.indexOf(EVENT_CATEGORY_NAME));
                                    etCityOfTheEventPDApplication.setText(dataBean.getPd_event_city());
                                    etEventCreditPDApplication.setText(String.valueOf(dataBean.getPd_event_credit()));

                                    tvFromDatePDApplication.setText(String.valueOf(dataBean.getEvent_from_date()));
                                    tvDateToPDApplication.setText(String.valueOf(dataBean.getEvent_to_date()));

                                    spRoleOfApplicantIntheEventPDApplication.setSelection(rollOfApplicantsOpetion.indexOf(APPLICANT_ROLL_NAME));
                                    etObjectivePDApplication.setText(String.valueOf(dataBean.getPd_event_description()));

                                    tvLeaveFromDatePDApplication.setText(String.valueOf(dataBean.getLeave_from_date()));
                                    tvLeaveToDatePDApplication.setText(String.valueOf(dataBean.getLeave_to_date()));
                                    etRegistrationFeesPDApplication.setText(String.valueOf((int)dataBean.getPd_registration_fees()));
                                    etTransportationPDApplication.setText(String.valueOf((int)dataBean.getPd_transportation()));
                                    etAccommodationPDApplication.setText(String.valueOf((int)dataBean.getPd_accommodation()));
                                    etLeaveExpensePDApplication.setText(String.valueOf((int)dataBean.getPd_leave_expense()));
                                    etODPDApplication.setText(String.valueOf(dataBean.getPd_no_od_leaves()));

                                    if (addMultipleFileModelPDArrayList != null) {
                                        addMultipleFileModelPDArrayList.clear();
                                    }
                                    addMultipleFileModelPDArrayList = new ArrayList<>();

                                    AddMultipleFileModelPD addMultipleFileModelPD = new AddMultipleFileModelPD();
                                    addMultipleFileModelPD.setFileName("No File Chosen");
                                    addMultipleFileModelPDArrayList.add(addMultipleFileModelPD);
                                    ll_upload_document_pd_application.setVisibility(View.VISIBLE);

                                    uploadMultipleDocumentPDAdapter = new UploadMultipleDocumentPDAdapter(ReqPDApplicationActivity.this, addMultipleFileModelPDArrayList);
                                    rvUploadDocumentPDApplication.setLayoutManager(layoutManager);
                                    rvUploadDocumentPDApplication.setAdapter(uploadMultipleDocumentPDAdapter);

                                } catch (Exception ex) {
                                    Toast.makeText(ReqPDApplicationActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                spRoleOfApplicantIntheEventPDApplication.setSelection(rollOfApplicantsOpetion.indexOf(0));
                            }


                        } else {
                            Toast.makeText(ReqPDApplicationActivity.this, "Applicant Role Empty", Toast.LENGTH_SHORT).show();
                            DialogUtils.hideProgressDialog();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ReqPDApplicationActivity.this, "Applicant Role Empty", Toast.LENGTH_SHORT).show();
                    DialogUtils.hideProgressDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }


    @Override
    public void addFilePD(ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDArrayList) {
        isFileSelected = false;
        if (addMultipleFileModelPDArrayList != null) {
            AddMultipleFileModelPD addMultipleFileModelPD = new AddMultipleFileModelPD();
            addMultipleFileModelPD.setFileName("No file chosen");
            addMultipleFileModelPDArrayList.add(addMultipleFileModelPD);
            uploadMultipleDocumentPDAdapter = new UploadMultipleDocumentPDAdapter(this, addMultipleFileModelPDArrayList);
            rvUploadDocumentPDApplication.setAdapter(uploadMultipleDocumentPDAdapter);
        }
    }

    @Override
    public void removeFilePD(ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDArrayList, int position) {

        if (addMultipleFileModelPDArrayList != null) {
            addMultipleFileModelPDArrayList.remove(position);
            uploadMultipleDocumentPDAdapter = new UploadMultipleDocumentPDAdapter(this, addMultipleFileModelPDArrayList);
            rvUploadDocumentPDApplication.setAdapter(uploadMultipleDocumentPDAdapter);
        }

        selectMultipleFileModelPDArrayList = addMultipleFileModelPDArrayList;

        for (int i = 0; i < selectMultipleFileModelPDArrayList.size(); i++) {
            if (i == 0 && isPDFileUploaded) {
                isFileSelected = true;
            } else {
                if (selectMultipleFileModelPDArrayList.get(i).getFileName().equalsIgnoreCase("No file chosen")) {
                    isFileSelected = false;
                    break;
                } else {
                    isFileSelected = true;
                }
            }
        }

    }

    @Override
    public void selectFilePD(ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDArrayList, int position) {
        selectTedFilePosition = position;
        selectMultipleFileModelPDArrayList = addMultipleFileModelPDArrayList;

        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(false) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();
    }


    private void callUploadPDDocumentAPI(final String id, final String emp_id, final String user_id,
                                         final String ip, final String File_Name, final String File_Title) {
        DialogUtils.showProgressDialog(ReqPDApplicationActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Insert_File_Upload_PD_Application, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {

                    //Toast.makeText(ReqPatentAwardedActivity.this, "Data Submited Successfully", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    isPDFileUploaded = true;
                    DialogUtils.Show_Toast(ReqPDApplicationActivity.this, responsePojo.getData().get(0).getMsg());

                    if (!getIntent().hasExtra(IntentConstants.REQ_VIEW_PD_APPLICATION_POJO)) {
                        onBackPressed();
                    }

                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPDApplicationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Please Try Again Later");
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
                params2.put("File_Name", File_Name);
                params2.put("File_Title", File_Title);
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


    private void callSavePDApplicationAPI(final String id, final String emp_id, final String user_id,
                                          final String ip, final String pd_event_name, final String pd_event_type_id,
                                          final String pd_organized_by, final String pd_event_cate_id, final String pd_event_credit,
                                          final String pd_event_city, final String From_Date, final String To_Date,
                                          final String pd_role_of_applicant, final String pd_event_description, final String pd_registration_fees,
                                          final String pd_transportation, final String pd_accommodation, final String pd_leave_expense,
                                          final String pd_leave_from_date, final String pd_leave_to_date, final String pd_no_od_leaves) {

        DialogUtils.showProgressDialog(ReqPDApplicationActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_Update_PD_Application, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {

                    if (getIntent().hasExtra(IntentConstants.REQ_VIEW_PD_APPLICATION_POJO)) {
                        Gson gson = new Gson();
                        SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                        DialogUtils.Show_Toast(ReqPDApplicationActivity.this, responsePojo.getData().get(0).getMsg());
                        Intent intent = new Intent(ReqPDApplicationActivity.this, ReqPDApplicationListActivity.class);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Gson gson = new Gson();
                        //SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                        FDPSaveResponsePojo fdpSaveResponsePojo = gson.fromJson("{\"Data\":" + response + "}", FDPSaveResponsePojo.class);

                        if (!TextUtils.isEmpty(fdpSaveResponsePojo.getData().get(0).getMsg())) {
                            if (fdpSaveResponsePojo.getData().get(0).getMsg().contains("Duplicate")) {
                                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, fdpSaveResponsePojo.getData().get(0).getMsg());
                                isPDDataSubmited = false;
                            } else {
                                if (!TextUtils.isEmpty(fdpSaveResponsePojo.getData().get(0).getMsg())) {
                                    DialogUtils.Show_Toast(ReqPDApplicationActivity.this, fdpSaveResponsePojo.getData().get(0).getMsg());
                                    DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Please Upload Documents");
                                } else {
                                    DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Data Save Successfully");
                                    DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Please Upload Documents");
                                }
                                //String[] separated = fdpSaveResponsePojo.getData().get(0).getMsg().split(":");
                                newRecordID = fdpSaveResponsePojo.getData().get(0).getMsg1();
                                ll_upload_document_pd_application.setVisibility(View.VISIBLE);
//                            tvSubmitFDPReq.setEnabled(false);
                                isPDDataSubmited = true;
                            }
                        }
                    }

                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPDApplicationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Please Try Again Later");
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
                params2.put("pd_event_name", pd_event_name);
                params2.put("pd_event_type_id", pd_event_type_id);
                params2.put("pd_organized_by", pd_organized_by);
                params2.put("pd_event_cate_id", pd_event_cate_id);
                params2.put("pd_event_credit", pd_event_credit);
                params2.put("pd_event_city", pd_event_city);
                params2.put("From_Date", From_Date);
                params2.put("To_Date", To_Date);
                params2.put("pd_role_of_applicant", pd_role_of_applicant);
                params2.put("pd_event_description", pd_event_description);
                params2.put("pd_registration_fees", pd_registration_fees);
                params2.put("pd_transportation", pd_transportation);
                params2.put("pd_accommodation", pd_accommodation);
                params2.put("pd_leave_expense", pd_leave_expense);
                params2.put("pd_leave_from_date", pd_leave_from_date);
                params2.put("pd_leave_to_date", pd_leave_to_date);
                params2.put("pd_no_od_leaves", pd_no_od_leaves);
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
    public void onBackPressed() {

        if (!isFromUpdate) {

            if (!isPDDataSubmited && !isPDFileUploaded) {
                super.onBackPressed();
            } else if (isPDDataSubmited && !isPDFileUploaded && !isFileSelected) {
                Toast.makeText(this, "Please Select File", Toast.LENGTH_SHORT).show();
            } else if (isFileSelected && isPDDataSubmited && isPDFileUploaded) {
                Intent intent = new Intent(ReqPDApplicationActivity.this, ReqPDApplicationListActivity.class);
                setResult(RESULT_OK, intent);
                super.onBackPressed();
            } else if (!isFileSelected && isPDDataSubmited && isPDFileUploaded) {
                Toast.makeText(this, "Please Select File", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (isFileSelected) {
                Intent intent = new Intent(ReqPDApplicationActivity.this, ReqPDApplicationListActivity.class);
                setResult(RESULT_OK, intent);
                super.onBackPressed();
            } else {
                Toast.makeText(this, "Please Select File", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void calculateNoOfDays() {
        if (TextUtils.isEmpty(tvFromDatePDApplication.getText().toString())) {

        } else if (TextUtils.isEmpty(tvDateToPDApplication.getText().toString())) {

        } else if (!compareDates(tvFromDatePDApplication.getText().toString(), tvDateToPDApplication.getText().toString())) {
            Toast.makeText(this, "from date Can not be greater than to date", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Date dateBefore = simpleDateFormate.parse(tvFromDatePDApplication.getText().toString());
                Date dateAfter = simpleDateFormate.parse(tvDateToPDApplication.getText().toString());
                long difference = dateAfter.getTime() - dateBefore.getTime();
                float daysBetween = (difference / (1000 * 60 * 60 * 24));
                /* You can also convert the milliseconds to days using this method
                 * float daysBetween =
                 *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
                 */
                tvNoOfDayPDApplication.setText(String.valueOf(daysBetween));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateTotalExpences() {

        if (TextUtils.isEmpty(etRegistrationFeesPDApplication.getText().toString())) {
//            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Registration Fees");
        } else if (TextUtils.isEmpty(etTransportationPDApplication.getText().toString())) {
//            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Tranportation");
        } else if (TextUtils.isEmpty(etAccommodationPDApplication.getText().toString())) {
//            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Accommodation");
        } else if (TextUtils.isEmpty(etLeaveExpensePDApplication.getText().toString())) {
//            DialogUtils.Show_Toast(ReqPDApplicationActivity.this, "Enter Leave Expense");
        } else {

            int regFee = Integer.parseInt(etRegistrationFeesPDApplication.getText().toString());
            int tranportation = Integer.parseInt(etTransportationPDApplication.getText().toString());
            int accommodation = Integer.parseInt(etAccommodationPDApplication.getText().toString());
            int leaveExpense = Integer.parseInt(etLeaveExpensePDApplication.getText().toString());

            String totalExpense = String.valueOf(regFee + tranportation + accommodation + leaveExpense);

            tvTotalExpensePDApplication.setText(totalExpense);
        }
    }


}
