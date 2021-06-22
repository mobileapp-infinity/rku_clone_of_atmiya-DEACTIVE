package com.infinity.infoway.rkuniversity.faculty.faculty_direct_login_to_student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.activity.FacultyDashboardActivity;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyStudentListAdapter extends RecyclerView.Adapter<FacultyStudentListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<FacultyStudentListPojo.Table> tableArrayList;
    private LayoutInflater layoutInflater;
    private MySharedPreferences mySharedPreferences;

    public FacultyStudentListAdapter(Context context, ArrayList<FacultyStudentListPojo.Table> tableArrayList) {
        this.context = context;
        mySharedPreferences = new MySharedPreferences(context);
        this.tableArrayList = tableArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.faculty_student_list_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FacultyStudentListPojo.Table table = tableArrayList.get(position);

        if (!CommonUtil.checkIsEmptyOrNullCommon(table.getStudPhoto())) {
            Glide.with(context)
                    .asBitmap()
                    .load(table.getStudPhoto())
                    .placeholder(R.drawable.person_img)
                    .error(R.drawable.person_img)
                    .into(holder.cImgStudentPhoto);
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(table.getStudName())) {
            holder.tvStudentName.setText(table.getStudName());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(table.getStudEnrollmentNo())) {
            holder.tvEnNo.setText(table.getStudEnrollmentNo());
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(table.getStudMobileNo())) {
            holder.tvMobileNo.setText(table.getStudMobileNo());
        }

        holder.llFacultyStudentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonUtil.checkIsEmptyOrNullCommon(table.getStudId())) {
                    getStudentDetailsByIdApiCall(table.getStudId() + "");
                } else {
                    Toast.makeText(context, "Student Id Not Found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llFacultyStudentMain;
        CircleImageView cImgStudentPhoto;
        TextViewRegularFont tvStudentName;
        TextViewRegularFont tvEnNo;
        TextViewRegularFont tvMobileNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llFacultyStudentMain = itemView.findViewById(R.id.llFacultyStudentMain);
            cImgStudentPhoto = itemView.findViewById(R.id.cImgStudentPhoto);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvEnNo = itemView.findViewById(R.id.tvEnNo);
            tvMobileNo = itemView.findViewById(R.id.tvMobileNo);
        }
    }

    public void updateList(ArrayList<FacultyStudentListPojo.Table> tableArrayList) {
        this.tableArrayList = tableArrayList;
        notifyDataSetChanged();
    }

    private void getStudentDetailsByIdApiCall(String studentId) {
        DialogUtil.showProgressDialogNotCancelable(context, "");
        ApiImplementer.getStudentDetailsForDirectLoginByStudentIdApiImplementer(studentId, new Callback<GetStudentDetailsForDirectLoginPojo>() {
            @Override
            public void onResponse(Call<GetStudentDetailsForDirectLoginPojo> call, Response<GetStudentDetailsForDirectLoginPojo> response) {
                DialogUtil.hideProgressDialog();
                try {
                    if (response.code() == 200 && response.body() != null &&
                            response.body().getTable() != null) {
                        if (response.body().getTable().size() > 0) {
                            mySharedPreferences.logoutStudentOrFaulty();
                            setStudentLoginData(response.body().getTable().get(0));
                            Intent intent = new Intent(context, FacultyDashboardActivity.class);
                            ((Activity) context).setResult(Activity.RESULT_OK, intent);
                            ((Activity) context).finish();
                        } else {
                            Toast.makeText(context, "Student Details Not Found!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetStudentDetailsForDirectLoginPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setStudentLoginData(GetStudentDetailsForDirectLoginPojo.Table getStudentDetailsForDirectLoginPojo) {

        if (getStudentDetailsForDirectLoginPojo.getStudUserName() != null) {
            mySharedPreferences.setStudentUsername(getStudentDetailsForDirectLoginPojo.getStudUserName() + "");
        }
//        mySharedPreferences.setStudentPassword(password);
        mySharedPreferences.setLoginUserType(CommonUtil.LOGIN_TYPE_STUDENT);

        if (!CommonUtil.checkIsEmptyOrNullCommon(getStudentDetailsForDirectLoginPojo.getStudId())) {
            mySharedPreferences.setStudentId(getStudentDetailsForDirectLoginPojo.getStudId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getDmId() != null) {
            mySharedPreferences.setDMId(getStudentDetailsForDirectLoginPojo.getDmId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getDmFullName() != null) {
            mySharedPreferences.setDmFullName(getStudentDetailsForDirectLoginPojo.getDmFullName());
        }

        if (getStudentDetailsForDirectLoginPojo.getCourseId() != null) {
            mySharedPreferences.setCourseId(getStudentDetailsForDirectLoginPojo.getCourseId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getCourseFullname() != null) {
            mySharedPreferences.setCourseFullName(getStudentDetailsForDirectLoginPojo.getCourseFullname());
        }

        if (getStudentDetailsForDirectLoginPojo.getSmId() != null) {
            mySharedPreferences.setSmId(getStudentDetailsForDirectLoginPojo.getSmId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getSmName() != null) {
            mySharedPreferences.setSmName(getStudentDetailsForDirectLoginPojo.getSmName());
        }

        if (getStudentDetailsForDirectLoginPojo.getAcId() != null) {
            mySharedPreferences.setAcId(getStudentDetailsForDirectLoginPojo.getAcId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getAcFullName() != null) {
            mySharedPreferences.setAcFullName(getStudentDetailsForDirectLoginPojo.getAcFullName());
        }

        if (getStudentDetailsForDirectLoginPojo.getSwdYearId() != null) {
            mySharedPreferences.setSwdYearId(getStudentDetailsForDirectLoginPojo.getSwdYearId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getAcCode() != null) {
            mySharedPreferences.setAcCode(getStudentDetailsForDirectLoginPojo.getAcCode() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getHostelCode() != null) {
            mySharedPreferences.setHostelCode(getStudentDetailsForDirectLoginPojo.getHostelCode() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getName() != null) {
            mySharedPreferences.setStudentName(getStudentDetailsForDirectLoginPojo.getName());
        }

        if (getStudentDetailsForDirectLoginPojo.getStudAdmissionNo() != null) {
            mySharedPreferences.setStudAdmissionNo(getStudentDetailsForDirectLoginPojo.getStudAdmissionNo());
        }

        if (getStudentDetailsForDirectLoginPojo.getStudEnrollmentNo() != null) {
            mySharedPreferences.setStudentEnrollmentNo(getStudentDetailsForDirectLoginPojo.getStudEnrollmentNo());
        }

        if (getStudentDetailsForDirectLoginPojo.getStudPhoto() != null) {
            mySharedPreferences.setStudentPhotoUrl(getStudentDetailsForDirectLoginPojo.getStudPhoto());
        }

        if (getStudentDetailsForDirectLoginPojo.getStatus() != null) {
            mySharedPreferences.setStatus(getStudentDetailsForDirectLoginPojo.getStatus() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getSwdDivisionId() != null) {
            mySharedPreferences.setSwdDivisionId(getStudentDetailsForDirectLoginPojo.getSwdDivisionId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getSwdBatchId() != null) {
            mySharedPreferences.setSwdBatchId(getStudentDetailsForDirectLoginPojo.getSwdBatchId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getShiftId() != null) {
            mySharedPreferences.setShiftId(getStudentDetailsForDirectLoginPojo.getShiftId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getImDomainName() != null) {
            mySharedPreferences.setImDomainName(getStudentDetailsForDirectLoginPojo.getImDomainName());
        }

        if (getStudentDetailsForDirectLoginPojo.getIntituteId() != null) {
            mySharedPreferences.setInstituteId(getStudentDetailsForDirectLoginPojo.getIntituteId() + "");
        }

        if (getStudentDetailsForDirectLoginPojo.getFcFile() != null) {
            mySharedPreferences.setFcFile(getStudentDetailsForDirectLoginPojo.getFcFile());
        }

        if (getStudentDetailsForDirectLoginPojo.getImExamDbName() != null) {
            mySharedPreferences.setImExamDbName(getStudentDetailsForDirectLoginPojo.getImExamDbName());
        }
    }


}
