package com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPendingFeeListFromFeeTypePojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("StudId")
    @Expose
    private String studId;
    @SerializedName("SemId")
    @Expose
    private String semId;
    @SerializedName("SemNo")
    @Expose
    private String semNo;
    @SerializedName("HeadId")
    @Expose
    private String headId;
    @SerializedName("TotalPeningFee")
    @Expose
    private String totalPeningFee;
    @SerializedName("SemName")
    @Expose
    private String semName;
    @SerializedName("Fees")
    @Expose
    private List<Fee> fees = null;

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getSemId() {
        return semId;
    }

    public void setSemId(String semId) {
        this.semId = semId;
    }

    public String getSemNo() {
        return semNo;
    }

    public void setSemNo(String semNo) {
        this.semNo = semNo;
    }

    public String getHeadId() {
        return headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    public String getTotalPeningFee() {
        return totalPeningFee;
    }

    public void setTotalPeningFee(String totalPeningFee) {
        this.totalPeningFee = totalPeningFee;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    public static class Fee {

        double partialAmountForTotal = 0.0;
        boolean isPartialAmountIsCorrect = true;

        public boolean isPartialAmountIsCorrect() {
            return isPartialAmountIsCorrect;
        }

        public void setPartialAmountIsCorrect(boolean partialAmountIsCorrect) {
            isPartialAmountIsCorrect = partialAmountIsCorrect;
        }

        public double getPartialAmountForTotal() {
            return partialAmountForTotal;
        }

        public void setPartialAmountForTotal(double partialAmountForTotal) {
            this.partialAmountForTotal = partialAmountForTotal;
        }

        @SerializedName("FeeId")
        @Expose
        private String feeId;
        @SerializedName("HeadName")
        @Expose
        private String headName;
        @SerializedName("FeeName")
        @Expose
        private String feeName;
        @SerializedName("PendingFee")
        @Expose
        private String pendingFee;
        @SerializedName("IsRebate")
        @Expose
        private String isRebate;
        @SerializedName("MinimumFee")
        @Expose
        private String minimumFee;

        public String getFeeId() {
            return feeId;
        }

        public void setFeeId(String feeId) {
            this.feeId = feeId;
        }

        public String getHeadName() {
            return headName;
        }

        public void setHeadName(String headName) {
            this.headName = headName;
        }

        public String getFeeName() {
            return feeName;
        }

        public void setFeeName(String feeName) {
            this.feeName = feeName;
        }

        public String getPendingFee() {
            return pendingFee;
        }

        public void setPendingFee(String pendingFee) {
            this.pendingFee = pendingFee;
        }

        public String getIsRebate() {
            return isRebate;
        }

        public void setIsRebate(String isRebate) {
            this.isRebate = isRebate;
        }

        public String getMinimumFee() {
            return minimumFee;
        }

        public void setMinimumFee(String minimumFee) {
            this.minimumFee = minimumFee;
        }

    }

}
