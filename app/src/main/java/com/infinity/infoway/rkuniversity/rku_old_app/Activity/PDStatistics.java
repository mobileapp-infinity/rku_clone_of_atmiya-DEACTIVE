package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.PDStatisticsAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MonthYearPicker;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.PdStatisticsPojo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PDStatistics extends AppCompatActivity {

    private ListView lv_pd_statistics;
    private CardView card_leave_balance;
    private ImageView iv_back;
    private CustomBoldTextView txt_act;
    private ImageView iv_info;
    private ImageView iv_profile;
    private RelativeLayout rel;
    private Toolbar toolbar_act;
    private CoordinatorLayout toolbarContainer;
    private ImageView iv_todays_inout;
    private CustomTextView tv_count_total_pending;
    /**
     *
     */
    private CustomBoldTextView tv_emp_code;
    private CustomTextView tv_total_pending_count;
    /**
     *
     */
    private CustomBoldTextView tv_version;
    /**
     *
     */
    private CustomBoldTextView tv_version_code;
    private LinearLayout ll_bottom;
    PDStatisticsAdapter pdStatisticsAdapter;
    RequestQueue queue;
    FloatingActionButton fab;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    private MonthYearPicker myp;
    int currentMonth=0;
    int currentYear=0;
    Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdstatistics);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_act);
        setSupportActionBar(toolbar);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        txt_act.setText("PD Statistics");

        myp = new MonthYearPicker(this);
        myp.build(new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
              //  attReportDetailPojo = new AttReportDetailPojo();
//                System.out.println("select call ::::: month ::::::: " + String.valueOf(myp.getSelectedMonth() + 1) + "year ::::::: " + String.valueOf(myp.getSelectedYear()));
                //AttendanceReportApi(String.valueOf(myp.getSelectedMonth() + 1), String.valueOf(myp.getSelectedYear()));
              //  SummeryDisplayApi(String.valueOf(myp.getSelectedMonth() + 1), String.valueOf(myp.getSelectedYear()));

                //tvFromDate.setText(myp.getSelectedMonthName() + "-" + myp.getSelectedYear());
            }
        }, null);
        initView();
        PDStatisticsAPI();

        Date currentTime = localCalendar.getTime();
        int currentDay = localCalendar.get(Calendar.DATE);
        currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        currentYear = localCalendar.get(Calendar.YEAR);
        int currentDayOfWeek = localCalendar.get(Calendar.DAY_OF_WEEK);
        int currentDayOfMonth = localCalendar.get(Calendar.DAY_OF_MONTH);
        int CurrentDayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);


        System.out.println("Current Date: " + currentTime);
        System.out.println("Current Day: " + currentDay);
        System.out.println("Current Month: " + currentMonth);
        System.out.println("Current Year: " + currentYear);
        System.out.println("Current Day of Week: " + currentDayOfWeek);
        System.out.println("Current Day of Month: " + currentDayOfMonth);
        System.out.println("Current Day of Year: " + CurrentDayOfYear);
    }

    private void PDStatisticsAPI()
    {


        String url = URLS.Get_PD_Statistics + mySharedPreferecesRKUOLD.getUserID() + "";
        url = url.replace(" ", "%20");
        System.out.println("Get_PD_Statistics URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (response.length() > 10) {

                    response = response + "";
                    response = "{\"Data\":" + response + "}";

                    System.out.println("success response Get_PD_Statistics !!!!!!!!!!!!!!!!!!!" + response + "");

                    Gson gson = new Gson();
                    PdStatisticsPojo pdStatisticsPojo = gson.fromJson(response, PdStatisticsPojo.class);
                    if (pdStatisticsPojo != null)
                    {
                        if (pdStatisticsPojo.getData() != null)
                        {
                            if (pdStatisticsPojo.getData().get(0) != null)
                            {
                                if (pdStatisticsPojo.getData().size() > 0)
                                {
                                    card_leave_balance.setVisibility(View.VISIBLE);
                                    if (lv_pd_statistics != null)
                                    {
                                        card_leave_balance.setVisibility(View.VISIBLE);
                                        pdStatisticsAdapter = new PDStatisticsAdapter(PDStatistics.this, pdStatisticsPojo);
                                        lv_pd_statistics.setAdapter(pdStatisticsAdapter);

                                    }

                                } else {
                                    card_leave_balance.setVisibility(View.GONE);
                                    DialogUtils.Show_Toast(PDStatistics.this, "No Records Found");

                                }

                            }
                        }
                    }
                } else {
                    card_leave_balance.setVisibility(View.GONE);
                    DialogUtils.Show_Toast(PDStatistics.this, "No Records Found");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PDStatistics.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                //System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        queue = Volley.newRequestQueue(this);
        fab =(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myp.show();
            }
        });


        lv_pd_statistics = (ListView) findViewById(R.id.lv_pd_statistics);
        card_leave_balance = (CardView) findViewById(R.id.card_leave_balance);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        txt_act = (CustomBoldTextView) findViewById(R.id.txt_act);
        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        rel = (RelativeLayout) findViewById(R.id.rel);
        toolbar_act = (Toolbar) findViewById(R.id.toolbar_act);
        toolbarContainer = (CoordinatorLayout) findViewById(R.id.toolbarContainer);
        iv_todays_inout = (ImageView) findViewById(R.id.iv_todays_inout);
        tv_count_total_pending = (CustomTextView) findViewById(R.id.tv_count_total_pending);
        tv_emp_code = (CustomBoldTextView) findViewById(R.id.tv_emp_code);
        tv_total_pending_count = (CustomTextView) findViewById(R.id.tv_total_pending_count);
        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version_code = (CustomBoldTextView) findViewById(R.id.tv_version_code);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);

        PackageInfo pInfo = null;
        assert pInfo != null;

        try
        {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        tv_emp_code = (CustomBoldTextView) findViewById(R.id.tv_emp_code);
        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version_code = (CustomBoldTextView) findViewById(R.id.tv_version_code);
        tv_version.setText(pInfo.versionName);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

    }
}
