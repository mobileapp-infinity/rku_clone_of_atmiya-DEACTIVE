package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ReqViewPgScholarGuidedPojo implements Serializable {


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
         * create_by : 747
         * create_ip : 117.240.19.146
         * create_dnt : 2020-03-07T14:19:01.973
         * modify_by : 699
         * modify_ip : 117.240.19.146
         * modify_dnt : 2020-03-13T08:29:06.363
         * pgs_scholar_name : Rehana Mulani
         * pgs_scholar_guide_name : null
         * pgs_research_title : “Fundamental Analysis on Performance Evaluation of Selected Mutual Fund Schemes”
         * pgs_reg_year : 2018
         * pgs_award_year : 2020
         * pgs_status : Completed
         * pgs_emp_id : 699
         * pgs_prt_emp_id : 651
         * pgs_year_id : 3
         * year_name : 2019-20
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
        private String pgs_scholar_name;
        private Object pgs_scholar_guide_name;
        private String pgs_research_title;
        private int pgs_reg_year;
        private int pgs_award_year;
        private String pgs_status;
        private int pgs_emp_id;
        private int pgs_prt_emp_id;
        private int pgs_year_id;
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

        public String getPgs_scholar_name() {
            return pgs_scholar_name;
        }

        public void setPgs_scholar_name(String pgs_scholar_name) {
            this.pgs_scholar_name = pgs_scholar_name;
        }

        public Object getPgs_scholar_guide_name() {
            return pgs_scholar_guide_name;
        }

        public void setPgs_scholar_guide_name(Object pgs_scholar_guide_name) {
            this.pgs_scholar_guide_name = pgs_scholar_guide_name;
        }

        public String getPgs_research_title() {
            return pgs_research_title;
        }

        public void setPgs_research_title(String pgs_research_title) {
            this.pgs_research_title = pgs_research_title;
        }

        public int getPgs_reg_year() {
            return pgs_reg_year;
        }

        public void setPgs_reg_year(int pgs_reg_year) {
            this.pgs_reg_year = pgs_reg_year;
        }

        public int getPgs_award_year() {
            return pgs_award_year;
        }

        public void setPgs_award_year(int pgs_award_year) {
            this.pgs_award_year = pgs_award_year;
        }

        public String getPgs_status() {
            return pgs_status;
        }

        public void setPgs_status(String pgs_status) {
            this.pgs_status = pgs_status;
        }

        public int getPgs_emp_id() {
            return pgs_emp_id;
        }

        public void setPgs_emp_id(int pgs_emp_id) {
            this.pgs_emp_id = pgs_emp_id;
        }

        public int getPgs_prt_emp_id() {
            return pgs_prt_emp_id;
        }

        public void setPgs_prt_emp_id(int pgs_prt_emp_id) {
            this.pgs_prt_emp_id = pgs_prt_emp_id;
        }

        public int getPgs_year_id() {
            return pgs_year_id;
        }

        public void setPgs_year_id(int pgs_year_id) {
            this.pgs_year_id = pgs_year_id;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }
    }
}
