package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ReqGetCPDApplicationrequestListPojo {


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
         * emp_parent_id : 686
         * id : 55
         * ipc_emp_name : 658 - AKASH THUMAR
         * status : Pending
         * approve_status : 1
         * create_by : kajal.savaliya@rku.ac.in
         * create_date : 2020-03-26T18:19:41.573
         * year_name : 2018-19
         * fdpo_from_date : 26/03/2020
         * fdpo_to_date : 26/03/2020
         * fdpo_participants_number : 9
         * fdpo_feedback : test
         * fdpo_prt_emp_id : 686
         * fdpo_approve_reject_by : null
         * fdpo_approve_reject_reason : null
         * approve_reject_by_user : null
         * fdpo_cpd_app_link : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/FDPOrganized/CPDapplink/55/1718448f-babe-4f74-8735-d06143ff588a.pdf
         * fdpo_website_link : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/FDPOrganized/Websitelink/55/1581bbe0-a9e9-4eb2-a355-3155666e5aeb.pdf
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
        private String fdpo_from_date;
        private String fdpo_to_date;
        private int fdpo_participants_number;
        private String fdpo_feedback;
        private int fdpo_prt_emp_id;
        private Object fdpo_approve_reject_by;
        private Object fdpo_approve_reject_reason;
        private Object approve_reject_by_user;
        private String fdpo_cpd_app_link;
        private String fdpo_website_link;
        private Object approve_rejct_date;
        private String fdpo_workshop_name;

        public String getFdpo_workshop_name() {
            return fdpo_workshop_name;
        }

        public void setFdpo_workshop_name(String fdpo_workshop_name) {
            this.fdpo_workshop_name = fdpo_workshop_name;
        }

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

        public String getFdpo_from_date() {
            return fdpo_from_date;
        }

        public void setFdpo_from_date(String fdpo_from_date) {
            this.fdpo_from_date = fdpo_from_date;
        }

        public String getFdpo_to_date() {
            return fdpo_to_date;
        }

        public void setFdpo_to_date(String fdpo_to_date) {
            this.fdpo_to_date = fdpo_to_date;
        }

        public int getFdpo_participants_number() {
            return fdpo_participants_number;
        }

        public void setFdpo_participants_number(int fdpo_participants_number) {
            this.fdpo_participants_number = fdpo_participants_number;
        }

        public String getFdpo_feedback() {
            return fdpo_feedback;
        }

        public void setFdpo_feedback(String fdpo_feedback) {
            this.fdpo_feedback = fdpo_feedback;
        }

        public int getFdpo_prt_emp_id() {
            return fdpo_prt_emp_id;
        }

        public void setFdpo_prt_emp_id(int fdpo_prt_emp_id) {
            this.fdpo_prt_emp_id = fdpo_prt_emp_id;
        }

        public Object getFdpo_approve_reject_by() {
            return fdpo_approve_reject_by;
        }

        public void setFdpo_approve_reject_by(Object fdpo_approve_reject_by) {
            this.fdpo_approve_reject_by = fdpo_approve_reject_by;
        }

        public Object getFdpo_approve_reject_reason() {
            return fdpo_approve_reject_reason;
        }

        public void setFdpo_approve_reject_reason(Object fdpo_approve_reject_reason) {
            this.fdpo_approve_reject_reason = fdpo_approve_reject_reason;
        }

        public Object getApprove_reject_by_user() {
            return approve_reject_by_user;
        }

        public void setApprove_reject_by_user(Object approve_reject_by_user) {
            this.approve_reject_by_user = approve_reject_by_user;
        }

        public String getFdpo_cpd_app_link() {
            return fdpo_cpd_app_link;
        }

        public void setFdpo_cpd_app_link(String fdpo_cpd_app_link) {
            this.fdpo_cpd_app_link = fdpo_cpd_app_link;
        }

        public String getFdpo_website_link() {
            return fdpo_website_link;
        }

        public void setFdpo_website_link(String fdpo_website_link) {
            this.fdpo_website_link = fdpo_website_link;
        }

        public Object getApprove_rejct_date() {
            return approve_rejct_date;
        }

        public void setApprove_rejct_date(Object approve_rejct_date) {
            this.approve_rejct_date = approve_rejct_date;
        }
    }
}
