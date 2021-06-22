package com.infinity.infoway.rkuniversity.faculty.faculty_pending_attendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class FacultyPendingAttendancePojo implements Serializable {


    @SerializedName("Table")
    @Expose
    private ArrayList<Details> detailsArrayList;

    public ArrayList<Details> getDetailsArrayList() {
        return detailsArrayList;
    }

    public void setDetailsArrayList(ArrayList<Details> detailsArrayList) {
        this.detailsArrayList = detailsArrayList;
    }

    public class Details implements Serializable{

        boolean isExpanded = true;

        public boolean isExpanded() {
            return isExpanded;
        }

        public void setExpanded(boolean expanded) {
            isExpanded = expanded;
        }


        @SerializedName("dl_date")
        @Expose
        private String dlDate;
        @SerializedName("college_id")
        @Expose
        private Integer collegeId;
        @SerializedName("college_name")
        @Expose
        private String collegeName;
        @SerializedName("dept_id")
        @Expose
        private Integer deptId;
        @SerializedName("department")
        @Expose
        private String department;
        @SerializedName("course_id")
        @Expose
        private Integer courseId;
        @SerializedName("course_name")
        @Expose
        private String courseName;
        @SerializedName("sm_id")
        @Expose
        private Integer smId;
        @SerializedName("semester_name")
        @Expose
        private String semesterName;
        @SerializedName("div_id")
        @Expose
        private Integer divId;
        @SerializedName("division_name")
        @Expose
        private String divisionName;
        @SerializedName("sub_id")
        @Expose
        private Integer subId;
        @SerializedName("sub_name")
        @Expose
        private String subName;
        @SerializedName("batch_id")
        @Expose
        private Integer batchId;
        @SerializedName("batch_name")
        @Expose
        private String batchName;
        @SerializedName("DL_RECOURSE_ID")
        @Expose
        private Integer dLRECOURSEID;
        @SerializedName("resourse_name")
        @Expose
        private String resourseName;
        @SerializedName("lec_no")
        @Expose
        private Integer lecNo;
        @SerializedName("lecture_name")
        @Expose
        private String lectureName;
        @SerializedName("lec_type")
        @Expose
        private String lecType;
        @SerializedName("DL_VERSION_ID")
        @Expose
        private Integer dLVERSIONID;
        @SerializedName("lect_name")
        @Expose
        private String lectName;
        @SerializedName("lect_type")
        @Expose
        private String lectType;
        @SerializedName("emp_id")
        @Expose
        private Integer empId;
        @SerializedName("dl_is_homework")
        @Expose
        private Integer dlIsHomework;

        public String getDlDate() {
            return dlDate;
        }

        public void setDlDate(String dlDate) {
            this.dlDate = dlDate;
        }

        public Integer getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(Integer collegeId) {
            this.collegeId = collegeId;
        }

        public String getCollegeName() {
            return collegeName;
        }

        public void setCollegeName(String collegeName) {
            this.collegeName = collegeName;
        }

        public Integer getDeptId() {
            return deptId;
        }

        public void setDeptId(Integer deptId) {
            this.deptId = deptId;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

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

        public Integer getSmId() {
            return smId;
        }

        public void setSmId(Integer smId) {
            this.smId = smId;
        }

        public String getSemesterName() {
            return semesterName;
        }

        public void setSemesterName(String semesterName) {
            this.semesterName = semesterName;
        }

        public Integer getDivId() {
            return divId;
        }

        public void setDivId(Integer divId) {
            this.divId = divId;
        }

        public String getDivisionName() {
            return divisionName;
        }

        public void setDivisionName(String divisionName) {
            this.divisionName = divisionName;
        }

        public Integer getSubId() {
            return subId;
        }

        public void setSubId(Integer subId) {
            this.subId = subId;
        }

        public String getSubName() {
            return subName;
        }

        public void setSubName(String subName) {
            this.subName = subName;
        }

        public Integer getBatchId() {
            return batchId;
        }

        public void setBatchId(Integer batchId) {
            this.batchId = batchId;
        }

        public String getBatchName() {
            return batchName;
        }

        public void setBatchName(String batchName) {
            this.batchName = batchName;
        }

        public Integer getDLRECOURSEID() {
            return dLRECOURSEID;
        }

        public void setDLRECOURSEID(Integer dLRECOURSEID) {
            this.dLRECOURSEID = dLRECOURSEID;
        }

        public String getResourseName() {
            return resourseName;
        }

        public void setResourseName(String resourseName) {
            this.resourseName = resourseName;
        }

        public Integer getLecNo() {
            return lecNo;
        }

        public void setLecNo(Integer lecNo) {
            this.lecNo = lecNo;
        }

        public String getLectureName() {
            return lectureName;
        }

        public void setLectureName(String lectureName) {
            this.lectureName = lectureName;
        }

        public String getLecType() {
            return lecType;
        }

        public void setLecType(String lecType) {
            this.lecType = lecType;
        }

        public Integer getDLVERSIONID() {
            return dLVERSIONID;
        }

        public void setDLVERSIONID(Integer dLVERSIONID) {
            this.dLVERSIONID = dLVERSIONID;
        }

        public String getLectName() {
            return lectName;
        }

        public void setLectName(String lectName) {
            this.lectName = lectName;
        }

        public String getLectType() {
            return lectType;
        }

        public void setLectType(String lectType) {
            this.lectType = lectType;
        }

        public Integer getEmpId() {
            return empId;
        }

        public void setEmpId(Integer empId) {
            this.empId = empId;
        }

        public Integer getDlIsHomework() {
            return dlIsHomework;
        }

        public void setDlIsHomework(Integer dlIsHomework) {
            this.dlIsHomework = dlIsHomework;
        }


    }


}
