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
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.DocumentPojo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Pragna on 16-4-2019.
 */

public class Document1_adapter_expandable extends BaseAdapter {

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

    DocumentPojo teachingMethodPojo;
    Boolean b;
    On_Download on_download;
    public static HashMap<String, String> ID_Check_status = new HashMap<>();
//    public StudentListAdapter(List<String> customBenifitList, Context ctx) {
//        this.ctx = ctx;
//        this.customBenifitList=customBenifitList;
//        inflater = LayoutInflater.from(this.ctx);
//        //selected.addAll(customBenifitList);
//    }


    public Document1_adapter_expandable(Context ctx, DocumentPojo TESTPOJO_LIST, On_Download on_download) {

        this.ctx = ctx;
        this.teachingMethodPojo = TESTPOJO_LIST;
//        this.b = b;
this.on_download=on_download;
        inflater = LayoutInflater.from(this.ctx);
    //    storage = new DataStorage("Login_Detail", ctx);

    }

    @Override
    public int getCount() {
        System.out.println("this is count@@@@@@@@@@@@ "+teachingMethodPojo.getData().size()+"");
        return teachingMethodPojo.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return teachingMethodPojo.getData().get(position);
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

        //        CheckBox itemCheckBox;
        // CustomTextView txt_status_show;
        //    CustomTextView txt_status_counter;
        // final View mView;
        DocumentPojo studentsDisplyaFillPojo;
        private CustomTextView tv_name;
        LinearLayout ll_doc;
//        private CustomTextView tv_framework_credit,tv_remark,tv_duty_leave;


    }
    public interface On_Download {
        public void on_download_click(String download_link);
    }
    public static ArrayList<String> selected_check_IDS = new ArrayList<>();

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        view = convertView;
        //  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.doc_download_adapter, null);
            holder = new ViewHolder();
            //    holder.itemCheckBox = (CheckBox) view.findViewById(R.id.itemCheckBox);

            holder.tv_name = (CustomTextView) view.findViewById(R.id.tv_name);
            holder.ll_doc = (LinearLayout) view.findViewById(R.id.ll_doc);



            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_name.setText(teachingMethodPojo.getData().get(position).getTitle() + "");
        holder.ll_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                on_download.on_download_click(teachingMethodPojo.getData().get(position).getDownload_Document()+"");
                //get_qualified_candidates_for_position(dashbordManagerModelList.get(position).getPositionId() + "");
            }
        });

        return view;
    }


}