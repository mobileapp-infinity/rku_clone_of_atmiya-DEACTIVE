package com.infinity.infoway.rkuniversity.student.e_learning.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckIsELearningManagementGroupIsCompulsoryOrNot {


    @SerializedName("grp_type")
    @Expose
    private Integer grpType;

    public Integer getGrpType() {
        return grpType;
    }

    public void setGrpType(Integer grpType) {
        this.grpType = grpType;
    }
}
