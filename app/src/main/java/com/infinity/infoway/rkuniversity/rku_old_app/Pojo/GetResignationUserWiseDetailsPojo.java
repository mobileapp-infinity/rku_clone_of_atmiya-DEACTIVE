package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetResignationUserWiseDetailsPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 1120
         * comp_id : 1
         * emp_name : TEST MONPARA RASHMIKANT
         * emp_resi_date : 09/04/2020
         * emp_resi_reason : test test test
         * emp_notice_period : 32
         */

        private int id;
        private int comp_id;
        private String emp_name;
        private String emp_resi_date;
        private String emp_resi_reason;
        private int emp_notice_period;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getComp_id() {
            return comp_id;
        }

        public void setComp_id(int comp_id) {
            this.comp_id = comp_id;
        }

        public String getEmp_name() {
            return emp_name;
        }

        public void setEmp_name(String emp_name) {
            this.emp_name = emp_name;
        }

        public String getEmp_resi_date() {
            return emp_resi_date;
        }

        public void setEmp_resi_date(String emp_resi_date) {
            this.emp_resi_date = emp_resi_date;
        }

        public String getEmp_resi_reason() {
            return emp_resi_reason;
        }

        public void setEmp_resi_reason(String emp_resi_reason) {
            this.emp_resi_reason = emp_resi_reason;
        }

        public int getEmp_notice_period() {
            return emp_notice_period;
        }

        public void setEmp_notice_period(int emp_notice_period) {
            this.emp_notice_period = emp_notice_period;
        }
    }
}
