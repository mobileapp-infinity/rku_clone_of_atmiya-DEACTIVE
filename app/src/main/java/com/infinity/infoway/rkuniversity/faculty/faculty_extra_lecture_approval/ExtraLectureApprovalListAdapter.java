package com.infinity.infoway.rkuniversity.faculty.faculty_extra_lecture_approval;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtraLectureApprovalListAdapter extends RecyclerView.Adapter<ExtraLectureApprovalListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ExtraLectureApprovalPojo> extraLectureApprovalPojoArrayList;
    private LayoutInflater layoutInflater;
    private IApproveRejectExtraLecture iApproveRejectExtraLecture;
    private MySharedPreferences mySharedPreferences;
    private Dialog m_Dialog = null;
    AlertDialog.Builder builder;

    public ExtraLectureApprovalListAdapter(Context context, ArrayList<ExtraLectureApprovalPojo> extraLectureApprovalPojoArrayList) {
        this.context = context;
        this.extraLectureApprovalPojoArrayList = extraLectureApprovalPojoArrayList;
        this.layoutInflater = LayoutInflater.from(context);
        this.iApproveRejectExtraLecture = (IApproveRejectExtraLecture) context;
        mySharedPreferences = new MySharedPreferences(context);
        builder = new AlertDialog.Builder(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.faculty_extra_lecure_approval_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ExtraLectureApprovalPojo extraLectureApprovalPojo = extraLectureApprovalPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getEmpName())) {
            holder.tvStudentName.setText(extraLectureApprovalPojo.getEmpName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getExtLectDate())) {
            holder.tvLeaveDate.setText(extraLectureApprovalPojo.getExtLectDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getCourse())) {
            holder.tvLeaveCourse.setText(extraLectureApprovalPojo.getCourse() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getLectNo())) {
            holder.tvLectureNo.setText(extraLectureApprovalPojo.getLectNo() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getSemester())) {
            holder.tvSemester.setText(extraLectureApprovalPojo.getSemester() + "("+extraLectureApprovalPojo.getDivision()+")"+"("+extraLectureApprovalPojo.getRes()+")");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getAppStatus())) {
            holder.tvStatus.setText(extraLectureApprovalPojo.getAppStatus() + "");

            String approveRejectStatus = extraLectureApprovalPojo.getExLecAppStatus() + "";//0 Pending,1 Approved,2Reject

            if (approveRejectStatus.equalsIgnoreCase(FacultyExtraLectureApprovalActivity.EXTRA_LECTURE_PENDING)) {
                holder.llForShape.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_for_pending));
                holder.btnApprove.setVisibility(View.VISIBLE);
                holder.llSpace.setVisibility(View.VISIBLE);
                holder.btnReject.setVisibility(View.VISIBLE);
            } else if (approveRejectStatus.equalsIgnoreCase(FacultyExtraLectureApprovalActivity.EXTRA_LECTURE_APPROVE)) {
                holder.llForShape.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_for_approve));
                holder.btnApprove.setVisibility(View.GONE);
                holder.llSpace.setVisibility(View.GONE);
                holder.btnReject.setVisibility(View.VISIBLE);
            } else if (approveRejectStatus.equalsIgnoreCase(FacultyExtraLectureApprovalActivity.EXTRA_LECTURE_REJECT)) {
                holder.llForShape.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_for_reject));
                holder.btnApprove.setVisibility(View.VISIBLE);
                holder.btnReject.setVisibility(View.GONE);
                holder.llSpace.setVisibility(View.GONE);
            } else {
                holder.llForShape.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_for_pending));
                holder.btnApprove.setVisibility(View.GONE);
                holder.llSpace.setVisibility(View.GONE);
                holder.btnReject.setVisibility(View.GONE);
                holder.llApproveRejectBtn.setVisibility(View.GONE);
            }
        }


        holder.btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Approve",extraLectureApprovalPojo,true);
            }
        });

        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Reject",extraLectureApprovalPojo,false);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExtraLectureDetailsDialog(extraLectureApprovalPojo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return extraLectureApprovalPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvStatus;
        TextViewRegularFont tvStudentName;
        LinearLayout llForShape;
        TextViewRegularFont tvLeaveDate;
        LinearLayout llApproveRejectBtn;
        LinearLayout llSpace;
        TextViewRegularFont btnApprove;
        TextViewRegularFont btnReject;
        TextViewRegularFont tvLeaveCourse;
        TextViewRegularFont tvLectureNo;
        TextViewRegularFont tvSemester;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            llForShape = itemView.findViewById(R.id.llForShape);
            tvLeaveDate = itemView.findViewById(R.id.tvLeaveDate);
            llApproveRejectBtn = itemView.findViewById(R.id.llApproveRejectBtn);
            btnApprove = itemView.findViewById(R.id.btnApprove);
            btnReject = itemView.findViewById(R.id.btnReject);
            llSpace = itemView.findViewById(R.id.llSpace);
            tvLeaveCourse = itemView.findViewById(R.id.tvLeaveCourse);
            tvLectureNo = itemView.findViewById(R.id.tvLectureNo);
            tvSemester = itemView.findViewById(R.id.tvSemester);
        }
    }

    private void showAlertDialog(String message,ExtraLectureApprovalPojo extraLectureApprovalPojo,boolean isApproveReq){
        builder.setMessage("Are you sure you want to " +message+" ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        if (isApproveReq){
                            approveOrRejectExtraLectureApiCall("1", extraLectureApprovalPojo.getExLecId());
                        }else{
                            approveOrRejectExtraLectureApiCall("2", extraLectureApprovalPojo.getExLecId());
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void approveOrRejectExtraLectureApiCall(String App_Rej_status,
                                                    String ex_lec_ids) {
        DialogUtil.showProgressDialogNotCancelable(context, "");
        ApiImplementer.approvOrRejectExtraLectureApiImplementer(App_Rej_status, ex_lec_ids, "from mobile app", mySharedPreferences.getEmpId(), new Callback<ApproveOrRejectExtraLecturePojo>() {
            @Override
            public void onResponse(Call<ApproveOrRejectExtraLecturePojo> call, Response<ApproveOrRejectExtraLecturePojo> response) {
                DialogUtil.hideProgressDialog();
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        iApproveRejectExtraLecture.onApproveOrReject();
                        Toast.makeText(context, "" + response.body().getResponse(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        DialogUtil.hideProgressDialog();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApproveOrRejectExtraLecturePojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showExtraLectureDetailsDialog(ExtraLectureApprovalPojo extraLectureApprovalPojo) {
        m_Dialog = new Dialog(context);
        m_Dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);//if need to change dialog radius in custom_layout_for_progress_dialog
        // layout than also change same radius in bg_shape_for_custom_dialog
        m_Dialog.setCancelable(true);
        View extraLectureDetailsDialog = LayoutInflater.from(context).inflate(R.layout.layout_for_extra_leave_approval_dialog, null);
        m_Dialog.setContentView(extraLectureDetailsDialog);
        TextViewRegularFont tvName = extraLectureDetailsDialog.findViewById(R.id.tvStudentName);
        TextViewRegularFont tvDate = extraLectureDetailsDialog.findViewById(R.id.tvLeaveDate);
        TextViewRegularFont tvCourse = extraLectureDetailsDialog.findViewById(R.id.tvCourse);
        TextViewRegularFont tvSem = extraLectureDetailsDialog.findViewById(R.id.tvSemester);
        TextViewRegularFont tvDiv = extraLectureDetailsDialog.findViewById(R.id.tvDivision);
        TextViewRegularFont tvLecType = extraLectureDetailsDialog.findViewById(R.id.tvLectureType);
        TextViewRegularFont tvSub = extraLectureDetailsDialog.findViewById(R.id.tvSubject);
        TextViewRegularFont tvStatus = extraLectureDetailsDialog.findViewById(R.id.tvStatus);
        TextViewRegularFont tvLectureNo = extraLectureDetailsDialog.findViewById(R.id.tvLectureNo);
        AppCompatImageView icClose = extraLectureDetailsDialog.findViewById(R.id.imgClose);

        icClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideExtraLeaveApprovalDialog();
            }
        });

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getEmpName())) {
           tvName.setText(extraLectureApprovalPojo.getEmpName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getExtLectDate())) {
            tvDate.setText(extraLectureApprovalPojo.getExtLectDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getCourse())) {
            tvCourse.setText(extraLectureApprovalPojo.getCourse() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getSemester())) {
            tvSem.setText(extraLectureApprovalPojo.getSemester() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getDivision())) {
            tvDiv.setText(extraLectureApprovalPojo.getDivision() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getExtLecType())) {
            tvLecType.setText(extraLectureApprovalPojo.getExtLecType() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getSub())) {
            tvSub.setText(extraLectureApprovalPojo.getSub() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getAppStatus())) {
            tvStatus.setText(extraLectureApprovalPojo.getAppStatus() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(extraLectureApprovalPojo.getLectNo())) {
            tvLectureNo.setText(extraLectureApprovalPojo.getLectNo() + "");
        }

        m_Dialog.show();
    }

    public void hideExtraLeaveApprovalDialog() {
        if (m_Dialog != null) {
            if (m_Dialog.isShowing()) {
                m_Dialog.dismiss();
            }
        }
    }

    public interface IApproveRejectExtraLecture {
        void onApproveOrReject();
    }
}
