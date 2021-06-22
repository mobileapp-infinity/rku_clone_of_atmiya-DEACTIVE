package com.infinity.infoway.rkuniversity.faculty.faculty_profile;

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
import com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.activity.FacultyDashboardActivity;
import com.infinity.infoway.rkuniversity.login.pojo.LogOutPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharedPreferences mySharedPreferences;
    private ConnectionDetector connectionDetector;
    private AppCompatImageView ivCloseFacultyProfile;
    private CircleImageView faculty_profile_image;
    private AppCompatImageView imgFacultyLogout;

    private TextViewRegularFont tvFacultyName;
    private TextViewRegularFont tvFacultyEmail;
    //    private TextViewMediumFont tvEmpId;
    private TextViewMediumFont tvFacultyEmployeeNo;
    private TextViewMediumFont tvFacultyEducation;

    //personal Details
    private LinearLayout llExpandedHeaderFacultyPersonalDetailsProfile;
    private LinearLayout llExpandableLayoutFacultyPersonalDetailsProfile;
    private AppCompatImageView ivViewMoreBtnPersonalDetailFacultyProfile;
    private TextViewMediumFont tvFacultyGender;
    private TextViewMediumFont tvFacultyDOB;
    private TextViewMediumFont tvFacultyAadhaarCardNo;
    private TextViewMediumFont tvFacultyPanCardNo;
    private TextViewMediumFont tvFacultyMobileNo;
    boolean isPersonalDetailsExpanded = true;

    //Official Details
    private LinearLayout llExpandedHeaderOfficialDetailsFacultyProfile;
    private LinearLayout llExpandableLayoutOfficialDetailsFacultyProfile;
    private AppCompatImageView ivViewMoreBtnOfficialDetailsFacultyProfile;
    private TextViewMediumFont tvFacultyDepartmentName;
    private TextViewMediumFont tvFacultyJobName;
    private TextViewMediumFont tvFacultyDateOfJoining;
    private TextViewMediumFont tvFacultyOfferLetterNo;
    private TextViewMediumFont tvFacultyCardNo;
    private TextViewMediumFont tvFacultyJobTimeFromToto;
    boolean isOfficialDetailsExpanded = false;

    //Account Details
    private LinearLayout llExpandedHeaderAccountDetailsFaculty;
    private AppCompatImageView ivViewMoreBtnAccountDetails;
    private LinearLayout llExpandableLayoutAccountDetailsFaculty;
    private TextViewMediumFont tvFacultyBankName;
    private TextViewMediumFont tvFacultyAccountNo;
    private TextViewMediumFont tvFacultyBranchName;
    private TextViewMediumFont tvFacultyAccountType;
    boolean isAccountDetailsExpanded = false;

    //contact details
    private LinearLayout llExpandedHeaderContactDetailsFaculty;
    private AppCompatImageView ivViewMoreBtnContactDetails;
    private LinearLayout llExpandableLayoutContactDetailsFaculty;
    private TextViewMediumFont tvFacultyPermenantAddress;
    private TextViewMediumFont tvFacultyPresentAddress;
    private TextViewMediumFont tvFacultyHomeMobileNo;
    private TextViewMediumFont tvFacultyCity;
    private TextViewMediumFont tvFacultyState;
    private TextViewMediumFont tvFacultyCountry;
    boolean isContactDetailsExpanded = false;

    //family details
    private LinearLayout llExpandedHeaderFamilyDetailsFaculty;
    private AppCompatImageView ivViewMoreBtnFamilyDetails;
    private LinearLayout llExpandableLayoutFamilyDetailsFaculty;
    private TextViewMediumFont tvFacultyFatherName;
    private TextViewMediumFont tvFacultyFatherOccupation;
    private TextViewMediumFont tvFacultyFathersAddress;
    private TextViewMediumFont tvFacultyMotherName;
    private TextViewMediumFont tvFacultyMotherOccupation;
    private TextViewMediumFont tvFacultySpouseName;
    private TextViewMediumFont tvFacultySppuseOccupation;
    private TextViewMediumFont tvFacultySonOrDoughterName;
    private TextViewMediumFont tvFacultySonOrDaughterDOF;
    boolean isFamilyDetailsExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);
        initView();
        getFacultyProfileDetailsApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyProfileActivity.this);
        connectionDetector = new ConnectionDetector(FacultyProfileActivity.this);
        ivCloseFacultyProfile = findViewById(R.id.ivCloseFacultyProfile);
        ivCloseFacultyProfile.setOnClickListener(this);
        faculty_profile_image = findViewById(R.id.faculty_profile_image);
        imgFacultyLogout = findViewById(R.id.imgFacultyLogout);
        imgFacultyLogout.setOnClickListener(this);

        tvFacultyName = findViewById(R.id.tvFacultyName);
        tvFacultyEmail = findViewById(R.id.tvFacultyEmail);
