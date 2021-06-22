package com.infinity.infoway.rkuniversity.student.fee_details.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewMediumFont;
import com.infinity.infoway.rkuniversity.student.fee_details.adapter.AllPendingFeeStudentAdapter;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.FeeUrlPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.GetAllPendingFeeStudentPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.GetPaymentButtonHideShowPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.GetPaymentSingleButtonHideShowPojo;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PayFeeTypePojoList;
import com.infinity.infoway.rkuniversity.student.fee_details.pojo.PayWithPaytmPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayFeeActivity extends AppCompatActivity implements View.OnClickListener, AllPendingFeeStudentAdapter.ISelectedPendingFeeListItem {

    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    AppCompatImageView ivClosePayFee;

    TextViewMediumFont tvFeeDatePayFee;
    TextViewMediumFont tvFeeTypePayFee;
    TextViewMediumFont tvTotalFeePayFee;
    TextViewMediumFont tvPaidFeePayFee;
    TextViewMediumFont tvPendingFeePayFee;
    TextViewMediumFont tvPendingFeeRefundPayFee;
    TextViewMediumFont tvDueDatePayFee;

    LinearLayout llPayFeeDetails, llPayFeeProgressbar, llNoDataFoundPayFee;

    SearchableSpinner spFeeTypePayFee;
    ArrayList<String> feeTypeNameArrayList;
    HashMap<String, String> feeTypeAndIdHashMap;
    RequestQueue queue;

    private static String IP = "";
    private MaterialCardView cvPaymentOpetions;
    //    TextViewRegularFont btnPayWithHDFC, btnPayWithAxis, btnPayWithPaytm, btnPayNow;
    LinearLayout llContent;
    MaterialCardView cvPaymentDetails;
    LinearLayout llPendingFeeList;
    RecyclerView rvPendingFeeList;
    ArrayList<GetAllPendingFeeStudentPojo> getAllPendingFeeStudentPojoArrayListSelected;
    private boolean isMultiplePendingFeePayEnabled = false;
    private String multiplePendingFeePayListItemIds = "";
    private boolean isMultiplePendingFeePaySelected = false;
    //    private MaterialRadioButton rbtnPayNow;
    private MaterialRadioButton rbtnPayWithPaytm;
    private MaterialRadioButton rbtnPayWithHDFC;
    private MaterialRadioButton rbtnPayWithAxis;
    private RadioGroup rGroupPaymentOpetions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_fee);
        initView();
        getIp();
        getPendingFeeTypeApiCall();
    }

    private void initView() {
        queue = Volley.newRequestQueue(PayFeeActivity.this);
        mySharedPreferences = new MySharedPreferences(PayFeeActivity.this);
        connectionDetector = new ConnectionDetector(PayFeeActivity.this);
        ivClosePayFee = findViewById(R.id.ivClosePayFee);
        ivClosePayFee.setOnClickListener(this);

        tvFeeDatePayFee = findViewById(R.id.tvFeeDatePayFee);
        tvFeeTypePayFee = findViewById(R.id.tvFeeTypePayFee);
        tvTotalFeePayFee = findViewById(R.id.tvTotalFeePayFee);
        tvPaidFeePayFee = findViewById(R.id.tvPaidFeePayFee);
        tvPendingFeePayFee = findViewById(R.id.tvPendingFeePayFee);
        tvPendingFeeRefundPayFee = findViewById(R.id.tvPendingFeeRefundPayFee);
        tvDueDatePayFee = findViewById(R.id.tvDueDatePayFee);

        llPayFeeDetails = findViewById(R.id.llPayFeeDetails);
        llPayFeeProgressbar = findViewById(R.id.llPayFeeProgressbar);
        llNoDataFoundPayFee = findViewById(R.id.llNoDataFoundPayFee);

        spFeeTypePayFee = findViewById(R.id.spFeeTypePayFee);
        cvPaymentOpetions = findViewById(R.id.cvPaymentOpetions);

//        rbtnPayNow = findViewById(R.id.rbtnPayNow);
        rbtnPayWithPaytm = findViewById(R.id.rbtnPayWithPaytm);
        rbtnPayWithHDFC = findViewById(R.id.rbtnPayWithHDFC);
        rbtnPayWithAxis = findViewById(R.id.rbtnPayWithAxis);

        llContent = findViewById(R.id.llContent);
        cvPaymentDetails = findViewById(R.id.cvPaymentDetails);
        llPendingFeeList = findViewById(R.id.llPendingFeeList);
        rvPendingFeeList = findViewById(R.id.rvPendingFeeList);

        spFeeTypePayFee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String feeType = feeTypeNameArrayList.get(position).trim();
                    String feeTypeId = feeTypeAndIdHashMap.get(feeType).trim();
                    llContent.setVisibility(View.VISIBLE);
                    getPaymentButtonHideShowApiCall(feeTypeId);
                    getAllPendingFeeStudentListApiCall(feeType, feeTypeId);
                } else {
                    cvPaymentOpetions.setVisibility(View.GONE);
                    llContent.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        rGroupPaymentOpetions = findViewById(R.id.rGroupPaymentOpetions);
        rGroupPaymentOpetions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.rbtnPayNow) {
//
//                } else
                if (checkedId == R.id.rbtnPayWithPaytm) {
                    if (isValid()) {
                        String feeType = feeTypeNameArrayList.get(spFeeTypePayFee.getSelectedItemPosition()).trim() + "";
                        String feeTypeId = feeTypeAndIdHashMap.get(feeType).trim() + "";
                        if (isMultiplePendingFeePayEnabled && feeTypeId.equalsIgnoreCase("1")) {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(multiplePendingFeePayListItemIds)) {
                                payWithPayTmApiCall();
                            } else {
                                rbtnPayWithPaytm.setChecked(false);
                                Toast.makeText(PayFeeActivity.this, "Please Select Fee", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            payWithPayTmApiCall();
                        }
                    } else {
                        rbtnPayWithPaytm.setChecked(false);
                    }

                } else if (checkedId == R.id.rbtnPayWithHDFC) {
                    if (isValid()) {
                        String feeType = feeTypeNameArrayList.get(spFeeTypePayFee.getSelectedItemPosition()).trim() + "";
                        String feeTypeId = feeTypeAndIdHashMap.get(feeType).trim() + "";
                        if (isMultiplePendingFeePayEnabled && feeTypeId.equalsIgnoreCase("1")) {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(multiplePendingFeePayListItemIds) &&
                                    !multiplePendingFeePayListItemIds.contains("0")) {
                                sendFeeData(multiplePendingFeePayListItemIds, "HDFC", feeTypeId, IP);
                            } else {
                                rbtnPayWithHDFC.setChecked(false);
                                Toast.makeText(PayFeeActivity.this, "Please Select Fee", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (!feeTypeId.equalsIgnoreCase("0")) {
                                sendFeeDataTerm("HDFC", feeTypeId, IP);
                            }
                        }
                    } else {
                        rbtnPayWithHDFC.setChecked(false);
                    }
                } else if (checkedId == R.id.rbtnPayWithAxis) {
                    if (isValid()) {
                        String feeType = feeTypeNameArrayList.get(spFeeTypePayFee.getSelectedItemPosition()).trim() + "";
                        String feeTypeId = feeTypeAndIdHashMap.get(feeType).trim() + "";
                        if (isMultiplePendingFeePayEnabled && feeTypeId.equalsIgnoreCase("1")) {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(multiplePendingFeePayListItemIds) &&
                                    !multiplePendingFeePayListItemIds.contains("0")) {
                                sendFeeData(multiplePendingFeePayListItemIds, "AXIS", feeTypeId, IP);
                            } else {
                                rbtnPayWithAxis.setChecked(false);
                                Toast.makeText(PayFeeActivity.this, "Please Select Fee", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (!feeTypeId.equalsIgnoreCase("0")) {
                                sendFeeDataTerm("AXIS", feeTypeId, IP);
                            }
                        }
                    }else {
                        rbtnPayWithAxis.setChecked(false);
                    }

                }
            }
        });

    }

    private void getIp() {
        String GET_IP_URL = "http://checkip.amazonaws.com";
        StringRequest req = new StringRequest(Request.Method.GET, GET_IP_URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(response)) {
                                IP = response.trim() + "";
                            } else {
                                IP = "";
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                IP = "";
            }
        });
        queue.add(req);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivClosePayFee) {
            onBackPressed();
        }
    }

    private boolean isValid() {
        boolean isValidationCorrect = true;
        if (feeTypeNameArrayList == null || feeTypeNameArrayList.size() == 0 || feeTypeAndIdHashMap == null || feeTypeAndIdHashMap.size() == 0) {
            isValidationCorrect = false;
        }
        return isValidationCorrect;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void payWithPayTmApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(PayFeeActivity.this, "");
            ApiImplementer.payWithPaytmApiImplementer(mySharedPreferences.getAcId(), mySharedPreferences.getStudAdmissionNo(), new Callback<PayWithPaytmPojo>() {
                @Override
                public void onResponse(Call<PayWithPaytmPojo> call, Response<PayWithPaytmPojo> response) {
                    DialogUtil.hideProgressDialog();
                    if (response.isSuccessful() && response.body() != null && response.body().getStatus().trim().equalsIgnoreCase("Success")) {
                        PayWithPaytmPojo payWithPaytmPojo = response.body();
                        String url = payWithPaytmPojo.getResponse();
                        if (url.length() > 5) {
                            Intent intent = new Intent(PayFeeActivity.this, PaytmPaymentActivity.class);
                            intent.putExtra("res_status", "0");
                            intent.putExtra("url", url);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(PayFeeActivity.this, "Status:- " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PayWithPaytmPojo> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(PayFeeActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getPendingFeeTypeApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llPayFeeProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundPayFee.setVisibility(View.GONE);
            llPayFeeDetails.setVisibility(View.GONE);
            ApiImplementer.getPendingFeeTypeApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(),
                    mySharedPreferences.getStudAdmissionNo(), mySharedPreferences.getAcCode(), mySharedPreferences.getHostelCode(),
                    new Callback<ArrayList<PayFeeTypePojoList>>() {
                        @Override
                        public void onResponse(Call<ArrayList<PayFeeTypePojoList>> call, Response<ArrayList<PayFeeTypePojoList>> response) {
                            llPayFeeProgressbar.setVisibility(View.GONE);
                            try {
                                if (response.isSuccessful() && response.body() != null &&
                                        response.body().size() > 0) {
                                    ArrayList<PayFeeTypePojoList> payFeeTypePojoListArrayList = response.body();
                                    llPayFeeDetails.setVisibility(View.VISIBLE);
                                    feeTypeNameArrayList = new ArrayList<>();
                                    feeTypeAndIdHashMap = new HashMap<>();

                                    for (int i = 0; i < payFeeTypePojoListArrayList.size(); i++) {
                                        PayFeeTypePojoList payFeeTypePojoList = payFeeTypePojoListArrayList.get(i);
                                        if (!CommonUtil.checkIsEmptyOrNullCommon(payFeeTypePojoList.getFeeType()) &&
                                                !CommonUtil.checkIsEmptyOrNullCommon(payFeeTypePojoList.getFeeTypeId())) {
                                            feeTypeNameArrayList.add(payFeeTypePojoList.getFeeType() + "");
                                            feeTypeAndIdHashMap.put(payFeeTypePojoList.getFeeType(), payFeeTypePojoList.getFeeTypeId() + "");
                                        }
                                    }

                                    ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>
                                            (PayFeeActivity.this, R.layout.layout_dropdown_row,
                                                    feeTypeNameArrayList);
                                    bankAdapter.setDropDownViewResource(R.layout.layout_dropdown_row);
                                    spFeeTypePayFee.setTitle("Select Fee Type");
                                    spFeeTypePayFee.setAdapter(bankAdapter);

                                } else {
                                    llPayFeeDetails.setVisibility(View.GONE);
                                    llNoDataFoundPayFee.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<PayFeeTypePojoList>> call, Throwable t) {
                            llPayFeeProgressbar.setVisibility(View.GONE);
                            llPayFeeDetails.setVisibility(View.GONE);
                            llNoDataFoundPayFee.setVisibility(View.VISIBLE);
                            Toast.makeText(PayFeeActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendFeeDataTerm(String feeSource, String feeId, String createdIp) {

        DialogUtil.showProgressDialogNotCancelable(PayFeeActivity.this, "");
        ApiImplementer.feeUrlPojoApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(), "0", //Ord_Exam_id is 0 as per old code
                mySharedPreferences.getSmId(), feeSource, feeId, createdIp, new Callback<FeeUrlPojo>() {
                    @Override
                    public void onResponse(Call<FeeUrlPojo> call, Response<FeeUrlPojo> response) {
                        DialogUtil.hideProgressDialog();
                        if (response.isSuccessful() && response.body() != null) {
                            FeeUrlPojo feeUrlPojo = response.body();

                            if (feeUrlPojo.getResStatus() != null && feeUrlPojo.getResStatus().toString().trim().compareToIgnoreCase("1") == 0) {
                                Intent intent = new Intent(PayFeeActivity.this, AxisAndHdfcPaymentWebViewActivity.class);
                                intent.putExtra("res_status", feeUrlPojo.getResStatus() + "");
                                intent.putExtra("url", feeUrlPojo.getResMessage() + "");
                                startActivity(intent);
                            } else if (feeUrlPojo.getResStatus() != null && feeUrlPojo.getResStatus().toString().trim().compareToIgnoreCase("0") == 0) {
                                if (feeUrlPojo.getResMessage() != null && feeUrlPojo.getResMessage().length() > 5) {
                                    Intent intent = new Intent(PayFeeActivity.this, AxisAndHdfcPaymentWebViewActivity.class);
                                    intent.putExtra("res_status", feeUrlPojo.getResStatus() + "");
                                    intent.putExtra("url", feeUrlPojo.getResMessage() + "");
                                    startActivity(intent);
                                }
                            }
                        } else {
                            Toast.makeText(PayFeeActivity.this, "No Data Fond!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FeeUrlPojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(PayFeeActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendFeeData(String examId, String feeSource, String feeId, String createdIp) {
        DialogUtil.showProgressDialogNotCancelable(PayFeeActivity.this, "");
        ApiImplementer.feeUrlPojoApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(), examId,
                mySharedPreferences.getSmId(), feeSource, feeId, createdIp, new Callback<FeeUrlPojo>() {
                    @Override
                    public void onResponse(Call<FeeUrlPojo> call, Response<FeeUrlPojo> response) {
                        DialogUtil.hideProgressDialog();
                        if (response.isSuccessful() && response.body() != null) {
                            FeeUrlPojo feeUrlPojo = response.body();
                            if (feeUrlPojo.getResStatus() != null && feeUrlPojo.getResStatus().toString().trim().compareToIgnoreCase("1") == 0) {

                                Intent intent = new Intent(PayFeeActivity.this, AxisAndHdfcPaymentWebViewActivity.class);
                                intent.putExtra("res_status", feeUrlPojo.getResStatus() + "");
                                intent.putExtra("url", feeUrlPojo.getResMessage() + "");
                                startActivity(intent);

                            } else if (feeUrlPojo.getResStatus() != null && feeUrlPojo.getResStatus().toString().trim().compareToIgnoreCase("0") == 0) {


                                if (feeUrlPojo.getResMessage() != null && feeUrlPojo.getResMessage().length() > 5) {
                                    Intent intent = new Intent(PayFeeActivity.this, AxisAndHdfcPaymentWebViewActivity.class);
                                    intent.putExtra("res_status", feeUrlPojo.getResStatus() + "");
                                    intent.putExtra("url", feeUrlPojo.getResMessage() + "");
                                    startActivity(intent);
                                }
                            }
                        } else {
                            Toast.makeText(PayFeeActivity.this, "No Data Fond!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FeeUrlPojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(PayFeeActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getPaymentButtonHideShowApiCall(String feeType) {
        ApiImplementer.getPaymentButtonHideShowApiImplementer(mySharedPreferences.getStudentId(), feeType, new Callback<ArrayList<GetPaymentButtonHideShowPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetPaymentButtonHideShowPojo>> call, Response<ArrayList<GetPaymentButtonHideShowPojo>> response) {
                try {
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
//                    if (response.body().get(0).getStatus().equalsIgnoreCase("0")) {
////                        cvPaymentOpetions.setVisibility(View.GONE);
//                        Toast.makeText(PayFeeActivity.this, "" + response.body().get(0).getMsg(), Toast.LENGTH_SHORT).show();
//                    } else if (response.body().get(0).getStatus().equalsIgnoreCase("1")) {
////                        cvPaymentOpetions.setVisibility(View.VISIBLE);
//                        getPaymentSingleButtonHideShowApiCall();
//                    } else {
////                        cvPaymentOpetions.setVisibility(View.GONE);
//                    }

                        if (response.body().get(0).getStatus().equalsIgnoreCase("0")) {
                            Toast.makeText(PayFeeActivity.this, "" + response.body().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                        }

                        getPaymentSingleButtonHideShowApiCall(response.body().get(0).getStatus().trim() + "");

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetPaymentButtonHideShowPojo>> call, Throwable t) {

            }
        });
    }

    private void getPaymentSingleButtonHideShowApiCall(String paymentButtonOptionStatus) {
        ApiImplementer.getPaymentSingleButtonHideShowApiImplementer(new Callback<ArrayList<GetPaymentSingleButtonHideShowPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetPaymentSingleButtonHideShowPojo>> call, Response<ArrayList<GetPaymentSingleButtonHideShowPojo>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    GetPaymentSingleButtonHideShowPojo getPaymentSingleButtonHideShowPojo = response.body().get(0);


                    if (paymentButtonOptionStatus.compareToIgnoreCase("1") == 0) {
                        cvPaymentOpetions.setVisibility(View.VISIBLE);
                    } else {
                        cvPaymentOpetions.setVisibility(View.GONE);
                    }

                    if (getPaymentSingleButtonHideShowPojo.getHDFC().equalsIgnoreCase("0")) {
                        rbtnPayWithHDFC.setVisibility(View.GONE);
                    } else {
                        rbtnPayWithHDFC.setVisibility(View.VISIBLE);
                    }


                    if (getPaymentSingleButtonHideShowPojo.getAXIS().equalsIgnoreCase("0")) {

                        rbtnPayWithAxis.setVisibility(View.GONE);
                    } else {
                        rbtnPayWithAxis.setVisibility(View.VISIBLE);
                    }


                    if (getPaymentSingleButtonHideShowPojo.getPAYTM().equalsIgnoreCase("0")) {

                        rbtnPayWithPaytm.setVisibility(View.GONE);
                    } else {
                        rbtnPayWithPaytm.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetPaymentSingleButtonHideShowPojo>> call, Throwable t) {

            }
        });
    }

    private void getAllPendingFeeStudentListApiCall(String feeTypeText, String feeTypeId) {
        DialogUtil.showProgressDialogNotCancelable(PayFeeActivity.this, "");
        ApiImplementer.getAllPendingFeeStudentApiImplementer(mySharedPreferences.getStudentId(),
                mySharedPreferences.getSwdYearId(), mySharedPreferences.getStudAdmissionNo(), "0",
                mySharedPreferences.getAcCode(), feeTypeText, mySharedPreferences.getHostelCode(), new Callback<ArrayList<GetAllPendingFeeStudentPojo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GetAllPendingFeeStudentPojo>> call, Response<ArrayList<GetAllPendingFeeStudentPojo>> response) {
                        DialogUtil.hideProgressDialog();
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {

                            GetAllPendingFeeStudentPojo getAllPendingFeeStudentPojo = response.body().get(0);

                            if (feeTypeId.equalsIgnoreCase("1")) {
                                isMultiplePendingFeePayEnabled = true;
                                cvPaymentDetails.setVisibility(View.GONE);
                                llPendingFeeList.setVisibility(View.VISIBLE);
                                rvPendingFeeList.setAdapter(new AllPendingFeeStudentAdapter(PayFeeActivity.this, response.body()));
                            } else {
                                isMultiplePendingFeePayEnabled = false;
                                cvPaymentDetails.setVisibility(View.VISIBLE);
                                llPendingFeeList.setVisibility(View.GONE);

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeFeeDate())) {
                                    tvFeeDatePayFee.setText(getAllPendingFeeStudentPojo.getFeeFeeDate() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeFeeType())) {
                                    tvFeeTypePayFee.setText(getAllPendingFeeStudentPojo.getFeeFeeType() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeTotalFee())) {
                                    tvTotalFeePayFee.setText(getAllPendingFeeStudentPojo.getFeeTotalFee() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeePaidFee())) {
                                    tvPaidFeePayFee.setText(getAllPendingFeeStudentPojo.getFeePaidFee() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeePendingFee())) {
                                    tvPendingFeePayFee.setText(getAllPendingFeeStudentPojo.getFeePendingFee() + "");
                                }

                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeePendingFeeRefund())) {
                                    tvPendingFeeRefundPayFee.setText(getAllPendingFeeStudentPojo.getFeePendingFeeRefund() + "");
                                }
                                if (!CommonUtil.checkIsEmptyOrNullCommon(getAllPendingFeeStudentPojo.getFeeDueDate())) {
                                    tvDueDatePayFee.setText(getAllPendingFeeStudentPojo.getFeeDueDate() + "");
                                }
                            }
                        } else {
                            isMultiplePendingFeePayEnabled = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<GetAllPendingFeeStudentPojo>> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        isMultiplePendingFeePayEnabled = false;
                    }
                });
    }

    @Override
    public void onPendingFeeListItemSelected(ArrayList<GetAllPendingFeeStudentPojo> getAllPendingFeeStudentPojoArrayList) {
        multiplePendingFeePayListItemIds = "";
        this.getAllPendingFeeStudentPojoArrayListSelected = getAllPendingFeeStudentPojoArrayList;
        for (int i = 0; i < getAllPendingFeeStudentPojoArrayListSelected.size(); i++) {
            if (getAllPendingFeeStudentPojoArrayListSelected.get(i).getIsPendingFeeItemIsCheckOrNot()) {
                multiplePendingFeePayListItemIds = multiplePendingFeePayListItemIds + "," + getAllPendingFeeStudentPojoArrayListSelected.get(i).getFeeExId() + "";
            }
        }
        multiplePendingFeePayListItemIds = multiplePendingFeePayListItemIds.replaceFirst(",", "");
        if (CommonUtil.checkIsEmptyOrNullCommon(multiplePendingFeePayListItemIds)) {
            isMultiplePendingFeePaySelected = false;
        } else {
            isMultiplePendingFeePaySelected = true;
        }

    }
}