package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ReqConsultancyRequestListPojo {


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
         * con_is_approve_value : Pending
         * approve_status : 1
         * con_emp_name : 734 - TEST RASHMIKANT
         * year_name : 2017-18
         * id : 15
         * comp_id : 1
         * status : 1
         * con_project_name : test
         * con_contect_details : test
         * con_status : Completed
         * con_revenue_gerented : 100.0
         * con_emp_id : 1120
         * con_approve_reject_reason : null
         * create_by : null
         * create_dnt : 2020-03-21T08:11:28.273
         * approve_reject_dnt : null
         * modify_by : null
         * approve_reject_by_user : null
         */

        private int SrNo;
        private String con_is_approve_value;
        private int approve_status;
        private String con_emp_name;
        private String year_name;
        private int id;
        private int comp_id;
        private int status;
        private String con_project_name;
        private String con_contect_details;
        private String con_status;
        private double con_revenue_gerented;
        private int con_emp_id;
        private Object con_approve_reject_reason;
        private Object create_by;
        private String create_dnt;
        private Object approve_reject_dnt;
        private Object modify_by;
        private Object approve_reject_by_user;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public String getCon_is_approve_value() {
            return con_is_approve_value;
        }

        public void setCon_is_approve_value(String con_is_approve_value) {
            this.con_is_approve_value = con_is_approve_value;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getCon_emp_name() {
            return con_emp_name;
        }

        public void setCon_emp_name(String con_emp_name) {
            this.con_emp_name = con_emp_name;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCon_project_name() {
            return con_project_name;
        }

        public void setCon_project_name(String con_project_name) {
            this.con_project_name = con_project_name;
        }

        public String getCon_contect_details() {
            return con_contect_details;
        }

        public void setCon_contect_details(String con_contect_details) {
            this.con_contect_details = con_contect_details;
        }

        public String getCon_status() {
            return con_status;
        }

        public void setCon_status(String con_status) {
            this.con_status = con_status;
        }

        public double getCon_revenue_gerented() {
            return con_revenue_gerented;
        }

        public void setCon_revenue_gerented(double con_revenue_gerented) {
            this.con_revenue_gerented = con_revenue_gerented;
        }

        public int getCon_emp_id() {
            return con_emp_id;
        }

        public void setCon_emp_id(int con_emp_id) {
            this.con_emp_id = con_emp_id;
        }

        public Object getCon_approve_reject_reason() {
            return con_approve_reject_reason;
        }

        public void setCon_approve_reject_reason(Object con_approve_reject_reason) {
            this.con_approve_reject_reason = con_approve_reject_reason;
        }

        public Object getCreate_by() {
            return create_by;
        }

        public void setCreate_by(Object create_by) {
            this.create_by = create_by;
        }

        public String getCreate_dnt() {
            return create_dnt;
        }

        public void setCreate_dnt(String create_dnt) {
            this.create_dnt = create_dnt;
        }

        public Object getApprove_reject_dnt() {
            return approve_reject_dnt;
        }

        public void setApprove_reject_dnt(Object approve_reject_dnt) {
            this.approve_reject_dnt = approve_reject_dnt;
        }

        public Object getModify_by() {
            return modify_by;
        }

        public void setModify_by(Object modify_by) {
            this.modify_by = modify_by;
        }

        public Object getApprove_reject_by_user() {
            return approve_reject_by_user;
        }

        public void setApprove_reject_by_user(Object approve_reject_by_user) {
            this.approve_reject_by_user = approve_reject_by_user;
        }
    }
}
