package com.infinity.infoway.rkuniversity.api;

import com.infinity.infoway.rkuniversity.event_calender.EventModel;
import com.infinity.infoway.rkuniversity.faculty.faculty_attendance.FacultyAttendancePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.pojo.CoOrdinatorGetCollegePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.pojo.CoOrdinatorGetCoursePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.pojo.CoOrdinatorGetDepartmentPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.pojo.CoOrdinatorGetSemesterPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.pojo.CoOrdinatorLeaveListPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.pojo.UpdateFaultyFCMTokenPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_direct_login_to_student.FacultyStudentListPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_direct_login_to_student.GetStudentDetailsForDirectLoginPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_extra_lecture_approval.ApproveOrRejectExtraLecturePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_extra_lecture_approval.ExtraLectureApprovalPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.FacultyFillAttendanceConfigurationPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.FacultyPendingAttendanceUnitPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetTeachingAidDetailsPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetTeachingMethodPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.InsertAllAbsentStudentByAlternateMethodPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.InsertAllPresentStudentByAlternateMethodPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.InsertClassWiseAttendancePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.StudentDetailsFillAttendancePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.UpdateDailyLectureWiseAttendanceStatusPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.HODAndCoOrdinatorApprovLeavePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODGetCollegePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODGetCoursePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODGetDepartmentPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODGetSemesterPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODStudentLeaveApproveApplicationAllPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_leave.FacultyLeavePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_lecture_plan.FacultyLecturePlanPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_news.FacultyNewsPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_pending_attendance.FacultyPendingAttendancePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_profile.FacultyProfilePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_rem_attendance.FacultyRemAttendancePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_student_messages.pojo.StudentMsgListPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_suspended_lecture_approval.pojo.GetSuspensionListPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_adviser_remarks.FacultyAdviserRemarksListPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_details_of_theory_sub.FacultyDetailsOfTheorySubjectTaughtPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_lab_or_lecture_plan_teaching_update.FacultyLabOrLecturePlanTeachingUpdatePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_student_forum.FacultyStudentForumPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_subject_wise_division_wise_total_theory_period_engaged.FacultySubAndDivWiseTotTheoryPeriodEngagedPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.pojo.FacultyTimeTablePojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.CheckLoginByOTPAndUsernamePojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.CheckOTPVerificationForEmployeePojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.CheckOTPVerificationForStudentPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetForgetPasswordDetailByUserIDPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetInstituteFromDomainPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetSMSApiForApplicationPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetStudentForgotPasswordDetailsPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetUserWiseDetailForgetPasswordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertForgetPasswordSendSMSRecordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertForgotPasswordOTPSmsRecordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertStudentPasswordAndSMSAbsentApiCall;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.OtpBaseLoginDetailsForEmployeePojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.OtpBaseLoginDetailsForStudentPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.ResetEmployeePasswordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.ResetStudentPasswordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.ResetUserPasswordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.UpdateStudentForgotPasswordOtpPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.UpdateUserOTPAtForgetPasswordPojo;
import com.infinity.infoway.rkuniversity.login.pojo.CheckVersionApiPojo;
import com.infinity.infoway.rkuniversity.login.pojo.CommonNewImageSliderPojo;
import com.infinity.infoway.rkuniversity.login.pojo.EmployeeLoginPojo;
import com.infinity.infoway.rkuniversity.login.pojo.LogOutPojo;
import com.infinity.infoway.rkuniversity.login.pojo.StudentLoginPojo;
import com.infinity.infoway.rkuniversity.student.assignment.StudentAssignmentListPojo;
import com.infinity.infoway.rkuniversity.student.attendance.pojo.StudentLectureWiseAttendancePojo;
import com.infinity.infoway.rkuniversity.student.attendance.pojo.StudentSubjectWiseAttendancePojo;
import com.infinity.infoway.rkuniversity.student.contact_us.ContactUsPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.CheckIsELearningManagementGroupIsCompulsoryOrNot;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.CheckIsLearningManagementGroupIsExistOrNotPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.ELearningYearListPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.EnrollToGroupPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.GroupWiseSubjectlistPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.InsertStudentLearningManagementPushNotificationPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.JoinGroupListPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.LearningManagementGroupDetailsPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.StudentWiseLearningGroupPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.CIAExamResultPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.CIAExamSemesterPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadExaminationSchedulePojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadHallTicketExaminationSchedulePojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadStudentMidResultPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.ExaminationScheduleDetailsPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.ExaminationScheduleProgramWiseTimetablePojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.MidResultPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentCIAMarksPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentMidMarksPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentReulstPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.FeeReceiptPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.FeeUrlPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.GetAllPendingFeeStudentPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.GetPaymentButtonHideShowPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.GetPaymentSingleButtonHideShowPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PayFeeTypePojoList;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PaySlipOfAxisFeeTypePojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PaySlipOfAxisPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PayWithPaytmPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PrintFeeReceiptPojo;
import com.infinity.infoway.rkuniversity.student.holiday.HolidayListPojo;
import com.infinity.infoway.rkuniversity.student.home_work.pojo.StudentHomeWorkPojo;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.CheckStudentLeaveExistPojo;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.InsertStudentLeavePojo;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.KindOfLeaveListPojo;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.LeaveApplicationHistoryPojo;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.SelectLectureForPartialLeavePojo;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.TypeOfFileUploadPojo;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.UploadStudentLeaveDocumentPojo;
import com.infinity.infoway.rkuniversity.student.lesson_plan.StudentLessonPlanListPojo;
import com.infinity.infoway.rkuniversity.student.message_history.MessageHistoryListPojo;
import com.infinity.infoway.rkuniversity.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.rkuniversity.student.news_or_notification.UpdateNotificationStatusPojo;
import com.infinity.infoway.rkuniversity.student.profile.StudentProfilePojo;
import com.infinity.infoway.rkuniversity.student.student_activity.StudentActivityPojo;
import com.infinity.infoway.rkuniversity.student.student_dashboard.pojo.UpdateStudentFCMTokenPojo;
import com.infinity.infoway.rkuniversity.student.student_msg_to_teacher.pojo.GetPersonPojo;
import com.infinity.infoway.rkuniversity.student.student_msg_to_teacher.pojo.InsertMsgPojo;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetFeeTypePojo;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetPendingFeeListFromFeeTypePojo;
import com.infinity.infoway.rkuniversity.student.student_syllabus.SyllabusListPojo;
import com.infinity.infoway.rkuniversity.student.student_timetable.pojo.StudentTimeTablePojo;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

