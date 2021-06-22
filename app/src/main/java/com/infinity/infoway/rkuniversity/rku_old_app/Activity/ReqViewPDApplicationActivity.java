package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.DownloadDocumentListAdapterPDView;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.FileAndFolderInfo;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewPDApplicationPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.PDApplicationModel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class ReqViewPDApplicationActivity extends AppCompatActivity implements View.OnClickListener, DownloadDocumentListAdapterPDView.PDDownloadDocument {


    RecyclerView rvDownloadDocumentViewPD;

    CustomTextView tvEmployeeNameViewPD, tvNameOftheEventViewPD, tvEventTypeViewPD, tvOrganizedByViewPD,
            tvEventCategoryViewPD, tvCityOfTheEventViewPD, tvEventCreditViewPD, tvFromDateViewPD,
            tvToDateViewPD, tvRollOfApplicantViewPD, tvObjectiveViewPD, tvLeaveFromDateViewPD,
            tvLeaveToDateViewPD, tvRegistrationFeesViewPD, tvTransportationViewPD,
            tvAccommodationViewPD, tvLeaveExpenseViewPD, tvODViewPD, tvNoOfDayViewPD,
            tvTotalExpenseViewPD;
//    CustomTextView tvFinalEventCreditViewPD;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<PDApplicationModel> pdApplicationModelArrayList = new ArrayList<>();
    DownloadDocumentListAdapterPDView downloadDocumentListAdapterPDView;

    String event_type_name = "";
    String event_category_name = "";
    String applicant_roll_name = "";

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = simpleDateFormat.format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_view_pdapplication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.ID_PD_APPLICATION)) {

            event_type_name = getIntent().getStringExtra(IntentConstants.PD_EVENT_TYPE_NAME_VIEW_PD);
            event_category_name = getIntent().getStringExtra(IntentConstants.PD_EVENT_CATEGORY_NAME_VIEW_PD);
            applicant_roll_name = getIntent().getStringExtra(IntentConstants.PD_APPLICANT_ROLLL_NAME_VIEW_PD);

            callViewPDApplicationAPI(getIntent().getStringExtra(IntentConstants.ID_PD_APPLICATION));

        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        }
    }

    private void initView() {
        marshMallowPermission = new MarshMallowPermission(ReqViewPDApplicationActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("PD Application Details");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvEmployeeNameViewPD = findViewById(R.id.tvEmployeeNameViewPD);
        tvEmployeeNameViewPD.setText(mySharedPreferecesRKUOLD.getFullName());

        tvNameOftheEventViewPD = findViewById(R.id.tvNameOftheEventViewPD);
        tvEventTypeViewPD = findViewById(R.id.tvEventTypeViewPD);
        tvOrganizedByViewPD = findViewById(R.id.tvOrganizedByViewPD);
        tvEventCategoryViewPD = findViewById(R.id.tvEventCategoryViewPD);
        tvCityOfTheEventViewPD = findViewById(R.id.tvCityOfTheEventViewPD);
        tvEventCreditViewPD = findViewById(R.id.tvEventCreditViewPD);
        tvFromDateViewPD = findViewById(R.id.tvFromDateViewPD);
        tvToDateViewPD = findViewById(R.id.tvToDateViewPD);
        tvRollOfApplicantViewPD = findViewById(R.id.tvRollOfApplicantViewPD);
        tvObjectiveViewPD = findViewById(R.id.tvObjectiveViewPD);
        tvLeaveFromDateViewPD = findViewById(R.id.tvLeaveFromDateViewPD);
        tvLeaveToDateViewPD = findViewById(R.id.tvLeaveToDateViewPD);
        tvRegistrationFeesViewPD = findViewById(R.id.tvRegistrationFeesViewPD);
        tvTransportationViewPD = findViewById(R.id.tvTransportationViewPD);
        tvAccommodationViewPD = findViewById(R.id.tvAccommodationViewPD);
        tvLeaveExpenseViewPD = findViewById(R.id.tvLeaveExpenseViewPD);
        tvODViewPD = findViewById(R.id.tvODViewPD);
        tvNoOfDayViewPD = findViewById(R.id.tvNoOfDayViewPD);
        tvTotalExpenseViewPD = findViewById(R.id.tvTotalExpenseViewPD);
//        tvFinalEventCreditViewPD = findViewById(R.id.tvFinalEventCreditViewPD);

        rvDownloadDocumentViewPD = findViewById(R.id.rvDownloadDocumentViewPD);
        layoutManager = new LinearLayoutManager(this);

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


    private void callViewPDApplicationAPI(final String id) {
        DialogUtils.showProgressDialog(ReqViewPDApplicationActivity.this, "");

        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_PD_Application, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    ViewPDApplicationPojo reqViewPDApplicationPojo = gson.fromJson("{\"Data\":" + response + "}", ViewPDApplicationPojo.class);

                    if (reqViewPDApplicationPojo != null && reqViewPDApplicationPojo.getData().size() > 0) {

                        ViewPDApplicationPojo.DataBean dataBean = reqViewPDApplicationPojo.getData().get(0);

                        tvNameOftheEventViewPD.setText(String.valueOf(dataBean.getPd_event_name()));
                        tvEventTypeViewPD.setText(event_type_name);
                        tvOrganizedByViewPD.setText(dataBean.getPd_organized_by());
                        tvEventCategoryViewPD.setText(event_category_name);
                        tvCityOfTheEventViewPD.setText(dataBean.getPd_event_city());
                        tvEventCreditViewPD.setText(String.valueOf(dataBean.getPd_event_credit()));
//                        tvFinalEventCreditViewPD.setText(String.valueOf(dataBean.get));
                        tvFromDateViewPD.setText(String.valueOf(dataBean.getEvent_from_date()));
                        tvToDateViewPD.setText(String.valueOf(dataBean.getEvent_to_date()));
                        tvNoOfDayViewPD.setText(String.valueOf(dataBean.getPd_no_of_day()));


                        for (int i = 0; i < reqViewPDApplicationPojo.getData().size(); i++) {

                            PDApplicationModel pdApplicationModel = new PDApplicationModel();
                            pdApplicationModel.setFileName(reqViewPDApplicationPojo.getData().get(i).getFile_Title());
                            pdApplicationModel.setFileURL(reqViewPDApplicationPojo.getData().get(i).getFileName());
                            pdApplicationModelArrayList.add(pdApplicationModel);
                        }
                        downloadDocumentListAdapterPDView = new DownloadDocumentListAdapterPDView(ReqViewPDApplicationActivity.this, pdApplicationModelArrayList);
                        rvDownloadDocumentViewPD.setLayoutManager(layoutManager);
                        rvDownloadDocumentViewPD.setAdapter(downloadDocumentListAdapterPDView);


                        tvRollOfApplicantViewPD.setText(applicant_roll_name);
                        tvObjectiveViewPD.setText(dataBean.getPd_event_description());

                        tvLeaveFromDateViewPD.setText(String.valueOf(dataBean.getLeave_from_date()));
                        tvLeaveToDateViewPD.setText(String.valueOf(dataBean.getLeave_to_date()));
                        tvRegistrationFeesViewPD.setText(String.valueOf(dataBean.getPd_registration_fees()));
                        tvTransportationViewPD.setText(String.valueOf(dataBean.getPd_transportation()));
                        tvAccommodationViewPD.setText(String.valueOf(dataBean.getPd_accommodation()));
                        tvLeaveExpenseViewPD.setText(String.valueOf(dataBean.getPd_leave_expense()));
                        tvTotalExpenseViewPD.setText(String.valueOf(dataBean.getPd_total_expense()));
                        tvODViewPD.setText(String.valueOf(dataBean.getPd_no_od_leaves()));

                    } else {
                        DialogUtils.hideProgressDialog();
                        DialogUtils.Show_Toast(ReqViewPDApplicationActivity.this, response);
                    }


                } else {
                    DialogUtils.hideProgressDialog();
                    DialogUtils.Show_Toast(ReqViewPDApplicationActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqViewPDApplicationActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
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
    public void downloadPDDocument(String fileName, String fileURL) {
        if (!marshMallowPermission.checkPermissionForExternalStorage()) {
            marshMallowPermission.requestPermissionForExternalStorage();
        } else {

            if (!TextUtils.isEmpty(fileURL) && !TextUtils.isEmpty(fileName)) {

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
                    new ReqViewPDApplicationActivity.DownloadFileFromURL(extention).execute(url);
                } else {
                    DialogUtils.Show_Toast(ReqViewPDApplicationActivity.this, "No Documents Available");
                }
            }

        }
    }

    @Override
    public void viewPDDocument(String fileName, String fileURL) {
        Intent intent = new Intent(ReqViewPDApplicationActivity.this, ViewPDFInWebViewCommanActivity.class);
        intent.putExtra(IntentConstants.VIEW_PDF_COMMAN,fileURL);
        startActivity(intent);
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

            DialogUtils.showProgressDialog(ReqViewPDApplicationActivity.this, "");

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


                File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + FileAndFolderInfo.FOLDER_NAME + "/PDApplication/");
                folder.mkdirs();

                String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "PDApplication",
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

            DialogUtils.Show_Toast(ReqViewPDApplicationActivity.this, "Download Completed Successfully");

            System.out.println("EXTENSION OF FILE onPostExecute::::::::::" + Extenson);


            String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "PDApplication",
                    currentDate, nameoffile);

            File file11 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),  "/" + FileAndFolderInfo.FOLDER_NAME + "/PDApplication/" + actualFileName);


////            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), URLS.folder_name + "/" + nameoffile);
//            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Rku/" + "/PatentAwarded/" + nameoffile);
//            Log.d("pathoffile", String.valueOf(file11));
////                                    Uri uri = FileProvider.getUriForFile(AssignmentActivity.this, "com.infinity.infoway.atmiya.activity.AssignmentActivity.fileprovider", file11);
////

            /// for  opening downloaded documentssssssssssss
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(ReqViewPDApplicationActivity.this, ReqViewPDApplicationActivity.this.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                ReqViewPDApplicationActivity.this.grantUriPermission(ReqViewPDApplicationActivity.this.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent = null;
                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(uri, "application/pdf");
                }
                intent = Intent.createChooser(target, "Open File");
                try {
                    ReqViewPDApplicationActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(ReqViewPDApplicationActivity.this, "No Apps can performs This action");
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
                    ReqViewPDApplicationActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(ReqViewPDApplicationActivity.this, "No Apps can performs This action");

                }

            }

        }

    }

}
