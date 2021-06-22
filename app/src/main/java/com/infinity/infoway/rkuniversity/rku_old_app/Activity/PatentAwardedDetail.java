package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomButtonView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.FileAndFolderInfo;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ConAppRejPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PatentAwaredPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatentAwardedDetail extends AppCompatActivity implements View.OnClickListener {

    CustomBoldTextView tvapprove;
    CustomBoldTextView tvdelete;
    LinearLayout llupdatedelete;
    EditText edtempname;
    LinearLayout llempname;
    EditText edtyear;
    LinearLayout llleavebalance;
    EditText edtnamepatentpublished;
    LinearLayout llnamepatentpublished;
    EditText edttitleofreachpaper;
    LinearLayout lltitleofreachpaper;
    EditText edtyearawd;
    LinearLayout llyearawd;
    EditText edtlvljou;
    ImageView ivcalendar;
    LinearLayout lllvljou;
    EditText edtpatentno;
    LinearLayout llpatentno;
    EditText edttypeauth;
    LinearLayout lltypeauth;
    EditText edtugccare;
    LinearLayout llugccare;
    EditText edtleveljou;
    LinearLayout llleveljou;
    EditText edtisbn;
    LinearLayout llisbn;
    EditText edtappdate;
    LinearLayout llapproveddt;
    EditText edtappby;
    LinearLayout llapprovedby;
    ImageView ivfile;
    LinearLayout lldoc;
    CustomBoldTextView tvsubmit;
    CustomBoldTextView tvcancel;
    LinearLayout llsubmitcancel;

    MarshMallowPermission marshMallowPermission;

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ImageView iv_back;
    CustomBoldTextView txt_act;
    CustomBoldTextView tv_emp_code, tv_version, tv_version_code;
    RequestQueue queue;
    Activity activity;
    String ID = "", status = "";
    PatentAwaredPojo patentAwaredPojo;
    //written by remish varsani to check make field editable on 16-03-2020
    LinearLayout ll_update_delete, ll_approved_dt, ll_approved_by, ll_leave_balance, ll_doc;
//    Spinner spAcademicYearPatent;
//    ArrayList<String> academicYearOpetion = new ArrayList<>();

//    LinearLayout ll_submit_cancelPatent;
//    RelativeLayout rlUploadFilePatent;
//    ImageView imgUploadFilePatent,imgClearPatent;
//    CustomTextView tvChooseFile;
//    CustomBoldTextView tv_submitPatent,tv_cancelPatent;

    public static boolean is_back_jounral_pub = false;
//    String base64StringFile;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = simpleDateFormat.format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patent_awarded_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_act);
        setSupportActionBar(toolbar);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                is_back_jounral_pub = true;
                onBackPressed();
            }
        });
        activity = PatentAwardedDetail.this;
        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        txt_act.setText("Patent Awarded Detail");
        activity = PatentAwardedDetail.this;
        initView();

        //written by remish varsani to make filed editable or not on 16-03-2020
        //commant by remish varsani because of unused 05-04-2020
