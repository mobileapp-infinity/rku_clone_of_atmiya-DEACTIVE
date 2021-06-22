package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ViewPDApplicationPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 383
         * comp_id : 1
         * status : 1
         * create_by : 1086
         * create_ip : 117.240.19.146
         * create_dnt : 2020-01-20T08:28:57.87
         * modify_by : 884
         * modify_ip : null
         * modify_dnt : 2020-03-14T23:08:30.08
         * pd_application_no : SOSWORKSHOP12020383
         * pd_event_name : national workshop on molecular docking
         * pd_event_type_id : 3
         * pd_organized_by : Dr.sejal shah
         * pd_event_cate_id : 2
         * pd_event_credit : 9
         * pd_event_city : RK University Rajkot
         * pd_event_from_date : 2019-12-21T00:00:00
         * pd_event_to_date : 2019-12-23T00:00:00
         * pd_no_of_day : 3
         * pd_role_of_applicant : 4
         * pd_event_description : the workshop was based on computer-based techniques for deriving, representing and
         * manipulating the structures and reactions of molecules, and those properties that are dependent on
         * these three-dimensional structures.
         * pd_registration_fees : 2500.0
         * pd_leave_from_date : 2019-12-21T00:00:00
         * pd_leave_to_date : 2019-12-23T00:00:00
         * pd_transportation : 0.0
         * pd_leave_expense : 0.0
         * pd_accommodation : 0.0
         * pd_total_expense : 2500.0
         * pd_prt_emp_ids : 836,656,1003
         * pd_is_approve : 1
         * pd_approval_by : 1053
         * pd_reject_resion : null
         * pd_emp_id : 1055
         * pd_is_document : 1
         * pd_document_approve : 1
         * pd_is_payment : null
         * pd_file_resion : This workshop was domain specific
         * pd_no_od_leaves : 0.0
         * pd_final_leave_from_date : 2019-12-21T00:00:00
         * pd_final_to_date : 2019-12-23T00:00:00
         * pd_final_registration_fees : 2500.0
         * pd_final_transportation : 0.0
         * pd_final_accommodation : 0.0
         * pd_final_leave_expense : 0.0
         * pd_final_total_expense : 2500.0
         * ev_type_name : Workshop
         * ev_category_name : Domain Specific
         * epd_emp_name : 669 - KIRAN KURHADE
         * FileName : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/PD/1/383/national workshop on molecular docking_383_468_moleculerrr.pdf
         */

        private int id;
        private int comp_id;
        private int status;
        private int create_by;
        private String create_ip;
        private String create_dnt;
        private int modify_by;
        private Object modify_ip;
        private String modify_dnt;
        private String pd_application_no;
        private String pd_event_name;
        private int pd_event_type_id;
        private String pd_organized_by;
        private int pd_event_cate_id;
        private String pd_event_credit;
        private String pd_event_city;
        private String pd_event_from_date;
        private String pd_event_to_date;
        private String pd_no_of_day;
        private int pd_role_of_applicant;
        private String pd_event_description;
        private double pd_registration_fees;
        private String pd_leave_from_date;
        private String pd_leave_to_date;
        private double pd_transportation;
        private double pd_leave_expense;
        private double pd_accommodation;
        private double pd_total_expense;
        private String pd_prt_emp_ids;
        private int pd_is_approve;
        private int pd_approval_by;
        private Object pd_reject_resion;
        private int pd_emp_id;
        private int pd_is_document;
        private int pd_document_approve;
        private Object pd_is_payment;
        private String pd_file_resion;
        private double pd_no_od_leaves;
        private String pd_final_leave_from_date;
        private String pd_final_to_date;
        private double pd_final_registration_fees;
        private double pd_final_transportation;
        private double pd_final_accommodation;
        private double pd_final_leave_expense;
        private double pd_final_total_expense;
        private String ev_type_name;
        private String ev_category_name;
        private String epd_emp_name;
        private String FileName;
        private String File_Title;
        private String event_from_date;
        private String event_to_date;
        private String leave_from_date;
        private String leave_to_date;

        public String getEvent_from_date() {
            return event_from_date;
        }

        public void setEvent_from_date(String event_from_date) {
            this.event_from_date = event_from_date;
        }

        public String getEvent_to_date() {
            return event_to_date;
        }

        public void setEvent_to_date(String event_to_date) {
            this.event_to_date = event_to_date;
        }

        public String getLeave_from_date() {
            return leave_from_date;
        }

        public void setLeave_from_date(String leave_from_date) {
            this.leave_from_date = leave_from_date;
        }

        public String getLeave_to_date() {
            return leave_to_date;
        }

        public void setLeave_to_date(String leave_to_date) {
            this.leave_to_date = leave_to_date;
        }

        public String getFile_Title() {
            return File_Title;
        }

        public void setFile_Title(String file_Title) {
            File_Title = file_Title;
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

        public Object getModify_ip() {
            return modify_ip;
        }

        public void setModify_ip(Object modify_ip) {
            this.modify_ip = modify_ip;
        }

        public String getModify_dnt() {
            return modify_dnt;
        }

        public void setModify_dnt(String modify_dnt) {
            this.modify_dnt = modify_dnt;
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

        public int getPd_event_type_id() {
            return pd_event_type_id;
        }

        public void setPd_event_type_id(int pd_event_type_id) {
            this.pd_event_type_id = pd_event_type_id;
        }

        public String getPd_organized_by() {
            return pd_organized_by;
        }

        public void setPd_organized_by(String pd_organized_by) {
            this.pd_organized_by = pd_organized_by;
        }

        public int getPd_event_cate_id() {
            return pd_event_cate_id;
        }

        public void setPd_event_cate_id(int pd_event_cate_id) {
            this.pd_event_cate_id = pd_event_cate_id;
        }

        public String getPd_event_credit() {
            return pd_event_credit;
        }

        public void setPd_event_credit(String pd_event_credit) {
            this.pd_event_credit = pd_event_credit;
        }

        public String getPd_event_city() {
            return pd_event_city;
        }

        public void setPd_event_city(String pd_event_city) {
            this.pd_event_city = pd_event_city;
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

        public int getPd_role_of_applicant() {
            return pd_role_of_applicant;
        }

        public void setPd_role_of_applicant(int pd_role_of_applicant) {
            this.pd_role_of_applicant = pd_role_of_applicant;
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

        public String getPd_prt_emp_ids() {
            return pd_prt_emp_ids;
        }

        public void setPd_prt_emp_ids(String pd_prt_emp_ids) {
            this.pd_prt_emp_ids = pd_prt_emp_ids;
        }

        public int getPd_is_approve() {
            return pd_is_approve;
        }

        public void setPd_is_approve(int pd_is_approve) {
            this.pd_is_approve = pd_is_approve;
        }

        public int getPd_approval_by() {
            return pd_approval_by;
        }

        public void setPd_approval_by(int pd_approval_by) {
            this.pd_approval_by = pd_approval_by;
        }

        public Object getPd_reject_resion() {
            return pd_reject_resion;
        }

        public void setPd_reject_resion(Object pd_reject_resion) {
            this.pd_reject_resion = pd_reject_resion;
        }

        public int getPd_emp_id() {
            return pd_emp_id;
        }

        public void setPd_emp_id(int pd_emp_id) {
            this.pd_emp_id = pd_emp_id;
        }

        public int getPd_is_document() {
            return pd_is_document;
        }

        public void setPd_is_document(int pd_is_document) {
            this.pd_is_document = pd_is_document;
        }

        public int getPd_document_approve() {
            return pd_document_approve;
        }

        public void setPd_document_approve(int pd_document_approve) {
            this.pd_document_approve = pd_document_approve;
        }

        public Object getPd_is_payment() {
            return pd_is_payment;
        }

        public void setPd_is_payment(Object pd_is_payment) {
            this.pd_is_payment = pd_is_payment;
        }

        public String getPd_file_resion() {
            return pd_file_resion;
        }

        public void setPd_file_resion(String pd_file_resion) {
            this.pd_file_resion = pd_file_resion;
        }

        public double getPd_no_od_leaves() {
            return pd_no_od_leaves;
        }

        public void setPd_no_od_leaves(double pd_no_od_leaves) {
            this.pd_no_od_leaves = pd_no_od_leaves;
        }

        public String getPd_final_leave_from_date() {
            return pd_final_leave_from_date;
        }

        public void setPd_final_leave_from_date(String pd_final_leave_from_date) {
            this.pd_final_leave_from_date = pd_final_leave_from_date;
        }

        public String getPd_final_to_date() {
            return pd_final_to_date;
        }

        public void setPd_final_to_date(String pd_final_to_date) {
            this.pd_final_to_date = pd_final_to_date;
        }

        public double getPd_final_registration_fees() {
            return pd_final_registration_fees;
        }

        public void setPd_final_registration_fees(double pd_final_registration_fees) {
            this.pd_final_registration_fees = pd_final_registration_fees;
        }

        public double getPd_final_transportation() {
            return pd_final_transportation;
        }

        public void setPd_final_transportation(double pd_final_transportation) {
            this.pd_final_transportation = pd_final_transportation;
        }

        public double getPd_final_accommodation() {
            return pd_final_accommodation;
        }

        public void setPd_final_accommodation(double pd_final_accommodation) {
            this.pd_final_accommodation = pd_final_accommodation;
        }

        public double getPd_final_leave_expense() {
            return pd_final_leave_expense;
        }

        public void setPd_final_leave_expense(double pd_final_leave_expense) {
            this.pd_final_leave_expense = pd_final_leave_expense;
        }

        public double getPd_final_total_expense() {
            return pd_final_total_expense;
        }

        public void setPd_final_total_expense(double pd_final_total_expense) {
            this.pd_final_total_expense = pd_final_total_expense;
        }

        public String getEv_type_name() {
            return ev_type_name;
        }

        public void setEv_type_name(String ev_type_name) {
            this.ev_type_name = ev_type_name;
        }

        public String getEv_category_name() {
            return ev_category_name;
        }

        public void setEv_category_name(String ev_category_name) {
            this.ev_category_name = ev_category_name;
        }

        public String getEpd_emp_name() {
            return epd_emp_name;
        }

        public void setEpd_emp_name(String epd_emp_name) {
            this.epd_emp_name = epd_emp_name;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }
    }
}
