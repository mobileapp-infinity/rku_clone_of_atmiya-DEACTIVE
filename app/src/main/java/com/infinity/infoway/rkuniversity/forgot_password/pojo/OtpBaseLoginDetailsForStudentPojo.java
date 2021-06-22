package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OtpBaseLoginDetailsForStudentPojo {


    private List<OtpBaseLoginDetailsForStudentPojo.TableBean> Table;

    public List<OtpBaseLoginDetailsForStudentPojo.TableBean> getTable() {
        return Table;
    }

    public void setTable(List<OtpBaseLoginDetailsForStudentPojo.TableBean> Table) {
        this.Table = Table;
    }

    public class TableBean {
        @SerializedName("stud_id")
        @Expose
        private Integer studId;
        @SerializedName("stud_name")
        @Expose
        private String studName;
        @SerializedName("stud_user_name")
        @Expose
        private String studUserName;
        @SerializedName("Stud_admission_no")
        @Expose
        private String studAdmissionNo;
        @SerializedName("Stud_Enrollment_no")
        @Expose
        private String studEnrollmentNo;
        @SerializedName("stud_mobile_no")
        @Expose
        private String studMobileNo;
        @SerializedName("Stud_password")
        @Expose
        private String studPassword;

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

        public String getStudUserName() {
            return studUserName;
        }

        public void setStudUserName(String studUserName) {
            this.studUserName = studUserName;
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

        public String getStudMobileNo() {
            return studMobileNo;
        }

        public void setStudMobileNo(String studMobileNo) {
            this.studMobileNo = studMobileNo;
        }

        public String getStudPassword() {
            return studPassword;
        }

        public void setStudPassword(String studPassword) {
            this.studPassword = studPassword;
        }


    }

}
