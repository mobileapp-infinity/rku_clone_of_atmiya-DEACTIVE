package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSMSApiForApplicationPojo {


    @SerializedName("sms_id")
    @Expose
    private Integer smsId;
    @SerializedName("sms_main_school_id")
    @Expose
    private Object smsMainSchoolId;
    @SerializedName("sms_api")
    @Expose
    private String smsApi;

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public Object getSmsMainSchoolId() {
        return smsMainSchoolId;
    }

    public void setSmsMainSchoolId(Object smsMainSchoolId) {
        this.smsMainSchoolId = smsMainSchoolId;
    }

    public String getSmsApi() {
        return smsApi;
    }

    public void setSmsApi(String smsApi) {
        this.smsApi = smsApi;
    }



}
