package com.infinity.infoway.rkuniversity.faculty.faculty_news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyNewsActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivCloseFacultyNews;
    TabLayout tlFacultyNews;
    ViewPager vpFacultyNews;
    LinearLayout llFacultyNews;
    LinearLayout llFacultyNewsProgressbar;
    LinearLayout llNoDataFoundFacultyNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_news);
        initView();
        getFacultyTimeTableApiCall();
    }


    private void initView() {
        mySharedPreferences = new MySharedPreferences(FacultyNewsActivity.this);
        connectionDetector = new ConnectionDetector(FacultyNewsActivity.this);
        ivCloseFacultyNews = findViewById(R.id.ivCloseFacultyNews);
        ivCloseFacultyNews.setOnClickListener(this);

        llFacultyNews = findViewById(R.id.llFacultyNews);
        llFacultyNewsProgressbar = findViewById(R.id.llFacultyNewsProgressbar);
        llNoDataFoundFacultyNews = findViewById(R.id.llNoDataFoundFacultyNews);

        tlFacultyNews = findViewById(R.id.tlFacultyNews);
        vpFacultyNews = findViewById(R.id.vpFacultyNews);

        tlFacultyNews.setupWithViewPager(vpFacultyNews);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ivCloseFacultyNews) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getFacultyTimeTableApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyNews.setVisibility(View.GONE);
            llFacultyNewsProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFacultyNews.setVisibility(View.GONE);
            ApiImplementer.getFacultyNewsDetailsApiImplementer(mySharedPreferences.getEmpInstituteId(), new Callback<ArrayList<FacultyNewsPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FacultyNewsPojo>> call, Response<ArrayList<FacultyNewsPojo>> response) {
                    try {
                        llFacultyNewsProgressbar.setVisibility(View.GONE);
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            llFacultyNews.setVisibility(View.VISIBLE);
                            llNoDataFoundFacultyNews.setVisibility(View.GONE);
                            FacultyNewsViewPagerAdapter adapter = new FacultyNewsViewPagerAdapter(getSupportFragmentManager());
                            ArrayList<FacultyNewsPojo> facultyNewsPojoArrayList = response.body();
                            boolean isFragmentAdded = false;
                            for (int i = 0; i < facultyNewsPojoArrayList.size(); i++) {
                                FacultyNewsPojo facultyNewsPojo = facultyNewsPojoArrayList.get(i);
                                if (!CommonUtil.checkIsEmptyOrNullCommon(facultyNewsPojo.getGroupName())) {
                                    String dayName = "";
//                                    if (facultyNewsPojo.getDayName().length() > 3) {
//                                        dayName = facultyNewsPojo.getDayName().substring(0, 3);
//                                    } else {
                                    dayName = facultyNewsPojo.getGroupName();
//                                    }
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(IntentConstants.FACULTY_NEWS_GROUP_WISE, (Serializable) facultyNewsPojo.getGroupNewsDetail());

                                    FacultyNewsFragment facultyNewsFragment = new FacultyNewsFragment();
                                    facultyNewsFragment.setArguments(bundle);
                                    adapter.addFragment(facultyNewsFragment, dayName);
                                    isFragmentAdded = true;
                                }
                            }
                            if (isFragmentAdded) {
                                vpFacultyNews.setAdapter(adapter);
                            }
                        } else {
                            llFacultyNews.setVisibility(View.GONE);
                            llNoDataFoundFacultyNews.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception ex) {
                        Toast.makeText(FacultyNewsActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FacultyNewsPojo>> call, Throwable t) {
                    llFacultyNews.setVisibility(View.GONE);
                    llFacultyNewsProgressbar.setVisibility(View.GONE);
                    llNoDataFoundFacultyNews.setVisibility(View.VISIBLE);
                    Toast.makeText(FacultyNewsActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}


