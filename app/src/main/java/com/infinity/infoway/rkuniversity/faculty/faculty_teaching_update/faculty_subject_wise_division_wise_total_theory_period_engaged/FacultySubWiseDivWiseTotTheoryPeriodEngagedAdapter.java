package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_subject_wise_division_wise_total_theory_period_engaged;

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

public class FacultySubWiseDivWiseTotTheoryPeriodEngagedAdapter extends RecyclerView.Adapter<FacultySubWiseDivWiseTotTheoryPeriodEngagedAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultySubAndDivWiseTotTheoryPeriodEngagedPojo> facultySubAndDivWiseTotTheoryPeriodEngagedPojoArrayList;

    public FacultySubWiseDivWiseTotTheoryPeriodEngagedAdapter(Context context, ArrayList<FacultySubAndDivWiseTotTheoryPeriodEngagedPojo> facultySubAndDivWiseTotTheoryPeriodEngagedPojoArrayList) {
        this.context = context;
        this.facultySubAndDivWiseTotTheoryPeriodEngagedPojoArrayList = facultySubAndDivWiseTotTheoryPeriodEngagedPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_subject_wise_division_wise_total_theory_period_engaged_items, parent, false);
        return new FacultySubWiseDivWiseTotTheoryPeriodEngagedAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FacultySubAndDivWiseTotTheoryPeriodEngagedPojo facultySubAndDivWiseTotTheoryPeriodEngagedPojo = facultySubAndDivWiseTotTheoryPeriodEngagedPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getSubName())) {
            holder.tvSubjectNameFaculty.setText(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getSubName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getYearName())) {
            holder.tvAcademicYearFaculty.setText(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getYearName()+ "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getSemName())) {
            holder.tvSemesterFaculty.setText(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getSemName()+ "");
        }


        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getDivName())) {
            holder.tvDivisionFaculty.setText(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getDivName()+ "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getLecPerWeek())) {
            holder.tvTotalLectureEngagedFaculty.setText(facultySubAndDivWiseTotTheoryPeriodEngagedPojo.getLecPerWeek() + "");
        }
        holder.llExpandedHeaderFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!facultySubAndDivWiseTotTheoryPeriodEngagedPojoArrayList.get(position).isExpanded(), holder.ivViewMoreFaculty, holder.llExpandableLayoutFaculty);
                facultySubAndDivWiseTotTheoryPeriodEngagedPojoArrayList.get(position).setExpanded(show);
            }
        });

    }

    @Override
    public int getItemCount() {
        return facultySubAndDivWiseTotTheoryPeriodEngagedPojoArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivViewMoreFaculty;
        LinearLayout llExpandableLayoutFaculty;
        LinearLayout llExpandedHeaderFaculty;

        TextViewRegularFont tvSubjectNameFaculty;
        TextViewRegularFont tvAcademicYearFaculty;
        TextViewRegularFont tvSemesterFaculty;

        TextViewRegularFont tvDivisionFaculty;
        TextViewRegularFont tvTotalLectureEngagedFaculty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreFaculty = itemView.findViewById(R.id.ivViewMoreFaculty);
            llExpandableLayoutFaculty = itemView.findViewById(R.id.llExpandableLayoutFaculty);
            llExpandedHeaderFaculty = itemView.findViewById(R.id.llExpandedHeaderFaculty);
            tvSubjectNameFaculty = itemView.findViewById(R.id.tvSubjectNameFaculty);
            tvAcademicYearFaculty = itemView.findViewById(R.id.tvAcademicYearFaculty);
            tvSemesterFaculty = itemView.findViewById(R.id.tvSemesterFaculty);
            tvDivisionFaculty = itemView.findViewById(R.id.tvDivisionFaculty);
            tvTotalLectureEngagedFaculty = itemView.findViewById(R.id.tvTotalLectureEngagedFaculty);
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
