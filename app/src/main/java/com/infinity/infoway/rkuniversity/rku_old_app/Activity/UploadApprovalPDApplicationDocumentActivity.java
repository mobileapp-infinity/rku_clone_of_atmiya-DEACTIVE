package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.UploadApprovalPDDocumentAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.UploadApprovalPDDocumentModel;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

public class UploadApprovalPDApplicationDocumentActivity extends AppCompatActivity implements View.OnClickListener, UploadApprovalPDDocumentAdapter.AddDeletePDApprovalFile {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;

    CustomTextView tvNameOftheEventUploadApprovalPDDocument, tvEventTypeUploadApprovalPDDocument,
            tvOrganizedByUploadApprovalPDDocument, tvEventCategoryUploadApprovalPDDocument;

    ArrayList<UploadApprovalPDDocumentModel> addMultipleFileModelPDArrayList;
    ArrayList<UploadApprovalPDDocumentModel> selectMultipleFileModelPDArrayList;
    UploadApprovalPDDocumentAdapter uploadApprovalPDDocumentAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView rvUploadApprovalPdDocument;

//    CustomBoldTextView tvSubmitApprovalPDDocument, tvCancelApprovalPDDocument;

    int selectTedFilePosition;
    String base64StringFile;
    boolean isFileSelected = false;
    private boolean isPDFileUploaded = false;

    String recoredId = "";
    String eventNamePDApproval = "";
    String eventTypePDApproval = "";
    String organizedbyPDApproval = "";
    String eventCategoryPDApproval = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_approval_pdapplication_document);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.UPLOAD_PD_DOCUMENT_ID)) {
            recoredId = getIntent().getStringExtra(IntentConstants.UPLOAD_PD_DOCUMENT_ID);
        }

        if (getIntent().hasExtra(IntentConstants.EVENT_NAME_PD_APPROVAL)) {
            eventNamePDApproval = getIntent().getStringExtra(IntentConstants.EVENT_NAME_PD_APPROVAL);
        }

        if (getIntent().hasExtra(IntentConstants.EVENT_TYPE_PD_APPROVAL)) {
            eventTypePDApproval = getIntent().getStringExtra(IntentConstants.EVENT_TYPE_PD_APPROVAL);
        }

        if (getIntent().hasExtra(IntentConstants.ORGANIZED_BY_PD_APPROVAL)) {
            organizedbyPDApproval = getIntent().getStringExtra(IntentConstants.ORGANIZED_BY_PD_APPROVAL);
        }

        if (getIntent().hasExtra(IntentConstants.EVENT_CATEGORY_PD_APPROVAL)) {
            eventCategoryPDApproval = getIntent().getStringExtra(IntentConstants.EVENT_CATEGORY_PD_APPROVAL);
        }


        tvNameOftheEventUploadApprovalPDDocument.setText(eventNamePDApproval);
        tvEventTypeUploadApprovalPDDocument.setText(eventTypePDApproval);
        tvOrganizedByUploadApprovalPDDocument.setText(organizedbyPDApproval);
        tvEventCategoryUploadApprovalPDDocument.setText(eventCategoryPDApproval);

    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Upload PD Approval Document");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);


        tvNameOftheEventUploadApprovalPDDocument = findViewById(R.id.tvNameOftheEventUploadApprovalPDDocument);
        tvEventTypeUploadApprovalPDDocument = findViewById(R.id.tvEventTypeUploadApprovalPDDocument);
        tvOrganizedByUploadApprovalPDDocument = findViewById(R.id.tvOrganizedByUploadApprovalPDDocument);
        tvEventCategoryUploadApprovalPDDocument = findViewById(R.id.tvEventCategoryUploadApprovalPDDocument);

