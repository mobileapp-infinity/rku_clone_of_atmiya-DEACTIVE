package com.infinity.infoway.rkuniversity.student.holiday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

public class HolidayActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivCloseHolidays;
    ConnectionDetector connectionDetector;
    LinearLayout llStudentHolidayList, llHolidayProgressbar, llNoDataFoundHoliday;
    RecyclerView rvHolidayStudent;
    MaterialCardView cvStudentHolidayListHeader;
    AppCompatImageView imClearSearch;
    AppCompatEditText edSearchHolidayName;
    HolidayListAdapter holidayListAdapter;
    ArrayList<HolidayListPojo> holidayListStudentModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);
        initView();
        getHolidayListApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(HolidayActivity.this);
        connectionDetector = new ConnectionDetector(HolidayActivity.this);
        ivCloseHolidays = findViewById(R.id.ivCloseHolidays);
        ivCloseHolidays.setOnClickListener(this);
        rvHolidayStudent = findViewById(R.id.rvHolidayStudent);
        llStudentHolidayList = findViewById(R.id.llStudentHolidayList);
        llHolidayProgressbar = findViewById(R.id.llHolidayProgressbar);
        llNoDataFoundHoliday = findViewById(R.id.llNoDataFoundHoliday);
        cvStudentHolidayListHeader = findViewById(R.id.cvStudentHolidayListHeader);
        imClearSearch = findViewById(R.id.imClearSearch);
        imClearSearch.setOnClickListener(this);
        edSearchHolidayName = findViewById(R.id.edSearchHolidayName);
        edSearchHolidayName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    imClearSearch.setVisibility(View.VISIBLE);
                } else {
                    imClearSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }


    void filter(String text) {
        ArrayList<HolidayListPojo> updatedList = new ArrayList();
        for (HolidayListPojo holidayListStudentModel : holidayListStudentModelArrayList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (holidayListStudentModel.getHolidayName().toLowerCase().contains(text.toLowerCase())) {
                updatedList.add(holidayListStudentModel);
            }
        }

        if (updatedList.size() > 0) {
            cvStudentHolidayListHeader.setVisibility(View.VISIBLE);
        } else {
            cvStudentHolidayListHeader.setVisibility(View.GONE);
        }

        //update recyclerview
        holidayListAdapter.updateList(updatedList);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseHolidays) {
            onBackPressed();
        } else if (v.getId() == R.id.imClearSearch) {
            edSearchHolidayName.setText("");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getHolidayListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llStudentHolidayList.setVisibility(View.GONE);
            llHolidayProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundHoliday.setVisibility(View.GONE);
            ApiImplementer.studentHolidayListApiImplementer(mySharedPreferences.getStudentId(), new Callback<ArrayList<HolidayListPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<HolidayListPojo>> call, Response<ArrayList<HolidayListPojo>> response) {
                    llHolidayProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().size() > 0) {
                        llStudentHolidayList.setVisibility(View.VISIBLE);
                        llNoDataFoundHoliday.setVisibility(View.GONE);
                        holidayListStudentModelArrayList = response.body();
                        holidayListAdapter = new HolidayListAdapter(HolidayActivity.this, holidayListStudentModelArrayList);
                        rvHolidayStudent.setAdapter(holidayListAdapter);
                    } else {
                        llStudentHolidayList.setVisibility(View.GONE);
                        llNoDataFoundHoliday.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<HolidayListPojo>> call, Throwable t) {
                    llStudentHolidayList.setVisibility(View.GONE);
                    llHolidayProgressbar.setVisibility(View.GONE);
                    llNoDataFoundHoliday.setVisibility(View.VISIBLE);
                    Toast.makeText(HolidayActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}