package com.infinity.infoway.rkuniversity.student.attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.attendance.pojo.StudentSubjectWiseAttendancePojo;

import java.util.ArrayList;

public class SubjectWiseAttendanceAdapter extends RecyclerView.Adapter<SubjectWiseAttendanceAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<StudentSubjectWiseAttendancePojo> studentSubjectWiseAttendanceArrayListPojo;


    public SubjectWiseAttendanceAdapter(Context context, ArrayList<StudentSubjectWiseAttendancePojo> studentSubjectWiseAttendanceArrayListPojo) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.studentSubjectWiseAttendanceArrayListPojo = studentSubjectWiseAttendanceArrayListPojo;
    }

    @NonNull
    @Override
    public SubjectWiseAttendanceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_subject_wise_attendance, parent, false);
        return new SubjectWiseAttendanceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectWiseAttendanceAdapter.MyViewHolder holder, int position) {

        StudentSubjectWiseAttendancePojo.Data data = studentSubjectWiseAttendanceArrayListPojo.get(position).getInout_array1().get(0);

        holder.tvSubjectName.setText(studentSubjectWiseAttendanceArrayListPojo.get(position).getSub_fullname());

        if (data.getTot_lect() != null && !data.getTot_lect().isEmpty()) {
            holder.tvTotalLecture.setText(studentSubjectWiseAttendanceArrayListPojo.get(position).getInout_array1().get(0).getTot_lect());
        }

        if (data.getRemaining_lect() != null && !data.getRemaining_lect().isEmpty()) {
            holder.tvRemainingLecture.setText(studentSubjectWiseAttendanceArrayListPojo.get(position).getInout_array1().get(0).getRemaining_lect());
        }

        if (data.getPresent_lect() != null && !data.getPresent_lect().isEmpty()) {
            holder.tvPresentInLecture.setText(studentSubjectWiseAttendanceArrayListPojo.get(position).getInout_array1().get(0).getPresent_lect());
        }

        if (data.getPersentage_lect() != null && !data.getPersentage_lect().isEmpty()) {
            holder.tvTotalAttendance.setText(studentSubjectWiseAttendanceArrayListPojo.get(position).getInout_array1().get(0).getPersentage_lect());
        }


        holder.llExpandedHeaderSubjectWise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!studentSubjectWiseAttendanceArrayListPojo.get(position).isExpanded(), holder.ivViewMoreBtnSubjectWise, holder.llExpandableLayouSubjectWise);
                studentSubjectWiseAttendanceArrayListPojo.get(position).setExpanded(show);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentSubjectWiseAttendanceArrayListPojo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreBtnSubjectWise;
        LinearLayout llExpandableLayouSubjectWise;
        LinearLayout llExpandedHeaderSubjectWise;
        TextViewMediumFont tvTotalLecture;
        TextViewMediumFont tvRemainingLecture;
        TextViewMediumFont tvPresentInLecture;
        TextViewMediumFont tvTotalAttendance;
        TextViewRegularFont tvSubjectName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreBtnSubjectWise = itemView.findViewById(R.id.ivViewMoreBtnSubjectWise);
            llExpandableLayouSubjectWise = itemView.findViewById(R.id.llExpandableLayoutSubjectWise);
            llExpandedHeaderSubjectWise = itemView.findViewById(R.id.llExpandedHeaderSubjectWise);
            tvTotalLecture = itemView.findViewById(R.id.tvTotalLecture);
            tvRemainingLecture = itemView.findViewById(R.id.tvRemainingLecture);
            tvPresentInLecture = itemView.findViewById(R.id.tvPresentInLecture);
            tvTotalAttendance = itemView.findViewById(R.id.tvTotalAttendance);
            tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
        }
    }


    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand) {
        CustomAnimationForDefaultExpandableCard.toggleArrow(v, isExpanded);
        if (isExpanded) {
            CustomAnimationForDefaultExpandableCard.expand(layoutExpand);
        } else {
            CustomAnimationForDefaultExpandableCard.collapse(layoutExpand);
        }
        return isExpanded;

    }
}