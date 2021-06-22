package com.infinity.infoway.rkuniversity.event_calender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventModel {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("array")
    @Expose
    private List<Array> array = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Array> getArray() {
        return array;
    }

    public void setArray(List<Array> array) {
        this.array = array;
    }

    public class Array {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("end_date")
        @Expose
        private String endDate;
        @SerializedName("detailes")
        @Expose
        private String detailes;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getDetailes() {
            return detailes;
        }

        public void setDetailes(String detailes) {
            this.detailes = detailes;
        }

    }

}
