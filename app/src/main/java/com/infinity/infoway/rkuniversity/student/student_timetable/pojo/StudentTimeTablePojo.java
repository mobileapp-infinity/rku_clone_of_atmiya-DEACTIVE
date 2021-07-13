package com.infinity.infoway.rkuniversity.student.student_timetable.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StudentTimeTablePojo implements Serializable {

    @SerializedName("day_id")
    @Expose
    private String dayId;
    @SerializedName("day_name")
    @Expose
    private String dayName;
    @SerializedName("isDisplayTime")
    @Expose
    private Integer isDisplayTime;

    public Integer getIsDisplayTime() {
        return isDisplayTime;
    }

    public void setIsDisplayTime(Integer isDisplayTime) {
        this.isDisplayTime = isDisplayTime;
    }

    @SerializedName("inout_array1")
    @Expose
    private List<InoutArray1> inoutArray1 = null;

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

    public List<InoutArray1> getInoutArray1() {
        return inoutArray1;
    }

    public void setInoutArray1(List<InoutArray1> inoutArray1) {
        this.inoutArray1 = inoutArray1;
    }

    public class InoutArray1 implements Serializable {

        @SerializedName("lect_name")
        @Expose
        private String lectName;
        @SerializedName("lect_st_time")
        @Expose
        private String lectStTime;
        @SerializedName("lect_end_time")
        @Expose
        private String lectEndTime;
        @SerializedName("emp_name")
        @Expose
        private String empName;
        @SerializedName("rm_name")
        @Expose
        private String rmName;
        @SerializedName("sub_short_name")
        @Expose
        private String subShortName;
        @SerializedName("tt_prac_the_status")
        @Expose
        private String ttPracTheStatus;
        @SerializedName("lab_array")
        @Expose
        private List<LabArray> labArray;

        public String getLectName() {
            return lectName;
        }

        public void setLectName(String lectName) {
            this.lectName = lectName;
        }

        public String getLectStTime() {
            return lectStTime;
        }

        public void setLectStTime(String lectStTime) {
            this.lectStTime = lectStTime;
        }

        public String getLectEndTime() {
            return lectEndTime;
        }

        public void setLectEndTime(String lectEndTime) {
            this.lectEndTime = lectEndTime;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getRmName() {
            return rmName;
        }

        public void setRmName(String rmName) {
            this.rmName = rmName;
        }

        public String getSubShortName() {
            return subShortName;
        }

        public void setSubShortName(String subShortName) {
            this.subShortName = subShortName;
        }

        public String getTtPracTheStatus() {
            return ttPracTheStatus;
        }

        public void setTtPracTheStatus(String ttPracTheStatus) {
            this.ttPracTheStatus = ttPracTheStatus;
        }

        public List<LabArray> getLabArray() {
            return labArray;
        }

        public void setLabArray(List<LabArray> labArray) {
            this.labArray = labArray;
        }
    }

    public class LabArray implements Serializable {
        @SerializedName("lect_name")
        @Expose
        private String lectName;
        @SerializedName("lect_st_time")
        @Expose
        private String lectStTime;
        @SerializedName("lect_end_time")
        @Expose
        private String lectEndTime;
        @SerializedName("sm_name")
        @Expose
        private String smName;
        @SerializedName("dvm_name")
        @Expose
        private String dvmName;
        @SerializedName("emp_name")
        @Expose
        private String empName;
        @SerializedName("rm_name")
        @Expose
        private String rmName;
        @SerializedName("sub_short_name")
        @Expose
        private String subShortName;
        @SerializedName("tt_prac_the_status")
        @Expose
        private String ttPracTheStatus;

        public String getLectName() {
            return lectName;
        }

        public void setLectName(String lectName) {
            this.lectName = lectName;
        }

        public String getLectStTime() {
            return lectStTime;
        }

        public void setLectStTime(String lectStTime) {
            this.lectStTime = lectStTime;
        }

        public String getLectEndTime() {
            return lectEndTime;
        }

        public void setLectEndTime(String lectEndTime) {
            this.lectEndTime = lectEndTime;
        }

        public String getSmName() {
            return smName;
        }

        public void setSmName(String smName) {
            this.smName = smName;
        }

        public String getDvmName() {
            return dvmName;
        }

        public void setDvmName(String dvmName) {
            this.dvmName = dvmName;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getRmName() {
            return rmName;
        }

        public void setRmName(String rmName) {
            this.rmName = rmName;
        }

        public String getSubShortName() {
            return subShortName;
        }

        public void setSubShortName(String subShortName) {
            this.subShortName = subShortName;
        }

        public String getTtPracTheStatus() {
            return ttPracTheStatus;
        }

        public void setTtPracTheStatus(String ttPracTheStatus) {
            this.ttPracTheStatus = ttPracTheStatus;
        }

    }
}
