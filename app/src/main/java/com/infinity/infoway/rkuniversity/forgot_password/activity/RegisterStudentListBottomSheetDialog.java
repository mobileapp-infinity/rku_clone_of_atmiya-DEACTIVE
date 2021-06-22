package com.infinity.infoway.rkuniversity.forgot_password.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.forgot_password.adapter.RegisterStudentListAdapter;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetStudentForgotPasswordDetailsPojo;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RegisterStudentListBottomSheetDialog extends BottomSheetDialogFragment {

    ForgotPasswordActivity context;
    RecyclerView rvRegisterStudentList;
    ArrayList<GetStudentForgotPasswordDetailsPojo> getStudentForgotPasswordDetailsPojoArrayList;

    RegisterStudentListBottomSheetDialog() {

    }

    RegisterStudentListBottomSheetDialog(Context context,
                                         ArrayList<GetStudentForgotPasswordDetailsPojo> getStudentForgotPasswordDetailsPojoArrayList) {
        this.context = (ForgotPasswordActivity) context;
        this.getStudentForgotPasswordDetailsPojoArrayList = getStudentForgotPasswordDetailsPojoArrayList;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_register_student_bottom_sheet,
                container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvRegisterStudentList = view.findViewById(R.id.rvRegisterStudentList);
        RegisterStudentListAdapter registerStudentListAdapter = new RegisterStudentListAdapter(context, getStudentForgotPasswordDetailsPojoArrayList);
        rvRegisterStudentList.setAdapter(registerStudentListAdapter);
    }


}