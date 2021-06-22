package com.infinity.infoway.rkuniversity.student.e_learning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.student.e_learning.activity.ELearningActivity;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.CheckIsLearningManagementGroupIsExistOrNotPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.EnrollToGroupPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.InsertStudentLearningManagementPushNotificationPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.JoinGroupListPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinGroupListAdapter extends BaseExpandableListAdapter {

    private ELearningActivity context;
    private ArrayList<JoinGroupListPojo> joinGroupHeaderListPojoArrayList;
    private HashMap<String, ArrayList<JoinGroupListPojo.DetailsArray>> joinGroupChildHashMap;
    private LayoutInflater layoutInflater;
    private MySharedPreferences mySharedPreferences;
    private ConnectionDetector connectionDetector;

    public JoinGroupListAdapter(ELearningActivity context, ArrayList<JoinGroupListPojo> joinGroupHeaderListPojoArrayList, HashMap<String, ArrayList<JoinGroupListPojo.DetailsArray>> joinGroupChildHashMap) {
        this.context = context;
        this.joinGroupHeaderListPojoArrayList = joinGroupHeaderListPojoArrayList;
        this.joinGroupChildHashMap = joinGroupChildHashMap;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mySharedPreferences = new MySharedPreferences(context);
        connectionDetector = new ConnectionDetector(context);
    }

    @Override
    public int getGroupCount() {
        return joinGroupHeaderListPojoArrayList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return joinGroupChildHashMap.get(joinGroupHeaderListPojoArrayList.get(i).getGroupName()).size();
    }

    @Override
    public Object getGroup(int i) {
        return joinGroupHeaderListPojoArrayList.get(i);
    }

    @Override
    public Object getChild(int headerPosition, int childPosition) {
        return joinGroupChildHashMap.get(joinGroupHeaderListPojoArrayList.get(headerPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int headerPosition, int expandedChildId) {
        return expandedChildId;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.inflater_join_group_header_list_item, null);
        }

        JoinGroupListPojo joinGroupListPojo = joinGroupHeaderListPojoArrayList.get(groupPosition);

        TextViewRegularFont tvJoinGroupNameHeader = view.findViewById(R.id.tvJoinGroupNameHeader);
        TextViewRegularFont tvJoinGroupDateHeader = view.findViewById(R.id.tvJoinGroupDateHeader);

        if (!CommonUtil.checkIsEmptyOrNullCommon(joinGroupListPojo.getGroupName())) {
            tvJoinGroupNameHeader.setText(joinGroupListPojo.getGroupName() + "");
        }

        if (!CommonUtil.checkIsEmptyOrNullCommon(joinGroupListPojo.getGroupDate())) {
            tvJoinGroupDateHeader.setText(joinGroupListPojo.getGroupDate() + "");
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.inflater_join_group_chilt_list_item, null);
        }
        JoinGroupListPojo.DetailsArray detailsArray = joinGroupChildHashMap.get(joinGroupHeaderListPojoArrayList.get(groupPosition).getGroupName()).get(childPosition);

        TextViewRegularFont tvJoinGroupDescriptionChild = view.findViewById(R.id.tvJoinGroupDescriptionChild);
        TextViewRegularFont btnJoinGroupClickToEnrollChild = view.findViewById(R.id.btnJoinGroupClickToEnrollChild);
        TextViewRegularFont btnJoinGroupClickToDeclineChild = view.findViewById(R.id.btnJoinGroupClickToDeclineChild);

        if (!CommonUtil.checkIsEmptyOrNullCommon(detailsArray.getGrpDesc())) {
            tvJoinGroupDescriptionChild.setText(detailsArray.getGrpDesc() + "");
        }

        btnJoinGroupClickToEnrollChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String grp_id = joinGroupHeaderListPojoArrayList.get(groupPosition).getGroupId();
                String grp_name = "";
                if (!CommonUtil.checkIsEmptyOrNullCommon(joinGroupHeaderListPojoArrayList.get(groupPosition).getGroupName())) {
                    grp_name = joinGroupHeaderListPojoArrayList.get(groupPosition).getGroupName();
                }
                if (!CommonUtil.checkIsEmptyOrNullCommon(grp_id)) {
                    checkIsGroupIsExistOrNotApiCall(grp_id, grp_name);
                }
            }
        });

        btnJoinGroupClickToDeclineChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String grp_id = joinGroupHeaderListPojoArrayList.get(groupPosition).getGroupId();
                declineGroup(grp_id);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    private void checkIsGroupIsExistOrNotApiCall(String grp_id, String grp_name) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(context, "");
            ApiImplementer.checkIsLearningManagementGroupIsExistOrNotApiImplementer(grp_id,
                    mySharedPreferences.getStudentId(), new Callback<CheckIsLearningManagementGroupIsExistOrNotPojo>() {
                        @Override
                        public void onResponse(Call<CheckIsLearningManagementGroupIsExistOrNotPojo> call, Response<CheckIsLearningManagementGroupIsExistOrNotPojo> response) {
//                            DialogUtil.hideProgressDialog();
                            if (response.isSuccessful() && response.body() != null &&
                                    response.body().getTable().size() > 0) {
                                if (response.body().getTable().get(0).getErrorCode() == 0) {
                                    enrollToGroupApiCall(false, false,
                                            grp_id, grp_name);
                                } else {
                                    DialogUtil.hideProgressDialog();
                                    Toast.makeText(context, "Group Already Exist.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                DialogUtil.hideProgressDialog();
                                Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CheckIsLearningManagementGroupIsExistOrNotPojo> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "No internet connection,Please try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void enrollToGroupApiCall(boolean isPdShow, boolean isPdHide,
                                      String grp_id, String grp_name) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(context, "");
        }
        ApiImplementer.enrollToGroupApiImplementer(grp_id, mySharedPreferences.getStudentId(),
                mySharedPreferences.getStudentId(), "1", "1", new Callback<EnrollToGroupPojo>() {
                    @Override
                    public void onResponse(Call<EnrollToGroupPojo> call, Response<EnrollToGroupPojo> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().getTable().size() > 0) {
                            if (response.body().getTable().get(0).getErrorCode().equalsIgnoreCase("1")) {
                                insertStudentLearningManagementPushNotification(false, true,
                                        grp_id, grp_name);
                            } else {
                                DialogUtil.hideProgressDialog();
                            }
                        } else {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EnrollToGroupPojo> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void insertStudentLearningManagementPushNotification(boolean isPdShow, boolean isPdHide, String grp_id, String grp_name) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(context, "");
        }
        ApiImplementer.insertStudentLearningManagementPushNotificationApiImplementer(grp_id,
                mySharedPreferences.getSwdYearId(), mySharedPreferences.getStudentId(),
                mySharedPreferences.getSmId(), "Learning Management For Student",
                "Learning Management :" + grp_name + "File Posted.",
                "0", mySharedPreferences.getStudentId(), "1",
                mySharedPreferences.getInstituteId(), new Callback<ArrayList<InsertStudentLearningManagementPushNotificationPojo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<InsertStudentLearningManagementPushNotificationPojo>> call, Response<ArrayList<InsertStudentLearningManagementPushNotificationPojo>> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().size() > 0) {
                            if (response.body().get(0).getErrorCode().equalsIgnoreCase("1")) {
                                Toast.makeText(context, "Enroll Successfully", Toast.LENGTH_SHORT).show();
                                context.finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<InsertStudentLearningManagementPushNotificationPojo>> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void declineGroup(String grp_id) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(context, "");
            ApiImplementer.enrollToGroupApiImplementer(grp_id, mySharedPreferences.getStudentId(),
                    mySharedPreferences.getStudentId(), "1", "0", new Callback<EnrollToGroupPojo>() {
                        @Override
                        public void onResponse(Call<EnrollToGroupPojo> call, Response<EnrollToGroupPojo> response) {
                            DialogUtil.hideProgressDialog();
                            if (response.isSuccessful() && response.body() != null &&
                                    response.body().getTable().size() > 0) {
                                if (response.body().getTable().get(0).getErrorCode().equalsIgnoreCase("1")) {
                                    Toast.makeText(context, "Group Decline Successfully.", Toast.LENGTH_SHORT).show();
                                    context.finish();
                                }
                            } else {
                                Toast.makeText(context, "Something went wrong,Please try again later.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<EnrollToGroupPojo> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
                            Toast.makeText(context, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

}
