package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.Document1_adapter_expandable;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.Document_adapter_expandable;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.Test_adapter_expandable;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.ExpandableHeightListView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ApprovePdWindowPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.DocumentPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PDAppApprovalPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PDAppPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PDDocumentPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PdAppApproveMailPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.TestPojo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PDApplicationDetail extends AppCompatActivity {


    MarshMallowPermission marshMallowPermission;

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ImageView iv_back;
    CustomBoldTextView txt_act;
    CustomBoldTextView tv_emp_code, tv_version, tv_version_code;
    RequestQueue queue;
    Activity activity;
    String ID = "", status = "";
    PDAppPojo pdAppPojo;
    public static boolean is_back_pd_app = false;
    private ImageView iv_info;
    private ImageView iv_profile;
    private RelativeLayout rel;
    private Toolbar toolbar_act;
    private CoordinatorLayout toolbarContainer;
    private ImageView iv_todays_inout;
    private CustomTextView tv_count_total_pending;
    private CustomTextView tv_total_pending_count;
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

    PDDocumentPojo pdDocumentPojo;
    Calendar myCalendar;
    Calendar myCalendar2;
    ExpandableHeightListView lv_doc, lv_doc_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdapplication_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_act);
        setSupportActionBar(toolbar);

        initView();

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                is_back_pd_app = true;
                onBackPressed();
            }
        });
        activity = PDApplicationDetail.this;
        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        txt_act.setText("PD Application Detail");
        Intent intent = getIntent();

        ID = intent.getStringExtra("ID");
        System.out.println("Id of ConferencePubApproveReject from intent ::::::::::::::::::::::::: " + ID);
        status = intent.getStringExtra("status");

        System.out.println("status of pd app ::::::::::" + status);

        if (status != null && !status.contentEquals("")) {
            if (status.compareToIgnoreCase("pending") == 0 || status.compareToIgnoreCase("Partial Approve") == 0) {
                ll_update_delete.setVisibility(View.VISIBLE);
            } else {
                ll_update_delete.setVisibility(View.GONE);
            }
        }
        if (ID != null && status != null && !ID.contentEquals("") && !status.contentEquals("")) {
            PDAppDetailAPI(ID, status);
            //  PDAppDocument(ID);
            docAPI(ID + "");
            docAPI1(ID + "");
        }


    }

    ArrayList<String> total_name = new ArrayList<>();
    List<TestPojo.DataBean> TESTPOJO_LIST = new ArrayList<>();

    private void APIComment(String id, final ExpandableHeightListView lv_expan_stud) {

        String url = URLS.Get_open_approve_pd_window_list + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&id=" + id + "";
        url = url.replace(" ", "%20");
        System.out.println("get_notification_new calls faculty   " + url + "");
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response = response + "";
                response = "{\"data\":" + response + "}";
                System.out.println("this is response !!!!!! " + response + "");
                Gson gson = new Gson();
                TestPojo testPojo = gson.fromJson(response, TestPojo.class);
                if (testPojo != null) {
                    System.out.println("not null!!!!!!!!!");
                    if (testPojo.getData() != null) {
                        System.out.println("getdata not null!!!!!!!!!");
                      /*  for (int i = 0; i < testPojo.getData().size(); i++) {
                            System.out.println("testPojo.getData().get(i).getPdar_remarks() " + testPojo.getData().get(i).getPdar_remarks() + "");
                            if (testPojo.getData().get(i).getPdar_remarks() != null && testPojo.getData().get(i).getPdar_remarks().trim().length() > 0 && testPojo.getData().get(i).getPdar_remarks().trim().compareToIgnoreCase("null") != 0) {

                                TestPojo.DataBean testPojoFINAL = new TestPojo.DataBean();
                                testPojoFINAL = testPojo.getData().get(i);
                                TESTPOJO_LIST.add(testPojoFINAL);
                                total_name.add(testPojo.getData().get(i).getPdar_remarks().trim() + "");
                            }
                        }*/
                    }
                }

                if (testPojo.getData() != null && testPojo.getData().size() > 0) {
                    Test_adapter_expandable a = new Test_adapter_expandable(PDApplicationDetail.this, testPojo);
                    lv_expan_stud.setAdapter(a);


                }

             /*   if (TESTPOJO_LIST != null && TESTPOJO_LIST.size() > 0) {
                    System.out.println("in to set adapter!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    Test_adapter_expandable a = new Test_adapter_expandable(PDApplicationDetail.this, TESTPOJO_LIST);

                    lv_expan_stud.setAdapter(a);
                }*/
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(req);
    }

    private void updateLabel1(EditText edtLeaveFromDate) {

        String myFormat1 = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
        SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
        edtLeaveFromDate.setText(sdf.format(myCalendar.getTime()) + "");
    }

    private void updateLabel2(EditText edt_LeaveToDate) {

        String myFormat1 = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat1, Locale.US);
        SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
        edt_LeaveToDate.setText(sdf.format(myCalendar2.getTime()) + " ");


    }

    public void showDialog(final Context context, final String approve_reject, final String ID, final ApprovePdWindowPojo approvePdWindowPojo) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.pd_app_approve_window, null);


        CustomBoldTextView tvtitile;
        LinearLayout llcmt;
        final CustomTextView etvcommentapproval;
        LinearLayout llcommentapproval;
        final CustomTextView tvcreditapproval;
        LinearLayout llpdcreditapproval;
        final CustomTextView tvdutyleaveapproval;
        LinearLayout lldutyleaveapproval;
        final LinearLayout llcomment;
        CardView cardviewleave;
        LinearLayout llexpdetail;
        final EditText edtPDFrameworkCredit3;
        LinearLayout llPDFrameworkCredit3;
        final EditText edtNosdutyleaves3;
        LinearLayout llNosdutyleaves3;
        final EditText edtRemarksApprove3;
        LinearLayout llRemarksApprove3;
        final LinearLayout ll3field;
        final EditText edtPDFrameworkCredit;
        LinearLayout llPDFrameworkCredit;
        final EditText edtNosdutyleaves;
        LinearLayout llNosdutyleaves;
        final EditText edtLeaveFromDate;
        LinearLayout llLeaveFromDate;
        final EditText edtRegistrationFees;
        LinearLayout llRegistrationFees;
        final EditText edtTransportation;
        LinearLayout llTransportation;
        final EditText edtAccommodation;
        LinearLayout llAccommodation;
        final EditText edtLeaveExpense;
        LinearLayout llLeaveExpense;
        final EditText edtTotalExpense;
        LinearLayout llTotalExpense;
        final EditText edtUnutilizedBudget;
        LinearLayout llUnutilizedBudget;
        final EditText edtSanctionedExpense;
        final EditText edt_LeaveToDate;
        LinearLayout llSanctionedExpense;
        final EditText edtRemarksApprove;
        final EditText edt_current_app_cost;
        LinearLayout llRemarksApprove;
        final LinearLayout ll_all_fields;
        CustomBoldTextView txtapprove;
        CustomBoldTextView tvcancel;
        LinearLayout llupdatedelete;
        RelativeLayout rl;
        EditText edt_current_app_cost_3_fields, edt_utilized_budgedt_3_fields;
        LinearLayout ll_curent_app_cost_3_fields, ll_utilized_budgedt_3_fields;
        ExpandableHeightListView lv_expan_stud;

        ImageView iv_calendar_from_date, iv_calendar_to_date;


        iv_calendar_from_date = (ImageView) dialogView.findViewById(R.id.iv_calendar_from_date);
        iv_calendar_to_date = (ImageView) dialogView.findViewById(R.id.iv_calendar_to_date);

        tvtitile = (CustomBoldTextView) dialogView.findViewById(R.id.tv_titile);
        llcmt = (LinearLayout) dialogView.findViewById(R.id.ll_cmt);
        //  etvcommentapproval = (CustomTextView) dialogView.findViewById(R.id.etv_comment_approval);
        //  llcommentapproval = (LinearLayout) dialogView.findViewById(R.id.ll_comment__approval);
        // tvcreditapproval = (CustomTextView) dialogView.findViewById(R.id.tv_credit_approval);
        //    llpdcreditapproval = (LinearLayout) dialogView.findViewById(R.id.ll_pd_credit_approval);
        // tvdutyleaveapproval = (CustomTextView) dialogView.findViewById(R.id.tv_duty_leave_approval);
        // lldutyleaveapproval = (LinearLayout) dialogView.findViewById(R.id.ll_duty_leave_approval);
        llcomment = (LinearLayout) dialogView.findViewById(R.id.ll_comment);
        cardviewleave = (CardView) dialogView.findViewById(R.id.card_view_leave);
        llexpdetail = (LinearLayout) dialogView.findViewById(R.id.ll_exp_detail);
        edtPDFrameworkCredit3 = (EditText) dialogView.findViewById(R.id.edt_PD_Framework_Credit_3);
        llPDFrameworkCredit3 = (LinearLayout) dialogView.findViewById(R.id.ll_PDFrameworkCredit_3);
        edtNosdutyleaves3 = (EditText) dialogView.findViewById(R.id.edt_Nosdutyleaves_3);
        llNosdutyleaves3 = (LinearLayout) dialogView.findViewById(R.id.ll_Nosdutyleaves_3);
        edtRemarksApprove3 = (EditText) dialogView.findViewById(R.id.edt_RemarksApprove_3);
        llRemarksApprove3 = (LinearLayout) dialogView.findViewById(R.id.ll_RemarksApprove_3);
        ll3field = (LinearLayout) dialogView.findViewById(R.id.ll_3_field);
        edtPDFrameworkCredit = (EditText) dialogView.findViewById(R.id.edt_PD_Framework_Credit);
        llPDFrameworkCredit = (LinearLayout) dialogView.findViewById(R.id.ll_PDFrameworkCredit);
        edtNosdutyleaves = (EditText) dialogView.findViewById(R.id.edt_Nosdutyleaves);
        llNosdutyleaves = (LinearLayout) dialogView.findViewById(R.id.ll_Nosdutyleaves);
        edtLeaveFromDate = (EditText) dialogView.findViewById(R.id.edt_LeaveFromDate);
        llLeaveFromDate = (LinearLayout) dialogView.findViewById(R.id.ll_LeaveFromDate);
        edtRegistrationFees = (EditText) dialogView.findViewById(R.id.edt_RegistrationFees);
        llRegistrationFees = (LinearLayout) dialogView.findViewById(R.id.ll_RegistrationFees);
        edtTransportation = (EditText) dialogView.findViewById(R.id.edt_Transportation);
        llTransportation = (LinearLayout) dialogView.findViewById(R.id.ll_Transportation);
        edtAccommodation = (EditText) dialogView.findViewById(R.id.edt_Accommodation);
        llAccommodation = (LinearLayout) dialogView.findViewById(R.id.ll_Accommodation);
        ll_all_fields = (LinearLayout) dialogView.findViewById(R.id.ll_all_fields);
        edtLeaveExpense = (EditText) dialogView.findViewById(R.id.edt_LeaveExpense);
        llLeaveExpense = (LinearLayout) dialogView.findViewById(R.id.ll_LeaveExpense);
        edtTotalExpense = (EditText) dialogView.findViewById(R.id.edt_TotalExpense);
        llTotalExpense = (LinearLayout) dialogView.findViewById(R.id.ll_TotalExpense);
        edtUnutilizedBudget = (EditText) dialogView.findViewById(R.id.edt_UnutilizedBudget);
        llUnutilizedBudget = (LinearLayout) dialogView.findViewById(R.id.ll_UnutilizedBudget);
        edtSanctionedExpense = (EditText) dialogView.findViewById(R.id.edt_SanctionedExpense);
        edt_current_app_cost = (EditText) dialogView.findViewById(R.id.edt_current_app_cost);
        edt_LeaveToDate = (EditText) dialogView.findViewById(R.id.edt_LeaveToDate);
        llSanctionedExpense = (LinearLayout) dialogView.findViewById(R.id.ll_SanctionedExpense);
        edtRemarksApprove = (EditText) dialogView.findViewById(R.id.edt_RemarksApprove);
        llRemarksApprove = (LinearLayout) dialogView.findViewById(R.id.ll_RemarksApprove);
        txtapprove = (CustomBoldTextView) dialogView.findViewById(R.id.txt_approve);
        tvcancel = (CustomBoldTextView) dialogView.findViewById(R.id.tv_cancel);
        llupdatedelete = (LinearLayout) dialogView.findViewById(R.id.ll_update_delete);
        rl = (RelativeLayout) dialogView.findViewById(R.id.rl);
        ll_curent_app_cost_3_fields = (LinearLayout) dialogView.findViewById(R.id.ll_curent_app_cost_3_fields);
        ll_utilized_budgedt_3_fields = (LinearLayout) dialogView.findViewById(R.id.ll_utilized_budgedt_3_fields);
        edt_current_app_cost_3_fields = (EditText) dialogView.findViewById(R.id.edt_current_app_cost_3_fields);
        edt_utilized_budgedt_3_fields = (EditText) dialogView.findViewById(R.id.edt_utilized_budgedt_3_fields);

        lv_expan_stud = (ExpandableHeightListView) dialogView.findViewById(R.id.lv_expan_stud);
        lv_expan_stud.setExpanded(true);
        APIComment(ID, lv_expan_stud);
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

        tvcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();

            }
        });

        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1(edtLeaveFromDate);


            }

        };
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabel2(edt_LeaveToDate);


            }

        };
        iv_calendar_from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PDApplicationDetail.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        iv_calendar_to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PDApplicationDetail.this, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /*hide show comments if comments available*/

        if (approvePdWindowPojo.getData() != null && approvePdWindowPojo.getData().size() > 0) {

            for (int i = 0; i < approvePdWindowPojo.getData().size(); i++) {
                if (approvePdWindowPojo.getData().get(i).getPdar_remarks() != null && !approvePdWindowPojo.getData().get(i).getPdar_remarks().contentEquals("")) {


                    llcomment.setVisibility(View.VISIBLE);
                    llcmt.setVisibility(View.VISIBLE);


                } else {
                    llcomment.setVisibility(View.GONE);
                    llcmt.setVisibility(View.GONE);
                }


                //System.out.println("IS VISIBLE OR NOT *********** " + approvePdWindowPojo.getData().get(0).getIs_visible());
                /*hide show fields if available*/
                if (approvePdWindowPojo.getData().get(i).getIs_visible().compareToIgnoreCase("0") == 0) {

                    ll3field.setVisibility(View.VISIBLE);
                    ll_all_fields.setVisibility(View.GONE);

                } else {

                    ll_all_fields.setVisibility(View.VISIBLE);
                    ll3field.setVisibility(View.GONE);


                }


                edtPDFrameworkCredit.setText(approvePdWindowPojo.getData().get(i).getPd_event_credit() + "");
                edtPDFrameworkCredit3.setText(approvePdWindowPojo.getData().get(i).getPd_event_credit() + "");
                edtNosdutyleaves.setText(approvePdWindowPojo.getData().get(i).getPd_no_od_leaves() + "");
                edtNosdutyleaves3.setText(approvePdWindowPojo.getData().get(i).getPd_no_od_leaves() + "");
                edtLeaveFromDate.setText(approvePdWindowPojo.getData().get(i).getPd_final_leave_from_date() + "");
                edt_LeaveToDate.setText(approvePdWindowPojo.getData().get(i).getPd_final_to_date() + "");
                edtRegistrationFees.setText(approvePdWindowPojo.getData().get(i).getPd_final_registration_fees() + "");
                edtTransportation.setText(approvePdWindowPojo.getData().get(i).getPd_final_transportation() + "");
                edtAccommodation.setText(approvePdWindowPojo.getData().get(i).getPd_final_accommodation() + "");
                edtLeaveExpense.setText(approvePdWindowPojo.getData().get(i).getPd_final_leave_expense() + "");
                edtUnutilizedBudget.setText(approvePdWindowPojo.getData().get(i).getBudget() + "");
                edt_utilized_budgedt_3_fields.setText(approvePdWindowPojo.getData().get(i).getBudget() + "");
                edtSanctionedExpense.setText(approvePdWindowPojo.getData().get(i).getPd_sanction_expense() + "");
                edt_current_app_cost_3_fields.setText(approvePdWindowPojo.getData().get(i).getPd_sanction_expense() + "");
                edt_current_app_cost.setText(approvePdWindowPojo.getData().get(i).getPd_sanction_expense() + "");


                if (edt_utilized_budgedt_3_fields.getText().toString().trim() != null && !edt_utilized_budgedt_3_fields.getText().toString().trim().contentEquals("")) {
                    String un_utilized_budget_3 = edt_utilized_budgedt_3_fields.getText().toString().trim();
                    String un_utilized_budget = edtUnutilizedBudget.getText().toString().trim();

                    double val = Double.parseDouble(un_utilized_budget_3);
                    double val1 = Double.parseDouble(un_utilized_budget);
                    if (val < 0) {
                        edt_utilized_budgedt_3_fields.setTextColor(getResources().getColor(R.color.red));
                    }

                    if (val1 < 0) {
                        edtUnutilizedBudget.setTextColor(getResources().getColor(R.color.red));
                    }
                }

            }
        }


        CalculateSum(edtRegistrationFees, edtTransportation, edtAccommodation, edtLeaveExpense, edtTotalExpense);


        edtRegistrationFees.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                CalculateSum(edtRegistrationFees, edtTransportation, edtAccommodation, edtLeaveExpense, edtTotalExpense);

                CalculateSanctionalExpense(edtRegistrationFees, edtTransportation, edtAccommodation, edtSanctionedExpense, edt_current_app_cost);

            }
        });

        edtTransportation.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                CalculateSum(edtRegistrationFees, edtTransportation, edtAccommodation, edtLeaveExpense, edtTotalExpense);

                CalculateSanctionalExpense(edtRegistrationFees, edtTransportation, edtAccommodation, edtSanctionedExpense, edt_current_app_cost);


            }
        });


        edtAccommodation.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {


            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                CalculateSum(edtRegistrationFees, edtTransportation, edtAccommodation, edtLeaveExpense, edtTotalExpense);

                CalculateSanctionalExpense(edtRegistrationFees, edtTransportation, edtAccommodation, edtSanctionedExpense, edt_current_app_cost);

            }
        });

        edtLeaveExpense.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                CalculateSum(edtRegistrationFees, edtTransportation, edtAccommodation, edtLeaveExpense, edtTotalExpense);


            }
        });


        CalculateSanctionalExpense(edtRegistrationFees, edtTransportation, edtAccommodation, edtSanctionedExpense, edt_current_app_cost);


        txtapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ll3field.getVisibility() == View.VISIBLE) {


                    if (isvalidate(edtRemarksApprove3, edtPDFrameworkCredit3, edtNosdutyleaves3)) {

                        APICallApprovePDApp(ID, 1, edtRemarksApprove3.getText().toString(), edtPDFrameworkCredit3.getText().toString(), edtNosdutyleaves3.getText().toString(), edtLeaveFromDate.getText().toString(), edt_LeaveToDate.getText().toString(), edtRegistrationFees.getText().toString(), edtTransportation.getText().toString(), edtAccommodation.getText().toString(), edtLeaveExpense.getText().toString(), edtTotalExpense.getText().toString(), show);


                    }

                }

                if (ll_all_fields.getVisibility() == View.VISIBLE) {

                    if (validateAll(edtPDFrameworkCredit, edtNosdutyleaves, edtLeaveFromDate, edt_LeaveToDate, edtRegistrationFees, edtTransportation, edtAccommodation, edtLeaveExpense, edtTotalExpense, edtUnutilizedBudget, edtSanctionedExpense, edtRemarksApprove)) {
                        APICallApprovePDApp(ID, 1, edtRemarksApprove.getText().toString(), edtPDFrameworkCredit.getText().toString(), edtNosdutyleaves.getText().toString(), edtLeaveFromDate.getText().toString(), edt_LeaveToDate.getText().toString(), edtRegistrationFees.getText().toString(), edtTransportation.getText().toString(), edtAccommodation.getText().toString(), edtLeaveExpense.getText().toString(), edtTotalExpense.getText().toString(), show);

                    }


                }

            }
        });


    }


    private boolean isvalidate(EditText edt_reason, EditText edtPDFrameworkCredit3, EditText edtNosdutyleaves3) {
        if (edt_reason.getText().toString().trim().isEmpty() || edt_reason.getText().toString().contentEquals("") || edt_reason.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(PDApplicationDetail.this, "Enter Remarks");
            return false;
        } else if (edtPDFrameworkCredit3.getText().toString().trim().isEmpty() || edtPDFrameworkCredit3.getText().toString().contentEquals("") || edtPDFrameworkCredit3.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(PDApplicationDetail.this, "Enter Framework Credit");
            return false;
        } else if (edtNosdutyleaves3.getText().toString().trim().isEmpty() || edtNosdutyleaves3.getText().toString().contentEquals("") || edtNosdutyleaves3.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(PDApplicationDetail.this, "Enter Duty Leaves");
            return false;
        }

        return true;
    }

    PDAppApprovalPojo pdAppApprovalPojo;

    private void APICallApprovePDApp(final String ID, final int is_approve, final String remarks, final String PD_credit, final String duty_leaves, final String from_date, final String to_date, final String reg_fees, final String transpotation, final String accommodation, final String leave_expense, final String total_expesne, final AlertDialog show) {

        //changed by remish to solve remark problem //-17/08/2020
//        String url = URLS.Get_PD_application_approve_or_reject + "&pd_is_approve=" + is_approve + "&ip=" + "1" + "&remarks=" + remarks + "&user_id=" + mySharedPrefereces.getUserID() + "&id=" + ID + "&pd_approve_credit=" + PD_credit + "&pd_approve_duty_leaves=" + duty_leaves + "&txtLeaveFromDate=" + from_date + "&txtLeaveToDate=" + to_date + "&txtRegistrationFees=" + reg_fees + "&txtTransportation=" + transpotation + "&txtAccommodation=" + accommodation + "&txtLeaveExpense=" + leave_expense + "&txtTotalExpense=" + total_expesne + "";
//        System.out.println("Get_PD_application_approve_or_reject URL " + url + "");

        StringRequest request = new StringRequest(Request.Method.POST, URLS.Get_PD_application_approve_or_reject1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // DialogUtils.hideProgressDialog();

                //  System.out.println("response of employee_cancel_leave_application_approve !!!!!!!!!!! " + response);
                response = response + "";
                response = "{\"Data\":" + response + "}";

                System.out.println("success response Get_PD_application_approve_or_reject !!!!!!!!!!!!!!!!!!!" + response + "");

                if (response.length() > 5) {
                    Gson gson = new Gson();
                    pdAppApprovalPojo = gson.fromJson(response, PDAppApprovalPojo.class);
                    if (pdAppApprovalPojo != null) {
                        if (pdAppApprovalPojo.getData().size() > 0) {

                            if (pdAppApprovalPojo.getData().get(0).getMsg() != null && !pdAppApprovalPojo.getData().get(0).getMsg().contentEquals("")) {

                                DialogUtils.Show_Toast(PDApplicationDetail.this, pdAppApprovalPojo.getData().get(0).getMsg());

                                ApproveMailSend(ID, show);
                            }
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDApplicationDetail.this, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", ID);
                params2.put("pd_is_approve", String.valueOf(is_approve));
                params2.put("user_id", mySharedPreferecesRKUOLD.getUserID());
                params2.put("ip", "1");
                params2.put("remarks", remarks);
                params2.put("pd_approve_credit", PD_credit);
                params2.put("pd_approve_duty_leaves", duty_leaves);
                params2.put("txtLeaveFromDate", from_date);
                params2.put("txtLeaveToDate", to_date);
                params2.put("txtRegistrationFees", reg_fees);
                params2.put("txtTransportation", transpotation);
                params2.put("txtAccommodation", accommodation);
                params2.put("txtLeaveExpense", leave_expense);
                params2.put("txtTotalExpense", total_expesne);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);


    }


    private void ApproveMailSend(String ID, final AlertDialog show) {


        String url = URLS.PD_Approved_mail + "&id=" + ID + "";
        System.out.println("PD_Approved_mail URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // DialogUtils.hideProgressDialog();

                //  System.out.println("response of employee_cancel_leave_application_approve !!!!!!!!!!! " + response);
                response = response + "";
                response = "{\"Data\":" + response + "}";

                System.out.println("success response PD_Approved_mail !!!!!!!!!!!!!!!!!!!" + response + "");

                if (response.length() > 5) {
                    Gson gson = new Gson();


                    PdAppApproveMailPojo pdAppApproveMailPojo = gson.fromJson(response, PdAppApproveMailPojo.class);

                    if (pdAppApproveMailPojo != null) {
                        if (pdAppApproveMailPojo.getData().size() > 0) {


                            show.dismiss();
                            DialogUtils.Show_Toast(PDApplicationDetail.this, pdAppApproveMailPojo.getData().get(0).getMsg() + "");

                            finish();
//
//                            PDApplication.listall = new ArrayList<>();
//                            PDApplication.listall.clear();
//                            PDApplication.pdAppPojo = new PDAppPojo();
//                            PDApplication.PDAppApproval(1, false);
//                            //AproveMailSend();
//                            Intent intent = new Intent(PDApplicationDetail.this, MainActivity.class);
//                            startActivity(intent);


                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDApplicationDetail.this, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr in pd approve mail send ::::: " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }

    private void CalculateSanctionalExpense(EditText edtRegistrationFees, EditText edtTransportation, EditText edtAccommodation, EditText edtSanctionedExpense, EditText edt_current_app_cost) {

        if (edtRegistrationFees.getText().toString().length() > 0 && !edtRegistrationFees.getText().toString().contentEquals("") && edtTransportation.getText().toString().length() > 0 && !edtTransportation.getText().toString().contentEquals("") && edtAccommodation.getText().toString().length() > 0 && !edtAccommodation.getText().toString().contentEquals("")) {


            String REG_FEES = edtRegistrationFees.getText().toString();
            String Transportation = edtTransportation.getText().toString();
            String Accommodation = edtAccommodation.getText().toString();
//                    String  LeaveExpense =edtLeaveExpense.getText().toString();

            double reg_fee = Double.parseDouble(REG_FEES);
            double transportation = Double.parseDouble(Transportation);
            double accommodation = Double.parseDouble(Accommodation);
            //int leaveexpense = Integer.parseInt(LeaveExpense);


            double sensectional_expense = reg_fee + transportation + accommodation;


            edtSanctionedExpense.setText(String.valueOf(sensectional_expense));
            edt_current_app_cost.setText(String.valueOf(sensectional_expense));


            System.out.println("SENSATIONAL EXPENSE ********************* " + sensectional_expense);


        }


    }

    private boolean validateAll(EditText edtPDFrameworkCredit, EditText edtNosdutyleaves, EditText edtLeaveFromDate, EditText edt_LeaveToDate, EditText edtRegistrationFees, EditText edtTransportation, EditText edtAccommodation, EditText edtLeaveExpense, EditText edtTotalExpense, EditText edtUnutilizedBudget, EditText edtSanctionedExpense, EditText edtRemarksApprove) {
        if (edtPDFrameworkCredit.getText().toString().trim().isEmpty() || edtPDFrameworkCredit.getText().toString().contentEquals("") || edtPDFrameworkCredit.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter Framework Credit");
            return false;
        } else if (edtNosdutyleaves.getText().toString().trim().isEmpty() || edtNosdutyleaves.getText().toString().contentEquals("") || edtNosdutyleaves.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter duty leaves");
            return false;
        } else if (edtRegistrationFees.getText().toString().trim().isEmpty() || edtRegistrationFees.getText().toString().contentEquals("") || edtRegistrationFees.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter registration fees");
            return false;
        } else if (edtTransportation.getText().toString().trim().isEmpty() || edtTransportation.getText().toString().contentEquals("") || edtTransportation.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter transportation");
            return false;
        } else if (edtAccommodation.getText().toString().trim().isEmpty() || edtAccommodation.getText().toString().contentEquals("") || edtAccommodation.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter accomodation");
            return false;
        } else if (edtLeaveExpense.getText().toString().trim().isEmpty() || edtLeaveExpense.getText().toString().contentEquals("") || edtLeaveExpense.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter leave expense");
            return false;
        } else if (edtTotalExpense.getText().toString().trim().isEmpty() || edtTotalExpense.getText().toString().contentEquals("") || edtTotalExpense.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter total expense");
            return false;
        } else if (edtSanctionedExpense.getText().toString().trim().isEmpty() || edtSanctionedExpense.getText().toString().contentEquals("") || edtSanctionedExpense.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter sanction expense");
            return false;
        } else if (edtRemarksApprove.getText().toString().trim().isEmpty() || edtRemarksApprove.getText().toString().contentEquals("") || edtRemarksApprove.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(this, "Enter remarks");
            return false;
        }

        return true;
    }

    private void CalculateSum(EditText edtRegistrationFees, EditText edtTransportation, EditText edtAccommodation, EditText edtLeaveExpense, EditText edtTotalExpense) {

        System.out.println("calculate sum :::::::::::::::::");

        if (edtRegistrationFees.getText().toString().length() > 0 && !edtRegistrationFees.getText().toString().contentEquals("") && edtTransportation.getText().toString().length() > 0 && !edtTransportation.getText().toString().contentEquals("") && edtAccommodation.getText().toString().length() > 0 && !edtAccommodation.getText().toString().contentEquals("") && edtLeaveExpense.getText().toString().length() > 0 && !edtLeaveExpense.getText().toString().contentEquals("")) {
            String REG_FEES = edtRegistrationFees.getText().toString();
            String Transportation = edtTransportation.getText().toString();
            String Accommodation = edtAccommodation.getText().toString();
            String LeaveExpense = edtLeaveExpense.getText().toString();

            double reg_fee = Double.parseDouble(REG_FEES);
            double transportation = Double.parseDouble(Transportation);
            double accommodation = Double.parseDouble(Accommodation);
            double leaveexpense = Double.parseDouble(LeaveExpense);


            double total_Expense = reg_fee + transportation + accommodation + leaveexpense;


            edtTotalExpense.setText(String.valueOf(total_Expense));
        }


    }


    private void showDialogApproval() {


    }


/*
    private void PDAppDocument(String id) {


        if (status.compareToIgnoreCase("Pending") == 0) {
            status = "1";
        } else {
            status = "2";
        }
        queue = Volley.newRequestQueue(PDApplicationDetail.this);

        String url = URLS.Get_PD_Application_document_list + "&id=" + id + "";

        url = url.replace(" ", "%20");
        System.out.println("Get_FDP_Attended_list_Document URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();

                //System.out.println("response of Get_leave_approve_list !!!!!!!!!!! " + response);
                response = response + "";

                System.out.println("Get_PD_Application_document_list response size in leave listing :::::::::::::::::::::::::::::::::::::: " + response.length());
                if (response.length() > 10) {
                    response = "{\"Data\":" + response + "}";


                    // System.out.println("sucess response v !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();

                    pdDocumentPojo = gson.fromJson(response, PDDocumentPojo.class);


                    if (pdDocumentPojo != null) {
                        if (pdDocumentPojo.getData() != null) {
                            if (pdDocumentPojo.getData().get(0) != null) {
                                if (pdDocumentPojo.getData().size() > 0) {
                                    ImageView imageView = null;
                                    String url = "";

                                    // fdpDocumentPojo.getData().size() = 3;
                                    for (int i = 0; i < pdDocumentPojo.getData().size(); i++) {
                                        LinearLayout ll = new LinearLayout(PDApplicationDetail.this);
                                        LinearLayout.LayoutParams params_main = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        params_main.setMargins(0, 20, 0, 0);
                                        ll.setPadding(10, 10, 10, 10);
                                        ll.setLayoutParams(params_main);
                                        ll.setBackground(getResources().getDrawable(R.drawable.add_leave_border));
                                        ll.setOrientation(LinearLayout.HORIZONTAL);

                                        ll.setWeightSum(2);
                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.8f);
                                        LinearLayout.LayoutParams params_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.2f);

                                        TextView customBoldTextView = new TextView(PDApplicationDetail.this);
                                        customBoldTextView.setPadding(15, 15, 15, 15);
                                        customBoldTextView.setLayoutParams(params);
//                                        customBoldTextView.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
//                                        customBoldTextView.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                                        customBoldTextView.setTextSize(17);
                                        customBoldTextView.setTextColor(getResources().getColor(R.color.black));
                                        customBoldTextView.setText(pdDocumentPojo.getData().get(i).getTitle() + "");

                                        imageView = new ImageView(PDApplicationDetail.this);
                                        imageView.setPadding(15, 15, 15, 15);
                                        imageView.setLayoutParams(params_image);
                                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.download_salary_slip));

                                        ll.addView(customBoldTextView);
                                        ll.addView(imageView);
                                        ll_main_doc_file.addView(ll);

                                        if (pdDocumentPojo.getData().get(i).getDownload_Document() != null && !pdDocumentPojo.getData().get(i).getDownload_Document().contentEquals("")) {
                                            url = pdDocumentPojo.getData().get(i).getDownload_Document() + "";
                                        }


                                        String finalUrl = url;
                                        if (finalUrl.contains(" ")) {
                                            finalUrl = finalUrl.replace(" ", "%20");
                                        } else {
                                            finalUrl = finalUrl;
                                        }

                                        final String urll = finalUrl;
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                System.out.println("URL OF DOCUMENT *********************" + urll);

                                                String url = urll;
                                                if (url != null && url.length() > 5) {
                                                    String extention = url.substring(url.lastIndexOf("."), url.length());
                                                    //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
                                                    new DownloadFileFromURL(extention).execute(url);
                                                } else {
                                                    DialogUtils.Show_Toast(PDApplicationDetail.this, "No Documents Available");
                                                }

                                            }
                                        });
                                    }


                                } else {

                                    System.out.println("else  calll ################");
                                    DialogUtils.Show_Toast(PDApplicationDetail.this, "No Records Found");
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
                DialogUtils.Show_Toast(PDApplicationDetail.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }*/

    @Override
    public void onBackPressed() {
        is_back_pd_app = true;
        super.onBackPressed();
    }

    private void PDAppDetailAPI(String id, String status) {
        if (status.compareToIgnoreCase("Pending") == 0 || status.compareToIgnoreCase("Partial Approve") == 0) {
            status = "1";
        } else {
            status = "2";
        }
        queue = Volley.newRequestQueue(PDApplicationDetail.this);

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
                                    edt_Total_Expense.setText(pdAppPojo.getData().get(0).getLeave_From_Date() + "");
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

                                    System.out.println("else  calll ################");
                                    DialogUtils.Show_Toast(PDApplicationDetail.this, "No Records Found");
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
                DialogUtils.Show_Toast(PDApplicationDetail.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }


    DocumentPojo documentPojo = new DocumentPojo();

    void docAPI(String id) {

        DialogUtils.showProgressDialog(PDApplicationDetail.this, "");
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
                                        Document_adapter_expandable document_adapter_expandable = new Document_adapter_expandable(PDApplicationDetail.this, documentPojo, new Document_adapter_expandable.On_Download() {
                                            @Override
                                            public void on_download_click(String download_link) {

                                                String url = download_link;
                                                if (url.contains(" ")) {
                                                    url = url.replace(" ", "%20");
                                                }


                                                System.out.println("download pd doc !!!!!!!!!!!" + download_link);

                                                marshMallowPermission = new MarshMallowPermission(PDApplicationDetail.this);

                                                if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                                                    marshMallowPermission.requestPermissionForExternalStorage();
                                                } else {
                                                    if (url != null && url.length() > 5) {
                                                        String extention = url.substring(url.lastIndexOf("."), url.length());
                                                        //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
                                                        new DownloadFileFromURL(extention).execute(url);
                                                    } else {
                                                        DialogUtils.Show_Toast(PDApplicationDetail.this, "No Documents Available");
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
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Rku/" + "/PDApp/");
                dir.mkdirs();


                System.out.println("path of file>>>>>" + dir.getAbsolutePath());
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();
                //  Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());


                OutputStream output = new FileOutputStream("sdcard/Rku/" + "/PDApp/" + nameoffile);

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

            DialogUtils.Show_Toast(PDApplicationDetail.this, "Download Completed Successfully");

            System.out.println("EXTENSION OF FILE onPostExecute::::::::::" + Extenson);

            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Rku/" + "/PDApp/" + nameoffile);
            Log.d("pathoffile", String.valueOf(file11));
//                                    Uri uri = FileProvider.getUriForFile(AssignmentActivity.this, "com.infinity.infoway.atmiya.activity.AssignmentActivity.fileprovider", file11);
//

            /// for  opening downloaded documentssssssssssss
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(PDApplicationDetail.this, PDApplicationDetail.this.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                PDApplicationDetail.this.grantUriPermission(PDApplicationDetail.this.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

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
                    DialogUtils.Show_Toast(PDApplicationDetail.this, "No Apps can performs This action");
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
                    PDApplicationDetail.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(PDApplicationDetail.this, "No Apps can performs This action");

                }

            }


        }

    }

    DocumentPojo documentPojo1 = new DocumentPojo();

    void docAPI1(String id) {

        //DialogUtils.showProgressDialog(PDAppDocumentApprovalActivity.this, "");
        String url;
        url = URLS.Get_PD_Application_brochures_document_list + id + "";
        System.out.println("urllllllllllllll " + url);
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
                                        Document1_adapter_expandable document_adapter_expandable = new Document1_adapter_expandable(PDApplicationDetail.this, documentPojo1, new Document1_adapter_expandable.On_Download() {
                                            @Override
                                            public void on_download_click(String download_link) {
                                                marshMallowPermission = new MarshMallowPermission(PDApplicationDetail.this);
                                                String url = download_link;

                                                System.out.println("PD DOCUMEMT :::::::::: " + url);
                                                if (url.contains(" ")) {
                                                    url = url.replace(" ", "%20");
                                                }
                                                if (!marshMallowPermission.checkPermissionForExternalStorage()) {
                                                    marshMallowPermission.requestPermissionForExternalStorage();
                                                } else {
                                                    if (url != null && url.length() > 5) {
                                                        String extention = url.substring(url.lastIndexOf("."), url.length());
                                                        //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
                                                        new DownloadFileFromURL(extention).execute(url);
                                                    } else {
                                                        DialogUtils.Show_Toast(PDApplicationDetail.this, "No Documents Available");
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

    private void initView() {

        lv_doc = (ExpandableHeightListView) findViewById(R.id.lv_doc);
        lv_doc_1 = (ExpandableHeightListView) findViewById(R.id.lv_doc_1);
        lv_doc.setExpanded(true);
        lv_doc_1.setExpanded(true);

        myCalendar = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        queue = Volley.newRequestQueue(this);
        marshMallowPermission = new MarshMallowPermission(activity);
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
                ApproveWindowAPI(ID, "Approve");

            }
        });
        tv_delete = (CustomBoldTextView) findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApproveWindowAPI(ID, "Reject");


            }
        });
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
        //  iv_file = (ImageView) findViewById(R.id.iv_file);
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
    }

    private void showDialogReject(final Context context, String approve_rej, final String ID, ApprovePdWindowPojo approvePdWindowPojo) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.reject_leave_window_pd_app, null);

        //  final EditText etv_comment_approval = (EditText) dialogView.findViewById(R.id.etv_comment_approval);
//        final EditText tv_credit_approval = (EditText) dialogView.findViewById(R.id.tv_credit_approval);
//        final EditText tv_duty_leave_approval = (EditText) dialogView.findViewById(R.id.tv_duty_leave_approval);
        final EditText edt_reason_reject = (EditText) dialogView.findViewById(R.id.edt_reason_reject);
        ExpandableHeightListView lv_expan_stud_for_reject = (ExpandableHeightListView) dialogView.findViewById(R.id.lv_expan_stud_for_reject);
        lv_expan_stud_for_reject.setExpanded(true);
        // LinearLayout ll_comment = (LinearLayout) dialogView.findViewById(R.id.ll_comment);
        LinearLayout ll_cmt = (LinearLayout) dialogView.findViewById(R.id.ll_cmt);


        if (approvePdWindowPojo.getData() != null && approvePdWindowPojo.getData().size() > 0) {

            for (int i = 0; i < approvePdWindowPojo.getData().size(); i++) {
                if (approvePdWindowPojo.getData().get(i).getPdar_remarks() != null && !approvePdWindowPojo.getData().get(i).getPdar_remarks().contentEquals("")) {

                    ll_cmt.setVisibility(View.VISIBLE);


                    APIComment(ID, lv_expan_stud_for_reject);
                } else {
                    ll_cmt.setVisibility(View.GONE);
                }
            }


        }



    /*    if (approvePdWindowPojo.getData().get(0).getPdar_remarks() != null && !approvePdWindowPojo.getData().get(0).getPdar_remarks().contentEquals("")) {

            ll_cmt.setVisibility(View.VISIBLE);
            //   ll_comment.setVisibility(View.VISIBLE);


            // etv_comment_approval.setText(approvePdWindowPojo.getData().get(pos).getPdar_remarks() + "");
//            tv_credit_approval.setText(approvePdWindowPojo.getData().get(pos).getPdar_credit() + "");
//            tv_duty_leave_approval.setText(approvePdWindowPojo.getData().get(pos).getPdar_duty_leaves() + "");

            APIComment(ID, lv_expan_stud_for_reject);
        } else {
            ll_cmt.setVisibility(View.GONE);
            // ll_comment.setVisibility(View.GONE);
        }
*/

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

        CustomBoldTextView txt_reject = (CustomBoldTextView) dialogView.findViewById(R.id.txt_reject);
        CustomBoldTextView tv_cancel = (CustomBoldTextView) dialogView.findViewById(R.id.tv_cancel);


        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        txt_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edt_reason_reject.getText().toString().contentEquals("") && edt_reason_reject.getText().toString().length() > 0) {


                    RejectAPICall("2", ID, show);


                } else {
                    DialogUtils.Show_Toast(context, "Enter Reason of Reject");
                }

            }
        });


    }


    private void RejectAPICall(String is_approve, final String ID, final AlertDialog show) {

        String url = URLS.Get_PD_application_approve_or_reject + "&pd_is_approve=" + is_approve + "&ip=" + "1" + "&remarks=" + "" + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&id=" + ID + "&pd_approve_credit=" + "" + "&pd_approve_duty_leaves=" + "" + "&txtLeaveFromDate=" + "" + "&txtLeaveToDate=" + "" + "&txtRegistrationFees=" + "" + "&txtTransportation=" + "" + "&txtAccommodation=" + "" + "&txtLeaveExpense=" + "" + "&txtTotalExpense=" + "" + "";
        System.out.println("Get_PD_application_approve_or_reject URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // DialogUtils.hideProgressDialog();

                //  System.out.println("response of employee_cancel_leave_application_approve !!!!!!!!!!! " + response);
                response = response + "";
                response = "{\"Data\":" + response + "}";

                System.out.println("success response Get_PD_application_approve_or_reject !!!!!!!!!!!!!!!!!!!" + response + "");

                if (response.length() > 5) {
                    Gson gson = new Gson();


                    pdAppApprovalPojo = gson.fromJson(response, PDAppApprovalPojo.class);

                    if (pdAppApprovalPojo != null) {
                        if (pdAppApprovalPojo.getData().size() > 0) {

                            if (pdAppApprovalPojo.getData().get(0).getMsg() != null && !pdAppApprovalPojo.getData().get(0).getMsg().contentEquals("")) {


                                DialogUtils.Show_Toast(PDApplicationDetail.this, pdAppApprovalPojo.getData().get(0).getMsg());

                                RejectMailSend(ID, show);
                            }


                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDApplicationDetail.this, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void RejectMailSend(String ID, final AlertDialog show) {

        String url = URLS.PD_Rejected_mail + "&id=" + ID + "";
        System.out.println("PD_Rejected_mail URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // DialogUtils.hideProgressDialog();

                //  System.out.println("response of employee_cancel_leave_application_approve !!!!!!!!!!! " + response);
                response = response + "";
                response = "{\"Data\":" + response + "}";

                System.out.println("success response PD_Approved_mail !!!!!!!!!!!!!!!!!!!" + response + "");

                if (response.length() > 5) {
                    Gson gson = new Gson();


                    PdAppApproveMailPojo pdAppApproveMailPojo = gson.fromJson(response, PdAppApproveMailPojo.class);

                    if (pdAppApproveMailPojo != null) {
                        if (pdAppApproveMailPojo.getData().size() > 0) {


                            show.dismiss();
                            DialogUtils.Show_Toast(PDApplicationDetail.this, pdAppApproveMailPojo.getData().get(0).getMsg() + "");
                            finish();

                   /*         PDApplication.listall = new ArrayList<>();
                            PDApplication.listall.clear();
                            PDApplication.pdAppPojo = new PDAppPojo();
                            PDApplication.PDAppApproval(1, false);
                            //AproveMailSend();
                            Intent intent = new Intent(PDApplicationDetail.this, MainActivity.class);
                            startActivity(intent);*/

                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDApplicationDetail.this, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }

    private void ApproveWindowAPI(final String ID, final String approve) {
        String url = URLS.Get_open_approve_pd_window_list + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&id=" + ID + "";
        System.out.println("Get_open_approve_pd_window_list URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // DialogUtils.hideProgressDialog();

                //  System.out.println("response of employee_cancel_leave_application_approve !!!!!!!!!!! " + response);
                response = response + "";
                response = "{\"Data\":" + response + "}";

                System.out.println("success response employee_leave_application_reject_mail !!!!!!!!!!!!!!!!!!!" + response + "");

                if (response.length() > 5) {
                    Gson gson = new Gson();


                    ApprovePdWindowPojo approvePdWindowPojo = gson.fromJson(response, ApprovePdWindowPojo.class);

                    if (approvePdWindowPojo != null) {
                        if (approvePdWindowPojo.getData().size() > 0) {


                            if (approve.compareToIgnoreCase("Approve") == 0) {
                                showDialog(PDApplicationDetail.this, approve, ID, approvePdWindowPojo);
                            }

                            if (approve.compareToIgnoreCase("Reject") == 0) {
                                showDialogReject(PDApplicationDetail.this, approve, ID, approvePdWindowPojo);
                            }


                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDApplicationDetail.this, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);


    }

}
