package com.infinity.infoway.rkuniversity.student.distribution_details;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.student.distribution_details.pojo.GetDistributionDetailListPojo;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DistributionDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharedPreferences mySharedPreferences;
    private AppCompatImageView ivClose;
    private RecyclerView rvDistribution;
    private LinearLayout llItemDistribution;
    private LinearLayout llDistributionPb;
    private LinearLayout llNoDataFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_details);
        initView();
        getDistributionListApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(DistributionDetailsActivity.this);
        ivClose = findViewById(R.id.ivClose);
        ivClose.setOnClickListener(this);
        llItemDistribution = findViewById(R.id.llItemDistribution);
        rvDistribution = findViewById(R.id.rvDistribution);
        llNoDataFound = findViewById(R.id.llNoDataFound);
        llDistributionPb = findViewById(R.id.llDistributionPb);
    }


    private void getDistributionListApiCall() {
        llDistributionPb.setVisibility(View.VISIBLE);
        llItemDistribution.setVisibility(View.GONE);
        llNoDataFound.setVisibility(View.GONE);
        ApiImplementer.getStudentWiseItemDistributionApiImplementer(mySharedPreferences.getInstituteId(), mySharedPreferences.getStudMainSchoolId(),
                mySharedPreferences.getStudentId(), new Callback<ArrayList<GetDistributionDetailListPojo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GetDistributionDetailListPojo>> call, Response<ArrayList<GetDistributionDetailListPojo>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            llDistributionPb.setVisibility(View.GONE);
                            llItemDistribution.setVisibility(View.VISIBLE);
                            llNoDataFound.setVisibility(View.GONE);
                            rvDistribution.setAdapter(new ItemDistributionListAdapter(DistributionDetailsActivity.this, response.body()));
                        } else {
                            llDistributionPb.setVisibility(View.GONE);
                            llItemDistribution.setVisibility(View.GONE);
                            llNoDataFound.setVisibility(View.VISIBLE);
                            Toast.makeText(DistributionDetailsActivity.this, "No Data found!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<GetDistributionDetailListPojo>> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        llDistributionPb.setVisibility(View.GONE);
                        llItemDistribution.setVisibility(View.GONE);
                        llNoDataFound.setVisibility(View.VISIBLE);
                        Toast.makeText(DistributionDetailsActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivClose) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}