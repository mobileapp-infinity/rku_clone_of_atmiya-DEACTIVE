package com.infinity.infoway.rkuniversity.student.exam.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MidResultPojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("sr_no")
    @Expose
    private Integer srNo;
    @SerializedName("srpc_id")
    @Expose
    private Integer srpcId;
    @SerializedName("srpc_college_id")
    @Expose
    private Integer srpcCollegeId;
    @SerializedName("srpc_dept_id")
    @Expose
    private Integer srpcDeptId;
    @SerializedName("srpc_course_id")
    @Expose
    private Integer srpcCourseId;
    @SerializedName("srpc_sem_id")
    @Expose
    private Integer srpcSemId;
    @SerializedName("srpc_report_name")
    @Expose
    private String srpcReportName;
    @SerializedName("srpc_result_display_date")
    @Expose
    private String srpcResultDisplayDate;
    @SerializedName("srpc_result_heading")
    @Expose
    private String srpcResultHeading;
    @SerializedName("srpc_is_exam_display")
    @Expose
    private Integer srpcIsExamDisplay;
    @SerializedName("srpc_is_total_marks_display")
    @Expose
    private Integer srpcIsTotalMarksDisplay;
    @SerializedName("srpc_remarks")
    @Expose
    private String srpcRemarks;
    @SerializedName("srpc_publish_start_date")
    @Expose
    private String srpcPublishStartDate;
    @SerializedName("srpc_publish_end_date")
    @Expose
    private String srpcPublishEndDate;
    @SerializedName("srpc_exam_string")
    @Expose
    private String srpcExamString;
    @SerializedName("srpc_term_string")
    @Expose
    private String srpcTermString;
    @SerializedName("srpc_year_id")
    @Expose
    private Integer srpcYearId;
    @SerializedName("swd_division_id")
    @Expose
    private Integer swdDivisionId;
    @SerializedName("stud_admission_no")
    @Expose
    private String studAdmissionNo;
    @SerializedName("srpc_result_name")
    @Expose
    private String srpcResultName;
    @SerializedName("year_name")
    @Expose
    private String yearName;
    @SerializedName("sm_name")
    @Expose
    private String smName;
    @SerializedName("publish_start_end_date")
    @Expose
    private String publishStartEndDate;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public Integer getSrpcId() {
        return srpcId;
    }

    public void setSrpcId(Integer srpcId) {
        this.srpcId = srpcId;
    }

    public Integer getSrpcCollegeId() {
        return srpcCollegeId;
    }

    public void setSrpcCollegeId(Integer srpcCollegeId) {
        this.srpcCollegeId = srpcCollegeId;
    }

    public Integer getSrpcDeptId() {
        return srpcDeptId;
    }

    public void setSrpcDeptId(Integer srpcDeptId) {
        this.srpcDeptId = srpcDeptId;
    }

    public Integer getSrpcCourseId() {
        return srpcCourseId;
    }

    public void setSrpcCourseId(Integer srpcCourseId) {
        this.srpcCourseId = srpcCourseId;
    }

    public Integer getSrpcSemId() {
        return srpcSemId;
    }

    public void setSrpcSemId(Integer srpcSemId) {
        this.srpcSemId = srpcSemId;
    }

    public String getSrpcReportName() {
        return srpcReportName;
    }

    public void setSrpcReportName(String srpcReportName) {
        this.srpcReportName = srpcReportName;
    }

    public String getSrpcResultDisplayDate() {
        return srpcResultDisplayDate;
    }

    public void setSrpcResultDisplayDate(String srpcResultDisplayDate) {
        this.srpcResultDisplayDate = srpcResultDisplayDate;
    }

    public String getSrpcResultHeading() {
        return srpcResultHeading;
    }

    public void setSrpcResultHeading(String srpcResultHeading) {
        this.srpcResultHeading = srpcResultHeading;
    }

    public Integer getSrpcIsExamDisplay() {
        return srpcIsExamDisplay;
    }

    public void setSrpcIsExamDisplay(Integer srpcIsExamDisplay) {
        this.srpcIsExamDisplay = srpcIsExamDisplay;
    }

    public Integer getSrpcIsTotalMarksDisplay() {
        return srpcIsTotalMarksDisplay;
    }

    public void setSrpcIsTotalMarksDisplay(Integer srpcIsTotalMarksDisplay) {
        this.srpcIsTotalMarksDisplay = srpcIsTotalMarksDisplay;
    }

    public String getSrpcRemarks() {
        return srpcRemarks;
    }

    public void setSrpcRemarks(String srpcRemarks) {
        this.srpcRemarks = srpcRemarks;
    }

    public String getSrpcPublishStartDate() {
        return srpcPublishStartDate;
    }

    public void setSrpcPublishStartDate(String srpcPublishStartDate) {
        this.srpcPublishStartDate = srpcPublishStartDate;
    }

    public String getSrpcPublishEndDate() {
        return srpcPublishEndDate;
    }

    public void setSrpcPublishEndDate(String srpcPublishEndDate) {
        this.srpcPublishEndDate = srpcPublishEndDate;
    }

    public String getSrpcExamString() {
        return srpcExamString;
    }

    public void setSrpcExamString(String srpcExamString) {
        this.srpcExamString = srpcExamString;
    }

    public String getSrpcTermString() {
        return srpcTermString;
    }

    public void setSrpcTermString(String srpcTermString) {
        this.srpcTermString = srpcTermString;
    }

    public Integer getSrpcYearId() {
        return srpcYearId;
    }

    public void setSrpcYearId(Integer srpcYearId) {
        this.srpcYearId = srpcYearId;
    }

    public Integer getSwdDivisionId() {
        return swdDivisionId;
    }

    public void setSwdDivisionId(Integer swdDivisionId) {
        this.swdDivisionId = swdDivisionId;
    }

    public String getStudAdmissionNo() {
        return studAdmissionNo;
    }

    public void setStudAdmissionNo(String studAdmissionNo) {
        this.studAdmissionNo = studAdmissionNo;
    }

    public String getSrpcResultName() {
        return srpcResultName;
    }

    public void setSrpcResultName(String srpcResultName) {
        this.srpcResultName = srpcResultName;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getPublishStartEndDate() {
        return publishStartEndDate;
    }

    public void setPublishStartEndDate(String publishStartEndDate) {
        this.publishStartEndDate = publishStartEndDate;
    }

}
