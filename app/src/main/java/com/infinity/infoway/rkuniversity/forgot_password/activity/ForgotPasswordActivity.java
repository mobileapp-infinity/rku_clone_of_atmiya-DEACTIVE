package com.infinity.infoway.rkuniversity.forgot_password.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.api.Urls;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetForgetPasswordDetailByUserIDPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetUserWiseDetailForgetPasswordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertForgetPasswordSendSMSRecordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.UpdateUserOTPAtForgetPasswordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.adapter.RegisterEmployeeListAdapter;
import com.infinity.infoway.rkuniversity.forgot_password.adapter.RegisterStudentListAdapter;
import com.infinity.infoway.rkuniversity.forgot_password.adapter.RegisterStudentListIfOTPBasedVerificationAdapter;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetSMSApiForApplicationPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetStudentForgotPasswordDetailsPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertForgotPasswordOTPSmsRecordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertStudentPasswordAndSMSAbsentApiCall;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.OtpBaseLoginDetailsForStudentPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.UpdateStudentForgotPasswordOtpPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener,
        RegisterStudentListAdapter.IUserListDialog,
        RegisterEmployeeListAdapter.IRegisterEmployeeList,
        RegisterStudentListIfOTPBasedVerificationAdapter.ISelectRegisterStudentIfOTPBasedLogin {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseForgotPassword;
    AppCompatEditText edtEnterRegisterMobileNo;
    TextViewRegularFont btnSendMsg;
    WebView webview;
    private String SMS_URL = "";
    ArrayList<GetForgetPasswordDetailByUserIDPojo> getForgetPasswordDetailByUserIDPojoArrayList;
    RequestQueue queue;
    private RegisterStudentListBottomSheetDialog registerStudentListBottomSheetDialog;
    private RegisteredEmployeeListBottomSheetDialog registeredEmployeeListBottomSheetDialog;
    //    private RegisterStudentListIfOTPBasedVerificationBottomSheet registerStudentListIfOTPBasedVerificationBottomSheet;
    private Intent otpBasedForgetPasswordIntent, instituteIdIntent, facultyOrStudentIntent;
    String otpBaseForgetPassword, instituteId, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_forgot_password);
        initView();
        otpBaseForgetPassword = otpBasedForgetPasswordIntent.getStringExtra(IntentConstants.IM_OTP_BASE_FORGET_PASSWORD);
        instituteId = instituteIdIntent.getStringExtra(IntentConstants.INSTITUTE_ID_FOR_FORGET_PASSWORD);
        userType = String.valueOf(facultyOrStudentIntent.getIntExtra(IntentConstants.FACULTY_OR_STUDENT, 0));
    }

    private void initView() {
        queue = Volley.newRequestQueue(this);
        mySharedPreferences = new MySharedPreferences(ForgotPasswordActivity.this);
        connectionDetector = new ConnectionDetector(ForgotPasswordActivity.this);
        ivCloseForgotPassword = findViewById(R.id.ivCloseForgotPassword);
        ivCloseForgotPassword.setOnClickListener(this);
        edtEnterRegisterMobileNo = findViewById(R.id.edtEnterRegisterMobileNo);
        btnSendMsg = findViewById(R.id.btnSendMsg);
        btnSendMsg.setOnClickListener(this);

        webview = new WebView(ForgotPasswordActivity.this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());
        if (Build.VERSION.SDK_INT >= 21) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//            webview.getSettings().setDefaultTextEncodingName("utf-8");
        }
        otpBasedForgetPasswordIntent = getIntent();
        instituteIdIntent = getIntent();
        facultyOrStudentIntent = getIntent();
    }


    private boolean isValid() {
        boolean isValid = true;

        if (CommonUtil.checkIsEmptyOrNullCommon(edtEnterRegisterMobileNo.getText().toString().trim())) {
            isValid = false;
            Toast.makeText(this, "Please enter mobile no.", Toast.LENGTH_SHORT).show();
        } else if (edtEnterRegisterMobileNo.getText().toString().trim().length() != 10) {
            isValid = false;
            Toast.makeText(this, "Please enter valid mobile no.", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseForgotPassword) {
            onBackPressed();
        } else if (v.getId() == R.id.btnSendMsg) {
            if (isValid()) {
                CommonUtil.hideKeyboardCommon(ForgotPasswordActivity.this);
                //getInstituteFromDomainUrlApiCall();
                if (otpBaseForgetPassword.contentEquals("1")) {
                    getUserWiseDetailForForgetPasswordUsingOTPAPI(edtEnterRegisterMobileNo.getText().toString().trim(), userType);
                } else {
                    getStudentForgetPasswordDetailsUsingMailApiCall(false, true, edtEnterRegisterMobileNo.getText().toString().trim());
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getStudentForgetPasswordDetailsUsingMailApiCall(boolean isPdShow, boolean isPdHide, final String email) {

        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
        }
        ApiImplementer.getStudentForgetPasswordDetailsApiImplementer(email, new Callback<ArrayList<GetStudentForgotPasswordDetailsPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetStudentForgotPasswordDetailsPojo>> call, Response<ArrayList<GetStudentForgotPasswordDetailsPojo>> response) {
                if (isPdHide) {
                    DialogUtil.hideProgressDialog();
                }
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    try {
                        ArrayList<GetStudentForgotPasswordDetailsPojo> getStudentForgotPasswordDetailsPojoArrayList = response.body();
                        if (!CommonUtil.checkIsEmptyOrNullCommon(getStudentForgotPasswordDetailsPojoArrayList.get(0).getUsername()) &&
                                !CommonUtil.checkIsEmptyOrNullCommon(getStudentForgotPasswordDetailsPojoArrayList.get(0).getName())) {
                            showRegisterUserListDialog(getStudentForgotPasswordDetailsPojoArrayList);
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "Mobile no not registered!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(ForgotPasswordActivity.this, "Exception:-" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Response Code:- " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetStudentForgotPasswordDetailsPojo>> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(ForgotPasswordActivity.this, "Request Failed:-" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void closeUserListDialog(String emp_stud_id, String emp_stud_status) {
        if (registerStudentListBottomSheetDialog != null) {
            registerStudentListBottomSheetDialog.dismiss();
            getForgetPasswordDetailByUserID(emp_stud_id, userType, Urls.DOMAIN_NAME);
        }
    }

    private void getForgetPasswordDetailByUserID(final String user_id, final String user_type, final String domain_name) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");

            ApiImplementer.getForgetPasswordDetailByUserIDImplementer(user_id, user_type, domain_name, new Callback<ArrayList<GetForgetPasswordDetailByUserIDPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<GetForgetPasswordDetailByUserIDPojo>> call, Response<ArrayList<GetForgetPasswordDetailByUserIDPojo>> response) {

                    //                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        getForgetPasswordDetailByUserIDPojoArrayList = response.body();

                        String encrypted_pwd_config = getForgetPasswordDetailByUserIDPojoArrayList.get(0).getEncryptedPwdConfig().toString().trim();

                        if (encrypted_pwd_config.equalsIgnoreCase("1")) {
                            if (!TextUtils.isEmpty(getForgetPasswordDetailByUserIDPojoArrayList.get(0).getInstituteId().toString())) {
                                getSMSApiForApplicationApiCall(false, true, getForgetPasswordDetailByUserIDPojoArrayList.get(0).getInstituteId().toString());
                            } else {
                                DialogUtil.hideProgressDialog();
                                Toast.makeText(ForgotPasswordActivity.this, "Institute_Id not found!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            if (getForgetPasswordDetailByUserIDPojoArrayList.get(0).getStatus().toString().trim().equalsIgnoreCase("1")) {
                                String username = getForgetPasswordDetailByUserIDPojoArrayList.get(0).getUsername().toString().trim();
                                String password = getForgetPasswordDetailByUserIDPojoArrayList.get(0).getPassword().toString().trim();

//                                "http://message.smartwave.co.in/rest/services/sendSMS/sendGroupSms?AUTH_KEY=c564eac8f430fd3023e1c046e286885b&senderId=ATMIYA&routeId=1&smsContentType=english&message=addmessage&mobileNos=addmobileno"//New response url

//                                String webUrl = "http://message.smartwave.co.in/rest/services/sendSMS/sendGroupSms?AUTH_KEY=c564eac8f430fd3023e1c046e286885b&senderId=ATMIYA&routeId=1&smsContentType=english&message= Username :" + username + "  Password :" + password + "&mobileNos=" + edtEnterRegisterMobileNo.getText().toString().trim();//OLD URL

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getForgetPasswordDetailByUserIDPojoArrayList.get(0).getSmsApi())) {
                                    String webUrl = getForgetPasswordDetailByUserIDPojoArrayList.get(0).getSmsApi();

                                    String msgForSMSProvider = " Username :" + username + "  Password :" + password;

                                    webUrl = webUrl.replace("addmessage", msgForSMSProvider);

                                    webUrl = webUrl.replace("addmobileno", edtEnterRegisterMobileNo.getText().toString().trim());

                                    webview.loadUrl(webUrl, null);
                                    Toast.makeText(ForgotPasswordActivity.this, "SMS sent", Toast.LENGTH_LONG).show();
                                    finish();

                                } else {
                                    Toast.makeText(ForgotPasswordActivity.this, "SMS Url not found!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "Your mobile number is not registered", Toast.LENGTH_LONG).show();
                            }
                        }

                    } else {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(ForgotPasswordActivity.this, "Response Code:- " + response.code(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<GetForgetPasswordDetailByUserIDPojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ForgotPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getSMSApiForApplicationApiCall(boolean isPdShow, boolean isPdHide, String institute_id) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
            }
            ApiImplementer.getSMSApiForApplicationApiImplementer(institute_id, new Callback<ArrayList<GetSMSApiForApplicationPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<GetSMSApiForApplicationPojo>> call, Response<ArrayList<GetSMSApiForApplicationPojo>> response) {
                    if (isPdHide) {
                        DialogUtil.hideProgressDialog();
                    }
                    try {
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            ArrayList<GetSMSApiForApplicationPojo> getSMSApiForApplicationPojoArrayList = response.body();
                            SMS_URL = getSMSApiForApplicationPojoArrayList.get(0).getSmsApi();

                            if (!TextUtils.isEmpty(SMS_URL)) {
                                String webUrl = SMS_URL.replace("addmessage", getForgetPasswordDetailByUserIDPojoArrayList.get(0).getForgetPwdLink());

                                webUrl = webUrl.replace("addmobileno", edtEnterRegisterMobileNo.getText().toString().trim());

                                String urlStr = webUrl;
                                URL url = new URL(urlStr);
                                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                url = uri.toURL();

//                                String encodedQueryString = URLEncoder.encode(webUrl,"UTF-8");

                                StringRequest request = new StringRequest(Request.Method.GET, url.toString(), new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(ForgotPasswordActivity.this, "SMS sent", Toast.LENGTH_LONG).show();
                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(ForgotPasswordActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                queue.add(request);
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "SMS Url Not found!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<GetSMSApiForApplicationPojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ForgotPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    private void showRegisterUserListDialog(ArrayList<GetStudentForgotPasswordDetailsPojo> getStudentForgotPasswordDetailsPojoArrayList) {
        registerStudentListBottomSheetDialog = new RegisterStudentListBottomSheetDialog(ForgotPasswordActivity.this, getStudentForgotPasswordDetailsPojoArrayList);
        registerStudentListBottomSheetDialog.show(this.getSupportFragmentManager(), "Select Register Student");
    }

    @Override
    public void onRegisterEmployeeSelected(GetUserWiseDetailForgetPasswordPojo.TableBean tableBean, String instituteId) {
        registeredEmployeeListBottomSheetDialog.dismiss();

        final String RANDOM_6_DIGIT_OTP = CommonUtil.getRandom6DigitOTP();

        if (!CommonUtil.checkIsEmptyOrNullCommon(instituteId)) {

            try {

                if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getSms_api())) {
                    SMS_URL = tableBean.getSms_api();
                    msg = SMS_URL.replace("addmessage", RANDOM_6_DIGIT_OTP + " is your One Time Password for Login " +
                            "in CMS. Do not share this with anyone." + "");
                    msg = msg.replace("addmobileno", edtEnterRegisterMobileNo.getText().toString().trim());

                    URL url = new URL(msg);
                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                    url = uri.toURL();

                    StringRequest request = new StringRequest(Request.Method.GET, url.toString(), new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(ForgotPasswordActivity.this, "SMS sent", Toast.LENGTH_LONG).show();
                            updateUserOTPAtForgetPasswordAPI(userType, tableBean.getUser_id() + "", RANDOM_6_DIGIT_OTP,
                                    "1", tableBean.getUsername(), edtEnterRegisterMobileNo.getText().toString().trim());
                            insertForgetPasswordSendSMSRecord(msg, tableBean.getUser_id() + "", userType,
                                    edtEnterRegisterMobileNo.getText().toString().trim());
                        }
                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ForgotPasswordActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(request);

                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "SMS Url Not found!", Toast.LENGTH_SHORT).show();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } else {
            Toast.makeText(this, "instituteId not found!", Toast.LENGTH_SHORT).show();
        }
    }

    String msg = "";

    private void getSMSApiForApplicationApiCallIfLoginIsOTPBased(boolean isPdShow, boolean isPdHide,
                                                                 String institute_id, final String otpText, final String userTypeId, final String mobileNo,
                                                                 String isEmp, String isStudent,
                                                                 String userName) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
            }
            ApiImplementer.getSMSApiForApplicationApiImplementer(institute_id, new Callback<ArrayList<GetSMSApiForApplicationPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<GetSMSApiForApplicationPojo>> call, Response<ArrayList<GetSMSApiForApplicationPojo>> response) {
                    if (isPdHide) {
                        DialogUtil.hideProgressDialog();
                    }
                    try {
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            ArrayList<GetSMSApiForApplicationPojo> getSMSApiForApplicationPojoArrayList = response.body();
                            SMS_URL = getSMSApiForApplicationPojoArrayList.get(0).getSmsApi();

                            if (!TextUtils.isEmpty(SMS_URL)) {
                                msg = SMS_URL.replace("addmessage", otpText + " is your One Time Password for Login " +
                                        "in CMS. Do not share this with anyone." + "");
                                msg = msg.replace("addmobileno", edtEnterRegisterMobileNo.getText().toString().trim());

                                URL url = new URL(msg);
                                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                                url = uri.toURL();

                                String sendUrlToServer = url.toString();

//                                String encodedQueryString = URLEncoder.encode(webUrl,"UTF-8");

                                StringRequest request = new StringRequest(Request.Method.GET, url.toString(), new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //  redirectToOTPVerificationScreen(institute_id, userName, isEmp + "", isStudent + "", mobileNo);
                                        insertStudentPasswordAndSmsAbsentApiCall(sendUrlToServer, userTypeId, isEmp, isStudent, mobileNo, msg, "1", userTypeId, otpText);
                                        Toast.makeText(ForgotPasswordActivity.this, "SMS sent", Toast.LENGTH_LONG).show();
                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(ForgotPasswordActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                queue.add(request);
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "SMS Url Not found!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<GetSMSApiForApplicationPojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ForgotPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void insertStudentPasswordAndSmsAbsentApiCall(String sms, String user_id, String is_emp, String is_stud, String mobile_no,
                                                          String otp_msg, String created_ip, String created_by, String otpText) {
        ApiImplementer.insertStudentPasswordAndSendSmsAbsentApiImplementer(sms, new Callback<InsertStudentPasswordAndSMSAbsentApiCall>() {
            @Override
            public void onResponse(Call<InsertStudentPasswordAndSMSAbsentApiCall> call, Response<InsertStudentPasswordAndSMSAbsentApiCall> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                    insertForgotPasswordOTPSmsRecordApiCall(user_id, is_emp, is_stud, mobile_no, otp_msg, created_ip, created_by, otpText);
                }
            }

            @Override
            public void onFailure(Call<InsertStudentPasswordAndSMSAbsentApiCall> call, Throwable t) {

            }
        });
    }

    private void insertForgotPasswordOTPSmsRecordApiCall(String user_id, String is_emp, String is_stud, String mobile_no,
                                                         String otp_msg, String created_ip, String created_by, String otpText) {
        ApiImplementer.insertForgotPasswordOTPSmsRecordApiApiImplementer(user_id, is_emp, is_stud, mobile_no, otp_msg, created_ip, created_by, new Callback<InsertForgotPasswordOTPSmsRecordPojo>() {
            @Override
            public void onResponse(Call<InsertForgotPasswordOTPSmsRecordPojo> call, Response<InsertForgotPasswordOTPSmsRecordPojo> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                    updateStudentEmployeeFogetPasswordOTPApiCall(user_id, is_emp, is_stud, otpText, created_by);
                }
            }

            @Override
            public void onFailure(Call<InsertForgotPasswordOTPSmsRecordPojo> call, Throwable t) {

            }
        });
    }

    private void updateStudentEmployeeFogetPasswordOTPApiCall(String user_id, String is_emp, String is_stud,
                                                              String otp, String modify_by) {
        ApiImplementer.updateStudentEmpForgotPasswordOTPApiImplementer(user_id, is_emp, is_stud, otp, "1", modify_by, new Callback<UpdateStudentForgotPasswordOtpPojo>() {
            @Override
            public void onResponse(Call<UpdateStudentForgotPasswordOtpPojo> call, Response<UpdateStudentForgotPasswordOtpPojo> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {

                }
            }

            @Override
            public void onFailure(Call<UpdateStudentForgotPasswordOtpPojo> call, Throwable t) {

            }
        });
    }

    private void redirectToOTPVerificationScreen(String instituteId, String userName, String mobileNo, String userType, String user_id) {
        Intent intent = new Intent(ForgotPasswordActivity.this, VerifyOTPActivity.class);
        intent.putExtra(IntentConstants.INSTITUTE_ID_FOR_VERIFY_OTP, instituteId);
        intent.putExtra(IntentConstants.USERNAME_FOR_VERIFY_OTP, userName);
        intent.putExtra(IntentConstants.USER_ID_VERIFY_OTP, user_id);
        intent.putExtra(IntentConstants.ENTERED_MOBILE_NO, mobileNo);
        intent.putExtra(IntentConstants.FACULTY_OR_STUDENT, userType);
        startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_VERIFY_OTP);
    }

    @Override
    public void onOtpBasedVerificationRegisteredStudentSelected(OtpBaseLoginDetailsForStudentPojo.TableBean tableBean, String instituteId) {
//        registerStudentListIfOTPBasedVerificationBottomSheet.dismiss();
        final String RANDOM_6_DIGIT_OTP = CommonUtil.getRandom6DigitOTP();
        getSMSApiForApplicationApiCallIfLoginIsOTPBased(true, true, instituteId, RANDOM_6_DIGIT_OTP,
                tableBean.getStudId().toString(), tableBean.getStudMobileNo(), "0", "1",
                tableBean.getStudUserName() + "");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK &&
                requestCode == IntentConstants.REQUEST_CODE_FOR_VERIFY_OTP) {
            Intent intent = new Intent(ForgotPasswordActivity.this, SelectUserTypeActivity.class);
            boolean isOTPVerifiedAndResetPass = false;
            if (data.hasExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS)) {
                isOTPVerifiedAndResetPass = data.getBooleanExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, false);
            }
            intent.putExtra(IntentConstants.IS_OTP_VERIFIED_AND_RESENT_PASS, isOTPVerifiedAndResetPass);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }


    //Added by harsh on 18-1-2021
    private void getUserWiseDetailForForgetPasswordUsingOTPAPI(String uname_mobile, String user_type) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
            ApiImplementer.getUserWiseDetailForForgetPasswordAPIImplementer(uname_mobile, user_type, Urls.DOMAIN_NAME, new Callback<GetUserWiseDetailForgetPasswordPojo>() {
                @Override
                public void onResponse(Call<GetUserWiseDetailForgetPasswordPojo> call, Response<GetUserWiseDetailForgetPasswordPojo> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        DialogUtil.hideProgressDialog();
                        GetUserWiseDetailForgetPasswordPojo getUserWiseDetailForgetPasswordPojo = response.body();
                        if (!CommonUtil.checkIsEmptyOrNullCommon(getUserWiseDetailForgetPasswordPojo.getTable().get(0).getMobile_no())) {
                            if (getUserWiseDetailForgetPasswordPojo.getTable().size() > 0) {
                                registeredEmployeeListBottomSheetDialog = new RegisteredEmployeeListBottomSheetDialog(ForgotPasswordActivity.this, getUserWiseDetailForgetPasswordPojo, instituteId);
                                registeredEmployeeListBottomSheetDialog.show(ForgotPasswordActivity.this.getSupportFragmentManager(), "Select Registered User");
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(ForgotPasswordActivity.this, "Mobile no not registered.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(ForgotPasswordActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetUserWiseDetailForgetPasswordPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ForgotPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }


    private void insertForgetPasswordSendSMSRecord(String sms, String user_id, String user_type, String mobile_no) {
        if (connectionDetector.isConnectingToInternet()) {
//            DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
            ApiImplementer.insertForgetPasswordSendSMSRecordImplementer(sms, user_id, user_type, mobile_no, "1", new Callback<InsertForgetPasswordSendSMSRecordPojo>() {
                @Override
                public void onResponse(Call<InsertForgetPasswordSendSMSRecordPojo> call, Response<InsertForgetPasswordSendSMSRecordPojo> response) {
//                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().get(0).getErrorCode().equals("1")) {
                    }
                }

                @Override
                public void onFailure(Call<InsertForgetPasswordSendSMSRecordPojo> call, Throwable t) {
//                    DialogUtil.hideProgressDialog();
                }
            });

        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }


    private void updateUserOTPAtForgetPasswordAPI(String user_type, String user_id, String otp, String ip_addr, String userName, String mobile_no) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(ForgotPasswordActivity.this, "");
            ApiImplementer.updateUserOTPAtForgetPasswordAPIImplementer(user_type, user_id, otp, ip_addr, new Callback<UpdateUserOTPAtForgetPasswordPojo>() {
                @Override
                public void onResponse(Call<UpdateUserOTPAtForgetPasswordPojo> call, Response<UpdateUserOTPAtForgetPasswordPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().get(0).getErrorCode().equals("1")) {
                        redirectToOTPVerificationScreen(instituteId, userName, mobile_no, user_type, user_id);
                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UpdateUserOTPAtForgetPasswordPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(ForgotPasswordActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }


    }
}