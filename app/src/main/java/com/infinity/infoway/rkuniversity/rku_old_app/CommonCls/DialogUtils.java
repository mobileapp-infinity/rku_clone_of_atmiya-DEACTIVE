package com.infinity.infoway.rkuniversity.rku_old_app.CommonCls;



import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;

import android.text.SpannableString;
import android.text.Spanned;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.infinity.infoway.rkuniversity.R;


/**
 * Created by ADMIN on 03-05-2017.
 */

public class DialogUtils extends Application
{
    static ProgressDialog m_Dialog = null;
    private CustomTextView mTitileTextView;
    private CustomTextView mMsgTextView;
    private Button mDialogButtonOKButton;
    public static EditText edtotp6, edtotp5, edtotp4, edtotp3, edtotp2, edtotp1;
    public static TextView tv_resend;
    public static AlertDialog show = null;
    public static ProgressDialog showProgressDialog(Context context, String text)
    {
        text="Please Wait...";
        m_Dialog = new ProgressDialog(context);
        SpannableString spannableString = new SpannableString(text);
        Typeface typefaceSpan = Validations.setTypeface(context);
        spannableString.setSpan(typefaceSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        m_Dialog.setMessage(spannableString);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
        return m_Dialog;
    }

    public static void hideProgressDialog()
    {
        if (m_Dialog != null)
        {
            if (m_Dialog.isShowing())
            {
                m_Dialog.dismiss();
            }
        }

    }


    public interface DailogCallBackCancelButtonClick
    {
        public void onDialogCancelButtonClicked();

    }
    public interface Dailogtv_resendButtonClick
    {
        public void onDialogtv_resendClicked();

    }

    public static ProgressDialog showProgressDialogNotCancelable(Context context, String text)
    {
        // if(context!=null) {
        text="Please Wait...";
        m_Dialog = new ProgressDialog(context);
        SpannableString spannableString = new SpannableString(text);
        Typeface typefaceSpan = Validations.setTypeface(context);
        spannableString.setSpan(typefaceSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        m_Dialog.setMessage(spannableString);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
        return m_Dialog;
        // }
    }

    public static Toast Show_Toast(Context ctx, String msg) {
       /* Toast t = new Toast(ctx);
        t.setText(msg + "");
        t.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);

        t.setDuration(Toast.LENGTH_SHORT);
        t.show();*/


        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        //  Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        return toast;
    }


    public static Toast Show_Toast1(Context ctx, String msg) {
       /* Toast t = new Toast(ctx);
        t.setText(msg + "");
        t.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);

        t.setDuration(Toast.LENGTH_SHORT);
        t.show();*/


        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
        //  Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        return toast;
    }


  /*  public static Toast Show_Toast(Context ctx, String msg) {
        Toast t = new Toast(ctx);
        t.setText(msg + "");

        t.setDuration(Toast.LENGTH_SHORT);
        t.show();
        return t;
    }*/

    /**
     * DISPLAY DIALOG
     **/
    public static void showDialog(final Context context, String title, String message) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.aommoncls_dialogbox, null);
        TextView titileTextView = (TextView) dialogView.findViewById(R.id.tv_titile);
        CustomTextView msgTextView = (CustomTextView) dialogView.findViewById(R.id.tv_msg);
        Button dialogButtonOKButton = (Button) dialogView.findViewById(R.id.dialogButtonOK);
        titileTextView.setTypeface(Validations.setTypeface(context));
        msgTextView.setTypeface(Validations.setTypeface(context));
        dialogButtonOKButton.setTypeface(Validations.setTypeface(context));
        msgTextView.setText(message + "");
        titileTextView.setText(title + "");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //  final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
        final AlertDialog b = builder.create();
        //  builder.setTitle("Material Style Dialog");
        builder.setCancelable(true);
        builder.setView(dialogView);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        final AlertDialog show = builder.show();
        dialogButtonOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
    }

