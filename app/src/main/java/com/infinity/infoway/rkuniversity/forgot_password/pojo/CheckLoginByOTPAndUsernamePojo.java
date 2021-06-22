package com.infinity.infoway.rkuniversity.forgot_password.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckLoginByOTPAndUsernamePojo {

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

        //for student response parameters
        @SerializedName("ac_id")
        @Expose
        private Integer acId;
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
        @SerializedName("swd_year_id")
        @Expose
        private Integer swdYearId;
        @SerializedName("hostel_code")
        @Expose
        private String hostelCode;
        @SerializedName("stud_admission_no")
        @Expose
        private String studAdmissionNo;
        @SerializedName("Stud_Enrollment_no")
        @Expose
        private String studEnrollmentNo;
        @SerializedName("swd_division_id")
        @Expose
        private Integer swdDivisionId;
        @SerializedName("swd_batch_id")
        @Expose
        private Integer swdBatchId;
        @SerializedName("shift_id")
        @Expose
        private Integer shiftId;
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

        public String getImExamDbName() {
            return imExamDbName;
        }

        public void setImExamDbName(String imExamDbName) {
            this.imExamDbName = imExamDbName;
        }

        public Integer getAcId() {
            return acId;
        }

        public void setAcId(Integer acId) {
            this.acId = acId;
        }

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

        public Integer getSwdYearId() {
            return swdYearId;
        }

        public void setSwdYearId(Integer swdYearId) {
            this.swdYearId = swdYearId;
        }

        public String getHostelCode() {
            return hostelCode;
        }

        public void setHostelCode(String hostelCode) {
            this.hostelCode = hostelCode;
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


        //for employee response parameters
        @SerializedName("emp_id")
        @Expose
        private Integer empId;
        @SerializedName("is_admin")
        @Expose
        private Integer isAdmin;
        @SerializedName("emp_number")
        @Expose
        private String empNumber;
        @SerializedName("emp_main_school_id")
        @Expose
        private Integer empMainSchoolId;
        @SerializedName("ac_logo")
        @Expose
        private String acLogo;
        @SerializedName("is_director")
        @Expose
        private Integer isDirector;
        @SerializedName("emp_year_id")
        @Expose
        private Integer empYearId;
        @SerializedName("emp_user_status")
        @Expose
        private Integer empUserStatus;
        @SerializedName("emp_permenant_college")
        @Expose
        private Object empPermenantCollege;
        @SerializedName("emp_department_id")
        @Expose
        private Integer empDepartmentId;
        @SerializedName("emp_username")
        @Expose
        private String empUsername;
        @SerializedName("emp_password")
        @Expose
        private Object empPassword;

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

        public Integer getEmpMainSchoolId() {
            return empMainSchoolId;
        }

        public void setEmpMainSchoolId(Integer empMainSchoolId) {
            this.empMainSchoolId = empMainSchoolId;
        }

        public String getAcLogo() {
            return acLogo;
        }

        public void setAcLogo(String acLogo) {
            this.acLogo = acLogo;
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

        //common parameters
        @SerializedName("institute_id")
        @Expose
        private Integer instituteId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("college_code")
        @Expose
        private Integer collegeCode;
        @SerializedName("college_name")
        @Expose
        private String collegeName;
        @SerializedName("stud_photo")
        @Expose
        private String studPhoto;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("im_domain_name")
        @Expose
        private String imDomainName;
        @SerializedName("ac_full_name")
        @Expose
        private String acFullName;
        @SerializedName("ac_code")
        @Expose
        private String acCode;

        public String getAcFullName() {
            return acFullName;
        }

        public void setAcFullName(String acFullName) {
            this.acFullName = acFullName;
        }

        public String getAcCode() {
            return acCode;
        }

        public void setAcCode(String acCode) {
            this.acCode = acCode;
        }

        public Integer getInstituteId() {
            return instituteId;
        }

        public void setInstituteId(Integer instituteId) {
            this.instituteId = instituteId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCollegeCode() {
            return collegeCode;
        }

        public void setCollegeCode(Integer collegeCode) {
            this.collegeCode = collegeCode;
        }

        public String getCollegeName() {
            return collegeName;
        }

        public void setCollegeName(String collegeName) {
            this.collegeName = collegeName;
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

        public String getImDomainName() {
            return imDomainName;
        }

        public void setImDomainName(String imDomainName) {
            this.imDomainName = imDomainName;
        }

    }
}
