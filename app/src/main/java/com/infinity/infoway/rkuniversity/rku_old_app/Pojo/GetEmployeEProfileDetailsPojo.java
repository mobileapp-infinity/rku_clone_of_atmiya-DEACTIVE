package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetEmployeEProfileDetailsPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Basic_Detail : [{"employee_name":"TEST MONPARA RASHMIKANT","employee_code":"734","parent_employee":"INDU JAISWAL","designation":"Lecturer.","date_of_join":"01/01/2019","brm_name":"School of Engineering","dep_name":"Computer Application","branch_logo":"D:\\datahost\\rku.ierp.co.in\\data\\app/Branch/Branch_Logo/16_68526d74-69cc-4024-a202-78eb5ba31570.png"}]
         * Exp_Detail : [{"non_rku_teaching_exp":"0 Year 0 Month 0 Days","non_rku_industrial_exp":"0 Year 0 Month 0 Days","non_rku_other_exp":"0 Year 0 Month 0 Days","non_rku_research_exp":"0 Year 0 Month 0 Days","non_rku_administrative_exp":"0 Year 0 Month 0 Days","non_rku_non_teaching_exp":"0 Year 0 Month 0 Days","rku_non_teaching_exp":"0 Year 0 Month 0 Days","rku_teaching_exp":"1 Year 3 Month 12 Days"}]
         * total_experience : "1 Years 3 Months 12 Days"
         * Publication_conf : []
         * Publication_jour : [{"Srno":1,"researce_paper":"aaa","author_type":"Primary","level":"National","journal":"bbb","no_of_authors":5,"year_of_publication":2020,"isbn_issn":"gagga","category_in_ugc":"B"}]
         * book_chapter_publication : [{"Srno":2,"title":"td","date":"01/04/2020","publisher":"Number trdt"},{"Srno":1,"title":"aaab","date":"25/03/2020","publisher":"aaaa"}]
         * research_patent_details : [{"Srno":1,"patent_published_awarded_name":"hh","patent_number":"2","year_of_award":"2020"}]
         * pg_scholars_guided : []
         * phd_scholars_guided : []
         * cpd_applications : []
         * faculty_devlopment_program : []
         * consultancy_details : [{"Srno":1,"consultancy_project_name":"f6z","consulting_sponsoring_agency_with_contanct_details":"7rs","revenue_generated":658.0,"consultancy_status":"Ongoing"}]
         * grant_received : []
         * seed_money : []
         * mis_punch_req : [{"MisPunch_count":0}]
         * early_leave_duration : "00:00:00"
         * late_reported_duration : "00:00:00"
         * leave_balance : []
         * committee_details : []
         * PD_Statistics : [{"srno":1,"category":"Utilized Budget","credit":0.00}]
         */

        private String Basic_Detail;
        private String Exp_Detail;
        private String total_experience;
        private String Publication_conf;
        private String Publication_jour;
        private String book_chapter_publication;
        private String research_patent_details;
        private String pg_scholars_guided;
        private String phd_scholars_guided;
        private String cpd_applications;
        private String faculty_devlopment_program;
        private String consultancy_details;
        private String grant_received;
        private String seed_money;
        private String mis_punch_req;
        private String early_leave_duration;
        private String late_reported_duration;
        private String leave_balance;
        private String committee_details;
        private String PD_Statistics;

        public String getBasic_Detail() {
            return Basic_Detail;
        }

        public void setBasic_Detail(String Basic_Detail) {
            this.Basic_Detail = Basic_Detail;
        }

        public String getExp_Detail() {
            return Exp_Detail;
        }

        public void setExp_Detail(String Exp_Detail) {
            this.Exp_Detail = Exp_Detail;
        }

        public String getTotal_experience() {
            return total_experience;
        }

        public void setTotal_experience(String total_experience) {
            this.total_experience = total_experience;
        }

        public String getPublication_conf() {
            return Publication_conf;
        }

        public void setPublication_conf(String Publication_conf) {
            this.Publication_conf = Publication_conf;
        }

        public String getPublication_jour() {
            return Publication_jour;
        }

        public void setPublication_jour(String Publication_jour) {
            this.Publication_jour = Publication_jour;
        }

        public String getBook_chapter_publication() {
            return book_chapter_publication;
        }

        public void setBook_chapter_publication(String book_chapter_publication) {
            this.book_chapter_publication = book_chapter_publication;
        }

        public String getResearch_patent_details() {
            return research_patent_details;
        }

        public void setResearch_patent_details(String research_patent_details) {
            this.research_patent_details = research_patent_details;
        }

        public String getPg_scholars_guided() {
            return pg_scholars_guided;
        }

        public void setPg_scholars_guided(String pg_scholars_guided) {
            this.pg_scholars_guided = pg_scholars_guided;
        }

        public String getPhd_scholars_guided() {
            return phd_scholars_guided;
        }

        public void setPhd_scholars_guided(String phd_scholars_guided) {
            this.phd_scholars_guided = phd_scholars_guided;
        }

        public String getCpd_applications() {
            return cpd_applications;
        }

        public void setCpd_applications(String cpd_applications) {
            this.cpd_applications = cpd_applications;
        }

        public String getFaculty_devlopment_program() {
            return faculty_devlopment_program;
        }

        public void setFaculty_devlopment_program(String faculty_devlopment_program) {
            this.faculty_devlopment_program = faculty_devlopment_program;
        }

        public String getConsultancy_details() {
            return consultancy_details;
        }

        public void setConsultancy_details(String consultancy_details) {
            this.consultancy_details = consultancy_details;
        }

        public String getGrant_received() {
            return grant_received;
        }

        public void setGrant_received(String grant_received) {
            this.grant_received = grant_received;
        }

        public String getSeed_money() {
            return seed_money;
        }

        public void setSeed_money(String seed_money) {
            this.seed_money = seed_money;
        }

        public String getMis_punch_req() {
            return mis_punch_req;
        }

        public void setMis_punch_req(String mis_punch_req) {
            this.mis_punch_req = mis_punch_req;
        }

        public String getEarly_leave_duration() {
            return early_leave_duration;
        }

        public void setEarly_leave_duration(String early_leave_duration) {
            this.early_leave_duration = early_leave_duration;
        }

        public String getLate_reported_duration() {
            return late_reported_duration;
        }

        public void setLate_reported_duration(String late_reported_duration) {
            this.late_reported_duration = late_reported_duration;
        }

        public String getLeave_balance() {
            return leave_balance;
        }

        public void setLeave_balance(String leave_balance) {
            this.leave_balance = leave_balance;
        }

        public String getCommittee_details() {
            return committee_details;
        }

        public void setCommittee_details(String committee_details) {
            this.committee_details = committee_details;
        }

        public String getPD_Statistics() {
            return PD_Statistics;
        }

        public void setPD_Statistics(String PD_Statistics) {
            this.PD_Statistics = PD_Statistics;
        }
    }
}
