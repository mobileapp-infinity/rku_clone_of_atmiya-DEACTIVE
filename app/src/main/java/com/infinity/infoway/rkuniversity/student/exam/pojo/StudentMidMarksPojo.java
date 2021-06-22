package com.infinity.infoway.rkuniversity.student.exam.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentMidMarksPojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("main_ex_id")
    @Expose
    private String mainExId;
    @SerializedName("main_ex_name")
    @Expose
    private String mainExName;
    @SerializedName("main_re_date")
    @Expose
    private String mainReDate;
    @SerializedName("Subjects_array")
    @Expose
    private List<SubjectsArray> subjectsArray = null;

    public String getMainExId() {
        return mainExId;
    }

    public void setMainExId(String mainExId) {
        this.mainExId = mainExId;
    }

    public String getMainExName() {
        return mainExName;
    }

    public void setMainExName(String mainExName) {
        this.mainExName = mainExName;
    }

    public String getMainReDate() {
        return mainReDate;
    }

    public void setMainReDate(String mainReDate) {
        this.mainReDate = mainReDate;
    }

    public List<SubjectsArray> getSubjectsArray() {
        return subjectsArray;
    }

    public void setSubjectsArray(List<SubjectsArray> subjectsArray) {
        this.subjectsArray = subjectsArray;
    }

    public class SubjectsArray {

        @SerializedName("sub_name")
        @Expose
        private String subName;
        @SerializedName("sub_tot_mark")
        @Expose
        private String subTotMark;
        @SerializedName("sub_obt_mark")
        @Expose
        private String subObtMark;
        @SerializedName("sub_weight")
        @Expose
        private String subWeight;

        public String getSubName() {
            return subName;
        }

        public void setSubName(String subName) {
            this.subName = subName;
        }

        public String getSubTotMark() {
            return subTotMark;
        }

        public void setSubTotMark(String subTotMark) {
            this.subTotMark = subTotMark;
        }

        public String getSubObtMark() {
            return subObtMark;
        }

        public void setSubObtMark(String subObtMark) {
            this.subObtMark = subObtMark;
        }

        public String getSubWeight() {
            return subWeight;
        }

        public void setSubWeight(String subWeight) {
            this.subWeight = subWeight;
        }

    }

}
