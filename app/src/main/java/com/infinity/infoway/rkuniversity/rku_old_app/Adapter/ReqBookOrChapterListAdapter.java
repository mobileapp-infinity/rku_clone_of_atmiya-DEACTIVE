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
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqBookOrChapterPublicationActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqViewBookOrChapterActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqBookOrChapterListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewBookOrChapterPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ReqBookOrChapterListAdapter extends BaseSwipeAdapter {
    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<ReqBookOrChapterListPojo.DataBean> bookOrchapterList;
    Boolean Isc = true;
    RequestQueue queue;

    public ReqBookOrChapterListAdapter(Context ctx,ArrayList<ReqBookOrChapterListPojo.DataBean> bookOrchapterList, Boolean Isc)
    {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.bookOrchapterList = bookOrchapterList;
        queue = Volley.newRequestQueue(ctx);
    }


    @Override
    public int getCount()
    {
        return bookOrchapterList.size();
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
        return R.id.swipe_req_book_or_chapter;
        //  } else {
        //  return 0;
        // }
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_book_or_chapter_list, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_req_book_or_chapter);

        if (!String.valueOf(bookOrchapterList.get(position).getStatus()).equalsIgnoreCase("P")) {
            swipeLayout.setSwipeEnabled(false);
        }

        CustomTextView tvStatusBookOrChapterReq,tvAYBookOrChapterReq,tvTitleOfBookOrChapterReq,tvDateOfPublicationBookOrchapterReq;

        ImageView iv_edit_req_book_or_chapter, iv_delete_req_book_or_chapter;
        LinearLayout main_ll_req_book_or_chapter;


        iv_edit_req_book_or_chapter = (ImageView) convertView.findViewById(R.id.iv_edit_req_book_or_chapter);
        iv_delete_req_book_or_chapter = (ImageView) convertView.findViewById(R.id.iv_delete_req_book_or_chapter);

        tvStatusBookOrChapterReq = convertView.findViewById(R.id.tvStatusBookOrChapterReq);
        tvAYBookOrChapterReq = convertView.findViewById(R.id.tvAYBookOrChapterReq);
        tvTitleOfBookOrChapterReq = convertView.findViewById(R.id.tvTitleOfBookOrChapterReq);
        tvDateOfPublicationBookOrchapterReq =  convertView.findViewById(R.id.tvDateOfPublicationBookOrchapterReq);

        main_ll_req_book_or_chapter = (LinearLayout) convertView.findViewById(R.id.main_ll_req_book_or_chapter);

        if (position % 2 == 0)
        {
            main_ll_req_book_or_chapter.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        }else {
            main_ll_req_book_or_chapter.setBackgroundColor(ctx.getResources().getColor(R.color.white));
        }


        main_ll_req_book_or_chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, ReqViewBookOrChapterActivity.class);
                intent.putExtra(IntentConstants.ID__VIEW_BOOK_OR_CHAPTER,bookOrchapterList.get(position).getId()+"");
                ctx.startActivity(intent);
            }
        });

        iv_edit_req_book_or_chapter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //swipeLayout.close();
                DialogUtils.showProgressDialog(ctx, "");
                StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_Boook_Chapter_Publication, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DialogUtils.hideProgressDialog();
                        if (!TextUtils.isEmpty(response)){
                            Gson gson = new Gson();
                            ReqViewBookOrChapterPojo reqViewBookOrChapterPojo = gson.fromJson("{\"Data\":" + response + "}",ReqViewBookOrChapterPojo.class);
                            if (reqViewBookOrChapterPojo.getData() != null){
                                Intent intent = new Intent(ctx, ReqBookOrChapterPublicationActivity.class);
                                intent.putExtra(IntentConstants.REQ_VIEW_BOOK_OR_CHAPTER_POJO,reqViewBookOrChapterPojo.getData().get(0));
                                ((Activity)ctx).startActivityForResult(intent,IntentConstants.REQUEST_CODE_FOR_EDIT_BOOK_OR_CHAPTER_PUBLICATION);
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
                        params2.put("id", bookOrchapterList.get(position).getId()+"");
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

        iv_delete_req_book_or_chapter.setOnClickListener(new View.OnClickListener()
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
                        StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Dalete_Boook_Chapter_Publication, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                DialogUtils.hideProgressDialog();
                                if (!TextUtils.isEmpty(response)){
                                    //swipeLayout.close();
                                    bookOrchapterList.remove(position);
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
                                params2.put("id", String.valueOf(bookOrchapterList.get(position).getId()));
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

        tvStatusBookOrChapterReq.setText(bookOrchapterList.get(position).getStatus());
        tvAYBookOrChapterReq.setText(String.valueOf(bookOrchapterList.get(position).getAcademic_Year()));
        tvTitleOfBookOrChapterReq.setText(bookOrchapterList.get(position).getTitle_of_Book_Chapter());
        tvDateOfPublicationBookOrchapterReq.setText(String.valueOf(bookOrchapterList.get(position).getDate_of_Publication()));
    }
}

