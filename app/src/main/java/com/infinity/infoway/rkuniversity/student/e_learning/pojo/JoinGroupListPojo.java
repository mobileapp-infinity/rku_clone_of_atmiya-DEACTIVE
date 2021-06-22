package com.infinity.infoway.rkuniversity.student.e_learning.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JoinGroupListPojo {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("group_date")
    @Expose
    private String groupDate;
    @SerializedName("stud_id")
    @Expose
    private String studId;
    @SerializedName("details_array")
    @Expose
    private List<DetailsArray> detailsArray = null;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public List<DetailsArray> getDetailsArray() {
        return detailsArray;
    }

    public void setDetailsArray(List<DetailsArray> detailsArray) {
        this.detailsArray = detailsArray;
    }

    public class DetailsArray {

        @SerializedName("grp_desc")
        @Expose
        private String grpDesc;
        @SerializedName("grp_type")
        @Expose
        private String grpType;
        @SerializedName("no_of_posts")
        @Expose
        private String noOfPosts;
        @SerializedName("dept_name")
        @Expose
        private String deptName;
        @SerializedName("grp_created_by")
        @Expose
        private String grpCreatedBy;

        public String getGrpDesc() {
            return grpDesc;
        }

        public void setGrpDesc(String grpDesc) {
            this.grpDesc = grpDesc;
        }

        public String getGrpType() {
            return grpType;
        }

        public void setGrpType(String grpType) {
            this.grpType = grpType;
        }

        public String getNoOfPosts() {
            return noOfPosts;
        }

        public void setNoOfPosts(String noOfPosts) {
            this.noOfPosts = noOfPosts;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getGrpCreatedBy() {
            return grpCreatedBy;
        }

        public void setGrpCreatedBy(String grpCreatedBy) {
            this.grpCreatedBy = grpCreatedBy;
        }

    }


}
