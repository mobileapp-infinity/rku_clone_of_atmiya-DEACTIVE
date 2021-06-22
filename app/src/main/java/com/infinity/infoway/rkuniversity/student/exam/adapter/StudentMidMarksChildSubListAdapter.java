package com.infinity.infoway.rkuniversity.student.exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentMidMarksPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentMidMarksChildSubListAdapter extends RecyclerView.Adapter<StudentMidMarksChildSubListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<StudentMidMarksPojo.SubjectsArray> subjectsArrayArrayList;
    private LayoutInflater layoutInflater;

    public StudentMidMarksChildSubListAdapter(Context context, ArrayList<StudentMidMarksPojo.SubjectsArray> subjectsArrayArrayList) {
        this.context = context;
        this.subjectsArrayArrayList = subjectsArrayArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_mid_marks_subject_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentMidMarksPojo.SubjectsArray subjectsArray = subjectsArrayArrayList.get(position);


        if (!CommonUtil.checkIsEmptyOrNullCommon(subjectsArray.getSubName())) {
            holder.tvSubjectName.setText(subjectsArray.getSubName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(subjectsArray.getSubTotMark())) {
            holder.tvTotalMarks.setText(subjectsArray.getSubTotMark() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(subjectsArray.getSubObtMark())) {
            holder.tvObtainMarks.setText(subjectsArray.getSubObtMark() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(subjectsArray.getSubWeight())) {
            holder.tvSubWeightage.setText(subjectsArray.getSubWeight() + "");
        }

        if (position == subjectsArrayArrayList.size() - 1) {
            holder.viewLine.setVisibility(View.GONE);
        } else {
            holder.viewLine.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return subjectsArrayArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View viewLine;
        TextViewRegularFont tvSubjectName, tvTotalMarks, tvObtainMarks, tvSubWeightage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewLine = itemView.findViewById(R.id.viewLine);
            tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
            tvTotalMarks = itemView.findViewById(R.id.tvTotalMarks);
            tvObtainMarks = itemView.findViewById(R.id.tvObtainMarks);
            tvSubWeightage = itemView.findViewById(R.id.tvSubWeightage);
        }
    }
}
