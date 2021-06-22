package com.infinity.infoway.rkuniversity.faculty.faculty_news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FacultyNewsPojo implements Serializable {

    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("group_news_detail")
    @Expose
    private List<GroupNewsDetail> groupNewsDetail = null;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<GroupNewsDetail> getGroupNewsDetail() {
        return groupNewsDetail;
    }

    public void setGroupNewsDetail(List<GroupNewsDetail> groupNewsDetail) {
        this.groupNewsDetail = groupNewsDetail;
    }

    public class GroupNewsDetail implements Serializable {

        @SerializedName("cn_id")
        @Expose
        private Integer cnId;
        @SerializedName("cn_subject")
        @Expose
        private String cnSubject;
        @SerializedName("cn_content")
        @Expose
        private String cnContent;
        @SerializedName("cn_date")
        @Expose
        private String cnDate;
        @SerializedName("ph_1")
        @Expose
        private String ph1;
        @SerializedName("ph_2")
        @Expose
        private String ph2;

        public String getCn_file() {
            return cn_file;
        }

        public void setCn_file(String cn_file) {
            this.cn_file = cn_file;
        }

        @SerializedName("cn_file")
        @Expose
        private String cn_file;

        public Integer getCnId() {
            return cnId;
        }

        public void setCnId(Integer cnId) {
            this.cnId = cnId;
        }

        public String getCnSubject() {
            return cnSubject;
        }

        public void setCnSubject(String cnSubject) {
            this.cnSubject = cnSubject;
        }

        public String getCnContent() {
            return cnContent;
        }

        public void setCnContent(String cnContent) {
            this.cnContent = cnContent;
        }

        public String getCnDate() {
            return cnDate;
        }

        public void setCnDate(String cnDate) {
            this.cnDate = cnDate;
        }

        public String getPh1() {
            return ph1;
        }

        public void setPh1(String ph1) {
            this.ph1 = ph1;
        }

        public String getPh2() {
            return ph2;
        }

        public void setPh2(String ph2) {
            this.ph2 = ph2;
        }

    }

}