public interface IApiInterface {

    //TODO below API are for student side modules

    @GET("student_login_check_api")
    Call<StudentLoginPojo> checkStudentLogin(@QueryMap Map<String, String> params);

    @GET("get_student_profile_detail_atmiya")
    Call<StudentProfilePojo> getStudentProfile(@QueryMap Map<String, String> params);

    @GET("get_student_attendance_api")
    Call<ArrayList<StudentLectureWiseAttendancePojo>> getStudentLectureWiseAttendance(@QueryMap Map<String, String> params);

    @GET("get_subject_name_to_display_attendance_in_student_profile")
    @Streaming
    Call<ArrayList<StudentSubjectWiseAttendancePojo>> getStudentSubjectWiseAttendance(@QueryMap Map<String, String> params);


//    @GET("Fee_Receipt_List")
//    Call<ArrayList<FeeReceiptPojo>> getFeeReceipt(@Query("stud_id") String stud_id);

    //new api for fee receipt for only rk as per discussion with nikunj sir and krishna madam
    @GET("get_student_fee_installment_detail_API")
    Call<ArrayList<FeeReceiptPojo>> getFeeReceipt(@Query("stud_id") String stud_id);

    @GET("Print_Fee_Receipt")
    @Streaming
    Call<PrintFeeReceiptPojo> downloadFeeReceipt(@Query("stud_id") String stud_id, @Query("Receipt_no") String receipt_no);

    @GET("Get_Student_All_Exam_Detail_By_Enrollment_no")
    Call<ArrayList<StudentReulstPojo>> getStuedntResultList(@Query("enrollement_no") String enrollement_no);

    @GET("Download_Student_Exam_Result")
    @Streaming
    Call<CIAExamResultPojo> downloadCIAExamResult(@Query("Collegeid") String Collegeid, @Query("Yearid") String Yearid, @Query("Semid") String Semid,
                                                  @Query("Exam_id") String Exam_id, @Query("stud_id") String stud_id);


    @GET("Get_Exam_Details_by_stud_id_API")
    Call<ExaminationScheduleDetailsPojo> getExaminationScheduleDetails(@Query("stud_id") String stud_id,
                                                                       @Query("exam_db") String exam_db);

    @GET("Get_Program_wise_timetable_by_stud_id_API")
    Call<ExaminationScheduleProgramWiseTimetablePojo> getExaminationScheduleProgramWiseTimetable(@Query("sem_id") String sem_id,
                                                                                                 @Query("year_id") String year_id,
                                                                                                 @Query("stud_id") String stud_id,
                                                                                                 @Query("repeater_status") String repeater_status,
                                                                                                 @Query("exam_db") String exam_db);

    @GET("Download_AU_Examination_Schedule")
    Call<DownloadExaminationSchedulePojo> downloadExaminationSchedule(@Query("sem_id") String sem_id,
                                                                      @Query("year_id") String year_id,
                                                                      @Query("stud_id") String stud_id,
                                                                      @Query("repeater_status") String repeater_status,
                                                                      @Query("exam_db") String exam_db);


//    @GET("Print_Axis_Payslip")
//    @Streaming
//    Call<PaySlipOfAxisPojo> downloadPaySlipOfAxis(@Query("stud_id") String stud_id);

    @GET("Print_Axis_Payslip_with_payment_code")
    @Streaming
    Call<PaySlipOfAxisPojo> downloadPaySlipOfAxis(@Query("stud_id") String stud_id, @Query("fee_type") String fee_type);

    @GET("BindFeeType")
    @Streaming
    Call<ArrayList<PaySlipOfAxisFeeTypePojo>> getFeeTypePaySlipOfAxis(@Query("stud_id") String stud_id);

    @GET("Get_Student_Assignment_Detail")
    @Streaming
    Call<ArrayList<StudentAssignmentListPojo>> getStudentAssignmentList(@Query("stud_id") String stud_id, @Query("year_id") String year_id);


    @GET("get_student_sms_send_detail_with_content")
    @Streaming
    Call<ArrayList<MessageHistoryListPojo>> getStudentMessageHistory(@Query("stud_id") String stud_id, @Query("year_id") String year_id);


    @GET("get_leave_type_institute_wise_for_student")
    Call<KindOfLeaveListPojo> getKindOfLeaveList(@Query("institute_id") String institute_id);

    @GET("get_leave_type_file_upload")
    Call<TypeOfFileUploadPojo> getTypeOfLeaveFileUpload(@Query("leave_type_id") String leave_type_id);

    @GET("Get_Date_Wise_Lecture_List_for_student_leave")
    Call<SelectLectureForPartialLeavePojo> selectLectureForPartialLeaveList(@Query("institute_id") String institute_id, @Query("sem_id") String sem_id,
                                                                            @Query("div_id") String div_id, @Query("stud_id") String stud_id,
                                                                            @Query("from_date") String from_date, @Query("year_id") String year_id);

    @GET("Check_Student_Leave_Exist_API")
    Call<CheckStudentLeaveExistPojo> checkStudentLeaveExistOrNot(@Query("stud_id") String stud_id, @Query("year_id") String year_id,
                                                                 @Query("leave_from_date") String leave_from_date, @Query("leave_to_date") String leave_to_date,
                                                                 @Query("leave_day_type") String leave_day_type, @Query("lect_no") String lect_no);


