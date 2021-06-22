package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.InvestmentListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.InvestmentListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class InvestmentListActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    FloatingActionButton fabAddInvestment;
    RequestQueue queue;
    // Switch cb_check_investment_list;
    ListView lv_investment;
    List<InvestmentListPojo.DataBean> investmentList = new ArrayList<>();
    InvestmentListAdapter investmentListAdapter;

    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentPageNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        callGetInvestmentListAPI(mySharedPreferecesRKUOLD.getEmpID(), 1);

//        if (!cb_check_investment_list.isChecked())
//        {
//            callGetSeedMoneyAPI("0", mySharedPrefereces.getUserID(), URLS.RowsPerPage,"1","1" );
//        }

        lv_investment.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((hasMoreData) && (isScrolling) && (firstVisibleItem + visibleItemCount == totalItemCount)) {
                    isScrolling = false;
                    currentPageNo = currentPageNo + 1;

                    callGetInvestmentListAPI(mySharedPreferecesRKUOLD.getEmpID(), currentPageNo);
                }
            }
        });


    }

    private void initView() {
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("Investment List");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        lv_investment = findViewById(R.id.lv_investment);

        fabAddInvestment = findViewById(R.id.fabAddInvestment);
        fabAddInvestment.setOnClickListener(this);

//        cb_check_investment_list = findViewById(R.id.cb_check_investment_list);
//
//        cb_check_investment_list.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked)
//                    callGetSeedMoneyAPI("0", mySharedPrefereces.getUserID(), URLS.RowsPerPage,"1","2" );
//                else
//                    callGetSeedMoneyAPI("0", mySharedPrefereces.getUserID(), URLS.RowsPerPage,"1","1" );
//            }
//        });


        PackageInfo pInfo = null;
        assert pInfo != null;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tv_version = (CustomBoldTextView) findViewById(R.id.tv_version);
        tv_version.setText(pInfo.versionName);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.fabAddInvestment) {
            Intent intent = new Intent(InvestmentListActivity.this, InvestmentActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_ADD_INVESTMENT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_ADD_INVESTMENT && resultCode == RESULT_OK) {
            resetListData();
            callGetInvestmentListAPI(mySharedPreferecesRKUOLD.getEmpID(), 1);
        } else if (requestCode == IntentConstants.REQUEST_CODE_FOR_EDIT__INVESTMENT && resultCode == RESULT_OK) {
            resetListData();
            callGetInvestmentListAPI(mySharedPreferecesRKUOLD.getEmpID(), 1);
        }
    }

    private void callGetInvestmentListAPI(final String emp_id,
                                          final int PageNumber) {
        DialogUtils.showProgressDialog(InvestmentListActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_Investment_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        InvestmentListPojo investmentListPojo = gson.fromJson("{\"Data\":" + response + "}", InvestmentListPojo.class);
                        if (investmentListPojo.getData() != null &&
                                investmentListPojo.getData().size() > 0) {
                            investmentList.addAll(investmentListPojo.getData());
                            if (PageNumber == 1) {
                                investmentListAdapter = new InvestmentListAdapter(InvestmentListActivity.this, (ArrayList<InvestmentListPojo.DataBean>) investmentList, true);
                                lv_investment.setAdapter(investmentListAdapter);
                            } else {
                                investmentListAdapter.notifyDataSetChanged();
                            }
                        } else {
                            if (PageNumber == 1) {
                                if (investmentList != null) {
                                    investmentList.clear();
                                }
                                investmentListAdapter = new InvestmentListAdapter(InvestmentListActivity.this, (ArrayList<InvestmentListPojo.DataBean>) investmentList, true);
                                lv_investment.setAdapter(investmentListAdapter);
                                DialogUtils.Show_Toast(InvestmentListActivity.this, "No Data Found!");
                            } else {
                                hasMoreData = false;
                            }
                        }
                    } catch (Exception ex) {
                        Toast.makeText(InvestmentListActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DialogUtils.Show_Toast(InvestmentListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(InvestmentListActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("emp_id", emp_id);
                params2.put("RowsPerPage", IntentConstants.ROW_PER_PAGE);
                params2.put("PageNumber", String.valueOf(PageNumber));
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);
    }


    private void resetListData() {
        isScrolling = false;
        hasMoreData = true;
        currentPageNo = 1;
        if (investmentList != null) {
            investmentList.clear();
            investmentList = new ArrayList<>();
        }
    }

}