    public static void noInterNetDialog(final Context context, String title, String message, final NoInterNetDailogClickListner noInterNetDailogClickListner) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.no_inter_net_dialog, null);
        TextView titileTextView = (TextView) dialogView.findViewById(R.id.tv_titile);
        CustomTextView msgTextView = (CustomTextView) dialogView.findViewById(R.id.tv_msg);
        CustomButtonView dialogButtonOKButton = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonOK);
        CustomButtonView dialogButtonCancel = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonCancel);
        titileTextView.setTypeface(Validations.setTypeface(context));
        msgTextView.setTypeface(Validations.setTypeface(context));
        dialogButtonOKButton.setTypeface(Validations.setTypeface(context));
        msgTextView.setText(message + "");
        titileTextView.setText(title + "");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //  final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
        final AlertDialog b = builder.create();
        //  builder.setTitle("Material Style Dialog");
        builder.setCancelable(true);
        builder.setView(dialogView);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        final AlertDialog show = builder.show();
        dialogButtonOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                noInterNetDailogClickListner.onDialogOkButtonClicked();
            }
        });
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                noInterNetDailogClickListner.onDialogCancelButtonClicked();
            }
        });
    }
    public static void hideOTPDialog() {
        if (show != null) {
            if (show.isShowing()) {
                show.dismiss();
            }
        }

    }






    public static void showDialog4YNo(final Context context, String title, String message, final DailogCallBackOkButtonClick dailogCallBackOkButtonClick, final DailogCallBackCancelButtonClick dailogCallBackCancelButtonClick) {
        title = title + "";
        if (title.contentEquals("")) {
            title = context.getResources().getString(R.string.app_name);
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.aommoncls_dialogbox_redblack_guj, null);
        TextView titileTextView = (TextView) dialogView.findViewById(R.id.tv_titile);
        CustomTextView msgTextView = (CustomTextView) dialogView.findViewById(R.id.tv_msg);
        CustomTextView tv_msg_guj = (CustomTextView) dialogView.findViewById(R.id.tv_msg_guj);
        CustomButtonView dialogButtonOKButton = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonOK);
        CustomButtonView dialogButtonCancel = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonCancel);
        dialogButtonCancel.setTypeface(Validations.setTypeface(context));
        titileTextView.setTypeface(Validations.setTypeface(context));
        titileTextView.setText(title + "");
        msgTextView.setTypeface(Validations.setTypeface(context));
        tv_msg_guj.setTypeface(Validations.setTypeface(context));
        dialogButtonCancel.setVisibility(View.VISIBLE);
        dialogButtonOKButton.setTypeface(Validations.setTypeface(context));
        msgTextView.setText(message + "");
        //    if()
        tv_msg_guj.setTextColor(context.getResources().getColor(R.color.colorAccent));
//        tv_msg_guj.setText(context.getResources().getString(R.string.ADD_MEMBER) + "");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //  final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
        final AlertDialog b = builder.create();
        //  builder.setTitle("Material Style Dialog");
        builder.setCancelable(false);
        builder.setView(dialogView);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        final AlertDialog show = builder.show();
        dialogButtonOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                if (dailogCallBackOkButtonClick != null)
                    dailogCallBackOkButtonClick.onDialogOkButtonClicked();
            }
        });

        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                if (dailogCallBackCancelButtonClick != null)
                    dailogCallBackCancelButtonClick.onDialogCancelButtonClicked();
            }
        });
    }
    public static void showDialog4YNo4Update(final Context context, String title, String message, final DailogCallBackOkButtonClick dailogCallBackOkButtonClick, final DailogCallBackCancelButtonClick dailogCallBackCancelButtonClick) {
        title = title + "";
        if (title.contentEquals("")) {
            title = context.getResources().getString(R.string.app_name);
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.aommoncls_dialogbox_redblack_guj, null);
        TextView titileTextView = (TextView) dialogView.findViewById(R.id.tv_titile);
        CustomTextView msgTextView = (CustomTextView) dialogView.findViewById(R.id.tv_msg);
        CustomTextView tv_msg_guj = (CustomTextView) dialogView.findViewById(R.id.tv_msg_guj);
        CustomButtonView dialogButtonOKButton = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonOK);
        CustomButtonView dialogButtonCancel = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonCancel);
        dialogButtonCancel.setTypeface(Validations.setTypeface(context));
        titileTextView.setTypeface(Validations.setTypeface(context));
        titileTextView.setText(title + "");
        msgTextView.setTypeface(Validations.setTypeface(context));
        tv_msg_guj.setTypeface(Validations.setTypeface(context));
        dialogButtonCancel.setVisibility(View.VISIBLE);
        dialogButtonOKButton.setTypeface(Validations.setTypeface(context));
        msgTextView.setText(message + "");
        //    if()
        tv_msg_guj.setTextColor(context.getResources().getColor(R.color.colorAccent));
