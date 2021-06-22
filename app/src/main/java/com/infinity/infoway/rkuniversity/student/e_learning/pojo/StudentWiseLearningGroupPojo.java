package com.infinity.infoway.rkuniversity.student.e_learning.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentWiseLearningGroupPojo {

    @SerializedName("Table")
    @Expose
    private List<Table> table = null;

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public class Table {

        @SerializedName("grp_id")
        @Expose
        private Integer grpId;
        @SerializedName("grp_name")
        @Expose
        private String grpName;
        @SerializedName("group_created_date")
        @Expose
        private String groupCreatedDate;
        @SerializedName("grp_desc")
        @Expose
        private String grpDesc;

        public Integer getGrpId() {
            return grpId;
        }

        public void setGrpId(Integer grpId) {
            this.grpId = grpId;
        }

        public String getGrpName() {
            return grpName;
        }

        public void setGrpName(String grpName) {
            this.grpName = grpName;
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

    }

}
