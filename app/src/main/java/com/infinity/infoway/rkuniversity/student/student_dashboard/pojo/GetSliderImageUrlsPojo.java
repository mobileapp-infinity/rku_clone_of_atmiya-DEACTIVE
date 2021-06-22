package com.infinity.infoway.rkuniversity.student.student_dashboard.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSliderImageUrlsPojo {

    @SerializedName("url")
    @Expose
    private List<String> url = null;

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

}
