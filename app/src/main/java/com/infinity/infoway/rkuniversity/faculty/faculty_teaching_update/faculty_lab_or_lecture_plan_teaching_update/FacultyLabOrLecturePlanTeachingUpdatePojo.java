package com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.faculty_lab_or_lecture_plan_teaching_update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FacultyLabOrLecturePlanTeachingUpdatePojo implements Serializable {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


    @SerializedName("lp_srno")
    @Expose
    private Integer lpSrno;
    @SerializedName("lp_id")
    @Expose
    private Integer lpId;
    @SerializedName("lp_course")
    @Expose
    private String lpCourse;
    @SerializedName("lp_sem")
    @Expose
    private String lpSem;
    @SerializedName("lp_div")
    @Expose
    private String lpDiv;
    @SerializedName("lp_sub")
    @Expose
    private String lpSub;
    @SerializedName("lp_lab_per_week")
    @Expose
    private String lpLabPerWeek;
    @SerializedName("lp_reference")
    @Expose
    private String lpReference;
    @SerializedName("file_detail_array")
    @Expose
    private List<LabOrLectureDetails> labOrLectureDetailsList = null;

    public Integer getLpSrno() {
        return lpSrno;
    }

    public void setLpSrno(Integer lpSrno) {
        this.lpSrno = lpSrno;
    }

    public Integer getLpId() {
        return lpId;
    }

    public void setLpId(Integer lpId) {
        this.lpId = lpId;
    }

    public String getLpCourse() {
        return lpCourse;
    }

    public void setLpCourse(String lpCourse) {
        this.lpCourse = lpCourse;
    }

    public String getLpSem() {
        return lpSem;
    }

    public void setLpSem(String lpSem) {
        this.lpSem = lpSem;
    }

    public String getLpDiv() {
        return lpDiv;
    }

    public void setLpDiv(String lpDiv) {
        this.lpDiv = lpDiv;
    }

    public String getLpSub() {
        return lpSub;
    }

    public void setLpSub(String lpSub) {
        this.lpSub = lpSub;
    }

    public String getLpLabPerWeek() {
        return lpLabPerWeek;
    }

    public void setLpLabPerWeek(String lpLabPerWeek) {
        this.lpLabPerWeek = lpLabPerWeek;
    }

    public String getLpReference() {
        return lpReference;
    }

    public void setLpReference(String lpReference) {
        this.lpReference = lpReference;
    }

    public List<LabOrLectureDetails> getLabOrLectureDetailsList() {
        return labOrLectureDetailsList;
    }

    public void setLabOrLectureDetailsList(List<LabOrLectureDetails> labOrLectureDetailsList) {
        this.labOrLectureDetailsList = labOrLectureDetailsList;
    }


    public class LabOrLectureDetails implements Serializable {

        @SerializedName("lp_id")
        @Expose
        private Integer lpId;
        @SerializedName("lp_srno")
        @Expose
        private Integer lpSrno;
        @SerializedName("lp_unit")
        @Expose
        private String lpUnit;
        @SerializedName("lp_topic")
        @Expose
        private String lpTopic;
        @SerializedName("lp_method")
        @Expose
        private String lpMethod;
        @SerializedName("lp_aid")
        @Expose
        private String lpAid;
        @SerializedName("lp_co")
        @Expose
        private String lpCo;
        @SerializedName("lp_po")
        @Expose
        private String lpPo;
        @SerializedName("lp_online_post")
        @Expose
        private String lpOnlinePost;

        public Integer getLpId() {
            return lpId;
        }

        public void setLpId(Integer lpId) {
            this.lpId = lpId;
        }

        public Integer getLpSrno() {
            return lpSrno;
        }

        public void setLpSrno(Integer lpSrno) {
            this.lpSrno = lpSrno;
        }

        public String getLpUnit() {
            return lpUnit;
        }

        public void setLpUnit(String lpUnit) {
            this.lpUnit = lpUnit;
        }

        public String getLpTopic() {
            return lpTopic;
        }

        public void setLpTopic(String lpTopic) {
            this.lpTopic = lpTopic;
        }

        public String getLpMethod() {
            return lpMethod;
        }

        public void setLpMethod(String lpMethod) {
            this.lpMethod = lpMethod;
        }

        public String getLpAid() {
            return lpAid;
        }

        public void setLpAid(String lpAid) {
            this.lpAid = lpAid;
        }

        public String getLpCo() {
            return lpCo;
        }

        public void setLpCo(String lpCo) {
            this.lpCo = lpCo;
        }

        public String getLpPo() {
            return lpPo;
        }

        public void setLpPo(String lpPo) {
            this.lpPo = lpPo;
        }

        public String getLpOnlinePost() {
            return lpOnlinePost;
        }

        public void setLpOnlinePost(String lpOnlinePost) {
            this.lpOnlinePost = lpOnlinePost;
        }

    }


}
