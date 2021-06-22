package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class SavePhdScolarGuidedPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * msg : Save Successfully
         * Status : 1
         */

        private String msg;
        private int Status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }
    }
}