//        if (getIntent().hasExtra(IntentConstants.IS_EDITABLE)){
//            if (getIntent().getBooleanExtra(IntentConstants.IS_EDITABLE,false)){
//                llempname.setVisibility(View.GONE);
//                ll_update_delete.setVisibility(View.GONE);
//                ll_approved_dt.setVisibility(View.GONE);
//                ll_approved_by.setVisibility(View.GONE);
//                ll_leave_balance.setVisibility(View.GONE);
//                llAcademicYearPatent.setVisibility(View.VISIBLE);
//                ll_submit_cancelPatent.setVisibility(View.VISIBLE);
//                ll_doc.setVisibility(View.GONE);
//                llUploadDocumentPatent.setVisibility(View.VISIBLE);
//                getYearApiCall();
//            }
//        }

        Intent intent = getIntent();

        ID = intent.getStringExtra("ID");
        System.out.println("Id of ConferencePubApproveReject from intent ::::::::::::::::::::::::: " + ID);
        status = intent.getStringExtra("status");

        if (ID != null && status != null && !ID.contentEquals("") && !status.contentEquals("")) {
            PatentAwardedDetailAPI(ID, status);
        }

        if (status != null && !status.contentEquals("")) {
//            if(status.compareToIgnoreCase("pending")!=0)
            if (status.compareToIgnoreCase(IntentConstants.PENDING_STATUS_FOR_APPROVAL) != 0) {
                llupdatedelete.setVisibility(View.GONE);
            } else {
                llupdatedelete.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onBackPressed() {
        is_back_jounral_pub = true;
        super.onBackPressed();
    }

    private void PatentAwardedDetailAPI(String id, String status) {

//        if (status.compareToIgnoreCase("Pending")==0)
        if (status.compareToIgnoreCase(IntentConstants.PENDING_STATUS_FOR_APPROVAL) == 0) {
            status = "1";
        } else {
            status = "2";
        }
        queue = Volley.newRequestQueue(PatentAwardedDetail.this);

        String url = URLS.Get_Patent_awarded_list + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&emp_id=" + mySharedPreferecesRKUOLD.getEmpID() + "&RowsPerPage=" + URLS.RowsPerPage + "&PageNumber=" + "1" + "&status=" + status + "&id=" + id + "";

        url = url.replace(" ", "%20");
        System.out.println("Get_Patent_awarded_list URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                //System.out.println("response of Get_leave_approve_list !!!!!!!!!!! " + response);
                response = response + "";

                System.out.println("Get_Patent_awarded_list response size in leave listing :::::::::::::::::::::::::::::::::::::: " + response.length());
                if (response.length() > 10) {
                    response = "{\"Data\":" + response + "}";


                    // System.out.println("sucess response v !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();

                    patentAwaredPojo = gson.fromJson(response, PatentAwaredPojo.class);
                    System.out.println("Get_Patent_awarded_list data size :::::::::::::::::::::::::::::::::::::" + patentAwaredPojo.getData().size());


                    if (patentAwaredPojo != null) {
                        if (patentAwaredPojo.getData() != null) {
                            if (patentAwaredPojo.getData().get(0) != null) {
                                if (patentAwaredPojo.getData().size() > 0) {

                                    if (llempname.getVisibility() == View.VISIBLE) {
                                        edtempname.setText(patentAwaredPojo.getData().get(0).getEmployee_name() + "");
                                    }
                                    edtyear.setText(patentAwaredPojo.getData().get(0).getAcademic_Year() + "");
                                    edtnamepatentpublished.setText(patentAwaredPojo.getData().get(0).getName_of_Patent_Published_Awarded() + "");
                                    edtyearawd.setText(patentAwaredPojo.getData().get(0).getYear_of_Award() + "");
                                    edtpatentno.setText(patentAwaredPojo.getData().get(0).getPatent_Number() + "");
                                    edtappdate.setText(patentAwaredPojo.getData().get(0).getApprove_rejct_date() + "");
                                    edtappby.setText(patentAwaredPojo.getData().get(0).getApprove_reject_by_user() + "");

                                    ivfile.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                                                marshMallowPermission.requestPermissionForExternalStorage();
                                            } else {

                                                if (patentAwaredPojo.getData().get(0).getDownload_Document() != null &&
                                                        !patentAwaredPojo.getData().get(0).getDownload_Document().isEmpty()) {
                                                    lldoc.setVisibility(View.VISIBLE);
                                                    String url_con = patentAwaredPojo.getData().get(0).getDownload_Document() + "";
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
                                                        new DownloadFileFromURL(extention).execute(url);
                                                    } else {
                                                        DialogUtils.Show_Toast(PatentAwardedDetail.this, "No Documents Available");
                                                    }
                                                } else {
                                                    Toast.makeText(activity, "File Not Found!", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        }
                                    });


                                } else {

                                    System.out.println("else  calll ################");
                                    DialogUtils.Show_Toast(PatentAwardedDetail.this, "No Records Found");
                                }

                            }
                        }
                    }
                } else {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PatentAwardedDetail.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
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

            DialogUtils.showProgressDialog(activity, "");

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
//                dir.mkdirs();
//
//
//                System.out.println("path of file>>>>>" + dir.getAbsolutePath());
//                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();
                //  Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());


                File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + FileAndFolderInfo.FOLDER_NAME + "/PatentAwardApproval/");
                folder.mkdirs();

                String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "PatentAwardApproval",
                        currentDate, nameoffile);

                File file = new File(folder, actualFileName);
                file.createNewFile();
                FileOutputStream output = new FileOutputStream(file);


//                OutputStream output = new FileOutputStream("sdcard/Rku/" + "/PatentAwarded/" + nameoffile);

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


            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
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

            DialogUtils.Show_Toast(PatentAwardedDetail.this, "Download Completed Successfully");

//            System.out.println("EXTENSION OF FILE onPostExecute::::::::::" + Extenson);
//
//            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Rku/" + "/PatentAwarded/" + nameoffile);
//            Log.d("pathoffile", String.valueOf(file11));
////                                    Uri uri = FileProvider.getUriForFile(AssignmentActivity.this, "com.infinity.infoway.atmiya.activity.AssignmentActivity.fileprovider", file11);
//

            String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "PatentAwardApproval",
                    currentDate, nameoffile);

            File file11 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/" + FileAndFolderInfo.FOLDER_NAME + "/PatentAwardApproval/" + actualFileName);


            /// for  opening downloaded documentssssssssssss
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(PatentAwardedDetail.this, PatentAwardedDetail.this.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                PatentAwardedDetail.this.grantUriPermission(PatentAwardedDetail.this.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent = null;
                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(uri, "application/pdf");
                }
                intent = Intent.createChooser(target, "Open File");
                try {
                    PatentAwardedDetail.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(PatentAwardedDetail.this, "No Apps can performs This action");
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
                    PatentAwardedDetail.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(PatentAwardedDetail.this, "No Apps can performs This action");

                }

            }


        }

    }

    public void showDialog(final Context context, final String approve_reject, final String ID) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.popup_miss_punch, null);

        final EditText edt_reason = (EditText) dialogView.findViewById(R.id.edt_reason);
        CustomBoldTextView tv_titile = (CustomBoldTextView) dialogView.findViewById(R.id.tv_titile);
        tv_titile.setText(context.getResources().getString(R.string.app_name));
        CustomButtonView dialogButtonCancel = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonCancel);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //  final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
        final AlertDialog b = builder.create();
        //  builder.setTitle("Material Style Dialog");
        builder.setCancelable(true);
        builder.setView(dialogView);
        b.setCanceledOnTouchOutside(true);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //  builder.
        final AlertDialog show = builder.show();
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();

            }
        });
        CustomButtonView dialogButtonOK = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonOK);
        dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isvalidate(edt_reason)) {
                    show.dismiss();

//                    if (approve_reject.compareToIgnoreCase("approve")==0)
                    if (approve_reject.compareToIgnoreCase(IntentConstants.APPROVED_STATUS_FOR_APPROVAL) == 0) {
                        ApproveConfarancePub(edt_reason.getText().toString().trim(), show, ID, IntentConstants.APPROVED_STATUS_FOR_APPROVAL);
                    } else {
                        ApproveConfarancePub(edt_reason.getText().toString().trim(), show, ID, IntentConstants.REJECT_STATUS_FOR_APPROVAL);
                        // RejectLeave(edt_reason.getText().toString().trim(), show, pos,ID,"reject");
                    }

                }
            }
        });
    }

    private boolean isvalidate(EditText edt_reason) {
        if (edt_reason.getText().toString().trim().isEmpty() || edt_reason.getText().toString().contentEquals("") || edt_reason.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(PatentAwardedDetail.this, "Enter Reason");
            return false;
        }

        return true;
    }

    ConAppRejPojo conAppRejPojo;

    private void ApproveConfarancePub(String reason, AlertDialog show, final String ID, String app_rej) {

        DialogUtils.showProgressDialog(PatentAwardedDetail.this, "");
        String url;

//        if (app_rej.compareToIgnoreCase("approve")==0)
        if (app_rej.compareToIgnoreCase(IntentConstants.APPROVED_STATUS_FOR_APPROVAL) == 0) {
            url = URLS.Get_Patent_awarded_Approved_or_Reject + "&id=" + ID + "&remarks=" + reason + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&ip=" + "" + "&approve_reject=" + "1" + "";

        } else {
            url = URLS.Get_Patent_awarded_Approved_or_Reject + "&id=" + ID + "&remarks=" + reason + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&ip=" + "" + "&approve_reject=" + "2" + "";

        }

        url = url.replace(" ", "%20");
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        DialogUtils.hideProgressDialog();
                        response = response + "";
                        response = "{\"Data\":" + response + "}";
                        System.out.println("THIS Get_publication_conference_Approved_or_Reject  RESPONSE      !!!!!!!!!!!!!!!!!!!" + response + "");


                        System.out.println("response length :::::::::::::: " + response.length());
                        System.out.println("response data size  :::::::::::::: " + response);

                        if (response.length() > 10) {
                            Gson gson = new Gson();

                            conAppRejPojo = gson.fromJson(response, ConAppRejPojo.class);
                            if (conAppRejPojo != null) {
                                if (conAppRejPojo.getData() != null) {
                                    if (conAppRejPojo.getData().get(0) != null && conAppRejPojo.getData().size() > 0) {

                                        if (!conAppRejPojo.getData().get(0).getMsg().contentEquals("")) {
                                            DialogUtils.Show_Toast(PatentAwardedDetail.this, conAppRejPojo.getData().get(0).getMsg());

                                            finish();
                                        }


                                    }
                                }
                            }


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.hideProgressDialog();
                System.out.println();
            }
        });
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);


    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());

        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

