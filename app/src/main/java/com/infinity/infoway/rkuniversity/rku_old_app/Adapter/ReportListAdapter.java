package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.AcademicContributionReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.AllEmployeeAttendanceReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.DailyAttendanceReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.EmployeeBranchTransferReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.EmployeeDetailReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.EmployeeLateComingReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.EmployeeLeaveBalanceReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.EmployeeMonthWiseMissPunchReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.EmployeePayrollDetailReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.MonthWiseLateReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PDStatisticsReportForStaffActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.QualificationReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.UniversitySeedMoneySummaryReportActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.models.ReportListModel;

import java.util.ArrayList;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.MyViewHolder> {

    Context context;
    ArrayList<ReportListModel> reportArrayList;
    LayoutInflater inflater;

    public ReportListAdapter(Context context, ArrayList<ReportListModel> reportArrayList) {
        this.context = context;
        this.reportArrayList = reportArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ReportListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.inflater_report_list_row, viewGroup, false);
        return new ReportListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportListAdapter.MyViewHolder myViewHolder, final int position) {
        if (reportArrayList != null && reportArrayList.size() > 0) {
            myViewHolder.ivReport.setImageResource(reportArrayList.get(position).getIcone());
            myViewHolder.tvReport.setText(reportArrayList.get(position).getReportTitle());

            myViewHolder.llReportMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Academic Contribution Summary Report")) {
                        Intent intent = new Intent(context, AcademicContributionReportActivity.class);
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Daily Attendance Report")) {
                        Intent intent = new Intent(context, DailyAttendanceReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Employee Detail Report")) {
                        Intent intent = new Intent(context, EmployeeDetailReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Leave Balance Report")) {
                        Intent intent = new Intent(context, EmployeeLeaveBalanceReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Qualification Summary Report")) {
                        Intent intent = new Intent(context, QualificationReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Employee Payroll Detail Report")) {
                        Intent intent = new Intent(context, EmployeePayrollDetailReportActivity.class);
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Employee Branch Transfer Report")) {
                        Intent intent = new Intent(context, EmployeeBranchTransferReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("University Seed Money Summary Report")) {
                        Intent intent = new Intent(context, UniversitySeedMoneySummaryReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Employee Late Coming Report")) {
                        Intent intent = new Intent(context, EmployeeLateComingReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Employee Month Wise Mis Punch Report")) {
                        Intent intent = new Intent(context, EmployeeMonthWiseMissPunchReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("Employee Month Wise Late Report")) {
                        Intent intent = new Intent(context, MonthWiseLateReportActivity.class);
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("All Employee Attendance Report")) {
                        Intent intent = new Intent(context, AllEmployeeAttendanceReportActivity.class);//Done
                        context.startActivity(intent);
                    } else if (reportArrayList.get(position).getReportTitle().equalsIgnoreCase("PD Statistics Report For Staff")) {
                        Intent intent = new Intent(context, PDStatisticsReportForStaffActivity.class);//Done
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return reportArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llReportMain;
        ImageView ivReport;
        CustomTextView tvReport;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivReport = itemView.findViewById(R.id.ivReport);
            tvReport = itemView.findViewById(R.id.tvReport);
            llReportMain = itemView.findViewById(R.id.llReportMain);
        }
    }

}

