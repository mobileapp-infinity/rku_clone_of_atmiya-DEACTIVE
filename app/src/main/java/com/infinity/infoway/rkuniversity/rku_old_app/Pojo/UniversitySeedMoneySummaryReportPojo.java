package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class UniversitySeedMoneySummaryReportPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * School : SCHOOL OF ENGINEERING
         * Amount : 175.0
         */

        private String School;
        private double Amount;

        public String getSchool() {
            return School;
        }

        public void setSchool(String School) {
            this.School = School;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }
    }
}
