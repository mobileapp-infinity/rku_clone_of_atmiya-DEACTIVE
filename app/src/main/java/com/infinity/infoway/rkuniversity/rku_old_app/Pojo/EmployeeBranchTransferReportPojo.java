package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class EmployeeBranchTransferReportPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Name_of_Employee : 740 - HIMANI TEST THAKER
         * Effective_Date : 29/04/2020
         * OLD_Payroll_Branch : School of Pharmacy
         * NEW_Payroll_Branch : School of Physiotherapy
         * Enter_Date : 29/04/2020
         */

        private String Name_of_Employee;
        private String Effective_Date;
        private String OLD_Payroll_Branch;
        private String NEW_Payroll_Branch;
        private String Enter_Date;

        public String getName_of_Employee() {
            return Name_of_Employee;
        }

        public void setName_of_Employee(String Name_of_Employee) {
            this.Name_of_Employee = Name_of_Employee;
        }

        public String getEffective_Date() {
            return Effective_Date;
        }

        public void setEffective_Date(String Effective_Date) {
            this.Effective_Date = Effective_Date;
        }

        public String getOLD_Payroll_Branch() {
            return OLD_Payroll_Branch;
        }

        public void setOLD_Payroll_Branch(String OLD_Payroll_Branch) {
            this.OLD_Payroll_Branch = OLD_Payroll_Branch;
        }

        public String getNEW_Payroll_Branch() {
            return NEW_Payroll_Branch;
        }

        public void setNEW_Payroll_Branch(String NEW_Payroll_Branch) {
            this.NEW_Payroll_Branch = NEW_Payroll_Branch;
        }

        public String getEnter_Date() {
            return Enter_Date;
        }

        public void setEnter_Date(String Enter_Date) {
            this.Enter_Date = Enter_Date;
        }
    }
}
