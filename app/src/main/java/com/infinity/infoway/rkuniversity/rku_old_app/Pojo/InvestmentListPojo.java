package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class InvestmentListPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * SrNo : 1
         * id : 300
         * Cover_In : Section 24
         * Investment_Name : Interest paid on Home Loan
         * Financial_Year : FY2019-20
         * Investment_Amount : 5000.0
         * ewi_desc : TTTT
         * ewi_fym_id : 1
         * Status : Pending
         * Document_Status : Pending
         */

        private int SrNo;
        private int id;
        private String Cover_In;
        private String Investment_Name;
        private String Financial_Year;
        private double Investment_Amount;
        private String ewi_desc;
        private int ewi_fym_id;
        private String Status;
        private String Document_Status;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCover_In() {
            return Cover_In;
        }

        public void setCover_In(String Cover_In) {
            this.Cover_In = Cover_In;
        }

        public String getInvestment_Name() {
            return Investment_Name;
        }

        public void setInvestment_Name(String Investment_Name) {
            this.Investment_Name = Investment_Name;
        }

        public String getFinancial_Year() {
            return Financial_Year;
        }

        public void setFinancial_Year(String Financial_Year) {
            this.Financial_Year = Financial_Year;
        }

        public double getInvestment_Amount() {
            return Investment_Amount;
        }

        public void setInvestment_Amount(double Investment_Amount) {
            this.Investment_Amount = Investment_Amount;
        }

        public String getEwi_desc() {
            return ewi_desc;
        }

        public void setEwi_desc(String ewi_desc) {
            this.ewi_desc = ewi_desc;
        }

        public int getEwi_fym_id() {
            return ewi_fym_id;
        }

        public void setEwi_fym_id(int ewi_fym_id) {
            this.ewi_fym_id = ewi_fym_id;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getDocument_Status() {
            return Document_Status;
        }

        public void setDocument_Status(String Document_Status) {
            this.Document_Status = Document_Status;
        }
    }
}
