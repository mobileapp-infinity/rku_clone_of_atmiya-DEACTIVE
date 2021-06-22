package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class ReqFDPGetEventTypePojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ev_type_name : Conference
         * id : 2
         * ev_is_for_book : 1
         * ev_is_insert_flag : 0
         */

        private String ev_type_name;
        private int id;
        private int ev_is_for_book;
        private int ev_is_insert_flag;

        public String getEv_type_name() {
            return ev_type_name;
        }

        public void setEv_type_name(String ev_type_name) {
            this.ev_type_name = ev_type_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getEv_is_for_book() {
            return ev_is_for_book;
        }

        public void setEv_is_for_book(int ev_is_for_book) {
            this.ev_is_for_book = ev_is_for_book;
        }

        public int getEv_is_insert_flag() {
            return ev_is_insert_flag;
        }

        public void setEv_is_insert_flag(int ev_is_insert_flag) {
            this.ev_is_insert_flag = ev_is_insert_flag;
        }
    }
}
