package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqPGScholarGuidedActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqViewPgScholarGuidedListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.RegPgScholarGuidedListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewPgScholarGuidedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ReqPgScholarGuidedListAdapter extends BaseSwipeAdapter {

    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<RegPgScholarGuidedListPojo.DataBean> pgScholarList;
    Boolean Isc = true;
    RequestQueue queue;

    public ReqPgScholarGuidedListAdapter(Context ctx, ArrayList<RegPgScholarGuidedListPojo.DataBean> pgScholarList, Boolean Isc) {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.pgScholarList = pgScholarList;
        queue = Volley.newRequestQueue(ctx);
    }


    @Override
    public int getCount() {
        return pgScholarList.size();
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
        return R.id.swipe_req_pg_scholar_guided;
        //  } else {
        //  return 0;
        // }
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_pg_scholar_guided_list_adapter, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_req_pg_scholar_guided);
//
        // final int po = position;
        if (!String.valueOf(pgScholarList.get(position).getStatus()).equalsIgnoreCase("P")) {
            swipeLayout.setSwipeEnabled(false);
        }

        CustomTextView tvAcademicYearPgScholarGuidedReq, tvNameofPgScholarsReq, tvStatusOfPgScholarGuidedScholarReq;

        ImageView iv_edit_req_pg_scholar_guided_req, iv_delete_req_pg_scholar_guided_req;
        LinearLayout main_ll_req_pg_scholar_guided_req;


        iv_edit_req_pg_scholar_guided_req = (ImageView) convertView.findViewById(R.id.iv_edit_req_pg_scholar_guided_req);
        iv_delete_req_pg_scholar_guided_req = (ImageView) convertView.findViewById(R.id.iv_delete_req_pg_scholar_guided_req);

        tvAcademicYearPgScholarGuidedReq = convertView.findViewById(R.id.tvAcademicYearPgScholarGuidedReq);
        tvNameofPgScholarsReq = convertView.findViewById(R.id.tvNameofPgScholarsReq);
        //tvYearofRegistrationPgScholarGuideReq = convertView.findViewById(R.id.tvYearofRegistrationPgScholarGuideReq);
        tvStatusOfPgScholarGuidedScholarReq = convertView.findViewById(R.id.tvStatusOfPgScholarGuidedScholarReq);

        main_ll_req_pg_scholar_guided_req = (LinearLayout) convertView.findViewById(R.id.main_ll_req_pg_scholar_guided_req);

        if (position % 2 == 0) {
//            Log.d("PG_SCHOLAR_ADAPTER_POS", String.valueOf(position));
            main_ll_req_pg_scholar_guided_req.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        }
        else {
            main_ll_req_pg_scholar_guided_req.setBackgroundColor(ctx.getResources().getColor(R.color.white));
        }


        main_ll_req_pg_scholar_guided_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CLICKED_POSITION:- ",String.valueOf(position));
                Intent intent = new Intent(ctx, ReqViewPgScholarGuidedListActivity.class);
                intent.putExtra(IntentConstants.ID_VIEW_PG_SCHOLAR, pgScholarList.get(position).getId() + "");
                ctx.startActivity(intent);
            }
        });

        iv_edit_req_pg_scholar_guided_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //swipeLayout.close();
                DialogUtils.showProgressDialog(ctx, "");
                StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_PG_Scholars_Guided_request, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DialogUtils.hideProgressDialog();
                        if (!TextUtils.isEmpty(response)) {
                            Gson gson = new Gson();
                            ReqViewPgScholarGuidedPojo reqViewPgScholarGuidedPojo = gson.fromJson("{\"Data\":" + response + "}", ReqViewPgScholarGuidedPojo.class);
                            if (reqViewPgScholarGuidedPojo.getData() != null) {
                                Intent intent = new Intent(ctx, ReqPGScholarGuidedActivity.class);
                                intent.putExtra(IntentConstants.REQ_VIEW_PG_SCHOLAR_GUIDED_POJO, reqViewPgScholarGuidedPojo.getData().get(0));
                                ((Activity) ctx).startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_EDIT_PG_SCHOLAR_GUIDED);
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
                        params2.put("id", pgScholarList.get(position).getId() + "");
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

        iv_delete_req_pg_scholar_guided_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogUtils.showDialog4YNo(ctx, "", "Are You Sure You want to delete ?", new DialogUtils.DailogCallBackOkButtonClick() {
                    @Override
                    public void onDialogOkButtonClicked() {
                        DialogUtils.showProgressDialog(ctx, "");
                        StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_PG_Scholars_Guided_request, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                DialogUtils.hideProgressDialog();
                                if (!TextUtils.isEmpty(response)) {
                                    //swipeLayout.close();
                                    pgScholarList.remove(position);
                                    notifyDataSetChanged();
                                    Gson gson = new Gson();
                                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
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
                                params2.put("id", String.valueOf(pgScholarList.get(position).getId()));
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
                }, new DialogUtils.DailogCallBackCancelButtonClick() {
                    @Override
                    public void onDialogCancelButtonClicked() {
                    }
                });
            }
        });

        tvAcademicYearPgScholarGuidedReq.setText(pgScholarList.get(position).getYear_name());
        tvNameofPgScholarsReq.setText(pgScholarList.get(position).getPgs_scholar_name());
        //tvYearofRegistrationPgScholarGuideReq.setText(String.valueOf(pgScholarList.get(position).getPgs_reg_year()));
        tvStatusOfPgScholarGuidedScholarReq.setText(pgScholarList.get(position).getStatus());

    }
}


