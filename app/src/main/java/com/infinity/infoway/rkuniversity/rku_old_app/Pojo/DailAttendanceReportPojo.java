package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class DailAttendanceReportPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Daily_Attendance : Present
         * brm_code : ACH
         * count : 0
         */
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private String Daily_Attendance;
        private String brm_code;
        private int count;

        public String getDaily_Attendance() {
            return Daily_Attendance;
        }

        public void setDaily_Attendance(String Daily_Attendance) {
            this.Daily_Attendance = Daily_Attendance;
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
