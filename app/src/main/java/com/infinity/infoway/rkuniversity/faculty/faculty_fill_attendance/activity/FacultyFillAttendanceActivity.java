package com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.SpinnerSimpleAdapter;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.adapter.SelectTeachingMethodGridViewAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.adapter.StudentListFillAttendanceAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.FacultyFillAttendanceConfigurationPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.FacultyPendingAttendanceUnitPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetTeachingAidDetailsPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetTeachingMethodPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.InsertAllAbsentStudentByAlternateMethodPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.InsertAllPresentStudentByAlternateMethodPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.InsertClassWiseAttendancePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.StudentDetailsFillAttendancePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.UpdateDailyLectureWiseAttendanceStatusPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_pending_attendance.FacultyPendingAttendanceActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_pending_attendance.FacultyPendingAttendancePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyFillAttendanceActivity extends AppCompatActivity implements View.OnClickListener,
        SelectTeachingMethodGridViewAdapter.ITeachingMethod, StudentListFillAttendanceAdapter.IOnStudentAbsentPresentStatusChanged {

    private static int FACULTY_FILL_ATTENDANCE_CONFIGURATION = 0;

    private static final String SELECT_NO_OF_POST_ON = "Select No Of Post";
    private static final String SELECT_TEACHING_AID = "Select Teaching Aid";
    private static final String SELECT_TEACHING_UNIT = "Select Unit";
    private static final String SELECT_TOPIC = "Select Topic";

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyFillAttendance;
    SwitchButton sBtnAttendanceForByRollNo;
    TextViewRegularFont tvAttendanceForByRollNumber;

    LinearLayout llSelectTeachingMethod;
    GridView gvTeachingMethodList;
    String selectedMethodIds = "";
    private boolean isMethodSelected = false;
    ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayListNew;

    SpinnerSimpleAdapter spinnerAdapterYearSelectNoOfPost;
    Spinner spSelectNoOfPostOn;
    ArrayList<String> noOfPostArrayList = new ArrayList<>();

    LinearLayout llFaciltyAidDetails;
    SpinnerSimpleAdapter spinnerAdapterFacultyAidDetails;
    Spinner spSelectTeachingAid;
    ArrayList<String> teachingAidArrayList;
    HashMap<String, String> teachingAidNameAndIdHashMap;


    LinearLayout llFacultyPendingAttendaUnit;
    SpinnerSimpleAdapter spinnerAdapterFacultyUnit;
    Spinner spSelectUnit;
    ArrayList<String> teachingUnitArrayList;
    HashMap<String, String> teachingUnitNameAndIdHashMap;
    FacultyPendingAttendancePojo.Details facultyPendingAttendancePojo;

    RadioGroup rbtnGrpByStudentAndRollNo;
    LinearLayout llFillAttendanceByRollNumber;
    LinearLayout llFillAttendanceByStudent;

    //By Roll Number
    TextInputEditText tilStudentCommaSeparatedRollNumber;

    //By Student
    RecyclerView rvFillAttendanceStudentList;
    LinearLayout llFillAttendanceByStudentInner;

    //Common
    TextInputEditText tilTopic;
    TextViewRegularFont btnSaveFilledAttendance;

    TextViewRegularFont tvCourseName;
    TextViewRegularFont tvLectureName;
    LinearLayout llHangingCard;

//    LinearLayout llEnterTopicEdt;
//    Spinner spEnterTopic;

    LinearLayout llCommonForConfig1;
    LinearLayout llCommonForConfig2;
    Spinner spSelectTopicForConfig2;
    ArrayList<String> topicListForConfig2;
    HashMap<String, String> topicNameAndIdListForConfig2;
    SpinnerSimpleAdapter spinnerAdapterFacultyTopicForConfig2;
    LinearLayout llTopicUnitForConfig2;
//    LinearLayout llEnterTopicSp;

    String strDate = "";

    StudentListFillAttendanceAdapter studentListFillAttendanceAdapter = null;
    ArrayList<StudentDetailsFillAttendancePojo.TableBean> studentAbsentPresentList = null;
    SwitchButton sbtnPresentOrAbsentAll;

    public static boolean IS_STUDENT_NAME_DISPLAY = true;
    public static boolean IS_STUDENT_ENROLLMENT_NUMBER_DISPLAY = true;
    AppCompatButton btnColumn;
    TextViewRegularFont tvRollNoHeader;
    TextViewRegularFont tvStudentNameHeader;
    TextViewRegularFont tvStudentEnNoHeader;

    View view1Header;
    View view2Header;
    View view3Header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_fill_attendance);
        initView();

        if (getIntent().hasExtra(IntentConstants.FACULTY_FILL_ATTENDANCE)) {
            facultyPendingAttendancePojo = (FacultyPendingAttendancePojo.Details) getIntent().getSerializableExtra(IntentConstants.FACULTY_FILL_ATTENDANCE);


            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getDlDate())) {
                String date_oldformat = facultyPendingAttendancePojo.getDlDate() + "";//
                if (date_oldformat != null && date_oldformat.length() > 4) {

                    date_oldformat = date_oldformat + "-";
                    date_oldformat = date_oldformat.replace("/", "-");
                    String temp[] = date_oldformat.split("-");
                    strDate = temp[2] + "-" + temp[1] + "-" + temp[0];
                }
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getCourseName()) ||
                    !CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getLectName())) {
                llHangingCard.setVisibility(View.VISIBLE);
                tvCourseName.setText(facultyPendingAttendancePojo.getCourseName() + "");
                tvLectureName.setText(facultyPendingAttendancePojo.getSubName() + "");
            } else {
                llHangingCard.setVisibility(View.GONE);
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getDivId()) &&
                    !CommonUtil.checkIsEmptyOrNullCommon(facultyPendingAttendancePojo.getSubId())) {
                getFacultyFillAttendanceConfigurationApiCall(true, false);
            } else {
                Toast.makeText(this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
        }

        rbtnGrpByStudentAndRollNo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbtnFillAttendanceByStydent) {
                    llFillAttendanceByStudent.setVisibility(View.VISIBLE);
                    llFillAttendanceByRollNumber.setVisibility(View.GONE);
                } else if (checkedId == R.id.rbtnFillAttendanceByRolNo) {
                    llFillAttendanceByRollNumber.setVisibility(View.VISIBLE);
                    llFillAttendanceByStudent.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initView() {
        IS_STUDENT_NAME_DISPLAY = true;
        IS_STUDENT_ENROLLMENT_NUMBER_DISPLAY = true;
        mySharedPreferences = new MySharedPreferences(FacultyFillAttendanceActivity.this);
        connectionDetector = new ConnectionDetector(FacultyFillAttendanceActivity.this);
        tilStudentCommaSeparatedRollNumber = findViewById(R.id.tilStudentCommaSeparatedRollNumber);
        tilTopic = findViewById(R.id.tilTopic);
        llFillAttendanceByStudent = findViewById(R.id.llFillAttendanceByStudent);
        llFillAttendanceByRollNumber = findViewById(R.id.llFillAttendanceByRollNumber);
        rbtnGrpByStudentAndRollNo = findViewById(R.id.rbtnGrpByStudentAndRollNo);
        ivCloseFacultyFillAttendance = findViewById(R.id.ivCloseFacultyFillAttendance);
        ivCloseFacultyFillAttendance.setOnClickListener(this);
        sBtnAttendanceForByRollNo = findViewById(R.id.sBtnAttendanceForByRollNo);
        tvAttendanceForByRollNumber = findViewById(R.id.tvAttendanceForByRollNumber);
        rvFillAttendanceStudentList = findViewById(R.id.rvFillAttendanceStudentList);
        llFillAttendanceByStudentInner = findViewById(R.id.llFillAttendanceByStudentInner);
        btnSaveFilledAttendance = findViewById(R.id.btnSaveFilledAttendance);
        btnSaveFilledAttendance.setOnClickListener(this);
        sbtnPresentOrAbsentAll = findViewById(R.id.sbtnPresentOrAbsentAll);
        tvCourseName = findViewById(R.id.tvCourseName);
        tvLectureName = findViewById(R.id.tvLectureName);
        llHangingCard = findViewById(R.id.llHangingCard);
        llTopicUnitForConfig2 = findViewById(R.id.llTopicForConfig2);
        btnColumn = findViewById(R.id.btnColumn);
        btnColumn.setOnClickListener(this);
        tvRollNoHeader = findViewById(R.id.tvRollNoHeader);
        tvStudentNameHeader = findViewById(R.id.tvStudentNameHeader);
        tvStudentEnNoHeader = findViewById(R.id.tvStudentEnNoHeader);

        llSelectTeachingMethod = findViewById(R.id.llSelectTeachingMethod);
        gvTeachingMethodList = findViewById(R.id.gvTeachingMethodList);

        view1Header = findViewById(R.id.view1Header);
        view2Header = findViewById(R.id.view2Header);
        view3Header = findViewById(R.id.view3Header);

        noOfPostArrayList.add(SELECT_NO_OF_POST_ON);

        for (int i = 0; i <= 10; i++) {
            noOfPostArrayList.add(String.valueOf(i));
        }

        spinnerAdapterYearSelectNoOfPost = new SpinnerSimpleAdapter(FacultyFillAttendanceActivity.this, noOfPostArrayList);
        spSelectNoOfPostOn = findViewById(R.id.spSelectNoOfPostOn);
        spSelectNoOfPostOn.setAdapter(spinnerAdapterYearSelectNoOfPost);

        llFaciltyAidDetails = findViewById(R.id.llFaciltyAidDetails);
        spSelectTeachingAid = findViewById(R.id.spSelectTeachingAid);

        llFacultyPendingAttendaUnit = findViewById(R.id.llFacultyPendingAttendaUnit);
        spSelectUnit = findViewById(R.id.spSelectUnit);

        llCommonForConfig1 = findViewById(R.id.llCommonForConfig1);
        llCommonForConfig2 = findViewById(R.id.llCommonForConfig2);
//        llEnterTopicSp = findViewById(R.id.llEnterTopicSp);

        spSelectTopicForConfig2 = findViewById(R.id.spSelectTopicForConfig2);

        sbtnPresentOrAbsentAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (studentListFillAttendanceAdapter != null) {
                    if (b) {
                        studentListFillAttendanceAdapter.selectAll();
                    } else {
                        studentListFillAttendanceAdapter.unSelectAll();
                    }
                }
            }
        });

        spSelectTopicForConfig2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    String topicIdForConfig2 = topicNameAndIdListForConfig2.get(topicListForConfig2.get(i)) + "";
                    getMethodFromApi(true, true, topicIdForConfig2);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        spSelectNoOfPostOn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i > 0) {
//
//                } else {
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


//        spSelectTeachingAid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


//        spSelectUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        sBtnAttendanceForByRollNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvAttendanceForByRollNumber.setText("Attendance For Present");
                } else {
                    tvAttendanceForByRollNumber.setText("Attendance For Absent");
                }
            }
        });
    }

    AlertDialog filterDialog;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyFillAttendance) {
            onBackPressed();
        } else if (v.getId() == R.id.btnSaveFilledAttendance) {
            callStudentAttendanceSaveApi();
        } else if (v.getId() == R.id.btnColumn) {
            try {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FacultyFillAttendanceActivity.this);
                dialogBuilder.setCancelable(true);
                LayoutInflater layoutInflater1 = LayoutInflater.from(FacultyFillAttendanceActivity.this);
                View dialogView = layoutInflater1.inflate(R.layout.custom_layout_for_fill_attendance_filter, null);
                AppCompatCheckBox cbSelectEnrollmentNo = dialogView.findViewById(R.id.cbSelectEnrollmentNo);
                AppCompatCheckBox cbSelectStudentName = dialogView.findViewById(R.id.cbSelectStudentName);
                AppCompatButton btnCancel = dialogView.findViewById(R.id.btnCancel);
                AppCompatButton btnOk = dialogView.findViewById(R.id.btnOk);

                applyFilter(cbSelectEnrollmentNo, cbSelectStudentName);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterDialog.hide();
                    }
                });
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        IS_STUDENT_ENROLLMENT_NUMBER_DISPLAY = cbSelectEnrollmentNo.isChecked();
                        IS_STUDENT_NAME_DISPLAY = cbSelectStudentName.isChecked();

                        applyFilter(cbSelectEnrollmentNo, cbSelectStudentName);

                        if (studentListFillAttendanceAdapter != null) {
                            studentListFillAttendanceAdapter.notifyDataSetChanged();
                        }
                        filterDialog.hide();
                    }
                });


                dialogBuilder.setView(dialogView);
                filterDialog = dialogBuilder.create();
                filterDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                filterDialog.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    private void applyFilter(AppCompatCheckBox cbSelectEnrollmentNo, AppCompatCheckBox cbSelectStudentName) {
        if (FacultyFillAttendanceActivity.IS_STUDENT_NAME_DISPLAY && FacultyFillAttendanceActivity.IS_STUDENT_ENROLLMENT_NUMBER_DISPLAY) {
            cbSelectEnrollmentNo.setChecked(true);
            cbSelectStudentName.setChecked(true);

            LinearLayout.LayoutParams layoutParamForStudentName = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.3f);
            tvStudentNameHeader.setLayoutParams(layoutParamForStudentName);

            LinearLayout.LayoutParams layoutParamForStudentEnNo = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.3f);
            tvStudentEnNoHeader.setLayoutParams(layoutParamForStudentEnNo);

            LinearLayout.LayoutParams layoutParamForStudentRollNo = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f);
            tvRollNoHeader.setLayoutParams(layoutParamForStudentRollNo);

            LinearLayout.LayoutParams layoutParamForStudentSwitch = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f);
            sbtnPresentOrAbsentAll.setLayoutParams(layoutParamForStudentSwitch);

            view3Header.setVisibility(View.VISIBLE);
            view2Header.setVisibility(View.VISIBLE);

            tvStudentEnNoHeader.setVisibility(View.VISIBLE);
            tvStudentNameHeader.setVisibility(View.VISIBLE);

        } else if (FacultyFillAttendanceActivity.IS_STUDENT_NAME_DISPLAY && !FacultyFillAttendanceActivity.IS_STUDENT_ENROLLMENT_NUMBER_DISPLAY) {
            cbSelectStudentName.setChecked(true);
            cbSelectEnrollmentNo.setChecked(false);

            LinearLayout.LayoutParams layoutParamForStudentName = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.6f);
            tvStudentNameHeader.setLayoutParams(layoutParamForStudentName);

            LinearLayout.LayoutParams layoutParamForStudentEnNo = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f);
            tvStudentEnNoHeader.setLayoutParams(layoutParamForStudentEnNo);


            LinearLayout.LayoutParams layoutParamForStudentRollNo = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f);
            tvRollNoHeader.setLayoutParams(layoutParamForStudentRollNo);

            LinearLayout.LayoutParams layoutParamForStudentSwitch = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f);
            sbtnPresentOrAbsentAll.setLayoutParams(layoutParamForStudentSwitch);

            view3Header.setVisibility(View.GONE);
            view2Header.setVisibility(View.VISIBLE);

            tvStudentNameHeader.setVisibility(View.VISIBLE);
            tvStudentEnNoHeader.setVisibility(View.GONE);

        } else if (!FacultyFillAttendanceActivity.IS_STUDENT_NAME_DISPLAY && FacultyFillAttendanceActivity.IS_STUDENT_ENROLLMENT_NUMBER_DISPLAY) {
            cbSelectEnrollmentNo.setChecked(true);
            cbSelectStudentName.setChecked(false);

            LinearLayout.LayoutParams layoutParamForStudentName = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f);
            tvStudentNameHeader.setLayoutParams(layoutParamForStudentName);

            LinearLayout.LayoutParams layoutParamForStudentEnNo = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.6f);
            tvStudentEnNoHeader.setLayoutParams(layoutParamForStudentEnNo);

            LinearLayout.LayoutParams layoutParamForStudentRollNo = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f);
            tvRollNoHeader.setLayoutParams(layoutParamForStudentRollNo);

            LinearLayout.LayoutParams layoutParamForStudentSwitch = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f);
            sbtnPresentOrAbsentAll.setLayoutParams(layoutParamForStudentSwitch);

            view3Header.setVisibility(View.VISIBLE);
            view2Header.setVisibility(View.GONE);

            tvStudentNameHeader.setVisibility(View.GONE);
            tvStudentEnNoHeader.setVisibility(View.VISIBLE);

        } else {
            cbSelectEnrollmentNo.setChecked(false);
            cbSelectStudentName.setChecked(false);

            LinearLayout.LayoutParams layoutParamForStudentName = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f);
            tvStudentNameHeader.setLayoutParams(layoutParamForStudentName);

            LinearLayout.LayoutParams layoutParamForStudentEnNo = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f);
            tvStudentEnNoHeader.setLayoutParams(layoutParamForStudentEnNo);

            LinearLayout.LayoutParams layoutParamForStudentRollNo = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f);
            tvRollNoHeader.setLayoutParams(layoutParamForStudentRollNo);

            LinearLayout.LayoutParams layoutParamForStudentSwitch = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f);
            sbtnPresentOrAbsentAll.setLayoutParams(layoutParamForStudentSwitch);

            view3Header.setVisibility(View.VISIBLE);
            view2Header.setVisibility(View.GONE);

            tvStudentEnNoHeader.setVisibility(View.GONE);
            tvStudentNameHeader.setVisibility(View.GONE);

        }
    }

    private void callStudentAttendanceSaveApi() {
        if (rbtnGrpByStudentAndRollNo.getCheckedRadioButtonId() == R.id.rbtnFillAttendanceByStydent) {//by student selection method
            //Call insert student attendance by class api

            String attStatus = "1";
            String attHomeWork = "";

            if (FACULTY_FILL_ATTENDANCE_CONFIGURATION == 1) {
                String attTeachingMethod;
                String flint;
                String attAid = "0";
                String unitId = "0";
                String attTopic;
                if (facultyPendingAttendancePojo.getLecType().equalsIgnoreCase("1")) {
                    if (CommonUtil.checkIsEmptyOrNullCommon(selectedMethodIds)) {
                        Toast.makeText(this, "Please select Teaching Method", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (spSelectNoOfPostOn.getSelectedItemPosition() == 0) {
                        Toast.makeText(this, "Please select No of post", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (teachingAidArrayList != null && teachingAidArrayList.size() > 0 &&
                            spSelectTeachingAid.getSelectedItemPosition() == 0) {
                        Toast.makeText(this, "Please select Teaching Aid", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (teachingUnitArrayList != null && teachingUnitArrayList.size() > 0 &&
                            spSelectUnit.getSelectedItemPosition() == 0) {
                        Toast.makeText(this, "Please select unit", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (CommonUtil.checkIsEmptyOrNullCommon(tilTopic.getText().toString())) {
                        Toast.makeText(this, "Please enter topic name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    attTopic = tilTopic.getText().toString();
                    unitId = teachingUnitArrayList != null && teachingUnitArrayList.size() > 0 ? teachingUnitNameAndIdHashMap.get(teachingUnitArrayList.get(spSelectUnit.getSelectedItemPosition())) : "0";
                    flint = noOfPostArrayList.get(spSelectNoOfPostOn.getSelectedItemPosition()).trim();
                    attTeachingMethod = selectedMethodIds;
                    attAid = teachingAidArrayList != null && teachingAidArrayList.size() > 0 ? teachingAidNameAndIdHashMap.get(teachingAidArrayList.get(spSelectTeachingAid.getSelectedItemPosition())) : "0";

                    insertClassWiseAttendanceApiCall(true, false, attStatus, attTopic, attTeachingMethod,
                            attAid, flint, attHomeWork, unitId, selectedPresentIDList, selectedAbsentIDList);

                } else {
                    if (teachingUnitArrayList != null && teachingUnitArrayList.size() > 0 && spSelectUnit.getSelectedItemPosition() == 0) {
                        Toast.makeText(this, "Please select unit", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (CommonUtil.checkIsEmptyOrNullCommon(tilTopic.getText().toString())) {
                        Toast.makeText(this, "Please enter topic name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    attTopic = tilTopic.getText().toString();
                    unitId = teachingUnitArrayList != null && teachingUnitArrayList.size() > 0 ? teachingUnitNameAndIdHashMap.get(teachingUnitArrayList.get(spSelectUnit.getSelectedItemPosition())) : "0";
                    attTeachingMethod = "";
                    attAid = "0";
                    flint = "0";

                    insertClassWiseAttendanceApiCall(true, false, attStatus, attTopic, attTeachingMethod,
                            attAid, flint, attHomeWork, unitId, selectedPresentIDList, selectedAbsentIDList);

                }
            } else if (FACULTY_FILL_ATTENDANCE_CONFIGURATION == 2) {

                String attTopic = "";

                if (topicListForConfig2 != null && topicListForConfig2.size() > 0 &&
                        spSelectTopicForConfig2.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Please select topic", Toast.LENGTH_SHORT).show();
                    return;
                } else if (topicListForConfig2 == null || topicListForConfig2.size() == 0) {
                    Toast.makeText(this, "Topic not found!", Toast.LENGTH_SHORT).show();
                    return;
                }

                attTopic = topicListForConfig2.get(spSelectTopicForConfig2.getSelectedItemPosition());


                insertClassWiseAttendanceApiCall(true, false, attStatus, attTopic, topicMethodForConfig2,
                        topicMethodAidForConfig2, "0", attHomeWork, topicUnitIdForConfig2, selectedPresentIDList, selectedAbsentIDList);
            }
        } else if (rbtnGrpByStudentAndRollNo.getCheckedRadioButtonId() == R.id.rbtnFillAttendanceByRolNo) {//by manually comma Separated roll no
            if (sBtnAttendanceForByRollNo.isChecked()) {
                //Call Present All Student Api
                makeStudentAbsentAndPresentByRollNumber(true);
            } else {
                //Call Absent All Student Api
                makeStudentAbsentAndPresentByRollNumber(false);
            }
        }
    }


    private void makeStudentAbsentAndPresentByRollNumber(boolean isAttendanceForPresent) {
        if (FACULTY_FILL_ATTENDANCE_CONFIGURATION == 1) {

            String attTeachingMethod;
            String flint;
            String attAid = "0";
            String unitId = "0";
            String attTopic;
            String commaSeparatedRollNumber;

            if (facultyPendingAttendancePojo.getLecType().equalsIgnoreCase("1")) {

                if (CommonUtil.checkIsEmptyOrNullCommon(tilStudentCommaSeparatedRollNumber.getText().toString())) {
                    Toast.makeText(this, "Please enter comma separated roll number", Toast.LENGTH_SHORT).show();
                    return;
                } else if (CommonUtil.checkIsEmptyOrNullCommon(selectedMethodIds)) {
                    Toast.makeText(this, "Please select Teaching Method", Toast.LENGTH_SHORT).show();
                    return;
                } else if (spSelectNoOfPostOn.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Please select No of post", Toast.LENGTH_SHORT).show();
                    return;
                } else if (teachingAidArrayList != null && teachingAidArrayList.size() > 0 &&
                        spSelectTeachingAid.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Please select Teaching Aid", Toast.LENGTH_SHORT).show();
                    return;
                } else if (teachingUnitArrayList != null && teachingUnitArrayList.size() > 0 &&
                        spSelectUnit.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Please select unit", Toast.LENGTH_SHORT).show();
                    return;
                } else if (CommonUtil.checkIsEmptyOrNullCommon(tilTopic.getText().toString())) {
                    Toast.makeText(this, "Please enter topic name", Toast.LENGTH_SHORT).show();
                    return;
                }
                attTopic = tilTopic.getText().toString();
                unitId = teachingUnitArrayList != null && teachingUnitArrayList.size() > 0 ? teachingUnitNameAndIdHashMap.get(teachingUnitArrayList.get(spSelectUnit.getSelectedItemPosition())) : "0";
                flint = noOfPostArrayList.get(spSelectNoOfPostOn.getSelectedItemPosition()).trim();
                attTeachingMethod = selectedMethodIds;
                attAid = teachingAidArrayList != null && teachingAidArrayList.size() > 0 ? teachingAidNameAndIdHashMap.get(teachingAidArrayList.get(spSelectTeachingAid.getSelectedItemPosition())) : "0";
                commaSeparatedRollNumber = tilStudentCommaSeparatedRollNumber.getText().toString();


                if (isAttendanceForPresent) {
                    presentAllStudentApiCall(true, false, attTopic, attTeachingMethod, attAid,
                            flint, commaSeparatedRollNumber, unitId);
                } else {
                    absentAllStudentApiCall(true, false, attTopic, attTeachingMethod, attAid,
                            flint, commaSeparatedRollNumber, unitId);
                }

            } else {
                if (CommonUtil.checkIsEmptyOrNullCommon(tilStudentCommaSeparatedRollNumber.getText().toString())) {
                    Toast.makeText(this, "Please enter comma separated roll number", Toast.LENGTH_SHORT).show();
                    return;
                } else if (teachingUnitArrayList != null && teachingUnitArrayList.size() > 0 && spSelectUnit.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Please select unit", Toast.LENGTH_SHORT).show();
                    return;
                } else if (CommonUtil.checkIsEmptyOrNullCommon(tilTopic.getText().toString())) {
                    Toast.makeText(this, "Please enter topic name", Toast.LENGTH_SHORT).show();
                    return;
                }
                attTopic = tilTopic.getText().toString();
                unitId = teachingUnitArrayList != null && teachingUnitArrayList.size() > 0 ? teachingUnitNameAndIdHashMap.get(teachingUnitArrayList.get(spSelectUnit.getSelectedItemPosition())) : "0";
                attTeachingMethod = "";
                attAid = "0";
                flint = "0";
                commaSeparatedRollNumber = tilStudentCommaSeparatedRollNumber.getText().toString();

                if (isAttendanceForPresent) {
                    presentAllStudentApiCall(true, false, attTopic, attTeachingMethod, attAid,
                            flint, commaSeparatedRollNumber, unitId);
                } else {
                    absentAllStudentApiCall(true, false, attTopic, attTeachingMethod, attAid,
                            flint, commaSeparatedRollNumber, unitId);
                }

            }
        } else if (FACULTY_FILL_ATTENDANCE_CONFIGURATION == 2) {

            String attTopic = "";
            String commaSeparatedRollNumber = "";

            if (CommonUtil.checkIsEmptyOrNullCommon(tilStudentCommaSeparatedRollNumber.getText().toString())) {
                Toast.makeText(this, "Please enter comma separated roll number", Toast.LENGTH_SHORT).show();
                return;
            } else if (topicListForConfig2 != null && topicListForConfig2.size() > 0 &&
                    spSelectTopicForConfig2.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Please select topic", Toast.LENGTH_SHORT).show();
                return;
            } else if (topicListForConfig2 == null || topicListForConfig2.size() == 0) {
                Toast.makeText(this, "Topic not found!", Toast.LENGTH_SHORT).show();
                return;
            }
            commaSeparatedRollNumber = tilStudentCommaSeparatedRollNumber.getText().toString();
            attTopic = topicListForConfig2.get(spSelectTopicForConfig2.getSelectedItemPosition());

            if (isAttendanceForPresent) {
                presentAllStudentApiCall(true, false, attTopic, topicMethodForConfig2,
                        topicMethodAidForConfig2, "0", commaSeparatedRollNumber, topicUnitIdForConfig2);
            } else {
                absentAllStudentApiCall(true, false, attTopic, topicMethodForConfig2,
                        topicMethodAidForConfig2, "0", commaSeparatedRollNumber, topicUnitIdForConfig2);
            }
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FacultyFillAttendanceActivity.this, FacultyPendingAttendanceActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void getFacultyFillAttendanceConfigurationApiCall(boolean isPdShow, boolean isPdHide) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
        }
        ApiImplementer.insertAllStudentAsPresentApiCallApiImplementer(facultyPendingAttendancePojo.getCollegeId() + "",
                mySharedPreferences.getEmpInstituteId(), new Callback<FacultyFillAttendanceConfigurationPojo>() {
                    @Override
                    public void onResponse(Call<FacultyFillAttendanceConfigurationPojo> call, Response<FacultyFillAttendanceConfigurationPojo> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().getTable() != null && response.body().getTable().size() > 0) {
                            FacultyFillAttendanceConfigurationPojo facultyFillAttendanceConfigurationPojo = response.body();
                            if (facultyFillAttendanceConfigurationPojo.getTable().get(0).getAcAttendanceMethod() == 1) {
                                FACULTY_FILL_ATTENDANCE_CONFIGURATION = 1;
                                llCommonForConfig1.setVisibility(View.VISIBLE);
                                llCommonForConfig2.setVisibility(View.GONE);
                                getFacultyPendingAttendanceTeachingMethodApiCall(false, false);
                                if (facultyPendingAttendancePojo.getLecType().equalsIgnoreCase("1")) {
                                    llFaciltyAidDetails.setVisibility(View.VISIBLE);
                                    llSelectTeachingMethod.setVisibility(View.VISIBLE);
                                } else {
                                    llSelectTeachingMethod.setVisibility(View.GONE);
                                    llFaciltyAidDetails.setVisibility(View.GONE);
                                }
                            } else {
                                FACULTY_FILL_ATTENDANCE_CONFIGURATION = 2;
                                llCommonForConfig1.setVisibility(View.GONE);
                                llCommonForConfig2.setVisibility(View.VISIBLE);
                                getFacultyPendingAttendanceTeachingMethodApiCall(false, false);
                            }

                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(FacultyFillAttendanceActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FacultyFillAttendanceConfigurationPojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(FacultyFillAttendanceActivity.this, "request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void getFacultyPendingAttendanceTeachingMethodApiCall(boolean isPdShow, boolean isPdHide) {
        if (connectionDetector.isConnectingToInternet()) {
            llSelectTeachingMethod.setVisibility(View.GONE);
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
            }
            ApiImplementer.getTeachingMethodApiImplementer(mySharedPreferences.getEmpInstituteId(), new Callback<GetTeachingMethodPojo>() {
                @Override
                public void onResponse(Call<GetTeachingMethodPojo> call, Response<GetTeachingMethodPojo> response) {
                    if (isPdHide) {
                        DialogUtil.hideProgressDialog();
                    }
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayList = (ArrayList<GetTeachingMethodPojo.Table>) response.body().getTable();
                        llSelectTeachingMethod.setVisibility(View.VISIBLE);
                        SelectTeachingMethodGridViewAdapter selectTeachingMethodGridViewAdapter = new SelectTeachingMethodGridViewAdapter(FacultyFillAttendanceActivity.this,
                                getTeachingMethodArrayList);
                        gvTeachingMethodList.setAdapter(selectTeachingMethodGridViewAdapter);
                        isMethodSelected = true;
                        getFacultyAidDetailsApiCall(false, false);
                    } else {
                        isMethodSelected = false;
                        getFacultyAidDetailsApiCall(false, false);
                        llSelectTeachingMethod.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<GetTeachingMethodPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    isMethodSelected = false;
                    llSelectTeachingMethod.setVisibility(View.GONE);
                    Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {

            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void getFacultyAidDetailsApiCall(boolean isPdShow, boolean isPdHide) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
            }
            llFaciltyAidDetails.setVisibility(View.GONE);
            ApiImplementer.getTeachingAidDetailsApiImplementer(mySharedPreferences.getEmpInstituteId(), new Callback<GetTeachingAidDetailsPojo>() {
                @Override
                public void onResponse(Call<GetTeachingAidDetailsPojo> call, Response<GetTeachingAidDetailsPojo> response) {
                    if (isPdHide) {
                        DialogUtil.hideProgressDialog();
                    }
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        teachingAidArrayList = new ArrayList<>();
                        teachingAidArrayList.add(SELECT_TEACHING_AID);
                        teachingAidNameAndIdHashMap = new HashMap<>();
                        llFaciltyAidDetails.setVisibility(View.VISIBLE);

                        ArrayList<GetTeachingAidDetailsPojo.Table> tableArrayList = (ArrayList<GetTeachingAidDetailsPojo.Table>) response.body().getTable();

                        for (int i = 0; i < tableArrayList.size(); i++) {
                            GetTeachingAidDetailsPojo.Table table = tableArrayList.get(i);

                            if (!CommonUtil.checkIsEmptyOrNullCommon(table.getTaId()) &&
                                    !CommonUtil.checkIsEmptyOrNullCommon(table.getTaName())) {
                                teachingAidArrayList.add(table.getTaName().trim());
                                teachingAidNameAndIdHashMap.put(table.getTaName().trim(), table.getTaId() + "");
                            }
                        }
                        spinnerAdapterFacultyAidDetails = new SpinnerSimpleAdapter(FacultyFillAttendanceActivity.this, teachingAidArrayList);
                        spSelectTeachingAid.setAdapter(spinnerAdapterFacultyAidDetails);
                        getFacultyPendingAttendanceUnitApiCall(false, false);
                    } else {
                        getFacultyPendingAttendanceUnitApiCall(false, false);
                        llFaciltyAidDetails.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<GetTeachingAidDetailsPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    llFaciltyAidDetails.setVisibility(View.GONE);
                    Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFacultyPendingAttendanceUnitApiCall(boolean isPdShow, boolean isPdHide) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
            }
            llFacultyPendingAttendaUnit.setVisibility(View.GONE);
            ApiImplementer.getFacultyPendingAttendanceUnitApiImplementer(mySharedPreferences.getEmpId(), facultyPendingAttendancePojo.getDivId() + "",
                    facultyPendingAttendancePojo.getSubId() + "", mySharedPreferences.getEmpYearId(), new Callback<FacultyPendingAttendanceUnitPojo>() {
                        @Override
                        public void onResponse(Call<FacultyPendingAttendanceUnitPojo> call, Response<FacultyPendingAttendanceUnitPojo> response) {
                            if (isPdHide) {
                                DialogUtil.hideProgressDialog();
                            }
                            if (response.isSuccessful() && response.body() != null && response.body().getTable() != null &&
                                    response.body().getTable().size() > 0) {
                                teachingUnitArrayList = new ArrayList<>();
                                teachingUnitArrayList.add(SELECT_TEACHING_UNIT);
                                teachingUnitNameAndIdHashMap = new HashMap<>();
                                ArrayList<FacultyPendingAttendanceUnitPojo.Table> tableArrayList = (ArrayList<FacultyPendingAttendanceUnitPojo.Table>) response.body().getTable();

                                for (int i = 0; i < tableArrayList.size(); i++) {
                                    FacultyPendingAttendanceUnitPojo.Table table = tableArrayList.get(i);
                                    if (!CommonUtil.checkIsEmptyOrNullCommon(table.getUnitId()) &&
                                            !CommonUtil.checkIsEmptyOrNullCommon(table.getUnitName())) {
                                        teachingUnitArrayList.add(table.getUnitName().trim());
                                        teachingUnitNameAndIdHashMap.put(table.getUnitName().trim(),
                                                table.getUnitId() + "");
                                    }
                                }

                                spinnerAdapterFacultyUnit = new SpinnerSimpleAdapter(FacultyFillAttendanceActivity.this, teachingUnitArrayList);
                                spSelectUnit.setAdapter(spinnerAdapterFacultyUnit);
                                llFacultyPendingAttendaUnit.setVisibility(View.VISIBLE);
                                getPendingAttendanceStudentListApiCall(false, false);
                            } else {
                                llFacultyPendingAttendaUnit.setVisibility(View.GONE);
                                getPendingAttendanceStudentListApiCall(false, false);
                            }
                        }

                        @Override
                        public void onFailure(Call<FacultyPendingAttendanceUnitPojo> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            llFacultyPendingAttendaUnit.setVisibility(View.GONE);
                            Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection ", Toast.LENGTH_SHORT).show();
        }
    }

    private void getPendingAttendanceStudentListApiCall(boolean isPdShow, boolean isPdHide) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
            }
            llFillAttendanceByStudentInner.setVisibility(View.GONE);
            ApiImplementer.getPendingAttendanceStudentListApiImplementer(facultyPendingAttendancePojo.getBatchId() + "", facultyPendingAttendancePojo.getDivId() + "",
                    facultyPendingAttendancePojo.getSmId() + "", facultyPendingAttendancePojo.getLecNo() + "", facultyPendingAttendancePojo.getDlDate() + "",
                    mySharedPreferences.getEmpYearId(), facultyPendingAttendancePojo.getSubId() + "", new Callback<StudentDetailsFillAttendancePojo>() {
                        @Override
                        public void onResponse(Call<StudentDetailsFillAttendancePojo> call, Response<StudentDetailsFillAttendancePojo> response) {
                            if (isPdHide) {
                                DialogUtil.hideProgressDialog();
                            }
                            if (response.isSuccessful() && response.body() != null && response.body().getTableBean() != null &&
                                    response.body().getTableBean().size() > 0) {
                                llFillAttendanceByStudentInner.setVisibility(View.VISIBLE);
                                onAbsentPresentStatusChange((ArrayList<StudentDetailsFillAttendancePojo.TableBean>) response.body().getTableBean());

                                studentAbsentPresentList = (ArrayList<StudentDetailsFillAttendancePojo.TableBean>) response.body().getTableBean();
                                studentListFillAttendanceAdapter = new StudentListFillAttendanceAdapter(FacultyFillAttendanceActivity.this, studentAbsentPresentList);
                                rvFillAttendanceStudentList.setAdapter(studentListFillAttendanceAdapter);
                                getLessonPlanningDetailsByFacultyAndTopicWiseApiCall(false, true);
                            } else {
                                getLessonPlanningDetailsByFacultyAndTopicWiseApiCall(false, true);
                                llFillAttendanceByStudentInner.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<StudentDetailsFillAttendancePojo> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            llFillAttendanceByStudentInner.setVisibility(View.GONE);
                            Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    private void presentAllStudentApiCall(boolean isPdShow, boolean isPdHide,
                                          String attTopicName, String attTopicMethod,
                                          String attAid, String attFlint, String commaSepratedRollNo,
                                          String unitId) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
        }
        ApiImplementer.insertAllStudentAsPresentApiCallApiImplementer(facultyPendingAttendancePojo.getCollegeId() + "",
                facultyPendingAttendancePojo.getSmId() + "",
                facultyPendingAttendancePojo.getDivId() + "", facultyPendingAttendancePojo.getCourseId() + "", facultyPendingAttendancePojo.getBatchId() + "",
                mySharedPreferences.getEmpYearId(), facultyPendingAttendancePojo.getLecNo() + "", strDate, "", attTopicName, attTopicMethod, attAid,
                attFlint, commaSepratedRollNo, facultyPendingAttendancePojo.getLecType(), facultyPendingAttendancePojo.getDLVERSIONID() + "",
                mySharedPreferences.getEmpId(), unitId, facultyPendingAttendancePojo.getSubId() + "", "", mySharedPreferences.getEmpId(), "1", new Callback<InsertAllPresentStudentByAlternateMethodPojo>() {
                    @Override
                    public void onResponse(Call<InsertAllPresentStudentByAlternateMethodPojo> call, Response<InsertAllPresentStudentByAlternateMethodPojo> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null && response.body().getTable() != null && response.body().getTable().size() > 0) {
                            InsertAllPresentStudentByAlternateMethodPojo.Table table = response.body().getTable().get(0);
                            if (table.getErrorCode().equalsIgnoreCase("1")) {
                                Toast.makeText(FacultyFillAttendanceActivity.this, "" + table.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                updateStudentDailyLectureWiseAttendanceStatusApiCall(false, true);
                            } else if (table.getErrorCode().contentEquals("2")) {
                                Toast.makeText(FacultyFillAttendanceActivity.this, "" + table.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(FacultyFillAttendanceActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertAllPresentStudentByAlternateMethodPojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void absentAllStudentApiCall(boolean isPdShow, boolean isPdHide,
                                         String attTopicName, String attTopicMethod,
                                         String attAid, String attFlint, String commaSepratedRollNo,
                                         String unitId) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
        }
        ApiImplementer.insertAllStudentAbsentApiCallApiCallApiImplementer(facultyPendingAttendancePojo.getCollegeId() + "", facultyPendingAttendancePojo.getSmId() + "",
                facultyPendingAttendancePojo.getDivId() + "", facultyPendingAttendancePojo.getCourseId() + "", facultyPendingAttendancePojo.getBatchId() + "",
                mySharedPreferences.getEmpYearId(), facultyPendingAttendancePojo.getLecNo() + "", strDate, "", attTopicName, attTopicMethod, attAid,
                attFlint, commaSepratedRollNo, facultyPendingAttendancePojo.getLecType(), facultyPendingAttendancePojo.getDLVERSIONID() + "",
                mySharedPreferences.getEmpId(), unitId, facultyPendingAttendancePojo.getSubId() + "", "", mySharedPreferences.getEmpId(), "1", new Callback<InsertAllAbsentStudentByAlternateMethodPojo>() {
                    @Override
                    public void onResponse(Call<InsertAllAbsentStudentByAlternateMethodPojo> call, Response<InsertAllAbsentStudentByAlternateMethodPojo> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null && response.body().getTable() != null && response.body().getTable().size() > 0) {
                            InsertAllAbsentStudentByAlternateMethodPojo.Table table = response.body().getTable().get(0);

                            if (table.getErrorCode().contentEquals("1")) {
                                Toast.makeText(FacultyFillAttendanceActivity.this, "" + table.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                updateStudentDailyLectureWiseAttendanceStatusApiCall(false, true);
                            } else if (table.getErrorCode().contentEquals("2")) {
                                Toast.makeText(FacultyFillAttendanceActivity.this, "" + table.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(FacultyFillAttendanceActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertAllAbsentStudentByAlternateMethodPojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    String topicNameForConfig2 = "";
    String topicMethodForConfig2 = "0";
    String topicMethodAidForConfig2 = "0";
    String topicUnitIdForConfig2 = "0";

    private void getMethodFromApi(boolean isPdShow, boolean isPdHide, String topicId) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
        }

        ApiImplementer.getMethodFromApiApiImplementer(mySharedPreferences.getEmpId(),
                facultyPendingAttendancePojo.getDivId() + "", facultyPendingAttendancePojo.getSubId() + "", mySharedPreferences.getEmpYearId(),
                topicId, new Callback<GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo>() {
                    @Override
                    public void onResponse(Call<GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo> call, Response<GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getTable() != null &&
                                    response.body().getTable().size() > 0) {
                                GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo.Table table = response.body().getTable().get(0);
                                topicNameForConfig2 = table.getTopicName() + "";
                                topicMethodForConfig2 = table.getTopicMethod() + "";
                                topicMethodAidForConfig2 = table.getTopicAid() + "";
                                topicUnitIdForConfig2 = table.getUnitId() + "";
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(FacultyFillAttendanceActivity.this, "Something went wrong,Please try again later", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo> call, Throwable t) {

                    }
                });

    }

    private void getLessonPlanningDetailsByFacultyAndTopicWiseApiCall(boolean isPdShow, boolean isPdHide) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
        }
        ApiImplementer.getLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyApiImplementer(mySharedPreferences.getEmpId(),
                facultyPendingAttendancePojo.getDivId() + "", facultyPendingAttendancePojo.getSubId() + "", mySharedPreferences.getEmpYearId(), new Callback<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo>() {
                    @Override
                    public void onResponse(Call<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo> call, Response<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getTable() != null &&
                                    response.body().getTable().size() > 0) {
                                ArrayList<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo.Table> tableArrayList = (ArrayList<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo.Table>) response.body().getTable();
                                topicListForConfig2 = new ArrayList<>();
                                topicListForConfig2.add(SELECT_TOPIC);
                                topicNameAndIdListForConfig2 = new HashMap<>();
                                for (int i = 0; i < tableArrayList.size(); i++) {
                                    GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo.Table table = tableArrayList.get(i);
                                    if (!CommonUtil.checkIsEmptyOrNullCommon(table.getTopicId()) &&
                                            !CommonUtil.checkIsEmptyOrNullCommon(table.getTopicName())) {
                                        topicListForConfig2.add(table.getTopicName().trim());
                                        topicNameAndIdListForConfig2.put(table.getTopicName().trim(),
                                                table.getTopicId() + "");
                                    }
                                }

                                spinnerAdapterFacultyTopicForConfig2 = new SpinnerSimpleAdapter(FacultyFillAttendanceActivity.this, topicListForConfig2);
                                spSelectTopicForConfig2.setAdapter(spinnerAdapterFacultyTopicForConfig2);
                                llTopicUnitForConfig2.setVisibility(View.VISIBLE);
                            } else {
                                llTopicUnitForConfig2.setVisibility(View.GONE);
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(FacultyFillAttendanceActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo> call, Throwable t) {
                        llTopicUnitForConfig2.setVisibility(View.GONE);
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateStudentDailyLectureWiseAttendanceStatusApiCall(boolean isPdShow, boolean isPdHide) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
        }
        ApiImplementer.updateDailyLectureWiseAttendanceApiImplementer(facultyPendingAttendancePojo.getCourseId() + "",
                facultyPendingAttendancePojo.getSmId() + "",
                facultyPendingAttendancePojo.getDivId() + "", facultyPendingAttendancePojo.getBatchId() + "",
                facultyPendingAttendancePojo.getDlDate() + "",
                facultyPendingAttendancePojo.getLecType() + "", mySharedPreferences.getEmpId(), facultyPendingAttendancePojo.getLecNo() + "",
                facultyPendingAttendancePojo.getSubId() + "", facultyPendingAttendancePojo.getDLRECOURSEID() + "",
                facultyPendingAttendancePojo.getDLVERSIONID() + "",
                mySharedPreferences.getEmpId(), "1", new Callback<ArrayList<UpdateDailyLectureWiseAttendanceStatusPojo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<UpdateDailyLectureWiseAttendanceStatusPojo>> call, Response<ArrayList<UpdateDailyLectureWiseAttendanceStatusPojo>> response) {
                        try {
//    if (isPdHide) {
                            DialogUtil.hideProgressDialog();
//                        }

                            onBackPressed();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        //

//                        if (response.isSuccessful() && response.body() != null && response.body().getTable() != null && response.body().getTable().size() > 0) {
////                            if (response.body().getTable().get(0).getErrorCode().equalsIgnoreCase("1")) {
////                                onBackPressed();
////                            } else {
////                                Toast.makeText(FacultyFillAttendanceActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
////                            }
//                            onBackPressed();
//                        } else {
//                            DialogUtil.hideProgressDialog();
//                            Toast.makeText(FacultyFillAttendanceActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
//                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<UpdateDailyLectureWiseAttendanceStatusPojo>> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void insertClassWiseAttendanceApiCall(boolean isPdShow, boolean isPdHide, String attStatus,
                                                  String attTopic, String attTeachingMethod, String attAid,
                                                  String flint, String attHomeWork, String unitId,
                                                  String presentId, String absentId) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(FacultyFillAttendanceActivity.this, "");
        }
        ApiImplementer.insertClassWiseAttendanceApiImplementer(facultyPendingAttendancePojo.getCollegeId() + "", presentId, absentId,
                facultyPendingAttendancePojo.getSmId() + "" + "", facultyPendingAttendancePojo.getDivId() + "",
                facultyPendingAttendancePojo.getBatchId() + "",
                facultyPendingAttendancePojo.getLecNo() + "", strDate, "",
                attStatus, mySharedPreferences.getEmpYearId(),
                attTopic, attTeachingMethod, attAid, flint, facultyPendingAttendancePojo.getLecType(), facultyPendingAttendancePojo.getDLVERSIONID() + "",
                "By Selection Method Application", mySharedPreferences.getEmpId(), unitId, attHomeWork, mySharedPreferences.getEmpId(), "1", new Callback<InsertClassWiseAttendancePojo>() {
                    @Override
                    public void onResponse(Call<InsertClassWiseAttendancePojo> call, Response<InsertClassWiseAttendancePojo> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null) {
                            InsertClassWiseAttendancePojo.Table table = response.body().getTable().get(0);
                            if (table.getErrorCode().equalsIgnoreCase("1")) {
                                Toast.makeText(FacultyFillAttendanceActivity.this, "" + table.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                updateStudentDailyLectureWiseAttendanceStatusApiCall(false, true);
                            } else if (table.getErrorCode().equalsIgnoreCase("2")) {
                                Toast.makeText(FacultyFillAttendanceActivity.this, "" + table.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(FacultyFillAttendanceActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertClassWiseAttendancePojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(FacultyFillAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClickTeachingMethod(ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayListNew) {
        selectedMethodIds = "";
        this.getTeachingMethodArrayListNew = getTeachingMethodArrayListNew;
        for (int i = 0; i < getTeachingMethodArrayListNew.size(); i++) {
            if (getTeachingMethodArrayListNew.get(i).isSelected()) {
                selectedMethodIds = selectedMethodIds + "," + getTeachingMethodArrayListNew.get(i).getTmId() + "";
            }
        }
        selectedMethodIds = selectedMethodIds.replaceFirst(",", "");
        if (CommonUtil.checkIsEmptyOrNullCommon(selectedMethodIds)) {
            isMethodSelected = false;
        } else {
            isMethodSelected = true;
        }
    }


    private String selectedPresentIDList = "";
    private String selectedAbsentIDList = "";
    private ArrayList<StudentDetailsFillAttendancePojo.TableBean> selectedStudentAbsentOrPresentArrayListNew;

    @Override
    public void onAbsentPresentStatusChange(ArrayList<StudentDetailsFillAttendancePojo.TableBean> tableBeanArrayList) {
        selectedPresentIDList = "";
        selectedAbsentIDList = "";
        selectedStudentAbsentOrPresentArrayListNew = tableBeanArrayList;
        for (int i = 0; i < tableBeanArrayList.size(); i++) {
            if (tableBeanArrayList.get(i).isChecked()) {
                selectedPresentIDList = selectedPresentIDList + "," + selectedStudentAbsentOrPresentArrayListNew.get(i).getStudId() + "";
            } else {
                selectedAbsentIDList = selectedAbsentIDList + "," + selectedStudentAbsentOrPresentArrayListNew.get(i).getStudId() + "";
            }
        }
        selectedPresentIDList = selectedPresentIDList.replaceFirst(",", "");
        selectedAbsentIDList = selectedAbsentIDList.replaceFirst(",", "");
    }
}