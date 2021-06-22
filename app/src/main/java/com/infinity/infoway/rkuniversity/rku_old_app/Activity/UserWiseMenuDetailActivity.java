package com.infinity.infoway.rkuniversity.rku_old_app.Activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Adapter.UserWiseMenuDetailsAdapter;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomBoldTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.constants.IntentConstants;
import com.infinity.infoway.rkuniversity.rku_old_app.models.UserWiseSubMenuModel;
import com.infinity.infoway.rkuniversity.rku_old_app.models.UserWiseSubMenuWritesModel;

import java.util.ArrayList;
import java.util.HashMap;

public class UserWiseMenuDetailActivity extends AppCompatActivity implements View.OnClickListener{

    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    CustomBoldTextView tv_emp_code, txt_act, tv_version;
    RequestQueue queue;
    ImageView ivBack;
    ArrayList<UserWiseSubMenuModel> groupArrayList;
    HashMap<String,ArrayList<UserWiseSubMenuWritesModel>> hashMapChildren;
    ExpandableListView expLvUserWiseMenu;
    UserWiseMenuDetailsAdapter userWiseMenuDetailsAdapter;
    String parentMenu = "User Wise Menu";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_wise_menu_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
//        prepareExpandableListView();

        if (getIntent().hasExtra(IntentConstants.GROUP_USER_WISE_MENU_ARRAY_LIST)){
            parentMenu = getIntent().getStringExtra(IntentConstants.PARENT_MENU);
            txt_act.setText(parentMenu);
            groupArrayList = (ArrayList<UserWiseSubMenuModel>) getIntent().getSerializableExtra(IntentConstants.GROUP_USER_WISE_MENU_ARRAY_LIST);
            if (getIntent().hasExtra(IntentConstants.HASH_MAP_CHILDREN)){
                hashMapChildren = (HashMap<String, ArrayList<UserWiseSubMenuWritesModel>>) getIntent().getSerializableExtra(IntentConstants.HASH_MAP_CHILDREN);
                userWiseMenuDetailsAdapter = new UserWiseMenuDetailsAdapter(this,groupArrayList,hashMapChildren);
                expLvUserWiseMenu.setAdapter(userWiseMenuDetailsAdapter);
            }else {
                Toast.makeText(this, "Writes Menu Intent Empty or null", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Sub Menu Intent Empty or null", Toast.LENGTH_SHORT).show();
        }

    }

    private void initView(){

        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(getApplicationContext());
        tv_emp_code = findViewById(R.id.tv_emp_code);
        tv_emp_code.setText(mySharedPreferecesRKUOLD.getEmpCode());

        txt_act = findViewById(R.id.txt_act);
        txt_act.setText(parentMenu);

        queue = Volley.newRequestQueue(this);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);

        expLvUserWiseMenu = findViewById(R.id.expLvUserWiseMenu);

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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back){
            onBackPressed();
        }
    }


//    private void prepareExpandableListView(){
//
//        groupArrayList = new ArrayList<>();
//        hashMapChildren = new HashMap<>();
//
//
//        for (int i = 0 ; i < 3;i++){
//            UserWiseSubMenuModel userWiseSubMenuModel = new UserWiseSubMenuModel();
//            userWiseSubMenuModel.setSubMenuId(i);
//            userWiseSubMenuModel.setSubMenuName("Book Or Chapter " + i);
//            groupArrayList.add(userWiseSubMenuModel);
//        }
//
//
//        ArrayList<UserWiseSubMenuWritesModel> userWiseSubMenuWritesModelArrayList = new ArrayList<>();
//        UserWiseSubMenuWritesModel userWiseSubMenuWritesModel = new UserWiseSubMenuWritesModel();
//        userWiseSubMenuWritesModel.setChecked(false);
//        userWiseSubMenuWritesModel.setWritesName("Insert");
//        userWiseSubMenuWritesModelArrayList.add(userWiseSubMenuWritesModel);
//
//        ArrayList<UserWiseSubMenuWritesModel> userWiseSubMenuWritesModelArrayList1 = new ArrayList<>();
//        UserWiseSubMenuWritesModel userWiseSubMenuWritesModel1 = new UserWiseSubMenuWritesModel();
//        userWiseSubMenuWritesModel1.setChecked(false);
//        userWiseSubMenuWritesModel1.setWritesName("Insert");
//        userWiseSubMenuWritesModelArrayList1.add(userWiseSubMenuWritesModel1);
//
//        UserWiseSubMenuWritesModel userWiseSubMenuWritesModel12 = new UserWiseSubMenuWritesModel();
//        userWiseSubMenuWritesModel12.setChecked(true);
//        userWiseSubMenuWritesModel12.setWritesName("Update");
//        userWiseSubMenuWritesModelArrayList1.add(userWiseSubMenuWritesModel12);
//
//        ArrayList<UserWiseSubMenuWritesModel> userWiseSubMenuWritesModelArrayList3 = new ArrayList<>();
//        UserWiseSubMenuWritesModel userWiseSubMenuWritesModel4 = new UserWiseSubMenuWritesModel();
//        userWiseSubMenuWritesModel4.setChecked(false);
//        userWiseSubMenuWritesModel4.setWritesName("Insert");
//        userWiseSubMenuWritesModelArrayList3.add(userWiseSubMenuWritesModel);
//
//        hashMapChildren.put(groupArrayList.get(0).getSubMenuName(),userWiseSubMenuWritesModelArrayList);
//        hashMapChildren.put(groupArrayList.get(1).getSubMenuName(),userWiseSubMenuWritesModelArrayList1);
//        hashMapChildren.put(groupArrayList.get(2).getSubMenuName(),userWiseSubMenuWritesModelArrayList3);
//
//
//    }




}
