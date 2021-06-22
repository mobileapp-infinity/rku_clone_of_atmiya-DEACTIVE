package com.infinity.infoway.rkuniversity.faculty.faculty_extra_lecture_approval;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtraLectureApprovalPojo {
    @SerializedName("ex_lec_id")
    @Expose
    private String exLecId;
    @SerializedName("employee")
    @Expose
    private Integer employee;
    @SerializedName("emp_name")
    @Expose
    private String empName;
    @SerializedName("ext_lect_date")
    @Expose
    private String extLectDate;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("ext_lec_type")
    @Expose
    private String extLecType;
    @SerializedName("lect_no")
    @Expose
    private String lectNo;
    @SerializedName("sub")
    @Expose
    private String sub;
    @SerializedName("res")
    @Expose
    private String res;
    @SerializedName("app_status")
    @Expose
    private String appStatus;
    @SerializedName("ex_lec_app_status")
    @Expose
    private Integer exLecAppStatus;

    public String getExLecId() {
        return exLecId;
    }

    public void setExLecId(String exLecId) {
        this.exLecId = exLecId;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getExtLectDate() {
        return extLectDate;
    }

    public void setExtLectDate(String extLectDate) {
        this.extLectDate = extLectDate;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getExtLecType() {
        return extLecType;
    }

    public void setExtLecType(String extLecType) {
        this.extLecType = extLecType;
    }

    public String getLectNo() {
        return lectNo;
    }

    public void setLectNo(String lectNo) {
        this.lectNo = lectNo;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public Integer getExLecAppStatus() {
        return exLecAppStatus;
    }

    public void setExLecAppStatus(Integer exLecAppStatus) {
        this.exLecAppStatus = exLecAppStatus;
    }


}
