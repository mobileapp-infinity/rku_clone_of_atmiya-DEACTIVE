package com.infinity.infoway.rkuniversity.student.student_syllabus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DownloadPdfFromUrl;

import java.util.ArrayList;

public class StudentSyllabusListAdapter extends RecyclerView.Adapter<StudentSyllabusListAdapter.MyViewHolder> {

    Context context;
    ArrayList<SyllabusListPojo> syllabusListPojoArrayList;
    LayoutInflater layoutInflater;

    public StudentSyllabusListAdapter(Context context, ArrayList<SyllabusListPojo> syllabusListPojoArrayList) {
        this.context = context;
        this.syllabusListPojoArrayList = syllabusListPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_syllabus_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SyllabusListPojo syllabusListPojo = syllabusListPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(syllabusListPojo.getSubjectName())) {
            holder.tvStudentSubjectNameSyllabus.setText(syllabusListPojo.getSubjectName() + "");
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(syllabusListPojo.getSmName())) {
//            holder.tvSemStudentSyllabus.setText(syllabusListPojo.getSmName() + "");
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(syllabusListPojo.getSubCode())) {
            holder.tvSubjectCodeStudentSyllabus.setText(syllabusListPojo.getSubCode() + "");
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(syllabusListPojo.getCourseFullname())) {
//            holder.tvCourseNameStudentSyllabus.setText(syllabusListPojo.getCourseFullname() + "");
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(syllabusListPojo.getPDFURL())) {
            holder.llDownloadDocumentsStudentSyllabus.setVisibility(View.VISIBLE);
            holder.btnDownloadStudentSyllabus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String fileUrl = syllabusListPojo.getPDFURL();
                    String fileExtension = fileUrl.substring(fileUrl.lastIndexOf("."));
                    new DownloadPdfFromUrl(context, syllabusListPojo.getPDFURL().trim(), fileExtension, "Syllabus");
                }
            });
        } else {
            holder.llDownloadDocumentsStudentSyllabus.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return syllabusListPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

//        TextViewRegularFont tvSemStudentSyllabus;
        TextViewRegularFont tvSubjectCodeStudentSyllabus;
//        TextViewRegularFont tvCourseNameStudentSyllabus;
        LinearLayout llDownloadDocumentsStudentSyllabus;
        TextViewRegularFont btnDownloadStudentSyllabus;
        TextViewRegularFont tvStudentSubjectNameSyllabus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            tvSemStudentSyllabus = itemView.findViewById(R.id.tvSemStudentSyllabus);
            tvSubjectCodeStudentSyllabus = itemView.findViewById(R.id.tvSubjectCodeStudentSyllabus);
//            tvCourseNameStudentSyllabus = itemView.findViewById(R.id.tvCourseNameStudentSyllabus);
            llDownloadDocumentsStudentSyllabus = itemView.findViewById(R.id.llDownloadDocumentsStudentSyllabus);
            btnDownloadStudentSyllabus = itemView.findViewById(R.id.btnDownloadStudentSyllabus);
            tvStudentSubjectNameSyllabus = itemView.findViewById(R.id.tvStudentSubjectNameSyllabus);
        }
    }

}
