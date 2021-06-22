package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ViewCommitteeByIdPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 10
         * comp_id : 1
         * status : 1
         * create_by : 1151
         * create_ip : 117.240.19.146
         * create_date : 2020-02-22T13:17:34.98
         * modify_by : null
         * modify_ip : null
         * modify_date : null
         * committee_name : SC - ST Cell
         * rolls_responsibility_coordinatior : Collect information regarding course-wise admissions to candidates belonging to the Scheduled Castes and Scheduled Tribes in the Universities.Also collect information of these communities of teaching and non -teaching staffs in different colleges under university.
         To circulate State Govt. and UGC’s decisions about different scholarship programs.
         To aware the SC/ST students regarding various scholarships program of State Govt. and UGC, namely Post-Matric Scholarship and their different scheme. Also take follow up of their scholarship.
         To solve academic as well as administrative problems of SC/ST students and employees of the university. Also Keep record of all complains and its solution in register.
         To collect reports and information of State Govt. and UGC’s orders on various aspects of education, employment of SC/ST Students.
         To communicate with the students and motivate them for better future planning.
         * main_coordinatior : 21 - RADHIKA SENJALIYA
         * rolls_ressponsibility_member : Collect information regarding course-wise admissions to candidates belonging to the Scheduled Castes and Scheduled Tribes in the Universities.Also collect information of these communities of teaching and non -teaching staffs in different colleges under university.
         To circulate State Govt. and UGC’s decisions about different scholarship programs.
         To aware the SC/ST students regarding various scholarships program of State Govt. and UGC, namely Post-Matric Scholarship and their different scheme. Also take follow up of their scholarship.
         To solve academic as well as administrative problems of SC/ST students and employees of the university. Also Keep record of all complains and its solution in register.
         To collect reports and information of State Govt. and UGC’s orders on various aspects of education, employment of SC/ST Students.
         To communicate with the students and motivate them for better future planning.

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
        private String committee_name;
        private String rolls_responsibility_coordinatior;
        private String main_coordinatior;
        private String rolls_ressponsibility_member;

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
    }
}
