package com.infinity.infoway.rkuniversity.login.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckVersionApiPojo {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("APK_URL")
    @Expose
    private String aPKURL;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAPKURL() {
        return aPKURL;
    }

    public void setAPKURL(String aPKURL) {
        this.aPKURL = aPKURL;
    }

}
