package com.infinity.infoway.rkuniversity.rku_old_app.CommonCls;

import android.app.Application;
import android.util.Base64DataException;
import android.widget.BaseExpandableListAdapter;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;

/**
 * Created by ADMIN on 26-04-2018.
 */

public class URLS extends Application {

//    public static String Base_URl = "http://rku.ierp.co.in/IerpHR.asmx/";


    // ****** testing url for demo *********
//    public static String Base_URl = "http://demo1.iipl.info/ierphr.asmx/";


    //public static String Base_URl = "http://iipl.iipl.info/ierphr.asmx/";


    //********** live rk url **********
//    public static String Base_URl = "http://rku.ierp.co.in/ierphr.asmx/";//OLD
    public static String Base_URl = "https://ierp.rku.ac.in/ierphr.asmx/";//NEW



    public static int TIME_TILL_DISABLE_BTN = 2000;

    public static String get_multi_compny_permission_user = Base_URl + "get_multi_compny_permission_user?";

    //********pagination data(only 20 rows per page)******
    public static String RowsPerPage = "10";

    public static String get_user_compny_permission_list = Base_URl + "get_user_compny_permission_list?";


    // ************dash-board detail API *********
    public static String Get_Dashboard_detail = Base_URl + "Get_Dashboard_detail?";


    //******* check login of user *****************
    public static String LoginCheck = Base_URl + "LoginCheck?";

    //********* display leave balance of user ***************
    public static String Get_User_LeaveBalance = Base_URl + "Get_User_LeaveBalance?";

    //************* display employee punch detail *********
    public static String Get_employee_punch_detail = Base_URl + "Get_employee_punch_detail?";

    //************* display leave listing *********
    public static String Get_leave_appliation_list = Base_URl + "Get_leave_appliation_list?";


    //*********** Bind type , reason and note *************
    public static String Get_leave_type_and_reason_and_note = Base_URl + "Get_leave_type_and_reason_and_note?";


    // **************** Miss Punch request listing ***********************
    public static String Get_miss_punch_request_list = Base_URl + "Get_miss_punch_request_list?";


    // ****************** Miss Punch Approval listing *******************
    public static String Get_miss_pucn_approve_list = Base_URl + "Get_miss_pucn_approve_list?";


    // ****************** leave approval listing ************************
    public static String Get_leave_approve_list = Base_URl + "Get_leave_approve_list?";

    //************ leave detail of edit and update *******************
    public static String Get_leave_detail = Base_URl + "Get_leave_detail?";

    //************** approve leave application ************
    public static String employee_leave_application_approval = Base_URl + "employee_leave_application_approval?";

    public static String employee_leave_application_approval_mail = Base_URl + "employee_leave_application_approval_mail?";

    public static String employee_leave_application_reject = Base_URl + "employee_leave_application_reject?";

    public static String employee_leave_application_reject_mail = Base_URl + "employee_leave_application_reject_mail?";

    public static String employee_leave_application_mst_delete = Base_URl + "employee_leave_application_mst_delete?";

    public static String Apply_Cancel_Leave_application = Base_URl + "Apply_Cancel_Leave_application?";

    public static String employee_cancel_leave_application_approve_mail = Base_URl + "employee_cancel_leave_application_approve_mail?";

    public static String Get_apply_cancel_leave_appliation_list = Base_URl + "Get_apply_cancel_leave_appliation_list?";

    public static String employee_cancel_leave_application_approve = Base_URl + "employee_cancel_leave_application_approve?";

    public static String Get_cancel_leave_appliation_approve_list = Base_URl + "Get_cancel_leave_appliation_approve_list?";

    public static String Apply_Cancel_Leave_application_mail = Base_URl + "Apply_Cancel_Leave_application_mail?";

    public static String employee_cancel_leave_application_reject = Base_URl + "employee_cancel_leave_application_reject ?";

    public static String employee_cancel_leave_application_reject_mail = Base_URl + "employee_cancel_leave_application_reject_mail?";


    public static String Employee_miss_punch_request_reject = Base_URl + "Employee_miss_punch_request_reject?";

