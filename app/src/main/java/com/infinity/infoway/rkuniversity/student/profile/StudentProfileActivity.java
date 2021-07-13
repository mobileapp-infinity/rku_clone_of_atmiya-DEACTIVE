package com.infinity.infoway.rkuniversity.student.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.Animations;
import com.infinity.infoway.rkuniversity.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.login.pojo.LogOutPojo;
import com.infinity.infoway.rkuniversity.student.student_dashboard.activity.StudentDashboardActivity;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentProfileActivity extends AppCompatActivity implements View.OnClickListener {


    MySharedPreferences mySharedPreferences;

    AppCompatImageView ivCloseProfile, imgStudentLogout;

    LinearLayout llExpandedHeaderPersonalDetailsProfile;
    AppCompatImageView ivViewMoreBtnPersonalDetailProfile;
    LinearLayout llExpandableLayoutPersonalDetailsProfile;
    boolean isPersonalDetailsExpanded = true;

    LinearLayout llExpandedHeaderAcademicDetailsProfile;
    AppCompatImageView ivViewMoreBtnAcademicDetailsProfile;
    LinearLayout llExpandableLayoutAcademicDetailsProfile;
    boolean isAcademicDetailsExpanded = false;

    LinearLayout llExpandedHeaderContactDetailsProfile;
    AppCompatImageView ivViewMoreBtnContactDetailsProfile;
    LinearLayout llExpandableLayoutContactDetailsProfile;
    boolean isContactDetailsExpanded = false;


    private CircleImageView student_profile_image;
    private TextViewRegularFont mTvStudentName;
    private TextViewRegularFont mTvStudentEmail;
    //    private TextViewMediumFont mTvStudentUserId;
    private TextViewMediumFont mTvStudentEnrollmentNo;
    private TextViewMediumFont mTvStudentSem;
    private TextViewMediumFont mTvStudentGender;
    private TextViewMediumFont mTvStudentDOB;
    private TextViewMediumFont mTvStudentAadhaarCardNo;
    private TextViewMediumFont mTvStudentMobileNo;
    private TextViewMediumFont mTvStudentCollegeName;
    private TextViewMediumFont mTvStudentDepartment;
    private TextViewMediumFont mTvStudentCourse;
    private TextViewMediumFont mTvStudentCourseNew;
    private TextViewMediumFont mTvStudentDivision;
    private TextViewMediumFont mTvStudentRollNo;
    private TextViewMediumFont mTvStudentAdmissionNo;
    private TextViewMediumFont mTvStudentadmissionYear;
    private TextViewMediumFont mTvStudentAtmissionType;
    private TextViewMediumFont mTvStudentInternetUsername;
    private TextViewMediumFont mTvStudentInternetPassword;
    private TextViewMediumFont mTvStudentPermenentAddress;
    private TextViewMediumFont mTvStudentPresentAddress;
    private TextViewMediumFont mTvStudentFatherMobileNo;
    private TextViewMediumFont mTvStudentCityName;
    private TextViewMediumFont mTvStudentStateName;
    private TextViewMediumFont mTvStudentCountryName;

    ConnectionDetector connectionDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        initView();
        getStudentProfileAndAttendanceData();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentProfileActivity.this);
        connectionDetector = new ConnectionDetector(StudentProfileActivity.this);
        ivCloseProfile = findViewById(R.id.ivCloseProfile);
        ivCloseProfile.setOnClickListener(this);

        imgStudentLogout = findViewById(R.id.imgStudentLogout);
        imgStudentLogout.setOnClickListener(this);

        llExpandedHeaderPersonalDetailsProfile = findViewById(R.id.llExpandedHeaderPersonalDetailsProfile);
        llExpandedHeaderPersonalDetailsProfile.setOnClickListener(this);
        ivViewMoreBtnPersonalDetailProfile = findViewById(R.id.ivViewMoreBtnPersonalDetailProfile);
        llExpandableLayoutPersonalDetailsProfile = findViewById(R.id.llExpandableLayoutPersonalDetailsProfile);

        llExpandedHeaderAcademicDetailsProfile = findViewById(R.id.llExpandedHeaderAcademicDetailsProfile);
        llExpandedHeaderAcademicDetailsProfile.setOnClickListener(this);
        ivViewMoreBtnAcademicDetailsProfile = findViewById(R.id.ivViewMoreBtnAcademicDetailsProfile);
        llExpandableLayoutAcademicDetailsProfile = findViewById(R.id.llExpandableLayoutAcademicDetailsProfile);

        llExpandedHeaderContactDetailsProfile = findViewById(R.id.llExpandedHeaderContactDetailsProfile);
        llExpandedHeaderContactDetailsProfile.setOnClickListener(this);
        ivViewMoreBtnContactDetailsProfile = findViewById(R.id.ivViewMoreBtnContactDetailsProfile);
        llExpandableLayoutContactDetailsProfile = findViewById(R.id.llExpandableLayoutContactDetailsProfile);

        student_profile_image = findViewById(R.id.student_profile_image);
        mTvStudentName = findViewById(R.id.tvStudentName);
        mTvStudentEmail = findViewById(R.id.tvStudentEmail);
