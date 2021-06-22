package com.infinity.infoway.rkuniversity.student.holiday;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HolidayListPojo {

    @SerializedName("srno")
    @Expose
    private Integer srno;
    @SerializedName("HolidayName")
    @Expose
    private String holidayName;
    @SerializedName("ac_full_name")
    @Expose
    private String acFullName;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("Description")
    @Expose
    private String description;

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getAcFullName() {
        return acFullName;
    }

    public void setAcFullName(String acFullName) {
        this.acFullName = acFullName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
