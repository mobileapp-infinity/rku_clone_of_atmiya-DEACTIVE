package com.infinity.infoway.rkuniversity.rku_old_app.models;

import java.util.HashMap;

public class QualificationNameBranchAndCountModel {

    private String qualificationName;
    private HashMap<String,String> hashMapBranchAndCount;

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public HashMap<String, String> getHashMapBranchAndCount() {
        return hashMapBranchAndCount;
    }

    public void setHashMapBranchAndCount(HashMap<String, String> hashMapBranchAndCount) {
        this.hashMapBranchAndCount = hashMapBranchAndCount;
    }
}
