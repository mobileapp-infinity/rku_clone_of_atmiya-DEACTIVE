package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.EmployeeLeaveBalanceReportPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.MemberNameAndIdModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class EmployeeLeaveBalanceReportActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SELECT_BRANCH = "Select Branch";
    private static final String SELECT_DEPARTMENT = "Select Department";


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    Spinner spBranchEmpLeaveBalanceReport, spDepartmentEmpLeaveBalanceReport,
            spMonthEmpLeaveBalanceReport, spYearEmpLeaveBalanceReport;
    //    AppCompatAutoCompleteTextView actEmpNameLeaveBalanceReport;
    RadioButton rbtnPayrollEmpLeaveBalanceReport, rbtnMailEmpLeaveBalanceReport;
    CustomBoldTextView tvLoadLeaveBalanceReport, tvCancelLeaveBalanceReport;

    ArrayList<String> branchOpetions;
    HashMap<String, Integer> hashMapBranchOpetionAndID;

    ArrayList<String> departmentOpetions;
    HashMap<String, Integer> hashMapDepartmentAndID;


    String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    ArrayList<String> monthOpetions = new ArrayList<>();
    HashMap<String, Integer> hashMapMonthOpetionAndID = new HashMap<>();

    //    String years[] = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
    ArrayList<String> yearOpetions;
//    HashMap<String, Integer> hashMapYearOpetionAndID = new HashMap<>();

    ArrayList<MemberNameAndIdModel> memberNameAndIdModelArrayList;
    ArrayList<String> employeeNameArrayListForAdapter;

    List<EmployeeLeaveBalanceReportPojo.DataBean> employeeLeaveBalanceReportPojoList;

//    AppCompatImageView imClearEmployeeNameEmployeeLeaveBalanceReport;

//    String memberId = "";


    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_leave_balance_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getReportFilterYearApiCall();

//        spMonthEmpLeaveBalanceReport.setSelection(currentMonth + 1);
//        spYearEmpLeaveBalanceReport.setSelection(yearOpetions.indexOf(String.valueOf(currentYear)));

        spBranchEmpLeaveBalanceReport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    getDepartmentApiCall(true, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(position))));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        actEmpNameLeaveBalanceReport.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!TextUtils.isEmpty(s)) {
//                    imClearEmployeeNameEmployeeLeaveBalanceReport.setVisibility(View.VISIBLE);
//                } else {
//                    imClearEmployeeNameEmployeeLeaveBalanceReport.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Leave Balance Report");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        queue = Volley.newRequestQueue(this);

        spBranchEmpLeaveBalanceReport = findViewById(R.id.spBranchEmpLeaveBalanceReport);
        spDepartmentEmpLeaveBalanceReport = findViewById(R.id.spDepartmentEmpLeaveBalanceReport);
        spMonthEmpLeaveBalanceReport = findViewById(R.id.spMonthEmpLeaveBalanceReport);
        monthOpetions.add("Select Month");
        spYearEmpLeaveBalanceReport = findViewById(R.id.spYearEmpLeaveBalanceReport);

        for (int i = 0; i < months.length; i++) {
            monthOpetions.add(months[i]);
            hashMapMonthOpetionAndID.put(months[i], i + 1);
        }

        SpinnerSimpleAdapter monthOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLeaveBalanceReportActivity.this, monthOpetions);
        spMonthEmpLeaveBalanceReport.setAdapter(monthOpetionAdapter);

//        for (int i = 0; i < years.length; i++) {
//            yearOpetions.add(years[i]);
//            hashMapYearOpetionAndID.put(years[i], i + 1);
//        }

//        SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLeaveBalanceReportActivity.this, yearOpetions);
//        spYearEmpLeaveBalanceReport.setAdapter(yearsOpetionAdapter);

