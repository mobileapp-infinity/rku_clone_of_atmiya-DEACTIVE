package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class QualificationReportPojo {

    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Qualification : 10th
         * brm_code : SOE
         * count : 1
         */
        private int id;
        private String Qualification;
        private String brm_code;
        private int count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQualification() {
            return Qualification;
        }

        public void setQualification(String Qualification) {
            this.Qualification = Qualification;
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





//    private List<DataBean> Data;
//
//    public List<DataBean> getData() {
//        return Data;
//    }
//
//    public void setData(List<DataBean> Data) {
//        this.Data = Data;
//    }
//
//    public static class DataBean {
//        /**
//         * Qualification : Diploma
//         * SAS : null
//         * SDS : 3
//         * SOE : 4
//         * SOM : null
//         * SOP : null
//         * SOS : 2
//         * SPT : null
//         */
//
//        private String Qualification;
//        private Object SAS;
//        private int SDS;
//        private int SOE;
//        private Object SOM;
//        private Object SOP;
//        private int SOS;
//        private Object SPT;
//
//        public String getQualification() {
//            return Qualification;
//        }
//
//        public void setQualification(String Qualification) {
//            this.Qualification = Qualification;
//        }
//
//        public Object getSAS() {
//            return SAS;
//        }
//
//        public void setSAS(Object SAS) {
//            this.SAS = SAS;
//        }
//
//        public int getSDS() {
//            return SDS;
//        }
//
//        public void setSDS(int SDS) {
//            this.SDS = SDS;
//        }
//
//        public int getSOE() {
//            return SOE;
//        }
//
//        public void setSOE(int SOE) {
//            this.SOE = SOE;
//        }
//
//        public Object getSOM() {
//            return SOM;
//        }
//
//        public void setSOM(Object SOM) {
//            this.SOM = SOM;
//        }
//
//        public Object getSOP() {
//            return SOP;
//        }
//
//        public void setSOP(Object SOP) {
//            this.SOP = SOP;
//        }
//
//        public int getSOS() {
//            return SOS;
//        }
//
//        public void setSOS(int SOS) {
//            this.SOS = SOS;
//        }
//
//        public Object getSPT() {
//            return SPT;
//        }
//
//        public void setSPT(Object SPT) {
//            this.SPT = SPT;
//        }
//    }



}
