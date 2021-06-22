package com.infinity.infoway.rkuniversity.student.leave_application.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.student.leave_application.activity.LeaveApplicationActivity;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;

public class UploadFileBottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    LeaveApplicationActivity context;
    LinearLayout llCaptureImage;
    LinearLayout llAttachFile;
    CloseUploadFileDialog CaptureOrAttachFile;
    ApplyILeaveFragment applyLeaveFragment;

    UploadFileBottomSheetDialog() {

    }

    UploadFileBottomSheetDialog(Context context, ApplyILeaveFragment applyLeaveFragment) {
        this.context = (LeaveApplicationActivity) context;
        this.applyLeaveFragment = applyLeaveFragment;
        CaptureOrAttachFile = (CloseUploadFileDialog) applyLeaveFragment;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_upload_file_bottom_sheet,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        llCaptureImage = view.findViewById(R.id.llCaptureImage);
        llCaptureImage.setOnClickListener(this);
        llAttachFile = view.findViewById(R.id.llAttachFile);
        llAttachFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llCaptureImage) {
            CaptureOrAttachFile.closeUploadFileBottomSheetDialog();
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            context.startActivityIfNeeded(intent, IntentConstants.REQUEST_CODE_FOR_CAPTURE_IMAGE);
        } else if (v.getId() == R.id.llAttachFile) {
            CaptureOrAttachFile.closeUploadFileBottomSheetDialog();
            Intent intent = new Intent(context, FilePickerActivity.class);
            intent.putExtra(com.jaiselrahman.filepicker.activity.FilePickerActivity.CONFIGS,
                    new Configurations.Builder()
                            .setCheckPermission(true)
                            .setShowImages(true)
                            .setShowAudios(false)
                            .setShowVideos(false)
                            .enableImageCapture(false)
                            .setMaxSelection(1)
                            .setSkipZeroSizeFiles(true)
                            .build());
            context.startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_ATTACH_FILE);
        }
    }

    public interface CloseUploadFileDialog {
        void closeUploadFileBottomSheetDialog();
    }

}
