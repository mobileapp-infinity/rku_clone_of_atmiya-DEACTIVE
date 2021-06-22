package com.infinity.infoway.rkuniversity.login.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeLoginPojo {

    @SerializedName("emp_id")
    @Expose
    private Integer empId;
    @SerializedName("is_admin")
    @Expose
    private Integer isAdmin;
    @SerializedName("emp_number")
    @Expose
    private String empNumber;
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
    @SerializedName("emp_photo")
    @Expose
    private String empPhoto;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("ac_code")
    @Expose
    private String acCode;
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
    private Integer empPermenantCollege;
    @SerializedName("emp_department_id")
    @Expose
    private Integer empDepartmentId;
    @SerializedName("login_user_type")
    @Expose
    private Integer loginUserType;
    @SerializedName("ac_id")
    @Expose
    private Integer acId;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
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

    public String getEmpPhoto() {
        return empPhoto;
    }

    public void setEmpPhoto(String empPhoto) {
        this.empPhoto = empPhoto;
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

    public Integer getEmpPermenantCollege() {
        return empPermenantCollege;
    }

    public void setEmpPermenantCollege(Integer empPermenantCollege) {
        this.empPermenantCollege = empPermenantCollege;
    }

    public Integer getEmpDepartmentId() {
        return empDepartmentId;
    }

    public void setEmpDepartmentId(Integer empDepartmentId) {
        this.empDepartmentId = empDepartmentId;
    }

    public Integer getLoginUserType() {
        return loginUserType;
    }

    public void setLoginUserType(Integer loginUserType) {
        this.loginUserType = loginUserType;
    }

}
