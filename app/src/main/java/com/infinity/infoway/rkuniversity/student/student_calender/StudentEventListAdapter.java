package com.infinity.infoway.rkuniversity.student.student_calender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.event_calender.EventModel;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentEventListAdapter extends RecyclerView.Adapter<StudentEventListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<EventModel.Array> eventDetailsArrayList;
    private LayoutInflater layoutInflater;

    public StudentEventListAdapter(Context context, ArrayList<EventModel.Array> eventDetailsArrayList) {
        this.context = context;
        this.eventDetailsArrayList = eventDetailsArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.event_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EventModel.Array eventDetails = eventDetailsArrayList.get(position);

        if (position == eventDetailsArrayList.size() - 1) {
            holder.divider.setVisibility(View.GONE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(eventDetails.getName())){
            holder.tvEventTitle.setText(eventDetails.getName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(eventDetails.getDetailes())){
            holder.tvEventDescription.setText(eventDetails.getDetailes());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(eventDetails.getDate())){
            holder.tvEventDate.setText(eventDetails.getDate());
        }

    }

    @Override
    public int getItemCount() {
        return eventDetailsArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvEventTitle;
        AppCompatTextView tvEventDescription;
        AppCompatTextView tvEventDate;
        View divider;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEventTitle = itemView.findViewById(R.id.tvEventTitle);
            tvEventDescription = itemView.findViewById(R.id.tvEventDescription);
            tvEventDate = itemView.findViewById(R.id.tvEventDate);
            divider = itemView.findViewById(R.id.divider);
        }
    }

}
