package com.infinity.infoway.rkuniversity.student.fee_details.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.Animations;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.FeeReceiptPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PrintFeeReceiptPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.GeneratePDFFileFromBase64String;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeReceiptListAdapter extends RecyclerView.Adapter<FeeReceiptListAdapter.MyViewHolderFeeReceipt> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FeeReceiptPojo> feeReceiptPojoArrayList;
    MySharedPreferences mySharedPreferences;
//    ProgressDialog progressDialog;

    public FeeReceiptListAdapter(Context context, ArrayList<FeeReceiptPojo> feeReceiptPojoArrayList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.feeReceiptPojoArrayList = feeReceiptPojoArrayList;
        mySharedPreferences = new MySharedPreferences(context);
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Please wait....");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @NonNull
    @Override
    public FeeReceiptListAdapter.MyViewHolderFeeReceipt onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_fee_receipt_list_item, parent, false);
        return new MyViewHolderFeeReceipt(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeeReceiptListAdapter.MyViewHolderFeeReceipt holder, int position) {


        FeeReceiptPojo feeReceiptPojo = feeReceiptPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getStudName())) {
            holder.tvClassName.setText(feeReceiptPojo.getStudName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getSemName())){
            holder.tvClasName_.setText(feeReceiptPojo.getSemName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getTwfPaymentAmount())) {
            holder.tvFeeAmount_.setText("Rs. " + feeReceiptPojo.getTwfPaymentAmount() + "/-");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getSwfReceiptNo())) {
            holder.tvFeeReceiptNo.setText(feeReceiptPojo.getSwfReceiptNo() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFinalPaymentDate())) {
            holder.tvPaymentDate.setText(feeReceiptPojo.getFinalPaymentDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeePayType())) {
            holder.tvPaymentMode.setText(feeReceiptPojo.getFeePayType() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getBankTrans())) {
            holder.tvBankName.setText(feeReceiptPojo.getBankTrans() + "");
        }


        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!feeReceiptPojoArrayList.get(position).isExpanded(), holder.ivViewMoreBtn, holder.llExpandableLayout);
                feeReceiptPojoArrayList.get(position).setExpanded(show);
            }
        });

        holder.tvPreintFeeReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feeReceiptPojo.getReceiptBase64string() != null && !feeReceiptPojo.getReceiptBase64string().isEmpty()) {
                    new GeneratePDFFileFromBase64String(context, "Fee Receipt", feeReceiptPojo.getReceiptName()+ System.currentTimeMillis(),
                            feeReceiptPojo.getReceiptBase64string());
                } else {
//                        progressDialog.hide();
                    Toast.makeText(context, "Some thing went wrong please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


//    private void downloadFeeReceiptApiCall(String feeReceiptNo) {
//        DialogUtil.showProgressDialogNotCancelable(context, "downloading... ");
////        progressDialog.show();
//        ApiImplementer.downloadFeeReceiptApiImplementer(mySharedPreferences.getStudentId(), feeReceiptNo, new Callback<PrintFeeReceiptPojo>() {
//            @Override
//            public void onResponse(Call<PrintFeeReceiptPojo> call, Response<PrintFeeReceiptPojo> response) {
//                try {
//                    DialogUtil.hideProgressDialog();
//
//                } catch (Exception ex) {
////                    progressDialog.hide();
//                    ex.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PrintFeeReceiptPojo> call, Throwable t) {
//                DialogUtil.hideProgressDialog();
////                progressDialog.hide();
//                Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @Override
    public int getItemCount() {
        return feeReceiptPojoArrayList.size();
    }

    class MyViewHolderFeeReceipt extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreBtn;
        LinearLayout llExpandableLayout;
        LinearLayout llExpandedHeader;
        TextViewRegularFont tvClassName, tvClasName_, tvFeeAmount_;
        TextViewMediumFont tvPaymentDate;
//        TextViewRegularFont tvFeeAmount;

        TextViewMediumFont tvFeeReceiptNo, tvFeeReceiptDate, tvPaymentMode, tvReferenceNo, tvBankName;
        TextViewRegularFont tvPreintFeeReceipt;

        public MyViewHolderFeeReceipt(@NonNull View itemView) {
            super(itemView);
            ivViewMoreBtn = itemView.findViewById(R.id.ivViewMoreBtn);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            tvClassName = itemView.findViewById(R.id.tvClassName);
//            tvFeeAmount = itemView.findViewById(R.id.tvFeeAmount);
            tvPaymentMode = itemView.findViewById(R.id.tvPaymentMode);
            tvClasName_ = itemView.findViewById(R.id.tvClasName_);
            tvFeeAmount_ = itemView.findViewById(R.id.tvFeeAmount_);
            tvFeeReceiptNo = itemView.findViewById(R.id.tvFeeReceiptNo);
            tvPreintFeeReceipt = itemView.findViewById(R.id.tvPreintFeeReceipt);
            tvPaymentDate = itemView.findViewById(R.id.tvPaymentDate);
            tvBankName = itemView.findViewById(R.id.tvBankName);
        }
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
