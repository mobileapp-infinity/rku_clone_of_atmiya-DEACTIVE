package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class GetPDApplicationListPojo implements Serializable{


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * SrNo : 4
         * pd_is_approve_value : Pending
         * approve_status : 1
         * pd_document_is_approve_value : Document Upload Pending
         * approve_document_status : 1
         * pd_account_is_approve_value : Pending
         * pd_file_resion : null
         * id : 474
         * status : True
         * pd_emp_name : 313 - VIJAY KUMAR
         * pd_application_no : SOSWORKSHOP42020474
         * pd_event_name : Happiness Program Round IV
         * ev_type_name : Workshop
         * pd_organized_by : RK University
         * ev_category_name : Soft skills & Leadership
         * pd_event_credit : 30
         * pd_final_credit : null
         * pd_event_city : Rajkot
         * pd_no_od_leaves : 0.0
         * pd_event_from_date : 22/07/2019
         * pd_event_to_date : 26/07/2019
         * pd_no_of_day : 5
         * ev_role_name : Participant
         * pd_event_description : This workshop helped in learning daily stress management at workplace.
         * pd_registration_fees : 0.0
         * pd_leave_from_date : 2019-07-22T00:00:00
         * pd_leave_to_date : 2019-07-26T00:00:00
         * pd_transportation : 0.0
         * pd_leave_expense : 0.0
         * pd_accommodation : 0.0
         * pd_total_expense : 0.0
         * sanctioned_expense : 0.0
         * pd_payable_amount : null
         * approve_by_user : null
         * modify_dnt : null
         * pd_is_approved : 0
         * ed_doc_file : D:\datahost\rku.ierp.co.in\data\app\PD\1\474\Mail confirmation from Dr. Chetan patel_474_574_RK University Mail _ Happiness Program Round IV.pdf
         * Prt_emp_name : mayank.pandya@rku.ac.in
         * file_count : 1
         */

        private int SrNo;
        private String pd_is_approve_value;
        private int approve_status;
        private String pd_document_is_approve_value;
        private int approve_document_status;
        private String pd_account_is_approve_value;
        private Object pd_file_resion;
        private int id;
        private String status;
        private String pd_emp_name;
        private String pd_application_no;
        private String pd_event_name;
        private String ev_type_name;
        private String pd_organized_by;
        private String ev_category_name;
        private String pd_event_credit;
        private Object pd_final_credit;
        private String pd_event_city;
        private double pd_no_od_leaves;
        private String pd_event_from_date;
        private String pd_event_to_date;
        private String pd_no_of_day;
        private String ev_role_name;
        private String pd_event_description;
        private double pd_registration_fees;
        private String pd_leave_from_date;
        private String pd_leave_to_date;
        private double pd_transportation;
        private double pd_leave_expense;
        private double pd_accommodation;
        private double pd_total_expense;
        private double sanctioned_expense;
        private Object pd_payable_amount;
        private Object approve_by_user;
        private Object modify_dnt;
        private int pd_is_approved;
        private String ed_doc_file;
        private String Prt_emp_name;
        private int file_count;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public String getPd_is_approve_value() {
            return pd_is_approve_value;
        }

        public void setPd_is_approve_value(String pd_is_approve_value) {
            this.pd_is_approve_value = pd_is_approve_value;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getPd_document_is_approve_value() {
            return pd_document_is_approve_value;
        }

        public void setPd_document_is_approve_value(String pd_document_is_approve_value) {
            this.pd_document_is_approve_value = pd_document_is_approve_value;
        }

        public int getApprove_document_status() {
            return approve_document_status;
        }

        public void setApprove_document_status(int approve_document_status) {
            this.approve_document_status = approve_document_status;
        }

        public String getPd_account_is_approve_value() {
            return pd_account_is_approve_value;
        }

        public void setPd_account_is_approve_value(String pd_account_is_approve_value) {
            this.pd_account_is_approve_value = pd_account_is_approve_value;
        }

        public Object getPd_file_resion() {
            return pd_file_resion;
        }

        public void setPd_file_resion(Object pd_file_resion) {
            this.pd_file_resion = pd_file_resion;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPd_emp_name() {
            return pd_emp_name;
        }

        public void setPd_emp_name(String pd_emp_name) {
            this.pd_emp_name = pd_emp_name;
        }

        public String getPd_application_no() {
            return pd_application_no;
        }

        public void setPd_application_no(String pd_application_no) {
            this.pd_application_no = pd_application_no;
        }

        public String getPd_event_name() {
            return pd_event_name;
        }

        public void setPd_event_name(String pd_event_name) {
            this.pd_event_name = pd_event_name;
        }

        public String getEv_type_name() {
            return ev_type_name;
        }

        public void setEv_type_name(String ev_type_name) {
            this.ev_type_name = ev_type_name;
        }

        public String getPd_organized_by() {
            return pd_organized_by;
        }

        public void setPd_organized_by(String pd_organized_by) {
            this.pd_organized_by = pd_organized_by;
        }

        public String getEv_category_name() {
            return ev_category_name;
        }

        public void setEv_category_name(String ev_category_name) {
            this.ev_category_name = ev_category_name;
        }

        public String getPd_event_credit() {
            return pd_event_credit;
        }

        public void setPd_event_credit(String pd_event_credit) {
            this.pd_event_credit = pd_event_credit;
        }

        public Object getPd_final_credit() {
            return pd_final_credit;
        }

        public void setPd_final_credit(Object pd_final_credit) {
            this.pd_final_credit = pd_final_credit;
        }

        public String getPd_event_city() {
            return pd_event_city;
        }

        public void setPd_event_city(String pd_event_city) {
            this.pd_event_city = pd_event_city;
        }

        public double getPd_no_od_leaves() {
            return pd_no_od_leaves;
        }

        public void setPd_no_od_leaves(double pd_no_od_leaves) {
            this.pd_no_od_leaves = pd_no_od_leaves;
        }

        public String getPd_event_from_date() {
            return pd_event_from_date;
        }

        public void setPd_event_from_date(String pd_event_from_date) {
            this.pd_event_from_date = pd_event_from_date;
        }

        public String getPd_event_to_date() {
            return pd_event_to_date;
        }

        public void setPd_event_to_date(String pd_event_to_date) {
            this.pd_event_to_date = pd_event_to_date;
        }

        public String getPd_no_of_day() {
            return pd_no_of_day;
        }

        public void setPd_no_of_day(String pd_no_of_day) {
            this.pd_no_of_day = pd_no_of_day;
        }

        public String getEv_role_name() {
            return ev_role_name;
        }

        public void setEv_role_name(String ev_role_name) {
            this.ev_role_name = ev_role_name;
        }

        public String getPd_event_description() {
            return pd_event_description;
        }

        public void setPd_event_description(String pd_event_description) {
            this.pd_event_description = pd_event_description;
        }

        public double getPd_registration_fees() {
            return pd_registration_fees;
        }

        public void setPd_registration_fees(double pd_registration_fees) {
            this.pd_registration_fees = pd_registration_fees;
        }

        public String getPd_leave_from_date() {
            return pd_leave_from_date;
        }

        public void setPd_leave_from_date(String pd_leave_from_date) {
            this.pd_leave_from_date = pd_leave_from_date;
        }

        public String getPd_leave_to_date() {
            return pd_leave_to_date;
        }

        public void setPd_leave_to_date(String pd_leave_to_date) {
            this.pd_leave_to_date = pd_leave_to_date;
        }

        public double getPd_transportation() {
            return pd_transportation;
        }

        public void setPd_transportation(double pd_transportation) {
            this.pd_transportation = pd_transportation;
        }

        public double getPd_leave_expense() {
            return pd_leave_expense;
        }

        public void setPd_leave_expense(double pd_leave_expense) {
            this.pd_leave_expense = pd_leave_expense;
        }

        public double getPd_accommodation() {
            return pd_accommodation;
        }

        public void setPd_accommodation(double pd_accommodation) {
            this.pd_accommodation = pd_accommodation;
        }

        public double getPd_total_expense() {
            return pd_total_expense;
        }

        public void setPd_total_expense(double pd_total_expense) {
            this.pd_total_expense = pd_total_expense;
        }

        public double getSanctioned_expense() {
            return sanctioned_expense;
        }

        public void setSanctioned_expense(double sanctioned_expense) {
            this.sanctioned_expense = sanctioned_expense;
        }

        public Object getPd_payable_amount() {
            return pd_payable_amount;
        }

        public void setPd_payable_amount(Object pd_payable_amount) {
            this.pd_payable_amount = pd_payable_amount;
        }

        public Object getApprove_by_user() {
            return approve_by_user;
        }

        public void setApprove_by_user(Object approve_by_user) {
            this.approve_by_user = approve_by_user;
        }

        public Object getModify_dnt() {
            return modify_dnt;
        }

        public void setModify_dnt(Object modify_dnt) {
            this.modify_dnt = modify_dnt;
        }

        public int getPd_is_approved() {
            return pd_is_approved;
        }

        public void setPd_is_approved(int pd_is_approved) {
            this.pd_is_approved = pd_is_approved;
        }

        public String getEd_doc_file() {
            return ed_doc_file;
        }

        public void setEd_doc_file(String ed_doc_file) {
            this.ed_doc_file = ed_doc_file;
        }

        public String getPrt_emp_name() {
            return Prt_emp_name;
        }

        public void setPrt_emp_name(String Prt_emp_name) {
            this.Prt_emp_name = Prt_emp_name;
        }

        public int getFile_count() {
            return file_count;
        }

        public void setFile_count(int file_count) {
            this.file_count = file_count;
        }
    }
}
