package com.infinity.infoway.rkuniversity.rku_old_app.Activity;


import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.LastInOutAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.LeaveAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.MissPunchAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.App.Config;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomButtonView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTypefaceSpan;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.ExpandableHeightGridView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.CheckVersionPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.CoffPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.FireBasePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.LastInOutPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.LeaveApproveLPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.MissPunchApprovePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PendingApprovalsPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReportListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.TodaysInOutPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private long lastClickTime = 0;
    ImageView imageView, iv_profile, iv_view_leave, iv_add_leave,
            iv_leave_approval, iv_leave_balance, iv_view_miss_punch,
            iv_add_miss_punch, iv_miss_punch_approval,//iv_logout
            iv_add_cancel_leave, iv_add_cancel_leave_apr, iv_apply_leave, iv_att,
            iv_change_psw, iv_salary_slip, iv_coff_approval,
            iv_todays_inout, iv_leave_statastics, iv_pd_statastics, ivAcademicContribution;

    ImageView ivCommittee, ivCommitteeMember;
    //    ImageView iv_employee_e_profile;
    ImageView ivRequestForResignation;
    ImageView ivCommitteeDetails;
    ImageView ivReqPDApplication;

    CustomTextView tvmyprofile, tv_leave_statastics;
    //    CustomTextView tv_todays_out;
//    CustomTextView tv_todays_in;
    LinearLayout llmyprofile;
    CustomTextView tvviewleave, tv_pd_statastics;
    LinearLayout llviewleave, ll_salary_slip;
    //Below filed is added by remish varsani to display academic contribution on 16-03-2020
    CustomTextView tvAcademicContribution;
    LinearLayout llAcademicContribution;

    //Below field is added by remish varsani to display committee and committee member 12/04/2020
//    CustomTextView tvCommittee;
//    LinearLayout llCommittee;

    LinearLayout llCommitteeMember;
    CustomTextView tvCommitteeMember;

    LinearLayout llRequestForResignation;
    CustomTextView tvRequestForResignation;

    LinearLayout llCommitteeDetails;
    CustomTextView tvCommitteeDetails;

    ImageView ivAcademicContributionReport;
    CustomTextView tvAcademicContributionReport;
    LinearLayout llAcademicContributionReport;

//    ImageView ivUserWiseMenuDisplay;
//    CustomTextView tvUserWiseMenuDisplay;
//    LinearLayout llUserWiseMenuDisplay;

//    LinearLayout ll_employee_e_profile;
//    CustomTextView tv_employee_e_profile;

    LinearLayout llReqPDApplication;
    CustomTextView tvReqPDApplication;

    CustomTextView tvaddleave, tv_change_psw;
    LinearLayout lladdleave;
    CustomTextView tvleaveapproval, tv_att;
    LinearLayout llleaveapproval, line_approve_miiss;
    CustomTextView tvleavebalance, tv_logout;
    LinearLayout llleavebalance;
    CustomTextView tvviewmp, tv_view_cl_leave_apr, tv_view_cl_leave, tv_coff_approval;
    LinearLayout llviewmp;
    CustomTextView tvaddmp, tv_apply_leave;
    LinearLayout lladdmp;
    CustomTextView tvmisspunchapp, tv_salary_slip, tv_leave_approval_count, tv_cancel_leave_approval_count, tv_pending_miss_punch_approval_count;
    LinearLayout llmisspunchapp;//ll_logout
    CustomTextView tvOnlineAdmission;
    NavigationView navview;
    DrawerLayout drawerlayout;
    //    ListView lv;
    LastInOutAdapter lastInOutAdapter;
    LeaveAdapter leaveAdapter;
    ExpandableHeightGridView gridView_leave, grid_miss_punch;
    CustomBoldTextView tv_leave, tv_miss_punch;
    MissPunchAdapter missPunchAdapter;
    MySharedPreferecesRKUOLD mySharedPreferenses;
    RequestQueue queue;
    ImageView imgviewleave, imgaddleave, iv_add_leave_dra, imgleavebalance, imgviewcancelleaves, imgleaveapproval, imgcancelleaveapproval, imgviewmisspunch, imgaddmisspunch, imgviewmisspunchapproval;
    CustomBoldTextView tv_emp_code, tv_version, tv_version_code, tv_appluy_cancel_leave_gd;
    CustomBoldTextView tv_view_miss_punch, tv_add_miss_punch, tv_miss_punch_approval;
    CustomBoldTextView tv_leave_balance, tv_leave_approval, tv_add_leave, tv_view_leave, tv_app_cancel_leave, tv_view_cancel_leave_approve;
    String IS_Parent, coff_display;
    LinearLayout llviewleave_new, lladdleave_new, lllevebalance, llviewcancelleaves, ll_leave_approval, llviewcancelleavesapproval, ll_miss_punch_new, ll_add_punch_new, ll_miss_punch_approval_new, ll_leave_statastics, ll_pd_statastics;
    LinearLayout ll_leave_main, ll_att, line_approve_canelleave, line_approve, ll_miss_punch_main, ll_view_cancel_leave, ll_view_cancel_leave_apr, ll_apply_leave, ll_change_psw, ll_coff_approval, line_coff;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    String refreshedToken;
    CardView card_view_leave, card_add_leave, card_leaveleave_balance, card_view_cancel_leave, cardleaveapproval, card_cancel_leave_approval, card_view_miss_punch, card_add_miss_punch, card_view_miss_punch_approval;
    private SweetAlertDialog dialogSuccess;
    CustomTextView tv_count_view_miss_punch, tv_count_view_leave_cancel, tv_count_leave, tv_count_cancel_leave, tv_total_pending_count;

    /*pending counts of all approvals nirali 13 dec 2019*/

    CustomTextView tv_leave_p, tv_cancel_leaves_p, tv_misspunch_p, tv_confarance_pub_p, tv_consultuncy_p, tv_grant_receive_p, tv_pd_app_p, tv_post_doc_p, tv_fdp_att_p, tv_journal_pub_p, tv_books_p, tv_phd_scholars_p, tv_pg_scholars_p, tv_seed_money_p, tv_patent_awarded_p, tv_cpd, tv_pd_app_p_doc;
    public boolean doubleBackToExitPressedOnce = false;


    LinearLayout ll_leave_p, ll_cancel_leave_p, ll_miss_punch_p, ll_confarance_p, ll_patent_aware_p, ll_consultancy_p, ll_grant_receive_p, ll_pd_app, ll_post_doc_p, ll_fdp_att_p, ll_joural_plub_p, ll_books_chap_p, ll_phd_shcolar_p, ll_pg_scholar_p, ll_seed_money_p, ll_cpd, ll_pd_app_doc, ll_all_approvals, ll_pd_statistics;

    int PENDING_COUNT = 0;
    CustomTextView tv_count_total_pending;
    PendingApprovalsPojo pendingApprovalsPojo;
    int total_pending_counts = 0;

    MarshMallowPermission marshMallowPermission;
    //For Expandeble Listview
//    ExpandableListView expLvCommittee;
//    List<String> listDataHeader;
//    HashMap<String, List<String>> listDataChild;
//    CommitteeExpandableListAdapter committeeExpandableListAdapter;

    LinearLayout llDisplayCommitteeMemberWritesWise;

    CardView cvLastAndTodayInOut;
    CustomBoldTextView tvLastInOutAndDate;
    CustomBoldTextView tvTodayInOutAndDate;
    LinearLayout llViewAllLastInOutAndTodayInOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getReportListApi(mySharedPreferenses.getUserID());
        DisplayTodaysInOut(false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);


        }

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    // txtMessage.setText(message);
                }
            }
        };

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("FCMToken", null);

        System.out.println("regId :::: " + regId);
        if (regId != null && !regId.contentEquals("")) {
            RegisterFirebaseToken(regId);
        }
        /*
         *//*for miss punch approval pending records count show in dashboard*//*
        MissPunchApproveCount();
        *//*for  leave approval pending records count show in dashboard*//*
        LeaveApprovalCount();
        *//*for cancel leave approval pending records count show in dashboard*//*
        CancelLeaveApprovalCount();*/


//        pendingApprovalCounts();

        //For settings of expandable listview
