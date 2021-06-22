package com.infinity.infoway.rkuniversity.faculty.faculty_leave;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacultyLeavePojo {


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
    @SerializedName("Leave_Name")
    @Expose
    private String leaveName;
    @SerializedName("Leave_Day")
    @Expose
    private Double leaveDay;
    @SerializedName("Leave_Balance")
    @Expose
    private Double leaveBalance;
    @SerializedName("Leave_Taken")
    @Expose
    private Double leaveTaken;

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public Double getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Double leaveDay) {
        this.leaveDay = leaveDay;
    }

    public Double getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(Double leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public Double getLeaveTaken() {
        return leaveTaken;
    }

    public void setLeaveTaken(Double leaveTaken) {
        this.leaveTaken = leaveTaken;
    }
}
