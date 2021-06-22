package com.infinity.infoway.rkuniversity.faculty.faculty_announcement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.rkuniversity.student.news_or_notification.UpdateNotificationStatusPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.DownloadPdfFromUrl;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyViewAllAnnouncementAdapter extends RecyclerView.Adapter<FacultyViewAllAnnouncementAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data> facultyAnnouncementPojoArrayList;
    FacultyViewAllAnnouncementAdapter.IRemoveStudentNewsOrNotification iRemoveStudentNewsOrNotification;
    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;

    public FacultyViewAllAnnouncementAdapter(Context context, ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data> facultyAnnouncementPojoArrayList) {
        this.context = context;
        this.facultyAnnouncementPojoArrayList = facultyAnnouncementPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
        iRemoveStudentNewsOrNotification = (FacultyAnnouncementActivity) context;
        mySharedPreferences = new MySharedPreferences(context);
        connectionDetector = new ConnectionDetector(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_layout_view_all_faculty_announcement, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyOrStudentNewsOrNotificationsPojo.Data data = facultyAnnouncementPojoArrayList.get(position);

        if (data.getNt_is_notif() == 1 && data.getRead_unread_status() == 0) {
            holder.tvNewNotificationVisibleView.setVisibility(View.VISIBLE);
            holder.tvNewNotificationInVisibleView.setVisibility(View.INVISIBLE);
        } else {
            holder.tvNewNotificationVisibleView.setVisibility(View.INVISIBLE);
            holder.tvNewNotificationInVisibleView.setVisibility(View.INVISIBLE);
        }

//        holder.cbMarkAsReadStudentNotification.setChecked(false);
        if (data.getNt_head() != null && !data.getNt_head().isEmpty()) {
            holder.tvNotificationTitle.setText(data.getNt_head());
        }

        if (data.getNt_date() != null && !data.getNt_date().isEmpty()) {
            String date = "";
            try {
                if (data.getNt_date().contains("/")) {
                    String dateArray[] = data.getNt_date().split("/");
                    date = dateArray[0] + " " + CommonUtil.getMonthSortNameFromNumber(Integer.parseInt(dateArray[1])) + "," + dateArray[2];
                }
                holder.tvNotificationDate.setText(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (data.getNt_desc() != null && !data.getNt_desc().isEmpty()) {
            holder.tvNotificationDesc.setText(data.getNt_desc());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.getNt_is_notif() == 1 && data.getRead_unread_status() == 0 && holder.tvNewNotificationVisibleView.getVisibility() == View.VISIBLE) {
                    updateNewsOrNotificationStatus(data, position, holder.tvNewNotificationVisibleView,
                            holder.tvNewNotificationInVisibleView);
                }
            }
        });

//        holder.cbMarkAsReadStudentNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    new MaterialAlertDialogBuilder(context)
//                            .setMessage("Are you sure you want to mark as read ?")
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    updateNewsOrNotificationStatus(data, holder.cbMarkAsReadStudentNotification, position);
//                                    dialog.dismiss();
//                                }
//                            })
//                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    holder.cbMarkAsReadStudentNotification.setChecked(false);
//                                    dialog.dismiss();
//                                }
//                            })
//                            .show();
//                }
//            }
//        });

        if (!CommonUtil.checkIsEmptyOrNullCommon(data.getNt_file_path())) {
            holder.imgDownloadAttachFileStudent.setVisibility(View.VISIBLE);
        } else {
            holder.imgDownloadAttachFileStudent.setVisibility(View.GONE);
        }

        holder.imgDownloadAttachFileStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileUrl = data.getNt_file_path();
                String fileExtension = fileUrl.substring(fileUrl.lastIndexOf("."));
                new DownloadPdfFromUrl(context, data.getNt_file_path(), fileExtension, "Faculty Announcement");
            }
        });
    }


    private void updateNewsOrNotificationStatus(FacultyOrStudentNewsOrNotificationsPojo.Data data,
                                                int position, TextViewRegularFont tvNewNotificationVisibleView,
                                                TextViewRegularFont tvNewNotificationInVisibleView) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(context, "");
            ApiImplementer.updateStudentOrEmployeeNotificationStatusApiImplementer(mySharedPreferences.getLoginUserType() + "",
                    data.getNt_id() + "", mySharedPreferences.getEmpId(), "1", new Callback<UpdateNotificationStatusPojo>() {
                        @Override
                        public void onResponse(Call<UpdateNotificationStatusPojo> call, Response<UpdateNotificationStatusPojo> response) {
                            DialogUtil.hideProgressDialog();
                            if (response.isSuccessful() && response.body() != null &&
                                    response.body().getTable().size() > 0) {
                                tvNewNotificationVisibleView.setVisibility(View.INVISIBLE);
                                tvNewNotificationInVisibleView.setVisibility(View.INVISIBLE);
//                                facultyAnnouncementPojoArrayList.remove(position);
//                                iRemoveStudentNewsOrNotification.onNotificationRemove(position);
                            } else {
                                tvNewNotificationVisibleView.setVisibility(View.VISIBLE);
                                tvNewNotificationInVisibleView.setVisibility(View.INVISIBLE);
//                                cbMarkAsReadStudentNotification.setChecked(false);
                                Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UpdateNotificationStatusPojo> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            tvNewNotificationVisibleView.setVisibility(View.VISIBLE);
                            tvNewNotificationInVisibleView.setVisibility(View.INVISIBLE);
//                            cbMarkAsReadStudentNotification.setChecked(false);
                            Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return facultyAnnouncementPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imgDownloadAttachFileStudent;
//        AppCompatCheckBox cbMarkAsReadStudentNotification;

        TextViewRegularFont tvNotificationDate;
        TextViewRegularFont tvNotificationTitle;
        TextViewRegularFont tvNotificationDesc;

        TextViewRegularFont tvNewNotificationVisibleView;
        TextViewRegularFont tvNewNotificationInVisibleView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNewNotificationVisibleView = itemView.findViewById(R.id.tvNewNotificationVisibleView);
            tvNewNotificationInVisibleView = itemView.findViewById(R.id.tvNewNotificationInVisibleView);
            tvNotificationDate = itemView.findViewById(R.id.tvNotificationDate);
            tvNotificationTitle = itemView.findViewById(R.id.tvNotificationTitle);
            tvNotificationDesc = itemView.findViewById(R.id.tvNotificationDesc);
            imgDownloadAttachFileStudent = itemView.findViewById(R.id.imgDownloadAttachFileStudent);
//            cbMarkAsReadStudentNotification = itemView.findViewById(R.id.cbMarkAsReadStudentNotification);
        }
    }

    public interface IRemoveStudentNewsOrNotification {
        void onNotificationRemove(int removeIndex);
    }


}
