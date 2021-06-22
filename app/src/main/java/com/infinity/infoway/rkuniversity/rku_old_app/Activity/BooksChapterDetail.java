package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomButtonView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.FileAndFolderInfo;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.BookChapterPubPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ConAppRejPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BooksChapterDetail extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private CustomBoldTextView txt_act;
    private ImageView iv_info;
    private ImageView iv_profile;
    private RelativeLayout rel;
    private Toolbar toolbar_act;
    private CoordinatorLayout toolbarContainer;
    private ImageView iv_todays_inout;
    private CustomTextView tv_count_total_pending;
    /**
     *
     */
    private CustomBoldTextView tv_emp_code;
    private CustomTextView tv_total_pending_count;
    /**
     *
     */
    private CustomBoldTextView tv_version;
    /**
     *
     */
    private CustomBoldTextView tv_version_code;
    private LinearLayout ll_bottom;
    /**
     * Approve
     */
    private CustomBoldTextView tv_approve;
    /**
     * Reject
     */
    private CustomBoldTextView tv_delete;
    private LinearLayout ll_update_delete;
    /**
     * Enter Name
     */
    private EditText edt_emp_name;
    private LinearLayout ll_emp_name;
    /**
     * Enter Name of Publisher
     */
    private EditText edt_name_publisher;
    private LinearLayout ll_name_publisher;
    /**
     * Enter Title of Research Paper
     */
    private EditText edt_title_of_reach_paper;
    private LinearLayout ll_title_of_reach_paper;
    /**
     * Enter Title of Book/Chapter
     */
    private EditText edt_title_book_chepter;
    private LinearLayout ll_year_awdtitle_book_che;
    /**
     * Enter Date of Publication
     */
    private EditText edt_date_publication;
    private ImageView iv_calendar;
    private LinearLayout ll_date_publication;
    private EditText edt_app_date;
    private LinearLayout ll_approved_dt;
    private EditText edt_app_by;
    private LinearLayout ll_approved_by;
    private ImageView iv_file;
    private LinearLayout ll_doc;
    /**
     * Submit
     */
    private CustomBoldTextView tv_submit;
    /**
     * cancel
     */
    private CustomBoldTextView tv_cancel;
    private LinearLayout ll_submit_cancel;

    Activity activity;
    String ID = "", status = "";
    RequestQueue queue;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    BookChapterPubPojo bookChapterPubPojo;
    public static boolean is_back_book_chepter_approval = false;

    String File_URL = "";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = simpleDateFormat.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_chapter_detail);

        Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_act);
        setSupportActionBar(toolbar);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_back_book_chepter_approval = true;
                onBackPressed();
            }
        });
        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        txt_act.setText("Books/Chapter Detail");
        activity = BooksChapterDetail.this;
        initView();

        Intent intent = getIntent();

        ID = intent.getStringExtra("ID");
        System.out.println("Id of ConferencePubApproveReject from intent ::::::::::::::::::::::::: " + ID);
        status = intent.getStringExtra("status");

        if (ID != null && status != null && !ID.contentEquals("") && !status.contentEquals("")) {
            BookChepterDetailAPi(ID, status);
        }

        if (status != null && !status.contentEquals("")) {
//            if(status.compareToIgnoreCase("pending")!=0)
            if (status.compareToIgnoreCase(IntentConstants.PENDING_STATUS_FOR_APPROVAL) != 0) {
                ll_update_delete.setVisibility(View.GONE);
            } else {
                ll_update_delete.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public void onBackPressed() {
        is_back_book_chepter_approval = true;
        super.onBackPressed();
    }

    private void BookChepterDetailAPi(String id, String status) {
        queue = Volley.newRequestQueue(BooksChapterDetail.this);

        String url = URLS.Get_Book_Chapter_Publication_list + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&emp_id=" + mySharedPreferecesRKUOLD.getEmpID() + "&RowsPerPage=" + URLS.RowsPerPage + "&PageNumber=" + "1" + "&status=" + "1" + "&id=" + id + "";

        url = url.replace(" ", "%20");
        System.out.println("Get_Book_Chapter_Publication_list URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                //System.out.println("response of Get_leave_approve_list !!!!!!!!!!! " + response);
                response = response + "";

                System.out.println("Get_Book_Chapter_Publication_list response size in leave listing :::::::::::::::::::::::::::::::::::::: " + response.length());
                if (response.length() > 10) {
                    response = "{\"Data\":" + response + "}";


                    // System.out.println("sucess response v !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();

                    bookChapterPubPojo = gson.fromJson(response, BookChapterPubPojo.class);
                    System.out.println("Get_Book_Chapter_Publication_list approve leave listing data size :::::::::::::::::::::::::::::::::::::" + bookChapterPubPojo.getData().size());


                    if (bookChapterPubPojo != null) {
                        if (bookChapterPubPojo.getData() != null) {
                            if (bookChapterPubPojo.getData().get(0) != null) {
                                if (bookChapterPubPojo.getData().size() > 0) {


                                    edt_emp_name.setText(bookChapterPubPojo.getData().get(0).getEmployee_name() + "");
                                    edt_name_publisher.setText(bookChapterPubPojo.getData().get(0).getName_of_Publisher());
                                    edt_title_book_chepter.setText(bookChapterPubPojo.getData().get(0).getTitle_of_Book_Chapter() + "");
                                    edt_date_publication.setText(bookChapterPubPojo.getData().get(0).getDate_of_Publication() + "");
                                    edt_app_by.setText(bookChapterPubPojo.getData().get(0).getApprove_reject_by_user() + "");
                                    edt_app_date.setText(bookChapterPubPojo.getData().get(0).getApprove_rejct_date() + "");

                                    if (bookChapterPubPojo.getData().get(0).getDownload_Document() != null &&
                                            !bookChapterPubPojo.getData().get(0).getDownload_Document().isEmpty()) {
                                        ll_doc.setVisibility(View.VISIBLE);
                                        File_URL = bookChapterPubPojo.getData().get(0).getDownload_Document();
                                    } else {
                                        ll_doc.setVisibility(View.GONE);
                                    }

                                } else {

                                    System.out.println("else  calll ################");
                                    DialogUtils.Show_Toast(BooksChapterDetail.this, "No Records Found");
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
                DialogUtils.Show_Toast(BooksChapterDetail.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }

    private void initView() {
        queue = Volley.newRequestQueue(this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        iv_back = (ImageView) findViewById(R.id.iv_back);
        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        rel = (RelativeLayout) findViewById(R.id.rel);
        toolbar_act = (Toolbar) findViewById(R.id.toolbar_act);
        toolbarContainer = (CoordinatorLayout) findViewById(R.id.toolbarContainer);
        iv_todays_inout = (ImageView) findViewById(R.id.iv_todays_inout);
        tv_count_total_pending = (CustomTextView) findViewById(R.id.tv_count_total_pending);
        tv_emp_code = (CustomBoldTextView) findViewById(R.id.tv_emp_code);
        tv_total_pending_count = (CustomTextView) findViewById(R.id.tv_total_pending_count);
        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version_code = (CustomBoldTextView) findViewById(R.id.tv_version_code);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        tv_approve = (CustomBoldTextView) findViewById(R.id.tv_approve);
        tv_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(BooksChapterDetail.this, IntentConstants.APPROVED_STATUS_FOR_APPROVAL, ID);

            }
        });
        tv_delete = (CustomBoldTextView) findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogUtils.showDialog4YNo(BooksChapterDetail.this, "", "Are You Sure To Reject ?", new DialogUtils.DailogCallBackOkButtonClick() {
                    @Override
                    public void onDialogOkButtonClicked() {
                        showDialog(BooksChapterDetail.this, IntentConstants.REJECT_STATUS_FOR_APPROVAL, ID);


                    }
                }, new DialogUtils.DailogCallBackCancelButtonClick() {
                    @Override
                    public void onDialogCancelButtonClicked() {


                    }
                });
            }
        });
        ll_update_delete = (LinearLayout) findViewById(R.id.ll_update_delete);
        edt_emp_name = (EditText) findViewById(R.id.edt_emp_name);
        ll_emp_name = (LinearLayout) findViewById(R.id.ll_emp_name);
        edt_name_publisher = (EditText) findViewById(R.id.edt_name_publisher);
        ll_name_publisher = (LinearLayout) findViewById(R.id.ll_name_publisher);
        edt_title_of_reach_paper = (EditText) findViewById(R.id.edt_title_of_reach_paper);
        ll_title_of_reach_paper = (LinearLayout) findViewById(R.id.ll_title_of_reach_paper);
        edt_title_book_chepter = (EditText) findViewById(R.id.edt_title_book_chepter);
        ll_year_awdtitle_book_che = (LinearLayout) findViewById(R.id.ll_year_awdtitle_book_che);
        edt_date_publication = (EditText) findViewById(R.id.edt_date_publication);
        iv_calendar = (ImageView) findViewById(R.id.iv_calendar);
        ll_date_publication = (LinearLayout) findViewById(R.id.ll_date_publication);
        edt_app_date = (EditText) findViewById(R.id.edt_app_date);
        ll_approved_dt = (LinearLayout) findViewById(R.id.ll_approved_dt);
        edt_app_by = (EditText) findViewById(R.id.edt_app_by);
        ll_approved_by = (LinearLayout) findViewById(R.id.ll_approved_by);
        iv_file = (ImageView) findViewById(R.id.iv_file);
        iv_file.setOnClickListener(this);
        ll_doc = (LinearLayout) findViewById(R.id.ll_doc);
        tv_submit = (CustomBoldTextView) findViewById(R.id.tv_submit);
        tv_cancel = (CustomBoldTextView) findViewById(R.id.tv_cancel);
        ll_submit_cancel = (LinearLayout) findViewById(R.id.ll_submit_cancel);

        PackageInfo pInfo = null;
        assert pInfo != null;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tv_emp_code = (CustomBoldTextView) findViewById(R.id.tv_emp_code);
        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version_code = (CustomBoldTextView) findViewById(R.id.tv_version_code);
        tv_version.setText(pInfo.versionName);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());
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
            DialogUtils.Show_Toast(BooksChapterDetail.this, "Enter Reason");
            return false;
        }

        return true;
    }


    ConAppRejPojo conAppRejPojo;

    private void ApproveConfarancePub(String reason, AlertDialog show, final String ID, String app_rej) {

        DialogUtils.showProgressDialog(BooksChapterDetail.this, "");
        String url;

//        if (app_rej.compareToIgnoreCase("approve")==0)
        if (app_rej.compareToIgnoreCase(IntentConstants.APPROVED_STATUS_FOR_APPROVAL) == 0) {
            url = URLS.Get_Book_Chapter_Publication_Approved_or_Reject + "&id=" + ID + "&remarks=" + reason + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&ip=" + "" + "&approve_reject=" + "1" + "";

        } else {
            url = URLS.Get_Book_Chapter_Publication_Approved_or_Reject + "&id=" + ID + "&remarks=" + reason + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&ip=" + "" + "&approve_reject=" + "2" + "";

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
                                            DialogUtils.Show_Toast(BooksChapterDetail.this, conAppRejPojo.getData().get(0).getMsg());

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_file) {
            if (!TextUtils.isEmpty(File_URL)) {
                if (File_URL.length() > 5) {
                    String extention = File_URL.substring(File_URL.lastIndexOf("."), File_URL.length());
                    //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
                    new BooksChapterDetail.DownloadFileFromURL(extention).execute(File_URL);
                } else {
                    DialogUtils.Show_Toast(BooksChapterDetail.this, "No Documents Available");
                }
            } else {
                Toast.makeText(activity, "File url empty or null", Toast.LENGTH_SHORT).show();
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
//                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Rku/" + "/JournalPub/");
//                dir.mkdirs();
//
//
//                System.out.println("path of file>>>>>" + dir.getAbsolutePath());
//                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();
                //  Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());

                File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + FileAndFolderInfo.FOLDER_NAME + "/BookOrchapterApproval/");
                folder.mkdirs();

                String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "BookOrchapterApproval",
                        currentDate, nameoffile);

                File file = new File(folder, actualFileName);
                file.createNewFile();
                FileOutputStream output = new FileOutputStream(file);

//                OutputStream output = new FileOutputStream("sdcard/Rku/" + "/JournalPub/" + nameoffile);

//                System.out.println("output:::::::::" + output.toString());
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

            DialogUtils.Show_Toast(BooksChapterDetail.this, "Download Completed Successfully");

//            System.out.println("EXTENSION OF FILE onPostExecute::::::::::" + Extenson);
//
//            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Rku/" + "/JournalPub/" + nameoffile);
//            Log.d("pathoffile", String.valueOf(file11));
////                                    Uri uri = FileProvider.getUriForFile(AssignmentActivity.this, "com.infinity.infoway.atmiya.activity.AssignmentActivity.fileprovider", file11);
////

            String actualFileName = FileAndFolderInfo.getCustomeFileName(mySharedPreferecesRKUOLD.getEmpCode(), "BookOrchapterApproval",
                    currentDate, nameoffile);

            File file11 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/" + FileAndFolderInfo.FOLDER_NAME + "/BookOrchapterApproval/" + actualFileName);


            /// for  opening downloaded documentssssssssssss
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(BooksChapterDetail.this, BooksChapterDetail.this.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                BooksChapterDetail.this.grantUriPermission(BooksChapterDetail.this.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent = null;
                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(uri, "application/pdf");
                }
                intent = Intent.createChooser(target, "Open File");
                try {
                    BooksChapterDetail.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(BooksChapterDetail.this, "No Apps can performs This action");
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
                    BooksChapterDetail.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(BooksChapterDetail.this, "No Apps can performs This action");

                }

            }


        }

    }

}