    public static String Employee_miss_punch_request_mst_update = Base_URl + "Employee_miss_punch_request_mst_update?";

    public static String Employee_miss_punch_request_approved = Base_URl + "Employee_miss_punch_request_approved?";

    public static String Employee_miss_punch_request_mst_delete = Base_URl + "Employee_miss_punch_request_mst_delete?";

    public static String Get_miss_punch_detail = Base_URl + "Get_miss_punch_detail?";


    public static String Get_Employee_Leave_Days = Base_URl + "Get_Employee_Leave_Days?";

    public static String Get_Employee_Leave_balance = Base_URl + "Get_Employee_Leave_balance?";

    public static String Employee_leave_application_insert = Base_URl + "Employee_leave_application_insert?";


    public static String Get_employee_inout_time = Base_URl + "Get_employee_inout_time?";

    public static String Get_employee_miss_puch_date_wise_inout_time = Base_URl + "Get_employee_miss_puch_date_wise_inout_time?";

    public static String Employee_miss_punch_request_mst_insert = Base_URl + "Employee_miss_punch_request_mst_insert?";

    //*******************for attendance detail *********************
    public static String Get_employee_attendance_report_detail = Base_URl + "Get_employee_attendance_report_detail?";

    //************************* summery of attendance ***************************
    public static String Get_employee_attendance_report_summary = Base_URl + "Get_employee_attendance_report_summary?";

    public static String Employee_Forgot_password = Base_URl + "Employee_Forgot_password?";

    public static String Employee_Change_password = Base_URl + "Employee_Change_password?";

    public static String Get_employee_salary_slip = Base_URl + "Get_employee_salary_slip?";

    public static String Get_Employee_salary_slip_download = Base_URl + "Get_Employee_salary_slip_download?";

    public static String compensatory_leave_approve_list = Base_URl + "compensatory_leave_approve_list?";

    public static String compensatory_leave_approve_detail = Base_URl + "compensatory_leave_approve_detail?";

    public static String compensatory_leave_approve = Base_URl + "compensatory_leave_approve?";

    public static String Insert_mobile_tocken = Base_URl + "Insert_mobile_tocken?";

    public static String Get_Today_in_out_time = Base_URl + "Get_Today_in_out_time?";

    public static String Get_app_version = Base_URl + "Get_app_version?";

    public static String Get_miss_punch_hours_calculation = Base_URl + "Get_miss_punch_hours_calculation?";

    public static String Get_Other_Statistics = Base_URl + "Get_Other_Statistics?";

    /*pending approvals API */

    public static String Get_publication_conference_list = Base_URl + "Get_publication_conference_list?";

    public static String Get_publication_in_journal_list = Base_URl + "Get_publication_in_journal_list?";

    public static String Get_Book_Chapter_Publication_list = Base_URl + "Get_Book_Chapter_Publication_list?";

    public static String Get_Patent_awarded_list = Base_URl + "Get_Patent_awarded_list?";

    public static String Get_PG_Scholars_Guided_list = Base_URl + "Get_PG_Scholars_Guided_list?";

    public static String Get_PhD_Scholars_Guided_list = Base_URl + "Get_PhD_Scholars_Guided_list?";

    public static String Get_CPD_Application_list = Base_URl + "Get_CPD_Application_list?";

    public static String Get_Consultancy_list = Base_URl + "Get_Consultancy_list?";

    public static String Get_Grant_Received_list = Base_URl + "Get_Grant_Received_list?";

    public static String Get_Seed_Money_list = Base_URl + "Get_Seed_Money_list?";

    public static String Get_FDP_Attended_list = Base_URl + "Get_FDP_Attended_list?";

    public static String Get_FDP_Attended_list_Document = Base_URl + "Get_FDP_Attended_list_Document?";

    public static String Get_PD_Application_list = Base_URl + "Get_PD_Application_list?";

    public static String Get_PD_Application_document_list = Base_URl + "Get_PD_Application_document_list?";

    /*approve rejects API for approvals 12 dec 2019 nirali */

    public static String Get_publication_conference_Approved_or_Reject = Base_URl + "Get_publication_conference_Approved_or_Reject?";

