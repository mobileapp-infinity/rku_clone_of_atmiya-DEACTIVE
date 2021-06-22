package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_subject_wise_division_wise_total_theory_period_engaged;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacultySubAndDivWiseTotTheoryPeriodEngagedPojo {

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
    @SerializedName("sem_name")
    @Expose
    private String semName;
    @SerializedName("div_name")
    @Expose
    private String divName;
    @SerializedName("sub_name")
    @Expose
    private String subName;
    @SerializedName("lec_per_week")
    @Expose
    private Integer lecPerWeek;
    @SerializedName("year_name")
    @Expose
    private String yearName;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getLecPerWeek() {
        return lecPerWeek;
    }

    public void setLecPerWeek(Integer lecPerWeek) {
        this.lecPerWeek = lecPerWeek;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }
}
