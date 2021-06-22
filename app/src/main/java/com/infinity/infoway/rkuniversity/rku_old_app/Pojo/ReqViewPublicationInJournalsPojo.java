package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import android.view.View;

import java.io.Serializable;
import java.util.List;

public class ReqViewPublicationInJournalsPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 10
         * comp_id : 1
         * status : 1
         * create_by : 866
         * create_ip : 117.240.19.146
         * create_dnt : 2019-12-02T14:29:40.15
         * modify_by : 844
         * modify_ip : 117.240.19.146
         * modify_dnt : 2020-01-10T14:32:33.143
         * ipj_year_id : 3
         * ipj_author_no : 3
         * ipj_author_type : Primary
         * ipj_research_paper : [1]	Regioselective Synthetic Optimization of Purine-C6-Hydrazinylidenes using Catalyst Free Green Ch
         * ipj_journal_name : Russian Journal of Organic Chemistry
         * ipj_year : 2019
         * ipj_isbn_issn_number : ISSN 1070-4280
         * ipj_category : A
         * ipj_Journal_level : International
         * ipj_file_upload : KM Kapadiya Russ J org Chem.pdf
         * year_name : 2019-20
         * publication_year : 2019
         * UGC_Category : A
         * FileName : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/JournalsPublication/10/KM Kapadiya Russ J org Chem.pdf
         */

        private int id;
        private int comp_id;
        private int status;
        private int create_by;
        private String create_ip;
        private String create_dnt;
        private int modify_by;
        private String modify_ip;
        private String modify_dnt;
        private int ipj_year_id;
        private int ipj_author_no;
        private String ipj_author_type;
        private String ipj_research_paper;
        private String ipj_journal_name;
        private int ipj_year;
        private String ipj_isbn_issn_number;
        private String ipj_category;
        private String ipj_Journal_level;
        private String ipj_file_upload;
        private String year_name;
        private int publication_year;
        private String UGC_Category;
        private String FileName;

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

        public int getCreate_by() {
            return create_by;
        }

        public void setCreate_by(int create_by) {
            this.create_by = create_by;
        }

        public String getCreate_ip() {
            return create_ip;
        }

        public void setCreate_ip(String create_ip) {
            this.create_ip = create_ip;
        }

        public String getCreate_dnt() {
            return create_dnt;
        }

        public void setCreate_dnt(String create_dnt) {
            this.create_dnt = create_dnt;
        }

        public int getModify_by() {
            return modify_by;
        }

        public void setModify_by(int modify_by) {
            this.modify_by = modify_by;
        }

        public String getModify_ip() {
            return modify_ip;
        }

        public void setModify_ip(String modify_ip) {
            this.modify_ip = modify_ip;
        }

        public String getModify_dnt() {
            return modify_dnt;
        }

        public void setModify_dnt(String modify_dnt) {
            this.modify_dnt = modify_dnt;
        }

        public int getIpj_year_id() {
            return ipj_year_id;
        }

        public void setIpj_year_id(int ipj_year_id) {
            this.ipj_year_id = ipj_year_id;
        }

        public int getIpj_author_no() {
            return ipj_author_no;
        }

        public void setIpj_author_no(int ipj_author_no) {
            this.ipj_author_no = ipj_author_no;
        }

        public String getIpj_author_type() {
            return ipj_author_type;
        }

        public void setIpj_author_type(String ipj_author_type) {
            this.ipj_author_type = ipj_author_type;
        }

        public String getIpj_research_paper() {
            return ipj_research_paper;
        }

        public void setIpj_research_paper(String ipj_research_paper) {
            this.ipj_research_paper = ipj_research_paper;
        }

        public String getIpj_journal_name() {
            return ipj_journal_name;
        }

        public void setIpj_journal_name(String ipj_journal_name) {
            this.ipj_journal_name = ipj_journal_name;
        }

        public int getIpj_year() {
            return ipj_year;
        }

        public void setIpj_year(int ipj_year) {
            this.ipj_year = ipj_year;
        }

        public String getIpj_isbn_issn_number() {
            return ipj_isbn_issn_number;
        }

        public void setIpj_isbn_issn_number(String ipj_isbn_issn_number) {
            this.ipj_isbn_issn_number = ipj_isbn_issn_number;
        }

        public String getIpj_category() {
            return ipj_category;
        }

        public void setIpj_category(String ipj_category) {
            this.ipj_category = ipj_category;
        }

        public String getIpj_Journal_level() {
            return ipj_Journal_level;
        }

        public void setIpj_Journal_level(String ipj_Journal_level) {
            this.ipj_Journal_level = ipj_Journal_level;
        }

        public String getIpj_file_upload() {
            return ipj_file_upload;
        }

        public void setIpj_file_upload(String ipj_file_upload) {
            this.ipj_file_upload = ipj_file_upload;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }

        public int getPublication_year() {
            return publication_year;
        }

        public void setPublication_year(int publication_year) {
            this.publication_year = publication_year;
        }

        public String getUGC_Category() {
            return UGC_Category;
        }

        public void setUGC_Category(String UGC_Category) {
            this.UGC_Category = UGC_Category;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }
    }
}
