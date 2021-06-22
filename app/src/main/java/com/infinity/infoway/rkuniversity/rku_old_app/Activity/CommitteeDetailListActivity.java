package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.CommitteeMemberDetailsAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetListOfCommitteeWiseMemberDetailsPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CommitteeDetailListActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    RequestQueue queue;
    ListView lv_committee_details;
    List<GetListOfCommitteeWiseMemberDetailsPojo.DataBean> committeeDetailsList = new ArrayList<>();
    CommitteeMemberDetailsAdapter committeeMemberDetailsAdapter;

    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentPageNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_detail_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        callGetCommitteeDetailsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1);


        lv_committee_details.setOnScrollListener(new AbsListView.OnScrollListener() {
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
                    callGetCommitteeDetailsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), currentPageNo);
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
        txt_act.setText("Committee Details");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        lv_committee_details = findViewById(R.id.lv_committee_details);

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
        }
    }

    private void callGetCommitteeDetailsListAPI(final String id, final String user_id, final int pageno) {

        DialogUtils.showProgressDialog(CommitteeDetailListActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_List_Of_Committee_Wise_Member_Details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetListOfCommitteeWiseMemberDetailsPojo getListOfCommitteeWiseMemberDetailsPojo = gson.fromJson("{\"Data\":" + response + "}", GetListOfCommitteeWiseMemberDetailsPojo.class);
                        if (getListOfCommitteeWiseMemberDetailsPojo.getData() != null &&
                                getListOfCommitteeWiseMemberDetailsPojo.getData().size() > 0) {
                            committeeDetailsList.addAll(getListOfCommitteeWiseMemberDetailsPojo.getData());
                            if (pageno == 1) {
                                committeeMemberDetailsAdapter = new CommitteeMemberDetailsAdapter(CommitteeDetailListActivity.this, (ArrayList<GetListOfCommitteeWiseMemberDetailsPojo.DataBean>) committeeDetailsList, true);
                                lv_committee_details.setAdapter(committeeMemberDetailsAdapter);
                            } else {
                                committeeMemberDetailsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            if (pageno == 1) {
                                if (committeeDetailsList != null) {
                                    committeeDetailsList.clear();
                                }
                                committeeMemberDetailsAdapter = new CommitteeMemberDetailsAdapter(CommitteeDetailListActivity.this, (ArrayList<GetListOfCommitteeWiseMemberDetailsPojo.DataBean>) committeeDetailsList, false);
                                lv_committee_details.setAdapter(committeeMemberDetailsAdapter);
                                DialogUtils.Show_Toast(CommitteeDetailListActivity.this, "No Data Found!");
                            } else {
                                hasMoreData = false;
                            }
                        }
                    } catch (Exception ex) {
                        Toast.makeText(CommitteeDetailListActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DialogUtils.Show_Toast(CommitteeDetailListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(CommitteeDetailListActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("user_id", user_id);
                params2.put("rowperpage", IntentConstants.ROW_PER_PAGE);
                params2.put("pageno", String.valueOf(pageno));
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

}
