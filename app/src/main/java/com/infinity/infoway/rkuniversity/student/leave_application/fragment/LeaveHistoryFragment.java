package com.infinity.infoway.rkuniversity.student.leave_application.fragment;

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
import com.infinity.infoway.rkuniversity.student.leave_application.activity.LeaveApplicationActivity;
import com.infinity.infoway.rkuniversity.student.leave_application.adapter.StudentLeaveApplicationHistoryAdapter;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.LeaveApplicationHistoryPojo;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveHistoryFragment extends Fragment {

    LeaveApplicationActivity leaveApplicationActivity;
    ConnectionDetector connectionDetector;
    MySharedPreferences mySharedPreferences;
    private static LeaveHistoryFragment leaveHistoryFragment = null;
    LinearLayout llStudentLeaveList, llLeaveHistoryProgressbar, llNoDataFoundLeaveHistory;
    RecyclerView rvStudentLeaveList;

    public static LeaveHistoryFragment newInstance() {
        if (leaveHistoryFragment == null) {
            leaveHistoryFragment = new LeaveHistoryFragment();
        }
        return leaveHistoryFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getStudentLeaveApplicationHistoryListApiCall();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        leaveApplicationActivity = (LeaveApplicationActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave_history, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(leaveApplicationActivity);
        connectionDetector = new ConnectionDetector(leaveApplicationActivity);
        llStudentLeaveList = view.findViewById(R.id.llStudentLeaveList);
        llLeaveHistoryProgressbar = view.findViewById(R.id.llLeaveHistoryProgressbar);
        llNoDataFoundLeaveHistory = view.findViewById(R.id.llNoDataFoundLeaveHistory);
        rvStudentLeaveList = view.findViewById(R.id.rvStudentLeaveList);
    }


    private void getStudentLeaveApplicationHistoryListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentLeaveList.setVisibility(View.GONE);
            llLeaveHistoryProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundLeaveHistory.setVisibility(View.GONE);
            ApiImplementer.getStudentLeaveApplicationHistoryApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(), new Callback<LeaveApplicationHistoryPojo>() {
                @Override
                public void onResponse(Call<LeaveApplicationHistoryPojo> call, Response<LeaveApplicationHistoryPojo> response) {
                    llLeaveHistoryProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                        llStudentLeaveList.setVisibility(View.VISIBLE);
                        rvStudentLeaveList.setAdapter(new StudentLeaveApplicationHistoryAdapter(leaveApplicationActivity, (ArrayList<LeaveApplicationHistoryPojo.TableBean>) response.body().getTable()));
                    } else {
                        llStudentLeaveList.setVisibility(View.GONE);
                        llNoDataFoundLeaveHistory.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<LeaveApplicationHistoryPojo> call, Throwable t) {
                    llStudentLeaveList.setVisibility(View.GONE);
                    llLeaveHistoryProgressbar.setVisibility(View.GONE);
                    llNoDataFoundLeaveHistory.setVisibility(View.VISIBLE);
                    Toast.makeText(leaveApplicationActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(leaveApplicationActivity, "No internet connection Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

}