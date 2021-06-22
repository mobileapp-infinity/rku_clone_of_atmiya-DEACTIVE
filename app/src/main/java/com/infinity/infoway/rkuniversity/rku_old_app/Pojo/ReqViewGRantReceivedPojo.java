package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ReqViewGRantReceivedPojo implements Serializable{


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 21
         * comp_id : 1
         * status : 1
         * create_by : 1044
         * create_ip : 0
         * create_dnt : 2020-03-18T23:51:27.147
         * modify_by : null
         * modify_ip : null
         * modify_dnt : null
         * gr_project_name : itdyf
         * gr_principal_investigator : utsts
         * gr_principal_investigator_department : 7dd
         * gr_award_year : 2018-19
         * gr_funds_provided : ursts
         * gr_project_duration : 90
         * gr_year_id : 2
         * gr_status : Ongoing
         * gr_agency_name : Non-Govt
         * year_name : 2018-19
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
        private String gr_project_name;
        private String gr_principal_investigator;
        private String gr_principal_investigator_department;
        private String gr_award_year;
        private String gr_funds_provided;
        private String gr_project_duration;
        private int gr_year_id;
        private String gr_status;
        private String gr_agency_name;
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

        public String getGr_project_name() {
            return gr_project_name;
        }

        public void setGr_project_name(String gr_project_name) {
            this.gr_project_name = gr_project_name;
        }

        public String getGr_principal_investigator() {
            return gr_principal_investigator;
        }

        public void setGr_principal_investigator(String gr_principal_investigator) {
            this.gr_principal_investigator = gr_principal_investigator;
        }

        public String getGr_principal_investigator_department() {
            return gr_principal_investigator_department;
        }

        public void setGr_principal_investigator_department(String gr_principal_investigator_department) {
            this.gr_principal_investigator_department = gr_principal_investigator_department;
        }

        public String getGr_award_year() {
            return gr_award_year;
        }

        public void setGr_award_year(String gr_award_year) {
            this.gr_award_year = gr_award_year;
        }

        public String getGr_funds_provided() {
            return gr_funds_provided;
        }

        public void setGr_funds_provided(String gr_funds_provided) {
            this.gr_funds_provided = gr_funds_provided;
        }

        public String getGr_project_duration() {
            return gr_project_duration;
        }

        public void setGr_project_duration(String gr_project_duration) {
            this.gr_project_duration = gr_project_duration;
        }

        public int getGr_year_id() {
            return gr_year_id;
        }

        public void setGr_year_id(int gr_year_id) {
            this.gr_year_id = gr_year_id;
        }

        public String getGr_status() {
            return gr_status;
        }

        public void setGr_status(String gr_status) {
            this.gr_status = gr_status;
        }

        public String getGr_agency_name() {
            return gr_agency_name;
        }

        public void setGr_agency_name(String gr_agency_name) {
            this.gr_agency_name = gr_agency_name;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }
    }
}
