package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class PdStatisticsPojo
{


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * category : Research Method
         * credit : 44
         */

        private String category;
        private String credit;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }
    }
}
