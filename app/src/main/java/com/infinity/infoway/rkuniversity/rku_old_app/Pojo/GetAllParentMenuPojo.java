package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetAllParentMenuPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * men_dir : Account
         * men_id : 19
         * men_Item : Accounts
         * men_URL : null
         */

        private String men_dir;
        private int men_id;
        private String men_Item;
        private Object men_URL;

        public String getMen_dir() {
            return men_dir;
        }

        public void setMen_dir(String men_dir) {
            this.men_dir = men_dir;
        }

        public int getMen_id() {
            return men_id;
        }

        public void setMen_id(int men_id) {
            this.men_id = men_id;
        }

        public String getMen_Item() {
            return men_Item;
        }

        public void setMen_Item(String men_Item) {
            this.men_Item = men_Item;
        }

        public Object getMen_URL() {
            return men_URL;
        }

        public void setMen_URL(Object men_URL) {
            this.men_URL = men_URL;
        }
    }
}