    @GET("insert_student_leave_API")
    Call<InsertStudentLeavePojo> insertStudentLeave(@Query("student_id") String student_id, @Query("leave_type_id") String leave_type_id,
                                                    @Query("from_date") String from_date, @Query("to_date") String to_date,
                                                    @Query("leave_day_type") String leave_day_type, @Query("lecture_date") String lecture_date,
                                                    @Query("lecture_no") String lecture_no, @Query("Remarks") String Remarks,
                                                    @Query("year_id") String year_id, @Query("created_by") String created_by,
                                                    @Query("created_ip") String created_ip, @Query("leave_taken_by") String leave_taken_by);

    @FormUrlEncoded
    @POST("Upload_Student_Leave_Document")
    Call<UploadStudentLeaveDocumentPojo> uploadStudentLeaveDocument(@Field("name") String name,
                                                                    @Field("image") String image,
                                                                    @Field("stud_leave_id") String stud_leave_id);

    @GET("get_student_leave_application_data_API")
    Call<LeaveApplicationHistoryPojo> getStudentLeaveApplicationHistory(@Query("stud_id") String stud_id,
                                                                        @Query("year_id") String year_id);

    @GET("Get_Student_Wise_Division_Wise_Student_Activity_API")
    Call<ArrayList<StudentActivityPojo>> getStudentActivityList(@Query("stud_id") String stud_id,
                                                                @Query("url") String url);

    @GET("Get_Year_Master_At_Learning_Managemnt_API")
    Call<ELearningYearListPojo> getELearningYear(@Query("institute_id") String institute_id);

    @GET("Get_Student_Wise_Learning_Management_Group_API")
    Call<StudentWiseLearningGroupPojo> getStudentWiseLearningGroup(@Query("stud_id") String stud_id);

    @GET("Check_Learning_Management_Group_is_Compulsory_or_Not_API")
    Call<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> checkIsELearningGroupIsCompulsoryOrNot(@Query("grp_id") String grp_id);

    @GET("Get_Learning_Management_Groups_For_Students_API")
    Call<ArrayList<JoinGroupListPojo>> getJoinLearningManagementGroups(@Query("stud_id") String stud_id,
                                                                       @Query("year_id") String year_id);

    @GET("Get_Group_Wise_Learning_Management_Subject_Details_API")
    Call<ArrayList<GroupWiseSubjectlistPojo>> groupWiseLearningManagementSubjectList(@Query("stud_id") String stud_id,
                                                                                     @Query("sem_id") String sem_id,
                                                                                     @Query("year_id") String year_id,
                                                                                     @Query("grp_id") String grp_id);

    @GET("Get_Learning_Management_Group_Details_Group_Wise_New_API")
    Call<ArrayList<LearningManagementGroupDetailsPojo>> groupWiseLearningManagementDocumentList(@Query("grp_id") String grp_id,
                                                                                                @Query("stud_id") String stud_id,
                                                                                                @Query("sem_id") String sem_id,
                                                                                                @Query("year_id") String year_id,
                                                                                                @Query("from_date") String from_date,
                                                                                                @Query("to_date") String to_date,
                                                                                                @Query("sub_id") String sub_id);

    @GET("Check_Exist_Learning_Mangement_Group_Wise_Student_Master_API")
    Call<CheckIsLearningManagementGroupIsExistOrNotPojo> checkIsLearningManagementGroupIsExistOrNot(@Query("grp_id") String grp_id,
                                                                                                    @Query("stud_id") String stud_id);


    @GET("Insert_Student_To_Learning_Management_Group_Wise_Student_Master_API")
    Call<EnrollToGroupPojo> enrollToGroup(@Query("grp_id") String grp_id,
                                          @Query("stud_id") String stud_id,
                                          @Query("created_by") String created_by,
                                          @Query("created_ip") String created_ip,
                                          @Query("lm_grpws_is_enroll_status") String lm_grpws_is_enroll_status);


    @GET("Insert_Student_Learning_Management_Push_Notification_API")
    Call<ArrayList<InsertStudentLearningManagementPushNotificationPojo>> insertStudentLearningManagementPushNotification(
            @Query("grp_id") String grp_id,
            @Query("year_id") String year_id,
            @Query("stud_id") String stud_id,
            @Query("sem_id") String sem_id,
            @Query("nt_type") String nt_type,
            @Query("notif_desc") String notif_desc,
            @Query("read_status") String read_status,
            @Query("created_by") String created_by,
            @Query("created_ip") String created_ip,
            @Query("institute_id") String institute_id);

    @GET("Get_Student_Wise_Holidays_Detail_API")
    Call<ArrayList<HolidayListPojo>> getStudentHolidayList(
            @Query("stud_id") String stud_id);


    @GET("get_student_timetable_api_lopgin")
    Call<ArrayList<StudentTimeTablePojo>> getStudentTimetable(
            @Query("stud_id") String stud_id,
            @Query("course_id") String course_id,
            @Query("div_id") String div_id,
            @Query("shift_id") String shift_id,
            @Query("batch_id") String batch_id,
            @Query("year_id") String year_id);


    @GET("Get_Student_Current_sem_syllabus")
    Call<ArrayList<SyllabusListPojo>> getStudentSyllabusList(
            @Query("stud_id") String stud_id);

    @GET("Student_Lesson_Planning_API")
    Call<ArrayList<StudentLessonPlanListPojo>> getStudentLessonPlanList(
            @Query("stud_id") String stud_id);

    @GET("GetPendingFeeType")
    Call<ArrayList<PayFeeTypePojoList>> getPendingFeeType(
            @Query("StudId") String StudId,
            @Query("YearId") String YearId,
            @Query("AdmissionNo") String AdmissionNo,
            @Query("CompanyCode") String CompanyCode,
            @Query("hostel_code") String hostel_code);

