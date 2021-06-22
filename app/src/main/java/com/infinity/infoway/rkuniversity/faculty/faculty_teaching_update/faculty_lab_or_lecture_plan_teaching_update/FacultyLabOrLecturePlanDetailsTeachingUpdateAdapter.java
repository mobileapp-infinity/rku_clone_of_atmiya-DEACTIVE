package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_lab_or_lecture_plan_teaching_update;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewBoldFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class FacultyLabOrLecturePlanDetailsTeachingUpdateAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> facultyLabOrLectureDetailsHeaderArrayList;
    private HashMap<String, ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo.LabOrLectureDetails>> facultyLabOrLecturePlanDetailsTeachingUpdateHashMap;
    private LayoutInflater layoutInflater;

    public FacultyLabOrLecturePlanDetailsTeachingUpdateAdapter(Context context, ArrayList<String> facultyLabOrLectureDetailsHeaderArrayList, HashMap<String, ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo.LabOrLectureDetails>> facultyLabOrLecturePlanDetailsTeachingUpdateHashMap) {
        this.context = context;
        this.facultyLabOrLectureDetailsHeaderArrayList = facultyLabOrLectureDetailsHeaderArrayList;
        this.facultyLabOrLecturePlanDetailsTeachingUpdateHashMap = facultyLabOrLecturePlanDetailsTeachingUpdateHashMap;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getGroupCount() {
        return facultyLabOrLectureDetailsHeaderArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return facultyLabOrLecturePlanDetailsTeachingUpdateHashMap.get(facultyLabOrLectureDetailsHeaderArrayList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return facultyLabOrLectureDetailsHeaderArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return facultyLabOrLecturePlanDetailsTeachingUpdateHashMap.get(facultyLabOrLectureDetailsHeaderArrayList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.inflater_faculty_lab_or_lecture_plan_details_teaching_update_header, null);
        }

        String topicName = facultyLabOrLectureDetailsHeaderArrayList.get(groupPosition);

        TextViewBoldFont tvFacultyLabOrLectureDerailsIndex = view.findViewById(R.id.tvFacultyLabOrLectureDerailsIndex);
        TextViewRegularFont tvFacultyLabOrLectureDerailsTopicDescription = view.findViewById(R.id.tvFacultyLabOrLectureDerailsTopicDescription);

        View viewLineFacultyLabOrLectureDerails = view.findViewById(R.id.viewLineFacultyLabOrLectureDerails);

        if (!CommonUtil.checkIsEmptyOrNullCommon(topicName)) {
            tvFacultyLabOrLectureDerailsTopicDescription.setText(topicName);
        }

        if (groupPosition == facultyLabOrLectureDetailsHeaderArrayList.size() - 1) {
            viewLineFacultyLabOrLectureDerails.setVisibility(View.GONE);
        } else {
            viewLineFacultyLabOrLectureDerails.setVisibility(View.VISIBLE);
        }

        tvFacultyLabOrLectureDerailsIndex.setText((groupPosition + 1) + "");

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.inflater_faculty_lab_or_lecture_details_teaching_update_child_list_item, null);
        }
        TextViewRegularFont tvTopicNameFacultyDetailsTeachingUpdate = convertView.findViewById(R.id.tvTopicNameFacultyDetailsTeachingUpdate);
        TextViewRegularFont tvTopicAidFacultyDetailsTeachingUpdate = convertView.findViewById(R.id.tvTopicAidFacultyDetailsTeachingUpdate);
        TextViewRegularFont tvTopicMethodFacultyDetailsTeachingUpdate = convertView.findViewById(R.id.tvTopicMethodFacultyDetailsTeachingUpdate);

        FacultyLabOrLecturePlanTeachingUpdatePojo.LabOrLectureDetails labOrLectureDetails = facultyLabOrLecturePlanDetailsTeachingUpdateHashMap.get(facultyLabOrLectureDetailsHeaderArrayList.get(groupPosition)).get(childPosition);

        if (!CommonUtil.checkIsEmptyOrNullCommon(labOrLectureDetails.getLpAid())) {
            tvTopicNameFacultyDetailsTeachingUpdate.setText(labOrLectureDetails.getLpAid().trim() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(labOrLectureDetails.getLpMethod())) {
            tvTopicAidFacultyDetailsTeachingUpdate.setText(labOrLectureDetails.getLpMethod().trim() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(labOrLectureDetails.getLpCo())) {
            tvTopicMethodFacultyDetailsTeachingUpdate.setText(labOrLectureDetails.getLpCo().trim() + "");
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
