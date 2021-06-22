package com.infinity.infoway.rkuniversity.student.student_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentActivityListAdapter extends RecyclerView.Adapter<StudentActivityListAdapter.MyViewHolder> {

    Context context;
    ArrayList<StudentActivityPojo> studentActivityPojoArrayList;
    LayoutInflater layoutInflater;

    public StudentActivityListAdapter(Context context, ArrayList<StudentActivityPojo> studentActivityPojoArrayList) {
        this.context = context;
        this.studentActivityPojoArrayList = studentActivityPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_activity_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentActivityPojo studentActivityPojo = studentActivityPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentActivityPojo.getActivityDate())) {
            holder.tvActivityDate.setText(studentActivityPojo.getActivityDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentActivityPojo.getDaDescription())) {
            holder.tvActivityDescription.setText(studentActivityPojo.getDaDescription() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentActivityPojo.getActivityFile()) && studentActivityPojoArrayList.size() > 0) {
            holder.rvStudentActivityImageList.setVisibility(View.VISIBLE);
            holder.rvStudentActivityImageList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.rvStudentActivityImageList.setAdapter(new StudentActivityImageListAdapter(context, (ArrayList<StudentActivityPojo.ActivityFile>) studentActivityPojoArrayList.get(position).getActivityFile()));
        } else {
            holder.rvStudentActivityImageList.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return studentActivityPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvActivityDate, tvActivityDescription;
        RecyclerView rvStudentActivityImageList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvActivityDate = itemView.findViewById(R.id.tvActivityDate);
            tvActivityDescription = itemView.findViewById(R.id.tvActivityDescription);
            rvStudentActivityImageList = itemView.findViewById(R.id.rvStudentActivityImageList);
        }
    }

}
