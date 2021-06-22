package com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTeachingMethodPojo {

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

        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        @SerializedName("tm_id")
        @Expose
        private Integer tmId;
        @SerializedName("tm_short_name")
        @Expose
        private String tmShortName;
        @SerializedName("method_name")
        @Expose
        private String methodName;

        public Integer getTmId() {
            return tmId;
        }

        public void setTmId(Integer tmId) {
            this.tmId = tmId;
        }

        public String getTmShortName() {
            return tmShortName;
        }

        public void setTmShortName(String tmShortName) {
            this.tmShortName = tmShortName;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

    }

}
