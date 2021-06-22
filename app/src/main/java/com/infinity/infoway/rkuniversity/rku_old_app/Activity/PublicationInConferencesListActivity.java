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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.PublicationInconferenceListadapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetPublicationInConferencePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


public class PublicationInConferencesListActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    ListView lv_publication_list;
    List<GetPublicationInConferencePojo.DataBeanBean> publicationInConferencePojoArrayList = new ArrayList<>();
    PublicationInconferenceListadapter publicationInconferenceListadapter;
    FloatingActionButton fabAddPublication;
    RequestQueue queue;
    Switch cb_check_publication_list;

    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentPageNo = 1;

    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(PublicationInConferencesListActivity.this)
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
        setContentView(R.layout.activity_publication_in_conferences_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        if (!cb_check_publication_list.isChecked()) {
            callGetPublicationInconferenceList("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        }

        lv_publication_list.setOnScrollListener(new AbsListView.OnScrollListener() {
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

                    if (!cb_check_publication_list.isChecked())
                        callGetPublicationInconferenceList("0", mySharedPreferecesRKUOLD.getUserID(), currentPageNo, "1");
                    else
                        callGetPublicationInconferenceList("0", mySharedPreferecesRKUOLD.getUserID(), currentPageNo, "2");

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
        txt_act.setText("Publication in Conferences");

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        lv_publication_list = findViewById(R.id.lv_publication_list);

        fabAddPublication = findViewById(R.id.fabAddPublication);
        fabAddPublication.setOnClickListener(this);

        cb_check_publication_list = findViewById(R.id.cb_check_publication_list);

        cb_check_publication_list.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                resetListData();
                if (publicationInconferenceListadapter != null){
                    publicationInconferenceListadapter.notifyDataSetChanged();
                }
                if (isChecked)
                    callGetPublicationInconferenceList("0", mySharedPreferecesRKUOLD.getUserID(), 1, "2");
                else
                    callGetPublicationInconferenceList("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
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
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.fabAddPublication) {
            Intent intent = new Intent(PublicationInConferencesListActivity.this, PublicationInConferencesActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_PUBLICATION_IN_CONFERENCE);
        } else if (view.getId() == R.id.iv_info) {
            showTooltip(view);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_PUBLICATION_IN_CONFERENCE && resultCode == RESULT_OK) {
            resetListData();
            callGetPublicationInconferenceList("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        } else if (requestCode == IntentConstants.REQUEST_CODE_FOR_EDIT_PUBLICATION_IN_CONFERENCE && resultCode == RESULT_OK) {
            resetListData();
            callGetPublicationInconferenceList("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        }
    }

    private void callGetPublicationInconferenceList(final String id, final String user_id,
                                                    final int PageNumber, final String Status) {

        DialogUtils.showProgressDialog(PublicationInConferencesListActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.GET_PUBLICATION_IN_CONFERENCES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        GetPublicationInConferencePojo getPublicationInConferencePojo = gson.fromJson("{\"DataBean\":" + response + "}", GetPublicationInConferencePojo.class);
                        if (getPublicationInConferencePojo.getDataBean() != null &&
                                getPublicationInConferencePojo.getDataBean().size() > 0) {
                            publicationInConferencePojoArrayList.addAll(getPublicationInConferencePojo.getDataBean());
                            if (PageNumber == 1) {
                                publicationInconferenceListadapter = new PublicationInconferenceListadapter(PublicationInConferencesListActivity.this, (ArrayList<GetPublicationInConferencePojo.DataBeanBean>) publicationInConferencePojoArrayList, true);
                                lv_publication_list.setAdapter(publicationInconferenceListadapter);
                            } else {
                                publicationInconferenceListadapter.notifyDataSetChanged();
                            }
                        } else {
                            if (PageNumber == 1) {
                                if (publicationInConferencePojoArrayList != null) {
                                    publicationInConferencePojoArrayList.clear();
                                }
                                publicationInconferenceListadapter = new PublicationInconferenceListadapter(PublicationInConferencesListActivity.this, (ArrayList<GetPublicationInConferencePojo.DataBeanBean>) publicationInConferencePojoArrayList, false);
                                lv_publication_list.setAdapter(publicationInconferenceListadapter);
                                DialogUtils.Show_Toast(PublicationInConferencesListActivity.this, "No Data Found!");
                            } else {
                                hasMoreData = false;
                            }
                        }
                    } catch (Exception ex) {
                        Toast.makeText(PublicationInConferencesListActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DialogUtils.Show_Toast(PublicationInConferencesListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(PublicationInConferencesListActivity.this, "Please Try Again Later");
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
        savePublicationInConferenceRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(savePublicationInConferenceRequest);
    }

    private void resetListData(){
        isScrolling = false;
        hasMoreData = true;
        currentPageNo = 1;
        if (publicationInConferencePojoArrayList != null) {
            publicationInConferencePojoArrayList.clear();
            publicationInConferencePojoArrayList = new ArrayList<>();
        }
    }

}
