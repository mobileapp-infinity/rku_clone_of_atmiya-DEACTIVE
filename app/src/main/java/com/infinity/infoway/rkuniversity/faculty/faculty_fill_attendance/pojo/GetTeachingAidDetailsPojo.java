package com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTeachingAidDetailsPojo {

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

        @SerializedName("ta_id")
        @Expose
        private Integer taId;
        @SerializedName("ta_name")
        @Expose
        private String taName;

        public Integer getTaId() {
            return taId;
        }

        public void setTaId(Integer taId) {
            this.taId = taId;
        }

        public String getTaName() {
            return taName;
        }

        public void setTaName(String taName) {
            this.taName = taName;
        }

    }

}
