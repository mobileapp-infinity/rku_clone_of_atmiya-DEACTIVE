package com.infinity.infoway.rkuniversity.student.attendance.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.student.attendance.activity.StudentAttendanceActivity;
import com.infinity.infoway.rkuniversity.student.attendance.adapter.SubjectWiseAttendanceAdapter;
import com.infinity.infoway.rkuniversity.student.attendance.pojo.StudentSubjectWiseAttendancePojo;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubjectWiseAttendanceFragment extends Fragment {

    RecyclerView rvSubjectWiseList;
    private StudentAttendanceActivity studentAttendanceActivity;
    LinearLayout llSubjectWiseAttendance, llSubjectWiseAttendanceProgressbar;
    MySharedPreferences mySharedPreferences;
    LinearLayout llNoDataFound;
    ConnectionDetector connectionDetector;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        studentAttendanceActivity = (StudentAttendanceActivity) context;
        mySharedPreferences = new MySharedPreferences(context);
    }

    public SubjectWiseAttendanceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject_wise_attendance, container, false);
        initView(view);
        getStudentSubjectWiseAttendance();
        return view;
    }

    private void initView(View view) {
        connectionDetector = new ConnectionDetector(studentAttendanceActivity);
        llSubjectWiseAttendance = view.findViewById(R.id.llSubjectWiseAttendance);
        llSubjectWiseAttendanceProgressbar = view.findViewById(R.id.llSubjectWiseAttendanceProgressbar);
        rvSubjectWiseList = view.findViewById(R.id.rvSubjectWiseList);
        llNoDataFound = view.findViewById(R.id.llNoDataFound);
    }


    private void getStudentSubjectWiseAttendance() {
        if (connectionDetector.isConnectingToInternet()) {
            llSubjectWiseAttendance.setVisibility(View.GONE);
            llSubjectWiseAttendanceProgressbar.setVisibility(View.VISIBLE);
            llNoDataFound.setVisibility(View.GONE);
            Map<String, String> mParams;
            mParams = new HashMap<>();
            mParams.put("stud_id", mySharedPreferences.getStudentId());
            mParams.put("year_id", mySharedPreferences.getSwdYearId());
            ApiImplementer.getStudentSubjectWiseAttendanceApiImplementer(mParams, new Callback<ArrayList<StudentSubjectWiseAttendancePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<StudentSubjectWiseAttendancePojo>> call, Response<ArrayList<StudentSubjectWiseAttendancePojo>> response) {
                    llSubjectWiseAttendanceProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().size() > 0) {
                        llNoDataFound.setVisibility(View.GONE);
                        ArrayList<StudentSubjectWiseAttendancePojo> studentSubjectWiseAttendancePojoArrayList = response.body();
                        rvSubjectWiseList.setAdapter(new SubjectWiseAttendanceAdapter(studentAttendanceActivity, studentSubjectWiseAttendancePojoArrayList));
                        llSubjectWiseAttendance.setVisibility(View.VISIBLE);
                    } else {
                        llNoDataFound.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<StudentSubjectWiseAttendancePojo>> call, Throwable t) {
                    Toast.makeText(studentAttendanceActivity, "Request Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    llNoDataFound.setVisibility(View.VISIBLE);
                    llSubjectWiseAttendance.setVisibility(View.GONE);
                    llSubjectWiseAttendanceProgressbar.setVisibility(View.GONE);
                }
            });
        } else {
            Toast.makeText(studentAttendanceActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}