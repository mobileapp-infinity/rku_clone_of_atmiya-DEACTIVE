package com.infinity.infoway.rkuniversity.event_calender;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.event_calender.listeners.DateSelectListener;
import com.infinity.infoway.rkuniversity.event_calender.listeners.DialogCompleteListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by psinetron on 29/11/2018.
 * http://slybeaver.ru
 */
public class SlyCalendarView extends FrameLayout implements DateSelectListener {

    private Context context;
    private SlyCalendarData slyCalendarData;

    private SlyCalendarDialog.Callback callback = null;

    private DialogCompleteListener completeListener = null;

    private AttributeSet attrs = null;
    private int defStyleAttr = 0;
    public GridAdapter gridAdapterTest;
    //    private Date startDate;
//    private Date endDate;
    private ArrayList<EventModel> eventModelArrayList;


    public SlyCalendarView(Context context) {
        super(context);
        init(null, 0);
    }

    public SlyCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
    }

    public SlyCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;
        this.defStyleAttr = defStyleAttr;

    }

    public void setCallback(@Nullable SlyCalendarDialog.Callback callback) {
        this.callback = callback;
    }

    public void setCompleteListener(@Nullable DialogCompleteListener completeListener) {
        this.completeListener = completeListener;
    }

//    public void setSlyCalendarData(SlyCalendarData slyCalendarData, Date startDate, Date endDate) {
//        this.slyCalendarData = slyCalendarData;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        init(attrs, defStyleAttr);
//        showCalendar();
//
////        dateSelect(startDate);
////        try {
////            Thread.sleep(300);
////        }catch (Exception ex){
////
////        }
////        dateLongSelect(endDate);
//
//
//    }

    public void setSlyCalendarData(Context context, SlyCalendarData slyCalendarData, ArrayList<EventModel> eventModelArrayList) {
        this.context = context;
        this.slyCalendarData = slyCalendarData;
        this.eventModelArrayList = eventModelArrayList;
        //        this.startDate = startDate;
//        this.endDate = endDate;
        init(attrs, defStyleAttr);
        showCalendar();

//        dateSelect(startDate);
//        try {
//            Thread.sleep(300);
//        }catch (Exception ex){
//
//        }
//        dateLongSelect(endDate);


    }

    private void init(@Nullable AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.slycalendar_frame, this);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SlyCalendarView, defStyle, 0);

        if (slyCalendarData.getBackgroundColor() == null) {
            slyCalendarData.setBackgroundColor(typedArray.getColor(R.styleable.SlyCalendarView_backgroundColor, ContextCompat.getColor(getContext(), R.color.slycalendar_defBackgroundColor)));
        }
        if (slyCalendarData.getHeaderColor() == null) {
            slyCalendarData.setHeaderColor(typedArray.getColor(R.styleable.SlyCalendarView_headerColor, ContextCompat.getColor(getContext(), R.color.slycalendar_defHeaderColor)));
        }
        if (slyCalendarData.getHeaderTextColor() == null) {
            slyCalendarData.setHeaderTextColor(typedArray.getColor(R.styleable.SlyCalendarView_headerTextColor, ContextCompat.getColor(getContext(), R.color.slycalendar_defHeaderTextColor)));
        }
        if (slyCalendarData.getTextColor() == null) {
            slyCalendarData.setTextColor(typedArray.getColor(R.styleable.SlyCalendarView_textColor, ContextCompat.getColor(getContext(), R.color.slycalendar_defTextColor)));
        }
        if (slyCalendarData.getSelectedColor() == null) {
            slyCalendarData.setSelectedColor(typedArray.getColor(R.styleable.SlyCalendarView_selectedColor, ContextCompat.getColor(getContext(), R.color.slycalendar_defSelectedColor)));
        }
        if (slyCalendarData.getSelectedTextColor() == null) {
            slyCalendarData.setSelectedTextColor(typedArray.getColor(R.styleable.SlyCalendarView_selectedTextColor, ContextCompat.getColor(getContext(), R.color.slycalendar_defSelectedTextColor)));
        }

        typedArray.recycle();
        MonthPagerAdapter monthPagerAdapter = new MonthPagerAdapter(context, slyCalendarData, this, eventModelArrayList);