//        prepareExpandableListViewForCommittee();
    }


    private void getReportListApi(final String User_id) {
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Report_right, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    ReportListPojo reportListPojo = gson.fromJson("{\"Data\":" + response + "}", ReportListPojo.class);
                    if (reportListPojo.getData() != null && reportListPojo.getData().size() > 0) {
                        ArrayList<ReportListPojo.DataBean> dataBeanArrayList = (ArrayList<ReportListPojo.DataBean>) reportListPojo.getData();
                        ArrayList<String> reportList = new ArrayList<>();
                        for (int i = 0; i < dataBeanArrayList.size(); i++) {
                            if (!TextUtils.isEmpty(dataBeanArrayList.get(i).getMen_URL().trim())) {
                                reportList.add(dataBeanArrayList.get(i).getMen_URL().trim());
                            }
                        }
                        if (reportList.contains("list_hrhr_committee_wise_member_mst.aspx")) {
                            llDisplayCommitteeMemberWritesWise.setVisibility(View.VISIBLE);
                        } else {
                            llDisplayCommitteeMemberWritesWise.setVisibility(View.GONE);
                        }
                    } else {
                        llDisplayCommitteeMemberWritesWise.setVisibility(View.GONE);
                    }
                } else {
                    llDisplayCommitteeMemberWritesWise.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                llDisplayCommitteeMemberWritesWise.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("User_id", User_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getPatentAwardedList.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getPatentAwardedList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /******for miss punch approval pending records count show in dashboard*********/
//        MissPunchApproveCount();
        /*for  leave approval pending records count show in dashboard*/
        PENDING_COUNT = 0;
//        MissPunchApproveCount();
        // LeaveApprovalCount();
        /*for cancel leave approval pending records count show in dashboard*/
//        CancelLeaveApprovalCount();
        pendingApprovalCounts();

        System.out.println("PENDING_COUNT **************** " + PENDING_COUNT);
    }

   /* CancelApproveLPojo cancelApproveLPojo;

    private void CancelLeaveApprovalCount() {

        String url;
        queue = Volley.newRequestQueue(MainActivity.this);


        url = URLS.Get_cancel_leave_appliation_approve_list + "&user_id=" + mySharedPreferenses.getUserID() + "&RowsPerPage=" + "10000" + "&PageNumber=" + "1" + "&status=" + "1" + "";


        url = url.replace(" ", "%20");
        System.out.println("Get_cancel_leave_appliation_approve_list URL onResume Call *********" + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MissPunchApproveCount();
//                DialogUtils.hideProgressDialog();

                // System.out.println("response of Get_User_LeaveBalance !!!!!!!!!!! " + response);
                response = response + "";

                if (response.length() > 10) {
//                    System.out.println("response size of Get_apply_cancel_leave_appliation_list listing ::::::::::::::::::: " + response.length());

                    response = "{\"Data\":" + response + "}";

                    // System.out.println("sucess response Get_User_LeaveBalance !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();
                    cancelApproveLPojo = gson.fromJson(response, CancelApproveLPojo.class);
                    //System.out.println("response data size of cancelLeaveleave listing ::::::::::::::::::: " + cancelLeavePojo.getData().size());
                    if (cancelApproveLPojo != null) {
                        if (cancelApproveLPojo.getData() != null) {
                            if (cancelApproveLPojo.getData().get(0) != null) {
                                if (cancelApproveLPojo.getData().size() > 0) {
                                    PENDING_COUNT += cancelApproveLPojo.getData().size();
                                    tv_count_cancel_leave.setVisibility(View.VISIBLE);

                                    tv_count_cancel_leave.setText(cancelApproveLPojo.getData().size() + "");

                                } else {
                                    tv_count_cancel_leave.setVisibility(View.INVISIBLE);

                                }

                            } else {
                                tv_count_cancel_leave.setVisibility(View.INVISIBLE);

                            }
                        }
                    }
                } else {
                    tv_count_cancel_leave.setVisibility(View.INVISIBLE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }*/

/*    LeaveApproveLPojo leaveApproveLPojo;

    private void LeaveApprovalCount() {

        String url;
        queue = Volley.newRequestQueue(MainActivity.this);


        // url = URLS.Get_leave_approve_list + "&user_id=" + "1" + "&RowsPerPage=" + URLS.RowsPerPage + "&PageNumber=" + PageNo + "&status=" + "1" + "";
        url = URLS.Get_leave_approve_list + "&user_id=" + mySharedPreferenses.getUserID() + "&RowsPerPage=" + "10000" + "&PageNumber=" + "1" + "&status=" + "1" + "";

        url = url.replace(" ", "%20");
        System.out.println("Get_leave_approve_list URL on resume ::::::  " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                CancelLeaveApprovalCount();
                response = response + "";


                System.out.println("response size in leave listing :::::::::::::::::::::::::::::::::::::: " + response.length());
                if (response.length() > 10)
                {
                    response = "{\"Data\":" + response + "}";


                    // System.out.println("sucess response v !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();
                    leaveApproveLPojo = new LeaveApproveLPojo();
                    leaveApproveLPojo = gson.fromJson(response, LeaveApproveLPojo.class);
                    System.out.println("approve leave listing data size :::::::::::::::::::::::::::::::::::::" + leaveApproveLPojo.getData().size());


                    if (leaveApproveLPojo != null)
                    {
                        if (leaveApproveLPojo.getData() != null)
                        {
                            if (leaveApproveLPojo.getData().get(0) != null) {
                                if (leaveApproveLPojo.getData().size() > 0) {
                                    PENDING_COUNT += leaveApproveLPojo.getData().size();
                                    tv_count_leave.setVisibility(View.VISIBLE);
                                    tv_count_leave.setText(leaveApproveLPojo.getData().size() + "");
                                } else {
                                    tv_count_leave.setVisibility(View.INVISIBLE);
                                }

                            } else {
                                tv_count_leave.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                } else {
                    tv_count_leave.setVisibility(View.INVISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }*/

    MissPunchApprovePojo missPunchApprovePojo;

/*    private void MissPunchApproveCount() {

        String url;
        queue = Volley.newRequestQueue(MainActivity.this);


        url = URLS.Get_miss_pucn_approve_list + "&emp_id=" + mySharedPreferenses.getEmpID() + "&RowsPerPage=" + "10000" + "&PageNumber=" + "1" + "&status=" + "1" + "";

        url = url.replace(" ", "%20");
        System.out.println("Get_miss_pucn_approve_list URL page load " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                // System.out.println("response of Get_miss_pucn_approve_list !!!!!!!!!!! " + response);
                response = response + "";


                if (response.length() > 10) {

                    System.out.println("response body size :::::::::::::::" + response.length());
                    response = "{\"Data\":" + response + "}";

                    //   System.out.println("sucess response Get_miss_pucn_approve_list !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();

                    missPunchApprovePojo = gson.fromJson(response, MissPunchApprovePojo.class);


                    if (missPunchApprovePojo != null) {
                        if (missPunchApprovePojo.getData() != null) {
                            if (missPunchApprovePojo.getData().get(0) != null) {
                                if (missPunchApprovePojo.getData().size() > 0) {
                                    PENDING_COUNT += missPunchApprovePojo.getData().size();
                                    tv_count_view_miss_punch.setVisibility(View.VISIBLE);
                                    tv_count_view_miss_punch.setText(missPunchApprovePojo.getData().size() + "");

                                    System.out.println("PENDING_COUNT:::::::::::" + PENDING_COUNT);


                                    if (PENDING_COUNT != 0) {
                                        // tv_total_pending_count.setVisibility(View.VISIBLE);
                                        tv_total_pending_count.setText(PENDING_COUNT + "");

                                        tv_total_pending_count.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                showDialogApprovalsCount(MainActivity.this);
                                            }
                                        });
                                    }

                                } else {
                                    tv_count_view_miss_punch.setVisibility(View.INVISIBLE);
                                }

                            } else {
                                tv_count_view_miss_punch.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                } else {
                    tv_count_view_miss_punch.setVisibility(View.INVISIBLE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }*/


    //********* api call of in-out time display ****************
    private void DisplayTodaysInOut(final boolean isDialogShow) {
        String url = URLS.Get_Today_in_out_time + "&user_id=" + mySharedPreferenses.getUserID() + "";
        System.out.println("Get_Today_in_out_time URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                // System.out.println("response of Get_Dashboard_detail !!!!!!!!!!! " + response);

                if (response.length() > 5) {
                    response = response + "";
                    response = "{\"Data\":" + response + "}";

                    // System.out.println("sucess response Get_Dashboard_detail !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();
                    TodaysInOutPojo todaysInOutPojo = gson.fromJson(response, TodaysInOutPojo.class);
                    if (todaysInOutPojo != null) {
                        if (todaysInOutPojo.getData() != null) {
                            if (todaysInOutPojo.getData().get(0) != null) {
                               /* tv_todays_in.setText(todaysInOutPojo.getData().get(0).getIntime() + "");
                                tv_todays_out.setText(todaysInOutPojo.getData().get(0).getOuttime() + "");


*/

                                //  pendingApprovalCounts(todaysInOutPojo);
                                /*showDialog(MainActivity.this, todaysInOutPojo.getData().get(0).getIntime() + "", todaysInOutPojo.getData().get(0).getOuttime() + "");*/

                                try {

                                    String todayInOut = "-|-";

                                    if (todaysInOutPojo.getData().get(0).getIntime() != null && !todaysInOutPojo.getData().get(0).getIntime().isEmpty()) {
                                        todayInOut = todaysInOutPojo.getData().get(0).getIntime() + " | ";
                                    }

                                    if (todaysInOutPojo.getData().get(0).getOuttime() != null && !todaysInOutPojo.getData().get(0).getOuttime().isEmpty()) {
                                        todayInOut += todaysInOutPojo.getData().get(0).getOuttime();
                                    }

                                    tvTodayInOutAndDate.setText(todayInOut);


                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                if (isDialogShow) {
                                    showDialog(MainActivity.this, todaysInOutPojo.getData().get(0).getIntime() + "", todaysInOutPojo.getData().get(0).getOuttime() + "");
                                }
                            }

                        }
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(MainActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }

    private void RegisterFirebaseToken(String refreshedToken) {
        String url = URLS.Insert_mobile_tocken + "&userid=" + mySharedPreferenses.getUserID() + "&tocken=" + refreshedToken + "";
        System.out.println("Insert_mobile_tocken URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                // System.out.println("response of Get_Dashboard_detail !!!!!!!!!!! " + response);

                if (response.length() > 5) {
                    response = response + "";
                    response = "{\"Data\":" + response + "}";

                    // System.out.println("sucess response Get_Dashboard_detail !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();
                    FireBasePojo fireBasePojo = gson.fromJson(response, FireBasePojo.class);
                    if (fireBasePojo != null) {
                        if (fireBasePojo.getData() != null) {
                            System.out.println("registration successful ::::::::::::::::::::::");

                        }
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(MainActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);


    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        System.out.println("firebase registration id is :::::::::" + regId);
        Log.e("token", regId);

       /* if (!TextUtils.isEmpty(regId))
            txtRegId.setText("Firebase Reg Id: " + regId);
        else
            txtRegId.setText("Firebase Reg Id is not received yet!");*/
    }

    private void HideShowApprovals(final LinearLayout ll_all_approvals) {
        String url = URLS.Get_Dashboard_detail + "&emp_id=" + mySharedPreferenses.getEmpID() + "";
        System.out.println("Get_Dashboard_detail URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                // System.out.println("response of Get_Dashboard_detail !!!!!!!!!!! " + response);

                if (response.length() > 10) {
                    response = response + "";
                    response = "{\"Data\":" + response + "}";

                    // System.out.println("success response Get_Dashboard_detail !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();
                    LastInOutPojo lastInOutPojo = gson.fromJson(response, LastInOutPojo.class);
                    if (lastInOutPojo != null) {
                        if (lastInOutPojo.getData() != null) {

                            if (lastInOutPojo.getData().size() > 0) {


                                System.out.println("is parentttttttttttttt " + lastInOutPojo.getData().get(0).getIsParent());


                                if (lastInOutPojo.getData().get(0).getIsParent().contentEquals("1")) {

                                    ll_all_approvals.setVisibility(View.VISIBLE);
                                } else {
                                    ll_all_approvals.setVisibility(View.GONE);
                                }

                            } else {

                            }

                            // }
                        }
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(MainActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }

    private void LastInOutTimeAPICall() {

//        DialogUtils.showProgressDialog(MainActivity.this,"");
        String url = URLS.Get_Dashboard_detail + "&emp_id=" + mySharedPreferenses.getEmpID() + "";
        System.out.println("Get_Dashboard_detail URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                // System.out.println("response of Get_Dashboard_detail !!!!!!!!!!! " + response);

                if (response.length() > 10) {
                    response = response + "";
                    response = "{\"Data\":" + response + "}";

                    // System.out.println("sucess response Get_Dashboard_detail !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();
                    LastInOutPojo lastInOutPojo = gson.fromJson(response, LastInOutPojo.class);
                    if (lastInOutPojo != null) {
                        if (lastInOutPojo.getData() != null) {

                            if (lastInOutPojo.getData().size() > 0) {

//                                lv = (ListView) findViewById(R.id.lv);
//                                if (lv != null) {
                                mySharedPreferenses.setIsParent(lastInOutPojo.getData().get(0).getIsParent() + "");
                                mySharedPreferenses.setcoff_is_display(lastInOutPojo.getData().get(0).getCoff_is_display() + "");
                                IS_Parent = mySharedPreferenses.getIsPatrent();
                                coff_display = mySharedPreferenses.getCoffisDisplay();

                                System.out.println("coff_display from API ************ " + coff_display);
                                if (IS_Parent.compareToIgnoreCase("1") == 0) {
                                    tv_count_total_pending.setVisibility(View.VISIBLE);
                                    card_cancel_leave_approval.setVisibility(View.VISIBLE);
                                    card_view_miss_punch_approval.setVisibility(View.VISIBLE);
                                    cardleaveapproval.setVisibility(View.VISIBLE);
                                    // tv_leave_approval.setVisibility(View.VISIBLE);
                                    // tv_miss_punch_approval.setVisibility(View.VISIBLE);
                                    //tv_view_cancel_leave_approve.setVisibility(View.VISIBLE);
                                    llleaveapproval.setVisibility(View.VISIBLE);
                                    llmisspunchapp.setVisibility(View.VISIBLE);
                                    ll_view_cancel_leave_apr.setVisibility(View.VISIBLE);
                                    line_approve_canelleave.setVisibility(View.VISIBLE);
                                    line_approve.setVisibility(View.VISIBLE);
                                    line_approve_miiss.setVisibility(View.VISIBLE);
                                } else {
                                    tv_count_total_pending.setVisibility(View.GONE);
                                    card_cancel_leave_approval.setVisibility(View.GONE);
                                    card_view_miss_punch_approval.setVisibility(View.GONE);
                                    cardleaveapproval.setVisibility(View.GONE);
                                    // tv_leave_approval.setVisibility(View.INVISIBLE);
                                    //tv_miss_punch_approval.setVisibility(View.INVISIBLE);
                                    //tv_view_cancel_leave_approve.setVisibility(View.INVISIBLE);
                                    llleaveapproval.setVisibility(View.GONE);
                                    llmisspunchapp.setVisibility(View.GONE);
                                    line_approve_canelleave.setVisibility(View.GONE);
                                    line_approve.setVisibility(View.GONE);
                                    line_approve_miiss.setVisibility(View.GONE);
                                    ll_view_cancel_leave_apr.setVisibility(View.GONE);
                                }


                                if (coff_display.compareToIgnoreCase("1") == 0) {
                                    ll_coff_approval.setVisibility(View.VISIBLE);
                                    line_coff.setVisibility(View.VISIBLE);
                                } else {
                                    ll_coff_approval.setVisibility(View.GONE);
                                    line_coff.setVisibility(View.GONE);
                                }


                                lastInOutAdapter = new LastInOutAdapter(MainActivity.this, lastInOutPojo);


                                try {
                                    if (lastInOutPojo != null && lastInOutPojo.getData().size() > 0) {

                                        String lastInOut = "-|-";

                                        if (lastInOutPojo.getData().get(0).getLastDate() != null && !lastInOutPojo.getData().get(0).getLastDate().isEmpty()) {
                                            lastInOut = lastInOutPojo.getData().get(0).getLastDate() + " ";
                                        }

                                        if (lastInOutPojo.getData().get(0).getLast_in() != null && !lastInOutPojo.getData().get(0).getLast_in().isEmpty() &&
                                                lastInOutPojo.getData().get(0).getLast_in().contains(" ")) {
                                            lastInOut += lastInOutPojo.getData().get(0).getLast_in().split(" ")[1] + " | ";
                                        }

                                        int listSize = lastInOutPojo.getData().size();

                                        if (lastInOutPojo.getData().get(listSize - 1).getLast_Out() != null && !lastInOutPojo.getData().get(listSize - 1).getLast_Out().isEmpty() &&
                                                lastInOutPojo.getData().get(listSize - 1).getLast_Out().contains(" ")) {
                                            lastInOut += lastInOutPojo.getData().get(listSize - 1).getLast_Out().split(" ")[1];
                                        }

                                        tvLastInOutAndDate.setText(lastInOut);

                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }


//                                    lv.setAdapter(lastInOutAdapter);

//                                }

                            } else {

                            }

                            // }
                        }
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(MainActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);


    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    private void initView() {
        mySharedPreferenses = new MySharedPreferecesRKUOLD(getApplicationContext());
        queue = Volley.newRequestQueue(MainActivity.this);
   /* card_miss_punch= (CardView)findViewById(R.id.card_miss_punch);
    card_leave= (CardView)findViewById(R.id.card_leave);*/
        marshMallowPermission = new MarshMallowPermission(this);
        PackageInfo pInfo = null;
        assert pInfo != null;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

//        expLvCommittee = findViewById(R.id.expLvCommittee);
        llDisplayCommitteeMemberWritesWise = findViewById(R.id.llDisplayCommitteeMemberWritesWise);

        tv_count_total_pending = (CustomTextView) findViewById(R.id.tv_count_total_pending);
        tv_count_total_pending.setVisibility(View.VISIBLE);
        tv_count_view_miss_punch = (CustomTextView) findViewById(R.id.tv_count_view_miss_punch);
        tv_count_view_leave_cancel = (CustomTextView) findViewById(R.id.tv_count_view_leave_cancel);
        tv_count_leave = (CustomTextView) findViewById(R.id.tv_count_leave);
        tv_count_cancel_leave = (CustomTextView) findViewById(R.id.tv_count_cancel_leave);

        tv_total_pending_count = (CustomTextView) findViewById(R.id.tv_total_pending_count);
        tv_emp_code = (CustomBoldTextView) findViewById(R.id.tv_emp_code);
        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version_code = (CustomBoldTextView) findViewById(R.id.tv_version_code);
        tv_version.setText(pInfo.versionName);
        tv_emp_code.setText(mySharedPreferenses.getEmpCode());
//        tv_version_code.setText(pInfo.versionCode);


        iv_todays_inout = (ImageView) findViewById(R.id.iv_todays_inout);
        iv_todays_inout.setVisibility(View.VISIBLE);
        iv_todays_inout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DisplayTodaysInOut(true);

            }
        });
        iv_miss_punch_approval = (ImageView) findViewById(R.id.iv_miss_punch_approval);
        tv_leave_statastics = (CustomTextView) findViewById(R.id.tv_leave_statastics);
        iv_leave_statastics = (ImageView) findViewById(R.id.iv_leave_statastics);
        iv_pd_statastics = (ImageView) findViewById(R.id.iv_pd_statastics);
        ivAcademicContribution = findViewById(R.id.ivAcademicContribution);
        ivCommitteeMember = findViewById(R.id.ivCommitteeMemberNew);
        ivCommitteeDetails = findViewById(R.id.ivCommitteeDetails);
        ivRequestForResignation = findViewById(R.id.ivRequestForResignation);
        ivAcademicContributionReport = findViewById(R.id.ivAcademicContributionReport);
//        ivUserWiseMenuDisplay = findViewById(R.id.ivUserWiseMenuDisplay);
        ivReqPDApplication = findViewById(R.id.ivReqPDApplication);
        //        iv_employee_e_profile = findViewById(R.id.iv_employee_e_profile);
//        ivCommittee = findViewById(R.id.ivCommitteeNew);
        ll_leave_statastics = (LinearLayout) findViewById(R.id.ll_leave_statastics);
        ll_pd_statastics = (LinearLayout) findViewById(R.id.ll_pd_statastics);
        card_view_leave = (CardView) findViewById(R.id.card_view_leave);
        card_add_leave = (CardView) findViewById(R.id.card_add_leave);
        card_add_miss_punch = (CardView) findViewById(R.id.card_add_miss_punch);
        card_cancel_leave_approval = (CardView) findViewById(R.id.card_cancel_leave_approval);
        card_view_cancel_leave = (CardView) findViewById(R.id.card_view_cancel_leave);
        cardleaveapproval = (CardView) findViewById(R.id.cardleaveapproval);
        card_view_miss_punch = (CardView) findViewById(R.id.card_view_miss_punch);
        card_leaveleave_balance = (CardView) findViewById(R.id.card_leaveleave_balance);
        card_view_miss_punch_approval = (CardView) findViewById(R.id.card_view_miss_punch_approval);


//        ll_logout = (LinearLayout) findViewById(R.id.ll_logout);
        imageView = (ImageView) findViewById(R.id.imageView);

        imgviewleave = (ImageView) findViewById(R.id.imgviewleave);
        imgaddleave = (ImageView) findViewById(R.id.imgaddleave);
//        iv_add_leave_dra = (ImageView) findViewById(R.id.iv_add_leave_dra);
        imgleavebalance = (ImageView) findViewById(R.id.imgleavebalance);
        imgviewcancelleaves = (ImageView) findViewById(R.id.imgviewcancelleaves);
        imgleaveapproval = (ImageView) findViewById(R.id.imgleaveapproval);
        imgcancelleaveapproval = (ImageView) findViewById(R.id.imgcancelleaveapproval);
        imgviewmisspunch = (ImageView) findViewById(R.id.imgviewmisspunch);
        imgaddmisspunch = (ImageView) findViewById(R.id.imgaddmisspunch);
        imgviewmisspunchapproval = (ImageView) findViewById(R.id.imgviewmisspunchapproval);

//        iv_logout = (ImageView) findViewById(R.id.iv_logout);
        iv_change_psw = (ImageView) findViewById(R.id.iv_change_psw);
        iv_salary_slip = (ImageView) findViewById(R.id.iv_salary_slip);
        iv_coff_approval = (ImageView) findViewById(R.id.iv_coff_approval);
        iv_add_cancel_leave = (ImageView) findViewById(R.id.iv_add_cancel_leave);
        iv_add_cancel_leave_apr = (ImageView) findViewById(R.id.iv_add_cancel_leave_apr);
        iv_apply_leave = (ImageView) findViewById(R.id.iv_apply_leave);
        iv_att = (ImageView) findViewById(R.id.iv_att);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        iv_view_leave = (ImageView) findViewById(R.id.iv_view_leave);
        iv_add_leave = (ImageView) findViewById(R.id.iv_add_leave_dra);
        iv_leave_approval = (ImageView) findViewById(R.id.iv_leave_approval);
        iv_leave_balance = (ImageView) findViewById(R.id.iv_leave_balance);
        iv_view_miss_punch = (ImageView) findViewById(R.id.iv_view_miss_punch);
        iv_add_miss_punch = (ImageView) findViewById(R.id.iv_add_miss_punch);
        iv_miss_punch_approval = (ImageView) findViewById(R.id.iv_miss_punch_approval);
        tvmyprofile = (CustomTextView) findViewById(R.id.tv_my_profile);

//        tv_todays_out = (CustomTextView) findViewById(R.id.tv_todays_out);
//        tv_todays_in = (CustomTextView) findViewById(R.id.tv_todays_in);

//        tv_logout = (CustomTextView) findViewById(R.id.tv_logout);
        llmyprofile = (LinearLayout) findViewById(R.id.ll_my_profile);
        tvviewleave = (CustomTextView) findViewById(R.id.tv_view_leave);
        tv_pd_statastics = (CustomTextView) findViewById(R.id.tv_pd_statastics);
        llviewleave = (LinearLayout) findViewById(R.id.ll_view_leave);
        ll_salary_slip = (LinearLayout) findViewById(R.id.ll_salary_slip);
        llCommitteeMember = findViewById(R.id.llCommitteeMemberNew);
        llCommitteeDetails = findViewById(R.id.llCommitteeDetails);
        llRequestForResignation = findViewById(R.id.llRequestForResignation);
        llAcademicContributionReport = findViewById(R.id.llAcademicContributionReport);
//        llUserWiseMenuDisplay = findViewById(R.id.llUserWiseMenuDisplay);
        llReqPDApplication = findViewById(R.id.llReqPDApplication);
        //        ll_employee_e_profile = findViewById(R.id.ll_employee_e_profile);
        tvCommitteeMember = findViewById(R.id.tvCommitteeMemberNew);
        tvCommitteeDetails = findViewById(R.id.tvCommitteeDetails);
        tvRequestForResignation = findViewById(R.id.tvRequestForResignation);
        tvAcademicContributionReport = findViewById(R.id.tvAcademicContributionReport);
//        tvUserWiseMenuDisplay = findViewById(R.id.tvUserWiseMenuDisplay);
        tvReqPDApplication = findViewById(R.id.tvReqPDApplication);
        //        tv_employee_e_profile = findViewById(R.id.tv_employee_e_profile);
//        tvCommittee = findViewById(R.id.tvCommitteeNew);
//        llCommittee = findViewById(R.id.llCommitteeNew);
        llAcademicContribution = findViewById(R.id.llAcademicContribution);
        tvAcademicContribution = findViewById(R.id.tvAcademicContribution);
        tvaddleave = (CustomTextView) findViewById(R.id.tv_add_leave);
        tv_change_psw = (CustomTextView) findViewById(R.id.tv_change_psw);
        lladdleave = (LinearLayout) findViewById(R.id.ll_add_leave);
        tvleaveapproval = (CustomTextView) findViewById(R.id.tv_leave_approval);
        tv_att = (CustomTextView) findViewById(R.id.tv_att);
        llleaveapproval = (LinearLayout) findViewById(R.id.ll_leave_approval);
        tvleavebalance = (CustomTextView) findViewById(R.id.tv_leave_balance);
        llleavebalance = (LinearLayout) findViewById(R.id.ll_leave_balance);
        line_approve_miiss = (LinearLayout) findViewById(R.id.line_approve_miiss);
        tvviewmp = (CustomTextView) findViewById(R.id.tv_view_mp);
        tv_view_cl_leave_apr = (CustomTextView) findViewById(R.id.tv_view_cl_leave_apr);
        tv_view_cl_leave = (CustomTextView) findViewById(R.id.tv_view_cl_leave);
        tv_coff_approval = (CustomTextView) findViewById(R.id.tv_coff_approval);
        llviewmp = (LinearLayout) findViewById(R.id.ll_view_mp);
        tvaddmp = (CustomTextView) findViewById(R.id.tv_add_mp);
        tv_apply_leave = (CustomTextView) findViewById(R.id.tv_apply_leave);
        lladdmp = (LinearLayout) findViewById(R.id.ll_add_mp);
        tvmisspunchapp = (CustomTextView) findViewById(R.id.tv_miss_punch_app);
        tv_salary_slip = (CustomTextView) findViewById(R.id.tv_salary_slip);
        llmisspunchapp = (LinearLayout) findViewById(R.id.ll_miss_punch_app);
        tvOnlineAdmission = (CustomTextView) findViewById(R.id.tv_Online_Admission);
        navview = (NavigationView) findViewById(R.id.nav_view);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        lv = (ListView) findViewById(R.id.lv);

//        gridView_leave = (ExpandableHeightGridView) findViewById(R.id.grid_leave);
//        grid_miss_punch = (ExpandableHeightGridView) findViewById(R.id.grid_miss_punch);
//        grid_miss_punch.setExpanded(true);
//        leaveAdapter = new LeaveAdapter(MainActivity.this);
//        gridView_leave.setExpanded(true);
//        gridView_leave.setAdapter(leaveAdapter);
//        missPunchAdapter = new MissPunchAdapter(MainActivity.this);
//        grid_miss_punch.setAdapter(missPunchAdapter);


        tv_view_miss_punch = (CustomBoldTextView) findViewById(R.id.tv_view_miss_punch_gd);
        tv_add_leave = (CustomBoldTextView) findViewById(R.id.tv_add_leave_gd);
        tv_add_miss_punch = (CustomBoldTextView) findViewById(R.id.tv_add_miss_punch_gd);
        tv_leave_balance = (CustomBoldTextView) findViewById(R.id.tv_leave_balance_gd);
        tv_appluy_cancel_leave_gd = (CustomBoldTextView) findViewById(R.id.tv_appluy_cancel_leave_gd);
        tv_app_cancel_leave = (CustomBoldTextView) findViewById(R.id.tv_cancel_leave);
        tv_view_cancel_leave_approve = (CustomBoldTextView) findViewById(R.id.tv_view_cancel_leave_approve);
        tv_view_leave = (CustomBoldTextView) findViewById(R.id.tv_view_leave_gd);
        tv_leave_approval = (CustomBoldTextView) findViewById(R.id.tv_leave_approval_gd);
        tv_miss_punch_approval = (CustomBoldTextView) findViewById(R.id.tv_miss_punch_approval_gd);

        tv_leave = (CustomBoldTextView) findViewById(R.id.tv_leave);
        tv_miss_punch = (CustomBoldTextView) findViewById(R.id.tv_miss_punch);

        ll_leave_main = (LinearLayout) findViewById(R.id.ll_leave_main);


        llviewleave_new = (LinearLayout) findViewById(R.id.llviewleave);
        lladdleave_new = (LinearLayout) findViewById(R.id.lladdleave_new);
        lllevebalance = (LinearLayout) findViewById(R.id.lllevebalance);
        llviewcancelleaves = (LinearLayout) findViewById(R.id.llviewcancelleaves);
        ll_leave_approval = (LinearLayout) findViewById(R.id.ll_leave_approval_new);
        llviewcancelleavesapproval = (LinearLayout) findViewById(R.id.llviewcancelleavesapproval);
        ll_miss_punch_new = (LinearLayout) findViewById(R.id.ll_miss_punch_new);
        ll_add_punch_new = (LinearLayout) findViewById(R.id.ll_add_punch_new);
        ll_miss_punch_approval_new = (LinearLayout) findViewById(R.id.ll_miss_punch_approval_new);


        ll_change_psw = (LinearLayout) findViewById(R.id.ll_change_psw);
        ll_coff_approval = (LinearLayout) findViewById(R.id.ll_coff_approval);
        line_coff = (LinearLayout) findViewById(R.id.line_coff);
        ll_att = (LinearLayout) findViewById(R.id.ll_att);

        line_approve = (LinearLayout) findViewById(R.id.line_approve);
        line_approve_canelleave = (LinearLayout) findViewById(R.id.line_approve_canelleave);
        ll_miss_punch_main = (LinearLayout) findViewById(R.id.ll_miss_punch_main);
        ll_view_cancel_leave = (LinearLayout) findViewById(R.id.ll_view_cancel_leave);
        ll_view_cancel_leave_apr = (LinearLayout) findViewById(R.id.ll_view_cancel_leave_apr);
        ll_apply_leave = (LinearLayout) findViewById(R.id.ll_apply_leave);

//added by remish
        cvLastAndTodayInOut = findViewById(R.id.cvLastAndTodayInOut);
        tvLastInOutAndDate = findViewById(R.id.tvLastInOutAndDate);
        tvTodayInOutAndDate = findViewById(R.id.tvTodayInOutAndDate);
        llViewAllLastInOutAndTodayInOut = findViewById(R.id.llViewAllLastInOutAndTodayInOut);
        llViewAllLastInOutAndTodayInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewAllLeavePunchInOut.class);
                startActivity(intent);
            }
        });


/*
        tv_leave_p = (CustomTextView) findViewById(R.id.tv_leave_p);
        tv_cancel_leaves_p = (CustomTextView) findViewById(R.id.tv_cancel_leaves_p);
        tv_misspunch_p = (CustomTextView) findViewById(R.id.tv_misspunch_p);
        tv_confarance_pub_p = (CustomTextView) findViewById(R.id.tv_confarance_pub_p);
        tv_consultuncy_p = (CustomTextView) findViewById(R.id.tv_consultuncy_p);
        tv_grant_receive_p = (CustomTextView) findViewById(R.id.tv_grant_receive_p);
        tv_pd_app_p = (CustomTextView) findViewById(R.id.tv_pd_app_p);
        tv_post_doc_p = (CustomTextView) findViewById(R.id.tv_post_doc_p);
        tv_fdp_att_p = (CustomTextView) findViewById(R.id.tv_fdp_att_p);
        tv_journal_pub_p = (CustomTextView) findViewById(R.id.tv_journal_pub_p);
        tv_books_p = (CustomTextView) findViewById(R.id.tv_books_p);
        tv_phd_scholars_p = (CustomTextView) findViewById(R.id.tv_phd_scholars_p);
        tv_pg_scholars_p = (CustomTextView) findViewById(R.id.tv_pg_scholars_p);
        tv_seed_money_p = (CustomTextView) findViewById(R.id.tv_seed_money_p);*/


        /*approvals layout*/


        LastInOutTimeAPICall();


        CheckVersionApi();


        System.out.println("IS_Parent value in Main activity @@@@@@@@@@@@@@@ " + IS_Parent);
       /* if (IS_Parent.compareToIgnoreCase("1")==0) {
            tv_leave_approval.setVisibility(View.VISIBLE);
            tv_miss_punch_approval.setVisibility(View.VISIBLE);
        } else {
            tv_leave_approval.setVisibility(View.INVISIBLE);
            tv_miss_punch_approval.setVisibility(View.INVISIBLE);
        }*/


        tv_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();*/
                ll_leave_main.setVisibility(View.VISIBLE);
                ll_miss_punch_main.setVisibility(View.GONE);

               /* gridView_leave.setVisibility(View.VISIBLE);
                grid_miss_punch.setVisibility(View.GONE);*/

                tv_leave.setTextColor(getResources().getColor(R.color.white));
                tv_leave.setBackground(getResources().getDrawable(R.drawable.border_fill_border_pink));
                tv_miss_punch.setBackground(getResources().getDrawable(R.drawable.border_cat_main));
                tv_miss_punch.setTextColor(getResources().getColor(R.color.black));
            }
        });
        tv_appluy_cancel_leave_gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_appluy_cancel_leave_gd.setTextColor(getResources().getColor(R.color.white));
                tv_appluy_cancel_leave_gd.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, ApplyCancelLeaveActivity.class);
                startActivity(intent);
            }
        });
        tv_miss_punch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
//                {
//                    return;
//                }
//                lastClickTime = SystemClock.elapsedRealtime();
                ll_leave_main.setVisibility(View.GONE);
                ll_miss_punch_main.setVisibility(View.VISIBLE);


              /*  gridView_leave.setVisibility(View.GONE);
                grid_miss_punch.setVisibility(View.VISIBLE);*/


                tv_leave.setTextColor(getResources().getColor(R.color.black));
                tv_leave.setBackground(getResources().getDrawable(R.drawable.border_cat_main));
                tv_miss_punch.setBackground(getResources().getDrawable(R.drawable.border_fill_border_pink));
                tv_miss_punch.setTextColor(getResources().getColor(R.color.white));
            }
        });

        llviewleave_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                card_view_leave.setCardBackgroundColor(getResources().getColor(R.color.dark_black));
                tv_view_leave.setTextColor(getResources().getColor(R.color.white));
                imgviewleave.setImageDrawable(getResources().getDrawable(R.drawable.view_leave_final_white));

//                llviewleave_new.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, ViewLeaveListingActivity.class);
                startActivity(intent);

            }
        });

        lladdleave_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_add_leave.setTextColor(getResources().getColor(R.color.white));
                imgaddleave.setImageDrawable(getResources().getDrawable(R.drawable.add_leave_final_white));
                card_add_leave.setCardBackgroundColor(getResources().getColor(R.color.dark_black));

