package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ParentMenuModel {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public static class DataBean{
        private String module_name;
        private int parent_menu_id;
        private String Parent_menu;

        public String getModule_name() {
            return module_name;
        }

        public void setModule_name(String module_name) {
            this.module_name = module_name;
        }

        public int getParent_menu_id() {
            return parent_menu_id;
        }

        public void setParent_menu_id(int parent_menu_id) {
            this.parent_menu_id = parent_menu_id;
        }

        public String getParent_menu() {
            return Parent_menu;
        }

        public void setParent_menu(String parent_menu) {
            Parent_menu = parent_menu;
        }
    }

}
