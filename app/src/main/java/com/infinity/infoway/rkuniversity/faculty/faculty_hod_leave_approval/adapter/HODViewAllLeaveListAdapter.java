package com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.Animations;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.FacultyHODLeaveApprovalActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.HODAndCoOrdinatorApprovLeavePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODStudentLeaveApproveApplicationAllPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.DownloadPdfFromUrl;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HODViewAllLeaveListAdapter extends RecyclerView.Adapter<HODViewAllLeaveListAdapter.MyViewHolder> {


    private Context context;
    private ArrayList<HODStudentLeaveApproveApplicationAllPojo> HODStudentLeaveApproveApplicationAllPojoArrayList;
    private LayoutInflater layoutInflater;
    private MySharedPreferences mySharedPreferences;
    private IApproveOrRejectLeave iApproveOrRejectLeave;
    private static final String LEAVE_APPROVAL_STATUS_REJECT = "0";
    private static final String LEAVE_APPROVAL_STATUS_APPROVE = "1";
    private Dialog m_Dialog = null;

    public HODViewAllLeaveListAdapter(Context context,
                                      ArrayList<HODStudentLeaveApproveApplicationAllPojo> HODStudentLeaveApproveApplicationAllPojoArrayList, IApproveOrRejectLeave iApproveOrRejectLeave) {
        this.context = context;
        this.HODStudentLeaveApproveApplicationAllPojoArrayList = HODStudentLeaveApproveApplicationAllPojoArrayList;
        layoutInflater = LayoutInflater.from(context);
        mySharedPreferences = new MySharedPreferences(context);
        this.iApproveOrRejectLeave = iApproveOrRejectLeave;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hod_view_all_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HODStudentLeaveApproveApplicationAllPojo hodStudentLeaveApproveApplicationAllPojo = HODStudentLeaveApproveApplicationAllPojoArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(hodStudentLeaveApproveApplicationAllPojo.getStudNameApp())) {
            holder.tvStudentName.setText(hodStudentLeaveApproveApplicationAllPojo.getStudNameApp() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(hodStudentLeaveApproveApplicationAllPojo.getLeaveDate())) {
            holder.tvLeaveDate.setText(hodStudentLeaveApproveApplicationAllPojo.getLeaveDate() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(hodStudentLeaveApproveApplicationAllPojo.getStudRemarks())) {
            holder.tvLeaveRemaks.setText(hodStudentLeaveApproveApplicationAllPojo.getStudRemarks() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(hodStudentLeaveApproveApplicationAllPojo.getAppStatusText())) {
            String leaveStatus = hodStudentLeaveApproveApplicationAllPojo.getAppStatusText() + "";
            holder.tvStatus.setText(hodStudentLeaveApproveApplicationAllPojo.getAppStatusText() + "");

            String approveRejectStatus = hodStudentLeaveApproveApplicationAllPojo.getAppStatus()+ "";//0 Pending,1 Approved,2Reject

            if (approveRejectStatus.equalsIgnoreCase(FacultyHODLeaveApprovalActivity.HOD_STATUS_PENDING)) {
                holder.llForShape.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_for_pending));
                holder.btnApprove.setVisibility(View.VISIBLE);
                holder.llSpace.setVisibility(View.VISIBLE);
                holder.btnReject.setVisibility(View.VISIBLE);
            } else if (approveRejectStatus.equalsIgnoreCase(FacultyHODLeaveApprovalActivity.HOD_STATUS_APPROVE)) {
                holder.llForShape.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_for_approve));
                holder.btnApprove.setVisibility(View.GONE);
                holder.llSpace.setVisibility(View.GONE);
                holder.btnReject.setVisibility(View.VISIBLE);
            } else if (approveRejectStatus.equalsIgnoreCase(FacultyHODLeaveApprovalActivity.HOD_STATUS_REJECT)) {
                holder.llForShape.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_for_reject));
                holder.btnApprove.setVisibility(View.VISIBLE);
                holder.btnReject.setVisibility(View.GONE);
                holder.llSpace.setVisibility(View.GONE);
            }else{
                holder.llForShape.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_for_pending));
                holder.btnApprove.setVisibility(View.GONE);
                holder.llSpace.setVisibility(View.GONE);
                holder.btnReject.setVisibility(View.GONE);
                holder.llApproveRejectBtn.setVisibility(View.GONE);
            }
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(hodStudentLeaveApproveApplicationAllPojo.getViewFile()) &&
                hodStudentLeaveApproveApplicationAllPojo.getViewFile().length() > 7) {
            holder.llDoc.setVisibility(View.VISIBLE);
        } else {
            holder.llDoc.setVisibility(View.GONE);
        }

        holder.tvClikToView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileUrl = hodStudentLeaveApproveApplicationAllPojo.getViewFile().trim();
                String fileExtension = fileUrl.substring(fileUrl.lastIndexOf("."));
                new DownloadPdfFromUrl(context, fileUrl, fileExtension, "Leave_Doc");
            }
        });

        holder.btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHodLeaveDialog(hodStudentLeaveApproveApplicationAllPojo, true);
            }
        });

        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHodLeaveDialog(hodStudentLeaveApproveApplicationAllPojo, false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return HODStudentLeaveApproveApplicationAllPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvStatus;
        TextViewRegularFont tvStudentName;
        LinearLayout llForShape;
        TextViewRegularFont tvLeaveDate;
        TextViewRegularFont tvLeaveRemaks;
        LinearLayout llDoc;
        TextViewRegularFont tvClikToView;
        LinearLayout llApproveRejectBtn;
        LinearLayout llSpace;
        TextViewRegularFont btnApprove;
        TextViewRegularFont btnReject;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            llForShape = itemView.findViewById(R.id.llForShape);
            tvLeaveDate = itemView.findViewById(R.id.tvLeaveDate);
            tvLeaveRemaks = itemView.findViewById(R.id.tvLeaveRemaks);
            llDoc = itemView.findViewById(R.id.llDoc);
            tvClikToView = itemView.findViewById(R.id.tvClikToView);
            llApproveRejectBtn = itemView.findViewById(R.id.llApproveRejectBtn);
            btnApprove = itemView.findViewById(R.id.btnApprove);
            btnReject = itemView.findViewById(R.id.btnReject);
            llSpace = itemView.findViewById(R.id.llSpace);
        }
    }

    private void showHodLeaveDialog(HODStudentLeaveApproveApplicationAllPojo hodStudentLeaveApproveApplicationAllPojo, boolean isApprove) {
        m_Dialog = new Dialog(context);
        m_Dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);//if need to change dialog radius in custom_layout_for_progress_dialog
        // layout than also change same radius in bg_shape_for_custom_dialog
        m_Dialog.setCancelable(false);
        View llHodLeaveDialog = LayoutInflater.from(context).inflate(R.layout.layout_for_hod_leave, null);
        MaterialCheckBox cbPresentOnLeave = llHodLeaveDialog.findViewById(R.id.cbPresentOnLeave);
        TextInputEditText tiedtRemarks = llHodLeaveDialog.findViewById(R.id.tiedtRemarks);
        TextViewRegularFont btnCancel = llHodLeaveDialog.findViewById(R.id.btnCancel);
        TextViewRegularFont btnSubmit = llHodLeaveDialog.findViewById(R.id.btnSubmit);

        if (!isApprove) {
            cbPresentOnLeave.setVisibility(View.GONE);
        }
        m_Dialog.setContentView(llHodLeaveDialog);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideHodLeaveDialog();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideHodLeaveDialog();
                String comments ="";
                if (!CommonUtil.checkIsEmptyOrNullCommon(tiedtRemarks.getText().toString().trim())){
                    comments = tiedtRemarks.getText().toString().trim();
                }
                String slId = hodStudentLeaveApproveApplicationAllPojo.getSlId() + "";
                String chIsPresentOrNot = "0";
                String approveRejectStatus = LEAVE_APPROVAL_STATUS_REJECT;
                if (isApprove) {
                    approveRejectStatus = LEAVE_APPROVAL_STATUS_APPROVE;
                    chIsPresentOrNot = cbPresentOnLeave.isChecked() ? "1" : "0";
                }
                submitRemarksApiCall(slId, comments, chIsPresentOrNot, approveRejectStatus);
            }
        });
        m_Dialog.show();
    }

    public void hideHodLeaveDialog() {
        if (m_Dialog != null) {
            if (m_Dialog.isShowing()) {
                m_Dialog.dismiss();
            }
        }
    }

    private void submitRemarksApiCall(String slId, String comments, String chkPresentOrNot, String approveRejectStatus) {
        DialogUtil.showProgressDialogNotCancelable(context, "");
        ApiImplementer.updateStudentLeaveApprovalStatusApiImplementer(slId, mySharedPreferences.getEmpId(), mySharedPreferences.getEmpInstituteId(),
                mySharedPreferences.getEmpYearId(), comments, "from mobile app", approveRejectStatus, chkPresentOrNot, new Callback<HODAndCoOrdinatorApprovLeavePojo>() {
                    @Override
                    public void onResponse(Call<HODAndCoOrdinatorApprovLeavePojo> call, Response<HODAndCoOrdinatorApprovLeavePojo> response) {
                        DialogUtil.hideProgressDialog();
                        try {
                            if (response.isSuccessful() && response.body() != null) {
                                iApproveOrRejectLeave.onLeaveApprove();
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
                    public void onFailure(Call<HODAndCoOrdinatorApprovLeavePojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public interface IApproveOrRejectLeave {
        void onLeaveApprove();
    }

}
