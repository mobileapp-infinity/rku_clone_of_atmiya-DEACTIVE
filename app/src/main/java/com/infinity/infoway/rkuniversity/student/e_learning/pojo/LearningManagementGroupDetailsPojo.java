package com.infinity.infoway.rkuniversity.student.e_learning.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LearningManagementGroupDetailsPojo {

    boolean isExpanded = false;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("group_created_date")
    @Expose
    private String groupCreatedDate;
    @SerializedName("grp_desc")
    @Expose
    private String grpDesc;
    @SerializedName("group_detail_array")
    @Expose
    private List<GroupDetailArray> groupDetailArray = null;

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

    public String getGroupCreatedDate() {
        return groupCreatedDate;
    }

    public void setGroupCreatedDate(String groupCreatedDate) {
        this.groupCreatedDate = groupCreatedDate;
    }

    public String getGrpDesc() {
        return grpDesc;
    }

    public void setGrpDesc(String grpDesc) {
        this.grpDesc = grpDesc;
    }

    public List<GroupDetailArray> getGroupDetailArray() {
        return groupDetailArray;
    }

    public void setGroupDetailArray(List<GroupDetailArray> groupDetailArray) {
        this.groupDetailArray = groupDetailArray;
    }

    public class GroupDetailArray {

        @SerializedName("file_sub_name")
        @Expose
        private String fileSubName;
        @SerializedName("file_desc")
        @Expose
        private String fileDesc;
        @SerializedName("file_upload_date")
        @Expose
        private String fileUploadDate;
        @SerializedName("file_url")
        @Expose
        private String fileUrl;

        public String getFileSubName() {
            return fileSubName;
        }

        public void setFileSubName(String fileSubName) {
            this.fileSubName = fileSubName;
        }

        public String getFileDesc() {
            return fileDesc;
        }

        public void setFileDesc(String fileDesc) {
            this.fileDesc = fileDesc;
        }

        public String getFileUploadDate() {
            return fileUploadDate;
        }

        public void setFileUploadDate(String fileUploadDate) {
            this.fileUploadDate = fileUploadDate;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

    }

}