//        MonthPagerAdapter monthPagerAdapter = new MonthPagerAdapter(getContext(), slyCalendarData, this, startDate, endDate);
        final ViewPager vpager = findViewById(R.id.content);
        vpager.setAdapter(monthPagerAdapter);
        vpager.setCurrentItem(vpager.getAdapter().getCount() / 2);

        showCalendar();
        gridAdapterTest = monthPagerAdapter.gridAdapter;
    }

    private void showCalendar() {

        paintCalendar();
        showTime();

//        findViewById(R.id.txtCancel).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (callback != null) {
//                    callback.onCancelled();
//                }
//                if (completeListener != null) {
//                    completeListener.complete();
//                }
//            }
//        });

//        findViewById(R.id.txtSave).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (callback != null) {
//                    Calendar start = null;
//                    Calendar end = null;
//                    if (slyCalendarData.getSelectedStartDate() != null) {
//                        start = Calendar.getInstance();
//                        start.setTime(slyCalendarData.getSelectedStartDate());
//                    }
//                    if (slyCalendarData.getSelectedEndDate() != null) {
//                        end = Calendar.getInstance();
//                        end.setTime(slyCalendarData.getSelectedEndDate());
//                    }
//                    callback.onDataSelected(start, end, slyCalendarData.getSelectedHour(), slyCalendarData.getSelectedMinutes());
//                }
//                if (completeListener != null) {
//                    completeListener.complete();
//                }
//            }
//        });


        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = null;
        if (slyCalendarData.getSelectedStartDate() != null) {
            calendarStart.setTime(slyCalendarData.getSelectedStartDate());
        } else {
            calendarStart.setTime(slyCalendarData.getShowDate());
        }

        if (slyCalendarData.getSelectedEndDate() != null) {
            calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(slyCalendarData.getSelectedEndDate());
        }

//        ((TextView) findViewById(R.id.txtYear)).setText(String.valueOf(calendarStart.get(Calendar.YEAR)));


//        if (calendarEnd == null) {
//            ((TextView) findViewById(R.id.txtSelectedPeriod)).setText(
//                    new SimpleDateFormat("EE, dd MMMM", Locale.getDefault()).format(calendarStart.getTime())
//            );
//        } else {
//            if (calendarStart.get(Calendar.MONTH) == calendarEnd.get(Calendar.MONTH)) {
//                ((TextView) findViewById(R.id.txtSelectedPeriod)).setText(
//                        getContext().getString(R.string.slycalendar_dates_period, new SimpleDateFormat("EE, dd", Locale.getDefault()).format(calendarStart.getTime()), new SimpleDateFormat("EE, dd MMM", Locale.getDefault()).format(calendarEnd.getTime()))
//                );
//            } else {
//                ((TextView) findViewById(R.id.txtSelectedPeriod)).setText(
//                        getContext().getString(R.string.slycalendar_dates_period, new SimpleDateFormat("EE, dd MMM", Locale.getDefault()).format(calendarStart.getTime()), new SimpleDateFormat("EE, dd MMM", Locale.getDefault()).format(calendarEnd.getTime()))
//                );
//            }
//        }


        findViewById(R.id.btnMonthPrev).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vpager = findViewById(R.id.content);
                vpager.setCurrentItem(vpager.getCurrentItem() - 1);
            }
        });

        findViewById(R.id.btnMonthNext).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vpager = findViewById(R.id.content);
                vpager.setCurrentItem(vpager.getCurrentItem() + 1);
            }
        });

//        findViewById(R.id.txtTime).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int style = R.style.SlyCalendarTimeDialogTheme;
//                if (slyCalendarData.getTimeTheme() != null) {
//                    style = slyCalendarData.getTimeTheme();
//                }
//
//                TimePickerDialog tpd = new TimePickerDialog(getContext(), style, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        slyCalendarData.setSelectedHour(hourOfDay);
//                        slyCalendarData.setSelectedMinutes(minute);
//                        showTime();
//                    }
//                }, slyCalendarData.getSelectedHour(), slyCalendarData.getSelectedMinutes(), true);
//                tpd.show();
//            }
//        });

        ViewPager vpager = findViewById(R.id.content);
        vpager.getAdapter().notifyDataSetChanged();
        vpager.invalidate();

    }

    @Override
    public void dateSelect(Date selectedDate) {
        if (slyCalendarData.getSelectedStartDate() == null || slyCalendarData.isSingle()) {
            slyCalendarData.setSelectedStartDate(selectedDate);
            showCalendar();
            return;
        }
        if (slyCalendarData.getSelectedEndDate() == null) {
            if (selectedDate.getTime() < slyCalendarData.getSelectedStartDate().getTime()) {
                slyCalendarData.setSelectedEndDate(slyCalendarData.getSelectedStartDate());
                slyCalendarData.setSelectedStartDate(selectedDate);
                showCalendar();
                return;
            } else if (selectedDate.getTime() == slyCalendarData.getSelectedStartDate().getTime()) {
                slyCalendarData.setSelectedEndDate(null);
                slyCalendarData.setSelectedStartDate(selectedDate);
                showCalendar();
                return;
            } else if (selectedDate.getTime() > slyCalendarData.getSelectedStartDate().getTime()) {
                slyCalendarData.setSelectedEndDate(selectedDate);
                showCalendar();
                return;
            }
        }
        if (slyCalendarData.getSelectedEndDate() != null) {
            slyCalendarData.setSelectedEndDate(null);
            slyCalendarData.setSelectedStartDate(selectedDate);
            showCalendar();
        }
    }

    @Override
    public void dateLongSelect(Date selectedDate) {
        slyCalendarData.setSelectedEndDate(null);
        slyCalendarData.setSelectedStartDate(selectedDate);
        showCalendar();
    }

    private void paintCalendar() {
        findViewById(R.id.mainFrame).setBackgroundColor(slyCalendarData.getBackgroundColor());
//        findViewById(R.id.headerView).setBackgroundColor(slyCalendarData.getHeaderColor());
//        ((TextView) findViewById(R.id.txtYear)).setTextColor(slyCalendarData.getHeaderTextColor());
//        ((TextView) findViewById(R.id.txtSelectedPeriod)).setTextColor(slyCalendarData.getHeaderTextColor());
//        ((TextView) findViewById(R.id.txtTime)).setTextColor(slyCalendarData.getHeaderColor());

    }


    private void showTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, slyCalendarData.getSelectedHour());
        calendar.set(Calendar.MINUTE, slyCalendarData.getSelectedMinutes());
//        ((TextView) findViewById(R.id.txtTime)).setText(
//                new SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.getTime())
//        );
    }

    public interface IDataWithSlyCalendarView {
        void dataWithSlyCalendarView(Object object);
    }
}


