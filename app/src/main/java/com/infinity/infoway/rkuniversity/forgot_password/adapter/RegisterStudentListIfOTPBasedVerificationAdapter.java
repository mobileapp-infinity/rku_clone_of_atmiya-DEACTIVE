package com.infinity.infoway.rkuniversity.forgot_password.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.OtpBaseLoginDetailsForStudentPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class RegisterStudentListIfOTPBasedVerificationAdapter extends RecyclerView.Adapter<RegisterStudentListIfOTPBasedVerificationAdapter.MyViewHolder> {

    Context context;
    ArrayList<OtpBaseLoginDetailsForStudentPojo.TableBean> tableBeanArrayList;
    LayoutInflater layoutInflater;
    ISelectRegisterStudentIfOTPBasedLogin iSelectRegisterStudentIfOTPBasedLogin;
    String instituteId = "";

    public RegisterStudentListIfOTPBasedVerificationAdapter(Context context, ArrayList<OtpBaseLoginDetailsForStudentPojo.TableBean> tableBeanArrayList,
                                                            String instituteId) {
        this.context = context;
        this.tableBeanArrayList = tableBeanArrayList;
        layoutInflater = LayoutInflater.from(context);
        iSelectRegisterStudentIfOTPBasedLogin = (ISelectRegisterStudentIfOTPBasedLogin) context;
        this.instituteId = instituteId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_register_student_list_if_otp_based_verification, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OtpBaseLoginDetailsForStudentPojo.TableBean tableBean = tableBeanArrayList.get(position);

        if (position == tableBeanArrayList.size() - 1) {
            holder.line.setVisibility(View.GONE);
        }

        String userName = "";
        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getStudName())) {
            userName = tableBean.getStudName() + " ";
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getStudUserName())) {
            userName += "(" + tableBean.getStudUserName() + ")";
        }

        holder.tvRegisterStudentIfOTPBasedVerification.setText(userName);

        holder.cbSelectRegisterStudentIfOTPBasedVerification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iSelectRegisterStudentIfOTPBasedLogin.onOtpBasedVerificationRegisteredStudentSelected(tableBean, instituteId);
                        }
                    }, 80);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tableBeanArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvRegisterStudentIfOTPBasedVerification;
        AppCompatCheckBox cbSelectRegisterStudentIfOTPBasedVerification;
        View line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRegisterStudentIfOTPBasedVerification = itemView.findViewById(R.id.tvRegisterStudentIfOTPBasedVerification);
            cbSelectRegisterStudentIfOTPBasedVerification = itemView.findViewById(R.id.cbSelectRegisterStudentIfOTPBasedVerification);
            line = itemView.findViewById(R.id.line);
        }
    }


    public interface ISelectRegisterStudentIfOTPBasedLogin {
        void onOtpBasedVerificationRegisteredStudentSelected(OtpBaseLoginDetailsForStudentPojo.TableBean tableBean, String instituteId);
    }

}