    @GET("GetPendingFeeStudentURL")
    Call<FeeUrlPojo> feeUrlPojo(
            @Query("Ord_stud_id") String Ord_stud_id,
            @Query("Ord_year_id") String Ord_year_id,
            @Query("Ord_Exam_id") String Ord_Exam_id,
            @Query("Ord_sem_id") String Ord_sem_id,
            @Query("Ord_Fee_Source") String Ord_Fee_Source,
            @Query("Ord_Fee_Type") String Ord_Fee_Type,
            @Query("Ord_created_ip") String Ord_created_ip);

    @GET("GetPaymentButtonHideShow")
    Call<ArrayList<GetPaymentButtonHideShowPojo>> getPaymentButtonHideShow(
            @Query("StudId") String StudId,
            @Query("FeeType") String FeeType);

    @GET("GetPaymentSingleButtonHideShow")
    Call<ArrayList<GetPaymentSingleButtonHideShowPojo>> getPaymentSingleButtonHideShow();

    @GET("GetAllPendingFeeStudent")
    Call<ArrayList<GetAllPendingFeeStudentPojo>> getAllPendingFeeStudent(
            @Query("StudId") String StudId,
            @Query("YearId") String YearId,
            @Query("AdmissionNo") String AdmissionNo,
            @Query("ClassName") String ClassName,
            @Query("CompanyCode") String CompanyCode,
            @Query("FeeType") String FeeType,
            @Query("hostel_code") String hostel_code);


    @GET("Get_Student_CIA_Marks")
    Call<ArrayList<StudentCIAMarksPojo>> getStudentCIAMarks(
            @Query("stud_id") String stud_id,
            @Query("sem_id") String sem_id,
            @Query("exam_type") String exam_type);

    @GET("Get_Semester_By_Course_Marks_Display")
    Call<CIAExamSemesterPojo> getCIAExamSemester(
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("dept_id") String dept_id);

    @GET("get_result_publish_configuration_semester_wise_for_api")
    Call<ArrayList<MidResultPojo>> getStudentMidResult(
            @Query("institute_id") String institute_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("stud_id") String stud_id,
            @Query("srpc_id") String srpc_id);

    @GET("get_stud_publish_result_download")
    Call<DownloadStudentMidResultPojo> downloadStudentMidResult(
            @Query("sem_id") String sem_id,
            @Query("srpc_id") String srpc_id,
            @Query("stud_id") String stud_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id);


    @GET("Get_Institute_From_Domain_API")
    Call<GetInstituteFromDomainPojo> getInstituteFromDomain(
            @Query("domain_name") String domain_name);

    @GET("get_student_forget_password_detail")
    Call<ArrayList<GetStudentForgotPasswordDetailsPojo>> getStudentForgetPassDeail(@Query("email") String email);

    //new Api Added By harsh
    @GET("Get_Forget_Password_Detail_by_User_ID")
    Call<ArrayList<GetForgetPasswordDetailByUserIDPojo>> getForgetPasswordDetailByUserID(
            @Query("user_id") String user_id,
            @Query("user_type") String user_type,
            @Query("domain_name") String domain_name);

    @GET("GET_SMS_API_For_Application")
    Call<ArrayList<GetSMSApiForApplicationPojo>> getSMSApiForApplication(
            @Query("institute_id") String institute_id);

    @GET("Update_Isrp_Student_FCM_Id")
    Call<UpdateStudentFCMTokenPojo> updateStudentFcmToken(
            @Query("stud_id") String stud_id,
            @Query("FCM_ID") String FCM_ID,
            @Query(value = "KEY", encoded = true) String KEY);

    @GET("get_otp_base_login_details_for_emp_API")
    Call<OtpBaseLoginDetailsForEmployeePojo> getOTPBaseLoginDetailsForEmployee(
            @Query("uname_mobile") String uname_mobile,
            @Query("institute_id") String institute_id);

    @GET("get_otp_base_login_details_for_stud_API")
    Call<OtpBaseLoginDetailsForStudentPojo> getOTPBaseLoginDetailsForStudent(
            @Query("uname_mobile") String uname_mobile,
            @Query("institute_id") String institute_id);

    @GET("insert_stduent_password_send_sms_absent_API")
    Call<InsertStudentPasswordAndSMSAbsentApiCall> insertStudentPasswordAndSendSmsAbsent(
            @Query("sms") String sms);

    @GET("Insert_Forget_Password_OTP_SMS_Record_API")
    Call<InsertForgotPasswordOTPSmsRecordPojo> insertForgotPasswordOTPSmsRecordApi(
            @Query("user_id") String user_id,
            @Query("is_emp") String is_emp,
            @Query("is_stud") String is_stud,
            @Query("mobile_no") String mobile_no,
            @Query("otp_msg") String otp_msg,
            @Query("created_ip") String created_ip,
            @Query("created_by") String created_by);

    @GET("Update_Stud_Emp_Forget_Password_OTP_API")
    Call<UpdateStudentForgotPasswordOtpPojo> updateStudentEmpForgotPasswordOTP(
            @Query("user_id") String user_id,
            @Query("is_emp") String is_emp,
            @Query("is_stud") String is_stud,
            @Query("otp") String otp,
            @Query("modify_ip") String modify_ip,
            @Query("modify_by") String modify_by);

    @GET("check_otp_base_login_with_emp_uname_API")
    Call<CheckOTPVerificationForEmployeePojo> checkOTPVerificationForEmployee(
            @Query("username") String username,
            @Query("otp") String otp,
            @Query("institute_id") String institute_id,
            @Query("ip_addr") String ip_addr);

