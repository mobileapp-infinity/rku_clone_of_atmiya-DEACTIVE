package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.makeramen.roundedimageview.RoundedImageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    RoundedImageView profileimg;
    ImageView editpic;
   // CustomBoldTextView txtusername;
    LinearLayout widget393;
    LinearLayout widget39;
    CustomTextView edtempcode;
    CustomTextView edtempname;
    ImageView ivreporting;
    CustomTextView edtreportingto;
    CustomTextView edtbranch;
    CustomTextView edtdept;
    CustomTextView edtdesignation;
    CustomBoldTextView txtprofilesubmit;
    LinearLayout widget37;
    RelativeLayout widget30;
    CustomBoldTextView txt_act;
    ImageView iv_back;
    CustomBoldTextView tv_emp_code, tv_version, tv_version_code;
    CustomTextView edt_personal_email, edt_office_email, edt_mo_no, edt_joining_date;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tvInvestment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_u);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_act);
        setSupportActionBar(toolbar);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        txt_act.setText("Profile");
        initView();
    }

    private void initView()
    {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        profileimg = (RoundedImageView) findViewById(R.id.profile_img);
        editpic = (ImageView) findViewById(R.id.edit_pic);
       // txtusername = (CustomBoldTextView) findViewById(R.id.txt_username);
        widget393 = (LinearLayout) findViewById(R.id.widget393);
        widget39 = (LinearLayout) findViewById(R.id.widget39);
        edtempcode = (CustomTextView) findViewById(R.id.edt_emp_code);
        edtempcode.setText(mySharedPreferecesRKUOLD.getEmpCode() + "");
        edtempname = (CustomTextView) findViewById(R.id.edt_emp_name);
        edtempname.setText(mySharedPreferecesRKUOLD.getFullName() + "");
        ivreporting = (ImageView) findViewById(R.id.iv_reporting);
        edtreportingto = (CustomTextView) findViewById(R.id.edt_reporting_to);
        edtreportingto.setText(mySharedPreferecesRKUOLD.getREPORTINGTO() + "");
        edtbranch = (CustomTextView) findViewById(R.id.edt_branch);
        edtbranch.setText(mySharedPreferecesRKUOLD.getBranch() + "");
        edtdept = (CustomTextView) findViewById(R.id.edt_dept);
        edtdept.setText(mySharedPreferecesRKUOLD.getDepartment() + "");
        edtdesignation = (CustomTextView) findViewById(R.id.edt_designation);
        edtdesignation.setText(mySharedPreferecesRKUOLD.getDesignation() + "");
        txtprofilesubmit = (CustomBoldTextView) findViewById(R.id.txt_profile_submit);
        widget37 = (LinearLayout) findViewById(R.id.widget37);
        widget30 = (RelativeLayout) findViewById(R.id.widget30);

        tvInvestment = findViewById(R.id.tvInvestment);
        tvInvestment.setOnClickListener(this);

        edt_personal_email = (CustomTextView) findViewById(R.id.edt_personal_email);
        edt_personal_email.setText(mySharedPreferecesRKUOLD.getPersonalEmail()+"");
        edt_office_email = (CustomTextView) findViewById(R.id.edt_office_email);
        edt_office_email.setText(mySharedPreferecesRKUOLD.getOfficeEmail()+"");
        edt_mo_no = (CustomTextView) findViewById(R.id.edt_mo_no);
        edt_mo_no.setText(mySharedPreferecesRKUOLD.getMoNo()+"");
        edt_joining_date = (CustomTextView) findViewById(R.id.edt_joining_date);
        edt_joining_date.setText(mySharedPreferecesRKUOLD.getJoiningDate()+"");

        Glide.with(ProfileActivity.this).load(mySharedPreferecesRKUOLD.getUserPhoto() + "").error(R.drawable.noimage).into(profileimg);
        PackageInfo pInfo = null;
        assert pInfo != null;

        try
        {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        tv_emp_code = (CustomBoldTextView) findViewById(R.id.tv_emp_code);
        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version_code = (CustomBoldTextView) findViewById(R.id.tv_version_code);
        tv_version.setText(pInfo.versionName);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvInvestment){
            requestForReadExternalStoragePermission();
        }
    }


    private void requestForReadExternalStoragePermission(){

        if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Required Read External Storage Permission!")
                        .setMessage("To Upload Document we need read external storage permission")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 127);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create().show();
            } else if (mySharedPreferecesRKUOLD.getIsPermissionGranted()) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Required Read External Storage Permission!")
                        .setMessage("To Enable Read External Storage Permission click on Ok button")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE_PERMISSION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 127);
            }
            mySharedPreferecesRKUOLD.setIsPermissionGranted();

        } else {
            //Read External Storage Permission
            Intent intent = new Intent(ProfileActivity.this,InvestmentListActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 127 && ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            //permission granted
            Intent intent = new Intent(ProfileActivity.this,InvestmentListActivity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE_PERMISSION &&
                ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            //Read external Storage permission ganted
            Intent intent = new Intent(ProfileActivity.this,InvestmentListActivity.class);
            startActivity(intent);
        }
    }

}
