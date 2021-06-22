package com.infinity.infoway.rkuniversity.rku_old_app.models;

public class PublicationInConferenceModel {

    String SrNo;
    String researchPaper;
    String level;
    String conference;
    String date;
    String titleOfProceedings;
    String proceedingsISBN;
    String nameOfPublisher;


    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getResearchPaper() {
        return researchPaper;
    }

    public void setResearchPaper(String researchPaper) {
        this.researchPaper = researchPaper;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitleOfProceedings() {
        return titleOfProceedings;
    }

    public void setTitleOfProceedings(String titleOfProceedings) {
        this.titleOfProceedings = titleOfProceedings;
    }

    public String getProceedingsISBN() {
        return proceedingsISBN;
    }

    public void setProceedingsISBN(String proceedingsISBN) {
        this.proceedingsISBN = proceedingsISBN;
    }

    public String getNameOfPublisher() {
        return nameOfPublisher;
    }

    public void setNameOfPublisher(String nameOfPublisher) {
        this.nameOfPublisher = nameOfPublisher;
    }
}
