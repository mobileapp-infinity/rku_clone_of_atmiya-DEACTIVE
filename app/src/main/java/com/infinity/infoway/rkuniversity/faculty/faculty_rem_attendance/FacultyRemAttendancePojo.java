package com.infinity.infoway.rkuniversity.faculty.faculty_rem_attendance;

import com.google.gson.annotations.SerializedName;

public class FacultyRemAttendancePojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("dl_date")
    private String date;

    public String getDate()
    {
        return date;
    }

    @SerializedName("course")
    private String course;

    public String getCourse()
    {
        return course;
    }

    @SerializedName("sem_name")
    private String semname;

    public String getSemname()
    {
        return semname;
    }


    @SerializedName("division")
    private String division;

    public String getDivision()
    {
        return division;
    }


    @SerializedName("lec_no")
    private String lecno;

    public String getLecno()
    {
        return lecno;
    }

}