//                lladdleave_new.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, AddLeaveAcivity.class);
                intent.putExtra("ID", "");
                intent.putExtra("status", "");
                intent.putExtra("is_submit", true);
                startActivity(intent);
            }
        });

        lllevebalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_leave_balance.setTextColor(getResources().getColor(R.color.white));
                imgleavebalance.setImageDrawable(getResources().getDrawable(R.drawable.leave_balance_final_white));
                card_leaveleave_balance.setCardBackgroundColor(getResources().getColor(R.color.dark_black));

//                lladdleave_new.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, LeaveBalanceActivity.class);
                startActivity(intent);
            }
        });
        llviewcancelleaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {

                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_app_cancel_leave.setTextColor(getResources().getColor(R.color.white));
                imgviewcancelleaves.setImageDrawable(getResources().getDrawable(R.drawable.view_cancel_leave_final_white));
                card_view_cancel_leave.setCardBackgroundColor(getResources().getColor(R.color.dark_black));

                Intent intent = new Intent(MainActivity.this, ViewCancelLeaveActivity.class);
                startActivity(intent);
            }
        });
        ll_leave_approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {

                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                ApproveLeaveActivity.listall = new ArrayList<>();
                ApproveLeaveActivity.listall.clear();
                ApproveLeaveActivity.leaveApproveLPojo = new LeaveApproveLPojo();
                ApproveLeaveActivity.isChecked_API = false;
                changeListingDataColor();

                tv_leave_approval.setTextColor(getResources().getColor(R.color.white));
                imgleaveapproval.setImageDrawable(getResources().getDrawable(R.drawable.leave_approval_final_white));
                cardleaveapproval.setCardBackgroundColor(getResources().getColor(R.color.dark_black));

                Intent intent = new Intent(MainActivity.this, ApproveLeaveActivity.class);
                startActivity(intent);
            }
        });

        llviewcancelleavesapproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {

                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();

                tv_view_cancel_leave_approve.setTextColor(getResources().getColor(R.color.white));
                imgcancelleaveapproval.setImageDrawable(getResources().getDrawable(R.drawable.cancel_leave_approval_final_white));
                card_cancel_leave_approval.setCardBackgroundColor(getResources().getColor(R.color.dark_black));

                Intent intent = new Intent(MainActivity.this, ViewApproveCancelLeaveActivity.class);
                startActivity(intent);
            }
        });
        ll_miss_punch_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {

                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();

                tv_view_miss_punch.setTextColor(getResources().getColor(R.color.white));
                imgviewmisspunch.setImageDrawable(getResources().getDrawable(R.drawable.view_miss_punch_final_white));
                card_view_miss_punch.setCardBackgroundColor(getResources().getColor(R.color.dark_black));

                Intent intent = new Intent(MainActivity.this, MyMissPunchActivity.class);
                startActivity(intent);
            }
        });

        ll_add_punch_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {

                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();

                tv_add_miss_punch.setTextColor(getResources().getColor(R.color.white));
                imgaddmisspunch.setImageDrawable(getResources().getDrawable(R.drawable.add_miss_punch_final_white));
                card_add_miss_punch.setCardBackgroundColor(getResources().getColor(R.color.dark_black));
                Intent intent = new Intent(MainActivity.this, AddMissPunchActivity.class);
                startActivity(intent);
            }
        });

        ll_miss_punch_approval_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {

                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                MissPunchApproval.listall = new ArrayList<>();
                MissPunchApproval.listall.clear();
                MissPunchApproval.missPunchApprovePojo = new MissPunchApprovePojo();
                MissPunchApproval.is_check_API = false;

                changeListingDataColor();

                tv_miss_punch_approval.setTextColor(getResources().getColor(R.color.white));
                imgviewmisspunchapproval.setImageDrawable(getResources().getDrawable(R.drawable.miss_punch_approval_final_white));
                card_view_miss_punch_approval.setCardBackgroundColor(getResources().getColor(R.color.dark_black));
                Intent intent = new Intent(MainActivity.this, MissPunchApproval.class);
                startActivity(intent);
            }
        });


     /*   tv_miss_punch_approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_miss_punch_approval.setTextColor(getResources().getColor(R.color.white));
                tv_miss_punch_approval.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, MissPunchApproval.class);
                startActivity(intent);
            }
        });*/
