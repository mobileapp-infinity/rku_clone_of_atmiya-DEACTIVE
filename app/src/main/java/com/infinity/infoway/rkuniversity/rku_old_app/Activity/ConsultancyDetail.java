package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomButtonView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ConAppRejPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ConsultuncyPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

public class ConsultancyDetail extends AppCompatActivity {

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
     * Enter Academic Year
     */
    private EditText edt_year;
    private LinearLayout ll_leave_balance;
    /**
     * Enter Name of Consultancy Project
     */
    private EditText edt_name_project;
    private LinearLayout ll_name_project;
    /**
     * Enter Title of Research Paper
     */
    private EditText edt_title_of_reach_paper;
    private LinearLayout ll_title_of_reach_paper;
    /**
     * Enter Revenue Generated
     */
    private EditText edt_revenue;
    private LinearLayout ll_revenue;
    /**
     * Enter Level of Journal
     */
    private EditText edt_lvl_jou;
    private ImageView iv_calendar;
    private LinearLayout ll_lvl_jou;
    /**
     * Enter Consulting/Sponsoring Agency With Contact Details
     */
    private EditText edt_consuting;
    private LinearLayout ll_consulting;
    /**
     * Enter Status
     */
    private EditText edt_Status;
    private LinearLayout ll_Status;
    private EditText edt_app_date;
    private LinearLayout ll_approved_dt;
    private EditText edt_app_by;
    private LinearLayout ll_approved_by;
    /**
     * Submit
     */
    private CustomBoldTextView tv_submit;
    /**
     * cancel
     */
    private CustomBoldTextView tv_cancel;
    private LinearLayout ll_submit_cancel;

    RequestQueue queue;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ConsultuncyPojo consultuncyPojo;
    Activity activity;

