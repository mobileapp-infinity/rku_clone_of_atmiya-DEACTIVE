package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetDepartmentPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 90
         * dep_name : Computer Application
         */

        private int id;
        private String dep_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDep_name() {
            return dep_name;
        }

        public void setDep_name(String dep_name) {
            this.dep_name = dep_name;
        }
    }
}
