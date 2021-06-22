package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.CommitteeMemberNameListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.CommitteeWiseMemberListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.Utility;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetCommitteeEmployeeNamePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetCommitteeNamePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetEmployeeIdWiseNameCommitteePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewCommitteeWiseMemberPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.CommitteMemberListModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.CommitteeNameAndIdModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.MemberNameAndIdModel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


public class CommitteeMemberActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, CommitteeMemberNameListAdapter.RemoveCommitteeMember {


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;

    CustomTextView tvNameOfCoOrdinatorCommitteeMember;
    Spinner spCommitteeNameCommitteeMember;
    LinearLayout ll_submit_cancel_committee_member, ll_update_committee_member;

    CustomBoldTextView tvSubmitCommitteeMember, tvCancelCommitteeMember,
            tvUpdateCommitteeMember, tvCancelCommitteeMemberUpdate;
    ArrayList<String> employeeNameArrayListForAdapter;

    RecyclerView rvAddCommitteeMember;
    RecyclerView.LayoutManager layoutManager;
    CommitteeMemberNameListAdapter committeeMemberNameListAdapter;
    ArrayList<CommitteMemberListModel> committeMemberListModelArrayList = new ArrayList<>();
    AppCompatAutoCompleteTextView actEmployeeNameCommitteeMember;

    ArrayList<MemberNameAndIdModel> memberNameAndIdModelArrayList;
    AppCompatImageView imClearEmployeeName;

    ArrayList<CommitteeNameAndIdModel> committeeNameAndIdModelArrayList;
    ArrayList<String> committeeNameForAdapter;
    String committeeId = "";

    ViewCommitteeWiseMemberPojo.DataBean dataBean;

    CustomBoldTextView tvNameOfMainCoOrdinator;
    LinearLayout ll_lv_for_committee_wise_member;
    ListView lv_committee_wise_member;
    CommitteeWiseMemberListAdapter committeeWiseMemberListAdapter;
    List<GetCommitteeEmployeeNamePojo.DataBean> committeeWiseMemberDetailsList;


    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(CommitteeMemberActivity.this)
                .anchorView(view)
                .gravity(Gravity.BOTTOM)
                .modal(true)
                .arrowColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .text(getString(R.string.app_name))
                .contentView(R.layout.tooltip_delete_committee_member)
                .build();
        tooltip.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_member);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

