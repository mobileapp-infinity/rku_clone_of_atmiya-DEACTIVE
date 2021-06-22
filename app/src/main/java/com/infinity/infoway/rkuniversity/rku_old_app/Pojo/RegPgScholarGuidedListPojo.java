package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class RegPgScholarGuidedListPojo  {


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
         * emp_parent_id : 688
         * id : 19
         * ipc_emp_name : 734 - TEST RASHMIKANT
         * status : Pending
         * approve_status : 1
         * create_by : rashmikantm
         * create_date : 2020-03-20T02:05:26.207
         * year_name : null
         * pgs_scholar_guide_name : test
         * pgs_scholar_name : test
         * pgs_research_title : test
         * pgs_award_year : 2018
         * pgs_reg_year : 2018
         * pgs_status : 1
         * approve_reject_by_user : null
         * approve_rejct_date : null
         */

        private int SrNo;
        private int emp_parent_id;
        private int id;
        private String ipc_emp_name;
        private String status;
        private int approve_status;
        private String create_by;
        private String create_date;
        private String year_name;
        private String pgs_scholar_guide_name;
        private String pgs_scholar_name;
        private String pgs_research_title;
        private int pgs_award_year;
        private int pgs_reg_year;
        private String pgs_status;
        private Object approve_reject_by_user;
        private Object approve_rejct_date;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public int getEmp_parent_id() {
            return emp_parent_id;
        }

        public void setEmp_parent_id(int emp_parent_id) {
            this.emp_parent_id = emp_parent_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIpc_emp_name() {
            return ipc_emp_name;
        }

        public void setIpc_emp_name(String ipc_emp_name) {
            this.ipc_emp_name = ipc_emp_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }

        public String getPgs_scholar_guide_name() {
            return pgs_scholar_guide_name;
        }

        public void setPgs_scholar_guide_name(String pgs_scholar_guide_name) {
            this.pgs_scholar_guide_name = pgs_scholar_guide_name;
        }

        public String getPgs_scholar_name() {
            return pgs_scholar_name;
        }

        public void setPgs_scholar_name(String pgs_scholar_name) {
            this.pgs_scholar_name = pgs_scholar_name;
        }

        public String getPgs_research_title() {
            return pgs_research_title;
        }

        public void setPgs_research_title(String pgs_research_title) {
            this.pgs_research_title = pgs_research_title;
        }

        public int getPgs_award_year() {
            return pgs_award_year;
        }

        public void setPgs_award_year(int pgs_award_year) {
            this.pgs_award_year = pgs_award_year;
        }

        public int getPgs_reg_year() {
            return pgs_reg_year;
        }

        public void setPgs_reg_year(int pgs_reg_year) {
            this.pgs_reg_year = pgs_reg_year;
        }

        public String getPgs_status() {
            return pgs_status;
        }

        public void setPgs_status(String pgs_status) {
            this.pgs_status = pgs_status;
        }

        public Object getApprove_reject_by_user() {
            return approve_reject_by_user;
        }

        public void setApprove_reject_by_user(Object approve_reject_by_user) {
            this.approve_reject_by_user = approve_reject_by_user;
        }

        public Object getApprove_rejct_date() {
            return approve_rejct_date;
        }

        public void setApprove_rejct_date(Object approve_rejct_date) {
            this.approve_rejct_date = approve_rejct_date;
        }
    }
}
