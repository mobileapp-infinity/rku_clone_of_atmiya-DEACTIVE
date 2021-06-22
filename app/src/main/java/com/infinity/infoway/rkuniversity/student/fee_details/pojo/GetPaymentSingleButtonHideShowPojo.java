package com.infinity.infoway.rkuniversity.student.fee_details.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaymentSingleButtonHideShowPojo {

    @SerializedName("HDFC")
    @Expose
    private String hDFC;
    @SerializedName("AXIS")
    @Expose
    private String aXIS;
    @SerializedName("PAYTM")
    @Expose
    private String pAYTM;

    public String getHDFC() {
        return hDFC;
    }

    public void setHDFC(String hDFC) {
        this.hDFC = hDFC;
    }

    public String getAXIS() {
        return aXIS;
    }

    public void setAXIS(String aXIS) {
        this.aXIS = aXIS;
    }

    public String getPAYTM() {
        return pAYTM;
    }

    public void setPAYTM(String pAYTM) {
        this.pAYTM = pAYTM;
    }


}
