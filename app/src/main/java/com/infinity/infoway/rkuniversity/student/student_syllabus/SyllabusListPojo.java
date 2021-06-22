package com.infinity.infoway.rkuniversity.student.student_syllabus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyllabusListPojo {

    @SerializedName("course_fullname")
    @Expose
    private String courseFullname;
    @SerializedName("sm_name")
    @Expose
    private String smName;
    @SerializedName("sub_code")
    @Expose
    private String subCode;
    @SerializedName("Subject_Name")
    @Expose
    private String subjectName;
    @SerializedName("PDF_URL")
    @Expose
    private String pDFURL;

    public String getCourseFullname() {
        return courseFullname;
    }

    public void setCourseFullname(String courseFullname) {
        this.courseFullname = courseFullname;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPDFURL() {
        return pDFURL;
    }

    public void setPDFURL(String pDFURL) {
        this.pDFURL = pDFURL;
    }

}
