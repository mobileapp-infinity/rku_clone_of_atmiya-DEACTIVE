package com.infinity.infoway.rkuniversity.faculty.faculty_student_messages.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentMsgListPojo {

    @SerializedName("srno")
    @Expose
    private Integer srno;
    @SerializedName("Stud_Enrollment_no")
    @Expose
    private String studEnrollmentNo;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("smt_title")
    @Expose
    private String smtTitle;
    @SerializedName("smt_message")
    @Expose
    private String smtMessage;

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public String getStudEnrollmentNo() {
        return studEnrollmentNo;
    }

    public void setStudEnrollmentNo(String studEnrollmentNo) {
        this.studEnrollmentNo = studEnrollmentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSmtTitle() {
        return smtTitle;
    }

    public void setSmtTitle(String smtTitle) {
        this.smtTitle = smtTitle;
    }

    public String getSmtMessage() {
        return smtMessage;
    }

    public void setSmtMessage(String smtMessage) {
        this.smtMessage = smtMessage;
    }

}
