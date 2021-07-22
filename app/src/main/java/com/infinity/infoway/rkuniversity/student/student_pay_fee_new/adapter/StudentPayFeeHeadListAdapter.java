package com.infinity.infoway.rkuniversity.student.student_pay_fee_new.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.activity.StudentPendingFeeWebViewActivity;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetPendingFeeListFromFeeTypePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StudentPayFeeHeadListAdapter extends RecyclerView.Adapter<StudentPayFeeHeadListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<GetPendingFeeListFromFeeTypePojo> pendingFeeArrayList;
    private LayoutInflater layoutInflater;
    private MySharedPreferences mySharedPreferences;

    public StudentPayFeeHeadListAdapter(Context context, ArrayList<GetPendingFeeListFromFeeTypePojo> pendingFeeArrayList) {
        this.context = context;
        this.pendingFeeArrayList = pendingFeeArrayList;
        mySharedPreferences = new MySharedPreferences(context);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_pay_fee_head, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GetPendingFeeListFromFeeTypePojo getPendingFeeListFromFeeTypePojo = pendingFeeArrayList.get(position);
        try {
            if (getPendingFeeListFromFeeTypePojo.getFees() != null && getPendingFeeListFromFeeTypePojo.getFees().size() > 0) {
                holder.btnPayNow.setVisibility(View.VISIBLE);
                holder.rvPayFeeTypeList.setAdapter(new StudentPayFeeChildListAdapter(context, (ArrayList<GetPendingFeeListFromFeeTypePojo.Fee>) getPendingFeeListFromFeeTypePojo.getFees(),
                        holder.btnPayNow, holder.tvTotalPartialAmount, new StudentPayFeeChildListAdapter.IOnPartialValueChanged() {
                    @Override
                    public void onPartialValueChange(ArrayList<GetPendingFeeListFromFeeTypePojo.Fee> pendingFeeArrayList, MaterialButton btnPayNow,
                                                     TextViewRegularFont tvTotalPartialAmount, boolean isFirstTime) {
                        calculatePartialFee(pendingFeeArrayList, btnPayNow, tvTotalPartialAmount, isFirstTime);
                    }
                }));
                calculatePartialFee((ArrayList<GetPendingFeeListFromFeeTypePojo.Fee>) getPendingFeeListFromFeeTypePojo.getFees(), holder.btnPayNow, holder.tvTotalPartialAmount, true);
                calculateTotalFee((ArrayList<GetPendingFeeListFromFeeTypePojo.Fee>) getPendingFeeListFromFeeTypePojo.getFees(), holder.tvTotalPendingAmount);
            } else {
                holder.btnPayNow.setVisibility(View.GONE);
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(getPendingFeeListFromFeeTypePojo.getSemName())) {
                holder.tvCourseAndSem.setText(getPendingFeeListFromFeeTypePojo.getSemName());
            }


            holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean show = toggleLayoutForDefaultOpenCard(!getPendingFeeListFromFeeTypePojo.isExpanded(), holder.ivViewMoreBtn, holder.llExpandableLayout);
                    getPendingFeeListFromFeeTypePojo.setExpanded(show);
                }
            });

            holder.btnPayNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StudentPendingFeeWebViewActivity.class);
                    intent.putExtra(IntentConstants.STUDENT_PENDING_FEE_URL, "https://www.google.com");//TODO need to change When ravibhai give api for pay fee
                    context.startActivity(intent);
                }
            });

        } catch (Exception exception) {
            Toast.makeText(context, "" + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return pendingFeeArrayList.size();
    }

    private void calculatePartialFee(ArrayList<GetPendingFeeListFromFeeTypePojo.Fee> pendingFeeArrayList, MaterialButton btnPayNow,
                                     TextViewRegularFont tvTotalPartialAmount, boolean isFirstTime) {
        try {
            double totalPartialFee = 0.0;
            for (int i = 0; i < pendingFeeArrayList.size(); i++) {
                GetPendingFeeListFromFeeTypePojo.Fee fee = pendingFeeArrayList.get(i);
                if (!fee.isPartialAmountIsCorrect()) {
                    btnPayNow.setEnabled(false);
                    break;
                } else {
                    if (isFirstTime) {
                        if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getMinimumFee())) {
                            int minFee = (int) Double.parseDouble(fee.getMinimumFee());
                            if (minFee <= 0) {
                                if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getIsRebate()) &&
                                        !CommonUtil.checkIsEmptyOrNullCommon(fee.getPendingFee())) {
                                    if (fee.getIsRebate().equalsIgnoreCase("1")) {
                                        totalPartialFee = totalPartialFee - Double.parseDouble(fee.getPendingFee());
                                    } else {
                                        totalPartialFee = totalPartialFee + Double.parseDouble(fee.getPendingFee());
                                    }
                                }
                            }
                        } else {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getIsRebate()) && !CommonUtil.checkIsEmptyOrNullCommon(fee.getPendingFee())) {
                                if (fee.getIsRebate().equalsIgnoreCase("1")) {
                                    totalPartialFee = totalPartialFee - Double.parseDouble(fee.getPendingFee());
                                } else {
                                    totalPartialFee = totalPartialFee + Double.parseDouble(fee.getPendingFee());
                                }
                            }
                        }
                    } else {
                        if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getIsRebate())) {
                            if (fee.getIsRebate().equalsIgnoreCase("1")) {
                                totalPartialFee = totalPartialFee - fee.getPartialAmountForTotal();
                            } else {
                                totalPartialFee = totalPartialFee + fee.getPartialAmountForTotal();
                            }
                        }
                    }
                    tvTotalPartialAmount.setText(new DecimalFormat("##.##").format(totalPartialFee));
                    btnPayNow.setEnabled(true);
                }
            }
        } catch (Exception ex) {
            Toast.makeText(context, "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateTotalFee(ArrayList<GetPendingFeeListFromFeeTypePojo.Fee> pendingFeeArrayList, TextViewRegularFont tvTotalPendingAmount) {
        double totalPendingAmount = 0.0;
        for (int i = 0; i < pendingFeeArrayList.size(); i++) {
            GetPendingFeeListFromFeeTypePojo.Fee fee = pendingFeeArrayList.get(i);
            if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getPendingFee())) {
                if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getIsRebate()) && fee.getIsRebate().equalsIgnoreCase("1")) {
                    totalPendingAmount = totalPendingAmount - Double.parseDouble(fee.getPendingFee());
                } else {
                    totalPendingAmount = totalPendingAmount + Double.parseDouble(fee.getPendingFee());
                }
            }
        }
        tvTotalPendingAmount.setText(new DecimalFormat("##.##").format(totalPendingAmount));
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout llExpandedHeader;
        private TextViewRegularFont tvCourseAndSem;
        private AppCompatImageView ivViewMoreBtn;
        private LinearLayout llExpandableLayout;
        private RecyclerView rvPayFeeTypeList;
        private MaterialButton btnPayNow;
        private TextViewRegularFont tvTotalPartialAmount;
        private TextViewRegularFont tvTotalPendingAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            tvCourseAndSem = itemView.findViewById(R.id.tvCourseAndSem);
            ivViewMoreBtn = itemView.findViewById(R.id.ivViewMoreBtn);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            rvPayFeeTypeList = itemView.findViewById(R.id.rvPayFeeTypeList);
            btnPayNow = itemView.findViewById(R.id.btnPayNow);
            tvTotalPartialAmount = itemView.findViewById(R.id.tvTotalPartialAmount);
            tvTotalPendingAmount = itemView.findViewById(R.id.tvTotalPendingAmount);
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
}
