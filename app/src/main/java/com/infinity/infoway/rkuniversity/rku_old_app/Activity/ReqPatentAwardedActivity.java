package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

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
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetAcademicContributionYearPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewPatentAwardedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class ReqPatentAwardedActivity extends AppCompatActivity implements View.OnClickListener {


    Spinner spAcademicYearPatentReq,spYearofAwardPatentAwardedReq;
    CustomEditText etNameofPatentPublishedAwardedReq, etPatentNumberPatentAwardedReq;
    LinearLayout ll_submit_cancel_patent_awarded_req, ll_update_patent_awarded_req;
    CustomBoldTextView tvUpdatePatentAwardedReq, tvCancelPatentAwardedReqUpdate, tvSubmitPatentAwardedReq, tvCancelPatentAwardedReq;
    RelativeLayout rlUploadFilePatentAwardedreq;
    CustomBoldTextView tv_version;
    CustomTextView tvChooseFilePatentAwardedReq;
    ImageView imgUploadFilePatentAwardedReq, imgClearPatentAwardedReq, ivBack;

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act;
    RequestQueue queue;
    int hasfile = 0;
    ArrayList<String> academicYearOpetion = new ArrayList<>();
    ArrayList<String> yearOfAwardOpetion = new ArrayList<>();
    String base64StringFile;

    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    ReqViewPatentAwardedPojo.DataBean dataBean;

    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(ReqPatentAwardedActivity.this)
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
        setContentView(R.layout.activity_req_patent_awarded);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getYearApiCall();
    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Patent Awarded Request");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);

        spAcademicYearPatentReq = findViewById(R.id.spAcademicYearPatentReq);
        spYearofAwardPatentAwardedReq = findViewById(R.id.spYearofAwardPatentAwardedReq);

        yearOfAwardOpetion .add("Select Year of Award");
        academicYearOpetion.add("Select Year");

        etNameofPatentPublishedAwardedReq = findViewById(R.id.etNameofPatentPublishedAwardedReq);
        etPatentNumberPatentAwardedReq = findViewById(R.id.etPatentNumberPatentAwardedReq);
//        etYearofAwardPatentAwardedReq = findViewById(R.id.etYearofAwardPatentAwardedReq);

        ll_submit_cancel_patent_awarded_req = findViewById(R.id.ll_submit_cancel_patent_awarded_req);
        ll_update_patent_awarded_req = findViewById(R.id.ll_update_patent_awarded_req);

        tvUpdatePatentAwardedReq = findViewById(R.id.tvUpdatePatentAwardedReq);
        tvUpdatePatentAwardedReq.setOnClickListener(this);
        tvCancelPatentAwardedReqUpdate = findViewById(R.id.tvCancelPatentAwardedReqUpdate);
        tvCancelPatentAwardedReqUpdate.setOnClickListener(this);
        tvSubmitPatentAwardedReq = findViewById(R.id.tvSubmitPatentAwardedReq);
        tvSubmitPatentAwardedReq.setOnClickListener(this);
        tvCancelPatentAwardedReq = findViewById(R.id.tvCancelPatentAwardedReq);
        tvCancelPatentAwardedReq.setOnClickListener(this);

        rlUploadFilePatentAwardedreq = findViewById(R.id.rlUploadFilePatentAwardedreq);
        rlUploadFilePatentAwardedreq.setOnClickListener(this);

        tvChooseFilePatentAwardedReq = findViewById(R.id.tvChooseFilePatentAwardedReq);
        tvChooseFilePatentAwardedReq.setOnClickListener(this);

        imgUploadFilePatentAwardedReq = findViewById(R.id.imgUploadFilePatentAwardedReq);
        imgUploadFilePatentAwardedReq.setOnClickListener(this);
        imgClearPatentAwardedReq = findViewById(R.id.imgClearPatentAwardedReq);
        imgClearPatentAwardedReq.setOnClickListener(this);

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
        if (academicYearOpetion.get(spAcademicYearPatentReq.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etNameofPatentPublishedAwardedReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Enter Name of Patent Published/Awarded");
            return false;
        } else if (TextUtils.isEmpty(etPatentNumberPatentAwardedReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Enter Patent Number");
            return false;
        } else if (yearOfAwardOpetion.get(spYearofAwardPatentAwardedReq.getSelectedItemPosition()).equalsIgnoreCase("Select Year of Award")) {
            DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Select Year of Award ");
            return false;
        } else if (!tvChooseFilePatentAwardedReq.getText().toString().contains(".pdf")) {
            DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Select File");
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.tvCancelPatentAwardedReqUpdate) {
            onBackPressed();
        } else if (view.getId() == R.id.tvCancelPatentAwardedReq) {
            onBackPressed();
        } else if (view.getId() == R.id.tvSubmitPatentAwardedReq) {
            //submit
            if (isValid()) {
                String year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPatentReq.getSelectedItemPosition())));
                String patent_name = etNameofPatentPublishedAwardedReq.getText().toString();
                String patent_number = etPatentNumberPatentAwardedReq.getText().toString();
                String patent_year = yearOfAwardOpetion.get(spYearofAwardPatentAwardedReq.getSelectedItemPosition());
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String filename;
                if (!TextUtils.isEmpty(base64StringFile)) {
                    filename = base64StringFile;
                    callSavePatentAwardedApi(year, patent_name, patent_number, patent_year, user_id, ip, filename, emp_id);
                } else {
                    DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Select File");
                }
            }
        } else if (view.getId() == R.id.tvUpdatePatentAwardedReq) {
            //update
            if (isValid()) {

                String year = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearPatentReq.getSelectedItemPosition())));
                String patent_name = etNameofPatentPublishedAwardedReq.getText().toString();
                String patent_number = etPatentNumberPatentAwardedReq.getText().toString();
                String patent_year = yearOfAwardOpetion.get(spYearofAwardPatentAwardedReq.getSelectedItemPosition());
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String filename;

                if (hasfile == 0) {
                    if (dataBean != null) {
                        updatePatentAwarded(String.valueOf(dataBean.getId()), year, patent_name, patent_number, patent_year, user_id, ip, "", emp_id, String.valueOf(hasfile));
                    }
                } else {
                    if (dataBean != null && !TextUtils.isEmpty(base64StringFile)) {
                        filename = base64StringFile;
                        updatePatentAwarded(String.valueOf(dataBean.getId()), year, patent_name, patent_number, patent_year, user_id, ip, filename, emp_id, String.valueOf(hasfile));
                    } else {
                        DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Select File");
                    }
                }
            }
        } else if (view.getId() == R.id.imgClearPatentAwardedReq) {
            //clear file
            tvChooseFilePatentAwardedReq.setClickable(true);
            rlUploadFilePatentAwardedreq.setClickable(true);
            tvChooseFilePatentAwardedReq.setText("Choose File");
            imgClearPatentAwardedReq.setVisibility(View.GONE);
            imgUploadFilePatentAwardedReq.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.tvChooseFilePatentAwardedReq || view.getId() == R.id.rlUploadFilePatentAwardedreq || view.getId() == R.id.imgUploadFilePatentAwardedReq) {
            //chose or upload file
            new MaterialFilePicker()
                    .withActivity(this)
                    .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                    .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                    .withFilterDirectories(false) // Set directories filterable (false by default)
                    .withHiddenFiles(true) // Show hidden files and folders
                    .start();
        } else if (view.getId() == R.id.iv_info) {
            showTooltip(view);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT && resultCode == RESULT_OK) {

            try {

                // String filePath = intent.getData().getPath();
                //  String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
                //String selectedFilePath = FileUtils.getPath(this, uri);
                String filePath = intent.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                final File file = new File(filePath);

                if (file != null && file.length() > 0) {

                    if (file.length() > 2000000){ //2000000bytes == 2MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    }else {
                        hasfile = 1;

                        tvChooseFilePatentAwardedReq.setClickable(false);
                        rlUploadFilePatentAwardedreq.setClickable(false);
                        imgUploadFilePatentAwardedReq.setVisibility(View.GONE);
                        imgClearPatentAwardedReq.setVisibility(View.VISIBLE);

                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64StringFile = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);
                        tvChooseFilePatentAwardedReq.setText(file.getName());
                    }

                } else {
                    DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
//        else if (requestCode == IntentConstants.REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE_PERMISSION &&
//                ContextCompat.checkSelfPermission(PublicationInConferencesActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//            //permission ganted
//            //requestForWriteExternalStoragePermission();
//
//        }
//        else if (requestCode == IntentConstants.REQUEST_CODE_FOR_WRITE_EXTERNAL_STORAGE_PERMISSION &&
//                ContextCompat.checkSelfPermission(PublicationInConferencesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//            //permission ganted
//            UploadDocumentUtility.uploadDocument(this,"application/pdf","Select PDF",IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);
//        }
    }


    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqPatentAwardedActivity.this, "");
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
//                            spAcademicYearPatentReq.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter = new SpinnerSimpleAdapter(ReqPatentAwardedActivity.this, academicYearOpetion);
                            spAcademicYearPatentReq.setAdapter(academicYearAdapter);

                            callAcademicContributionAPI();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }


    private void callAcademicContributionAPI() {

//        DialogUtils.showProgressDialog(ReqFDPAttendedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.GET, URLS.Get_Academic_Contributions_year_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetAcademicContributionYearPojo getAcademicContributionYearPojo = gson.fromJson("{\"Data\":" + response + "}", GetAcademicContributionYearPojo.class);

                        if (getAcademicContributionYearPojo != null && getAcademicContributionYearPojo.getData().size() > 0) {

                            for (int i = 0 ; i < getAcademicContributionYearPojo.getData().size() ; i++){
                                yearOfAwardOpetion.add(String.valueOf(getAcademicContributionYearPojo.getData().get(i).getYear()));
                            }

                            SpinnerSimpleAdapter yearOfAwardedOpetionAdapter = new SpinnerSimpleAdapter(ReqPatentAwardedActivity.this, yearOfAwardOpetion);
                            spYearofAwardPatentAwardedReq.setAdapter(yearOfAwardedOpetionAdapter);

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_PATENT_AWARDED)) {
                                ll_submit_cancel_patent_awarded_req.setVisibility(View.GONE);
                                ll_update_patent_awarded_req.setVisibility(View.VISIBLE);
                                dataBean = (ReqViewPatentAwardedPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_PATENT_AWARDED);

                                if (dataBean != null) {
                                    spAcademicYearPatentReq.setSelection(academicYearOpetion.indexOf(dataBean.getYear_name()));
                                    etNameofPatentPublishedAwardedReq.setText(dataBean.getPat_patent_name());
                                    etPatentNumberPatentAwardedReq.setText(String.valueOf(dataBean.getPat_patent_number()));
                                    spYearofAwardPatentAwardedReq.setSelection(yearOfAwardOpetion.indexOf(String.valueOf(dataBean.getPat_patent_year())));
//                                    etYearofAwardPatentAwardedReq.setText(String.valueOf(dataBean.getPat_patent_year()));
                                    rlUploadFilePatentAwardedreq.setClickable(true);
                                    imgUploadFilePatentAwardedReq.setVisibility(View.GONE);
                                    imgClearPatentAwardedReq.setVisibility(View.VISIBLE);
                                    tvChooseFilePatentAwardedReq.setText(dataBean.getPat_file_name());
                                }
                            } else {
                                spAcademicYearPatentReq.setSelection(academicYearOpetion.indexOf(0));
                            }

                        }else {
                            Toast.makeText(ReqPatentAwardedActivity.this, "Academic Contribution opetion null or empty", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(ReqPatentAwardedActivity.this, "Exception:- "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ReqPatentAwardedActivity.this, "Response Empty or null", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);

    }


    private void callSavePatentAwardedApi(final String year, final String patent_name, final String patent_number, final String patent_year,
                                          final String user_id, final String ip, final String filename, final String emp_id) {

        DialogUtils.showProgressDialog(ReqPatentAwardedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_Patent_Awarded_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    //Toast.makeText(ReqPatentAwardedActivity.this, "Data Submited Successfully", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqPatentAwardedActivity.this, ReqPatentAwardedListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("year", year);
                params2.put("patent_name", patent_name);
                params2.put("patent_number", patent_number);
                params2.put("patent_year", patent_year);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("filename", filename);
                params2.put("emp_id", emp_id);
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


    private void updatePatentAwarded(final String id, final String year, final String patent_name, final String patent_number,
                                     final String patent_year, final String user_id, final String ip, final String filename,
                                     final String emp_id, final String hashfile) {

        DialogUtils.showProgressDialog(ReqPatentAwardedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_Patent_Awarded_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    // Toast.makeText(ReqPatentAwardedActivity.this, "Data Submited Successfully", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqPatentAwardedActivity.this, ReqPatentAwardedListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPatentAwardedActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("year", year);
                params2.put("patent_name", patent_name);
                params2.put("patent_number", patent_number);
                params2.put("patent_year", patent_year);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("filename", filename);
                params2.put("emp_id", emp_id);
                params2.put("hashfile", hashfile);
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
