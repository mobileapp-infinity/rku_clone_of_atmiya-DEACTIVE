package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OtpBaseLoginDetailsForEmployeePojo {

    private List<OtpBaseLoginDetailsForEmployeePojo.TableBean> Table;

    public List<OtpBaseLoginDetailsForEmployeePojo.TableBean> getTable() {
        return Table;
    }

    public void setTable(List<OtpBaseLoginDetailsForEmployeePojo.TableBean> Table) {
        this.Table = Table;
    }

    public class TableBean {
        @SerializedName("emp_id")
        @Expose
        private Integer empId;
        @SerializedName("emp_main_school_id")
        @Expose
        private Integer empMainSchoolId;
        @SerializedName("emp_username")
        @Expose
        private String empUsername;
        @SerializedName("emp_password")
        @Expose
        private String empPassword;
        @SerializedName("ac_full_name")
        @Expose
        private String acFullName;
        @SerializedName("ac_logo")
        @Expose
        private String acLogo;
        @SerializedName("ac_institute_id")
        @Expose
        private Integer acInstituteId;
        @SerializedName("emp_number")
        @Expose
        private Integer empNumber;
        @SerializedName("ac_code")
        @Expose
        private Integer acCode;
        @SerializedName("emp_name")
        @Expose
        private String empName;
        @SerializedName("im_exam_db_name")
        @Expose
        private String imExamDbName;
        @SerializedName("emp_department_id")
        @Expose
        private Integer empDepartmentId;
        @SerializedName("emp_permenant_college")
        @Expose
        private Integer empPermenantCollege;
        @SerializedName("emp_user_status")
        @Expose
        private Object empUserStatus;
        @SerializedName("year_id")
        @Expose
        private Integer yearId;
        @SerializedName("im_company_id")
        @Expose
        private Integer imCompanyId;
        @SerializedName("im_ierp_domain_name")
        @Expose
        private Object imIerpDomainName;
        @SerializedName("im_cbsc_domain_name")
        @Expose
        private Object imCbscDomainName;
        @SerializedName("im_yearly_domain_name")
        @Expose
        private Object imYearlyDomainName;
        @SerializedName("emp_mobile_phone")
        @Expose
        private Integer empMobilePhone;
        @SerializedName("is_emp_login_otp_required")
        @Expose
        private Integer isEmpLoginOtpRequired;

        public Integer getEmpId() {
            return empId;
        }

        public void setEmpId(Integer empId) {
            this.empId = empId;
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

        public String getEmpPassword() {
            return empPassword;
        }

        public void setEmpPassword(String empPassword) {
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

        public Integer getAcInstituteId() {
            return acInstituteId;
        }

        public void setAcInstituteId(Integer acInstituteId) {
            this.acInstituteId = acInstituteId;
        }

        public Integer getEmpNumber() {
            return empNumber;
        }

        public void setEmpNumber(Integer empNumber) {
            this.empNumber = empNumber;
        }

        public Integer getAcCode() {
            return acCode;
        }

        public void setAcCode(Integer acCode) {
            this.acCode = acCode;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getImExamDbName() {
            return imExamDbName;
        }

        public void setImExamDbName(String imExamDbName) {
            this.imExamDbName = imExamDbName;
        }

        public Integer getEmpDepartmentId() {
            return empDepartmentId;
        }

        public void setEmpDepartmentId(Integer empDepartmentId) {
            this.empDepartmentId = empDepartmentId;
        }

        public Integer getEmpPermenantCollege() {
            return empPermenantCollege;
        }

        public void setEmpPermenantCollege(Integer empPermenantCollege) {
            this.empPermenantCollege = empPermenantCollege;
        }

        public Object getEmpUserStatus() {
            return empUserStatus;
        }

        public void setEmpUserStatus(Object empUserStatus) {
            this.empUserStatus = empUserStatus;
        }

        public Integer getYearId() {
            return yearId;
        }

        public void setYearId(Integer yearId) {
            this.yearId = yearId;
        }

        public Integer getImCompanyId() {
            return imCompanyId;
        }

        public void setImCompanyId(Integer imCompanyId) {
            this.imCompanyId = imCompanyId;
        }

        public Object getImIerpDomainName() {
            return imIerpDomainName;
        }

        public void setImIerpDomainName(Object imIerpDomainName) {
            this.imIerpDomainName = imIerpDomainName;
        }

        public Object getImCbscDomainName() {
            return imCbscDomainName;
        }

        public void setImCbscDomainName(Object imCbscDomainName) {
            this.imCbscDomainName = imCbscDomainName;
        }

        public Object getImYearlyDomainName() {
            return imYearlyDomainName;
        }

        public void setImYearlyDomainName(Object imYearlyDomainName) {
            this.imYearlyDomainName = imYearlyDomainName;
        }

        public Integer getEmpMobilePhone() {
            return empMobilePhone;
        }

        public void setEmpMobilePhone(Integer empMobilePhone) {
            this.empMobilePhone = empMobilePhone;
        }

        public Integer getIsEmpLoginOtpRequired() {
            return isEmpLoginOtpRequired;
        }

        public void setIsEmpLoginOtpRequired(Integer isEmpLoginOtpRequired) {
            this.isEmpLoginOtpRequired = isEmpLoginOtpRequired;
        }
    }


}
