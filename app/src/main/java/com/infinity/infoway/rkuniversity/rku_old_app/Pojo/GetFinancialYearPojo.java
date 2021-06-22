package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetFinancialYearPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 1
         * fym_name : FY2019-20
         * fym_is_current : 1
         */

        private int id;
        private String fym_name;
        private int fym_is_current;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFym_name() {
            return fym_name;
        }

        public void setFym_name(String fym_name) {
            this.fym_name = fym_name;
        }

        public int getFym_is_current() {
            return fym_is_current;
        }

        public void setFym_is_current(int fym_is_current) {
            this.fym_is_current = fym_is_current;
        }
    }
}
