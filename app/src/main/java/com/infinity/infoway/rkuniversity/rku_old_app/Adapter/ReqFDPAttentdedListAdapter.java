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
import android.widget.Toast;

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
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqFDPAttendedActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqViewFDPAttendedActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqGetFDPAttendedRequestListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ReqViewFDPAttendedPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ReqFDPAttentdedListAdapter extends BaseSwipeAdapter {
    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<ReqGetFDPAttendedRequestListPojo.DataBean> fdpList;
    Boolean Isc = true;
    RequestQueue queue;
    GetCorrentRecoredId getCorrentRecoredId;

    public ReqFDPAttentdedListAdapter(Context ctx, ArrayList<ReqGetFDPAttendedRequestListPojo.DataBean> fdpList, Boolean Isc) {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.fdpList = fdpList;
        queue = Volley.newRequestQueue(ctx);
        getCorrentRecoredId = (GetCorrentRecoredId) ctx;
    }


    @Override
    public int getCount() {
        return fdpList.size();
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
        return R.id.swipe_req_fdp;
        //  } else {
        //  return 0;
        // }
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_fdp_attended_list_adapter, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_req_fdp);
//
        // final int po = position;
        if (!String.valueOf(fdpList.get(position).getStatus()).equalsIgnoreCase("P")) {
            swipeLayout.setSwipeEnabled(false);
        }

        CustomTextView tvStatuseFDP, tvAcademicYearFDP, tvNameOftheWorkshopFDP,fromDateToDateFDP;

        ImageView iv_edit_req_fdp, iv_delete_req_fdp;
        LinearLayout main_ll_req_fdp, ll_upload_pending_file_fdp;


        iv_edit_req_fdp = (ImageView) convertView.findViewById(R.id.iv_edit_req_fdp);
        iv_delete_req_fdp = (ImageView) convertView.findViewById(R.id.iv_delete_req_fdp);

        tvStatuseFDP = convertView.findViewById(R.id.tvStatuseFDP);
        tvAcademicYearFDP = convertView.findViewById(R.id.tvAcademicYearFDP);
        tvNameOftheWorkshopFDP = convertView.findViewById(R.id.tvNameOftheWorkshopFDP);
        fromDateToDateFDP = convertView.findViewById(R.id.tvFromDateToDateFDP);

        ll_upload_pending_file_fdp = convertView.findViewById(R.id.ll_upload_pending_file_fdp);

        main_ll_req_fdp = (LinearLayout) convertView.findViewById(R.id.main_ll_req_fdp);

        if (fdpList.get(position).getFile_count() == 0) {
            ll_upload_pending_file_fdp.setVisibility(View.VISIBLE);
        }


        if (position % 2 == 0) {
            main_ll_req_fdp.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        }else {
            main_ll_req_fdp.setBackgroundColor(ctx.getResources().getColor(R.color.white));
        }


        main_ll_req_fdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fdpList.get(position).getFile_count() == 1) {
                    Intent intent = new Intent(ctx, ReqViewFDPAttendedActivity.class);
                    intent.putExtra(IntentConstants.ID_FDP_ATTENDED, fdpList.get(position).getId() + "");
                    intent.putExtra(IntentConstants.FDP_EVENT_TYPE, fdpList.get(position).getEv_type_name());
                    ctx.startActivity(intent);
                } else {
                    Toast.makeText(ctx, "Please Upload File", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ll_upload_pending_file_fdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCorrentRecoredId.setRecoredId(fdpList.get(position).getId() + "");

                new MaterialFilePicker()
                        .withActivity((Activity) ctx)
                        .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                        .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                        .withFilterDirectories(false) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();

            }
        });

        iv_edit_req_fdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //swipeLayout.close();
                DialogUtils.showProgressDialog(ctx, "");
                StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_File_Upload_FDP_Attended_request, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (!TextUtils.isEmpty(response)) {
                            callViewFDPAttendedAPI(position);
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
                        params2.put("id", String.valueOf(fdpList.get(position).getId()));
                        params2.put("emp_id", mySharedPreferecesRKUOLD.getEmpID());
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

            }
        });

        iv_delete_req_fdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogUtils.showDialog4YNo(ctx, "", "Are You Sure You want to delete ?", new DialogUtils.DailogCallBackOkButtonClick() {
                    @Override
                    public void onDialogOkButtonClicked() {
                        DialogUtils.showProgressDialog(ctx, "");
                        StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_File_Upload_FDP_Attended_request, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (!TextUtils.isEmpty(response)) {
                                    //swipeLayout.close();
                                    callDeleteFDPAttendedRequest(position);
//                                    seedMoneyList.remove(position);
//                                    notifyDataSetChanged();
//                                    Gson gson = new Gson();
//                                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}",SaveDeleteUpdateResponsePojo.class);
//                                    DialogUtils.Show_Toast(ctx, responsePojo.getData().get(0).getMsg());
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
                                params2.put("id", String.valueOf(fdpList.get(position).getId()));
                                params2.put("emp_id", mySharedPreferecesRKUOLD.getEmpID());
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

        tvAcademicYearFDP.setText(String.valueOf(fdpList.get(position).getYear_name()));
        tvStatuseFDP.setText(fdpList.get(position).getStatus());
        tvNameOftheWorkshopFDP.setText(String.valueOf(fdpList.get(position).getFdpa_workshop_name()));
        fromDateToDateFDP.setText(String.valueOf(fdpList.get(position).getFdpa_from_date() + " to " +
                String.valueOf(fdpList.get(position).getFdpa_to_date())));
    }


    private void callDeleteFDPAttendedRequest(final int position) {
        StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_FDP_Attended_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    fdpList.remove(position);
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
                params2.put("id", String.valueOf(fdpList.get(position).getId()));
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
    }


    private void callViewFDPAttendedAPI(final int position) {

//        DialogUtils.showProgressDialog(ctx, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_FDP_Attended_request, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    ReqViewFDPAttendedPojo reqViewFDPAttendedPojo = gson.fromJson("{\"Data\":" + response + "}", ReqViewFDPAttendedPojo.class);
                    if (reqViewFDPAttendedPojo.getData() != null) {
                        Intent intent = new Intent(ctx, ReqFDPAttendedActivity.class);
                        intent.putExtra(IntentConstants.REQ_VIEW_FDP_ATTENDED_POJO_LIDT, (Serializable) reqViewFDPAttendedPojo.getData());
                        intent.putExtra(IntentConstants.FDP_EVENT_TYPE, fdpList.get(position).getEv_type_name());
                        ((Activity) ctx).startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_EDIT_FDP_ATTENDED);
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
                params2.put("id", fdpList.get(position).getId() + "");
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

    public interface GetCorrentRecoredId {
        void setRecoredId(String recoredId);
    }
}


