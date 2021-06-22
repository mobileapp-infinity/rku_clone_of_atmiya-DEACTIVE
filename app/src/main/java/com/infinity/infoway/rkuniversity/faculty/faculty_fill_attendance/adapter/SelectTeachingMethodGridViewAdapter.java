package com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.faculty.faculty_fill_attendance.pojo.GetTeachingMethodPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class SelectTeachingMethodGridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayList;
    private LayoutInflater layoutInflater;
    ITeachingMethod iTeachingMethod;

    public SelectTeachingMethodGridViewAdapter(Context context, ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayList) {
        this.context = context;
        this.getTeachingMethodArrayList = getTeachingMethodArrayList;
        iTeachingMethod = (ITeachingMethod) context;
    }

    @Override
    public int getCount() {
        return getTeachingMethodArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return getTeachingMethodArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.inflater_faculty_teaching_method, null);
        }
        AppCompatCheckBox cbSelectTeachingMethod = convertView.findViewById(R.id.cbSelectTeachingMethod);
        if (!CommonUtil.checkIsEmptyOrNullCommon(getTeachingMethodArrayList.get(position).getTmShortName())) {
            cbSelectTeachingMethod.setText("  " + getTeachingMethodArrayList.get(position).getTmShortName());
        }
        cbSelectTeachingMethod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getTeachingMethodArrayList.get(position).setSelected(isChecked);
                iTeachingMethod.onClickTeachingMethod(getTeachingMethodArrayList);
            }
        });

        return convertView;
    }

    public interface ITeachingMethod {
        void onClickTeachingMethod(ArrayList<GetTeachingMethodPojo.Table> getTeachingMethodArrayList);
    }

}
