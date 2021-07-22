package com.infinity.infoway.rkuniversity.student.student_pay_fee_new.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.api.Urls;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.adapter.StudentPayFeeChildListAdapter;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.adapter.StudentPayFeeHeadListAdapter;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetFeeTypePojo;
import com.infinity.infoway.rkuniversity.student.student_pay_fee_new.pojo.GetPendingFeeListFromFeeTypePojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentPayFeeNewActivity extends AppCompatActivity implements View.OnClickListener, StudentPayFeeChildListAdapter.IError {

    private static final String SELECT_PAY_FEE = "Select Pay Fee";

    private MySharedPreferences mySharedPreferences;
    private AppCompatImageView ivClosePayFee;
    private LinearLayout llFeeType;
    private SearchableSpinner spFeeType;

    private RecyclerView rvStudentFee;
    private LinearLayout llStudentFeeList;
    private LinearLayout llPayFeeProgressbar;
    private LinearLayout llNoDataFoundStudentPayFee;
    private TextViewRegularFont tvErrorTxt;
    private MaterialCardView cvError;

    private ArrayList<String> feeTypeNameArrayList;
    private HashMap<String, String> feeTypeNameAndIdHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pay_fee_new);
        initView();
        getFeeTypeApiCall();
    }

    private void initView() {
        mySharedPreferences = new MySharedPreferences(StudentPayFeeNewActivity.this);
        tvErrorTxt = findViewById(R.id.tvErrorTxt);
        cvError = findViewById(R.id.cvError);
        ivClosePayFee = findViewById(R.id.ivClosePayFee);
        ivClosePayFee.setOnClickListener(this);
        llFeeType = findViewById(R.id.llFeeType);
        spFeeType = findViewById(R.id.spFeeType);

        llStudentFeeList = findViewById(R.id.llStudentFeeList);
        rvStudentFee = findViewById(R.id.rvStudentFee);
        llPayFeeProgressbar = findViewById(R.id.llPayFeeProgressbar);
        llNoDataFoundStudentPayFee = findViewById(R.id.llNoDataFoundStudentPayFee);

//        ArrayList<String> testingArrayList = new ArrayList<>();
//        testingArrayList.add("testing1");
//        testingArrayList.add("testing2");
//        testingArrayList.add("testing3");
//        testingArrayList.add("testing4");
//
//        ArrayAdapter<String> feeTypeAdapter = new ArrayAdapter<String>
//                (StudentPayFeeNewActivity.this, R.layout.layout_for_pay_fee_new_drop_down,
//                        testingArrayList);
//        feeTypeAdapter.setDropDownViewResource(R.layout.layout_dropdown_row);
//        spFeeType.setTitle("Select Fee Type");
//        spFeeType.setAdapter(feeTypeAdapter);
        spFeeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String feeHeadId = feeTypeNameAndIdHashMap.get(feeTypeNameArrayList.get(position).trim());
                    getPendingFeeListApiCall(feeHeadId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //this is for static data settings
//    private void setStaticData() {
//
//        ArrayList<GetPendingFeeListFromFeeTypePojo> pendingFeeArrayList = new ArrayList<>();
//
//        ArrayList<GetPendingFeeListFromFeeTypePojo.Fee> feeList = new ArrayList<>();
//        GetPendingFeeListFromFeeTypePojo.Fee fee = new GetPendingFeeListFromFeeTypePojo.Fee();
//        fee.setFeeId("1");
//        fee.setHeadName("Tution Fee");
//        fee.setFeeName("Testing Fee");
//        fee.setPendingFee("3000.0");
//        fee.setIsRebate("0");
//        fee.setMinimumFee("1000");
//        feeList.add(fee);
//
//        GetPendingFeeListFromFeeTypePojo.Fee fee1 = new GetPendingFeeListFromFeeTypePojo.Fee();
//        fee1.setFeeId("2");
//        fee1.setHeadName("School Fee");
//        fee1.setFeeName("School Fee");
//        fee1.setPendingFee("5000.56");
//        fee1.setIsRebate("0");
//        fee1.setMinimumFee("0.0");
//        feeList.add(fee1);
//
//        GetPendingFeeListFromFeeTypePojo.Fee fee3 = new GetPendingFeeListFromFeeTypePojo.Fee();
//        fee3.setFeeId("4");
//        fee3.setHeadName("Sem Fee");
//        fee3.setFeeName("Sem Fee");
//        fee3.setPendingFee("1000.0");
//        fee3.setIsRebate("1");
//        fee3.setMinimumFee("0.0");
//        feeList.add(fee3);
//
//        for (int i = 0; i < 1; i++) {
//            GetPendingFeeListFromFeeTypePojo getPendingFeeListFromFeeTypePojo = new GetPendingFeeListFromFeeTypePojo();
//            getPendingFeeListFromFeeTypePojo.setStudId(i + "123");
//            getPendingFeeListFromFeeTypePojo.setSemId(i + "566");
//            getPendingFeeListFromFeeTypePojo.setSemNo(i + "");
//            getPendingFeeListFromFeeTypePojo.setHeadId("Course head");
//            getPendingFeeListFromFeeTypePojo.setTotalPeningFee("6542");
//            getPendingFeeListFromFeeTypePojo.setSemName("Semester" + i + "123");
//            getPendingFeeListFromFeeTypePojo.setFees(feeList);
//            pendingFeeArrayList.add(getPendingFeeListFromFeeTypePojo);
//        }
//        rvStudentFee.setAdapter(new StudentPayFeeHeadListAdapter(StudentPayFeeNewActivity.this, pendingFeeArrayList));
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivClosePayFee) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getFeeTypeApiCall() {
        DialogUtil.showProgressDialogNotCancelable(StudentPayFeeNewActivity.this, "");
        ApiImplementer.getFeeTypeNewApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getInstituteId(), new Callback<ArrayList<GetFeeTypePojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetFeeTypePojo>> call, Response<ArrayList<GetFeeTypePojo>> response) {
                DialogUtil.hideProgressDialog();
                try {
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        feeTypeNameArrayList = new ArrayList<>();
                        feeTypeNameArrayList.add(SELECT_PAY_FEE);
                        feeTypeNameAndIdHashMap = new HashMap<>();
                        for (int i = 0; i < response.body().size(); i++) {
                            if (!CommonUtil.checkIsEmptyOrNullCommon(response.body().get(i).getFeeHeadId()) &&
                                    !CommonUtil.checkIsEmptyOrNullCommon(response.body().get(i).getFeeHeadName())) {
                                String feeTypeName = response.body().get(i).getFeeHeadName().trim();
                                String feeTypeId = response.body().get(i).getFeeHeadId() + "";
                                feeTypeNameArrayList.add(feeTypeName);
                                feeTypeNameAndIdHashMap.put(feeTypeName, feeTypeId);
                            }
                        }
                        ArrayAdapter<String> feeTypeAdapter = new ArrayAdapter<String>
                                (StudentPayFeeNewActivity.this, R.layout.layout_dropdown_row,
                                        feeTypeNameArrayList);
                        feeTypeAdapter.setDropDownViewResource(R.layout.layout_dropdown_row);
                        spFeeType.setTitle("Select Fee Type");
                        spFeeType.setAdapter(feeTypeAdapter);
                    } else {
                        Toast.makeText(StudentPayFeeNewActivity.this, "There is no any pending fee available!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetFeeTypePojo>> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(StudentPayFeeNewActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPendingFeeListApiCall(String headId) {
        llStudentFeeList.setVisibility(View.GONE);
        llPayFeeProgressbar.setVisibility(View.VISIBLE);
        llNoDataFoundStudentPayFee.setVisibility(View.GONE);
        ApiImplementer.getPendingFeeListFromFeeTypeNewImplementer(mySharedPreferences.getStudentId(), headId, Urls.S_KEY_FOR_STUDENT_PAY_FEE, new Callback<ArrayList<GetPendingFeeListFromFeeTypePojo>>() {
            @Override
            public void onResponse(Call<ArrayList<GetPendingFeeListFromFeeTypePojo>> call, Response<ArrayList<GetPendingFeeListFromFeeTypePojo>> response) {
                try {
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                        rvStudentFee.setAdapter(new StudentPayFeeHeadListAdapter(StudentPayFeeNewActivity.this, response.body()));
                        llPayFeeProgressbar.setVisibility(View.GONE);
                        llStudentFeeList.setVisibility(View.VISIBLE);
                        llNoDataFoundStudentPayFee.setVisibility(View.GONE);
                    } else {
                        llPayFeeProgressbar.setVisibility(View.GONE);
                        llStudentFeeList.setVisibility(View.GONE);
                        llNoDataFoundStudentPayFee.setVisibility(View.VISIBLE);
                        Toast.makeText(StudentPayFeeNewActivity.this, "No Pending Fee Available!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetPendingFeeListFromFeeTypePojo>> call, Throwable t) {
                llPayFeeProgressbar.setVisibility(View.GONE);
                llStudentFeeList.setVisibility(View.GONE);
                llNoDataFoundStudentPayFee.setVisibility(View.VISIBLE);
                Toast.makeText(StudentPayFeeNewActivity.this, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onError(boolean isError, String feeType, double maxAmt, double minAmt) {
        if (isError) {
            try {
                cvError.setVisibility(View.VISIBLE);
                String msg = feeType + " can not be greater then " + maxAmt + " and less then " + minAmt + "!";
                tvErrorTxt.setText(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            cvError.setVisibility(View.GONE);
        }
    }
}