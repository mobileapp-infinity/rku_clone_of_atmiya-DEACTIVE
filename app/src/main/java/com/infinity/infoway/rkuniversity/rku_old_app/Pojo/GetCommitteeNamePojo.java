package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetCommitteeNamePojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 7
         * committee_name :  ITS(ERP,LMS & Website)
         */

        private int id;
        private String committee_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCommittee_name() {
            return committee_name;
        }

        public void setCommittee_name(String committee_name) {
            this.committee_name = committee_name;
        }
    }
}