//        tv_msg_guj.setText(context.getResources().getString(R.string.ADD_MEMBER) + "");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //  final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
        final AlertDialog b = builder.create();
        //  builder.setTitle("Material Style Dialog");
        builder.setCancelable(true);
        builder.setView(dialogView);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        final AlertDialog show = builder.show();
        dialogButtonOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                if (dailogCallBackOkButtonClick != null)
                    dailogCallBackOkButtonClick.onDialogOkButtonClicked();
            }
        });

        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                if (dailogCallBackCancelButtonClick != null)
                    dailogCallBackCancelButtonClick.onDialogCancelButtonClicked();
            }
        });
    }
    public static void showDialog4Yes(final Context context, String title, String message, final DailogCallBackOkButtonClick dailogCallBackOkButtonClick, final DailogCallBackCancelButtonClick dailogCallBackCancelButtonClick) {
        title = title + "";
        if (title.contentEquals("")) {
            title = context.getResources().getString(R.string.app_name);
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.aommoncls_dialogbox_redblack_guj, null);
        TextView titileTextView = (TextView) dialogView.findViewById(R.id.tv_titile);
        CustomTextView msgTextView = (CustomTextView) dialogView.findViewById(R.id.tv_msg);
        CustomTextView tv_msg_guj = (CustomTextView) dialogView.findViewById(R.id.tv_msg_guj);
        CustomButtonView dialogButtonOKButton = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonOK);
        CustomButtonView dialogButtonCancel = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonCancel);
        dialogButtonCancel.setTypeface(Validations.setTypeface(context));
        titileTextView.setTypeface(Validations.setTypeface(context));
        titileTextView.setText(title + "");
        msgTextView.setTypeface(Validations.setTypeface(context));
        tv_msg_guj.setTypeface(Validations.setTypeface(context));
//        dialogButtonCancel.setVisibility(View.VISIBLE);
        dialogButtonCancel.setVisibility(View.GONE);
        dialogButtonOKButton.setTypeface(Validations.setTypeface(context));
        msgTextView.setText(message + "");
        //    if()
        tv_msg_guj.setTextColor(context.getResources().getColor(R.color.colorAccent));
//        tv_msg_guj.setText(context.getResources().getString(R.string.ADD_MEMBER) + "");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //  final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
        final AlertDialog b = builder.create();
        //  builder.setTitle("Material Style Dialog");
//        builder.setCancelable(false);
        builder.setCancelable(false);
        builder.setView(dialogView);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        final AlertDialog show = builder.show();
        dialogButtonOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                if (dailogCallBackOkButtonClick != null)
                    dailogCallBackOkButtonClick.onDialogOkButtonClicked();
            }
        });

        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                if (dailogCallBackCancelButtonClick != null)
                    dailogCallBackCancelButtonClick.onDialogCancelButtonClicked();
            }
        });
    }








    public interface NoInterNetDailogClickListner {
        public void onDialogOkButtonClicked();

        public void onDialogCancelButtonClicked();
    }

    public interface DailogCallBackOkButtonClick {
        public void onDialogOkButtonClicked();

    }

    /*THEME */
    public static void showDialog4Activity(final Context context, String title, String message, final DailogCallBackOkButtonClick dailogCallBackOkButtonClick) {
        title=title+"";
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.aommoncls_dialogbox, null);
        TextView titileTextView = (TextView) dialogView.findViewById(R.id.tv_titile);
        CustomTextView msgTextView = (CustomTextView) dialogView.findViewById(R.id.tv_msg);
        Button dialogButtonOKButton = (Button) dialogView.findViewById(R.id.dialogButtonOK);
        titileTextView.setTypeface(Validations.setTypeface(context));
        msgTextView.setTypeface(Validations.setTypeface(context));
        dialogButtonOKButton.setTypeface(Validations.setTypeface(context));
        msgTextView.setText(message + "");
        if(!title.contentEquals("")){
            titileTextView.setText(title + "");
        }else{
            titileTextView.setText(context.getResources().getString(R.string.app_name) + "");
        }

//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.myDialog));
        final AlertDialog b = builder.create();
        //  builder.setTitle("Material Style Dialog");
        builder.setCancelable(true);
        builder.setView(dialogView);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        final AlertDialog show = builder.show();
        dialogButtonOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
                if (dailogCallBackOkButtonClick != null)
                    dailogCallBackOkButtonClick.onDialogOkButtonClicked();
            }
        });
    }


}
