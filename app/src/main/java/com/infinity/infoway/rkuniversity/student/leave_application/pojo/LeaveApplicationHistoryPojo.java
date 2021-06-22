package com.infinity.infoway.rkuniversity.student.leave_application.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaveApplicationHistoryPojo {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        @SerializedName("sl_id")
        @Expose
        private String slId;
        @SerializedName("sl_stud_id")
        @Expose
        private String slStudId;
        @SerializedName("leave_type")
        @Expose
        private String leaveType;
        @SerializedName("leave_date")
        @Expose
        private String leaveDate;
        @SerializedName("leacture_no")
        @Expose
        private String leactureNo;
        @SerializedName("app_status")
        @Expose
        private String appStatus;
        @SerializedName("app_status_text")
        @Expose
        private String appStatusText;
        @SerializedName("document")
        @Expose
        private String document;
        @SerializedName("stud_remarks")
        @Expose
        private String studRemarks;
        @SerializedName("is_image")
        @Expose
        private String isImage;
        @SerializedName("leave_apply_date")
        @Expose
        private String leave_apply_date;

        public String getLeave_apply_date() {
            return leave_apply_date;
        }

        public void setLeave_apply_date(String leave_apply_date) {
            this.leave_apply_date = leave_apply_date;
        }

        public String getSlId() {
            return slId;
        }

        public void setSlId(String slId) {
            this.slId = slId;
        }

        public String getSlStudId() {
            return slStudId;
        }

        public void setSlStudId(String slStudId) {
            this.slStudId = slStudId;
        }

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        public String getLeaveDate() {
            return leaveDate;
        }

        public void setLeaveDate(String leaveDate) {
            this.leaveDate = leaveDate;
        }

        public String getLeactureNo() {
            return leactureNo;
        }

        public void setLeactureNo(String leactureNo) {
            this.leactureNo = leactureNo;
        }

        public String getAppStatus() {
            return appStatus;
        }

        public void setAppStatus(String appStatus) {
            this.appStatus = appStatus;
        }

        public String getAppStatusText() {
            return appStatusText;
        }

        public void setAppStatusText(String appStatusText) {
            this.appStatusText = appStatusText;
        }

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public String getStudRemarks() {
            return studRemarks;
        }

        public void setStudRemarks(String studRemarks) {
            this.studRemarks = studRemarks;
        }

        public String getIsImage() {
            return isImage;
        }

        public void setIsImage(String isImage) {
            this.isImage = isImage;
        }
    }


}
