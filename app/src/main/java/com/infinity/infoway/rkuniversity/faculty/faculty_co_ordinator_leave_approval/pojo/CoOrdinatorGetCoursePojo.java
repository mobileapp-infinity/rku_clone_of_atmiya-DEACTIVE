package com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoOrdinatorGetCoursePojo {

    @SerializedName("course_id")
    @Expose
    private Integer courseId;
    @SerializedName("course_name")
    @Expose
    private String courseName;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