//        tvSubmitApprovalPDDocument = findViewById(R.id.tvSubmitApprovalPDDocument);
//        tvSubmitApprovalPDDocument.setOnClickListener(this);
//        tvCancelApprovalPDDocument = findViewById(R.id.tvCancelApprovalPDDocument);
//        tvCancelApprovalPDDocument.setOnClickListener(this);

        rvUploadApprovalPdDocument = findViewById(R.id.rvUploadApprovalPdDocument);

        addMultipleFileModelPDArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        rvUploadApprovalPdDocument.setLayoutManager(layoutManager);

        UploadApprovalPDDocumentModel uploadMultipleDocumentPDAdapter = new UploadApprovalPDDocumentModel();
        uploadMultipleDocumentPDAdapter.setFileName("No File Chosen");
        addMultipleFileModelPDArrayList.add(uploadMultipleDocumentPDAdapter);

        uploadApprovalPDDocumentAdapter = new UploadApprovalPDDocumentAdapter(this, addMultipleFileModelPDArrayList);

        rvUploadApprovalPdDocument.setAdapter(uploadApprovalPDDocumentAdapter);


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

//        if (v.getId() == R.id.tvSubmitApprovalPDDocument){
//
//        }
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        }

    }

    @Override
    public void addFilePD(ArrayList<UploadApprovalPDDocumentModel> uploadApprovalPDDocumentModelArrayList) {
        isFileSelected = false;
        if (uploadApprovalPDDocumentModelArrayList != null) {
            UploadApprovalPDDocumentModel addMultipleFileModelPD = new UploadApprovalPDDocumentModel();
            addMultipleFileModelPD.setFileName("No file chosen");
            uploadApprovalPDDocumentModelArrayList.add(addMultipleFileModelPD);
            uploadApprovalPDDocumentAdapter = new UploadApprovalPDDocumentAdapter(this, uploadApprovalPDDocumentModelArrayList);
            rvUploadApprovalPdDocument.setAdapter(uploadApprovalPDDocumentAdapter);
        }
    }

    @Override
    public void removeFilePD(ArrayList<UploadApprovalPDDocumentModel> uploadApprovalPDDocumentModelArrayList, int position) {

        if (uploadApprovalPDDocumentModelArrayList != null) {
            uploadApprovalPDDocumentModelArrayList.remove(position);
            uploadApprovalPDDocumentAdapter = new UploadApprovalPDDocumentAdapter(this, uploadApprovalPDDocumentModelArrayList);
            rvUploadApprovalPdDocument.setAdapter(uploadApprovalPDDocumentAdapter);
        }

        selectMultipleFileModelPDArrayList = uploadApprovalPDDocumentModelArrayList;

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
    public void selectFilePD(ArrayList<UploadApprovalPDDocumentModel> uploadApprovalPDDocumentModelArrayList1, int position) {
        selectTedFilePosition = position;
        selectMultipleFileModelPDArrayList = uploadApprovalPDDocumentModelArrayList1;

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
                        selectMultipleFileModelPDArrayList.get(selectTedFilePosition).setFileName(file.getName());
                        uploadApprovalPDDocumentAdapter = new UploadApprovalPDDocumentAdapter(this, selectMultipleFileModelPDArrayList);
                        rvUploadApprovalPdDocument.setAdapter(uploadApprovalPDDocumentAdapter);

                        String id = recoredId;
                        String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                        String user_id = mySharedPreferecesRKUOLD.getUserID();
                        String ip = "0";
                        String File_Name = base64StringFile;

                        if (!TextUtils.isEmpty(recoredId)) {
                            callUploadApprovalPDDocumentAPI(id, emp_id, user_id, ip, File_Name);
                        } else {
                            Toast.makeText(this, "Recored Id Null or Empty", Toast.LENGTH_SHORT).show();
                        }

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
                    DialogUtils.Show_Toast(UploadApprovalPDApplicationDocumentActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


    private void callUploadApprovalPDDocumentAPI(final String id, final String emp_id, final String user_id,
                                                 final String ip, final String File_Name) {

        DialogUtils.showProgressDialog(UploadApprovalPDApplicationDocumentActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Insert_Approved_Record_Document_Upload_PD_Application, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    //Toast.makeText(ReqPatentAwardedActivity.this, "Data Submited Successfully", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    isPDFileUploaded = true;
                    DialogUtils.Show_Toast(UploadApprovalPDApplicationDocumentActivity.this, responsePojo.getData().get(0).getMsg());
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(UploadApprovalPDApplicationDocumentActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(UploadApprovalPDApplicationDocumentActivity.this, "Please Try Again Later");
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

    @Override
    public void onBackPressed() {
        if (isFileSelected) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Please Select File", Toast.LENGTH_SHORT).show();
        }
    }

}
