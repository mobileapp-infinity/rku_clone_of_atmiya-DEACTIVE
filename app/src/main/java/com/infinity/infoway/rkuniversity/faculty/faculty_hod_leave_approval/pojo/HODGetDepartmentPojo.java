package com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HODGetDepartmentPojo {

    @SerializedName("dm_id")
    @Expose
    private Integer dmId;
    @SerializedName("dm_full_name")
    @Expose
    private String dmFullName;

    public Integer getDmId() {
        return dmId;
    }

    public void setDmId(Integer dmId) {
        this.dmId = dmId;
    }

    public String getDmFullName() {
        return dmFullName;
    }

    public void setDmFullName(String dmFullName) {
        this.dmFullName = dmFullName;
    }

}
