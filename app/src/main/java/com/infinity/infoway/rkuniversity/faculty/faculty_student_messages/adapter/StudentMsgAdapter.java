package com.infinity.infoway.rkuniversity.faculty.faculty_student_messages.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.faculty.faculty_student_messages.pojo.StudentMsgListPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentMsgAdapter extends RecyclerView.Adapter<StudentMsgAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<StudentMsgListPojo> studentMsgListPojoArrayList;
    private LayoutInflater layoutInflater;

    public StudentMsgAdapter(Context context, ArrayList<StudentMsgListPojo> studentMsgListPojoArrayList) {
        this.context = context;
        this.studentMsgListPojoArrayList = studentMsgListPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_for_student_msgs, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentMsgListPojo studentMsgListPojo = studentMsgListPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentMsgListPojo.getStudentName())) {
            holder.tvMsgFrom.setText(studentMsgListPojo.getStudentName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentMsgListPojo.getSmtTitle())) {
            holder.tvMsgTitle.setText(studentMsgListPojo.getSmtTitle());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentMsgListPojo.getSmtMessage())) {
            holder.tvMsg.setText(studentMsgListPojo.getSmtMessage());
        }

    }

    @Override
    public int getItemCount() {
        return studentMsgListPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewMediumFont tvMsgFrom;
        TextViewRegularFont tvMsgTitle;
        TextViewRegularFont tvMsg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMsgFrom = itemView.findViewById(R.id.tvMsgFrom);
            tvMsgTitle = itemView.findViewById(R.id.tvMsgTitle);
            tvMsg = itemView.findViewById(R.id.tvMsg);
        }
    }

}
