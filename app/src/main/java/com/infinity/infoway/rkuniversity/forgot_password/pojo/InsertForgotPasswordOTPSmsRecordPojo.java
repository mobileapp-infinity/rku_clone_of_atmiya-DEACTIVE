package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertForgotPasswordOTPSmsRecordPojo {

    private List<InsertForgotPasswordOTPSmsRecordPojo.TableBean> Table;

    public List<InsertForgotPasswordOTPSmsRecordPojo.TableBean> getTable() {
        return Table;
    }

    public void setTable(List<InsertForgotPasswordOTPSmsRecordPojo.TableBean> Table) {
        this.Table = Table;
    }

    public class TableBean {
        @SerializedName("insert_id")
        @Expose
        private Integer insertId;
        @SerializedName("Error_code")
        @Expose
        private Integer errorCode;
        @SerializedName("Error_msg")
        @Expose
        private String errorMsg;

        public Integer getInsertId() {
            return insertId;
        }

        public void setInsertId(Integer insertId) {
            this.insertId = insertId;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
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
