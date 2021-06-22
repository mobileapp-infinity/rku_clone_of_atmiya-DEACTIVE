package com.infinity.infoway.rkuniversity.student.leave_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.LeaveApplicationHistoryPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentLeaveApplicationHistoryAdapter extends RecyclerView.Adapter<StudentLeaveApplicationHistoryAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<LeaveApplicationHistoryPojo.TableBean> tableBeanArrayList;

    public StudentLeaveApplicationHistoryAdapter(Context context, ArrayList<LeaveApplicationHistoryPojo.TableBean> tableBeanArrayList) {
        this.context = context;
        this.tableBeanArrayList = tableBeanArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_leave_application_history_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LeaveApplicationHistoryPojo.TableBean tableBean = tableBeanArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getLeave_apply_date())) {
            String dateArray[] = tableBean.getLeave_apply_date().split("/");
            String date = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + "," + "\n" + dateArray[2];
            holder.tvLeaveApplyDate.setText(date + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getLeaveType())) {
            holder.tvLeaveType.setText(tableBean.getLeaveType() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getLeaveDate())) {
            holder.tvLeaveDate.setText(tableBean.getLeaveDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getStudRemarks())) {
            holder.tvLeaveRemaks.setText(tableBean.getStudRemarks() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getAppStatus())) {
            if (tableBean.getAppStatus().equalsIgnoreCase("0")) {
                holder.leaveStatus.setText("Pending");
            } else if (tableBean.getAppStatus().equalsIgnoreCase("1")) {
                holder.leaveStatus.setText("Approve");
            } else {
                holder.leaveStatus.setText("Rejected");
            }
        }


    }


    @Override
    public int getItemCount() {
        return tableBeanArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvLeaveApplyDate, tvLeaveType,
                tvLeaveDate, tvLeaveRemaks, leaveStatus;

//        TextViewRegularFont tvKindOfLeaveType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeaveApplyDate = itemView.findViewById(R.id.tvLeaveApplyDate);
//            tvKindOfLeaveType = itemView.findViewById(R.id.tvKindOfLeaveType);
            tvLeaveDate = itemView.findViewById(R.id.tvLeaveDate);
            tvLeaveType = itemView.findViewById(R.id.tvLeaveType);
            tvLeaveRemaks = itemView.findViewById(R.id.tvLeaveRemaks);
            leaveStatus = itemView.findViewById(R.id.leaveStatus);
        }
    }

}
