package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class EmployeeLateComingReportPojo implements Serializable{


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * name : 13-ASHISH RAMESHBHAI TANNA
         * total_late_hours : 1:55:40
         */

        private String name;
        private String total_late_hours;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotal_late_hours() {
            return total_late_hours;
        }

        public void setTotal_late_hours(String total_late_hours) {
            this.total_late_hours = total_late_hours;
        }
    }
}
