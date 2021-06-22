package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_student_forum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacultyStudentForumPojo {

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
    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("activity_date")
    @Expose
    private String activityDate;
    @SerializedName("no_of_pre_stud")
    @Expose
    private String noOfPreStud;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getNoOfPreStud() {
        return noOfPreStud;
    }

    public void setNoOfPreStud(String noOfPreStud) {
        this.noOfPreStud = noOfPreStud;
    }
}
