package com.infinity.infoway.rkuniversity.student.leave_application.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.student.leave_application.adapter.LeaveApplicationViewPagerAdapter;
import com.infinity.infoway.rkuniversity.student.leave_application.fragment.ApplyILeaveFragment;
import com.infinity.infoway.rkuniversity.student.leave_application.fragment.LeaveHistoryFragment;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LeaveApplicationActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivBackLeaveApplication;
    TabLayout tlLeaveApplication;
    ViewPager vpLeaveApplication;
    ApplyILeaveFragment applyLeaveFragment;
    ILeaveApplicationFileUpload iLeaveApplicationFileUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);
        initView();
    }

    private void initView() {

        ivBackLeaveApplication = findViewById(R.id.ivBackLeaveApplication);
        ivBackLeaveApplication.setOnClickListener(this);

        tlLeaveApplication = findViewById(R.id.tlLeaveApplication);
        vpLeaveApplication = findViewById(R.id.vpLeaveApplication);
        tlLeaveApplication.setupWithViewPager(vpLeaveApplication);

        applyLeaveFragment = ApplyILeaveFragment.newInstance();
        iLeaveApplicationFileUpload = (ILeaveApplicationFileUpload) applyLeaveFragment;
        setupViewPager(vpLeaveApplication);
    }

    private void setupViewPager(ViewPager viewPager) {
        LeaveApplicationViewPagerAdapter adapter = new LeaveApplicationViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(applyLeaveFragment, "    Apply    ");
        adapter.addFragment(new LeaveHistoryFragment(), "    History   ");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivBackLeaveApplication) {
            onBackPressed();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                requestCode == IntentConstants.REQUEST_CODE_FOR_ATTACH_FILE) {
            ArrayList<MediaFile> files = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
            if (data != null && files != null && files.size() == 1) {
                String attachedFilePath = files.get(0).getPath().trim();
                File attachedFile = new File(attachedFilePath);
                if (attachedFile != null && attachedFile.exists() && attachedFile.length() > 0) {
                    try {
                        byte[] byteImage = CommonUtil.readByteFromFile(attachedFile);
                        String imgByteArray = Base64.encodeToString(byteImage, Base64.DEFAULT);
                        iLeaveApplicationFileUpload.onFileSelectOrCapture(attachedFile.getName(), imgByteArray);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (resultCode == RESULT_OK &&
                requestCode == IntentConstants.REQUEST_CODE_FOR_CAPTURE_IMAGE) {
            if (data != null) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                String imageByteArray = Base64.encodeToString(byteArray, Base64.DEFAULT);
                String filename = CommonUtil.generateUniqueFileName("leave_attachment.JPEG");
                iLeaveApplicationFileUpload.onFileSelectOrCapture(filename, imageByteArray);
            }
        }
    }

    public interface ILeaveApplicationFileUpload {
        void onFileSelectOrCapture(String fileName, String byteArayOfFile);
    }

}