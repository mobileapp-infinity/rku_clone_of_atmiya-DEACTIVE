package com.infinity.infoway.rkuniversity.student.leave_application.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KindOfLeaveListPojo {

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

        @SerializedName("lt_id")
        @Expose
        private Integer ltId;
        @SerializedName("lt_name")
        @Expose
        private String ltName;

        public Integer getLtId() {
            return ltId;
        }

        public void setLtId(Integer ltId) {
            this.ltId = ltId;
        }

        public String getLtName() {
            return ltName;
        }

        public void setLtName(String ltName) {
            this.ltName = ltName;
        }

    }

}
