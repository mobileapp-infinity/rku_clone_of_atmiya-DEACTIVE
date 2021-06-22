package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ViewInvestmentDetailsByIdPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * cover_id : 1
         * investment_name : 8
         * investment_amount : 5000.0
         * Remarks : test
         * ewi_photo : 510dc829-3494-4457-836f-253c3e4a6cbc.pdf
         * ewi_original_photo : kpi_topic_report.pdf
         * financial_Year : 1
         * Visibale_edit_delete_btn : 1
         * Status : Rejected
         * Document_Status : Rejected
         * Filed : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/Investment/File/510dc829-3494-4457-836f-253c3e4a6cbc.pdf
         */

        private int cover_id;
        private int investment_name;
        private double investment_amount;
        private String Remarks;
        private String ewi_photo;
        private String ewi_original_photo;
        private int financial_Year;
        private int Visibale_edit_delete_btn;
        private String Status;
        private String Document_Status;
        private String Filed;

        public int getCover_id() {
            return cover_id;
        }

        public void setCover_id(int cover_id) {
            this.cover_id = cover_id;
        }

        public int getInvestment_name() {
            return investment_name;
        }

        public void setInvestment_name(int investment_name) {
            this.investment_name = investment_name;
        }

        public double getInvestment_amount() {
            return investment_amount;
        }

        public void setInvestment_amount(double investment_amount) {
            this.investment_amount = investment_amount;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public String getEwi_photo() {
            return ewi_photo;
        }

        public void setEwi_photo(String ewi_photo) {
            this.ewi_photo = ewi_photo;
        }

        public String getEwi_original_photo() {
            return ewi_original_photo;
        }

        public void setEwi_original_photo(String ewi_original_photo) {
            this.ewi_original_photo = ewi_original_photo;
        }

        public int getFinancial_Year() {
            return financial_Year;
        }

        public void setFinancial_Year(int financial_Year) {
            this.financial_Year = financial_Year;
        }

        public int getVisibale_edit_delete_btn() {
            return Visibale_edit_delete_btn;
        }

        public void setVisibale_edit_delete_btn(int Visibale_edit_delete_btn) {
            this.Visibale_edit_delete_btn = Visibale_edit_delete_btn;
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

        public String getFiled() {
            return Filed;
        }

        public void setFiled(String Filed) {
            this.Filed = Filed;
        }
    }
}
