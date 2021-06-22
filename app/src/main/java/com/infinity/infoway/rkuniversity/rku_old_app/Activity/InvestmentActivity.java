package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.FileAndFolderInfo;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetFinancialYearPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetInvestmentNameByCoverIdPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewInvestmentDetailsByIdPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


public class InvestmentActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    int hasfile = 0;
    String base64StringFile;
    String orignalFileName;

    Spinner spInvestmentCoverInInvestment, spInvestmentNameInvestment, spFinancialYearInvestment;
    CustomEditText etInvestmentAmountInvestment, etRemarksInvestment;
    RelativeLayout rlUploadFileInvestment;
    CustomTextView tvChooseFileInvestment;
    ImageView imgUploadFileInvestment, imgClearInvestment, ivBack;
    LinearLayout ll_submit_cancel_investment, ll_update_investment;
    CustomBoldTextView tvSubmitInvestment, tvCancelInvestment, tvUpdateInvestment, tvDeleteInvestment;
    ViewInvestmentDetailsByIdPojo.DataBean dataBean;
    //String id;

    ArrayList<String> investmentCoverInOpetions = new ArrayList<>();
    HashMap<Integer, String> hashMapInvestmentCoverInOpetions = new HashMap<>();
    ArrayList<String> investmentNameOpetions = new ArrayList<>();
    ArrayList<String> financialYearOpetions = new ArrayList<>();

    ArrayList<String> idInvestmentName = new ArrayList<>();

    boolean isEnable = false;

    LinearLayout ll_investment_cover_in_sp, ll_investment_name_sp, ll_financial_year_sp,
            llUploadDocumentInvestment;
    LinearLayout ll_investment_cover_in_tv, ll_investment_name_tv, ll_financial_year_tv,
            ll_remarks, ll_download_document_investment;

    CustomTextView tvInvestmentConverIn, tvInvestmentName, tvFinancialYear, tvFileNameInvestment;
    ImageView iv_download_file_investment, iv_view_investment_pdf;

    private boolean isFromEditOrDelete = true;
    private boolean isStatusPending = false;
    private String investmentUpdateRecordId;
    private String INVESTMENT_NAME = "";

    private ImageView iv_info;
    SimpleTooltip tooltip;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = simpleDateFormat.format(new Date());

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(InvestmentActivity.this)
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
        setContentView(R.layout.activity_investment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.VIEW_INVESTMENT_POJO)) {
            dataBean = (ViewInvestmentDetailsByIdPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.VIEW_INVESTMENT_POJO);
            investmentUpdateRecordId = getIntent().getStringExtra(IntentConstants.ID_INVESTMENT);
            if (!String.valueOf(dataBean.getStatus()).equalsIgnoreCase("Pending")) {
                isFromEditOrDelete = false;
            } else {
                isStatusPending = true;
            }
        }

        getFinancialYear(mySharedPreferecesRKUOLD.getUserID());

        spInvestmentCoverInInvestment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isEnable) {
                    isStatusPending = false;
                    if (investmentNameOpetions != null) {

                        if (investmentNameOpetions.size() > 0) {

                            String obj = investmentNameOpetions.get(0); // remember first item
                            investmentNameOpetions.clear(); // clear complete list
                            investmentNameOpetions.add(obj);
                        }
                    }

                    if (investmentCoverInOpetions.get(position).equalsIgnoreCase("80C")) {
                        getInvestmentNameByConverId(String.valueOf(1));
                    } else if (investmentCoverInOpetions.get(position).equalsIgnoreCase("80D")) {
                        getInvestmentNameByConverId(String.valueOf(2));
                    } else if (investmentCoverInOpetions.get(position).equalsIgnoreCase("80G")) {
                        getInvestmentNameByConverId(String.valueOf(3));
                    } else if (investmentCoverInOpetions.get(position).equalsIgnoreCase("80U")) {
                        getInvestmentNameByConverId(String.valueOf(5));
                    } else if (investmentCoverInOpetions.get(position).equalsIgnoreCase("80E")) {
                        getInvestmentNameByConverId(String.valueOf(6));
                    } else if (investmentCoverInOpetions.get(position).equalsIgnoreCase("Section 24")) {
                        getInvestmentNameByConverId(String.valueOf(7));
                    } else if (investmentCoverInOpetions.get(position).equalsIgnoreCase("Other")) {
                        getInvestmentNameByConverId(String.valueOf(4));
                    }


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Investment");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);


        spInvestmentCoverInInvestment = findViewById(R.id.spInvestmentCoverInInvestment);

        hashMapInvestmentCoverInOpetions.put(0, "Select Investment Cover In");
        hashMapInvestmentCoverInOpetions.put(1, "80C");
        hashMapInvestmentCoverInOpetions.put(2, "80D");
        hashMapInvestmentCoverInOpetions.put(3, "80G");
        hashMapInvestmentCoverInOpetions.put(4, "80U");
        hashMapInvestmentCoverInOpetions.put(5, "80E");
        hashMapInvestmentCoverInOpetions.put(6, "Section 24");
        hashMapInvestmentCoverInOpetions.put(7, "Other");

        investmentCoverInOpetions.add(hashMapInvestmentCoverInOpetions.get(0));
        investmentCoverInOpetions.add(hashMapInvestmentCoverInOpetions.get(1));
        investmentCoverInOpetions.add(hashMapInvestmentCoverInOpetions.get(2));
        investmentCoverInOpetions.add(hashMapInvestmentCoverInOpetions.get(3));
        investmentCoverInOpetions.add(hashMapInvestmentCoverInOpetions.get(4));
        investmentCoverInOpetions.add(hashMapInvestmentCoverInOpetions.get(5));
        investmentCoverInOpetions.add(hashMapInvestmentCoverInOpetions.get(6));
        investmentCoverInOpetions.add(hashMapInvestmentCoverInOpetions.get(7));

        try {

//            ArrayAdapter<String> levelOfAuthor = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, investmentCoverInOpetions);
//            spInvestmentCoverInInvestment.setAdapter(levelOfAuthor); // this will set list of values to spinner

            SpinnerSimpleAdapter levelOfAuthor = new SpinnerSimpleAdapter(InvestmentActivity.this, investmentCoverInOpetions);
            spInvestmentCoverInInvestment.setAdapter(levelOfAuthor);

            // spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf(0));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        spInvestmentNameInvestment = findViewById(R.id.spInvestmentNameInvestment);

        investmentNameOpetions.add("Select Investment Name");

        spFinancialYearInvestment = findViewById(R.id.spFinancialYearInvestment);

        financialYearOpetions.add("Select Year");

        etInvestmentAmountInvestment = findViewById(R.id.etInvestmentAmountInvestment);
        etRemarksInvestment = findViewById(R.id.etRemarksInvestment);

        rlUploadFileInvestment = findViewById(R.id.rlUploadFileInvestment);
        rlUploadFileInvestment.setOnClickListener(this);

        tvChooseFileInvestment = findViewById(R.id.tvChooseFileInvestment);
        tvChooseFileInvestment.setOnClickListener(this);

        imgUploadFileInvestment = findViewById(R.id.imgUploadFileInvestment);
        imgUploadFileInvestment.setOnClickListener(this);
        imgClearInvestment = findViewById(R.id.imgClearInvestment);
        imgClearInvestment.setOnClickListener(this);

        ll_submit_cancel_investment = findViewById(R.id.ll_submit_cancel_investment);
        ll_update_investment = findViewById(R.id.ll_update_investment);

        tvSubmitInvestment = findViewById(R.id.tvSubmitInvestment);
        tvSubmitInvestment.setOnClickListener(this);
        tvCancelInvestment = findViewById(R.id.tvCancelInvestment);
        tvCancelInvestment.setOnClickListener(this);
        tvUpdateInvestment = findViewById(R.id.tvUpdateInvestment);
        tvUpdateInvestment.setOnClickListener(this);
        tvDeleteInvestment = findViewById(R.id.tvDeleteInvestment);
        tvDeleteInvestment.setOnClickListener(this);


        ll_investment_cover_in_sp = findViewById(R.id.ll_investment_cover_in_sp);
        ll_investment_name_sp = findViewById(R.id.ll_investment_name_sp);
        ll_financial_year_sp = findViewById(R.id.ll_financial_year_sp);
        llUploadDocumentInvestment = findViewById(R.id.llUploadDocumentInvestment);
        ll_remarks = findViewById(R.id.ll_remarks);
        ll_download_document_investment = findViewById(R.id.ll_download_document_investment);
        ll_investment_cover_in_tv = findViewById(R.id.ll_investment_cover_in_tv);
        ll_investment_name_tv = findViewById(R.id.ll_investment_name_tv);
        ll_financial_year_tv = findViewById(R.id.ll_financial_year_tv);

        tvInvestmentConverIn = findViewById(R.id.tvInvestmentConverIn);
        tvInvestmentName = findViewById(R.id.tvInvestmentName);
        tvFinancialYear = findViewById(R.id.tvFinancialYear);
        tvFileNameInvestment = findViewById(R.id.tvFileNameInvestment);

        iv_download_file_investment = findViewById(R.id.iv_download_file_investment);
        iv_download_file_investment.setOnClickListener(this);
        iv_view_investment_pdf = findViewById(R.id.iv_view_investment_pdf);
        iv_view_investment_pdf.setOnClickListener(this);

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
        if (investmentCoverInOpetions.get(spInvestmentCoverInInvestment.getSelectedItemPosition()).equalsIgnoreCase("Select Investment Cover In")) {
            DialogUtils.Show_Toast(InvestmentActivity.this, "Select Investment Cover In");
            return false;
        } else if (investmentNameOpetions.get(spInvestmentNameInvestment.getSelectedItemPosition()).equalsIgnoreCase("Select Investment Name")) {
            DialogUtils.Show_Toast(InvestmentActivity.this, "Select Investment Name");
            return false;
        } else if (financialYearOpetions.get(spFinancialYearInvestment.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(InvestmentActivity.this, "Select Year");
            return false;
        } else if (TextUtils.isEmpty(etInvestmentAmountInvestment.getText().toString())) {
            DialogUtils.Show_Toast(InvestmentActivity.this, "Enter Investment Amount");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.tvCancelInvestment) {
            onBackPressed();
        } else if (view.getId() == R.id.tvSubmitInvestment) {
            //submit
            if (isValid()) {

                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String ewi_cover_in = "";

                if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80C")) {
                    ewi_cover_in = "1";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80D")) {
                    ewi_cover_in = "2";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80G")) {
                    ewi_cover_in = "3";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80U")) {
                    ewi_cover_in = "5";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80E")) {
                    ewi_cover_in = "6";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("Section 24")) {
                    ewi_cover_in = "7";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("Other")) {
                    ewi_cover_in = "4";
                }

                String Investment_Name_id = idInvestmentName.get(spInvestmentNameInvestment.getSelectedItemPosition());

                String Financial_Year_id = String.valueOf(spFinancialYearInvestment.getSelectedItemPosition());
                String Investment_Amount = etInvestmentAmountInvestment.getText().toString();
                String Investment_Remarks;
                if (TextUtils.isEmpty(etRemarksInvestment.getText().toString())) {
                    Investment_Remarks = "";
                } else {
                    Investment_Remarks = etRemarksInvestment.getText().toString();
                }
                String FileName = "";
                String image = "";
                if (TextUtils.isEmpty(base64StringFile)) {
                    image = "";
                } else {
                    image = base64StringFile;
                }

                if (TextUtils.isEmpty(orignalFileName)) {
                    FileName = "";
                } else {
                    FileName = orignalFileName;
                }

                String id = "0";
                String hasfile = String.valueOf(this.hasfile);

                saveOrUpdateInvestment(emp_id, user_id, ip, ewi_cover_in, Investment_Name_id, Financial_Year_id,
                        Investment_Amount, Investment_Remarks, FileName, image, id, hasfile);

            }
        } else if (view.getId() == R.id.tvUpdateInvestment) {

            //update
            if (isValid()) {
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String ewi_cover_in = "";

                if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80C")) {
                    ewi_cover_in = "1";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80D")) {
                    ewi_cover_in = "2";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80G")) {
                    ewi_cover_in = "3";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80U")) {
                    ewi_cover_in = "5";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("80E")) {
                    ewi_cover_in = "6";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("Section 24")) {
                    ewi_cover_in = "7";
                } else if (investmentCoverInOpetions.get(Integer.valueOf(spInvestmentCoverInInvestment.getSelectedItemPosition())).equalsIgnoreCase("Other")) {
                    ewi_cover_in = "4";
                }

                String Investment_Name_id = idInvestmentName.get(spInvestmentNameInvestment.getSelectedItemPosition());

                String Financial_Year_id = String.valueOf(spFinancialYearInvestment.getSelectedItemPosition());
                String Investment_Amount = etInvestmentAmountInvestment.getText().toString();
                String Investment_Remarks;
                if (TextUtils.isEmpty(etRemarksInvestment.getText().toString())) {
                    Investment_Remarks = "";
                } else {
                    Investment_Remarks = etRemarksInvestment.getText().toString();
                }

                String FileName = "";
                String image = "";

                if (TextUtils.isEmpty(base64StringFile)) {
                    image = "";
                } else {
                    image = base64StringFile;
                }

                if (TextUtils.isEmpty(FileName)) {
                    FileName = "";
                } else {
                    FileName = orignalFileName;
                }

                String hasfile = String.valueOf(this.hasfile);
                if (dataBean != null) {
                    saveOrUpdateInvestment(emp_id, user_id, ip, ewi_cover_in, Investment_Name_id, Financial_Year_id,
                            Investment_Amount, Investment_Remarks, FileName, image, investmentUpdateRecordId, hasfile);
                }
            }
        } else if (view.getId() == R.id.imgClearInvestment) {
            //clear file
            tvChooseFileInvestment.setClickable(true);
            rlUploadFileInvestment.setClickable(true);
            tvChooseFileInvestment.setText("Choose File");
            imgClearInvestment.setVisibility(View.GONE);
            imgUploadFileInvestment.setVisibility(View.VISIBLE);
            hasfile = 0;
            base64StringFile = "";

        } else if (view.getId() == R.id.tvChooseFileInvestment || view.getId() == R.id.rlUploadFileInvestment || view.getId() == R.id.imgUploadFileInvestment) {
            //chose or upload file
            new MaterialFilePicker()
                    .withActivity(this)
                    .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                    .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                    .withFilterDirectories(false) // Set directories filterable (false by default)
                    .withHiddenFiles(true) // Show hidden files and folders
                    .start();
        } else if (view.getId() == R.id.tvDeleteInvestment) {

            String id = investmentUpdateRecordId;
            String user_id = mySharedPreferecesRKUOLD.getUserID();
            String ip = "0";

            deleteInvestmentById(id, user_id, ip);

        } else if (view.getId() == R.id.iv_download_file_investment) {
            if (dataBean != null) {
                downloadDocument(dataBean.getEwi_photo(), dataBean.getFiled());
            }
        } else if (view.getId() == R.id.iv_view_investment_pdf) {
            Intent intent = new Intent(InvestmentActivity.this, ViewPDFInWebViewCommanActivity.class);
            intent.putExtra(IntentConstants.VIEW_PDF_COMMAN,dataBean.getFiled());
            startActivity(intent);
        } else if (view.getId() == R.id.iv_info) {
            showTooltip(view);
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

                        tvChooseFileInvestment.setClickable(false);
                        rlUploadFileInvestment.setClickable(false);
                        imgUploadFileInvestment.setVisibility(View.GONE);
                        imgClearInvestment.setVisibility(View.VISIBLE);

                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64StringFile = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);
                        tvChooseFileInvestment.setText(file.getName());
                        orignalFileName = file.getName();
                        hasfile = 1;

                    }

                } else {
                    DialogUtils.Show_Toast(InvestmentActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }


    private void getFinancialYear(final String user_id) {
        DialogUtils.showProgressDialog(InvestmentActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_Financial_Year, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (isFromEditOrDelete || isStatusPending) {
                    DialogUtils.hideProgressDialog();
                }
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    GetFinancialYearPojo getFinancialYearPojo = gson.fromJson("{\"Data\":" + response + "}", GetFinancialYearPojo.class);
                    if (getFinancialYearPojo != null && getFinancialYearPojo.getData().size() > 0) {

                        for (int i = 0; i < getFinancialYearPojo.getData().size(); i++) {
                            financialYearOpetions.add(getFinancialYearPojo.getData().get(i).getFym_name());
                        }

//                        ArrayAdapter<String> financialYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, financialYearOpetions);
//                        spFinancialYearInvestment.setAdapter(financialYearAdapter);

                        SpinnerSimpleAdapter financialYearAdapter = new SpinnerSimpleAdapter(InvestmentActivity.this, financialYearOpetions);
                        spFinancialYearInvestment.setAdapter(financialYearAdapter);


                        if (getIntent().hasExtra(IntentConstants.VIEW_INVESTMENT_POJO)) {
                            dataBean = (ViewInvestmentDetailsByIdPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.VIEW_INVESTMENT_POJO);
                            investmentUpdateRecordId = getIntent().getStringExtra(IntentConstants.ID_INVESTMENT);
                            INVESTMENT_NAME = getIntent().getStringExtra(IntentConstants.INVESTMENT_NAME);
                            if (String.valueOf(dataBean.getStatus()).equalsIgnoreCase("Pending")) {
                                ll_update_investment.setVisibility(View.VISIBLE);
                                ll_submit_cancel_investment.setVisibility(View.GONE);

                                try {

                                    Log.d("INVESTMENT_NAME", investmentCoverInOpetions.get(dataBean.getCover_id()));

                                    if (String.valueOf(dataBean.getCover_id()).equalsIgnoreCase("1")) {
                                        spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf("80C"));
                                    } else if (String.valueOf(dataBean.getCover_id()).equalsIgnoreCase("2")) {
                                        spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf("80D"));
                                    } else if (String.valueOf(dataBean.getCover_id()).equalsIgnoreCase("3")) {
                                        spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf("80G"));
                                    } else if (String.valueOf(dataBean.getCover_id()).equalsIgnoreCase("5")) {
                                        spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf("80U"));
                                    } else if (String.valueOf(dataBean.getCover_id()).equalsIgnoreCase("6")) {
                                        spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf("80E"));
                                    } else if (String.valueOf(dataBean.getCover_id()).equalsIgnoreCase("7")) {
                                        spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf("Section 24"));
                                    } else if (String.valueOf(dataBean.getCover_id()).equalsIgnoreCase("4")) {
                                        spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf("Other"));
                                    } else {
                                        spInvestmentCoverInInvestment.setSelection(investmentCoverInOpetions.indexOf(investmentCoverInOpetions.get(0)));
                                    }


                                    //   getInvestmentNameByConverId(String.valueOf(dataBean.getCover_id()));

                                    spFinancialYearInvestment.setSelection(financialYearOpetions.indexOf(financialYearOpetions.get(dataBean.getFinancial_Year())));

                                    Log.d("TAGGGGGG", String.valueOf(financialYearOpetions.indexOf(1)));

                                    etInvestmentAmountInvestment.setText(String.valueOf(dataBean.getInvestment_amount()));


                                } catch (Exception ex) {
                                    Log.d("TAGGGGGG", ex.getMessage().toString());
                                }


                                if (!TextUtils.isEmpty(dataBean.getEwi_photo()) &&
                                        !TextUtils.isEmpty(dataBean.getFiled())) {

                                    orignalFileName = dataBean.getEwi_photo();

                                    llUploadDocumentInvestment.setVisibility(View.VISIBLE);
                                    rlUploadFileInvestment.setClickable(true);
                                    imgUploadFileInvestment.setVisibility(View.GONE);
                                    imgClearInvestment.setVisibility(View.VISIBLE);
                                    tvChooseFileInvestment.setText(dataBean.getEwi_photo());

                                    ll_download_document_investment.setVisibility(View.VISIBLE);
                                    tvFileNameInvestment.setText(dataBean.getEwi_photo());
                                } else {
                                    llUploadDocumentInvestment.setVisibility(View.VISIBLE);
                                    ll_download_document_investment.setVisibility(View.GONE);
                                }


                                if (!TextUtils.isEmpty(dataBean.getRemarks())) {
                                    ll_remarks.setVisibility(View.VISIBLE);
                                    etRemarksInvestment.setText(dataBean.getRemarks());
                                } else {
                                    ll_remarks.setVisibility(View.VISIBLE);
                                    etRemarksInvestment.setHint("Enter Remarks");
                                }

                            } else {
                                //update delete button
                                ll_update_investment.setVisibility(View.GONE);
                                ll_submit_cancel_investment.setVisibility(View.GONE);

                                //hide all the spinner
                                ll_investment_cover_in_sp.setVisibility(View.GONE);
                                ll_investment_name_sp.setVisibility(View.GONE);
                                ll_financial_year_sp.setVisibility(View.GONE);
                                llUploadDocumentInvestment.setVisibility(View.GONE);

                                etInvestmentAmountInvestment.setEnabled(false);
                                etRemarksInvestment.setEnabled(false);

                                ll_investment_cover_in_tv.setVisibility(View.VISIBLE);
                                ll_investment_name_tv.setVisibility(View.VISIBLE);
                                ll_financial_year_tv.setVisibility(View.VISIBLE);

                                tvInvestmentConverIn.setText(investmentCoverInOpetions.get(dataBean.getCover_id()));

                                getInvestmentNameByConverId(String.valueOf(dataBean.getCover_id()));

                                tvFinancialYear.setText(financialYearOpetions.get(dataBean.getFinancial_Year()));
                                etInvestmentAmountInvestment.setText(String.valueOf(dataBean.getInvestment_amount()));

                                if (!TextUtils.isEmpty(dataBean.getEwi_photo()) &&
                                        !TextUtils.isEmpty(dataBean.getFiled())) {
                                    ll_download_document_investment.setVisibility(View.VISIBLE);
                                    tvFileNameInvestment.setText(dataBean.getEwi_photo());

                                } else {
                                    ll_download_document_investment.setVisibility(View.GONE);
                                }

                                if (!TextUtils.isEmpty(dataBean.getRemarks())) {
                                    etRemarksInvestment.setText(dataBean.getRemarks());
                                } else {
                                    ll_remarks.setVisibility(View.GONE);
                                }

                            }
                        } else {
                            spFinancialYearInvestment.setSelection(financialYearOpetions.indexOf(0));
                        }
                        isEnable = true;
                    }
                } else {
                    DialogUtils.Show_Toast(InvestmentActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(InvestmentActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
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


    private void getInvestmentNameByConverId(final String cover_id) {
        if (isFromEditOrDelete) {
            DialogUtils.showProgressDialog(InvestmentActivity.this, "");
        }
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_Investment_Name_by_conver_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetInvestmentNameByCoverIdPojo getInvestmentNameByCoverIdPojo = gson.fromJson("{\"Data\":" + response + "}", GetInvestmentNameByCoverIdPojo.class);

                        if (getInvestmentNameByCoverIdPojo != null && getInvestmentNameByCoverIdPojo.getData().size() > 0) {

                            if (idInvestmentName != null && idInvestmentName.size() > 0) {
                                idInvestmentName.clear();
                            }
                            idInvestmentName.add("");

                            for (int i = 0; i < getInvestmentNameByCoverIdPojo.getData().size(); i++) {

                                idInvestmentName.add(String.valueOf(getInvestmentNameByCoverIdPojo.getData().get(i).getId()));
                                investmentNameOpetions.add(getInvestmentNameByCoverIdPojo.getData().get(i).getIn_name());
                            }
//                            ArrayAdapter<String> financialYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, investmentNameOpetions);
//                            spInvestmentNameInvestment.setAdapter(financialYearAdapter);

                            SpinnerSimpleAdapter financialYearAdapter = new SpinnerSimpleAdapter(InvestmentActivity.this, investmentNameOpetions);
                            spInvestmentNameInvestment.setAdapter(financialYearAdapter);

                            if (dataBean != null) {
                                try {

                                    spInvestmentNameInvestment.setSelection(investmentNameOpetions.indexOf(INVESTMENT_NAME));

                                } catch (Exception ex) {
                                    Toast.makeText(InvestmentActivity.this, "" + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                spInvestmentNameInvestment.setSelection(investmentNameOpetions.indexOf(0));
                            }

                            if (!isFromEditOrDelete && dataBean != null) {
                                tvInvestmentName.setText(INVESTMENT_NAME);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(InvestmentActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(InvestmentActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("cover_id", cover_id);
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

    private void saveOrUpdateInvestment(final String emp_id, final String user_id, final String ip,
                                        final String ewi_cover_in, final String Investment_Name_id,
                                        final String Financial_Year_id, final String Investment_Amount,
                                        final String Investment_Remarks, final String FileName,
                                        final String image, final String id, final String hasfile) {

        DialogUtils.showProgressDialog(InvestmentActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_update_investment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    //Toast.makeText(ReqPublicationInJournalsActivity.this, response, Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(InvestmentActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(InvestmentActivity.this, InvestmentListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(InvestmentActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(InvestmentActivity.this, "Please Try Again Later");
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
                params2.put("ewi_cover_in", ewi_cover_in);
                params2.put("Investment_Name_id", Investment_Name_id);
                params2.put("Financial_Year_id", Financial_Year_id);
                params2.put("Investment_Amount", Investment_Amount);
                params2.put("Investment_Remarks", Investment_Remarks);
                params2.put("FileName", FileName);
                params2.put("image", image);
                params2.put("id", id);
                params2.put("hasfile", hasfile);
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


    private void deleteInvestmentById(final String id, final String user_id, final String ip) {
        DialogUtils.showDialog4YNo(this, "", "Are You Sure You want to delete ?", new DialogUtils.DailogCallBackOkButtonClick() {
            @Override
            public void onDialogOkButtonClicked() {
                DialogUtils.showProgressDialog(InvestmentActivity.this, "");
                StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_Investment, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DialogUtils.hideProgressDialog();
                        if (!TextUtils.isEmpty(response)) {
                            Gson gson = new Gson();
                            SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                            DialogUtils.Show_Toast(InvestmentActivity.this, responsePojo.getData().get(0).getMsg());
                            Intent intent = new Intent(InvestmentActivity.this, InvestmentListActivity.class);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DialogUtils.Show_Toast(InvestmentActivity.this, "Please Try Again Later");
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
                        return params2;
                    }

                    @Override
                    public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                        return "application/x-www-form-urlencoded; charset=UTF-8";
                    }
                };
                deletePublication.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(deletePublication);

//                        showDialog(ctx, position,String.valueOf(publicationInConferenceListModelArrayList.get(position).getId()),swipeLayout);


            }
        }, new DialogUtils.DailogCallBackCancelButtonClick() {
            @Override
            public void onDialogCancelButtonClicked() {
            }
        });
    }

    private void downloadDocument(String fileName, String fileURL) {
        if (!TextUtils.isEmpty(fileName) && !TextUtils.isEmpty(fileURL)) {

            String url_con = fileURL + "";
            System.out.println("url_con ::::::::::::::::::::: " + url_con);

            String[] file2 = url_con.split("/");


            System.out.println("file after split::::::>>>>***" + String.valueOf(file2));
            System.out.println("file after split::::::" + file2.toString());

            String result1 = file2[file2.length - 1];

            System.out.println("result1::::" + result1);
            String nameoffile1 = result1;


            System.out.println("nameoffile1****" + System.currentTimeMillis() + "_" + nameoffile1);


            String url = url_con;
            if (url != null && url.length() > 5) {
                String extention = url.substring(url.lastIndexOf("."), url.length());
                //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
                new InvestmentActivity.DownloadFileFromURL(extention).execute(url);
            } else {
                DialogUtils.Show_Toast(InvestmentActivity.this, "No Documents Available");
            }
        }
    }


    private class DownloadFileFromURL extends AsyncTask<String, String, String> {


        String nameoffile;
        String Extenson;

        DownloadFileFromURL(String Extenson) {
            this.Extenson = Extenson;

            System.out.println("EXTENSION OF FILE::::::::::" + Extenson);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            DialogUtils.showProgressDialog(InvestmentActivity.this, "");

        }

        /**
         * Downloading file in menubackground thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);

                String urofysllabusl = f_url[0];
                System.out.println("urofysllabusl::::::" + urofysllabusl);
                String[] parts = urofysllabusl.split("/");
                System.out.println("parts::::::::::::::" + parts);
                String result = parts[parts.length - 1];
                System.out.println("result:::::::::::" + result);
                nameoffile = result;

                System.out.println("result::::::doInback::::" + result);
                System.out.println("name in  doInBackground>>>>" + nameoffile);
                URLConnection conection = url.openConnection();
                conection.connect();
//                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Rku/" + "/PatentAwarded/");
//                //File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+URLS.folder_name +"/");
//                dir.mkdirs();
//
//
//                System.out.println("path of file>>>>>" + dir.getAbsolutePath());
//                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();
                //  Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());

                File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + FileAndFolderInfo.FOLDER_NAME + "/Investment/");
                folder.mkdirs();

                String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "Investment",
                        currentDate, nameoffile);

                File file = new File(folder, actualFileName);
                file.createNewFile();

//                OutputStream output = new FileOutputStream("sdcard/" + URLS.folder_name +"/" + nameoffile);
                FileOutputStream output = new FileOutputStream(file);

                System.out.println("output:::::::::" + output.toString());
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();


            } catch (MalformedURLException e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            //pDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            DialogUtils.hideProgressDialog();

            DialogUtils.Show_Toast(InvestmentActivity.this, "Download Completed Successfully");

            String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "Investment",
                    currentDate, nameoffile);

            File file11 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/" + FileAndFolderInfo.FOLDER_NAME + "/Investment/" + actualFileName);

            /// for  opening downloaded documentssssssssssss
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(InvestmentActivity.this, InvestmentActivity.this.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                InvestmentActivity.this.grantUriPermission(InvestmentActivity.this.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent = null;
                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(uri, "application/pdf");
                }
                intent = Intent.createChooser(target, "Open File");
                try {
                    InvestmentActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(InvestmentActivity.this, "No Apps can performs This action");
                }
            } else {

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.setDataAndType(Uri.fromFile(file11), "image/*");

                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(Uri.fromFile(file11), "application/pdf");
                }

                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    InvestmentActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(InvestmentActivity.this, "No Apps can performs This action");

                }

            }

        }

    }

}
