package com.infinity.infoway.rkuniversity.student.home_work.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewBoldFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.home_work.pojo.StudentHomeWorkPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class StudentHomeWorkListAdapter extends RecyclerView.Adapter<StudentHomeWorkListAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<StudentHomeWorkPojo.HomeworkArray> studentHomeWorkPojoArrayList;

    public StudentHomeWorkListAdapter(Context context, ArrayList<StudentHomeWorkPojo.HomeworkArray> studentHomeWorkPojoArrayList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.studentHomeWorkPojoArrayList = studentHomeWorkPojoArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.infater_student_home_work_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StudentHomeWorkPojo.HomeworkArray homeworkArray = studentHomeWorkPojoArrayList.get(position);

        if (position == 0) {
            holder.tvStart.setVisibility(View.VISIBLE);
            holder.tvEnd.setVisibility(View.GONE);
        } else if (position == studentHomeWorkPojoArrayList.size() - 1) {
            holder.tvEnd.setVisibility(View.VISIBLE);
            holder.tvStart.setVisibility(View.GONE);
        } else {
            holder.tvEnd.setVisibility(View.GONE);
            holder.tvStart.setVisibility(View.GONE);
        }

        String lectureNoForIndex = "-";

        if (homeworkArray.getLectNo().contains("-")) {
            lectureNoForIndex = homeworkArray.getLectNo().split("-")[1];
            holder.tvStudentHomeWorkLectureNoIndex.setText(lectureNoForIndex);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(homeworkArray.getSubjectName())) {
            holder.tvStudentHomeWorkSubject.setText(homeworkArray.getSubjectName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(homeworkArray.getContDeliDesc())) {
            holder.tvStudentHomeworkContentDeliver.setText(homeworkArray.getContDeliDesc() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(homeworkArray.getHomeworkDesc())) {
            holder.tvStudentHomework.setText(homeworkArray.getHomeworkDesc() + "");
        }
    }

    @Override
    public int getItemCount() {
        return studentHomeWorkPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewBoldFont tvStudentHomeWorkLectureNoIndex;

        AppCompatTextView tvStart;
        AppCompatTextView tvEnd;

        TextViewRegularFont tvStudentHomeWorkSubject;
        TextViewRegularFont tvStudentHomeworkContentDeliver;
        TextViewRegularFont tvStudentHomework;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentHomeWorkLectureNoIndex = itemView.findViewById(R.id.tvStudentHomeWorkLectureNoIndex);
            tvStudentHomeWorkSubject = itemView.findViewById(R.id.tvStudentHomeWorkSubject);
            tvStudentHomeworkContentDeliver = itemView.findViewById(R.id.tvStudentHomeworkContentDeliver);
            tvStudentHomework = itemView.findViewById(R.id.tvStudentHomework);

            tvStart = itemView.findViewById(R.id.tvStart);
            tvEnd = itemView.findViewById(R.id.tvEnd);

        }
    }

}
