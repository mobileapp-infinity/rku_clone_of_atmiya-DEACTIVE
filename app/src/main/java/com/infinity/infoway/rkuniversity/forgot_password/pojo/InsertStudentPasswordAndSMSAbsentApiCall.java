package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertStudentPasswordAndSMSAbsentApiCall {

    @SerializedName("Table")
    @Expose
    private List<InsertStudentPasswordAndSMSAbsentApiCall.Table> table = null;

    public List<InsertStudentPasswordAndSMSAbsentApiCall.Table> getTable() {
        return table;
    }

    public void setTable(List<InsertStudentPasswordAndSMSAbsentApiCall.Table> table) {
        this.table = table;
    }

    public class Table {

        @SerializedName("insert_id")
        @Expose
        private Integer insertId;
        @SerializedName("Error_code")
        @Expose
        private String errorCode;
        @SerializedName("Error_msg")
        @Expose
        private String errorMsg;

        public Integer getInsertId() {
            return insertId;
        }

        public void setInsertId(Integer insertId) {
            this.insertId = insertId;
        }

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
