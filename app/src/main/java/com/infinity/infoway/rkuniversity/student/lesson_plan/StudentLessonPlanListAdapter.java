package com.infinity.infoway.rkuniversity.student.lesson_plan;

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

public class StudentLessonPlanListAdapter extends RecyclerView.Adapter<StudentLessonPlanListAdapter.MyViewHolder> {

    Context context;
    ArrayList<StudentLessonPlanListPojo> studentLessonPlanListPojoArrayList;
    LayoutInflater layoutInflater;

    public StudentLessonPlanListAdapter(Context context, ArrayList<StudentLessonPlanListPojo> studentLessonPlanListPojoArrayList) {
        this.context = context;
        this.studentLessonPlanListPojoArrayList = studentLessonPlanListPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_lesson_plan_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StudentLessonPlanListPojo studentLessonPlanListPojo = studentLessonPlanListPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getCourseName())) {
            holder.tvCourseNameLessonPlan.setText(studentLessonPlanListPojo.getCourseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getSemester())) {
            String semAndDiv = studentLessonPlanListPojo.getSemester() + "";

            if (semAndDiv.contains("-")) {
                semAndDiv = semAndDiv.split("-")[1].trim();
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getDivision())) {
                semAndDiv += " (" + studentLessonPlanListPojo.getDivision() + ")";
            }
            holder.tvStudentSemLessonPlan.setText(semAndDiv);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getLecturePerWeek())) {
            holder.tvLectureCountPerWeekLessonPlan.setText(studentLessonPlanListPojo.getLecturePerWeek() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getSubject())) {
            holder.tvSubjectNameLessonPlan.setText(studentLessonPlanListPojo.getSubject() + "");
        }


        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!studentLessonPlanListPojoArrayList.get(position).isExpanded(), holder.ivViewMoreLessonPlan, holder.llExpandableLayout);
                studentLessonPlanListPojoArrayList.get(position).setExpanded(show);
            }
        });

        holder.llExpandableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentLessonPlanDetailActivity.class);
                intent.putExtra(IntentConstants.STUDENT_LECTURE_DETAILS_LIST, studentLessonPlanListPojoArrayList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentLessonPlanListPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvCourseNameLessonPlan;
        AppCompatImageView ivViewMoreLessonPlan;
        LinearLayout llExpandableLayout;
        TextViewRegularFont tvStudentSemLessonPlan;
        TextViewRegularFont tvLectureCountPerWeekLessonPlan;
        TextViewRegularFont tvSubjectNameLessonPlan;
        LinearLayout llExpandedHeader;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseNameLessonPlan = itemView.findViewById(R.id.tvCourseNameLessonPlan);
            ivViewMoreLessonPlan = itemView.findViewById(R.id.ivViewMoreLessonPlan);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            tvStudentSemLessonPlan = itemView.findViewById(R.id.tvStudentSemLessonPlan);
            tvLectureCountPerWeekLessonPlan = itemView.findViewById(R.id.tvLectureCountPerWeekLessonPlan);
            tvSubjectNameLessonPlan = itemView.findViewById(R.id.tvSubjectNameLessonPlan);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
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
