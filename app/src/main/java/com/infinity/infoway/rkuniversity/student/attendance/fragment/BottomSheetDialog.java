package com.infinity.infoway.rkuniversity.student.attendance.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    String date;
    String time;
    String classOrLab;
    String subjectName;
    String professorName;
    String absendPresentStatus;
    TextViewMediumFont tvLectureDateAndTime;
    TextViewRegularFont tvClassOrLab;
    TextViewRegularFont tvSubjectName;
    TextViewRegularFont tvProfessorName;
    AppCompatImageView imgAbsentPresent;
    TextViewRegularFont tvAbsentPresent;

    public BottomSheetDialog() {

    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    public BottomSheetDialog(String date, String time, String classOrLab, String subjectName, String professorName, String absendPresentStatus) {
        this.date = date;
        this.time = time;
        this.classOrLab = classOrLab;
        this.subjectName = subjectName;
        this.professorName = professorName;
        this.absendPresentStatus = absendPresentStatus;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);
        initView(view);
        setData();
        return view;
    }

    private void initView(View view) {
        tvLectureDateAndTime = view.findViewById(R.id.tvLectureDateAndTime);
        tvClassOrLab = view.findViewById(R.id.tvClassOrLab);
        tvSubjectName = view.findViewById(R.id.tvSubjectName);
        tvProfessorName = view.findViewById(R.id.tvProfessorName);
        imgAbsentPresent = view.findViewById(R.id.imgAbsentPresent);
        tvAbsentPresent = view.findViewById(R.id.tvAbsentPresent);
    }

    private void setData() {
        tvLectureDateAndTime.setText(date + " | " + time);
        tvClassOrLab.setText(classOrLab);
        tvSubjectName.setText(subjectName);
        tvProfessorName.setText(professorName);

        if (absendPresentStatus.equalsIgnoreCase("A")) {
            imgAbsentPresent.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_absent));
            tvAbsentPresent.setText(" -Absent");
            tvAbsentPresent.setTextColor(getResources().getColor(R.color.status_absent_color));
        } else if (absendPresentStatus.equalsIgnoreCase("R")) {
            imgAbsentPresent.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_remaining_attendance));
            tvAbsentPresent.setText(" -Remaining");
            tvAbsentPresent.setTextColor(getResources().getColor(R.color.status_remaining_color));
        } else if (absendPresentStatus.equalsIgnoreCase("H")) {
            imgAbsentPresent.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_holiday));
            tvAbsentPresent.setText(" -Holiday");
            tvAbsentPresent.setTextColor(getResources().getColor(R.color.status_holiday_color));
        } else if (absendPresentStatus.equalsIgnoreCase("S")) {
            imgAbsentPresent.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_suspended));
            tvAbsentPresent.setText(" -Period Suspend");
            tvAbsentPresent.setTextColor(getResources().getColor(R.color.status_period_suspend_color));
        }

    }

}
