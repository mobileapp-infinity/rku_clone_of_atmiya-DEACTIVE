package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
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
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PDApprovalRemarksAndReasonActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PDUpdateCreditActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqPDApplicationActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqViewPDApplicationActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.UploadApprovalPDApplicationDocumentActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.App.MarshMallowPermission;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetPDApplicationListPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ViewPDApplicationPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ReqPDApplicationListAdapter extends BaseSwipeAdapter {
    Context ctx;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    ArrayList<GetPDApplicationListPojo.DataBean> pdApplicationList;
    Boolean Isc = true;
    RequestQueue queue;
    ReqPDApplicationListAdapter.GetCorrentRecoredIdPDApplication getCorrentRecoredId;
    MarshMallowPermission marshMallowPermission;

    public ReqPDApplicationListAdapter(Context ctx, ArrayList<GetPDApplicationListPojo.DataBean> pdApplicationList, Boolean Isc) {
        this.ctx = ctx;
        this.Isc = Isc;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);
        this.pdApplicationList = pdApplicationList;
        queue = Volley.newRequestQueue(ctx);
        getCorrentRecoredId = (ReqPDApplicationListAdapter.GetCorrentRecoredIdPDApplication) ctx;
        marshMallowPermission = new MarshMallowPermission(ctx);
    }


    @Override
    public int getCount() {
        return pdApplicationList.size();
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
        return R.id.swipe_req_pd;

    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.inflater_pd_application_list_adapter, null);
    }

    @Override
    public void fillValues(final int position, View convertView) {
        final SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_req_pd);
//
        // final int po = position;
