package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.UploadMultipleFileAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.FDPSaveResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqFDPGetEventCategoryPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqFDPGetEventTypePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewFDPAttendedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.AddMultipleFileModelFDP;
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


public class ReqFDPAttendedActivity extends AppCompatActivity implements UploadMultipleFileAdapter.AddFile, View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act;
    CustomBoldTextView tv_version;
    RequestQueue queue;
    ImageView ivBack;

    ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList;
    ArrayList<AddMultipleFileModelFDP> selectedFileFDPArrayList;
    UploadMultipleFileAdapter uploadMultipleFileAdapter;
    RecyclerView.LayoutManager layoutManager;
    int selectTedFilePosition;
    String base64StringFile;
    boolean isFileSelected = false;
    boolean isFromUpdate = false;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();
    HashMap<String, Integer> hashMapEventTypeAndID = new HashMap<>();
    HashMap<String, Integer> hashMapEventCategoryAndID = new HashMap<>();

    RecyclerView rvUploadFile;
    Spinner spAcademicYearFDPReq, spEventTypeFDPReq, spOrganizedByFDPReq, spEventCategoryFDPReq;
    CustomEditText etNameOfTheWorkShowFDPReq, etNameOfOrganizerFDPReq, etCityFDPReq, etPDFrmWorkCreditFDPReq;
    CustomTextView tvFromDateFDPReq, tvDateToFDPReq;
    CustomBoldTextView tvSubmitFDPReq, tvCancelFDPReq, tvUpdateFDPReq, tvCancelFDPUpdateReq;
    LinearLayout llNameOfOrganizerFDPReq, llCityFDPReq, ll_submit_cancel_fdp_req, ll_update_fdp_req;
    ImageView imvFromDateFDPReq, imvToDateFDPReq;
    ArrayList<String> academicYearOpetion = new ArrayList<>();
    ArrayList<String> eventTypeOpetion = new ArrayList<>();
    ArrayList<String> eventCategoryOpetion = new ArrayList<>();
    HashMap<Integer, String> hashMapOrganized = new HashMap<>();
    ArrayList<String> organizedOpetion = new ArrayList<>();
    private Calendar fromDateCalendar;
    private Calendar toDateCalendar;

    private ArrayList<ReqViewFDPAttendedPojo.DataBean> dataBeanArrayList;
    private String newRecordID;
    LinearLayout ll_upload_document_fdp;

    private boolean isFDPDataSubmited = false;
    private boolean isFDPFileUploaded = false;

    String FDP_EVENT_TYPE = "";


    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(ReqFDPAttendedActivity.this)
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
        setContentView(R.layout.activity_req_fdpattended);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();

        spOrganizedByFDPReq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                etNameOfOrganizerFDPReq.setText("");
