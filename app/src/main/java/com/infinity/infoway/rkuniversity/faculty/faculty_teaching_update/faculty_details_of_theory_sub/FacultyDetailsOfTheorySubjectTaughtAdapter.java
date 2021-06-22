package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_details_of_theory_sub;

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
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class FacultyDetailsOfTheorySubjectTaughtAdapter extends RecyclerView.Adapter<FacultyDetailsOfTheorySubjectTaughtAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo> facultyDetailsOfTheorySubjectTaughtPojoArrayList;

    public FacultyDetailsOfTheorySubjectTaughtAdapter(Context context,  ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo> facultyDetailsOfTheorySubjectTaughtPojoArrayList) {
        this.context = context;
        this.facultyDetailsOfTheorySubjectTaughtPojoArrayList = facultyDetailsOfTheorySubjectTaughtPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_details_of_theory_subjects_taught_items, parent, false);
        return new FacultyDetailsOfTheorySubjectTaughtAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyDetailsOfTheorySubjectTaughtPojo facultyDetailsOfTheorySubjectTaughtPojo = facultyDetailsOfTheorySubjectTaughtPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyDetailsOfTheorySubjectTaughtPojo.getCourseName())) {
            holder.tvCourseNameFaculty.setText(facultyDetailsOfTheorySubjectTaughtPojo.getCourseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyDetailsOfTheorySubjectTaughtPojo.getCollegeName())) {
            holder.tvCollegeNameFaculty.setText(facultyDetailsOfTheorySubjectTaughtPojo.getCollegeName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyDetailsOfTheorySubjectTaughtPojo.getSemName())) {
            holder.tvSemesterFaculty.setText(facultyDetailsOfTheorySubjectTaughtPojo.getSemName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyDetailsOfTheorySubjectTaughtPojo.getSubName())) {
            holder.tvSubjectFaculty.setText(facultyDetailsOfTheorySubjectTaughtPojo.getSubName()+ "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyDetailsOfTheorySubjectTaughtPojo.getDivName())) {
            holder.tvDivisionFaculty.setText(facultyDetailsOfTheorySubjectTaughtPojo.getDivName()+"");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyDetailsOfTheorySubjectTaughtPojo.getResourceName())) {
            holder.tvResourceFaculty.setText(facultyDetailsOfTheorySubjectTaughtPojo.getResourceName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyDetailsOfTheorySubjectTaughtPojo.getDeptName())) {
            holder.tvDepartmentNameFaculty.setText(facultyDetailsOfTheorySubjectTaughtPojo.getDeptName() + "");
        }
        holder.llExpandedHeaderFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!facultyDetailsOfTheorySubjectTaughtPojoArrayList.get(position).isExpanded(), holder.ivViewMoreFaculty, holder.llExpandableLayoutFaculty);
                facultyDetailsOfTheorySubjectTaughtPojoArrayList.get(position).setExpanded(show);
            }
        });

    }

    @Override
    public int getItemCount() {
        return facultyDetailsOfTheorySubjectTaughtPojoArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreFaculty;
        LinearLayout llExpandableLayoutFaculty;
        LinearLayout llExpandedHeaderFaculty;

        TextViewRegularFont tvCourseNameFaculty;
        TextViewRegularFont tvCollegeNameFaculty;
        TextViewRegularFont tvSemesterFaculty;
        TextViewRegularFont tvSubjectFaculty;
        TextViewRegularFont tvDivisionFaculty;
        TextViewRegularFont tvResourceFaculty;
        TextViewRegularFont tvDepartmentNameFaculty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivViewMoreFaculty = itemView.findViewById(R.id.ivViewMoreFaculty);
            llExpandableLayoutFaculty = itemView.findViewById(R.id.llExpandableLayoutFaculty);
            llExpandedHeaderFaculty = itemView.findViewById(R.id.llExpandedHeaderFaculty);
            tvCourseNameFaculty = itemView.findViewById(R.id.tvCourseNameFaculty);
            tvCollegeNameFaculty = itemView.findViewById(R.id.tvCollegeNameFaculty);
            tvSemesterFaculty = itemView.findViewById(R.id.tvSemesterFaculty);
            tvSubjectFaculty = itemView.findViewById(R.id.tvSubjectFaculty);
            tvDivisionFaculty = itemView.findViewById(R.id.tvDivisionFaculty);
            tvResourceFaculty = itemView.findViewById(R.id.tvResourceFaculty);
            tvDepartmentNameFaculty = itemView.findViewById(R.id.tvDepartmentNameFaculty);
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
