package com.infinity.infoway.rkuniversity.faculty.faculty_attendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacultyAttendancePojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("Contact_ID")
    @Expose
    private Integer contactID;
    @SerializedName("Att_date")
    @Expose
    private String attDate;
    @SerializedName("Day")
    @Expose
    private String day;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Total_Minute")
    @Expose
    private Integer totalMinute;
    @SerializedName("Total_hour")
    @Expose
    private String totalHour;
    @SerializedName("Late_By")
    @Expose
    private String lateBy;
    @SerializedName("Early_By")
    @Expose
    private String earlyBy;
    @SerializedName("Late_By_Hour")
    @Expose
    private String lateByHour;
    @SerializedName("Early_By_Hour")
    @Expose
    private String earlyByHour;
    @SerializedName("Extra_Hours")
    @Expose
    private String extraHours;
    @SerializedName("Remark")
    @Expose
    private String remark;
    @SerializedName("Attendance_Shift_Name")
    @Expose
    private String attendanceShiftName;
    @SerializedName("Inout_Array")
    @Expose
    private List<InoutArray> inoutArray = null;

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalMinute() {
        return totalMinute;
    }

    public void setTotalMinute(Integer totalMinute) {
        this.totalMinute = totalMinute;
    }

    public String getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(String totalHour) {
        this.totalHour = totalHour;
    }

    public String getLateBy() {
        return lateBy;
    }

    public void setLateBy(String lateBy) {
        this.lateBy = lateBy;
    }

    public String getEarlyBy() {
        return earlyBy;
    }

    public void setEarlyBy(String earlyBy) {
        this.earlyBy = earlyBy;
    }

    public String getLateByHour() {
        return lateByHour;
    }

    public void setLateByHour(String lateByHour) {
        this.lateByHour = lateByHour;
    }

    public String getEarlyByHour() {
        return earlyByHour;
    }

    public void setEarlyByHour(String earlyByHour) {
        this.earlyByHour = earlyByHour;
    }

    public String getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(String extraHours) {
        this.extraHours = extraHours;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttendanceShiftName() {
        return attendanceShiftName;
    }

    public void setAttendanceShiftName(String attendanceShiftName) {
        this.attendanceShiftName = attendanceShiftName;
    }

    public List<InoutArray> getInoutArray() {
        return inoutArray;
    }

    public void setInoutArray(List<InoutArray> inoutArray) {
        this.inoutArray = inoutArray;
    }

    public class InoutArray {

        @SerializedName("InTime")
        @Expose
        private String inTime;
        @SerializedName("OutTime")
        @Expose
        private String outTime;

        public String getInTime() {
            return inTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

    }


}
