package com.infinity.infoway.rkuniversity.student.exam.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CIAExamSemesterPojo {

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

}
