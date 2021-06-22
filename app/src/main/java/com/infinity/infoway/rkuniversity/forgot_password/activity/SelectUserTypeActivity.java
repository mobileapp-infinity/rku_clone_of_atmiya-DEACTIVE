package com.infinity.infoway.rkuniversity.forgot_password.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.api.Urls;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetInstituteFromDomainPojo;
import com.infinity.infoway.rkuniversity.login.activity.LoginActivity;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectUserTypeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llIAmAnEmployee;
    LinearLayout llIAmAStudent;
    ConnectionDetector connectionDetector;
    final Handler handler = new Handler();

    MySharedPreferences mySharedPreferences;
    String imOtpBasedForgotPassword, instituteId;
    AppCompatImageView ivCloseForSelectYourRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        initView();
        getInstituteFromDomainUrlApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(SelectUserTypeActivity.this);
        connectionDetector = new ConnectionDetector(SelectUserTypeActivity.this);
        llIAmAnEmployee = findViewById(R.id.llIAmAnEmployee);
        llIAmAnEmployee.setOnClickListener(this);
        llIAmAStudent = findViewById(R.id.llIAmAStudent);
        llIAmAStudent.setOnClickListener(this);
        ivCloseForSelectYourRole = findViewById(R.id.ivCloseForSelectYourRole);
        ivCloseForSelectYourRole.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llIAmAnEmployee) {
            llIAmAnEmployee.setBackground(ContextCompat.getDrawable(SelectUserTypeActivity.this, R.drawable.shape_select_employee_student_filled));
            llIAmAStudent.setBackground(ContextCompat.getDrawable(SelectUserTypeActivity.this, R.drawable.shape_select_employee_student_common));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    redirectToForgotPasswordActivity(CommonUtil.LOGIN_TYPE_FACULTY);
                }
            }, 50);
        } else if (v.getId() == R.id.llIAmAStudent) {
            llIAmAnEmployee.setBackground(ContextCompat.getDrawable(SelectUserTypeActivity.this, R.drawable.shape_select_employee_student_common));
            llIAmAStudent.setBackground(ContextCompat.getDrawable(SelectUserTypeActivity.this, R.drawable.shape_select_employee_student_filled));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    redirectToForgotPasswordActivity(CommonUtil.LOGIN_TYPE_STUDENT);
                }
            }, 50);
        } else if (v.getId() == R.id.ivCloseForSelectYourRole) {
            onBackPressed();
        }
    }


    private void redirectToForgotPasswordActivity(int selectedUserType) {

        Intent intent = new Intent(SelectUserTypeActivity.this, ForgotPasswordActivity.class);
        intent.putExtra(IntentConstants.FACULTY_OR_STUDENT, selectedUserType);
        intent.putExtra(IntentConstants.INSTITUTE_ID_FOR_FORGET_PASSWORD, instituteId);
        intent.putExtra(IntentConstants.IM_OTP_BASE_FORGET_PASSWORD, imOtpBasedForgotPassword);
        startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_FORGOT_PASSWORD);
    }


    //(1)Api added on 18-1-2021 by harsh
    private void getInstituteFromDomainUrlApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(SelectUserTypeActivity.this, "");
            ApiImplementer.getInstituteFromDomainApiImplementer(Urls.DOMAIN_NAME_URL_FOR_FORGOT_PASSWORD, new Callback<GetInstituteFromDomainPojo>() {
                @Override
                public void onResponse(Call<GetInstituteFromDomainPojo> call, Response<GetInstituteFromDomainPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        imOtpBasedForgotPassword = response.body().getTable().get(0).getImOtpBaseForgetPwd() + "";
                        instituteId = response.body().getTable().get(0).getImId() + "";

                    } else {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(SelectUserTypeActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<GetInstituteFromDomainPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(SelectUserTypeActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK &&
                requestCode == IntentConstants.REQUEST_CODE_FOR_FORGOT_PASSWORD) {
            Intent intent = new Intent(SelectUserTypeActivity.this, LoginActivity.class);

            boolean isOTPVerifiedAndResetPass = false;

            if (data.hasExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS)) {
                isOTPVerifiedAndResetPass = data.getBooleanExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, false);
            }

            intent.putExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, isOTPVerifiedAndResetPass);
            setResult(Activity.RESULT_OK, intent);
            finish();

        }
    }
}
