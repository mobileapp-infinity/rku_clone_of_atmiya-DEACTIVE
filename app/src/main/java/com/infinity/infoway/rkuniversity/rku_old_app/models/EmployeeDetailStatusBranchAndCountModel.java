package com.infinity.infoway.rkuniversity.rku_old_app.models;

import java.util.HashMap;

public class EmployeeDetailStatusBranchAndCountModel {

    private String empDetailStatus;
    private HashMap<String,String> hashMapBranchAndCount;


    public String getEmpDetailStatus() {
        return empDetailStatus;
    }

    public void setEmpDetailStatus(String empDetailStatus) {
        this.empDetailStatus = empDetailStatus;
    }

    public HashMap<String, String> getHashMapBranchAndCount() {
        return hashMapBranchAndCount;
    }

    public void setHashMapBranchAndCount(HashMap<String, String> hashMapBranchAndCount) {
        this.hashMapBranchAndCount = hashMapBranchAndCount;
    }
}
