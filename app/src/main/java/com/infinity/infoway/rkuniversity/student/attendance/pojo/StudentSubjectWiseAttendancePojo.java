package com.infinity.infoway.rkuniversity.student.attendance.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StudentSubjectWiseAttendancePojo {
    boolean isExpanded = true;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @SerializedName("sub_id")
    private String sub_id;
    @SerializedName("sub_fullname")
    private String sub_fullname;
    @SerializedName("Day")
    private String day;

    @SerializedName("inout_array1")
    private ArrayList<Data> inout_array1;

    public String getSub_id() {
        return sub_id;
    }


    public String getSub_fullname() {
        return sub_fullname;
    }


    public ArrayList<Data> getInout_array1() {
        return inout_array1;
    }

    public void setInout_Array(ArrayList<Data> Innerdata) {
        this.inout_array1 = inout_array1;

    }


    public class Data {
        @SerializedName("tot_lect")
        private String tot_lect;
        @SerializedName("remaining_lect")
        private String remaining_lect;
        @SerializedName("present_lect")
        private String present_lect;
        @SerializedName("TotalPresent")
        private String TotalPresent;
        @SerializedName("persentage_lect")
        private String persentage_lect;
        @SerializedName("Aggr")
        private String Aggr;


        public String getTot_lect() {
            return tot_lect;
        }

        public String getRemaining_lect() {
            return remaining_lect;
        }

        public String getPresent_lect() {
            return present_lect;
        }

        public String getTotalPresent() {
            return TotalPresent;
        }

        public String getPersentage_lect() {
            return persentage_lect;
        }

        public String getAggr() {
            return Aggr;
        }
    }

}
