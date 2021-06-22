package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class PDStatisticsReportForStaffPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * emp_name : 13 - ASHISH TANNA
         * Teaching_Learning : 0.0
         * Domain_Specific : 0.0
         * Research_Method : 0.0
         * Soft_skills_Leadership : 5.0
         * Utilized_Budget : 10500.0
         */

        private String emp_name;
        private double Teaching_Learning;
        private double Domain_Specific;
        private double Research_Method;
        private double Soft_skills_Leadership;
        private double Utilized_Budget;

        public String getEmp_name() {
            return emp_name;
        }

        public void setEmp_name(String emp_name) {
            this.emp_name = emp_name;
        }

        public double getTeaching_Learning() {
            return Teaching_Learning;
        }

        public void setTeaching_Learning(double Teaching_Learning) {
            this.Teaching_Learning = Teaching_Learning;
        }

        public double getDomain_Specific() {
            return Domain_Specific;
        }

        public void setDomain_Specific(double Domain_Specific) {
            this.Domain_Specific = Domain_Specific;
        }

        public double getResearch_Method() {
            return Research_Method;
        }

        public void setResearch_Method(double Research_Method) {
            this.Research_Method = Research_Method;
        }

        public double getSoft_skills_Leadership() {
            return Soft_skills_Leadership;
        }

        public void setSoft_skills_Leadership(double Soft_skills_Leadership) {
            this.Soft_skills_Leadership = Soft_skills_Leadership;
        }

        public double getUtilized_Budget() {
            return Utilized_Budget;
        }

        public void setUtilized_Budget(double Utilized_Budget) {
            this.Utilized_Budget = Utilized_Budget;
        }
    }
}
