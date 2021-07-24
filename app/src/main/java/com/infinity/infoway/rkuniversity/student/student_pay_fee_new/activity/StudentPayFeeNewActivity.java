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
                    if (response.isSuccessful() && response.body() != null && response.body().size() > 0 &&
                            response.body().get(0).getStatusCode().equalsIgnoreCase("1")) {
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
                                (StudentPayFeeNewActivity.this, R.layout.layout_for_pay_fee_new_drop_down,
                                        feeTypeNameArrayList);
                        feeTypeAdapter.setDropDownViewResource(R.layout.layout_for_pay_fee_new_drop_down);
                        spFeeType.setTitle("Select Fee Type");
                        spFeeType.setAdapter(feeTypeAdapter);
                    } else {
                        Toast.makeText(StudentPayFeeNewActivity.this, "There is no any pending fee available!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (Exception ex) {
                    Toast.makeText(StudentPayFeeNewActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
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
        ApiImplementer.getPendingFeeListFromFeeTypeNewImplementer(mySharedPreferences.getStudentId(), headId, mySharedPreferences.getInstituteId(), new Callback<ArrayList<GetPendingFeeListFromFeeTypePojo>>() {
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