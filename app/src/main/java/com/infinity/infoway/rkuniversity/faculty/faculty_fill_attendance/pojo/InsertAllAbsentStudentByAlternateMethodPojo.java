package com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertAllAbsentStudentByAlternateMethodPojo {

    @SerializedName("Table")
    @Expose
    private List<InsertAllAbsentStudentByAlternateMethodPojo.Table> table = null;

    public List<InsertAllAbsentStudentByAlternateMethodPojo.Table> getTable() {
        return table;
    }

    public void setTable(List<InsertAllAbsentStudentByAlternateMethodPojo.Table> table) {
        this.table = table;
    }

    public class Table {

        @SerializedName("Error_code")
        @Expose
        private String errorCode;
        @SerializedName("Error_msg")
        @Expose
        private String errorMsg;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

    }
}
