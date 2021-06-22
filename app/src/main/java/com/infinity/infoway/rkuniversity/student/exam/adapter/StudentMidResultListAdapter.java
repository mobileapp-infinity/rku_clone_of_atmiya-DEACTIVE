package com.infinity.infoway.rkuniversity.student.exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.CustomAnimationForDefaultExpandableCard;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.exam.pojo.DownloadStudentMidResultPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.MidResultPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.GeneratePDFFileFromBase64String;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentMidResultListAdapter extends RecyclerView.Adapter<StudentMidResultListAdapter.MyViewHolder> {

    Context context;
    ArrayList<MidResultPojo> midResultPojoArrayList;
    LayoutInflater layoutInflater;
    MySharedPreferences mySharedPreferences;
//    ProgressDialog progressDialog;
    ConnectionDetector connectionDetector;

    public StudentMidResultListAdapter(Context context, ArrayList<MidResultPojo> midResultPojoArrayList) {
        this.context = context;
        this.midResultPojoArrayList = midResultPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
        connectionDetector = new ConnectionDetector(context);
        mySharedPreferences = new MySharedPreferences(context);
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Please wait....");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_mid_result_marks_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MidResultPojo midResultPojo = midResultPojoArrayList.get(position);
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getSmName())) {
            holder.tvSemNameMidResult.setText(midResultPojo.getSmName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getYearName())) {
            holder.tvYearNameMidResult.setText(midResultPojo.getYearName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getSrpcResultName())) {
            holder.tvResultNameMidResult.setText(midResultPojo.getSrpcResultName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getSrpcResultDisplayDate())) {
            holder.tvResultDateMidResult.setText(midResultPojo.getSrpcResultDisplayDate() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojo.getSrpcRemarks())) {
            holder.tvRemarksMidResult.setText(midResultPojo.getSrpcRemarks() + "");
        }

        holder.llExpandedHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean show = toggleLayout(!midResultPojoArrayList.get(position).isExpanded(), holder.ivViewMoreMidResult, holder.llExpandableLayout);
                midResultPojoArrayList.get(position).setExpanded(show);
            }
        });

        holder.btnDownloadMidResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonUtil.checkIsEmptyOrNullCommon(midResultPojoArrayList.get(position).getSrpcId())) {
                    downloadStudentMidResultApiCall(midResultPojoArrayList.get(position).getSrpcId() + "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return midResultPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivViewMoreMidResult;
        LinearLayout llExpandableLayout;
        LinearLayout llExpandedHeader;

        LinearLayout llDownloadMidResult;
        TextViewRegularFont btnDownloadMidResult;
        TextViewRegularFont tvSemNameMidResult;
        TextViewRegularFont tvYearNameMidResult;
        TextViewRegularFont tvResultNameMidResult;
        TextViewRegularFont tvResultDateMidResult;
        TextViewRegularFont tvRemarksMidResult;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivViewMoreMidResult = itemView.findViewById(R.id.ivViewMoreMidResult);
            llExpandableLayout = itemView.findViewById(R.id.llExpandableLayout);
            llExpandedHeader = itemView.findViewById(R.id.llExpandedHeader);
            llDownloadMidResult = itemView.findViewById(R.id.llDownloadMidResult);
            btnDownloadMidResult = itemView.findViewById(R.id.btnDownloadMidResult);
            tvSemNameMidResult = itemView.findViewById(R.id.tvSemNameMidResult);
            tvYearNameMidResult = itemView.findViewById(R.id.tvYearNameMidResult);
            tvResultNameMidResult = itemView.findViewById(R.id.tvResultNameMidResult);
            tvResultDateMidResult = itemView.findViewById(R.id.tvResultDateMidResult);
            tvRemarksMidResult = itemView.findViewById(R.id.tvRemarksMidResult);


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

    private void downloadStudentMidResultApiCall(String srpcId) {
        if (connectionDetector.isConnectingToInternet()) {
//            progressDialog.show();
            DialogUtil.showProgressDialogNotCancelable(context,"downloading... ");
            ApiImplementer.downloadStudentMidResultApiImplementer(mySharedPreferences.getSmId(), srpcId, mySharedPreferences.getStudentId(), mySharedPreferences.getDmId(),
                    mySharedPreferences.getCourseId(), new Callback<DownloadStudentMidResultPojo>() {
                        @Override
                        public void onResponse(Call<DownloadStudentMidResultPojo> call, Response<DownloadStudentMidResultPojo> response) {
                            DialogUtil.hideProgressDialog();
                            if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 1 &&
                                    response.body().getBase64string() != null && !response.body().getBase64string().isEmpty()) {
                                new GeneratePDFFileFromBase64String(context, "Mid Result", CommonUtil.generateUniqueFileName(response.body().getFilename()), response.body().getBase64string());
                            } else {
                                Toast.makeText(context, "some thing went wrong please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DownloadStudentMidResultPojo> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "No internet connection,Please try again later", Toast.LENGTH_SHORT).show();
        }
    }

}
