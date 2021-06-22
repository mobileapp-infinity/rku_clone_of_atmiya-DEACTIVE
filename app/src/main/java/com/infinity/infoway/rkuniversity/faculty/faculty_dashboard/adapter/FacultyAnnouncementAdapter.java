package com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.faculty.faculty_announcement.FacultyAnnouncementActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.activity.FacultyDashboardActivity;
import com.infinity.infoway.rkuniversity.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;

import java.util.ArrayList;

public class FacultyAnnouncementAdapter extends RecyclerView.Adapter<FacultyAnnouncementAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data> studentNewsOrNotificationList;

    public FacultyAnnouncementAdapter(Context context, ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data> studentNewsOrNotificationList) {
        this.context = context;
        this.studentNewsOrNotificationList = studentNewsOrNotificationList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_announcement_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FacultyOrStudentNewsOrNotificationsPojo.Data data = studentNewsOrNotificationList.get(position);

        if (data.getNt_head() != null && !data.getNt_head().isEmpty()) {
            holder.tvNotificationHeadFacultySide.setText(data.getNt_head());
        }

        if (data.getNt_date() != null && !data.getNt_date().isEmpty()) {
            String date = "";
            try {
                if (data.getNt_date().contains("/")) {
                    String dateArray[] = data.getNt_date().split("/");
                    date = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + "," + "\n" + dateArray[2];
                }
                holder.tvNotificationDateFacultySide.setText(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (data.getNt_desc() != null && !data.getNt_desc().isEmpty()) {
            holder.tvnotificationDescriptionFacultySide.setText(data.getNt_desc());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FacultyAnnouncementActivity.class);
                ((FacultyDashboardActivity)context).startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_VIEW_ALL_NEWS_OR_NOTIFICATION_FACULTY_SIDE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return studentNewsOrNotificationList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvNotificationHeadFacultySide;
        TextViewRegularFont tvNotificationDateFacultySide;
        TextViewRegularFont tvnotificationDescriptionFacultySide;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNotificationHeadFacultySide = itemView.findViewById(R.id.tvNotificationHeadFacultySide);
            tvNotificationDateFacultySide = itemView.findViewById(R.id.tvNotificationDateFacultySide);
            tvnotificationDescriptionFacultySide = itemView.findViewById(R.id.tvnotificationDescriptionFacultySide);
        }
    }

}
