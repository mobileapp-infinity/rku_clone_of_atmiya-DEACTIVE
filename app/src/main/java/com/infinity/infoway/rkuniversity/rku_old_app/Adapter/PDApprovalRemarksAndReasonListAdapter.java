package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.models.PDApprovalRemarksAndReasonListModel;

import java.util.ArrayList;

public class PDApprovalRemarksAndReasonListAdapter extends RecyclerView.Adapter<PDApprovalRemarksAndReasonListAdapter.MyViewHolder> {

    Context context;
    ArrayList<PDApprovalRemarksAndReasonListModel> approvalRemarksAndReasonListModelArrayList;
    LayoutInflater layoutInflater;


    public PDApprovalRemarksAndReasonListAdapter(Context context, ArrayList<PDApprovalRemarksAndReasonListModel> approvalRemarksAndReasonListModelArrayList) {

        this.context = context;
        this.approvalRemarksAndReasonListModelArrayList = approvalRemarksAndReasonListModelArrayList;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.inflater_pd_approval_remarks_and_reason_list,viewGroup,false);
        return new PDApprovalRemarksAndReasonListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tvEmployeeNamePDApprovalRemarks.setText(approvalRemarksAndReasonListModelArrayList.get(i).getEmployeeName());
        myViewHolder.tvReasonPDApprovalRemarks.setText(approvalRemarksAndReasonListModelArrayList.get(i).getReason());
        myViewHolder.tvPDFrameworkCreditPDApprovalRemarks.setText(approvalRemarksAndReasonListModelArrayList.get(i).getPdFrameworkCredit());
        myViewHolder.tvNoOfOnDutyLeavesPDApprovalRemarks.setText(approvalRemarksAndReasonListModelArrayList.get(i).getNoOfOnDutyLeaves());

    }

    @Override
    public int getItemCount() {
        return approvalRemarksAndReasonListModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CustomBoldTextView tvEmployeeNamePDApprovalRemarks;
        CustomTextView tvReasonPDApprovalRemarks,tvPDFrameworkCreditPDApprovalRemarks,
                tvNoOfOnDutyLeavesPDApprovalRemarks;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEmployeeNamePDApprovalRemarks = itemView.findViewById(R.id.tvEmployeeNamePDApprovalRemarks);
            tvReasonPDApprovalRemarks = itemView.findViewById(R.id.tvReasonPDApprovalRemarks);
            tvPDFrameworkCreditPDApprovalRemarks = itemView.findViewById(R.id.tvPDFrameworkCreditPDApprovalRemarks);
            tvNoOfOnDutyLeavesPDApprovalRemarks = itemView.findViewById(R.id.tvNoOfOnDutyLeavesPDApprovalRemarks);
        }
    }

}
