package com.infinity.infoway.rkuniversity.student.exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.exam.pojo.CIAExamResultPojo;
import com.infinity.infoway.rkuniversity.student.exam.pojo.StudentReulstPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.GeneratePDFFileFromBase64String;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<StudentReulstPojo> studentReulstPojoArrayList;
//    ProgressDialog progressDialog;

    public ResultListAdapter(Context context, ArrayList<StudentReulstPojo> studentReulstPojoArrayList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.studentReulstPojoArrayList = studentReulstPojoArrayList;
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Please wait....");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_cia_marks_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        StudentReulstPojo studentReulstPojo = studentReulstPojoArrayList.get(position);

//        if (position % 2 == 0) {
//            holder.llCIAMarksListMain.setBackground(ContextCompat.getDrawable(context, R.color.white));
//        } else {
//            holder.llCIAMarksListMain.setBackground(ContextCompat.getDrawable(context, R.color.exam_module_row_color));
//        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(studentReulstPojo.getProgramname())) {
            holder.tvCiaProgramName.setText(studentReulstPojo.getProgramname() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(studentReulstPojo.getSemesterName())) {
            holder.tvCIASemName.setText(studentReulstPojo.getSemesterName() + "");
        }
        if (!CommonUtil.checkIsEmptyOrNullCommon(studentReulstPojo.getYearName())) {
            holder.tvCIAExamination.setText(studentReulstPojo.getYearName() + "");
        }

        holder.tvCIAResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                downloadCIAResultApiCall(studentReulstPojo.getSwdCollegeId() + "",
//                        studentReulstPojo.getSwdYearId() + "", studentReulstPojo.getSwdSemId() + "",
//                        studentReulstPojo.getExamId() + "", studentReulstPojo.getStudentId() + "");
            }
        });

    }

//    private void downloadCIAResultApiCall(String Collegeid, String Yearid, String Semid,
//                                          String Exam_id, String stud_id) {
////        progressDialog.show();
//        DialogUtil.showProgressDialogNotCancelable(context, "downloading... ");
//        ApiImplementer.downloadCIAExamResultApiImplementer(Collegeid, Yearid, Semid, Exam_id, stud_id, new Callback<CIAExamResultPojo>() {
//            @Override
//            public void onResponse(Call<CIAExamResultPojo> call, Response<CIAExamResultPojo> response) {
//                DialogUtil.hideProgressDialog();
//                if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 1 &&
//                        response.body().getBase64string() != null && !response.body().getBase64string().isEmpty()) {
//                    new GeneratePDFFileFromBase64String(context, "CIA Marks", CommonUtil.generateUniqueFileName(response.body().getFilename()), response.body().getBase64string());
//                } else {
////                    progressDialog.hide();
//                    Toast.makeText(context, "some thing went wrong please try again later.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CIAExamResultPojo> call, Throwable t) {
//                DialogUtil.hideProgressDialog();
////                progressDialog.hide();
//                Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }


    @Override
    public int getItemCount() {
        return studentReulstPojoArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //        LinearLayout llCIAMarksListMain;
        TextViewMediumFont tvCiaProgramName, tvCIASemName, tvCIAExamination;
        TextViewRegularFont tvCIAResult;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCiaProgramName = itemView.findViewById(R.id.tvCiaProgramName);
            tvCIASemName = itemView.findViewById(R.id.tvCIASemName);
            tvCIAExamination = itemView.findViewById(R.id.tvCIAExamination);
            tvCIAResult = itemView.findViewById(R.id.tvCIAResult);
//            llCIAMarksListMain = itemView.findViewById(R.id.llCIAMarksListMain);
        }
    }

}
