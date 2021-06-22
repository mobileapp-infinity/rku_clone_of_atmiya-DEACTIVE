package com.infinity.infoway.rkuniversity.faculty.faculty_lecture_plan;

import android.content.Context;
import android.content.Intent;
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
import com.infinity.infoway.rkuniversity.utils.IntentConstants;

import java.util.ArrayList;

public class FacultyLecturePlanListAdapter extends RecyclerView.Adapter<FacultyLecturePlanListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyLecturePlanPojo> facultyLecturePlanPojoArrayList;

    public FacultyLecturePlanListAdapter(Context context, ArrayList<FacultyLecturePlanPojo> facultyLecturePlanPojoArrayList) {
        this.context = context;
        this.facultyLecturePlanPojoArrayList = facultyLecturePlanPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_lecture_plan_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyLecturePlanPojo facultyLecturePlanPojo = facultyLecturePlanPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getCourseName())) {
            holder.tvCourseNameFacultyLecturePlan.setText(facultyLecturePlanPojo.getCourseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getSemester())) {

            String semAndDiv = facultyLecturePlanPojo.getSemester() + "";

            if (semAndDiv.contains("-")) {
                semAndDiv = semAndDiv.split("-")[1].trim();
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getDivision())) {
                semAndDiv += " (" + facultyLecturePlanPojo.getDivision() + ")";
            }

            holder.tvFacultySemAndDivLecturePlan.setText(semAndDiv);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getSubject())) {
            holder.tvSubjectFacultyLecturePlan.setText(facultyLecturePlanPojo.getSubject() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getLecturePerWeek())) {
            holder.tvLectureCountPerWeekFacultyLecturePlan.setText(facultyLecturePlanPojo.getLecturePerWeek() + "");
        }


        holder.llExpandedHeaderFacultyLecturePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!facultyLecturePlanPojoArrayList.get(position).isExpanded(), holder.ivViewMoreFacultyLecturePlan, holder.llExpandableLayoutFacultyLecturePlan);
                facultyLecturePlanPojoArrayList.get(position).setExpanded(show);
            }
        });

        holder.llExpandableLayoutFacultyLecturePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FacultyLecturePlanDetailActivity.class);
                intent.putExtra(IntentConstants.FACULTY_LECTURE_DETAILS_LIST, facultyLecturePlanPojoArrayList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return facultyLecturePlanPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llExpandedHeaderFacultyLecturePlan;
        LinearLayout llExpandableLayoutFacultyLecturePlan;
        AppCompatImageView ivViewMoreFacultyLecturePlan;

        TextViewRegularFont tvCourseNameFacultyLecturePlan;
        TextViewRegularFont tvFacultySemAndDivLecturePlan;
        TextViewRegularFont tvSubjectFacultyLecturePlan;
        TextViewRegularFont tvLectureCountPerWeekFacultyLecturePlan;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            llExpandedHeaderFacultyLecturePlan = itemView.findViewById(R.id.llExpandedHeaderFacultyLecturePlan);
            llExpandableLayoutFacultyLecturePlan = itemView.findViewById(R.id.llExpandableLayoutFacultyLecturePlan);
            ivViewMoreFacultyLecturePlan = itemView.findViewById(R.id.ivViewMoreFacultyLecturePlan);
            tvCourseNameFacultyLecturePlan = itemView.findViewById(R.id.tvCourseNameFacultyLecturePlan);
            tvFacultySemAndDivLecturePlan = itemView.findViewById(R.id.tvFacultySemAndDivLecturePlan);
            tvSubjectFacultyLecturePlan = itemView.findViewById(R.id.tvSubjectFacultyLecturePlan);
            tvLectureCountPerWeekFacultyLecturePlan = itemView.findViewById(R.id.tvLectureCountPerWeekFacultyLecturePlan);

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
