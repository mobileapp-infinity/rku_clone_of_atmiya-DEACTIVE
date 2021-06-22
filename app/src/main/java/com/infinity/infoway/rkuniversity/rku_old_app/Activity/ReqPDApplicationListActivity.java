package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.ReqPDApplicationListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetPDApplicationListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


//TODO Pagination is Pading in this Activity
public class ReqPDApplicationListActivity extends AppCompatActivity implements ReqPDApplicationListAdapter.GetCorrentRecoredIdPDApplication,View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    FloatingActionButton fabAddPDApplication;
    RequestQueue queue;
    ListView lv_pd_application;
    List<GetPDApplicationListPojo.DataBean> reqGetPDApplicationList;
    ReqPDApplicationListAdapter reqPDApplicationListAdapter;
    String base64StringFile;
    String recoredId;

    ImageView ivSelectFilterOpetions;

    private ImageView iv_info;
    SimpleTooltip tooltip;
    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(ReqPDApplicationListActivity.this)
                .anchorView(view)
                .gravity(Gravity.BOTTOM)
                .modal(true)
                .arrowColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .text(getString(R.string.app_name))
                .contentView(R.layout.tooltip_comman)
                .build();
        tooltip.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdapplication_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        callGetPDApplicationListAPI("0", mySharedPreferecesRKUOLD.getUserID(), "1","1","1");

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

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);

        ivSelectFilterOpetions = findViewById(R.id.ivSelectFilterOpetions);
        ivSelectFilterOpetions.setOnClickListener(this);

        lv_pd_application = findViewById(R.id.lv_pd_application);

        fabAddPDApplication = findViewById(R.id.fabAddPDApplication);
        fabAddPDApplication.setOnClickListener(this);

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
        } else if (view.getId() == R.id.fabAddPDApplication) {
            Intent intent = new Intent(ReqPDApplicationListActivity.this, ReqPDApplicationActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_ADD_PD_APPLICATION);
        }else if (view.getId() == R.id.iv_info){
            showTooltip(view);
        }else if (view.getId() == R.id.ivSelectFilterOpetions){

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogLayout = getLayoutInflater().inflate(R.layout.inflater_filter_pd_application_list,null);
            builder.setView(dialogLayout);
            builder.setCancelable(true);

            final RadioButton rbtn_approved_approved = dialogLayout.findViewById(R.id.rbtn_approved_approved);
            final RadioButton rbtn_approved_pending = dialogLayout.findViewById(R.id.rbtn_approved_pending);
            final RadioButton rbtn_approved_fileUploadPending = dialogLayout.findViewById(R.id.rbtn_approved_fileUploadPending);
            final RadioButton rbtn_pending_pending = dialogLayout.findViewById(R.id.rbtn_pending_pending);

            rbtn_approved_approved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rbtn_approved_approved.setChecked(true);
                    rbtn_approved_pending.setChecked(false);
                    rbtn_approved_fileUploadPending.setChecked(false);
                    rbtn_pending_pending.setChecked(false);
                }
            });

            rbtn_approved_pending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rbtn_approved_pending.setChecked(true);
                    rbtn_approved_approved.setChecked(false);
                    rbtn_approved_fileUploadPending.setChecked(false);
                    rbtn_pending_pending.setChecked(false);
                }
            });

            rbtn_approved_fileUploadPending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rbtn_approved_fileUploadPending.setChecked(true);
                    rbtn_approved_pending.setChecked(false);
                    rbtn_approved_approved.setChecked(false);
                    rbtn_pending_pending.setChecked(false);
                }
            });

            rbtn_pending_pending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rbtn_pending_pending.setChecked(true);
                    rbtn_approved_fileUploadPending.setChecked(false);
                    rbtn_approved_pending.setChecked(false);
                    rbtn_approved_approved.setChecked(false);
                }
            });


            builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                    if (rbtn_approved_approved.isChecked()){
                        rbtn_approved_approved.setChecked(true);
                        callGetPDApplicationListAPI("0", mySharedPreferecesRKUOLD.getUserID(), "1","2","2");
                    }else if (rbtn_approved_pending.isChecked()){
                        rbtn_approved_pending.setChecked(true);
                        callGetPDApplicationListAPI("0", mySharedPreferecesRKUOLD.getUserID(), "1","2","1");
                    }else if (rbtn_approved_fileUploadPending.isChecked()){
                        rbtn_approved_fileUploadPending.setChecked(true);
                        callGetPDApplicationListAPI("0", mySharedPreferecesRKUOLD.getUserID(), "1","2","1");
                    }else if (rbtn_pending_pending.isChecked()){
                        rbtn_pending_pending.setChecked(true);
                        callGetPDApplicationListAPI("0", mySharedPreferecesRKUOLD.getUserID(), "1","1","1");
                    }

                }
            });

