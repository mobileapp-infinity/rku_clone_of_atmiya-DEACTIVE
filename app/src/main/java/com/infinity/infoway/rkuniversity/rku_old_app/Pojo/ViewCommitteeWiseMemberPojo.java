package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ViewCommitteeWiseMemberPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 24
         * comp_id : 1
         * status : 1
         * create_by : 751
         * create_ip : 117.240.19.146
         * create_date : 2020-02-22T14:59:39.71
         * modify_by : 751
         * modify_ip : 117.240.19.146
         * modify_date : 2020-02-22T15:08:37.683
         * main_coordinatior : 83 - KAMLESHKUMAR PATEL
         * cmtm_committee_id : 20
         * committee_name : ITS (ERP, Canvas LMS & Website)
         * cmtm_member_id : INDU JAISWAL,HARSHAL DESAI,janki.kansagra@rku.ac.in,PARTH RAVAL,SOHIL CHAUHAN,rahul.patoliya@rku.ac.in,shivangi.patel@rku.ac.in,JANKI AGHERA
         * cmtm_member_wise_id : 688,712,768,846,859,882,889,1067
         */

        private int id;
        private int comp_id;
        private int status;
        private int create_by;
        private String create_ip;
        private String create_date;
        private int modify_by;
        private String modify_ip;
        private String modify_date;
        private String main_coordinatior;
        private int cmtm_committee_id;
        private String committee_name;
        private String cmtm_member_id;
        private String cmtm_member_wise_id;

        public String getCmtm_member_wise_id() {
            return cmtm_member_wise_id;
        }

        public void setCmtm_member_wise_id(String cmtm_member_wise_id) {
            this.cmtm_member_wise_id = cmtm_member_wise_id;
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

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public int getModify_by() {
            return modify_by;
        }

        public void setModify_by(int modify_by) {
            this.modify_by = modify_by;
        }

        public String getModify_ip() {
            return modify_ip;
        }

        public void setModify_ip(String modify_ip) {
            this.modify_ip = modify_ip;
        }

        public String getModify_date() {
            return modify_date;
        }

        public void setModify_date(String modify_date) {
            this.modify_date = modify_date;
        }

        public String getMain_coordinatior() {
            return main_coordinatior;
        }

        public void setMain_coordinatior(String main_coordinatior) {
            this.main_coordinatior = main_coordinatior;
        }

        public int getCmtm_committee_id() {
            return cmtm_committee_id;
        }

        public void setCmtm_committee_id(int cmtm_committee_id) {
            this.cmtm_committee_id = cmtm_committee_id;
        }

        public String getCommittee_name() {
            return committee_name;
        }

        public void setCommittee_name(String committee_name) {
            this.committee_name = committee_name;
        }

        public String getCmtm_member_id() {
            return cmtm_member_id;
        }

        public void setCmtm_member_id(String cmtm_member_id) {
            this.cmtm_member_id = cmtm_member_id;
        }
    }
}
