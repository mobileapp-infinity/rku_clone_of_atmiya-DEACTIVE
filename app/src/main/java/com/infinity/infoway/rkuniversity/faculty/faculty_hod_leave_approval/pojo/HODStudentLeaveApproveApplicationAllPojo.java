package com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HODStudentLeaveApproveApplicationAllPojo {

    boolean isExpanded = false;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("sl_id")
    @Expose
    private Integer slId;
    @SerializedName("sl_stud_id")
    @Expose
    private Integer slStudId;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("leave_type")
    @Expose
    private String leaveType;
    @SerializedName("leave_date")
    @Expose
    private String leaveDate;
    @SerializedName("leacture_no")
    @Expose
    private String leactureNo;
    @SerializedName("app_status")
    @Expose
    private Integer appStatus;
    @SerializedName("app_status_text")
    @Expose
    private String appStatusText;
    @SerializedName("app_status_color")
    @Expose
    private String appStatusColor;
    @SerializedName("app_rej_detail")
    @Expose
    private String appRejDetail;
    @SerializedName("app_rej_detail_App")
    @Expose
    private String appRejDetailApp;
    @SerializedName("document")
    @Expose
    private Object document;
    @SerializedName("view_file")
    @Expose
    private String viewFile;
    @SerializedName("stud_remarks")
    @Expose
    private String studRemarks;
    @SerializedName("stud_name")
    @Expose
    private String studName;
    @SerializedName("stud_name_App")
    @Expose
    private String studNameApp;
    @SerializedName("course_name_App")
    @Expose
    private String courseNameApp;
    @SerializedName("sem_name_App")
    @Expose
    private String semNameApp;
    @SerializedName("stud_div_App")
    @Expose
    private String studDivApp;
    @SerializedName("leave_detail")
    @Expose
    private String leaveDetail;
    @SerializedName("leave_detail_App")
    @Expose
    private String leaveDetailApp;
    @SerializedName("lbl_approval")
    @Expose
    private String lblApproval;
    @SerializedName("lbl_approval_App")
    @Expose
    private String lblApprovalApp;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("sl_lt_id")
    @Expose
    private Integer slLtId;
    @SerializedName("created_date_App")
    @Expose
    private String createdDateApp;
    @SerializedName("Stud_mobile_no")
    @Expose
    private String studMobileNo;
    @SerializedName("leave_detail_for_notif")
    @Expose
    private String leaveDetailForNotif;
    @SerializedName("sl_leave_daye_type_App")
    @Expose
    private String slLeaveDayeTypeApp;
    @SerializedName("sl_kind_of_leave_App")
    @Expose
    private String slKindOfLeaveApp;
    @SerializedName("leave_detail_for_notif_App")
    @Expose
    private String leaveDetailForNotifApp;

    public Integer getSlId() {
        return slId;
    }

    public void setSlId(Integer slId) {
        this.slId = slId;
    }

    public Integer getSlStudId() {
        return slStudId;
    }

    public void setSlStudId(Integer slStudId) {
        this.slStudId = slStudId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeactureNo() {
        return leactureNo;
    }

    public void setLeactureNo(String leactureNo) {
        this.leactureNo = leactureNo;
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

    public String getAppStatusText() {
        return appStatusText;
    }

    public void setAppStatusText(String appStatusText) {
        this.appStatusText = appStatusText;
    }

    public String getAppStatusColor() {
        return appStatusColor;
    }

    public void setAppStatusColor(String appStatusColor) {
        this.appStatusColor = appStatusColor;
    }

    public String getAppRejDetail() {
        return appRejDetail;
    }

    public void setAppRejDetail(String appRejDetail) {
        this.appRejDetail = appRejDetail;
    }

    public String getAppRejDetailApp() {
        return appRejDetailApp;
    }

    public void setAppRejDetailApp(String appRejDetailApp) {
        this.appRejDetailApp = appRejDetailApp;
    }

    public Object getDocument() {
        return document;
    }

    public void setDocument(Object document) {
        this.document = document;
    }

    public String getViewFile() {
        return viewFile;
    }

    public void setViewFile(String viewFile) {
        this.viewFile = viewFile;
    }

    public String getStudRemarks() {
        return studRemarks;
    }

    public void setStudRemarks(String studRemarks) {
        this.studRemarks = studRemarks;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudNameApp() {
        return studNameApp;
    }

    public void setStudNameApp(String studNameApp) {
        this.studNameApp = studNameApp;
    }

    public String getCourseNameApp() {
        return courseNameApp;
    }

    public void setCourseNameApp(String courseNameApp) {
        this.courseNameApp = courseNameApp;
    }

    public String getSemNameApp() {
        return semNameApp;
    }

    public void setSemNameApp(String semNameApp) {
        this.semNameApp = semNameApp;
    }

    public String getStudDivApp() {
        return studDivApp;
    }

    public void setStudDivApp(String studDivApp) {
        this.studDivApp = studDivApp;
    }

    public String getLeaveDetail() {
        return leaveDetail;
    }

    public void setLeaveDetail(String leaveDetail) {
        this.leaveDetail = leaveDetail;
    }

    public String getLeaveDetailApp() {
        return leaveDetailApp;
    }

    public void setLeaveDetailApp(String leaveDetailApp) {
        this.leaveDetailApp = leaveDetailApp;
    }

    public String getLblApproval() {
        return lblApproval;
    }

    public void setLblApproval(String lblApproval) {
        this.lblApproval = lblApproval;
    }

    public String getLblApprovalApp() {
        return lblApprovalApp;
    }

    public void setLblApprovalApp(String lblApprovalApp) {
        this.lblApprovalApp = lblApprovalApp;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getSlLtId() {
        return slLtId;
    }

    public void setSlLtId(Integer slLtId) {
        this.slLtId = slLtId;
    }

    public String getCreatedDateApp() {
        return createdDateApp;
    }

    public void setCreatedDateApp(String createdDateApp) {
        this.createdDateApp = createdDateApp;
    }

    public String getStudMobileNo() {
        return studMobileNo;
    }

    public void setStudMobileNo(String studMobileNo) {
        this.studMobileNo = studMobileNo;
    }

    public String getLeaveDetailForNotif() {
        return leaveDetailForNotif;
    }

    public void setLeaveDetailForNotif(String leaveDetailForNotif) {
        this.leaveDetailForNotif = leaveDetailForNotif;
    }

    public String getSlLeaveDayeTypeApp() {
        return slLeaveDayeTypeApp;
    }

    public void setSlLeaveDayeTypeApp(String slLeaveDayeTypeApp) {
        this.slLeaveDayeTypeApp = slLeaveDayeTypeApp;
    }

    public String getSlKindOfLeaveApp() {
        return slKindOfLeaveApp;
    }

    public void setSlKindOfLeaveApp(String slKindOfLeaveApp) {
        this.slKindOfLeaveApp = slKindOfLeaveApp;
    }

    public String getLeaveDetailForNotifApp() {
        return leaveDetailForNotifApp;
    }

    public void setLeaveDetailForNotifApp(String leaveDetailForNotifApp) {
        this.leaveDetailForNotifApp = leaveDetailForNotifApp;
    }

}
