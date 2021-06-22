package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class GetEmployeeMonthWiseMisPunchReportPojo implements Serializable{


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {
        /**
         * Employee_Name : 100-RAVINDRA RAMESHBHAI DANGAR
         * API : 0
         * AUG : 0
         * DEC : 0
         * FEB : 0
         * JAN : 0
         * JUL : 0
         * JUN : 0
         * MAR : 0
         * MAY : 0
         * NOV : 0
         * OCT : 0
         * SEP : 0
         */

        private String Employee_Name;
        private int API;
        private int AUG;
        private int DEC;
        private int FEB;
        private int JAN;
        private int JUL;
        private int JUN;
        private int MAR;
        private int MAY;
        private int NOV;
        private int OCT;
        private int SEP;

        public String getEmployee_Name() {
            return Employee_Name;
        }

        public void setEmployee_Name(String Employee_Name) {
            this.Employee_Name = Employee_Name;
        }

        public int getAPI() {
            return API;
        }

        public void setAPI(int API) {
            this.API = API;
        }

        public int getAUG() {
            return AUG;
        }

        public void setAUG(int AUG) {
            this.AUG = AUG;
        }

        public int getDEC() {
            return DEC;
        }

        public void setDEC(int DEC) {
            this.DEC = DEC;
        }

        public int getFEB() {
            return FEB;
        }

        public void setFEB(int FEB) {
            this.FEB = FEB;
        }

        public int getJAN() {
            return JAN;
        }

        public void setJAN(int JAN) {
            this.JAN = JAN;
        }

        public int getJUL() {
            return JUL;
        }

        public void setJUL(int JUL) {
            this.JUL = JUL;
        }

        public int getJUN() {
            return JUN;
        }

        public void setJUN(int JUN) {
            this.JUN = JUN;
        }

        public int getMAR() {
            return MAR;
        }

        public void setMAR(int MAR) {
            this.MAR = MAR;
        }

        public int getMAY() {
            return MAY;
        }

        public void setMAY(int MAY) {
            this.MAY = MAY;
        }

        public int getNOV() {
            return NOV;
        }

        public void setNOV(int NOV) {
            this.NOV = NOV;
        }

        public int getOCT() {
            return OCT;
        }

        public void setOCT(int OCT) {
            this.OCT = OCT;
        }

        public int getSEP() {
            return SEP;
        }

        public void setSEP(int SEP) {
            this.SEP = SEP;
        }
    }
}
