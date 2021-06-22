package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ReportListPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * menu_id : 531
         * menu_name :  All Employee Month Wise  Attendance Report
         * men_URL : print_hrhr_all_employee_month_wise_automatic_attendence_report.aspx
         * sub_menu_id : 1,2,3,4,5,6,7
         * Sub_menu_name : Insert, Update, Delete, View, Export, View Log, Deleted View Log
         * Checked_Parameter_value :
         */

        private int menu_id;
        private String menu_name;
        private String men_URL;
        private String sub_menu_id;
        private String Sub_menu_name;
        private String Checked_Parameter_value;

        public int getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }

        public String getMen_URL() {
            return men_URL;
        }

        public void setMen_URL(String men_URL) {
            this.men_URL = men_URL;
        }

        public String getSub_menu_id() {
            return sub_menu_id;
        }

        public void setSub_menu_id(String sub_menu_id) {
            this.sub_menu_id = sub_menu_id;
        }

        public String getSub_menu_name() {
            return Sub_menu_name;
        }

        public void setSub_menu_name(String Sub_menu_name) {
            this.Sub_menu_name = Sub_menu_name;
        }

        public String getChecked_Parameter_value() {
            return Checked_Parameter_value;
        }

        public void setChecked_Parameter_value(String Checked_Parameter_value) {
            this.Checked_Parameter_value = Checked_Parameter_value;
        }
    }
}
