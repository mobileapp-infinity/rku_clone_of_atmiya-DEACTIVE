package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetCommitteeEmployeeNamePojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 72
         * comp_id : 1
         * status : 1
         * create_by : 1044
         * create_ip : 1
         * create_date : 2020-03-20T18:35:19
         * modify_by : null
         * modify_ip : null
         * modify_date : null
         * main_coordinatior : 734 - TEST RASHMIKANT
         * members : 658 - AKASH THUMAR
         * committee_name : null
         */

        private int id;
        private int comp_id;
        private int status;
        private int create_by;
        private String create_ip;
        private String create_date;
        private Object modify_by;
        private Object modify_ip;
        private Object modify_date;
        private String main_coordinatior;
        private String members;
        private Object committee_name;

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

        public int getCreate_by() {
            return create_by;
        }

        public void setCreate_by(int create_by) {
            this.create_by = create_by;
        }

        public String getCreate_ip() {
            return create_ip;
        }

        public void setCreate_ip(String create_ip) {
            this.create_ip = create_ip;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public Object getModify_by() {
            return modify_by;
        }

        public void setModify_by(Object modify_by) {
            this.modify_by = modify_by;
        }

        public Object getModify_ip() {
            return modify_ip;
        }

        public void setModify_ip(Object modify_ip) {
            this.modify_ip = modify_ip;
        }

        public Object getModify_date() {
            return modify_date;
        }

        public void setModify_date(Object modify_date) {
            this.modify_date = modify_date;
        }

        public String getMain_coordinatior() {
            return main_coordinatior;
        }

        public void setMain_coordinatior(String main_coordinatior) {
            this.main_coordinatior = main_coordinatior;
        }

        public String getMembers() {
            return members;
        }

        public void setMembers(String members) {
            this.members = members;
        }

        public Object getCommittee_name() {
            return committee_name;
        }

        public void setCommittee_name(Object committee_name) {
            this.committee_name = committee_name;
        }
    }
}
