package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetDesignationPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 115
         * comp_id : 1
         * status : 1
         * create_by : 1
         * create_ip : 103.36.122.212
         * create_dnt : 2019-03-06T17:30:00
         * modify_by : 1
         * modify_ip : 117.240.19.146
         * modify_dnt : 2020-04-14T09:58:50.023
         * des_name : Human Resource
         * des_parent_id : 114
         * des_brm_id : 16,29
         * des_type : 2
         * des_key : human_resource
         * reporting_to : Vice President
         */

        private int id;
        private int comp_id;
        private int status;
        private int create_by;
        private String create_ip;
        private String create_dnt;
        private int modify_by;
        private String modify_ip;
        private String modify_dnt;
        private String des_name;
        private int des_parent_id;
        private String des_brm_id;
        private int des_type;
        private String des_key;
        private String reporting_to;

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

        public String getCreate_dnt() {
            return create_dnt;
        }

        public void setCreate_dnt(String create_dnt) {
            this.create_dnt = create_dnt;
        }

        public int getModify_by() {
            return modify_by;
        }

        public void setModify_by(int modify_by) {
            this.modify_by = modify_by;
        }

        public String getModify_ip() {
            return modify_ip;
        }

        public void setModify_ip(String modify_ip) {
            this.modify_ip = modify_ip;
        }

        public String getModify_dnt() {
            return modify_dnt;
        }

        public void setModify_dnt(String modify_dnt) {
            this.modify_dnt = modify_dnt;
        }

        public String getDes_name() {
            return des_name;
        }

        public void setDes_name(String des_name) {
            this.des_name = des_name;
        }

        public int getDes_parent_id() {
            return des_parent_id;
        }

        public void setDes_parent_id(int des_parent_id) {
            this.des_parent_id = des_parent_id;
        }

        public String getDes_brm_id() {
            return des_brm_id;
        }

        public void setDes_brm_id(String des_brm_id) {
            this.des_brm_id = des_brm_id;
        }

        public int getDes_type() {
            return des_type;
        }

        public void setDes_type(int des_type) {
            this.des_type = des_type;
        }

        public String getDes_key() {
            return des_key;
        }

        public void setDes_key(String des_key) {
            this.des_key = des_key;
        }

        public String getReporting_to() {
            return reporting_to;
        }

        public void setReporting_to(String reporting_to) {
            this.reporting_to = reporting_to;
        }
    }
}
