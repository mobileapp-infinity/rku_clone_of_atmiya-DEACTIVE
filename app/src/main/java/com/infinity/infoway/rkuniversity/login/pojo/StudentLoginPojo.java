package com.infinity.infoway.rkuniversity.login.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentLoginPojo {

    @SerializedName("stud_id")
    @Expose
    private String studId;
    @SerializedName("dm_id")
    @Expose
    private String dmId;
    @SerializedName("dm_full_name")
    @Expose
    private String dmFullName;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("course_fullname")
    @Expose
    private String courseFullname;
    @SerializedName("sm_id")
    @Expose
    private String smId;
    @SerializedName("sm_name")
    @Expose
    private String smName;
    @SerializedName("ac_id")
    @Expose
    private String acId;
    @SerializedName("ac_full_name")
    @Expose
    private String acFullName;
    @SerializedName("swd_year_id")
    @Expose
    private String swdYearId;
    @SerializedName("status")
    @Expose
    private String status;
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
    private String swdDivisionId;
    @SerializedName("swd_batch_id")
    @Expose
    private String swdBatchId;
    @SerializedName("shift_id")
    @Expose
    private String shiftId;
    @SerializedName("im_domain_name")
    @Expose
    private String imDomainName;
    @SerializedName("intitute_id")
    @Expose
    private String intituteId;
    @SerializedName("fc_file")
    @Expose
    private String fcFile;
    @SerializedName("Stud_user_name")
    @Expose
    private String studUserName;
    @SerializedName("Stud_password")
    @Expose
    private Object studPassword;
    @SerializedName("im_exam_db_name")
    @Expose
    private String imExamDbName;
    @SerializedName("login_user_type")
    @Expose
    private Integer loginUserType;

    public Integer getLoginUserType() {
        return loginUserType;
    }

    public void setLoginUserType(Integer loginUserType) {
        this.loginUserType = loginUserType;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getDmId() {
        return dmId;
    }

    public void setDmId(String dmId) {
        this.dmId = dmId;
    }

    public String getDmFullName() {
        return dmFullName;
    }

    public void setDmFullName(String dmFullName) {
        this.dmFullName = dmFullName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseFullname() {
        return courseFullname;
    }

    public void setCourseFullname(String courseFullname) {
        this.courseFullname = courseFullname;
    }

    public String getSmId() {
        return smId;
    }

    public void setSmId(String smId) {
        this.smId = smId;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId;
    }

    public String getAcFullName() {
        return acFullName;
    }

    public void setAcFullName(String acFullName) {
        this.acFullName = acFullName;
    }

    public String getSwdYearId() {
        return swdYearId;
    }

    public void setSwdYearId(String swdYearId) {
        this.swdYearId = swdYearId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getSwdDivisionId() {
        return swdDivisionId;
    }

    public void setSwdDivisionId(String swdDivisionId) {
        this.swdDivisionId = swdDivisionId;
    }

    public String getSwdBatchId() {
        return swdBatchId;
    }

    public void setSwdBatchId(String swdBatchId) {
        this.swdBatchId = swdBatchId;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getImDomainName() {
        return imDomainName;
    }

    public void setImDomainName(String imDomainName) {
        this.imDomainName = imDomainName;
    }

    public String getIntituteId() {
        return intituteId;
    }

    public void setIntituteId(String intituteId) {
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

    public Object getStudPassword() {
        return studPassword;
    }

    public void setStudPassword(Object studPassword) {
        this.studPassword = studPassword;
    }

    public String getImExamDbName() {
        return imExamDbName;
    }

    public void setImExamDbName(String imExamDbName) {
        this.imExamDbName = imExamDbName;
    }
}
