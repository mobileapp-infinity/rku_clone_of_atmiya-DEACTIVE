package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_student_forum;

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

public class FacultyStudentForumAdapter extends RecyclerView.Adapter<FacultyStudentForumAdapter.MyViewHolder> {


    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyStudentForumPojo> facultyStudentForumPojoArrayList;


    public FacultyStudentForumAdapter(Context context ,ArrayList<FacultyStudentForumPojo> facultyStudentForumPojoArrayList) {
        this.context = context;
        this.facultyStudentForumPojoArrayList = facultyStudentForumPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.infalter_faculty_student_forum_activity_items, parent, false);
        return new FacultyStudentForumAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FacultyStudentForumPojo facultyStudentForumPojo = facultyStudentForumPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyStudentForumPojo.getActivity())) {
            holder.tvActivityFaculty.setText(facultyStudentForumPojo.getActivity() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyStudentForumPojo.getNoOfPreStud())) {
            holder.tvNoOfPresentStudentFaculty.setText(facultyStudentForumPojo.getNoOfPreStud()+ "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyStudentForumPojo.getSemester())) {
            holder.tvSemesterFaculty.setText(facultyStudentForumPojo.getSemester() + "");
        }


        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyStudentForumPojo.getDivision())) {
            holder.tvDivisionFaculty.setText(facultyStudentForumPojo.getDivision() + "");
        }



        holder.llExpandedHeaderFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!facultyStudentForumPojoArrayList.get(position).isExpanded(), holder.ivViewMoreFaculty, holder.llExpandableLayoutFaculty);
                facultyStudentForumPojoArrayList.get(position).setExpanded(show);
            }
        });

    }

    @Override
    public int getItemCount() {
        return facultyStudentForumPojoArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreFaculty;
        LinearLayout llExpandableLayoutFaculty;
        LinearLayout llExpandedHeaderFaculty;

        TextViewRegularFont tvActivityFaculty;
        TextViewRegularFont tvNoOfPresentStudentFaculty;
        TextViewRegularFont tvSemesterFaculty;

        TextViewRegularFont tvDivisionFaculty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreFaculty = itemView.findViewById(R.id.ivViewMoreFaculty);
            llExpandableLayoutFaculty = itemView.findViewById(R.id.llExpandableLayoutFaculty);
            llExpandedHeaderFaculty = itemView.findViewById(R.id.llExpandedHeaderFaculty);
            tvActivityFaculty = itemView.findViewById(R.id.tvActivityFaculty);
            tvNoOfPresentStudentFaculty = itemView.findViewById(R.id.tvNoOfPresentStudentFaculty);
            tvSemesterFaculty = itemView.findViewById(R.id.tvSemesterFaculty);
            tvDivisionFaculty = itemView.findViewById(R.id.tvDivisionFaculty);

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
