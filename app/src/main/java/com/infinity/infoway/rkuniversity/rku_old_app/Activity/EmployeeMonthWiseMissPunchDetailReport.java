package com.infinity.infoway.rkuniversity.rku_old_app.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetEmployeeMonthWiseMisPunchReportPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.List;


public class EmployeeMonthWiseMissPunchDetailReport extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<GetEmployeeMonthWiseMisPunchReportPojo.DataBean> getEmployeeMonthWiseMissPunchReportPojoList;

    LinearLayout ll_employee_month_wise_miss_punch_report,ll_mail_emp_month_wise_miss_pinch_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_month_wise_miss_punch_detail_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.EMPLOYEE_MONTH_WISE_MIS_PUNCH_REPORT_DEATILS)) {
            getEmployeeMonthWiseMissPunchReportPojoList = (List<GetEmployeeMonthWiseMisPunchReportPojo.DataBean>) getIntent().getSerializableExtra(IntentConstants.EMPLOYEE_MONTH_WISE_MIS_PUNCH_REPORT_DEATILS);
            addRowDyanimically();
        } else {
            Toast.makeText(this, "No Data Found!", Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Employee Month Wise Mis Punch Report");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_employee_month_wise_miss_punch_report = findViewById(R.id.ll_employee_month_wise_miss_punch_report);
        ll_mail_emp_month_wise_miss_pinch_report = findViewById(R.id.ll_mail_emp_month_wise_miss_pinch_report);

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
        }
    }

    void addRowDyanimically() {

        for (int l = 0; l < getEmployeeMonthWiseMissPunchReportPojoList.size(); l++) {

            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 50f);

            LinearLayout llEmployeeMonthWiseMissPunchReportRow = new LinearLayout(EmployeeMonthWiseMissPunchDetailReport.this);
            llEmployeeMonthWiseMissPunchReportRow.setOrientation(LinearLayout.HORIZONTAL);
            llEmployeeMonthWiseMissPunchReportRow.setLayoutParams(layoutParamsForll);

            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 8);
            CustomBoldTextView tvForEmpName = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
            tvForEmpName.setTextSize(15);
            tvForEmpName.setPadding(4, 4, 4, 4);
            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
            tvForEmpName.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvForEmpName.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getEmployee_Name()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvForEmpName);


            LinearLayout.LayoutParams janHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvJan = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvJan.setLayoutParams(janHeaderAndValueLayoutParam);
            tvJan.setTextColor(getResources().getColor(R.color.black));
            tvJan.setTextSize(15);
            tvJan.setPadding(4, 4, 4, 4);
            tvJan.setGravity(Gravity.CENTER);
            tvJan.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvJan.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJAN()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvJan);

            LinearLayout.LayoutParams febHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvfeb = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvfeb.setLayoutParams(febHeaderAndValueLayoutParam);
            tvfeb.setTextColor(getResources().getColor(R.color.black));
            tvfeb.setTextSize(15);
            tvfeb.setPadding(4, 4, 4, 4);
            tvfeb.setGravity(Gravity.CENTER);
            tvfeb.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvfeb.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getFEB()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvfeb);

            LinearLayout.LayoutParams marHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3.2f);
            CustomBoldTextView tvMar = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvMar.setLayoutParams(marHeaderAndValueLayoutParam);
            tvMar.setTextColor(getResources().getColor(R.color.black));
            tvMar.setTextSize(15);
            tvMar.setPadding(4, 4, 4, 4);
            tvMar.setGravity(Gravity.CENTER);
            tvMar.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvMar.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getMAR()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvMar);

            LinearLayout.LayoutParams aprHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvApr = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvApr.setLayoutParams(aprHeaderAndValueLayoutParam);
            tvApr.setTextColor(getResources().getColor(R.color.black));
            tvApr.setTextSize(15);
            tvApr.setPadding(4, 4, 4, 4);
            tvApr.setGravity(Gravity.CENTER);
            tvApr.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvApr.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAPI()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvApr);

            LinearLayout.LayoutParams mayHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 4f);
            CustomBoldTextView tvMay = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvMay.setLayoutParams(mayHeaderAndValueLayoutParam);
            tvMay.setTextColor(getResources().getColor(R.color.black));
            tvMay.setTextSize(15);
            tvMay.setPadding(4, 4, 4, 4);
            tvMay.setGravity(Gravity.CENTER);
            tvMay.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvMay.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getMAY()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvMay);

            LinearLayout.LayoutParams junHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvJun = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvJun.setLayoutParams(junHeaderAndValueLayoutParam);
            tvJun.setTextColor(getResources().getColor(R.color.black));
            tvJun.setTextSize(15);
            tvJun.setPadding(4, 4, 4, 4);
            tvJun.setGravity(Gravity.CENTER);
            tvJun.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvJun.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJUN()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvJun);

            LinearLayout.LayoutParams julHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvJul = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvJul.setLayoutParams(julHeaderAndValueLayoutParam);
            tvJul.setTextColor(getResources().getColor(R.color.black));
            tvJul.setTextSize(15);
            tvJul.setPadding(4, 4, 4, 4);
            tvJul.setGravity(Gravity.CENTER);
            tvJul.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvJul.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJUL()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvJul);


            LinearLayout.LayoutParams augHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvAug = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvAug.setLayoutParams(augHeaderAndValueLayoutParam);
            tvAug.setTextColor(getResources().getColor(R.color.black));
            tvAug.setTextSize(15);
            tvAug.setPadding(4, 4, 4, 4);
            tvAug.setGravity(Gravity.CENTER);
            tvAug.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvAug.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAUG()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvAug);


            LinearLayout.LayoutParams sepHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvSep = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvSep.setLayoutParams(sepHeaderAndValueLayoutParam);
            tvSep.setTextColor(getResources().getColor(R.color.black));
            tvSep.setTextSize(15);
            tvSep.setPadding(4, 4, 4, 4);
            tvSep.setGravity(Gravity.CENTER);
            tvSep.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvSep.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getSEP()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvSep);


            LinearLayout.LayoutParams octHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvOct = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvOct.setLayoutParams(octHeaderAndValueLayoutParam);
            tvOct.setTextColor(getResources().getColor(R.color.black));
            tvOct.setTextSize(15);
            tvOct.setPadding(4, 4, 4, 4);
            tvOct.setGravity(Gravity.CENTER);
            tvOct.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvOct.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getOCT()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvOct);


            LinearLayout.LayoutParams novHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3.3f);
            CustomBoldTextView tvNov = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvNov.setLayoutParams(novHeaderAndValueLayoutParam);
            tvNov.setTextColor(getResources().getColor(R.color.black));
            tvNov.setTextSize(15);
            tvNov.setPadding(4, 4, 4, 4);
            tvNov.setGravity(Gravity.CENTER);
            tvNov.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvNov.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getNOV()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvNov);


            LinearLayout.LayoutParams decHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            CustomBoldTextView tvDec = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvDec.setLayoutParams(decHeaderAndValueLayoutParam);
            tvDec.setTextColor(getResources().getColor(R.color.black));
            tvDec.setTextSize(15);
            tvDec.setPadding(4, 4, 4, 4);
            tvDec.setGravity(Gravity.CENTER);
            tvDec.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvDec.setText(String.valueOf(getEmployeeMonthWiseMissPunchReportPojoList.get(l).getDEC()));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvDec);


            int total = getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJAN() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getFEB() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getMAR() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAPI() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getMAY() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJUN() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getJUL() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getAUG() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getSEP() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getOCT() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getNOV() +
                    getEmployeeMonthWiseMissPunchReportPojoList.get(l).getDEC();

            LinearLayout.LayoutParams totalHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 4.5f);
            CustomBoldTextView tvTotal = new CustomBoldTextView(EmployeeMonthWiseMissPunchDetailReport.this);
            tvTotal.setLayoutParams(totalHeaderAndValueLayoutParam);
            tvTotal.setTextColor(getResources().getColor(R.color.black));
            tvTotal.setTextSize(15);
            tvTotal.setPadding(4, 4, 4, 4);
            tvTotal.setGravity(Gravity.CENTER);
            tvTotal.setBackground(ContextCompat.getDrawable(EmployeeMonthWiseMissPunchDetailReport.this,
                    R.drawable.bg_header_ac_report));
            tvTotal.setText(String.valueOf(total));

            llEmployeeMonthWiseMissPunchReportRow.addView(tvTotal);

            ll_employee_month_wise_miss_punch_report.addView(llEmployeeMonthWiseMissPunchReportRow);


            if (l == getEmployeeMonthWiseMissPunchReportPojoList.size() - 1) {
                DialogUtils.hideProgressDialog();
                ll_mail_emp_month_wise_miss_pinch_report.setVisibility(View.VISIBLE);
            }
        }
    }
}
