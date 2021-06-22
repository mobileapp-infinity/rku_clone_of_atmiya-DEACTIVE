package com.infinity.infoway.rkuniversity.student.home_work.activity;

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
import com.infinity.infoway.rkuniversity.student.home_work.adapter.StudentHomeWorkListAdapter;
import com.infinity.infoway.rkuniversity.student.home_work.pojo.StudentHomeWorkPojo;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;

import java.util.ArrayList;


public class StudentHomeWorkFragment extends Fragment {

    ArrayList<StudentHomeWorkPojo.HomeworkArray> studentDayWiseHomeWork;
    LinearLayout llStudentHomeWorkList, llNoDataFoundStudentHomeWork;
    RecyclerView rvStudentHomeWork;
    StudentHomeWorkActivity studentHomeWorkActivity;

    public StudentHomeWorkFragment() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        studentHomeWorkActivity = (StudentHomeWorkActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            studentDayWiseHomeWork = (ArrayList<StudentHomeWorkPojo.HomeworkArray>) bundle.getSerializable(IntentConstants.STUDENT_HOME_WORK_DAY_WISE_LIST);
        } else {
            studentDayWiseHomeWork = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_home_work, container, false);
        initView(view);
        displayStudentTimeTable();
        return view;
    }

    private void initView(View view) {
        llStudentHomeWorkList = view.findViewById(R.id.llStudentHomeWorkList);
        llNoDataFoundStudentHomeWork = view.findViewById(R.id.llNoDataFoundStudentHomeWork);
        rvStudentHomeWork = view.findViewById(R.id.rvStudentHomeWork);
    }

    private void displayStudentTimeTable() {
        if (studentDayWiseHomeWork != null && studentDayWiseHomeWork.size() > 0) {
            llNoDataFoundStudentHomeWork.setVisibility(View.GONE);
            llStudentHomeWorkList.setVisibility(View.VISIBLE);
            rvStudentHomeWork.setAdapter(new StudentHomeWorkListAdapter(studentHomeWorkActivity, studentDayWiseHomeWork));
        } else {
            llNoDataFoundStudentHomeWork.setVisibility(View.VISIBLE);
            llStudentHomeWorkList.setVisibility(View.GONE);
        }
    }

}