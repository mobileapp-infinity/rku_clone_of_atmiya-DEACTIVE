package com.infinity.infoway.rkuniversity.student.lesson_plan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.google.android.material.card.MaterialCardView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentLessonPlanDetailActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseLessonPlanDetails;

    TextViewRegularFont tvSemesterLectureDetails;
    TextViewRegularFont tvCourseNameLectureDetails;
    AppCompatImageView imgExpandLectureDetailsCard;
    TextViewRegularFont tvFacultyNameLectureDetails;
    TextViewRegularFont tvLecturePerWeekCountLectureDetails;
    TextViewRegularFont tvSubjectNameLectureDetails;
    TextViewRegularFont tvRefBookLectureDetails;

    LinearLayout llExpandableLayoutLectureDetails, llExpandedLectureDetailsHeader;
    boolean isLectureDetailsCardExpanded = true;
    MaterialCardView cvLectureDetails;

    MaterialCardView cvTopicsLecturePlanDetails;

    StudentLessonPlanListPojo studentLessonPlanListPojo = null;
    ExpandableListView elvLectureDetailsTopics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_lesson_plan_detail);
        initView();

        if (getIntent().hasExtra(IntentConstants.STUDENT_LECTURE_DETAILS_LIST)) {
            studentLessonPlanListPojo = (StudentLessonPlanListPojo) getIntent().getSerializableExtra(IntentConstants.STUDENT_LECTURE_DETAILS_LIST);
            setLectureDetailsData(studentLessonPlanListPojo);
            if (studentLessonPlanListPojo.getLpDetails() != null &&
                    studentLessonPlanListPojo.getLpDetails().size() > 0) {
                cvTopicsLecturePlanDetails.setVisibility(View.VISIBLE);
                setLectureDetailsExpandableList(studentLessonPlanListPojo);
            } else {
                cvTopicsLecturePlanDetails.setVisibility(View.GONE);
            }
        } else {
            cvLectureDetails.setVisibility(View.GONE);
        }
    }

    private void setLectureDetailsData(StudentLessonPlanListPojo studentLessonPlanListPojo) {
        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getSemester())) {
            tvSemesterLectureDetails.setText(studentLessonPlanListPojo.getSemester() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getCourseName())) {
            tvCourseNameLectureDetails.setText(studentLessonPlanListPojo.getCourseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getFacultyName())) {
            tvFacultyNameLectureDetails.setText(studentLessonPlanListPojo.getFacultyName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getLecturePerWeek())) {
            tvLecturePerWeekCountLectureDetails.setText(studentLessonPlanListPojo.getLecturePerWeek() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getSubject())) {
            tvSubjectNameLectureDetails.setText(studentLessonPlanListPojo.getSubject() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getRefBookName())) {
            tvRefBookLectureDetails.setText(studentLessonPlanListPojo.getRefBookName() + "");
        }

    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentLessonPlanDetailActivity.this);
        connectionDetector = new ConnectionDetector(StudentLessonPlanDetailActivity.this);
        ivCloseLessonPlanDetails = findViewById(R.id.ivCloseLessonPlanDetails);
        ivCloseLessonPlanDetails.setOnClickListener(this);

        tvSemesterLectureDetails = findViewById(R.id.tvSemesterLectureDetails);
        tvCourseNameLectureDetails = findViewById(R.id.tvCourseNameLectureDetails);
        imgExpandLectureDetailsCard = findViewById(R.id.imgExpandLectureDetailsCard);
        tvFacultyNameLectureDetails = findViewById(R.id.tvFacultyNameLectureDetails);
        tvLecturePerWeekCountLectureDetails = findViewById(R.id.tvLecturePerWeekCountLectureDetails);
        tvSubjectNameLectureDetails = findViewById(R.id.tvSubjectNameLectureDetails);
        tvRefBookLectureDetails = findViewById(R.id.tvRefBookLectureDetails);
        llExpandableLayoutLectureDetails = findViewById(R.id.llExpandableLayoutLectureDetails);
        llExpandedLectureDetailsHeader = findViewById(R.id.llExpandedLectureDetailsHeader);
        llExpandedLectureDetailsHeader.setOnClickListener(this);

        cvLectureDetails = findViewById(R.id.cvLectureDetails);
        cvTopicsLecturePlanDetails = findViewById(R.id.cvTopicsLecturePlanDetails);
        elvLectureDetailsTopics = findViewById(R.id.elvLectureDetailsTopics);
    }

    private void setLectureDetailsExpandableList(StudentLessonPlanListPojo studentLessonPlanListPojo) {
        ArrayList<String> lectureDetailsHeaderArrayList = new ArrayList<>();
        HashMap<String, ArrayList<StudentLessonPlanListPojo.LpSubTopic>> lectureDetailsChildHashMap = new HashMap<>();

        ArrayList<StudentLessonPlanListPojo.LpSubTopic> lpSubTopicArrayList = new ArrayList<>();

        for (int i = 0; i < studentLessonPlanListPojo.getLpDetails().size(); i++) {
            if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.getLpDetails().get(i).getTopicName())) {
                lectureDetailsHeaderArrayList.add(studentLessonPlanListPojo.getLpDetails().get(i).getTopicName() + "");

                for (int k = 0; k < studentLessonPlanListPojo.getLpDetails().get(i).getLpSubTopic().size(); k++) {
                    StudentLessonPlanListPojo.LpSubTopic lpSubTopic =
                            studentLessonPlanListPojo.getLpDetails().get(i).getLpSubTopic().get(k);
                    lpSubTopicArrayList.add(lpSubTopic);
                }
                lectureDetailsChildHashMap.put(studentLessonPlanListPojo.getLpDetails().get(i).getTopicName(), lpSubTopicArrayList);
            }
        }

        elvLectureDetailsTopics.setAdapter(new StudentLectureDetailsAdapter(StudentLessonPlanDetailActivity.this, lectureDetailsHeaderArrayList,
                lectureDetailsChildHashMap));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseLessonPlanDetails) {
            onBackPressed();
        } else if (v.getId() == R.id.llExpandedLectureDetailsHeader) {
            isLectureDetailsCardExpanded = toggleLayoutForDefaultOpenCard(!isLectureDetailsCardExpanded, imgExpandLectureDetailsCard, llExpandableLayoutLectureDetails);
        }
    }

    private boolean toggleLayoutForDefaultOpenCard(boolean isExpanded, View v, LinearLayout layoutExpand) {
        CustomAnimationForDefaultExpandableCard.toggleArrow(v, isExpanded);
        if (isExpanded) {
            CustomAnimationForDefaultExpandableCard.expand(layoutExpand);
        } else {
            CustomAnimationForDefaultExpandableCard.collapse(layoutExpand);
        }
        return isExpanded;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}