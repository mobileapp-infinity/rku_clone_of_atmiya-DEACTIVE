package com.infinity.infoway.rkuniversity.student.student_timetable.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.student.student_timetable.activity.StudentTimeTableActivity;
import com.infinity.infoway.rkuniversity.student.student_timetable.adapter.StudentTimeTableAdapter;
import com.infinity.infoway.rkuniversity.student.student_timetable.pojo.StudentTimeTablePojo;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;

import java.util.ArrayList;

public class TimeTableFragment extends Fragment {


    ArrayList<StudentTimeTablePojo.InoutArray1> inoutArray1ArrayList;
    LinearLayout llStudentTimeTableList, llNoDataFoundStudentTimeTable;
    RecyclerView rvStudentTimeTable;
    StudentTimeTableActivity studentTimeTableActivity;
    boolean isDisplayTime = false;

    public TimeTableFragment() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        studentTimeTableActivity = (StudentTimeTableActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            inoutArray1ArrayList = (ArrayList<StudentTimeTablePojo.InoutArray1>) bundle.getSerializable(IntentConstants.STUDENT_TIME_TABLE_DAY_WISE_LIST);
            isDisplayTime = bundle.getBoolean(IntentConstants.IS_DISPLAY_TIME);
        } else {
            inoutArray1ArrayList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);
        initView(view);
        displayStudentTimeTable();
        return view;
    }

    private void initView(View view) {
        llStudentTimeTableList = view.findViewById(R.id.llStudentTimeTableList);
        llNoDataFoundStudentTimeTable = view.findViewById(R.id.llNoDataFoundStudentTimeTable);
        rvStudentTimeTable = view.findViewById(R.id.rvStudentTimeTable);
    }

    private void displayStudentTimeTable() {
        if (inoutArray1ArrayList != null && inoutArray1ArrayList.size() > 0) {
            llNoDataFoundStudentTimeTable.setVisibility(View.GONE);
            llStudentTimeTableList.setVisibility(View.VISIBLE);
            rvStudentTimeTable.setAdapter(new StudentTimeTableAdapter(studentTimeTableActivity, inoutArray1ArrayList,isDisplayTime));
        } else {
            llNoDataFoundStudentTimeTable.setVisibility(View.VISIBLE);
            llStudentTimeTableList.setVisibility(View.GONE);
        }
    }

}