    public static String Get_publication_in_journal_Approved_or_Reject = Base_URl + "Get_publication_in_journal_Approved_or_Reject?";

    public static String Get_Book_Chapter_Publication_Approved_or_Reject = Base_URl + "Get_Book_Chapter_Publication_Approved_or_Reject?";

    public static String Get_Patent_awarded_Approved_or_Reject = Base_URl + "Get_Patent_awarded_Approved_or_Reject?";

    public static String Get_PG_Scholars_Guided_Approved_or_Reject = Base_URl + "Get_PG_Scholars_Guided_Approved_or_Reject?";

    public static String Get_PhD_Scholars_Guided_Approved_or_Reject = Base_URl + "Get_PhD_Scholars_Guided_Approved_or_Reject?";

    public static String Get_CPD_Application_Approved_or_Reject = Base_URl + "Get_CPD_Application_Approved_or_Reject?";

    public static String Get_Consultancy_Approved_or_Reject = Base_URl + "Get_Consultancy_Approved_or_Reject?";

    public static String Get_Grant_Received_Approved_or_Reject = Base_URl + "Get_Grant_Received_Approved_or_Reject?";

    public static String Get_Seed_Money_Approved_or_Reject = Base_URl + "Get_Seed_Money_Approved_or_Reject?";

    public static String Get_FDP_Attended_Approved_or_Reject = Base_URl + "Get_FDP_Attended_Approved_or_Reject?";

    public static String Get_open_approve_pd_window_list = Base_URl + "Get_open_approve_pd_window_list?";

    public static String Get_PD_application_approve_or_reject = Base_URl + "Get_PD_application_approve_or_reject?";

    //Note that whenever changed in Get_PD_application_approve_or_reject URL then also chaneg below URL because it is same
    public static String Get_PD_application_approve_or_reject1 = Base_URl + "Get_PD_application_approve_or_reject"; //this url is changed by remish because
    // param is pass using hashmap in volly so no need of ?

    public static String PD_Approved_mail = Base_URl + "PD_Approved_mail?";

    public static String PD_Rejected_mail = Base_URl + "PD_Rejected_mail?";

    public static String Get_employee_pending_approvals = Base_URl + "Get_employee_pending_approvals?";


    public static String Get_PD_document_approve_or_reject = Base_URl + "Get_PD_document_approve_or_reject?";

    public static String Get_pd_document_approval_list = Base_URl + "Get_pd_document_approval_list?";

    public static String Get_PD_Application_document_list_PRAGNA = Base_URl + "Get_PD_Application_document_list?id=";

    public static String Get_PD_Application_brochures_document_list = Base_URl + "Get_PD_Application_brochures_document_list?id=";

    /*pd statistics*/
    public static String Get_PD_Statistics = Base_URl + "Get_PD_Statistics?user_id=";

    /*below api implemented by by remish varsani (16-03-2020)*/

    public static String Get_Academic_Contributions_year_list = Base_URl + "Get_Academic_Contributions_year_list";

    public static String Get_YEAR = Base_URl + "Get_Year";

    public static String SAVE_PUBLICATION_IN_CONFERENCE = Base_URl + "Save_publication_in_conferences";

    public static String GET_PUBLICATION_IN_CONFERENCES = Base_URl + "Get_publication_in_conferences";

    public static String VIEW_PUBLICATION_IN_CONFERENCES = Base_URl + "View_publication_in_conferences";

    public static String DELETE_PUBLICATION_IN_CONFERENCE = Base_URl + "Delete_publication_in_conferences";

    public static String UPDATE_PUBLICATION_IN_CONFERENCE = Base_URl + "Update_publication_in_conferences";

    public static String SAVE_GRANT_RECEIVED_REQUEST = Base_URl + "Save_Grant_Received_request";

    public static String Delete_Grant_Received_request = Base_URl + "Delete_Grant_Received_request";

    public static String Get_Grant_Received_request = Base_URl + "Get_Grant_Received_request";

    public static String View_by_id_Grant_Received_request = Base_URl + "View_by_id_Grant_Received_request";

    public static String Update_Grant_Received_request = Base_URl + "Update_Grant_Received_request";

