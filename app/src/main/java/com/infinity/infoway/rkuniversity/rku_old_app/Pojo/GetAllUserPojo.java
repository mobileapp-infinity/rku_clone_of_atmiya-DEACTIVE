package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class GetAllUserPojo {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * user_name : aarti.joshi@rku.ac.in
         * user_id : 699
         * user_group : Employee
         * comp_id : 1
         */

        private String user_name;
        private int user_id;
        private String user_group;
        private int comp_id;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_group() {
            return user_group;
        }

        public void setUser_group(String user_group) {
            this.user_group = user_group;
        }

        public int getComp_id() {
            return comp_id;
        }

        public void setComp_id(int comp_id) {
            this.comp_id = comp_id;
        }
    }
}
