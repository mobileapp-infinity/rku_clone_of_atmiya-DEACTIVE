package com.infinity.infoway.rkuniversity.student.student_pay_fee_new.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetPendingFeeListFromFeeTypePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentPayFeeChildListAdapter extends RecyclerView.Adapter<StudentPayFeeChildListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<GetPendingFeeListFromFeeTypePojo.Fee> pendingFeeArrayList;
    private LayoutInflater layoutInflater;
    private IError iError;
    private IOnPartialValueChanged iOnPartialValueChanged;
    private MaterialButton btnPayNow;
    private TextViewRegularFont tvTotalPartialAmount;

    public StudentPayFeeChildListAdapter(Context context, ArrayList<GetPendingFeeListFromFeeTypePojo.Fee> pendingFeeArrayList,
                                         MaterialButton btnPayNow, TextViewRegularFont tvTotalPartialAmount,
                                         IOnPartialValueChanged iOnPartialValueChanged) {
        this.context = context;
        iError = (IError) context;
        this.btnPayNow = btnPayNow;
        this.tvTotalPartialAmount = tvTotalPartialAmount;
        this.iOnPartialValueChanged = iOnPartialValueChanged;
        this.pendingFeeArrayList = pendingFeeArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student__pay_fee_child_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GetPendingFeeListFromFeeTypePojo.Fee fee = pendingFeeArrayList.get(position);
        String sign = "+";

        if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getIsRebate()) && fee.getIsRebate().equalsIgnoreCase("1")) {
            sign = "-";
        }

        fee.setPartialAmountIsCorrect(true);

        if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getFeeName())) {
            holder.tvFeeType.setText(fee.getFeeName());
        }

        try {
            if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getMinimumFee())) {
                int minFee = (int) Double.parseDouble(fee.getMinimumFee());
                if (minFee <= 0) {
                    holder.tvMinFee.setText("-");
                    holder.tvPartialFee.setVisibility(View.VISIBLE);
                    holder.edtEnterPartialFeeAmount.setVisibility(View.GONE);
                    if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getPendingFee())) {
                        holder.tvPartialFee.setText(sign + fee.getPendingFee() + "");
                        fee.setPartialAmountForTotal(Double.parseDouble(fee.getPendingFee()));
                        holder.tvPendingAmount.setText(sign + fee.getPendingFee() + "");
                    }
                } else {
                    fee.setPartialAmountForTotal(0.0);
                    double minAmt = Double.parseDouble(fee.getMinimumFee());
                    double maxAmt = Double.parseDouble(fee.getPendingFee());

                    holder.tvMinFee.setText(fee.getMinimumFee() + "");
                    holder.tvPartialFee.setVisibility(View.GONE);
                    holder.edtEnterPartialFeeAmount.setVisibility(View.VISIBLE);
                    if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getPendingFee())) {
                        holder.tvPendingAmount.setText(sign + fee.getPendingFee() + "");
                    }

                    holder.edtEnterPartialFeeAmount.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void afterTextChanged(Editable s) {

                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(s.toString())) {
                                double enteredAmt = Double.parseDouble(s.toString());
                                if (minAmt <= enteredAmt && enteredAmt <= maxAmt) {
                                    fee.setPartialAmountForTotal(enteredAmt);
                                    fee.setPartialAmountIsCorrect(true);
                                    iError.onError(false, fee.getFeeName(), maxAmt, minAmt);
                                } else {
                                    fee.setPartialAmountForTotal(0.0);
                                    iError.onError(true, fee.getFeeName(), maxAmt, minAmt);
                                    fee.setPartialAmountIsCorrect(false);
                                }
                                iOnPartialValueChanged.onPartialValueChange(pendingFeeArrayList, btnPayNow, tvTotalPartialAmount,
                                        false);
                            } else {
                                fee.setPartialAmountForTotal(0.0);
                                fee.setPartialAmountIsCorrect(true);
                                iError.onError(false, fee.getFeeName(), maxAmt, minAmt);
                                iOnPartialValueChanged.onPartialValueChange(pendingFeeArrayList, btnPayNow, tvTotalPartialAmount,
                                        false);
                            }
                        }
                    });
                }
            } else {
                holder.tvMinFee.setText("-");
                holder.tvPartialFee.setVisibility(View.VISIBLE);
                holder.edtEnterPartialFeeAmount.setVisibility(View.GONE);
                if (!CommonUtil.checkIsEmptyOrNullCommon(fee.getPendingFee())) {
                    holder.tvPartialFee.setText(sign + fee.getPendingFee() + "");
                    fee.setPartialAmountForTotal(Double.parseDouble(fee.getPendingFee()));
                    holder.tvPendingAmount.setText(sign + fee.getPendingFee() + "");
                }
            }
        } catch (Exception ex) {
            Toast.makeText(context, "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return pendingFeeArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextViewRegularFont tvFeeType;
        private TextViewRegularFont tvMinFee;
        private TextViewRegularFont tvPendingAmount;
        private AppCompatEditText edtEnterPartialFeeAmount;
        private TextViewRegularFont tvPartialFee;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFeeType = itemView.findViewById(R.id.tvFeeType);
            tvMinFee = itemView.findViewById(R.id.tvMinFee);
            tvPendingAmount = itemView.findViewById(R.id.tvPendingAmount);
            edtEnterPartialFeeAmount = itemView.findViewById(R.id.edtEnterPartialFeeAmount);
            tvPartialFee = itemView.findViewById(R.id.tvPartialFee);
        }
    }

    public interface IError {
        void onError(boolean isError, String feeType, double maxAmt, double minAmt);
    }

    public interface IOnPartialValueChanged {
        void onPartialValueChange(ArrayList<GetPendingFeeListFromFeeTypePojo.Fee> pendingFeeArrayList,
                                  MaterialButton btnPayNow, TextViewRegularFont tvTotalPartialAmount,
                                  boolean isFirstTime);
    }
}