/*
        tv_view_miss_punch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_view_miss_punch.setTextColor(getResources().getColor(R.color.white));
                tv_view_miss_punch.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, MyMissPunchActivity.class);
                startActivity(intent);
            }
        });*/

      /*  tv_view_cancel_leave_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_view_cancel_leave_approve.setTextColor(getResources().getColor(R.color.white));
                tv_view_cancel_leave_approve.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, ViewApproveCancelLeaveActivity.class);
                startActivity(intent);
            }
        });*/
        /*tv_app_cancel_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_app_cancel_leave.setTextColor(getResources().getColor(R.color.white));
                tv_app_cancel_leave.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, ViewCancelLeaveActivity.class);
                startActivity(intent);
            }
        });*/
       /* tv_add_miss_punch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_add_miss_punch.setTextColor(getResources().getColor(R.color.white));
                tv_add_miss_punch.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, AddMissPunchActivity.class);
                startActivity(intent);
            }
        });*/
     /*   tv_leave_approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                ApproveLeaveActivity.listall =new ArrayList<>();
                ApproveLeaveActivity.listall.clear();
                ApproveLeaveActivity.leaveApproveLPojo =new LeaveApproveLPojo();
                ApproveLeaveActivity.isChecked_API=false;
                changeListingDataColor();
                tv_leave_approval.setTextColor(getResources().getColor(R.color.white));
                tv_leave_approval.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, ApproveLeaveActivity.class);
                startActivity(intent);
            }
        });*/
    /*    tv_leave_balance.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_leave_balance.setTextColor(getResources().getColor(R.color.white));
                tv_leave_balance.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, LeaveBalanceActivity.class);
                startActivity(intent);
            }
        });*/
       /* tv_add_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_add_leave.setTextColor(getResources().getColor(R.color.white));
                tv_add_leave.setBackground(getResources().getDrawable(R.drawable.border_signup));

                Intent intent = new Intent(MainActivity.this, AddLeaveAcivity.class);
                intent.putExtra("ID", "");
                intent.putExtra("status", "");
                intent.putExtra("is_submit", true);
                startActivity(intent);
            }
        });*/
   /*     tv_view_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime <  URLS.TIME_TILL_DISABLE_BTN)
                {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                changeListingDataColor();
                tv_view_leave.setTextColor(getResources().getColor(R.color.white));

                tv_view_leave.setBackground(getResources().getDrawable(R.drawable.border_signup));
                Intent intent = new Intent(MainActivity.this, ViewLeaveListingActivity.class);
                startActivity(intent);
            }
        });*/


