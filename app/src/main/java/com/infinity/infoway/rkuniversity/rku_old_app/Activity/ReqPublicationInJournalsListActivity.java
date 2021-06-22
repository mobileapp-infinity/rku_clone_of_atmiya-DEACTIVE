package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.ReqPublicationInJournalsListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqPublicationInJournalsListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


public class ReqPublicationInJournalsListActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    ListView lv_publication_in_journals;
    List<ReqPublicationInJournalsListPojo.DataBean> publicationInJournalsList = new ArrayList<>();
    ReqPublicationInJournalsListAdapter reqPublicationInJournalsListAdapterq;
    FloatingActionButton fabAddPublicationInJournals;
    RequestQueue queue;
    Switch cb_check_req_publication_in_journals;

    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentPageNo = 1;


    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(ReqPublicationInJournalsListActivity.this)
                .anchorView(view)
                .gravity(Gravity.BOTTOM)
                .modal(true)
                .arrowColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .text(getString(R.string.app_name))
                .contentView(R.layout.tooltip_comman)
                .build();
        tooltip.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_publication_in_journals_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        if (!cb_check_req_publication_in_journals.isChecked()) {
            callGetReqPublicationInJournalsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        }

        lv_publication_in_journals.setOnScrollListener(new AbsListView.OnScrollListener() {
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

                    if (!cb_check_req_publication_in_journals.isChecked())
                        callGetReqPublicationInJournalsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), currentPageNo, "1");
                    else
                        callGetReqPublicationInJournalsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), currentPageNo, "2");

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
        txt_act.setText("Publication In Journals");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setVisibility(View.VISIBLE);
        iv_info.setOnClickListener(this);

        lv_publication_in_journals = findViewById(R.id.lv_publication_in_journals);

        fabAddPublicationInJournals = findViewById(R.id.fabAddPublicationInJournals);
        fabAddPublicationInJournals.setOnClickListener(this);

        cb_check_req_publication_in_journals = findViewById(R.id.cb_check_req_publication_in_journals);

        cb_check_req_publication_in_journals.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                resetListData();
                if (reqPublicationInJournalsListAdapterq != null){
                    reqPublicationInJournalsListAdapterq.notifyDataSetChanged();
                }
                if (isChecked)
                    callGetReqPublicationInJournalsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "2");
                else
                    callGetReqPublicationInJournalsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
            }
        });

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
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (v.getId() == R.id.fabAddPublicationInJournals) {
            Intent intent = new Intent(ReqPublicationInJournalsListActivity.this, ReqPublicationInJournalsActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_ADD_PUBLICATION_IN_JOURNALS);
        } else if (v.getId() == R.id.iv_info) {
            showTooltip(v);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_ADD_PUBLICATION_IN_JOURNALS && resultCode == RESULT_OK) {
            resetListData();
            callGetReqPublicationInJournalsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        } else if (requestCode == IntentConstants.REQUEST_CODE_FOR_EDIT_PUBLICATION_IN_JOURNALS && resultCode == RESULT_OK) {
            resetListData();
            callGetReqPublicationInJournalsListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        }

    }


    private void callGetReqPublicationInJournalsListAPI(final String id, final String user_id,
                                                        final int PageNumber,
                                                        final String Status) {

        DialogUtils.showProgressDialog(ReqPublicationInJournalsListActivity.this, "");
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Get_Publication_in_Journals, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        ReqPublicationInJournalsListPojo reqPublicationInJournalsListPojo = gson.fromJson("{\"Data\":" + response + "}", ReqPublicationInJournalsListPojo.class);
                        if (reqPublicationInJournalsListPojo.getData() != null &&
                                reqPublicationInJournalsListPojo.getData().size() > 0) {
                            publicationInJournalsList.addAll(reqPublicationInJournalsListPojo.getData());
                            if (PageNumber == 1){
                                reqPublicationInJournalsListAdapterq = new ReqPublicationInJournalsListAdapter(ReqPublicationInJournalsListActivity.this, (ArrayList<ReqPublicationInJournalsListPojo.DataBean>) publicationInJournalsList, true);
                                lv_publication_in_journals.setAdapter(reqPublicationInJournalsListAdapterq);
                            }else {
                                reqPublicationInJournalsListAdapterq.notifyDataSetChanged();
                            }

                        } else {
                            if (PageNumber == 1){
                                if (publicationInJournalsList != null) {
                                    publicationInJournalsList.clear();
                                }
                                reqPublicationInJournalsListAdapterq = new ReqPublicationInJournalsListAdapter(ReqPublicationInJournalsListActivity.this, (ArrayList<ReqPublicationInJournalsListPojo.DataBean>) publicationInJournalsList, true);
                                lv_publication_in_journals.setAdapter(reqPublicationInJournalsListAdapterq);
                                DialogUtils.Show_Toast(ReqPublicationInJournalsListActivity.this, "No Data Found!");
                            }else {
                                hasMoreData = false;
                            }
                        }
                    } catch (Exception ex) {
                        Toast.makeText(ReqPublicationInJournalsListActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DialogUtils.Show_Toast(ReqPublicationInJournalsListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqPublicationInJournalsListActivity.this, "Please Try Again Later");
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
                params2.put("RowsPerPage", IntentConstants.ROW_PER_PAGE);
                params2.put("PageNumber", String.valueOf(PageNumber));
                params2.put("Status", Status);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getPatentAwardedList.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getPatentAwardedList);

    }


    private void resetListData() {
        isScrolling = false;
        hasMoreData = true;
        currentPageNo = 1;
        if (publicationInJournalsList != null) {
            publicationInJournalsList.clear();
            publicationInJournalsList = new ArrayList<>();
        }
    }

}