//        mTvStudentUserId = findViewById(R.id.tvStudentUserId);
        mTvStudentCourseNew = findViewById(R.id.tvStudentCourseNew);
        mTvStudentEnrollmentNo = findViewById(R.id.tvStudentenrollmentNo);
        mTvStudentSem = findViewById(R.id.tvStudentSem);
        mTvStudentGender = findViewById(R.id.tvStudentGender);
        mTvStudentDOB = findViewById(R.id.tvStudentDOB);
        mTvStudentAadhaarCardNo = findViewById(R.id.tvStudentAadhaarCardNo);
        mTvStudentMobileNo = findViewById(R.id.tvStudentMobileNo);
        mTvStudentCollegeName = findViewById(R.id.tvStudentCollegeName);
        mTvStudentDepartment = findViewById(R.id.tvStudentDepartment);
        mTvStudentCourse = findViewById(R.id.tvStudentCourse);
        mTvStudentDivision = findViewById(R.id.tvStudentDivision);
        mTvStudentRollNo = findViewById(R.id.tvStudentRollNo);
        mTvStudentAdmissionNo = findViewById(R.id.tvStudentAdmissionNo);
        mTvStudentadmissionYear = findViewById(R.id.tvStudentadmissionYear);
        mTvStudentAtmissionType = findViewById(R.id.tvStudentAtmissionType);
        mTvStudentInternetUsername = findViewById(R.id.tvStudentInternetUsername);
        mTvStudentInternetPassword = findViewById(R.id.tvStudentInternetPassword);
        mTvStudentPermenentAddress = findViewById(R.id.tvStudentPermenentAddress);
        mTvStudentPresentAddress = findViewById(R.id.tvStudentPresentAddress);
        mTvStudentFatherMobileNo = findViewById(R.id.tvStudentFatherMobileNo);
        mTvStudentCityName = findViewById(R.id.tvStudentCityName);
        mTvStudentStateName = findViewById(R.id.tvStudentStateName);
        mTvStudentCountryName = findViewById(R.id.tvStudentCountryName);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseProfile) {
            onBackPressed();
        } else if (v.getId() == R.id.llExpandedHeaderPersonalDetailsProfile) {
            isPersonalDetailsExpanded = toggleLayoutForDefaultOpenCard(!isPersonalDetailsExpanded, ivViewMoreBtnPersonalDetailProfile, llExpandableLayoutPersonalDetailsProfile);
        } else if (v.getId() == R.id.llExpandedHeaderAcademicDetailsProfile) {
            isAcademicDetailsExpanded = toggleLayout(!isAcademicDetailsExpanded, ivViewMoreBtnAcademicDetailsProfile, llExpandableLayoutAcademicDetailsProfile);
        } else if (v.getId() == R.id.llExpandedHeaderContactDetailsProfile) {
            isContactDetailsExpanded = toggleLayout(!isContactDetailsExpanded, ivViewMoreBtnContactDetailsProfile, llExpandableLayoutContactDetailsProfile);
        } else if (v.getId() == R.id.imgStudentLogout) {

            new MaterialAlertDialogBuilder(StudentProfileActivity.this)
                    .setTitle(Html.fromHtml("<b>" + "Logout" + " </b>"))
                    .setMessage("Do you wan to logout from this app ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logoutUserApiCall();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }


    private void getStudentProfileAndAttendanceData() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(StudentProfileActivity.this, "");
            HashMap<String, String> mParams = new HashMap<>();
            mParams.put("stud_id", mySharedPreferences.getStudentId());
            mParams.put("year_id", mySharedPreferences.getSwdYearId());
            mParams.put("school_id", mySharedPreferences.getAcId());
            ApiImplementer.getStudentProfileImplementer(mParams, new Callback<StudentProfilePojo>() {
                @Override
                public void onResponse(Call<StudentProfilePojo> call, Response<StudentProfilePojo> response) {
                    DialogUtil.hideProgressDialog();
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            StudentProfilePojo studentProfilePojo = response.body();
                            Glide.with(StudentProfileActivity.this)
                                    .asBitmap()
                                    .load(studentProfilePojo.getStudPhoto())
                                    .override(70, 70)
                                    .placeholder(R.drawable.person_img)
                                    .error(R.drawable.person_img)
                                    .into(student_profile_image);
                            setProfileData(studentProfilePojo);
                        } else {
                            Toast.makeText(StudentProfileActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<StudentProfilePojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(StudentProfileActivity.this, "Request Failed,Please try again later", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    private void setProfileData(StudentProfilePojo studentProfilePojo) {

        if (studentProfilePojo.getName() != null && !studentProfilePojo.getName().isEmpty()) {
            mTvStudentName.setText(studentProfilePojo.getName());
        }

        if (studentProfilePojo.getStudEmail() != null && !studentProfilePojo.getStudEmail().isEmpty()) {
            mTvStudentEmail.setText(studentProfilePojo.getStudEmail());
        }

        //for academic Details
        if (studentProfilePojo.getStudEnrollmentNo() != null && !studentProfilePojo.getStudEnrollmentNo().toString().isEmpty()) {
            mTvStudentEnrollmentNo.setText(studentProfilePojo.getStudEnrollmentNo() + "");
        }
        if (studentProfilePojo.getSmName() != null && !studentProfilePojo.getSmName().isEmpty()) {
            mTvStudentSem.setText(studentProfilePojo.getSmName() + "");
        }
        if (studentProfilePojo.getGenName() != null && !studentProfilePojo.getGenName().toString().isEmpty()) {
            mTvStudentGender.setText(studentProfilePojo.getGenName() + "");
        }

        if (studentProfilePojo.getStudBdate() != null && !studentProfilePojo.getStudBdate().toString().isEmpty()) {
            mTvStudentDOB.setText(studentProfilePojo.getStudBdate() + "");
        }

        if (studentProfilePojo.getStudAdharNo() != null && !studentProfilePojo.getStudAdharNo().isEmpty()) {
            mTvStudentAadhaarCardNo.setText(studentProfilePojo.getStudAdharNo() + "");
        }

        if (studentProfilePojo.getStudMobileNo() != null && !studentProfilePojo.getStudMobileNo().isEmpty()) {
            mTvStudentMobileNo.setText(studentProfilePojo.getStudMobileNo() + "");
        }

//        if (studentProfilePojo.getCountryName() != null && !studentProfilePojo.getCountryName().toString().isEmpty()) {
//            mTvStudentCourse.setText(studentProfilePojo.getCourseFullname() + "");
//        }


//For Contact Details
        if (studentProfilePojo.getAcFullName() != null && !studentProfilePojo.getAcFullName().isEmpty()) {
            mTvStudentCollegeName.setText(studentProfilePojo.getAcFullName() + "");
        }

        if (studentProfilePojo.getDmFullName() != null && !studentProfilePojo.getDmFullName().isEmpty()) {
            mTvStudentDepartment.setText(studentProfilePojo.getDmFullName() + "");
        }

        if (studentProfilePojo.getCourseFullname() != null && !studentProfilePojo.getCourseFullname().isEmpty()) {
            mTvStudentCourse.setText(studentProfilePojo.getCourseFullname() + "");
            mTvStudentCourseNew.setText(studentProfilePojo.getCourseShortName() + "");
        }

        if (studentProfilePojo.getDvmName() != null && !studentProfilePojo.getDvmName().toString().isEmpty()) {
            mTvStudentDivision.setText(studentProfilePojo.getDvmName().toString());
        }
        if (studentProfilePojo.getSwdRollNo() != null && !studentProfilePojo.getSwdRollNo().toString().isEmpty()) {
            mTvStudentRollNo.setText(studentProfilePojo.getSwdRollNo().toString());
        }
        if (studentProfilePojo.getStudAdmissionNo() != null && !studentProfilePojo.getStudAdmissionNo().isEmpty()) {
            mTvStudentAdmissionNo.setText(studentProfilePojo.getStudAdmissionNo() + "");
        }

        if (studentProfilePojo.getStudAdmissionYear() != null && !studentProfilePojo.getStudAdmissionYear().toString().isEmpty()) {
            mTvStudentadmissionYear.setText(studentProfilePojo.getStudAdmissionYear() + "");
        }
        if (studentProfilePojo.getAdmissionType() != null && !studentProfilePojo.getAdmissionType().isEmpty()) {
            mTvStudentAtmissionType.setText(studentProfilePojo.getAdmissionType() + "");
        }
        if (studentProfilePojo.getStudInternetUsername() != null && !studentProfilePojo.getStudInternetUsername().isEmpty()) {
            mTvStudentInternetUsername.setText(studentProfilePojo.getStudInternetUsername());
        }
        if (studentProfilePojo.getStudInternetPassword() != null && !studentProfilePojo.getStudInternetPassword().isEmpty()) {
            mTvStudentInternetPassword.setText(studentProfilePojo.getStudInternetPassword() + "");
        }

        //For Contact Details
        if (studentProfilePojo.getStudAddress1() != null) {
            mTvStudentPermenentAddress.setText(studentProfilePojo.getStudAddress1() + "");
        }
        if (studentProfilePojo.getStudCurrentAddress() != null) {
            mTvStudentPresentAddress.setText(studentProfilePojo.getStudCurrentAddress().toString());
        }
        if (studentProfilePojo.getStudFatherMobileNo() != null) {
            mTvStudentFatherMobileNo.setText(studentProfilePojo.getStudFatherMobileNo() + "");
        }
        if (studentProfilePojo.getCityName() != null) {
            mTvStudentCityName.setText(studentProfilePojo.getCityName() + "");
        }
        if (studentProfilePojo.getStateName() != null) {
            mTvStudentStateName.setText(studentProfilePojo.getStateName() + "");
        }
        if (studentProfilePojo.getCountryName() != null) {
            mTvStudentCountryName.setText(studentProfilePojo.getCountryName().toString());
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand) {
        Animations.toggleArrow(v, isExpanded);
        if (isExpanded) {
            Animations.expand(layoutExpand);
        } else {
            Animations.collapse(layoutExpand);
        }
        return isExpanded;

    }

    private boolean toggleLayoutForDefaultOpenCard(boolean isExpanded, View v, LinearLayout layoutExpand) {
        CustomAnimationForDefaultExpandableCard.toggleArrow(v, isExpanded);
        if (isExpanded) {
            CustomAnimationForDefaultExpandableCard.expand(layoutExpand);
        } else {
            CustomAnimationForDefaultExpandableCard.collapse(layoutExpand);
        }
        return isExpanded;
    }


    private void logoutUserApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(StudentProfileActivity.this, "");
            ApiImplementer.logoutUserApiImplementer(mySharedPreferences.getLoginUserType(), mySharedPreferences.getStudentId(), new Callback<ArrayList<LogOutPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<LogOutPojo>> call, Response<ArrayList<LogOutPojo>> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        if (response.body().get(0).getStatus().equalsIgnoreCase("1")) {
                            mySharedPreferences.logoutStudentOrFaulty();
                            Intent intent = new Intent(StudentProfileActivity.this, StudentDashboardActivity.class);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            Toast.makeText(StudentProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(StudentProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<LogOutPojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(StudentProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

}