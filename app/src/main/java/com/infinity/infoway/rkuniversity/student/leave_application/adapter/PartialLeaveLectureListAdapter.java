package com.infinity.infoway.rkuniversity.student.leave_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.leave_application.fragment.ApplyILeaveFragment;
import com.infinity.infoway.rkuniversity.student.leave_application.pojo.SelectLectureForPartialLeavePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class PartialLeaveLectureListAdapter extends RecyclerView.Adapter<PartialLeaveLectureListAdapter.MyViewHolder> {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<SelectLectureForPartialLeavePojo.TableBean> tableBeanArrayList;
    ISelectLecture iSelectLecture;

    public PartialLeaveLectureListAdapter(Context context, ApplyILeaveFragment applyLeaveFragment, ArrayList<SelectLectureForPartialLeavePojo.TableBean> tableBeanArrayList) {
        this.context = context;
        this.tableBeanArrayList = tableBeanArrayList;
        layoutInflater = LayoutInflater.from(context);
        iSelectLecture = (ISelectLecture) applyLeaveFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_partial_leave_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SelectLectureForPartialLeavePojo.TableBean tableBean = tableBeanArrayList.get(position);

        if (tableBeanArrayList.get(position).isSelected()) {
            holder.ivSelectLecture.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_select_lecture_colored));
        } else {
            holder.ivSelectLecture.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_close_new));
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(tableBean.getDLLECNO())) {
            holder.tvLectureName.setText("Lecture - " + tableBean.getDLLECNO());
        }

        holder.llSelectLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableBeanArrayList.get(position).setSelected(!tableBeanArrayList.get(position).isSelected());
                iSelectLecture.onLectureSelected(tableBeanArrayList);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tableBeanArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextViewRegularFont tvLectureName;
        AppCompatImageView ivSelectLecture;
        LinearLayout llSelectLecture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLectureName = itemView.findViewById(R.id.tvLectureName);
            ivSelectLecture = itemView.findViewById(R.id.ivSelectLecture);
            llSelectLecture = itemView.findViewById(R.id.llSelectLecture);
        }
    }

    public interface ISelectLecture {
        void onLectureSelected(ArrayList<SelectLectureForPartialLeavePojo.TableBean> tableBeanArrayList);
    }

}
