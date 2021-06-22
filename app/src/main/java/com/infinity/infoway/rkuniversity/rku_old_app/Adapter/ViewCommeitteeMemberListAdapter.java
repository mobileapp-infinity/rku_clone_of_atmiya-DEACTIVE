package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;

import java.util.ArrayList;

public class ViewCommeitteeMemberListAdapter extends RecyclerView.Adapter<ViewCommeitteeMemberListAdapter.MyViewHolder>{

    Context context;
    ArrayList<String> committeeMemberNameArrayList;
    LayoutInflater layoutInflater;

    public ViewCommeitteeMemberListAdapter(Context context,ArrayList<String> committeeMemberNameArrayList){
        this.context = context;
        this.committeeMemberNameArrayList = committeeMemberNameArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewCommeitteeMemberListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.inflater_view_committee_member_list_item,viewGroup,false);
        return new ViewCommeitteeMemberListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCommeitteeMemberListAdapter.MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvCommitteeMemberName.setText(committeeMemberNameArrayList.get(i));

    }

    @Override
    public int getItemCount() {
        return committeeMemberNameArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        CustomTextView tvCommitteeMemberName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCommitteeMemberName = itemView.findViewById(R.id.tvCommitteeMemberName);

        }
    }
}

