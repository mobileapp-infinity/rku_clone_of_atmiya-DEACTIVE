package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_adviser_remarks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class FacultyAdviserRemarksExpandableListAdapter extends BaseExpandableListAdapter {

    private FacultyAdviserRemarksActivity context;
    private ArrayList<FacultyAdviserRemarksListPojo> facultyAdviserRemarksListPojoHeaderArrayList;
    private HashMap<String, ArrayList<FacultyAdviserRemarksListPojo.FaDetailArray>> facultyAdviserDetailsListChildHashMap;
    private LayoutInflater layoutInflater;

    public FacultyAdviserRemarksExpandableListAdapter(FacultyAdviserRemarksActivity context, ArrayList<FacultyAdviserRemarksListPojo> facultyAdviserRemarksListPojoHeaderArrayList, HashMap<String, ArrayList<FacultyAdviserRemarksListPojo.FaDetailArray>> facultyAdviserDetailsListChildHashMap) {
        this.context = context;
        this.facultyAdviserRemarksListPojoHeaderArrayList = facultyAdviserRemarksListPojoHeaderArrayList;
        this.facultyAdviserDetailsListChildHashMap = facultyAdviserDetailsListChildHashMap;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return facultyAdviserRemarksListPojoHeaderArrayList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return facultyAdviserDetailsListChildHashMap.get(facultyAdviserRemarksListPojoHeaderArrayList.get(i).getFaCourseName()).size();
    }

    @Override
    public Object getGroup(int i) {
        return facultyAdviserRemarksListPojoHeaderArrayList.get(i);
    }

    @Override
    public Object getChild(int headerPosition, int childPosition) {
        return facultyAdviserDetailsListChildHashMap.get(facultyAdviserRemarksListPojoHeaderArrayList.get(headerPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int headerPosition, int expandedChildId) {
        return expandedChildId;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.faculty_adviser_remarks_header_list_item, null);
        }

        FacultyAdviserRemarksListPojo facultyAdviserRemarksListPojo = facultyAdviserRemarksListPojoHeaderArrayList.get(groupPosition);

        TextViewRegularFont tvSemNameFacultyAdViserRemarks = view.findViewById(R.id.tvSemNameFacultyAdViserRemarks);
        TextViewRegularFont tvCourseNameFacultyAdviserRemarks = view.findViewById(R.id.tvCourseNameFacultyAdviserRemarks);

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAdviserRemarksListPojo.getFaSemName())) {
            tvSemNameFacultyAdViserRemarks.setText(facultyAdviserRemarksListPojo.getFaSemName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyAdviserRemarksListPojo.getFaCourseName())) {
            tvCourseNameFacultyAdviserRemarks.setText(facultyAdviserRemarksListPojo.getFaCourseName() + "");
        }


        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.faculty_adviser_remarks_list_child_item, null);
        }

        FacultyAdviserRemarksListPojo.FaDetailArray faDetailArray = facultyAdviserDetailsListChildHashMap.get(facultyAdviserRemarksListPojoHeaderArrayList.get(groupPosition).getFaCourseName()).get(childPosition);

        TextViewRegularFont tvStudentNameFacultyAdviserDetails = view.findViewById(R.id.tvStudentNameFacultyAdviserDetails);
        TextViewRegularFont tvStudentEnrollmentNoFacultyAdviserRemarks = view.findViewById(R.id.tvStudentEnrollmentNoFacultyAdviserRemarks);
        TextViewRegularFont tvRollNoFacultyAdviserRemarks = view.findViewById(R.id.tvRollNoFacultyAdviserRemarks);
        TextViewRegularFont tvStudentMobileNo = view.findViewById(R.id.tvStudentMobileNo);
        TextViewRegularFont tvStudentAttendancePr = view.findViewById(R.id.tvStudentAttendancePr);

        if (!CommonUtil.checkIsEmptyOrNullCommon(faDetailArray.getFaStudName())) {
            tvStudentNameFacultyAdviserDetails.setText(faDetailArray.getFaStudName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(faDetailArray.getFaStudEnrollno())) {
            tvStudentEnrollmentNoFacultyAdviserRemarks.setText(faDetailArray.getFaStudEnrollno() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(faDetailArray.getFaStudRollno())) {
            tvRollNoFacultyAdviserRemarks.setText(faDetailArray.getFaStudRollno() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(faDetailArray.getFaStudFatherMobile())) {
            tvStudentMobileNo.setText(faDetailArray.getFaStudFatherMobile() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(faDetailArray.getFaStudAttPerc())) {
            tvStudentAttendancePr.setText(faDetailArray.getFaStudAttPerc() + "");
        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
