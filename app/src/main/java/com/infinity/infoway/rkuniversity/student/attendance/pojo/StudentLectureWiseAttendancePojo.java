package com.infinity.infoway.rkuniversity.student.attendance.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentLectureWiseAttendancePojo {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("day_id")
    @Expose
    private String dayId;
    @SerializedName("all_lecture")
    @Expose
    private List<AllLecture> allLecture = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public List<AllLecture> getAllLecture() {
        return allLecture;
    }

    public void setAllLecture(List<AllLecture> allLecture) {
        this.allLecture = allLecture;
    }

    public class AllLecture {

        @SerializedName("att_status")
        @Expose
        private String attStatus;
        @SerializedName("lec_status")
        @Expose
        private String lecStatus;

        @SerializedName("lect_time")
        @Expose
        private String lectTime;
        @SerializedName("lect_type")
        @Expose
        private String lectType;
        @SerializedName("subject_name")
        @Expose
        private String subjectName;
        @SerializedName("faculty_name")
        @Expose
        private String facultyName;

        public String getLectTime() {
            return lectTime;
        }

        public void setLectTime(String lectTime) {
            this.lectTime = lectTime;
        }

        public String getLectType() {
            return lectType;
        }

        public void setLectType(String lectType) {
            this.lectType = lectType;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getFacultyName() {
            return facultyName;
        }

        public void setFacultyName(String facultyName) {
            this.facultyName = facultyName;
        }

        public String getAttStatus() {
            return attStatus;
        }

        public void setAttStatus(String attStatus) {
            this.attStatus = attStatus;
        }

        public String getLecStatus() {
            return lecStatus;
        }

        public void setLecStatus(String lecStatus) {
            this.lecStatus = lecStatus;
        }


    }
}
