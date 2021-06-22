package com.infinity.infoway.rkuniversity.student.student_timetable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewBoldFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.student_timetable.pojo.StudentTimeTablePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentTimeTableAdapter extends RecyclerView.Adapter<StudentTimeTableAdapter.MyViewHolder> {

    Context context;
    ArrayList<StudentTimeTablePojo.InoutArray1> studentTimeTablePojoArrayList;
    LayoutInflater layoutInflater;

    public StudentTimeTableAdapter(Context context, ArrayList<StudentTimeTablePojo.InoutArray1> studentTimeTablePojoArrayList) {
        this.context = context;
        this.studentTimeTablePojoArrayList = studentTimeTablePojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_time_table_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try {
            StudentTimeTablePojo.InoutArray1 inoutArray1 = studentTimeTablePojoArrayList.get(position);

            if (position == 0) {
                holder.tvStart.setVisibility(View.VISIBLE);
                holder.tvEnd.setVisibility(View.GONE);
            } else if (position == studentTimeTablePojoArrayList.size() - 1) {
                holder.tvEnd.setVisibility(View.VISIBLE);
                holder.tvStart.setVisibility(View.GONE);
            } else {
                holder.tvEnd.setVisibility(View.GONE);
                holder.tvStart.setVisibility(View.GONE);
            }

            if (inoutArray1.getLectName().contains("RECESS")) {
                holder.tvStudentLectureNoIndex.setVisibility(View.GONE);
                holder.imgStudentBreak.setVisibility(View.VISIBLE);

                holder.llContent.setVisibility(View.GONE);
                holder.llBreakTime.setVisibility(View.VISIBLE);
                if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getLectStTime()) &&
                        !CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getLectEndTime())) {
                    holder.tvStudentBreakTime.setText("Break Time " + "(" + inoutArray1.getLectStTime() + " to " + inoutArray1.getLectEndTime() + ")");
                }
            } else {
                holder.llBreakTime.setVisibility(View.GONE);
                holder.imgStudentBreak.setVisibility(View.GONE);

                if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getLectName()) &&
                        (inoutArray1.getLabArray() == null || inoutArray1.getLabArray().size() == 0)) {
                    holder.llMergingDynamicLayout.setVisibility(View.GONE);


                    if (position > 0 && studentTimeTablePojoArrayList.get(position - 1).getLabArray() != null &&
                            studentTimeTablePojoArrayList.get(position - 1).getLabArray().size() > 0
                            && (inoutArray1.getLabArray() == null || inoutArray1.getLabArray().size() == 0)) {
                        holder.cvData.setVisibility(View.GONE);
                        holder.tvStudentLectureNoIndex.setVisibility(View.GONE);
                        holder.horizontalLine.setVisibility(View.GONE);
                    } else {
                        holder.tvStudentLectureNoIndex.setVisibility(View.VISIBLE);
                        holder.llContent.setVisibility(View.VISIBLE);

                        if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getEmpName())) {
                            holder.tvStudentFacultyName.setText(inoutArray1.getEmpName() + "");
                        } else {
                            holder.tvStudentFacultyName.setText("-");
                        }

                        String lectureNoForIndex = "-";

                        if (inoutArray1.getLectName().contains("-")) {
                            lectureNoForIndex = inoutArray1.getLectName().split("-")[1];
                        }
                        holder.tvStudentLectureNoIndex.setText(lectureNoForIndex);
                        if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getSubShortName())) {
                            holder.tvStudentSubjectName.setText(inoutArray1.getSubShortName());
                        } else {
                            holder.tvStudentSubjectName.setText("-");
                        }

                        if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getRmName())) {
                            holder.tvStudentClassRoom.setText(inoutArray1.getRmName() + "");
                        } else {
                            holder.tvStudentClassRoom.setText("-");
                        }
                    }
                } else {
                    holder.llStaticLayoutStudentTimetable.setVisibility(View.GONE);

                    String lectNo = "";
                    if (inoutArray1.getLabArray().get(0).getLectName().contains("-")) {
                        lectNo = inoutArray1.getLabArray().get(0).getLectName().split("-")[1];
                        lectNo += "/" + (Integer.parseInt(lectNo.trim()) + 1);
                    }
                    holder.tvStudentLectureNoIndex.setText(lectNo);

                    holder.rvStudentMergingDynamicLayout.setAdapter(new StudentTimeTableChildMergingAdapter(context,
                            (ArrayList<StudentTimeTablePojo.LabArray>) studentTimeTablePojoArrayList.get(position).getLabArray()));

                    holder.llMergingDynamicLayout.setVisibility(View.VISIBLE);
//                    mergingLogic(inoutArray1, position, holder);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    private void mergingLogic(StudentTimeTablePojo.InoutArray1 inoutArray1, int position,
//                              MyViewHolder holder) {
//        //merging logic
//
//        if (inoutArray1.getLabArray() != null && inoutArray1.getLabArray().size() > 0 &&
//                CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getLectName())) {
//
//            try {
//                String lectNo = "";
//                if (inoutArray1.getLabArray().get(0).getLectName().contains("-")) {
//                    lectNo = inoutArray1.getLabArray().get(0).getLectName().split("-")[1];
//                    lectNo += "/" + (Integer.parseInt(lectNo.trim()) + 1);
//                }
//                holder.tvStudentLectureNoIndex.setText(lectNo);
//
//            } catch (Exception ex) {
//
//            }
//
//            for (int i = 0; i < inoutArray1.getLabArray().size(); i++) {
//
//                LayoutInflater inflaterForMergingLayout = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View mergingView = inflaterForMergingLayout.inflate(R.layout.student_time_table_merging_layout, null);
//
//                TextViewRegularFont tvStudentBatchMergingLayout = mergingView.findViewById(R.id.tvStudentBatchMergingLayout);
//                TextViewRegularFont tvStudentFacultyNameMergingLayout = mergingView.findViewById(R.id.tvStudentFacultyNameMergingLayout);
//                TextViewRegularFont tvStudentSubjectNameMergingLayout = mergingView.findViewById(R.id.tvStudentSubjectNameMergingLayout);
//                TextViewRegularFont tvStudentClassRoomMergingLayout = mergingView.findViewById(R.id.tvStudentClassRoomMergingLayout);
//                View lineStudentTimetableMergingLayout = mergingView.findViewById(R.id.lineStudentTimetableMergingLayout);
//
//                if (i == (inoutArray1.getLabArray().size() - 1)) {
//                    lineStudentTimetableMergingLayout.setVisibility(View.GONE);
//                }
//
//                StudentTimeTablePojo.LabArray labArray = inoutArray1.getLabArray().get(i);
//
//                if (!CommonUtil.checkIsEmptyOrNullCommon(labArray.getDvmName())) {
//                    tvStudentBatchMergingLayout.setText("Batch :- " + labArray.getDvmName() + "");
//                }
//
//                if (!CommonUtil.checkIsEmptyOrNullCommon(labArray.getEmpName())) {
//                    tvStudentFacultyNameMergingLayout.setText(labArray.getEmpName() + "");
//                }
//
//                if (!CommonUtil.checkIsEmptyOrNullCommon(labArray.getSubShortName())) {
//                    tvStudentSubjectNameMergingLayout.setText(labArray.getSubShortName() + "");
//                }
//
//                if (!CommonUtil.checkIsEmptyOrNullCommon(labArray.getRmName())) {
//                    tvStudentClassRoomMergingLayout.setText(labArray.getRmName() + "");
//                }
//
//                holder.llMergingDynamicLayout.addView(mergingView);
//
//            }
//
//        }
//    }


    @Override
    public int getItemCount() {
        return studentTimeTablePojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //For Left Side
        TextViewBoldFont tvStudentLectureNoIndex;
        AppCompatImageView imgStudentBreak;
        MaterialCardView cvData;
        View horizontalLine;

        //For Right Side Lecture Content Layout
        LinearLayout llContent;
        TextViewRegularFont tvStudentFacultyName;
        TextViewRegularFont tvStudentSubjectName;
        TextViewRegularFont tvStudentClassRoom;

        //For Right Break Side layout
        LinearLayout llBreakTime;
        TextViewBoldFont tvStudentBreakTime;

        //For Circle
        AppCompatTextView tvStart;
        AppCompatTextView tvEnd;

        LinearLayout llMergingDynamicLayout;
        LinearLayout llStaticLayoutStudentTimetable;
        RecyclerView rvStudentMergingDynamicLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentLectureNoIndex = itemView.findViewById(R.id.tvStudentLectureNoIndex);
            imgStudentBreak = itemView.findViewById(R.id.imgStudentBreak);
            cvData = itemView.findViewById(R.id.cvData);
            horizontalLine = itemView.findViewById(R.id.horizontalLine);

            llContent = itemView.findViewById(R.id.llContent);
            tvStudentFacultyName = itemView.findViewById(R.id.tvStudentFacultyName);
            tvStudentSubjectName = itemView.findViewById(R.id.tvStudentSubjectName);
            tvStudentClassRoom = itemView.findViewById(R.id.tvStudentClassRoom);

            llBreakTime = itemView.findViewById(R.id.llBreakTime);
            tvStudentBreakTime = itemView.findViewById(R.id.tvStudentBreakTime);

            tvStart = itemView.findViewById(R.id.tvStart);
            tvEnd = itemView.findViewById(R.id.tvEnd);
            llMergingDynamicLayout = itemView.findViewById(R.id.llMergingDynamicLayout);
            llStaticLayoutStudentTimetable = itemView.findViewById(R.id.llStaticLayoutStudentTimetable);

            rvStudentMergingDynamicLayout = itemView.findViewById(R.id.rvStudentMergingDynamicLayout);
        }
    }

}