    public static String Save_Seed_money = Base_URl + "Save_Seed_money";

    public static String Get_Seed_money = Base_URl + "Get_Seed_money";

    public static String Delete_Seed_money = Base_URl + "Delete_Seed_money";

    public static String View_by_id_Seed_money = Base_URl + "View_by_id_Seed_money";

    public static String Update_Seed_money = Base_URl + "Update_Seed_money";

    //PHD Scholar Guided
    public static String Save_PhD_Scholars_Guided_request = Base_URl + "Save_PhD_Scholars_Guided_request";

    public static String Get_PhD_Scholars_Guided_request = Base_URl + "Get_PhD_Scholars_Guided_request";

    public static String Delete_PhD_Scholars_Guided_request = Base_URl + "Delete_PhD_Scholars_Guided_request";

    public static String View_by_id_PhD_Scholars_Guided_request = Base_URl + "View_by_id_PhD_Scholars_Guided_request";

    public static String Update_PhD_Scholars_Guided_request = Base_URl + "Update_PhD_Scholars_Guided_request";

    //PG Scholar Guided
    public static String Save_PG_Scholars_Guided_request = Base_URl + "Save_PG_Scholars_Guided_request";

    public static String Delete_PG_Scholars_Guided_request = Base_URl + "Delete_PG_Scholars_Guided_request";

    public static String Get_PG_Scholars_Guided_request = Base_URl + "Get_PG_Scholars_Guided_request";

    public static String View_PG_Scholars_Guided_request = Base_URl + "View_PG_Scholars_Guided_request";

    public static String Update_PG_Scholars_Guided_request = Base_URl + "Update_PG_Scholars_Guided_request";

    //Patent Awarded
    public static String Save_Patent_Awarded_request = Base_URl + "Save_Patent_Awarded_request";

    public static String Get_Patent_Awarded_request = Base_URl + "Get_Patent_Awarded_request";

    public static String Delete_Patent_Awarded_request = Base_URl + "Delete_Patent_Awarded_request";

    public static String View_by_id_Patent_Awarded_request = Base_URl + "View_by_id_Patent_Awarded_request";

    public static String Update_Patent_Awarded_request = Base_URl + "Update_Patent_Awarded_request";

    //Consultancy Request
    public static String Save_Consultancy_request = Base_URl + "Save_Consultancy_request";

    public static String Update_Consultancy_request = Base_URl + "Update_Consultancy_request";

    public static String Delete_Consultancy_request = Base_URl + "Delete_Consultancy_request";

    public static String Get_Consultancy_request = Base_URl + "Get_Consultancy_request";

    public static String View_by_id_Consultancy_request = Base_URl + "View_by_id_Consultancy_request";


    //CPD Application Request

    public static String Save_CPD_Application_request = Base_URl + "Save_CPD_Application_request";

    public static String Delete_CPD_Application_request = Base_URl + "Delete_CPD_Application_request";

    public static String Get_CPD_Application_request = Base_URl + "Get_CPD_Application_request";

    public static String View_CPD_Application_request = Base_URl + "View_CPD_Application_request";

    public static String Update_CPD_Application_request = Base_URl + "Update_CPD_Application_request";

    //publication in journals

    public static String Save_Publication_in_Journals = Base_URl + "Save_Publication_in_Journals";

    public static String Delete_Publication_In_Journals = Base_URl + "Delete_Publication_In_Journals";

    public static String Get_Publication_in_Journals = Base_URl + "Get_Publication_in_Journals";

    public static String View_Publication_In_Journals = Base_URl + "View_Publication_In_Journals";

    public static String Update_Publication_in_Journals = Base_URl + "Update_Publication_in_Journals";


    //Book/Chapter publication

    public static String Save_Book_Chapter_Publication = Base_URl + "Save_Book_Chapter_Publication";

    public static String Dalete_Boook_Chapter_Publication = Base_URl + "Dalete_Boook_Chapter_Publication";

    public static String Get_Book_Chapter_Publication = Base_URl + "Get_Book_Chapter_Publication";

    public static String View_Boook_Chapter_Publication = Base_URl + "View_Boook_Chapter_Publication";

