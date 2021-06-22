package com.infinity.infoway.rkuniversity.rku_old_app.Activity;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.SpinnerSimpleAdapter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetAllMenuWiseRightPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetAllModulePojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetAllParentMenuPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetAllSubMenuByParentMenuPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.GetAllUserPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.ParentMenuModel;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.SubMenuModel;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.UserWiseSubMenuModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.UserWiseSubMenuWritesModel;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class UserWiseMenuActivity extends AppCompatActivity implements View.OnClickListener {

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;

    Spinner spModuleUserWiseMenuAct, spMenuUserWiseMenuAct;

    SearchableSpinner searchSpUserWiseMenuAct;

    ArrayList<String> allUserOpetions = new ArrayList<>();
    HashMap<String, String> hashMapUserOpetionAndID = new HashMap<>();

    ArrayList<String> allModuleMenuOpetions = new ArrayList<>();
    HashMap<String, String> hashMapModuleMenuAndID = new HashMap<>();

    ArrayList<String> allParentMenuOpetions = new ArrayList<>();
    HashMap<String, String> hashMapParentMenuAndID = new HashMap<>();


    List<GetAllUserPojo.DataBean> allUserPojoList;
    List<GetAllModulePojo.DataBean> allModulePojoList;
    List<GetAllParentMenuPojo.DataBean> allParentMenuPojoList;
    List<GetAllSubMenuByParentMenuPojo.SubMenuBean> allSubMenuByParentMenuPojoList;
    List<GetAllMenuWiseRightPojo.DataBean> getAllMenuWiseWrightesPojoList;

    List<ParentMenuModel.DataBean> parentMenuModelList;
    List<SubMenuModel.ChildrenBean> submenuModelList;

    ArrayList<UserWiseSubMenuModel> groupArrayList;
    HashMap<String, ArrayList<UserWiseSubMenuWritesModel>> hashMapChildren;

    CustomBoldTextView tvLoadUserWiseMenu, tvCancelUserWiseMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_wise_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        getAllUserApiCall();

        spModuleUserWiseMenuAct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    getAllParentMenuApiCall(String.valueOf(hashMapModuleMenuAndID.get(allModuleMenuOpetions.get(position))));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initView() {

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText("User Wise Menu");

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        searchSpUserWiseMenuAct = findViewById(R.id.searchSpUserWiseMenuAct);
        searchSpUserWiseMenuAct.setTitle("Select User");
        searchSpUserWiseMenuAct.setPositiveButton("OK");

//        spUserUserWiseMenuAct = findViewById(R.id.spUserUserWiseMenuAct);
//        allUserOpetions.add("Select User");
        spModuleUserWiseMenuAct = findViewById(R.id.spModuleUserWiseMenuAct);
        allModuleMenuOpetions.add("Select Module");
        spMenuUserWiseMenuAct = findViewById(R.id.spMenuUserWiseMenuAct);
        allParentMenuOpetions.add("Select Menu");

        tvLoadUserWiseMenu = findViewById(R.id.tvLoadUserWiseMenu);
        tvLoadUserWiseMenu.setOnClickListener(this);
        tvCancelUserWiseMenu = findViewById(R.id.tvCancelUserWiseMenu);
        tvCancelUserWiseMenu.setOnClickListener(this);

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

    private boolean isValid() {
//        if (allUserOpetions.get(searchSpUserWiseMenuAct.getSelectedItemPosition()).equalsIgnoreCase("Select User")) {
//            DialogUtils.Show_Toast(UserWiseMenuActivity.this, "Select User");
//            return false;
//        } else
        if (allModuleMenuOpetions.get(spModuleUserWiseMenuAct.getSelectedItemPosition()).equalsIgnoreCase("Select Module")) {
            DialogUtils.Show_Toast(UserWiseMenuActivity.this, "Select Module");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back || view.getId() == R.id.tvCancelUserWiseMenu) {
            onBackPressed();
        } else if (view.getId() == R.id.tvLoadUserWiseMenu) {
            if (isValid()) {

                String User_id = mySharedPreferecesRKUOLD.getUserID();
                String Parent_id = hashMapParentMenuAndID.get(allParentMenuOpetions.get(spMenuUserWiseMenuAct.getSelectedItemPosition()));

                getAllParentMenuApiCall(User_id, Parent_id);
            }
        }
    }

    private void getAllUserApiCall() {

        DialogUtils.showProgressDialog(UserWiseMenuActivity.this, "");
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.GET, URLS.Get_All_User, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    allUserPojoList = new ArrayList<>();
                    Gson gson = new Gson();
                    GetAllUserPojo getAllUserPojo = gson.fromJson("{\"Data\":" + response + "}", GetAllUserPojo.class);
                    if (getAllUserPojo.getData() != null && getAllUserPojo.getData().size() > 0) {
                        allUserPojoList = getAllUserPojo.getData();

                        for (int i = 0; i < allUserPojoList.size(); i++) {
                            hashMapUserOpetionAndID.put(String.valueOf(allUserPojoList.get(i).getUser_name()),
                                    String.valueOf(allUserPojoList.get(i).getUser_id()));
                            allUserOpetions.add(String.valueOf(allUserPojoList.get(i).getUser_name()));
                        }
                        searchSpUserWiseMenuAct.setAdapter(new ArrayAdapter<String>(UserWiseMenuActivity.this, android.R.layout.simple_spinner_dropdown_item, allUserOpetions));

//                        SpinnerSimpleAdapter userOpetionAdapter = new SpinnerSimpleAdapter(UserWiseMenuActivity.this, allUserOpetions);
//                        spUserUserWiseMenuAct.setAdapter(userOpetionAdapter);

                        getAllModuleApiCall();

                    } else {
                        DialogUtils.Show_Toast(UserWiseMenuActivity.this, "User List Empty or null!");
                    }
                } else {
                    DialogUtils.Show_Toast(UserWiseMenuActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(UserWiseMenuActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        getPatentAwardedList.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getPatentAwardedList);

    }


    private void getAllModuleApiCall() {

//        DialogUtils.showProgressDialog(UserWiseMenuActivity.this, "");
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.GET, URLS.Get_All_Module, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    allModulePojoList = new ArrayList<>();
                    Gson gson = new Gson();
                    GetAllModulePojo getAllModulePojo = gson.fromJson("{\"Data\":" + response + "}", GetAllModulePojo.class);
                    if (getAllModulePojo.getData() != null && getAllModulePojo.getData().size() > 0) {
                        allModulePojoList = getAllModulePojo.getData();

                        for (int i = 0; i < allModulePojoList.size(); i++) {
                            hashMapModuleMenuAndID.put(String.valueOf(allModulePojoList.get(i).getMen_Item()),
                                    String.valueOf(allModulePojoList.get(i).getMen_id()));
                            allModuleMenuOpetions.add(String.valueOf(allModulePojoList.get(i).getMen_Item()));
                        }
                        SpinnerSimpleAdapter moduleOpetionAdapter = new SpinnerSimpleAdapter(UserWiseMenuActivity.this, allModuleMenuOpetions);
                        spModuleUserWiseMenuAct.setAdapter(moduleOpetionAdapter);

                    } else {

                        DialogUtils.Show_Toast(UserWiseMenuActivity.this, "User Module is Empty or null!");
                    }
                } else {
                    DialogUtils.Show_Toast(UserWiseMenuActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(UserWiseMenuActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        getPatentAwardedList.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(getPatentAwardedList);

    }


    private void getAllParentMenuApiCall(final String Module_id) {

        DialogUtils.showProgressDialog(UserWiseMenuActivity.this, "");
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Get_All_Parent_Menu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                if (!TextUtils.isEmpty(response)) {
                    allParentMenuPojoList = new ArrayList<>();
                    Gson gson = new Gson();
                    GetAllParentMenuPojo getAllParentMenuPojo = gson.fromJson("{\"Data\":" + response + "}", GetAllParentMenuPojo.class);
                    if (getAllParentMenuPojo.getData() != null && getAllParentMenuPojo.getData().size() > 0) {
                        allParentMenuPojoList = getAllParentMenuPojo.getData();

                        for (int i = 0; i < allParentMenuPojoList.size(); i++) {
                            hashMapParentMenuAndID.put(String.valueOf(allParentMenuPojoList.get(i).getMen_Item()),
                                    String.valueOf(allParentMenuPojoList.get(i).getMen_id()));
                            allParentMenuOpetions.add(String.valueOf(allParentMenuPojoList.get(i).getMen_Item()));
                        }
                        SpinnerSimpleAdapter parentMenuOpetionAdapter = new SpinnerSimpleAdapter(UserWiseMenuActivity.this, allParentMenuOpetions);
                        spMenuUserWiseMenuAct.setAdapter(parentMenuOpetionAdapter);

                    } else {
                        DialogUtils.Show_Toast(UserWiseMenuActivity.this, "parent menu is empty or null!");
                    }
                } else {
                    DialogUtils.Show_Toast(UserWiseMenuActivity.this, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(UserWiseMenuActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("Module_id", Module_id);
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


    private void getAllParentMenuApiCall(final String User_id, final String Parent_id) {

        DialogUtils.showProgressDialog(UserWiseMenuActivity.this, "");
        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Get_All_Sub_Menu_by_Parent_Menu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();
                try {
                    if (!TextUtils.isEmpty(response)) {
                        allSubMenuByParentMenuPojoList = new ArrayList<>();
                        parentMenuModelList = new ArrayList<>();
                        submenuModelList = new ArrayList<>();
                        Gson gson = new Gson();

                        GetAllSubMenuByParentMenuPojo getAllSubMenuByParentMenuPojo = gson.fromJson("{\"SubMenu\":" + response + "}", GetAllSubMenuByParentMenuPojo.class);

                        ParentMenuModel parentMenuModel = gson.fromJson("{\"Data\":" + getAllSubMenuByParentMenuPojo.getSubMenu().get(0).getData() + "}", ParentMenuModel.class);
                        parentMenuModelList = parentMenuModel.getData();

                        SubMenuModel subMenuModel = gson.fromJson("{\"Children\":" + getAllSubMenuByParentMenuPojo.getSubMenu().get(0).getChildren() + "}", SubMenuModel.class);
                        submenuModelList = subMenuModel.getChildrenForSubMenuModel();

                        if (getAllSubMenuByParentMenuPojo.getSubMenu() != null && getAllSubMenuByParentMenuPojo.getSubMenu().size() > 0) {
                            allSubMenuByParentMenuPojoList = getAllSubMenuByParentMenuPojo.getSubMenu();

                            seperateSubMenuAndWritesNew(parentMenuModelList, submenuModelList);

                        } else {
                            DialogUtils.Show_Toast(UserWiseMenuActivity.this, "sub menu by parent menu is empty or null!");
                        }
                    } else {
                        DialogUtils.Show_Toast(UserWiseMenuActivity.this, response);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(UserWiseMenuActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params2 = new Hashtable<>();
                params2.put("User_id", User_id);
                params2.put("Parent_id", Parent_id);
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


    private void seperateSubMenuAndWrites(List<ParentMenuModel.DataBean> parentMenuModelList, List<SubMenuModel.ChildrenBean> submenuModelList) {

        try {

            if (submenuModelList != null && submenuModelList.size() > 0) {
                groupArrayList = new ArrayList<>();
                hashMapChildren = new HashMap<>();

                String[] writesNameArray = submenuModelList.get(0).getSub_menu_name().split(",");
                String[] checkedWritesNameArray = submenuModelList.get(0).getChecked_Parameter_value().split(",");
                String[] writesId = submenuModelList.get(0).getSub_menu_id().split(",");

                int k = 0;

                for (int i = 0; i < submenuModelList.size(); i++) {

                    UserWiseSubMenuModel userWiseSubMenuModel = new UserWiseSubMenuModel();
                    userWiseSubMenuModel.setSubMenuName(submenuModelList.get(i).getMenu_name());
                    groupArrayList.add(userWiseSubMenuModel);

                    ArrayList<UserWiseSubMenuWritesModel> userWiseSubMenuWritesModelArrayList = new ArrayList<>();

                    for (int j = 0; j < writesNameArray.length; j++) {
                        UserWiseSubMenuWritesModel userWiseSubMenuWritesModel = new UserWiseSubMenuWritesModel();
                        userWiseSubMenuWritesModel.setWritesName(writesNameArray[j]);

                        if (k < checkedWritesNameArray.length) {
                            if (String.valueOf(writesNameArray[j].trim()).equalsIgnoreCase(String.valueOf(checkedWritesNameArray[k].trim()))) {
                                userWiseSubMenuWritesModel.setChecked(true);
                                k++;
                            } else {
                                userWiseSubMenuWritesModel.setChecked(false);
                            }
                        } else {
                            userWiseSubMenuWritesModel.setChecked(false);
                        }

//                    if (k < checkedWritesNameArray.length && writesNameArray[k].equalsIgnoreCase(checkedWritesNameArray[k])) {
//                        userWiseSubMenuWritesModel.setChecked(true);
//                        k++;
//                    } else {
//                        userWiseSubMenuWritesModel.setChecked(false);
//                    }

                        userWiseSubMenuWritesModel.setPer_id(writesId[j]);
                        userWiseSubMenuWritesModel.setMenu_user_id(hashMapUserOpetionAndID.get(allUserOpetions.get(searchSpUserWiseMenuAct.getSelectedItemPosition())));
                        userWiseSubMenuWritesModel.setMenu_id(String.valueOf(submenuModelList.get(i).getMenu_id()));
                        userWiseSubMenuWritesModel.setModule_id(hashMapModuleMenuAndID.get(allModuleMenuOpetions.get(spModuleUserWiseMenuAct.getSelectedItemPosition())));

                        userWiseSubMenuWritesModelArrayList.add(userWiseSubMenuWritesModel);
                    }
                    hashMapChildren.put(submenuModelList.get(i).getMenu_name(), userWiseSubMenuWritesModelArrayList);
                }
                Intent intent = new Intent(UserWiseMenuActivity.this, UserWiseMenuDetailActivity.class);
                intent.putExtra(IntentConstants.PARENT_MENU, parentMenuModelList.get(0).getParent_menu());
                intent.putExtra(IntentConstants.GROUP_USER_WISE_MENU_ARRAY_LIST, (Serializable) groupArrayList);
                intent.putExtra(IntentConstants.HASH_MAP_CHILDREN, (Serializable) hashMapChildren);
                startActivity(intent);
            } else {
                Toast.makeText(this, "There no any writes found against " + mySharedPreferecesRKUOLD.getUserID() + " User id", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    private void seperateSubMenuAndWritesNew(List<ParentMenuModel.DataBean> parentMenuModelList,
                                             List<SubMenuModel.ChildrenBean> submenuModelList) {

        try {

            if (submenuModelList != null && submenuModelList.size() > 0) {
                groupArrayList = new ArrayList<>();
                hashMapChildren = new HashMap<>();

                for (int i = 0; i < submenuModelList.size(); i++) {
                    UserWiseSubMenuModel userWiseSubMenuModel = new UserWiseSubMenuModel();
                    userWiseSubMenuModel.setSubMenuName(submenuModelList.get(i).getMenu_name());
                    groupArrayList.add(userWiseSubMenuModel);

                    String[] writesNameArray = submenuModelList.get(i).getSub_menu_name().split(",");
                    String[] checkedWritesNameArray = submenuModelList.get(i).getChecked_Parameter_value().split(",");
                    String[] writesId = submenuModelList.get(i).getSub_menu_id().split(",");

                    ArrayList<UserWiseSubMenuWritesModel> userWiseSubMenuWritesModelArrayList = new ArrayList<>();

                    int k = 0;

                    if (writesNameArray != null && writesNameArray.length > 0 && !TextUtils.isEmpty(writesNameArray[0])) {
                        for (int j = 0; j < writesNameArray.length; j++) {
                            UserWiseSubMenuWritesModel userWiseSubMenuWritesModel = new UserWiseSubMenuWritesModel();
                            userWiseSubMenuWritesModel.setWritesName(writesNameArray[j]);
                            userWiseSubMenuWritesModel.setPer_id(writesId[j]);
                            userWiseSubMenuWritesModel.setMenu_user_id(hashMapUserOpetionAndID.get(allUserOpetions.get(searchSpUserWiseMenuAct.getSelectedItemPosition())));
                            userWiseSubMenuWritesModel.setMenu_id(String.valueOf(submenuModelList.get(i).getMenu_id()));
                            userWiseSubMenuWritesModel.setModule_id(hashMapModuleMenuAndID.get(allModuleMenuOpetions.get(spModuleUserWiseMenuAct.getSelectedItemPosition())));

                            if (checkedWritesNameArray != null &&
                                    checkedWritesNameArray.length > 0 &&
                                    !TextUtils.isEmpty(checkedWritesNameArray[0])) {
                                if (k < checkedWritesNameArray.length &&
                                        writesNameArray[j].trim().equalsIgnoreCase(checkedWritesNameArray[k].trim())) {
                                    userWiseSubMenuWritesModel.setChecked(true);
                                    k++;
                                } else {
                                    userWiseSubMenuWritesModel.setChecked(false);
                                }
                            } else {
                                userWiseSubMenuWritesModel.setChecked(false);
                            }

                            userWiseSubMenuWritesModelArrayList.add(userWiseSubMenuWritesModel);
                        }
                    } else {
                        Toast.makeText(this, "Writest Not Found!", Toast.LENGTH_SHORT).show();
                    }
                    hashMapChildren.put(submenuModelList.get(i).getMenu_name(), userWiseSubMenuWritesModelArrayList);
                }


                Intent intent = new Intent(UserWiseMenuActivity.this, UserWiseMenuDetailActivity.class);
                intent.putExtra(IntentConstants.PARENT_MENU, parentMenuModelList.get(0).getParent_menu());
                intent.putExtra(IntentConstants.GROUP_USER_WISE_MENU_ARRAY_LIST, (Serializable) groupArrayList);
                intent.putExtra(IntentConstants.HASH_MAP_CHILDREN, (Serializable) hashMapChildren);
                startActivity(intent);


//                String[] writesNameArray = submenuModelList.get(0).getSub_menu_name().split(",");
//                String[] checkedWritesNameArray = submenuModelList.get(0).getChecked_Parameter_value().split(",");
//                String[] writesId = submenuModelList.get(0).getSub_menu_id().split(",");
//
//                int k = 0;
//
//                for (int i = 0; i < submenuModelList.size(); i++) {
//
//                    UserWiseSubMenuModel userWiseSubMenuModel = new UserWiseSubMenuModel();
//                    userWiseSubMenuModel.setSubMenuName(submenuModelList.get(i).getMenu_name());
//                    groupArrayList.add(userWiseSubMenuModel);
//
//                    ArrayList<UserWiseSubMenuWritesModel> userWiseSubMenuWritesModelArrayList = new ArrayList<>();
//
//                    for (int j = 0; j < writesNameArray.length; j++) {
//                        UserWiseSubMenuWritesModel userWiseSubMenuWritesModel = new UserWiseSubMenuWritesModel();
//                        userWiseSubMenuWritesModel.setWritesName(writesNameArray[j]);
//
//                        if (k < checkedWritesNameArray.length) {
//                            if (String.valueOf(writesNameArray[j].trim()).equalsIgnoreCase(String.valueOf(checkedWritesNameArray[k].trim()))) {
//                                userWiseSubMenuWritesModel.setChecked(true);
//                                k++;
//                            } else {
//                                userWiseSubMenuWritesModel.setChecked(false);
//                            }
//                        } else {
//                            userWiseSubMenuWritesModel.setChecked(false);
//                        }
//
////                    if (k < checkedWritesNameArray.length && writesNameArray[k].equalsIgnoreCase(checkedWritesNameArray[k])) {
////                        userWiseSubMenuWritesModel.setChecked(true);
////                        k++;
////                    } else {
////                        userWiseSubMenuWritesModel.setChecked(false);
////                    }
//
//                        userWiseSubMenuWritesModel.setPer_id(writesId[j]);
//                        userWiseSubMenuWritesModel.setMenu_user_id(hashMapUserOpetionAndID.get(allUserOpetions.get(searchSpUserWiseMenuAct.getSelectedItemPosition())));
//                        userWiseSubMenuWritesModel.setMenu_id(String.valueOf(submenuModelList.get(i).getMenu_id()));
//                        userWiseSubMenuWritesModel.setModule_id(hashMapModuleMenuAndID.get(allModuleMenuOpetions.get(spModuleUserWiseMenuAct.getSelectedItemPosition())));
//
//                        userWiseSubMenuWritesModelArrayList.add(userWiseSubMenuWritesModel);
//                    }
//                    hashMapChildren.put(submenuModelList.get(i).getMenu_name(), userWiseSubMenuWritesModelArrayList);
//                }
//                Intent intent = new Intent(UserWiseMenuActivity.this, UserWiseMenuDetailActivity.class);
//                intent.putExtra(IntentConstants.PARENT_MENU, parentMenuModelList.get(0).getParent_menu());
//                intent.putExtra(IntentConstants.GROUP_USER_WISE_MENU_ARRAY_LIST, (Serializable) groupArrayList);
//                intent.putExtra(IntentConstants.HASH_MAP_CHILDREN, (Serializable) hashMapChildren);
//                startActivity(intent);
            } else {
                Toast.makeText(this, "There no any writes found against " + mySharedPreferecesRKUOLD.getUserID() + " User id", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Exception:- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


//    private void getAllMenuWiseWritesApiCall(final String User_id,final String Main_menu_id) {
//
////        DialogUtils.showProgressDialog(UserWiseMenuActivity.this, "");
//        StringRequest getPatentAwardedList = new StringRequest(Request.Method.POST, URLS.Get_Menu_Wise_Right, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                DialogUtils.hideProgressDialog();
//                try{
//                    if (!TextUtils.isEmpty(response)) {
//                        getAllMenuWiseWrightesPojoList = new ArrayList<>();
//                        Gson gson = new Gson();
//                        GetAllMenuWiseRightPojo getAllMenuWiseRightPojo = gson.fromJson("{\"Data\":" + response + "}", GetAllMenuWiseRightPojo.class);
//                        if (getAllMenuWiseRightPojo.getData() != null && getAllMenuWiseRightPojo.getData().size() > 0) {
//                            getAllMenuWiseWrightesPojoList = getAllMenuWiseRightPojo.getData();
//                        } else {
//                            DialogUtils.Show_Toast(UserWiseMenuActivity.this, "sub menu by parent menu is empty or null!");
//                        }
//                    } else {
//                        DialogUtils.Show_Toast(UserWiseMenuActivity.this, response);
//                    }
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                DialogUtils.Show_Toast(UserWiseMenuActivity.this, "Please Try Again Later");
//                DialogUtils.hideProgressDialog();
//                System.out.println("errorrrrrrrrrr " + error);
//                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params2 = new Hashtable<>();
//                params2.put("User_id", User_id);
//                params2.put("Main_menu_id", Main_menu_id);
//                return params2;
//            }
//
//            @Override
//            public String getBodyContentType() {
////                return "application/json; charset=UTF-8";
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
//        };
//        getPatentAwardedList.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(getPatentAwardedList);
//
//    }

}