//        if (getIntent().hasExtra(IntentConstants.VIEW_COMMITTEE_MEMBER_POJO)) {
//
//            dataBean = (ViewCommitteeWiseMemberPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.VIEW_COMMITTEE_MEMBER_POJO);
//
//            if (dataBean != null) {
//
//                try {
//
//                    ll_submit_cancel_committee_member.setVisibility(View.GONE);
//                    ll_update_committee_member.setVisibility(View.VISIBLE);
//
//                    String[] committeeMemberNameArray = dataBean.getCmtm_member_id().split(",");
//                    String[] committeeMemberId = dataBean.getCmtm_member_wise_id().split(",");
//
//                    if (committeeMemberNameArray.length == committeeMemberId.length) {
//                        for (int i = 0; i < committeeMemberId.length; i++) {
//
//
//                                CommitteMemberListModel committeMemberListModel = new CommitteMemberListModel();
//                                committeMemberListModel.setMemberName(committeeMemberNameArray[i]);
//                                committeMemberListModel.setMemberId(committeeMemberId[i]);
//
//                                committeMemberListModelArrayList.add(0, committeMemberListModel);
//
//                                committeeMemberNameListAdapter = new CommitteeMemberNameListAdapter(CommitteeMemberActivity.this, committeMemberListModelArrayList);
//                                rvAddCommitteeMember.setAdapter(committeeMemberNameListAdapter);
//                                rvAddCommitteeMember.setVisibility(View.VISIBLE);
//
//                        }
//                    } else {
//                        Toast.makeText(CommitteeMemberActivity.this, "Length of Employee Name and Id not equal", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception ex) {
//                    Toast.makeText(CommitteeMemberActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//
//            } else {
//                Toast.makeText(CommitteeMemberActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
//            }
//        }

        getCommitteeNameAPI(mySharedPreferecesRKUOLD.getEmpID());

        spCommitteeNameCommitteeMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    for (int i = 0; i < committeeNameAndIdModelArrayList.size(); i++) {
                        if (committeeNameAndIdModelArrayList.get(i).getCommitteeName().equalsIgnoreCase(committeeNameForAdapter.get(position))) {
                            committeeId = committeeNameAndIdModelArrayList.get(i).getCommitteeId();
                            break;
                        }
                    }
                    if (!TextUtils.isEmpty(committeeId)) {
                        getCommitteeEmployeeNameAPI(committeeId, mySharedPreferecesRKUOLD.getUserID());
                    } else {
                        Toast.makeText(CommitteeMemberActivity.this, "CommitteeId is Empty", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        actEmployeeNameCommitteeMember.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    imClearEmployeeName.setVisibility(View.VISIBLE);
                } else {
                    imClearEmployeeName.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private boolean isValid() {

        if (TextUtils.isEmpty(tvNameOfCoOrdinatorCommitteeMember.getText().toString())) {
            DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Name of Co-Ordinator is Empty");
            return false;
        } else if (committeeNameForAdapter.get(spCommitteeNameCommitteeMember.getSelectedItemPosition()).equalsIgnoreCase("Select Committee")) {
            DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Select Committee");
            return false;
        } else if (committeMemberListModelArrayList == null ||
                committeMemberListModelArrayList.size() < 1) {
            DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Please Add Member");
            return false;
        }
        return true;
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

        tvNameOfCoOrdinatorCommitteeMember = findViewById(R.id.tvNameOfCoOrdinatorCommitteeMember);
        tvNameOfCoOrdinatorCommitteeMember.setText(mySharedPreferecesRKUOLD.getEmpCode() + " - " + mySharedPreferecesRKUOLD.getFullName());
        spCommitteeNameCommitteeMember = findViewById(R.id.spCommitteeNameCommitteeMember);

        imClearEmployeeName = findViewById(R.id.imClearEmployeeName);
        imClearEmployeeName.setOnClickListener(this);

        ll_submit_cancel_committee_member = findViewById(R.id.ll_submit_cancel_committee_member);
        ll_update_committee_member = findViewById(R.id.ll_update_committee_member);

        tvSubmitCommitteeMember = findViewById(R.id.tvSubmitCommitteeMember);
        tvSubmitCommitteeMember.setOnClickListener(this);
        tvCancelCommitteeMember = findViewById(R.id.tvCancelCommitteeMember);
        tvCancelCommitteeMember.setOnClickListener(this);
        tvUpdateCommitteeMember = findViewById(R.id.tvUpdateCommitteeMember);
        tvUpdateCommitteeMember.setOnClickListener(this);
        tvCancelCommitteeMemberUpdate = findViewById(R.id.tvCancelCommitteeMemberUpdate);
        tvCancelCommitteeMemberUpdate.setOnClickListener(this);

        actEmployeeNameCommitteeMember = findViewById(R.id.actEmployeeNameCommitteeMember);
        actEmployeeNameCommitteeMember.setOnItemClickListener(this);

        rvAddCommitteeMember = findViewById(R.id.rvAddCommitteeMember);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvAddCommitteeMember.setLayoutManager(layoutManager);

        ll_lv_for_committee_wise_member = findViewById(R.id.ll_lv_for_committee_wise_member);
        tvNameOfMainCoOrdinator = findViewById(R.id.tvNameOfMainCoOrdinator);
        tvNameOfMainCoOrdinator.setText(mySharedPreferecesRKUOLD.getEmpCode() + " - " + mySharedPreferecesRKUOLD.getFullName());
        lv_committee_wise_member = findViewById(R.id.lv_committee_wise_member);

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CommitteMemberListModel committeMemberListModel = new CommitteMemberListModel();
        committeMemberListModel.setMemberName(parent.getItemAtPosition(position).toString());

        String memberId = "";

        for (int i = 0; i < memberNameAndIdModelArrayList.size(); i++) {
            if (memberNameAndIdModelArrayList.get(i).getMemberName().equalsIgnoreCase(parent.getItemAtPosition(position).toString())) {
                memberId = memberNameAndIdModelArrayList.get(i).getMemberId();
                break;
            }
        }

        committeMemberListModel.setMemberId(memberId);
        committeMemberListModelArrayList.add(0, committeMemberListModel);

        committeeMemberNameListAdapter = new CommitteeMemberNameListAdapter(this, committeMemberListModelArrayList);
        rvAddCommitteeMember.setAdapter(committeeMemberNameListAdapter);
        rvAddCommitteeMember.setVisibility(View.VISIBLE);
        rvAddCommitteeMember.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.tvSubmitCommitteeMember) {
            if (isValid()) {

                String User_id = mySharedPreferecesRKUOLD.getUserID();
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String ip = "0";
                String committee_id = committeeId;
                String Employee_ids = "";

                for (int i = 0; i < committeMemberListModelArrayList.size(); i++) {
                    if (i == committeMemberListModelArrayList.size() - 1) {
                        Employee_ids += committeMemberListModelArrayList.get(i).getMemberId();
                        break;
                    }
                    Employee_ids += committeMemberListModelArrayList.get(i).getMemberId() + ",";
                }
                saveCommitteeWiseMember(User_id, emp_id, ip, committee_id, Employee_ids);
            }
        } else if (view.getId() == R.id.tvCancelCommitteeMember || view.getId() == R.id.tvCancelCommitteeMemberUpdate) {
            onBackPressed();
        } else if (view.getId() == R.id.tvUpdateCommitteeMember) {
            if (isValid() && dataBean != null && !TextUtils.isEmpty(String.valueOf(dataBean.getId()))) {

                String id = String.valueOf(dataBean.getId());
                String User_id = mySharedPreferecesRKUOLD.getUserID();
                String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                String ip = "0";
                String committee_id = committeeId;
                String Employee_ids = "";

                for (int i = 0; i < committeMemberListModelArrayList.size(); i++) {

                    if (i == committeMemberListModelArrayList.size() - 1) {
                        Employee_ids += committeMemberListModelArrayList.get(i).getMemberId();
                        break;
                    }
                    Employee_ids += committeMemberListModelArrayList.get(i).getMemberId() + ",";
                }

                updateCommitteeWiseMember(id, User_id, emp_id, ip, committee_id, Employee_ids);
            }
        } else if (view.getId() == R.id.imClearEmployeeName) {
            actEmployeeNameCommitteeMember.setText("");
            imClearEmployeeName.setVisibility(View.GONE);
        } else if (view.getId() == R.id.iv_info) {
            showTooltip(view);
        }
    }

    private void getCommitteeNameAPI(final String emp_id) {

        DialogUtils.showProgressDialog(CommitteeMemberActivity.this, "");
        StringRequest getCommitteeName = new StringRequest(Request.Method.POST, URLS.Get_Committee_Name, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetCommitteeNamePojo getCommitteeNamePojo = gson.fromJson("{\"Data\":" + response + "}", GetCommitteeNamePojo.class);

                        if (getCommitteeNamePojo != null && getCommitteeNamePojo.getData().size() > 0) {

                            committeeNameForAdapter = new ArrayList<>();
                            committeeNameAndIdModelArrayList = new ArrayList<>();

                            committeeNameForAdapter.add("Select Committee");

                            for (int i = 0; i < getCommitteeNamePojo.getData().size(); i++) {
                                committeeNameForAdapter.add(String.valueOf(getCommitteeNamePojo.getData().get(i).getCommittee_name()));
                                CommitteeNameAndIdModel committeeNameAndIdModel = new CommitteeNameAndIdModel();
                                committeeNameAndIdModel.setCommitteeId(String.valueOf(getCommitteeNamePojo.getData().get(i).getId()));
                                committeeNameAndIdModel.setCommitteeName(String.valueOf(getCommitteeNamePojo.getData().get(i).getCommittee_name()));
                                committeeNameAndIdModelArrayList.add(committeeNameAndIdModel);
                            }

                            SpinnerSimpleAdapter committeeNameAdapter = new SpinnerSimpleAdapter(CommitteeMemberActivity.this, committeeNameForAdapter);
                            spCommitteeNameCommitteeMember.setAdapter(committeeNameAdapter);
                            getEmployeeIDWiseNameAPI("0");
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(CommitteeMemberActivity.this, "There is no any committee found to add member!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(CommitteeMemberActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_id", emp_id);//703 is for temporary replace it with emp_id in live
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getCommitteeName.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getCommitteeName);

    }

    private void getEmployeeIDWiseNameAPI(final String Emp_id) {

//        DialogUtils.showProgressDialog(CommitteeMemberActivity.this, "");
        StringRequest getEmployyeIdWiseName = new StringRequest(Request.Method.POST, URLS.Get_employee_ID_Wise_Name, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {

                    try {
                        Gson gson = new Gson();
                        GetEmployeeIdWiseNameCommitteePojo getEmployeeIdWiseNameCommitteePojo = gson.fromJson("{\"Data\":" + response + "}", GetEmployeeIdWiseNameCommitteePojo.class);

                        if (getEmployeeIdWiseNameCommitteePojo != null && getEmployeeIdWiseNameCommitteePojo.getData().size() > 0) {
                            employeeNameArrayListForAdapter = new ArrayList<>();
                            memberNameAndIdModelArrayList = new ArrayList<>();
                            for (int i = 0; i < getEmployeeIdWiseNameCommitteePojo.getData().size(); i++) {
//
//                                Log.d("MemberIdAndName------","Id:- "+getEmployeeIdWiseNameCommitteePojo.getData().get(i).getId()+" Name:- "+
//                                        getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());

                                employeeNameArrayListForAdapter.add(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());
                                MemberNameAndIdModel memberNameAndIdModel = new MemberNameAndIdModel();
                                memberNameAndIdModel.setMemberId(String.valueOf(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getId()));
                                memberNameAndIdModel.setMemberName(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());
                                memberNameAndIdModelArrayList.add(memberNameAndIdModel);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CommitteeMemberActivity.this, android.R.layout.simple_list_item_1, employeeNameArrayListForAdapter);
                            actEmployeeNameCommitteeMember.setAdapter(adapter);

                            if (getIntent().hasExtra(IntentConstants.VIEW_COMMITTEE_MEMBER_POJO)) {
                                dataBean = (ViewCommitteeWiseMemberPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.VIEW_COMMITTEE_MEMBER_POJO);
                                if (dataBean != null) {
                                    ll_submit_cancel_committee_member.setVisibility(View.GONE);
                                    ll_update_committee_member.setVisibility(View.VISIBLE);
                                    spCommitteeNameCommitteeMember.setSelection(committeeNameForAdapter.indexOf(dataBean.getCommittee_name()));
                                } else {
                                    Toast.makeText(CommitteeMemberActivity.this, "Data bean null or empty", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } else {
                            DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Employee list null or empty");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(CommitteeMemberActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Emp_id", Emp_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getEmployyeIdWiseName.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getEmployyeIdWiseName);

    }

    private void getCommitteeEmployeeNameAPI(final String Committee_id, final String User_id) {

        DialogUtils.showProgressDialog(CommitteeMemberActivity.this, "");
        StringRequest getCommitteeEmployeeNameReq = new StringRequest(Request.Method.POST, URLS.Get_Committee_Employee_Name, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetCommitteeEmployeeNamePojo getCommitteeEmployeeNamePojo = gson.fromJson("{\"Data\":" + response + "}", GetCommitteeEmployeeNamePojo.class);

                        committeeWiseMemberDetailsList = new ArrayList<>();
                        if (getCommitteeEmployeeNamePojo != null && getCommitteeEmployeeNamePojo.getData().size() > 0) {

                            committeeWiseMemberDetailsList = getCommitteeEmployeeNamePojo.getData();
                            committeeWiseMemberListAdapter = new CommitteeWiseMemberListAdapter(CommitteeMemberActivity.this, (ArrayList<GetCommitteeEmployeeNamePojo.DataBean>) committeeWiseMemberDetailsList, true);
                            lv_committee_wise_member.setAdapter(committeeWiseMemberListAdapter);
                            Utility.setListViewHeightBasedOnChildren(lv_committee_wise_member);
                            ll_lv_for_committee_wise_member.setVisibility(View.VISIBLE);
                            if (!getIntent().hasExtra(IntentConstants.VIEW_COMMITTEE_MEMBER_POJO)) {
                                ll_submit_cancel_committee_member.setVisibility(View.GONE);
                                ll_update_committee_member.setVisibility(View.GONE);

                                Toast.makeText(CommitteeMemberActivity.this, "In " + committeeNameForAdapter.get(spCommitteeNameCommitteeMember.getSelectedItemPosition()) + "Committee has already member exist please edit the committee to add the member.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            if (!getIntent().hasExtra(IntentConstants.VIEW_COMMITTEE_MEMBER_POJO)) {
                                ll_submit_cancel_committee_member.setVisibility(View.VISIBLE);
                                ll_update_committee_member.setVisibility(View.GONE);
                            }
                            ll_lv_for_committee_wise_member.setVisibility(View.GONE);
                            committeeWiseMemberDetailsList.clear();
                            committeeWiseMemberListAdapter = new CommitteeWiseMemberListAdapter(CommitteeMemberActivity.this, (ArrayList<GetCommitteeEmployeeNamePojo.DataBean>) committeeWiseMemberDetailsList, true);
                            lv_committee_wise_member.setAdapter(committeeWiseMemberListAdapter);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(CommitteeMemberActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Committee_id", Committee_id);
                params2.put("User_id", User_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getCommitteeEmployeeNameReq.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getCommitteeEmployeeNameReq);

    }


    private void saveCommitteeWiseMember(final String User_id, final String emp_id, final String ip,
                                         final String committee_id, final String Employee_ids) {

        DialogUtils.showProgressDialog(CommitteeMemberActivity.this, "");
        StringRequest getCommitteeName = new StringRequest(Request.Method.POST, URLS.Save_Committee_Wise_Member, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
//                        Gson gson = new Gson();
//                        SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
//                        DialogUtils.Show_Toast(CommitteeMemberActivity.this, responsePojo.getData().get(0).getMsg());
//
//                        Intent intent = new Intent(CommitteeMemberActivity.this, CommitteeMemberListActivity.class);
//                        setResult(RESULT_OK, intent);
//                        onBackPressed();

                        if (!TextUtils.isEmpty(committee_id)) {
                            getCommitteeEmployeeNameAPI(committeeId, mySharedPreferecesRKUOLD.getUserID());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(CommitteeMemberActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("User_id", User_id);
                params2.put("emp_id", emp_id);
                params2.put("ip", ip);
                params2.put("committee_id", committee_id);
                params2.put("Employee_ids", Employee_ids);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getCommitteeName.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getCommitteeName);
    }


    private void updateCommitteeWiseMember(final String id, final String User_id, final String emp_id,
                                           final String ip, final String committee_id, final String Employee_ids) {

        DialogUtils.showProgressDialog(CommitteeMemberActivity.this, "");
        StringRequest getCommitteeName = new StringRequest(Request.Method.POST, URLS.Update_Committee_Wise_Member, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                        DialogUtils.Show_Toast(CommitteeMemberActivity.this, responsePojo.getData().get(0).getMsg());

                        Intent intent = new Intent(CommitteeMemberActivity.this, CommitteeMemberListActivity.class);
                        setResult(RESULT_OK, intent);
                        onBackPressed();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(CommitteeMemberActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeMemberActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("User_id", User_id);
//                params2.put("emp_id", emp_id); // Removed as per talk with jonsi madam // commented by remish varsani
                params2.put("ip", ip);
                params2.put("committee_id", committee_id);
                params2.put("Employee_ids", Employee_ids);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getCommitteeName.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getCommitteeName);
    }

    @Override
    public void removeCommitteemember(ArrayList<CommitteMemberListModel> committeMemberListModelArrayList) {

        this.committeMemberListModelArrayList = committeMemberListModelArrayList;
        committeeMemberNameListAdapter = new CommitteeMemberNameListAdapter(this, committeMemberListModelArrayList);
        rvAddCommitteeMember.setAdapter(committeeMemberNameListAdapter);
        if (committeMemberListModelArrayList.size() == 0) {
            rvAddCommitteeMember.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CommitteeMemberActivity.this, CommitteeMemberListActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }
}
