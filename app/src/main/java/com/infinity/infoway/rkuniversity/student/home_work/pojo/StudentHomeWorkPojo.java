package com.infinity.infoway.rkuniversity.student.home_work.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StudentHomeWorkPojo implements Serializable{

    @SerializedName("day_id")
    @Expose
    private String dayId;
    @SerializedName("day_name")
    @Expose
    private String dayName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("homework_array")
    @Expose
    private List<HomeworkArray> homeworkArray = null;

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<HomeworkArray> getHomeworkArray() {
        return homeworkArray;
    }

    public void setHomeworkArray(List<HomeworkArray> homeworkArray) {
        this.homeworkArray = homeworkArray;
    }

    public class HomeworkArray implements Serializable {

        @SerializedName("lect_no")
        @Expose
        private String lectNo;
        @SerializedName("subject_name")
        @Expose
        private String subjectName;
        @SerializedName("cont_deli_desc")
        @Expose
        private String contDeliDesc;
        @SerializedName("homework_desc")
        @Expose
        private String homeworkDesc;

        public String getLectNo() {
            return lectNo;
        }

        public void setLectNo(String lectNo) {
            this.lectNo = lectNo;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getContDeliDesc() {
            return contDeliDesc;
        }

        public void setContDeliDesc(String contDeliDesc) {
            this.contDeliDesc = contDeliDesc;
        }

        public String getHomeworkDesc() {
            return homeworkDesc;
        }

        public void setHomeworkDesc(String homeworkDesc) {
            this.homeworkDesc = homeworkDesc;
        }

    }

}