//        rlUploadFilePatent = findViewById(R.id.rlUploadFilePatent);
//        rlUploadFilePatent.setOnClickListener(this);
//        imgUploadFilePatent = findViewById(R.id.imgUploadFilePatent);
//        imgUploadFilePatent.setOnClickListener(this);
//        imgClearPatent = findViewById(R.id.imgClearPatent);
//        imgClearPatent.setOnClickListener(this);
//        tvChooseFile = findViewById(R.id.tvChooseFile);
//        tvChooseFile.setOnClickListener(this);
//        tv_submitPatent = findViewById(R.id.tv_submitPatent);
//        tv_submitPatent.setOnClickListener(this);

//        tv_cancelPatent = findViewById(R.id.tv_cancelPatent);
//        tv_cancelPatent.setOnClickListener(this);

//        ll_submit_cancelPatent = findViewById(R.id.ll_submit_cancelPatent);
        ll_update_delete = findViewById(R.id.ll_update_delete);
        ll_approved_dt = findViewById(R.id.ll_approved_dt);
        ll_approved_by = findViewById(R.id.ll_approved_by);
        ll_leave_balance = findViewById(R.id.ll_leave_balance);
//        llAcademicYearPatent = findViewById(R.id.llAcademicYearPatent);
        ll_doc = findViewById(R.id.ll_doc);
