package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.SystemClock;
import androidx.cardview.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.MainActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PDApplication;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PDApplicationDetail;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.ExpandableHeightListView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ApprovePdWindowPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PDAppApprovalPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PDAppPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PdAppApproveMailPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.TestPojo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class PDAdapter extends BaseSwipeAdapter {


    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;

    PDAppPojo pdAppPojo;
    List<PDAppPojo.DataBean> listall;
    RequestQueue queue;
    Boolean Isc = true;
    private long lastClickTime = 0;

    SpinnerSimpleAdapter spinnerSimpleAdapter;
    Calendar myCalendar;
    Calendar myCalendar2;

    public PDAdapter(Context ctx, PDAppPojo pdAppPojo, List<PDAppPojo.DataBean> listall, Boolean Isc) {
        this.ctx = ctx;
        this.pdAppPojo = pdAppPojo;
        this.listall = listall;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        queue = Volley.newRequestQueue(ctx);

        myCalendar = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();
    }

    class ViewHolder {
        LinearLayout ll_main_approve_headder_cancel, ll_cancel_header, ll_approve_cancel_leave, ll_pending_cancel_leave, main_ll;
    }

    ConfarancePubAdapter.ViewHolder viewHolder;

    @Override
    public int getCount() {
        // return college.getTable().size();

        return listall.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        //  if (Isc) {
        return R.id.swipe;
        //  } else {
        //  return 0;
        // }
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.adapter_pd, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe);

        if (Isc) {
            swipeLayout.setSwipeEnabled(false);
        }
        TextView tv_emp_name, tv_status, tv_event_name;

        ImageView iv_approve, iv_reject;
        LinearLayout ll_main_approve_headder_cancel, ll_cancel_header, ll_approve_cancel_leave, ll_pending_cancel_leave, main_ll;


        iv_approve = (ImageView) convertView.findViewById(R.id.iv_approve);
        iv_reject = (ImageView) convertView.findViewById(R.id.iv_reject);
        tv_emp_name = (TextView) convertView.findViewById(R.id.tv_emp_name);
        tv_status = (TextView) convertView.findViewById(R.id.tv_status);
        tv_event_name = (TextView) convertView.findViewById(R.id.tv_event_name);

        ll_main_approve_headder_cancel = (LinearLayout) convertView.findViewById(R.id.ll_main_approve_headder_cancel);
        ll_cancel_header = (LinearLayout) convertView.findViewById(R.id.ll_cancel_header);
        ll_approve_cancel_leave = (LinearLayout) convertView.findViewById(R.id.ll_approve_cancel_leave);
        ll_pending_cancel_leave = (LinearLayout) convertView.findViewById(R.id.ll_pending_cancel_leave);
        main_ll = (LinearLayout) convertView.findViewById(R.id.main_ll);

        if (position % 2 == 0) {
            main_ll.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        }

        iv_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ApproveWindowAPI(position, pdAppPojo.getData().get(position).getId(), "Approve");
            }
        });

        iv_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApproveWindowAPI(position, pdAppPojo.getData().get(position).getId(), "Reject");

            }
        });

        ll_pending_cancel_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(ctx, PDApplicationDetail.class);
                intent.putExtra("ID", listall.get(position).getId());
                intent.putExtra("status", listall.get(position).getStatus());
