package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.models.CommitteMemberListModel;

import java.util.ArrayList;

public class CommitteeMemberNameListAdapter extends RecyclerView.Adapter<CommitteeMemberNameListAdapter.MyViewHolder> {

    Context context;
    ArrayList<CommitteMemberListModel> committeMemberListModelArrayList;
    LayoutInflater layoutInflater;
    RemoveCommitteeMember removeCommitteeMember;

    public CommitteeMemberNameListAdapter(Context context, ArrayList<CommitteMemberListModel> committeMemberListModelArrayList) {

        this.context = context;
        this.committeMemberListModelArrayList = committeMemberListModelArrayList;
        layoutInflater = LayoutInflater.from(context);
        removeCommitteeMember = (RemoveCommitteeMember) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.inflater_chip_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvNameOfCommitteeMember.setText(committeMemberListModelArrayList.get(i).getMemberName());

        myViewHolder.ivRemoveCommitteeMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                committeMemberListModelArrayList.remove(i);
                removeCommitteeMember.removeCommitteemember(committeMemberListModelArrayList);
            }
        });

    }

    @Override
    public int getItemCount() {
        return committeMemberListModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvNameOfCommitteeMember;
        AppCompatImageView ivRemoveCommitteeMember;
        LinearLayout ll_chip_background;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameOfCommitteeMember = itemView.findViewById(R.id.tvNameOfCommitteeMember);
            ivRemoveCommitteeMember = itemView.findViewById(R.id.ivRemoveCommitteeMember);
            ll_chip_background = itemView.findViewById(R.id.ll_chip_background);
        }
    }

    public interface RemoveCommitteeMember{
        void removeCommitteemember(ArrayList<CommitteMemberListModel> committeMemberListModelArrayList);
    }

}
