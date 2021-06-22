package com.infinity.infoway.rkuniversity.rku_old_app.models;

public class ResearchPatentDetailsModel {


    String srNo;
    String PatentName;
    String patentNumber;
    String yearOfAwarded;


    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getPatentName() {
        return PatentName;
    }

    public void setPatentName(String patentName) {
        PatentName = patentName;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    public String getYearOfAwarded() {
        return yearOfAwarded;
    }

    public void setYearOfAwarded(String yearOfAwarded) {
        this.yearOfAwarded = yearOfAwarded;
    }
}
