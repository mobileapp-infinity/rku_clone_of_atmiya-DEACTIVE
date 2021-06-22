package com.infinity.infoway.rkuniversity.faculty.faculty_timetable.adapter;

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
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.pojo.FacultyTimeTablePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class FacultyTimeTableListAdapter extends RecyclerView.Adapter<FacultyTimeTableListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyTimeTablePojo.InoutArray1> facultyLectureDetailsArrayList;

    public FacultyTimeTableListAdapter(Context context, ArrayList<FacultyTimeTablePojo.InoutArray1> facultyLectureDetailsArrayList) {
        this.context = context;
        this.facultyLectureDetailsArrayList = facultyLectureDetailsArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_time_table_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            FacultyTimeTablePojo.InoutArray1 inoutArray1 = facultyLectureDetailsArrayList.get(position);

            if (position == 0) {
                holder.tvStart.setVisibility(View.VISIBLE);
                holder.tvEnd.setVisibility(View.GONE);
            } else if (position == facultyLectureDetailsArrayList.size() - 1) {
                holder.tvStart.setVisibility(View.GONE);
                holder.tvEnd.setVisibility(View.VISIBLE);
            } else {
                holder.tvStart.setVisibility(View.GONE);
                holder.tvEnd.setVisibility(View.GONE);
            }

            if (inoutArray1.getLectName().contains("RECESS")) {
                holder.tvFacultyLectureNoIndex.setVisibility(View.GONE);
                holder.imgFacultyBreak.setVisibility(View.VISIBLE);

                holder.llContentFaculty.setVisibility(View.GONE);
                holder.llBreakTimeFaculty.setVisibility(View.VISIBLE);
                holder.tvFacultyBreakTime.setText(" RECESS ");
//                if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getLectStTime()) &&
//                        !CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getLectEndTime())) {
//                }
            } else {
                holder.llBreakTimeFaculty.setVisibility(View.GONE);
                holder.imgFacultyBreak.setVisibility(View.GONE);

                if (inoutArray1.getInputArray() == null || inoutArray1.getInputArray().size() == 0) {
                    holder.tvFacultyLectureNoIndex.setVisibility(View.VISIBLE);
                    holder.llContentFaculty.setVisibility(View.VISIBLE);
                    holder.llFacultyMergingDynamicLayout.setVisibility(View.GONE);

                    String lectureNoForIndex = "-";

                    if (inoutArray1.getLectName().contains("-")) {
                        lectureNoForIndex = inoutArray1.getLectName().split("-")[1];
                    }

                    holder.tvFacultyLectureNoIndex.setText(lectureNoForIndex);

                    if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getSmName())) {

                        String semAndDiv = inoutArray1.getSmName();

                        if (inoutArray1.getSmName().contains("-")) {
                            semAndDiv = inoutArray1.getSmName().split("-")[1];
                        }

                        if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getDvmName())) {
                            semAndDiv += " (" + inoutArray1.getDvmName() + ") ";
                        }


                        holder.tvFacultySemesterAndDiv.setText(semAndDiv);
                    } else {
                        holder.tvFacultySemesterAndDiv.setText("-");
                    }

                    if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getSubShortName())) {
                        holder.tvFacultySubjectName.setText(inoutArray1.getSubShortName());
                    } else {
                        holder.tvFacultySubjectName.setText("-");
                    }

                    if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getLectStTime())) {
                        String lecStartAndEndTime = inoutArray1.getLectStTime().trim();

                        if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getLectEndTime())) {
                            lecStartAndEndTime += " to " + inoutArray1.getLectEndTime();
                        }
                        holder.tvFacultyLectureStartAndEnTime.setText(lecStartAndEndTime);

                    }

                    if (!CommonUtil.checkIsEmptyOrNullCommon(inoutArray1.getRmName())) {
                        holder.tvFacultyRoomNo.setText(inoutArray1.getRmName() + "");
                    } else {
                        holder.tvFacultyRoomNo.setText("-");
                    }

                } else {
                    holder.llStaticLayoutFacultyTimetable.setVisibility(View.GONE);
                    //for mergigng

                    String lectureNoForIndex = "-";

                    if (inoutArray1.getLectName().contains("-")) {
                        lectureNoForIndex = inoutArray1.getLectName().split("-")[1];
                    }

                    holder.tvFacultyLectureNoIndex.setText(lectureNoForIndex);

                    holder.rvFacultyMergingDynamicLayout.setAdapter(new FacultyTimeTableChildListAdapter(context,
                            (ArrayList<FacultyTimeTablePojo.InputArray>) facultyLectureDetailsArrayList.get(position).getInputArray()));

                    holder.llFacultyMergingDynamicLayout.setVisibility(View.VISIBLE);
//                    mergingLogic(inoutArray1, holder);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    private void mergingLogic(FacultyTimeTablePojo.InoutArray1 inoutArray1,
//                              FacultyTimeTableListAdapter.MyViewHolder holder) {
//        holder.llFacultyMergingDynamicLayout.removeAllViewsInLayout();
//        //merging logic
//        try {
//            for (int i = 0; i < inoutArray1.getInputArray().size(); i++) {
//
//                LayoutInflater inflaterForMergingLayout = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View mergingView = inflaterForMergingLayout.inflate(R.layout.faculty_time_table_merging_layout, null);
//
//                TextViewRegularFont tvSemFacultyMergingLayout = mergingView.findViewById(R.id.tvSemFacultyMergingLayout);
//                TextViewRegularFont tvFacultySubjectNameMergingLayout = mergingView.findViewById(R.id.tvFacultySubjectNameMergingLayout);
//                TextViewRegularFont tvFacultyLectureStartAndEnTimeMergingLayout = mergingView.findViewById(R.id.tvFacultyLectureStartAndEnTimeMergingLayout);
//                TextViewRegularFont tvFacultyRoomNoMergingLayout = mergingView.findViewById(R.id.tvFacultyRoomNoMergingLayout);
//                View lineStudentTimetableMergingLayout = mergingView.findViewById(R.id.lineStudentTimetableMergingLayout);
//
//                if (i == (inoutArray1.getInputArray().size() - 1)) {
//                    lineStudentTimetableMergingLayout.setVisibility(View.GONE);
//                }
//
//                FacultyTimeTablePojo.InputArray test = inoutArray1.getInputArray().get(i);
//
//                if (!CommonUtil.checkIsEmptyOrNullCommon(test.getSmName())) {
//
//                    String semAndDiv = test.getSmName();
//
//                    if (test.getSmName().contains("-")) {
//                        semAndDiv = test.getSmName().split("-")[1];
//                    }
//
//                    if (!CommonUtil.checkIsEmptyOrNullCommon(test.getDvmName())) {
//                        semAndDiv += " (" + test.getDvmName() + ") ";
//                    }
//
//
//                    tvSemFacultyMergingLayout.setText(semAndDiv);
//                } else {
//                    tvSemFacultyMergingLayout.setText("-");
//                }
//
//                if (!CommonUtil.checkIsEmptyOrNullCommon(test.getSubShortName())) {
//                    tvFacultySubjectNameMergingLayout.setText(test.getSubShortName());
//                } else {
//                    tvFacultySubjectNameMergingLayout.setText("-");
//                }
//
//                if (!CommonUtil.checkIsEmptyOrNullCommon(test.getLectStTime())) {
//                    String lecStartAndEndTime = test.getLectStTime().trim();
//
//                    if (!CommonUtil.checkIsEmptyOrNullCommon(test.getLectEndTime())) {
//                        lecStartAndEndTime += " to " + test.getLectEndTime();
//                    }
//                    tvFacultyLectureStartAndEnTimeMergingLayout.setText(lecStartAndEndTime);
//
//                }
//
//                if (!CommonUtil.checkIsEmptyOrNullCommon(test.getRmName())) {
//                    tvFacultyRoomNoMergingLayout.setText(test.getRmName() + "");
//                } else {
//                    tvFacultyRoomNoMergingLayout.setText("-");
//                }
//
//                holder.llFacultyMergingDynamicLayout.addView(mergingView);
//
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    @Override
    public int getItemCount() {
        return facultyLectureDetailsArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //For Left Side
        TextViewBoldFont tvFacultyLectureNoIndex;
        AppCompatImageView imgFacultyBreak;
        MaterialCardView cvDataFaculty;

        //For Right Side Lecture Content Layout
        LinearLayout llContentFaculty;
        TextViewRegularFont tvFacultySemesterAndDiv;
        TextViewRegularFont tvFacultySubjectName;
        TextViewRegularFont tvFacultyLectureStartAndEnTime;
        TextViewRegularFont tvFacultyRoomNo;
        LinearLayout llFacultyMergingDynamicLayout;
        LinearLayout llStaticLayoutFacultyTimetable;

        //For Right Break Side layout
        LinearLayout llBreakTimeFaculty;
        TextViewBoldFont tvFacultyBreakTime;

        //For Circle
        AppCompatTextView tvStart;
        AppCompatTextView tvEnd;
        RecyclerView rvFacultyMergingDynamicLayout;

//        private final ViewGroup container;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            container = (ViewGroup) itemView;

            rvFacultyMergingDynamicLayout = itemView.findViewById(R.id.rvFacultyMergingDynamicLayout);

            tvFacultyLectureNoIndex = itemView.findViewById(R.id.tvFacultyLectureNoIndex);
            imgFacultyBreak = itemView.findViewById(R.id.imgFacultyBreak);
            cvDataFaculty = itemView.findViewById(R.id.cvDataFaculty);

            llContentFaculty = itemView.findViewById(R.id.llContentFaculty);
            tvFacultySemesterAndDiv = itemView.findViewById(R.id.tvFacultySemesterAndDiv);
            tvFacultySubjectName = itemView.findViewById(R.id.tvFacultySubjectName);
            tvFacultyLectureStartAndEnTime = itemView.findViewById(R.id.tvFacultyLectureStartAndEnTime);
            tvFacultyRoomNo = itemView.findViewById(R.id.tvFacultyRoomNo);
            llFacultyMergingDynamicLayout = itemView.findViewById(R.id.llFacultyMergingDynamicLayout);
            llStaticLayoutFacultyTimetable = itemView.findViewById(R.id.llStaticLayoutFacultyTimetable);

            llBreakTimeFaculty = itemView.findViewById(R.id.llBreakTimeFaculty);
            tvFacultyBreakTime = itemView.findViewById(R.id.tvFacultyBreakTime);

            tvStart = itemView.findViewById(R.id.tvStart);
            tvEnd = itemView.findViewById(R.id.tvEnd);
        }
    }

}
