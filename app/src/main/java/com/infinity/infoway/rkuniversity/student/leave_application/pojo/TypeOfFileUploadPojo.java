package com.infinity.infoway.rkuniversity.student.leave_application.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TypeOfFileUploadPojo {
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

        @SerializedName("lt_is_doc_attachment")
        @Expose
        private Integer ltIsDocAttachment;
        @SerializedName("lt_is_doc_compulsory")
        @Expose
        private Integer ltIsDocCompulsory;

        public Integer getLtIsDocAttachment() {
            return ltIsDocAttachment;
        }

        public void setLtIsDocAttachment(Integer ltIsDocAttachment) {
            this.ltIsDocAttachment = ltIsDocAttachment;
        }

        public Integer getLtIsDocCompulsory() {
            return ltIsDocCompulsory;
        }

        public void setLtIsDocCompulsory(Integer ltIsDocCompulsory) {
            this.ltIsDocCompulsory = ltIsDocCompulsory;
        }

    }

}
