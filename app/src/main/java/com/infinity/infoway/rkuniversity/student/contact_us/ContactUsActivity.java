package com.infinity.infoway.rkuniversity.student.contact_us;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharedPreferences mySharedPreferences;
    private AppCompatImageView ivCloseContactUs;
    private TextViewMediumFont tvPrincipalMobileNo;
    private TextViewMediumFont tvPrincipalEmailId;
    private TextViewMediumFont tvHOdMobileNo;
    private TextViewMediumFont tvHODEmailId;
    private TextViewMediumFont tvFacultyAdvisorMobileNo;
    private TextViewMediumFont tvFavultyadviserEmailID;
    private TextViewMediumFont tvCollegeMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initView();
        getContactUsDetailsApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(ContactUsActivity.this);
        ivCloseContactUs = findViewById(R.id.ivCloseContactUs);
        ivCloseContactUs.setOnClickListener(this);
        tvPrincipalMobileNo = findViewById(R.id.tvPrincipalMobileNo);
        tvPrincipalEmailId = findViewById(R.id.tvPrincipalEmailId);
        tvHOdMobileNo = findViewById(R.id.tvHOdMobileNo);
        tvHODEmailId = findViewById(R.id.tvHODEmailId);
        tvFacultyAdvisorMobileNo = findViewById(R.id.tvFacultyAdvisorMobileNo);
        tvFavultyadviserEmailID = findViewById(R.id.tvFavultyadviserEmailID);
        tvCollegeMobileNo = findViewById(R.id.tvCollegeMobileNo);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseContactUs) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getContactUsDetailsApiCall() {
        DialogUtil.showProgressDialogNotCancelable(ContactUsActivity.this, "");
        ApiImplementer.getContactUsDetailsApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(), mySharedPreferences.getStudMainSchoolId(), new Callback<ArrayList<ContactUsPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactUsPojo>> call, Response<ArrayList<ContactUsPojo>> response) {
                DialogUtil.hideProgressDialog();
                try {
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        ContactUsPojo contactUsPojo = response.body().get(0);
                        String principalMobileNo = contactUsPojo.getPrincipalDetail().split("_")[0];
                        String principalEmailId = contactUsPojo.getPrincipalDetail().split("_")[1];

                        String hodMobileNo = contactUsPojo.getHodDetail().split("_")[0];
                        String hodEmailId = contactUsPojo.getHodDetail().split("_")[1];

                        String facultyAdviserMobileNo = contactUsPojo.getFacultyDetail().split("_")[0];
                        String facultyAdviserEmailId = contactUsPojo.getFacultyDetail().split("_")[1];

                        String clgMobileNo = contactUsPojo.getCollegeDetail();


                        tvPrincipalMobileNo.setText(principalMobileNo);
                        tvPrincipalEmailId.setText(principalEmailId);
                        tvHOdMobileNo.setText(hodMobileNo);
                        tvHODEmailId.setText(hodEmailId);
                        tvFacultyAdvisorMobileNo.setText(facultyAdviserMobileNo);
                        tvFavultyadviserEmailID.setText(facultyAdviserEmailId);
                        tvCollegeMobileNo.setText(clgMobileNo);
                    } else {
                        Toast.makeText(ContactUsActivity.this, "Contact Details Not Available!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (Exception ex) {
                    Toast.makeText(ContactUsActivity.this, "Something went wrong,Please try again later!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ContactUsPojo>> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(ContactUsActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}