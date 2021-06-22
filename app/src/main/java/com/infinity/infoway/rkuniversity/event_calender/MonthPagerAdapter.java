package com.infinity.infoway.rkuniversity.event_calender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.event_calender.listeners.DateSelectListener;
import com.infinity.infoway.rkuniversity.event_calender.listeners.GridChangeListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by psinetron on 06/12/2018.
 * http://slybeaver.ru
 */
public class MonthPagerAdapter extends PagerAdapter {

    private SlyCalendarData slyCalendarData;
    private DateSelectListener listener;
    private ArrayList tags = new ArrayList();
    private final String TAG_PREFIX = "SLY_CAL_TAG";
//    private IGridAdapter iGridAdapter;
    GridAdapter gridAdapter = null;
//    private Date startDate;
//    private Date endDate;
    private Context context;
    private ArrayList<EventModel> eventModelArrayList;

    MonthPagerAdapter(Context context,SlyCalendarData slyCalendarData, DateSelectListener listener,ArrayList<EventModel> eventModelArrayList) {
        this.slyCalendarData = slyCalendarData;
        this.listener = listener;
//        this.startDate = startDate;
//        this.endDate = endDate;
        this.context = context;
        this.eventModelArrayList = eventModelArrayList;
//        iGridAdapter = (IGridAdapter) context;

    }

//    MonthPagerAdapter(Context context,SlyCalendarData slyCalendarData, DateSelectListener listener, Date startDate, Date endDate) {
//        this.slyCalendarData = slyCalendarData;
//        this.listener = listener;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.context = context;
////        iGridAdapter = (IGridAdapter) context;
//
//    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        tags.remove(((View) object).getTag().toString());
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        int indexShift = position - (getCount() / 2);


        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        final ViewGroup view = (ViewGroup) inflater.inflate(R.layout.slycalendar_calendar, container, false);

        gridAdapter = new GridAdapter(context,eventModelArrayList,container.getContext(), slyCalendarData, indexShift, listener, new GridChangeListener() {
            @Override
            public void gridChanged() {
                tags.add(TAG_PREFIX + (position + 1));
                tags.add(TAG_PREFIX + (position - 1));
                notifyDataSetChanged();
            }
        });

//        gridAdapter = new GridAdapter(startDate,endDate,container.getContext(), slyCalendarData, indexShift, listener, new GridChangeListener() {
//            @Override
//            public void gridChanged() {
//                tags.add(TAG_PREFIX + (position + 1));
//                tags.add(TAG_PREFIX + (position - 1));
//                notifyDataSetChanged();
//            }
//        });
//        iGridAdapter.gridAdapterObj(adapter);
        ((GridView) view.findViewById(R.id.calendarGrid)).setAdapter(gridAdapter);

        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(slyCalendarData.getShowDate());
        currentDate.add(Calendar.MONTH, indexShift);

        ((TextView) view.findViewById(R.id.txtSelectedMonth)).setText(new SimpleDateFormat("LLLL yyyy", Locale.getDefault()).format(currentDate.getTime()));

        view.setTag(TAG_PREFIX + position);
        container.addView(view);

        Calendar weekDays = Calendar.getInstance();
        weekDays.set(Calendar.DAY_OF_WEEK, slyCalendarData.isFirstMonday() ? 2 : 1);
        ((TextView) view.findViewById(R.id.day1)).setText(new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(0, 1).toUpperCase() + new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(1));
        weekDays.set(Calendar.DAY_OF_WEEK, slyCalendarData.isFirstMonday() ? 3 : 2);
        ((TextView) view.findViewById(R.id.day2)).setText(new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(0, 1).toUpperCase() + new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(1));
        weekDays.set(Calendar.DAY_OF_WEEK, slyCalendarData.isFirstMonday() ? 4 : 3);
        ((TextView) view.findViewById(R.id.day3)).setText(new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(0, 1).toUpperCase() + new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(1));
        weekDays.set(Calendar.DAY_OF_WEEK, slyCalendarData.isFirstMonday() ? 5 : 4);
        ((TextView) view.findViewById(R.id.day4)).setText(new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(0, 1).toUpperCase() + new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(1));
        weekDays.set(Calendar.DAY_OF_WEEK, slyCalendarData.isFirstMonday() ? 6 : 5);
        ((TextView) view.findViewById(R.id.day5)).setText(new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(0, 1).toUpperCase() + new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(1));
        weekDays.set(Calendar.DAY_OF_WEEK, slyCalendarData.isFirstMonday() ? 7 : 6);
        ((TextView) view.findViewById(R.id.day6)).setText(new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(0, 1).toUpperCase() + new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(1));
        weekDays.set(Calendar.DAY_OF_WEEK, slyCalendarData.isFirstMonday() ? 1 : 7);
        ((TextView) view.findViewById(R.id.day7)).setText(new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(0, 1).toUpperCase() + new SimpleDateFormat("EE", Locale.getDefault()).format(weekDays.getTime()).substring(1));


        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        String tag = ((ViewGroup) object).getTag().toString();
        if (tags.contains(tag)) {
            tags.remove(tag);
            return POSITION_NONE;
        }
        return POSITION_UNCHANGED;

    }


}
