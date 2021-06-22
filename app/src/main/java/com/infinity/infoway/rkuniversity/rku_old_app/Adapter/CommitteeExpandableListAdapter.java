package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.CommitteeListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.CommitteeMemberListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;

import java.util.HashMap;
import java.util.List;

public class CommitteeExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public CommitteeExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header_committee_explv, null);
        }

        LinearLayout llCommitteeHeader = convertView.findViewById(R.id.llCommitteeHeader);

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_committee_explv, null);
        }

        LinearLayout llCommitteeItem = convertView.findViewById(R.id.llCommitteeItem);
        CustomTextView tvCommitteeModuleName = convertView.findViewById(R.id.tvCommitteeModuleName);
        tvCommitteeModuleName.setText(childText);


        llCommitteeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (childText.equalsIgnoreCase("Committee")){
                    Intent intent = new Intent(_context, CommitteeListActivity.class);
                    _context.startActivity(intent);
                }else if (childText.equalsIgnoreCase("Committee Member")){
                    Intent intent = new Intent(_context, CommitteeMemberListActivity.class);
                    _context.startActivity(intent);
                }

            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
