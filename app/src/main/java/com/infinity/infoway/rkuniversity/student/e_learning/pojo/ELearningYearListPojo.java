package com.infinity.infoway.rkuniversity.student.e_learning.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ELearningYearListPojo {

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

        @SerializedName("year_name")
        @Expose
        private String yearName;
        @SerializedName("year_id")
        @Expose
        private Integer yearId;
        @SerializedName("year_is_current")
        @Expose
        private Integer yearIsCurrent;

        public String getYearName() {
            return yearName;
        }

        public void setYearName(String yearName) {
            this.yearName = yearName;
        }

        public Integer getYearId() {
            return yearId;
        }

        public void setYearId(Integer yearId) {
            this.yearId = yearId;
        }

        public Integer getYearIsCurrent() {
            return yearIsCurrent;
        }

        public void setYearIsCurrent(Integer yearIsCurrent) {
            this.yearIsCurrent = yearIsCurrent;
        }

    }

}
