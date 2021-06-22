package com.infinity.infoway.rkuniversity.faculty.faculty_direct_login_to_student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetStudentDetailsForDirectLoginPojo {

    @SerializedName("Table")
    @Expose
    private List<Table> table = null;

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public class Table {

        @SerializedName("stud_id")
        @Expose
        private Integer studId;
        @SerializedName("dm_id")
        @Expose
        private Integer dmId;
        @SerializedName("dm_full_name")
        @Expose
        private String dmFullName;
        @SerializedName("course_id")
        @Expose
        private Integer courseId;
        @SerializedName("course_fullname")
        @Expose
        private String courseFullname;
        @SerializedName("sm_id")
        @Expose
        private Integer smId;
        @SerializedName("sm_name")
        @Expose
        private String smName;
        @SerializedName("ac_id")
        @Expose
        private Integer acId;
        @SerializedName("ac_full_name")
        @Expose
        private String acFullName;
        @SerializedName("swd_year_id")
        @Expose
        private Integer swdYearId;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("ac_code")
        @Expose
        private String acCode;
        @SerializedName("hostel_code")
        @Expose
        private String hostelCode;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("stud_admission_no")
        @Expose
        private String studAdmissionNo;
        @SerializedName("Stud_Enrollment_no")
        @Expose
        private String studEnrollmentNo;
        @SerializedName("stud_photo")
        @Expose
        private String studPhoto;
        @SerializedName("swd_division_id")
        @Expose
        private Integer swdDivisionId;
        @SerializedName("swd_batch_id")
        @Expose
        private Integer swdBatchId;
        @SerializedName("shift_id")
        @Expose
        private Integer shiftId;
        @SerializedName("im_domain_name")
        @Expose
        private String imDomainName;
        @SerializedName("intitute_id")
        @Expose
        private Integer intituteId;
        @SerializedName("fc_file")
        @Expose
        private String fcFile;
        @SerializedName("Stud_user_name")
        @Expose
        private String studUserName;
        @SerializedName("Stud_password")
        @Expose
        private String studPassword;
        @SerializedName("im_exam_db_name")
        @Expose
        private String imExamDbName;
        @SerializedName("stud_name")
        @Expose
        private String studName;
        @SerializedName("login_user_type")
        @Expose
        private Integer loginUserType;

        public Integer getStudId() {
            return studId;
        }

        public void setStudId(Integer studId) {
            this.studId = studId;
        }

        public Integer getDmId() {
            return dmId;
        }

        public void setDmId(Integer dmId) {
            this.dmId = dmId;
        }

        public String getDmFullName() {
            return dmFullName;
        }

        public void setDmFullName(String dmFullName) {
            this.dmFullName = dmFullName;
        }

        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        public String getCourseFullname() {
            return courseFullname;
        }

        public void setCourseFullname(String courseFullname) {
            this.courseFullname = courseFullname;
        }

        public Integer getSmId() {
            return smId;
        }

        public void setSmId(Integer smId) {
            this.smId = smId;
        }

        public String getSmName() {
            return smName;
        }

        public void setSmName(String smName) {
            this.smName = smName;
        }

        public Integer getAcId() {
            return acId;
        }

        public void setAcId(Integer acId) {
            this.acId = acId;
        }

        public String getAcFullName() {
            return acFullName;
        }

        public void setAcFullName(String acFullName) {
            this.acFullName = acFullName;
        }

        public Integer getSwdYearId() {
            return swdYearId;
        }

        public void setSwdYearId(Integer swdYearId) {
            this.swdYearId = swdYearId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getAcCode() {
            return acCode;
        }

        public void setAcCode(String acCode) {
            this.acCode = acCode;
        }

        public String getHostelCode() {
            return hostelCode;
        }

        public void setHostelCode(String hostelCode) {
            this.hostelCode = hostelCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStudAdmissionNo() {
            return studAdmissionNo;
        }

        public void setStudAdmissionNo(String studAdmissionNo) {
            this.studAdmissionNo = studAdmissionNo;
        }

        public String getStudEnrollmentNo() {
            return studEnrollmentNo;
        }

        public void setStudEnrollmentNo(String studEnrollmentNo) {
            this.studEnrollmentNo = studEnrollmentNo;
        }

        public String getStudPhoto() {
            return studPhoto;
        }

        public void setStudPhoto(String studPhoto) {
            this.studPhoto = studPhoto;
        }

        public Integer getSwdDivisionId() {
            return swdDivisionId;
        }

        public void setSwdDivisionId(Integer swdDivisionId) {
            this.swdDivisionId = swdDivisionId;
        }

        public Integer getSwdBatchId() {
            return swdBatchId;
        }

        public void setSwdBatchId(Integer swdBatchId) {
            this.swdBatchId = swdBatchId;
        }

        public Integer getShiftId() {
            return shiftId;
        }

        public void setShiftId(Integer shiftId) {
            this.shiftId = shiftId;
        }

        public String getImDomainName() {
            return imDomainName;
        }

        public void setImDomainName(String imDomainName) {
            this.imDomainName = imDomainName;
        }

        public Integer getIntituteId() {
            return intituteId;
        }

        public void setIntituteId(Integer intituteId) {
            this.intituteId = intituteId;
        }

        public String getFcFile() {
            return fcFile;
        }

        public void setFcFile(String fcFile) {
            this.fcFile = fcFile;
        }

        public String getStudUserName() {
            return studUserName;
        }

        public void setStudUserName(String studUserName) {
            this.studUserName = studUserName;
        }

        public String getStudPassword() {
            return studPassword;
        }

        public void setStudPassword(String studPassword) {
            this.studPassword = studPassword;
        }

        public String getImExamDbName() {
            return imExamDbName;
        }

        public void setImExamDbName(String imExamDbName) {
            this.imExamDbName = imExamDbName;
        }

        public String getStudName() {
            return studName;
        }

        public void setStudName(String studName) {
            this.studName = studName;
        }

        public Integer getLoginUserType() {
            return loginUserType;
        }

        public void setLoginUserType(Integer loginUserType) {
            this.loginUserType = loginUserType;
        }

    }

}
