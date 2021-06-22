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
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetStudentForgotPasswordDetailsPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class RegisterStudentListAdapter extends RecyclerView.Adapter<RegisterStudentListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<GetStudentForgotPasswordDetailsPojo> getStudentForgotPasswordDetailsPojoArrayList;
    private IUserListDialog iUserListDialog;

    public RegisterStudentListAdapter(Context context, ArrayList<GetStudentForgotPasswordDetailsPojo> getStudentForgotPasswordDetailsPojoArrayList) {
        this.context = context;
        this.getStudentForgotPasswordDetailsPojoArrayList = getStudentForgotPasswordDetailsPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
        iUserListDialog = (IUserListDialog) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_register_user_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GetStudentForgotPasswordDetailsPojo getStudentForgotPasswordDetailsPojo = getStudentForgotPasswordDetailsPojoArrayList.get(position);
        String userName = "";
        if (!CommonUtil.checkIsEmptyOrNullCommon(getStudentForgotPasswordDetailsPojo.getName())) {
            userName = getStudentForgotPasswordDetailsPojo.getName() + " ";
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getStudentForgotPasswordDetailsPojo.getUsername())) {
            userName += "(" + getStudentForgotPasswordDetailsPojo.getUsername() + ")";
        }

        holder.tvRegisterUsername.setText(userName);

        holder.cbSelectRegisterUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iUserListDialog.closeUserListDialog(String.valueOf(getStudentForgotPasswordDetailsPojoArrayList.get(position).getId()),
                                    String.valueOf(getStudentForgotPasswordDetailsPojoArrayList.get(position).getEmpStudStatus()));
                        }
                    }, 80);
                }
            }
        });

        if (position == getStudentForgotPasswordDetailsPojoArrayList.size() - 1) {
            holder.line.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return getStudentForgotPasswordDetailsPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvRegisterUsername;
        AppCompatCheckBox cbSelectRegisterUser;
        View line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRegisterUsername = itemView.findViewById(R.id.tvRegisterUsername);
            cbSelectRegisterUser = itemView.findViewById(R.id.cbSelectRegisterUser);
            line = itemView.findViewById(R.id.line);
        }
    }

    public interface IUserListDialog {
        void closeUserListDialog(String emp_stud_id, String emp_stud_status);
    }

}
