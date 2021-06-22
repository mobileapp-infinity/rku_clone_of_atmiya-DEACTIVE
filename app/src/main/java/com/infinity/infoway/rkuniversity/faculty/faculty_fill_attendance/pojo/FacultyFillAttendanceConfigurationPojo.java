package com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacultyFillAttendanceConfigurationPojo {

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

        @SerializedName("ac_id")
        @Expose
        private Integer acId;
        @SerializedName("ac_attendance_method")
        @Expose
        private Integer acAttendanceMethod;

        public Integer getAcId() {
            return acId;
        }

        public void setAcId(Integer acId) {
            this.acId = acId;
        }

        public Integer getAcAttendanceMethod() {
            return acAttendanceMethod;
        }

        public void setAcAttendanceMethod(Integer acAttendanceMethod) {
            this.acAttendanceMethod = acAttendanceMethod;
        }

    }

}
