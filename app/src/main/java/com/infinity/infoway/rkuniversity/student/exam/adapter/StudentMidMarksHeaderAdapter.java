package com.infinity.infoway.rkuniversity.student.exam.adapter;

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
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentMidMarksPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentMidMarksHeaderAdapter extends RecyclerView.Adapter<StudentMidMarksHeaderAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<StudentMidMarksPojo> studentMidMarksPojoArrayList;
    private LayoutInflater layoutInflater;

    public StudentMidMarksHeaderAdapter(Context context, ArrayList<StudentMidMarksPojo> studentMidMarksPojoArrayList) {
        this.context = context;
        this.studentMidMarksPojoArrayList = studentMidMarksPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_mid_marks_header, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StudentMidMarksPojo studentMidMarksPojo = studentMidMarksPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentMidMarksPojo.getMainExName())) {
            holder.tvExamName.setText(studentMidMarksPojo.getMainExName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentMidMarksPojo.getMainReDate())) {
            holder.tvExamDate.setText(studentMidMarksPojo.getMainReDate());
        }

        if (studentMidMarksPojo.getSubjectsArray() != null && studentMidMarksPojo.getSubjectsArray().size() > 0) {
            holder.rvStudentMidMarksSubjectList.setAdapter(new StudentMidMarksChildSubListAdapter(context, (ArrayList<StudentMidMarksPojo.SubjectsArray>) studentMidMarksPojo.getSubjectsArray()));
        }

        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayoutForDefaultOpenCard(!studentMidMarksPojo.isExpanded(), holder.ivViewMoreBtn, holder.llExpandableLayout);
                studentMidMarksPojo.setExpanded(show);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentMidMarksPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llExpandedHeader;
        TextViewRegularFont tvExamName, tvExamDate;
        AppCompatImageView ivViewMoreBtn;
        LinearLayout llExpandableLayout;
        RecyclerView rvStudentMidMarksSubjectList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            tvExamName = itemView.findViewById(R.id.tvExamName);
            tvExamDate = itemView.findViewById(R.id.tvExamDate);
            ivViewMoreBtn = itemView.findViewById(R.id.ivViewMoreBtn);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            rvStudentMidMarksSubjectList = itemView.findViewById(R.id.rvStudentMidMarksSubjectList);
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
