package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class GetPublicationInConferencePojo implements Serializable{


    private List<DataBeanBean> DataBean;

    public List<DataBeanBean> getDataBean() {
        return DataBean;
    }

    public void setDataBean(List<DataBeanBean> DataBean) {
        this.DataBean = DataBean;
    }

    public static class DataBeanBean implements Serializable {
        /**
         * SrNo : 1
         * emp_parent_id : 688
         * id : 58
         * ipc_emp_name : 734 - TEST RASHMIKANT
         * status : Pending
         * approve_status : 1
         * create_by : rashmikantm
         * create_date : 2020-03-18T10:33:52.11
         * year_name : 2017-18
         * ipc_paper_title : Test Research Paper
         * ipc_conference_title : Test Proce
         * ipc_conference_name : Test c Name
         * ipc_level : 1
         * ipc_publication_date : 18/03/2020
         * ipc_isbn_issn_number : Proce ISBN
         * ipc_publisher_name : P TEST name 18
         * ipc_file_upload : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahostku.ierp.co.in\data\app/BookPublication/58/85496c90-0ea3-4b1a-9123-8c2f22ade246.pdf
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
        private String ipc_paper_title;
        private String ipc_conference_title;
        private String ipc_conference_name;
        private String ipc_level;
        private String ipc_publication_date;
        private String ipc_isbn_issn_number;
        private String ipc_publisher_name;
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

        public String getIpc_paper_title() {
            return ipc_paper_title;
        }

        public void setIpc_paper_title(String ipc_paper_title) {
            this.ipc_paper_title = ipc_paper_title;
        }

        public String getIpc_conference_title() {
            return ipc_conference_title;
        }

        public void setIpc_conference_title(String ipc_conference_title) {
            this.ipc_conference_title = ipc_conference_title;
        }

        public String getIpc_conference_name() {
            return ipc_conference_name;
        }

        public void setIpc_conference_name(String ipc_conference_name) {
            this.ipc_conference_name = ipc_conference_name;
        }

        public String getIpc_level() {
            return ipc_level;
        }

        public void setIpc_level(String ipc_level) {
            this.ipc_level = ipc_level;
        }

        public String getIpc_publication_date() {
            return ipc_publication_date;
        }

        public void setIpc_publication_date(String ipc_publication_date) {
            this.ipc_publication_date = ipc_publication_date;
        }

        public String getIpc_isbn_issn_number() {
            return ipc_isbn_issn_number;
        }

        public void setIpc_isbn_issn_number(String ipc_isbn_issn_number) {
            this.ipc_isbn_issn_number = ipc_isbn_issn_number;
        }

        public String getIpc_publisher_name() {
            return ipc_publisher_name;
        }

        public void setIpc_publisher_name(String ipc_publisher_name) {
            this.ipc_publisher_name = ipc_publisher_name;
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
