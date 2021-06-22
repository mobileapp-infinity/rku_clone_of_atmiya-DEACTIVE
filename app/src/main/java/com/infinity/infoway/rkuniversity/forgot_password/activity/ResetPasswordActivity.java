package com.infinity.infoway.rkuniversity.forgot_password.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.ResetUserPasswordPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseResetPassword;
    AppCompatEditText edtNewPassword;
    AppCompatEditText edtConfirmPassword;
    TextViewRegularFont btnUpdatePassword;
    TextViewRegularFont tvForgotPasswordUserName;

    String userId = "";
    String userType = "";
    String instituteId = "";
    String forgotPasswordUserName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initView();

        if (getIntent().hasExtra(IntentConstants.FORGOT_PASSWORD_USER_NAME)) {
            forgotPasswordUserName = getIntent().getStringExtra(IntentConstants.FORGOT_PASSWORD_USER_NAME);
            tvForgotPasswordUserName.setText(forgotPasswordUserName);
        }

        if (getIntent().hasExtra(IntentConstants.RESET_PASS_USER_ID)) {
            userId = getIntent().getStringExtra(IntentConstants.RESET_PASS_USER_ID);
        }

        if (getIntent().hasExtra(IntentConstants.FACULTY_OR_STUDENT)) {
            userType = getIntent().getStringExtra(IntentConstants.FACULTY_OR_STUDENT);
        }

        if (getIntent().hasExtra(IntentConstants.RESET_PASS_INSTITUTE_ID)) {
            instituteId = getIntent().getStringExtra(IntentConstants.RESET_PASS_INSTITUTE_ID);
        }

    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(ResetPasswordActivity.this);
        connectionDetector = new ConnectionDetector(ResetPasswordActivity.this);
        ivCloseResetPassword = findViewById(R.id.ivCloseResetPassword);
        ivCloseResetPassword.setOnClickListener(this);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnUpdatePassword = findViewById(R.id.btnUpdatePassword);
        btnUpdatePassword.setOnClickListener(this);
        tvForgotPasswordUserName = findViewById(R.id.tvForgotPasswordUserName);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseResetPassword) {
            onBackPressed();
        } else if (v.getId() == R.id.btnUpdatePassword) {
            if (isValid()) {
                CommonUtil.hideKeyboardCommon(ResetPasswordActivity.this);
                resetUserPasswordAPI(userType, userId, instituteId, edtConfirmPassword.getText().toString().trim(), "1");
            }
        }
    }

    private boolean isValid() {
        boolean isValid = true;
        if (CommonUtil.checkIsEmptyOrNullCommon(edtNewPassword.getText().toString().trim())) {
            isValid = false;
            Toast.makeText(this, "Please enter new password.", Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.checkIsEmptyOrNullCommon(edtConfirmPassword.getText().toString().trim())) {
            isValid = false;
            Toast.makeText(this, "Please enter confirm password.", Toast.LENGTH_SHORT).show();
        } else if (!edtNewPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString())) {
            isValid = false;
            Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResetPasswordActivity.this, VerifyOTPActivity.class);
        intent.putExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, false);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void resetUserPasswordAPI(String user_type, String user_id, String institute_id, String password, String ip_addr) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(ResetPasswordActivity.this, "");
            ApiImplementer.resetUserPasswordAPIImplementer(user_type, user_id, institute_id, password, ip_addr, new Callback<ResetUserPasswordPojo>() {
                @Override
                public void onResponse(Call<ResetUserPasswordPojo> call, Response<ResetUserPasswordPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().getTable().get(0).getErrorCode().equals("1")) {
                        mySharedPreferences.logoutStudentOrFaulty();
                        Toast.makeText(ResetPasswordActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, VerifyOTPActivity.class);
                        intent.putExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, true);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    } else {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(ResetPasswordActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResetUserPasswordPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ResetPasswordActivity.this, "Request failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }
}