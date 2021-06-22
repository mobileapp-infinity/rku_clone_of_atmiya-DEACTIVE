package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class FDPSaveResponsePojo {


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
         * msg1 : 664
         */

        private String msg;
        private String msg1;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg1() {
            return msg1;
        }

        public void setMsg1(String msg1) {
            this.msg1 = msg1;
        }
    }
}
