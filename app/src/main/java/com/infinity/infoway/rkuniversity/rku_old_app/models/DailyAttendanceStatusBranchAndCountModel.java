package com.infinity.infoway.rkuniversity.rku_old_app.models;

import java.util.HashMap;

public class DailyAttendanceStatusBranchAndCountModel {

    private String attendanceStatus;
    private HashMap<String,String> hashMapBranchAndCount;

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public HashMap<String, String> getHashMapBranchAndCount() {
        return hashMapBranchAndCount;
    }

    public void setHashMapBranchAndCount(HashMap<String, String> hashMapBranchAndCount) {
        this.hashMapBranchAndCount = hashMapBranchAndCount;
    }
}
