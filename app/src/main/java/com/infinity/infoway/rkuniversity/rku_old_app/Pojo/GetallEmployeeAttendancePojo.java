package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetallEmployeeAttendancePojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * sr_no : 6
         * MonthDate : P
         * employee_name : AFZAL DILAWARBHAI GORI
         * emp_code : 711
         * Present : 0
         * present_char : 25
         */

        private int sr_no;
        private String MonthDate;
        private String employee_name;
        private String emp_code;
        private String Present;
        private String present_char;

        public int getSr_no() {
            return sr_no;
        }

        public void setSr_no(int sr_no) {
            this.sr_no = sr_no;
        }

        public String getMonthDate() {
            return MonthDate;
        }

        public void setMonthDate(String MonthDate) {
            this.MonthDate = MonthDate;
        }

        public String getEmployee_name() {
            return employee_name;
        }

        public void setEmployee_name(String employee_name) {
            this.employee_name = employee_name;
        }

        public String getEmp_code() {
            return emp_code;
        }

        public void setEmp_code(String emp_code) {
            this.emp_code = emp_code;
        }

        public String getPresent() {
            return Present;
        }

        public void setPresent(String Present) {
            this.Present = Present;
        }

        public String getPresent_char() {
            return present_char;
        }

        public void setPresent_char(String present_char) {
            this.present_char = present_char;
        }
    }
}
