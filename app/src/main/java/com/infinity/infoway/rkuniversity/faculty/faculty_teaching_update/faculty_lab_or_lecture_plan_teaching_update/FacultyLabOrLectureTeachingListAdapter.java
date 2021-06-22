package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_lab_or_lecture_plan_teaching_update;

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

public class FacultyLabOrLectureTeachingListAdapter extends RecyclerView.Adapter<FacultyLabOrLectureTeachingListAdapter.MyViewHolder> {

    Context context;
    ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo> facultyLabOrLecturePlanTeachingUpdatePojoArrayList;
    LayoutInflater layoutInflater;

    public FacultyLabOrLectureTeachingListAdapter(Context context, ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo> facultyLabOrLecturePlanTeachingUpdatePojoArrayList) {
        this.context = context;
        this.facultyLabOrLecturePlanTeachingUpdatePojoArrayList = facultyLabOrLecturePlanTeachingUpdatePojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_lab_or_lecture_teaching_update_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyLabOrLecturePlanTeachingUpdatePojo facultyLabOrLecturePlanTeachingUpdatePojo = facultyLabOrLecturePlanTeachingUpdatePojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpCourse())) {
            holder.tvCourseNameLabOrLecturePlan.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getLpCourse() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpSem())) {
            String semAndDiv = facultyLabOrLecturePlanTeachingUpdatePojo.getLpSem() + "";

            if (semAndDiv.contains("-")) {
                semAndDiv = semAndDiv.split("-")[1].trim();
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpDiv())) {
                semAndDiv += " (" + facultyLabOrLecturePlanTeachingUpdatePojo.getLpDiv() + ")";
            }
            holder.tvStudentSemLabOrLecturePlan.setText(semAndDiv);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpLabPerWeek())) {
            holder.tvLectureCountPerWeekLabOrLecturePlan.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getLpLabPerWeek() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpSub())) {
            holder.tvSubjectNameLabOrLecturePlan.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getLpSub() + "");
        }


        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!facultyLabOrLecturePlanTeachingUpdatePojo.isExpanded(), holder.ivViewMoreLabOrLecturePlan, holder.llExpandableLayout);
                facultyLabOrLecturePlanTeachingUpdatePojo.setExpanded(show);
            }
        });

        holder.llExpandableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FacultyLabOrLecturePlanTeachingUpdateDetailsActivity.class);
                intent.putExtra(IntentConstants.FACULTY_LAB_OR_LECTURE_DETAILS_TEACHING_UPDATE, facultyLabOrLecturePlanTeachingUpdatePojoArrayList);
                intent.putExtra(IntentConstants.FACULTY_LAB_OR_LECTURE_DETAILS_TEACHING_UPDATE_POSITION, position + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return facultyLabOrLecturePlanTeachingUpdatePojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreLabOrLecturePlan;
        LinearLayout llExpandableLayout;
        LinearLayout llExpandedHeader;
        TextViewRegularFont tvCourseNameLabOrLecturePlan;

        TextViewRegularFont tvStudentSemLabOrLecturePlan;
        TextViewRegularFont tvSubjectNameLabOrLecturePlan;
        TextViewRegularFont tvLectureCountPerWeekLabOrLecturePlan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseNameLabOrLecturePlan = itemView.findViewById(R.id.tvCourseNameLabOrLecturePlan);
            ivViewMoreLabOrLecturePlan = itemView.findViewById(R.id.ivViewMoreLabOrLecturePlan);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);

            tvStudentSemLabOrLecturePlan = itemView.findViewById(R.id.tvStudentSemLabOrLecturePlan);
            tvSubjectNameLabOrLecturePlan = itemView.findViewById(R.id.tvSubjectNameLabOrLecturePlan);
            tvLectureCountPerWeekLabOrLecturePlan = itemView.findViewById(R.id.tvLectureCountPerWeekLabOrLecturePlan);
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
