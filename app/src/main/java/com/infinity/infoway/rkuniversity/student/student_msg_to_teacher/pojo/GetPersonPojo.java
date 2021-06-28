package com.infinity.infoway.rkuniversity.student.student_msg_to_teacher.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPersonPojo {

    @SerializedName("emp_id")
    @Expose
    private Integer empId;
    @SerializedName("Person_name")
    @Expose
    private String personName;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

}
