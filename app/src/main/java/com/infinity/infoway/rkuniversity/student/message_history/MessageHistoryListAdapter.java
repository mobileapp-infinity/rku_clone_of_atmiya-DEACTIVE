package com.infinity.infoway.rkuniversity.student.message_history;

import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class MessageHistoryListAdapter extends RecyclerView.Adapter<MessageHistoryListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<MessageHistoryListPojo> messageHistoryListPojoArrayList;

    public MessageHistoryListAdapter(Context context, ArrayList<MessageHistoryListPojo> messageHistoryListPojoArrayList) {
        this.context = context;
        this.messageHistoryListPojoArrayList = messageHistoryListPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_message_history_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MessageHistoryListPojo messageHistoryListPojo = messageHistoryListPojoArrayList.get(position);
        if (!CommonUtil.checkIsEmptyOrNullCommon(messageHistoryListPojo.getSmsMob())) {
            holder.tvMessageHistoryMobileNo.setText(messageHistoryListPojo.getSmsMob());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(messageHistoryListPojo.getSmsSentDate())) {
            holder.tvMessageHistoryDate.setText(messageHistoryListPojo.getSmsSentDate());
        }

//        if (!CommonUtil.checkIsEmptyOrNullCommon(messageHistoryListPojo.getSmName())) {
//            String semArray[] = messageHistoryListPojo.getSmName().split("-");
//            holder.tvMessageHistorySem.setText(semArray[1] + " (" + messageHistoryListPojo.getDvmName() + ")");
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(messageHistoryListPojo.getSmsMsg())) {
            holder.tvMessageHistoryNote.setText(messageHistoryListPojo.getSmsMsg());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            makeCustomOutline(holder.cvTest);
        }

//        holder.llExpandedHeaderMessageHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean show = toggleLayout(!messageHistoryListPojoArrayList.get(position).isExpanded(), holder.ivViewMoreBtnMessageHistory, holder.llExpandableLayoutMessageHistory);
//                messageHistoryListPojoArrayList.get(position).setExpanded(show);
//            }
//        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void makeCustomOutline(MaterialCardView card) {

        card.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {

                outline.setRoundRect(-1, -1, view.getWidth(),
                        (view.getHeight() + 7), (float) 7);
            }
        });
        card.setClipToOutline(true);

    }

    @Override
    public int getItemCount() {
        return messageHistoryListPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

//        AppCompatImageView ivViewMoreBtnMessageHistory;
//        LinearLayout llExpandedHeaderMessageHistory;
//        LinearLayout llExpandableLayoutMessageHistory;

        //        TextViewRegularFont tvMessageHistoryMobileNo;
        TextViewRegularFont tvMessageHistoryNote;
        TextViewMediumFont tvMessageHistoryDate, tvMessageHistoryMobileNo;
//        TextViewMediumFont tvMessageHistorySem;

        MaterialCardView cvTest;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvTest = itemView.findViewById(R.id.cvTest);
            tvMessageHistoryMobileNo = itemView.findViewById(R.id.tvMessageHistoryMobileNo);
//            tvMessageHistorySem = itemView.findViewById(R.id.tvMessageHistorySem);
            tvMessageHistoryDate = itemView.findViewById(R.id.tvMessageHistoryDate);
            tvMessageHistoryNote = itemView.findViewById(R.id.tvMessageHistoryNote);
//            ivViewMoreBtnMessageHistory = itemView.findViewById(R.id.ivViewMoreBtnMessageHistory);
//            llExpandedHeaderMessageHistory = itemView.findViewById(R.id.llExpandedHeaderMessageHistory);
//            llExpandableLayoutMessageHistory = itemView.findViewById(R.id.llExpandableLayoutMessageHistory);
        }
    }

    private boolean toggleLayout(boolean isExpanded, View v, LinearLayout layoutExpand) {
        CustomAnimationForDefaultExpandableCard.toggleArrow(v, isExpanded);
        if (isExpanded) {
            CustomAnimationForDefaultExpandableCard.expand(layoutExpand);
        } else {
            CustomAnimationForDefaultExpandableCard.collapse(layoutExpand);
        }
        return isExpanded;

    }

}
