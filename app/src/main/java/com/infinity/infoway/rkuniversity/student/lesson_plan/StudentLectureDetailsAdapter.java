package com.infinity.infoway.rkuniversity.student.lesson_plan;

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

public class StudentLectureDetailsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> lectureDetailsHeaderArrayList;
    private HashMap<String, ArrayList<StudentLessonPlanListPojo.LpSubTopic>> lectureDetailsChildHashMap;
    private LayoutInflater layoutInflater;

    public StudentLectureDetailsAdapter(Context context, ArrayList<String> lectureDetailsHeaderArrayList,
                                        HashMap<String, ArrayList<StudentLessonPlanListPojo.LpSubTopic>> lectureDetailsChildHashMap) {
        this.context = context;
        this.lectureDetailsHeaderArrayList = lectureDetailsHeaderArrayList;
        this.lectureDetailsChildHashMap = lectureDetailsChildHashMap;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return lectureDetailsHeaderArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return lectureDetailsChildHashMap.get(lectureDetailsHeaderArrayList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lectureDetailsHeaderArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return lectureDetailsChildHashMap.get(lectureDetailsHeaderArrayList.get(groupPosition)).get(childPosition);
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
            view = layoutInflater.inflate(R.layout.inflater_student_lecture_details_header, null);
        }

        String topicName = lectureDetailsHeaderArrayList.get(groupPosition);

        TextViewRegularFont tvLectureDetailsTopicDescription = view.findViewById(R.id.tvLectureDetailsTopicDescription);
        TextViewBoldFont tvLectureDetailsTopicsNo = view.findViewById(R.id.tvLectureDetailsTopicsNo);
        AppCompatImageView imgExpandMore = view.findViewById(R.id.imgExpandMore);

        View viewLine = view.findViewById(R.id.viewLine);

        if (isExpanded) {
            imgExpandMore.setImageResource(R.drawable.ic_expand_more);
        } else {
            imgExpandMore.setImageResource(R.drawable.ic_expand_less);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(topicName)) {
            tvLectureDetailsTopicDescription.setText(topicName);
        }

        if (groupPosition == lectureDetailsHeaderArrayList.size() - 1) {
            viewLine.setVisibility(View.GONE);
        } else {
            viewLine.setVisibility(View.VISIBLE);
        }

        tvLectureDetailsTopicsNo.setText((groupPosition + 1) + "");

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.inflater_student_lecture_details_child, null);
        }
        TextViewRegularFont tvTopicName = convertView.findViewById(R.id.tvTopicName);
        TextViewRegularFont tvTopicAid = convertView.findViewById(R.id.tvTopicAid);
        TextViewRegularFont tvTopicMethod = convertView.findViewById(R.id.tvTopicMethod);

        StudentLessonPlanListPojo.LpSubTopic lpSubTopic = lectureDetailsChildHashMap.get(lectureDetailsHeaderArrayList.get(groupPosition)).get(childPosition);

        if (!CommonUtil.checkIsEmptyOrNullCommon(lpSubTopic.getSubTopicName())) {
            tvTopicName.setText(lpSubTopic.getSubTopicName().trim() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(lpSubTopic.getSubTopicAid())) {
            tvTopicAid.setText(lpSubTopic.getSubTopicAid().trim() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(lpSubTopic.getSubTopicMethod())) {
            tvTopicMethod.setText(lpSubTopic.getSubTopicMethod().trim() + "");
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
