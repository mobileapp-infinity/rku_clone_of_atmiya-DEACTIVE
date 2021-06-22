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

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeClassName())) {
            holder.tvClassName.setText(feeReceiptPojo.getFeeClassName());
            holder.tvClasName_.setText(feeReceiptPojo.getFeeClassName());

        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeAmt())) {
            holder.tvFeeAmount.setText("Rs. " + feeReceiptPojo.getFeeAmt() + "/-");
            holder.tvFeeAmount_.setText("Rs. " + feeReceiptPojo.getFeeAmt() + "/-");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeReceiptNo())) {
            holder.tvFeeReceiptNo.setText(feeReceiptPojo.getFeeReceiptNo() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeReceiptDate())) {
            holder.tvFeeReceiptDate.setText(feeReceiptPojo.getFeeReceiptDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeBankDocumentType())) {
            holder.tvPaymentMode.setText(feeReceiptPojo.getFeeBankDocumentType() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeRefNo())) {
            holder.tvReferenceNo.setText(feeReceiptPojo.getFeeRefNo() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(feeReceiptPojo.getFeeBankName())) {
            holder.tvBankName.setText(feeReceiptPojo.getFeeBankName() + "");
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
                downloadFeeReceiptApiCall(feeReceiptPojoArrayList.get(position).getFeeReceiptNo());
            }
        });

    }


    private void downloadFeeReceiptApiCall(String feeReceiptNo) {
        DialogUtil.showProgressDialogNotCancelable(context, "downloading... ");
//        progressDialog.show();
        ApiImplementer.downloadFeeReceiptApiImplementer(mySharedPreferences.getStudentId(), feeReceiptNo, new Callback<PrintFeeReceiptPojo>() {
            @Override
            public void onResponse(Call<PrintFeeReceiptPojo> call, Response<PrintFeeReceiptPojo> response) {
                try {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 1 &&
                            response.body().getBase64string() != null && !response.body().getBase64string().isEmpty()) {
                        new GeneratePDFFileFromBase64String(context, "Fee Receipt", response.body().getFilename(),
                                response.body().getBase64string());
                    } else {
//                        progressDialog.hide();
                        Toast.makeText(context, "Some thing went wrong please try again later.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
//                    progressDialog.hide();
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PrintFeeReceiptPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
//                progressDialog.hide();
                Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return feeReceiptPojoArrayList.size();
    }

    class MyViewHolderFeeReceipt extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreBtn;
        LinearLayout llExpandableLayout;
        LinearLayout llExpandedHeader;
        TextViewRegularFont tvClassName, tvFeeAmount, tvClasName_, tvFeeAmount_;
        TextViewMediumFont tvFeeReceiptNo, tvFeeReceiptDate, tvPaymentMode, tvReferenceNo, tvBankName;
        TextViewRegularFont tvPreintFeeReceipt;

        public MyViewHolderFeeReceipt(@NonNull View itemView) {
            super(itemView);
            ivViewMoreBtn = itemView.findViewById(R.id.ivViewMoreBtn);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvFeeAmount = itemView.findViewById(R.id.tvFeeAmount);
            tvClasName_ = itemView.findViewById(R.id.tvClasName_);
            tvFeeAmount_ = itemView.findViewById(R.id.tvFeeAmount_);
            tvFeeReceiptNo = itemView.findViewById(R.id.tvFeeReceiptNo);
            tvPreintFeeReceipt = itemView.findViewById(R.id.tvPreintFeeReceipt);
            tvFeeReceiptDate = itemView.findViewById(R.id.tvFeeReceiptDate);
            tvPaymentMode = itemView.findViewById(R.id.tvPaymentMode);
            tvReferenceNo = itemView.findViewById(R.id.tvReferenceNo);
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
