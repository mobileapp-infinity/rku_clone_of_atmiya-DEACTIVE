package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ReqPatentAwardedListPojo {


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
         * pat_is_approve_value : Pending
         * approve_status : 1
         * pat_emp_name : 740 - HIMANI TEST THAKER
         * year_name : 2020-21
         * id : 1
         * comp_id : 1
         * status : 1
         * pat_patent_name : Test
         * pat_patent_number : 123456
         * pat_patent_year : 2020
         * pat_file_upload : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/Patent/1/Beginning Java 8 Fundamentals.pdf ( PDFDrive.com ).pdf
         * pat_emp_id : 1126
         * pat_approve_reject_reason : null
         * create_by : Himanit
         * create_dnt : 2020-01-21T14:39:43.513
         * approve_reject_dnt : null
         * modify_by : null
         * approve_reject_by_user : null
         */

        private int SrNo;
        private String pat_is_approve_value;
        private int approve_status;
        private String pat_emp_name;
        private String year_name;
        private int id;
        private int comp_id;
        private int status;
        private String pat_patent_name;
        private String pat_patent_number;
        private String pat_patent_year;
        private String pat_file_upload;
        private int pat_emp_id;
        private Object pat_approve_reject_reason;
        private String create_by;
        private String create_dnt;
        private Object approve_reject_dnt;
        private Object modify_by;
        private Object approve_reject_by_user;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public String getPat_is_approve_value() {
            return pat_is_approve_value;
        }

        public void setPat_is_approve_value(String pat_is_approve_value) {
            this.pat_is_approve_value = pat_is_approve_value;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getPat_emp_name() {
            return pat_emp_name;
        }

        public void setPat_emp_name(String pat_emp_name) {
            this.pat_emp_name = pat_emp_name;
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

        public String getPat_patent_name() {
            return pat_patent_name;
        }

        public void setPat_patent_name(String pat_patent_name) {
            this.pat_patent_name = pat_patent_name;
        }

        public String getPat_patent_number() {
            return pat_patent_number;
        }

        public void setPat_patent_number(String pat_patent_number) {
            this.pat_patent_number = pat_patent_number;
        }

        public String getPat_patent_year() {
            return pat_patent_year;
        }

        public void setPat_patent_year(String pat_patent_year) {
            this.pat_patent_year = pat_patent_year;
        }

        public String getPat_file_upload() {
            return pat_file_upload;
        }

        public void setPat_file_upload(String pat_file_upload) {
            this.pat_file_upload = pat_file_upload;
        }

        public int getPat_emp_id() {
            return pat_emp_id;
        }

        public void setPat_emp_id(int pat_emp_id) {
            this.pat_emp_id = pat_emp_id;
        }

        public Object getPat_approve_reject_reason() {
            return pat_approve_reject_reason;
        }

        public void setPat_approve_reject_reason(Object pat_approve_reject_reason) {
            this.pat_approve_reject_reason = pat_approve_reject_reason;
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