    @GET("check_otp_base_login_with_stud_uname_API")
    Call<CheckOTPVerificationForStudentPojo> checkOTPVerificationForStudent(
            @Query("username") String username,
            @Query("otp") String otp,
            @Query("institute_id") String institute_id,
            @Query("ip_addr") String ip_addr);

    @GET("Reset_Student_Password_API")
    Call<ResetStudentPasswordPojo> resetStudentPassword(
            @Query("stud_id") String stud_id,
            @Query("institute_id") String institute_id,
            @Query("password") String password,
            @Query("modify_by") String modify_by,
            @Query("modify_ip") String modify_ip);

    @GET("Reset_Employee_Password_API")
    Call<ResetEmployeePasswordPojo> resetEmployeePassword(
            @Query("emp_id") String emp_id,
            @Query("institute_id") String institute_id,
            @Query("password") String password,
            @Query("modify_by") String modify_by,
            @Query("modify_ip") String modify_ip);

    @GET("get_homework_and_content_delivered_API")
    Call<ArrayList<StudentHomeWorkPojo>> getStudentHomeWork(
            @Query("stud_id") String stud_id,
            @Query("year_id") String year_id,
            @Query("div_id") String div_id);

    @GET("Paynow_PAYTM")
    Call<PayWithPaytmPojo> payWithPaytm(
            @Query("SchoolId") String SchoolId,
            @Query("AdmissionNo") String AdmissionNo);

    //TODO Below API are for employee side modules

    @GET("login_user_new_api")
    Call<EmployeeLoginPojo> employeeLoginCheck(
            @Query("username") String username,
            @Query("password") String password);

    @GET("Update_Isrp_employee_FCM_Id")
    Call<UpdateFaultyFCMTokenPojo> updateFacultyFcmToken(
            @Query("emp_id") String emp_id,
            @Query("FCM_ID") String FCM_ID,
            @Query(value = "KEY", encoded = true) String KEY);

    @GET("employee_profile_api")
    Call<ArrayList<FacultyProfilePojo>> getFacultyProfileDetails(
            @Query("emp_id") String emp_id);


    @GET("Get_Image_URL_College_wise")
    Call<CommonNewImageSliderPojo> getCommonNewImageSlider(
            @Query("url") String url,
            @Query("institute_id") String institute_id,
            @Query("ac_id") String ac_id);


    @GET("Get_Employee_Timetable_Display_with_Merge_Lecture")
    Call<ArrayList<FacultyTimeTablePojo>> getFacultyTimeTable(
            @Query("emp_id") String emp_id,
            @Query("year_id") String year_id);

    //Common For Both Student And Employee(Faculty)


    @GET("Get_User_Wise_Announcement_Notification_API_with_Count")
    @Streaming
    Call<FacultyOrStudentNewsOrNotificationsPojo> getStudentNewsOrNotifications(
            @Query("notif_for") String notif_for,
            @Query("user_status") String user_status,
            @Query("user_id") String user_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("institute_id") String institute_id,
            @Query("year_id") String year_id,
            @Query("notif_count") String notification_count);

//    @GET("Get_Image_URL")
//    Call<GetSliderImageUrlsPojo> getSliderImages(@Query("url") String url, @Query("institute_id") String institute_id);

    @GET("Logout_and_Clear_FCM_id_of_User")
    Call<ArrayList<LogOutPojo>> logoutUser(@Query("login_user_type") int login_user_type,
                                           @Query("user_id") String user_id);

    @GET("check_version_api")
    Call<CheckVersionApiPojo> checkVersionInfo(@Query("version") int version);

    @GET("Update_Student_Employee_Wise_Notification_Status_API")
    Call<UpdateNotificationStatusPojo> updateStudentOrEmployeeNotificationStatus(
            @Query("notif_for") String notif_for,
            @Query("nt_id") String nt_id,
            @Query("modify_by") String modify_by,
            @Query("modify_ip") String modify_ip);

    @POST("Get_Leave_Balance")
    @Streaming
    Call<ArrayList<FacultyLeavePojo>> getFacultyLeave(@Query("KEY") String KEY, @Query("Contact_ID") String Contact_ID, @Query("Year") int year);

    @GET("Remaining_attendance_API")
    Call<ArrayList<FacultyRemAttendancePojo>> getFacultyRemAttendance(@Query("emp_id") String emp_id);

    @GET("Get_Emp_Wise_Lecture_Planning_Api")
    Call<ArrayList<FacultyLecturePlanPojo>> getEmployeeWiseLecturePlanning(@Query("emp_id") String emp_id);

    @GET("Get_Campus_News_Detail_API")
    Call<ArrayList<FacultyNewsPojo>> getFacultyNewsDetails(@Query("institute_id") String institute_id);

    @GET("Get_Punch_Data")
    Call<ArrayList<FacultyAttendancePojo>> getFacultyAttendance(
            @Query("KEY") String KEY,
            @Query("Contact_ID") String Contact_ID,
            @Query("Company_ID") String Company_ID,
            @Query("FromDate") String FromDate,
            @Query("ToDate") String ToDate);

    @GET("Get_employee_allocated_subject_details_by_id_API")
    @Streaming
    Call<ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo>> getEmployeeAllocatedSubjectDetailsByIdApi(@Query("emp_id") String emp_id,
                                                                                                       @Query("RowsPerPage") int RowsPerPage,
                                                                                                       @Query("PageNumber") int PageNumber);


    @GET("Get_SubjectWise_DivisionWise_Total_Theory_Period_Engaged_API")
    @Streaming
    Call<ArrayList<FacultySubAndDivWiseTotTheoryPeriodEngagedPojo>> GetSubjectWiseDivisionWiseTotalTheoryPeriodEngagedAPI(@Query("emp_id") String emp_id,
                                                                                                                          @Query("year_id") String year_id, @Query("RowsPerPage") String RowsPerPage, @Query("PageNumber") String PageNumber);


