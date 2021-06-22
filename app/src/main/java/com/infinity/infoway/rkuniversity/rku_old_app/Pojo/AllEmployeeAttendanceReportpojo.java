package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class AllEmployeeAttendanceReportpojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * sr_no : 1
         * employee_name : (RK UNIVERSITY)  HR
         * A : 0
         * HA : 0
         * HL : 0
         * L : 0
         * NIL : 27
         * P : 0
         * PT : 0
         * T : 0
         * WO : 4
         */

        private int sr_no;
        private String employee_name;
        private String A;
        private String HA;
        private String HL;
        private String L;
        private String NIL;
        private String P;
        private String PT;
        private String T;
        private String WO;

        public int getSr_no() {
            return sr_no;
        }

        public void setSr_no(int sr_no) {
            this.sr_no = sr_no;
        }

        public String getEmployee_name() {
            return employee_name;
        }

        public void setEmployee_name(String employee_name) {
            this.employee_name = employee_name;
        }

        public String getA() {
            return A;
        }

        public void setA(String A) {
            this.A = A;
        }

        public String getHA() {
            return HA;
        }

        public void setHA(String HA) {
            this.HA = HA;
        }

        public String getHL() {
            return HL;
        }

        public void setHL(String HL) {
            this.HL = HL;
        }

        public String getL() {
            return L;
        }

        public void setL(String L) {
            this.L = L;
        }

        public String getNIL() {
            return NIL;
        }

        public void setNIL(String NIL) {
            this.NIL = NIL;
        }

        public String getP() {
            return P;
        }

        public void setP(String P) {
            this.P = P;
        }

        public String getPT() {
            return PT;
        }

        public void setPT(String PT) {
            this.PT = PT;
        }

        public String getT() {
            return T;
        }

        public void setT(String T) {
            this.T = T;
        }

        public String getWO() {
            return WO;
        }

        public void setWO(String WO) {
            this.WO = WO;
        }
    }
}
