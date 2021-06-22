package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ReqViewCPDApplicationPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 15
         * comp_id : 1
         * status : 1
         * create_by : 1017
         * create_ip : 175.100.128.2
         * create_dnt : 2019-12-30T14:13:38.02
         * modify_by : 884
         * modify_ip : null
         * modify_dnt : 2020-01-04T20:06:50.523
         * fdpo_year_id : 3
         * fdpo_workshop_name : Hands on Workshop on Advance Molecular Biology and Immunology Techniques
         * fdpo_from_date : 05/12/2019
         * fdpo_to_date : 06/12/2019
         * fdpo_website_link : SOS_CPD_1909106 Event Conduction Report.pdf
         * fdpo_participants_number : 57
         * fdpo_cpd_app_link : SOS_CPD_1909106 CPD Form.pdf
         * fdpo_feedback : null
         * fdpo_emp_id : 969
         * fdpo_prt_emp_id : 836
         * year_name : 2019-20
         * File_cpd_app_link : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/FDPOrganized/CPDapplink/15/SOS_CPD_1909106 CPD Form.pdf
         * File_website_link : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/FDPOrganized/Websitelink/15/SOS_CPD_1909106 Event Conduction Report.pdf
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
        private int fdpo_year_id;
        private String fdpo_workshop_name;
        private String fdpo_from_date;
        private String fdpo_to_date;
        private String fdpo_website_link;
        private int fdpo_participants_number;
        private String fdpo_cpd_app_link;
        private Object fdpo_feedback;
        private int fdpo_emp_id;
        private int fdpo_prt_emp_id;
        private String year_name;
        private String File_cpd_app_link;
        private String File_website_link;

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

        public int getFdpo_year_id() {
            return fdpo_year_id;
        }

        public void setFdpo_year_id(int fdpo_year_id) {
            this.fdpo_year_id = fdpo_year_id;
        }

        public String getFdpo_workshop_name() {
            return fdpo_workshop_name;
        }

        public void setFdpo_workshop_name(String fdpo_workshop_name) {
            this.fdpo_workshop_name = fdpo_workshop_name;
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

        public String getFdpo_website_link() {
            return fdpo_website_link;
        }

        public void setFdpo_website_link(String fdpo_website_link) {
            this.fdpo_website_link = fdpo_website_link;
        }

        public int getFdpo_participants_number() {
            return fdpo_participants_number;
        }

        public void setFdpo_participants_number(int fdpo_participants_number) {
            this.fdpo_participants_number = fdpo_participants_number;
        }

        public String getFdpo_cpd_app_link() {
            return fdpo_cpd_app_link;
        }

        public void setFdpo_cpd_app_link(String fdpo_cpd_app_link) {
            this.fdpo_cpd_app_link = fdpo_cpd_app_link;
        }

        public Object getFdpo_feedback() {
            return fdpo_feedback;
        }

        public void setFdpo_feedback(Object fdpo_feedback) {
            this.fdpo_feedback = fdpo_feedback;
        }

        public int getFdpo_emp_id() {
            return fdpo_emp_id;
        }

        public void setFdpo_emp_id(int fdpo_emp_id) {
            this.fdpo_emp_id = fdpo_emp_id;
        }

        public int getFdpo_prt_emp_id() {
            return fdpo_prt_emp_id;
        }

        public void setFdpo_prt_emp_id(int fdpo_prt_emp_id) {
            this.fdpo_prt_emp_id = fdpo_prt_emp_id;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }

        public String getFile_cpd_app_link() {
            return File_cpd_app_link;
        }

        public void setFile_cpd_app_link(String File_cpd_app_link) {
            this.File_cpd_app_link = File_cpd_app_link;
        }

        public String getFile_website_link() {
            return File_website_link;
        }

        public void setFile_website_link(String File_website_link) {
            this.File_website_link = File_website_link;
        }
    }
}