    @GET("Get_Student_Forum_Activity_Faculty_Wise_API")
    @Streaming
    Call<ArrayList<FacultyStudentForumPojo>> GetStudentForumActivityFacultyWiseAPI(@Query("emp_id") String emp_id,
                                                                                   @Query("year_id") String year_id,
                                                                                   @Query("institute_id") String institute_id,
                                                                                   @Query("RowsPerPage") String RowsPerPage,
                                                                                   @Query("PageNumber") String PageNumber);


    @GET("Get_Pending_Attendance_Detail_Employee_Wise_API")
    @Streaming
    Call<FacultyPendingAttendancePojo> getFacultyPendingAttendance(@Query("emp_id") String emp_id,
                                                                   @Query("year_id") String year_id);

    @GET("Get_Approved_Lecture_Planning_Faculty_Wise_API")
    @Streaming
    Call<ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo>> getFacultyLabOrLecturePlanTeachingUpdate(@Query("emp_id") String emp_id,
                                                                                                        @Query("institute_id") String institute_id,
                                                                                                        @Query("year_id") String year_id,
                                                                                                        @Query("RowsPerPage") String RowsPerPage,
                                                                                                        @Query("PageNumber") String PageNumber);

    @GET("FETCH_FACULTY_WISE_ASSGNIED_BATCH_FACULTY_ADVISER_YEAR_WISE_API")
    @Streaming
    Call<ArrayList<FacultyAdviserRemarksListPojo>> facultyAdviserRemarks(@Query("emp_id") String emp_id,
                                                                         @Query("year_id") String year_id,
                                                                         @Query("institute_id") String institute_id,
                                                                         @Query("RowsPerPage") String RowsPerPage,
                                                                         @Query("PageNumber") String PageNumber);

    @GET("Get_Teaching_Method_Details_API")
    @Streaming
    Call<GetTeachingMethodPojo> getTeachingMethod(@Query("institute_id") String institute_id);

    @GET("Get_Teaching_Aid_Detail_API")
    @Streaming
    Call<GetTeachingAidDetailsPojo> getTeachingAidDetails(@Query("institute_id") String institute_id);

    @GET("Get_Subject_Wise_Unit_Detail")
    @Streaming
    Call<FacultyPendingAttendanceUnitPojo> getFacultyPendingAttendanceUnit(@Query("emp_id") String emp_id,
                                                                           @Query("div_id") String div_id,
                                                                           @Query("sub_id") String sub_id,
                                                                           @Query("year_id") String year_id);

    @GET("Get_Student_Detail_For_Attendance")
    @Streaming
    Call<StudentDetailsFillAttendancePojo> getPendingAttendanceStudentList(@Query("batch_id") String batch_id,
                                                                           @Query("div_id") String div_id,
                                                                           @Query("sem_id") String sem_id,
                                                                           @Query("lect_no") String lect_no,
                                                                           @Query("att_date") String att_date,
                                                                           @Query("year_id") String year_id,
                                                                           @Query("sub_id") String sub_id);

    @GET("Download_AU_Examination_Hall_Ticket")
    @Streaming
    Call<DownloadHallTicketExaminationSchedulePojo> downloadAUExaminationScheduleHallTicket(@Query("institute_id") String institute_id,
                                                                                            @Query("college_id") String college_id,
                                                                                            @Query("sem_id") String sem_id,
                                                                                            @Query("year_id") String year_id,
                                                                                            @Query("stud_id") String stud_id,
                                                                                            @Query("repeater_status") String repeater_status,
                                                                                            @Query("exam_db") String exam_db);

    @GET("Get_Attendance_Method_Configuration_For_Fill_Attendance")
    @Streaming
    Call<FacultyFillAttendanceConfigurationPojo> getFacultyFillAttendanceConfiguration(@Query("college_id") String college_id,
                                                                                       @Query("institute_id") String institute_id);

    @GET("Insert_Present_Student_Attendance_By_Alternate_Method")
    @Streaming
    Call<InsertAllPresentStudentByAlternateMethodPojo> insertAllStudentAsPresentApiCall(
            @Query("college_id") String college_id,
            @Query("sem_id") String sem_id,
            @Query("div_id") String div_id,
            @Query("course_id") String course_id,
            @Query("batch_id") String batch_id,
            @Query("year_id") String year_id,
            @Query("lecture_no") String lecture_no,
            @Query("att_date") String att_date,
            @Query("att_intime") String att_intime,
            @Query("att_topic") String att_topic,
            @Query("att_method") String att_method,
            @Query("att_aid") String att_aid,
            @Query("att_flinnt") String att_flinnt,
            @Query("roll_no") String roll_no,
            @Query("att_prac_the_status") String att_prac_the_status,
            @Query("version_id") String version_id,
            @Query("emp_id") String emp_id,
            @Query("unit_id") String unit_id,
            @Query("sub_id") String sub_id,
            @Query("att_homework") String att_homework,
            @Query("created_by") String created_by,
            @Query("created_ip") String created_ip);


    @GET("UPDATE_DAILY_LECTURE_PLANING_WISE_ATT_STATUS_API")
    @Streaming
    Call<ArrayList<UpdateDailyLectureWiseAttendanceStatusPojo>> updateDailyLectureWiseAttendance(
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("div_id") String div_id,
            @Query("batch_id") String batch_id,
            @Query("att_date") String att_date,
            @Query("lec_type") String lec_type,
            @Query("emp_id") String emp_id,
            @Query("lecture_no") String lecture_no,
            @Query("sub_id") String sub_id,
            @Query("res_id") String res_id,
            @Query("version_id") String version_id,
            @Query("modify_by") String modify_by,
            @Query("modify_ip") String modify_ip);

