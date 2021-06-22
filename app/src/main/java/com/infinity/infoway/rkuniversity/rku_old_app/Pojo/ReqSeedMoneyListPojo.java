package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ReqSeedMoneyListPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * SrNo : 1
         * id : 1
         * employee_name : 734 - TEST RASHMIKANT
         * status : Pending
         * approve_status : 1
         * Academic_Year : 2017-18
         * amount_of_seed_money : 100.0
         * Duration : 10
         * purpose : test
         * approve_reject_by_user : null
         * approve_rejct_date : null
         * created_by : rashmikantm
         * modify_by : null
         * create_dnt : 2020-03-18T20:31:37.947
         */

        private int SrNo;
        private int id;
        private String employee_name;
        private String status;
        private int approve_status;
        private String Academic_Year;
        private double amount_of_seed_money;
        private String Duration;
        private String purpose;
        private Object approve_reject_by_user;
        private Object approve_rejct_date;
        private String created_by;
        private Object modify_by;
        private String create_dnt;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmployee_name() {
            return employee_name;
        }

        public void setEmployee_name(String employee_name) {
            this.employee_name = employee_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getAcademic_Year() {
            return Academic_Year;
        }

        public void setAcademic_Year(String Academic_Year) {
            this.Academic_Year = Academic_Year;
        }

        public double getAmount_of_seed_money() {
            return amount_of_seed_money;
        }

        public void setAmount_of_seed_money(double amount_of_seed_money) {
            this.amount_of_seed_money = amount_of_seed_money;
        }

        public String getDuration() {
            return Duration;
        }

        public void setDuration(String Duration) {
            this.Duration = Duration;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public Object getApprove_reject_by_user() {
            return approve_reject_by_user;
        }

        public void setApprove_reject_by_user(Object approve_reject_by_user) {
            this.approve_reject_by_user = approve_reject_by_user;
        }

        public Object getApprove_rejct_date() {
            return approve_rejct_date;
        }

        public void setApprove_rejct_date(Object approve_rejct_date) {
            this.approve_rejct_date = approve_rejct_date;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public Object getModify_by() {
            return modify_by;
        }

        public void setModify_by(Object modify_by) {
            this.modify_by = modify_by;
        }

        public String getCreate_dnt() {
            return create_dnt;
        }

        public void setCreate_dnt(String create_dnt) {
            this.create_dnt = create_dnt;
        }
    }
}
