package com.infinity.infoway.rkuniversity.student.fee_details.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllPendingFeeStudentPojo {

    private boolean isFeeItemSelected = false;

    public boolean getIsPendingFeeItemIsCheckOrNot() {
        return isFeeItemSelected;
    }

    public void setPendingFeeTypeToChecked(boolean feeItemSelected) {
        isFeeItemSelected = feeItemSelected;
    }

    @SerializedName("fee_Company_Code")
    @Expose
    private Object feeCompanyCode;
    @SerializedName("fee_Student_ID")
    @Expose
    private Object feeStudentID;
    @SerializedName("fee_Student_Name")
    @Expose
    private Object feeStudentName;
    @SerializedName("fee_Fee_Circular")
    @Expose
    private Object feeFeeCircular;
    @SerializedName("fee_Fee_Date")
    @Expose
    private Object feeFeeDate;
    @SerializedName("fee_Fee_Type")
    @Expose
    private Object feeFeeType;
    @SerializedName("fee_Course_Name")
    @Expose
    private Object feeCourseName;
    @SerializedName("fee_Class_Name")
    @Expose
    private Object feeClassName;
    @SerializedName("fee_Paid_Fee")
    @Expose
    private Object feePaidFee;
    @SerializedName("fee_Total_Fee")
    @Expose
    private Object feeTotalFee;
    @SerializedName("fee_late_Fee")
    @Expose
    private Object feeLateFee;
    @SerializedName("fee_Pending_Fee")
    @Expose
    private Object feePendingFee;
    @SerializedName("fee_Pending_Fee_Refund")
    @Expose
    private Object feePendingFeeRefund;
    @SerializedName("fee_Due_Date")
    @Expose
    private Object feeDueDate;
    @SerializedName("fee_Fee_Circular_ID")
    @Expose
    private Object feeFeeCircularID;
    @SerializedName("fee_ex_id")
    @Expose
    private Object feeExId;
    @SerializedName("fee_year_id")
    @Expose
    private Object feeYearId;
    @SerializedName("fee_ex_name")
    @Expose
    private Object feeExName;
    @SerializedName("fee_ex_start_date")
    @Expose
    private Object feeExStartDate;
    @SerializedName("fee_ex_end_date")
    @Expose
    private Object feeExEndDate;
    @SerializedName("fee_view_file")
    @Expose
    private Object feeViewFile;
    @SerializedName("fee_Pay_status")
    @Expose
    private Object feePayStatus;
    @SerializedName("ErrorId")
    @Expose
    private String errorId;
    @SerializedName("ErrorName")
    @Expose
    private String errorName;
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("FeeStatus")
    @Expose
    private String feeStatus;
    @SerializedName("fee_convocation_id")
    @Expose
    private Integer feeConvocationId;

    public Object getFeeCompanyCode() {
        return feeCompanyCode;
    }

    public void setFeeCompanyCode(Object feeCompanyCode) {
        this.feeCompanyCode = feeCompanyCode;
    }

    public Object getFeeStudentID() {
        return feeStudentID;
    }

    public void setFeeStudentID(Object feeStudentID) {
        this.feeStudentID = feeStudentID;
    }

    public Object getFeeStudentName() {
        return feeStudentName;
    }

    public void setFeeStudentName(Object feeStudentName) {
        this.feeStudentName = feeStudentName;
    }

    public Object getFeeFeeCircular() {
        return feeFeeCircular;
    }

    public void setFeeFeeCircular(Object feeFeeCircular) {
        this.feeFeeCircular = feeFeeCircular;
    }

    public Object getFeeFeeDate() {
        return feeFeeDate;
    }

    public void setFeeFeeDate(Object feeFeeDate) {
        this.feeFeeDate = feeFeeDate;
    }

    public Object getFeeFeeType() {
        return feeFeeType;
    }

    public void setFeeFeeType(Object feeFeeType) {
        this.feeFeeType = feeFeeType;
    }

    public Object getFeeCourseName() {
        return feeCourseName;
    }

    public void setFeeCourseName(Object feeCourseName) {
        this.feeCourseName = feeCourseName;
    }

    public Object getFeeClassName() {
        return feeClassName;
    }

    public void setFeeClassName(Object feeClassName) {
        this.feeClassName = feeClassName;
    }

    public Object getFeePaidFee() {
        return feePaidFee;
    }

    public void setFeePaidFee(Object feePaidFee) {
        this.feePaidFee = feePaidFee;
    }

    public Object getFeeTotalFee() {
        return feeTotalFee;
    }

    public void setFeeTotalFee(Object feeTotalFee) {
        this.feeTotalFee = feeTotalFee;
    }

    public Object getFeeLateFee() {
        return feeLateFee;
    }

    public void setFeeLateFee(Object feeLateFee) {
        this.feeLateFee = feeLateFee;
    }

    public Object getFeePendingFee() {
        return feePendingFee;
    }

    public void setFeePendingFee(Object feePendingFee) {
        this.feePendingFee = feePendingFee;
    }

    public Object getFeePendingFeeRefund() {
        return feePendingFeeRefund;
    }

    public void setFeePendingFeeRefund(Object feePendingFeeRefund) {
        this.feePendingFeeRefund = feePendingFeeRefund;
    }

    public Object getFeeDueDate() {
        return feeDueDate;
    }

    public void setFeeDueDate(Object feeDueDate) {
        this.feeDueDate = feeDueDate;
    }

    public Object getFeeFeeCircularID() {
        return feeFeeCircularID;
    }

    public void setFeeFeeCircularID(Object feeFeeCircularID) {
        this.feeFeeCircularID = feeFeeCircularID;
    }

    public Object getFeeExId() {
        return feeExId;
    }

    public void setFeeExId(Object feeExId) {
        this.feeExId = feeExId;
    }

    public Object getFeeYearId() {
        return feeYearId;
    }

    public void setFeeYearId(Object feeYearId) {
        this.feeYearId = feeYearId;
    }

    public Object getFeeExName() {
        return feeExName;
    }

    public void setFeeExName(Object feeExName) {
        this.feeExName = feeExName;
    }

    public Object getFeeExStartDate() {
        return feeExStartDate;
    }

    public void setFeeExStartDate(Object feeExStartDate) {
        this.feeExStartDate = feeExStartDate;
    }

    public Object getFeeExEndDate() {
        return feeExEndDate;
    }

    public void setFeeExEndDate(Object feeExEndDate) {
        this.feeExEndDate = feeExEndDate;
    }

    public Object getFeeViewFile() {
        return feeViewFile;
    }

    public void setFeeViewFile(Object feeViewFile) {
        this.feeViewFile = feeViewFile;
    }

    public Object getFeePayStatus() {
        return feePayStatus;
    }

    public void setFeePayStatus(Object feePayStatus) {
        this.feePayStatus = feePayStatus;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }

    public Integer getFeeConvocationId() {
        return feeConvocationId;
    }

    public void setFeeConvocationId(Integer feeConvocationId) {
        this.feeConvocationId = feeConvocationId;
    }


}