//        ll_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeBackgoundColor();
//
//                iv_logout.setImageDrawable(getResources().getDrawable(R.drawable.logout_dra_white));
////                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
//                ll_logout.setBackgroundColor(getResources().getColor(R.color.red));
//
//                DialogUtils.showDialog4YNo(MainActivity.this, "", "Are You Sure To Logout ?", new DialogUtils.DailogCallBackOkButtonClick() {
//                    @Override
//                    public void onDialogOkButtonClicked() {
//                        mySharedPreferenses.logout();
//
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }, new DialogUtils.DailogCallBackCancelButtonClick() {
//                    @Override
//                    public void onDialogCancelButtonClicked() {
//
//                    }
//                });
////                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
//                tv_logout.setTextColor(getResources().getColor(R.color.white));
//
//            }
//        });

        ll_att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_att.setImageDrawable(getResources().getDrawable(R.drawable.attendance_report_dra_white));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                ll_att.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tv_att.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, AttendanceReportActivity.class);
                startActivity(i);
            }
        });

        ll_coff_approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                COffLeaveApprovalActitivty.listall = new ArrayList<>();
                COffLeaveApprovalActitivty.listall.clear();
                COffLeaveApprovalActitivty.coffPojo = new CoffPojo();
                COffLeaveApprovalActitivty.isChecked_API = false;
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_coff_approval.setImageDrawable(getResources().getDrawable(R.drawable.check_white));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                ll_coff_approval.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tv_coff_approval.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, COffLeaveApprovalActitivty.class);
                startActivity(i);
            }
        });


        ll_salary_slip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_salary_slip.setImageDrawable(getResources().getDrawable(R.drawable.salary_slip_dra_white));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                ll_salary_slip.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tv_salary_slip.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, SlarySlipActivity.class);
                startActivity(i);
            }
        });


        //This Filed is added by remish varsnai to display academic contribution on 16-03-2020
        llAcademicContribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ivAcademicContribution.setImageDrawable(getResources().getDrawable(R.drawable.salary_slip_dra_black));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                llAcademicContribution.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tvAcademicContribution.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                requestForReadExternalStoragePermission();
            }
        });


//        ll_employee_e_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeBackgoundColor();
//
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                iv_employee_e_profile.setImageDrawable(getResources().getDrawable(R.drawable.salary_slip_dra_white));
//
//                ll_employee_e_profile.setBackgroundColor(getResources().getColor(R.color.red));
//
//                tv_employee_e_profile.setTextColor(getResources().getColor(R.color.white));
//                drawer.closeDrawer(GravityCompat.START);
//                if (!marshMallowPermission.checkPermissionForReadExternalStorage()) {
//                    marshMallowPermission.requestPermissionForReadExternalStorage();
//                }else {
//                    Intent intent = new Intent(MainActivity.this,EmployeeEProfileActivity.class);
//                    startActivity(intent);
//                }
//
//            }
//        });


        llRequestForResignation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ivRequestForResignation.setImageDrawable(getResources().getDrawable(R.drawable.request_for_resignation));

                llRequestForResignation.setBackgroundColor(getResources().getColor(R.color.red));

                tvRequestForResignation.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);

                Intent intent = new Intent(MainActivity.this, RequestForResignationActivity.class);
                startActivity(intent);

            }
        });


        llAcademicContributionReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ivAcademicContributionReport.setImageDrawable(getResources().getDrawable(R.drawable.academic_contribution_report));

                llAcademicContributionReport.setBackgroundColor(getResources().getColor(R.color.red));

                tvAcademicContributionReport.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);

                Intent intent = new Intent(MainActivity.this, ReportListActivity.class);
                startActivity(intent);

            }
        });


//        llUserWiseMenuDisplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                changeBackgoundColor();
//
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                ivUserWiseMenuDisplay.setImageDrawable(getResources().getDrawable(R.drawable.salary_slip_dra_black));
//
//                llUserWiseMenuDisplay.setBackgroundColor(getResources().getColor(R.color.red));
//
//                tvUserWiseMenuDisplay.setTextColor(getResources().getColor(R.color.white));
//                drawer.closeDrawer(GravityCompat.START);
//
//                Intent intent = new Intent(MainActivity.this, UserWiseMenuActivity.class);
//                startActivity(intent);
//
//            }
//        });

//
//
        llReqPDApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ivReqPDApplication.setImageDrawable(getResources().getDrawable(R.drawable.salary_slip_dra_black));

                llReqPDApplication.setBackgroundColor(getResources().getColor(R.color.red));

                tvReqPDApplication.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                if (!marshMallowPermission.checkPermissionForReadExternalStorage()) {
                    marshMallowPermission.requestPermissionForReadExternalStorage();
                } else {
                    Intent intent = new Intent(MainActivity.this, ReqPDApplicationListActivity.class);
                    startActivity(intent);
                }

            }
        });

        llCommitteeMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ivCommitteeMember.setImageDrawable(getResources().getDrawable(R.drawable.member_png));

                llCommitteeMember.setBackgroundColor(getResources().getColor(R.color.red));

                tvCommitteeMember.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                if (!marshMallowPermission.checkPermissionForReadExternalStorage()) {
                    marshMallowPermission.requestPermissionForReadExternalStorage();
                } else {
                    Intent intent = new Intent(MainActivity.this, CommitteeMemberListActivity.class);
                    startActivity(intent);
                }

            }
        });

        llCommitteeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ivCommitteeDetails.setImageDrawable(getResources().getDrawable(R.drawable.committee_member_details));

                llCommitteeDetails.setBackgroundColor(getResources().getColor(R.color.red));

                tvCommitteeDetails.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);

                Intent intent = new Intent(MainActivity.this, CommitteeDetailListActivity.class);
                startActivity(intent);

            }
        });


