package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;

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
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.FDPDownloadDocumentListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.FileAndFolderInfo;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewFDPAttendedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.FDPDownloadDocumentModel;

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

public class ReqViewFDPAttendedActivity extends AppCompatActivity implements FDPDownloadDocumentListAdapter.IFDPDownloadDocument, View.OnClickListener {

    CustomTextView tvAcademicYearFDPView,tvNameOfWorkShopFDPView,tvEventTypeFDPView,tvOrganizedByFDPView,
            tvFromDateFDPView,tvToDateFDPView,tvPDFramworkCredit,tvEventCategoryFDPView;

    LinearLayout ll_organizer_name_fdp_view,ll_city_name_fdp_view;
    CustomTextView tvNameOfOrganizerFDPView,tvCityNameFDPView;

    RecyclerView rvDownloadDocumentFDPView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<FDPDownloadDocumentModel> fdpDownloadDocumentModelArrayList = new ArrayList<>();
    FDPDownloadDocumentListAdapter fdpDownloadDocumentListAdapter;

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;

    String FDP_EVENT_TYPE = "";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = simpleDateFormat.format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_view_fdpattended);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.ID_FDP_ATTENDED)){
            callViewFDPAttendedApi(getIntent().getStringExtra(IntentConstants.ID_FDP_ATTENDED));
        }
    }



    private void initView(){

        marshMallowPermission = new MarshMallowPermission(ReqViewFDPAttendedActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("FDP Attended Details");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvAcademicYearFDPView = findViewById(R.id.tvAcademicYearFDPView);
        tvNameOfWorkShopFDPView = findViewById(R.id.tvNameOfWorkShopFDPView);
        tvEventTypeFDPView = findViewById(R.id.tvEventTypeFDPView);
        tvOrganizedByFDPView = findViewById(R.id.tvOrganizedByFDPView);
        tvFromDateFDPView = findViewById(R.id.tvFromDateFDPView);
        tvToDateFDPView = findViewById(R.id.tvToDateFDPView);
        tvPDFramworkCredit = findViewById(R.id.tvPDFramworkCredit);
        tvEventCategoryFDPView = findViewById(R.id.tvEventCategoryFDPView);

        ll_organizer_name_fdp_view = findViewById(R.id.ll_organizer_name_fdp_view);
        ll_city_name_fdp_view = findViewById(R.id.ll_city_name_fdp_view);

        tvNameOfOrganizerFDPView = findViewById(R.id.tvNameOfOrganizerFDPView);
        tvCityNameFDPView = findViewById(R.id.tvCityNameFDPView);

        rvDownloadDocumentFDPView = findViewById(R.id.rvDownloadDocumentFDPView);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back){
            onBackPressed();
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

            DialogUtils.showProgressDialog(ReqViewFDPAttendedActivity.this, "");

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

                File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + FileAndFolderInfo.FOLDER_NAME + "/FDP/");
                folder.mkdirs();

                String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "FDP",
                        currentDate, nameoffile);

                File file = new File(folder, actualFileName);
                file.createNewFile();

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
            }catch (IOException ex){
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

            DialogUtils.Show_Toast(ReqViewFDPAttendedActivity.this, "Download Completed Successfully");



            String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "FDP",
                    currentDate, nameoffile);

            File file11 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/" + FileAndFolderInfo.FOLDER_NAME + "/FDP/" + actualFileName);


            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(ReqViewFDPAttendedActivity.this, ReqViewFDPAttendedActivity.this.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                ReqViewFDPAttendedActivity.this.grantUriPermission(ReqViewFDPAttendedActivity.this.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent = null;
                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(uri, "application/pdf");
                }
                intent = Intent.createChooser(target, "Open File");
                try {
                    ReqViewFDPAttendedActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(ReqViewFDPAttendedActivity.this, "No Apps can performs This action");
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
                    ReqViewFDPAttendedActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(ReqViewFDPAttendedActivity.this, "No Apps can performs This action");

                }

            }

        }

    }


    @Override
    public void downloadFDPDocument(String fileName, String fileURL) {
        if (!marshMallowPermission.checkPermissionForExternalStorage()) {
            marshMallowPermission.requestPermissionForExternalStorage();
        } else {

            if (!TextUtils.isEmpty(fileURL) && !TextUtils.isEmpty(fileName)){

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
                    new ReqViewFDPAttendedActivity.DownloadFileFromURL(extention).execute(url);
                } else {
                    DialogUtils.Show_Toast(ReqViewFDPAttendedActivity.this, "No Documents Available");
                }
            }

        }
    }

    @Override
    public void viewFDPDocument(String fileName, String fileURL) {
        Intent intent = new Intent(ReqViewFDPAttendedActivity.this, ViewPDFInWebViewCommanActivity.class);
        intent.putExtra(IntentConstants.VIEW_PDF_COMMAN,fileURL);
        startActivity(intent);
    }

    private void callViewFDPAttendedApi(final String id){
        DialogUtils.showProgressDialog(ReqViewFDPAttendedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_FDP_Attended_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                     ReqViewFDPAttendedPojo reqViewFDPAttendedPojo = gson.fromJson("{\"Data\":" + response + "}",ReqViewFDPAttendedPojo.class);
                    if (reqViewFDPAttendedPojo.getData() != null){
                        ReqViewFDPAttendedPojo.DataBean dataBean = reqViewFDPAttendedPojo.getData().get(0);

                        FDP_EVENT_TYPE = getIntent().getStringExtra(IntentConstants.FDP_EVENT_TYPE);

                        tvAcademicYearFDPView.setText(String.valueOf(dataBean.getYear_name()));
                        tvNameOfWorkShopFDPView.setText(String.valueOf(dataBean.getFdpa_workshop_name()));
                        tvEventTypeFDPView.setText(FDP_EVENT_TYPE);
                        tvOrganizedByFDPView.setText(String.valueOf(dataBean.getOrganize_by_name()));

                        if (dataBean.getFdpa_organized_by() == 2 && !TextUtils.isEmpty(dataBean.getFdpa_organizer_name()) &&
                        !TextUtils.isEmpty(dataBean.getFdpa_organizer_city())){
                            ll_organizer_name_fdp_view.setVisibility(View.VISIBLE);
                            ll_city_name_fdp_view.setVisibility(View.VISIBLE);

                            tvNameOfOrganizerFDPView.setText(dataBean.getFdpa_organizer_name());
                            tvCityNameFDPView.setText(dataBean.getFdpa_organizer_city());

                        }

                        tvFromDateFDPView.setText(String.valueOf(dataBean.getFdpa_from_date()));
                        tvToDateFDPView.setText(String.valueOf(dataBean.getFdpa_to_date()));
                        tvPDFramworkCredit.setText(String.valueOf(dataBean.getFdpa_pd_credit()));
                        tvEventCategoryFDPView.setText(dataBean.getEv_category_name());

                        for (int i = 0 ; i < reqViewFDPAttendedPojo.getData().size() ; i++){

                            FDPDownloadDocumentModel fdpDownloadDocumentModel = new FDPDownloadDocumentModel();
                            //for local below line is for temporary because file name param not available in this view FDP API
                            fdpDownloadDocumentModel.setFileName(String.valueOf(reqViewFDPAttendedPojo.getData().get(i).getFdpf_file()));
                            //for live uncomment below line
                            //fdpDownloadDocumentModel.setFileName(reqViewFDPAttendedPojo.getData().get(i).getFile_Name);
                            fdpDownloadDocumentModel.setFileURL(reqViewFDPAttendedPojo.getData().get(i).getFileName());
                            fdpDownloadDocumentModelArrayList.add(fdpDownloadDocumentModel);
                        }
                        fdpDownloadDocumentListAdapter = new FDPDownloadDocumentListAdapter(ReqViewFDPAttendedActivity.this,fdpDownloadDocumentModelArrayList);
                        rvDownloadDocumentFDPView.setLayoutManager(layoutManager);
                        rvDownloadDocumentFDPView.setAdapter(fdpDownloadDocumentListAdapter);
                    }
                }else {
                    DialogUtils.Show_Toast(ReqViewFDPAttendedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqViewFDPAttendedActivity.this, "Please Try Again Later");
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


}
