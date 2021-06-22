package com.infinity.infoway.rkuniversity.faculty.faculty_lecture_plan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FacultyLecturePlanPojo implements Serializable {

    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("Course_Name")
    @Expose
    private String courseName;
    @SerializedName("Semester")
    @Expose
    private String semester;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Lecture_Per_Week")
    @Expose
    private String lecturePerWeek;
    @SerializedName("ref_book_name")
    @Expose
    private String refBookName;
    @SerializedName("faculty_name")
    @Expose
    private Object facultyName;
    @SerializedName("lp_details")
    @Expose
    private List<LpDetail> lpDetails = null;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecturePerWeek() {
        return lecturePerWeek;
    }

    public void setLecturePerWeek(String lecturePerWeek) {
        this.lecturePerWeek = lecturePerWeek;
    }

    public String getRefBookName() {
        return refBookName;
    }

    public void setRefBookName(String refBookName) {
        this.refBookName = refBookName;
    }

    public Object getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(Object facultyName) {
        this.facultyName = facultyName;
    }

    public List<LpDetail> getLpDetails() {
        return lpDetails;
    }

    public void setLpDetails(List<LpDetail> lpDetails) {
        this.lpDetails = lpDetails;
    }

    public class LpDetail implements Serializable {

        @SerializedName("topic_Name")
        @Expose
        private String topicName;
        @SerializedName("lp_sub_topic")
        @Expose
        private List<LpSubTopic> lpSubTopic = null;

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public List<LpSubTopic> getLpSubTopic() {
            return lpSubTopic;
        }

        public void setLpSubTopic(List<LpSubTopic> lpSubTopic) {
            this.lpSubTopic = lpSubTopic;
        }

    }

    public class LpSubTopic implements Serializable {

        @SerializedName("sub_sr_no")
        @Expose
        private Integer subSrNo;
        @SerializedName("sub_topic_Name")
        @Expose
        private String subTopicName;
        @SerializedName("sub_topic_method")
        @Expose
        private String subTopicMethod;
        @SerializedName("sub_topic_aid")
        @Expose
        private String subTopicAid;
        @SerializedName("sub_topic_co")
        @Expose
        private String subTopicCo;
        @SerializedName("sub_topic_op")
        @Expose
        private String subTopicOp;

        public Integer getSubSrNo() {
            return subSrNo;
        }

        public void setSubSrNo(Integer subSrNo) {
            this.subSrNo = subSrNo;
        }

        public String getSubTopicName() {
            return subTopicName;
        }

        public void setSubTopicName(String subTopicName) {
            this.subTopicName = subTopicName;
        }

        public String getSubTopicMethod() {
            return subTopicMethod;
        }

        public void setSubTopicMethod(String subTopicMethod) {
            this.subTopicMethod = subTopicMethod;
        }

        public String getSubTopicAid() {
            return subTopicAid;
        }

        public void setSubTopicAid(String subTopicAid) {
            this.subTopicAid = subTopicAid;
        }

        public String getSubTopicCo() {
            return subTopicCo;
        }

        public void setSubTopicCo(String subTopicCo) {
            this.subTopicCo = subTopicCo;
        }

        public String getSubTopicOp() {
            return subTopicOp;
        }

        public void setSubTopicOp(String subTopicOp) {
            this.subTopicOp = subTopicOp;
        }

    }

}
