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
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.InvestmentActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.InvestmentListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewInvestmentDetailsByIdPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class InvestmentListAdapter extends BaseSwipeAdapter {
    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<InvestmentListPojo.DataBean> investmentList;
    Boolean Isc = true;
    RequestQueue queue;

    public InvestmentListAdapter(Context ctx, ArrayList<InvestmentListPojo.DataBean> investmentList, Boolean Isc) {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.investmentList = investmentList;
        queue = Volley.newRequestQueue(ctx);
    }


    @Override
    public int getCount() {
        return investmentList.size();
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
        return R.id.swipe_investment;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_investment_list, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_investment);
        swipeLayout.setSwipeEnabled(false);
//        // final int po = position;
//        if (!investmentList.get(position).getStatus().equalsIgnoreCase("Pending")) {
//            swipeLayout.setSwipeEnabled(false);
//        }

        CustomTextView tvStatuseInvestment, tvDocumentStatusInvestment, tvInvestmentNameInvestment;

        ImageView iv_edit_investment, iv_delete_investment;
        LinearLayout main_ll_investment;

//        iv_edit_investment = (ImageView) convertView.findViewById(R.id.iv_edit_investment);
//        iv_delete_investment = (ImageView) convertView.findViewById(R.id.iv_delete_investment);

        tvStatuseInvestment = convertView.findViewById(R.id.tvStatuseInvestment);
        tvDocumentStatusInvestment = convertView.findViewById(R.id.tvDocumentStatusInvestment);
        tvInvestmentNameInvestment = convertView.findViewById(R.id.tvInvestmentNameInvestment);

        main_ll_investment = (LinearLayout) convertView.findViewById(R.id.main_ll_investment);

        if (position % 2 == 0) {
            main_ll_investment.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        }else {
            main_ll_investment.setBackgroundColor(ctx.getResources().getColor(R.color.white));
        }


        main_ll_investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //swipeLayout.close();
                DialogUtils.showProgressDialog(ctx, "");
                StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Get_Investment_detail_by_id, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DialogUtils.hideProgressDialog();
                        if (!TextUtils.isEmpty(response)) {
                            Gson gson = new Gson();
                            ViewInvestmentDetailsByIdPojo viewInvestmentDetailsByIdPojo = gson.fromJson("{\"Data\":" + response + "}", ViewInvestmentDetailsByIdPojo.class);
                            if (viewInvestmentDetailsByIdPojo.getData() != null) {
                                Intent intent = new Intent(ctx, InvestmentActivity.class);
                                intent.putExtra(IntentConstants.VIEW_INVESTMENT_POJO, viewInvestmentDetailsByIdPojo.getData().get(0));
                                intent.putExtra(IntentConstants.ID_INVESTMENT,investmentList.get(position).getId() + "");
                                intent.putExtra(IntentConstants.INVESTMENT_NAME,String.valueOf(investmentList.get(position).getInvestment_Name()));
                                ((Activity) ctx).startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_EDIT__INVESTMENT);
                            }
                        } else {
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
                        params2.put("id", investmentList.get(position).getId() + "");
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


//        iv_delete_investment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

        tvInvestmentNameInvestment.setText(String.valueOf(investmentList.get(position).getInvestment_Name()));
        tvDocumentStatusInvestment.setText(String.valueOf(investmentList.get(position).getDocument_Status()));
        tvStatuseInvestment.setText(investmentList.get(position).getStatus());

    }
}


