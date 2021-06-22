package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.Document1_adapter_expandable;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.Document_adapter_expandable;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomButtonView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.ExpandableHeightListView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ConAppRejPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.DocumentPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PDAppPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PdApprovalListingPojo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

//pragna
public class PDAppDocumentApprovalActivity extends AppCompatActivity {
    ImageView iv_back;
    String ID = "", status = "";
    private LinearLayout ll_update_delete;
    Activity activity;
    CustomBoldTextView txt_act;
    RequestQueue queue;
    MarshMallowPermission marshMallowPermission;
    private ImageView iv_info;
    private ImageView iv_profile;
    private RelativeLayout rel;
    private Toolbar toolbar_act;
    private CoordinatorLayout toolbarContainer;
    private ImageView iv_todays_inout;
    private CustomTextView tv_count_total_pending;
    private CustomTextView tv_total_pending_count;
    private LinearLayout ll_bottom;
    ExpandableHeightListView lv_doc, lv_doc_1;
    /**
     * Approve
     */
    private CustomBoldTextView tv_approve;
    /**
     * Reject
     */
    private CustomBoldTextView tv_delete;

    /**
     * Enter Name
     */
    private EditText edt_emp_name;
    private LinearLayout ll_emp_name;
    /**
     * Enter Name Of The Event
     */
    private EditText edt_event_name;
    private LinearLayout ll_event_name;
    /**
     * Enter Organized By
     */
    private EditText edt_Organized_By;
    private LinearLayout ll_Organized_By;
    /**
     * Enter Title of Research Paper
     */
    private EditText edt_title_of_reach_paper;
    private LinearLayout ll_title_of_reach_paper;
    /**
     * Enter City Of The Event
     */
    private EditText edt_city;
    private LinearLayout ll_city;
    /**
     * Enter Level of Journal
     */
    private EditText edt_lvl_jou;
    private ImageView iv_calendar;
    private LinearLayout ll_lvl_jou;
    /**
     * Enter From Date
     */
    private EditText edt_From_Date;
    private LinearLayout ll_From_Date;
    /**
     * Enter  No. Of Day
     */
    private EditText edt_NoDay;
    private LinearLayout ll_NoDay;
    /**
     * Enter  Event Type
     */
    private EditText edt_EventType;
    private LinearLayout ll_EventType;
    /**
     * Enter Event Category
     */
    private EditText edt_event_cat;
    private LinearLayout ll_event_cat;
    /**
     * Enter Event Credit
     */
    private EditText edt_credit;
    private LinearLayout ll_credit, ll_main_doc_file;
    /**
     * Enter Event Credit
     */
    private EditText edt_To_Date;
    private LinearLayout ll_To_Date;
    private EditText edt_app_date;
    private LinearLayout ll_approved_dt;
    private EditText edt_app_by;
    private LinearLayout ll_approved_by;
    private ImageView iv_file;
    private LinearLayout ll_doc;
    private LinearLayout ll_purpose_benifits;
    private EditText edt_role_app;
    private LinearLayout ll_role_app;
    private EditText edt_objective;
    private LinearLayout ll_objective;
    private LinearLayout ll_expense;
    private EditText edt_leave_from;
    private LinearLayout ll_leave_from;
    private EditText edt_reg_fee;
    private LinearLayout ll_reg_fee;
    private EditText edt_Accommodation;
    private LinearLayout ll_Accommodation;
    private EditText edt_LeaveDate;
    private LinearLayout ll_LeaveDate;
    private EditText edt_Total_Expense;
    private LinearLayout ll_Total_Expense;
    private EditText edt_Leave_To_Date;
    private LinearLayout ll_Leave_toDate;
    private EditText edt_transportation;
    private LinearLayout ll_transportation;
    private EditText edt_Leave_Expense;
    private LinearLayout ll_Leave_Expense;
    private EditText edt_od_leaves;
    private LinearLayout ll_od_leaves;
    private LinearLayout ll_post_event_doc;
    private ImageView iv_file_post_doc;
    private LinearLayout ll_post_doc;
    CustomBoldTextView tv_emp_code, tv_version, tv_version_code;

