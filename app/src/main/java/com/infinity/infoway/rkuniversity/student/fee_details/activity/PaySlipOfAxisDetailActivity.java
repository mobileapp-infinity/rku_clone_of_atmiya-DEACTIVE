package com.infinity.infoway.rkuniversity.student.fee_details.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PaySlipOfAxisFeeTypePojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PaySlipOfAxisPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.GeneratePDFFileFromBase64String;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaySlipOfAxisDetailActivity extends AppCompatActivity implements View.OnClickListener {

//    private static final String SELECT_FEE_TYPE = "Select Fee Type";

    MySharedPreferences mySharedPreferences;
    AppCompatImageView ivClosePlaySlipOfAxis;
    ConnectionDetector connectionDetector;

    MaterialCardView cvSelectFeeType;
    SearchableSpinner spFeeTypeName;
    ArrayList<String> feeTypeArrayList;

    LinearLayout llPaySlipOfAxisProgressbar, llNoDataFoundPaySlipOfAxis;
    FrameLayout flStudentPaySlipOfAxis;
    ExtendedFloatingActionButton efabDownloadPaySlipOfAxis;

    String feeType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip_of_axis_detail);
        initView();
        getPaySlipOfAxisApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(PaySlipOfAxisDetailActivity.this);
        connectionDetector = new ConnectionDetector(PaySlipOfAxisDetailActivity.this);
        ivClosePlaySlipOfAxis = findViewById(R.id.ivClosePlaySlipOfAxis);
        ivClosePlaySlipOfAxis.setOnClickListener(this);
        cvSelectFeeType = findViewById(R.id.cvSelectFeeType);
        spFeeTypeName = findViewById(R.id.spFeeTypeName);
        llPaySlipOfAxisProgressbar = findViewById(R.id.llPaySlipOfAxisProgressbar);
        llNoDataFoundPaySlipOfAxis = findViewById(R.id.llNoDataFoundPaySlipOfAxis);
        flStudentPaySlipOfAxis = findViewById(R.id.flStudentPaySlipOfAxis);
        efabDownloadPaySlipOfAxis = findViewById(R.id.efabDownloadPaySlipOfAxis);
        efabDownloadPaySlipOfAxis.setOnClickListener(this);

        spFeeTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    feeType = feeTypeArrayList.get(position);
                } else {
                    feeType = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getPaySlipOfAxisApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            cvSelectFeeType.setVisibility(View.GONE);
            llPaySlipOfAxisProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundPaySlipOfAxis.setVisibility(View.GONE);
            flStudentPaySlipOfAxis.setVisibility(View.GONE);

            ApiImplementer.getFeeTypePaySlipOfAxis(mySharedPreferences.getStudentId(), new Callback<ArrayList<PaySlipOfAxisFeeTypePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<PaySlipOfAxisFeeTypePojo>> call, Response<ArrayList<PaySlipOfAxisFeeTypePojo>> response) {
                    llPaySlipOfAxisProgressbar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null &&
                            response.body().size() > 0) {
                        ArrayList<PaySlipOfAxisFeeTypePojo> paySlipOfAxisFeeTypePojoArrayList = response.body();
                        cvSelectFeeType.setVisibility(View.VISIBLE);
                        flStudentPaySlipOfAxis.setVisibility(View.VISIBLE);
                        llNoDataFoundPaySlipOfAxis.setVisibility(View.GONE);
                        feeTypeArrayList = new ArrayList<>();

                        for (int i = 0; i < paySlipOfAxisFeeTypePojoArrayList.size(); i++) {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(paySlipOfAxisFeeTypePojoArrayList.get(i).getFeeType())) {
                                feeTypeArrayList.add(paySlipOfAxisFeeTypePojoArrayList.get(i).getFeeType() + "");
                            }
                        }

                        ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>
                                (PaySlipOfAxisDetailActivity.this, R.layout.layout_dropdown_row,
                                        feeTypeArrayList);
                        bankAdapter.setDropDownViewResource(R.layout.layout_dropdown_row);
                        spFeeTypeName.setTitle("Select Fee Type");
                        spFeeTypeName.setAdapter(bankAdapter);

                    } else {
                        cvSelectFeeType.setVisibility(View.GONE);
                        flStudentPaySlipOfAxis.setVisibility(View.GONE);
                        llNoDataFoundPaySlipOfAxis.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<PaySlipOfAxisFeeTypePojo>> call, Throwable t) {
                    cvSelectFeeType.setVisibility(View.GONE);
                    flStudentPaySlipOfAxis.setVisibility(View.GONE);
                    llPaySlipOfAxisProgressbar.setVisibility(View.GONE);
                    llNoDataFoundPaySlipOfAxis.setVisibility(View.VISIBLE);
                    Toast.makeText(PaySlipOfAxisDetailActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection, Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadPayslipOfAxis(String feeType) {
        DialogUtil.showProgressDialogNotCancelable(PaySlipOfAxisDetailActivity.this, "downloading... ");
        ApiImplementer.downloadPaySlipOfAxisApiImplementer(mySharedPreferences.getStudentId(), feeType, new Callback<PaySlipOfAxisPojo>() {
            @Override
            public void onResponse(Call<PaySlipOfAxisPojo> call, Response<PaySlipOfAxisPojo> response) {
                DialogUtil.hideProgressDialog();
                if (response.isSuccessful() && response.body() != null && response.body().getBase64string() != null &&
                        !response.body().getBase64string().isEmpty()) {
                    new GeneratePDFFileFromBase64String(PaySlipOfAxisDetailActivity.this, "Pay Slip Of Axis",
                            CommonUtil.generateUniqueFileName(response.body().getFilename()),
                            response.body().getBase64string());
                } else {
                    Toast.makeText(PaySlipOfAxisDetailActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaySlipOfAxisPojo> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(PaySlipOfAxisDetailActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivClosePlaySlipOfAxis) {
            onBackPressed();
        } else if (v.getId() == R.id.efabDownloadPaySlipOfAxis) {
            if (!CommonUtil.checkIsEmptyOrNullCommon(feeType) && spFeeTypeName.getSelectedItemPosition() > 0) {
                downloadPayslipOfAxis(feeType);
            } else {
                Toast.makeText(this, "Please select fee type", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}