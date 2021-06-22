package com.infinity.infoway.rkuniversity.faculty.faculty_rem_attendance;

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

public class FacultyRemAttendanceListAdapter extends RecyclerView.Adapter<FacultyRemAttendanceListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyRemAttendancePojo> facultyRemAttendancePojoArrayList;

    public FacultyRemAttendanceListAdapter(Context context, ArrayList<FacultyRemAttendancePojo> facultyRemAttendancePojoArrayList) {
        this.context = context;
        this.facultyRemAttendancePojoArrayList = facultyRemAttendancePojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FacultyRemAttendanceListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_rem_attendance_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyRemAttendanceListAdapter.MyViewHolder holder, int position) {
        FacultyRemAttendancePojo facultyRemAttendancePojo = facultyRemAttendancePojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyRemAttendancePojo.getCourse())) {
            holder.tvCourseNameRemAttendace.setText(facultyRemAttendancePojo.getCourse());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyRemAttendancePojo.getSemname())) {
            String sem = facultyRemAttendancePojo.getSemname();
            if (facultyRemAttendancePojo.getSemname().contains("-")) {
                sem = sem.split("-")[1].trim();
            }
            holder.tvSemesterFacultyRemAttendance.setText(sem + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyRemAttendancePojo.getDate())) {
            holder.tvRemAttendanceFacultyDate.setText(facultyRemAttendancePojo.getDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyRemAttendancePojo.getLecno())) {
            holder.tvLectureNoFacultyremAttendance.setText(facultyRemAttendancePojo.getLecno() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyRemAttendancePojo.getDivision())) {
            holder.tvRemAttendanceFacultyDivision.setText(facultyRemAttendancePojo.getDivision() + "");
        }

        holder.llExpandedHeaderFacultyRemAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!facultyRemAttendancePojoArrayList.get(position).isExpanded(), holder.ivViewMoreFacultyRemAttendance, holder.llExpandableLayoutFacultyRemAttendance);
                facultyRemAttendancePojoArrayList.get(position).setExpanded(show);
            }
        });

    }

    @Override
    public int getItemCount() {
        return facultyRemAttendancePojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreFacultyRemAttendance;
        LinearLayout llExpandableLayoutFacultyRemAttendance;
        LinearLayout llExpandedHeaderFacultyRemAttendance;
        TextViewRegularFont tvCourseNameRemAttendace;
        TextViewRegularFont tvSemesterFacultyRemAttendance;
        TextViewRegularFont tvRemAttendanceFacultyDivision;
        TextViewRegularFont tvLectureNoFacultyremAttendance;
        TextViewRegularFont tvRemAttendanceFacultyDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreFacultyRemAttendance = itemView.findViewById(R.id.ivViewMoreFacultyRemAttendance);
            llExpandableLayoutFacultyRemAttendance = itemView.findViewById(R.id.llExpandableLayoutFacultyRemAttendance);
            llExpandedHeaderFacultyRemAttendance = itemView.findViewById(R.id.llExpandedHeaderFacultyRemAttendance);
            tvCourseNameRemAttendace = itemView.findViewById(R.id.tvCourseNameRemAttendace);
            tvSemesterFacultyRemAttendance = itemView.findViewById(R.id.tvSemesterFacultyRemAttendance);
            tvRemAttendanceFacultyDivision = itemView.findViewById(R.id.tvRemAttendanceFacultyDivision);
            tvLectureNoFacultyremAttendance = itemView.findViewById(R.id.tvLectureNoFacultyremAttendance);
            tvRemAttendanceFacultyDate = itemView.findViewById(R.id.tvRemAttendanceFacultyDate);
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
