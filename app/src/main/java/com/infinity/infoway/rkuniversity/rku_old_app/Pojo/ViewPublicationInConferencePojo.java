package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ViewPublicationInConferencePojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 53
         * comp_id : 1
         * status : 1
         * create_by : 1044
         * create_ip : 0
         * create_dnt : 2020-03-17T17:40:17.163
         * modify_by : null
         * modify_ip : null
         * modify_dnt : null
         * ipc_year_id : 1
         * ipc_paper_title : test
         * ipc_conference_title : test
         * ipc_conference_name : test
         * ipc_level : 1
         * ipc_publication_date : 1753-01-01T00:00:00
         * ipc_isbn_issn_number : test
         * ipc_file_upload : 8036160b-ae22-475c-90b9-aef3c0c53a31.pdf
         * ipc_publisher_name : test
         */

        private int id;
        private int comp_id;
        private int status;
        private int create_by;
        private String create_ip;
        private String create_dnt;
        private Object modify_by;
        private Object modify_ip;
        private Object modify_dnt;
        private int ipc_year_id;
        private String ipc_paper_title;
        private String ipc_conference_title;
        private String ipc_conference_name;
        private String ipc_level;
        private String ipc_publication_date;
        private String ipc_isbn_issn_number;
        private String ipc_file_upload;
        private String ipc_publisher_name;
        private String year_name;
        private String Proceedings_Title;
        private String FileName;

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String fileName) {
            FileName = fileName;
        }

        public String getProceedings_Title() {
            return Proceedings_Title;
        }

        public void setProceedings_Title(String proceedings_Title) {
            Proceedings_Title = proceedings_Title;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
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

        public Object getModify_by() {
            return modify_by;
        }

        public void setModify_by(Object modify_by) {
            this.modify_by = modify_by;
        }

        public Object getModify_ip() {
            return modify_ip;
        }

        public void setModify_ip(Object modify_ip) {
            this.modify_ip = modify_ip;
        }

        public Object getModify_dnt() {
            return modify_dnt;
        }

        public void setModify_dnt(Object modify_dnt) {
            this.modify_dnt = modify_dnt;
        }

        public int getIpc_year_id() {
            return ipc_year_id;
        }

        public void setIpc_year_id(int ipc_year_id) {
            this.ipc_year_id = ipc_year_id;
        }

        public String getIpc_paper_title() {
            return ipc_paper_title;
        }

        public void setIpc_paper_title(String ipc_paper_title) {
            this.ipc_paper_title = ipc_paper_title;
        }

        public String getIpc_conference_title() {
            return ipc_conference_title;
        }

        public void setIpc_conference_title(String ipc_conference_title) {
            this.ipc_conference_title = ipc_conference_title;
        }

        public String getIpc_conference_name() {
            return ipc_conference_name;
        }

        public void setIpc_conference_name(String ipc_conference_name) {
            this.ipc_conference_name = ipc_conference_name;
        }

        public String getIpc_level() {
            return ipc_level;
        }

        public void setIpc_level(String ipc_level) {
            this.ipc_level = ipc_level;
        }

        public String getIpc_publication_date() {
            return ipc_publication_date;
        }

        public void setIpc_publication_date(String ipc_publication_date) {
            this.ipc_publication_date = ipc_publication_date;
        }

        public String getIpc_isbn_issn_number() {
            return ipc_isbn_issn_number;
        }

        public void setIpc_isbn_issn_number(String ipc_isbn_issn_number) {
            this.ipc_isbn_issn_number = ipc_isbn_issn_number;
        }

        public String getIpc_file_upload() {
            return ipc_file_upload;
        }

        public void setIpc_file_upload(String ipc_file_upload) {
            this.ipc_file_upload = ipc_file_upload;
        }

        public String getIpc_publisher_name() {
            return ipc_publisher_name;
        }

        public void setIpc_publisher_name(String ipc_publisher_name) {
            this.ipc_publisher_name = ipc_publisher_name;
        }
    }
}
