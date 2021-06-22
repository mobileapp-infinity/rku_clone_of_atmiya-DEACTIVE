package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_adviser_remarks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FacultyAdviserRemarksListPojo implements Serializable {

    @SerializedName("fa_srno")
    @Expose
    private Integer faSrno;
    @SerializedName("fa_college_id")
    @Expose
    private Integer faCollegeId;
    @SerializedName("fa_dept_id")
    @Expose
    private Integer faDeptId;
    @SerializedName("fa_course_id")
    @Expose
    private Integer faCourseId;
    @SerializedName("fa_sem_id")
    @Expose
    private Integer faSemId;
    @SerializedName("fa_div_id")
    @Expose
    private Integer faDivId;
    @SerializedName("fa_batch_id")
    @Expose
    private Integer faBatchId;
    @SerializedName("fa_dept_name")
    @Expose
    private String faDeptName;
    @SerializedName("fa_course_name")
    @Expose
    private String faCourseName;
    @SerializedName("fa_sem_name")
    @Expose
    private String faSemName;
    @SerializedName("fa_divison_name")
    @Expose
    private String faDivisonName;
    @SerializedName("fa_batch_name")
    @Expose
    private String faBatchName;
    @SerializedName("fa_detail_array")
    @Expose
    private List<FaDetailArray> faDetailArray = null;

    public Integer getFaSrno() {
        return faSrno;
    }

    public void setFaSrno(Integer faSrno) {
        this.faSrno = faSrno;
    }

    public Integer getFaCollegeId() {
        return faCollegeId;
    }

    public void setFaCollegeId(Integer faCollegeId) {
        this.faCollegeId = faCollegeId;
    }

    public Integer getFaDeptId() {
        return faDeptId;
    }

    public void setFaDeptId(Integer faDeptId) {
        this.faDeptId = faDeptId;
    }

    public Integer getFaCourseId() {
        return faCourseId;
    }

    public void setFaCourseId(Integer faCourseId) {
        this.faCourseId = faCourseId;
    }

    public Integer getFaSemId() {
        return faSemId;
    }

    public void setFaSemId(Integer faSemId) {
        this.faSemId = faSemId;
    }

    public Integer getFaDivId() {
        return faDivId;
    }

    public void setFaDivId(Integer faDivId) {
        this.faDivId = faDivId;
    }

    public Integer getFaBatchId() {
        return faBatchId;
    }

    public void setFaBatchId(Integer faBatchId) {
        this.faBatchId = faBatchId;
    }

    public String getFaDeptName() {
        return faDeptName;
    }

    public void setFaDeptName(String faDeptName) {
        this.faDeptName = faDeptName;
    }

    public String getFaCourseName() {
        return faCourseName;
    }

    public void setFaCourseName(String faCourseName) {
        this.faCourseName = faCourseName;
    }

    public String getFaSemName() {
        return faSemName;
    }

    public void setFaSemName(String faSemName) {
        this.faSemName = faSemName;
    }

    public String getFaDivisonName() {
        return faDivisonName;
    }

    public void setFaDivisonName(String faDivisonName) {
        this.faDivisonName = faDivisonName;
    }

    public String getFaBatchName() {
        return faBatchName;
    }

    public void setFaBatchName(String faBatchName) {
        this.faBatchName = faBatchName;
    }

    public List<FaDetailArray> getFaDetailArray() {
        return faDetailArray;
    }

    public void setFaDetailArray(List<FaDetailArray> faDetailArray) {
        this.faDetailArray = faDetailArray;
    }

    public class FaDetailArray implements Serializable {

        @SerializedName("fa_stud_id")
        @Expose
        private Integer faStudId;
        @SerializedName("fa_stud_srno")
        @Expose
        private Integer faStudSrno;
        @SerializedName("fa_stud_stud_id")
        @Expose
        private Integer faStudStudId;
        @SerializedName("fa_stud_name")
        @Expose
        private String faStudName;
        @SerializedName("fa_stud_rollno")
        @Expose
        private String faStudRollno;
        @SerializedName("fa_stud_enrollno")
        @Expose
        private String faStudEnrollno;
        @SerializedName("fa_stud_father_mobile")
        @Expose
        private String faStudFatherMobile;
        @SerializedName("fa_stud_att_perc")
        @Expose
        private String faStudAttPerc;
        @SerializedName("fa_stud_backlogs")
        @Expose
        private String faStudBacklogs;
        @SerializedName("fa_stud_remarks_array")
        @Expose
        private Object faStudRemarksArray;

        public Integer getFaStudId() {
            return faStudId;
        }

        public void setFaStudId(Integer faStudId) {
            this.faStudId = faStudId;
        }

        public Integer getFaStudSrno() {
            return faStudSrno;
        }

        public void setFaStudSrno(Integer faStudSrno) {
            this.faStudSrno = faStudSrno;
        }

        public Integer getFaStudStudId() {
            return faStudStudId;
        }

        public void setFaStudStudId(Integer faStudStudId) {
            this.faStudStudId = faStudStudId;
        }

        public String getFaStudName() {
            return faStudName;
        }

        public void setFaStudName(String faStudName) {
            this.faStudName = faStudName;
        }

        public String getFaStudRollno() {
            return faStudRollno;
        }

        public void setFaStudRollno(String faStudRollno) {
            this.faStudRollno = faStudRollno;
        }

        public String getFaStudEnrollno() {
            return faStudEnrollno;
        }

        public void setFaStudEnrollno(String faStudEnrollno) {
            this.faStudEnrollno = faStudEnrollno;
        }

        public String getFaStudFatherMobile() {
            return faStudFatherMobile;
        }

        public void setFaStudFatherMobile(String faStudFatherMobile) {
            this.faStudFatherMobile = faStudFatherMobile;
        }

        public String getFaStudAttPerc() {
            return faStudAttPerc;
        }

        public void setFaStudAttPerc(String faStudAttPerc) {
            this.faStudAttPerc = faStudAttPerc;
        }

        public String getFaStudBacklogs() {
            return faStudBacklogs;
        }

        public void setFaStudBacklogs(String faStudBacklogs) {
            this.faStudBacklogs = faStudBacklogs;
        }

        public Object getFaStudRemarksArray() {
            return faStudRemarksArray;
        }

        public void setFaStudRemarksArray(Object faStudRemarksArray) {
            this.faStudRemarksArray = faStudRemarksArray;
        }

    }

}
