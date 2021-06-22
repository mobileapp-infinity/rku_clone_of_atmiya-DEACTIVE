package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetEmployeeIdWiseNameCommitteePojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 1008
         * emp_full_name : 638 - (RK UNIVERSITY) HR
         */

        private int id;
        private String emp_full_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmp_full_name() {
            return emp_full_name;
        }

        public void setEmp_full_name(String emp_full_name) {
            this.emp_full_name = emp_full_name;
        }
    }
}