//        actEmpNameLeaveBalanceReport = findViewById(R.id.actEmpNameLeaveBalanceReport);

        rbtnPayrollEmpLeaveBalanceReport = findViewById(R.id.rbtnPayrollEmpLeaveBalanceReport);
        rbtnMailEmpLeaveBalanceReport = findViewById(R.id.rbtnMailEmpLeaveBalanceReport);

        tvLoadLeaveBalanceReport = findViewById(R.id.tvLoadLeaveBalanceReport);
        tvLoadLeaveBalanceReport.setOnClickListener(this);
        tvCancelLeaveBalanceReport = findViewById(R.id.tvCancelLeaveBalanceReport);
        tvCancelLeaveBalanceReport.setOnClickListener(this);

//        imClearEmployeeNameEmployeeLeaveBalanceReport = findViewById(R.id.imClearEmployeeNameEmployeeLeaveBalanceReport);
//        imClearEmployeeNameEmployeeLeaveBalanceReport.setOnClickListener(this);

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

    private boolean isValid() {
//        if (branchOpetions.get(spBranchEmpLeaveBalanceReport.getSelectedItemPosition()).equalsIgnoreCase("Select Branch")) {
//            DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Select Branch");
//            return false;
//        } else if (departmentOpetions.get(spDepartmentEmpLeaveBalanceReport.getSelectedItemPosition()).equalsIgnoreCase("Select Department")) {
//            DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Select Department");
//            return false;
//        } else
        if (monthOpetions.get(spMonthEmpLeaveBalanceReport.getSelectedItemPosition()).equalsIgnoreCase("Select Month")) {
            DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Select Month");
            return false;
        } else if (yearOpetions.get(spYearEmpLeaveBalanceReport.getSelectedItemPosition()).equalsIgnoreCase("Select Year")) {
            DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Select Year");
            return false;
        }

//        else if (!TextUtils.isEmpty(memberId)) {
//            DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Select Employee Name");
//            return false;
//        }

        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back || view.getId() == R.id.tvCancelLeaveBalanceReport) {
            onBackPressed();
        } else if (view.getId() == R.id.tvLoadLeaveBalanceReport) {

            if (isValid()) {

                String Branch_Id = String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(spBranchEmpLeaveBalanceReport.getSelectedItemPosition())));
                String Department_Id = String.valueOf(hashMapDepartmentAndID.get(departmentOpetions.get(spDepartmentEmpLeaveBalanceReport.getSelectedItemPosition())));
                String user_id = mySharedPreferecesRKUOLD.getUserID();
//                String Main_Branch_Or_Payroll_Branch = "1";
//
//                if (rbtnMailEmpLeaveBalanceReport.isChecked()){
//                    Main_Branch_Or_Payroll_Branch = "2";
//                }

                String Year = yearOpetions.get(spYearEmpLeaveBalanceReport.getSelectedItemPosition());
                String Month = String.valueOf(hashMapMonthOpetionAndID.get(monthOpetions.get(spMonthEmpLeaveBalanceReport.getSelectedItemPosition())));
//                String Emp_Id = "0";//0 As per tolk with jonsi madam

                loadLeaveBalanceReportApiCall(Branch_Id, Department_Id,
                        user_id, Year, Month);

            }
        }
