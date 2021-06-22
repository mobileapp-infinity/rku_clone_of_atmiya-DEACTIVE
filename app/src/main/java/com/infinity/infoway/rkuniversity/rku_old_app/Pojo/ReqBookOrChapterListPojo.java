package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ReqBookOrChapterListPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * SrNo : 1
         * id : 10
         * approve_status : 1
         * status : Pending
         * create_by : rashmikantm
         * create_date : 2020-03-23T17:23:54.363
         * Academic_Year : 2018-19
         * Title_of_Book_Chapter : test
         * Date_of_Publication : 23/03/2020
         * Name_of_Publisher : test
         * Download_Document : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/BookPublication/BP/10/68461cf3-4ea1-4ac2-ad7c-da147b7604d3.pdf
         * approve_reject_by_user : null
         * approve_rejct_date : null
         */

        private int SrNo;
        private int id;
        private int approve_status;
        private String status;
        private String create_by;
        private String create_date;
        private String Academic_Year;
        private String Title_of_Book_Chapter;
        private String Date_of_Publication;
        private String Name_of_Publisher;
        private String Download_Document;
        private Object approve_reject_by_user;
        private Object approve_rejct_date;

        public int getSrNo() {
            return SrNo;
        }

        public void setSrNo(int SrNo) {
            this.SrNo = SrNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_by() {
            return create_by;
        }

        public void setCreate_by(String create_by) {
            this.create_by = create_by;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getAcademic_Year() {
            return Academic_Year;
        }

        public void setAcademic_Year(String Academic_Year) {
            this.Academic_Year = Academic_Year;
        }

        public String getTitle_of_Book_Chapter() {
            return Title_of_Book_Chapter;
        }

        public void setTitle_of_Book_Chapter(String Title_of_Book_Chapter) {
            this.Title_of_Book_Chapter = Title_of_Book_Chapter;
        }

        public String getDate_of_Publication() {
            return Date_of_Publication;
        }

        public void setDate_of_Publication(String Date_of_Publication) {
            this.Date_of_Publication = Date_of_Publication;
        }

        public String getName_of_Publisher() {
            return Name_of_Publisher;
        }

        public void setName_of_Publisher(String Name_of_Publisher) {
            this.Name_of_Publisher = Name_of_Publisher;
        }

        public String getDownload_Document() {
            return Download_Document;
        }

        public void setDownload_Document(String Download_Document) {
            this.Download_Document = Download_Document;
        }

        public Object getApprove_reject_by_user() {
            return approve_reject_by_user;
        }

        public void setApprove_reject_by_user(Object approve_reject_by_user) {
            this.approve_reject_by_user = approve_reject_by_user;
        }

        public Object getApprove_rejct_date() {
            return approve_rejct_date;
        }

        public void setApprove_rejct_date(Object approve_rejct_date) {
            this.approve_rejct_date = approve_rejct_date;
        }
    }
}
