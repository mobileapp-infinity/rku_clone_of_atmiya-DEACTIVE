package com.infinity.infoway.rkuniversity.student.e_learning.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupWiseSubjectlistPojo {

    @SerializedName("sub_id")
    @Expose
    private String subId;
    @SerializedName("sub_name")
    @Expose
    private String subName;

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

}