//        if (!String.valueOf(pdApplicationList.get(position).getPd_account_is_approve_value()).equalsIgnoreCase("Pending")) {
//            swipeLayout.setSwipeEnabled(false);
//        }

        CustomTextView tvPDStatusPDApplication, tvPDDocumentStatusPDApplication,tvEventNamePDApplication;

        ImageView iv_edit_req_pd, iv_delete_req_pd, iv_upload_approval_pd_document,
                iv_review_approval_pd_document, iv_print_approval_pd_document;
        LinearLayout main_ll_req_pd, ll_upload_pending_file_pd;

        iv_review_approval_pd_document = convertView.findViewById(R.id.iv_review_approval_pd_document);
        iv_edit_req_pd = (ImageView) convertView.findViewById(R.id.iv_edit_req_pd);
        iv_delete_req_pd = (ImageView) convertView.findViewById(R.id.iv_delete_req_pd);
        iv_upload_approval_pd_document = convertView.findViewById(R.id.iv_upload_approval_pd_document);
        iv_print_approval_pd_document = convertView.findViewById(R.id.iv_print_approval_pd_document);

        tvPDStatusPDApplication = convertView.findViewById(R.id.tvPDStatusPDApplication);
        tvPDDocumentStatusPDApplication = convertView.findViewById(R.id.tvPDDocumentStatusPDApplication);
        tvEventNamePDApplication = convertView.findViewById(R.id.tvEventNamePDApplication);

        ll_upload_pending_file_pd = convertView.findViewById(R.id.ll_upload_pending_file_pd);

        main_ll_req_pd = (LinearLayout) convertView.findViewById(R.id.main_ll_req_pd);

        if (pdApplicationList.get(position).getFile_count() == 0) {
            ll_upload_pending_file_pd.setVisibility(View.VISIBLE);
        }


        if (position % 2 == 0) {
            main_ll_req_pd.setBackgroundColor(ctx.getResources().getColor(R.color.test));
        }else {
            main_ll_req_pd.setBackgroundColor(ctx.getResources().getColor(R.color.white));
        }


        if (pdApplicationList.get(position).getPd_is_approve_value().equalsIgnoreCase("R")) {

            iv_edit_req_pd.setVisibility(View.GONE);
            iv_delete_req_pd.setVisibility(View.GONE);
            iv_upload_approval_pd_document.setVisibility(View.GONE);
            iv_review_approval_pd_document.setVisibility(View.GONE);
            iv_print_approval_pd_document.setVisibility(View.GONE);

        } else if (pdApplicationList.get(position).getPd_is_approve_value().equalsIgnoreCase("A") &&
                pdApplicationList.get(position).getPd_document_is_approve_value().equalsIgnoreCase("A")) {
            iv_edit_req_pd.setVisibility(View.GONE);
            iv_delete_req_pd.setVisibility(View.GONE);
            iv_upload_approval_pd_document.setVisibility(View.GONE);
            iv_review_approval_pd_document.setVisibility(View.VISIBLE);
            iv_print_approval_pd_document.setVisibility(View.VISIBLE);
        } else if (pdApplicationList.get(position).getPd_is_approve_value().equalsIgnoreCase("A") &&
                pdApplicationList.get(position).getPd_document_is_approve_value().equalsIgnoreCase("P")) {
            iv_edit_req_pd.setVisibility(View.VISIBLE);
            iv_delete_req_pd.setVisibility(View.GONE);
            iv_upload_approval_pd_document.setVisibility(View.GONE);
            iv_review_approval_pd_document.setVisibility(View.VISIBLE);
            iv_print_approval_pd_document.setVisibility(View.GONE);
        } else if (pdApplicationList.get(position).getPd_is_approve_value().equalsIgnoreCase("A") &&
                pdApplicationList.get(position).getPd_document_is_approve_value().equalsIgnoreCase(IntentConstants.DOCUMENT_UPLOAD_PENDING)) {
            iv_edit_req_pd.setVisibility(View.GONE);
            iv_delete_req_pd.setVisibility(View.GONE);
            iv_upload_approval_pd_document.setVisibility(View.VISIBLE);
            iv_review_approval_pd_document.setVisibility(View.VISIBLE);
            iv_print_approval_pd_document.setVisibility(View.GONE);
        } else if (pdApplicationList.get(position).getPd_is_approve_value().equalsIgnoreCase("A") &&
                pdApplicationList.get(position).getPd_document_is_approve_value().equalsIgnoreCase("R")) {
            iv_edit_req_pd.setVisibility(View.GONE);
            iv_delete_req_pd.setVisibility(View.GONE);
            iv_upload_approval_pd_document.setVisibility(View.GONE);
            iv_review_approval_pd_document.setVisibility(View.VISIBLE);
            iv_print_approval_pd_document.setVisibility(View.GONE);
        } else if (pdApplicationList.get(position).getPd_is_approve_value().equalsIgnoreCase("P") &&
                pdApplicationList.get(position).getPd_document_is_approve_value().equalsIgnoreCase("P")) {
            iv_edit_req_pd.setVisibility(View.VISIBLE);
            iv_delete_req_pd.setVisibility(View.VISIBLE);
            iv_upload_approval_pd_document.setVisibility(View.GONE);
            iv_review_approval_pd_document.setVisibility(View.VISIBLE);
            iv_print_approval_pd_document.setVisibility(View.GONE);
        } else if (pdApplicationList.get(position).getPd_is_approve_value().equalsIgnoreCase("P") &&
                pdApplicationList.get(position).getPd_document_is_approve_value().equalsIgnoreCase(IntentConstants.DOCUMENT_UPLOAD_PENDING)) {
            iv_edit_req_pd.setVisibility(View.VISIBLE);
            iv_delete_req_pd.setVisibility(View.VISIBLE);
            iv_upload_approval_pd_document.setVisibility(View.GONE);
            iv_review_approval_pd_document.setVisibility(View.GONE);
            iv_print_approval_pd_document.setVisibility(View.GONE);
        }

        main_ll_req_pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pdApplicationList.get(position).getFile_count() == 1) {
                    Intent intent = new Intent(ctx, ReqViewPDApplicationActivity.class);
                    intent.putExtra(IntentConstants.ID_PD_APPLICATION, pdApplicationList.get(position).getId() + "");
                    intent.putExtra(IntentConstants.PD_EVENT_TYPE_NAME_VIEW_PD, pdApplicationList.get(position).getEv_type_name());
                    intent.putExtra(IntentConstants.PD_EVENT_CATEGORY_NAME_VIEW_PD, pdApplicationList.get(position).getEv_category_name());
                    intent.putExtra(IntentConstants.PD_APPLICANT_ROLLL_NAME_VIEW_PD, pdApplicationList.get(position).getEv_role_name());
                    ctx.startActivity(intent);
                } else {
                    Toast.makeText(ctx, "Please Upload File", Toast.LENGTH_SHORT).show();
                }

            }
        });

        iv_print_approval_pd_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //swipeLayout.close();
                DialogUtils.showProgressDialog(ctx, "");
                StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.Print_of_Approved_record_by_id_PD_Application, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DialogUtils.hideProgressDialog();
                        try {

                            JSONObject obj = new JSONObject(response);
                            String file_url = obj.getString("URL");

//                            Gson gson = new Gson();
//                            PrintApprovedPDDocumentPojo printApprovedPDDocumentPojo = gson.fromJson("", PrintApprovedPDDocumentPojo.class);
//
//                            String file_url = printApprovedPDDocumentPojo.getURL();

                            if (!TextUtils.isEmpty(file_url)) {

                                String url_con = file_url;
                                System.out.println("url_con ::::::::::::::::::::: " + url_con);

                                String[] file2 = url_con.split("/");

                                String result1 = file2[file2.length - 1];

                                System.out.println("result1::::" + result1);
                                String nameoffile1 = result1;


                                System.out.println("nameoffile1****" + System.currentTimeMillis() + "_" + nameoffile1);


                                String url = url_con;
                                if (url != null && url.length() > 5) {
                                    String extention = url.substring(url.lastIndexOf("."), url.length());
                                    //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
                                    new ReqPDApplicationListAdapter.DownloadFileFromURL(extention).execute(url);
                                } else {
                                    DialogUtils.Show_Toast(ctx, "No Documents Available");
                                }


                            } else {
                                Toast.makeText(ctx, "file url is empty or null", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            DialogUtils.hideProgressDialog();
                            Toast.makeText(ctx, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        params2.put("id", pdApplicationList.get(position).getId() + "");
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


//                //swipeLayout.close();
//                DialogUtils.showProgressDialog(ctx, "");
//                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                        URLS.Print_of_Approved_record_by_id_PD_Application, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        DialogUtils.hideProgressDialog();
//                        try {
//                            String file_url = response.getString("URL");
//
//                            if (!TextUtils.isEmpty(file_url)) {
//
//                                if (!marshMallowPermission.checkPermissionForExternalStorage()) {
//                                    marshMallowPermission.requestPermissionForExternalStorage();
//                                } else {
//
//                                    String url_con = file_url;
//                                    System.out.println("url_con ::::::::::::::::::::: " + url_con);
//
//                                    String[] file2 = url_con.split("/");
//
//                                    String result1 = file2[file2.length - 1];
//
//                                    System.out.println("result1::::" + result1);
//                                    String nameoffile1 = result1;
//
//
//                                    System.out.println("nameoffile1****" + System.currentTimeMillis() + "_" + nameoffile1);
//
//
//                                    String url = url_con;
//                                    if (url != null && url.length() > 5) {
//                                        String extention = url.substring(url.lastIndexOf("."), url.length());
//                                        //Log.d("syllabuspdfurl", syllabusdetail.get(position).getPdf());
//                                        new ReqPDApplicationListAdapter.DownloadFileFromURL(extention).execute(url);
//                                    } else {
//                                        DialogUtils.Show_Toast(ctx, "No Documents Available");
//                                    }
//                                }
//
//                            } else {
//                                Toast.makeText(ctx, "file url is empty or null", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e) {
//                            DialogUtils.hideProgressDialog();
//                            Toast.makeText(ctx, "Exception:- " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        DialogUtils.Show_Toast(ctx, "Please Try Again Later");
//                        DialogUtils.hideProgressDialog();
//                        System.out.println("errorrrrrrrrrr " + error);
//                        System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params2 = new Hashtable<>();
//                        params2.put("id", String.valueOf(pdApplicationList.get(position).getId()));
//                        return params2;
//                    }
//
//                    @Override
//                    public String getBodyContentType() {
//                        return "application/x-www-form-urlencoded; charset=UTF-8";
//                    }
//                };
//                jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                queue.add(jsonObjReq);


            }
        });

        iv_review_approval_pd_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, PDApprovalRemarksAndReasonActivity.class);
                intent.putExtra(IntentConstants.PD_APPROVAL_REMARK_ID, pdApplicationList.get(position).getId() + "");
                ctx.startActivity(intent);

            }
        });

        ll_upload_pending_file_pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCorrentRecoredId.setRecoredId(pdApplicationList.get(position).getId() + "");

                new MaterialFilePicker()
                        .withActivity((Activity) ctx)
                        .withRequestCode(IntentConstants.REQUEST_CODE_FOR_UPLOAD_DOCUMENT)
                        .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                        .withFilterDirectories(false) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();

            }
        });


        iv_upload_approval_pd_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //swipeLayout.close();
                DialogUtils.showProgressDialog(ctx, "");
                StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_Approved_Record_Document_Upload_PD_Application, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        DialogUtils.hideProgressDialog();
                        if (!TextUtils.isEmpty(response)) {
                            Intent intent = new Intent(ctx, UploadApprovalPDApplicationDocumentActivity.class);
                            intent.putExtra(IntentConstants.UPLOAD_PD_DOCUMENT_ID, pdApplicationList.get(position).getId() + "");
                            intent.putExtra(IntentConstants.EVENT_NAME_PD_APPROVAL, pdApplicationList.get(position).getPd_event_name());
                            intent.putExtra(IntentConstants.EVENT_TYPE_PD_APPROVAL, pdApplicationList.get(position).getEv_type_name());
                            intent.putExtra(IntentConstants.ORGANIZED_BY_PD_APPROVAL, pdApplicationList.get(position).getPd_organized_by());
                            intent.putExtra(IntentConstants.EVENT_CATEGORY_PD_APPROVAL, pdApplicationList.get(position).getEv_category_name());
                            ctx.startActivity(intent);
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
                        params2.put("emp_id", mySharedPreferecesRKUOLD.getEmpID());
                        params2.put("id", String.valueOf(pdApplicationList.get(position).getId()));
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


        iv_edit_req_pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //swipeLayout.close();

                if (pdApplicationList.get(position).getPd_is_approve_value().equalsIgnoreCase(IntentConstants.APPROVED_STATUS) &&
                        pdApplicationList.get(position).getPd_document_is_approve_value().equalsIgnoreCase(IntentConstants.PENDING_STATUS)) {
                    Intent intent = new Intent(ctx, PDUpdateCreditActivity.class);
                    intent.putExtra(IntentConstants.PD_APPLICATION_LIST_ITEM, pdApplicationList.get(position));
                    ctx.startActivity(intent);
                } else {
                    DialogUtils.showProgressDialog(ctx, "");
                    StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_File_Upload_PD_Application, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (!TextUtils.isEmpty(response)) {
                                callViewPDApplicationAPI(position);
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
                            params2.put("id", String.valueOf(pdApplicationList.get(position).getId()));
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
            }
        });

        iv_delete_req_pd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogUtils.showDialog4YNo(ctx, "", "Are You Sure You want to delete ?", new DialogUtils.DailogCallBackOkButtonClick() {
                    @Override
                    public void onDialogOkButtonClicked() {
                        DialogUtils.showProgressDialog(ctx, "");
                        StringRequest deletePublication = new StringRequest(Request.Method.POST, URLS.Delete_File_Upload_PD_Application, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (!TextUtils.isEmpty(response)) {
                                    //swipeLayout.close();
                                    callDeletePDApplicationRequest(position);
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
                                params2.put("id", String.valueOf(pdApplicationList.get(position).getId()));
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

        tvPDStatusPDApplication.setText(String.valueOf(pdApplicationList.get(position).getPd_is_approve_value()));
        tvPDDocumentStatusPDApplication.setText(String.valueOf(pdApplicationList.get(position).getPd_document_is_approve_value()));
        tvEventNamePDApplication.setText(String.valueOf(pdApplicationList.get(position).getPd_event_name()));
    }

    private void callDeletePDApplicationRequest(final int position) {
        StringRequest deletePdApplication = new StringRequest(Request.Method.POST, URLS.Delete_PD_Application, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    pdApplicationList.remove(position);
                    notifyDataSetChanged();
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    DialogUtils.Show_Toast(ctx, responsePojo.getData().get(0).getMsg());
                } else {
                    DialogUtils.hideProgressDialog();
                    Toast.makeText(ctx, "Response:- " + response, Toast.LENGTH_SHORT).show();
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
                params2.put("id", String.valueOf(pdApplicationList.get(position).getId()));
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
        deletePdApplication.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(deletePdApplication);
    }


    private void callViewPDApplicationAPI(final int position) {

//        DialogUtils.showProgressDialog(ctx, "");
        StringRequest savePublicationInConferenceRequest = new StringRequest(Request.Method.POST, URLS.View_by_id_PD_Application, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    ViewPDApplicationPojo reqViewPDApplicationPojo = gson.fromJson("{\"Data\":" + response + "}", ViewPDApplicationPojo.class);
                    if (reqViewPDApplicationPojo.getData() != null) {
                        Intent intent = new Intent(ctx, ReqPDApplicationActivity.class);
                        intent.putExtra(IntentConstants.REQ_VIEW_PD_APPLICATION_POJO, (Serializable) reqViewPDApplicationPojo.getData());
                        intent.putExtra(IntentConstants.PD_EVENT_TYPE_NAME_VIEW_PD, pdApplicationList.get(position).getEv_type_name());
                        intent.putExtra(IntentConstants.PD_EVENT_CATEGORY_NAME_VIEW_PD, pdApplicationList.get(position).getEv_category_name());
                        intent.putExtra(IntentConstants.PD_APPLICANT_ROLLL_NAME_VIEW_PD, pdApplicationList.get(position).getEv_role_name());
                        ((Activity) ctx).startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_EDIT_PD_APPLICATION);
                    } else {
                        DialogUtils.hideProgressDialog();
                        DialogUtils.Show_Toast(ctx, response);
                    }
                } else {
                    DialogUtils.hideProgressDialog();
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
                params2.put("id", pdApplicationList.get(position).getId() + "");
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

    public interface GetCorrentRecoredIdPDApplication {
        void setRecoredId(String recoredId);
    }


    private class DownloadFileFromURL extends AsyncTask<String, String, String> {


        String nameoffile;
        String Extenson;

        DownloadFileFromURL(String Extenson) {
            this.Extenson = Extenson;

            System.out.println("EXTENSION OF FILE::::::::::" + Extenson);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            DialogUtils.showProgressDialog(ctx, "");

        }

        /**
         * Downloading file in menubackground thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);

                String urofysllabusl = f_url[0];
                System.out.println("urofysllabusl::::::" + urofysllabusl);
                String[] parts = urofysllabusl.split("/");
                System.out.println("parts::::::::::::::" + parts);
                String result = parts[parts.length - 1];
                System.out.println("result:::::::::::" + result);
                nameoffile = result;

                System.out.println("result::::::doInback::::" + result);
                System.out.println("name in  doInBackground>>>>" + nameoffile);
                URLConnection conection = url.openConnection();
                conection.connect();
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Rku/" + "/PatentAwarded/");
                //File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+URLS.folder_name +"/");
                dir.mkdirs();


                System.out.println("path of file>>>>>" + dir.getAbsolutePath());
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();
                //  Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());

//                OutputStream output = new FileOutputStream("sdcard/" + URLS.folder_name +"/" + nameoffile);
                OutputStream output = new FileOutputStream("sdcard/Rku/" + "/PatentAwarded/" + nameoffile);

                System.out.println("output:::::::::" + output.toString());
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();


            } catch (MalformedURLException e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            //pDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            DialogUtils.hideProgressDialog();

            DialogUtils.Show_Toast(ctx, "Download Completed Successfully");

            System.out.println("EXTENSION OF FILE onPostExecute::::::::::" + Extenson);

//            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), URLS.folder_name + "/" + nameoffile);
            File file11 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Rku/" + "/PatentAwarded/" + nameoffile);
            Log.d("pathoffile", String.valueOf(file11));
//                                    Uri uri = FileProvider.getUriForFile(AssignmentActivity.this, "com.infinity.infoway.atmiya.activity.AssignmentActivity.fileprovider", file11);
//

            /// for  opening downloaded documentssssssssssss
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT > 24) {


                System.out.println("path of file is :::::::::::::: " + file11.getPath());
                Uri uri = FileProvider.getUriForFile(ctx, ctx.getPackageName() + ".fileprovider", file11);
//                Uri uri = FileProvider.getUriForFile(_context, _context.getPackageName() , file11);
                ctx.grantUriPermission(ctx.getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent = null;
                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(uri, "application/pdf");
                }
                intent = Intent.createChooser(target, "Open File");
                try {
                    ctx.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(ctx, "No Apps can performs This action");
                }
            } else {

                target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                target.setDataAndType(Uri.fromFile(file11), "image/*");

                if (Extenson.compareToIgnoreCase(".pdf") == 0 || Extenson.compareToIgnoreCase("pdf") == 0) {
                    target.setDataAndType(Uri.fromFile(file11), "application/pdf");
                }

                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    ctx.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    DialogUtils.Show_Toast(ctx, "No Apps can performs This action");

                }

            }

        }

    }
}



