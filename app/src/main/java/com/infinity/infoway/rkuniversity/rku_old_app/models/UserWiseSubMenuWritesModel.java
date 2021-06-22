package com.infinity.infoway.rkuniversity.rku_old_app.models;

import java.io.Serializable;

public class UserWiseSubMenuWritesModel implements Serializable {


    String writesName;
    boolean isChecked;
    String per_id;
    String menu_user_id;
    String menu_id;
    String Module_id;

    public String getPer_id() {
        return per_id;
    }

    public void setPer_id(String per_id) {
        this.per_id = per_id;
    }

    public String getMenu_user_id() {
        return menu_user_id;
    }

    public void setMenu_user_id(String menu_user_id) {
        this.menu_user_id = menu_user_id;
    }

    public String getModule_id() {
        return Module_id;
    }

    public void setModule_id(String module_id) {
        Module_id = module_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getWritesName() {
        return writesName;
    }

    public void setWritesName(String writesName) {
        this.writesName = writesName;
    }
}
