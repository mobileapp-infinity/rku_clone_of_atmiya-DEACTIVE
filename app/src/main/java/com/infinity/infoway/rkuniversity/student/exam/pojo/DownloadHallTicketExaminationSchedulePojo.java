package com.infinity.infoway.rkuniversity.student.exam.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownloadHallTicketExaminationSchedulePojo {

    @SerializedName("Filename")
    @Expose
    private String filename;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("bytearr")
    @Expose
    private Object bytearr;
    @SerializedName("base64string")
    @Expose
    private String base64string;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getBytearr() {
        return bytearr;
    }

    public void setBytearr(Object bytearr) {
        this.bytearr = bytearr;
    }

    public String getBase64string() {
        return base64string;
    }

    public void setBase64string(String base64string) {
        this.base64string = base64string;
    }

}
