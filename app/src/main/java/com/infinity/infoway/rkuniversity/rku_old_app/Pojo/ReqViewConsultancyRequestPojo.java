package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ReqViewConsultancyRequestPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 5
         * comp_id : 1
         * status : 1
         * create_by : 714
         * create_ip : 117.240.19.146
         * create_dnt : 2019-11-18T14:30:53.397
         * modify_by : 713
         * modify_ip : 27.61.137.125
         * modify_dnt : 2019-11-18T16:39:21.013
         * con_year_id : 2
         * con_project_name : Pharmacological Study
         * con_contect_details : Kiran Gohil, Research Scholar, M. Pharm Sem 4, BK Modi Govt. Pharmacy college, Rajkot
         * con_revenue_gerented : 2200.0
         * con_status : Completed
         * year_name : 2018-19
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
        private int con_year_id;
        private String con_project_name;
        private String con_contect_details;
        private double con_revenue_gerented;
        private String con_status;
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

        public int getCon_year_id() {
            return con_year_id;
        }

        public void setCon_year_id(int con_year_id) {
            this.con_year_id = con_year_id;
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

        public double getCon_revenue_gerented() {
            return con_revenue_gerented;
        }

        public void setCon_revenue_gerented(double con_revenue_gerented) {
            this.con_revenue_gerented = con_revenue_gerented;
        }

        public String getCon_status() {
            return con_status;
        }

        public void setCon_status(String con_status) {
            this.con_status = con_status;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }
    }
}