    public static boolean is_back = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pdapp_document_approval);
        setContentView(R.layout.activity_pdapplication_detail_document);//pragna
        initView();
        System.out.println("FINAL PAGE********!!!!!!!!!!!!!!!!!!!!!");
    }

    private void initView() {
        activity = PDAppDocumentApprovalActivity.this;
        ll_update_delete = (LinearLayout) findViewById(R.id.ll_update_delete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_act);
        setSupportActionBar(toolbar);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        queue = Volley.newRequestQueue(this);
        marshMallowPermission = new MarshMallowPermission(activity);
        try {
            if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                marshMallowPermission.requestPermissionForExternalStorage();
            }
        } catch (Exception e) {

        }
        lv_doc = (ExpandableHeightListView) findViewById(R.id.lv_doc);
        lv_doc_1 = (ExpandableHeightListView) findViewById(R.id.lv_doc_1);
        lv_doc.setExpanded(true);
        lv_doc_1.setExpanded(true);
        iv_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                is_back = true;
                onBackPressed();
            }
        });

        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        txt_act.setText("PD Application Document");
        Intent intent = getIntent();

        ID = intent.getStringExtra("ID");
        System.out.println("Id of ConferencePubApproveReject from intent ::::::::::::::::::::::::: " + ID);
        status = intent.getStringExtra("status");
       /* if (status != null && !status.contentEquals("")) {
            if (status.compareToIgnoreCase("pending") != 0) {
                ll_update_delete.setVisibility(View.GONE);
            } else {
                ll_update_delete.setVisibility(View.VISIBLE);
            }
        }*/
        if (status != null && !status.contentEquals("")) {
            if (status.compareToIgnoreCase("pending") == 0 || status.compareToIgnoreCase("Partial Approve") == 0) {
                ll_update_delete.setVisibility(View.VISIBLE);
            } else {
                ll_update_delete.setVisibility(View.GONE);
            }
        }
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
        tv_delete = (CustomBoldTextView) findViewById(R.id.tv_delete);
        ll_update_delete = (LinearLayout) findViewById(R.id.ll_update_delete);
        edt_emp_name = (EditText) findViewById(R.id.edt_emp_name);
        ll_emp_name = (LinearLayout) findViewById(R.id.ll_emp_name);
        edt_event_name = (EditText) findViewById(R.id.edt_event_name);
        ll_event_name = (LinearLayout) findViewById(R.id.ll_event_name);
        edt_Organized_By = (EditText) findViewById(R.id.edt_Organized_By);
        ll_Organized_By = (LinearLayout) findViewById(R.id.ll_Organized_By);
        edt_title_of_reach_paper = (EditText) findViewById(R.id.edt_title_of_reach_paper);
        ll_title_of_reach_paper = (LinearLayout) findViewById(R.id.ll_title_of_reach_paper);
        edt_city = (EditText) findViewById(R.id.edt_city);
        ll_city = (LinearLayout) findViewById(R.id.ll_city);
        edt_lvl_jou = (EditText) findViewById(R.id.edt_lvl_jou);
        iv_calendar = (ImageView) findViewById(R.id.iv_calendar);
        ll_lvl_jou = (LinearLayout) findViewById(R.id.ll_lvl_jou);
        edt_From_Date = (EditText) findViewById(R.id.edt_From_Date);
        ll_From_Date = (LinearLayout) findViewById(R.id.ll_From_Date);
        edt_NoDay = (EditText) findViewById(R.id.edt_NoDay);
        ll_NoDay = (LinearLayout) findViewById(R.id.ll_NoDay);
        edt_EventType = (EditText) findViewById(R.id.edt_EventType);
        ll_EventType = (LinearLayout) findViewById(R.id.ll_EventType);
        edt_event_cat = (EditText) findViewById(R.id.edt_event_cat);
        ll_event_cat = (LinearLayout) findViewById(R.id.ll_event_cat);
        edt_credit = (EditText) findViewById(R.id.edt_credit);
        ll_credit = (LinearLayout) findViewById(R.id.ll_credit);
        ll_main_doc_file = (LinearLayout) findViewById(R.id.ll_main_doc_file);
        edt_To_Date = (EditText) findViewById(R.id.edt_To_Date);
        ll_To_Date = (LinearLayout) findViewById(R.id.ll_To_Date);
        edt_app_date = (EditText) findViewById(R.id.edt_app_date);
        ll_approved_dt = (LinearLayout) findViewById(R.id.ll_approved_dt);
        edt_app_by = (EditText) findViewById(R.id.edt_app_by);
        ll_approved_by = (LinearLayout) findViewById(R.id.ll_approved_by);
        iv_file = (ImageView) findViewById(R.id.iv_file);
        ll_doc = (LinearLayout) findViewById(R.id.ll_doc);
        ll_purpose_benifits = (LinearLayout) findViewById(R.id.ll_purpose_benifits);
        edt_role_app = (EditText) findViewById(R.id.edt_role_app);
        ll_role_app = (LinearLayout) findViewById(R.id.ll_role_app);
        edt_objective = (EditText) findViewById(R.id.edt_objective);
        ll_objective = (LinearLayout) findViewById(R.id.ll_objective);
        ll_expense = (LinearLayout) findViewById(R.id.ll_expense);
        edt_leave_from = (EditText) findViewById(R.id.edt_leave_from);
        ll_leave_from = (LinearLayout) findViewById(R.id.ll_leave_from);
        edt_reg_fee = (EditText) findViewById(R.id.edt_reg_fee);
        ll_reg_fee = (LinearLayout) findViewById(R.id.ll_reg_fee);
        edt_Accommodation = (EditText) findViewById(R.id.edt_Accommodation);
        ll_Accommodation = (LinearLayout) findViewById(R.id.ll_Accommodation);
        edt_LeaveDate = (EditText) findViewById(R.id.edt_LeaveDate);
        ll_LeaveDate = (LinearLayout) findViewById(R.id.ll_LeaveDate);
        edt_Total_Expense = (EditText) findViewById(R.id.edt_Total_Expense);
        ll_Total_Expense = (LinearLayout) findViewById(R.id.ll_Total_Expense);
        edt_Leave_To_Date = (EditText) findViewById(R.id.edt_Leave_To_Date);
        ll_Leave_toDate = (LinearLayout) findViewById(R.id.ll_Leave_toDate);
        edt_transportation = (EditText) findViewById(R.id.edt_transportation);
        ll_transportation = (LinearLayout) findViewById(R.id.ll_transportation);
        edt_Leave_Expense = (EditText) findViewById(R.id.edt_Leave_Expense);
        ll_Leave_Expense = (LinearLayout) findViewById(R.id.ll_Leave_Expense);
        edt_od_leaves = (EditText) findViewById(R.id.edt_od_leaves);
        ll_od_leaves = (LinearLayout) findViewById(R.id.ll_od_leaves);
        ll_post_event_doc = (LinearLayout) findViewById(R.id.ll_post_event_doc);
        iv_file_post_doc = (ImageView) findViewById(R.id.iv_file_post_doc);
        ll_post_doc = (LinearLayout) findViewById(R.id.ll_post_doc);


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


        if (ID != null && status != null && !ID.contentEquals("") && !status.contentEquals(""))
        {

            if (status.compareToIgnoreCase("Pending") == 0 || status.compareToIgnoreCase("Partial Approve") == 0)
            {
                status = "1";
            }
            else
                {
                status = "2";
            }
            try
            {
                PDAppDetailAPI(ID, status);
                docAPI(ID + "");
                docAPI1(ID + "");
                ll_post_event_doc.setVisibility(View.GONE);
            } catch (Exception e) {
                System.out.println("in layout@@@@@@@@@@@@@@@@******");
            }
            //  PDAppDocument(ID);


            tv_approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showDialog(PDAppDocumentApprovalActivity.this, "approve", ID);
                }
            });

            tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DialogUtils.showDialog4YNo(PDAppDocumentApprovalActivity.this, "", "Are You Sure To Reject ?", new DialogUtils.DailogCallBackOkButtonClick() {
                        @Override
                        public void onDialogOkButtonClicked() {
                            showDialog(PDAppDocumentApprovalActivity.this, "reject", ID + "");


                        }
                    }, new DialogUtils.DailogCallBackCancelButtonClick() {
                        @Override
                        public void onDialogCancelButtonClicked() {

                        }
                    });
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        is_back = true;
    }

    PDAppPojo pdAppPojo;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;

    private void PDAppDetailAPI(String id, String status) {
        if (status.compareToIgnoreCase("Pending") == 0) {
            status = "1";
        } else {
            status = "2";
        }
        queue = Volley.newRequestQueue(PDAppDocumentApprovalActivity.this);

        String url = URLS.Get_PD_Application_list + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&RowsPerPage=" + URLS.RowsPerPage + "&PageNumber=" + "1" + "&status=" + status + "&id=" + id + "";

        url = url.replace(" ", "%20");
        System.out.println("Get_PD_Application_list URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                //System.out.println("response of Get_leave_approve_list !!!!!!!!!!! " + response);
                response = response + "";

                System.out.println("Get_PD_Application_list response size in leave listing :::::::::::::::::::::::::::::::::::::: " + response.length());
                if (response.length() > 10) {
                    response = "{\"Data\":" + response + "}";


                    // System.out.println("sucess response v !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();

                    pdAppPojo = gson.fromJson(response, PDAppPojo.class);
                    System.out.println("Get_PD_Application_list data size :::::::::::::::::::::::::::::::::::::" + pdAppPojo.getData().size());


                    if (pdAppPojo != null) {
                        if (pdAppPojo.getData() != null) {
                            if (pdAppPojo.getData().get(0) != null) {
                                if (pdAppPojo.getData().size() > 0) {
                                    edt_emp_name.setText(pdAppPojo.getData().get(0).getEmployee_Name() + "");
                                    edt_event_name.setText(pdAppPojo.getData().get(0).getPd_event_name() + "");
                                    edt_Organized_By.setText(pdAppPojo.getData().get(0).getOrganized_By() + "");
                                    edt_city.setText(pdAppPojo.getData().get(0).getCity_Of_The_Event() + "");
                                    edt_From_Date.setText(pdAppPojo.getData().get(0).getFrom_Date() + "");
                                    edt_To_Date.setText(pdAppPojo.getData().get(0).getTo_Date() + "");
                                    edt_NoDay.setText(pdAppPojo.getData().get(0).getNo_Of_Day() + "");
                                    edt_EventType.setText(pdAppPojo.getData().get(0).getEvent_Type() + "");
                                    edt_event_cat.setText(pdAppPojo.getData().get(0).getEvent_Category() + "");
                                    edt_credit.setText(pdAppPojo.getData().get(0).getEvent_Credit() + "");
                                    //    edt_app_date.setText(pdAppPojo.getData().get(0).dat+"");
                                    edt_app_by.setText(pdAppPojo.getData().get(0).getApprove_by_user() + "");
                                    edt_role_app.setText(pdAppPojo.getData().get(0).getRole_Of_Applicant_in_the_event() + "");
                                    edt_objective.setText(pdAppPojo.getData().get(0).getObjective() + "");
                                    edt_leave_from.setText(pdAppPojo.getData().get(0).getLeave_From_Date() + "");
                                    edt_Accommodation.setText(pdAppPojo.getData().get(0).getAccommodation() + "");
                                    edt_Total_Expense.setText(pdAppPojo.getData().get(0).getTotal_Expense() + "");
                                    edt_LeaveDate.setText(pdAppPojo.getData().get(0).getLeave_To_Date() + "");
                                    edt_transportation.setText(pdAppPojo.getData().get(0).getTransportation() + "");
                                    edt_od_leaves.setText(pdAppPojo.getData().get(0).getNo_of_OD_Leaves() + "");
                                    edt_Total_Expense.setText(pdAppPojo.getData().get(0).getTotal_Expense() + "");
                                    edt_Leave_Expense.setText(pdAppPojo.getData().get(0).getLeave_Expense() + "");
                                    edt_reg_fee.setText(pdAppPojo.getData().get(0).getRegistration_Fees() + "");
//                                    edt_Leave_Expense.setText(pdAppPojo.getData().get(0).getLeave_Expense() + "");





                                  /*  iv_file.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                                                marshMallowPermission.requestPermissionForExternalStorage();
                                            } else {


                                                String url_con = pdAppPojo.getData().get(0).getDownload_Document() + "";
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
                                                    DialogUtils.Show_Toast(PDApplicationDetail.this, "No Documents Available");
                                                }


                                            }

                                        }
                                    });*/


                                } else {

                                    System.out.println("else  calll *******################");
                                    DialogUtils.Show_Toast(PDAppDocumentApprovalActivity.this, "No Records Found");
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
                DialogUtils.Show_Toast(PDAppDocumentApprovalActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr******* " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
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
            public void onClick(View v)
            {
                if (isvalidate(edt_reason))
                {
                    show.dismiss();


                    if (approve_reject.compareToIgnoreCase("approve") == 0) {
//                        ApproveConfarancePub(edt_reason.getText().toString().trim(),show,pos,ID,"approve");
                        ApproveConfarancePub(edt_reason.getText().toString().trim(), show, ID, "approve");
                    } else {
//                        ApproveConfarancePub(edt_reason.getText().toString().trim(),show,pos,ID,"reject");
                        ApproveConfarancePub(edt_reason.getText().toString().trim(), show, ID, "reject");
                        // RejectLeave(edt_reason.getText().toString().trim(), show, pos,ID,"reject");
                    }

                }
            }
        });
    }

    ConAppRejPojo conAppRejPojo;

    private void ApproveConfarancePub(String reason, AlertDialog show, final String ID, String app_rej) {

        DialogUtils.showProgressDialog(PDAppDocumentApprovalActivity.this, "");
        String url;

        if (app_rej.compareToIgnoreCase("approve") == 0) {
//            url = URLS.Get_PG_Scholars_Guided_Approved_or_Reject +"&id="+ID+"&remarks="+reason+ "&user_id="+mySharedPrefereces.getUserID()+"&ip="+""+"&approve_reject=" + "1" + "";
            url = URLS.Get_PD_document_approve_or_reject + "&id=" + ID + "&remarks=" + reason + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&ip=" + "" + "&pd_is_approve=" + "1" + "";

        } else {
//            url = URLS.Get_PG_Scholars_Guided_Approved_or_Reject +"&id="+ID+"&remarks="+reason+ "&user_id="+mySharedPrefereces.getUserID()+"&ip="+""+"&approve_reject=" + "2" + "";
            url = URLS.Get_PD_document_approve_or_reject + "&id=" + ID + "&remarks=" + reason + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&ip=" + "" + "&pd_is_approve=" + "2" + "";

        }

        url = url.replace(" ", "%20");
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        DialogUtils.hideProgressDialog();
                        response = response + "";
                        response = "{\"Data\":" + response + "}";
                        System.out.println("THIS Get_PD_document_approve_or_reject  RESPONSE      !!!!!!!!!!!!!!!!!!!" + response + "");


                        System.out.println("response length :::::::::::::: " + response.length());
                        System.out.println("response data size  :::::::::::::: " + response);

                        if (response.length() > 10) {
                            Gson gson = new Gson();

                            conAppRejPojo = gson.fromJson(response, ConAppRejPojo.class);
                            if (conAppRejPojo != null) {
                                if (conAppRejPojo.getData() != null) {
                                    if (conAppRejPojo.getData().get(0) != null && conAppRejPojo.getData().size() > 0) {

                                        if (!conAppRejPojo.getData().get(0).getMsg().contentEquals("")) {
                                            DialogUtils.Show_Toast1(PDAppDocumentApprovalActivity.this, conAppRejPojo.getData().get(0).getMsg());

                                          /*  PGScholars.listall = new ArrayList<>();
                                            PGScholars.listall.clear();
                                            PGScholars.pgScolarsGuidedPojo = new PgScolarsGuidedPojo();
                                            PGScholars.Pgscholarsapprovelist(1,false);*/

                                            PDAppDocumentApprovalListing.listall = new ArrayList<>();
                                            PDAppDocumentApprovalListing.listall.clear();
                                            PDAppDocumentApprovalListing.pdApprovalListingPojo = new PdApprovalListingPojo();
                                            PDAppDocumentApprovalListing.PDAppDocumentApprovalListingAPI(1, false);
                                            is_back = true;
                                            // finish();

                                            onBackPressed();


                                            System.out.println("this is final TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

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
                System.out.println("ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);


    }


    private boolean isvalidate(EditText edt_reason) {
        if (edt_reason.getText().toString().trim().isEmpty() || edt_reason.getText().toString().contentEquals("") || edt_reason.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(PDAppDocumentApprovalActivity.this, "Enter Reason");
            return false;
        }

        return true;
    }

    DocumentPojo documentPojo = new DocumentPojo();

    void docAPI(String id) {

        DialogUtils.showProgressDialog(PDAppDocumentApprovalActivity.this, "");
        String url;
        url = URLS.Get_PD_Application_document_list_PRAGNA + id + "";
        url = url.replace(" ", "%20");
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        DialogUtils.hideProgressDialog();
                        response = response + "";


                        if (response.length() > 10) {
                            response = "{\"data\":" + response + "}";
                            System.out.println("THIS Get_PD_Application_document_list_PRAGNA  RESPONSE      !!!!!!!!!!!!!!!!!!!" + response + "");

                            Gson gson = new Gson();

                            documentPojo = gson.fromJson(response, DocumentPojo.class);
                            if (documentPojo != null) {
                                if (documentPojo.getData() != null) {
                                    if (documentPojo.getData().get(0) != null && documentPojo.getData().size() > 0) {
                                        ll_post_event_doc.setVisibility(View.VISIBLE);
                                        Document_adapter_expandable document_adapter_expandable = new Document_adapter_expandable(PDAppDocumentApprovalActivity.this, documentPojo, new Document_adapter_expandable.On_Download() {
                                            @Override
                                            public void on_download_click(String download_link) {

                                                String url = download_link;

                                                if (url.contains(" "))
                                                {
                                                    url = url.replace(" ","%20");
                                                }

                                                if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                                                    marshMallowPermission.requestPermissionForExternalStorage();
                                                } else {
                                                    if (url != null && url.length() > 5) {
                                                        String extention = url.substring(url.lastIndexOf("."), url.length());
                                                        //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
                                                        new DownloadFileFromURL(extention).execute(url);
                                                    } else {
                                                        DialogUtils.Show_Toast(PDAppDocumentApprovalActivity.this, "No Documents Available");
                                                    }

                                                }
                                            }
                                        });
                                        lv_doc.setAdapter(document_adapter_expandable);


                                    }
                                }
                            }
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.hideProgressDialog();
                System.out.println("ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);

    }

    DocumentPojo documentPojo1 = new DocumentPojo();

    void docAPI1(String id) {

        //DialogUtils.showProgressDialog(PDAppDocumentApprovalActivity.this, "");
        String url;
        url = URLS.Get_PD_Application_brochures_document_list + id + "";
        url = url.replace(" ", "%20");
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        DialogUtils.hideProgressDialog();
                        response = response + "";

                        if (response.length() > 10) {
                            response = "{\"data\":" + response + "}";
                            System.out.println("THIS Get_PD_Application_brochures_document_list*******  RESPONSE      !!!!!!!!!!!!!!!!!!!" + response + "");

                            Gson gson = new Gson();

                            documentPojo1 = gson.fromJson(response, DocumentPojo.class);
                            if (documentPojo1 != null) {
                                if (documentPojo1.getData() != null) {
                                    if (documentPojo1.getData().get(0) != null && documentPojo1.getData().size() > 0) {
                                        ll_doc.setVisibility(View.VISIBLE);
                                        Document1_adapter_expandable document_adapter_expandable = new Document1_adapter_expandable(PDAppDocumentApprovalActivity.this, documentPojo1, new Document1_adapter_expandable.On_Download() {
                                            @Override
                                            public void on_download_click(String download_link) {

                                                String url = download_link;
                                                if (url.contains(" "))
                                                {
                                                    url = url.replace(" ","%20");
                                                }

                                                if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                                                    marshMallowPermission.requestPermissionForExternalStorage();
                                                } else {
                                                    if (url != null && url.length() > 5) {
                                                        String extention = url.substring(url.lastIndexOf("."), url.length());
                                                        //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
                                                        new DownloadFileFromURL(extention).execute(url);
                                                    } else {
                                                        DialogUtils.Show_Toast(PDAppDocumentApprovalActivity.this, "No Documents Available");
                                                    }

                                                }
                                            }
                                        });
                                        lv_doc_1.setAdapter(document_adapter_expandable);


                                    }
                                }
                            }

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.hideProgressDialog();
                System.out.println("ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);

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
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Rku/" + "/PD_DOC/");
                dir.mkdirs();


                System.out.println("path of file>>>>>" + dir.getAbsolutePath());
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();
                //  Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());


                OutputStream output = new FileOutputStream("sdcard/Rku/" + "/PD_DOC/" + nameoffile);

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

            DialogUtils.Show_Toast(PDAppDocumentApprovalActivity.this, "Download Completed Successfully");

            System.out.println("EXTENSION OF FILE onPostExecute::::::::::" + Extenson);

            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Rku/" + "/PD_DOC/" + nameoffile);
            Log.d("pathoffile", String.valueOf(file11));
//                                    Uri uri = FileProvider.getUriForFile(AssignmentActivity.this, "com.infinity.infoway.atmiya.activity.AssignmentActivity.fileprovider", file11);
//

            /// for  opening downloaded documentssssssssssss
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(PDAppDocumentApprovalActivity.this, PDAppDocumentApprovalActivity.this.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                PDAppDocumentApprovalActivity.this.grantUriPermission(PDAppDocumentApprovalActivity.this.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent = null;
                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(uri, "application/pdf");
                }
                intent = Intent.createChooser(target, "Open File");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(PDAppDocumentApprovalActivity.this, "No Apps can performs This action");
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
                    PDAppDocumentApprovalActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(PDAppDocumentApprovalActivity.this, "No Apps can performs This action");

                }

            }


        }

    }

}