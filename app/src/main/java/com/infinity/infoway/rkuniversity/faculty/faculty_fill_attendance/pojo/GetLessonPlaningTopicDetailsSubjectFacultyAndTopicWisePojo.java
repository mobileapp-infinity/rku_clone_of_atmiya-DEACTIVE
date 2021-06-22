package com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLessonPlaningTopicDetailsSubjectFacultyAndTopicWisePojo {

    @SerializedName("Table")
    @Expose
    private List<Table> table = null;

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public class Table {

        @SerializedName("unit_id")
        @Expose
        private String unitId;
        @SerializedName("unit_name")
        @Expose
        private String unitName;
        @SerializedName("topic_id")
        @Expose
        private String topicId;
        @SerializedName("topic_name")
        @Expose
        private String topicName;
        @SerializedName("topic_name1")
        @Expose
        private String topicName1;
        @SerializedName("topic_method")
        @Expose
        private String topicMethod;
        @SerializedName("topic_aid")
        @Expose
        private String topicAid;

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getTopicName1() {
            return topicName1;
        }

        public void setTopicName1(String topicName1) {
            this.topicName1 = topicName1;
        }

        public String getTopicMethod() {
            return topicMethod;
        }

        public void setTopicMethod(String topicMethod) {
            this.topicMethod = topicMethod;
        }

        public String getTopicAid() {
            return topicAid;
        }

        public void setTopicAid(String topicAid) {
            this.topicAid = topicAid;
        }

    }


}
