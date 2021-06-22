package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_lab_or_lecture_plan_teaching_update;

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
import com.infinity.infoway.rkuniversity.utils.IntentConstants;

import java.util.ArrayList;
import java.util.HashMap;

public class FacultyLabOrLecturePlanTeachingUpdateDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView ivCloseFacultyLabOrLecturePlanDetails;

    TextViewRegularFont tvSemesterFacultyLabOrLecturePlanDetails;
    TextViewRegularFont tvCourseNameFacultyLabOrLecturePlanDetails;
    AppCompatImageView imgExpandFacultyLabOrLecturePlanDetails;

    //    TextViewRegularFont tvFacultyNameFacultyLabOrLecturePlanDetails;
    TextViewRegularFont tvSubjectNameFacultyLabOrLecturePlanDetails;
    TextViewRegularFont tvRefBookFacultyLabOrLecturePlanDetails;
    TextViewRegularFont tvLecturePerWeekCountFacultyLabOrLecturePlanDetails;

    LinearLayout llExpandableLayoutFacultyLabOrLecturePlanDetails, llExpandedFacultyLabOrLecturePlanDetails;
    boolean isLbOrLectureDetailsCardExpanded = true;
    MaterialCardView cvLectureDetailsLabOrLecture;

    MaterialCardView cvTopicsFacultyLabOrLecturePlanDetails;

    ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo> facultyLabOrLecturePlanTeachingUpdatePojoArrayList = null;
    ExpandableListView elvFacultyLabOrLecturePlanDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_lab_or_lecture_plan_teaching_update_details);
        initView();
        if (getIntent().hasExtra(IntentConstants.FACULTY_LAB_OR_LECTURE_DETAILS_TEACHING_UPDATE)) {
            int position = Integer.parseInt(getIntent().getStringExtra(IntentConstants.FACULTY_LAB_OR_LECTURE_DETAILS_TEACHING_UPDATE_POSITION));
            facultyLabOrLecturePlanTeachingUpdatePojoArrayList = (ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo>) getIntent().getSerializableExtra(IntentConstants.FACULTY_LAB_OR_LECTURE_DETAILS_TEACHING_UPDATE);
            setLectureDetailsData(facultyLabOrLecturePlanTeachingUpdatePojoArrayList.get(position));
            if (facultyLabOrLecturePlanTeachingUpdatePojoArrayList.get(position).getLabOrLectureDetailsList() != null &&
                    facultyLabOrLecturePlanTeachingUpdatePojoArrayList.get(position).getLabOrLectureDetailsList().size() > 0) {
                cvTopicsFacultyLabOrLecturePlanDetails.setVisibility(View.VISIBLE);
                setLectureDetailsExpandableList(facultyLabOrLecturePlanTeachingUpdatePojoArrayList);
            } else {
                cvTopicsFacultyLabOrLecturePlanDetails.setVisibility(View.GONE);
            }
        } else {
            cvLectureDetailsLabOrLecture.setVisibility(View.GONE);
        }
    }


    private void setLectureDetailsExpandableList(ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo> studentLessonPlanListPojo) {
        ArrayList<String> lectureDetailsHeaderArrayList = new ArrayList<>();
        HashMap<String, ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo.LabOrLectureDetails>> lectureDetailsChildHashMap = new HashMap<>();

        ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo.LabOrLectureDetails> lpSubTopicArrayList = new ArrayList<>();

        for (int i = 0; i < studentLessonPlanListPojo.size(); i++) {
            if (!CommonUtil.checkIsEmptyOrNullCommon(studentLessonPlanListPojo.get(i).getLpSub())) {
                lectureDetailsHeaderArrayList.add(studentLessonPlanListPojo.get(i).getLpSub() + "");

                for (int k = 0; k < studentLessonPlanListPojo.get(i).getLabOrLectureDetailsList().size(); k++) {
                    FacultyLabOrLecturePlanTeachingUpdatePojo.LabOrLectureDetails lpSubTopic =
                            studentLessonPlanListPojo.get(i).getLabOrLectureDetailsList().get(k);
                    lpSubTopicArrayList.add(lpSubTopic);
                }
                lectureDetailsChildHashMap.put(studentLessonPlanListPojo.get(i).getLpSub(), lpSubTopicArrayList);
            }
        }

        elvFacultyLabOrLecturePlanDetails.setAdapter(new FacultyLabOrLecturePlanDetailsTeachingUpdateAdapter(FacultyLabOrLecturePlanTeachingUpdateDetailsActivity.this, lectureDetailsHeaderArrayList,
                lectureDetailsChildHashMap));
    }

    private void setLectureDetailsData(FacultyLabOrLecturePlanTeachingUpdatePojo facultyLabOrLecturePlanTeachingUpdatePojo) {
        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpSem())) {
            tvSemesterFacultyLabOrLecturePlanDetails.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getLpSem() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpCourse())) {
            tvCourseNameFacultyLabOrLecturePlanDetails.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getLpCourse() + "");
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.get())) {
//            tvFacultyNameLectureDetails.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getFacultyName() + "");
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpLabPerWeek())) {
            tvLecturePerWeekCountFacultyLabOrLecturePlanDetails.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getLpLabPerWeek() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpSub())) {
            tvSubjectNameFacultyLabOrLecturePlanDetails.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getLpSub() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyLabOrLecturePlanTeachingUpdatePojo.getLpReference())) {
            tvRefBookFacultyLabOrLecturePlanDetails.setText(facultyLabOrLecturePlanTeachingUpdatePojo.getLpReference() + "");
        }

    }


    private void initView() {
        ivCloseFacultyLabOrLecturePlanDetails = findViewById(R.id.ivCloseFacultyLabOrLecturePlanDetails);
        ivCloseFacultyLabOrLecturePlanDetails.setOnClickListener(this);

        tvSemesterFacultyLabOrLecturePlanDetails = findViewById(R.id.tvSemesterFacultyLabOrLecturePlanDetails);
        tvCourseNameFacultyLabOrLecturePlanDetails = findViewById(R.id.tvCourseNameFacultyLabOrLecturePlanDetails);
        imgExpandFacultyLabOrLecturePlanDetails = findViewById(R.id.imgExpandFacultyLabOrLecturePlanDetails);
//        tvFacultyNameLectureDetails = findViewById(R.id.tvFacultyNameLectureDetails);
        tvLecturePerWeekCountFacultyLabOrLecturePlanDetails = findViewById(R.id.tvLecturePerWeekCountFacultyLabOrLecturePlanDetails);
        tvSubjectNameFacultyLabOrLecturePlanDetails = findViewById(R.id.tvSubjectNameFacultyLabOrLecturePlanDetails);
        tvRefBookFacultyLabOrLecturePlanDetails = findViewById(R.id.tvRefBookFacultyLabOrLecturePlanDetails);
        llExpandableLayoutFacultyLabOrLecturePlanDetails = findViewById(R.id.llExpandableLayoutFacultyLabOrLecturePlanDetails);
        llExpandedFacultyLabOrLecturePlanDetails = findViewById(R.id.llExpandedFacultyLabOrLecturePlanDetails);
        llExpandedFacultyLabOrLecturePlanDetails.setOnClickListener(this);

        cvLectureDetailsLabOrLecture = findViewById(R.id.cvLectureDetailsLabOrLecture);
        cvTopicsFacultyLabOrLecturePlanDetails = findViewById(R.id.cvTopicsFacultyLabOrLecturePlanDetails);
        elvFacultyLabOrLecturePlanDetails = findViewById(R.id.elvFacultyLabOrLecturePlanDetails);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyLabOrLecturePlanDetails) {
            onBackPressed();
        } else if (v.getId() == R.id.llExpandedFacultyLabOrLecturePlanDetails) {
            isLbOrLectureDetailsCardExpanded = toggleLayoutForDefaultOpenCard(!isLbOrLectureDetailsCardExpanded, imgExpandFacultyLabOrLecturePlanDetails, llExpandableLayoutFacultyLabOrLecturePlanDetails);
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