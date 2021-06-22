package com.infinity.infoway.rkuniversity.student.news_or_notification;

import java.util.List;

public class FacultyOrStudentNewsOrNotificationsPojo {

    private List<Data> Table;

    public List<Data> getTable() {
        return Table;
    }

    public void setTable(List<Data> Table) {
        this.Table = Table;
    }

    public static class Data {
        /**
         * nt_srno : 1
         * nt_id : 2
         * nt_date : 20/06/2020
         * nt_for : Staff
         * nt_head : Learning Management For Employee
         * nt_desc : New Testing Notification added.
         * nt_file_path :
         * nt_file :
         * nt_is_notif : 1
         */

        private int nt_srno;
        private int nt_id;
        private String nt_date;
        private String nt_for;
        private String nt_head;
        private String nt_desc;
        private String nt_file_path;
        private String nt_file;
        private int nt_is_notif;
        private int read_unread_status;

        public int getRead_unread_status() {
            return read_unread_status;
        }

        public void setRead_unread_status(int read_unread_status) {
            this.read_unread_status = read_unread_status;
        }

        public int getNt_srno() {
            return nt_srno;
        }

        public void setNt_srno(int nt_srno) {
            this.nt_srno = nt_srno;
        }

        public int getNt_id() {
            return nt_id;
        }

        public void setNt_id(int nt_id) {
            this.nt_id = nt_id;
        }

        public String getNt_date() {
            return nt_date;
        }

        public void setNt_date(String nt_date) {
            this.nt_date = nt_date;
        }

        public String getNt_for() {
            return nt_for;
        }

        public void setNt_for(String nt_for) {
            this.nt_for = nt_for;
        }

        public String getNt_head() {
            return nt_head;
        }

        public void setNt_head(String nt_head) {
            this.nt_head = nt_head;
        }

        public String getNt_desc() {
            return nt_desc;
        }

        public void setNt_desc(String nt_desc) {
            this.nt_desc = nt_desc;
        }

        public String getNt_file_path() {
            return nt_file_path;
        }

        public void setNt_file_path(String nt_file_path) {
            this.nt_file_path = nt_file_path;
        }

        public String getNt_file() {
            return nt_file;
        }

        public void setNt_file(String nt_file) {
            this.nt_file = nt_file;
        }

        public int getNt_is_notif() {
            return nt_is_notif;
        }

        public void setNt_is_notif(int nt_is_notif) {
            this.nt_is_notif = nt_is_notif;
        }
    }

}
