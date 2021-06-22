package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ReqViewPhdScholarGuidedPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 22
         * comp_id : 1
         * status : 1
         * create_by : 1044
         * create_ip : 0
         * create_dnt : 2020-03-19T14:46:48.797
         * modify_by : null
         * modify_ip : null
         * modify_dnt : null
         * phdsg_scholar_name : Remish
         * phdsg_dept_name : CE
         * phdsg_guide_name : Remish
         * phdsg_thesis_title : Test
         * phdsg_scholar_reg_year : 2018
         * phdsg_award_year : 2018
         * phdsg_status : 1
         * phdsg_year_id : 2
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
        private String phdsg_scholar_name;
        private String phdsg_dept_name;
        private String phdsg_guide_name;
        private String phdsg_thesis_title;
        private int phdsg_scholar_reg_year;
        private int phdsg_award_year;
        private String phdsg_status;
        private int phdsg_year_id;
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

        public String getPhdsg_scholar_name() {
            return phdsg_scholar_name;
        }

        public void setPhdsg_scholar_name(String phdsg_scholar_name) {
            this.phdsg_scholar_name = phdsg_scholar_name;
        }

        public String getPhdsg_dept_name() {
            return phdsg_dept_name;
        }

        public void setPhdsg_dept_name(String phdsg_dept_name) {
            this.phdsg_dept_name = phdsg_dept_name;
        }

        public String getPhdsg_guide_name() {
            return phdsg_guide_name;
        }

        public void setPhdsg_guide_name(String phdsg_guide_name) {
            this.phdsg_guide_name = phdsg_guide_name;
        }

        public String getPhdsg_thesis_title() {
            return phdsg_thesis_title;
        }

        public void setPhdsg_thesis_title(String phdsg_thesis_title) {
            this.phdsg_thesis_title = phdsg_thesis_title;
        }

        public int getPhdsg_scholar_reg_year() {
            return phdsg_scholar_reg_year;
        }

        public void setPhdsg_scholar_reg_year(int phdsg_scholar_reg_year) {
            this.phdsg_scholar_reg_year = phdsg_scholar_reg_year;
        }

        public int getPhdsg_award_year() {
            return phdsg_award_year;
        }

        public void setPhdsg_award_year(int phdsg_award_year) {
            this.phdsg_award_year = phdsg_award_year;
        }

        public String getPhdsg_status() {
            return phdsg_status;
        }

        public void setPhdsg_status(String phdsg_status) {
            this.phdsg_status = phdsg_status;
        }

        public int getPhdsg_year_id() {
            return phdsg_year_id;
        }

        public void setPhdsg_year_id(int phdsg_year_id) {
            this.phdsg_year_id = phdsg_year_id;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }
    }
}
