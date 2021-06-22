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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.exam.pojo.CIAExamSemesterPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentCIAMarksPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CIAExamMarksActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SELECT_SEM = "Select Semester";

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivCloseCIAExamMarks;
    ConnectionDetector connectionDetector;
    LinearLayout llCIAMarksList, llCIAMarksProgressbar, llNoDataFoundCIAMarks;

    TextViewRegularFont tvProgramNameCIAMarks;
    LinearLayout llCIAMarksMain;

    SearchableSpinner spSelectSemesterCIAMarks;
    MaterialCardView cvSelectSem;

    ArrayList<String> semNameArrayList;
    HashMap<String, String> semNameAndIdHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_i_a_exam_marks);
        initView();
        getStudentSemesterApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(CIAExamMarksActivity.this);
        connectionDetector = new ConnectionDetector(CIAExamMarksActivity.this);
        ivCloseCIAExamMarks = findViewById(R.id.ivCloseCIAExamMarks);
        ivCloseCIAExamMarks.setOnClickListener(this);
        llCIAMarksList = findViewById(R.id.llCIAMarksList);
        llCIAMarksProgressbar = findViewById(R.id.llCIAMarksProgressbar);
        llNoDataFoundCIAMarks = findViewById(R.id.llNoDataFoundCIAMarks);
        tvProgramNameCIAMarks = findViewById(R.id.tvProgramNameCIAMarks);
        llCIAMarksMain = findViewById(R.id.llCIAMarksMain);
        spSelectSemesterCIAMarks = findViewById(R.id.spSelectSemesterCIAMarks);
        cvSelectSem = findViewById(R.id.cvSelectSem);

        spSelectSemesterCIAMarks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String semId = semNameAndIdHashMap.get(semNameArrayList.get(position)) + "";
                    getStudentCIAMarksApiCall(semId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseCIAExamMarks) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getStudentSemesterApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            cvSelectSem.setVisibility(View.GONE);
            llCIAMarksProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundCIAMarks.setVisibility(View.GONE);
            ApiImplementer.getCIAExamSemesterApiImplementer(mySharedPreferences.getCourseId(), mySharedPreferences.getSmId(), mySharedPreferences.getDmId(), new Callback<CIAExamSemesterPojo>() {
                @Override
                public void onResponse(Call<CIAExamSemesterPojo> call, Response<CIAExamSemesterPojo> response) {
                    llCIAMarksProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().getTable().size() > 0) {

                        ArrayList<CIAExamSemesterPojo.Table> tableArrayList = (ArrayList<CIAExamSemesterPojo.Table>) response.body().getTable();

                        semNameArrayList = new ArrayList<>();
                        semNameArrayList.add(SELECT_SEM);
                        semNameAndIdHashMap = new HashMap<>();
                        cvSelectSem.setVisibility(View.VISIBLE);
                        llNoDataFoundCIAMarks.setVisibility(View.GONE);

                        for (int i = 0; i < tableArrayList.size(); i++) {
                            CIAExamSemesterPojo.Table table = tableArrayList.get(i);
                            if (!CommonUtil.checkIsEmptyOrNullCommon(table.getSmId()) &&
                                    !CommonUtil.checkIsEmptyOrNullCommon(table.getSmName())) {
                                semNameArrayList.add(table.getSmName());
                                semNameAndIdHashMap.put(table.getSmName(), table.getSmId() + "");
                            }
                        }

                        ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>
                                (CIAExamMarksActivity.this, R.layout.layout_dropdown_row,
                                        semNameArrayList);
                        bankAdapter.setDropDownViewResource(R.layout.layout_dropdown_row);
                        spSelectSemesterCIAMarks.setTitle("Select Semester");
                        spSelectSemesterCIAMarks.setAdapter(bankAdapter);

                    } else {
                        cvSelectSem.setVisibility(View.GONE);
                        llNoDataFoundCIAMarks.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<CIAExamSemesterPojo> call, Throwable t) {
                    cvSelectSem.setVisibility(View.VISIBLE);
                    llCIAMarksProgressbar.setVisibility(View.GONE);
                    llNoDataFoundCIAMarks.setVisibility(View.VISIBLE);
                    Toast.makeText(CIAExamMarksActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getStudentCIAMarksApiCall(String semId) {
        if (connectionDetector.isConnectingToInternet()) {
            llCIAMarksMain.removeAllViewsInLayout();
            llCIAMarksList.setVisibility(View.GONE);
            llCIAMarksProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundCIAMarks.setVisibility(View.GONE);
            ApiImplementer.getStudentCIAMarksApiImplementer(mySharedPreferences.getStudentId(), semId, "2", new Callback<ArrayList<StudentCIAMarksPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<StudentCIAMarksPojo>> call, Response<ArrayList<StudentCIAMarksPojo>> response) {
                    try {
                        llCIAMarksProgressbar.setVisibility(View.GONE);
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().size() > 0) {
                            llCIAMarksList.setVisibility(View.VISIBLE);
                            llNoDataFoundCIAMarks.setVisibility(View.GONE);
                            ArrayList<StudentCIAMarksPojo> studentCIAMarksPojoArrayList = response.body();
                            if (!CommonUtil.checkIsEmptyOrNullCommon(studentCIAMarksPojoArrayList.get(0).getSemesterName())) {
                                tvProgramNameCIAMarks.setText(studentCIAMarksPojoArrayList.get(0).getSemesterName());
                            }
                            displayDynamicCIAMarks(studentCIAMarksPojoArrayList);
                        } else {
                            llCIAMarksList.setVisibility(View.GONE);
                            llNoDataFoundCIAMarks.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<StudentCIAMarksPojo>> call, Throwable t) {
                    llCIAMarksList.setVisibility(View.GONE);
                    llCIAMarksProgressbar.setVisibility(View.GONE);
                    llNoDataFoundCIAMarks.setVisibility(View.VISIBLE);
                    Toast.makeText(CIAExamMarksActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDynamicCIAMarks(ArrayList<StudentCIAMarksPojo> studentCIAMarksPojoArrayList) {

        int marginTopMainRow = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 8);
        int marginBottomMainRow = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 8);

        int paddingTopCourseCode = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 5);
        int paddingBottomCourseCode = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 2);

        int paddingTopCourseName = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 2);
        int paddingBottomCourseName = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 5);

        int marginTopCIAMarksTable = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 3);

        int widthCIAMarksComponent = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 112);
        int widthCIAMarksValueLayout = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 200);

        int commonBoxPadding = CommonUtil.convertDpToPxe(CIAExamMarksActivity.this, 6);

        for (int i = 0; i < studentCIAMarksPojoArrayList.size(); i++) {

            StudentCIAMarksPojo studentCIAMarksPojo = studentCIAMarksPojoArrayList.get(i);

            LinearLayout.LayoutParams CIAMarksMainRowParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayoutCIAMarksMainRow = new LinearLayout(CIAExamMarksActivity.this);
            CIAMarksMainRowParam.setMargins(0, marginTopMainRow, 0, marginBottomMainRow);
            linearLayoutCIAMarksMainRow.setLayoutParams(CIAMarksMainRowParam);
            linearLayoutCIAMarksMainRow.setOrientation(LinearLayout.VERTICAL);


            LinearLayout.LayoutParams CIAMarksCorseCodeAndNameRowParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayoutCIAMarksCorseCodeAndName = new LinearLayout(CIAExamMarksActivity.this);
            linearLayoutCIAMarksCorseCodeAndName.setLayoutParams(CIAMarksCorseCodeAndNameRowParam);
            linearLayoutCIAMarksCorseCodeAndName.setOrientation(LinearLayout.VERTICAL);


            LinearLayout.LayoutParams layoutParamForCourseCode = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCourseCode = new TextViewRegularFont(CIAExamMarksActivity.this);
            textViewCourseCode.setLayoutParams(layoutParamForCourseCode);
            textViewCourseCode.setGravity(Gravity.CENTER);
            textViewCourseCode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textViewCourseCode.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (!CommonUtil.checkIsEmptyOrNullCommon(studentCIAMarksPojo.getSubjectCode())) {
                textViewCourseCode.setText("Course Code : " + studentCIAMarksPojo.getSubjectCode());
            } else {
                textViewCourseCode.setText("Course Code : " + "-");
            }
            textViewCourseCode.setPadding(0, paddingTopCourseCode, 0, paddingBottomCourseCode);


            LinearLayout.LayoutParams layoutParamForCourseName = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextViewRegularFont textViewCourseName = new TextViewRegularFont(CIAExamMarksActivity.this);
            textViewCourseName.setLayoutParams(layoutParamForCourseName);
            textViewCourseName.setGravity(Gravity.CENTER);
            textViewCourseName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            textViewCourseName.setTextColor(getResources().getColor(R.color.colorPrimary));
            if (!CommonUtil.checkIsEmptyOrNullCommon(studentCIAMarksPojo.getSubjectCode())) {
                textViewCourseName.setText("Course Name : " + studentCIAMarksPojo.getSubjectName());
            } else {
                textViewCourseName.setText("Course Name : " + "-");
            }
            textViewCourseName.setPadding(0, paddingTopCourseName, 0, paddingBottomCourseName);


            linearLayoutCIAMarksCorseCodeAndName.addView(textViewCourseCode);
            linearLayoutCIAMarksCorseCodeAndName.addView(textViewCourseName);

            linearLayoutCIAMarksMainRow.addView(linearLayoutCIAMarksCorseCodeAndName);


            LinearLayout.LayoutParams CIAMarksTableRowParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            CIAMarksTableRowParam.setMargins(0, marginTopCIAMarksTable, 0, 0);
            LinearLayout linearLayoutCIAMarksTable = new LinearLayout(CIAExamMarksActivity.this);
            linearLayoutCIAMarksTable.setLayoutParams(CIAMarksTableRowParam);
            linearLayoutCIAMarksTable.setOrientation(LinearLayout.HORIZONTAL);


            drawComponentNameAndTotalMarksColumn(false, widthCIAMarksComponent, commonBoxPadding, linearLayoutCIAMarksTable, "Component", "Total Marks", "Obtain Marks");

            for (int k = 0; k < studentCIAMarksPojo.getComponentDetail().size(); k++) {

                StudentCIAMarksPojo.ComponentDetail componentDetail = studentCIAMarksPojo.getComponentDetail().get(k);

                LinearLayout.LayoutParams CIAMarksTableBoxValueParam = new LinearLayout.LayoutParams(widthCIAMarksValueLayout, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout linearLayoutCIAMarksTableBoxValue = new LinearLayout(CIAExamMarksActivity.this);
                linearLayoutCIAMarksTableBoxValue.setLayoutParams(CIAMarksTableBoxValueParam);
                linearLayoutCIAMarksTableBoxValue.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams layoutParamForBoxHeader = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                TextViewRegularFont textViewBoxHeader = new TextViewRegularFont(CIAExamMarksActivity.this);
                textViewBoxHeader.setLayoutParams(layoutParamForBoxHeader);
                textViewBoxHeader.setGravity(Gravity.CENTER);
                textViewBoxHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                textViewBoxHeader.setTextColor(getResources().getColor(R.color.white));
                textViewBoxHeader.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_common_header));
                if (!CommonUtil.checkIsEmptyOrNullCommon(componentDetail.getName())) {
                    textViewBoxHeader.setText(componentDetail.getName() + "");
                } else {
                    textViewBoxHeader.setText("-");
                }
                textViewBoxHeader.setPadding(commonBoxPadding, commonBoxPadding, commonBoxPadding, commonBoxPadding);

                linearLayoutCIAMarksTableBoxValue.addView(textViewBoxHeader);

                String total1 = "-";
                String total2 = "-";
                String obtain1 = "-";
                String obtain2 = "-";

                if (!CommonUtil.checkIsEmptyOrNullCommon(componentDetail.getTotalMarks()) && componentDetail.getTotalMarks().contains(",")) {
                    total1 = componentDetail.getTotalMarks().split(",")[0];
                    total2 = componentDetail.getTotalMarks().split(",")[1];
                }

                if (!CommonUtil.checkIsEmptyOrNullCommon(componentDetail.getObtainMarks()) && componentDetail.getObtainMarks().contains(",")) {
                    obtain1 = componentDetail.getObtainMarks().split(",")[0];
                    obtain2 = componentDetail.getObtainMarks().split(",")[1];
                }

                drawTableBoxValueLayout(linearLayoutCIAMarksTableBoxValue, commonBoxPadding, total1, total2, obtain1, obtain2);

                linearLayoutCIAMarksTable.addView(linearLayoutCIAMarksTableBoxValue);
            }

            String totalMarks = "-";
            String obtainMarks = "-";

            if (!CommonUtil.checkIsEmptyOrNullCommon(studentCIAMarksPojo.getTotalMarks())) {
                totalMarks = studentCIAMarksPojo.getTotalMarks() + "";
            }

            if (!CommonUtil.checkIsEmptyOrNullCommon(studentCIAMarksPojo.getTotalObtMarks())) {
                obtainMarks = studentCIAMarksPojo.getTotalObtMarks() + "";
            }

            drawComponentNameAndTotalMarksColumn(true, widthCIAMarksComponent, commonBoxPadding, linearLayoutCIAMarksTable, "Total Marks", totalMarks, obtainMarks);

            linearLayoutCIAMarksMainRow.addView(linearLayoutCIAMarksTable);
            llCIAMarksMain.addView(linearLayoutCIAMarksMainRow);
        }
    }

    private void drawComponentNameAndTotalMarksColumn(boolean isTotal, int widthCIAMarksComponent, int commonBoxPadding, LinearLayout linearLayoutCIAMarksTable, String str1, String str2, String str3) {
        LinearLayout.LayoutParams CIAMarksTableComponentParam = new LinearLayout.LayoutParams(widthCIAMarksComponent, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayoutCIAMarksTableComponent = new LinearLayout(CIAExamMarksActivity.this);
        linearLayoutCIAMarksTableComponent.setLayoutParams(CIAMarksTableComponentParam);
        linearLayoutCIAMarksTableComponent.setOrientation(LinearLayout.VERTICAL);


        LinearLayout.LayoutParams layoutParamForComponent = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TextViewRegularFont textViewComponent = new TextViewRegularFont(CIAExamMarksActivity.this);
        textViewComponent.setLayoutParams(layoutParamForComponent);
        textViewComponent.setGravity(Gravity.CENTER);
        textViewComponent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textViewComponent.setTextColor(getResources().getColor(R.color.white));
        textViewComponent.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_common_header));
        textViewComponent.setText(str1);
        textViewComponent.setPadding(commonBoxPadding, commonBoxPadding, commonBoxPadding, commonBoxPadding);

        LinearLayout.LayoutParams layoutParamForTotalMarks = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TextViewRegularFont textViewTotalMarks = new TextViewRegularFont(CIAExamMarksActivity.this);
        textViewTotalMarks.setLayoutParams(layoutParamForTotalMarks);
        textViewTotalMarks.setGravity(Gravity.CENTER);
        textViewTotalMarks.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textViewTotalMarks.setTextColor(getResources().getColor(R.color.white));
        textViewTotalMarks.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_common_header));
        if (isTotal) {
            textViewTotalMarks.setTextColor(getResources().getColor(R.color.colorPrimary));
            textViewTotalMarks.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_value));
        }
        textViewTotalMarks.setText(str2);
        textViewTotalMarks.setPadding(commonBoxPadding, commonBoxPadding, commonBoxPadding, commonBoxPadding);

        LinearLayout.LayoutParams layoutParamForOntainMarks = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TextViewRegularFont textViewObtainMarks = new TextViewRegularFont(CIAExamMarksActivity.this);
        textViewObtainMarks.setLayoutParams(layoutParamForOntainMarks);
        textViewObtainMarks.setGravity(Gravity.CENTER);
        textViewObtainMarks.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textViewObtainMarks.setTextColor(getResources().getColor(R.color.white));
        textViewObtainMarks.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_common_header));
        if (isTotal) {
            textViewObtainMarks.setTextColor(getResources().getColor(R.color.colorPrimary));
            textViewObtainMarks.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_value));
        }
        textViewObtainMarks.setText(str3);
        textViewObtainMarks.setPadding(commonBoxPadding, commonBoxPadding, commonBoxPadding, commonBoxPadding);

        linearLayoutCIAMarksTableComponent.addView(textViewComponent);
        linearLayoutCIAMarksTableComponent.addView(textViewTotalMarks);
        linearLayoutCIAMarksTableComponent.addView(textViewObtainMarks);

        linearLayoutCIAMarksTable.addView(linearLayoutCIAMarksTableComponent);
    }

    private void drawTableBoxValueLayout(LinearLayout linearLayoutCIAMarksTableBoxValue, int commonBoxPadding, String total1, String total2, String obtain1, String obtain2) {
        LinearLayout.LayoutParams CIAMarksTableTotal12MarksParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayoutCIAMarksTableTotal12Marks = new LinearLayout(CIAExamMarksActivity.this);
        linearLayoutCIAMarksTableTotal12Marks.setLayoutParams(CIAMarksTableTotal12MarksParam);
        linearLayoutCIAMarksTableTotal12Marks.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout.LayoutParams layoutParamForBoxValueTotal1Param = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        TextViewRegularFont textViewBoxValueTotal1 = new TextViewRegularFont(CIAExamMarksActivity.this);
        textViewBoxValueTotal1.setLayoutParams(layoutParamForBoxValueTotal1Param);
        textViewBoxValueTotal1.setGravity(Gravity.CENTER);
        textViewBoxValueTotal1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textViewBoxValueTotal1.setTextColor(getResources().getColor(R.color.colorPrimary));
        textViewBoxValueTotal1.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_value));
        textViewBoxValueTotal1.setText(total1);
        textViewBoxValueTotal1.setPadding(commonBoxPadding, commonBoxPadding, commonBoxPadding, commonBoxPadding);


        LinearLayout.LayoutParams layoutParamForBoxValueTotal2Param = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        TextViewRegularFont textViewBoxValueTotal2 = new TextViewRegularFont(CIAExamMarksActivity.this);
        textViewBoxValueTotal2.setLayoutParams(layoutParamForBoxValueTotal2Param);
        textViewBoxValueTotal2.setGravity(Gravity.CENTER);
        textViewBoxValueTotal2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textViewBoxValueTotal2.setTextColor(getResources().getColor(R.color.colorPrimary));
        textViewBoxValueTotal2.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_value));
        textViewBoxValueTotal2.setText(total2);
        textViewBoxValueTotal2.setPadding(commonBoxPadding, commonBoxPadding, commonBoxPadding, commonBoxPadding);

        linearLayoutCIAMarksTableTotal12Marks.addView(textViewBoxValueTotal1);
        linearLayoutCIAMarksTableTotal12Marks.addView(textViewBoxValueTotal2);


        linearLayoutCIAMarksTableBoxValue.addView(linearLayoutCIAMarksTableTotal12Marks);


        LinearLayout.LayoutParams CIAMarksTableObtain12MarksParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayoutCIAMarksTableObtain12Marks = new LinearLayout(CIAExamMarksActivity.this);
        linearLayoutCIAMarksTableObtain12Marks.setLayoutParams(CIAMarksTableObtain12MarksParam);
        linearLayoutCIAMarksTableObtain12Marks.setOrientation(LinearLayout.HORIZONTAL);


        LinearLayout.LayoutParams layoutParamForBoxValueObtain1Param = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        TextViewRegularFont textViewBoxValueObtain1 = new TextViewRegularFont(CIAExamMarksActivity.this);
        textViewBoxValueObtain1.setLayoutParams(layoutParamForBoxValueObtain1Param);
        textViewBoxValueObtain1.setGravity(Gravity.CENTER);
        textViewBoxValueObtain1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textViewBoxValueObtain1.setTextColor(getResources().getColor(R.color.colorPrimary));
        textViewBoxValueObtain1.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_value));
        textViewBoxValueObtain1.setText(obtain1);
        textViewBoxValueObtain1.setPadding(commonBoxPadding, commonBoxPadding, commonBoxPadding, commonBoxPadding);


        LinearLayout.LayoutParams layoutParamForBoxValueObtain2Param = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        TextViewRegularFont textViewBoxValueObtain2 = new TextViewRegularFont(CIAExamMarksActivity.this);
        textViewBoxValueObtain2.setLayoutParams(layoutParamForBoxValueObtain2Param);
        textViewBoxValueObtain2.setGravity(Gravity.CENTER);
        textViewBoxValueObtain2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textViewBoxValueObtain2.setTextColor(getResources().getColor(R.color.colorPrimary));
        textViewBoxValueObtain2.setBackgroundDrawable(ContextCompat.getDrawable(CIAExamMarksActivity.this, R.drawable.shape_cia_marks_value));
        textViewBoxValueObtain2.setText(obtain2);
        textViewBoxValueObtain2.setPadding(commonBoxPadding, commonBoxPadding, commonBoxPadding, commonBoxPadding);

        linearLayoutCIAMarksTableObtain12Marks.addView(textViewBoxValueObtain1);
        linearLayoutCIAMarksTableObtain12Marks.addView(textViewBoxValueObtain2);


        linearLayoutCIAMarksTableBoxValue.addView(linearLayoutCIAMarksTableObtain12Marks);
    }

}