//        llCommittee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeBackgoundColor();
//
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                ivCommittee.setImageDrawable(getResources().getDrawable(R.drawable.committee));
////
//                llCommittee.setBackgroundColor(getResources().getColor(R.color.red));
//
//                tvCommittee.setTextColor(getResources().getColor(R.color.white));
//                drawer.closeDrawer(GravityCompat.START);
//
//                if (!marshMallowPermission.checkPermissionForReadExternalStorage()) {
//                    marshMallowPermission.requestPermissionForReadExternalStorage();
//                } else {
//                    Intent intent = new Intent(MainActivity.this, CommitteeListActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

        ll_view_cancel_leave_apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (SystemClock.elapsedRealtime() - lastClickTime < 10000)
//                {
//                    return;
//                }

                changeBackgoundColor();

                System.out.println("call profile layout !!!!!!!!!!!!!!!!!!!!!! ");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_add_cancel_leave_apr.setImageDrawable(getResources().getDrawable(R.drawable.cancel_leave_aaproval_dra_white));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                ll_view_cancel_leave_apr.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tv_view_cl_leave_apr.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, ViewApproveCancelLeaveActivity.class);
                startActivity(i);
            }
        });
        ll_apply_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                System.out.println("call profile layout !!!!!!!!!!!!!!!!!!!!!! ");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_apply_leave.setImageDrawable(getResources().getDrawable(R.drawable.add_leave_white));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                ll_apply_leave.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tv_apply_leave.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, ApplyCancelLeaveActivity.class);
                startActivity(i);
            }
        });
        llmyprofile.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;

            @Override
            public void onClick(View v) {
//                if (SystemClock.elapsedRealtime() - lastClickTime < 10000)
//                {
//                    return;
//                }
                changeBackgoundColor();

                System.out.println("call profile layout !!!!!!!!!!!!!!!!!!!!!! ");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_profile.setImageDrawable(getResources().getDrawable(R.drawable.my_profile_dra_white));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                llmyprofile.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tvmyprofile.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);

            }
        });

        ll_leave_statastics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgoundColor();

                System.out.println("call profile layout !!!!!!!!!!!!!!!!!!!!!! ");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_leave_statastics.setImageDrawable(getResources().getDrawable(R.drawable.leave_balance_dra_white));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                ll_leave_statastics.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tv_leave_statastics.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(i);
            }
        });


        ll_pd_statastics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBackgoundColor();

                System.out.println("call profile layout !!!!!!!!!!!!!!!!!!!!!! ");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_pd_statastics.setImageDrawable(getResources().getDrawable(R.drawable.leave_balance_dra_white));
//                llmyprofile.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                ll_pd_statastics.setBackgroundColor(getResources().getColor(R.color.red));
//                tvmyprofile.setTextColor(getResources().getColor(R.color.red));
                tv_pd_statastics.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, PDStatistics.class);
                startActivity(i);
            }
        });
        //4//
        llleaveapproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApproveLeaveActivity.listall = new ArrayList<>();
                ApproveLeaveActivity.listall.clear();
                ApproveLeaveActivity.leaveApproveLPojo = new LeaveApproveLPojo();
                ApproveLeaveActivity.isChecked_API = false;
                changeBackgoundColor();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_leave_approval.setImageDrawable(getResources().getDrawable(R.drawable.leave_approval_dra_white));
//                llleaveapproval.setBackgroundColor(getResources().getColor(R.color.bg_drawer_select));
                llleaveapproval.setBackgroundColor(getResources().getColor(R.color.red));
