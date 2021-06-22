package com.infinity.infoway.rkuniversity.rku_old_app.models;

import java.util.ArrayList;
import java.util.HashMap;

public class AcademicContributionTypeBranchandCountModel {

    private String acTypeName;
    private HashMap<String,String> hashMapBranchAndCount;


    public String getAcTypeName() {
        return acTypeName;
    }

    public void setAcTypeName(String acTypeName) {
        this.acTypeName = acTypeName;
    }

    public HashMap<String, String> getHashMapBranchAndCount() {
        return hashMapBranchAndCount;
    }

    public void setHashMapBranchAndCount(HashMap<String, String> hashMapBranchAndCount) {
        this.hashMapBranchAndCount = hashMapBranchAndCount;
    }
}
