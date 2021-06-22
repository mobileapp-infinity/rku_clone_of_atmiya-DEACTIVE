package com.infinity.infoway.rkuniversity.faculty.faculty_direct_login_to_student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacultyStudentListPojo {


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

        @SerializedName("Stud_id")
        @Expose
        private Integer studId;
        @SerializedName("stud_name")
        @Expose
        private String studName;
        @SerializedName("Stud_Enrollment_no")
        @Expose
        private String studEnrollmentNo;
        @SerializedName("Stud_admission_no")
        @Expose
        private String studAdmissionNo;
        @SerializedName("Stud_email")
        @Expose
        private String studEmail;
        @SerializedName("ac_short_name")
        @Expose
        private String acShortName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("Stud_mobile_no")
        @Expose
        private String studMobileNo;
        @SerializedName("Stud_user_name")
        @Expose
        private String studUserName;
        @SerializedName("stud_photo")
        @Expose
        private String studPhoto;

        public Integer getStudId() {
            return studId;
        }

        public void setStudId(Integer studId) {
            this.studId = studId;
        }

        public String getStudName() {
            return studName;
        }

        public void setStudName(String studName) {
            this.studName = studName;
        }

        public String getStudEnrollmentNo() {
            return studEnrollmentNo;
        }

        public void setStudEnrollmentNo(String studEnrollmentNo) {
            this.studEnrollmentNo = studEnrollmentNo;
        }

        public String getStudAdmissionNo() {
            return studAdmissionNo;
        }

        public void setStudAdmissionNo(String studAdmissionNo) {
            this.studAdmissionNo = studAdmissionNo;
        }

        public String getStudEmail() {
            return studEmail;
        }

        public void setStudEmail(String studEmail) {
            this.studEmail = studEmail;
        }

        public String getAcShortName() {
            return acShortName;
        }

        public void setAcShortName(String acShortName) {
            this.acShortName = acShortName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getStudMobileNo() {
            return studMobileNo;
        }

        public void setStudMobileNo(String studMobileNo) {
            this.studMobileNo = studMobileNo;
        }

        public String getStudUserName() {
            return studUserName;
        }

        public void setStudUserName(String studUserName) {
            this.studUserName = studUserName;
        }

        public String getStudPhoto() {
            return studPhoto;
        }

        public void setStudPhoto(String studPhoto) {
            this.studPhoto = studPhoto;
        }

    }
}