//                tvleaveapproval.setTextColor(getResources().getColor(R.color.red));
                tvleaveapproval.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, ApproveLeaveActivity.class);
                startActivity(i);
            }
        });
        //4//


        llviewmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgoundColor();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_view_miss_punch.setImageDrawable(getResources().getDrawable(R.drawable.view_miss_punch_dra_white));
                llviewmp.setBackgroundColor(getResources().getColor(R.color.red));
                tvviewmp.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, MyMissPunchActivity.class);
                startActivity(i);
            }
        });

        lladdmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgoundColor();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_add_miss_punch.setImageDrawable(getResources().getDrawable(R.drawable.add_miss_punch_ra_white));
                lladdmp.setBackgroundColor(getResources().getColor(R.color.red));
                tvaddmp.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, AddMissPunchActivity.class);
                startActivity(i);
            }
        });

        llmisspunchapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgoundColor();
                MissPunchApproval.is_check_API = false;
                MissPunchApproval.listall = new ArrayList<>();
                MissPunchApproval.listall.clear();
                MissPunchApproval.missPunchApprovePojo = new MissPunchApprovePojo();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_miss_punch_approval.setImageDrawable(getResources().getDrawable(R.drawable.miss_punch_app_dra_white));
                llmisspunchapp.setBackgroundColor(getResources().getColor(R.color.red));
                tvmisspunchapp.setTextColor(getResources().getColor(R.color.white));
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(MainActivity.this, MissPunchApproval.class);
                startActivity(i);
            }
        });

        llviewleave.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;

            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                iv_view_leave.setImageDrawable(getResources().getDrawable(R.drawable.view_leave_dra_white));
                llviewleave.setBackgroundColor(getResources().getColor(R.color.red));
                tvviewleave.setTextColor(getResources().getColor(R.color.white));
                Intent i = new Intent(MainActivity.this, ViewLeaveListingActivity.class);
                startActivity(i);

            }
        });
        //2//
        llleavebalance.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;

            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                iv_leave_balance.setImageDrawable(getResources().getDrawable(R.drawable.leave_balance_dra_white));
                llleavebalance.setBackgroundColor(getResources().getColor(R.color.red));
                tvleavebalance.setTextColor(getResources().getColor(R.color.white));
                Intent i = new Intent(MainActivity.this, LeaveBalanceActivity.class);
                startActivity(i);

            }
        });
        //2//
        //1//
        lladdleave.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;

            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_add_leave.setImageDrawable(getResources().getDrawable(R.drawable.add_leave_dra_white));
                drawer.closeDrawer(GravityCompat.START);
                lladdleave.setBackgroundColor(getResources().getColor(R.color.red));
                tvaddleave.setTextColor(getResources().getColor(R.color.white));
                Intent i = new Intent(MainActivity.this, AddLeaveAcivity.class);
                i.putExtra("ID", "");
                i.putExtra("status", "");
                i.putExtra("is_submit", true);
                startActivity(i);

            }
        });
        //1//
        //3//
        ll_view_cancel_leave.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;

            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_add_cancel_leave.setImageDrawable(getResources().getDrawable(R.drawable.view_cancel_leave_dra_white));
                drawer.closeDrawer(GravityCompat.START);
                ll_view_cancel_leave.setBackgroundColor(getResources().getColor(R.color.red));
                tv_view_cl_leave.setTextColor(getResources().getColor(R.color.white));
                Intent i = new Intent(MainActivity.this, ViewCancelLeaveActivity.class);
                startActivity(i);

            }
        });
        //3//

        llviewmp.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;

            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_view_miss_punch.setImageDrawable(getResources().getDrawable(R.drawable.view_miss_punch_dra_white));
                drawer.closeDrawer(GravityCompat.START);
                llviewmp.setBackgroundColor(getResources().getColor(R.color.red));
                tvviewmp.setTextColor(getResources().getColor(R.color.white));
                Intent i = new Intent(MainActivity.this, MyMissPunchActivity.class);
                startActivity(i);

            }
        });
        ll_change_psw.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;

            @Override
            public void onClick(View v) {
                changeBackgoundColor();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                iv_change_psw.setImageDrawable(getResources().getDrawable(R.drawable.chnage_psw_dra_white));
                drawer.closeDrawer(GravityCompat.START);
                ll_change_psw.setBackgroundColor(getResources().getColor(R.color.red));
                tv_change_psw.setTextColor(getResources().getColor(R.color.white));
                Intent i = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(i);

            }
        });

    }


    private void pendingApprovalCounts() {

        total_pending_counts = 0;
        queue = Volley.newRequestQueue(MainActivity.this);

        String url = URLS.Get_employee_pending_approvals + "&user_id=" + mySharedPreferenses.getUserID() + "";

        url = url.replace(" ", "%20");
        System.out.println("Get_employee_pending_approvals URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                //System.out.println("response of Get_leave_approve_list !!!!!!!!!!! " + response);
                response = response + "";

                System.out.println("Get_employee_pending_approvals response size in leave listing :::::::::::::::::::::::::::::::::::::: " + response.length());
                if (response.length() > 10) {
                    response = "{\"Data\":" + response + "}";


                    // System.out.println("sucess response v !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();

                    pendingApprovalsPojo = gson.fromJson(response, PendingApprovalsPojo.class);


                    if (pendingApprovalsPojo != null) {
                        if (pendingApprovalsPojo.getData() != null) {
                            if (pendingApprovalsPojo.getData().get(0) != null) {
                                if (pendingApprovalsPojo.getData().size() > 0) {

                                    for (int i = 0; i < pendingApprovalsPojo.getData().size(); i++) {

                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Leave Application Request") == 0) {

                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }

                                        System.out.println("total_pending_counts :::::::::: " + total_pending_counts);

                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Cancel Leave Application Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }

                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Mis-punch Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }


                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("PD Application Document Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }

                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("PD Application Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }
                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Publication in conference Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }

                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Publication in journal Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }
                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Book/Chapter Publication Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }
                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Patent Awarded Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }
                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("PG Scholars Guided Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }
                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("PhD Scholars Guided Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }
                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("CPD Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }

                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("FDP Attendant Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }

                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Consultancy Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }

                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Grant Received Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }


                                        if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Seed Money Received From University Request") == 0) {
                                            total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

                                        }

                                        tv_count_total_pending.setText(total_pending_counts + "");

                                    }

//                                    showDialog(MainActivity.this, pendingApprovalsPojo, todaysInOutPojo.getData().get(0).getIntime() + "", todaysInOutPojo.getData().get(0).getOuttime() + "");

                                } else {

                                    System.out.println("else  calll ################");
                                    DialogUtils.Show_Toast(MainActivity.this, "No Records Found");
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
                DialogUtils.Show_Toast(MainActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);


    }

/*    public void showDialogApprovalsCount(final Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.pending_approvals_count, null);


        CustomTextView tv_leave_approval_count = (CustomTextView) dialogView.findViewById(R.id.tv_leave_approval_count);
        if (leaveApproveLPojo.getData() != null && leaveApproveLPojo.getData().size() > 0) {
            tv_leave_approval_count.setText(leaveApproveLPojo.getData().size() + "");
        }


        final CustomTextView tv_cancel_leave_approval_count = (CustomTextView) dialogView.findViewById(R.id.tv_cancel_leave_approval_count);

        if (cancelApproveLPojo.getData() != null && cancelApproveLPojo.getData().size() > 0) {
            tv_cancel_leave_approval_count.setText(cancelApproveLPojo.getData().size() + "");
        }


        final CustomTextView tv_pending_miss_punch_approval_count = (CustomTextView) dialogView.findViewById(R.id.tv_pending_miss_punch_approval_count);
        if (missPunchApprovePojo.getData() != null && missPunchApprovePojo.getData().size() > 0) {
            tv_pending_miss_punch_approval_count.setText(missPunchApprovePojo.getData().size() + "");
        }


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

        LinearLayout ll_leave_approval_count = (LinearLayout) dialogView.findViewById(R.id.ll_leave_approval_count);
        ll_leave_approval_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ApproveLeaveActivity.class);
                startActivity(intent);
                show.dismiss();

            }
        });

        LinearLayout ll_cancel_leave_approval_count = (LinearLayout) dialogView.findViewById(R.id.ll_cancel_leave_approval_count);
        ll_cancel_leave_approval_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewApproveCancelLeaveActivity.class);
                startActivity(intent);
                show.dismiss();
            }
        });

        LinearLayout ll_miss_punch_aaproval_count = (LinearLayout) dialogView.findViewById(R.id.ll_miss_punch_aaproval_count);
        ll_miss_punch_aaproval_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MissPunchApproval.class);
                startActivity(intent);
                show.dismiss();
            }
        });

    }*/


    public void showDialog(final Context context, String in_time, String out_time) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.todays_in_out_layout, null);

        ImageView iv_close = (ImageView) dialogView.findViewById(R.id.iv_close);
//        final CustomTextView tv_in_time = (CustomTextView) dialogView.findViewById(R.id.tv_todays_in);
//        tv_in_time.setText(" " + in_time + "|" + out_time);
//        tv_in_time.setText(in_time + "  |"+ " "+"7:54AM");
//        final CustomTextView tv_todays_out = (CustomTextView) dialogView.findViewById(R.id.tv_todays_out);
//        tv_todays_out.setText(out_time + "");
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


        /*all pending approvals list*/

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        IS_Parent = mySharedPreferenses.getIsPatrent();
        ll_all_approvals = (LinearLayout) dialogView.findViewById(R.id.ll_all_approvals);

        HideShowApprovals(ll_all_approvals);



       /* if (IS_Parent.compareToIgnoreCase("1") == 0) {
            tv_count_total_pending.setVisibility(View.VISIBLE);
            ll_all_approvals.setVisibility(View.VISIBLE);

        } else {
            tv_count_total_pending.setVisibility(View.GONE);
            ll_all_approvals.setVisibility(View.GONE);
        }*/

        System.out.println("IS_Parent  :::: ** " + IS_Parent);

        tv_leave_p = (CustomTextView) dialogView.findViewById(R.id.tv_leave_p);
        tv_confarance_pub_p = (CustomTextView) dialogView.findViewById(R.id.tv_confarance_pub_p);
        tv_pd_app_p = (CustomTextView) dialogView.findViewById(R.id.tv_pd_app_p);
        tv_consultuncy_p = (CustomTextView) dialogView.findViewById(R.id.tv_consultuncy_p);
        tv_grant_receive_p = (CustomTextView) dialogView.findViewById(R.id.tv_grant_receive_p);
        tv_post_doc_p = (CustomTextView) dialogView.findViewById(R.id.tv_post_doc_p);
        tv_fdp_att_p = (CustomTextView) dialogView.findViewById(R.id.tv_fdp_att_p);
        tv_journal_pub_p = (CustomTextView) dialogView.findViewById(R.id.tv_journal_pub_p);
        tv_books_p = (CustomTextView) dialogView.findViewById(R.id.tv_books_p);
        tv_phd_scholars_p = (CustomTextView) dialogView.findViewById(R.id.tv_phd_scholars_p);
        tv_pg_scholars_p = (CustomTextView) dialogView.findViewById(R.id.tv_pg_scholars_p);
        tv_seed_money_p = (CustomTextView) dialogView.findViewById(R.id.tv_seed_money_p);
        tv_patent_awarded_p = (CustomTextView) dialogView.findViewById(R.id.tv_patent_awarded_p);
        tv_cpd = (CustomTextView) dialogView.findViewById(R.id.tv_cpd);
        tv_pd_app_p_doc = (CustomTextView) dialogView.findViewById(R.id.tv_pd_app_p_doc);

        for (int i = 0; i < pendingApprovalsPojo.getData().size(); i++) {

            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Leave Application Request") == 0) {
                tv_leave_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");
                //total_pending_counts = total_pending_counts + Integer.parseInt(pendingApprovalsPojo.getData().get(i).getPen_count());

            }

            System.out.println("total_pending_counts :::::::::: " + total_pending_counts);
            tv_cancel_leaves_p = (CustomTextView) dialogView.findViewById(R.id.tv_cancel_leaves_p);
            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Cancel Leave Application Request") == 0) {
                tv_cancel_leaves_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }
            tv_misspunch_p = (CustomTextView) dialogView.findViewById(R.id.tv_misspunch_p);
            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Mis-punch Request") == 0) {
                tv_misspunch_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }


            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("PD Application Document Request") == 0) {
                tv_pd_app_p_doc.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }

            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("PD Application Request") == 0) {
                tv_pd_app_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }
            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Publication in conference Request") == 0) {

                tv_confarance_pub_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");

            }

            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Publication in journal Request") == 0) {
                tv_journal_pub_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }
            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Book/Chapter Publication Request") == 0) {
                tv_books_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }
            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Patent Awarded Request") == 0) {
                tv_patent_awarded_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }
            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("PG Scholars Guided Request") == 0) {
                tv_pg_scholars_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }
            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("PhD Scholars Guided Request") == 0) {
                tv_phd_scholars_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");


            }
            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("CPD Request") == 0) {
                tv_cpd.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");

            }

            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("FDP Attendant Request") == 0) {
                tv_fdp_att_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");

            }

            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Consultancy Request") == 0) {
                tv_consultuncy_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");

            }

            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Grant Received Request") == 0) {
                tv_grant_receive_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");

            }


            if (pendingApprovalsPojo.getData().get(i).getApp_type().compareToIgnoreCase("Seed Money Received From University Request") == 0) {
                tv_seed_money_p.setText(pendingApprovalsPojo.getData().get(i).getPen_count() + "");

            }

            // tv_count_total_pending.setText(total_pending_counts + "");

        }
        ll_pd_statistics = (LinearLayout) dialogView.findViewById(R.id.ll_pd_statistics);
        ll_pd_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PDStatistics.class);
                startActivity(intent);

                show.dismiss();
            }
        });
        ll_leave_p = (LinearLayout) dialogView.findViewById(R.id.ll_leave_p);
        ll_leave_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ApproveLeaveActivity.class);
                startActivity(intent);

                show.dismiss();
            }
        });
        ll_cancel_leave_p = (LinearLayout) dialogView.findViewById(R.id.ll_cancel_leave_p);
        ll_cancel_leave_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewApproveCancelLeaveActivity.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_miss_punch_p = (LinearLayout) dialogView.findViewById(R.id.ll_miss_punch_p);
        ll_miss_punch_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MissPunchApproval.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_confarance_p = (LinearLayout) dialogView.findViewById(R.id.ll_confarance_p);
        ll_confarance_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConferencePubActivity.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_patent_aware_p = (LinearLayout) dialogView.findViewById(R.id.ll_patent_aware_p);
        ll_patent_aware_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatentAwarded.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_consultancy_p = (LinearLayout) dialogView.findViewById(R.id.ll_consultancy_p);
        ll_consultancy_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consultancy.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_grant_receive_p = (LinearLayout) dialogView.findViewById(R.id.ll_grant_receive_p);
        ll_grant_receive_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GrantReceived.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_pd_app = (LinearLayout) dialogView.findViewById(R.id.ll_pd_app);
        ll_pd_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PDApplication.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_pd_app_doc = (LinearLayout) dialogView.findViewById(R.id.ll_pd_app_doc);
        ll_pd_app_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PDAppDocumentApprovalListing.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_post_doc_p = (LinearLayout) dialogView.findViewById(R.id.ll_post_doc_p);
        ll_post_doc_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostDocument.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_fdp_att_p = (LinearLayout) dialogView.findViewById(R.id.ll_fdp_att_p);
        ll_fdp_att_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FDPAttended.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_joural_plub_p = (LinearLayout) dialogView.findViewById(R.id.ll_joural_plub_p);
        ll_joural_plub_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JournalPub.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_books_chap_p = (LinearLayout) dialogView.findViewById(R.id.ll_books_chap_p);
        ll_books_chap_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BooksChapter.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_phd_shcolar_p = (LinearLayout) dialogView.findViewById(R.id.ll_phd_shcolar_p);
        ll_phd_shcolar_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhDDScholars.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_pg_scholar_p = (LinearLayout) dialogView.findViewById(R.id.ll_pg_scholar_p);
        ll_pg_scholar_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PGScholars.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_seed_money_p = (LinearLayout) dialogView.findViewById(R.id.ll_seed_money_p);
        ll_seed_money_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SeedMoney.class);
                startActivity(intent);
                show.dismiss();
            }
        });
        ll_cpd = (LinearLayout) dialogView.findViewById(R.id.ll_cpd);
        ll_cpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CpdApplication.class);
                startActivity(intent);
                show.dismiss();
            }
        });


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void CheckVersionApi() {
        String url = URLS.Get_app_version + "";
        url = url.replace(" ", "%20");
        System.out.println("Get_app_version URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                // System.out.println("response of Get_Dashboard_detail !!!!!!!!!!! " + response);

                if (response.length() > 5) {
                    response = response + "";
                    response = "{\"Data\":" + response + "}";

                    // System.out.println("sucess response Get_Dashboard_detail !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();

                    CheckVersionPojo checkVersionPojo = gson.fromJson(response, CheckVersionPojo.class);


                    if (checkVersionPojo != null) {
                        if (checkVersionPojo.getData() != null) {
                            if (checkVersionPojo.getData().get(0) != null) {

                                PackageInfo pInfo = null;
                                assert pInfo != null;

                                try {
                                    pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

                                } catch (PackageManager.NameNotFoundException e) {
                                    e.printStackTrace();
                                }

                                int versionCode = pInfo.versionCode;

                                if (!checkVersionPojo.getData().get(0).getApp_version().contentEquals("") && !checkVersionPojo.getData().get(0).getIs_force_update().contentEquals("") && checkVersionPojo.getData().get(0).getIs_force_update().compareToIgnoreCase("1") == 0 && versionCode < Integer.parseInt(checkVersionPojo.getData().get(0).getApp_version())) {


                                    try {

                                        dialogSuccess = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                        dialogSuccess.setTitleText(getResources().getString(R.string.title_app_update));
                                        dialogSuccess.setContentText(getResources().getString(R.string.title_new_version_available));
                                        dialogSuccess.setCancelable(false);
                                        dialogSuccess.show();

                                        Button confirm_button = dialogSuccess.findViewById(R.id.confirm_button);
                                        confirm_button.setText(R.string.title_update_now);

                                        confirm_button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialogSuccess.dismissWithAnimation();
                                                dialogSuccess.cancel();
                                                try {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Config.PACKAGE_NAME)));
                                                    finish();
                                                } catch (android.content.ActivityNotFoundException anfe) {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + Config.PACKAGE_NAME)));
                                                    finish();
                                                }

                                            }
                                        });
                                    } catch (Exception ignored) {

                                    }

                                } else if (!checkVersionPojo.getData().get(0).getApp_version().contentEquals("") && !checkVersionPojo.getData().get(0).getIs_force_update().contentEquals("") && checkVersionPojo.getData().get(0).getIs_force_update().compareToIgnoreCase("0") == 0 && versionCode < Integer.parseInt(checkVersionPojo.getData().get(0).getApp_version())) {
                                    try {

                                        dialogSuccess = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                        dialogSuccess.setTitleText(getResources().getString(R.string.title_app_update));

                                        dialogSuccess.setContentText(getResources().getString(R.string.title_new_version_available));
                                        dialogSuccess.setCancelable(false);
                                        dialogSuccess.show();

                                        dialogSuccess.setCancelText(getResources().getString(R.string.title_not_now));
                                        dialogSuccess.showCancelButton(true);

                                        Button confirm_button = dialogSuccess.findViewById(R.id.confirm_button);
                                        confirm_button.setText(R.string.title_update_now);

                                        confirm_button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialogSuccess.dismissWithAnimation();
                                                dialogSuccess.cancel();
                                                try {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Config.PACKAGE_NAME)));
                                                    finish();
                                                } catch (android.content.ActivityNotFoundException anfe) {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + Config.PACKAGE_NAME)));
                                                    finish();
                                                }

                                            }
                                        });

                                        Button cancel_button = dialogSuccess.findViewById(R.id.cancel_button);
                                        cancel_button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialogSuccess.dismissWithAnimation();
                                              /*  Intent intent = new Intent(Activity_Splash.this, Activity_Login.class);
                                                startActivity(intent);
                                                finish();*/
                                            }
                                        });

                                    } catch (Exception ignored) {
                                    }


                                }

                            }

                        }
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(MainActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);


    }


    public void changeListingDataColor() {
        tv_miss_punch_approval.setTextColor(getResources().getColor(R.color.black));
        tv_leave_balance.setTextColor(getResources().getColor(R.color.black));
        imgviewmisspunchapproval.setImageDrawable(getResources().getDrawable(R.drawable.miss_punch_approve_final_red));
        card_view_miss_punch_approval.setCardBackgroundColor(getResources().getColor(R.color.white));


        tv_add_miss_punch.setTextColor(getResources().getColor(R.color.black));
        imgaddmisspunch.setImageDrawable(getResources().getDrawable(R.drawable.add_miss_punch_final_red));
        card_add_miss_punch.setCardBackgroundColor(getResources().getColor(R.color.white));


        tv_view_miss_punch.setTextColor(getResources().getColor(R.color.black));
        imgviewmisspunch.setImageDrawable(getResources().getDrawable(R.drawable.vew_miss_punch_final_red));
        card_view_miss_punch.setCardBackgroundColor(getResources().getColor(R.color.white));


        tv_view_cancel_leave_approve.setTextColor(getResources().getColor(R.color.black));
        imgcancelleaveapproval.setImageDrawable(getResources().getDrawable(R.drawable.cancel_leave_approval_final_red));
        card_cancel_leave_approval.setCardBackgroundColor(getResources().getColor(R.color.white));


        card_view_leave.setCardBackgroundColor(getResources().getColor(R.color.white));
        card_add_leave.setCardBackgroundColor(getResources().getColor(R.color.white));
        card_leaveleave_balance.setCardBackgroundColor(getResources().getColor(R.color.white));


        tv_leave_approval.setTextColor(getResources().getColor(R.color.black));
        imgleaveapproval.setImageDrawable(getResources().getDrawable(R.drawable.leave_approval_final_red));
        cardleaveapproval.setCardBackgroundColor(getResources().getColor(R.color.white));


        tv_app_cancel_leave.setTextColor(getResources().getColor(R.color.black));

        imgviewcancelleaves.setImageDrawable(getResources().getDrawable(R.drawable.view_cancel_leave_final_red));

        imgaddleave.setImageDrawable(getResources().getDrawable(R.drawable.add_leave_final_red));
        imgviewleave.setImageDrawable(getResources().getDrawable(R.drawable.view_leave_final_red));


        card_view_cancel_leave.setCardBackgroundColor(getResources().getColor(R.color.white));

        imgviewleave.setImageDrawable(getResources().getDrawable(R.drawable.view_leave_final_red));
        imgaddleave.setImageDrawable(getResources().getDrawable(R.drawable.add_leave_final_red));
        imgleavebalance.setImageDrawable(getResources().getDrawable(R.drawable.leave_balance_final_red));


        tv_add_leave.setTextColor(getResources().getColor(R.color.black));
        tv_view_leave.setTextColor(getResources().getColor(R.color.black));


       /* tv_appluy_cancel_leave_gd.setTextColor(getResources().getColor(R.color.text));
        tv_view_cancel_leave_approve.setTextColor(getResources().getColor(R.color.text));
        tv_app_cancel_leave.setTextColor(getResources().getColor(R.color.text));
        tv_add_leave.setTextColor(getResources().getColor(R.color.text));
        tv_leave_approval.setTextColor(getResources().getColor(R.color.text));
        tv_leave_balance.setTextColor(getResources().getColor(R.color.text));
        tv_add_miss_punch.setTextColor(getResources().getColor(R.color.text));
        tv_view_miss_punch.setTextColor(getResources().getColor(R.color.text));
        tv_miss_punch_approval.setTextColor(getResources().getColor(R.color.text));
//        tv_view_leave.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
        tv_view_cancel_leave_approve.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
        tv_miss_punch_approval.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
        tv_view_miss_punch.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
        tv_add_miss_punch.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
        tv_leave_approval.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
        tv_leave_balance.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
        tv_appluy_cancel_leave_gd.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
//        llviewleave_new.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
//        lladdleave_new.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
//        tv_add_leave.setBackground(getResources().getDrawable(R.drawable.view_leave_border));
        tv_app_cancel_leave.setBackground(getResources().getDrawable(R.drawable.view_leave_border));*/
    }

    public void changeBackgoundColor() {
        iv_miss_punch_approval.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.miss_punch_app_dra_black));
        iv_leave_statastics.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.leave_balance_dra_black));
        iv_pd_statastics.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.leave_balance_dra_black));
        iv_coff_approval.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.check_black));
        iv_change_psw.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.change_psw_dra_black));
        iv_att.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.attendance_report_dra_black));
        iv_salary_slip.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.salary_slip_dra_black));
        ivAcademicContribution.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.salary_slip_dra_black));
        ivCommitteeMember.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.member_png));
        ivCommitteeDetails.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.committee_member_details));
        ivRequestForResignation.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.request_for_resignation));
        ivAcademicContributionReport.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.academic_contribution_report));
