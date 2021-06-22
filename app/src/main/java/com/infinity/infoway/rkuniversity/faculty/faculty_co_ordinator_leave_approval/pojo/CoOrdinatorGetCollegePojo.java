package com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoOrdinatorGetCollegePojo {
    @SerializedName("ac_id")
    @Expose
    private Integer acId;
    @SerializedName("college_name")
    @Expose
    private String collegeName;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

}
