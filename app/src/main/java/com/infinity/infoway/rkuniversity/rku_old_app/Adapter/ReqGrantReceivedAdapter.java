package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqGrantReceivedRequest;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqViewGrantReceivedActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqGrantReceivedListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewGRantReceivedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ReqGrantReceivedAdapter extends BaseSwipeAdapter {
    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<ReqGrantReceivedListPojo.DataBean> grantListModelArrayList;
    Boolean Isc = true;
    RequestQueue queue;

    public ReqGrantReceivedAdapter(Context ctx,ArrayList<ReqGrantReceivedListPojo.DataBean> grantListModelArrayList, Boolean Isc)
    {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.grantListModelArrayList = grantListModelArrayList;
        queue = Volley.newRequestQueue(ctx);
    }


    @Override
    public int getCount()
    {
        return grantListModelArrayList.size();
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
        return R.id.swipe_req_grant_received;
        //  } else {
        //  return 0;
        // }
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_req_grant_received, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_req_grant_received);
//
        final int po = position;
        if (!String.valueOf(grantListModelArrayList.get(position).getGr_is_approve_value()).equalsIgnoreCase("P")) {
            swipeLayout.setSwipeEnabled(false);
        }

        CustomTextView tvNameOfProject,tvAYGrantReceived,tvFundProvided,tvStatusReqGrantReceived;

        ImageView iv_edit_req_received, iv_delete_req_grant_received;
        LinearLayout main_ll_req_grant_received;

        iv_edit_req_received = (ImageView) convertView.findViewById(R.id.iv_edit_req_received);
        iv_delete_req_grant_received = (ImageView) convertView.findViewById(R.id.iv_delete_req_grant_received);
        tvNameOfProject = convertView.findViewById(R.id.tvNameOfProject);
        tvAYGrantReceived = convertView.findViewById(R.id.tvAYGrantReceived);
        tvFundProvided = convertView.findViewById(R.id.tvFundProvided);
        tvStatusReqGrantReceived =  convertView.findViewById(R.id.tvStatusReqGrantReceived);
        main_ll_req_grant_received = (LinearLayout) convertView.findViewById(R.id.main_ll_req_grant_received);

        if (position % 2 == 0)
        {
            main_ll_req_grant_received.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        }else {
            main_ll_req_grant_received.setBackgroundColor(ctx.getResources().getColor(R.color.white));
        }


        main_ll_req_grant_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, ReqViewGrantReceivedActivity.class);
                intent.putExtra(IntentConstants.ID_VIEW_GRANT_RECEIVED,grantListModelArrayList.get(position).getId()+"");
                ctx.startActivity(intent);
            }
        });

        iv_edit_req_received.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //swipeLayout.close();
                DialogUtils.showProgressDialog(ctx, "");
                StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_Grant_Received_request, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DialogUtils.hideProgressDialog();
                        if (!TextUtils.isEmpty(response)){
                            Gson gson = new Gson();
                            ReqViewGRantReceivedPojo reqViewGRantReceivedPojo= gson.fromJson("{\"Data\":" + response + "}",ReqViewGRantReceivedPojo.class);
                            if (reqViewGRantReceivedPojo.getData() != null){

                                Intent intent = new Intent(ctx, ReqGrantReceivedRequest.class);
                                intent.putExtra(IntentConstants.REQ_VIEW_GRANT_RECEIVED_POJO,reqViewGRantReceivedPojo.getData().get(0));
                                ((Activity)ctx).startActivityForResult(intent,IntentConstants.REQUEST_CODE_FOR_REQ_EDIT_GRANT_RECEIVED);
                            }
                        }else {
                            DialogUtils.Show_Toast(ctx, response);
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
                        params2.put("id", grantListModelArrayList.get(position).getId()+"");
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
        });

        iv_delete_req_grant_received.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                DialogUtils.showDialog4YNo(ctx, "", "Are You Sure You want to delete ?", new DialogUtils.DailogCallBackOkButtonClick()
                {
                    @Override
                    public void onDialogOkButtonClicked()
                    {
                        DialogUtils.showProgressDialog(ctx, "");
                        StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_Grant_Received_request, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                DialogUtils.hideProgressDialog();
                                if (!TextUtils.isEmpty(response)){
                                    //swipeLayout.close();
                                    grantListModelArrayList.remove(position);
                                    notifyDataSetChanged();
                                    Gson gson = new Gson();
                                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
                                    DialogUtils.Show_Toast(ctx, responsePojo.getData().get(0).getMsg());
                                    //DialogUtils.Show_Toast(ctx, response);
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
                                params2.put("id", String.valueOf(grantListModelArrayList.get(position).getId()));
                                params2.put("user_id", mySharedPreferecesRKUOLD.getUserID());
                                params2.put("ip", "0");
                                return params2;
                            }

                            @Override
                            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                                return "application/x-www-form-urlencoded; charset=UTF-8";
                            }
                        };
                        deletePublication.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        queue.add(deletePublication);

//                        showDialog(ctx, position,String.valueOf(publicationInConferenceListModelArrayList.get(position).getId()),swipeLayout);


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

        tvNameOfProject.setText(grantListModelArrayList.get(position).getGr_project_name());
        tvAYGrantReceived.setText(grantListModelArrayList.get(position).getYear_name());
        tvFundProvided.setText(grantListModelArrayList.get(position).getGr_funds_provided());
        tvStatusReqGrantReceived.setText(grantListModelArrayList.get(position).getGr_is_approve_value());
    }
}
