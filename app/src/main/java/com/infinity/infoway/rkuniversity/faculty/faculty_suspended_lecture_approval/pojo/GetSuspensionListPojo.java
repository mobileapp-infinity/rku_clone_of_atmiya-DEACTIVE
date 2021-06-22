package com.infinity.infoway.rkuniversity.faculty.faculty_suspended_lecture_approval.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSuspensionListPojo {

    @SerializedName("ts_lec_id")
    @Expose
    private Integer tsLecId;
    @SerializedName("college")
    @Expose
    private String college;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("ts_lec_course_id")
    @Expose
    private Integer tsLecCourseId;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("ts_lec_sem_id")
    @Expose
    private Integer tsLecSemId;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("ts_lec_div_id")
    @Expose
    private Integer tsLecDivId;
    @SerializedName("ts_lec_batch_id")
    @Expose
    private Integer tsLecBatchId;
    @SerializedName("lect_date")
    @Expose
    private String lectDate;
    @SerializedName("lect_no")
    @Expose
    private Integer lectNo;
    @SerializedName("ts_lec_type")
    @Expose
    private Integer tsLecType;
    @SerializedName("period_no")
    @Expose
    private String periodNo;
    @SerializedName("emp_name")
    @Expose
    private String empName;
    @SerializedName("lect_type")
    @Expose
    private String lectType;
    @SerializedName("ts_lec_reason")
    @Expose
    private Integer tsLecReason;
    @SerializedName("lec_app_status")
    @Expose
    private String lecAppStatus;
    @SerializedName("ts_lec_approve_status")
    @Expose
    private Integer tsLecApproveStatus;
    @SerializedName("app_by_emp")
    @Expose
    private String appByEmp;
    @SerializedName("ts_lec_sub_id")
    @Expose
    private Integer tsLecSubId;
    @SerializedName("ts_lec_emp_id")
    @Expose
    private Integer tsLecEmpId;

    public Integer getTsLecId() {
        return tsLecId;
    }

    public void setTsLecId(Integer tsLecId) {
        this.tsLecId = tsLecId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getTsLecCourseId() {
        return tsLecCourseId;
    }

    public void setTsLecCourseId(Integer tsLecCourseId) {
        this.tsLecCourseId = tsLecCourseId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getTsLecSemId() {
        return tsLecSemId;
    }

    public void setTsLecSemId(Integer tsLecSemId) {
        this.tsLecSemId = tsLecSemId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Integer getTsLecDivId() {
        return tsLecDivId;
    }

    public void setTsLecDivId(Integer tsLecDivId) {
        this.tsLecDivId = tsLecDivId;
    }

    public Integer getTsLecBatchId() {
        return tsLecBatchId;
    }

    public void setTsLecBatchId(Integer tsLecBatchId) {
        this.tsLecBatchId = tsLecBatchId;
    }

    public String getLectDate() {
        return lectDate;
    }

    public void setLectDate(String lectDate) {
        this.lectDate = lectDate;
    }

    public Integer getLectNo() {
        return lectNo;
    }

    public void setLectNo(Integer lectNo) {
        this.lectNo = lectNo;
    }

    public Integer getTsLecType() {
        return tsLecType;
    }

    public void setTsLecType(Integer tsLecType) {
        this.tsLecType = tsLecType;
    }

    public String getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getLectType() {
        return lectType;
    }

    public void setLectType(String lectType) {
        this.lectType = lectType;
    }

    public Integer getTsLecReason() {
        return tsLecReason;
    }

    public void setTsLecReason(Integer tsLecReason) {
        this.tsLecReason = tsLecReason;
    }

    public String getLecAppStatus() {
        return lecAppStatus;
    }

    public void setLecAppStatus(String lecAppStatus) {
        this.lecAppStatus = lecAppStatus;
    }

    public Integer getTsLecApproveStatus() {
        return tsLecApproveStatus;
    }

    public void setTsLecApproveStatus(Integer tsLecApproveStatus) {
        this.tsLecApproveStatus = tsLecApproveStatus;
    }

    public String getAppByEmp() {
        return appByEmp;
    }

    public void setAppByEmp(String appByEmp) {
        this.appByEmp = appByEmp;
    }

    public Integer getTsLecSubId() {
        return tsLecSubId;
    }

    public void setTsLecSubId(Integer tsLecSubId) {
        this.tsLecSubId = tsLecSubId;
    }

    public Integer getTsLecEmpId() {
        return tsLecEmpId;
    }

    public void setTsLecEmpId(Integer tsLecEmpId) {
        this.tsLecEmpId = tsLecEmpId;
    }

}
