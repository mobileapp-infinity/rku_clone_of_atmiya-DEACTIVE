package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ReqViewseedMoneyPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * comp_id : 1
         * status : 1
         * create_by : 1044
         * create_ip : 0
         * create_dnt : 2020-03-18T20:31:37.947
         * modify_by : null
         * modify_ip : null
         * modify_dnt : null
         * sm_teacher_name : null
         * sm_amount : 100.0
         * sm_year : null
         * sm_grant_duration : 10
         * sm_year_id : 1
         * sm_purpose : test
         * year_name : 2017-18
         */

        private int id;
        private int comp_id;
        private int status;
        private int create_by;
        private String create_ip;
        private String create_dnt;
        private Object modify_by;
        private Object modify_ip;
        private Object modify_dnt;
        private Object sm_teacher_name;
        private double sm_amount;
        private Object sm_year;
        private String sm_grant_duration;
        private int sm_year_id;
        private String sm_purpose;
        private String year_name;

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

        public Object getModify_dnt() {
            return modify_dnt;
        }

        public void setModify_dnt(Object modify_dnt) {
            this.modify_dnt = modify_dnt;
        }

        public Object getSm_teacher_name() {
            return sm_teacher_name;
        }

        public void setSm_teacher_name(Object sm_teacher_name) {
            this.sm_teacher_name = sm_teacher_name;
        }

        public double getSm_amount() {
            return sm_amount;
        }

        public void setSm_amount(double sm_amount) {
            this.sm_amount = sm_amount;
        }

        public Object getSm_year() {
            return sm_year;
        }

        public void setSm_year(Object sm_year) {
            this.sm_year = sm_year;
        }

        public String getSm_grant_duration() {
            return sm_grant_duration;
        }

        public void setSm_grant_duration(String sm_grant_duration) {
            this.sm_grant_duration = sm_grant_duration;
        }

        public int getSm_year_id() {
            return sm_year_id;
        }

        public void setSm_year_id(int sm_year_id) {
            this.sm_year_id = sm_year_id;
        }

        public String getSm_purpose() {
            return sm_purpose;
        }

        public void setSm_purpose(String sm_purpose) {
            this.sm_purpose = sm_purpose;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }
    }
}
