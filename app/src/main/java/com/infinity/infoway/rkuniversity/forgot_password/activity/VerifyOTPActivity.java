package com.infinity.infoway.rkuniversity.forgot_password.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.CheckLoginByOTPAndUsernamePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOTPActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseVerifyOTP;
    OtpView otp_view;
    TextViewRegularFont btnVerifyOtpAndProceed;
    String instituteId = "";
    String userName = "";
    String userType = "";
    String userId = "";
    String mobileNo = "";
    TextViewMediumFont tvEnteredMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);
        initView();

        if (getIntent().hasExtra(IntentConstants.INSTITUTE_ID_FOR_VERIFY_OTP)) {
            instituteId = getIntent().getStringExtra(IntentConstants.INSTITUTE_ID_FOR_VERIFY_OTP);
        }

        if (getIntent().hasExtra(IntentConstants.USERNAME_FOR_VERIFY_OTP)) {
            userName = getIntent().getStringExtra(IntentConstants.USERNAME_FOR_VERIFY_OTP);
        }

        if (getIntent().hasExtra(IntentConstants.FACULTY_OR_STUDENT)) {
            userType = getIntent().getStringExtra(IntentConstants.FACULTY_OR_STUDENT);
        }

        if (getIntent().hasExtra(IntentConstants.USER_ID_VERIFY_OTP)) {
            userId = getIntent().getStringExtra(IntentConstants.USER_ID_VERIFY_OTP);
        }

        if (getIntent().hasExtra(IntentConstants.ENTERED_MOBILE_NO)) {
            mobileNo = getIntent().getStringExtra(IntentConstants.ENTERED_MOBILE_NO);
            tvEnteredMobileNo.setText("+91  - " + mobileNo);
        } else {
            tvEnteredMobileNo.setText("+91  - " + "xxxxxxxxxx");
        }
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(VerifyOTPActivity.this);
        connectionDetector = new ConnectionDetector(VerifyOTPActivity.this);
        ivCloseVerifyOTP = findViewById(R.id.ivCloseVerifyOTP);
        ivCloseVerifyOTP.setOnClickListener(this);
        otp_view = findViewById(R.id.otp_view);
        btnVerifyOtpAndProceed = findViewById(R.id.btnVerifyOtpAndProceed);
        btnVerifyOtpAndProceed.setOnClickListener(this);
        tvEnteredMobileNo = findViewById(R.id.tvEnteredMobileNo);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivCloseVerifyOTP) {
            onBackPressed();
        } else if (view.getId() == R.id.btnVerifyOtpAndProceed) {
            String otpText = otp_view.getOTP();
            if (!CommonUtil.checkIsEmptyOrNullCommon(otpText) &&
                    otpText.length() == 6) {
                //new Call
                checkLoginByOTPAndUsernameAPI(userType, userName, otpText, instituteId, "1");

            } else {
                Toast.makeText(this, "Please enter 6 digit OTP.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void checkLoginByOTPAndUsernameAPI(String user_type, String username, String otp, String institute_id, String ip_addr) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(VerifyOTPActivity.this, "");
            ApiImplementer.checkLoginByOTPAndUsernameAPIImplementer(user_type, username, otp, institute_id, ip_addr, new Callback<CheckLoginByOTPAndUsernamePojo>() {
                @Override
                public void onResponse(Call<CheckLoginByOTPAndUsernamePojo> call, Response<CheckLoginByOTPAndUsernamePojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        CheckLoginByOTPAndUsernamePojo checkLoginByOTPAndUsernamePojo = response.body();
                        String forgotPassUserName = "";
                        if (userType.trim().equals(String.valueOf(CommonUtil.LOGIN_TYPE_STUDENT))) {
                            setStudentLoginData(checkLoginByOTPAndUsernamePojo.getTable().get(0));
                            Toast.makeText(VerifyOTPActivity.this, "OTP verified Successfully ", Toast.LENGTH_SHORT).show();
                            if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getName())) {
                                forgotPassUserName = checkLoginByOTPAndUsernamePojo.getTable().get(0).getName();
                            }
                            redirectToResetPasswordActivity(userId, institute_id, user_type, forgotPassUserName);
                        } else if (userType.trim().equals(String.valueOf(CommonUtil.LOGIN_TYPE_FACULTY))) {
                            setEmployeeLoginData(checkLoginByOTPAndUsernamePojo.getTable().get(0));
                            Toast.makeText(VerifyOTPActivity.this, "OTP verified Successfully ", Toast.LENGTH_SHORT).show();
                            if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojo.getTable().get(0).getName())) {
                                forgotPassUserName = checkLoginByOTPAndUsernamePojo.getTable().get(0).getName();
                            }
                            redirectToResetPasswordActivity(userId, institute_id, user_type, forgotPassUserName);
                        }
                    } else {
                        Toast.makeText(VerifyOTPActivity.this, "Incorrect OTP,Please enter correct OTP.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CheckLoginByOTPAndUsernamePojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(VerifyOTPActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void redirectToResetPasswordActivity(String userId, String instituteId, String userType, String forgotPasswordUserName) {
        Intent intent = new Intent(VerifyOTPActivity.this, ResetPasswordActivity.class);
        intent.putExtra(IntentConstants.RESET_PASS_USER_ID, userId);
        intent.putExtra(IntentConstants.FACULTY_OR_STUDENT, userType);
        intent.putExtra(IntentConstants.RESET_PASS_INSTITUTE_ID, instituteId);
        intent.putExtra(IntentConstants.FORGOT_PASSWORD_USER_NAME, forgotPasswordUserName);
        startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_RESET_PASSWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK &&
                requestCode == IntentConstants.REQUEST_CODE_FOR_RESET_PASSWORD) {
            Intent intent = new Intent(VerifyOTPActivity.this, ForgotPasswordActivity.class);
            boolean isOTPVerifiedAndResetPass = false;
            if (data.hasExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS)) {
                isOTPVerifiedAndResetPass = data.getBooleanExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, false);
            }
            intent.putExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, isOTPVerifiedAndResetPass);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    private void setStudentLoginData(CheckLoginByOTPAndUsernamePojo.Table checkLoginByOTPAndUsernamePojoTable) {

        if (checkLoginByOTPAndUsernamePojoTable.getStudUserName() != null) {
            mySharedPreferences.setStudentUsername(checkLoginByOTPAndUsernamePojoTable.getStudUserName() + "");
        }
//        mySharedPreferences.setStudentPassword(password);
        mySharedPreferences.setLoginUserType(CommonUtil.LOGIN_TYPE_STUDENT);

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsernamePojoTable.getStudId())) {
            mySharedPreferences.setStudentId(checkLoginByOTPAndUsernamePojoTable.getStudId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getDmId() != null) {
            mySharedPreferences.setDMId(checkLoginByOTPAndUsernamePojoTable.getDmId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getDmFullName() != null) {
            mySharedPreferences.setDmFullName(checkLoginByOTPAndUsernamePojoTable.getDmFullName());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getCourseId() != null) {
            mySharedPreferences.setCourseId(checkLoginByOTPAndUsernamePojoTable.getCourseId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getCourseFullname() != null) {
            mySharedPreferences.setCourseFullName(checkLoginByOTPAndUsernamePojoTable.getCourseFullname());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getSmId() != null) {
            mySharedPreferences.setSmId(checkLoginByOTPAndUsernamePojoTable.getSmId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getSmName() != null) {
            mySharedPreferences.setSmName(checkLoginByOTPAndUsernamePojoTable.getSmName());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getAcId() != null) {
            mySharedPreferences.setAcId(checkLoginByOTPAndUsernamePojoTable.getAcId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getAcFullName() != null) {
            mySharedPreferences.setAcFullName(checkLoginByOTPAndUsernamePojoTable.getAcFullName());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getSwdYearId() != null) {
            mySharedPreferences.setSwdYearId(checkLoginByOTPAndUsernamePojoTable.getSwdYearId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getAcCode() != null) {
            mySharedPreferences.setAcCode(checkLoginByOTPAndUsernamePojoTable.getAcCode() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getHostelCode() != null) {
            mySharedPreferences.setHostelCode(checkLoginByOTPAndUsernamePojoTable.getHostelCode() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getName() != null) {
            mySharedPreferences.setStudentName(checkLoginByOTPAndUsernamePojoTable.getName());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getStudAdmissionNo() != null) {
            mySharedPreferences.setStudAdmissionNo(checkLoginByOTPAndUsernamePojoTable.getStudAdmissionNo());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getStudEnrollmentNo() != null) {
            mySharedPreferences.setStudentEnrollmentNo(checkLoginByOTPAndUsernamePojoTable.getStudEnrollmentNo());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getStudPhoto() != null) {
            mySharedPreferences.setStudentPhotoUrl(checkLoginByOTPAndUsernamePojoTable.getStudPhoto());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getStatus() != null) {
            mySharedPreferences.setStatus(checkLoginByOTPAndUsernamePojoTable.getStatus() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getSwdDivisionId() != null) {
            mySharedPreferences.setSwdDivisionId(checkLoginByOTPAndUsernamePojoTable.getSwdDivisionId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getSwdBatchId() != null) {
            mySharedPreferences.setSwdBatchId(checkLoginByOTPAndUsernamePojoTable.getSwdBatchId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getShiftId() != null) {
            mySharedPreferences.setShiftId(checkLoginByOTPAndUsernamePojoTable.getShiftId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getImDomainName() != null) {
            mySharedPreferences.setImDomainName(checkLoginByOTPAndUsernamePojoTable.getImDomainName());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getInstituteId() != null) {
            mySharedPreferences.setInstituteId(checkLoginByOTPAndUsernamePojoTable.getInstituteId() + "");
        }

        if (checkLoginByOTPAndUsernamePojoTable.getFcFile() != null) {
            mySharedPreferences.setFcFile(checkLoginByOTPAndUsernamePojoTable.getFcFile());
        }

        if (checkLoginByOTPAndUsernamePojoTable.getImExamDbName() != null) {
            mySharedPreferences.setImExamDbName(checkLoginByOTPAndUsernamePojoTable.getImExamDbName());
        }
    }

    private void setEmployeeLoginData(CheckLoginByOTPAndUsernamePojo.Table checkLoginByOTPAndUsername) {

        if (checkLoginByOTPAndUsername.getEmpUsername() != null) {
            mySharedPreferences.setEmpUserName(checkLoginByOTPAndUsername.getEmpUsername());
        }
//        mySharedPreferences.setEmpPassword(empPassword);

//        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getLoginUserType())) {
        mySharedPreferences.setLoginUserType(CommonUtil.LOGIN_TYPE_FACULTY);
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getEmpId())) {
            mySharedPreferences.setEmpId(checkLoginByOTPAndUsername.getEmpId() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getIsAdmin())) {
            mySharedPreferences.setEmpIsAdminOrNot(checkLoginByOTPAndUsername.getIsAdmin() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getEmpNumber())) {
            mySharedPreferences.setEmpNumber(checkLoginByOTPAndUsername.getEmpNumber() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getName())) {
            mySharedPreferences.setEmpName(checkLoginByOTPAndUsername.getName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getEmpMainSchoolId())) {
            mySharedPreferences.setEmpMainSchoolId(checkLoginByOTPAndUsername.getEmpMainSchoolId() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getAcFullName())) {
            mySharedPreferences.setAcFullName(checkLoginByOTPAndUsername.getAcFullName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getAcLogo())) {
            mySharedPreferences.setEmpAcLogo(checkLoginByOTPAndUsername.getAcLogo() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getStudPhoto())) {
            mySharedPreferences.setStudentPhotoUrl(checkLoginByOTPAndUsername.getStudPhoto() + "");
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getStatus())) {
//            mySharedPreferences.setEmpStatus(checkLoginByOTPAndUsername.getStatus() + "");
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getAcCode())) {
            mySharedPreferences.setEmpAcCode(checkLoginByOTPAndUsername.getAcCode() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getIsDirector())) {
            mySharedPreferences.setEmpIsDirectory(checkLoginByOTPAndUsername.getIsDirector() + "");
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getEmpId())) {
//            mySharedPreferences.setEmpId(checkLoginByOTPAndUsername.getEmpId() + "");
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getEmpYearId())) {
            mySharedPreferences.setEmpYearId(checkLoginByOTPAndUsername.getEmpYearId() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getInstituteId())) {
            mySharedPreferences.setEmpInstituteId(checkLoginByOTPAndUsername.getInstituteId() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getImDomainName())) {
            mySharedPreferences.setEmpImDomainName(checkLoginByOTPAndUsername.getImDomainName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getEmpUserStatus())) {
            mySharedPreferences.setEmpUserStatus(checkLoginByOTPAndUsername.getEmpUserStatus() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getEmpPermenantCollege())) {
            mySharedPreferences.setEmpPermanentCollege(checkLoginByOTPAndUsername.getEmpPermenantCollege() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(checkLoginByOTPAndUsername.getEmpDepartmentId())) {
            mySharedPreferences.setEmpDepartmentId(checkLoginByOTPAndUsername.getEmpDepartmentId() + "");
        }
    }
}