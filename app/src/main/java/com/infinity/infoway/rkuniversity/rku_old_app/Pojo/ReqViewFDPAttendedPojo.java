package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import android.se.omapi.SEService;

import java.io.Serializable;
import java.util.List;

public class ReqViewFDPAttendedPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable {

        /**
         * id : 24
         * comp_id : 1
         * status : 1
         * fdpa_year : null
         * fdpa_workshop_name : Edpuzzle - Canvas Basics
         * fdpa_year_id : 3
         * fdpa_from_date : 13/11/2019
         * fdpa_to_date : 13/12/2019
         * fdpa_activity_report_link : null
         * fdpa_ipr_cell_date : null
         * fdpa_emp_id : 703
         * fdpa_prt_emp_id : 1003
         * fdpa_event_type : 9
         * fdpa_organized_by : 2
         * fdpa_organizer_name : Edpuzzle Online Course
         * fdpa_organizer_city : Online
         * fdpa_pd_credit : 1
         * fdpa_pd_ev_cat : 1
         * fdpa_emp_id1 : 703
         * year_name : 2019-20
         * ev_type_name : Online Course
         * ev_category_name : Teaching Learning (Pedagogy)
         * organize_by_name : Other
         * FileName : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/FDP/1/24/Document/24_703_certificate_canvas.pdf
         */

        private int id;
        private int comp_id;
        private int status;
        private Object fdpa_year;
        private String fdpa_workshop_name;
        private int fdpa_year_id;
        private String fdpa_from_date;
        private String fdpa_to_date;
        private Object fdpa_activity_report_link;
        private Object fdpa_ipr_cell_date;
        private int fdpa_emp_id;
        private int fdpa_prt_emp_id;
        private int fdpa_event_type;
        private int fdpa_organized_by;
        private String fdpa_organizer_name;
        private String fdpa_organizer_city;
        private String fdpa_pd_credit;
        private int fdpa_pd_ev_cat;
        private int fdpa_emp_id1;
        private String year_name;
        private String ev_type_name;
        private String ev_category_name;
        private String organize_by_name;
        private String FileName;
        private String fdpf_file;

        public String getFdpf_file() {
            return fdpf_file;
        }

        public void setFdpf_file(String fdpf_file) {
            this.fdpf_file = fdpf_file;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getComp_id() {
            return comp_id;
        }

        public void setComp_id(int comp_id) {
            this.comp_id = comp_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getFdpa_year() {
            return fdpa_year;
        }

        public void setFdpa_year(Object fdpa_year) {
            this.fdpa_year = fdpa_year;
        }

        public String getFdpa_workshop_name() {
            return fdpa_workshop_name;
        }

        public void setFdpa_workshop_name(String fdpa_workshop_name) {
            this.fdpa_workshop_name = fdpa_workshop_name;
        }

        public int getFdpa_year_id() {
            return fdpa_year_id;
        }

        public void setFdpa_year_id(int fdpa_year_id) {
            this.fdpa_year_id = fdpa_year_id;
        }

        public String getFdpa_from_date() {
            return fdpa_from_date;
        }

        public void setFdpa_from_date(String fdpa_from_date) {
            this.fdpa_from_date = fdpa_from_date;
        }

        public String getFdpa_to_date() {
            return fdpa_to_date;
        }

        public void setFdpa_to_date(String fdpa_to_date) {
            this.fdpa_to_date = fdpa_to_date;
        }

        public Object getFdpa_activity_report_link() {
            return fdpa_activity_report_link;
        }

        public void setFdpa_activity_report_link(Object fdpa_activity_report_link) {
            this.fdpa_activity_report_link = fdpa_activity_report_link;
        }

        public Object getFdpa_ipr_cell_date() {
            return fdpa_ipr_cell_date;
        }

        public void setFdpa_ipr_cell_date(Object fdpa_ipr_cell_date) {
            this.fdpa_ipr_cell_date = fdpa_ipr_cell_date;
        }

        public int getFdpa_emp_id() {
            return fdpa_emp_id;
        }

        public void setFdpa_emp_id(int fdpa_emp_id) {
            this.fdpa_emp_id = fdpa_emp_id;
        }

        public int getFdpa_prt_emp_id() {
            return fdpa_prt_emp_id;
        }

        public void setFdpa_prt_emp_id(int fdpa_prt_emp_id) {
            this.fdpa_prt_emp_id = fdpa_prt_emp_id;
        }

        public int getFdpa_event_type() {
            return fdpa_event_type;
        }

        public void setFdpa_event_type(int fdpa_event_type) {
            this.fdpa_event_type = fdpa_event_type;
        }

        public int getFdpa_organized_by() {
            return fdpa_organized_by;
        }

        public void setFdpa_organized_by(int fdpa_organized_by) {
            this.fdpa_organized_by = fdpa_organized_by;
        }

        public String getFdpa_organizer_name() {
            return fdpa_organizer_name;
        }

        public void setFdpa_organizer_name(String fdpa_organizer_name) {
            this.fdpa_organizer_name = fdpa_organizer_name;
        }

        public String getFdpa_organizer_city() {
            return fdpa_organizer_city;
        }

        public void setFdpa_organizer_city(String fdpa_organizer_city) {
            this.fdpa_organizer_city = fdpa_organizer_city;
        }

        public String getFdpa_pd_credit() {
            return fdpa_pd_credit;
        }

        public void setFdpa_pd_credit(String fdpa_pd_credit) {
            this.fdpa_pd_credit = fdpa_pd_credit;
        }

        public int getFdpa_pd_ev_cat() {
            return fdpa_pd_ev_cat;
        }

        public void setFdpa_pd_ev_cat(int fdpa_pd_ev_cat) {
            this.fdpa_pd_ev_cat = fdpa_pd_ev_cat;
        }

        public int getFdpa_emp_id1() {
            return fdpa_emp_id1;
        }

        public void setFdpa_emp_id1(int fdpa_emp_id1) {
            this.fdpa_emp_id1 = fdpa_emp_id1;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }

        public String getEv_type_name() {
            return ev_type_name;
        }

        public void setEv_type_name(String ev_type_name) {
            this.ev_type_name = ev_type_name;
        }

        public String getEv_category_name() {
            return ev_category_name;
        }

        public void setEv_category_name(String ev_category_name) {
            this.ev_category_name = ev_category_name;
        }

        public String getOrganize_by_name() {
            return organize_by_name;
        }

        public void setOrganize_by_name(String organize_by_name) {
            this.organize_by_name = organize_by_name;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }
    }
}
