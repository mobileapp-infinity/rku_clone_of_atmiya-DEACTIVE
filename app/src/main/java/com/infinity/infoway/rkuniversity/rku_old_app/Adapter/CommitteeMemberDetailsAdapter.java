package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetListOfCommitteeWiseMemberDetailsPojo;

import java.util.ArrayList;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class CommitteeMemberDetailsAdapter extends BaseSwipeAdapter {
    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<GetListOfCommitteeWiseMemberDetailsPojo.DataBean> committeeDetailsList;
    Boolean Isc = true;
    RequestQueue queue;

    public CommitteeMemberDetailsAdapter(Context ctx,ArrayList<GetListOfCommitteeWiseMemberDetailsPojo.DataBean> committeeDetailsList, Boolean Isc)
    {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.committeeDetailsList = committeeDetailsList;
        queue = Volley.newRequestQueue(ctx);
    }


    @Override
    public int getCount()
    {
        return committeeDetailsList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_committee_details;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_committee_details_list, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_committee_details);
        swipeLayout.setSwipeEnabled(false);

        CustomTextView tvCommitteeNameDetails,tvCommitteeDetailMainCoOrdinator;

        tvCommitteeNameDetails = convertView.findViewById(R.id.tvCommitteeNameDetails);
        tvCommitteeDetailMainCoOrdinator = convertView.findViewById(R.id.tvCommitteeDetailMainCoOrdinator);

        tvCommitteeNameDetails.setText(String.valueOf(committeeDetailsList.get(position).getCommittee_name()));
        tvCommitteeDetailMainCoOrdinator.setText(String.valueOf(committeeDetailsList.get(position).getMain_coordinatior()));

    }
}
