package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

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
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.ViewCommeitteeMemberListAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewCommitteeWiseMemberPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class ViewCommetteeMemberActivity extends AppCompatActivity implements View.OnClickListener{


    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act,tv_version;
    ImageView ivBack;
    RequestQueue queue;

    CustomTextView tvNameOfCoOrdinatorViewCommitteeMember,tvCommitteeNameViewCommitteeMember;

    RecyclerView rvViewCommitteeMember;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> committeeMemberNameArrayList;
    ViewCommeitteeMemberListAdapter viewCommeitteeMemberListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_commettee_member);
        initView();

        if (getIntent().hasExtra(IntentConstants.ID_VIEW_COMMITTEE_MEMBER_DETAILS)){
            callViewCommitteeMemberDetails(getIntent().getStringExtra(IntentConstants.ID_VIEW_COMMITTEE_MEMBER_DETAILS));
        }
    }


    private void initView(){

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        queue = Volley.newRequestQueue(this);

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("View CommitteeMember Details");

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        tvNameOfCoOrdinatorViewCommitteeMember = findViewById(R.id.tvNameOfCoOrdinatorViewCommitteeMember);
        tvCommitteeNameViewCommitteeMember = findViewById(R.id.tvCommitteeNameViewCommitteeMember);

        rvViewCommitteeMember = findViewById(R.id.rvViewCommitteeMember);
        layoutManager = new LinearLayoutManager(this);
        rvViewCommitteeMember.setLayoutManager(layoutManager);

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
        if (v.getId() == R.id.iv_back){
            onBackPressed();
        }
    }

    private void callViewCommitteeMemberDetails(final String id){
        DialogUtils.showProgressDialog(ViewCommetteeMemberActivity.this, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_Committee_wise_memeber, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                    ViewCommitteeWiseMemberPojo viewCommitteeWiseMemberPojo = gson.fromJson("{\"Data\":" + response + "}",ViewCommitteeWiseMemberPojo.class);
                    if (viewCommitteeWiseMemberPojo.getData() != null && viewCommitteeWiseMemberPojo.getData().size() > 0){

                        try{

                            tvNameOfCoOrdinatorViewCommitteeMember.setText(viewCommitteeWiseMemberPojo.getData().get(0).getMain_coordinatior());
                            tvCommitteeNameViewCommitteeMember.setText(viewCommitteeWiseMemberPojo.getData().get(0).getCommittee_name());

                            committeeMemberNameArrayList = new ArrayList<>();

                            String[] committeeMemberNameArray = viewCommitteeWiseMemberPojo.getData().get(0).getCmtm_member_id().split(",");

                            for (int i = 0 ; i < committeeMemberNameArray.length ; i++){
                                committeeMemberNameArrayList.add(committeeMemberNameArray[i]);
                            }

                            viewCommeitteeMemberListAdapter = new ViewCommeitteeMemberListAdapter(ViewCommetteeMemberActivity.this,committeeMemberNameArrayList);
                            rvViewCommitteeMember.setAdapter(viewCommeitteeMemberListAdapter);

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                    }else {
                        DialogUtils.Show_Toast(ViewCommetteeMemberActivity.this, "No Data Available");
                        finish();
                    }
                }else {
                    DialogUtils.Show_Toast(ViewCommetteeMemberActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(ViewCommetteeMemberActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("id", id);
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
