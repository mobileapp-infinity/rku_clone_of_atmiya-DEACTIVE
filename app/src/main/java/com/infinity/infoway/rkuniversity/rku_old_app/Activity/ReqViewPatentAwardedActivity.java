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
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.FileAndFolderInfo;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewPatentAwardedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class ReqViewPatentAwardedActivity extends AppCompatActivity implements View.OnClickListener {


    CustomTextView tvAcademicYearPatentAwardedView,tvNameofPatentPatentAwarded,tvPatentNumberPatentAwardedView,tvYearofAwardPatentAwardedView;

    LinearLayout ll_doc_patent_awarded_view;

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack,iv_file_patent_awarded_view,iv_view_patent_awarded_pdf;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;
    ReqViewPatentAwardedPojo.DataBean dataBean;
    CustomTextView tvFileName;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = simpleDateFormat.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_view_patent_awarded);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.ID_VIEW_PATENT_AWARDED)){
            callViewInPublicationApi(getIntent().getStringExtra(IntentConstants.ID_VIEW_PATENT_AWARDED));
        }
    }



    private void initView(){
        marshMallowPermission = new MarshMallowPermission(ReqViewPatentAwardedActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Patent Awarded Details");

        iv_file_patent_awarded_view = findViewById(R.id.iv_file_patent_awarded_view);
        iv_file_patent_awarded_view.setOnClickListener(this);
        iv_view_patent_awarded_pdf = findViewById(R.id.iv_view_patent_awarded_pdf);
        iv_view_patent_awarded_pdf.setOnClickListener(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvFileName = findViewById(R.id.tvFileNamePatentAwardedView);

        ll_doc_patent_awarded_view = findViewById(R.id.ll_doc_patent_awarded_view);

        tvAcademicYearPatentAwardedView = findViewById(R.id.tvAcademicYearPatentAwardedView);
        tvNameofPatentPatentAwarded = findViewById(R.id.tvNameofPatentPatentAwarded);
        tvPatentNumberPatentAwardedView = findViewById(R.id.tvPatentNumberPatentAwardedView);
        tvYearofAwardPatentAwardedView = findViewById(R.id.tvYearofAwardPatentAwardedView);

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
        }else if (view.getId() == R.id.ll_doc_patent_awarded_view){

        }else if (view.getId() == R.id.iv_file_patent_awarded_view){
            if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                marshMallowPermission.requestPermissionForExternalStorage();
            } else {

                if (dataBean != null && !TextUtils.isEmpty(dataBean.getPat_file_name())){

                    String url_con = dataBean.getFileName() + "";
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
                        new ReqViewPatentAwardedActivity.DownloadFileFromURL(extention).execute(url);
                    } else {
                        DialogUtils.Show_Toast(ReqViewPatentAwardedActivity.this, "No Documents Available");
                    }
                }
            }
        }else if (view.getId() == R.id.iv_view_patent_awarded_pdf){
            Intent intent = new Intent(ReqViewPatentAwardedActivity.this, ViewPDFInWebViewCommanActivity.class);
            intent.putExtra(IntentConstants.VIEW_PDF_COMMAN,dataBean.getFileName());
            startActivity(intent);
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

            DialogUtils.showProgressDialog(ReqViewPatentAwardedActivity.this, "");

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
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();
                //  Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());

                File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + FileAndFolderInfo.FOLDER_NAME + "/PatentAwarded/");
                folder.mkdirs();

                String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "PatentAwarded",
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

            DialogUtils.Show_Toast(ReqViewPatentAwardedActivity.this, "Download Completed Successfully");

            System.out.println("EXTENSION OF FILE onPostExecute::::::::::" + Extenson);


            String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "PatentAwarded",
                    currentDate, nameoffile);

            File file11 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/" + FileAndFolderInfo.FOLDER_NAME + "/PatentAwarded/" + actualFileName);

            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(ReqViewPatentAwardedActivity.this, ReqViewPatentAwardedActivity.this.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                ReqViewPatentAwardedActivity.this.grantUriPermission(ReqViewPatentAwardedActivity.this.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent = null;
                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(uri, "application/pdf");
                }
                intent = Intent.createChooser(target, "Open File");
                try {
                    ReqViewPatentAwardedActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(ReqViewPatentAwardedActivity.this, "No Apps can performs This action");
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
                    ReqViewPatentAwardedActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(ReqViewPatentAwardedActivity.this, "No Apps can performs This action");

                }

            }

        }

    }

    private void callViewInPublicationApi(final String id){
        DialogUtils.showProgressDialog(ReqViewPatentAwardedActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_Patent_Awarded_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                    ReqViewPatentAwardedPojo reqViewPatentAwardedPojo = gson.fromJson("{\"Data\":" + response + "}",ReqViewPatentAwardedPojo.class);
                    if (reqViewPatentAwardedPojo.getData() != null){
                        dataBean = reqViewPatentAwardedPojo.getData().get(0);
                        tvAcademicYearPatentAwardedView.setText(dataBean.getYear_name());
                        tvNameofPatentPatentAwarded.setText(dataBean.getPat_patent_name());
                        tvPatentNumberPatentAwardedView.setText(dataBean.getPat_patent_number());
                        tvYearofAwardPatentAwardedView.setText(dataBean.getPat_patent_year());
                        tvFileName.setText(String.valueOf(dataBean.getPat_file_name()));
                    }
                }else {
                    DialogUtils.Show_Toast(ReqViewPatentAwardedActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqViewPatentAwardedActivity.this, "Please Try Again Later");
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
