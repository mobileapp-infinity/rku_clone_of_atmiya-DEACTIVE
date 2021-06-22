package com.infinity.infoway.rkuniversity.student.exam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadExaminationSchedulePojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadHallTicketExaminationSchedulePojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.ExaminationScheduleDetailsPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.ExaminationScheduleProgramWiseTimetablePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.GeneratePDFFileFromBase64String;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExaminationScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivCloseExaminationSchedule;
    ConnectionDetector connectionDetector;
    //    RecyclerView rvExaminationScheduleStudent;
    FrameLayout llStudentExaminationSchedule;
    LinearLayout llExaminationScheduleProgressbar, llNoDataFoundExaminationSchedule;
    String yearId = "";
    String semId = "";
    String repeaterStatus = "";

    ArrayList<String> examNameArrayList;
    SearchableSpinner spExaminationScheduleName;
    LinearLayout llExaminationScheduleList;
    ArrayList<String> examId;
    MaterialCardView cvSelectExam;
    ExtendedFloatingActionButton efabDownloadExaminationSchedule;
    ExtendedFloatingActionButton efabDownloadHallTicketExaminationSchedule;
    //    ProgressDialog progressDialog;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_schedule);
        initView();
        getExaminationScheduleDetailsListForDropdownApiCall();

        spExaminationScheduleName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (++check > 1) {
                    String examIdArray[] = examId.get(position).split("_");
                    yearId = examIdArray[0];
                    semId = examIdArray[1];
                    repeaterStatus = examIdArray[2];
                    getExaminationScheduleWiseProgramTimetableApiCall(semId, yearId, repeaterStatus);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(ExaminationScheduleActivity.this);
        connectionDetector = new ConnectionDetector(ExaminationScheduleActivity.this);
        ivCloseExaminationSchedule = findViewById(R.id.ivCloseExaminationSchedule);
        ivCloseExaminationSchedule.setOnClickListener(this);
        cvSelectExam = findViewById(R.id.cvSelectExam);
        efabDownloadExaminationSchedule = findViewById(R.id.efabDownloadExaminationSchedule);
        efabDownloadExaminationSchedule.setOnClickListener(this);
        efabDownloadHallTicketExaminationSchedule = findViewById(R.id.efabDownloadHallTicketExaminationSchedule);
        efabDownloadHallTicketExaminationSchedule.setOnClickListener(this);
//        rvExaminationScheduleStudent = findViewById(R.id.rvExaminationScheduleStudent);
        llStudentExaminationSchedule = findViewById(R.id.llStudentExaminationSchedule);
        llExaminationScheduleProgressbar = findViewById(R.id.llExaminationScheduleProgressbar);
        llNoDataFoundExaminationSchedule = findViewById(R.id.llNoDataFoundExaminationSchedule);
        spExaminationScheduleName = findViewById(R.id.spExaminationScheduleName);
        llExaminationScheduleList = findViewById(R.id.llExaminationScheduleList);
//        progressDialog = new ProgressDialog(ExaminationScheduleActivity.this);
//        progressDialog.setMessage("Please wait....");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void getExaminationScheduleDetailsListForDropdownApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            cvSelectExam.setVisibility(View.GONE);
            ApiImplementer.examinationScheduleDetailsApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getImExamDbName(), new Callback<ExaminationScheduleDetailsPojo>() {
                @Override
                public void onResponse(Call<ExaminationScheduleDetailsPojo> call, Response<ExaminationScheduleDetailsPojo> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        cvSelectExam.setVisibility(View.VISIBLE);
                        examNameArrayList = new ArrayList<>();
                        examId = new ArrayList<>();
                        String[] examIdArray = response.body().getTable().get(0).getExamId().split("_");
                        yearId = examIdArray[0];
                        semId = examIdArray[1];
                        repeaterStatus = examIdArray[2];
                        for (int i = 0; i < response.body().getTable().size(); i++) {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(response.body().getTable().get(i).getExamName()) &&
                                    !CommonUtil.checkIsEmptyOrNullCommon(response.body().getTable().get(i).getExamId())) {
                                examNameArrayList.add(response.body().getTable().get(i).getExamName());
                                examId.add(response.body().getTable().get(i).getExamId());
                            }
                        }
                        ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>
                                (ExaminationScheduleActivity.this, R.layout.layout_dropdown_row,
                                        examNameArrayList);
                        bankAdapter.setDropDownViewResource(R.layout.layout_dropdown_row);
                        spExaminationScheduleName.setTitle("Select Examination Schedule");
                        spExaminationScheduleName.setAdapter(bankAdapter);
                        getExaminationScheduleWiseProgramTimetableApiCall(semId, yearId, repeaterStatus);
                    } else {
                        cvSelectExam.setVisibility(View.GONE);
                        llStudentExaminationSchedule.setVisibility(View.GONE);
                        llExaminationScheduleProgressbar.setVisibility(View.GONE);
                        llNoDataFoundExaminationSchedule.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ExaminationScheduleDetailsPojo> call, Throwable t) {
                    cvSelectExam.setVisibility(View.GONE);
                    llStudentExaminationSchedule.setVisibility(View.GONE);
                    llExaminationScheduleProgressbar.setVisibility(View.GONE);
                    llNoDataFoundExaminationSchedule.setVisibility(View.VISIBLE);
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getExaminationScheduleWiseProgramTimetableApiCall(String semId, String yearId, String repeaterStatus) {
        llExaminationScheduleList.removeAllViewsInLayout();
        if (connectionDetector.isConnectingToInternet()) {
            llStudentExaminationSchedule.setVisibility(View.GONE);
            llExaminationScheduleProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundExaminationSchedule.setVisibility(View.GONE);
            ApiImplementer.examinationScheduleProgramWiseTimetableApiImplementer(semId, yearId,
                    mySharedPreferences.getStudentId(), repeaterStatus, mySharedPreferences.getImExamDbName(), new Callback<ExaminationScheduleProgramWiseTimetablePojo>() {
                        @Override
                        public void onResponse(Call<ExaminationScheduleProgramWiseTimetablePojo> call, Response<ExaminationScheduleProgramWiseTimetablePojo> response) {
                            try {
                                llExaminationScheduleProgressbar.setVisibility(View.GONE);
                                if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                                    llStudentExaminationSchedule.setVisibility(View.VISIBLE);
                                    generateDynamicExaminationScheduleListRow((ArrayList<ExaminationScheduleProgramWiseTimetablePojo.Table>) response.body().getTable());
                                } else {
                                    llNoDataFoundExaminationSchedule.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ExaminationScheduleProgramWiseTimetablePojo> call, Throwable t) {
                            llStudentExaminationSchedule.setVisibility(View.GONE);
                            llExaminationScheduleProgressbar.setVisibility(View.GONE);
                            llNoDataFoundExaminationSchedule.setVisibility(View.VISIBLE);
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void generateDynamicExaminationScheduleListRow(ArrayList<ExaminationScheduleProgramWiseTimetablePojo.Table> examinationScheduleTimetableList) {

        int leftPadding = CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 2);
        int rightPadding = CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 2);
        int topPadding = CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 10);
        int bottomPadding = CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 10);
        int widthForView = CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 1);
//        llExaminationScheduleList.removeAllViewsInLayout();

        for (int i = 0; i < examinationScheduleTimetableList.size(); i++) {
            LinearLayout.LayoutParams examinationScheduleRowParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            examinationScheduleRowParam.gravity = Gravity.CENTER_VERTICAL;
            LinearLayout linearLayoutRow = new LinearLayout(ExaminationScheduleActivity.this);
            linearLayoutRow.setLayoutParams(examinationScheduleRowParam);
            linearLayoutRow.setOrientation(LinearLayout.HORIZONTAL);

//            linearLayoutRow.removeAllViewsInLayout();

//            if(linearLayoutRow.getParent() != null) {
//                ((ViewGroup)linearLayoutRow.getParent()).removeView(linearLayoutRow); // <- fix
//            }
//            linearLayoutRow.addView(linearLayoutRow);

            if (i % 2 == 0) {
                linearLayoutRow.setBackground(ContextCompat.getDrawable(ExaminationScheduleActivity.this, R.color.white));
            } else {
                linearLayoutRow.setBackground(ContextCompat.getDrawable(ExaminationScheduleActivity.this, R.color.exam_module_row_color));
            }

            LinearLayout.LayoutParams layoutParamForSrNo = new LinearLayout.LayoutParams(CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 60), LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCellSrNo = new TextViewRegularFont(ExaminationScheduleActivity.this);
            textViewCellSrNo.setLayoutParams(layoutParamForSrNo);
            textViewCellSrNo.setGravity(Gravity.CENTER);
            textViewCellSrNo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textViewCellSrNo.setTextColor(getResources().getColor(R.color.colorPrimary));
            textViewCellSrNo.setText((i + 1) + "");
            textViewCellSrNo.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

            View view = new View(ExaminationScheduleActivity.this);
            LinearLayout.LayoutParams layoutParamView = new LinearLayout.LayoutParams(widthForView, LinearLayout.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(layoutParamView);
            view.setBackgroundColor(getResources().getColor(R.color.white));


            LinearLayout.LayoutParams layoutParamForCourseCode = new LinearLayout.LayoutParams(CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 120), LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCellCourseCode = new TextViewRegularFont(ExaminationScheduleActivity.this);
            textViewCellCourseCode.setLayoutParams(layoutParamForCourseCode);
            textViewCellCourseCode.setGravity(Gravity.CENTER);
            textViewCellCourseCode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textViewCellCourseCode.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (!CommonUtil.checkIsEmptyOrNullCommon(examinationScheduleTimetableList.get(i).getSubjectCode())) {
                textViewCellCourseCode.setText(examinationScheduleTimetableList.get(i).getSubjectCode() + "");
            } else {
                textViewCellCourseCode.setText("-");
            }
            textViewCellCourseCode.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);


            View view1 = new View(ExaminationScheduleActivity.this);
            LinearLayout.LayoutParams layoutParamView1 = new LinearLayout.LayoutParams(widthForView, LinearLayout.LayoutParams.MATCH_PARENT);
            view1.setLayoutParams(layoutParamView1);
            view1.setBackgroundColor(getResources().getColor(R.color.white));

            LinearLayout.LayoutParams layoutParamForCourseName = new LinearLayout.LayoutParams(CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 210), LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCellCourseName = new TextViewRegularFont(ExaminationScheduleActivity.this);
            textViewCellCourseName.setLayoutParams(layoutParamForCourseName);
            textViewCellCourseName.setGravity(Gravity.CENTER);
            textViewCellCourseName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textViewCellCourseName.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (!CommonUtil.checkIsEmptyOrNullCommon(examinationScheduleTimetableList.get(i).getSubjectPaper())) {
                textViewCellCourseName.setText(examinationScheduleTimetableList.get(i).getSubjectPaper() + "");
            } else {
                textViewCellCourseName.setText("-");
            }
            textViewCellCourseName.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);


            View view2 = new View(ExaminationScheduleActivity.this);
            LinearLayout.LayoutParams layoutParamView2 = new LinearLayout.LayoutParams(widthForView, LinearLayout.LayoutParams.MATCH_PARENT);
            view2.setLayoutParams(layoutParamView2);
            view2.setBackgroundColor(getResources().getColor(R.color.white));

            LinearLayout.LayoutParams layoutParamForDate = new LinearLayout.LayoutParams(CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 120), LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCellDate = new TextViewRegularFont(ExaminationScheduleActivity.this);
            textViewCellDate.setLayoutParams(layoutParamForDate);
            textViewCellDate.setGravity(Gravity.CENTER);
            textViewCellDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textViewCellDate.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (!CommonUtil.checkIsEmptyOrNullCommon(examinationScheduleTimetableList.get(i).getTtDate())) {
                textViewCellDate.setText(examinationScheduleTimetableList.get(i).getTtDate() + "");
            } else {
                textViewCellDate.setText("-");
            }
            textViewCellDate.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);


            View view3 = new View(ExaminationScheduleActivity.this);
            LinearLayout.LayoutParams layoutParamView3 = new LinearLayout.LayoutParams(widthForView, LinearLayout.LayoutParams.MATCH_PARENT);
            view3.setLayoutParams(layoutParamView3);
            view3.setBackgroundColor(getResources().getColor(R.color.white));

            LinearLayout.LayoutParams layoutParamForDay = new LinearLayout.LayoutParams(CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 120), LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCellDay = new TextViewRegularFont(ExaminationScheduleActivity.this);
            textViewCellDay.setLayoutParams(layoutParamForDay);
            textViewCellDay.setGravity(Gravity.CENTER);
            textViewCellDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textViewCellDay.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (!CommonUtil.checkIsEmptyOrNullCommon(examinationScheduleTimetableList.get(i).getTtDay())) {
                textViewCellDay.setText(examinationScheduleTimetableList.get(i).getTtDay() + "");
            } else {
                textViewCellDay.setText("-");
            }
            textViewCellDay.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);


            View view4 = new View(ExaminationScheduleActivity.this);
            LinearLayout.LayoutParams layoutParamView4 = new LinearLayout.LayoutParams(widthForView, LinearLayout.LayoutParams.MATCH_PARENT);
            view4.setLayoutParams(layoutParamView4);
            view4.setBackgroundColor(getResources().getColor(R.color.white));

            LinearLayout.LayoutParams layoutParamForTime = new LinearLayout.LayoutParams(CommonUtil.convertDpToPxe(ExaminationScheduleActivity.this, 160), LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCellTime = new TextViewRegularFont(ExaminationScheduleActivity.this);
            textViewCellTime.setLayoutParams(layoutParamForTime);
            textViewCellTime.setGravity(Gravity.CENTER);
            textViewCellTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textViewCellTime.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (!CommonUtil.checkIsEmptyOrNullCommon(examinationScheduleTimetableList.get(i).getExamTime())) {
                textViewCellTime.setText(examinationScheduleTimetableList.get(i).getExamTime() + "");
            } else {
                textViewCellTime.setText("-");
            }
            textViewCellTime.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

            linearLayoutRow.addView(textViewCellSrNo);
            linearLayoutRow.addView(view);
            linearLayoutRow.addView(textViewCellCourseCode);
            linearLayoutRow.addView(view1);
            linearLayoutRow.addView(textViewCellCourseName);
            linearLayoutRow.addView(view2);
            linearLayoutRow.addView(textViewCellDate);
            linearLayoutRow.addView(view3);
            linearLayoutRow.addView(textViewCellDay);
            linearLayoutRow.addView(view4);
            linearLayoutRow.addView(textViewCellTime);
            llExaminationScheduleList.addView(linearLayoutRow);
        }


    }

    private void downloadExaminationSchedule(String semId, String yearId, String repeaterStatus) {
//        progressDialog.show();
        DialogUtil.showProgressDialogNotCancelable(ExaminationScheduleActivity.this, "downloading... ");
        ApiImplementer.downloadExaminationScheduleApiImplementer(semId, yearId,
                mySharedPreferences.getStudentId(), repeaterStatus, mySharedPreferences.getImExamDbName(), new Callback<DownloadExaminationSchedulePojo>() {
                    @Override
                    public void onResponse(Call<DownloadExaminationSchedulePojo> call, Response<DownloadExaminationSchedulePojo> response) {
                        try {
                            DialogUtil.hideProgressDialog();
                            if (response.isSuccessful() && response.body() != null) {
                                if (response.body().getBase64string() != null &&
                                        !response.body().getBase64string().isEmpty()) {
                                    new GeneratePDFFileFromBase64String(ExaminationScheduleActivity.this, "Exam Schedule", CommonUtil.generateUniqueFileName(response.body().getFilename()),
                                            response.body().getBase64string());
                                } else {
//                                    progressDialog.hide();
                                    Toast.makeText(ExaminationScheduleActivity.this, "Exam Schedule Not Found!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
//                                progressDialog.hide();
                                Toast.makeText(ExaminationScheduleActivity.this, "Some thing went wrong please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
//                            progressDialog.hide();
                        }
                    }

                    @Override
                    public void onFailure(Call<DownloadExaminationSchedulePojo> call, Throwable t) {
//                        progressDialog.hide();
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(ExaminationScheduleActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void downloadHallTicketExaminationSchedule(String semId, String yearId, String repeaterStatus) {
//        progressDialog.show();
        DialogUtil.showProgressDialogNotCancelable(ExaminationScheduleActivity.this, "downloading... ");
        ApiImplementer.downloadHallTicketExaminationScheduleApiImplementer(mySharedPreferences.getInstituteId(), mySharedPreferences.getAcId(), semId,
                yearId, mySharedPreferences.getStudentId(), repeaterStatus, mySharedPreferences.getImExamDbName(), new Callback<DownloadHallTicketExaminationSchedulePojo>() {
                    @Override
                    public void onResponse(Call<DownloadHallTicketExaminationSchedulePojo> call, Response<DownloadHallTicketExaminationSchedulePojo> response) {
                        try {
                            DialogUtil.hideProgressDialog();
                            if (response.isSuccessful() && response.body() != null) {
                                if (response.body().getBase64string() != null &&
                                        !response.body().getBase64string().isEmpty()) {
                                    new GeneratePDFFileFromBase64String(ExaminationScheduleActivity.this, "Exam Schedule", CommonUtil.generateUniqueFileName(response.body().getFilename()),
                                            response.body().getBase64string());
                                } else {
//                                    progressDialog.hide();
                                    Toast.makeText(ExaminationScheduleActivity.this, "Hall Ticket Not Publish.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
//                                progressDialog.hide();
                                Toast.makeText(ExaminationScheduleActivity.this, "Some thing went wrong please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ex) {
//                            progressDialog.hide();
                        }
                    }

                    @Override
                    public void onFailure(Call<DownloadHallTicketExaminationSchedulePojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
//                        progressDialog.hide();
                        Toast.makeText(ExaminationScheduleActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseExaminationSchedule) {
            onBackPressed();
        } else if (v.getId() == R.id.efabDownloadExaminationSchedule) {
            downloadExaminationSchedule(semId, yearId, repeaterStatus);
        } else if (v.getId() == R.id.efabDownloadHallTicketExaminationSchedule) {
            downloadHallTicketExaminationSchedule(semId, yearId, repeaterStatus);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}