//            AppCompatButton btn_submit_filter = dialogLayout.findViewById(R.id.btn_submit_filter);
//
//            btn_submit_filter.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//
//                    if (rbtn_approved_approved.isChecked()){
//                        callGetPDApplicationListAPI("0",mySharedPrefereces.getUserID(), URLS.RowsPerPage, "1","2","2");
//                    }else if (rbtn_approved_pending.isChecked()){
//
//                    }else if (rbtn_approved_fileUploadPending.isChecked()){
//
//                    }else if (rbtn_pending_pending.isChecked()){
//                        callGetPDApplicationListAPI("0",mySharedPrefereces.getUserID(), URLS.RowsPerPage, "1","1","1");
//                    }
//
//
//
//                }
//            });

            builder.create().show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_ADD_PD_APPLICATION && resultCode == RESULT_OK) {
            callGetPDApplicationListAPI("0", mySharedPreferecesRKUOLD.getUserID(), "1","1","1");
        } else if (requestCode == IntentConstants.REQUEST_CODE_FOR_EDIT_PD_APPLICATION && resultCode == RESULT_OK) {
            callGetPDApplicationListAPI("0", mySharedPreferecesRKUOLD.getUserID(), "1","1","1");
        } else if (requestCode == IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT && resultCode == RESULT_OK) {
            try {

                String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                final File file = new File(filePath);

                if (file != null && file.length() > 0) {

                    if (file.length() > 2000000){ //2000000bytes == 2MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    }else {
                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64StringFile = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);

                        String id = recoredId;
                        String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                        String user_id = mySharedPreferecesRKUOLD.getUserID();
                        String ip = "0";
                        String File_Name = base64StringFile;

                        if (!TextUtils.isEmpty(recoredId)){
                            callUploadPDDocumentAPI(id,emp_id,user_id,ip,File_Name,String.valueOf(file.getName()));
                        }else {
                            DialogUtils.Show_Toast(ReqPDApplicationListActivity.this, "Can't Upload file");
                        }
                    }

                } else {
                    DialogUtils.Show_Toast(ReqPDApplicationListActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void callGetPDApplicationListAPI(final String id, final String user_id,
                                final String pageno,
                                final String status,final String doc_status) {
        DialogUtils.showProgressDialog(ReqPDApplicationListActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_i_hrhr_PD_Application_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        reqGetPDApplicationList = new ArrayList<>();
                        Gson gson = new Gson();
                        GetPDApplicationListPojo getPDApplicationListPojo = gson.fromJson("{\"Data\":" + response + "}", GetPDApplicationListPojo.class);
                        if (getPDApplicationListPojo.getData() != null && getPDApplicationListPojo.getData().size() > 0) {
                            reqGetPDApplicationList = getPDApplicationListPojo.getData();
                            reqPDApplicationListAdapter = new ReqPDApplicationListAdapter(ReqPDApplicationListActivity.this,  (ArrayList<GetPDApplicationListPojo.DataBean>) reqGetPDApplicationList, true);
                            lv_pd_application.setAdapter(reqPDApplicationListAdapter);
                        } else {
                            if (reqGetPDApplicationList != null){
                                reqGetPDApplicationList.clear();
                            }
                            reqPDApplicationListAdapter = new ReqPDApplicationListAdapter(ReqPDApplicationListActivity.this,  (ArrayList<GetPDApplicationListPojo.DataBean>) reqGetPDApplicationList, true);
                            lv_pd_application.setAdapter(reqPDApplicationListAdapter);
                            DialogUtils.Show_Toast(ReqPDApplicationListActivity.this, "No Data Found!");
                        }
                    }catch (Exception ex){
                        Toast.makeText(ReqPDApplicationListActivity.this, "Exception:- "+ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DialogUtils.Show_Toast(ReqPDApplicationListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPDApplicationListActivity.this, "Please Try Again Later");
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
                params2.put("rowperpage", "1000");
                params2.put("pageno", pageno);
                params2.put("status", status);
                params2.put("doc_status", doc_status);
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


    private void callUploadPDDocumentAPI(final String id, final String emp_id, final String user_id,
                                         final String ip, final String File_Name, final String File_Title) {
        DialogUtils.showProgressDialog(ReqPDApplicationListActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Insert_File_Upload_PD_Application, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    //Toast.makeText(ReqPatentAwardedActivity.this, "Data Submited Successfully", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqPDApplicationListActivity.this, responsePojo.getData().get(0).getMsg());
                    callGetPDApplicationListAPI("0", mySharedPreferecesRKUOLD.getUserID(), "1","1","1");
                } else {
                    DialogUtils.Show_Toast(ReqPDApplicationListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPDApplicationListActivity.this, "Please Try Again Later");
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


    @Override
    public void setRecoredId(String recoredId) {
        this.recoredId = recoredId;
    }

}
