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
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewBookOrChapterPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.ApiConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
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


public class ReqBookOrChapterPublicationActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    int hasfile = 0;
    String base64StringFile;
    Calendar myCalendar;
    ReqViewBookOrChapterPojo.DataBean dataBean;

    ArrayList<String> academicYearOpetion = new ArrayList<>();
    HashMap<String, Integer> hashMapAcademicYearAndID = new HashMap<>();

    Spinner spAcademicYearBookOrChapterReq;
    CustomEditText etTitleofBookorChapterPublicationReq, etNameofPublisherBookOrChapterReq;
    CustomTextView tvDateOfPublicationBookorChapterReq, tvChooseFileBookOrChapterReq;
    ImageView imvCalenderDateOfPublicationBookorChapterReq, imgUploadFileBookOrChapterReq,
            imgClearBookorChapterdReq, ivBack;
    RelativeLayout rlUploadFilePBookorChapterreq;
    LinearLayout ll_submit_cancel_book_or_chapter_req, ll_update_book_or_chapter_req;
    CustomBoldTextView tvSubmitBookOrChapterdReq, tvCancelBookOrChapterReq, tvUpdateBookOrChapterReq,
            tvCancelBookOrChapterReqUpdate;

    private ImageView iv_info;
    SimpleTooltip tooltip;
    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(ReqBookOrChapterPublicationActivity.this)
                .anchorView(view)
                .gravity(Gravity.BOTTOM)
                .modal(true)
                .arrowColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .text(getString(R.string.app_name))
                .contentView(R.layout.tooltip_file_upload_size)
                .build();
        tooltip.show();
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String currentDate = simpleDateFormat.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_book_or_chapter_publication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        tvDateOfPublicationBookorChapterReq.setText(currentDate);
        getYearApiCall();
    }


    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Book/Chapter Publication");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);

        myCalendar = Calendar.getInstance();

        spAcademicYearBookOrChapterReq = findViewById(R.id.spAcademicYearBookOrChapterReq);

        academicYearOpetion.add("Select Year");

        etTitleofBookorChapterPublicationReq = findViewById(R.id.etTitleofBookorChapterPublicationReq);
        etNameofPublisherBookOrChapterReq = findViewById(R.id.etNameofPublisherBookOrChapterReq);

        tvDateOfPublicationBookorChapterReq = findViewById(R.id.tvDateOfPublicationBookorChapterReq);
        tvChooseFileBookOrChapterReq = findViewById(R.id.tvChooseFileBookOrChapterReq);
        tvChooseFileBookOrChapterReq.setOnClickListener(this);

        imvCalenderDateOfPublicationBookorChapterReq = findViewById(R.id.imvCalenderDateOfPublicationBookorChapterReq);
        imvCalenderDateOfPublicationBookorChapterReq.setOnClickListener(this);
        imgUploadFileBookOrChapterReq = findViewById(R.id.imgUploadFileBookOrChapterReq);
        imgUploadFileBookOrChapterReq.setOnClickListener(this);
        imgClearBookorChapterdReq = findViewById(R.id.imgClearBookorChapterdReq);
        imgClearBookorChapterdReq.setOnClickListener(this);

        rlUploadFilePBookorChapterreq = findViewById(R.id.rlUploadFilePBookorChapterreq);
        rlUploadFilePBookorChapterreq.setOnClickListener(this);

        ll_submit_cancel_book_or_chapter_req = findViewById(R.id.ll_submit_cancel_book_or_chapter_req);
        ll_submit_cancel_book_or_chapter_req.setOnClickListener(this);
        ll_update_book_or_chapter_req = findViewById(R.id.ll_update_book_or_chapter_req);
        ll_update_book_or_chapter_req.setOnClickListener(this);

        tvSubmitBookOrChapterdReq = findViewById(R.id.tvSubmitBookOrChapterdReq);
        tvSubmitBookOrChapterdReq.setOnClickListener(this);
        tvCancelBookOrChapterReq = findViewById(R.id.tvCancelBookOrChapterReq);
        tvCancelBookOrChapterReq.setOnClickListener(this);
        tvUpdateBookOrChapterReq = findViewById(R.id.tvUpdateBookOrChapterReq);
        tvUpdateBookOrChapterReq.setOnClickListener(this);
        tvCancelBookOrChapterReqUpdate = findViewById(R.id.tvCancelBookOrChapterReqUpdate);
        tvCancelBookOrChapterReqUpdate.setOnClickListener(this);

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
        if (academicYearOpetion.get(spAcademicYearBookOrChapterReq.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Select Academic Year");
            return false;
        } else if (TextUtils.isEmpty(etTitleofBookorChapterPublicationReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Enter Title of Book/Chapter");
            return false;
        } else if (TextUtils.isEmpty(tvDateOfPublicationBookorChapterReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Enter Date of Publication");
            return false;
        } else if (TextUtils.isEmpty(etNameofPublisherBookOrChapterReq.getText().toString())) {
            DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Enter Name of Publisher");
            return false;
        } else if (!tvChooseFileBookOrChapterReq.getText().toString().contains(".pdf")) {
            DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Select File");
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.tvCancelBookOrChapterReqUpdate) {
            onBackPressed();
        } else if (view.getId() == R.id.tvCancelBookOrChapterReq) {
            onBackPressed();
        } else if (view.getId() == R.id.tvSubmitBookOrChapterdReq) {
            //submit
            if (isValid()) {
                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearBookOrChapterReq.getSelectedItemPosition())));
                String Book_Title = etTitleofBookorChapterPublicationReq.getText().toString();
                String Publication_date = tvDateOfPublicationBookorChapterReq.getText().toString();
                String publication_name = etNameofPublisherBookOrChapterReq.getText().toString();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String filename;
                if (!TextUtils.isEmpty(base64StringFile)) {
                    filename = base64StringFile;
                    callSaveBookOrChapterdApi(year_id, Book_Title, Publication_date, publication_name, user_id, ip,emp_id,filename);
                } else {
                    DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Select File");
                }
            }
        } else if (view.getId() == R.id.tvUpdateBookOrChapterReq) {
            //update
            if (isValid()){

                String year_id = String.valueOf(hashMapAcademicYearAndID.get(academicYearOpetion.get(spAcademicYearBookOrChapterReq.getSelectedItemPosition())));
                String Book_Title = etTitleofBookorChapterPublicationReq.getText().toString();
                String Publication_date = tvDateOfPublicationBookorChapterReq.getText().toString();
                String publication_name = etNameofPublisherBookOrChapterReq.getText().toString();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String filename;

                if (hasfile == 0){
                    if (dataBean != null){
                       callUpdateBookOrChapterdApi(String.valueOf(dataBean.getId()),year_id,Book_Title,Publication_date,publication_name,user_id,ip,emp_id,"",String.valueOf(hasfile));
                    }
                }else {
                    if (dataBean != null &&!TextUtils.isEmpty(base64StringFile)){
                        filename = base64StringFile;
                        callUpdateBookOrChapterdApi(String.valueOf(dataBean.getId()),year_id,Book_Title,Publication_date,publication_name,user_id,ip,emp_id,filename,String.valueOf(hasfile));
                    }else {
                        DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Select File");
                    }
                }
            }
        } else if (view.getId() == R.id.imgClearBookorChapterdReq) {
            //clear file
            tvChooseFileBookOrChapterReq.setClickable(true);
            rlUploadFilePBookorChapterreq.setClickable(true);
            tvChooseFileBookOrChapterReq.setText("Choose File");
            imgClearBookorChapterdReq.setVisibility(View.GONE);
            imgUploadFileBookOrChapterReq.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.tvChooseFileBookOrChapterReq || view.getId() == R.id.rlUploadFilePBookorChapterreq || view.getId() == R.id.imgUploadFileBookOrChapterReq) {
            //chose or upload file
            new MaterialFilePicker()
                    .withActivity(this)
                    .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                    .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                    .withFilterDirectories(false) // Set directories filterable (false by default)
                    .withHiddenFiles(true) // Show hidden files and folders
                    .start();

        } else if (view.getId() == R.id.imvCalenderDateOfPublicationBookorChapterReq) {

            final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat1 = "dd/MM/yyyy";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
                    SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                    tvDateOfPublicationBookorChapterReq.setText(sdf.format(myCalendar.getTime()) + "");
                }
            };

            DatePickerDialog datePickerDialog = new DatePickerDialog(ReqBookOrChapterPublicationActivity.this, date1, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }else if (view.getId() == R.id.iv_info){
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

                    if (file.length() > 2000000){ //2000000bytes == 2MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    }else {
                        hasfile = 1;

                        tvChooseFileBookOrChapterReq.setClickable(false);
                        rlUploadFilePBookorChapterreq.setClickable(false);
                        imgUploadFileBookOrChapterReq.setVisibility(View.GONE);
                        imgClearBookorChapterdReq.setVisibility(View.VISIBLE);

                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64StringFile = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);
                        tvChooseFileBookOrChapterReq.setText(file.getName());

                    }


                } else {
                    DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }


    private void getYearApiCall() {
        DialogUtils.showProgressDialog(ReqBookOrChapterPublicationActivity.this, "");
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
//                            spAcademicYearBookOrChapterReq.setAdapter(academicYearAdapter);

                            SpinnerSimpleAdapter academicYearAdapter  = new SpinnerSimpleAdapter(ReqBookOrChapterPublicationActivity.this, academicYearOpetion);
                            spAcademicYearBookOrChapterReq.setAdapter(academicYearAdapter );

                            if (getIntent().hasExtra(IntentConstants.REQ_VIEW_BOOK_OR_CHAPTER_POJO)){
                                ll_submit_cancel_book_or_chapter_req.setVisibility(View.GONE);
                                ll_update_book_or_chapter_req.setVisibility(View.VISIBLE);
                                dataBean = (ReqViewBookOrChapterPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.REQ_VIEW_BOOK_OR_CHAPTER_POJO);

                                if (dataBean != null){
                                    spAcademicYearBookOrChapterReq.setSelection(academicYearOpetion.indexOf(dataBean.getYear_name()));
                                    etTitleofBookorChapterPublicationReq.setText(dataBean.getNpc_book_title());
                                    tvDateOfPublicationBookorChapterReq.setText(String.valueOf(dataBean.getNpc_publication_date()));
                                    etNameofPublisherBookOrChapterReq.setText(String.valueOf(dataBean.getNpc_publisher_name()));
                                    rlUploadFilePBookorChapterreq.setClickable(true);
                                    imgUploadFileBookOrChapterReq.setVisibility(View.GONE);
                                    imgClearBookorChapterdReq.setVisibility(View.VISIBLE);
                                    tvChooseFileBookOrChapterReq.setText(dataBean.getNpc_file_name());
                                }
                            }else {
                            spAcademicYearBookOrChapterReq.setSelection(academicYearOpetion.indexOf(0));
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
                DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(requestGetYear);
    }


    private void callSaveBookOrChapterdApi(final String year_id, final String Book_Title, final String Publication_date, final String publication_name,
                                           final String user_id, final String ip, final String emp_id, final String filename) {

        DialogUtils.showProgressDialog(ReqBookOrChapterPublicationActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Save_Book_Chapter_Publication, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                  //  Toast.makeText(ReqBookOrChapterPublicationActivity.this, response, Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqBookOrChapterPublicationActivity.this, ReqBookOrChapterListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("year_id", year_id);
                params2.put("Book_Title", Book_Title);
                params2.put("Publication_date", Publication_date);
                params2.put("publication_name", publication_name);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("emp_id", emp_id);
                params2.put("filename", filename);
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


    private void callUpdateBookOrChapterdApi(final String id,final String year_id, final String Book_Title, final String Publication_date, final String publication_name,
                                           final String user_id, final String ip, final String emp_id, final String filename,final String hashfile) {

        DialogUtils.showProgressDialog(ReqBookOrChapterPublicationActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Update_Book_Chapter_Publication, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    //Toast.makeText(ReqBookOrChapterPublicationActivity.this, response, Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, responsePojo.getData().get(0).getMsg());
                    Intent intent = new Intent(ReqBookOrChapterPublicationActivity.this, ReqBookOrChapterListActivity.class);
                    setResult(RESULT_OK, intent);
                    finish();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqBookOrChapterPublicationActivity.this, "Please Try Again Later");
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
                params2.put("Book_Title", Book_Title);
                params2.put("Publication_date", Publication_date);
                params2.put("publication_name", publication_name);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("emp_id", emp_id);
                params2.put("filename", filename);
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