//        tvEmpId = findViewById(R.id.tvEmpId);
        tvFacultyEmployeeNo = findViewById(R.id.tvFacultyEmployeeNo);
        tvFacultyEducation = findViewById(R.id.tvFacultyEducation);

        llExpandedHeaderFacultyPersonalDetailsProfile = findViewById(R.id.llExpandedHeaderFacultyPersonalDetailsProfile);
        llExpandedHeaderFacultyPersonalDetailsProfile.setOnClickListener(this);
        llExpandableLayoutFacultyPersonalDetailsProfile = findViewById(R.id.llExpandableLayoutFacultyPersonalDetailsProfile);
        ivViewMoreBtnPersonalDetailFacultyProfile = findViewById(R.id.ivViewMoreBtnPersonalDetailFacultyProfile);
        tvFacultyGender = findViewById(R.id.tvFacultyGender);
        tvFacultyDOB = findViewById(R.id.tvFacultyDOB);
        tvFacultyAadhaarCardNo = findViewById(R.id.tvFacultyAadhaarCardNo);
        tvFacultyPanCardNo = findViewById(R.id.tvFacultyPanCardNo);
        tvFacultyMobileNo = findViewById(R.id.tvFacultyMobileNo);

        llExpandedHeaderOfficialDetailsFacultyProfile = findViewById(R.id.llExpandedHeaderOfficialDetailsFacultyProfile);
        llExpandedHeaderOfficialDetailsFacultyProfile.setOnClickListener(this);
        llExpandableLayoutOfficialDetailsFacultyProfile = findViewById(R.id.llExpandableLayoutOfficialDetailsFacultyProfile);
        ivViewMoreBtnOfficialDetailsFacultyProfile = findViewById(R.id.ivViewMoreBtnOfficialDetailsFacultyProfile);
        tvFacultyDepartmentName = findViewById(R.id.tvFacultyDepartmentName);
        tvFacultyJobName = findViewById(R.id.tvFacultyJobName);
        tvFacultyDateOfJoining = findViewById(R.id.tvFacultyDateOfJoining);
        tvFacultyOfferLetterNo = findViewById(R.id.tvFacultyOfferLetterNo);
        tvFacultyCardNo = findViewById(R.id.tvFacultyCardNo);
        tvFacultyJobTimeFromToto = findViewById(R.id.tvFacultyJobTimeFromToto);

        llExpandedHeaderAccountDetailsFaculty = findViewById(R.id.llExpandedHeaderAccountDetailsFaculty);
        llExpandedHeaderAccountDetailsFaculty.setOnClickListener(this);
        ivViewMoreBtnAccountDetails = findViewById(R.id.ivViewMoreBtnAccountDetails);
        llExpandableLayoutAccountDetailsFaculty = findViewById(R.id.llExpandableLayoutAccountDetailsFaculty);
        tvFacultyBankName = findViewById(R.id.tvFacultyBankName);
        tvFacultyAccountNo = findViewById(R.id.tvFacultyAccountNo);
        tvFacultyBranchName = findViewById(R.id.tvFacultyBranchName);
        tvFacultyAccountType = findViewById(R.id.tvFacultyAccountType);

        llExpandedHeaderContactDetailsFaculty = findViewById(R.id.llExpandedHeaderContactDetailsFaculty);
        llExpandedHeaderContactDetailsFaculty.setOnClickListener(this);
        ivViewMoreBtnContactDetails = findViewById(R.id.ivViewMoreBtnContactDetails);
        llExpandableLayoutContactDetailsFaculty = findViewById(R.id.llExpandableLayoutContactDetailsFaculty);
        tvFacultyPermenantAddress = findViewById(R.id.tvFacultyPermenantAddress);
        tvFacultyPresentAddress = findViewById(R.id.tvFacultyPresentAddress);
        tvFacultyHomeMobileNo = findViewById(R.id.tvFacultyHomeMobileNo);
        tvFacultyCity = findViewById(R.id.tvFacultyCity);
        tvFacultyState = findViewById(R.id.tvFacultyState);
        tvFacultyCountry = findViewById(R.id.tvFacultyCountry);

        llExpandedHeaderFamilyDetailsFaculty = findViewById(R.id.llExpandedHeaderFamilyDetailsFaculty);
        llExpandedHeaderFamilyDetailsFaculty.setOnClickListener(this);
        ivViewMoreBtnFamilyDetails = findViewById(R.id.ivViewMoreBtnFamilyDetails);
        llExpandableLayoutFamilyDetailsFaculty = findViewById(R.id.llExpandableLayoutFamilyDetailsFaculty);
        tvFacultyFatherName = findViewById(R.id.tvFacultyFatherName);
        tvFacultyFatherOccupation = findViewById(R.id.tvFacultyFatherOccupation);
        tvFacultyFathersAddress = findViewById(R.id.tvFacultyFathersAddress);
        tvFacultyMotherName = findViewById(R.id.tvFacultyMotherName);
        tvFacultyMotherOccupation = findViewById(R.id.tvFacultyMotherOccupation);
        tvFacultySpouseName = findViewById(R.id.tvFacultySpouseName);
        tvFacultySppuseOccupation = findViewById(R.id.tvFacultySppuseOccupation);
        tvFacultySonOrDoughterName = findViewById(R.id.tvFacultySonOrDoughterName);
        tvFacultySonOrDaughterDOF = findViewById(R.id.tvFacultySonOrDaughterDOF);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyProfile) {
            onBackPressed();
        } else if (v.getId() == R.id.imgFacultyLogout) {
            new MaterialAlertDialogBuilder(FacultyProfileActivity.this)
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
        } else if (v.getId() == R.id.llExpandedHeaderFacultyPersonalDetailsProfile) {
            isPersonalDetailsExpanded = toggleLayoutForDefaultOpenCard(!isPersonalDetailsExpanded, ivViewMoreBtnPersonalDetailFacultyProfile, llExpandableLayoutFacultyPersonalDetailsProfile);
        } else if (v.getId() == R.id.llExpandedHeaderOfficialDetailsFacultyProfile) {
            isOfficialDetailsExpanded = toggleLayout(!isOfficialDetailsExpanded, ivViewMoreBtnOfficialDetailsFacultyProfile, llExpandableLayoutOfficialDetailsFacultyProfile);
        } else if (v.getId() == R.id.llExpandedHeaderAccountDetailsFaculty) {
            isAccountDetailsExpanded = toggleLayout(!isAccountDetailsExpanded, ivViewMoreBtnAccountDetails, llExpandableLayoutAccountDetailsFaculty);
        } else if (v.getId() == R.id.llExpandedHeaderContactDetailsFaculty) {
            isContactDetailsExpanded = toggleLayout(!isContactDetailsExpanded, ivViewMoreBtnContactDetails, llExpandableLayoutContactDetailsFaculty);
        } else if (v.getId() == R.id.llExpandedHeaderFamilyDetailsFaculty) {
            isFamilyDetailsExpanded = toggleLayout(!isFamilyDetailsExpanded, ivViewMoreBtnFamilyDetails, llExpandableLayoutFamilyDetailsFaculty);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getFacultyProfileDetailsApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(FacultyProfileActivity.this, "");
            ApiImplementer.getFacultyProfileDetailsApiImplementer(mySharedPreferences.getEmpId(), new Callback<ArrayList<FacultyProfilePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FacultyProfilePojo>> call, Response<ArrayList<FacultyProfilePojo>> response) {
                    DialogUtil.hideProgressDialog();
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            FacultyProfilePojo facultyProfilePojo = response.body().get(0);

                            if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getEmpPhotoUrl())) {
                                Glide.with(FacultyProfileActivity.this)
                                        .asBitmap()
                                        .load(mySharedPreferences.getEmpPhotoUrl().trim())
                                        .override(70, 70)
                                        .placeholder(R.drawable.person_img)
                                        .error(R.drawable.person_img)
                                        .into(faculty_profile_image);
                            }
                            setFacultyProfileDetails(facultyProfilePojo);
                        } else {
                            Toast.makeText(FacultyProfileActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FacultyProfilePojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(FacultyProfileActivity.this, "Request Failed,Please try again later", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setFacultyProfileDetails(FacultyProfilePojo facultyProfilePojo) {

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getName())) {
            tvFacultyName.setText(facultyProfilePojo.getName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpEmail())) {
            tvFacultyEmail.setText(facultyProfilePojo.getEmpEmail() + "");
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getEmpId())) {
//            tvEmpId.setText(mySharedPreferences.getEmpId() + "");
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpNumber())) {
            tvFacultyEmployeeNo.setText(facultyProfilePojo.getEmpNumber() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpQualification())) {
            tvFacultyEducation.setText(facultyProfilePojo.getEmpQualification() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getGender())) {
            tvFacultyGender.setText(facultyProfilePojo.getGender() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getBDate())) {
            tvFacultyDOB.setText(facultyProfilePojo.getBDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpAdharNo())) {
            tvFacultyAadhaarCardNo.setText(facultyProfilePojo.getEmpAdharNo() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpPanNo())) {
            tvFacultyPanCardNo.setText(facultyProfilePojo.getEmpPanNo() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpMobilePhone())) {
            tvFacultyMobileNo.setText(facultyProfilePojo.getEmpMobilePhone() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpMobilePhone())) {
            tvFacultyMobileNo.setText(facultyProfilePojo.getEmpMobilePhone() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEdName())) {
            tvFacultyDepartmentName.setText(facultyProfilePojo.getEdName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getJobName())) {
            tvFacultyJobName.setText(facultyProfilePojo.getJobName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getJoiningDate())) {
            tvFacultyDateOfJoining.setText(facultyProfilePojo.getJoiningDate() + "");
        }
