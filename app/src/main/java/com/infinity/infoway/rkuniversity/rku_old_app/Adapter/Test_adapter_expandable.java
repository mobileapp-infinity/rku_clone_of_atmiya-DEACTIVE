package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.RequestQueue;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.TestPojo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pragna on 16-4-2019.
 */

public class Test_adapter_expandable extends BaseAdapter {

    ArrayList<String> check_IDS = new ArrayList<>();
    private Context ctx;
    // List<String> AllStatuslist = new ArrayList<>();
    ViewHolder holder = null;
    public FragmentManager f_manager;
    //private LayoutInflater inflater;
    private LayoutInflater inflater;
    SharedPreferences prefs;
    String customerId;
    View view;
    private int selectedPosition = -1;
    public ArrayList<String> selected = new ArrayList<String>();
    //    List<String> customBenifitList;

    RequestQueue queue;
    Activity a;

    List<TestPojo.DataBean> teachingMethodPojo;
    Boolean b;
    TestPojo testPojo;

    public static HashMap<String, String> ID_Check_status = new HashMap<>();
//    public StudentListAdapter(List<String> customBenifitList, Context ctx) {
//        this.ctx = ctx;
//        this.customBenifitList=customBenifitList;
//        inflater = LayoutInflater.from(this.ctx);
//        //selected.addAll(customBenifitList);
//    }


   /* public Test_adapter_expandable(Context ctx, List<TestPojo.DataBean> TESTPOJO_LIST) {

        this.ctx = ctx;
        this.teachingMethodPojo = TESTPOJO_LIST;
//        this.b = b;

        inflater = LayoutInflater.from(this.ctx);


    }*/

    public Test_adapter_expandable(Context ctx, TestPojo testPojo) {

        this.ctx = ctx;
        this.testPojo = testPojo;
//        this.b = b;

        inflater = LayoutInflater.from(this.ctx);


    }

    @Override
    public int getCount() {
        System.out.println("this is count@@@@@@@@@@@@ " + testPojo.getData().size() + "");
        return testPojo.getData().size() ;
    }

    @Override
    public Object getItem(int position) {
        return testPojo.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<String> getSelected() {


        return selected;
    }

    public void clearSelected() {
        selected.clear();
    }


    private class ViewHolder {


        private CustomBoldTextView tv_name;
        private CustomBoldTextView tv_framework_credit, tv_remark, tv_duty_leave;


    }

    public static ArrayList<String> selected_check_IDS = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        view = convertView;
        //  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.test_layout, null);
            holder = new ViewHolder();
            //    holder.itemCheckBox = (CheckBox) view.findViewById(R.id.itemCheckBox);

            holder.tv_name =  view.findViewById(R.id.tv_name);
//            holder.tv_framework_credit =  view.findViewById(R.id.tv_framework_credit);
            holder.tv_remark =  view.findViewById(R.id.tv_remark);
//            holder.tv_duty_leave =  view.findViewById(R.id.tv_duty_leave);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_name.setText(testPojo.getData().get(position).getApprove_by()+ "");
        holder.tv_framework_credit.setText(testPojo.getData().get(position).getPdar_credit() + "");
        holder.tv_remark.setText(testPojo.getData().get(position).getPdar_remarks() + "");
        holder.tv_duty_leave.setText(testPojo.getData().get(position).getPdar_duty_leaves() + "");

/*        holder.tv_name.setText(teachingMethodPojo.get(position).getApprove_by() + "");
        holder.tv_framework_credit.setText(teachingMethodPojo.get(position).getPdar_credit() + "");
        holder.tv_remark.setText(teachingMethodPojo.get(position).getPdar_remarks() + "");
        holder.tv_duty_leave.setText(teachingMethodPojo.get(position).getPdar_duty_leaves() + "");*/

        return view;
    }


}