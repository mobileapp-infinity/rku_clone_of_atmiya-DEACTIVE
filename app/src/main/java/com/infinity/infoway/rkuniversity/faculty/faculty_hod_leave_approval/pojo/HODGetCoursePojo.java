package com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HODGetCoursePojo {

    @SerializedName("course_id")
    @Expose
    private Integer courseId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("course_id1")
    @Expose
    private Integer courseId1;
    @SerializedName("course_institute_id")
    @Expose
    private Integer courseInstituteId;
    @SerializedName("course_code")
    @Expose
    private String courseCode;
    @SerializedName("course_fullname")
    @Expose
    private String courseFullname;
    @SerializedName("course_short_name")
    @Expose
    private String courseShortName;
    @SerializedName("course_shift_id")
    @Expose
    private Integer courseShiftId;
    @SerializedName("course_is_delete")
    @Expose
    private Integer courseIsDelete;
    @SerializedName("course_is_status")
    @Expose
    private Integer courseIsStatus;
    @SerializedName("course_type_id")
    @Expose
    private Integer courseTypeId;

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

    public Integer getCourseId1() {
        return courseId1;
    }

    public void setCourseId1(Integer courseId1) {
        this.courseId1 = courseId1;
    }

    public Integer getCourseInstituteId() {
        return courseInstituteId;
    }

    public void setCourseInstituteId(Integer courseInstituteId) {
        this.courseInstituteId = courseInstituteId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseFullname() {
        return courseFullname;
    }

    public void setCourseFullname(String courseFullname) {
        this.courseFullname = courseFullname;
    }

    public String getCourseShortName() {
        return courseShortName;
    }

    public void setCourseShortName(String courseShortName) {
        this.courseShortName = courseShortName;
    }

    public Integer getCourseShiftId() {
        return courseShiftId;
    }

    public void setCourseShiftId(Integer courseShiftId) {
        this.courseShiftId = courseShiftId;
    }

    public Integer getCourseIsDelete() {
        return courseIsDelete;
    }

    public void setCourseIsDelete(Integer courseIsDelete) {
        this.courseIsDelete = courseIsDelete;
    }

    public Integer getCourseIsStatus() {
        return courseIsStatus;
    }

    public void setCourseIsStatus(Integer courseIsStatus) {
        this.courseIsStatus = courseIsStatus;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

}
