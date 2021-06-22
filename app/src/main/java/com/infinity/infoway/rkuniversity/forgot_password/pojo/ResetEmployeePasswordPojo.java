package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResetEmployeePasswordPojo {

    private List<ResetEmployeePasswordPojo.TableBean> Table;

    public List<ResetEmployeePasswordPojo.TableBean> getTable() {
        return Table;
    }

    public void setTable(List<ResetEmployeePasswordPojo.TableBean> Table) {
        this.Table = Table;
    }

    public class TableBean {
        @SerializedName("Error_code")
        @Expose
        private Integer errorCode;
        @SerializedName("Error_msg")
        @Expose
        private String errorMsg;

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