//        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.())) {
//            tvFacultyDepartmentName.setText(facultyProfilePojo.getEdName() + "");
//        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpCardNo())) {
            tvFacultyCardNo.setText(facultyProfilePojo.getEmpCardNo() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpJobTimeFrom()) &&
                !CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpJobTimeTo())) {
            tvFacultyJobTimeFromToto.setText(facultyProfilePojo.getEmpJobTimeFrom() + " to " + facultyProfilePojo.getEmpJobTimeTo());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpBankName())) {
            tvFacultyBankName.setText(facultyProfilePojo.getEmpBankName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpAccountNo())) {
            tvFacultyAccountNo.setText(facultyProfilePojo.getEmpAccountNo() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpBranchName())) {
            tvFacultyBranchName.setText(facultyProfilePojo.getEmpBranchName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpPermanentAddress())) {
            tvFacultyPermenantAddress.setText(facultyProfilePojo.getEmpPermanentAddress() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpPermanentAddress1())) {
            tvFacultyPresentAddress.setText(facultyProfilePojo.getEmpPermanentAddress1() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpHomePhone())) {
            tvFacultyHomeMobileNo.setText(facultyProfilePojo.getEmpHomePhone() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getCityName())) {
            tvFacultyCity.setText(facultyProfilePojo.getCityName() + "");
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.get())) {
//            tvFacultyHomeMobileNo.setText(facultyProfilePojo.getEmpHomePhone() + "");
//        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.cou())) {
//            tvFacultyHomeMobileNo.setText(facultyProfilePojo.getEmpHomePhone() + "");
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpFatherName())) {
            tvFacultyFatherName.setText(facultyProfilePojo.getEmpFatherName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpFatherOccupation())) {
            tvFacultyFatherOccupation.setText(facultyProfilePojo.getEmpFatherOccupation() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpPermanentAddress1())) {
            tvFacultyFathersAddress.setText(facultyProfilePojo.getEmpPermanentAddress1() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpMotherName())) {
            tvFacultyMotherName.setText(facultyProfilePojo.getEmpMotherName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpMotherOccupation())) {
            tvFacultyMotherOccupation.setText(facultyProfilePojo.getEmpMotherOccupation() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpSpouseName())) {
            tvFacultySpouseName.setText(facultyProfilePojo.getEmpSpouseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpSpouseOccupation())) {
            tvFacultySppuseOccupation.setText(facultyProfilePojo.getEmpSpouseOccupation() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpChildName())) {
            tvFacultySonOrDoughterName.setText(facultyProfilePojo.getEmpChildName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEmpDateOfChield())) {
            tvFacultySonOrDaughterDOF.setText(facultyProfilePojo.getEmpDateOfChield() + "");
        }
    }

    private void logoutUserApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(FacultyProfileActivity.this, "");
            ApiImplementer.logoutUserApiImplementer(mySharedPreferences.getLoginUserType(), mySharedPreferences.getEmpId(), new Callback<ArrayList<LogOutPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<LogOutPojo>> call, Response<ArrayList<LogOutPojo>> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        if (response.body().get(0).getStatus().equalsIgnoreCase("1")) {
                            mySharedPreferences.logoutStudentOrFaulty();
                            Intent intent = new Intent(FacultyProfileActivity.this, FacultyDashboardActivity.class);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            Toast.makeText(FacultyProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(FacultyProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<LogOutPojo>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(FacultyProfileActivity.this, "some thing went wrong please try again later", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

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

    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand) {
        Animations.toggleArrow(v, isExpanded);
        if (isExpanded) {
            Animations.expand(layoutExpand);
        } else {
            Animations.collapse(layoutExpand);
        }
        return isExpanded;

    }

}