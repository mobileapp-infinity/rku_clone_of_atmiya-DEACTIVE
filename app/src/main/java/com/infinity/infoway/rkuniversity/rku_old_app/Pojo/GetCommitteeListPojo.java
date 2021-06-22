package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetCommitteeListPojo {


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
         * con_emp_name : 46 - SHEETAL PANDYA
         * id : 26
         * status : 1
         * create_date : 2020-03-05T16:00:46.233
         * modify_date : null
         * committee_name : Women cell / ICC
         * rolls_responsibility_coordinatior : will update later
         * main_coordinatior : 46 - SHEETAL PANDYA
         * rolls_ressponsibility_member : will update later
         * ebd_name : University Level
         */

        private int SrNo;
        private String con_emp_name;
        private int id;
        private int status;
        private String create_date;
        private Object modify_date;
        private String committee_name;
        private String rolls_responsibility_coordinatior;
        private String main_coordinatior;
        private String rolls_ressponsibility_member;
        private String ebd_name;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public String getCon_emp_name() {
            return con_emp_name;
        }

        public void setCon_emp_name(String con_emp_name) {
            this.con_emp_name = con_emp_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public Object getModify_date() {
            return modify_date;
        }

        public void setModify_date(Object modify_date) {
            this.modify_date = modify_date;
        }

        public String getCommittee_name() {
            return committee_name;
        }

        public void setCommittee_name(String committee_name) {
            this.committee_name = committee_name;
        }

        public String getRolls_responsibility_coordinatior() {
            return rolls_responsibility_coordinatior;
        }

        public void setRolls_responsibility_coordinatior(String rolls_responsibility_coordinatior) {
            this.rolls_responsibility_coordinatior = rolls_responsibility_coordinatior;
        }

        public String getMain_coordinatior() {
            return main_coordinatior;
        }

        public void setMain_coordinatior(String main_coordinatior) {
            this.main_coordinatior = main_coordinatior;
        }

        public String getRolls_ressponsibility_member() {
            return rolls_ressponsibility_member;
        }

        public void setRolls_ressponsibility_member(String rolls_ressponsibility_member) {
            this.rolls_ressponsibility_member = rolls_ressponsibility_member;
        }

        public String getEbd_name() {
            return ebd_name;
        }

        public void setEbd_name(String ebd_name) {
            this.ebd_name = ebd_name;
        }
    }
}
