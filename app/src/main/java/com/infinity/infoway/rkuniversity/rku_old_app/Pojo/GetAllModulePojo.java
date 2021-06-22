package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetAllModulePojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * men_id : 1
         * men_Item : Config
         * men_URL : null
         * men_dir : null
         * men_parent_id : 0
         * men_link : null
         */

        private int men_id;
        private String men_Item;
        private Object men_URL;
        private Object men_dir;
        private int men_parent_id;
        private Object men_link;

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

        public Object getMen_dir() {
            return men_dir;
        }

        public void setMen_dir(Object men_dir) {
            this.men_dir = men_dir;
        }

        public int getMen_parent_id() {
            return men_parent_id;
        }

        public void setMen_parent_id(int men_parent_id) {
            this.men_parent_id = men_parent_id;
        }

        public Object getMen_link() {
            return men_link;
        }

        public void setMen_link(Object men_link) {
            this.men_link = men_link;
        }
    }
}
