package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;

import androidx.appcompat.widget.AppCompatCheckBox;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SaveDeleteUpdateResponsePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.models.UserWiseSubMenuModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.UserWiseSubMenuWritesModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class UserWiseMenuDetailsAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<UserWiseSubMenuModel> groupArrayList;
    HashMap<String, ArrayList<UserWiseSubMenuWritesModel>> hashMapChildren;
    RequestQueue queue;
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;

    public UserWiseMenuDetailsAdapter(Context context, ArrayList<UserWiseSubMenuModel> groupArrayList,
                                      HashMap<String, ArrayList<UserWiseSubMenuWritesModel>> hashMapChildren) {
        this.context = context;
        this.groupArrayList = groupArrayList;
        this.hashMapChildren = hashMapChildren;
        queue = Volley.newRequestQueue(context);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(context);
    }

    @Override
    public int getGroupCount() {
        return groupArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = groupArrayList.get(groupPosition).getSubMenuName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.inflater_user_wise_sub_menu, null);
        }
        CustomTextView tvUserWiseSubMenuName = convertView.findViewById(R.id.tvUserWiseSubMenuName);
//        LinearLayout llUserWiseSubMenu = convertView.findViewById(R.id.llUserWiseSubMenu);
        tvUserWiseSubMenuName.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getWritesName();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.inflater_user_wise_sub_menu_writes, null);
        }
        final AppCompatCheckBox cbUserWiseMenu = convertView.findViewById(R.id.cbUserWiseMenu);
        cbUserWiseMenu.setText(childText);

        if (hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).isChecked()) {
            cbUserWiseMenu.setChecked(true);
        } else {
            cbUserWiseMenu.setChecked(false);
        }

        cbUserWiseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbUserWiseMenu.isChecked()) {
                    saveUserWiseMenuPermissionApi(hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getPer_id(),
                            hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getMenu_user_id(),
                            hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getMenu_id(),
                            mySharedPreferecesRKUOLD.getUserID(), "1",groupPosition,childPosition);
                } else {
                    deleteUserWiseMenuPermissionApi(hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getMenu_user_id(),
                            hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getMenu_id(),
                            hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getPer_id(),
                            mySharedPreferecesRKUOLD.getUserID(), "1",groupPosition,childPosition);
                }
            }
        });

//        cbUserWiseMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).setChecked(true);
//                    saveUserWiseMenuPermissionApi(hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getPer_id(), hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getMenu_user_id(),
//                            hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getMenu_id(), mySharedPrefereces.getUserID(), "1");
//                } else {
//                    hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).setChecked(false);
//                    deleteUserWiseMenuPermissionApi(hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getMenu_user_id(),
//                            hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).getModule_id(), mySharedPrefereces.getUserID(), "1");
//                }
//            }
//        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private void deleteUserWiseMenuPermissionApi(final String Menu_user_id, final String Module_id,
                                                 final String Sub_to_sub_menu_id, final String User_id,
                                                 final String ip, final int groupPosition,
                                                 final int childPosition) {

        DialogUtils.showProgressDialog(context, "");
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Delete_User_wise_Menu_Permission, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).setChecked(false);
                    Toast.makeText(context, ""+responsePojo.getData().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                    DialogUtils.hideProgressDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Please Try Again Later", Toast.LENGTH_SHORT).show();
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Menu_user_id", Menu_user_id);
                params2.put("Sub_menu_id", Module_id);
                params2.put("Sub_to_sub_menu_id",Sub_to_sub_menu_id);
                params2.put("User_id", User_id);
                params2.put("ip", ip);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getPatentAwardedList.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getPatentAwardedList);

    }

    private void saveUserWiseMenuPermissionApi(final String per_id, final String menu_user_id,
                                               final String menu_id, final String user_id,
                                               final String ip, final int groupPosition,
                                               final int childPosition) {

        DialogUtils.showProgressDialog(context, "");
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Save_User_wise_Menu_Permission, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    SaveDeleteUpdateResponsePojo responsePojo = gson.fromJson("{\"Data\":" + response + "}", SaveDeleteUpdateResponsePojo.class);
                    hashMapChildren.get(groupArrayList.get(groupPosition).getSubMenuName()).get(childPosition).setChecked(true);
                    Toast.makeText(context, ""+responsePojo.getData().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                    DialogUtils.hideProgressDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Please Try Again Later", Toast.LENGTH_SHORT).show();
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("per_id", per_id);
                params2.put("menu_user_id", menu_user_id);
                params2.put("menu_id", menu_id);
                params2.put("user_id", user_id);
                params2.put("ip", ip);
                return params2;
            }

            @Override
            public String getBodyContentType() {
//                return "application/json; charset=UTF-8";
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        getPatentAwardedList.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getPatentAwardedList);

    }

}