//                Intent intent = new Intent(ctx, LeaveApprovalActivity.class);
                ctx.startActivity(intent);
            }
        });
        ll_approve_cancel_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime < URLS.TIME_TILL_DISABLE_BTN) {
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(ctx, PDApplicationDetail.class);
                intent.putExtra("status", listall.get(position).getStatus());
                intent.putExtra("ID", listall.get(position).getId());
                ctx.startActivity(intent);
            }
        });

      /*  String approve_by = listall.get(position).getApprovedby()+"";
        tv_emp_name.setText(listall.get(position).getEla_emp_name() + "");
        if (approve_by==null || approve_by.compareToIgnoreCase("null")==0)
        {
            approve_by ="";
        }*/


        if (listall.get(position).getApproval_no().contentEquals("1")) {
            tv_emp_name.setTextColor(ctx.getResources().getColor(R.color.row_color_green));
            tv_status.setTextColor(ctx.getResources().getColor(R.color.black));
            tv_event_name.setTextColor(ctx.getResources().getColor(R.color.black));


        } else {
            tv_emp_name.setTextColor(ctx.getResources().getColor(R.color.text));
            tv_status.setTextColor(ctx.getResources().getColor(R.color.text));
            tv_event_name.setTextColor(ctx.getResources().getColor(R.color.text));
        }
        tv_emp_name.setText(listall.get(position).getEmployee_Name() + "");
        tv_status.setText(listall.get(position).getStatus());
        tv_event_name.setText(listall.get(position).getPd_event_name() + "");


    }

    ArrayList<String> total_name = new ArrayList<>();
    List<TestPojo.DataBean> TESTPOJO_LIST = new ArrayList<>();

    private void APIComment(String id, final ExpandableHeightListView lv_expan_stud) {

        String url = URLS.Get_open_approve_pd_window_list + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&id=" + id + "";
        url = url.replace(" ", "%20");
        System.out.println("Get_open_approve_pd_window_list   " + url + "");
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
                        /*for (int i = 0; i < testPojo.getData().size(); i++)
                        {
                            System.out.println("testPojo.getData().get(i).getPdar_remarks() " + testPojo.getData().get(i).getPdar_remarks() + "");
                            if (testPojo.getData().get(i).getPdar_remarks() != null && testPojo.getData().get(i).getPdar_remarks().trim().length() > 0 && testPojo.getData().get(i).getPdar_remarks().trim().compareToIgnoreCase("null") != 0)
                            {

                                TestPojo.DataBean testPojoFINAL = new TestPojo.DataBean();
//                                testPojoFINAL = testPojo.getData().get(i);
                                testPojoFINAL = testPojo.getData().get(i);
                                TESTPOJO_LIST.add(testPojoFINAL);
                                total_name.add(testPojo.getData().get(i).getPdar_remarks().trim() + "");
                            }
                        }*/
                    }
                }
               /* if (TESTPOJO_LIST != null && TESTPOJO_LIST.size() > 0) {
                    System.out.println("in to set adapter!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    Test_adapter_expandable a = new Test_adapter_expandable(ctx, TESTPOJO_LIST);

                    lv_expan_stud.setAdapter(a);
                }*/

                if (testPojo.getData() != null && testPojo.getData().size() > 0) {
                    Test_adapter_expandable a = new Test_adapter_expandable(ctx, testPojo);
                    lv_expan_stud.setAdapter(a);


                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(req);
    }

    public void showDialog(final Context context, final int pos, final String approve_reject, final String ID, final ApprovePdWindowPojo approvePdWindowPojo) {

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
        final EditText edt_current_app_cost;
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
        LinearLayout llRemarksApprove;
        final LinearLayout ll_all_fields;
        CustomBoldTextView txtapprove;
        CustomBoldTextView tvcancel;
        LinearLayout llupdatedelete;
        RelativeLayout rl;
        ExpandableHeightListView lv_expan_stud;
        EditText edt_current_app_cost_3_fields, edt_utilized_budgedt_3_fields;
        LinearLayout ll_curent_app_cost_3_fields, ll_utilized_budgedt_3_fields;
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
        //lldutyleaveapproval = (LinearLayout) dialogView.findViewById(R.id.ll_duty_leave_approval);
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
        edt_current_app_cost = (EditText) dialogView.findViewById(R.id.edt_current_app_cost);
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
       /* WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(show.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        show.show();
        show.getWindow().setAttributes(lp);*/
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
                new DatePickerDialog(ctx, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        iv_calendar_to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ctx, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /*hide show comments if comments available*/


        if (approvePdWindowPojo.getData() != null && approvePdWindowPojo.getData().size() > 0)
        {

            for (int i = 0; i < approvePdWindowPojo.getData().size(); i++)
            {

                if (approvePdWindowPojo.getData().get(i).getPdar_remarks() != null && !approvePdWindowPojo.getData().get(i).getPdar_remarks().contentEquals(""))
                {


                    llcomment.setVisibility(View.VISIBLE);
                    llcmt.setVisibility(View.VISIBLE);


                } else {
                    llcomment.setVisibility(View.GONE);
                    llcmt.setVisibility(View.GONE);
                }
                if (approvePdWindowPojo.getData().get(i).getIs_visible().compareToIgnoreCase("0") == 0) {

                    ll3field.setVisibility(View.VISIBLE);
//            ll_all_fields.setVisibility(View.VISIBLE);
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
                edtSanctionedExpense.setText(approvePdWindowPojo.getData().get(i).getPd_sanction_expense() + "");
                edt_utilized_budgedt_3_fields.setText(approvePdWindowPojo.getData().get(i).getBudget() + "");
                edt_current_app_cost_3_fields.setText(approvePdWindowPojo.getData().get(i).getPd_sanction_expense()+"");
                edt_current_app_cost.setText(approvePdWindowPojo.getData().get(i).getPd_sanction_expense()+"");


                if (edt_utilized_budgedt_3_fields.getText().toString().trim() != null && !edt_utilized_budgedt_3_fields.getText().toString().trim().contentEquals("")) {
                    String un_utilized_budget_3 = edt_utilized_budgedt_3_fields.getText().toString().trim();
                    String un_utilized_budget = edtUnutilizedBudget.getText().toString().trim();

                    double val = Double.parseDouble(un_utilized_budget_3);
                    double val1 = Double.parseDouble(un_utilized_budget);
                    if (val < 0) {
                        edt_utilized_budgedt_3_fields.setTextColor(ctx.getResources().getColor(R.color.red));
                    }

                    if (val1 < 0) {
                        edtUnutilizedBudget.setTextColor(ctx.getResources().getColor(R.color.red));
                    }
                }


            }


        }


        //System.out.println("IS VISIBLE OR NOT *********** " + approvePdWindowPojo.getData().get(pos).getIs_visible());
        /*hide show fields if available*/


        // etvcommentapproval.setText(approvePdWindowPojo.getData().get(pos).getPdar_remarks() + "");
//        tvcreditapproval.setText(approvePdWindowPojo.getData().get(pos).getPdar_credit() + "");
        //  tvdutyleaveapproval.setText(approvePdWindowPojo.getData().get(pos).getPdar_duty_leaves() + "");


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

    PDAppApprovalPojo pdAppApprovalPojo;

    private void APICallApprovePDApp(final String ID, int is_approve, String remarks, String PD_credit, String duty_leaves, String from_date, String to_date, String reg_fees, String transpotation, String accommodation, String leave_expense, String total_expesne, final AlertDialog show) {

        String url = URLS.Get_PD_application_approve_or_reject + "&pd_is_approve=" + is_approve + "&ip=" + "1" + "&remarks=" + remarks + "&user_id=" + mySharedPreferecesRKUOLD.getUserID() + "&id=" + ID + "&pd_approve_credit=" + PD_credit + "&pd_approve_duty_leaves=" + duty_leaves + "&txtLeaveFromDate=" + from_date + "&txtLeaveToDate=" + to_date + "&txtRegistrationFees=" + reg_fees + "&txtTransportation=" + transpotation + "&txtAccommodation=" + accommodation + "&txtLeaveExpense=" + leave_expense + "&txtTotalExpense=" + total_expesne + "";
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


                                DialogUtils.Show_Toast(ctx, pdAppApprovalPojo.getData().get(0).getMsg());

                                ApproveMailSend(ID, show);
                            }


                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ctx, "Please Try Again Later");
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
                            DialogUtils.Show_Toast(ctx, pdAppApproveMailPojo.getData().get(0).getMsg() + "");


                            PDApplication.listall = new ArrayList<>();
                            PDApplication.listall.clear();
                            PDApplication.pdAppPojo = new PDAppPojo();
                            PDApplication.PDAppApproval(1, false);
                            //AproveMailSend();
                            Intent intent = new Intent(ctx, MainActivity.class);
                            ctx.startActivity(intent);

                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ctx, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
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
                            DialogUtils.Show_Toast(ctx, pdAppApproveMailPojo.getData().get(0).getMsg() + "");


                            PDApplication.listall = new ArrayList<>();
                            PDApplication.listall.clear();
                            PDApplication.pdAppPojo = new PDAppPojo();
                            PDApplication.PDAppApproval(1, false);
                            //AproveMailSend();
                            Intent intent = new Intent(ctx, MainActivity.class);
                            ctx.startActivity(intent);


                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ctx, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }


    private void ApproveWindowAPI(final int pos, final String ID, final String approve) {
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
                                showDialog(ctx, pos, approve, ID, approvePdWindowPojo);
                            }

                            if (approve.compareToIgnoreCase("Reject") == 0) {
                                showDialogReject(ctx, pos, approve, ID, approvePdWindowPojo);
                            }


                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ctx, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);


    }

    private void showDialogReject(final Context context, final int pos, String approve_rej, String ID, ApprovePdWindowPojo approvePdWindowPojo) {

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


                    APIComment(pdAppPojo.getData().get(pos).getId(), lv_expan_stud_for_reject);
                } else {
                    ll_cmt.setVisibility(View.GONE);
                }
            }


        }


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


                    RejectAPICall("2", pdAppPojo.getData().get(pos).getId(), show);


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


                                DialogUtils.Show_Toast(ctx, pdAppApprovalPojo.getData().get(0).getMsg());

                                RejectMailSend(ID, show);
                            }


                        }
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ctx, "Please Try Again Later");
                // DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }


    private boolean isvalidate(EditText edt_reason, EditText edtPDFrameworkCredit3, EditText edtNosdutyleaves3) {
        if (edt_reason.getText().toString().trim().isEmpty() || edt_reason.getText().toString().contentEquals("") || edt_reason.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter Remarks");
            return false;
        } else if (edtPDFrameworkCredit3.getText().toString().trim().isEmpty() || edtPDFrameworkCredit3.getText().toString().contentEquals("") || edtPDFrameworkCredit3.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter Framework Credit");
            return false;
        } else if (edtNosdutyleaves3.getText().toString().trim().isEmpty() || edtNosdutyleaves3.getText().toString().contentEquals("") || edtNosdutyleaves3.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter Duty Leaves");
            return false;
        }

        return true;
    }


    private boolean validateAll(EditText edtPDFrameworkCredit, EditText edtNosdutyleaves, EditText edtLeaveFromDate, EditText edt_LeaveToDate, EditText edtRegistrationFees, EditText edtTransportation, EditText edtAccommodation, EditText edtLeaveExpense, EditText edtTotalExpense, EditText edtUnutilizedBudget, EditText edtSanctionedExpense, EditText edtRemarksApprove) {
        if (edtPDFrameworkCredit.getText().toString().trim().isEmpty() || edtPDFrameworkCredit.getText().toString().contentEquals("") || edtPDFrameworkCredit.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter Framework Credit");
            return false;
        } else if (edtNosdutyleaves.getText().toString().trim().isEmpty() || edtNosdutyleaves.getText().toString().contentEquals("") || edtNosdutyleaves.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter duty leaves");
            return false;
        } else if (edtRegistrationFees.getText().toString().trim().isEmpty() || edtRegistrationFees.getText().toString().contentEquals("") || edtRegistrationFees.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter registration fees");
            return false;
        } else if (edtTransportation.getText().toString().trim().isEmpty() || edtTransportation.getText().toString().contentEquals("") || edtTransportation.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter transportation");
            return false;
        } else if (edtAccommodation.getText().toString().trim().isEmpty() || edtAccommodation.getText().toString().contentEquals("") || edtAccommodation.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter accomodation");
            return false;
        } else if (edtLeaveExpense.getText().toString().trim().isEmpty() || edtLeaveExpense.getText().toString().contentEquals("") || edtLeaveExpense.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter leave expense");
            return false;
        } else if (edtTotalExpense.getText().toString().trim().isEmpty() || edtTotalExpense.getText().toString().contentEquals("") || edtTotalExpense.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter total expense");
            return false;
        } else if (edtSanctionedExpense.getText().toString().trim().isEmpty() || edtSanctionedExpense.getText().toString().contentEquals("") || edtSanctionedExpense.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter sanction expense");
            return false;
        } else if (edtRemarksApprove.getText().toString().trim().isEmpty() || edtRemarksApprove.getText().toString().contentEquals("") || edtRemarksApprove.getText().toString().length() < 0) {
            DialogUtils.Show_Toast(ctx, "Enter remarks");
            return false;
        }

        return true;
    }


}
