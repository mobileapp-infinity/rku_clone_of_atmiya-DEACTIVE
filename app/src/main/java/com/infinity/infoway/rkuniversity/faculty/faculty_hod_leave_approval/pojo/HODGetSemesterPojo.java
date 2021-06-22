package com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HODGetSemesterPojo {

    @SerializedName("sm_id")
    @Expose
    private Integer smId;
    @SerializedName("sm_name")
    @Expose
    private String smName;

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

}
