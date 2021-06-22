package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetAllSubMenuByParentMenuPojo {


    private List<SubMenuBean> SubMenu;

    public List<SubMenuBean> getSubMenu() {
        return SubMenu;
    }

    public void setSubMenu(List<SubMenuBean> SubMenu) {
        this.SubMenu = SubMenu;
    }

    public static class SubMenuBean {

        /**
         * Data : [{"module_name":"purchase","parent_menu_id":141,"Parent_menu":"Weigh Bridge"}]
         * Children : [{"menu_id":140,"menu_name":"Weigh Bridge Waiting","module_name":"purchase","parent_menu_id":141,"Parent_menu":"Weigh Bridge","sub_menu_id":"4","Sub_menu_name":"View"},
         * {"menu_id":142,"menu_name":"Weigh Bridge Out","module_name":"purchase","parent_menu_id":141,"Parent_menu":"Weigh Bridge","sub_menu_id":"1,2,3,4,5,6,7","Sub_menu_name":"Insert, Update, Delete, View, Export, View Log, Deleted View Log"}]
         */

        private String Data;
        private String Children;

        public String getData() {
            return Data;
        }

        public void setData(String Data) {
            this.Data = Data;
        }

        public String getChildren() {
            return Children;
        }

        public void setChildren(String Children) {
            this.Children = Children;
        }
    }
}
