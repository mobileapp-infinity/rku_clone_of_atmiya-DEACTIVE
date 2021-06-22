package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.ReportListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReportListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.CommanConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.ReportListModel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ReportListActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView rvReport;
    ArrayList<ReportListModel> reportListModelsArrayList;
    RecyclerView.LayoutManager layoutManager;
    ReportListAdapter reportListAdapter;
    ImageView ivBack;
    CustomBoldTextView txt_act,tv_emp_code;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_version;
    RequestQueue queue;
    ArrayList<ReportListPojo.DataBean> dataBeanArrayList;
    ArrayList<String> reportList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        getReportListApi(mySharedPreferecesRKUOLD.getUserID());

    }
    private void initView(){
        queue = Volley.newRequestQueue(this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Report List");

        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        rvReport = findViewById(R.id.rvReport);
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);


//        ReportListModel academicContributionReport = new ReportListModel();
//        academicContributionReport.setIcone(R.drawable.salary_slip_dra_black);
//        academicContributionReport.setReportTitle(this.getString(R.string.academic_contribution_report));
//        reportListModelsArrayList.add(academicContributionReport);
//
//        ReportListModel dailyAttendanceReport = new ReportListModel();
//        dailyAttendanceReport.setIcone(R.drawable.salary_slip_dra_black);
//        dailyAttendanceReport.setReportTitle(this.getString(R.string.daily_attendance_report));
//        reportListModelsArrayList.add(dailyAttendanceReport);
//
//        ReportListModel employeeDetailReport = new ReportListModel();
//        employeeDetailReport.setIcone(R.drawable.salary_slip_dra_black);
//        employeeDetailReport.setReportTitle(this.getString(R.string.employee_detail_report));
//        reportListModelsArrayList.add(employeeDetailReport);
//
//        ReportListModel leaveBalanceReport = new ReportListModel();
//        leaveBalanceReport.setIcone(R.drawable.salary_slip_dra_black);
//        leaveBalanceReport.setReportTitle(this.getString(R.string.leave_balance_report));
//        reportListModelsArrayList.add(leaveBalanceReport);
//
//        ReportListModel qualificationReport = new ReportListModel();
//        qualificationReport.setIcone(R.drawable.salary_slip_dra_black);
//        qualificationReport.setReportTitle(this.getString(R.string.qualification_report));
//        reportListModelsArrayList.add(qualificationReport);
//
//        ReportListModel employeePayrollDetailReport = new ReportListModel();
//        employeePayrollDetailReport.setIcone(R.drawable.salary_slip_dra_black);
//        employeePayrollDetailReport.setReportTitle(this.getString(R.string.employee_payroll_details_report));
//        reportListModelsArrayList.add(employeePayrollDetailReport);
//
//        ReportListModel employeeBranchTransferReport = new ReportListModel();
//        employeeBranchTransferReport.setIcone(R.drawable.salary_slip_dra_black);
//        employeeBranchTransferReport.setReportTitle(this.getString(R.string.employee_branch_transfer_report));
//        reportListModelsArrayList.add(employeeBranchTransferReport);
//
//        ReportListModel universitySeedMoneySummaryReport = new ReportListModel();
//        universitySeedMoneySummaryReport.setIcone(R.drawable.salary_slip_dra_black);
//        universitySeedMoneySummaryReport.setReportTitle(this.getString(R.string.university_seed_money_summary_report));
//        reportListModelsArrayList.add(universitySeedMoneySummaryReport);
//
//        ReportListModel employeeLateComingReport = new ReportListModel();
//        employeeLateComingReport.setIcone(R.drawable.salary_slip_dra_black);
//        employeeLateComingReport.setReportTitle(this.getString(R.string.employee_late_coming_report));
//        reportListModelsArrayList.add(employeeLateComingReport);
//
//        ReportListModel employeeMonthWiseMissPunchReport = new ReportListModel();
//        employeeMonthWiseMissPunchReport.setIcone(R.drawable.salary_slip_dra_black);
//        employeeMonthWiseMissPunchReport.setReportTitle(this.getString(R.string.employee_month_wise_miss_punch_report));
//        reportListModelsArrayList.add(employeeMonthWiseMissPunchReport);
//
//        ReportListModel employeeMonthWiseLateReport = new ReportListModel();
//        employeeMonthWiseLateReport.setIcone(R.drawable.salary_slip_dra_black);
//        employeeMonthWiseLateReport.setReportTitle(this.getString(R.string.employee_month_wise_late_report));
//        reportListModelsArrayList.add(employeeMonthWiseLateReport);
//
//        ReportListModel allEmployeeAttendanceReport = new ReportListModel();
//        allEmployeeAttendanceReport.setIcone(R.drawable.salary_slip_dra_black);
//        allEmployeeAttendanceReport.setReportTitle(this.getString(R.string.all_employee_attendance_report));
//        reportListModelsArrayList.add(allEmployeeAttendanceReport);
//
//        ReportListModel pdStatisticsReportForStaff = new ReportListModel();
//        pdStatisticsReportForStaff.setIcone(R.drawable.salary_slip_dra_black);
//        pdStatisticsReportForStaff.setReportTitle(this.getString(R.string.pd_statistics_report_for_staff));
//        reportListModelsArrayList.add(pdStatisticsReportForStaff);


//        reportListAdapter = new ReportListAdapter(this,reportListModelsArrayList);
//
//        rvReport.setLayoutManager(layoutManager);
//        rvReport.setAdapter(reportListAdapter);

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
        if (view.getId() == R.id.iv_back){
            onBackPressed();
        }
    }


    private void getReportListApi(final String User_id){

        DialogUtils.showProgressDialog(ReportListActivity.this, "");
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Get_Employee_wise_Report_right, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    ReportListPojo reportListPojo = gson.fromJson("{\"Data\":" + response + "}", ReportListPojo.class);
                    if (reportListPojo.getData() != null && reportListPojo.getData().size() > 0) {
                        dataBeanArrayList = (ArrayList<ReportListPojo.DataBean>) reportListPojo.getData();
                        reportList = new ArrayList<>();
                        for (int i = 0 ; i <dataBeanArrayList.size();i++){
                            if (!TextUtils.isEmpty(dataBeanArrayList.get(i).getMen_URL().trim())){
                                reportList.add(dataBeanArrayList.get(i).getMen_URL().trim());
                            }
                        }
                        showAndHideReport(reportList);
                    } else {
                        DialogUtils.Show_Toast(ReportListActivity.this, "No Data Found!");
                        finish();
                    }
                } else {
                    DialogUtils.Show_Toast(ReportListActivity.this, response);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReportListActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                finish();
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

    private void showAndHideReport(ArrayList<String> reportList){

        reportListModelsArrayList = new ArrayList<>();

        //As per discussion with rashmikant sir below report url is static
        //when we need or any requirement to add new Report than ask rashmikant sir for that Report URL and
        // also ask is that report is ready in web if ready than ask for url and create static variable for that
        // in comman constant file and put if condition as below to display report.

        if (reportList.contains(CommanConstants.FOR_ACADEMIC_CONTRIBUTION_REPORT)){
            ReportListModel academicContributionReport = new ReportListModel();
            academicContributionReport.setIcone(R.drawable.salary_slip_dra_black);
            academicContributionReport.setReportTitle(this.getString(R.string.academic_contribution_report));
            reportListModelsArrayList.add(academicContributionReport);
        }


        if (reportList.contains(CommanConstants.FOR_DAILY_ATTENDANCE_REPORT)){
            ReportListModel dailyAttendanceReport = new ReportListModel();
            dailyAttendanceReport.setIcone(R.drawable.salary_slip_dra_black);
            dailyAttendanceReport.setReportTitle(this.getString(R.string.daily_attendance_report));
            reportListModelsArrayList.add(dailyAttendanceReport);
        }

        if (reportList.contains(CommanConstants.FOR_EMPLOYEE_DETAIL_REPORT)){
            ReportListModel employeeDetailReport = new ReportListModel();
            employeeDetailReport.setIcone(R.drawable.salary_slip_dra_black);
            employeeDetailReport.setReportTitle(this.getString(R.string.employee_detail_report));
            reportListModelsArrayList.add(employeeDetailReport);
        }

        if (reportList.contains(CommanConstants.FOR_LEAVE_BALANCE_REPORT)){
            ReportListModel leaveBalanceReport = new ReportListModel();
            leaveBalanceReport.setIcone(R.drawable.salary_slip_dra_black);
            leaveBalanceReport.setReportTitle(this.getString(R.string.leave_balance_report));
            reportListModelsArrayList.add(leaveBalanceReport);
        }

        if (reportList.contains(CommanConstants.FOR_QUALIFICATION_REPORT)){
            ReportListModel qualificationReport = new ReportListModel();
            qualificationReport.setIcone(R.drawable.salary_slip_dra_black);
            qualificationReport.setReportTitle(this.getString(R.string.qualification_report));
            reportListModelsArrayList.add(qualificationReport);
        }

        if (reportList.contains(CommanConstants.FOR_EMPLOYEE_BRANCH_TRANSFER_REPORT)){
            ReportListModel employeeBranchTransferReport = new ReportListModel();
            employeeBranchTransferReport.setIcone(R.drawable.salary_slip_dra_black);
            employeeBranchTransferReport.setReportTitle(this.getString(R.string.employee_branch_transfer_report));
            reportListModelsArrayList.add(employeeBranchTransferReport);
        }

        if (reportList.contains(CommanConstants.FOR_UNIVERSITY_SEED_MONEY_SUMMARY_REPORT)){
            ReportListModel universitySeedMoneySummaryReport = new ReportListModel();
            universitySeedMoneySummaryReport.setIcone(R.drawable.salary_slip_dra_black);
            universitySeedMoneySummaryReport.setReportTitle(this.getString(R.string.university_seed_money_summary_report));
            reportListModelsArrayList.add(universitySeedMoneySummaryReport);
        }

        if (reportList.contains(CommanConstants.FOR_EMPLOYEE_LATE_COMMING_REPORT)){
            ReportListModel employeeLateComingReport = new ReportListModel();
            employeeLateComingReport.setIcone(R.drawable.salary_slip_dra_black);
            employeeLateComingReport.setReportTitle(this.getString(R.string.employee_late_coming_report));
            reportListModelsArrayList.add(employeeLateComingReport);
        }

        if (reportList.contains(CommanConstants.FOR_EMPLOYEE_MONTH_WISE_MISPUNCH_REPORT)){
            ReportListModel employeeMonthWiseMisPunchReport = new ReportListModel();
            employeeMonthWiseMisPunchReport.setIcone(R.drawable.salary_slip_dra_black);
            employeeMonthWiseMisPunchReport.setReportTitle(this.getString(R.string.employee_month_wise_miss_punch_report));
            reportListModelsArrayList.add(employeeMonthWiseMisPunchReport);
        }

        if (reportList.contains(CommanConstants.FOR_EMPLOYEE_MONTH_WISE_LATE_REPORT)){
            ReportListModel employeeMonthWiseLateReport = new ReportListModel();
            employeeMonthWiseLateReport.setIcone(R.drawable.salary_slip_dra_black);
            employeeMonthWiseLateReport.setReportTitle(this.getString(R.string.employee_month_wise_late_report));
            reportListModelsArrayList.add(employeeMonthWiseLateReport);
        }

        if (reportList.contains(CommanConstants.FOR_ALL_EMPLOYEE_ATTENDANCE_REPORT)){
            ReportListModel allEmployeeAttendanceReport = new ReportListModel();
            allEmployeeAttendanceReport.setIcone(R.drawable.salary_slip_dra_black);
            allEmployeeAttendanceReport.setReportTitle(this.getString(R.string.all_employee_attendance_report));
            reportListModelsArrayList.add(allEmployeeAttendanceReport);
        }

        if (reportList.contains(CommanConstants.FOR_PD_STATISTICS_FOR_STAFF)){
            ReportListModel pdStatisticsReportForStaff = new ReportListModel();
            pdStatisticsReportForStaff.setIcone(R.drawable.salary_slip_dra_black);
            pdStatisticsReportForStaff.setReportTitle(this.getString(R.string.pd_statistics_report_for_staff));
            reportListModelsArrayList.add(pdStatisticsReportForStaff);
        }

        reportListAdapter = new ReportListAdapter(this,reportListModelsArrayList);

        rvReport.setLayoutManager(layoutManager);
        rvReport.setAdapter(reportListAdapter);
    }

}
