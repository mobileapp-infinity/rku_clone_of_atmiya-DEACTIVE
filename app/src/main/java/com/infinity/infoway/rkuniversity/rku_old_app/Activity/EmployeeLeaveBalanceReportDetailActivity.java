package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.EmployeeLeaveBalanceReportPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.List;

public class EmployeeLeaveBalanceReportDetailActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;
    List<EmployeeLeaveBalanceReportPojo.DataBean> employeeLeaveBalanceReportPojoList;
    
    LinearLayout ll_employee_leave_balance_report_details;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_leave_balance_report_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        
        if (getIntent().hasExtra(IntentConstants.EMPLOYEE_LEAVE_BALANCE_REPORT_POJO_LISt)){
            employeeLeaveBalanceReportPojoList = (List<EmployeeLeaveBalanceReportPojo.DataBean>) getIntent().getSerializableExtra(IntentConstants.EMPLOYEE_LEAVE_BALANCE_REPORT_POJO_LISt);
            addRowDyanimically();
        }else {
            Toast.makeText(this, "Leave balance report null or empty", Toast.LENGTH_SHORT).show();
            finish();
        }
        
    }

    private void initView(){
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Leave Balance Report");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        queue = Volley.newRequestQueue(this);

        ll_employee_leave_balance_report_details = findViewById(R.id.ll_employee_leave_balance_report_details);
        
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

    void addRowDyanimically() {

        for (int l = 0; l < employeeLeaveBalanceReportPojoList.size(); l++) {

            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,26.2f);

            LinearLayout llEmployeeLeaveBalanceReportDetailRow = new LinearLayout(EmployeeLeaveBalanceReportDetailActivity.this);
            llEmployeeLeaveBalanceReportDetailRow.setOrientation(LinearLayout.HORIZONTAL);
            llEmployeeLeaveBalanceReportDetailRow.setLayoutParams(layoutParamsForll);

            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 4f);
            CustomBoldTextView tvForEmpName = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
            tvForEmpName.setTextSize(15);
            tvForEmpName.setPadding(4, 4, 4, 4);
            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
            tvForEmpName.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForEmpName.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getEmp_name()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvForEmpName);


            LinearLayout.LayoutParams tvCLHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2.7f);
            CustomBoldTextView tvCL = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvCL.setLayoutParams(tvCLHeaderAndValueLayoutParam);
            tvCL.setTextColor(getResources().getColor(R.color.black));
            tvCL.setTextSize(15);
            tvCL.setPadding(4, 4, 4, 4);
            tvCL.setGravity(Gravity.CENTER);
            tvCL.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvCL.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_cl()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvCL);


            LinearLayout.LayoutParams tvMLHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2.7f);
            CustomBoldTextView tvML = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvML.setLayoutParams(tvMLHeaderAndValueLayoutParam);
            tvML.setTextColor(getResources().getColor(R.color.black));
            tvML.setTextSize(15);
            tvML.setPadding(4, 4, 4, 4);
            tvML.setGravity(Gravity.CENTER);
            tvML.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvML.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_ml()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvML);

            LinearLayout.LayoutParams tvELHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2f);
            CustomBoldTextView tvEL = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvEL.setLayoutParams(tvELHeaderAndValueLayoutParam);
            tvEL.setTextColor(getResources().getColor(R.color.black));
            tvEL.setTextSize(15);
            tvEL.setPadding(4, 4, 4, 4);
            tvEL.setGravity(Gravity.CENTER);
            tvEL.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvEL.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_el()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvEL);


            LinearLayout.LayoutParams tvCOFFHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2.6f);
            CustomBoldTextView tvCOFF = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvCOFF.setLayoutParams(tvCOFFHeaderAndValueLayoutParam);
            tvCOFF.setTextColor(getResources().getColor(R.color.black));
            tvCOFF.setTextSize(15);
            tvCOFF.setPadding(4, 4, 4, 4);
            tvCOFF.setGravity(Gravity.CENTER);
            tvCOFF.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvCOFF.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_coff()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvCOFF);


            LinearLayout.LayoutParams tvRHHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2.4f);
            CustomBoldTextView tvRH = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvRH.setLayoutParams(tvRHHeaderAndValueLayoutParam);
            tvRH.setTextColor(getResources().getColor(R.color.black));
            tvRH.setTextSize(15);
            tvRH.setPadding(4, 4, 4, 4);
            tvRH.setGravity(Gravity.CENTER);
            tvRH.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvRH.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_rh()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvRH);

            LinearLayout.LayoutParams tvLWPHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2.2f);
            CustomBoldTextView tvLWP = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvLWP.setLayoutParams(tvLWPHeaderAndValueLayoutParam);
            tvLWP.setTextColor(getResources().getColor(R.color.black));
            tvLWP.setTextSize(15);
            tvLWP.setPadding(4, 4, 4, 4);
            tvLWP.setGravity(Gravity.CENTER);
            tvLWP.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvLWP.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_lwp()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvLWP);


            LinearLayout.LayoutParams tvLWPSHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2.8f);
            CustomBoldTextView tvLWPS = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvLWPS.setLayoutParams(tvLWPSHeaderAndValueLayoutParam);
            tvLWPS.setTextColor(getResources().getColor(R.color.black));
            tvLWPS.setTextSize(15);
            tvLWPS.setPadding(4, 4, 4, 4);
            tvLWPS.setGravity(Gravity.CENTER);
            tvLWPS.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvLWPS.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_lwps()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvLWPS);


            LinearLayout.LayoutParams tvSVLSHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2.6f);
            CustomBoldTextView tvSVLS = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvSVLS.setLayoutParams(tvSVLSHeaderAndValueLayoutParam);
            tvSVLS.setTextColor(getResources().getColor(R.color.black));
            tvSVLS.setTextSize(15);
            tvSVLS.setPadding(4, 4, 4, 4);
            tvSVLS.setGravity(Gravity.CENTER);
            tvSVLS.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvSVLS.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_svls()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvSVLS);


            LinearLayout.LayoutParams tvODSHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2.2f);
            CustomBoldTextView tvODS = new CustomBoldTextView(EmployeeLeaveBalanceReportDetailActivity.this);
            tvODS.setLayoutParams(tvODSHeaderAndValueLayoutParam);
            tvODS.setTextColor(getResources().getColor(R.color.black));
            tvODS.setTextSize(15);
            tvODS.setPadding(4, 4, 4, 4);
            tvODS.setGravity(Gravity.CENTER);
            tvODS.setBackground(ContextCompat.getDrawable(EmployeeLeaveBalanceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvODS.setText(String.valueOf(employeeLeaveBalanceReportPojoList.get(l).getTotal_ods()));

            llEmployeeLeaveBalanceReportDetailRow.addView(tvODS);


            ll_employee_leave_balance_report_details.addView(llEmployeeLeaveBalanceReportDetailRow);
        }

    }
    
}