//                etNameOfOrganizerFDPReq.setHint("Enter Name of Organizer");
//                etCityFDPReq.setText("");
//                etCityFDPReq.setHint("Enter Name of City");

                if (organizedOpetion.get(position).equalsIgnoreCase("Other")) {
                    llNameOfOrganizerFDPReq.setVisibility(View.VISIBLE);
                    llCityFDPReq.setVisibility(View.VISIBLE);
                } else {
                    llNameOfOrganizerFDPReq.setVisibility(View.GONE);
                    llCityFDPReq.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private boolean isValid() {
        if (academicYearOpetion.get(spAcademicYearFDPReq.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etNameOfTheWorkShowFDPReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Enter Name of the workshop");
            return false;
        } else if (eventTypeOpetion.get(spEventTypeFDPReq.getSelectedItemPosition()).equalsIgnoreCase("Select Type")) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Select Event Type");
            return false;
        } else if (organizedOpetion.get(spOrganizedByFDPReq.getSelectedItemPosition()).equalsIgnoreCase("Select Organized By")) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Select Organized By");
            return false;
        } else if (llNameOfOrganizerFDPReq.getVisibility() == View.VISIBLE && TextUtils.isEmpty(etNameOfOrganizerFDPReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Enter Name of Organizer");
            return false;
        } else if (llCityFDPReq.getVisibility() == View.VISIBLE && TextUtils.isEmpty(etCityFDPReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Enter City");
            return false;
        } else if (TextUtils.isEmpty(tvFromDateFDPReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Enter From Date");
            return false;
        } else if (TextUtils.isEmpty(tvDateToFDPReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Enter To Date");
            return false;
        } else if (TextUtils.isEmpty(etPDFrmWorkCreditFDPReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Enter PD Framwork Credit");
            return false;
        } else if (eventCategoryOpetion.get(spEventCategoryFDPReq.getSelectedItemPosition()).equalsIgnoreCase("Select Category")) {
            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Select Category");
            return false;
        }
//        else if (!isFileSelected) {
//            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Select File");
//            return false;
//        }
        return true;
    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("FDP Attended");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);

        fromDateCalendar = Calendar.getInstance();
        toDateCalendar = Calendar.getInstance();

        spAcademicYearFDPReq = findViewById(R.id.spAcademicYearFDPReq);
        academicYearOpetion.add("Select Year");

        spEventTypeFDPReq = findViewById(R.id.spEventTypeFDPReq);
        eventTypeOpetion.add("Select Type");

        spOrganizedByFDPReq = findViewById(R.id.spOrganizedByFDPReq);

        hashMapOrganized.put(0, "Select Organized By");
        hashMapOrganized.put(1, "RK University");
        hashMapOrganized.put(2, "Other");

        organizedOpetion.add(hashMapOrganized.get(0));
        organizedOpetion.add(hashMapOrganized.get(1));
        organizedOpetion.add(hashMapOrganized.get(2));

//        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, organizedOpetion);
//        spOrganizedByFDPReq.setAdapter(statusAdapter); // this will set list of values to spinner

        SpinnerSimpleAdapter statusAdapter = new SpinnerSimpleAdapter(ReqFDPAttendedActivity.this, organizedOpetion);
        spOrganizedByFDPReq.setAdapter(statusAdapter);

        spOrganizedByFDPReq.setSelection(organizedOpetion.indexOf(0));

        spEventCategoryFDPReq = findViewById(R.id.spEventCategoryFDPReq);
        eventCategoryOpetion.add("Select Category");

        etNameOfTheWorkShowFDPReq = findViewById(R.id.etNameOfTheWorkShowFDPReq);
        etNameOfOrganizerFDPReq = findViewById(R.id.etNameOfOrganizerFDPReq);
        etCityFDPReq = findViewById(R.id.etCityFDPReq);
        etPDFrmWorkCreditFDPReq = findViewById(R.id.etPDFrmWorkCreditFDPReq);

        tvFromDateFDPReq = findViewById(R.id.tvFromDateFDPReq);
        tvDateToFDPReq = findViewById(R.id.tvDateToFDPReq);

        tvSubmitFDPReq = findViewById(R.id.tvSubmitFDPReq);
        tvSubmitFDPReq.setOnClickListener(this);
        tvCancelFDPReq = findViewById(R.id.tvCancelFDPReq);
        tvCancelFDPReq.setOnClickListener(this);
        tvUpdateFDPReq = findViewById(R.id.tvUpdateFDPReq);
        tvUpdateFDPReq.setOnClickListener(this);
        tvCancelFDPUpdateReq = findViewById(R.id.tvCancelFDPUpdateReq);
        tvCancelFDPUpdateReq.setOnClickListener(this);

        llNameOfOrganizerFDPReq = findViewById(R.id.llNameOfOrganizerFDPReq);
        llCityFDPReq = findViewById(R.id.llCityFDPReq);
        ll_submit_cancel_fdp_req = findViewById(R.id.ll_submit_cancel_fdp_req);
        ll_update_fdp_req = findViewById(R.id.ll_update_fdp_req);
        ll_upload_document_fdp = findViewById(R.id.ll_upload_document_fdp);

        imvFromDateFDPReq = findViewById(R.id.imvFromDateFDPReq);
        imvFromDateFDPReq.setOnClickListener(this);
        imvToDateFDPReq = findViewById(R.id.imvToDateFDPReq);
        imvToDateFDPReq.setOnClickListener(this);

        rvUploadFile = findViewById(R.id.rvUploadFileFDPReq);

        addMultipleFileModelFDPArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rvUploadFile.setLayoutManager(layoutManager);

        AddMultipleFileModelFDP addMultipleFileModelFDP = new AddMultipleFileModelFDP();
        addMultipleFileModelFDP.setFileName("No File Chosen");
        addMultipleFileModelFDPArrayList.add(addMultipleFileModelFDP);

        uploadMultipleFileAdapter = new UploadMultipleFileAdapter(this, addMultipleFileModelFDPArrayList);

        rvUploadFile.setAdapter(uploadMultipleFileAdapter);

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
    public void addFile(ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList) {
        isFileSelected = false;
        if (addMultipleFileModelFDPArrayList != null) {
            AddMultipleFileModelFDP addMultipleFileModelFDP = new AddMultipleFileModelFDP();
            addMultipleFileModelFDP.setFileName("No file chosen");
            addMultipleFileModelFDPArrayList.add(addMultipleFileModelFDP);
            uploadMultipleFileAdapter = new UploadMultipleFileAdapter(this, addMultipleFileModelFDPArrayList);
            rvUploadFile.setAdapter(uploadMultipleFileAdapter);
        }
    }

    @Override
    public void removeFile(ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList, int position) {

        if (addMultipleFileModelFDPArrayList != null) {
            addMultipleFileModelFDPArrayList.remove(position);
            uploadMultipleFileAdapter = new UploadMultipleFileAdapter(this, addMultipleFileModelFDPArrayList);
            rvUploadFile.setAdapter(uploadMultipleFileAdapter);
        }
        selectedFileFDPArrayList = addMultipleFileModelFDPArrayList;

        for (int i = 0; i < selectedFileFDPArrayList.size(); i++) {
            if (i == 0 && isFDPFileUploaded) {
                isFileSelected = true;
            } else {
                if (selectedFileFDPArrayList.get(i).getFileName().equalsIgnoreCase("No file chosen")) {
                    isFileSelected = false;
                    break;
                } else {
                    isFileSelected = true;
                }
            }
        }
    }

    @Override
    public void selectFile(ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList, int position) {
        selectTedFilePosition = position;
        selectedFileFDPArrayList = addMultipleFileModelFDPArrayList;

        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(false) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();
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
                        selectedFileFDPArrayList.get(selectTedFilePosition).setFileName(file.getName());
                        uploadMultipleFileAdapter = new UploadMultipleFileAdapter(this, selectedFileFDPArrayList);
                        rvUploadFile.setAdapter(uploadMultipleFileAdapter);

                        String id = newRecordID;
                        String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                        String user_id = mySharedPreferecesRKUOLD.getUserID();
                        String ip = "0";
                        String File_Name = base64StringFile;

                        callUploadFDPFileAPI(id, emp_id, user_id, ip, File_Name);

                        for (int i = 0; i < selectedFileFDPArrayList.size(); i++) {
                            if (selectedFileFDPArrayList.get(i).getFileName().equalsIgnoreCase("No file chosen")) {
                                isFileSelected = false;
                                break;
                            } else {
                                isFileSelected = true;
                            }
                        }

                    }

                } else {
                    DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (v.getId() == R.id.tvCancelFDPReq || v.getId() == R.id.tvCancelFDPUpdateReq) {
            onBackPressed();
        } else if (v.getId() == R.id.tvSubmitFDPReq) {
            //save FDP Form
            if (!isFDPDataSubmited) {
                if (isValid()) {
                    String fromDate = tvFromDateFDPReq.getText().toString();
                    String toDate = tvDateToFDPReq.getText().toString();
                    if (compareDates(fromDate, toDate)) {

                        String emp_code = mySharedPreferecesRKUOLD.getEmpCode();
                        String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearFDPReq.getSelectedItemPosition())));
                        String from_date = fromDate;
                        String to_date = toDate;
                        String workshop_name = etNameOfTheWorkShowFDPReq.getText().toString();
                        String year = "";
                        String activity_report_link = "";
                        String event_type = String.valueOf(hashMapEventTypeAndID.get(eventTypeOpetion.get(spEventTypeFDPReq.getSelectedItemPosition())));
                        String organize_by = String.valueOf(spOrganizedByFDPReq.getSelectedItemPosition());
                        String organizer_name;
                        String organizer_city;

                        if (organizedOpetion.get(spOrganizedByFDPReq.getSelectedItemPosition()).equalsIgnoreCase("Other")
                                && llNameOfOrganizerFDPReq.getVisibility() == View.VISIBLE &&
                                llCityFDPReq.getVisibility() == View.VISIBLE) {
                            organizer_name = etNameOfOrganizerFDPReq.getText().toString();
                            organizer_city = etCityFDPReq.getText().toString();
                        } else {
                            organizer_name = "";
                            organizer_city = "";
                        }

                        String pd_creadit = etPDFrmWorkCreditFDPReq.getText().toString();
                        String ev_catgory = String.valueOf(hashMapEventCategoryAndID.get(eventCategoryOpetion.get(spEventCategoryFDPReq.getSelectedItemPosition())));
                        String user_id = mySharedPreferecesRKUOLD.getUserID();
                        String ip = "0";

                        callSaveFDPAttendedAPI(emp_code, year_id, from_date, to_date, workshop_name, year,
                                activity_report_link, event_type, organize_by, organizer_name, organizer_city,
                                pd_creadit, ev_catgory, user_id, ip);

                    } else {
                        Toast.makeText(this, "from date Can not be greater than to date", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                onBackPressed();
            }

        } else if (v.getId() == R.id.tvUpdateFDPReq) {
            //update FDP Form

            if (isValid()) {
                if (isFileSelected) {

                    if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                        String fromDate = tvFromDateFDPReq.getText().toString();
                        String toDate = tvDateToFDPReq.getText().toString();
                        if (compareDates(fromDate, toDate)) {

                            String id = String.valueOf(dataBeanArrayList.get(0).getId());
                            String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearFDPReq.getSelectedItemPosition())));
                            String from_date = fromDate;
                            String to_date = toDate;
                            String workshop_name = etNameOfTheWorkShowFDPReq.getText().toString();
                            String year = "";
                            String activity_report_link = "";
                            String event_type = String.valueOf(hashMapEventTypeAndID.get(eventTypeOpetion.get(spEventTypeFDPReq.getSelectedItemPosition())));
                            String organize_by = String.valueOf(spOrganizedByFDPReq.getSelectedItemPosition());
                            String organizer_name;
                            String organizer_city;
                            String pd_creadit = etPDFrmWorkCreditFDPReq.getText().toString();
                            String ev_catgory = String.valueOf(hashMapEventCategoryAndID.get(eventCategoryOpetion.get(spEventCategoryFDPReq.getSelectedItemPosition())));
                            String user_id = mySharedPreferecesRKUOLD.getUserID();

                            if (organizedOpetion.get(spOrganizedByFDPReq.getSelectedItemPosition()).equalsIgnoreCase("Other")
                                    && llNameOfOrganizerFDPReq.getVisibility() == View.VISIBLE &&
                                    llCityFDPReq.getVisibility() == View.VISIBLE) {
                                organizer_name = etNameOfOrganizerFDPReq.getText().toString();
                                organizer_city = etCityFDPReq.getText().toString();
                            } else {
                                organizer_name = "";
                                organizer_city = "";
                            }
                            String ip = "0";


                            callUpdateFDPAttendedAPI(id, year_id, from_date, to_date, workshop_name,
                                    year, activity_report_link, event_type, organize_by, organizer_name,
                                    organizer_city, pd_creadit, ev_catgory, user_id, ip);


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

        } else if (v.getId() == R.id.imvFromDateFDPReq) {

            openFromDatePickerDialog();

        } else if (v.getId() == R.id.imvToDateFDPReq) {

            openToDatePickerDialog();
        } else if (v.getId() == R.id.iv_info) {
            showTooltip(v);
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
                SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                tvFromDateFDPReq.setText(sdf.format(fromDateCalendar.getTime()) + "");


            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(ReqFDPAttendedActivity.this, date1, fromDateCalendar
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
                tvDateToFDPReq.setText(sdf.format(toDateCalendar.getTime()) + "");

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(ReqFDPAttendedActivity.this, date1, toDateCalendar
                .get(Calendar.YEAR), toDateCalendar.get(Calendar.MONTH),
                toDateCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        String url = URLS.Get_YEAR;

        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
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
//                            spAcademicYearFDPReq.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqFDPAttendedActivity.this, academicYearOpetion);
                            spAcademicYearFDPReq.setAdapter(academicYearAdapter);
                            spAcademicYearFDPReq.setSelection(academicYearOpetion.indexOf(0));
                            getEventType("0", "1");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }

    private void getEventType(final String for_pd, final String for_fdp) {

//        DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_Event_Type, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        ReqFDPGetEventTypePojo reqFDPGetEventTypePojo = gson.fromJson("{\"Data\":" + response + "}", ReqFDPGetEventTypePojo.class);
//                        JSONArray jsonArray = new JSONArray(response);

                        if (reqFDPGetEventTypePojo != null && reqFDPGetEventTypePojo.getData().size() > 0) {

                            for (int i = 0; i < reqFDPGetEventTypePojo.getData().size(); i++) {
                                hashMapEventTypeAndID.put(reqFDPGetEventTypePojo.getData().get(i).getEv_type_name(),
                                        reqFDPGetEventTypePojo.getData().get(i).getId());
                                eventTypeOpetion.add(reqFDPGetEventTypePojo.getData().get(i).getEv_type_name());
                            }

//                            ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, eventTypeOpetion);
//                            spEventTypeFDPReq.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqFDPAttendedActivity.this, eventTypeOpetion);
                            spEventTypeFDPReq.setAdapter(academicYearAdapter);

                            spEventTypeFDPReq.setSelection(eventTypeOpetion.indexOf(0));
                            getEventCategory();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Try Again Later");
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
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);

//        //DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
//        String url = URLS.Get_Event_Type;
//
//        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                DialogUtils.hideProgressDialog();
//                if (!TextUtils.isEmpty(response)) {
//                    try {
//                        Gson gson = new Gson();
//                        ReqFDPGetEventTypePojo reqFDPGetEventTypePojo = gson.fromJson("{\"Data\":" + response + "}", ReqFDPGetEventTypePojo.class);
////                        JSONArray jsonArray = new JSONArray(response);
//
//                        if (reqFDPGetEventTypePojo != null && reqFDPGetEventTypePojo.getData().size() > 0) {
//
//                            for (int i = 0; i < reqFDPGetEventTypePojo.getData().size(); i++) {
//                                hashMapEventTypeAndID.put(reqFDPGetEventTypePojo.getData().get(i).getEv_type_name(),
//                                        reqFDPGetEventTypePojo.getData().get(i).getId());
//                                eventTypeOpetion.add(reqFDPGetEventTypePojo.getData().get(i).getEv_type_name());
//                            }
//
////                            ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, eventTypeOpetion);
////                            spEventTypeFDPReq.setAdapter(academicYearAdapter);
//
//                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqFDPAttendedActivity.this, eventTypeOpetion);
//                            spEventTypeFDPReq.setAdapter(academicYearAdapter);
//
//                            spEventTypeFDPReq.setSelection(eventTypeOpetion.indexOf(0));
//                            getEventCategory();
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
//                System.out.println("errorrrrrrrrrr " + error);
//                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params2 = new Hashtable<>();
//                params2.put("for_pd", for_pd);
//                params2.put("for_fdp", for_fdp);
//                return params2;
//            }
//
//            @Override
//            public String getBodyContentType() {
////                return "application/json; charset=UTF-8";
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//        };
//        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(requestGetYear);
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

//                            ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, eventCategoryOpetion);
//                            spEventCategoryFDPReq.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqFDPAttendedActivity.this, eventCategoryOpetion);
                            spEventCategoryFDPReq.setAdapter(academicYearAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_FDP_ATTENDED_POJO_LIDT)) {
                                try {

                                    FDP_EVENT_TYPE = getIntent().getStringExtra(IntentConstants.FDP_EVENT_TYPE);

                                    isFileSelected = false;
                                    isFromUpdate = true;
                                    ll_submit_cancel_fdp_req.setVisibility(View.GONE);
                                    ll_update_fdp_req.setVisibility(View.VISIBLE);

                                    dataBeanArrayList = (ArrayList<ReqViewFDPAttendedPojo.DataBean>) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_FDP_ATTENDED_POJO_LIDT);

                                    ReqViewFDPAttendedPojo.DataBean dataBean = dataBeanArrayList.get(0);

                                    newRecordID = String.valueOf(dataBean.getId());

                                    spAcademicYearFDPReq.setSelection(academicYearOpetion.indexOf(dataBean.getYear_name()));
                                    etNameOfTheWorkShowFDPReq.setText(dataBean.getFdpa_workshop_name());
                                    spEventTypeFDPReq.setSelection(eventTypeOpetion.indexOf(FDP_EVENT_TYPE));
                                    spOrganizedByFDPReq.setSelection(organizedOpetion.indexOf(organizedOpetion.get(dataBean.getFdpa_organized_by())));

                                    if (!TextUtils.isEmpty(dataBean.getFdpa_organizer_name()) &&
                                            !TextUtils.isEmpty(dataBean.getFdpa_organizer_city())) {
                                        llNameOfOrganizerFDPReq.setVisibility(View.VISIBLE);
                                        llCityFDPReq.setVisibility(View.VISIBLE);
                                        etNameOfOrganizerFDPReq.setText(dataBean.getFdpa_organizer_name());
                                        etCityFDPReq.setText(dataBean.getFdpa_organizer_city());
                                    }

                                    tvFromDateFDPReq.setText(String.valueOf(dataBean.getFdpa_from_date()));
                                    tvDateToFDPReq.setText(String.valueOf(dataBean.getFdpa_to_date()));
                                    etPDFrmWorkCreditFDPReq.setText(String.valueOf(dataBean.getFdpa_pd_credit()));
                                    spEventCategoryFDPReq.setSelection(eventCategoryOpetion.indexOf(dataBean.getEv_category_name()));

                                    if (addMultipleFileModelFDPArrayList != null) {
                                        addMultipleFileModelFDPArrayList.clear();
                                    }
                                    addMultipleFileModelFDPArrayList = new ArrayList<>();

                                    AddMultipleFileModelFDP addMultipleFileModelFDP = new AddMultipleFileModelFDP();
                                    addMultipleFileModelFDP.setFileName("No File Chosen");
                                    addMultipleFileModelFDPArrayList.add(addMultipleFileModelFDP);

                                    ll_upload_document_fdp.setVisibility(View.VISIBLE);

                                    //because list of file is not available
//                                for (int i = 0; i < dataBeanArrayList.size(); i++) {
//                                    AddMultipleFileModelFDP fdpDownloadDocumentModel = new AddMultipleFileModelFDP();
//                                    fdpDownloadDocumentModel.setFileName(String.valueOf(dataBeanArrayList.get(i).getFdpf_file()));
//                                    addMultipleFileModelFDPArrayList.add(fdpDownloadDocumentModel);
//                                }
                                    uploadMultipleFileAdapter = new UploadMultipleFileAdapter(ReqFDPAttendedActivity.this, addMultipleFileModelFDPArrayList);
                                    rvUploadFile.setLayoutManager(layoutManager);
                                    rvUploadFile.setAdapter(uploadMultipleFileAdapter);

                                } catch (Exception ex) {
                                    Toast.makeText(ReqFDPAttendedActivity.this, "" + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                spEventCategoryFDPReq.setSelection(eventCategoryOpetion.indexOf(0));
                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }


    private void callUploadFDPFileAPI(final String id, final String emp_id, final String user_id,
                                      final String ip, final String File_Name) {

        DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Insert_File_Upload_FDP_Attended_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    //Toast.makeText(ReqPatentAwardedActivity.this, "Data Submited Successfully", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    isFDPFileUploaded = true;
                    DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, responsePojo.getData().get(0).getMsg());
                    if (!getIntent().hasExtra(IntentConstants.REQ_VIEW_FDP_ATTENDED_POJO_LIDT)){
                        onBackPressed();
                    }
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Try Again Later");
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

    private void callSaveFDPAttendedAPI(final String emp_code, final String year_id, final String from_date,
                                        final String to_date, final String workshop_name, final String year,
                                        final String activity_report_link, final String event_type,
                                        final String organize_by, final String organizer_name,
                                        final String organizer_city, final String pd_creadit,
                                        final String ev_catgory, final String user_id, final String ip) {

        DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_FDP_Attended_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    //SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    FDPSaveResponsePojo fdpSaveResponsePojo = gson.fromJson("{\"Data\":" + response + "}", FDPSaveResponsePojo.class);

                    if (!TextUtils.isEmpty(fdpSaveResponsePojo.getData().get(0).getMsg())) {
                        if (fdpSaveResponsePojo.getData().get(0).getMsg().contains("Duplicate")) {
                            DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, fdpSaveResponsePojo.getData().get(0).getMsg());
                            isFDPDataSubmited = false;
                        } else {
                            if (!TextUtils.isEmpty(fdpSaveResponsePojo.getData().get(0).getMsg())) {
                                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, fdpSaveResponsePojo.getData().get(0).getMsg());
                                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Upload Documents");
                            } else {
                                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Data Save Successfully");
                                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Upload Documents");
                            }
                            //String[] separated = fdpSaveResponsePojo.getData().get(0).getMsg().split(":");
                            newRecordID = fdpSaveResponsePojo.getData().get(0).getMsg1();
                            ll_upload_document_fdp.setVisibility(View.VISIBLE);
//                            tvSubmitFDPReq.setEnabled(false);
                            isFDPDataSubmited = true;
                        }
                    }

                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_code", emp_code);
                params2.put("year_id", year_id);
                params2.put("from_date", from_date);
                params2.put("to_date", to_date);
                params2.put("workshop_name", workshop_name);
                params2.put("year", year);
                params2.put("activity_report_link", activity_report_link);
                params2.put("event_type", event_type);
                params2.put("organize_by", organize_by);
                params2.put("organizer_name", organizer_name);
                params2.put("organizer_city", organizer_city);
                params2.put("pd_creadit", pd_creadit);
                params2.put("ev_catgory", ev_catgory);
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

    @Override
    public void onBackPressed() {

        if (!isFromUpdate) {

            if (!isFDPDataSubmited && !isFDPFileUploaded) {
                super.onBackPressed();
            } else if (isFDPDataSubmited && !isFDPFileUploaded && !isFileSelected) {
                Toast.makeText(this, "Please Select File", Toast.LENGTH_SHORT).show();
            } else if (isFileSelected && isFDPDataSubmited && isFDPFileUploaded) {
                Intent intent = new Intent(ReqFDPAttendedActivity.this, ReqFDPAttendedListActivity.class);
                setResult(RESULT_OK, intent);
                super.onBackPressed();
            } else if (!isFileSelected && isFDPDataSubmited && isFDPFileUploaded) {
                Toast.makeText(this, "Please Select File", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (isFileSelected) {
                Intent intent = new Intent(ReqFDPAttendedActivity.this, ReqFDPAttendedListActivity.class);
                setResult(RESULT_OK, intent);
                super.onBackPressed();
            } else {
                Toast.makeText(this, "Please Select File", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void callUpdateFDPAttendedAPI(final String id, final String year_id, final String from_date,
                                          final String to_date, final String workshop_name, final String year,
                                          final String activity_report_link, final String event_type,
                                          final String organize_by, final String organizer_name, final String organizer_city,
                                          final String pd_creadit, final String ev_catgory, final String user_id, final String ip) {

        DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_FDP_Attended_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqFDPAttendedActivity.this, ReqFDPAttendedListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);

                } else {
                    DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqFDPAttendedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("year_id", year_id);
                params2.put("from_date", from_date);
                params2.put("to_date", to_date);
                params2.put("workshop_name", workshop_name);
                params2.put("year", year);
                params2.put("activity_report_link", activity_report_link);
                params2.put("event_type", event_type);
                params2.put("organize_by", organize_by);
                params2.put("organizer_name", organizer_name);
                params2.put("organizer_city", organizer_city);
                params2.put("pd_creadit", pd_creadit);
                params2.put("ev_catgory", ev_catgory);
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
