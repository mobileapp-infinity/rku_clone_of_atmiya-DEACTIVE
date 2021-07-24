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
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.activity.StudentPendingFeeWebViewActivity;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetOnlineFeePaymentURLPojo;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetPendingFeeListFromFeeTypePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    double totalPartialPayment = Double.parseDouble(holder.tvTotalPartialAmount.getText().toString().trim());
                    double totalTotalPayment = Double.parseDouble(holder.tvTotalPendingAmount.getText().toString().trim());
                    if (totalPartialPayment <= totalTotalPayment) {
                        payNowApiCall(getPendingFeeListFromFeeTypePojo,
                                (ArrayList<GetPendingFeeListFromFeeTypePojo.Fee>) getPendingFeeListFromFeeTypePojo.getFees(),
                                holder.tvTotalPendingAmount.getText().toString().trim());
                    } else {
                        Toast.makeText(context, "Partial amount cant not be greater then total amount!", Toast.LENGTH_SHORT).show();
                    }
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


    private void payNowApiCall(GetPendingFeeListFromFeeTypePojo getPendingFeeListFromFeeTypePojo, ArrayList<GetPendingFeeListFromFeeTypePojo.Fee> feeArrayList,
                               String totalPendingAmount) {
        try {
            String StudId = mySharedPreferences.getStudentId();
            String HeadId = getPendingFeeListFromFeeTypePojo.getHeadId();
            String SemId = getPendingFeeListFromFeeTypePojo.getSemId();
            String InstId = mySharedPreferences.getInstituteId();
            String YearId = getPendingFeeListFromFeeTypePojo.getYearId();
            String TotalPendingAmt = totalPendingAmount;
            String Response;
            JSONArray itemDetailsJsonArray = new JSONArray();
            JSONObject itemJson;

            for (int i = 0; i < feeArrayList.size(); i++) {
                GetPendingFeeListFromFeeTypePojo.Fee fee = feeArrayList.get(i);
                {
                    itemJson = new JSONObject();

                    String partialPayment = "";
                    String feeId = "";
                    String isRebate = "";
                    String minimumFee = "";
                    String pendingFee = "";

                    if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getFeeId())) {
                        feeId = fee.getFeeId();
                    }

                    if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getIsRebate())) {
                        isRebate = fee.getIsRebate();
                    }

                    if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getMinimumFee())) {
                        minimumFee = fee.getMinimumFee();
                    }

                    if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getMinimumFee())) {
                        int minFee = (int) Double.parseDouble(fee.getMinimumFee());
                        if (minFee <= 0) {
                            partialPayment = fee.getPendingFee();
                        } else {
                            partialPayment = fee.getPartialAmountForTotal() + "";
                        }
                    } else {
                        partialPayment = fee.getPendingFee();
                    }

                    if (CommonUtil.checkIsEmptyOrNullCommon(partialPayment) || Double.valueOf(partialPayment) <= 0) {
                        Toast.makeText(context, "Please enter " + fee.getFeeName() + " Fee Partial Amount!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getPendingFee())) {
                        pendingFee = fee.getPendingFee();
                    }

                    itemJson.put("FTypeId", feeId);
                    itemJson.put("IsRebate", isRebate);
                    itemJson.put("MinimumFee", minimumFee);
                    itemJson.put("PartialPayment", partialPayment);
                    itemJson.put("PendingFee", pendingFee);

                    itemDetailsJsonArray.put(itemJson);
                }
            }

            Response = itemDetailsJsonArray.toString();
            DialogUtil.showProgressDialogNotCancelable(context, "");
            ApiImplementer.getOnlineFeePaymentUrlApiImplementer(StudId, HeadId, SemId, InstId, YearId, TotalPendingAmt, Response, new Callback<GetOnlineFeePaymentURLPojo>() {
                @Override
                public void onResponse(Call<GetOnlineFeePaymentURLPojo> call, Response<GetOnlineFeePaymentURLPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null) {
                        if (!CommonUtil.checkIsEmptyOrNullCommon(response.body().getStatusMsg())) {
                            if (response.body().getStatusCode().equalsIgnoreCase("1")) {
                                Toast.makeText(context, "" + response.body().getStatusMsg(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, StudentPendingFeeWebViewActivity.class);
                                intent.putExtra(IntentConstants.STUDENT_PENDING_FEE_URL, "www.google.com");
                                context.startActivity(intent);
                            } else {
                                Toast.makeText(context, "" + response.body().getStatusMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Something went wrong,Please try again later!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Something went wrong,Please try again later!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetOnlineFeePaymentURLPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(context, "Failed to pay!", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception exception) {
            DialogUtils.hideProgressDialog();
            Toast.makeText(context, "Exception to pay fee!", Toast.LENGTH_SHORT).show();
        }
    }

}
