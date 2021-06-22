package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PublicationInConferenceDetailsActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PublicationInConferencesActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomButtonView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetPublicationInConferencePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewPublicationInConferencePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class PublicationInconferenceListadapter extends BaseSwipeAdapter {

    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<GetPublicationInConferencePojo.DataBeanBean> publicationInConferenceListModelArrayList;
    Boolean Isc = true;
    RequestQueue queue;

    public PublicationInconferenceListadapter(Context ctx, ArrayList<GetPublicationInConferencePojo.DataBeanBean> publicationInConferenceListModelArrayList, Boolean Isc) {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.publicationInConferenceListModelArrayList = publicationInConferenceListModelArrayList;
        queue = Volley.newRequestQueue(ctx);
    }


    @Override
    public int getCount() {
        return publicationInConferenceListModelArrayList.size();
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
        return R.id.swipe_publication_list;
        //  } else {
        //  return 0;
        // }
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_publication_in_conferences, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_publication_list);
//
        final int po = position;
        if (!String.valueOf(publicationInConferenceListModelArrayList.get(position).getStatus()).equalsIgnoreCase("P")) {
            swipeLayout.setSwipeEnabled(false);
        }

        CustomTextView tvResearchPaper, tvAYPublication, tvStatusOfPublicationInConferences,
                tvDatePubInConference;

        ImageView iv_edit_publication_list, iv_delete_publication_list;
        LinearLayout main_ll_publication_list;


        iv_edit_publication_list = (ImageView) convertView.findViewById(R.id.iv_edit_publication_list);
        iv_delete_publication_list = (ImageView) convertView.findViewById(R.id.iv_delete_publication_list);
        tvResearchPaper = convertView.findViewById(R.id.tvResearchPaper);
        tvAYPublication = convertView.findViewById(R.id.tvAYPublication);
        tvStatusOfPublicationInConferences = convertView.findViewById(R.id.tvStatusOfPublicationInConferences);
        tvDatePubInConference = convertView.findViewById(R.id.tvDatePubInConference);

        main_ll_publication_list = (LinearLayout) convertView.findViewById(R.id.main_ll_publication_list);

        if (position % 2 == 0) {
            main_ll_publication_list.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        } else {
            main_ll_publication_list.setBackgroundColor(ctx.getResources().getColor(R.color.white));
        }


        main_ll_publication_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, PublicationInConferenceDetailsActivity.class);
                intent.putExtra(IntentConstants.ID, publicationInConferenceListModelArrayList.get(position).getId() + "");
                ctx.startActivity(intent);
            }
        });

        iv_edit_publication_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //swipeLayout.close();
                DialogUtils.showProgressDialog(ctx, "");
                StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.VIEW_PUBLICATION_IN_CONFERENCES, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DialogUtils.hideProgressDialog();
                        if (!TextUtils.isEmpty(response)) {
                            Gson gson = new Gson();
                            ViewPublicationInConferencePojo viewPublicationInConferencePojo = gson.fromJson("{\"Data\":" + response + "}", ViewPublicationInConferencePojo.class);
                            if (viewPublicationInConferencePojo.getData() != null) {
                                Intent intent = new Intent(ctx, PublicationInConferencesActivity.class);
                                intent.putExtra(IntentConstants.UPDATE_PUBLICATION_IN_CONFERENCE, viewPublicationInConferencePojo.getData().get(0));
                                ((Activity) ctx).startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_EDIT_PUBLICATION_IN_CONFERENCE);
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
                        params2.put("id", publicationInConferenceListModelArrayList.get(position).getId() + "");
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

        iv_delete_publication_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogUtils.showDialog4YNo(ctx, "", "Are You Sure You want to delete ?", new DialogUtils.DailogCallBackOkButtonClick() {
                    @Override
                    public void onDialogOkButtonClicked() {
                        DialogUtils.showProgressDialog(ctx, "");
                        StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.DELETE_PUBLICATION_IN_CONFERENCE, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                DialogUtils.hideProgressDialog();
                                if (!TextUtils.isEmpty(response)) {
                                    //swipeLayout.close();
                                    publicationInConferenceListModelArrayList.remove(position);
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
                                params2.put("id", String.valueOf(publicationInConferenceListModelArrayList.get(position).getId()));
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

        tvResearchPaper.setText(publicationInConferenceListModelArrayList.get(position).getIpc_paper_title());
        tvAYPublication.setText(publicationInConferenceListModelArrayList.get(position).getYear_name());
        tvStatusOfPublicationInConferences.setText(String.valueOf(publicationInConferenceListModelArrayList.get(position).getStatus()));
        tvDatePubInConference.setText(String.valueOf(publicationInConferenceListModelArrayList.get(position).getIpc_publication_date()));

//        tvNameOfPublisher.setText(publicationInConferenceListModelArrayList.get(position).getIpc_publisher_name());

    }


    public void showDialog(final Context context, final int pos, final String ID, final SwipeLayout swipeLayout) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.popup_miss_punch, null);

        final EditText edt_reason = (EditText) dialogView.findViewById(R.id.edt_reason);
        CustomBoldTextView tv_titile = (CustomBoldTextView) dialogView.findViewById(R.id.tv_titile);
        tv_titile.setText(context.getResources().getString(R.string.app_name));
        CustomButtonView dialogButtonCancel = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonCancel);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //  final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog));
        final AlertDialog b = builder.create();
        //  builder.setTitle("Material Style Dialog");
        builder.setCancelable(true);
        builder.setView(dialogView);
        b.setCanceledOnTouchOutside(true);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //  builder.
        final AlertDialog show = builder.show();
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //swipeLayout.close();
                show.dismiss();

            }
        });
        CustomButtonView dialogButtonOK = (CustomButtonView) dialogView.findViewById(R.id.dialogButtonOK);
        dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

}
