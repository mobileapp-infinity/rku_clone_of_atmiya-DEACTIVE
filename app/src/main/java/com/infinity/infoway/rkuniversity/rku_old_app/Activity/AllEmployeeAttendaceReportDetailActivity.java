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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.AllEmployeeAttendanceReportpojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.List;

public class AllEmployeeAttendaceReportDetailActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    List<AllEmployeeAttendanceReportpojo.DataBean> allEmployeeAttendanceReportPojoList;

    LinearLayout ll_main_all_emp_attendance_report_details, ll_all_emp_attendance_report_header_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_employee_attendace_report_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (getIntent().hasExtra(IntentConstants.ALL_EMP_ATTENDANCE_REPORT)) {
            allEmployeeAttendanceReportPojoList = (List<AllEmployeeAttendanceReportpojo.DataBean>) getIntent().getSerializableExtra(IntentConstants.ALL_EMP_ATTENDANCE_REPORT);
            DialogUtils.showProgressDialog(AllEmployeeAttendaceReportDetailActivity.this, "");
            addRowDyanimically(allEmployeeAttendanceReportPojoList);
        } else {
            Toast.makeText(this, "All Employee Attendance List Empty or Null", Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("All Employee Attendance Report");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        ll_main_all_emp_attendance_report_details = findViewById(R.id.ll_main_all_emp_attendance_report_details);
        ll_all_emp_attendance_report_header_value = findViewById(R.id.ll_all_emp_attendance_report_header_value);

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

    void addRowDyanimically(List<AllEmployeeAttendanceReportpojo.DataBean> allEmployeeAttendanceReportPojoList) {

        for (int l = 0; l < allEmployeeAttendanceReportPojoList.size(); l++) {

            LinearLayout.LayoutParams layoutParamsForll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 11.2f);

            LinearLayout llAllEmpAttendanceReportRow = new LinearLayout(AllEmployeeAttendaceReportDetailActivity.this);
            llAllEmpAttendanceReportRow.setOrientation(LinearLayout.HORIZONTAL);
            llAllEmpAttendanceReportRow.setLayoutParams(layoutParamsForll);

            LinearLayout.LayoutParams empNameHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2);
            CustomBoldTextView tvForEmpName = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvForEmpName.setLayoutParams(empNameHeaderAndValueLayoutParam);
            tvForEmpName.setTextColor(getResources().getColor(R.color.black));
            tvForEmpName.setTextSize(15);
            tvForEmpName.setPadding(4, 4, 4, 4);
            tvForEmpName.setGravity(Gravity.CENTER_VERTICAL);
            tvForEmpName.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvForEmpName.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getEmployee_name()));
            llAllEmpAttendanceReportRow.addView(tvForEmpName);

            LinearLayout.LayoutParams aHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            CustomBoldTextView tvA = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvA.setLayoutParams(aHeaderAndValueLayoutParam);
            tvA.setTextColor(getResources().getColor(R.color.black));
            tvA.setTextSize(15);
            tvA.setPadding(4, 4, 4, 4);
            tvA.setGravity(Gravity.CENTER);
            tvA.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvA.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getA()));
            llAllEmpAttendanceReportRow.addView(tvA);


            LinearLayout.LayoutParams haHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            CustomBoldTextView tvHA = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvHA.setLayoutParams(haHeaderAndValueLayoutParam);
            tvHA.setTextColor(getResources().getColor(R.color.black));
            tvHA.setTextSize(15);
            tvHA.setPadding(4, 4, 4, 4);
            tvHA.setGravity(Gravity.CENTER);
            tvHA.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvHA.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getHA()));

            llAllEmpAttendanceReportRow.addView(tvHA);

            LinearLayout.LayoutParams hlHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            CustomBoldTextView tvHL = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvHL.setLayoutParams(hlHeaderAndValueLayoutParam);
            tvHL.setTextColor(getResources().getColor(R.color.black));
            tvHL.setTextSize(15);
            tvHL.setPadding(4, 4, 4, 4);
            tvHL.setGravity(Gravity.CENTER);
            tvHL.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvHL.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getHL()));

            llAllEmpAttendanceReportRow.addView(tvHL);

            LinearLayout.LayoutParams lHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            CustomBoldTextView tvL = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvL.setLayoutParams(lHeaderAndValueLayoutParam);
            tvL.setTextColor(getResources().getColor(R.color.black));
            tvL.setTextSize(15);
            tvL.setPadding(4, 4, 4, 4);
            tvL.setGravity(Gravity.CENTER);
            tvL.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvL.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getL()));

            llAllEmpAttendanceReportRow.addView(tvL);

            LinearLayout.LayoutParams nilHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1.2f);
            CustomBoldTextView tvNIL = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvNIL.setLayoutParams(nilHeaderAndValueLayoutParam);
            tvNIL.setTextColor(getResources().getColor(R.color.black));
            tvNIL.setTextSize(15);
            tvNIL.setPadding(4, 4, 4, 4);
            tvNIL.setGravity(Gravity.CENTER);
            tvNIL.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvNIL.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getNIL()));

            llAllEmpAttendanceReportRow.addView(tvNIL);

            LinearLayout.LayoutParams pHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            CustomBoldTextView tvP = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvP.setLayoutParams(pHeaderAndValueLayoutParam);
            tvP.setTextColor(getResources().getColor(R.color.black));
            tvP.setTextSize(15);
            tvP.setPadding(4, 4, 4, 4);
            tvP.setGravity(Gravity.CENTER);
            tvP.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvP.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getP()));

            llAllEmpAttendanceReportRow.addView(tvP);

            LinearLayout.LayoutParams ptHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            CustomBoldTextView tvPT = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvPT.setLayoutParams(ptHeaderAndValueLayoutParam);
            tvPT.setTextColor(getResources().getColor(R.color.black));
            tvPT.setTextSize(15);
            tvPT.setPadding(4, 4, 4, 4);
            tvPT.setGravity(Gravity.CENTER);
            tvPT.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvPT.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getPT()));

            llAllEmpAttendanceReportRow.addView(tvPT);


            LinearLayout.LayoutParams tHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            CustomBoldTextView tvT = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvT.setLayoutParams(tHeaderAndValueLayoutParam);
            tvT.setTextColor(getResources().getColor(R.color.black));
            tvT.setTextSize(15);
            tvT.setPadding(4, 4, 4, 4);
            tvT.setGravity(Gravity.CENTER);
            tvT.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvT.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getT()));

            llAllEmpAttendanceReportRow.addView(tvT);


            LinearLayout.LayoutParams woHeaderAndValueLayoutParam = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            CustomBoldTextView tvWO = new CustomBoldTextView(AllEmployeeAttendaceReportDetailActivity.this);
            tvWO.setLayoutParams(woHeaderAndValueLayoutParam);
            tvWO.setTextColor(getResources().getColor(R.color.black));
            tvWO.setTextSize(15);
            tvWO.setPadding(4, 4, 4, 4);
            tvWO.setGravity(Gravity.CENTER);
            tvWO.setBackground(ContextCompat.getDrawable(AllEmployeeAttendaceReportDetailActivity.this,
                    R.drawable.bg_header_ac_report));
            tvWO.setText(String.valueOf(allEmployeeAttendanceReportPojoList.get(l).getWO()));

            llAllEmpAttendanceReportRow.addView(tvWO);
            

            ll_all_emp_attendance_report_header_value.addView(llAllEmpAttendanceReportRow);


            if (l == allEmployeeAttendanceReportPojoList.size() - 1) {
                DialogUtils.hideProgressDialog();
                ll_main_all_emp_attendance_report_details.setVisibility(View.VISIBLE);
                DialogUtils.hideProgressDialog();
            }
        }
    }


}