//        ivUserWiseMenuDisplay.setImageDrawable(getResources().getDrawable(R.drawable.salary_slip_dra_black));
        //        iv_employee_e_profile.setImageDrawable(getResources().getDrawable(R.drawable.salary_slip_dra_black));
//        ivCommittee.setImageDrawable(getResources().getDrawable(R.drawable.committee));
        //        iv_add_miss_punch.setImageDrawable(getResources().getDrawable(R.drawable.add_leave_white));
        //        iv_add_leave.setImageDrawable(getResources().getDrawable(R.drawable.add_leave_white));
        //        iv_view_miss_punch.setImageDrawable(getResources().getDrawable(R.drawable.view_leave_black));
        ivReqPDApplication.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.salary_slip_dra_black));
        iv_apply_leave.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.add_leave_black));
        iv_add_cancel_leave_apr.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.cancel_leave_approval_dra_black));
        iv_add_cancel_leave.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.view_cancel_leave_dra_black));
//        iv_logout.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.logout_dra_black));
        iv_add_miss_punch.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.add_miss_punch_dra_black));
        iv_view_leave.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.view_leave_dra_black));
        iv_leave_balance.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.leave_balance_dra_black));
        iv_add_leave.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.add_leave_dra_black));
        iv_view_miss_punch.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.view_miss_punch_dra_black));
        iv_leave_approval.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.leave_apprval_dra_black));
        iv_profile.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.my_profile_dra_black));
        llviewleave.setBackgroundColor(getResources().getColor(R.color.drawer_new_bg));

        ll_coff_approval.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        ll_apply_leave.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        ll_change_psw.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        ll_view_cancel_leave_apr.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        ll_salary_slip.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llAcademicContribution.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llCommitteeMember.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llCommitteeDetails.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llRequestForResignation.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llAcademicContributionReport.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llReqPDApplication.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        lladdmp.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llviewmp.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llmisspunchapp.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        ll_view_cancel_leave.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llleaveapproval.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llviewmp.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        llleavebalance.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        ll_pd_statastics.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        tvviewleave.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvviewmp.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvaddmp.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvmisspunchapp.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvleaveapproval.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvviewmp.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_coff_approval.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_change_psw.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_leave_statastics.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_pd_statastics.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvleavebalance.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        llmyprofile.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        ll_leave_statastics.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        ll_att.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        tvmyprofile.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_att.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        lladdleave.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.drawer_new_bg));
        tvaddleave.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
//        tv_logout.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_salary_slip.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvAcademicContribution.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvCommitteeMember.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvCommitteeDetails.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvRequestForResignation.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvAcademicContributionReport.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tvReqPDApplication.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_view_cl_leave_apr.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_apply_leave.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        tv_view_cl_leave.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.black));
//        tvUserWiseMenuDisplay.setTextColor(getResources().getColor(R.color.black));
        //        tv_employee_e_profile.setTextColor(getResources().getColor(R.color.black));
//        tvCommittee.setTextColor(getResources().getColor(R.color.black));
        //        ll_employee_e_profile.setBackgroundColor(getResources().getColor(R.color.drawer_new_bg));
//        llCommittee.setBackgroundColor(getResources().getColor(R.color.drawer_new_bg));
        //        llUserWiseMenuDisplay.setBackgroundColor(getResources().getColor(R.color.drawer_new_bg));
//        ll_logout.setBackgroundColor(getResources().getColor(R.color.drawer_new_bg));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    /*@Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


        openExitDialog();
    }*/

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Snackbar bar = Snackbar.make(drawer, getResources().getString(R.string.tap_back_again_to_exit), Snackbar.LENGTH_LONG);
            bar.show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
/* if (mService != null) {
mService.requestLocationUpdates();
}*/

                }
            }, 2000);

        } else {
            super.onBackPressed();
        }
    }

    private void openExitDialog() {

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View dialogView = inflater.inflate(R.layout.app_exit_dialog, null);


        CustomBoldTextView tv_titile = (CustomBoldTextView) dialogView.findViewById(R.id.tv_titile);
        tv_titile.setText(this.getResources().getString(R.string.app_name));

        CustomButtonView dialogButtonCancel = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonCancel);

        CustomButtonView dialogButtonOK = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonOK);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

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
            public void onClick(View view) {
                show.dismiss();
            }
        });

        dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void requestForReadExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Required Read External Storage Permission!")
                        .setMessage("To Upload Document we need read external storage permission")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 127);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create().show();
            } else if (mySharedPreferenses.getIsPermissionGranted()) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Required Read External Storage Permission!")
                        .setMessage("To Enable Read External Storage Permission click on Ok button")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE_PERMISSION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 127);
            }
            mySharedPreferenses.setIsPermissionGranted();

        } else {
            //Read External Storage Permission
            Intent i = new Intent(MainActivity.this, AcademicContributionActivity.class);
            startActivity(i);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 127 && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //permission granted
            Intent i = new Intent(MainActivity.this, AcademicContributionActivity.class);
            startActivity(i);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE_PERMISSION &&
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //Read external Storage permission ganted
            Intent i = new Intent(MainActivity.this, AcademicContributionActivity.class);
            startActivity(i);
        }
    }


//    private void prepareExpandableListViewForCommittee(){
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
//
//        // Adding Header data
//        listDataHeader.add("Committee");
//
//        // Adding child data
//        List<String> listItem = new ArrayList<String>();
//        listItem.add("Committee");
//        listItem.add("Committee Member");
//
//        listDataChild.put(listDataHeader.get(0), listItem); // Header, Child data
//
//        committeeExpandableListAdapter = new CommitteeExpandableListAdapter(this, listDataHeader, listDataChild);
//
//        // setting list adapter
//        expLvCommittee.setAdapter(committeeExpandableListAdapter);
//
//        expLvCommittee.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Log.d("onGroupClick:", "worked");
//                parent.expandGroup(groupPosition);
//                return false;
//            }
//        });
//
//    }
}
