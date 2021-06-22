package com.infinity.infoway.rkuniversity.student.fee_details.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.student.fee_details.adapter.FeeReceiptListAdapter;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.FeeReceiptPojo;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeReciptActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    LinearLayout llFeeReceiptProgressbar, llNoDataFoundFeeReceipt, llStudentFeeReceiptList;
    RecyclerView rvFeeReceiptStudent;
    AppCompatImageView ivCloseFeeReceipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_recipt);
        initView();
        getFeeReceiptApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(FeeReciptActivity.this);
        connectionDetector = new ConnectionDetector(FeeReciptActivity.this);
        ivCloseFeeReceipt = findViewById(R.id.ivCloseFeeReceipt);
        ivCloseFeeReceipt.setOnClickListener(this);
        llFeeReceiptProgressbar = findViewById(R.id.llAxisHdfcPaymentProgressbar);
        llNoDataFoundFeeReceipt = findViewById(R.id.llNoDataFoundFeeReceipt);
        llStudentFeeReceiptList = findViewById(R.id.llStudentFeeReceiptList);
        rvFeeReceiptStudent = findViewById(R.id.rvFeeReceiptStudent);
    }

    private void getFeeReceiptApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFeeReceiptProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundFeeReceipt.setVisibility(View.GONE);
            llStudentFeeReceiptList.setVisibility(View.GONE);
            ApiImplementer.getFeeReceiptApiImplementer(mySharedPreferences.getStudentId(), new Callback<ArrayList<FeeReceiptPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FeeReceiptPojo>> call, Response<ArrayList<FeeReceiptPojo>> response) {
                    llFeeReceiptProgressbar.setVisibility(View.GONE);
                    try {
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            llStudentFeeReceiptList.setVisibility(View.VISIBLE);
                            rvFeeReceiptStudent.setAdapter(new FeeReceiptListAdapter(FeeReciptActivity.this, (ArrayList<FeeReceiptPojo>) response.body()));
                        } else {
                            llStudentFeeReceiptList.setVisibility(View.GONE);
                            llNoDataFoundFeeReceipt.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FeeReceiptPojo>> call, Throwable t) {
                    llFeeReceiptProgressbar.setVisibility(View.GONE);
                    llStudentFeeReceiptList.setVisibility(View.GONE);
                    llNoDataFoundFeeReceipt.setVisibility(View.VISIBLE);
                    Toast.makeText(FeeReciptActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivCloseFeeReceipt) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}