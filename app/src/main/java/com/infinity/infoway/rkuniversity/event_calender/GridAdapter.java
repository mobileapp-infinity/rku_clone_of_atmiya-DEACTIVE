package com.infinity.infoway.rkuniversity.event_calender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.internal.service.Common;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.event_calender.listeners.DateSelectListener;
import com.infinity.infoway.rkuniversity.event_calender.listeners.GridChangeListener;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GridAdapter extends ArrayAdapter {

    private SlyCalendarData calendarData;
    private int shiftMonth;
    private ArrayList<Date> monthlyDates = new ArrayList<>();
    private DateSelectListener listener;
    private LayoutInflater inflater;
    private GridChangeListener gridListener;
    private ArrayList<EventModel> eventModelArrayList;
    private Context ctx;
    private IGridAdapterData iGridAdapterData;
    private int SELECTED_CELL = -1;
    private String currentDate = "";
    private Calendar calendar;

    public GridAdapter(Context ctx, ArrayList<EventModel> eventModelArrayList, @NonNull Context context, @NonNull SlyCalendarData calendarData, int shiftMonth, @Nullable DateSelectListener listener, GridChangeListener gridListener) {
        super(context, R.layout.slycalendar_single_cell);
        this.ctx = ctx;
        iGridAdapterData = (IGridAdapterData) ctx;
        this.calendarData = calendarData;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
        this.shiftMonth = shiftMonth;
        this.gridListener = gridListener;
        this.eventModelArrayList = eventModelArrayList;
        calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        currentDate = dateFormat.format(calendar.getTime());
        init();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(monthlyDates.get(position));

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.slycalendar_single_cell, parent, false);
        }

        TextView txtDate;
        LinearLayout llEventPointer;
        FrameLayout cellView;

        txtDate = ((TextView) view.findViewById(R.id.txtDate));
        llEventPointer = view.findViewById(R.id.llEventPointer);
        cellView = view.findViewById(R.id.cellView);
        String cellContent = String.valueOf(dateCal.get(Calendar.DAY_OF_MONTH));
        if (cellContent.length() == 1) {
            cellContent = "0" + cellContent;
        }
        txtDate.setText(cellContent);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String cellDate = dateFormat.format(dateCal.getTime());

        if (CommonUtil.IS_EVENT_CAL_LOADED_FIRST_TIME && cellDate.equalsIgnoreCase(currentDate)){
            FrameLayout frameLayout = (FrameLayout) cellView;
            if (frameLayout.getTag() != null) {
                ArrayList<EventModel.Array> arrayList = (ArrayList<EventModel.Array>) frameLayout.getTag();
                iGridAdapterData.gridAdapterData(arrayList);
            }
            SELECTED_CELL = position;
            notifyDataSetChanged();
        }

        ArrayList<EventModel.Array> eventDetailsArrayList;
        for (int l = 0; l < eventModelArrayList.size(); l++) {
            eventDetailsArrayList = new ArrayList<>();
            if (cellDate.equalsIgnoreCase(eventModelArrayList.get(l).getDate())) {
                eventDetailsArrayList = (ArrayList<EventModel.Array>) eventModelArrayList.get(l).getArray();
                cellView.setTag(eventDetailsArrayList);
                llEventPointer.setVisibility(View.VISIBLE);
                break;
            } else {
                llEventPointer.setVisibility(View.GONE);
                cellView.setTag(eventDetailsArrayList);
            }
        }
        cellView.setBackgroundResource(R.color.slycalendar_defBackgroundColor);

        cellView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.IS_EVENT_CAL_LOADED_FIRST_TIME = false;
                FrameLayout frameLayout = (FrameLayout) v;
                if (frameLayout.getTag() != null) {
                    ArrayList<EventModel.Array> arrayList = (ArrayList<EventModel.Array>) frameLayout.getTag();
                    iGridAdapterData.gridAdapterData(arrayList);
                }
                SELECTED_CELL = position;
                notifyDataSetChanged();
            }
        });

        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(calendarData.getShowDate());
        currentDate.add(Calendar.MONTH, shiftMonth);

        view.findViewById(R.id.frameSelected).setBackgroundResource(0);
        ((TextView) view.findViewById(R.id.txtDate)).setTextColor(calendarData.getTextColor());
        if (currentDate.get(Calendar.MONTH) == dateCal.get(Calendar.MONTH)) {
            (view.findViewById(R.id.txtDate)).setAlpha(1);
        } else {
            (view.findViewById(R.id.txtDate)).setAlpha(.2f);
        }

        if (SELECTED_CELL == position) {
            cellView.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.bg_same_date));
            txtDate.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }

        return view;
    }

    @Override
    public int getCount() {
        return monthlyDates.size();
    }

    @Nullable
    @Override
    public Date getItem(int position) {
        return monthlyDates.get(position);
    }

    private void init() {
        monthlyDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(calendarData.getShowDate());
        calendar.add(Calendar.MONTH, shiftMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = calendarData.isFirstMonday() ? calendar.get(Calendar.DAY_OF_WEEK) - 2 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        int MAX_CALENDAR_COLUMN = 42;

        while (monthlyDates.size() < MAX_CALENDAR_COLUMN) {
            monthlyDates.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public interface IGridAdapterData {
        void gridAdapterData(ArrayList<EventModel.Array> eventDetailsArrayList);
    }

}