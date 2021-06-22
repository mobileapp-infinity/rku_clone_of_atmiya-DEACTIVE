package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class SubMenuModel {

    private List<ChildrenBean> Children;

    public List<ChildrenBean> getChildrenForSubMenuModel() {
        return Children;
    }

    public void setChildren(List<ChildrenBean> children) {
        Children = children;
    }

    public static class ChildrenBean{

        private int menu_id;
        private String menu_name;
        private String module_name;
        private int parent_menu_id;
        private String Parent_menu;
        private String sub_menu_id;
        private String Sub_menu_name;
        private String Checked_Parameter_value;



        public String getChecked_Parameter_value() {
            return Checked_Parameter_value;
        }

        public void setChecked_Parameter_value(String checked_Parameter_value) {
            Checked_Parameter_value = checked_Parameter_value;
        }

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

        public String getSub_menu_id() {
            return sub_menu_id;
        }

        public void setSub_menu_id(String sub_menu_id) {
            this.sub_menu_id = sub_menu_id;
        }

        public String getSub_menu_name() {
            return Sub_menu_name;
        }

        public void setSub_menu_name(String sub_menu_name) {
            Sub_menu_name = sub_menu_name;
        }
    }

}
