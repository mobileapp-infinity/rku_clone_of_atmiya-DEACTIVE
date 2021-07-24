package com.infinity.infoway.rkuniversity.student.distribution_details.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDistributionDetailListPojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("StudId")
    @Expose
    private Integer studId;
    @SerializedName("StudCode")
    @Expose
    private String studCode;
    @SerializedName("StudName")
    @Expose
    private String studName;
    @SerializedName("CourseName")
    @Expose
    private String courseName;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("ItemStatus")
    @Expose
    private String itemStatus;

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getStudCode() {
        return studCode;
    }

    public void setStudCode(String studCode) {
        this.studCode = studCode;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

}
