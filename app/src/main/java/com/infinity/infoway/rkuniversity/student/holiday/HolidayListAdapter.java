package com.infinity.infoway.rkuniversity.student.holiday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<HolidayListPojo> holidayListStudentModelArrayList;


    public HolidayListAdapter(Context context, ArrayList<HolidayListPojo> holidayListStudentModelArrayList) {
        this.context = context;
        this.holidayListStudentModelArrayList = holidayListStudentModelArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.inflater_student_holiday_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {//7984328847

        if (i % 2 == 0) {
            myViewHolder.llMain.setBackground(ContextCompat.getDrawable(context, R.color.exam_module_row_color));
        } else {
            myViewHolder.llMain.setBackground(ContextCompat.getDrawable(context, R.color.white));
        }

        if (holidayListStudentModelArrayList.get(i).getHolidayName() != null &&
                !holidayListStudentModelArrayList.get(i).getHolidayName().isEmpty()) {
            myViewHolder.tvHolidayName.setText(holidayListStudentModelArrayList.get(i).getHolidayName());
        }

        if (holidayListStudentModelArrayList.get(i).getFromDate() != null &&
                !holidayListStudentModelArrayList.get(i).getFromDate().isEmpty()) {
            myViewHolder.tvHolidayFromDate.setText(holidayListStudentModelArrayList.get(i).getFromDate());
        }

        if (holidayListStudentModelArrayList.get(i).getToDate() != null &&
                !holidayListStudentModelArrayList.get(i).getToDate().isEmpty()) {
            myViewHolder.tvHolidayToDate.setText(holidayListStudentModelArrayList.get(i).getToDate());
        }

        if (holidayListStudentModelArrayList.get(i).getDescription() != null &&
                !holidayListStudentModelArrayList.get(i).getDescription().isEmpty()) {
            myViewHolder.tvHolidayDescription.setText(holidayListStudentModelArrayList.get(i).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return holidayListStudentModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextViewRegularFont tvHolidayName, tvHolidayFromDate, tvHolidayToDate, tvHolidayDescription;
        LinearLayout llMain;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHolidayName = itemView.findViewById(R.id.tvHolidayName);
            tvHolidayFromDate = itemView.findViewById(R.id.tvHolidayFromDate);
            tvHolidayToDate = itemView.findViewById(R.id.tvHolidayToDate);
            tvHolidayDescription = itemView.findViewById(R.id.tvHolidayDescription);
            llMain = itemView.findViewById(R.id.llMain);
        }
    }

    public void updateList(ArrayList<HolidayListPojo> holidayListStudentModelArrayListNew) {
        this.holidayListStudentModelArrayList = holidayListStudentModelArrayListNew;
        notifyDataSetChanged();
    }
}
