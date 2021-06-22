package com.infinity.infoway.rkuniversity.student.student_timetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.student_timetable.pojo.StudentTimeTablePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentTimeTableChildMergingAdapter extends RecyclerView.Adapter<StudentTimeTableChildMergingAdapter.MyViewHolder> {

    Context context;
    ArrayList<StudentTimeTablePojo.LabArray> studentTimeTableLabArrayList;
    LayoutInflater layoutInflater;

    public StudentTimeTableChildMergingAdapter(Context context, ArrayList<StudentTimeTablePojo.LabArray> studentTimeTableLabArrayList) {
        this.context = context;
        this.studentTimeTableLabArrayList = studentTimeTableLabArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_timetable_child_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentTimeTablePojo.LabArray labArray = studentTimeTableLabArrayList.get(position);

        if (position == (studentTimeTableLabArrayList.size() - 1)) {
            holder.lineStudentTimetableMergingLayout.setVisibility(View.GONE);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(labArray.getDvmName())) {
            holder.tvStudentBatchMergingLayout.setText("Batch :- " + labArray.getDvmName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(labArray.getEmpName())) {
            holder.tvStudentFacultyNameMergingLayout.setText(labArray.getEmpName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(labArray.getSubShortName())) {
            holder.tvStudentSubjectNameMergingLayout.setText(labArray.getSubShortName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(labArray.getRmName())) {
            holder.tvStudentClassRoomMergingLayout.setText(labArray.getRmName() + "");
        }

    }

    @Override
    public int getItemCount() {
        return studentTimeTableLabArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvStudentBatchMergingLayout;
        TextViewRegularFont tvStudentFacultyNameMergingLayout;
        TextViewRegularFont tvStudentSubjectNameMergingLayout;
        TextViewRegularFont tvStudentClassRoomMergingLayout;
        View lineStudentTimetableMergingLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentBatchMergingLayout = itemView.findViewById(R.id.tvStudentBatchMergingLayout);
            tvStudentFacultyNameMergingLayout = itemView.findViewById(R.id.tvStudentFacultyNameMergingLayout);
            tvStudentSubjectNameMergingLayout = itemView.findViewById(R.id.tvStudentSubjectNameMergingLayout);
            tvStudentClassRoomMergingLayout = itemView.findViewById(R.id.tvStudentClassRoomMergingLayout);
            lineStudentTimetableMergingLayout = itemView.findViewById(R.id.lineStudentTimetableMergingLayout);

        }
    }

}
