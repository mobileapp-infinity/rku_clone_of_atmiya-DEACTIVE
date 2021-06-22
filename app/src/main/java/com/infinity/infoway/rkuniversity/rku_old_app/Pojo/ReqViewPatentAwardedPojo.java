package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.io.Serializable;
import java.util.List;

public class ReqViewPatentAwardedPojo implements Serializable {


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
         * create_by : 1142
         * create_ip : 103.36.122.212
         * create_dnt : 2020-01-21T14:39:43.513
         * modify_by : null
         * modify_ip : null
         * modify_dnt : null
         * pat_patent_name : Test
         * pat_patent_number : 123456
         * pat_patent_year : 2020
         * pat_year_id : 4
         * pat_file_name : Beginning Java 8 Fundamentals.pdf ( PDFDrive.com ).pdf
         * year_name : 2020-21
         * FileName : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/Patent/1/Beginning Java 8 Fundamentals.pdf ( PDFDrive.com ).pdf
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
        private String pat_patent_name;
        private String pat_patent_number;
        private String pat_patent_year;
        private int pat_year_id;
        private String pat_file_name;
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

        public String getPat_patent_name() {
            return pat_patent_name;
        }

        public void setPat_patent_name(String pat_patent_name) {
            this.pat_patent_name = pat_patent_name;
        }

        public String getPat_patent_number() {
            return pat_patent_number;
        }

        public void setPat_patent_number(String pat_patent_number) {
            this.pat_patent_number = pat_patent_number;
        }

        public String getPat_patent_year() {
            return pat_patent_year;
        }

        public void setPat_patent_year(String pat_patent_year) {
            this.pat_patent_year = pat_patent_year;
        }

        public int getPat_year_id() {
            return pat_year_id;
        }

        public void setPat_year_id(int pat_year_id) {
            this.pat_year_id = pat_year_id;
        }

        public String getPat_file_name() {
            return pat_file_name;
        }

        public void setPat_file_name(String pat_file_name) {
            this.pat_file_name = pat_file_name;
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