    @GET("Insert_Absent_Student_Attendance_By_Alternate_Method")
    @Streaming
    Call<InsertAllAbsentStudentByAlternateMethodPojo> insertAllStudentAbsent(
            @Query("college_id") String college_id,
            @Query("sem_id") String sem_id,
            @Query("div_id") String div_id,
            @Query("course_id") String course_id,
            @Query("batch_id") String batch_id,
            @Query("year_id") String year_id,
            @Query("lecture_no") String lecture_no,
            @Query("att_date") String att_date,
            @Query("att_intime") String att_intime,
            @Query("att_topic") String att_topic,
            @Query("att_method") String att_method,
            @Query("att_aid") String att_aid,
            @Query("att_flinnt") String att_flinnt,
            @Query("roll_no") String roll_no,
            @Query("att_prac_the_status") String att_prac_the_status,
            @Query("version_id") String version_id,
            @Query("emp_id") String emp_id,
            @Query("unit_id") String unit_id,
            @Query("sub_id") String sub_id,
            @Query("att_homework") String att_homework,
            @Query("created_by") String created_by,
            @Query("created_ip") String created_ip);


    @GET("Get_Lesson_Planning_Topic_Detail_Subject_and_Faculty_Wise_Topic_Wise_API")
    @Streaming
    Call<GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo> getMethodFromApi(
            @Query("emp_id") String emp_id,
            @Query("div_id") String div_id,
            @Query("sub_id") String sub_id,
            @Query("year_id") String year_id,
            @Query("topic_id") String topic_id);

    @GET("Get_Lesson_Planning_Topic_Detail_Subject_and_Faculty_Wise_API")
    @Streaming
    Call<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo> getLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnly(
            @Query("emp_id") String emp_id,
            @Query("div_id") String div_id,
            @Query("sub_id") String sub_id,
            @Query("year_id") String year_id);

    @GET("Insert_isrp_class_wise_attendance_API")
    @Streaming
    Call<InsertClassWiseAttendancePojo> insertClassWiseAttendance(
            @Query("college_id") String college_id,
            @Query("present_stud_id") String present_stud_id,
            @Query("absent_stud_id") String absent_stud_id,
            @Query("sem_id") String sem_id,
            @Query("div_id") String div_id,
            @Query("batch_id") String batch_id,
            @Query("lecture_no") String lecture_no,
            @Query("att_date") String att_date,
            @Query("att_intime") String att_intime,
            @Query("att_status") String att_status,
            @Query("year_id") String year_id,
            @Query("att_topic") String att_topic,
            @Query("att_teaching_method") String att_teaching_method,
            @Query("att_aid") String att_aid,
            @Query("no_of_flinnt") String no_of_flinnt,
            @Query("prac_the_status") String prac_the_status,
            @Query("att_version_id") String att_version_id,
            @Query("att_type") String att_type,
            @Query("att_emp_id") String att_emp_id,
            @Query("att_unit_id") String att_unit_id,
            @Query("att_homework") String att_homework,
            @Query("att_created_by") String att_created_by,
            @Query("att_created_ip") String att_created_ip);

    //Added by harsh on 18-1-2021
    @GET("Get_User_wise_Detail_For_Forget_Password_API")
    Call<GetUserWiseDetailForgetPasswordPojo> getUserWiseDetailForForgetPasswordAPI(
            @Query("uname_mobile") String uname_mobile, @Query("user_type") String user_type,
            @Query("domain_name") String domain_name);


    @GET("Insert_Forget_Password_Send_SMS_Record")
    Call<InsertForgetPasswordSendSMSRecordPojo> insertForgetPasswordSendSMSRecord(
            @Query("sms") String sms, @Query("user_id") String user_id,
            @Query("user_type") String user_type, @Query("mobile_no") String mobile_no, @Query("ip_addr") String ip_addr);


    @GET("Update_User_OTP_At_Forget_Password_API")
    Call<UpdateUserOTPAtForgetPasswordPojo> updateUserOTPAtForgetPasswordAPI(
            @Query("user_type") String user_type, @Query("user_id") String user_id,
            @Query("otp") String otp, @Query("ip_addr") String ip_addr);


    @GET("Check_Login_By_OTP_and_Username_API")
    Call<CheckLoginByOTPAndUsernamePojo> checkLoginByOTPAndUsernameAPI(
            @Query("user_type") String user_type,
            @Query("username") String username,
            @Query("otp") String otp,
            @Query("institute_id") String institute_id,
            @Query("ip_addr") String ip_addr);


    @GET("Reset_User_Password_API")
    Call<ResetUserPasswordPojo> resetUserPasswordAPI(
            @Query("user_type") String user_type,
            @Query("user_id") String user_id,
            @Query("institute_id") String institute_id,
            @Query("password") String password,
            @Query("ip_addr") String ip_addr);

    @GET("get_mid_sem_student_wise_exam_mark_API")
    Call<ArrayList<StudentMidMarksPojo>> getStudentMidMarksAPI(
            @Query("stud_id") String stud_id,
            @Query("sem_id") String sem_id,
            @Query("div_id") String div_id,
            @Query("year_id") String year_id);

    @GET("Search_Student_Master_Detail")
    Call<FacultyStudentListPojo> getFacultyStudentListforDirectLogin(
            @Query("search") String search,
            @Query("institute_id") String institute_id);

    @GET("get_student_detail_for_direct_login")
    Call<GetStudentDetailsForDirectLoginPojo> getStudentDetailsForDirectLoginByStudentId(
            @Query("student_id") String student_id);


    //HOD Leave Approval
    @GET("student_leave_approved_application_all")
    Call<ArrayList<HODStudentLeaveApproveApplicationAllPojo>> getStudentLeaveApprovedApplicationAll(
            @Query("emp_id") String emp_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("year_id") String year_id,
            @Query("institute_id") String institute_id,
            @Query("search") String search,
            @Query("status") String status);

