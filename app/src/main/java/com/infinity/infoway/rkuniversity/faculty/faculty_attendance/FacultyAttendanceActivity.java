package com.infinity.infoway.rkuniversity.faculty.faculty_attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyAttendanceActivity extends AppCompatActivity implements View.OnClickListener, MaterialPickerOnPositiveButtonClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;

    AppCompatImageView ivCloseFacultyAttendance;
    MaterialDatePicker.Builder builder;
    MaterialDatePicker materialDatePicker;
    TextViewRegularFont tvFacultyAttendanceFromDate, tvFacultyAttendanceToDate;
    MaterialCardView cvFacultyAttendance;
    String dateFormatForEmpAttendance = "yyyy/MM/dd";

    LinearLayout llFacultyAttendanceList, llFacultyAttendanceProgressbar, llNoDataFoundFacultyAttendance;
    //    ExpandableListView elvFacultyAttendance;
    RecyclerView rvFacultyAttendanceListNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_attendance);
        initView();
        if (isValid()) {
//            getFacultyAttendanceApiCall(tvFacultyAttendanceFromDate.getText().toString().trim(),
//                    tvFacultyAttendanceToDate.getText().toString().trim());
        }
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyAttendanceActivity.this);
        connectionDetector = new ConnectionDetector(FacultyAttendanceActivity.this);
        ivCloseFacultyAttendance = findViewById(R.id.ivCloseFacultyAttendance);
        ivCloseFacultyAttendance.setOnClickListener(this);
        tvFacultyAttendanceFromDate = findViewById(R.id.tvFacultyAttendanceFromDate);
        tvFacultyAttendanceToDate = findViewById(R.id.tvFacultyAttendanceToDate);
        cvFacultyAttendance = findViewById(R.id.cvFacultyAttendance);
        cvFacultyAttendance.setOnClickListener(this);

        llFacultyAttendanceList = findViewById(R.id.llFacultyAttendanceList);
        llFacultyAttendanceProgressbar = findViewById(R.id.llFacultyAttendanceProgressbar);
        llNoDataFoundFacultyAttendance = findViewById(R.id.llNoDataFoundFacultyAttendance);
//        elvFacultyAttendance = findViewById(R.id.elvFacultyAttendance);
        rvFacultyAttendanceListNew = findViewById(R.id.rvFacultyAttendanceListNew);

        builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select A Date");
        materialDatePicker = builder.build();
        materialDatePicker.addOnPositiveButtonClickListener(this);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat(dateFormatForEmpAttendance, Locale.getDefault());
        String formattedDate = df.format(c);

        tvFacultyAttendanceFromDate.setText(formattedDate);
        tvFacultyAttendanceToDate.setText(formattedDate);
    }

    private boolean isValid() {
        boolean isValid = true;

        if (CommonUtil.checkIsEmptyOrNullCommon(tvFacultyAttendanceFromDate.getText().toString())) {
            isValid = false;
            Toast.makeText(this, "Please select from date", Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.checkIsEmptyOrNullCommon(tvFacultyAttendanceToDate.getText().toString())) {
            isValid = false;
            Toast.makeText(this, "Please select to date", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyAttendance) {
            onBackPressed();
        } else if (v.getId() == R.id.cvFacultyAttendance) {
            if (!materialDatePicker.isVisible()){
                materialDatePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPositiveButtonClick(Object selectedDate) {

        final Pair<Date, Date> rangeDate = new Pair<>(new Date((Long) ((Pair) selectedDate).first), new Date((Long) ((Pair) selectedDate).second));
        Date startDate = rangeDate.first;
        Date endDate = rangeDate.second;
        SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormatForEmpAttendance);
        tvFacultyAttendanceFromDate.setText(simpleFormat.format(startDate));
        tvFacultyAttendanceToDate.setText(simpleFormat.format(endDate));

        if (isValid()) {
//            getFacultyAttendanceApiCall(tvFacultyAttendanceFromDate.getText().toString().trim(),
//                    tvFacultyAttendanceToDate.getText().toString().trim());
        }

    }


//    private void getFacultyAttendanceApiCall(String fromDate, String toDate) {
//        if (connectionDetector.isConnectingToInternet()) {
//            llFacultyAttendanceList.setVisibility(View.GONE);
//            llFacultyAttendanceProgressbar.setVisibility(View.VISIBLE);
//            llNoDataFoundFacultyAttendance.setVisibility(View.GONE);
//            ApiImplementer.getFacultyAttendanceApiImplementer(mySharedPreferences.getEmpNumber(), mySharedPreferences.getEmpAcCode(),
//                    fromDate, toDate, new Callback<ArrayList<FacultyAttendancePojo>>() {
//                        @Override
//                        public void onResponse(Call<ArrayList<FacultyAttendancePojo>> call, Response<ArrayList<FacultyAttendancePojo>> response) {
//                            try {
//                                llFacultyAttendanceProgressbar.setVisibility(View.GONE);
//
//                                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
//                                    llFacultyAttendanceList.setVisibility(View.VISIBLE);
//                                    ArrayList<FacultyAttendancePojo> facultyAttendancePojoArrayList = response.body();
//                                    rvFacultyAttendanceListNew.setAdapter(new FacultyAttendanceAdapterNew(FacultyAttendanceActivity.this, facultyAttendancePojoArrayList));
//                                } else {
//                                    llFacultyAttendanceList.setVisibility(View.GONE);
//                                    llNoDataFoundFacultyAttendance.setVisibility(View.VISIBLE);
//                                }
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ArrayList<FacultyAttendancePojo>> call, Throwable t) {
//                            llFacultyAttendanceList.setVisibility(View.GONE);
//                            llFacultyAttendanceProgressbar.setVisibility(View.GONE);
//                            llNoDataFoundFacultyAttendance.setVisibility(View.VISIBLE);
//                            Toast.makeText(FacultyAttendanceActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        } else {
//            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
//        }
//    }

}