package com.infinity.infoway.rkuniversity.rku_old_app.models;

import java.io.Serializable;

public class UserWiseSubMenuModel implements Serializable {

    int subMenuId;
    String subMenuName;

    public int getSubMenuId() {
        return subMenuId;
    }

    public void setSubMenuId(int subMenuId) {
        this.subMenuId = subMenuId;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }
}
