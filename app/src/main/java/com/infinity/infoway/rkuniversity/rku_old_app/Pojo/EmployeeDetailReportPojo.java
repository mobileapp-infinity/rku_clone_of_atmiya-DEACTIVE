package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class EmployeeDetailReportPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Employee_details : Non-Teaching
         * brm_code : ACH
         * count : 4
         */
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private String Employee_details;
        private String brm_code;
        private int count;

        public String getEmployee_details() {
            return Employee_details;
        }

        public void setEmployee_details(String Employee_details) {
            this.Employee_details = Employee_details;
        }

        public String getBrm_code() {
            return brm_code;
        }

        public void setBrm_code(String brm_code) {
            this.brm_code = brm_code;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
