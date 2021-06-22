package com.infinity.infoway.rkuniversity.student.message_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageHistoryListPojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("sm_name")
    @Expose
    private String smName;
    @SerializedName("dvm_name")
    @Expose
    private String dvmName;
    @SerializedName("sms_sent_date")
    @Expose
    private String smsSentDate;
    @SerializedName("sms_mob")
    @Expose
    private String smsMob;
    @SerializedName("sms_msg")
    @Expose
    private String smsMsg;

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

    public String getSmsSentDate() {
        return smsSentDate;
    }

    public void setSmsSentDate(String smsSentDate) {
        this.smsSentDate = smsSentDate;
    }

    public String getSmsMob() {
        return smsMob;
    }

    public void setSmsMob(String smsMob) {
        this.smsMob = smsMob;
    }

    public String getSmsMsg() {
        return smsMsg;
    }

    public void setSmsMsg(String smsMsg) {
        this.smsMsg = smsMsg;
    }


}