    public static String Update_Book_Chapter_Publication = Base_URl + "Update_Book_Chapter_Publication";

    //FDP Attended
    public static String Get_Event_Type = Base_URl + "Get_Event_Type";

    public static String Get_Event_Category = Base_URl + "Get_Event_Category";

    public static String Insert_File_Upload_FDP_Attended_request = Base_URl + "Insert_File_Upload_FDP_Attended_request";

    public static String Save_FDP_Attended_request = Base_URl + "Save_FDP_Attended_request";

    public static String Delete_FDP_Attended_request = Base_URl + "Delete_FDP_Attended_request";

    public static String Delete_File_Upload_FDP_Attended_request = Base_URl + "Delete_File_Upload_FDP_Attended_request";

    public static String Get_FDP_Attended_request = Base_URl + "Get_FDP_Attended_request";

    public static String View_by_id_FDP_Attended_request = Base_URl + "View_by_id_FDP_Attended_request";

    public static String Update_FDP_Attended_request = Base_URl + "Update_FDP_Attended_request";

    //Investment
    public static String Get_Financial_Year = Base_URl + "Get_Financial_Year";

    public static String Get_Investment_Name_by_conver_id = Base_URl + "Get_Investment_Name_by_conver_id";

    public static String Save_update_investment = Base_URl + "Save_update_investment";

    public static String Get_Investment_list = Base_URl + "Get_Investment_list";

    public static String Delete_Investment = Base_URl + "Delete_Investment";

    public static String Get_Investment_detail_by_id = Base_URl + "Get_Investment_detail_by_id";


    //Committee

    public static String Save_Update_Committee = Base_URl + "Save_Update_Committee";

    public static String Delete_Committee = Base_URl + "Delete_Committee";

    public static String View_by_id_Committee = Base_URl + "View_by_id_Committee";

    public static String Get_employee_ID_Wise_Name = Base_URl + "Get_employee_ID_Wise_Name";

    public static String Get_Committee = Base_URl + "Get_Committee";


    //Committee Member

    public static String Get_Committee_Name = Base_URl + "Get_Committee_Name";

    public static String Get_Committee_Employee_Name = Base_URl + "Get_Committee_Employee_Name";

    public static String Save_Committee_Wise_Member = Base_URl + "Save_Committee_Wise_Member";

    public static String Update_Committee_Wise_Member = Base_URl + "Update_Committee_Wise_Member";

    public static String Delete_Committee_wise_memeber = Base_URl + "Delete_Committee_wise_memeber";

    public static String Get_Committee_wise_memeber = Base_URl + "Get_Committee_wise_memeber";

    public static String View_Committee_wise_memeber = Base_URl + "View_Committee_wise_memeber";

    public static String Delete_Committee_wise_employee_name = Base_URl + "Delete_Committee_wise_employee_name";


    //Committee Details

    public static String Get_List_Of_Committee_Wise_Member_Details = Base_URl + "Get_List_Of_Committee_Wise_Member_Details";


    //Employee EProfile Details

    public static String Get_employee_e_profile = Base_URl + "Get_employee_e_profile";

    //Request For Resignation

    public static String Apply_resignation_process = Base_URl + "Apply_resignation_process";

    public static String Get_resignation_user_wise_Details = Base_URl + "Get_resignation_user_wise_Details";


    //Pd Application
    public static String Get_Event_Role = Base_URl + "Get_Event_Role";

    public static String Insert_File_Upload_PD_Application = Base_URl + "Insert_File_Upload_PD_Application";

    public static String Save_Update_PD_Application = Base_URl + "Save_Update_PD_Application";

    public static String Get_i_hrhr_PD_Application_data = Base_URl + "Get_i_hrhr_PD_Application_data";

    public static String Delete_PD_Application = Base_URl + "Delete_PD_Application";

    public static String Delete_File_Upload_PD_Application = Base_URl + "Delete_File_Upload_PD_Application";

    public static String View_by_id_PD_Application = Base_URl + "View_by_id_PD_Application";

    public static String Insert_Approved_Record_Document_Upload_PD_Application = Base_URl + "Insert_Approved_Record_Document_Upload_PD_Application";

