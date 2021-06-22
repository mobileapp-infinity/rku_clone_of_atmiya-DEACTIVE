package com.infinity.infoway.rkuniversity.faculty.faculty_lecture_plan;

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

public class FacultyLecturePlanDetailActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyLecturePlanDetails;

    TextViewRegularFont tvSemesterFacultyLectureDetails;
    TextViewRegularFont tvCourseNameFacultyLectureDetails;
    AppCompatImageView imgExpandFacultyLectureDetailsCard;
    TextViewRegularFont tvFacultyNameFacultyLectureDetails;
    TextViewRegularFont tvSubjectNameFacultyLectureDetails;
    TextViewRegularFont tvRefBookFacultyLectureDetails;
    TextViewRegularFont tvFacultyLecturePerWeekCountDetails;

    LinearLayout llExpandableLayoutFacultyLectureDetails, llExpandedFacultyLectureDetailsHeader;
    boolean isFacultyLectureDetailsCardExpanded = true;
    MaterialCardView cvFacultyLectureDetails;

    MaterialCardView cvTopicsFacultyLecturePlanDetails;

    FacultyLecturePlanPojo facultyLecturePlanPojo = null;
    ExpandableListView elvFacultyLectureDetailsTopics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_lecture_plan_detail);
        initView();

        if (getIntent().hasExtra(IntentConstants.FACULTY_LECTURE_DETAILS_LIST)) {
            facultyLecturePlanPojo = (FacultyLecturePlanPojo) getIntent().getSerializableExtra(IntentConstants.FACULTY_LECTURE_DETAILS_LIST);
            setLectureDetailsData(facultyLecturePlanPojo);
            if (facultyLecturePlanPojo.getLpDetails() != null &&
                    facultyLecturePlanPojo.getLpDetails().size() > 0) {
                cvTopicsFacultyLecturePlanDetails.setVisibility(View.VISIBLE);
                setLectureDetailsExpandableList(facultyLecturePlanPojo);
            } else {
                cvTopicsFacultyLecturePlanDetails.setVisibility(View.GONE);
            }
        } else {
            cvFacultyLectureDetails.setVisibility(View.GONE);
        }
    }

    private void setLectureDetailsData(FacultyLecturePlanPojo facultyLecturePlanPojo) {
        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getSemester())) {
            tvSemesterFacultyLectureDetails.setText(facultyLecturePlanPojo.getSemester() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getCourseName())) {
            tvCourseNameFacultyLectureDetails.setText(facultyLecturePlanPojo.getCourseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getFacultyName())) {
            tvFacultyNameFacultyLectureDetails.setText(facultyLecturePlanPojo.getFacultyName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getLecturePerWeek())) {
            tvFacultyLecturePerWeekCountDetails.setText(facultyLecturePlanPojo.getLecturePerWeek() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getSubject())) {
            tvSubjectNameFacultyLectureDetails.setText(facultyLecturePlanPojo.getSubject() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getRefBookName())) {
            tvRefBookFacultyLectureDetails.setText(facultyLecturePlanPojo.getRefBookName() + "");
        }
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyLecturePlanDetailActivity.this);
        connectionDetector = new ConnectionDetector(FacultyLecturePlanDetailActivity.this);
        ivCloseFacultyLecturePlanDetails = findViewById(R.id.ivCloseFacultyLecturePlanDetails);
        ivCloseFacultyLecturePlanDetails.setOnClickListener(this);

        tvSemesterFacultyLectureDetails = findViewById(R.id.tvSemesterFacultyLectureDetails);
        tvCourseNameFacultyLectureDetails = findViewById(R.id.tvCourseNameFacultyLectureDetails);
        imgExpandFacultyLectureDetailsCard = findViewById(R.id.imgExpandFacultyLectureDetailsCard);
        tvFacultyNameFacultyLectureDetails = findViewById(R.id.tvFacultyNameFacultyLectureDetails);
        tvFacultyLecturePerWeekCountDetails = findViewById(R.id.tvFacultyLecturePerWeekCountDetails);
        tvSubjectNameFacultyLectureDetails = findViewById(R.id.tvSubjectNameFacultyLectureDetails);
        tvRefBookFacultyLectureDetails = findViewById(R.id.tvRefBookFacultyLectureDetails);
        llExpandableLayoutFacultyLectureDetails = findViewById(R.id.llExpandableLayoutFacultyLectureDetails);
        llExpandedFacultyLectureDetailsHeader = findViewById(R.id.llExpandedFacultyLectureDetailsHeader);
        llExpandedFacultyLectureDetailsHeader.setOnClickListener(this);

        cvFacultyLectureDetails = findViewById(R.id.cvFacultyLectureDetails);
        cvTopicsFacultyLecturePlanDetails = findViewById(R.id.cvTopicsFacultyLecturePlanDetails);
        elvFacultyLectureDetailsTopics = findViewById(R.id.elvFacultyLectureDetailsTopics);
    }

    private void setLectureDetailsExpandableList(FacultyLecturePlanPojo facultyLecturePlanPojo) {
        ArrayList<String> lectureDetailsHeaderArrayList = new ArrayList<>();
        HashMap<String, ArrayList<FacultyLecturePlanPojo.LpSubTopic>> lectureDetailsChildHashMap = new HashMap<>();

        ArrayList<FacultyLecturePlanPojo.LpSubTopic> lpSubTopicArrayList = new ArrayList<>();

        for (int i = 0; i < facultyLecturePlanPojo.getLpDetails().size(); i++) {
            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLecturePlanPojo.getLpDetails().get(i).getTopicName())) {
                lectureDetailsHeaderArrayList.add(facultyLecturePlanPojo.getLpDetails().get(i).getTopicName() + "");

                for (int k = 0; k < facultyLecturePlanPojo.getLpDetails().get(i).getLpSubTopic().size(); k++) {
                    FacultyLecturePlanPojo.LpSubTopic lpSubTopic =
                            facultyLecturePlanPojo.getLpDetails().get(i).getLpSubTopic().get(k);
                    lpSubTopicArrayList.add(lpSubTopic);
                }
                lectureDetailsChildHashMap.put(facultyLecturePlanPojo.getLpDetails().get(i).getTopicName(), lpSubTopicArrayList);
            }
        }

        elvFacultyLectureDetailsTopics.setAdapter(new FacultyLecturePlanDetailsListAdapter(FacultyLecturePlanDetailActivity.this, lectureDetailsHeaderArrayList,
                lectureDetailsChildHashMap));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyLecturePlanDetails) {
            onBackPressed();
        } else if (v.getId() == R.id.llExpandedFacultyLectureDetailsHeader) {
            isFacultyLectureDetailsCardExpanded = toggleLayoutForDefaultOpenCard(!isFacultyLectureDetailsCardExpanded, imgExpandFacultyLectureDetailsCard, llExpandableLayoutFacultyLectureDetails);
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