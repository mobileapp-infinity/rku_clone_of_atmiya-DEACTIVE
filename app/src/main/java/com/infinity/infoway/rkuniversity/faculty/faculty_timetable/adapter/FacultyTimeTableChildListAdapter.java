package com.infinity.infoway.rkuniversity.faculty.faculty_timetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.pojo.FacultyTimeTablePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FacultyTimeTableChildListAdapter extends RecyclerView.Adapter<FacultyTimeTableChildListAdapter.MyViewHolder> {

    Context context;
    ArrayList<FacultyTimeTablePojo.InputArray> inputArrayArrayList;
    LayoutInflater layoutInflater;

    public FacultyTimeTableChildListAdapter(Context context, ArrayList<FacultyTimeTablePojo.InputArray> inputArrayArrayList) {
        this.context = context;
        this.inputArrayArrayList = inputArrayArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.faculty_time_table_merging_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (position == inputArrayArrayList.size() - 1) {
            holder.lineStudentTimetableMergingLayout.setVisibility(View.GONE);
        } else {
            holder.lineStudentTimetableMergingLayout.setVisibility(View.VISIBLE);
        }

        FacultyTimeTablePojo.InputArray test = inputArrayArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(test.getSmName())) {

            String semAndDiv = test.getSmName();

            if (test.getSmName().contains("-")) {
                semAndDiv = test.getSmName().split("-")[1];
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(test.getDvmName())) {
                semAndDiv += " (" + test.getDvmName() + ") ";
            }


            holder.tvSemFacultyMergingLayout.setText(semAndDiv);
        } else {
            holder.tvSemFacultyMergingLayout.setText("-");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(test.getSubShortName())) {
            holder.tvFacultySubjectNameMergingLayout.setText(test.getSubShortName());
        } else {
            holder.tvFacultySubjectNameMergingLayout.setText("-");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(test.getLectStTime())) {
            String lecStartAndEndTime = test.getLectStTime().trim();

            if (!CommonUtil.checkIsEmptyOrNullCommon(test.getLectEndTime())) {
                lecStartAndEndTime += " to " + test.getLectEndTime();
            }
            holder.tvFacultyLectureStartAndEnTimeMergingLayout.setText(lecStartAndEndTime);

        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(test.getRmName())) {
            holder.tvFacultyRoomNoMergingLayout.setText(test.getRmName() + "");
        } else {
            holder.tvFacultyRoomNoMergingLayout.setText("-");
        }

    }

    @Override
    public int getItemCount() {
        return inputArrayArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvSemFacultyMergingLayout;
        TextViewRegularFont tvFacultySubjectNameMergingLayout;
        TextViewRegularFont tvFacultyLectureStartAndEnTimeMergingLayout;
        TextViewRegularFont tvFacultyRoomNoMergingLayout;
        View lineStudentTimetableMergingLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lineStudentTimetableMergingLayout = itemView.findViewById(R.id.lineStudentTimetableMergingLayout);
            tvSemFacultyMergingLayout = itemView.findViewById(R.id.tvSemFacultyMergingLayout);
            tvFacultySubjectNameMergingLayout = itemView.findViewById(R.id.tvFacultySubjectNameMergingLayout);
            tvFacultyLectureStartAndEnTimeMergingLayout = itemView.findViewById(R.id.tvFacultyLectureStartAndEnTimeMergingLayout);
            tvFacultyRoomNoMergingLayout = itemView.findViewById(R.id.tvFacultyRoomNoMergingLayout);
        }
    }

}
