package com.infinity.infoway.rkuniversity.api;

import com.infinity.infoway.rkuniversity.event_calender.EventModel;
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
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetForgetPasswordDetailByUserIDPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetInstituteFromDomainPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetSMSApiForApplicationPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetStudentForgotPasswordDetailsPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.GetUserWiseDetailForgetPasswordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertForgetPasswordSendSMSRecordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertForgotPasswordOTPSmsRecordPojo;
import com.infinity.infoway.rkuniversity.forgot_password.pojo.InsertStudentPasswordAndSMSAbsentApiCall;
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
import com.infinity.infoway.rkuniversity.student.distribution_details.pojo.GetDistributionDetailListPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.CheckIsELearningManagementGroupIsCompulsoryOrNot;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.CheckIsLearningManagementGroupIsExistOrNotPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.ELearningYearListPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.EnrollToGroupPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.GroupWiseSubjectlistPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.InsertStudentLearningManagementPushNotificationPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.JoinGroupListPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.LearningManagementGroupDetailsPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.StudentWiseLearningGroupPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.CIAExamSemesterPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadExaminationSchedulePojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadHallTicketExaminationSchedulePojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadStudentMidResultPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.ExaminationScheduleDetailsPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.ExaminationScheduleProgramWiseTimetablePojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.MidResultPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentCIAMarksPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentMidMarksPojo;
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
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetOnlineFeePaymentURLPojo;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetPendingFeeListFromFeeTypePojo;
import com.infinity.infoway.rkuniversity.student.student_syllabus.SyllabusListPojo;
import com.infinity.infoway.rkuniversity.student.student_timetable.pojo.StudentTimeTablePojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiImplementer {

    //TODO Student side module implementers

    public static void logoutUserApiImplementer(int loginUserType, String userId, Callback<ArrayList<LogOutPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<LogOutPojo>> call = apiService.logoutUser(loginUserType, userId);
        call.enqueue(cb);
    }

    public static void checkVersionInfoApiImplementer(int appVersionCode, Callback<CheckVersionApiPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckVersionApiPojo> call = apiService.checkVersionInfo(appVersionCode);
        call.enqueue(cb);
    }

    public static void checkStudentLoginApiImplementer(HashMap<String, String> param, Callback<StudentLoginPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<StudentLoginPojo> call = apiService.checkStudentLogin(param);
        call.enqueue(cb);
    }

    //this is old ,image slider api don't use it new api is below
//    public static void getSliderImagesApiImplementer(String url, String instituteId, Callback<GetSliderImageUrlsPojo> cb) {
//        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
//        Call<GetSliderImageUrlsPojo> call = apiService.getSliderImages(url, instituteId);
//        call.enqueue(cb);
//    }


    //this is new image slider api
    public static void getImageSliderNewApiImplementer(String url, String institute_id, String ac_id, Callback<CommonNewImageSliderPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CommonNewImageSliderPojo> call = apiService.getCommonNewImageSlider(url, institute_id, ac_id);
        call.enqueue(cb);
    }

    public static void getStudentProfileImplementer(HashMap<String, String> map, Callback<StudentProfilePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<StudentProfilePojo> call = apiService.getStudentProfile(map);
        call.enqueue(cb);
    }

    public static void getFacultyOrStudentNewsOrNotificationImplementer(String notif_for, String user_status, String user_id, String college_id,
                                                                        String dept_id, String course_id, String sem_id, String institute_id,
                                                                        String year_id, String notification_count, Callback<FacultyOrStudentNewsOrNotificationsPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<FacultyOrStudentNewsOrNotificationsPojo> call = apiService.getStudentNewsOrNotifications(notif_for, user_status, user_id, college_id, dept_id,
                course_id, sem_id, institute_id, year_id, notification_count);
        call.enqueue(cb);
    }

    public static void getStudentLectureWiseAttendanceApiImplementer(Map<String, String> params, Callback<ArrayList<StudentLectureWiseAttendancePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentLectureWiseAttendancePojo>> call = apiService.getStudentLectureWiseAttendance(params);
        call.enqueue(cb);
    }

    public static void getStudentSubjectWiseAttendanceApiImplementer(Map<String, String> params, Callback<ArrayList<StudentSubjectWiseAttendancePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentSubjectWiseAttendancePojo>> call = apiService.getStudentSubjectWiseAttendance(params);
        call.enqueue(cb);
    }

    public static void getFeeReceiptApiImplementer(String stud_id, Callback<ArrayList<FeeReceiptPojo>> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<FeeReceiptPojo>> call = apiService.getFeeReceipt(stud_id);
        call.enqueue(cb);
    }

    public static void downloadFeeReceiptApiImplementer(String stud_id, String Receipt_no, Callback<PrintFeeReceiptPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<PrintFeeReceiptPojo> call = apiService.downloadFeeReceipt(stud_id, Receipt_no);
        call.enqueue(cb);
    }


//    public static void getStudentResultListApiImplementer(String enrollement_no, Callback<ArrayList<StudentReulstPojo>> cb) {
//        final IApiInterface apiService = ApiClientForCIAMarks.getClient().create(IApiInterface.class);
//        Call<ArrayList<StudentReulstPojo>> call = apiService.getStuedntResultList(enrollement_no);
//        call.enqueue(cb);
//    }

//    public static void downloadCIAExamResultApiImplementer(String Collegeid, String Yearid, String Semid, String Exam_id, String stud_id,
//                                                           Callback<CIAExamResultPojo> cb) {
//        final IApiInterface apiService = ApiClientForCIAMarks.getClient().create(IApiInterface.class);
//        Call<CIAExamResultPojo> call = apiService.downloadCIAExamResult(Collegeid, Yearid, Semid, Exam_id, stud_id);
//        call.enqueue(cb);
//    }

    public static void examinationScheduleDetailsApiImplementer(String stud_id, String exam_db,
                                                                Callback<ExaminationScheduleDetailsPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ExaminationScheduleDetailsPojo> call = apiService.getExaminationScheduleDetails(stud_id, exam_db);
        call.enqueue(cb);
    }

    public static void examinationScheduleProgramWiseTimetableApiImplementer(String sem_id, String year_id, String stud_id, String repeater_status, String exam_db,
                                                                             Callback<ExaminationScheduleProgramWiseTimetablePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ExaminationScheduleProgramWiseTimetablePojo> call = apiService.getExaminationScheduleProgramWiseTimetable(sem_id, year_id, stud_id, repeater_status, exam_db);
        call.enqueue(cb);
    }

    public static void downloadExaminationScheduleApiImplementer(String sem_id, String year_id, String stud_id, String repeater_status, String exam_db,
                                                                 Callback<DownloadExaminationSchedulePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<DownloadExaminationSchedulePojo> call = apiService.downloadExaminationSchedule(sem_id, year_id, stud_id, repeater_status, exam_db);
        call.enqueue(cb);
    }

//    public static void downloadPaySlipOfAxisApiImplementer(String stud_id,
//                                                           Callback<PaySlipOfAxisPojo> cb) {
//        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
//        Call<PaySlipOfAxisPojo> call = apiService.downloadPaySlipOfAxis(stud_id);
//        call.enqueue(cb);
//    }

    public static void downloadPaySlipOfAxisApiImplementer(String stud_id, String fee_type,
                                                           Callback<PaySlipOfAxisPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<PaySlipOfAxisPojo> call = apiService.downloadPaySlipOfAxis(stud_id, fee_type);
        call.enqueue(cb);
    }

    public static void getFeeTypePaySlipOfAxis(String stud_id,
                                               Callback<ArrayList<PaySlipOfAxisFeeTypePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<PaySlipOfAxisFeeTypePojo>> call = apiService.getFeeTypePaySlipOfAxis(stud_id);
        call.enqueue(cb);
    }

    public static void getStudentAssignmentListApiImplementer(String stud_id, String year_id,
                                                              Callback<ArrayList<StudentAssignmentListPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentAssignmentListPojo>> call = apiService.getStudentAssignmentList(stud_id, year_id);
        call.enqueue(cb);
    }

    public static void getStudentMessageHistoryListApiImplementer(String stud_id, String year_id,
                                                                  Callback<ArrayList<MessageHistoryListPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<MessageHistoryListPojo>> call = apiService.getStudentMessageHistory(stud_id, year_id);
        call.enqueue(cb);
    }


    public static void getKindOfLeaveListApiImplementer(String institute_id,
                                                        Callback<KindOfLeaveListPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<KindOfLeaveListPojo> call = apiService.getKindOfLeaveList(institute_id);
        call.enqueue(cb);
    }

    public static void getTypeOfFileUploadApiImplementer(String leave_type_id,
                                                         Callback<TypeOfFileUploadPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<TypeOfFileUploadPojo> call = apiService.getTypeOfLeaveFileUpload(leave_type_id);
        call.enqueue(cb);
    }

    public static void selectLectureListApiImplementer(String institute_id,
                                                       String sem_id,
                                                       String div_id,
                                                       String stud_id,
                                                       String from_date,
                                                       String year_id,
                                                       Callback<SelectLectureForPartialLeavePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<SelectLectureForPartialLeavePojo> call = apiService.selectLectureForPartialLeaveList(institute_id, sem_id, div_id, stud_id, from_date, year_id);
        call.enqueue(cb);
    }


    public static void checkStudentLeaveExisOrNotApiImplementer(String stud_id,
                                                                String year_id,
                                                                String leave_from_date,
                                                                String leave_to_date,
                                                                String leave_day_type,
                                                                String lect_no,
                                                                Callback<CheckStudentLeaveExistPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckStudentLeaveExistPojo> call = apiService.checkStudentLeaveExistOrNot(stud_id, year_id, leave_from_date, leave_to_date, leave_day_type, lect_no);
        call.enqueue(cb);
    }

    public static void insertStudentLeaveApiImplementer(String student_id,
                                                        String leave_type_id,
                                                        String from_date,
                                                        String to_date,
                                                        String leave_day_type,
                                                        String lecture_date,
                                                        String lecture_no,
                                                        String Remarks,
                                                        String year_id,
                                                        String created_by,
                                                        String created_ip,
                                                        String leave_taken_by,
                                                        Callback<InsertStudentLeavePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<InsertStudentLeavePojo> call = apiService.insertStudentLeave(student_id, leave_type_id, from_date, to_date, leave_day_type,
                lecture_date, lecture_no, Remarks, year_id, created_by, created_ip, leave_taken_by);
        call.enqueue(cb);
    }


    public static void uploadStudentLeaveDocumentApiImplementer(String fileName, String image, String stud_leave_id,
                                                                Callback<UploadStudentLeaveDocumentPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<UploadStudentLeaveDocumentPojo> call = apiService.uploadStudentLeaveDocument(fileName, image, stud_leave_id);
        call.enqueue(cb);
    }

    public static void getStudentLeaveApplicationHistoryApiImplementer(String stud_id, String year_id,
                                                                       Callback<LeaveApplicationHistoryPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<LeaveApplicationHistoryPojo> call = apiService.getStudentLeaveApplicationHistory(stud_id, year_id);
        call.enqueue(cb);
    }

    public static void getStudentActivityListApiImplementer(String stud_id, String url,
                                                            Callback<ArrayList<StudentActivityPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentActivityPojo>> call = apiService.getStudentActivityList(stud_id, url);
        call.enqueue(cb);
    }

    public static void getELearningYearListApiImplementer(String institute_id,
                                                          Callback<ELearningYearListPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ELearningYearListPojo> call = apiService.getELearningYear(institute_id);
        call.enqueue(cb);
    }

    public static void getStudentWiseLearningGroupApiImplementer(String stud_id,
                                                                 Callback<StudentWiseLearningGroupPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<StudentWiseLearningGroupPojo> call = apiService.getStudentWiseLearningGroup(stud_id);
        call.enqueue(cb);
    }

    public static void checkIsELearningGroupIsCompulsoryOrNotApiImplementer(String grp_id,
                                                                            Callback<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> call = apiService.checkIsELearningGroupIsCompulsoryOrNot(grp_id);
        call.enqueue(cb);
    }

    public static void getJoinLearningManagementGroupsApiImplementer(String stud_id, String year_id,
                                                                     Callback<ArrayList<JoinGroupListPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<JoinGroupListPojo>> call = apiService.getJoinLearningManagementGroups(stud_id, year_id);
        call.enqueue(cb);
    }

    public static void groupWiseLearningManagementSubjectListApiImplementer(String stud_id, String sem_id, String year_id, String grp_id,
                                                                            Callback<ArrayList<GroupWiseSubjectlistPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GroupWiseSubjectlistPojo>> call = apiService.groupWiseLearningManagementSubjectList(stud_id, sem_id, year_id, grp_id);
        call.enqueue(cb);
    }

    public static void groupWiseLearningManagementDocumentListApiImplementer(String grp_id, String stud_id, String sem_id, String year_id,
                                                                             String from_date, String to_date, String sub_id,
                                                                             Callback<ArrayList<LearningManagementGroupDetailsPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<LearningManagementGroupDetailsPojo>> call = apiService.groupWiseLearningManagementDocumentList(grp_id, stud_id, sem_id, year_id,
                from_date, to_date, sub_id);
        call.enqueue(cb);
    }

    public static void checkIsLearningManagementGroupIsExistOrNotApiImplementer(String grp_id, String stud_id,
                                                                                Callback<CheckIsLearningManagementGroupIsExistOrNotPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckIsLearningManagementGroupIsExistOrNotPojo> call = apiService.checkIsLearningManagementGroupIsExistOrNot(grp_id, stud_id);
        call.enqueue(cb);
    }

    public static void enrollToGroupApiImplementer(String grp_id, String stud_id, String created_by,
                                                   String created_ip, String lm_grpws_is_enroll_status,
                                                   Callback<EnrollToGroupPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<EnrollToGroupPojo> call = apiService.enrollToGroup(grp_id, stud_id, created_by, created_ip, lm_grpws_is_enroll_status);
        call.enqueue(cb);
    }


    public static void insertStudentLearningManagementPushNotificationApiImplementer(
            String grp_id, String year_id, String stud_id, String sem_id, String nt_type,
            String notif_desc, String read_status, String created_by, String created_ip,
            String institute_id,
            Callback<ArrayList<InsertStudentLearningManagementPushNotificationPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<InsertStudentLearningManagementPushNotificationPojo>> call = apiService.insertStudentLearningManagementPushNotification(
                grp_id, year_id, stud_id, sem_id, nt_type, notif_desc, read_status, created_by,
                created_ip, institute_id);
        call.enqueue(cb);
    }

    public static void studentHolidayListApiImplementer(String student_id, Callback<ArrayList<HolidayListPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<HolidayListPojo>> call = apiInterface.getStudentHolidayList(student_id);
        call.enqueue(cb);
    }

    public static void getStudentTimetableApiImplementer(String stud_id, String course_id, String div_id, String shift_id,
                                                         String batch_id, String year_id, Callback<ArrayList<StudentTimeTablePojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentTimeTablePojo>> call = apiInterface.getStudentTimetable(stud_id, course_id, div_id, shift_id, batch_id, year_id);
        call.enqueue(cb);
    }

    public static void getStudentSyllabusListApiImplementer(String stud_id, Callback<ArrayList<SyllabusListPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<SyllabusListPojo>> call = apiInterface.getStudentSyllabusList(stud_id);
        call.enqueue(cb);
    }

    public static void getStudentLessonPlanListApiImplementer(String stud_id, Callback<ArrayList<StudentLessonPlanListPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentLessonPlanListPojo>> call = apiInterface.getStudentLessonPlanList(stud_id);
        call.enqueue(cb);
    }

    public static void getPendingFeeTypeApiImplementer(String StudId, String YearId, String AdmissionNo,
                                                       String CompanyCode, String hostel_code, Callback<ArrayList<PayFeeTypePojoList>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<PayFeeTypePojoList>> call = apiInterface.getPendingFeeType(StudId, YearId, AdmissionNo, CompanyCode, hostel_code);
        call.enqueue(cb);
    }

    public static void feeUrlPojoApiImplementer(String Ord_stud_id, String Ord_year_id, String Ord_Exam_id,
                                                String Ord_sem_id, String Ord_Fee_Source, String Ord_Fee_Type, String Ord_created_ip,
                                                Callback<FeeUrlPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<FeeUrlPojo> call = apiInterface.feeUrlPojo(Ord_stud_id, Ord_year_id, Ord_Exam_id, Ord_sem_id, Ord_Fee_Source, Ord_Fee_Type, Ord_created_ip);
        call.enqueue(cb);
    }

    public static void getPaymentButtonHideShowApiImplementer(String StudId, String FeeType, Callback<ArrayList<GetPaymentButtonHideShowPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetPaymentButtonHideShowPojo>> call = apiInterface.getPaymentButtonHideShow(StudId, FeeType);
        call.enqueue(cb);
    }

    public static void getPaymentSingleButtonHideShowApiImplementer(Callback<ArrayList<GetPaymentSingleButtonHideShowPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetPaymentSingleButtonHideShowPojo>> call = apiInterface.getPaymentSingleButtonHideShow();
        call.enqueue(cb);
    }

    public static void getAllPendingFeeStudentApiImplementer(String StudId, String YearId, String AdmissionNo,
                                                             String ClassName, String CompanyCode, String FeeType, String hostel_code,
                                                             Callback<ArrayList<GetAllPendingFeeStudentPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetAllPendingFeeStudentPojo>> call = apiInterface.getAllPendingFeeStudent(StudId, YearId, AdmissionNo,
                ClassName, CompanyCode, FeeType, hostel_code);
        call.enqueue(cb);
    }

    public static void getStudentCIAMarksApiImplementer(String stud_id, String sem_id, String exam_type,
                                                        Callback<ArrayList<StudentCIAMarksPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentCIAMarksPojo>> call = apiInterface.getStudentCIAMarks(stud_id, sem_id, exam_type);
        call.enqueue(cb);
    }

    public static void getCIAExamSemesterApiImplementer(String course_id, String sem_id, String dept_id,
                                                        Callback<CIAExamSemesterPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<CIAExamSemesterPojo> call = apiInterface.getCIAExamSemester(course_id, sem_id, dept_id);
        call.enqueue(cb);
    }

    public static void getStudentMidResultApiImplementer(String institute_id, String dept_id, String course_id,
                                                         String sem_id, String stud_id, String srpc_id, Callback<ArrayList<MidResultPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<MidResultPojo>> call = apiInterface.getStudentMidResult(institute_id, dept_id, course_id, sem_id, stud_id, srpc_id);
        call.enqueue(cb);
    }

    public static void downloadStudentMidResultApiImplementer(String sem_id, String srpc_id, String stud_id,
                                                              String dept_id, String course_id, Callback<DownloadStudentMidResultPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<DownloadStudentMidResultPojo> call = apiInterface.downloadStudentMidResult(sem_id, srpc_id, stud_id, dept_id, course_id);
        call.enqueue(cb);
    }

    public static void getInstituteFromDomainApiImplementer(String domain_name, Callback<GetInstituteFromDomainPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<GetInstituteFromDomainPojo> call = apiInterface.getInstituteFromDomain(domain_name);
        call.enqueue(cb);
    }

    public static void getStudentForgetPasswordDetailsApiImplementer(String email, Callback<ArrayList<GetStudentForgotPasswordDetailsPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetStudentForgotPasswordDetailsPojo>> call = apiInterface.getStudentForgetPassDeail(email);
        call.enqueue(cb);
    }

    //new Api added by harsh
    public static void getForgetPasswordDetailByUserIDImplementer(String user_id, String user_type, String domain_name, Callback<ArrayList<GetForgetPasswordDetailByUserIDPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetForgetPasswordDetailByUserIDPojo>> call = apiInterface.getForgetPasswordDetailByUserID(user_id, user_type, domain_name);
        call.enqueue(cb);
    }

//    public static void getStudentForgetPasswordDetailsApiImplementer(String emp_stud_id, String emp_stud_status, String institute_id, Callback<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>> cb) {
//        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
//        Call<ArrayList<GetForgetPasswordDetailsByStudentEmployeeIdPojo>> call = apiInterface.getForgotPasswordDetailsByStudentOrEmpId(emp_stud_id, emp_stud_status, institute_id);
//        call.enqueue(cb);
//    }

    public static void getSMSApiForApplicationApiImplementer(String institute_id, Callback<ArrayList<GetSMSApiForApplicationPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<GetSMSApiForApplicationPojo>> call = apiInterface.getSMSApiForApplication(institute_id);
        call.enqueue(cb);
    }

    public static void updateStudentFcmTokenApiImplementer(String stud_id, String FCM_ID,
                                                           String KEY, Callback<UpdateStudentFCMTokenPojo> cb) {
        final IApiInterface apiInterface = ApiClientForStudentAndEmployeeFcmApi.getClient().create(IApiInterface.class);
        Call<UpdateStudentFCMTokenPojo> call = apiInterface.updateStudentFcmToken(stud_id, FCM_ID, KEY);
        call.enqueue(cb);
    }


//    public static void getOTPBaseLoginDetailsForEmployeeApiImplementer(String uname_mobile, String institute_id, Callback<OtpBaseLoginDetailsForEmployeePojo> cb) {
//        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
//        Call<OtpBaseLoginDetailsForEmployeePojo> call = apiInterface.getOTPBaseLoginDetailsForEmployee(uname_mobile, institute_id);
//        call.enqueue(cb);
//    }
//
//    public static void getOTPBaseLoginDetailsForStudentApiImplementer(String uname_mobile, String institute_id, Callback<OtpBaseLoginDetailsForStudentPojo> cb) {
//        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
//        Call<OtpBaseLoginDetailsForStudentPojo> call = apiInterface.getOTPBaseLoginDetailsForStudent(uname_mobile, institute_id);
//        call.enqueue(cb);
//    }

    public static void insertStudentPasswordAndSendSmsAbsentApiImplementer(String sms, Callback<InsertStudentPasswordAndSMSAbsentApiCall> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<InsertStudentPasswordAndSMSAbsentApiCall> call = apiInterface.insertStudentPasswordAndSendSmsAbsent(sms);
        call.enqueue(cb);
    }

    public static void insertForgotPasswordOTPSmsRecordApiApiImplementer(String user_id, String is_emp, String is_stud,
                                                                         String mobile_no, String otp_msg, String created_ip,
                                                                         String created_by, Callback<InsertForgotPasswordOTPSmsRecordPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<InsertForgotPasswordOTPSmsRecordPojo> call = apiInterface.insertForgotPasswordOTPSmsRecordApi(user_id, is_emp,
                is_stud, mobile_no, otp_msg, created_ip, created_by);
        call.enqueue(cb);
    }

    public static void updateStudentEmpForgotPasswordOTPApiImplementer(String user_id, String is_emp, String is_stud,
                                                                       String otp, String modify_ip, String modify_by,
                                                                       Callback<UpdateStudentForgotPasswordOtpPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<UpdateStudentForgotPasswordOtpPojo> call = apiInterface.updateStudentEmpForgotPasswordOTP(user_id, is_emp, is_stud,
                otp, modify_ip, modify_by);
        call.enqueue(cb);
    }

//    public static void checkOTPVerificationForEmployeeApiImplementer(String username, String otp, String institute_id, String ip_addr,
//                                                                     Callback<CheckOTPVerificationForEmployeePojo> cb) {
//        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
//        Call<CheckOTPVerificationForEmployeePojo> call = apiInterface.checkOTPVerificationForEmployee(username, otp, institute_id, ip_addr);
//        call.enqueue(cb);
//    }
//
//    public static void checkOTPVerificationForStudentApiImplementer(String username, String otp, String institute_id, String ip_addr,
//                                                                    Callback<CheckOTPVerificationForStudentPojo> cb) {
//        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
//        Call<CheckOTPVerificationForStudentPojo> call = apiInterface.checkOTPVerificationForStudent(username, otp, institute_id, ip_addr);
//        call.enqueue(cb);
//    }
//
//    public static void resetStudentPasswordApiImplementer(String stud_id, String institute_id, String password, String modify_by,
//                                                          String modify_ip, Callback<ResetStudentPasswordPojo> cb) {
//        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
//        Call<ResetStudentPasswordPojo> call = apiInterface.resetStudentPassword(stud_id, institute_id, password, modify_by, modify_ip);
//        call.enqueue(cb);
//    }
//
//    public static void resetEmployeePasswordApiImplementer(String emp_id, String institute_id, String password, String modify_by,
//                                                           String modify_ip, Callback<ResetEmployeePasswordPojo> cb) {
//        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
//        Call<ResetEmployeePasswordPojo> call = apiInterface.resetEmployeePassword(emp_id, institute_id, password, modify_by, modify_ip);
//        call.enqueue(cb);
//    }

    public static void getStudentHomeWorkApiImplementer(String stud_id, String year_id, String div_id,
                                                        Callback<ArrayList<StudentHomeWorkPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentHomeWorkPojo>> call = apiInterface.getStudentHomeWork(stud_id, year_id, div_id);
        call.enqueue(cb);
    }

    public static void payWithPaytmApiImplementer(String SchoolId, String AdmissionNo,
                                                  Callback<PayWithPaytmPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<PayWithPaytmPojo> call = apiInterface.payWithPaytm(SchoolId, AdmissionNo);
        call.enqueue(cb);
    }


    //TODO Employee Side Implementers

    public static void employeeLoginCheckApiImplementer(String username, String password,
                                                        Callback<EmployeeLoginPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<EmployeeLoginPojo> call = apiInterface.employeeLoginCheck(username, password);
        call.enqueue(cb);
    }

    public static void updateFacultyFcmTokenApiImplementer(String emp_id, String FCM_ID,
                                                           String KEY, Callback<UpdateFaultyFCMTokenPojo> cb) {
        final IApiInterface apiInterface = ApiClientForStudentAndEmployeeFcmApi.getClient().create(IApiInterface.class);
        Call<UpdateFaultyFCMTokenPojo> call = apiInterface.updateFacultyFcmToken(emp_id, FCM_ID, KEY);
        call.enqueue(cb);
    }

    public static void getFacultyProfileDetailsApiImplementer(String emp_id, Callback<ArrayList<FacultyProfilePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyProfilePojo>> call = apiService.getFacultyProfileDetails(emp_id);
        call.enqueue(cb);
    }

    public static void getFacultyTimeTableApiImplementer(String emp_id, String year_id, Callback<ArrayList<FacultyTimeTablePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyTimeTablePojo>> call = apiService.getFacultyTimeTable(emp_id, year_id);
        call.enqueue(cb);
    }

    public static void updateStudentOrEmployeeNotificationStatusApiImplementer(String notif_for, String nt_id, String modify_by,
                                                                               String modify_ip, Callback<UpdateNotificationStatusPojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<UpdateNotificationStatusPojo> call = apiService.updateStudentOrEmployeeNotificationStatus(notif_for, nt_id, modify_by, modify_ip);
        call.enqueue(cb);
    }

//    public static void getFacultyLeaveApiImplementer(String contactId, int yearId, Callback<ArrayList<FacultyLeavePojo>> cb) {
//        final IApiInterface apiService = ApiClientForEmployeeLeave.getClient().create(IApiInterface.class);
//        Call<ArrayList<FacultyLeavePojo>> call = apiService.getFacultyLeave(Urls.KEY_FOR_EMPLOYEE_LEAVE, contactId, yearId);
//        call.enqueue(cb);
//    }

    public static void getFacultyRemAttendanceApiImplementer(String emp_id, Callback<ArrayList<FacultyRemAttendancePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyRemAttendancePojo>> call = apiService.getFacultyRemAttendance(emp_id);
        call.enqueue(cb);
    }

    public static void getEmployeeWiseLecturePlanningApiImplementer(String emp_id, Callback<ArrayList<FacultyLecturePlanPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyLecturePlanPojo>> call = apiService.getEmployeeWiseLecturePlanning(emp_id);
        call.enqueue(cb);
    }

    public static void getFacultyNewsDetailsApiImplementer(String institute_id, Callback<ArrayList<FacultyNewsPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyNewsPojo>> call = apiService.getFacultyNewsDetails(institute_id);
        call.enqueue(cb);
    }

//    public static void getFacultyAttendanceApiImplementer(String Contact_ID, String Company_ID, String FromDate, String ToDate, Callback<ArrayList<FacultyAttendancePojo>> cb) {
//        final IApiInterface apiService = ApiClientForEmpAttendance.getClient().create(IApiInterface.class);
//        Call<ArrayList<FacultyAttendancePojo>> call = apiService.getFacultyAttendance(Urls.KEY_FOR_FACULTY_ATTENDANCE, Contact_ID, Company_ID,
//                FromDate, ToDate);
//        call.enqueue(cb);
//    }

    public static void getFacultyEmployeeAllocatedSubjectDetailsByIdApiImplementer(String emp_id, int RowsPerPage, int PageNumber, Callback<ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyDetailsOfTheorySubjectTaughtPojo>> call = apiService.getEmployeeAllocatedSubjectDetailsByIdApi(emp_id, RowsPerPage, PageNumber);
        call.enqueue(cb);
    }

    public static void getFacultySubjectAndDivisionWiseTotalTheoryPeriodEngagedImplementer(String emp_id, String year_id, String RowsPerPage, String PageNumber, Callback<ArrayList<FacultySubAndDivWiseTotTheoryPeriodEngagedPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultySubAndDivWiseTotTheoryPeriodEngagedPojo>> call = apiService.GetSubjectWiseDivisionWiseTotalTheoryPeriodEngagedAPI(emp_id, year_id, RowsPerPage, PageNumber);
        call.enqueue(cb);
    }

    public static void getFacultyStudentForumActivityFacultyWiseApiImplementer(String emp_id, String year_id, String institute_id, String RowsPerPage, String PageNumber, Callback<ArrayList<FacultyStudentForumPojo>> cb) {

        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyStudentForumPojo>> call = apiService.GetStudentForumActivityFacultyWiseAPI(emp_id, year_id, institute_id, RowsPerPage, PageNumber);
        call.enqueue(cb);
    }

    public static void getFacultyPendingAttendanceApiImplementer(String emp_id, String year_id, Callback<FacultyPendingAttendancePojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<FacultyPendingAttendancePojo> call = apiService.getFacultyPendingAttendance(emp_id, year_id);
        call.enqueue(cb);
    }

    public static void getApprovedLecturePlanningFacultyWiseApiImplementer(String emp_id, String institute_id, String year_id,
                                                                           String RowsPerPage, String PageNumber, Callback<ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyLabOrLecturePlanTeachingUpdatePojo>> call = apiService.getFacultyLabOrLecturePlanTeachingUpdate(emp_id,
                institute_id, year_id, RowsPerPage, PageNumber);
        call.enqueue(cb);
    }

    public static void facultyAdviserRemarksApiImplementer(String emp_id, String year_id, String institute_id,
                                                           String RowsPerPage, String PageNumber, Callback<ArrayList<FacultyAdviserRemarksListPojo>> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<FacultyAdviserRemarksListPojo>> call = apiService.facultyAdviserRemarks(emp_id, year_id, institute_id, RowsPerPage, PageNumber);
        call.enqueue(cb);
    }

    public static void getTeachingMethodApiImplementer(String institute_id, Callback<GetTeachingMethodPojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<GetTeachingMethodPojo> call = apiService.getTeachingMethod(institute_id);
        call.enqueue(cb);
    }

    public static void getTeachingAidDetailsApiImplementer(String institute_id, Callback<GetTeachingAidDetailsPojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<GetTeachingAidDetailsPojo> call = apiService.getTeachingAidDetails(institute_id);
        call.enqueue(cb);
    }

    public static void getFacultyPendingAttendanceUnitApiImplementer(String emp_id, String div_id, String sub_id, String year_id, Callback<FacultyPendingAttendanceUnitPojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<FacultyPendingAttendanceUnitPojo> call = apiService.getFacultyPendingAttendanceUnit(emp_id, div_id, sub_id, year_id);
        call.enqueue(cb);
    }

    public static void getPendingAttendanceStudentListApiImplementer(String batch_id, String div_id, String sem_id, String lect_no,
                                                                     String att_date, String year_id, String sub_id, Callback<StudentDetailsFillAttendancePojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<StudentDetailsFillAttendancePojo> call = apiService.getPendingAttendanceStudentList(batch_id, div_id, sem_id, lect_no, att_date, year_id, sub_id);
        call.enqueue(cb);
    }

    public static void downloadHallTicketExaminationScheduleApiImplementer(String institute_id, String college_id, String sem_id, String year_id, String stud_id,
                                                                           String repeater_status, String exam_db, Callback<DownloadHallTicketExaminationSchedulePojo> cb) {
        final IApiInterface apiService = ApiClient.getClient().create(IApiInterface.class);
        Call<DownloadHallTicketExaminationSchedulePojo> call = apiService.downloadAUExaminationScheduleHallTicket(institute_id, college_id, sem_id, year_id, stud_id, repeater_status, exam_db);
        call.enqueue(cb);
    }

    public static void insertAllStudentAsPresentApiCallApiImplementer(String college_id, String institute_id,
                                                                      Callback<FacultyFillAttendanceConfigurationPojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<FacultyFillAttendanceConfigurationPojo> call = apiService.getFacultyFillAttendanceConfiguration(college_id, institute_id);
        call.enqueue(cb);
    }

    public static void insertAllStudentAsPresentApiCallApiImplementer(String college_id, String sem_id, String div_id, String course_id,
                                                                      String batch_id,
                                                                      String year_id,
                                                                      String lecture_no,
                                                                      String att_date,
                                                                      String att_intime,
                                                                      String att_topic,
                                                                      String att_method,
                                                                      String att_aid,
                                                                      String att_flinnt,
                                                                      String roll_no,
                                                                      String att_prac_the_status,
                                                                      String version_id,
                                                                      String emp_id,
                                                                      String unit_id,
                                                                      String sub_id,
                                                                      String att_homework,
                                                                      String created_by,
                                                                      String created_ip,
                                                                      Callback<InsertAllPresentStudentByAlternateMethodPojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<InsertAllPresentStudentByAlternateMethodPojo> call = apiService.insertAllStudentAsPresentApiCall(college_id,
                sem_id,
                div_id,
                course_id,
                batch_id,
                year_id,
                lecture_no,
                att_date,
                att_intime,
                att_topic,
                att_method,
                att_aid,
                att_flinnt,
                roll_no,
                att_prac_the_status,
                version_id,
                emp_id,
                unit_id,
                sub_id,
                att_homework,
                created_by,
                created_ip);
        call.enqueue(cb);
    }


    public static void updateDailyLectureWiseAttendanceApiImplementer(String course_id,
                                                                      String sem_id,
                                                                      String div_id,
                                                                      String batch_id,
                                                                      String att_date,
                                                                      String lec_type,
                                                                      String emp_id,
                                                                      String lecture_no,
                                                                      String sub_id,
                                                                      String res_id,
                                                                      String version_id,
                                                                      String modify_by,
                                                                      String modify_ip,
                                                                      Callback<ArrayList<UpdateDailyLectureWiseAttendanceStatusPojo>> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<UpdateDailyLectureWiseAttendanceStatusPojo>> call = apiService.updateDailyLectureWiseAttendance(
                course_id,
                sem_id,
                div_id,
                batch_id,
                att_date,
                lec_type,
                emp_id,
                lecture_no,
                sub_id,
                res_id,
                version_id,
                modify_by,
                modify_ip);
        call.enqueue(cb);
    }

    public static void insertAllStudentAbsentApiCallApiCallApiImplementer(String college_id, String sem_id, String div_id, String course_id,
                                                                          String batch_id,
                                                                          String year_id,
                                                                          String lecture_no,
                                                                          String att_date,
                                                                          String att_intime,
                                                                          String att_topic,
                                                                          String att_method,
                                                                          String att_aid,
                                                                          String att_flinnt,
                                                                          String roll_no,
                                                                          String att_prac_the_status,
                                                                          String version_id,
                                                                          String emp_id,
                                                                          String unit_id,
                                                                          String sub_id,
                                                                          String att_homework,
                                                                          String created_by,
                                                                          String created_ip,
                                                                          Callback<InsertAllAbsentStudentByAlternateMethodPojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<InsertAllAbsentStudentByAlternateMethodPojo> call = apiService.insertAllStudentAbsent(college_id,
                sem_id,
                div_id,
                course_id,
                batch_id,
                year_id,
                lecture_no,
                att_date,
                att_intime,
                att_topic,
                att_method,
                att_aid,
                att_flinnt,
                roll_no,
                att_prac_the_status,
                version_id,
                emp_id,
                unit_id,
                sub_id,
                att_homework,
                created_by,
                created_ip);
        call.enqueue(cb);
    }

    public static void getMethodFromApiApiImplementer(String emp_id, String div_id,
                                                      String sub_id, String year_id, String topic_id,
                                                      Callback<GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo> call = apiService.getMethodFromApi(emp_id, div_id, sub_id, year_id, topic_id);
        call.enqueue(cb);
    }

    public static void getLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyApiImplementer(String emp_id, String div_id,
                                                                                              String sub_id, String year_id, Callback<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<GetLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnlyPojo> call = apiService.getLessonPlanningTopicDetailsBySubjectAndFacultyWiseOnly(emp_id, div_id,
                sub_id, year_id);
        call.enqueue(cb);
    }

    public static void insertClassWiseAttendanceApiImplementer(String college_id,
                                                               String present_stud_id,
                                                               String absent_stud_id,
                                                               String sem_id,
                                                               String div_id,
                                                               String batch_id,
                                                               String lecture_no,
                                                               String att_date,
                                                               String att_intime,
                                                               String att_status,
                                                               String year_id,
                                                               String att_topic,
                                                               String att_teaching_method,
                                                               String att_aid,
                                                               String no_of_flinnt,
                                                               String prac_the_status,
                                                               String att_version_id,
                                                               String att_type,
                                                               String att_emp_id,
                                                               String att_unit_id,
                                                               String att_homework,
                                                               String att_created_by,
                                                               String att_created_ip, Callback<InsertClassWiseAttendancePojo> cb) {
        final IApiInterface apiService = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<InsertClassWiseAttendancePojo> call = apiService.insertClassWiseAttendance(
                college_id,
                present_stud_id,
                absent_stud_id,
                sem_id,
                div_id,
                batch_id,
                lecture_no,
                att_date,
                att_intime,
                att_status,
                year_id,
                att_topic,
                att_teaching_method,
                att_aid,
                no_of_flinnt,
                prac_the_status,
                att_version_id,
                att_type,
                att_emp_id,
                att_unit_id,
                att_homework,
                att_created_by,
                att_created_ip);
        call.enqueue(cb);
    }

    //Added by harsh 18-1-2021
    public static void getUserWiseDetailForForgetPasswordAPIImplementer(String uname_mobile, String user_type, String domain_name, Callback<GetUserWiseDetailForgetPasswordPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<GetUserWiseDetailForgetPasswordPojo> call = apiInterface.getUserWiseDetailForForgetPasswordAPI(uname_mobile, user_type, domain_name);
        call.enqueue(cb);
    }

    public static void insertForgetPasswordSendSMSRecordImplementer(String sms, String user_id, String user_type, String mobile_no, String ip_addr, Callback<InsertForgetPasswordSendSMSRecordPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<InsertForgetPasswordSendSMSRecordPojo> call = apiInterface.insertForgetPasswordSendSMSRecord(sms, user_id, user_type, mobile_no, ip_addr);
        call.enqueue(cb);
    }

    public static void updateUserOTPAtForgetPasswordAPIImplementer(String user_type, String user_id, String otp, String ip_addr, Callback<UpdateUserOTPAtForgetPasswordPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<UpdateUserOTPAtForgetPasswordPojo> call = apiInterface.updateUserOTPAtForgetPasswordAPI(user_type, user_id, otp, ip_addr);
        call.enqueue(cb);
    }


    public static void checkLoginByOTPAndUsernameAPIImplementer(String user_type,
                                                                String username,
                                                                String otp,
                                                                String institute_id,
                                                                String ip_addr,
                                                                Callback<CheckLoginByOTPAndUsernamePojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<CheckLoginByOTPAndUsernamePojo> call = apiInterface.checkLoginByOTPAndUsernameAPI(
                user_type,
                username,
                otp,
                institute_id,
                ip_addr);
        call.enqueue(cb);
    }

    public static void resetUserPasswordAPIImplementer(String user_type,
                                                       String user_id,
                                                       String institute_id,
                                                       String password,
                                                       String ip_addr,
                                                       Callback<ResetUserPasswordPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ResetUserPasswordPojo> call = apiInterface.resetUserPasswordAPI(user_type, user_id, institute_id, password, ip_addr);
        call.enqueue(cb);
    }

    public static void getStudentMidMarksApiImplementer(String stud_id, String sem_id, String div_id, String year_id, Callback<ArrayList<StudentMidMarksPojo>> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentMidMarksPojo>> call = apiInterface.getStudentMidMarksAPI(stud_id, sem_id, div_id, year_id);
        call.enqueue(cb);
    }

    public static void getFacultyStudentListforDirectLoginApiImplementer(String search, String institute_id, Callback<FacultyStudentListPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<FacultyStudentListPojo> call = apiInterface.getFacultyStudentListforDirectLogin(search, institute_id);
        call.enqueue(cb);
    }

    public static void getStudentDetailsForDirectLoginByStudentIdApiImplementer(String student_id, Callback<GetStudentDetailsForDirectLoginPojo> cb) {
        final IApiInterface apiInterface = ApiClient.getClient().create(IApiInterface.class);
        Call<GetStudentDetailsForDirectLoginPojo> call = apiInterface.getStudentDetailsForDirectLoginByStudentId(student_id);
        call.enqueue(cb);
    }


    //HOD Leave Approval
    public static void getStudentLeaveApprovedApplicationAllApiImplementer(String emp_id,
                                                                           String college_id,
                                                                           String dept_id,
                                                                           String course_id,
                                                                           String sem_id,
                                                                           String year_id,
                                                                           String institute_id,
                                                                           String search,
                                                                           String status, Callback<ArrayList<HODStudentLeaveApproveApplicationAllPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<HODStudentLeaveApproveApplicationAllPojo>> call = apiInterface.getStudentLeaveApprovedApplicationAll(emp_id, college_id, dept_id, course_id, sem_id, year_id, institute_id, search, status);
        call.enqueue(cb);
    }


    public static void getHODCollegeApiImplementer(String Institute_id, String emp_id, String status, Callback<ArrayList<HODGetCollegePojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<HODGetCollegePojo>> call = apiInterface.getHODCollege(Institute_id, emp_id, status);
        call.enqueue(cb);
    }

    public static void getHODDepartmentApiImplementer(String Institute_id, String collegeId, String emp_id, String status, Callback<ArrayList<HODGetDepartmentPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<HODGetDepartmentPojo>> call = apiInterface.getHODDepartment(Institute_id, collegeId, emp_id, status);
        call.enqueue(cb);
    }

    public static void getHODCoursesApiImplementer(String Institute_id,
                                                   String college_id,
                                                   String dept_id,
                                                   String emp_id,
                                                   String status, Callback<ArrayList<HODGetCoursePojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<HODGetCoursePojo>> call = apiInterface.getHODCourses(Institute_id, college_id, dept_id, emp_id, status);
        call.enqueue(cb);
    }

    public static void getHODSemesterApiImplementer(String Institute_id,
                                                    String college_id,
                                                    String dept_id,
                                                    String courseId,
                                                    Callback<ArrayList<HODGetSemesterPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<HODGetSemesterPojo>> call = apiInterface.getHODSemester(Institute_id, college_id, dept_id, courseId);
        call.enqueue(cb);
    }

    //for both coordinator and hod
    public static void updateStudentLeaveApprovalStatusApiImplementer(
            String sl_id,
            String emp_id,
            String institute_id,
            String year_id,
            String comment,
            String IP_Address,
            String status,
            String chk_present,
            Callback<HODAndCoOrdinatorApprovLeavePojo> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<HODAndCoOrdinatorApprovLeavePojo> call = apiInterface.updateStudentLeaveApprovalStatus(sl_id, emp_id, institute_id, year_id, comment, IP_Address, status, chk_present);
        call.enqueue(cb);
    }

    //coordinator
    public static void getCoOrdinatorLeaveListApiImplementer(String emp_id,
                                                             String college_id,
                                                             String dept_id,
                                                             String course_id,
                                                             String sem_id,
                                                             String year_id,
                                                             String institute_id,
                                                             String search,
                                                             String status, Callback<ArrayList<CoOrdinatorLeaveListPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<CoOrdinatorLeaveListPojo>> call = apiInterface.getCoOrdinatorLeaveList(emp_id, college_id, dept_id, course_id, sem_id, year_id, institute_id, search, status);
        call.enqueue(cb);
    }

    public static void getCoOrdinatorCollegeApiImplementer(String Institute_id, String emp_id, Callback<ArrayList<CoOrdinatorGetCollegePojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<CoOrdinatorGetCollegePojo>> call = apiInterface.getCoOrdinatorCollege(Institute_id, emp_id);
        call.enqueue(cb);
    }

    public static void getCoOrdinatorDepartmentApiImplementer(String Institute_id, String college_id, String emp_id, Callback<ArrayList<CoOrdinatorGetDepartmentPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<CoOrdinatorGetDepartmentPojo>> call = apiInterface.getCoOrdinatorDepartment(Institute_id, college_id, emp_id);
        call.enqueue(cb);
    }

    public static void getCoOrdinatorCourseApiImplementer(String Institute_id,
                                                          String college_id,
                                                          String dept_id,
                                                          String emp_id, Callback<ArrayList<CoOrdinatorGetCoursePojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<CoOrdinatorGetCoursePojo>> call = apiInterface.getCoOrdinatorCourse(Institute_id, college_id, dept_id, emp_id);
        call.enqueue(cb);
    }

    public static void getCoOrdinatorSemesterApiImplementer(String emp_id,
                                                            String Institute_id,
                                                            String college_id,
                                                            String dept_id,
                                                            String Course_id,
                                                            Callback<ArrayList<CoOrdinatorGetSemesterPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<CoOrdinatorGetSemesterPojo>> call = apiInterface.getCoOrdinatorSemester(emp_id, Institute_id, college_id, dept_id, Course_id);
        call.enqueue(cb);
    }

    public static void getExtraLectureApprovalApiImplementer(String emp_id,
                                                             String year_id, Callback<ArrayList<ExtraLectureApprovalPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<ExtraLectureApprovalPojo>> call = apiInterface.getExtraLectureDetailForApproval(emp_id, year_id);
        call.enqueue(cb);
    }

    public static void approvOrRejectExtraLectureApiImplementer(String App_Rej_status,
                                                                String ex_lec_ids,
                                                                String lec_app_rej_ip,
                                                                String school_user_id, Callback<ApproveOrRejectExtraLecturePojo> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ApproveOrRejectExtraLecturePojo> call = apiInterface.approvOrRejectExtraLecture(App_Rej_status, ex_lec_ids, lec_app_rej_ip, school_user_id);
        call.enqueue(cb);
    }

    public static void getSuspensionListApiImplementer(String reason_status,
                                                       String status,
                                                       String institute_id,
                                                       String college_id,
                                                       String dept_id,
                                                       String course_id,
                                                       String sem_id,
                                                       String div_id,
                                                       String from_date,
                                                       String to_date,
                                                       String search,
                                                       String year_id,
                                                       String emp_id,
                                                       String user_status, Callback<ArrayList<GetSuspensionListPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<GetSuspensionListPojo>> call = apiInterface.getSuspensionList(reason_status, status, institute_id, college_id, dept_id, course_id, sem_id, div_id, from_date, to_date, search, year_id, emp_id, user_status);
        call.enqueue(cb);
    }

    public static void getCalendarEventsApiImplementer(String institute_id, Callback<ArrayList<EventModel>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<EventModel>> call = apiInterface.getCalendarEvents(institute_id);
        call.enqueue(cb);
    }

    public static void getPersonApiImplementer(String stud_id, String institute_id, Callback<ArrayList<GetPersonPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<GetPersonPojo>> call = apiInterface.getPerson(stud_id, institute_id);
        call.enqueue(cb);
    }

    public static void insertMsgApiImplementer(String stud_id, String institute_id, String person_id, String title, String message, Callback<InsertMsgPojo> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<InsertMsgPojo> call = apiInterface.insertMsg(stud_id, institute_id, person_id, title, message);
        call.enqueue(cb);
    }

    public static void getStudentMessagesApiImplementer(String emp_id, String institute_id, Callback<ArrayList<StudentMsgListPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<StudentMsgListPojo>> call = apiInterface.getStudentMsgList(emp_id, institute_id);
        call.enqueue(cb);
    }

    public static void getContactUsDetailsApiImplementer(String stud_id, String stud_year_id, String stud_school_id, Callback<ArrayList<ContactUsPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<ContactUsPojo>> call = apiInterface.getContactUsDetails(stud_id, stud_year_id, stud_school_id);
        call.enqueue(cb);
    }

    //For Distribution Detail List
    public static void getStudentWiseItemDistributionApiImplementer(String INST_ID, String school_id,
                                                                    String stud_id, Callback<ArrayList<GetDistributionDetailListPojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<GetDistributionDetailListPojo>> call = apiInterface.getStudentWiseItemDistribution(INST_ID, school_id, stud_id);
        call.enqueue(cb);
    }

    public static void getFeeTypeNewApiImplementer(String StudId, String InstId, Callback<ArrayList<GetFeeTypePojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<GetFeeTypePojo>> call = apiInterface.getFeeTypeNew(StudId, InstId, Urls.S_KEY_FOR_STUDENT_PAY_FEE);
        call.enqueue(cb);
    }

    public static void getPendingFeeListFromFeeTypeNewImplementer(String StudId, String HeadId, String InstId, Callback<ArrayList<GetPendingFeeListFromFeeTypePojo>> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<ArrayList<GetPendingFeeListFromFeeTypePojo>> call = apiInterface.getPendingFeeList(StudId, HeadId, InstId, Urls.S_KEY_FOR_STUDENT_PAY_FEE);
        call.enqueue(cb);
    }

    public static void getOnlineFeePaymentUrlApiImplementer(String StudId, String HeadId, String SemId, String InstId,
                                                            String YearId, String TotalPendingAmt, String Response,
                                                            Callback<GetOnlineFeePaymentURLPojo> cb) {
        final IApiInterface apiInterface = ApiClientForFacultyPendingAttendance.getClient().create(IApiInterface.class);
        Call<GetOnlineFeePaymentURLPojo> call = apiInterface.getOnlineFeePaymentUrl(StudId, HeadId, SemId, InstId, YearId, TotalPendingAmt, Response);
        call.enqueue(cb);
    }
}
