package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetListOfCommitteeWiseMemberDetailsPojo {

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
         * id : 29
         * comp_id : 1
         * status : 1
         * create_by : raashid.saiyed@rku.ac.in
         * create_ip : 117.240.19.146
         * create_date : 2020-02-25T13:45:12.943
         * modify_by : null
         * modify_ip : null
         * modify_date : null
         * main_coordinatior : 561 - RAASHID SAIYED
         * committee_name :  KS Patel Center for Entrepreneurship
         * rolls_responsibity : Responsible to execute start-up, innovation and entrepreneurship related activities at School
         Responsible to coordinate SSIP related activities at school
         Facilitate queries of faculty members and students related to entrepreneurship and startup
         Encourage students to join E-cell as well as to participate in various entrepreneurship related activities
         Prepare all required reports and maintain a comprehensive set of records
         Contribute to the department and University in the best of the capacity
         Work in coordination with University incubation cell
         * cmtm_member_id : PARESH TANNA,milan.savaliya@rku.ac.in,NIRAV MANDAVIA,amit.sharma@rku.ac.in,BHAVIN DHADUK,VISHVESH KANABAR,HIREN HARSORA
         */

        private int SrNo;
        private int id;
        private int comp_id;
        private int status;
        private String create_by;
        private String create_ip;
        private String create_date;
        private Object modify_by;
        private Object modify_ip;
        private Object modify_date;
        private String main_coordinatior;
        private String committee_name;
        private String rolls_responsibity;
        private String cmtm_member_id;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
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

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public String getCreate_ip() {
            return create_ip;
        }

        public void setCreate_ip(String create_ip) {
            this.create_ip = create_ip;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
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

        public Object getModify_date() {
            return modify_date;
        }

        public void setModify_date(Object modify_date) {
            this.modify_date = modify_date;
        }

        public String getMain_coordinatior() {
            return main_coordinatior;
        }

        public void setMain_coordinatior(String main_coordinatior) {
            this.main_coordinatior = main_coordinatior;
        }

        public String getCommittee_name() {
            return committee_name;
        }

        public void setCommittee_name(String committee_name) {
            this.committee_name = committee_name;
        }

        public String getRolls_responsibity() {
            return rolls_responsibity;
        }

        public void setRolls_responsibity(String rolls_responsibity) {
            this.rolls_responsibity = rolls_responsibity;
        }

        public String getCmtm_member_id() {
            return cmtm_member_id;
        }

        public void setCmtm_member_id(String cmtm_member_id) {
            this.cmtm_member_id = cmtm_member_id;
        }
    }
}