    public static String Delete_Approved_Record_Document_Upload_PD_Application = Base_URl + "Delete_Approved_Record_Document_Upload_PD_Application";

    public static String Get_i_hrhr_PD_approval_remark = Base_URl + "Get_i_hrhr_PD_approval_remark";

    public static String Print_of_Approved_record_by_id_PD_Application = Base_URl + "Print_of_Approved_record_by_id_PD_Application";

    public static String pd_update_creadit = Base_URl + "pd_update_creadit";

    //Academic Contribution Report
    public static String Get_Academic_Contributon_Report = Base_URl + "Get_Academic_Contributon_Report";

    //Daily attendance Report
    public static String Get_Daily_Attendance_Report = Base_URl + "Get_Daily_Attendance_Report";

    //Employee Details Report
    public static String Get_Employee_Detail_Report = Base_URl + "Get_Employee_Detail_Report";

    //Leave balance Report
    public static String Get_leave_balance_Report = Base_URl + "Get_leave_balance_Report";

    //Below api are commented by remish and this branch and department API are used to get all branch and department list
//    public static String Get_Branch = Base_URl + "Get_Branch";

//    public static String Get_Department = Base_URl + "Get_Department";

    //Below branch and department api are used to get list of branch and department as per writes
    // Ex. if user login to app and
    // he/she belong to computer department then this two api will only
    // give branch and department of that particular department.
    //Added by remish on 17-08-2020
    public static String Get_Employee_wise_Branch = Base_URl + "Get_Employee_wise_Branch";

    public static String Get_Employee_wise_Department = Base_URl + "Get_Employee_wise_Department";


    //Qualification Report
    public static String Get_Qualification_Report = Base_URl + "Get_Qualification_Report";

    //Employee Payroll details Report
    public static String Get_Employee_Payroll_Details_Report = Base_URl + "Get_Employee_Payroll_Details_Report";

    //Employee Branch Transfer Report
    public static String Get_Employee_Branch_Transfer_Report = Base_URl + "Get_Employee_Branch_Transfer_Report";

    //Seed Money University Summary Report
    public static String Get_University_Seed_Money_Summary_Report = Base_URl + "Get_University_Seed_Money_Summary_Report";

    //Get Employee Month Wise Miss Punch Report
    public static String Get_Employee_Monthwise_Mis_Punch_Report = Base_URl + "Get_Employee_Monthwise_Mis_Punch_Report";

    //Get Employee Monthwise late Report
    public static String Get_Employee_Monthwise_Late_Report = Base_URl + "Get_Employee_Monthwise_Late_Report";

    //Get Employee Late Coming Report
    public static String Get_Employee_Late_Coming_Report = Base_URl + "Get_Employee_Late_Coming_Report";

    //All Employee Attendance Report
    public static String Get_All_Employee_Attendance_Report = Base_URl + "Get_All_Employee_Attendance_Report";

    public static String Get_Designation = Base_URl + "Get_Designation";

    //PD Statistic Report for Staff
    public static String Get_PD_Statistics_Report_for_Staff = Base_URl + "Get_PD_Statistics_Report_for_Staff";

    //Display User Wise Menu
    public static String Get_All_User = Base_URl + "Get_All_User";

    public static String Get_All_Module = Base_URl + "Get_All_Module";

    public static String Get_All_Parent_Menu = Base_URl + "Get_All_Parent_Menu";

    public static String Get_All_Sub_Menu_by_Parent_Menu = Base_URl + "Get_All_Sub_Menu_by_Parent_Menu";

    public static String Get_Menu_Wise_Right = Base_URl + "Get_Menu_Wise_Right";

    public static String Delete_User_wise_Menu_Permission = Base_URl + "Delete_User_wise_Menu_Permission";

    public static String Save_User_wise_Menu_Permission = Base_URl + "Save_User_wise_Menu_Permission";

    //For Displaying Report (Report list come from API)
    public static String Get_Employee_wise_Report_right = Base_URl + "Get_Employee_wise_Report_right";
    public static String GET_FILTER_YEAR = Base_URl + "Get_filter_Year";
}
