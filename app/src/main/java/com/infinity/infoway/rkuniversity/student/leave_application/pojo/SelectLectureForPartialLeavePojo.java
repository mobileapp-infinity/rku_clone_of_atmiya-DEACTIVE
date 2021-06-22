package com.infinity.infoway.rkuniversity.student.leave_application.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectLectureForPartialLeavePojo {

    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {

        private boolean isSelected = false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        @SerializedName("srno")
        @Expose
        private String srno;
        @SerializedName("DL_ID")
        @Expose
        private String dLID;
        @SerializedName("dm_id")
        @Expose
        private String dmId;
        @SerializedName("dm_full_name")
        @Expose
        private String dmFullName;
        @SerializedName("course_id")
        @Expose
        private String courseId;
        @SerializedName("course_fullname")
        @Expose
        private String courseFullname;
        @SerializedName("sm_id")
        @Expose
        private String smId;
        @SerializedName("sm_name")
        @Expose
        private String smName;
        @SerializedName("dvm_id")
        @Expose
        private String dvmId;
        @SerializedName("dvm_name")
        @Expose
        private String dvmName;
        @SerializedName("emp_id")
        @Expose
        private String empId;
        @SerializedName("emp_name")
        @Expose
        private String empName;
        @SerializedName("dl_date")
        @Expose
        private String dlDate;
        @SerializedName("DL_LEC_NO")
        @Expose
        private String dLLECNO;
        @SerializedName("DL_LEC_STATUS")
        @Expose
        private String dLLECSTATUS;

        public String getSrno() {
            return srno;
        }

        public void setSrno(String srno) {
            this.srno = srno;
        }

        public String getDLID() {
            return dLID;
        }

        public void setDLID(String dLID) {
            this.dLID = dLID;
        }

        public String getDmId() {
            return dmId;
        }

        public void setDmId(String dmId) {
            this.dmId = dmId;
        }

        public String getDmFullName() {
            return dmFullName;
        }

        public void setDmFullName(String dmFullName) {
            this.dmFullName = dmFullName;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseFullname() {
            return courseFullname;
        }

        public void setCourseFullname(String courseFullname) {
            this.courseFullname = courseFullname;
        }

        public String getSmId() {
            return smId;
        }

        public void setSmId(String smId) {
            this.smId = smId;
        }

        public String getSmName() {
            return smName;
        }

        public void setSmName(String smName) {
            this.smName = smName;
        }

        public String getDvmId() {
            return dvmId;
        }

        public void setDvmId(String dvmId) {
            this.dvmId = dvmId;
        }

        public String getDvmName() {
            return dvmName;
        }

        public void setDvmName(String dvmName) {
            this.dvmName = dvmName;
        }

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getDlDate() {
            return dlDate;
        }

        public void setDlDate(String dlDate) {
            this.dlDate = dlDate;
        }

        public String getDLLECNO() {
            return dLLECNO;
        }

        public void setDLLECNO(String dLLECNO) {
            this.dLLECNO = dLLECNO;
        }

        public String getDLLECSTATUS() {
            return dLLECSTATUS;
        }

        public void setDLLECSTATUS(String dLLECSTATUS) {
            this.dLLECSTATUS = dLLECSTATUS;
        }
    }


}
