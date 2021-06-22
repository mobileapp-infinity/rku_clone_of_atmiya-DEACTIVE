package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_details_of_theory_sub;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacultyDetailsOfTheorySubjectTaughtPojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


    @SerializedName("SrNo")
    @Expose
    private Integer srNo;
    @SerializedName("college_name")
    @Expose
    private String collegeName;
    @SerializedName("dept_name")
    @Expose
    private String deptName;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("sem_name")
    @Expose
    private String semName;
    @SerializedName("sub_name")
    @Expose
    private String subName;
    @SerializedName("div_name")
    @Expose
    private String divName;
    @SerializedName("resource_name")
    @Expose
    private String resourceName;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
