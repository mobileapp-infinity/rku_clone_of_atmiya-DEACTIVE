package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetCommitteeEmployeeNamePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class CommitteeWiseMemberListAdapter extends BaseSwipeAdapter {
    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<GetCommitteeEmployeeNamePojo.DataBean> committeeWiseMemberDetailsArrayList;
    Boolean Isc = true;
    RequestQueue queue;

    public CommitteeWiseMemberListAdapter(Context ctx, ArrayList<GetCommitteeEmployeeNamePojo.DataBean> committeeWiseMemberDetailsArrayList, Boolean Isc)
    {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.committeeWiseMemberDetailsArrayList = committeeWiseMemberDetailsArrayList;
        queue = Volley.newRequestQueue(ctx);
    }

    @Override
    public int getCount()
    {
        return committeeWiseMemberDetailsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        //  if (Isc) {
        return R.id.swipe_req_committee_wise_member;
        //  } else {
        //  return 0;
        // }
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_committee_wise_member_item, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_req_committee_wise_member);

        CustomTextView tvNameOfCommitteeCommetteeWiseMember,tvNameOfCommitteeWiseMember,
                tvNoOfMember;

        ImageView iv_delete_req_committee_wise_member;
        LinearLayout main_ll_req_committee_wise_member;

        iv_delete_req_committee_wise_member = (ImageView) convertView.findViewById(R.id.iv_delete_req_committee_wise_member);

        tvNameOfCommitteeCommetteeWiseMember = convertView.findViewById(R.id.tvNameOfCommitteeCommetteeWiseMember);
        tvNameOfCommitteeWiseMember = convertView.findViewById(R.id.tvNameOfCommitteeWiseMember);
        tvNoOfMember = convertView.findViewById(R.id.tvNoOfMember);

        main_ll_req_committee_wise_member = (LinearLayout) convertView.findViewById(R.id.main_ll_req_committee_wise_member);

        if (position % 2 == 0)
        {
            main_ll_req_committee_wise_member.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        }

        iv_delete_req_committee_wise_member.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                //swipeLayout.close();

                DialogUtils.showDialog4YNo(ctx, "", "Are You Sure You want to delete ?", new DialogUtils.DailogCallBackOkButtonClick()
                {
                    @Override
                    public void onDialogOkButtonClicked()
                    {
                        DialogUtils.showProgressDialog(ctx, "");
                        StringRequest deleteCommitteeWiseMemberReq = new StringRequest(Request.Method.POST, URLS.Delete_Committee_wise_employee_name, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                DialogUtils.hideProgressDialog();
                                if (!TextUtils.isEmpty(response)){
                                    //swipeLayout.close();
                                    committeeWiseMemberDetailsArrayList.remove(position);
                                    notifyDataSetChanged();
                                    Gson gson = new Gson();
                                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                                    DialogUtils.Show_Toast(ctx, responsePojo.getData().get(0).getMsg());
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                DialogUtils.Show_Toast(ctx, "Please Try Again Later");
                                DialogUtils.hideProgressDialog();
                                System.out.println("errorrrrrrrrrr " + error);
                                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params2 = new Hashtable<>();
                                params2.put("id", String.valueOf(committeeWiseMemberDetailsArrayList.get(position).getId()));
                                params2.put("ip", "0");
                                params2.put("User_id", mySharedPreferecesRKUOLD.getUserID());
                                return params2;
                            }

                            @Override
                            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                                return "application/x-www-form-urlencoded; charset=UTF-8";
                            }
                        };
                        deleteCommitteeWiseMemberReq.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        queue.add(deleteCommitteeWiseMemberReq);

                    }
                }, new DialogUtils.DailogCallBackCancelButtonClick()
                {
                    @Override
                    public void onDialogCancelButtonClicked()
                    {
                    }
                });
            }
        });

        tvNoOfMember.setText(String.valueOf(position+1));
        tvNameOfCommitteeCommetteeWiseMember.setText(String.valueOf(committeeWiseMemberDetailsArrayList.get(position).getCommittee_name()));
        tvNameOfCommitteeWiseMember.setText(committeeWiseMemberDetailsArrayList.get(position).getMembers());
    }
}



