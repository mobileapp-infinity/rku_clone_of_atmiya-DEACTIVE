package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import android.app.SearchManager;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class EmployeeLeaveBalanceReportPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * emp_name : TEST MONPARA RASHMIKANT
         * total_cl : 0/0
         * total_ml : 0/0
         * total_el : 0/4
         * total_coff : 0/0
         * total_rh : 0/0
         * total_lwp : 0/0
         * total_lwps : 0.0/0
         * total_svls : 0.0/0
         * total_ods : 0.0/0
         */

        private String emp_name;
        private String total_cl;
        private String total_ml;
        private String total_el;
        private String total_coff;
        private String total_rh;
        private String total_lwp;
        private String total_lwps;
        private String total_svls;
        private String total_ods;

        public String getEmp_name() {
            return emp_name;
        }

        public void setEmp_name(String emp_name) {
            this.emp_name = emp_name;
        }

        public String getTotal_cl() {
            return total_cl;
        }

        public void setTotal_cl(String total_cl) {
            this.total_cl = total_cl;
        }

        public String getTotal_ml() {
            return total_ml;
        }

        public void setTotal_ml(String total_ml) {
            this.total_ml = total_ml;
        }

        public String getTotal_el() {
            return total_el;
        }

        public void setTotal_el(String total_el) {
            this.total_el = total_el;
        }

        public String getTotal_coff() {
            return total_coff;
        }

        public void setTotal_coff(String total_coff) {
            this.total_coff = total_coff;
        }

        public String getTotal_rh() {
            return total_rh;
        }

        public void setTotal_rh(String total_rh) {
            this.total_rh = total_rh;
        }

        public String getTotal_lwp() {
            return total_lwp;
        }

        public void setTotal_lwp(String total_lwp) {
            this.total_lwp = total_lwp;
        }

        public String getTotal_lwps() {
            return total_lwps;
        }

        public void setTotal_lwps(String total_lwps) {
            this.total_lwps = total_lwps;
        }

        public String getTotal_svls() {
            return total_svls;
        }

        public void setTotal_svls(String total_svls) {
            this.total_svls = total_svls;
        }

        public String getTotal_ods() {
            return total_ods;
        }

        public void setTotal_ods(String total_ods) {
            this.total_ods = total_ods;
        }
    }
}
