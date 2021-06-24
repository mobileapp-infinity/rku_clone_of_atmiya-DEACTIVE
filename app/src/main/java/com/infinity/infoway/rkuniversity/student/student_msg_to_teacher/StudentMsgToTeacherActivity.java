package com.infinity.infoway.rkuniversity.student.student_msg_to_teacher;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.SpinnerSimpleAdapter;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

public class StudentMsgToTeacherActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SELECT_PERSON = "Select Person";

    private ArrayList<String> personArrayList = new ArrayList<>();
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
    }

    private void initView() {
        personArrayList.add(SELECT_PERSON);
        personArrayList.add("Mentor");
        personArrayList.add("Coordinator");
        personArrayList.add("HOD");
        ivCloseStudentMessagesToTeacher = findViewById(R.id.ivCloseStudentMessagesToTeacher);
        ivCloseStudentMessagesToTeacher.setOnClickListener(this);
        mySharedPreferences = new MySharedPreferences(StudentMsgToTeacherActivity.this);
        spSelectPerson = findViewById(R.id.spSelectPerson);
        tilTitle = findViewById(R.id.tilTitle);
        tilMessage = findViewById(R.id.tilMessage);
        tvSend = findViewById(R.id.tvSend);
        tvSend.setOnClickListener(this);

        spinnerAdapterSelectPerson = new SpinnerSimpleAdapter(StudentMsgToTeacherActivity.this, personArrayList);
        spSelectPerson.setAdapter(spinnerAdapterSelectPerson);
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
                String selectedPerson = personArrayList.get(spSelectPerson.getSelectedItemPosition());
                String title = tilTitle.getText().toString().trim();
                String messages = tilMessage.getText().toString().trim();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}