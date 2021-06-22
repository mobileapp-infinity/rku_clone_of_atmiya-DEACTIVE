package com.infinity.infoway.rkuniversity.rku_old_app.Pojo;

import java.util.List;

public class DocumentPojo {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Title : Document - 1
         * Download_Document : http://rku.ierp.co.in/download_file.aspx?PathToFile=D:\datahost\rku.ierp.co.in\data\app/pd/1/153/Document/153_0_5_Pen Sketch Profile.pdf
         */

        private String Title;
        private String Download_Document;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getDownload_Document() {
            return Download_Document;
        }

        public void setDownload_Document(String Download_Document) {
            this.Download_Document = Download_Document;
        }
    }
}
