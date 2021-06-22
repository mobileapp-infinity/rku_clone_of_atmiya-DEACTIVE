package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ReqGrantReceivedListPojo implements Serializable{


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * SrNo : 1
         * gr_is_approve_value : Pending
         * approve_status : 1
         * gr_emp_name : 734 - TEST RASHMIKANT
         * year_name : 2017-18
         * id : 18
         * comp_id : 1
         * status : 1
         * gr_project_name : test
         * gr_principal_investigator : test
         * gr_principal_investigator_department : test
         * gr_award_year : 2017-18
         * gr_funds_provided : test
         * gr_project_duration : 10
         * gr_emp_id : 1120
         * gr_agency_name : Govt
         * gr_approve_reject_reason : null
         * create_by : rashmikantm
         * create_dnt : 2020-03-18T21:05:33.513
         * approve_reject_dnt : null
         * modify_by : null
         * approve_reject_by_user : null
         */

        private int SrNo;
        private String gr_is_approve_value;
        private int approve_status;
        private String gr_emp_name;
        private String year_name;
        private int id;
        private int comp_id;
        private int status;
        private String gr_project_name;
        private String gr_principal_investigator;
        private String gr_principal_investigator_department;
        private String gr_award_year;
        private String gr_funds_provided;
        private String gr_project_duration;
        private int gr_emp_id;
        private String gr_agency_name;
        private Object gr_approve_reject_reason;
        private String create_by;
        private String create_dnt;
        private Object approve_reject_dnt;
        private Object modify_by;
        private Object approve_reject_by_user;
        private String gr_status;

        public String getGr_status() {
            return gr_status;
        }

        public void setGr_status(String gr_status) {
            this.gr_status = gr_status;
        }

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public String getGr_is_approve_value() {
            return gr_is_approve_value;
        }

        public void setGr_is_approve_value(String gr_is_approve_value) {
            this.gr_is_approve_value = gr_is_approve_value;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getGr_emp_name() {
            return gr_emp_name;
        }

        public void setGr_emp_name(String gr_emp_name) {
            this.gr_emp_name = gr_emp_name;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }

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

        public int getGr_emp_id() {
            return gr_emp_id;
        }

        public void setGr_emp_id(int gr_emp_id) {
            this.gr_emp_id = gr_emp_id;
        }

        public String getGr_agency_name() {
            return gr_agency_name;
        }

        public void setGr_agency_name(String gr_agency_name) {
            this.gr_agency_name = gr_agency_name;
        }

        public Object getGr_approve_reject_reason() {
            return gr_approve_reject_reason;
        }

        public void setGr_approve_reject_reason(Object gr_approve_reject_reason) {
            this.gr_approve_reject_reason = gr_approve_reject_reason;
        }

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public String getCreate_dnt() {
            return create_dnt;
        }

        public void setCreate_dnt(String create_dnt) {
            this.create_dnt = create_dnt;
        }

        public Object getApprove_reject_dnt() {
            return approve_reject_dnt;
        }

        public void setApprove_reject_dnt(Object approve_reject_dnt) {
            this.approve_reject_dnt = approve_reject_dnt;
        }

        public Object getModify_by() {
            return modify_by;
        }

        public void setModify_by(Object modify_by) {
            this.modify_by = modify_by;
        }

        public Object getApprove_reject_by_user() {
            return approve_reject_by_user;
        }

        public void setApprove_reject_by_user(Object approve_reject_by_user) {
            this.approve_reject_by_user = approve_reject_by_user;
        }
    }
}
