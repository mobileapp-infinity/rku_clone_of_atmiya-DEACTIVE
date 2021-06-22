package com.infinity.infoway.rkuniversity.faculty.faculty_direct_login_to_student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyDirectLoginToStudentActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivCloseFacultyStudentDirectLogin;
    ConnectionDetector connectionDetector;

    LinearLayout llFacultyStudentList;
    LinearLayout llFacultyStudentProgressbar;
    LinearLayout llNoDataFoundFacultyStudent;
    AppCompatEditText edSearchStudent;
    AppCompatImageView imClearSearch;
    RecyclerView rvStudentList;
    FacultyStudentListAdapter facultyStudentListAdapter;
    ArrayList<FacultyStudentListPojo.Table> tableArrayList;

    TextInputEditText tiedtStudentName;
    TextViewRegularFont tvBtnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_direct_login_to_student);
        initView();
    }

    private boolean isValid() {
        if (CommonUtil.checkIsEmptyOrNullCommon(tiedtStudentName.getText().toString().trim())) {
            Toast.makeText(this, "Please enter student name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyDirectLoginToStudentActivity.this);
        connectionDetector = new ConnectionDetector(FacultyDirectLoginToStudentActivity.this);
        ivCloseFacultyStudentDirectLogin = findViewById(R.id.ivCloseFacultyStudentDirectLogin);
        ivCloseFacultyStudentDirectLogin.setOnClickListener(this);

        tiedtStudentName = findViewById(R.id.tiedtStudentName);
        tvBtnSearch = findViewById(R.id.tvBtnSearch);
        tvBtnSearch.setOnClickListener(this);
        llFacultyStudentList = findViewById(R.id.llFacultyStudentList);
        llFacultyStudentProgressbar = findViewById(R.id.llFacultyStudentProgressbar);
        llNoDataFoundFacultyStudent = findViewById(R.id.llNoDataFoundFacultyStudent);
        edSearchStudent = findViewById(R.id.edSearchStudent);
        imClearSearch = findViewById(R.id.imClearSearch);
        imClearSearch.setOnClickListener(this);
        rvStudentList = findViewById(R.id.rvStudentList);

        edSearchStudent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    imClearSearch.setVisibility(View.VISIBLE);
                } else {
                    imClearSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    void filter(String text) {
        try {
            ArrayList<FacultyStudentListPojo.Table> updatedList = new ArrayList();
            for (FacultyStudentListPojo.Table table : tableArrayList) {
                if ((table.getStudName() != null && table.getStudName().toLowerCase().contains(text.toLowerCase())) ||
                        (table.getStudEnrollmentNo() != null && table.getStudEnrollmentNo().trim().contains(text.trim())) ||
                        (table.getStudMobileNo() != null && table.getStudMobileNo().trim().contains(text.trim()))) {
                    updatedList.add(table);
                }
            }
            facultyStudentListAdapter.updateList(updatedList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFacultyStudentDirectLogin) {
            onBackPressed();
        } else if (v.getId() == R.id.imClearSearch) {
            edSearchStudent.setText("");
        } else if (v.getId() == R.id.tvBtnSearch) {
            if (isValid()) {
                CommonUtil.hideKeyboardCommon(FacultyDirectLoginToStudentActivity.this);
                getStudentListApiCall(tiedtStudentName.getText().toString().trim());
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getStudentListApiCall(String studentName) {
        llFacultyStudentList.setVisibility(View.GONE);
        llFacultyStudentProgressbar.setVisibility(View.VISIBLE);
        llNoDataFoundFacultyStudent.setVisibility(View.GONE);
        ApiImplementer.getFacultyStudentListforDirectLoginApiImplementer(studentName, mySharedPreferences.getEmpInstituteId(), new Callback<FacultyStudentListPojo>() {
            @Override
            public void onResponse(Call<FacultyStudentListPojo> call, Response<FacultyStudentListPojo> response) {
                try {
                    if (response.code() == 200 && response.body() != null &&
                            response.body().getTable() != null) {
                        if (response.body().getTable().size() > 0) {
                            llFacultyStudentList.setVisibility(View.VISIBLE);
                            llFacultyStudentProgressbar.setVisibility(View.GONE);
                            llNoDataFoundFacultyStudent.setVisibility(View.GONE);
                            tableArrayList = (ArrayList<FacultyStudentListPojo.Table>) response.body().getTable();
                            facultyStudentListAdapter = new FacultyStudentListAdapter(FacultyDirectLoginToStudentActivity.this, tableArrayList);
                            rvStudentList.setAdapter(facultyStudentListAdapter);
                        } else {
                            llFacultyStudentList.setVisibility(View.GONE);
                            llFacultyStudentProgressbar.setVisibility(View.GONE);
                            llNoDataFoundFacultyStudent.setVisibility(View.VISIBLE);
                        }
                    } else {
                        llFacultyStudentList.setVisibility(View.GONE);
                        llFacultyStudentProgressbar.setVisibility(View.GONE);
                        llNoDataFoundFacultyStudent.setVisibility(View.VISIBLE);
                        Toast.makeText(FacultyDirectLoginToStudentActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FacultyStudentListPojo> call, Throwable t) {
                llFacultyStudentList.setVisibility(View.GONE);
                llFacultyStudentProgressbar.setVisibility(View.GONE);
                llNoDataFoundFacultyStudent.setVisibility(View.VISIBLE);
                Toast.makeText(FacultyDirectLoginToStudentActivity.this, "Request FailedL- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}