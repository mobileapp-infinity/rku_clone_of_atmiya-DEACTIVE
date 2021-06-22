package com.infinity.infoway.rkuniversity.rku_old_app.constants;

public class CommanConstants {

    public enum State{
        STATE_APPROVE(0),
        STATE_PENDING(100);

        private int type;

        State(int i){
            this.type = i;
        }

        public int getStateValue(){
            return type;
        }

    }

    //As per discussion with rashmikant sir below report url is static
    //when we need or any requirement to add new Report than ask rashmikant sir for that Report URL
    // (ex. print_hrhr_detailed_attendance_report.aspx) and
    // also ask is that report is ready in web or not and if report is ready in web than define variable
    // //for that report as below and add condition to ReportListActivity to Show that Report.

    public static final String FOR_ACADEMIC_CONTRIBUTION_REPORT = "print_hrhr_academic_contribution_summary_report.aspx";
    public static final String FOR_DAILY_ATTENDANCE_REPORT  = "print_hrhr_detailed_attendance_report.aspx";
    public static final String FOR_EMPLOYEE_DETAIL_REPORT = "form_hrhr_parent_employee_wise_employee_report.aspx";
    public static final String FOR_LEAVE_BALANCE_REPORT = "print_hrhr_all_employee_month_wise_leave_balance_report.aspx";
    public static final String FOR_QUALIFICATION_REPORT = "print_hrhr_Qualification_Summary_Report.aspx";
    public static final String FOR_EMPLOYEE_BRANCH_TRANSFER_REPORT = "print_hrhr_employee_branch_transfer_report.aspx";
    public static final String FOR_UNIVERSITY_SEED_MONEY_SUMMARY_REPORT = "print_hrhr_university_seed_money_summary_report.aspx";
    public static final String FOR_EMPLOYEE_LATE_COMMING_REPORT = "Print_hrhr_employee_late_coming_report.aspx";
    public static final String FOR_EMPLOYEE_MONTH_WISE_MISPUNCH_REPORT = "print_hrhr_employee_month_wise_mispunch_report.aspx";
    public static final String FOR_EMPLOYEE_MONTH_WISE_LATE_REPORT = "print_hrhr_employee_month_wise_late_report.aspx";
    public static final String FOR_ALL_EMPLOYEE_ATTENDANCE_REPORT = "print_hrhr_present_report.aspx";
    public static final String FOR_PD_STATISTICS_FOR_STAFF = "Print_hrhr_all_employee_pd_statastic_report.aspx";

}
