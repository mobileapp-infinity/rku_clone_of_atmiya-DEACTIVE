package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ReqPublicationInJournalsListPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setDate(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * SrNo : 1
         * emp_parent_id : 688
         * id : 51
         * ipc_emp_name : 734 - TEST RASHMIKANT
         * status : Pending
         * approve_status : 1
         * create_by : rashmikantm
         * create_date : 2020-03-22T17:35:00.627
         * year_name : 2017-18
         * ipj_author_no : 8
         * ipj_author_type : Primary
         * ipj_research_paper : hfjf
         * ipj_journal_name : ydr
         * ipj_year : 2020
         * ipj_isbn_issn_number : ydd
         * ipj_Journal_level : National
         * ipj_category : C
         * ipc_file_upload : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/JournalsPublication/51/8853f3b7-c6a5-48f8-9a73-3b65ea69788c.pdf
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
        private int ipj_author_no;
        private String ipj_author_type;
        private String ipj_research_paper;
        private String ipj_journal_name;
        private int ipj_year;
        private String ipj_isbn_issn_number;
        private String ipj_Journal_level;
        private String ipj_category;
        private String ipc_file_upload;
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

        public int getIpj_author_no() {
            return ipj_author_no;
        }

        public void setIpj_author_no(int ipj_author_no) {
            this.ipj_author_no = ipj_author_no;
        }

        public String getIpj_author_type() {
            return ipj_author_type;
        }

        public void setIpj_author_type(String ipj_author_type) {
            this.ipj_author_type = ipj_author_type;
        }

        public String getIpj_research_paper() {
            return ipj_research_paper;
        }

        public void setIpj_research_paper(String ipj_research_paper) {
            this.ipj_research_paper = ipj_research_paper;
        }

        public String getIpj_journal_name() {
            return ipj_journal_name;
        }

        public void setIpj_journal_name(String ipj_journal_name) {
            this.ipj_journal_name = ipj_journal_name;
        }

        public int getIpj_year() {
            return ipj_year;
        }

        public void setIpj_year(int ipj_year) {
            this.ipj_year = ipj_year;
        }

        public String getIpj_isbn_issn_number() {
            return ipj_isbn_issn_number;
        }

        public void setIpj_isbn_issn_number(String ipj_isbn_issn_number) {
            this.ipj_isbn_issn_number = ipj_isbn_issn_number;
        }

        public String getIpj_Journal_level() {
            return ipj_Journal_level;
        }

        public void setIpj_Journal_level(String ipj_Journal_level) {
            this.ipj_Journal_level = ipj_Journal_level;
        }

        public String getIpj_category() {
            return ipj_category;
        }

        public void setIpj_category(String ipj_category) {
            this.ipj_category = ipj_category;
        }

        public String getIpc_file_upload() {
            return ipc_file_upload;
        }

        public void setIpc_file_upload(String ipc_file_upload) {
            this.ipc_file_upload = ipc_file_upload;
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
