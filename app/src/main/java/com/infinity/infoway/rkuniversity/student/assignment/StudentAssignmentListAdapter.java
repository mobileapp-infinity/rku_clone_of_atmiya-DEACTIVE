package com.infinity.infoway.rkuniversity.student.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DownloadPdfFromUrl;

import java.util.ArrayList;

public class StudentAssignmentListAdapter extends RecyclerView.Adapter<StudentAssignmentListAdapter.MyViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<StudentAssignmentListPojo> studentAssignmentListPojoArrayList;

    public StudentAssignmentListAdapter(Context context, ArrayList<StudentAssignmentListPojo> studentAssignmentListPojoArrayList) {
        this.context = context;
        this.studentAssignmentListPojoArrayList = studentAssignmentListPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_assignment_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StudentAssignmentListPojo studentAssignmentListPojo = studentAssignmentListPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentAssignmentListPojo.getSubFullname())) {
            holder.tvAssignmentSubjectName.setText(studentAssignmentListPojo.getSubFullname() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentAssignmentListPojo.getAmName())) {
            holder.tvAssignmentName.setText(studentAssignmentListPojo.getAmName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentAssignmentListPojo.getAmLastSeenDate())) {
            holder.tvAssignmentSubjectDate.setText(studentAssignmentListPojo.getAmLastSeenDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentAssignmentListPojo.getPDFURL()) &&
                studentAssignmentListPojo.getPDFURL().endsWith(".pdf")) {
            holder.llDownloadStudentAssignments.setVisibility(View.VISIBLE);
            holder.tvDownloadAssignment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fileUrl = studentAssignmentListPojo.getPDFURL();
                    String fileExtension = fileUrl.substring(fileUrl.lastIndexOf("."));
                    new DownloadPdfFromUrl(context, studentAssignmentListPojo.getPDFURL(), fileExtension, "Assignments");
                }

            });
        } else {
            holder.llDownloadStudentAssignments.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return studentAssignmentListPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewMediumFont tvAssignmentSubjectName, tvAssignmentSubjectDate, tvAssignmentName;
        TextViewRegularFont tvDownloadAssignment;
        LinearLayout llDownloadStudentAssignments;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAssignmentSubjectName = itemView.findViewById(R.id.tvAssignmentSubjectName);
            tvAssignmentSubjectDate = itemView.findViewById(R.id.tvAssignmentSubjectDate);
            tvAssignmentName = itemView.findViewById(R.id.tvAssignmentName);
            tvDownloadAssignment = itemView.findViewById(R.id.tvDownloadAssignment);
            llDownloadStudentAssignments = itemView.findViewById(R.id.llDownloadStudentAssignments);
        }
    }
}
