package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckOTPVerificationForEmployeePojo {

    private List<CheckOTPVerificationForEmployeePojo.TableBean> Table;

    public List<CheckOTPVerificationForEmployeePojo.TableBean> getTable() {
        return Table;
    }

    public void setTable(List<CheckOTPVerificationForEmployeePojo.TableBean> Table) {
        this.Table = Table;
    }

    public class TableBean {

        @SerializedName("emp_id")
        @Expose
        private Integer empId;
        @SerializedName("emp_number")
        @Expose
        private Integer empNumber;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("emp_main_school_id")
        @Expose
        private Integer empMainSchoolId;
        @SerializedName("emp_username")
        @Expose
        private String empUsername;
        @SerializedName("emp_password")
        @Expose
        private Object empPassword;
        @SerializedName("ac_full_name")
        @Expose
        private String acFullName;
        @SerializedName("ac_logo")
        @Expose
        private String acLogo;
        @SerializedName("stud_photo")
        @Expose
        private String studPhoto;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("ac_code")
        @Expose
        private Integer acCode;
        @SerializedName("is_director")
        @Expose
        private Integer isDirector;
        @SerializedName("emp_year_id")
        @Expose
        private Integer empYearId;
        @SerializedName("institute_id")
        @Expose
        private Integer instituteId;
        @SerializedName("im_domain_name")
        @Expose
        private String imDomainName;
        @SerializedName("emp_user_status")
        @Expose
        private Integer empUserStatus;
        @SerializedName("emp_permenant_college")
        @Expose
        private Object empPermenantCollege;
        @SerializedName("emp_department_id")
        @Expose
        private Integer empDepartmentId;

        public Integer getEmpId() {
            return empId;
        }

        public void setEmpId(Integer empId) {
            this.empId = empId;
        }

        public Integer getEmpNumber() {
            return empNumber;
        }

        public void setEmpNumber(Integer empNumber) {
            this.empNumber = empNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getEmpMainSchoolId() {
            return empMainSchoolId;
        }

        public void setEmpMainSchoolId(Integer empMainSchoolId) {
            this.empMainSchoolId = empMainSchoolId;
        }

        public String getEmpUsername() {
            return empUsername;
        }

        public void setEmpUsername(String empUsername) {
            this.empUsername = empUsername;
        }

        public Object getEmpPassword() {
            return empPassword;
        }

        public void setEmpPassword(Object empPassword) {
            this.empPassword = empPassword;
        }

        public String getAcFullName() {
            return acFullName;
        }

        public void setAcFullName(String acFullName) {
            this.acFullName = acFullName;
        }

        public String getAcLogo() {
            return acLogo;
        }

        public void setAcLogo(String acLogo) {
            this.acLogo = acLogo;
        }

        public String getStudPhoto() {
            return studPhoto;
        }

        public void setStudPhoto(String studPhoto) {
            this.studPhoto = studPhoto;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getAcCode() {
            return acCode;
        }

        public void setAcCode(Integer acCode) {
            this.acCode = acCode;
        }

        public Integer getIsDirector() {
            return isDirector;
        }

        public void setIsDirector(Integer isDirector) {
            this.isDirector = isDirector;
        }

        public Integer getEmpYearId() {
            return empYearId;
        }

        public void setEmpYearId(Integer empYearId) {
            this.empYearId = empYearId;
        }

        public Integer getInstituteId() {
            return instituteId;
        }

        public void setInstituteId(Integer instituteId) {
            this.instituteId = instituteId;
        }

        public String getImDomainName() {
            return imDomainName;
        }

        public void setImDomainName(String imDomainName) {
            this.imDomainName = imDomainName;
        }

        public Integer getEmpUserStatus() {
            return empUserStatus;
        }

        public void setEmpUserStatus(Integer empUserStatus) {
            this.empUserStatus = empUserStatus;
        }

        public Object getEmpPermenantCollege() {
            return empPermenantCollege;
        }

        public void setEmpPermenantCollege(Object empPermenantCollege) {
            this.empPermenantCollege = empPermenantCollege;
        }

        public Integer getEmpDepartmentId() {
            return empDepartmentId;
        }

        public void setEmpDepartmentId(Integer empDepartmentId) {
            this.empDepartmentId = empDepartmentId;
        }

    }

}
