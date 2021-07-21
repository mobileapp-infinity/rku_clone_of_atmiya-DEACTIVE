package com.infinity.infoway.rkuniversity.student.contact_us;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUsPojo {

    @SerializedName("principal_detail")
    @Expose
    private String principalDetail;
    @SerializedName("hod_detail")
    @Expose
    private String hodDetail;
    @SerializedName("Faculty_detail")
    @Expose
    private String facultyDetail;
    @SerializedName("college_detail")
    @Expose
    private String collegeDetail;

    public String getPrincipalDetail() {
        return principalDetail;
    }

    public void setPrincipalDetail(String principalDetail) {
        this.principalDetail = principalDetail;
    }

    public String getHodDetail() {
        return hodDetail;
    }

    public void setHodDetail(String hodDetail) {
        this.hodDetail = hodDetail;
    }

    public String getFacultyDetail() {
        return facultyDetail;
    }

    public void setFacultyDetail(String facultyDetail) {
        this.facultyDetail = facultyDetail;
    }

    public String getCollegeDetail() {
        return collegeDetail;
    }

    public void setCollegeDetail(String collegeDetail) {
        this.collegeDetail = collegeDetail;
    }
}
