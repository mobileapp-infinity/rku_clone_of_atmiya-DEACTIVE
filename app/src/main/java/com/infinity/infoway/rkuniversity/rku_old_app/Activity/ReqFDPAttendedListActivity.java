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
import android.util.Base64;
import android.util.Log;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.ReqFDPAttentdedListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqGetFDPAttendedRequestListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;


public class ReqFDPAttendedListActivity extends AppCompatActivity implements View.OnClickListener, ReqFDPAttentdedListAdapter.GetCorrentRecoredId {


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    ImageView ivBack;
    FloatingActionButton fabAddFDPAttended;
    RequestQueue queue;
    Switch cb_check_fdp_attended;
    ListView lv_fdp_attended;
    List<ReqGetFDPAttendedRequestListPojo.DataBean> reqFDPAttendedList = new ArrayList<>();
    ReqFDPAttentdedListAdapter reqFDPAttentdedListAdapter;
    String base64StringFile;
    String recoredId;

    private boolean isScrolling = false;
    private boolean hasMoreData = true;
    private int currentPageNo = 1;

    private ImageView iv_info;
    SimpleTooltip tooltip;

    private void showTooltip(View view) {

        tooltip = new SimpleTooltip.Builder(ReqFDPAttendedListActivity.this)
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
        setContentView(R.layout.activity_req_fdpattended_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        if (!cb_check_fdp_attended.isChecked()) {
            callFDPListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        }


        lv_fdp_attended.setOnScrollListener(new AbsListView.OnScrollListener() {
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

                    if (!cb_check_fdp_attended.isChecked()) {
                        callFDPListAPI("0", mySharedPreferecesRKUOLD.getUserID(), currentPageNo, "1");
                    } else {
                        callFDPListAPI("0", mySharedPreferecesRKUOLD.getUserID(), currentPageNo, "2");
                    }

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
        txt_act.setText("FDP Attended");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        iv_info = (ImageView) findViewById(R.id.iv_info);
        iv_info.setOnClickListener(this);
        iv_info.setVisibility(View.VISIBLE);

        lv_fdp_attended = findViewById(R.id.lv_fdp_attended);

        fabAddFDPAttended = findViewById(R.id.fabAddFDPAttended);
        fabAddFDPAttended.setOnClickListener(this);

        cb_check_fdp_attended = findViewById(R.id.cb_check_fdp_attended);

        cb_check_fdp_attended.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                resetListData();
                if (reqFDPAttentdedListAdapter != null) {
                    reqFDPAttentdedListAdapter.notifyDataSetChanged();
                }
                if (isChecked)
                    callFDPListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "2");
                else
                    callFDPListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
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
        } else if (view.getId() == R.id.fabAddFDPAttended) {
            Intent intent = new Intent(ReqFDPAttendedListActivity.this, ReqFDPAttendedActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_ADD_FDP_ATTENDED);
        } else if (view.getId() == R.id.iv_info) {
            showTooltip(view);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.REQUEST_CODE_FOR_ADD_FDP_ATTENDED && resultCode == RESULT_OK) {
            resetListData();
            callFDPListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        } else if (requestCode == IntentConstants.REQUEST_CODE_FOR_EDIT_FDP_ATTENDED && resultCode == RESULT_OK) {
            resetListData();
            callFDPListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
        } else if (requestCode == IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT && resultCode == RESULT_OK) {
            try {

                String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                final File file = new File(filePath);

                if (file != null && file.length() > 0) {

                    if (file.length() > 2000000) { //2000000bytes == 2MB
                        Toast.makeText(this, "File length must be less than 2mb", Toast.LENGTH_SHORT).show();
                    } else {
                        byte[] buffer = new byte[(int) file.length() + 100];
                        int length = new FileInputStream(file).read(buffer);
                        base64StringFile = Base64.encodeToString(buffer, 0, length,
                                Base64.NO_WRAP);

                        String id = recoredId;
                        String emp_id = mySharedPreferecesRKUOLD.getEmpID();
                        String user_id = mySharedPreferecesRKUOLD.getUserID();
                        String ip = "0";
                        String File_Name = base64StringFile;

                        if (!TextUtils.isEmpty(recoredId)) {
                            callUploadFDPFileAPI(id, emp_id, user_id, ip, File_Name);
                        } else {
                            DialogUtils.Show_Toast(ReqFDPAttendedListActivity.this, "Can't Upload file");
                        }
                    }

                } else {
                    DialogUtils.Show_Toast(ReqFDPAttendedListActivity.this, "File Not Found");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void callFDPListAPI(final String id, final String user_id,
                                final int PageNumber,
                                final String Status) {
        DialogUtils.showProgressDialog(ReqFDPAttendedListActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_FDP_Attended_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    try {
                        Gson gson = new Gson();
                        ReqGetFDPAttendedRequestListPojo reqGetFDPAttendedRequestListPojo = gson.fromJson("{\"Data\":" + response + "}", ReqGetFDPAttendedRequestListPojo.class);
                        if (reqGetFDPAttendedRequestListPojo.getData() != null &&
                                reqGetFDPAttendedRequestListPojo.getData().size() > 0) {
                            reqFDPAttendedList.addAll(reqGetFDPAttendedRequestListPojo.getData());
                            if (PageNumber == 1) {
                                reqFDPAttentdedListAdapter = new ReqFDPAttentdedListAdapter(ReqFDPAttendedListActivity.this, (ArrayList<ReqGetFDPAttendedRequestListPojo.DataBean>) reqFDPAttendedList, true);
                                lv_fdp_attended.setAdapter(reqFDPAttentdedListAdapter);
                            } else {
                                reqFDPAttentdedListAdapter.notifyDataSetChanged();
                            }
                        } else {
                            if (PageNumber == 1) {
                                if (reqFDPAttendedList != null) {
                                    reqFDPAttendedList.clear();
                                }
                                reqFDPAttentdedListAdapter = new ReqFDPAttentdedListAdapter(ReqFDPAttendedListActivity.this, (ArrayList<ReqGetFDPAttendedRequestListPojo.DataBean>) reqFDPAttendedList, true);
                                lv_fdp_attended.setAdapter(reqFDPAttentdedListAdapter);
                                DialogUtils.Show_Toast(ReqFDPAttendedListActivity.this, "No Data Found!");
                            } else {
                                hasMoreData = false;
                            }
                        }
                    } catch (Exception ex) {
                        Toast.makeText(ReqFDPAttendedListActivity.this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DialogUtils.Show_Toast(ReqFDPAttendedListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqFDPAttendedListActivity.this, "Please Try Again Later");
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
                params2.put("status", Status);
                params2.put("rowperpage", IntentConstants.ROW_PER_PAGE);
                params2.put("pageno", String.valueOf(PageNumber));
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


    private void callUploadFDPFileAPI(final String id, final String emp_id, final String user_id,
                                      final String ip, final String File_Name) {

        DialogUtils.showProgressDialog(ReqFDPAttendedListActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Insert_File_Upload_FDP_Attended_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    //Toast.makeText(ReqPatentAwardedActivity.this, "Data Submited Successfully", Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ReqFDPAttendedListActivity.this, responsePojo.getData().get(0).getMsg());
                    Log.d("TAG", "Response:- " + response);
                    resetListData();
                    callFDPListAPI("0", mySharedPreferecesRKUOLD.getUserID(), 1, "1");
                } else {
                    DialogUtils.Show_Toast(ReqFDPAttendedListActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ReqFDPAttendedListActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
                params2.put("emp_id", emp_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                params2.put("File_Name", File_Name);
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


    @Override
    public void setRecoredId(String recoredId) {
        this.recoredId = recoredId;
    }


    private void resetListData() {
        isScrolling = false;
        hasMoreData = true;
        currentPageNo = 1;
        if (reqFDPAttendedList != null) {
            reqFDPAttendedList.clear();
            reqFDPAttendedList = new ArrayList<>();
        }
    }
}
