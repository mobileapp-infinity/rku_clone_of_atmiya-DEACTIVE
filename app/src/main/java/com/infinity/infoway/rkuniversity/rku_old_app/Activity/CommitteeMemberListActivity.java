package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.CommetteeMemberListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetCommitteeWiseMemberListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReportListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


public class CommitteeMemberListActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    FloatingActionButton fabAddCommitteeMemberDetails;
    RequestQueue queue;
    ListView lv_committee_member;
    List<GetCommitteeWiseMemberListPojo.DataBean> committeeMemberList = new ArrayList<>();
    CommetteeMemberListAdapter commetteeMemberListAdapter;

    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentPageNo = 1;

    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(CommitteeMemberListActivity.this)
                .anchorView(view)
                .gravity(Gravity.BOTTOM)
                .modal(true)
                .arrowColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .text(getString(R.string.app_name))
                .contentView(R.layout.tooltip_edit_committee_member)
                .build();
        tooltip.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_member_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getReportListApi(mySharedPreferecesRKUOLD.getUserID());
        callGetCommitteeMemberListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1);


        lv_committee_member.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((hasMoreData) && (isScrolling) && (firstVisibleItem + visibleItemCount == totalItemCount)) {
                    isScrolling = false;
                    currentPageNo = currentPageNo + 1;

                    callGetCommitteeMemberListAPI("0", mySharedPreferecesRKUOLD.getUserID(), currentPageNo);

                }
            }
        });

    }

    private void getReportListApi(final String User_id){
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Report_right, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    ReportListPojo reportListPojo = gson.fromJson("{\"Data\":" + response + "}", ReportListPojo.class);
                    if (reportListPojo.getData() != null && reportListPojo.getData().size() > 0) {
                        ArrayList<ReportListPojo.DataBean> dataBeanArrayList = (ArrayList<ReportListPojo.DataBean>) reportListPojo.getData();
                        ArrayList<String> reportList = new ArrayList<>();
                        for (int i = 0 ; i <dataBeanArrayList.size();i++){
                            if (!TextUtils.isEmpty(dataBeanArrayList.get(i).getMen_URL().trim())){
                                reportList.add(dataBeanArrayList.get(i).getMen_URL().trim());
                            }
                        }
                        if (reportList.contains("list_hrhr_committee_wise_member_mst.aspx")){
                            fabAddCommitteeMemberDetails.setVisibility(View.VISIBLE);
                        }else {
                            fabAddCommitteeMemberDetails.setVisibility(View.GONE);
                        }
                    } else {
                        fabAddCommitteeMemberDetails.setVisibility(View.GONE);
                    }
                } else {
                    fabAddCommitteeMemberDetails.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                fabAddCommitteeMemberDetails.setVisibility(View.GONE);
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


    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Committee Member");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);

        lv_committee_member = findViewById(R.id.lv_committee_member);

        fabAddCommitteeMemberDetails = findViewById(R.id.fabAddCommitteeMemberDetails);
        fabAddCommitteeMemberDetails.setOnClickListener(this);

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
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.fabAddCommitteeMemberDetails) {
            Intent intent = new Intent(CommitteeMemberListActivity.this, CommitteeMemberActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_ADD_COMMITTEE_MEMBER);
        } else if (view.getId() == R.id.iv_info) {
            showTooltip(view);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_ADD_COMMITTEE_MEMBER && resultCode == RESULT_OK) {
            resetListData();
            callGetCommitteeMemberListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1);
        } else if (requestCode == IntentConstants.REQUEST_CODE_FOR_EDIT_COMMITTEE_MEMBER && resultCode == RESULT_OK) {
            resetListData();
            callGetCommitteeMemberListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1);
        }
    }

    private void callGetCommitteeMemberListAPI(final String id, final String user_id, final int PageNumber) {
        DialogUtils.showProgressDialog(CommitteeMemberListActivity.this, "");
        StringRequest committeeListReq = new StringRequest(Request.Method.POST, URLS.Get_Committee_wise_memeber, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetCommitteeWiseMemberListPojo getCommitteeWiseMemberListPojo = gson.fromJson("{\"Data\":" + response + "}", GetCommitteeWiseMemberListPojo.class);
                        if (getCommitteeWiseMemberListPojo.getData() != null &&
                                getCommitteeWiseMemberListPojo.getData().size() > 0) {

                            committeeMemberList.addAll(getCommitteeWiseMemberListPojo.getData());

                            if (PageNumber == 1) {
                                for (Iterator<GetCommitteeWiseMemberListPojo.DataBean> iterator = committeeMemberList.iterator(); iterator.hasNext(); ) {
                                    GetCommitteeWiseMemberListPojo.DataBean fruit = iterator.next();
                                    if (TextUtils.isEmpty(fruit.getCommittee_name()) ||
                                            fruit.getCommittee_name().equalsIgnoreCase("null") ||
                                            TextUtils.isEmpty(fruit.getCmtm_member_name())) {
                                        iterator.remove();
                                    }
                                }
                                commetteeMemberListAdapter = new CommetteeMemberListAdapter(CommitteeMemberListActivity.this, (ArrayList<GetCommitteeWiseMemberListPojo.DataBean>) committeeMemberList, true);
                                lv_committee_member.setAdapter(commetteeMemberListAdapter);

                                if (committeeMemberList.size() < 1) {
                                    DialogUtils.Show_Toast(CommitteeMemberListActivity.this, "No Data Found!");
                                }
                            } else {
                                if (committeeMemberList != null && committeeMemberList.size() > 0) {
                                    for (Iterator<GetCommitteeWiseMemberListPojo.DataBean> iterator = committeeMemberList.iterator(); iterator.hasNext(); ) {
                                        GetCommitteeWiseMemberListPojo.DataBean fruit = iterator.next();
                                        if (TextUtils.isEmpty(fruit.getCommittee_name()) ||
                                                fruit.getCommittee_name().equalsIgnoreCase("null") ||
                                                TextUtils.isEmpty(fruit.getCmtm_member_name())) {
                                            iterator.remove();
                                        }
                                    }
                                }
                                commetteeMemberListAdapter.notifyDataSetChanged();
                            }

                        } else {

                            if (PageNumber == 1) {
                                if (committeeMemberList != null) {
                                    committeeMemberList.clear();
                                }
                                commetteeMemberListAdapter = new CommetteeMemberListAdapter(CommitteeMemberListActivity.this, (ArrayList<GetCommitteeWiseMemberListPojo.DataBean>) committeeMemberList, true);
                                lv_committee_member.setAdapter(commetteeMemberListAdapter);
                                DialogUtils.Show_Toast(CommitteeMemberListActivity.this, "No Data Found!");
                            } else {
                                hasMoreData = false;
                            }
                        }
                    } catch (Exception ex) {
                        Toast.makeText(CommitteeMemberListActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DialogUtils.Show_Toast(CommitteeMemberListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeMemberListActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("user_id", user_id);
                params2.put("rowperpage", IntentConstants.ROW_PER_PAGE);
                params2.put("pageno", String.valueOf(PageNumber));
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        committeeListReq.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(committeeListReq);
    }


    private void resetListData() {
        isScrolling = false;
        hasMoreData = true;
        currentPageNo = 1;
        if (committeeMemberList != null) {
            committeeMemberList.clear();
            committeeMemberList = new ArrayList<>();
        }
    }

}
