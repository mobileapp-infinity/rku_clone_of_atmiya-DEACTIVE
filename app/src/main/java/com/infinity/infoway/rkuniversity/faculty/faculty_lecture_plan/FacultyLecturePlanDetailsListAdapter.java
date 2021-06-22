package com.infinity.infoway.rkuniversity.faculty.faculty_lecture_plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.appcompat.widget.AppCompatImageView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewBoldFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class FacultyLecturePlanDetailsListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> facultyLectureDetailsHeaderArrayList;
    private HashMap<String, ArrayList<FacultyLecturePlanPojo.LpSubTopic>> facultyLectureDetailsChildHashMap;
    private LayoutInflater layoutInflater;

    public FacultyLecturePlanDetailsListAdapter(Context context, ArrayList<String> facultyLectureDetailsHeaderArrayList,
                                                HashMap<String, ArrayList<FacultyLecturePlanPojo.LpSubTopic>> facultyLectureDetailsChildHashMap) {
        this.context = context;
        this.facultyLectureDetailsHeaderArrayList = facultyLectureDetailsHeaderArrayList;
        this.facultyLectureDetailsChildHashMap = facultyLectureDetailsChildHashMap;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return facultyLectureDetailsHeaderArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return facultyLectureDetailsChildHashMap.get(facultyLectureDetailsHeaderArrayList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return facultyLectureDetailsHeaderArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return facultyLectureDetailsChildHashMap.get(facultyLectureDetailsHeaderArrayList.get(groupPosition)).get(childPosition);
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
            view = layoutInflater.inflate(R.layout.inflater_faculty_lecture_plan_details_list_item_header, null);
        }

        String topicName = facultyLectureDetailsHeaderArrayList.get(groupPosition);

        TextViewBoldFont tvFacultyLectureDetailsTopicsNo = view.findViewById(R.id.tvFacultyLectureDetailsTopicsNo);
        TextViewRegularFont tvFacultyLectureDetailsTopicDescription = view.findViewById(R.id.tvFacultyLectureDetailsTopicDescription);
        AppCompatImageView imgExpandMoreFaculty = view.findViewById(R.id.imgExpandMoreFaculty);
        View viewLine = view.findViewById(R.id.viewLineFaculty);

        if (isExpanded) {
            imgExpandMoreFaculty.setImageResource(R.drawable.ic_expand_more);
        } else {
            imgExpandMoreFaculty.setImageResource(R.drawable.ic_expand_less);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(topicName)) {
            tvFacultyLectureDetailsTopicDescription.setText(topicName);
        }

        if (groupPosition == facultyLectureDetailsHeaderArrayList.size() - 1) {
            viewLine.setVisibility(View.GONE);
        } else {
            viewLine.setVisibility(View.VISIBLE);
        }

        tvFacultyLectureDetailsTopicsNo.setText((groupPosition + 1) + "");

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.inflater_faculty_lecture_details_list_item_child, null);
        }
        TextViewRegularFont tvTopicNameFacultyLecture = convertView.findViewById(R.id.tvTopicNameFacultyLecture);
        TextViewRegularFont tvTopicAidFacultyLecture = convertView.findViewById(R.id.tvTopicAidFacultyLecture);
        TextViewRegularFont tvTopicMethodFacultyLecture = convertView.findViewById(R.id.tvTopicMethodFacultyLecture);

        FacultyLecturePlanPojo.LpSubTopic lpSubTopic = facultyLectureDetailsChildHashMap.get(facultyLectureDetailsHeaderArrayList.get(groupPosition)).get(childPosition);

        if (!CommonUtil.checkIsEmptyOrNullCommon(lpSubTopic.getSubTopicName())) {
            tvTopicNameFacultyLecture.setText(lpSubTopic.getSubTopicName().trim() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(lpSubTopic.getSubTopicAid())) {
            tvTopicAidFacultyLecture.setText(lpSubTopic.getSubTopicAid().trim() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(lpSubTopic.getSubTopicMethod())) {
            tvTopicMethodFacultyLecture.setText(lpSubTopic.getSubTopicMethod().trim() + "");
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