    @GET("get_college_by_student_leave_coordinator")
    Call<ArrayList<CoOrdinatorLeaveListPojo>> getCoOrdinatorLeaveList(
            @Query("emp_id") String emp_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("year_id") String year_id,
            @Query("institute_id") String institute_id,
            @Query("search") String search,
            @Query("status") String status);

    @GET("Get_College_Detail_By_Employee_for_app_fillter")
    Call<ArrayList<HODGetCollegePojo>> getHODCollege(
            @Query("Institute_id") String Institute_id,
            @Query("emp_id") String emp_id,
            @Query("status") String status);

    @GET("get_college_by_student_leave_coordinator_For_App_Fillter")
    Call<ArrayList<CoOrdinatorGetCollegePojo>> getCoOrdinatorCollege(
            @Query("Institute_id") String Institute_id,
            @Query("emp_id") String emp_id);

    @GET("Get_Department_Detail_By_Faculty_For_App_Filter")
    Call<ArrayList<HODGetDepartmentPojo>> getHODDepartment(
            @Query("Institute_id") String Institute_id,
            @Query("college_id") String college_id,
            @Query("emp_id") String emp_id,
            @Query("status") String status);

    @GET("get_department_by_student_leave_coordinator_For_App_Fillter")
    Call<ArrayList<CoOrdinatorGetDepartmentPojo>> getCoOrdinatorDepartment(
            @Query("Institute_id") String Institute_id,
            @Query("college_id") String college_id,
            @Query("emp_id") String emp_id);

    @GET("Get_Course_Detail_By_Faculty_For_App_Fillter")
    Call<ArrayList<HODGetCoursePojo>> getHODCourses(
            @Query("Institute_id") String Institute_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("emp_id") String emp_id,
            @Query("status") String status);

    @GET("get_course_by_student_leave_coordinator_For_App_Fillter")
    Call<ArrayList<CoOrdinatorGetCoursePojo>> getCoOrdinatorCourse(
            @Query("Institute_id") String Institute_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("emp_id") String emp_id);

    @GET("Get_Semester_Detail_by_college_For_App_Fillter")
    Call<ArrayList<HODGetSemesterPojo>> getHODSemester(
            @Query("Institute_id") String Institute_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("Course_id") String Course_id);

    @GET("get_semester_by_student_leave_coordinator_For_App_Fillter")
    Call<ArrayList<CoOrdinatorGetSemesterPojo>> getCoOrdinatorSemester(
            @Query("emp_id") String emp_id,
            @Query("Institute_id") String Institute_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("Course_id") String Course_id);

    @GET("Update_Student_Leave_Approvel_Status_HOD_CORDINATOR")
    Call<HODAndCoOrdinatorApprovLeavePojo> updateStudentLeaveApprovalStatus(
            @Query("sl_id") String sl_id,
            @Query("emp_id") String emp_id,
            @Query("institute_id") String institute_id,
            @Query("year_id") String year_id,
            @Query("comment") String comment,
            @Query("IP_Address") String IP_Address,
            @Query("status") String status,
            @Query("chk_present") String chk_present);

    @GET("Get_Extra_Lecture_Detail_For_Approval_api")
    Call<ArrayList<ExtraLectureApprovalPojo>> getExtraLectureDetailForApproval(
            @Query("emp_id") String emp_id,
            @Query("year_id") String year_id);

    @GET("Update_Approve_Status_Extra_Lecture_For_Api")
    Call<ApproveOrRejectExtraLecturePojo> approvOrRejectExtraLecture(
            @Query("App_Rej_status") String App_Rej_status,
            @Query("ex_lec_ids") String ex_lec_ids,
            @Query("lec_app_rej_ip") String lec_app_rej_ip,
            @Query("school_user_id") String school_user_id);

    @GET("Get_Reason_Wise_Teaching_Suspension_Detail_only_pending_api")
    Call<ArrayList<GetSuspensionListPojo>> getSuspensionList(
            @Query("reason_status") String reason_status,
            @Query("status") String status,
            @Query("institute_id") String institute_id,
            @Query("college_id") String college_id,
            @Query("dept_id") String dept_id,
            @Query("course_id") String course_id,
            @Query("sem_id") String sem_id,
            @Query("div_id") String div_id,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date,
            @Query("search") String search,
            @Query("year_id") String year_id,
            @Query("emp_id") String emp_id,
            @Query("user_status") String user_status);

    @GET("get_event_data_for_api")
    Call<ArrayList<EventModel>> getCalendarEvents(
            @Query("institute_id") String institute_id);

    @GET("Get_Peson_data_by_stud_id_API")
    Call<ArrayList<GetPersonPojo>> getPerson(
            @Query("stud_id") String stud_id,
            @Query("institute_id") String institute_id);

    @GET("Insert_New_Message_API")
    Call<InsertMsgPojo> insertMsg(
            @Query("stud_id") String stud_id,
            @Query("institute_id") String institute_id,
            @Query("person_id") String person_id,
            @Query("title") String title,
            @Query("message") String message);

    @GET("Get_Message_Data_By_emp_ID")
    Call<ArrayList<StudentMsgListPojo>> getStudentMsgList(
            @Query("emp_id") String emp_id,
            @Query("institute_id") String institute_id);

    @GET("Get_Countac_Us_Details_API")
    Call<ArrayList<ContactUsPojo>> getContactUsDetails(
            @Query("stud_id") String stud_id,
            @Query("stud_year_id") String stud_year_id,
            @Query("stud_school_id") String stud_school_id);

    @GET("GetPendingFeeHead")
    Call<ArrayList<GetFeeTypePojo>> getFeeTypeNew(
            @Query("StudId") String StudId,
            @Query("InstId") String InstId);

    @GET("GetPendingFeeForHeadWise")
    Call<ArrayList<GetPendingFeeListFromFeeTypePojo>> getPendingFeeListFromFeeTypeNew(
            @Query("StudId") String StudId,
            @Query("HeadId") String HeadId,
            @Query("Skey") String Skey);

}