//        else if (view.getId() == R.id.imClearEmployeeNameEmployeeLeaveBalanceReport){
////            memberId = "";
////            actEmpNameLeaveBalanceReport.setText("");
////            imClearEmployeeNameEmployeeLeaveBalanceReport.setVisibility(View.GONE);
//        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        for (int i = 0; i < memberNameAndIdModelArrayList.size(); i++) {
//            if (memberNameAndIdModelArrayList.get(i).getMemberName().equalsIgnoreCase(parent.getItemAtPosition(position).toString())) {
//                memberId = memberNameAndIdModelArrayList.get(i).getMemberId();
//                break;
//            }
//        }
//    }


    private void getBranchApiCall(boolean isPdShow, final boolean isPdHide) {
        if (isPdShow) {
            DialogUtils.showProgressDialog(EmployeeLeaveBalanceReportActivity.this, "");
        }

        StringRequest getBranchRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Branch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (isPdHide) {
                    DialogUtils.hideProgressDialog();
                }
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

                            branchOpetions = new ArrayList<>();
                            branchOpetions.add(SELECT_BRANCH);
                            hashMapBranchOpetionAndID = new HashMap<>();
                            hashMapBranchOpetionAndID.put(SELECT_BRANCH, 0);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapBranchOpetionAndID.put(jsonArray.getJSONObject(i).getString("brm_name"),
                                        jsonArray.getJSONObject(i).getInt("id"));
                                branchOpetions.add(jsonArray.getJSONObject(i).getString("brm_name"));
                            }
                            SpinnerSimpleAdapter branchOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLeaveBalanceReportActivity.this, branchOpetions);
                            spBranchEmpLeaveBalanceReport.setAdapter(branchOpetionAdapter);

                            getDepartmentApiCall(false, true, String.valueOf(hashMapBranchOpetionAndID.get(branchOpetions.get(1))));
                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeLeaveBalanceReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeLeaveBalanceReportActivity.this, "Branch empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("User_id", mySharedPreferecesRKUOLD.getUserID());
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        getBranchRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getBranchRequest);
    }

    private void getReportFilterYearApiCall() {
        DialogUtils.showProgressDialog(EmployeeLeaveBalanceReportActivity.this, "");

        StringRequest getBranchRequest = new StringRequest(Request.Method.POST, URLS.GET_FILTER_YEAR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray != null && jsonArray.length() > 0) {
                            yearOpetions = new ArrayList<>();
                            yearOpetions.add("Select Year");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                yearOpetions.add(jsonArray.getJSONObject(i).getString("year"));
                            }

                            SpinnerSimpleAdapter yearsOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLeaveBalanceReportActivity.this, yearOpetions);
                            spYearEmpLeaveBalanceReport.setAdapter(yearsOpetionAdapter);

                            getBranchApiCall(false, false);

                        } else {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(EmployeeLeaveBalanceReportActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeLeaveBalanceReportActivity.this, "Year empty or null", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
            }
        });

        getBranchRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getBranchRequest);
    }

    private void getDepartmentApiCall(final boolean isPdShow, final boolean isPdHide, final String branch_id) {

        if (isPdShow) {
            DialogUtils.showProgressDialog(EmployeeLeaveBalanceReportActivity.this, "");
        }
        StringRequest leaveBalanceRequest = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Department, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (isPdHide) {
                    DialogUtils.hideProgressDialog();
                }
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        if (jsonArray != null && jsonArray.length() > 0) {

                            departmentOpetions = new ArrayList<>();
                            departmentOpetions.add(SELECT_DEPARTMENT);
                            hashMapDepartmentAndID = new HashMap<>();
                            hashMapDepartmentAndID.put(SELECT_DEPARTMENT, 0);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                hashMapDepartmentAndID.put(jsonArray.getJSONObject(i).getString("dep_name"),
                                        jsonArray.getJSONObject(i).getInt("id"));
                                departmentOpetions.add(jsonArray.getJSONObject(i).getString("dep_name"));
                            }
                            SpinnerSimpleAdapter departmentOpetionAdapter = new SpinnerSimpleAdapter(EmployeeLeaveBalanceReportActivity.this, departmentOpetions);
                            spDepartmentEmpLeaveBalanceReport.setAdapter(departmentOpetionAdapter);
//                            getEmployeeIdWiseNameAPI("0"); //0 for getting all employee name for perticular employee pass EMP Id than it will return that perticular employee
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("User_id", mySharedPreferecesRKUOLD.getUserID());
                params2.put("Branch_id", branch_id);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        leaveBalanceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(leaveBalanceRequest);
    }

