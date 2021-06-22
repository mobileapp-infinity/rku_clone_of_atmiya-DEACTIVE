package com.infinity.infoway.rkuniversity.student.fee_details.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeeReceiptPojo {

    boolean isExpanded = false;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("Fee_Receipt_No")
    @Expose
    private String feeReceiptNo;
    @SerializedName("Fee_Receipt_Date")
    @Expose
    private String feeReceiptDate;
    @SerializedName("Fee_Course_Name")
    @Expose
    private String feeCourseName;
    @SerializedName("Fee_Class_Name")
    @Expose
    private String feeClassName;
    @SerializedName("Fee_Stud_Full_Name")
    @Expose
    private String feeStudFullName;
    @SerializedName("Fee_Amt")
    @Expose
    private Integer feeAmt;
    @SerializedName("Fee_Ref_No")
    @Expose
    private String feeRefNo;
    @SerializedName("Fee_Bank_Document_Type")
    @Expose
    private String feeBankDocumentType;
    @SerializedName("Fee_Bank_Name")
    @Expose
    private String feeBankName;

    public String getFeeReceiptNo() {
        return feeReceiptNo;
    }

    public void setFeeReceiptNo(String feeReceiptNo) {
        this.feeReceiptNo = feeReceiptNo;
    }

    public String getFeeReceiptDate() {
        return feeReceiptDate;
    }

    public void setFeeReceiptDate(String feeReceiptDate) {
        this.feeReceiptDate = feeReceiptDate;
    }

    public String getFeeCourseName() {
        return feeCourseName;
    }

    public void setFeeCourseName(String feeCourseName) {
        this.feeCourseName = feeCourseName;
    }

    public String getFeeClassName() {
        return feeClassName;
    }

    public void setFeeClassName(String feeClassName) {
        this.feeClassName = feeClassName;
    }

    public String getFeeStudFullName() {
        return feeStudFullName;
    }

    public void setFeeStudFullName(String feeStudFullName) {
        this.feeStudFullName = feeStudFullName;
    }

    public Integer getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(Integer feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getFeeRefNo() {
        return feeRefNo;
    }

    public void setFeeRefNo(String feeRefNo) {
        this.feeRefNo = feeRefNo;
    }

    public String getFeeBankDocumentType() {
        return feeBankDocumentType;
    }

    public void setFeeBankDocumentType(String feeBankDocumentType) {
        this.feeBankDocumentType = feeBankDocumentType;
    }

    public String getFeeBankName() {
        return feeBankName;
    }

    public void setFeeBankName(String feeBankName) {
        this.feeBankName = feeBankName;
    }
}
