package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomEditText;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetEmployeeIdWiseNameCommitteePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewCommitteeByIdPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.MemberNameAndIdModel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class CommitteeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    RequestQueue queue;
    ImageView ivBack;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;

    CustomEditText etNameOfCommittee, etRoleandResponsibilitiesCoordinator,
            etrolesAndResponsibilitiesMember;
    AppCompatCheckBox cbCommitteeStatus;

    LinearLayout ll_submit_cancel_commitee_details, ll_update_commitiee;

    CustomBoldTextView tvSubmitCommitiee, tvCancelCommitiee, tvUpdateCommitiee, tvCancelCommitieeUpdate;

    AppCompatAutoCompleteTextView actMainCoOrdinatorNameCommittee;
    AppCompatImageView imClearMainCoOrdinator;

    ArrayList<MemberNameAndIdModel> memberNameAndIdModelArrayList;
    ArrayList<String> employeeNameArrayListForAdapter;
    String memberId = "";

    ViewCommitteeByIdPojo.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getEmployeeIdWiseNameAPI("0");

        actMainCoOrdinatorNameCommittee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    imClearMainCoOrdinator.setVisibility(View.VISIBLE);
                } else {
                    imClearMainCoOrdinator.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    private boolean isValid() {
        if (TextUtils.isEmpty(etNameOfCommittee.getText().toString())) {
            DialogUtils.Show_Toast(CommitteeActivity.this, "Enter Name of Committee");
            return false;
        } else if (TextUtils.isEmpty(etRoleandResponsibilitiesCoordinator.getText().toString())) {
            DialogUtils.Show_Toast(CommitteeActivity.this, "Enter Roles and Responsibilities Coordinator");
            return false;
        } else if (TextUtils.isEmpty(memberId)) {
            DialogUtils.Show_Toast(CommitteeActivity.this, "Select Main Co-Ordinator");
            return false;
        } else if (TextUtils.isEmpty(etrolesAndResponsibilitiesMember.getText().toString())) {
            DialogUtils.Show_Toast(CommitteeActivity.this, "Enter Roles and Responsibilities Member");
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
        txt_act.setText("Committee");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        etNameOfCommittee = findViewById(R.id.etNameOfCommittee);
        etRoleandResponsibilitiesCoordinator = findViewById(R.id.etRoleandResponsibilitiesCoordinator);
        etrolesAndResponsibilitiesMember = findViewById(R.id.etrolesAndResponsibilitiesMember);

        cbCommitteeStatus = findViewById(R.id.cbCommitteeStatus);

        ll_submit_cancel_commitee_details = findViewById(R.id.ll_submit_cancel_commitee_details);
        ll_update_commitiee = findViewById(R.id.ll_update_commitiee);

        actMainCoOrdinatorNameCommittee = findViewById(R.id.actMainCoOrdinatorNameCommittee);
        actMainCoOrdinatorNameCommittee.setOnItemClickListener(this);
        imClearMainCoOrdinator = findViewById(R.id.imClearMainCoOrdinator);
        imClearMainCoOrdinator.setOnClickListener(this);

        tvSubmitCommitiee = findViewById(R.id.tvSubmitCommitiee);
        tvSubmitCommitiee.setOnClickListener(this);
        tvCancelCommitiee = findViewById(R.id.tvCancelCommitiee);
        tvCancelCommitiee.setOnClickListener(this);
        tvUpdateCommitiee = findViewById(R.id.tvUpdateCommitiee);
        tvUpdateCommitiee.setOnClickListener(this);
        tvCancelCommitieeUpdate = findViewById(R.id.tvCancelCommitieeUpdate);
        tvCancelCommitieeUpdate.setOnClickListener(this);

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
        } else if (view.getId() == R.id.tvSubmitCommitiee) {
            if (isValid()) {

                String id = "0";
                String committee_name = etNameOfCommittee.getText().toString();
                String rolls_coordinator = etRoleandResponsibilitiesCoordinator.getText().toString();
                String coordinator_ides = memberId;
                String rolls_member = etrolesAndResponsibilitiesMember.getText().toString();
                String user_id = mySharedPreferecesRKUOLD.getUserID();
                String ip = "0";
                String status = "0";
                if (cbCommitteeStatus.isChecked()) {
                    status = "1";
                }

                saveOrUpdateCommitteeDetailsAPI(id, committee_name, rolls_coordinator, coordinator_ides,
                        rolls_member, user_id, ip, status);

            }
        } else if (view.getId() == R.id.tvCancelCommitiee || view.getId() == R.id.tvCancelCommitieeUpdate) {
            onBackPressed();
        } else if (view.getId() == R.id.tvUpdateCommitiee) {
            if (isValid()) {
                if (dataBean != null && !TextUtils.isEmpty(String.valueOf(dataBean.getId()))) {
                    String id = String.valueOf(dataBean.getId());
                    String committee_name = etNameOfCommittee.getText().toString();
                    String rolls_coordinator = etRoleandResponsibilitiesCoordinator.getText().toString();
                    String coordinator_ides = memberId;
                    String rolls_member = etrolesAndResponsibilitiesMember.getText().toString();
                    String user_id = mySharedPreferecesRKUOLD.getUserID();
                    String ip = "0";
                    String status = "0";
                    if (cbCommitteeStatus.isChecked()) {
                        status = "1";
                    }

                    saveOrUpdateCommitteeDetailsAPI(id, committee_name, rolls_coordinator, coordinator_ides,
                            rolls_member, user_id, ip, status);

                } else {
                    Toast.makeText(this, "data bean is empty", Toast.LENGTH_SHORT).show();
                }

            }
        } else if (view.getId() == R.id.imClearMainCoOrdinator) {
            memberId = "";
            actMainCoOrdinatorNameCommittee.setText("");
            imClearMainCoOrdinator.setVisibility(View.GONE);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        for (int i = 0; i < memberNameAndIdModelArrayList.size(); i++) {
            if (memberNameAndIdModelArrayList.get(i).getMemberName().equalsIgnoreCase(parent.getItemAtPosition(position).toString())) {
                memberId = memberNameAndIdModelArrayList.get(i).getMemberId();
                Log.d("MEMBER_IDDDDDDDDDDDD", memberId);
                break;
            }
        }

    }


    private void saveOrUpdateCommitteeDetailsAPI(final String id, final String committee_name, final String rolls_coordinator,
                                                 final String coordinator_ides, final String rolls_member,
                                                 final String user_id, final String ip, final String status) {


        DialogUtils.showProgressDialog(CommitteeActivity.this, "");
        StringRequest saveCommitteeDetails = new StringRequest(Request.Method.POST, URLS.Save_Update_Committee, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(CommitteeActivity.this, responsePojo.getData().get(0).getMsg());

                    Intent intent = new Intent(CommitteeActivity.this, CommitteeListActivity.class);
                    setResult(RESULT_OK, intent);
                    onBackPressed();
//                    Toast.makeText(ReqSeedReceivedFromUniversityActivity.this, response, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Response:- " + response);
                } else {
                    DialogUtils.Show_Toast(CommitteeActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("committee_name", committee_name);
                params2.put("rolls_coordinator", rolls_coordinator);
                params2.put("coordinator_ides", coordinator_ides);
                params2.put("rolls_member", rolls_member);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("status", status);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        saveCommitteeDetails.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(saveCommitteeDetails);

    }

    private void getEmployeeIdWiseNameAPI(final String Emp_id) {

        DialogUtils.showProgressDialog(CommitteeActivity.this, "");
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

//                                Log.d("MemberIdAndName------","Id:- "+getEmployeeIdWiseNameCommitteePojo.getData().get(i).getId()+" Name:- "+
//                                        getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());

                                employeeNameArrayListForAdapter.add(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());
                                MemberNameAndIdModel memberNameAndIdModel = new MemberNameAndIdModel();
                                memberNameAndIdModel.setMemberId(String.valueOf(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getId()));
                                memberNameAndIdModel.setMemberName(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());
                                memberNameAndIdModelArrayList.add(memberNameAndIdModel);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CommitteeActivity.this, android.R.layout.simple_list_item_1, employeeNameArrayListForAdapter);
                            actMainCoOrdinatorNameCommittee.setAdapter(adapter);


                            if (getIntent().hasExtra(IntentConstants.VIEW_COMMITTEE_POJO)) {
                                ll_submit_cancel_commitee_details.setVisibility(View.GONE);
                                ll_update_commitiee.setVisibility(View.VISIBLE);

                                dataBean = (ViewCommitteeByIdPojo.DataBean) getIntent().getSerializableExtra(IntentConstants.VIEW_COMMITTEE_POJO);

                                if (dataBean != null) {

                                    try {
                                        etNameOfCommittee.setText(dataBean.getCommittee_name());
                                        etRoleandResponsibilitiesCoordinator.setText(dataBean.getRolls_responsibility_coordinatior());
                                        actMainCoOrdinatorNameCommittee.setText(dataBean.getMain_coordinatior());
                                        etrolesAndResponsibilitiesMember.setText(dataBean.getRolls_ressponsibility_member());


                                        for (int i = 0; i < memberNameAndIdModelArrayList.size(); i++) {
                                            if (memberNameAndIdModelArrayList.get(i).getMemberName().equalsIgnoreCase(dataBean.getMain_coordinatior())) {
                                                memberId = memberNameAndIdModelArrayList.get(i).getMemberId();
                                                Log.d("MEMBER_IDDDDDDDDDDDD", memberId);
                                                break;
                                            }
                                        }

                                        if (String.valueOf(dataBean.getStatus()).equalsIgnoreCase("1")) {
                                            cbCommitteeStatus.setChecked(true);
                                        } else {
                                            cbCommitteeStatus.setChecked(false);
                                        }
                                    } catch (Exception ex) {
                                        Toast.makeText(CommitteeActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(CommitteeActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                        } else {
                            DialogUtils.Show_Toast(CommitteeActivity.this, "Employee list null or empty");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(CommitteeActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeActivity.this, "Please Try Again Later");
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

}
