package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class EmployeePayrollDetailReportPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Name_of_Employee : RAVINDRA RAMESHBHAI DANGAR
         * Scale : 6th Scale
         * Basic : 0.0
         */

        private String Name_of_Employee;
        private String Scale;
        private double Basic;

        public String getName_of_Employee() {
            return Name_of_Employee;
        }

        public void setName_of_Employee(String Name_of_Employee) {
            this.Name_of_Employee = Name_of_Employee;
        }

        public String getScale() {
            return Scale;
        }

        public void setScale(String Scale) {
            this.Scale = Scale;
        }

        public double getBasic() {
            return Basic;
        }

        public void setBasic(double Basic) {
            this.Basic = Basic;
        }
    }
}
