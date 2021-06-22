package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class AcademicContributionReportPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 1
         * Academic_Contribution : Publication in Conferences(National)
         * brm_code : SOE
         * count : 6
         */

        private int id;
        private String Academic_Contribution;
        private String brm_code;
        private int count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAcademic_Contribution() {
            return Academic_Contribution;
        }

        public void setAcademic_Contribution(String Academic_Contribution) {
            this.Academic_Contribution = Academic_Contribution;
        }

        public String getBrm_code() {
            return brm_code;
        }

        public void setBrm_code(String brm_code) {
            this.brm_code = brm_code;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