    public static boolean is_back_consultuncy = false;
    String ID = "", status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultancy_detail);

        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_act);
        setSupportActionBar(toolbar);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                is_back_consultuncy = true;
                onBackPressed();
            }
        });
        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        txt_act.setText("Consultancy Detail");
        activity = ConsultancyDetail.this;
        initView();
        Intent intent = getIntent();

        ID = intent.getStringExtra("ID");
        System.out.println("Id of Consultancy from intent ::::::::::::::::::::::::: " + ID);
        status = intent.getStringExtra("status");

        if (ID != null && status != null && !ID.contentEquals("") && !status.contentEquals("")) {
            ConsultuncyDetail(ID, status);
        }

        if (status != null && !status.contentEquals("")) {
//            if (status.compareToIgnoreCase("pending") != 0) {
            if (status.compareToIgnoreCase(IntentConstants.PENDING_STATUS_FOR_APPROVAL) != 0) {
                ll_update_delete.setVisibility(View.GONE);
            } else {
                ll_update_delete.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onBackPressed() {

        is_back_consultuncy = true;
        super.onBackPressed();
    }

    private void ConsultuncyDetail(String id, String status) {


//        if (status.compareToIgnoreCase("Pending") == 0) {
        if (status.compareToIgnoreCase(IntentConstants.PENDING_STATUS_FOR_APPROVAL) == 0) {
            status = "1";
        } else {
            status = "2";
        }
        queue = Volley.newRequestQueue(ConsultancyDetail.this);

        String url = URLS.Get_Consultancy_list + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&emp_id=" + mySharedPreferecesRKUOLD.getEmpID() + "&RowsPerPage=" + URLS.RowsPerPage + "&PageNumber=" + "1" + "&status=" + status + "&id=" + id + "";

        url = url.replace(" ", "%20");
        System.out.println("Get_Consultancy_list URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                //System.out.println("response of Get_leave_approve_list !!!!!!!!!!! " + response);
                response = response + "";

                System.out.println("Get_Consultancy_list response size in leave listing :::::::::::::::::::::::::::::::::::::: " + response.length());
                if (response.length() > 10) {
                    response = "{\"Data\":" + response + "}";


                    // System.out.println("sucess response v !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();

                    consultuncyPojo = gson.fromJson(response, ConsultuncyPojo.class);
                    System.out.println("Get_Consultancy_list approve leave listing data size :::::::::::::::::::::::::::::::::::::" + consultuncyPojo.getData().size());


                    if (consultuncyPojo != null) {
                        if (consultuncyPojo.getData() != null) {
                            if (consultuncyPojo.getData().get(0) != null) {
                                if (consultuncyPojo.getData().size() > 0) {


                                    edt_emp_name.setText(consultuncyPojo.getData().get(0).getEmployee_name() + "");
                                    edt_year.setText(consultuncyPojo.getData().get(0).getAcademic_Year());
                                    edt_name_project.setText(consultuncyPojo.getData().get(0).getName_of_Consultancy_Project() + "");
                                    edt_revenue.setText(consultuncyPojo.getData().get(0).getRevenue_Generated() + "");
                                    edt_consuting.setText(consultuncyPojo.getData().get(0).getConsulting_Sponsoring_Agency_With_Contact_Details() + "");
                                    edt_Status.setText(consultuncyPojo.getData().get(0).getStatus() + "");
                                    edt_app_by.setText(consultuncyPojo.getData().get(0).getApprove_reject_by_user() + "");
                                    edt_app_date.setText(consultuncyPojo.getData().get(0).getApprove_rejct_date() + "");


                                } else {

                                    System.out.println("else  calll ################");
                                    DialogUtils.Show_Toast(ConsultancyDetail.this, "No Records Found");
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
                DialogUtils.Show_Toast(ConsultancyDetail.this, "Please Try Again Later");
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
                showDialog(ConsultancyDetail.this, IntentConstants.APPROVED_STATUS_FOR_APPROVAL, ID);

            }
        });
        tv_delete = (CustomBoldTextView) findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showDialog4YNo(ConsultancyDetail.this, "", "Are You Sure To Reject ?", new DialogUtils.DailogCallBackOkButtonClick() {
                    @Override
                    public void onDialogOkButtonClicked() {
                        //showDialog(ctx, position);
                        showDialog(ConsultancyDetail.this, IntentConstants.REJECT_STATUS_FOR_APPROVAL, ID);


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
        edt_year = (EditText) findViewById(R.id.edt_year);
        ll_leave_balance = (LinearLayout) findViewById(R.id.ll_leave_balance);
        edt_name_project = (EditText) findViewById(R.id.edt_name_project);
        ll_name_project = (LinearLayout) findViewById(R.id.ll_name_project);
        edt_title_of_reach_paper = (EditText) findViewById(R.id.edt_title_of_reach_paper);
        ll_title_of_reach_paper = (LinearLayout) findViewById(R.id.ll_title_of_reach_paper);
        edt_revenue = (EditText) findViewById(R.id.edt_revenue);
        ll_revenue = (LinearLayout) findViewById(R.id.ll_revenue);
        edt_lvl_jou = (EditText) findViewById(R.id.edt_lvl_jou);
        iv_calendar = (ImageView) findViewById(R.id.iv_calendar);
        ll_lvl_jou = (LinearLayout) findViewById(R.id.ll_lvl_jou);
        edt_consuting = (EditText) findViewById(R.id.edt_consuting);
        ll_consulting = (LinearLayout) findViewById(R.id.ll_consulting);
        edt_Status = (EditText) findViewById(R.id.edt_Status);
        ll_Status = (LinearLayout) findViewById(R.id.ll_Status);
        edt_app_date = (EditText) findViewById(R.id.edt_app_date);
        ll_approved_dt = (LinearLayout) findViewById(R.id.ll_approved_dt);
        edt_app_by = (EditText) findViewById(R.id.edt_app_by);
        ll_approved_by = (LinearLayout) findViewById(R.id.ll_approved_by);
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
        dialogButtonOK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isvalidate(edt_reason))
                {
                    show.dismiss();

//                    if (approve_reject.compareToIgnoreCase("approve") == 0)
                    if (approve_reject.compareToIgnoreCase(IntentConstants.APPROVED_STATUS_FOR_APPROVAL) == 0)
                    {
                        ApproveConfarancePub(edt_reason.getText().toString().trim(), show, ID, IntentConstants.APPROVED_STATUS_FOR_APPROVAL);
                    }
                    else
                        {
                        ApproveConfarancePub(edt_reason.getText().toString().trim(), show, ID, IntentConstants.REJECT_STATUS_FOR_APPROVAL);
                        // RejectLeave(edt_reason.getText().toString().trim(), show, pos,ID,"reject");
                    }

                }
            }
        });
    }

    private boolean isvalidate(EditText edt_reason)
    {
        if (edt_reason.getText().toString().trim().isEmpty() || edt_reason.getText().toString().contentEquals("") || edt_reason.getText().toString().length() < 0)
        {
            DialogUtils.Show_Toast(ConsultancyDetail.this, "Enter Reason");
            return false;
        }

        return true;
    }

    ConAppRejPojo conAppRejPojo;

    private void ApproveConfarancePub(String reason, AlertDialog show, final String ID, String app_rej)
    {

        String url;

//        if (app_rej.compareToIgnoreCase("approve") == 0) {
        if (app_rej.compareToIgnoreCase(IntentConstants.APPROVED_STATUS_FOR_APPROVAL) == 0) {
            url = URLS.Get_Consultancy_Approved_or_Reject + "&id=" + ID + "&remarks=" + reason + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&ip=" + "" + "&approve_reject=" + "1" + "";

        } else {
            url = URLS.Get_Consultancy_Approved_or_Reject + "&id=" + ID + "&remarks=" + reason + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&ip=" + "" + "&approve_reject=" + "2" + "";

        }

        url = url.replace(" ", "%20");
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                                            DialogUtils.Show_Toast(ConsultancyDetail.this, conAppRejPojo.getData().get(0).getMsg());
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
                System.out.println();
            }
        });
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(req);


    }
}
