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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.EmployeeLateComingReportPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.List;

public class EmployeeLateCommingDetailReportActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;
    List<EmployeeLateComingReportPojo.DataBean> emlpoyeeLateComingReportPojoList;
    LinearLayout ll_employee_late_coming_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_late_comming_detail_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.EMPLOYEE_LATE_COMMING_REPORT_DETAILS)) {
            emlpoyeeLateComingReportPojoList = (List<EmployeeLateComingReportPojo.DataBean>) getIntent().getSerializableExtra(IntentConstants.EMPLOYEE_LATE_COMMING_REPORT_DETAILS);
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
        txt_act.setText("Employee Late Coming Report");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_employee_late_coming_report = findViewById(R.id.ll_employee_late_coming_report);

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


        for (int l = 0; l < emlpoyeeLateComingReportPojoList.size(); l++) {

            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2);

            LinearLayout llLateComingReportRow = new LinearLayout(EmployeeLateCommingDetailReportActivity.this);
            llLateComingReportRow.setOrientation(LinearLayout.HORIZONTAL);
            llLateComingReportRow.setLayoutParams(layoutParamsForll);

            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.2f);
            CustomBoldTextView tvForEmpName = new CustomBoldTextView(EmployeeLateCommingDetailReportActivity.this);
            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
            tvForEmpName.setTextSize(15);
            tvForEmpName.setPadding(4, 4, 4, 4);
            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
            tvForEmpName.setBackground(ContextCompat.getDrawable(EmployeeLateCommingDetailReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForEmpName.setText(String.valueOf(emlpoyeeLateComingReportPojoList.get(l).getName()));

            llLateComingReportRow.addView(tvForEmpName);

            LinearLayout.LayoutParams lateByHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 0.8f);
            CustomBoldTextView tvForLateBy = new CustomBoldTextView(EmployeeLateCommingDetailReportActivity.this);
            tvForLateBy.setLayoutParams(lateByHeaderAndValueLayoutParam);
            tvForLateBy.setTextColor(getResources().getColor(R.color.black));
            tvForLateBy.setTextSize(15);
            tvForLateBy.setPadding(4, 4, 4, 4);
            tvForLateBy.setGravity(Gravity.CENTER_VERTICAL);
            tvForLateBy.setBackground(ContextCompat.getDrawable(EmployeeLateCommingDetailReportActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForLateBy.setText(String.valueOf(emlpoyeeLateComingReportPojoList.get(l).getTotal_late_hours()));

            llLateComingReportRow.addView(tvForLateBy);

            ll_employee_late_coming_report.addView(llLateComingReportRow);
        }

    }
}
