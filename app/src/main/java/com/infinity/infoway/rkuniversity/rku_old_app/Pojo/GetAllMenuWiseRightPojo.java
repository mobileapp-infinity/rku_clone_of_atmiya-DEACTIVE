package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetAllMenuWiseRightPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * menu_id : 902
         * user_id : 1044
         * uwp_per_id : 1
         * per_name : Insert
         * menu_name : Publication in Conferences
         */

        private int menu_id;
        private int user_id;
        private int uwp_per_id;
        private String per_name;
        private String menu_name;

        public int getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getUwp_per_id() {
            return uwp_per_id;
        }

        public void setUwp_per_id(int uwp_per_id) {
            this.uwp_per_id = uwp_per_id;
        }

        public String getPer_name() {
            return per_name;
        }

        public void setPer_name(String per_name) {
            this.per_name = per_name;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }
    }
}
