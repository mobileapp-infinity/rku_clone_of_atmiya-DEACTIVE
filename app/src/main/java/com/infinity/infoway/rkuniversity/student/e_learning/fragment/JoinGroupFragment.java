package com.infinity.infoway.rkuniversity.student.e_learning.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.student.e_learning.activity.ELearningActivity;
import com.infinity.infoway.rkuniversity.student.e_learning.adapter.JoinGroupListAdapter;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.JoinGroupListPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JoinGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JoinGroupFragment extends Fragment {

    private static JoinGroupFragment joinGroupFragment = null;
    private MySharedPreferences mySharedPreferences;
    private ConnectionDetector connectionDetector;
    private ELearningActivity eLearningActivity;

    LinearLayout llJoinGroupList, llJoinGroupProgressbar, llNoDataFoundJoinGroup;
    ExpandableListView elvJoinGroup;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        eLearningActivity = (ELearningActivity) context;
    }

    public JoinGroupFragment() {
        // Required empty public constructor
    }

    public static JoinGroupFragment newInstance() {
        if (joinGroupFragment == null) {
            joinGroupFragment = new JoinGroupFragment();
        }
        return joinGroupFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_group, container, false);
        initView(view);
        getStudentJoinLearningManagementGroupList();
        return view;
    }


    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(eLearningActivity);
        connectionDetector = new ConnectionDetector(eLearningActivity);
        llJoinGroupList = view.findViewById(R.id.llJoinGroupList);
        llJoinGroupProgressbar = view.findViewById(R.id.llJoinGroupProgressbar);
        llNoDataFoundJoinGroup = view.findViewById(R.id.llNoDataFoundJoinGroup);
        elvJoinGroup = view.findViewById(R.id.elvJoinGroup);
    }


    private void getStudentJoinLearningManagementGroupList() {
        if (connectionDetector.isConnectingToInternet()) {
            llJoinGroupList.setVisibility(View.GONE);
            llJoinGroupProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundJoinGroup.setVisibility(View.GONE);
            ApiImplementer.getJoinLearningManagementGroupsApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSwdYearId(), new Callback<ArrayList<JoinGroupListPojo>>() {
                @Override
                public void onResponse(Call<ArrayList<JoinGroupListPojo>> call, Response<ArrayList<JoinGroupListPojo>> response) {
                    try {
                        llJoinGroupProgressbar.setVisibility(View.GONE);
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            llJoinGroupList.setVisibility(View.VISIBLE);
                            ArrayList<JoinGroupListPojo> joinGroupListPojoArrayList = response.body();
                            HashMap<String, ArrayList<JoinGroupListPojo.DetailsArray>> childHashMap = new HashMap<>();
                            for (int i = 0; i < joinGroupListPojoArrayList.size(); i++) {
                                String grpName = joinGroupListPojoArrayList.get(i).getGroupName();
                                if (!CommonUtil.checkIsEmptyOrNullCommon(grpName)) {
                                    childHashMap.put(grpName, (ArrayList<JoinGroupListPojo.DetailsArray>) joinGroupListPojoArrayList.get(i).getDetailsArray());
                                }
                            }
                            JoinGroupListAdapter joinGroupListAdapter = new JoinGroupListAdapter(eLearningActivity,
                                    joinGroupListPojoArrayList, childHashMap);
                            elvJoinGroup.setAdapter(joinGroupListAdapter);
                        } else {
                            llJoinGroupList.setVisibility(View.GONE);
                            llNoDataFoundJoinGroup.setVisibility(View.VISIBLE);
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<JoinGroupListPojo>> call, Throwable t) {
                    llJoinGroupList.setVisibility(View.GONE);
                    llJoinGroupProgressbar.setVisibility(View.GONE);
                    llNoDataFoundJoinGroup.setVisibility(View.VISIBLE);
                    Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
            eLearningActivity.finish();
        }
    }
}