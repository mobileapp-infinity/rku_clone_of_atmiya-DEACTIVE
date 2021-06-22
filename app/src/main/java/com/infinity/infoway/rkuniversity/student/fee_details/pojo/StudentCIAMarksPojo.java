package com.infinity.infoway.rkuniversity.student.fee_details.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentCIAMarksPojo {

    @SerializedName("semester_name")
    @Expose
    private String semesterName;
    @SerializedName("Subject_Code")
    @Expose
    private String subjectCode;
    @SerializedName("Subject_Name")
    @Expose
    private String subjectName;
    @SerializedName("Total_Marks")
    @Expose
    private String totalMarks;
    @SerializedName("Total_Obt_Marks")
    @Expose
    private String totalObtMarks;
    @SerializedName("component_detail")
    @Expose
    private List<ComponentDetail> componentDetail = null;

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getTotalObtMarks() {
        return totalObtMarks;
    }

    public void setTotalObtMarks(String totalObtMarks) {
        this.totalObtMarks = totalObtMarks;
    }

    public List<ComponentDetail> getComponentDetail() {
        return componentDetail;
    }

    public void setComponentDetail(List<ComponentDetail> componentDetail) {
        this.componentDetail = componentDetail;
    }

    public class ComponentDetail {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Total_Marks")
        @Expose
        private String totalMarks;
        @SerializedName("Obtain_Marks")
        @Expose
        private String obtainMarks;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotalMarks() {
            return totalMarks;
        }

        public void setTotalMarks(String totalMarks) {
            this.totalMarks = totalMarks;
        }

        public String getObtainMarks() {
            return obtainMarks;
        }

        public void setObtainMarks(String obtainMarks) {
            this.obtainMarks = obtainMarks;
        }

    }

}
