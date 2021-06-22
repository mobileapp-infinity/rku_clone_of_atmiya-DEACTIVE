package com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentDetailsFillAttendancePojo {

    @SerializedName("Table")
    @Expose
    private List<TableBean> tableBean = null;

    public List<TableBean> getTableBean() {
        return tableBean;
    }

    public void setTableBean(List<TableBean> tableBean) {
        this.tableBean = tableBean;
    }

    public class TableBean {

        private boolean isChecked = false;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @SerializedName("sr_no")
        @Expose
        private Integer srNo;
        @SerializedName("Stud_Name")
        @Expose
        private String studName;
        @SerializedName("Stud_id")
        @Expose
        private String studId;
        @SerializedName("swd_batch_id")
        @Expose
        private String swdBatchId;
        @SerializedName("Stud_Enrollment_no")
        @Expose
        private String studEnrollmentNo;
        @SerializedName("swd_roll_no")
        @Expose
        private String swdRollNo;
        @SerializedName("Att_status")
        @Expose
        private String attStatus;
        @SerializedName("Att_status1")
        @Expose
        private String attStatus1;
        @SerializedName("pre_att_status")
        @Expose
        private String preAttStatus;
        @SerializedName("config_pre_att_status")
        @Expose
        private String configPreAttStatus;
        @SerializedName("is_elective")
        @Expose
        private String isElective;

        public Integer getSrNo() {
            return srNo;
        }

        public void setSrNo(Integer srNo) {
            this.srNo = srNo;
        }

        public String getStudName() {
            return studName;
        }

        public void setStudName(String studName) {
            this.studName = studName;
        }

        public String getStudId() {
            return studId;
        }

        public void setStudId(String studId) {
            this.studId = studId;
        }

        public String getSwdBatchId() {
            return swdBatchId;
        }

        public void setSwdBatchId(String swdBatchId) {
            this.swdBatchId = swdBatchId;
        }

        public String getStudEnrollmentNo() {
            return studEnrollmentNo;
        }

        public void setStudEnrollmentNo(String studEnrollmentNo) {
            this.studEnrollmentNo = studEnrollmentNo;
        }

        public String getSwdRollNo() {
            return swdRollNo;
        }

        public void setSwdRollNo(String swdRollNo) {
            this.swdRollNo = swdRollNo;
        }

        public String getAttStatus() {
            return attStatus;
        }

        public void setAttStatus(String attStatus) {
            this.attStatus = attStatus;
        }

        public String getAttStatus1() {
            return attStatus1;
        }

        public void setAttStatus1(String attStatus1) {
            this.attStatus1 = attStatus1;
        }

        public String getPreAttStatus() {
            return preAttStatus;
        }

        public void setPreAttStatus(String preAttStatus) {
            this.preAttStatus = preAttStatus;
        }

        public String getConfigPreAttStatus() {
            return configPreAttStatus;
        }

        public void setConfigPreAttStatus(String configPreAttStatus) {
            this.configPreAttStatus = configPreAttStatus;
        }

        public String getIsElective() {
            return isElective;
        }

        public void setIsElective(String isElective) {
            this.isElective = isElective;
        }

    }

}