//        llUploadDocumentPatent = findViewById(R.id.llUploadDocumentPatent);

//        spAcademicYearPatent = findViewById(R.id.spAcademicYearPatent);


//        academicYearOpetion.add("Select Year");
//        academicYearOpetion.add("2017-18");
//        academicYearOpetion.add("2018-19");
//        academicYearOpetion.add("2019-20");
//        academicYearOpetion.add("2020-21");

//        ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, academicYearOpetion);
//        spAcademicYearPatent.setAdapter(academicYearAdapter); // this will set list of values to spinner
//        spAcademicYearPatent.setSelection(academicYearOpetion.indexOf(0));

        queue = Volley.newRequestQueue(this);
        marshMallowPermission = new MarshMallowPermission(activity);
        tvapprove = (CustomBoldTextView) findViewById(R.id.tv_approve);


        tvapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(PatentAwardedDetail.this, IntentConstants.APPROVED_STATUS_FOR_APPROVAL, ID);

            }
        });
        tvdelete = (CustomBoldTextView) findViewById(R.id.tv_delete);
        tvdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showDialog4YNo(PatentAwardedDetail.this, "", "Are You Sure To Reject ?", new DialogUtils.DailogCallBackOkButtonClick() {
                    @Override
                    public void onDialogOkButtonClicked() {
                        showDialog(PatentAwardedDetail.this, IntentConstants.REJECT_STATUS_FOR_APPROVAL, ID);


                    }
                }, new DialogUtils.DailogCallBackCancelButtonClick() {
                    @Override
                    public void onDialogCancelButtonClicked() {

                    }
                });
            }
        });
        llupdatedelete = (LinearLayout) findViewById(R.id.ll_update_delete);
        edtempname = (EditText) findViewById(R.id.edt_emp_name);
        llempname = (LinearLayout) findViewById(R.id.ll_emp_name);
        edtyear = (EditText) findViewById(R.id.edt_year);
        llleavebalance = (LinearLayout) findViewById(R.id.ll_leave_balance);
        edtnamepatentpublished = (EditText) findViewById(R.id.edt_name_patent_published);
        llnamepatentpublished = (LinearLayout) findViewById(R.id.ll_name_patent_published);
        edttitleofreachpaper = (EditText) findViewById(R.id.edt_title_of_reach_paper);
        lltitleofreachpaper = (LinearLayout) findViewById(R.id.ll_title_of_reach_paper);
        edtyearawd = (EditText) findViewById(R.id.edt_year_awd);
        llyearawd = (LinearLayout) findViewById(R.id.ll_year_awd);
        edtlvljou = (EditText) findViewById(R.id.edt_lvl_jou);
        ivcalendar = (ImageView) findViewById(R.id.iv_calendar);
        lllvljou = (LinearLayout) findViewById(R.id.ll_lvl_jou);
        edtpatentno = (EditText) findViewById(R.id.edt_patent_no);
        llpatentno = (LinearLayout) findViewById(R.id.ll_patent_no);
        edttypeauth = (EditText) findViewById(R.id.edt_type_auth);
        lltypeauth = (LinearLayout) findViewById(R.id.ll_type_auth);
        edtugccare = (EditText) findViewById(R.id.edt_ugc_care);
        llugccare = (LinearLayout) findViewById(R.id.ll_ugc_care);
        edtleveljou = (EditText) findViewById(R.id.edt_level_jou);
        llleveljou = (LinearLayout) findViewById(R.id.ll_level_jou);
        edtisbn = (EditText) findViewById(R.id.edt_isbn);
        llisbn = (LinearLayout) findViewById(R.id.ll_isbn);
        edtappdate = (EditText) findViewById(R.id.edt_app_date);
        llapproveddt = (LinearLayout) findViewById(R.id.ll_approved_dt);
        edtappby = (EditText) findViewById(R.id.edt_app_by);
        llapprovedby = (LinearLayout) findViewById(R.id.ll_approved_by);
        ivfile = (ImageView) findViewById(R.id.iv_file);
        lldoc = (LinearLayout) findViewById(R.id.ll_doc);
        tvsubmit = (CustomBoldTextView) findViewById(R.id.tv_submit);
        tvcancel = (CustomBoldTextView) findViewById(R.id.tv_cancel);
        llsubmitcancel = (LinearLayout) findViewById(R.id.ll_submit_cancel);
    }


    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.rlUploadFile || view.getId() == R.id.tvChooseFile){
//            UploadDocumentUtility.uploadDocument(this,"application/pdf","Select PDF",IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT);
//        }else if (view.getId() == R.id.imgClearPatent){
//            tvChooseFile.setClickable(true);
//            rlUploadFilePatent.setClickable(true);
//            tvChooseFile.setText("Choose File");
//            imgClearPatent.setVisibility(View.GONE);
//            imgUploadFilePatent.setVisibility(View.VISIBLE);
//        }else if (view.getId() == R.id.tv_submitPatent){
//            if (isValid()){
//                Toast.makeText(this, "Validation Correct", Toast.LENGTH_SHORT).show();
//            }
//        }else if (view.getId() == R.id.tv_cancelPatent){
//            onBackPressed();
//        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT &&resultCode == RESULT_OK) {
////
////            Uri uri;
////
////            if (data.getData() == null){
////                uri = (Uri)data.getExtras().get("data");
////            }else {
////                uri = data.getData();
////            }
////
////            try {
////                tvChooseFile.setClickable(false);
////                rlUploadFilePatent.setClickable(false);
////                imgUploadFilePatent.setVisibility(View.GONE);
////                imgClearPatent.setVisibility(View.VISIBLE);
////
////                String filePath = data.getData().getPath();
////                String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
////                tvChooseFile.setText(filename);
////
////                String selectedFilePath = FileUtils.getPath(this, uri);
////                final File file = new File(selectedFilePath);
////
////                byte[] buffer = new byte[(int) file.length() + 100];
////                int length = new FileInputStream(file).read(buffer);
////                base64StringFile = Base64.encodeToString(buffer, 0, length,
////                        Base64.DEFAULT);
////
////            }catch (Exception ex){
////                ex.printStackTrace();
////            }
////        }
//    }

//    private void getYearApiCall() {
//        DialogUtils.showProgressDialog(PatentAwardedDetail.this, "");
//        String url = URLS.Get_YEAR;
//
//        StringRequest requestGetYear = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
//                if (!TextUtils.isEmpty(response)) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response);
//
//                        if (jsonArray != null && jsonArray.length() > 0) {
//
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                academicYearOpetion.add(jsonArray.getJSONObject(i).getString(ApiConstants.YEAR_NAME));
//                            }
//
//                            ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, academicYearOpetion);
//                            spAcademicYearPatent.setAdapter(academicYearAdapter); // this will set list of values to spinner
//                            spAcademicYearPatent.setSelection(academicYearOpetion.indexOf(0));
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                DialogUtils.Show_Toast(PatentAwardedDetail.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
//                System.out.println("errorrrrrrrrrr " + error);
//                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
//            }
//        });
//        requestGetYear.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(requestGetYear);
//    }
//
//
//    private boolean isValid(){
//
//        if (spAcademicYearPatent.getSelectedItem().toString().trim().equalsIgnoreCase("Select Year")) {
//            DialogUtils.Show_Toast(PatentAwardedDetail.this, "Selact Academic Year");
//            return false;
//        } else if (TextUtils.isEmpty(edtnamepatentpublished .getText().toString())) {
//            DialogUtils.Show_Toast(PatentAwardedDetail.this, "Enter Name of Patent Published");
//            return false;
//        } else if (TextUtils.isEmpty(edtyearawd.getText().toString())) {
//            DialogUtils.Show_Toast(PatentAwardedDetail.this, "Enter Year Of Award");
//            return false;
//        } else if (TextUtils.isEmpty(edtpatentno.getText().toString())) {
//            DialogUtils.Show_Toast(PatentAwardedDetail.this, "Enter Patent Number");
//            return false;
//        }else if (!tvChooseFile.getText().toString().contains(".pdf")) {
//            DialogUtils.Show_Toast(PatentAwardedDetail.this, "Selact File");
//            return false;
//        }
//
//        return true;
//
//    }

}
