package com.infinity.infoway.rkuniversity.student.distribution_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.distribution_details.pojo.GetDistributionDetailListPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class ItemDistributionListAdapter extends RecyclerView.Adapter<ItemDistributionListAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<GetDistributionDetailListPojo> getDistributionDetailListPojoArrayList;

    public ItemDistributionListAdapter(Context context, ArrayList<GetDistributionDetailListPojo> getDistributionDetailListPojoArrayList) {
        this.context = context;
        this.getDistributionDetailListPojoArrayList = getDistributionDetailListPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_distribution_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GetDistributionDetailListPojo getDistributionDetailListPojo = getDistributionDetailListPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(getDistributionDetailListPojo.getStudName())) {
            holder.tvStudentName.setText(getDistributionDetailListPojo.getStudName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getDistributionDetailListPojo.getCourseName())) {
            holder.tvCourseName.setText(getDistributionDetailListPojo.getCourseName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getDistributionDetailListPojo.getItemName())) {
            holder.tvItemName.setText(getDistributionDetailListPojo.getItemName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(getDistributionDetailListPojo.getItemStatus())) {
            holder.tvItemStatus.setText(getDistributionDetailListPojo.getItemStatus() + "");
        }

        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!getDistributionDetailListPojoArrayList.get(position).isExpanded(), holder.ivViewMoreLessonPlan, holder.llExpandableLayout);
                getDistributionDetailListPojoArrayList.get(position).setExpanded(show);
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return getDistributionDetailListPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreLessonPlan;
        LinearLayout llExpandableLayout;
        LinearLayout llExpandedHeader;
        TextViewRegularFont tvItemStatus;
        TextViewRegularFont tvStudentName;
        TextViewRegularFont tvCourseName;
        TextViewRegularFont tvItemName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemStatus = itemView.findViewById(R.id.tvItemStatus);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            ivViewMoreLessonPlan = itemView.findViewById(R.id.ivViewMoreLessonPlan);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
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
