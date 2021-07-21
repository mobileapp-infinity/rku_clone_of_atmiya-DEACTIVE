package com.infinity.infoway.rkuniversity.student.fee_details.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeeReceiptPojo {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("sr_no")
    @Expose
    private Integer srNo;
    @SerializedName("swf_id")
    @Expose
    private Integer swfId;
    @SerializedName("swf_bank_id")
    @Expose
    private Integer swfBankId;
    @SerializedName("sm_id")
    @Expose
    private Integer smId;
    @SerializedName("year_id")
    @Expose
    private Integer yearId;
    @SerializedName("ftype_act_head_id")
    @Expose
    private Integer ftypeActHeadId;
    @SerializedName("htype_key_id")
    @Expose
    private Integer htypeKeyId;
    @SerializedName("stud_main_school_id")
    @Expose
    private Integer studMainSchoolId;
    @SerializedName("stud_name")
    @Expose
    private String studName;
    @SerializedName("sem_name")
    @Expose
    private String semName;
    @SerializedName("twf_payment_amount")
    @Expose
    private Integer twfPaymentAmount;
    @SerializedName("fee_pay_type")
    @Expose
    private String feePayType;
    @SerializedName("swf_cheque_no")
    @Expose
    private String swfChequeNo;
    @SerializedName("act_pay_chkno")
    @Expose
    private String actPayChkno;
    @SerializedName("bank_trans")
    @Expose
    private Object bankTrans;
    @SerializedName("swf_cheque_no1")
    @Expose
    private String swfChequeNo1;
    @SerializedName("cheque_collect_date")
    @Expose
    private Object chequeCollectDate;
    @SerializedName("cheque_due_date")
    @Expose
    private Object chequeDueDate;
    @SerializedName("final_payment_date")
    @Expose
    private String finalPaymentDate;
    @SerializedName("cheque_clearance_date")
    @Expose
    private String chequeClearanceDate;
    @SerializedName("pay_status")
    @Expose
    private String payStatus;
    @SerializedName("swf_payment_status")
    @Expose
    private Integer swfPaymentStatus;
    @SerializedName("fee_remarks")
    @Expose
    private String feeRemarks;
    @SerializedName("cancel_cheque_status")
    @Expose
    private Integer cancelChequeStatus;
    @SerializedName("stud_enroll_no")
    @Expose
    private String studEnrollNo;
    @SerializedName("swf_receipt_no")
    @Expose
    private Integer swfReceiptNo;
    @SerializedName("pt_is_verification_required")
    @Expose
    private Integer ptIsVerificationRequired;
    @SerializedName("fee_payment_type")
    @Expose
    private Integer feePaymentType;
    @SerializedName("receipt_name")
    @Expose
    private String receiptName;
    @SerializedName("receipt_base64string")
    @Expose
    private String receiptBase64string;
    @SerializedName("file_status")
    @Expose
    private String fileStatus;
    @SerializedName("f_act_head_name")
    @Expose
    private String fActHeadName;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public Integer getSwfId() {
        return swfId;
    }

    public void setSwfId(Integer swfId) {
        this.swfId = swfId;
    }

    public Integer getSwfBankId() {
        return swfBankId;
    }

    public void setSwfBankId(Integer swfBankId) {
        this.swfBankId = swfBankId;
    }

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Integer getFtypeActHeadId() {
        return ftypeActHeadId;
    }

    public void setFtypeActHeadId(Integer ftypeActHeadId) {
        this.ftypeActHeadId = ftypeActHeadId;
    }

    public Integer getHtypeKeyId() {
        return htypeKeyId;
    }

    public void setHtypeKeyId(Integer htypeKeyId) {
        this.htypeKeyId = htypeKeyId;
    }

    public Integer getStudMainSchoolId() {
        return studMainSchoolId;
    }

    public void setStudMainSchoolId(Integer studMainSchoolId) {
        this.studMainSchoolId = studMainSchoolId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

    public Integer getTwfPaymentAmount() {
        return twfPaymentAmount;
    }

    public void setTwfPaymentAmount(Integer twfPaymentAmount) {
        this.twfPaymentAmount = twfPaymentAmount;
    }

    public String getFeePayType() {
        return feePayType;
    }

    public void setFeePayType(String feePayType) {
        this.feePayType = feePayType;
    }

    public String getSwfChequeNo() {
        return swfChequeNo;
    }

    public void setSwfChequeNo(String swfChequeNo) {
        this.swfChequeNo = swfChequeNo;
    }

    public String getActPayChkno() {
        return actPayChkno;
    }

    public void setActPayChkno(String actPayChkno) {
        this.actPayChkno = actPayChkno;
    }

    public Object getBankTrans() {
        return bankTrans;
    }

    public void setBankTrans(Object bankTrans) {
        this.bankTrans = bankTrans;
    }

    public String getSwfChequeNo1() {
        return swfChequeNo1;
    }

    public void setSwfChequeNo1(String swfChequeNo1) {
        this.swfChequeNo1 = swfChequeNo1;
    }

    public Object getChequeCollectDate() {
        return chequeCollectDate;
    }

    public void setChequeCollectDate(Object chequeCollectDate) {
        this.chequeCollectDate = chequeCollectDate;
    }

    public Object getChequeDueDate() {
        return chequeDueDate;
    }

    public void setChequeDueDate(Object chequeDueDate) {
        this.chequeDueDate = chequeDueDate;
    }

    public String getFinalPaymentDate() {
        return finalPaymentDate;
    }

    public void setFinalPaymentDate(String finalPaymentDate) {
        this.finalPaymentDate = finalPaymentDate;
    }

    public String getChequeClearanceDate() {
        return chequeClearanceDate;
    }

    public void setChequeClearanceDate(String chequeClearanceDate) {
        this.chequeClearanceDate = chequeClearanceDate;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getSwfPaymentStatus() {
        return swfPaymentStatus;
    }

    public void setSwfPaymentStatus(Integer swfPaymentStatus) {
        this.swfPaymentStatus = swfPaymentStatus;
    }

    public String getFeeRemarks() {
        return feeRemarks;
    }

    public void setFeeRemarks(String feeRemarks) {
        this.feeRemarks = feeRemarks;
    }

    public Integer getCancelChequeStatus() {
        return cancelChequeStatus;
    }

    public void setCancelChequeStatus(Integer cancelChequeStatus) {
        this.cancelChequeStatus = cancelChequeStatus;
    }

    public String getStudEnrollNo() {
        return studEnrollNo;
    }

    public void setStudEnrollNo(String studEnrollNo) {
        this.studEnrollNo = studEnrollNo;
    }

    public Integer getSwfReceiptNo() {
        return swfReceiptNo;
    }

    public void setSwfReceiptNo(Integer swfReceiptNo) {
        this.swfReceiptNo = swfReceiptNo;
    }

    public Integer getPtIsVerificationRequired() {
        return ptIsVerificationRequired;
    }

    public void setPtIsVerificationRequired(Integer ptIsVerificationRequired) {
        this.ptIsVerificationRequired = ptIsVerificationRequired;
    }

    public Integer getFeePaymentType() {
        return feePaymentType;
    }

    public void setFeePaymentType(Integer feePaymentType) {
        this.feePaymentType = feePaymentType;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptBase64string() {
        return receiptBase64string;
    }

    public void setReceiptBase64string(String receiptBase64string) {
        this.receiptBase64string = receiptBase64string;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getfActHeadName() {
        return fActHeadName;
    }

    public void setfActHeadName(String fActHeadName) {
        this.fActHeadName = fActHeadName;
    }
}
