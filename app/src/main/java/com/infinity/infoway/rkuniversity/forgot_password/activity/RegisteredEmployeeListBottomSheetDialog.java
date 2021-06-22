package com.infinity.infoway.rkuniversity.forgot_password.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.forgot_password.adapter.RegisterEmployeeListAdapter;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetUserWiseDetailForgetPasswordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.OtpBaseLoginDetailsForEmployeePojo;

import java.util.ArrayList;


public class RegisteredEmployeeListBottomSheetDialog extends BottomSheetDialogFragment {

    ForgotPasswordActivity context;
    RecyclerView rvRegisterEmployeeList;
    ArrayList<OtpBaseLoginDetailsForEmployeePojo.TableBean> tableBeanArrayList;
    GetUserWiseDetailForgetPasswordPojo getUserWiseDetailForgetPasswordPojo;
    String instituteId = "";


    RegisteredEmployeeListBottomSheetDialog() {

    }

//    RegisteredEmployeeListBottomSheetDialog(Context context,
//                                            ArrayList<OtpBaseLoginDetailsForEmployeePojo.TableBean> tableBeanArrayList, String instituteId) {
//        this.context = (ForgotPasswordActivity) context;
//        this.tableBeanArrayList = tableBeanArrayList;
//        this.instituteId = instituteId;
//    }

    RegisteredEmployeeListBottomSheetDialog(Context context,
                                            GetUserWiseDetailForgetPasswordPojo getUserWiseDetailForgetPasswordPojo, String instituteId) {
        this.context = (ForgotPasswordActivity) context;
        this.getUserWiseDetailForgetPasswordPojo = getUserWiseDetailForgetPasswordPojo;
        this.instituteId = instituteId;
    }


    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_register_employee_bottom_sheet,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvRegisterEmployeeList = view.findViewById(R.id.rvRegisterEmployeeList);
        rvRegisterEmployeeList.setAdapter(new RegisterEmployeeListAdapter(context, tableBeanArrayList, getUserWiseDetailForgetPasswordPojo, instituteId));
    }

}
