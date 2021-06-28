package com.infinity.infoway.rkuniversity.student.student_msg_to_teacher;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.SpinnerSimpleAdapter;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.login.activity.LoginActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.student.student_msg_to_teacher.pojo.GetPersonPojo;
import com.infinity.infoway.rkuniversity.student.student_msg_to_teacher.pojo.InsertMsgPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentMsgToTeacherActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SELECT_PERSON = "Select Person";

    private ArrayList<String> personArrayList;
    private HashMap<String, String> personHashMap;
    private MySharedPreferences mySharedPreferences;
    private Spinner spSelectPerson;
    private SpinnerSimpleAdapter spinnerAdapterSelectPerson;
    private TextInputEditText tilTitle;
    private TextInputEditText tilMessage;
    private TextViewRegularFont tvSend;
    private AppCompatImageView ivCloseStudentMessagesToTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_msg_to_teacher);
        initView();
        getPersonsApiCall();
    }

    private void initView() {
        ivCloseStudentMessagesToTeacher = findViewById(R.id.ivCloseStudentMessagesToTeacher);
        ivCloseStudentMessagesToTeacher.setOnClickListener(this);
        mySharedPreferences = new MySharedPreferences(StudentMsgToTeacherActivity.this);
        spSelectPerson = findViewById(R.id.spSelectPerson);
        tilTitle = findViewById(R.id.tilTitle);
        tilMessage = findViewById(R.id.tilMessage);
        tvSend = findViewById(R.id.tvSend);
        tvSend.setOnClickListener(this);
    }

    private boolean isValid() {
        if (spSelectPerson.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select person", Toast.LENGTH_SHORT).show();
            return false;
        } else if (CommonUtil.checkIsEmptyOrNullCommon(tilTitle.getText().toString().trim())) {
            Toast.makeText(this, "Please enter title", Toast.LENGTH_SHORT).show();
            return false;
        } else if (CommonUtil.checkIsEmptyOrNullCommon(tilMessage.getText().toString().trim())) {
            Toast.makeText(this, "Please enter message", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseStudentMessagesToTeacher) {
            onBackPressed();
        } else if (v.getId() == R.id.tvSend) {
            if (isValid()) {
                String personId = personHashMap.get(personArrayList.get(spSelectPerson.getSelectedItemPosition()).trim());
                String title = tilTitle.getText().toString().trim();
                String messages = tilMessage.getText().toString().trim();
                insertMsgApiCall(personId, messages, title);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getPersonsApiCall() {
        DialogUtil.showProgressDialogNotCancelable(StudentMsgToTeacherActivity.this, "");
        ApiImplementer.getPersonApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getInstituteId(), new Callback<ArrayList<GetPersonPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetPersonPojo>> call, Response<ArrayList<GetPersonPojo>> response) {
                DialogUtil.hideProgressDialog();
                if (response.code() == 200 && response.body() != null && response.body().size() > 0) {
                    personArrayList = new ArrayList<>();
                    personArrayList.add(SELECT_PERSON);
                    personHashMap = new HashMap<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        if (!CommonUtil.checkIsEmptyOrNullCommon(response.body().get(i).getEmpId()) &&
                                !CommonUtil.checkIsEmptyOrNullCommon(response.body().get(i).getPersonName())) {
                            personArrayList.add(response.body().get(i).getPersonName().trim());
                            personHashMap.put(response.body().get(i).getPersonName().trim(), response.body().get(i).getEmpId() + "");
                        }
                    }
                    spinnerAdapterSelectPerson = new SpinnerSimpleAdapter(StudentMsgToTeacherActivity.this, personArrayList);
                    spSelectPerson.setAdapter(spinnerAdapterSelectPerson);
                } else {
                    Toast.makeText(StudentMsgToTeacherActivity.this, "Person Not Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetPersonPojo>> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(StudentMsgToTeacherActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertMsgApiCall(String personId, String msg, String title) {
        DialogUtil.showProgressDialogNotCancelable(StudentMsgToTeacherActivity.this, "");
        ApiImplementer.insertMsgApiImplementer(mySharedPreferences.getStudentId(),mySharedPreferences.getInstituteId(), personId, title, msg, new Callback<InsertMsgPojo>() {
            @Override
            public void onResponse(Call<InsertMsgPojo> call, Response<InsertMsgPojo> response) {
                DialogUtil.hideProgressDialog();
                if (response.code() == 200 && response.body() != null && !CommonUtil.checkIsEmptyOrNullCommon(response.body().getResponse())) {
                    Toast.makeText(StudentMsgToTeacherActivity.this, "" + response.body().getResponse(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentMsgToTeacherActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertMsgPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(StudentMsgToTeacherActivity.this, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}