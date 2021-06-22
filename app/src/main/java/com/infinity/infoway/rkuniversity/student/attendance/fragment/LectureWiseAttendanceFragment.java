package com.infinity.infoway.rkuniversity.student.attendance.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.attendance.activity.StudentAttendanceActivity;
import com.infinity.infoway.rkuniversity.student.attendance.pojo.StudentLectureWiseAttendancePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LectureWiseAttendanceFragment extends Fragment implements View.OnClickListener {

    private StudentAttendanceActivity attendanceActivity;

    AppCompatEditText edMonthPicker;
    LinearLayout llAttendanceHeader, llAttendanceValue;
    MonthPickerDialog.Builder builder;
    Calendar calendar;
    int currentMonth;
    int currentYear;
    MaterialCardView cvStudentAttendance;
    LinearLayout llDayWiseProgressbar;
    MySharedPreferences mySharedPreferences;
    LinearLayout llNoDataFoundLectureWiseattendance;
    ConnectionDetector connectionDetector;


    public LectureWiseAttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.attendanceActivity = (StudentAttendanceActivity) context;
        mySharedPreferences = new MySharedPreferences(attendanceActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture_wise_attendance, container, false);
        initView(view);
        getStudentAttendance(currentYear + "", (currentMonth + 1) + "");
        return view;
    }

    private void initView(View view) {
        connectionDetector = new ConnectionDetector(attendanceActivity);
        calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        llDayWiseProgressbar = view.findViewById(R.id.llDayWiseProgressbar);
        llAttendanceHeader = view.findViewById(R.id.llAttendanceHeader);
        llAttendanceValue = view.findViewById(R.id.llAttendanceValue);

        llNoDataFoundLectureWiseattendance = view.findViewById(R.id.llNoDataFoundLectureWiseattendance);

        cvStudentAttendance = view.findViewById(R.id.cvStudentAttendance);

        edMonthPicker = view.findViewById(R.id.edMonthPicker);
        edMonthPicker.setOnClickListener(this);
        edMonthPicker.setText(CommonUtil.getMonthNameFromNumber(currentMonth) + " - " + currentYear);

        builder = new MonthPickerDialog.Builder(attendanceActivity, new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                edMonthPicker.setText(CommonUtil.getMonthNameFromNumber(selectedMonth) + " - " + selectedYear);
                getStudentAttendance(selectedYear + "", (selectedMonth + 1) + "");
            }
        }, currentYear, currentMonth);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.edMonthPicker) {

            builder.setActivatedMonth(currentMonth)
                    .setMinYear(2010)
                    .setActivatedYear(currentYear)
                    .setMaxYear(currentYear)
                    .setMinMonth(Calendar.JANUARY)
                    .setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
                    .build()
                    .show();

        } else if (view instanceof TextViewMediumFont) {
            TextViewMediumFont textViewMediumFont = (TextViewMediumFont) view;
            String dataArray[] = textViewMediumFont.getTag().toString().split("--/");
            String date = dataArray[0];
            String time = dataArray[1];
            String classOrLab = dataArray[2];
            String subjectName = dataArray[3];
            String profe = dataArray[4];
            String absentPresentStatus = dataArray[5];


            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(date, time, classOrLab, subjectName, profe, absentPresentStatus);
            bottomSheetDialog.show(attendanceActivity.getSupportFragmentManager(), "test");

        }
    }


    private void getStudentAttendance(String selectedYear, String selectedMonth) {
        if (connectionDetector.isConnectingToInternet()) {
            llAttendanceHeader.removeAllViewsInLayout();
            llAttendanceValue.removeAllViewsInLayout();
            cvStudentAttendance.setVisibility(View.GONE);
            llDayWiseProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundLectureWiseattendance.setVisibility(View.GONE);
            HashMap<String, String> params = new HashMap<>();
            params.put("year", selectedYear);
            params.put("month", selectedMonth);
            params.put("stud_id", mySharedPreferences.getStudentId());
            params.put("course_id", mySharedPreferences.getCourseId());
            params.put("div_id", mySharedPreferences.getSwdDivisionId());
            params.put("shift_id", mySharedPreferences.getShiftId());
            params.put("batch_id", mySharedPreferences.getSwdBatchId());
            params.put("year_id", mySharedPreferences.getSwdYearId());
            ApiImplementer.getStudentLectureWiseAttendanceApiImplementer(params, new Callback<ArrayList<StudentLectureWiseAttendancePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<StudentLectureWiseAttendancePojo>> call, Response<ArrayList<StudentLectureWiseAttendancePojo>> response) {
                    try {
                        if (response.isSuccessful() && response != null && response.body().size() > 0) {
                            cvStudentAttendance.setVisibility(View.VISIBLE);
                            llDayWiseProgressbar.setVisibility(View.GONE);
                            llNoDataFoundLectureWiseattendance.setVisibility(View.GONE);
                            int cellSpacing = convertDpToPxe(12);
                            ArrayList<StudentLectureWiseAttendancePojo> studentLectureWiseAttendancePojoArrayList = response.body();
                            generateHeader(studentLectureWiseAttendancePojoArrayList, cellSpacing);
                            generateDynamicAttendance(studentLectureWiseAttendancePojoArrayList, cellSpacing);
                        } else {
                            llDayWiseProgressbar.setVisibility(View.GONE);
                            cvStudentAttendance.setVisibility(View.GONE);
                            llNoDataFoundLectureWiseattendance.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<StudentLectureWiseAttendancePojo>> call, Throwable t) {
                    llDayWiseProgressbar.setVisibility(View.GONE);
                    cvStudentAttendance.setVisibility(View.GONE);
                    llNoDataFoundLectureWiseattendance.setVisibility(View.VISIBLE);
                    Toast.makeText(attendanceActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(attendanceActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
            attendanceActivity.finish();
        }
    }

    private void generateHeader
            (ArrayList<StudentLectureWiseAttendancePojo> studentLectureWiseAttendancePojoArrayList, int cellDp) {

        int headerSize = studentLectureWiseAttendancePojoArrayList.get(0).getAllLecture().size();

        float attendanceHeaderWeightSum = studentLectureWiseAttendancePojoArrayList.get(0).getAllLecture().size() + 1.2f;

        llAttendanceHeader.setWeightSum(attendanceHeaderWeightSum);


        LinearLayout.LayoutParams layoutParamCellDateForHeader = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.2f);
        TextViewRegularFont textViewCellDateForHeader = new TextViewRegularFont(attendanceActivity);
        textViewCellDateForHeader.setLayoutParams(layoutParamCellDateForHeader);
        textViewCellDateForHeader.setGravity(Gravity.CENTER);
        textViewCellDateForHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        textViewCellDateForHeader.setTextColor(getResources().getColor(R.color.white));
        textViewCellDateForHeader.setPadding(cellDp, cellDp, cellDp, cellDp);

        textViewCellDateForHeader.setText("Date");
        textViewCellDateForHeader.setBackground(ContextCompat.getDrawable(attendanceActivity, R.drawable.header_first_cell_shape));

        llAttendanceHeader.addView(textViewCellDateForHeader);


        for (int j = 0; j < headerSize; j++) {

            LinearLayout.LayoutParams layoutParamForOtherCell = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            TextViewRegularFont textViewForOtherCell = new TextViewRegularFont(attendanceActivity);
            textViewForOtherCell.setLayoutParams(layoutParamForOtherCell);
            textViewForOtherCell.setGravity(Gravity.CENTER);
            textViewForOtherCell.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textViewForOtherCell.setTextColor(getResources().getColor(R.color.white));
            textViewForOtherCell.setPadding(cellDp, cellDp, cellDp, cellDp);

            if (j == headerSize - 1) {
                textViewForOtherCell.setText("Slot " + (j + 1));
                textViewForOtherCell.setBackground(ContextCompat.getDrawable(attendanceActivity, R.drawable.header_last_cell_shape));
            } else {
                textViewForOtherCell.setText("Slot " + (j + 1));
                textViewForOtherCell.setBackground(ContextCompat.getDrawable(attendanceActivity, R.drawable.header_middle_cell_shape));
            }

            View view = new View(attendanceActivity);
            LinearLayout.LayoutParams layoutParamView = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(layoutParamView);
            view.setBackgroundColor(getResources().getColor(R.color.lightgrey));

            llAttendanceHeader.addView(view);
            llAttendanceHeader.addView(textViewForOtherCell);
        }
    }

    private void generateDynamicAttendance
            (ArrayList<StudentLectureWiseAttendancePojo> studentLectureWiseAttendancePojoArrayList, int cellDp) {
        //For Attendance Rows
        for (int i = 0; i < studentLectureWiseAttendancePojoArrayList.size(); i++) {


            List<StudentLectureWiseAttendancePojo.AllLecture> allLectureList = studentLectureWiseAttendancePojoArrayList.get(i).getAllLecture();


            LinearLayout.LayoutParams layoutParamsRow = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsRow.gravity = Gravity.CENTER_VERTICAL;
            LinearLayout linearLayoutRow = new LinearLayout(attendanceActivity);
            linearLayoutRow.setLayoutParams(layoutParamsRow);
            linearLayoutRow.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutRow.setWeightSum(allLectureList.size() + 1.2f);

            LinearLayout.LayoutParams layoutParamsForDateCellLayout = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.2f);
            layoutParamsForDateCellLayout.gravity = Gravity.CENTER;
            LinearLayout linearLayoutForDateCellLayout = new LinearLayout(attendanceActivity);
            linearLayoutForDateCellLayout.setLayoutParams(layoutParamsForDateCellLayout);
            linearLayoutForDateCellLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayoutForDateCellLayout.setPadding(cellDp, cellDp / 2, cellDp, cellDp / 2);


            String lecureDate = studentLectureWiseAttendancePojoArrayList.get(i).getDate();
            String lecureDatesArray[] = studentLectureWiseAttendancePojoArrayList.get(i).getDate().split("/");

            String lectureDay = lecureDatesArray[0] + "";
            String lectureMonth = CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(lecureDatesArray[1]));
            String lectureWeekDayName = CommonUtil.getWeekDayName(Integer.parseInt(lecureDatesArray[1]), Integer.parseInt(lecureDatesArray[0]), lecureDatesArray[0] + "-" + lecureDatesArray[1] + "-" + lecureDatesArray[2]);

            //For Date Column
            LinearLayout.LayoutParams layoutParamCellDate = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewMediumFont textViewCellDate = new TextViewMediumFont(attendanceActivity);
            textViewCellDate.setLayoutParams(layoutParamCellDate);
            textViewCellDate.setGravity(Gravity.CENTER);
            textViewCellDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            textViewCellDate.setTextColor(getResources().getColor(R.color.attendance_unselected_tab_color));
            textViewCellDate.setText(lectureDay + "-" + lectureMonth);


            LinearLayout.LayoutParams layoutParamCellWeekDay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCellWeekDay = new TextViewRegularFont(attendanceActivity);
            textViewCellWeekDay.setLayoutParams(layoutParamCellWeekDay);
            textViewCellWeekDay.setGravity(Gravity.CENTER);
            textViewCellWeekDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            textViewCellWeekDay.setTextColor(getResources().getColor(R.color.attendance_unselected_tab_color));
            textViewCellWeekDay.setText(lectureWeekDayName);

            linearLayoutForDateCellLayout.addView(textViewCellDate);
            linearLayoutForDateCellLayout.addView(textViewCellWeekDay);


            linearLayoutRow.addView(linearLayoutForDateCellLayout);

            //For No of slot Column
            for (int k = 0; k < allLectureList.size(); k++) {

                String att_status = allLectureList.get(k).getAttStatus();
                String lec_status = allLectureList.get(k).getLecStatus();
                String lectureType = allLectureList.get(k).getLectType();
                String lectureTime = allLectureList.get(k).getLectTime();
                String subjectName = allLectureList.get(k).getSubjectName();
                String facultyName = allLectureList.get(k).getFacultyName();


                if (lec_status != null && !lec_status.isEmpty() && Integer.parseInt(lec_status) > 1) {//this logic is for when more than one lecture is merged

                    int mergerCellWeight = Integer.parseInt(lec_status);

                    LinearLayout.LayoutParams layoutParamForMergedCell = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, mergerCellWeight);
                    TextViewMediumFont textViewForMergedCell = new TextViewMediumFont(attendanceActivity);
                    if (att_status != null && lec_status != null &&
                            !att_status.isEmpty() && !lec_status.isEmpty()) {
                        textViewForMergedCell.setOnClickListener(this);
                    }

                    String status = att_status.isEmpty() ? "-" : att_status;
                    textViewForMergedCell.setTag(lecureDate + "--/" + lectureTime + "--/" + lectureType + "--/" + subjectName + "--/" + facultyName + "--/" + status);
                    textViewForMergedCell.setLayoutParams(layoutParamForMergedCell);
                    textViewForMergedCell.setGravity(Gravity.CENTER);
                    textViewForMergedCell.setTextColor(getResources().getColor(R.color.green));
                    textViewForMergedCell.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    textViewForMergedCell.setText(att_status);
                    textViewForMergedCell.setPadding(cellDp, cellDp / 2, cellDp, cellDp / 2);
                    setAttendanceStatusColor(status, textViewForMergedCell);
                    View viewForMergedCell = new View(attendanceActivity);
                    LinearLayout.LayoutParams layoutParamViewForSingleCell = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
                    viewForMergedCell.setLayoutParams(layoutParamViewForSingleCell);
                    viewForMergedCell.setBackgroundColor(getResources().getColor(R.color.lightgrey));

                    linearLayoutRow.addView(viewForMergedCell);
                    linearLayoutRow.addView(textViewForMergedCell);
                    k = k + (mergerCellWeight - 1);

                } else {//this logic for when only single lecture
                    LinearLayout.LayoutParams layoutParamForSingleCell = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                    TextViewMediumFont textViewForSingleCell = new TextViewMediumFont(attendanceActivity);
                    if (att_status != null && lec_status != null &&
                            !att_status.isEmpty() && !lec_status.isEmpty()) {
                        textViewForSingleCell.setOnClickListener(this);
                    }
                    String status = att_status.isEmpty() ? "-" : att_status;
                    textViewForSingleCell.setTag(lecureDate + "--/" + lectureTime + "--/" + lectureType + "--/" + subjectName + "--/" + facultyName + "--/" + status);
                    textViewForSingleCell.setLayoutParams(layoutParamForSingleCell);
                    textViewForSingleCell.setGravity(Gravity.CENTER);
                    textViewForSingleCell.setTextColor(getResources().getColor(R.color.green));
                    textViewForSingleCell.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    textViewForSingleCell.setText(att_status);
                    setAttendanceStatusColor(status, textViewForSingleCell);
                    textViewForSingleCell.setPadding(cellDp, cellDp / 2, cellDp, cellDp / 2);

                    View viewForSingleCell = new View(attendanceActivity);
                    LinearLayout.LayoutParams layoutParamViewForSingleCell = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
                    viewForSingleCell.setLayoutParams(layoutParamViewForSingleCell);
                    viewForSingleCell.setBackgroundColor(getResources().getColor(R.color.lightgrey));


                    linearLayoutRow.addView(viewForSingleCell);
                    linearLayoutRow.addView(textViewForSingleCell);

                }

            }

            View horizontalLineView = new View(attendanceActivity);
            LinearLayout.LayoutParams layoutParamHoriontalLineView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
            horizontalLineView.setLayoutParams(layoutParamHoriontalLineView);
            horizontalLineView.setBackgroundColor(getResources().getColor(R.color.lightgrey));

            llAttendanceValue.addView(linearLayoutRow);
            llAttendanceValue.addView(horizontalLineView);
        }
    }


    private void setAttendanceStatusColor(String status, TextViewMediumFont textViewMediumFont) {
        switch (status) {

            case "P":
                textViewMediumFont.setTextColor(getResources().getColor(R.color.status_present_color));
                break;
            case "A":
                textViewMediumFont.setTextColor(getResources().getColor(R.color.status_absent_color));
                break;
            case "R":
                textViewMediumFont.setTextColor(getResources().getColor(R.color.status_remaining_color));
                break;
            case "H":
                textViewMediumFont.setTextColor(getResources().getColor(R.color.status_holiday_color));
                break;
            case "S":
                textViewMediumFont.setTextColor(getResources().getColor(R.color.status_period_suspend_color));
                break;
            default:
                textViewMediumFont.setTextColor(getResources().getColor(R.color.status_present_color));
                break;
        }
    }

    private int convertDpToPxe(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}