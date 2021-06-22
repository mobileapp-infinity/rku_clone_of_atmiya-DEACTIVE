package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetAcademicContributionYearPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * year : 2015
         */

        private int year;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
