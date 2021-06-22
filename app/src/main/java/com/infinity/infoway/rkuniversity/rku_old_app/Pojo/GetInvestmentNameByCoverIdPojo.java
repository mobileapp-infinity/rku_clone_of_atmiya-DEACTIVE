package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetInvestmentNameByCoverIdPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 10
         * in_cover_in : 1
         * in_name : Other Investment/payment eligiable for 80C Pls.specify in Remarks
         */

        private int id;
        private int in_cover_in;
        private String in_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIn_cover_in() {
            return in_cover_in;
        }

        public void setIn_cover_in(int in_cover_in) {
            this.in_cover_in = in_cover_in;
        }

        public String getIn_name() {
            return in_name;
        }

        public void setIn_name(String in_name) {
            this.in_name = in_name;
        }
    }
}
