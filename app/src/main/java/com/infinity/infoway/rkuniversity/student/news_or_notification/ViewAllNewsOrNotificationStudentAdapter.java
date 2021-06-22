package com.infinity.infoway.rkuniversity.student.news_or_notification;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.DownloadPdfFromUrl;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllNewsOrNotificationStudentAdapter extends RecyclerView.Adapter<ViewAllNewsOrNotificationStudentAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data> dataArrayList;
    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    IRemoveStudentNewsOrNotification iRemoveStudentNewsOrNotification;

    public ViewAllNewsOrNotificationStudentAdapter(Context context, ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        layoutInflater = LayoutInflater.from(context);
        mySharedPreferences = new MySharedPreferences(context);
        connectionDetector = new ConnectionDetector(context);
        iRemoveStudentNewsOrNotification = (IRemoveStudentNewsOrNotification) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_view_all_news_or_notification, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyOrStudentNewsOrNotificationsPojo.Data data = dataArrayList.get(position);
        holder.cbMarkAsReadStudentNotification.setChecked(false);
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

        if (data.getNt_is_notif() == 1) {
            holder.cbMarkAsReadStudentNotification.setVisibility(View.VISIBLE);
        } else {
            holder.cbMarkAsReadStudentNotification.setVisibility(View.GONE);
        }

        holder.cbMarkAsReadStudentNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new MaterialAlertDialogBuilder(context)
                            .setMessage("Are you sure you want to mark as read ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    updateNewsOrNotificationStatus(data, holder.cbMarkAsReadStudentNotification, position);
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    holder.cbMarkAsReadStudentNotification.setChecked(false);
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });

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
                new DownloadPdfFromUrl(context, data.getNt_file_path(), fileExtension, "Student News Or Notification");
            }
        });
    }


    private void updateNewsOrNotificationStatus(FacultyOrStudentNewsOrNotificationsPojo.Data data, AppCompatCheckBox cbMarkAsReadStudentNotification, int position) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(context, "");
            ApiImplementer.updateStudentOrEmployeeNotificationStatusApiImplementer(mySharedPreferences.getLoginUserType() + "",
                    data.getNt_id() + "", mySharedPreferences.getStudentId(), "1", new Callback<UpdateNotificationStatusPojo>() {
                        @Override
                        public void onResponse(Call<UpdateNotificationStatusPojo> call, Response<UpdateNotificationStatusPojo> response) {
                            DialogUtil.hideProgressDialog();
                            if (response.isSuccessful() && response.body() != null && response.body().getTable().size() > 0) {
                                dataArrayList.remove(position);
                                iRemoveStudentNewsOrNotification.onNotificationRemove(position);
                            } else {
                                cbMarkAsReadStudentNotification.setChecked(false);
                                Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UpdateNotificationStatusPojo> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            cbMarkAsReadStudentNotification.setChecked(false);
                            Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imgDownloadAttachFileStudent;
        AppCompatCheckBox cbMarkAsReadStudentNotification;

        TextViewRegularFont tvNotificationDate;
        TextViewRegularFont tvNotificationTitle;
        TextViewRegularFont tvNotificationDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNotificationDate = itemView.findViewById(R.id.tvNotificationDate);
            tvNotificationTitle = itemView.findViewById(R.id.tvNotificationTitle);
            tvNotificationDesc = itemView.findViewById(R.id.tvNotificationDesc);
            imgDownloadAttachFileStudent = itemView.findViewById(R.id.imgDownloadAttachFileStudent);
            cbMarkAsReadStudentNotification = itemView.findViewById(R.id.cbMarkAsReadStudentNotification);
        }
    }

    public interface IRemoveStudentNewsOrNotification {
        void onNotificationRemove(int removeIndex);
    }

}
