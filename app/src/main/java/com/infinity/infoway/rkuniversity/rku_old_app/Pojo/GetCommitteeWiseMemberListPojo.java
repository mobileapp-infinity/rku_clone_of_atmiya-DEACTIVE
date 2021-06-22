package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetCommitteeWiseMemberListPojo {


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
         * id : 37
         * main_coordinatior : 734 - TEST RASHMIKANT
         * committee_name : null
         * cmtm_member_name : RASHMIKANT BHADESIYA,CHIRAG RATHOD,AKSHAY SHAH,TEST RASHMIKANT
         * creatd_by : rashmikantm
         */

        private int SrNo;
        private int id;
        private String main_coordinatior;
        private String committee_name;
        private String cmtm_member_name;
        private String creatd_by;

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

        public String getCmtm_member_name() {
            return cmtm_member_name;
        }

        public void setCmtm_member_name(String cmtm_member_name) {
            this.cmtm_member_name = cmtm_member_name;
        }

        public String getCreatd_by() {
            return creatd_by;
        }

        public void setCreatd_by(String creatd_by) {
            this.creatd_by = creatd_by;
        }
    }
}