//    private void getEmployeeIdWiseNameAPI(final String Emp_id) {
//
////        DialogUtils.showProgressDialog(EmployeeLeaveBalanceReportActivity.this, "");
//        StringRequest getEmployyeIdWiseName = new StringRequest(Request.Method.POST, URLS.Get_employee_ID_Wise_Name, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
//                if (!TextUtils.isEmpty(response)) {
//
//                    try {
//                        Gson gson = new Gson();
//                        GetEmployeeIdWiseNameCommitteePojo getEmployeeIdWiseNameCommitteePojo = gson.fromJson("{\"Data\":" + response + "}", GetEmployeeIdWiseNameCommitteePojo.class);
//
//                        if (getEmployeeIdWiseNameCommitteePojo != null && getEmployeeIdWiseNameCommitteePojo.getData().size() > 0) {
//
//                            employeeNameArrayListForAdapter = new ArrayList<>();
//                            memberNameAndIdModelArrayList = new ArrayList<>();
//                            for (int i = 0; i < getEmployeeIdWiseNameCommitteePojo.getData().size(); i++) {
//
//                                employeeNameArrayListForAdapter.add(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());
//                                MemberNameAndIdModel memberNameAndIdModel = new MemberNameAndIdModel();
//                                memberNameAndIdModel.setMemberId(String.valueOf(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getId()));
//                                memberNameAndIdModel.setMemberName(getEmployeeIdWiseNameCommitteePojo.getData().get(i).getEmp_full_name());
//                                memberNameAndIdModelArrayList.add(memberNameAndIdModel);
//                            }
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EmployeeLeaveBalanceReportActivity.this, android.R.layout.simple_list_item_1, employeeNameArrayListForAdapter);
//                            actEmpNameLeaveBalanceReport.setAdapter(adapter);
//
//                        } else {
//                            DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "No data found!");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, response);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
//                System.out.println("errorrrrrrrrrr " + error);
//                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params2 = new Hashtable<>();
//                params2.put("Emp_id", Emp_id);
//                return params2;
//            }
//
//            @Override
//            public String getBodyContentType() {
////                return "application/json; charset=UTF-8";
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//        };
//        getEmployyeIdWiseName.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(getEmployyeIdWiseName);
//
//    }

    private void loadLeaveBalanceReportApiCall(final String Branch_Id, final String Department_Id,
                                               final String user_id, final String Year, final String Month) {
        DialogUtils.showProgressDialog(EmployeeLeaveBalanceReportActivity.this, "");

        StringRequest academicContributionReportRequest = new StringRequest(Request.Method.POST, URLS.Get_leave_balance_Report, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        EmployeeLeaveBalanceReportPojo employeeLeaveBalanceReportPojo = gson.fromJson("{\"Data\":" + response + "}", EmployeeLeaveBalanceReportPojo.class);
                        if (employeeLeaveBalanceReportPojo.getData() != null && employeeLeaveBalanceReportPojo.getData().size() > 0) {

                            employeeLeaveBalanceReportPojoList = employeeLeaveBalanceReportPojo.getData();

                            Intent intent = new Intent(EmployeeLeaveBalanceReportActivity.this, EmployeeLeaveBalanceReportDetailActivity.class);
                            intent.putExtra(IntentConstants.EMPLOYEE_LEAVE_BALANCE_REPORT_POJO_LISt, (Serializable) employeeLeaveBalanceReportPojoList);
                            startActivity(intent);

                        } else {
                            Toast.makeText(EmployeeLeaveBalanceReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(EmployeeLeaveBalanceReportActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(EmployeeLeaveBalanceReportActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Branch_Id", Branch_Id);
                params2.put("Department_Id", Department_Id);
                params2.put("user_id", user_id);
                params2.put("Main_Branch_Or_Payroll_Branch", "1");
                params2.put("Year", Year);
                params2.put("Month", Month);
//                params2.put("Emp_Id", Emp_Id);//commented by remish varsani
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        academicContributionReportRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(academicContributionReportRequest);
    }

}
