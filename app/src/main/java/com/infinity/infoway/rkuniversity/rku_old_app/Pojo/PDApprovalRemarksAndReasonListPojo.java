package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class PDApprovalRemarksAndReasonListPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * pd_final_leave_from_date : 19/10/2019
         * pd_final_to_date : 21/10/2019
         * pd_final_registration_fees : 0.0
         * pd_final_transportation : 2400.0
         * pd_final_accommodation : 4500.0
         * pd_final_leave_expense : 3875.6
         * pd_final_total_expense : 5000.0
         * pdar_approved_by : 860
         * pdar_remarks : She is presenting a research paper which is got selected. Recommended with OD
         * pdar_credit : 16
         * pdar_duty_leaves : 3
         * approve_by : AMIT  SHARMA
         * sanctioned_expense : 6900.0
         */

        private String pd_final_leave_from_date;
        private String pd_final_to_date;
        private double pd_final_registration_fees;
        private double pd_final_transportation;
        private double pd_final_accommodation;
        private double pd_final_leave_expense;
        private double pd_final_total_expense;
        private int pdar_approved_by;
        private String pdar_remarks;
        private String pdar_credit;
        private String pdar_duty_leaves;
        private String approve_by;
        private double sanctioned_expense;

        public String getPd_final_leave_from_date() {
            return pd_final_leave_from_date;
        }

        public void setPd_final_leave_from_date(String pd_final_leave_from_date) {
            this.pd_final_leave_from_date = pd_final_leave_from_date;
        }

        public String getPd_final_to_date() {
            return pd_final_to_date;
        }

        public void setPd_final_to_date(String pd_final_to_date) {
            this.pd_final_to_date = pd_final_to_date;
        }

        public double getPd_final_registration_fees() {
            return pd_final_registration_fees;
        }

        public void setPd_final_registration_fees(double pd_final_registration_fees) {
            this.pd_final_registration_fees = pd_final_registration_fees;
        }

        public double getPd_final_transportation() {
            return pd_final_transportation;
        }

        public void setPd_final_transportation(double pd_final_transportation) {
            this.pd_final_transportation = pd_final_transportation;
        }

        public double getPd_final_accommodation() {
            return pd_final_accommodation;
        }

        public void setPd_final_accommodation(double pd_final_accommodation) {
            this.pd_final_accommodation = pd_final_accommodation;
        }

        public double getPd_final_leave_expense() {
            return pd_final_leave_expense;
        }

        public void setPd_final_leave_expense(double pd_final_leave_expense) {
            this.pd_final_leave_expense = pd_final_leave_expense;
        }

        public double getPd_final_total_expense() {
            return pd_final_total_expense;
        }

        public void setPd_final_total_expense(double pd_final_total_expense) {
            this.pd_final_total_expense = pd_final_total_expense;
        }

        public int getPdar_approved_by() {
            return pdar_approved_by;
        }

        public void setPdar_approved_by(int pdar_approved_by) {
            this.pdar_approved_by = pdar_approved_by;
        }

        public String getPdar_remarks() {
            return pdar_remarks;
        }

        public void setPdar_remarks(String pdar_remarks) {
            this.pdar_remarks = pdar_remarks;
        }

        public String getPdar_credit() {
            return pdar_credit;
        }

        public void setPdar_credit(String pdar_credit) {
            this.pdar_credit = pdar_credit;
        }

        public String getPdar_duty_leaves() {
            return pdar_duty_leaves;
        }

        public void setPdar_duty_leaves(String pdar_duty_leaves) {
            this.pdar_duty_leaves = pdar_duty_leaves;
        }

        public String getApprove_by() {
            return approve_by;
        }

        public void setApprove_by(String approve_by) {
            this.approve_by = approve_by;
        }

        public double getSanctioned_expense() {
            return sanctioned_expense;
        }

        public void setSanctioned_expense(double sanctioned_expense) {
            this.sanctioned_expense = sanctioned_expense;
        }
    }
}
