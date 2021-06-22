package com.infinity.infoway.rkuniversity.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;

public class DialogUtil {

    static Dialog m_Dialog = null;


    public static Dialog showProgressDialogCancelable(Context context, String text) {
        String msg = "";
        if (CommonUtil.checkIsEmptyOrNullCommon(text)) {
            msg = "Please Wait... ";
        }else {
            msg = text;
        }

        m_Dialog = new Dialog(context);
        m_Dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);//if need to change dialog radius in custom_layout_for_progress_dialog
        // layout than also change same radius in bg_shape_for_custom_dialog
        m_Dialog.setCancelable(true);
        View customProgressDialog = LayoutInflater.from(context).inflate(R.layout.custom_layout_for_progress_dialog, null);
        TextViewRegularFont tvMsg = customProgressDialog.findViewById(R.id.tvMsg);
        tvMsg.setText(msg);
        m_Dialog.setContentView(customProgressDialog);
        m_Dialog.show();
        return m_Dialog;
    }

    public static void hideProgressDialog() {
        if (m_Dialog != null) {
            if (m_Dialog.isShowing()) {
                m_Dialog.dismiss();
            }
        }
    }

    public static Dialog showProgressDialogNotCancelable(Context context, String text) {

        String msg = "";
        if (CommonUtil.checkIsEmptyOrNullCommon(text)) {
            msg = "Please Wait... ";
        }else {
            msg = text;
        }

        m_Dialog = new Dialog(context);
        m_Dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);//if need to change dialog radius in custom_layout_for_progress_dialog
        // layout than also change same radius in bg_shape_for_custom_dialog
        m_Dialog.setCancelable(false);
        View customProgressDialog = LayoutInflater.from(context).inflate(R.layout.custom_layout_for_progress_dialog, null);
        TextViewRegularFont tvMsg = customProgressDialog.findViewById(R.id.tvMsg);
        tvMsg.setText(msg);
        m_Dialog.setContentView(customProgressDialog);
        m_Dialog.show();
        return m_Dialog;
    }

}


//////OLD CODE
//package com.infinity.infoway.rku.utils;
//
//        import android.app.Application;
//        import android.app.ProgressDialog;
//        import android.content.Context;
//        import android.graphics.Typeface;
//        import android.text.SpannableString;
//        import android.text.Spanned;
//
//        import androidx.core.content.res.ResourcesCompat;
//
//        import com.infinity.infoway.rku.R;
//
//public class DialogUtil {
//    static ProgressDialog m_Dialog = null;
//
//
//    public static ProgressDialog showProgressDialog(Context context, String text) {
//        m_Dialog = new ProgressDialog(context);
//        if (text == null || text.contentEquals("")) {
//            text = "Please Wait...";
//        }
//        SpannableString spannableString = new SpannableString(text);
//        spannableString.setSpan(ResourcesCompat.getFont(context, R.font.ubuntu_r), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        m_Dialog.setMessage(spannableString);
//
//        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        m_Dialog.setCancelable(true);
//        m_Dialog.show();
//        return m_Dialog;
//    }
//
//    public static void hideProgressDialog() {
//        if (m_Dialog != null) {
//            if (m_Dialog.isShowing()) {
//                m_Dialog.dismiss();
//            }
//        }
//    }
//
//    public static ProgressDialog showProgressDialogNotCancelable(Context context, String text) {
//        m_Dialog = new ProgressDialog(context);
//        if (text == null || text.contentEquals("")) {
//            text = "Please Wait...";
//        }
//        SpannableString spannableString = new SpannableString(text);
//        spannableString.setSpan(ResourcesCompat.getFont(context, R.font.ubuntu_r), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        m_Dialog.setMessage(spannableString);
//        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        m_Dialog.setCancelable(false);
//        m_Dialog.show();
//        return m_Dialog;
//    }
//
//}

