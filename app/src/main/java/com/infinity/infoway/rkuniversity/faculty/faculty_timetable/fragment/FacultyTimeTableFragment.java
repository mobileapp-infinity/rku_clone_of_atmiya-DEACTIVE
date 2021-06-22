package com.infinity.infoway.rkuniversity.faculty.faculty_timetable.fragment;

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
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.activity.FacultyTimeTableActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.adapter.FacultyTimeTableListAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.pojo.FacultyTimeTablePojo;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;

import java.util.ArrayList;

public class FacultyTimeTableFragment extends Fragment {


    ArrayList<FacultyTimeTablePojo.InoutArray1> lecturedetailArrayList;
    LinearLayout llFacultyTimeTableList, llNoDataFoundFacultyTimeTable;
    RecyclerView rvFacultyTimeTable;
    FacultyTimeTableActivity facultyTimeTableActivity;

    public FacultyTimeTableFragment() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        facultyTimeTableActivity = (FacultyTimeTableActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            lecturedetailArrayList = (ArrayList<FacultyTimeTablePojo.InoutArray1>) bundle.getSerializable(IntentConstants.FACULTY_TIME_TABLE_DAY_WISE_LIST);
        } else {
            lecturedetailArrayList = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty_time_table, container, false);
        initView(view);
        displayFacultyTimeTable();
        return view;
    }

    private void initView(View view) {
        llFacultyTimeTableList = view.findViewById(R.id.llFacultyTimeTableList);
        llNoDataFoundFacultyTimeTable = view.findViewById(R.id.llNoDataFoundFacultyTimeTable);
        rvFacultyTimeTable = view.findViewById(R.id.rvFacultyTimeTable);
    }

    private void displayFacultyTimeTable() {
        if (lecturedetailArrayList != null && lecturedetailArrayList.size() > 0) {
            llNoDataFoundFacultyTimeTable.setVisibility(View.GONE);
            llFacultyTimeTableList.setVisibility(View.VISIBLE);
            rvFacultyTimeTable.setAdapter(new FacultyTimeTableListAdapter(facultyTimeTableActivity, lecturedetailArrayList));
        } else {
            llNoDataFoundFacultyTimeTable.setVisibility(View.VISIBLE);
            llFacultyTimeTableList.setVisibility(View.GONE);
        }
    }

}