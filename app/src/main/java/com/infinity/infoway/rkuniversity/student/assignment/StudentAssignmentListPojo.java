package com.infinity.infoway.rkuniversity.student.assignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentAssignmentListPojo {

    @SerializedName("sm_name")
    @Expose
    private String smName;
    @SerializedName("dvm_name")
    @Expose
    private String dvmName;
    @SerializedName("sub_fullname")
    @Expose
    private String subFullname;
    @SerializedName("PDF_URL")
    @Expose
    private String pDFURL;
    @SerializedName("am_last_seen_date")
    @Expose
    private String amLastSeenDate;
    @SerializedName("am_name")
    @Expose
    private String amName;

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getDvmName() {
        return dvmName;
    }

    public void setDvmName(String dvmName) {
        this.dvmName = dvmName;
    }

    public String getSubFullname() {
        return subFullname;
    }

    public void setSubFullname(String subFullname) {
        this.subFullname = subFullname;
    }

    public String getPDFURL() {
        return pDFURL;
    }

    public void setPDFURL(String pDFURL) {
        this.pDFURL = pDFURL;
    }

    public String getAmLastSeenDate() {
        return amLastSeenDate;
    }

    public void setAmLastSeenDate(String amLastSeenDate) {
        this.amLastSeenDate = amLastSeenDate;
    }

    public String getAmName() {
        return amName;
    }

    public void setAmName(String amName) {
        this.amName = amName;
    }

}
