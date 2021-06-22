package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ReqViewBookOrChapterPojo implements Serializable {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * comp_id : 1
         * status : 1
         * create_by : 900
         * create_ip : 175.100.128.2
         * create_dnt : 2020-01-01T14:39:25.743
         * modify_by : 844
         * modify_ip : 117.240.19.146
         * modify_dnt : 2020-01-08T14:39:49.413
         * npc_year_id : 3
         * npc_book_title : Electro Analytical Techniques - Basic theory and Applications
         * npc_publication_date : 2019-12-28T00:00:00
         * npc_publisher_name : Lambert Acedemic Publising
         * npc_file_name : cover_201827.pdf
         * year_name : 2019-20
         * FileName : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/BP/1/cover_201827.pdf
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
        private int npc_year_id;
        private String npc_book_title;
        private String npc_publication_date;
        private String npc_publisher_name;
        private String npc_file_name;
        private String year_name;
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

        public int getNpc_year_id() {
            return npc_year_id;
        }

        public void setNpc_year_id(int npc_year_id) {
            this.npc_year_id = npc_year_id;
        }

        public String getNpc_book_title() {
            return npc_book_title;
        }

        public void setNpc_book_title(String npc_book_title) {
            this.npc_book_title = npc_book_title;
        }

        public String getNpc_publication_date() {
            return npc_publication_date;
        }

        public void setNpc_publication_date(String npc_publication_date) {
            this.npc_publication_date = npc_publication_date;
        }

        public String getNpc_publisher_name() {
            return npc_publisher_name;
        }

        public void setNpc_publisher_name(String npc_publisher_name) {
            this.npc_publisher_name = npc_publisher_name;
        }

        public String getNpc_file_name() {
            return npc_file_name;
        }

        public void setNpc_file_name(String npc_file_name) {
            this.npc_file_name = npc_file_name;
        }

        public String getYear_name() {
            return year_name;
        }

        public void setYear_name(String year_name) {
            this.year_name = year_name;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }
    }
}
