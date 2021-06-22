package com.infinity.infoway.rkuniversity.student.e_learning.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.SpinnerSimpleAdapter;
import com.infinity.infoway.rkuniversity.student.e_learning.activity.ELearningActivity;
import com.infinity.infoway.rkuniversity.student.e_learning.adapter.GroupWiseSubjectDetailsAdapter;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.CheckIsELearningManagementGroupIsCompulsoryOrNot;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.ELearningYearListPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.GroupWiseSubjectlistPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.LearningManagementGroupDetailsPojo;
import com.infinity.infoway.rkuniversity.student.e_learning.pojo.StudentWiseLearningGroupPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class EnrollToGroupFragment extends Fragment implements View.OnClickListener {

    private static final String SELECT_YEAR = "Year";
    private static final String SELECT_GROUP = "Group Name";
    private static final String SELECT_SUBJECT = "Subject";
    private static EnrollToGroupFragment enrollToGroupFragment = null;

    ELearningActivity eLearningActivity;
    MySharedPreferences mySharedPreferences;
    ConnectionDetector connectionDetector;
    Spinner spYear, spGroupName, spSubjectName;
    SpinnerSimpleAdapter spinnerAdapterYear, spinnerAdapterGroupName, spinnerAdapterSubject;
    //    LinearLayout llExpandedHeaderEnrollToGroup;
//    AppCompatImageView ivViewMoreEnrollToGroup;
    ExpandableListView elvGroupWiseSubjectDetails;
    //    TextViewRegularFont tvClassName, tvClassDate;
    ArrayList<String> yearArrayList;
    HashMap<String, String> yearNameAndIdHashMap;
    //    HashMap<String, String> yearNameAndYearStatusHashMap;
    ArrayList<String> eLearningGroupList;
    HashMap<String, String> groupNameAndIdHashMap;
    LinearLayout llEnrollToGroup, llEnrollToGroupProgressbar, llNoDataFoundEnrollToGroup;
    Calendar myCalendarFromDate = Calendar.getInstance();
    Calendar myCalendarToDate = Calendar.getInstance();
    AlertDialog applyFilterDialog;
    //    ExtendedFloatingActionButton efabApplyFilterEnrollToGroup, efabClearFilterEnrollToGroup;
    //    FrameLayout flStudentExaminationSchedule;
    LinearLayout llSubjectName;
    ArrayList<String> subjectNameArrayList;
    HashMap<String, String> subjectNameAndIdHashMap;

    //    String FROM_DATE = "";
//    String TO_DATE = "";
    String SUBJECT_ID = "0";
    AppCompatEditText edtFromDateEnrollToGroup;
    AppCompatEditText edtToDateEnrollToGroup;
    AppCompatImageView imgClearFromToDateFilter;
    AppCompatImageView imgClearSubjectFilter;
    LinearLayout llFromDateToDateFilterOption;

    public EnrollToGroupFragment() {
        // Required empty public constructor
    }

    public static EnrollToGroupFragment newInstance() {
        if (enrollToGroupFragment == null) {
            enrollToGroupFragment = new EnrollToGroupFragment();
        }
        return enrollToGroupFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        eLearningActivity = (ELearningActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enroll_to_group, container, false);
        initView(view);
        getYearListApiCall();
        return view;
    }

    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(eLearningActivity);
        connectionDetector = new ConnectionDetector(eLearningActivity);
        spYear = view.findViewById(R.id.spYear);
        spGroupName = view.findViewById(R.id.spGroupName);
        spSubjectName = view.findViewById(R.id.spSubjectName);
//        llExpandedHeaderEnrollToGroup = view.findViewById(R.id.llExpandedHeaderEnrollToGroup);
//        llExpandedHeaderEnrollToGroup.setOnClickListener(this);
//        tvClassName = view.findViewById(R.id.tvClassName);
//        tvClassDate = view.findViewById(R.id.tvClassDate);
//        ivViewMoreEnrollToGroup = view.findViewById(R.id.ivViewMoreEnrollToGroup);
        elvGroupWiseSubjectDetails = view.findViewById(R.id.elvGroupWiseSubjectDetails);
        llEnrollToGroup = view.findViewById(R.id.llEnrollToGroup);
        llEnrollToGroupProgressbar = view.findViewById(R.id.llEnrollToGroupProgressbar);
        llNoDataFoundEnrollToGroup = view.findViewById(R.id.llNoDataFoundEnrollToGroup);
//        efabApplyFilterEnrollToGroup = view.findViewById(R.id.efabApplyFilterEnrollToGroup);
//        efabApplyFilterEnrollToGroup.setOnClickListener(this);
//        efabClearFilterEnrollToGroup = view.findViewById(R.id.efabClearFilterEnrollToGroup);
//        efabClearFilterEnrollToGroup.setOnClickListener(this);
//        flStudentExaminationSchedule = view.findViewById(R.id.flStudentExaminationSchedule);
        llSubjectName = view.findViewById(R.id.llSubjectName);

        edtFromDateEnrollToGroup = view.findViewById(R.id.edtFromDateEnrollToGroup);
        edtFromDateEnrollToGroup.setOnClickListener(this);
        edtToDateEnrollToGroup = view.findViewById(R.id.edtToDateEnrollToGroup);
        edtToDateEnrollToGroup.setOnClickListener(this);
        imgClearFromToDateFilter = view.findViewById(R.id.imgClearFromToDateFilter);
        imgClearFromToDateFilter.setOnClickListener(this);
        imgClearSubjectFilter = view.findViewById(R.id.imgClearSubjectFilter);
        imgClearSubjectFilter.setOnClickListener(this);
        llFromDateToDateFilterOption = view.findViewById(R.id.llFromDateToDateFilterOption);

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    if (spGroupName.getSelectedItemPosition() > 0) {
                        String selectedGroupId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition())) + "";
                        String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition())) + "";
                        if (subjectNameArrayList != null &&
                                subjectNameArrayList.size() > 1 && spSubjectName.getSelectedItemPosition() > 0) {
                            SUBJECT_ID = subjectNameAndIdHashMap.get(subjectNameArrayList.get(spSubjectName.getSelectedItemPosition()));
                            String fromDate = edtFromDateEnrollToGroup.getText().toString().trim() == null ? "" : edtFromDateEnrollToGroup.getText().toString().trim();
                            String toDate = edtToDateEnrollToGroup.getText().toString().trim() == null ? "" : edtToDateEnrollToGroup.getText().toString().trim();

                            getEnrollToGroupExpandedListApiCall(true, true, selectedGroupId, selectedYearId, fromDate, toDate, SUBJECT_ID);
                        } else {
                            SUBJECT_ID = "0";
                            checkIsELearningGrpIsCompulsoryOrNot(selectedYearId, selectedGroupId);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spGroupName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    if (spYear.getSelectedItemPosition() > 0) {
                        String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition())) + "";
                        String selectedGroupId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition())) + "";
                        checkIsELearningGrpIsCompulsoryOrNot(selectedYearId, selectedGroupId);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spSubjectName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    SUBJECT_ID = subjectNameAndIdHashMap.get(subjectNameArrayList.get(spSubjectName.getSelectedItemPosition())) + "";
                    if (spYear.getSelectedItemPosition() > 0 &&
                            spGroupName.getSelectedItemPosition() > 0) {
                        String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition())) + "";
                        String selectedGroupId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition())) + "";
                        String fromDate = edtFromDateEnrollToGroup.getText().toString().trim() == null ? "" : edtFromDateEnrollToGroup.getText().toString().trim();
                        String toDate = edtToDateEnrollToGroup.getText().toString().trim() == null ? "" : edtToDateEnrollToGroup.getText().toString().trim();
                        getEnrollToGroupExpandedListApiCall(true, true, selectedGroupId, selectedYearId, fromDate, toDate, SUBJECT_ID);
                    }
                } else {
                    SUBJECT_ID = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private boolean isValid() {
        boolean validate = true;
        if (spYear.getSelectedItemPosition() == 0) {
            Toast.makeText(eLearningActivity, "Please select year", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (spGroupName.getSelectedItemPosition() == 0) {
            Toast.makeText(eLearningActivity, "Please select group", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        return validate;
    }

    private boolean isValidWithoutToast() {
        boolean validate = true;
        if (spYear.getSelectedItemPosition() == 0) {
//            Toast.makeText(eLearningActivity, "Please select year", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (spGroupName.getSelectedItemPosition() == 0) {
//            Toast.makeText(eLearningActivity, "Please select group", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        return validate;
    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.efabApplyFilterEnrollToGroup) {
//            if (isValid()) {
//                showApplyFilterDialog();
//            }
//        } else if (view.getId() == R.id.efabClearFilterEnrollToGroup) {
//            if (isValid()) {
//                if (subjectNameArrayList != null && subjectNameArrayList.size() > 2) {
//                    SUBJECT_ID = "0";
//                    spSubjectName.setSelection(0);
//                }
//                FROM_DATE = "";
//                TO_DATE = "";
//                efabClearFilterEnrollToGroup.setVisibility(View.GONE);
//                efabApplyFilterEnrollToGroup.setVisibility(View.VISIBLE);
//                String grpId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition()));
//                String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition()));
//                getEnrollToGroupExpandedListApiCall(true, true, grpId, selectedYearId, FROM_DATE, TO_DATE, SUBJECT_ID);
//            }
//        }
//        else
        if (view.getId() == R.id.edtFromDateEnrollToGroup) {
            showFromDateDialog(edtFromDateEnrollToGroup);
        } else if (view.getId() == R.id.edtToDateEnrollToGroup) {
            showToDateDialog(edtToDateEnrollToGroup);
        } else if (view.getId() == R.id.imgClearFromToDateFilter) {
//            FROM_DATE = "";
//            TO_DATE = "";
            if (isValidWithoutToast()) {
                clearFromToDateFilter();
                String grpId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition()));
                String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition()));
                getEnrollToGroupExpandedListApiCall(true, true, grpId, selectedYearId, "", "", SUBJECT_ID);
            } else {
                clearFromToDateFilter();
            }
        } else if (view.getId() == R.id.imgClearSubjectFilter) {
            if (isValidWithoutToast()) {
                clearSubjectFilter();
                String grpId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition()));
                String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition()));
                String fromDate = edtFromDateEnrollToGroup.getText().toString().trim() == null ? "" : edtFromDateEnrollToGroup.getText().toString().trim();
                String toDate = edtToDateEnrollToGroup.getText().toString().trim() == null ? "" : edtToDateEnrollToGroup.getText().toString().trim();
                getEnrollToGroupExpandedListApiCall(true, true, grpId, selectedYearId, fromDate, toDate, SUBJECT_ID);
            } else {
                clearSubjectFilter();
            }
        }
    }

    private void clearSubjectFilter() {
        if (subjectNameArrayList != null && subjectNameArrayList.size() > 2) {
            SUBJECT_ID = "0";
            spSubjectName.setSelection(0);
        }
    }

    private void clearFromToDateFilter() {
        edtFromDateEnrollToGroup.setText("");
        edtFromDateEnrollToGroup.setHint("From Date(dd/MM/yyyy)");
        edtToDateEnrollToGroup.setText("");
        edtToDateEnrollToGroup.setHint("To Date(dd/MM/yyyy)");
    }


    private void getYearListApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llEnrollToGroup.setVisibility(View.GONE);
            llEnrollToGroupProgressbar.setVisibility(View.VISIBLE);
            llNoDataFoundEnrollToGroup.setVisibility(View.GONE);
            ApiImplementer.getELearningYearListApiImplementer(mySharedPreferences.getInstituteId(), new Callback<ELearningYearListPojo>() {
                @Override
                public void onResponse(Call<ELearningYearListPojo> call, Response<ELearningYearListPojo> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().getTable().size() > 0) {
                            ArrayList<ELearningYearListPojo.Table> tableArrayList = (ArrayList<ELearningYearListPojo.Table>) response.body().getTable();
                            yearArrayList = new ArrayList<>();
                            yearNameAndIdHashMap = new HashMap<>();
//                            yearNameAndYearStatusHashMap = new HashMap<>();
                            yearArrayList.add(SELECT_YEAR);

                            for (int i = 0; i < tableArrayList.size(); i++) {
                                if (!CommonUtil.checkIsEmptyOrNullCommon(tableArrayList.get(i).getYearName())) {
                                    String yearName = tableArrayList.get(i).getYearName();
                                    yearArrayList.add(yearName);
                                    yearNameAndIdHashMap.put(yearName, tableArrayList.get(i).getYearId() + "");
//                                    yearNameAndYearStatusHashMap.put(yearName, tableArrayList.get(i).getYearIsCurrent() + "");
                                }
                            }
                            spinnerAdapterYear = new SpinnerSimpleAdapter(eLearningActivity, yearArrayList);
                            spYear.setAdapter(spinnerAdapterYear);
                            getStudentWiseLearningManagementGroupApiCall();
                        } else {
                            llEnrollToGroup.setVisibility(View.GONE);
                            llEnrollToGroupProgressbar.setVisibility(View.GONE);
                            llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception ex) {
                        llEnrollToGroup.setVisibility(View.GONE);
                        llEnrollToGroupProgressbar.setVisibility(View.GONE);
                        llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                        Toast.makeText(eLearningActivity, "Exception :- " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ELearningYearListPojo> call, Throwable t) {
                    llEnrollToGroup.setVisibility(View.GONE);
                    llEnrollToGroupProgressbar.setVisibility(View.GONE);
                    llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                    Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection please try again later.", Toast.LENGTH_SHORT).show();
            eLearningActivity.finish();
        }
    }

    private void getStudentWiseLearningManagementGroupApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            ApiImplementer.getStudentWiseLearningGroupApiImplementer(mySharedPreferences.getStudentId(),
                    new Callback<StudentWiseLearningGroupPojo>() {
                        @Override
                        public void onResponse(Call<StudentWiseLearningGroupPojo> call, Response<StudentWiseLearningGroupPojo> response) {
                            try {
                                llEnrollToGroupProgressbar.setVisibility(View.GONE);
                                if (response.isSuccessful() && response.body() != null &&
                                        response.body().getTable().size() > 0) {
                                    ArrayList<StudentWiseLearningGroupPojo.Table> tableArrayList = (ArrayList<StudentWiseLearningGroupPojo.Table>) response.body().getTable();
                                    eLearningGroupList = new ArrayList<>();
                                    groupNameAndIdHashMap = new HashMap<>();
                                    eLearningGroupList.add(SELECT_GROUP);
                                    for (int i = 0; i < tableArrayList.size(); i++) {
                                        if (!CommonUtil.checkIsEmptyOrNullCommon(tableArrayList.get(i).getGrpName())) {
                                            String grpName = tableArrayList.get(i).getGrpName();
                                            eLearningGroupList.add(grpName);
                                            groupNameAndIdHashMap.put(grpName, tableArrayList.get(i).getGrpId() + "");
                                        }
                                    }
                                    spinnerAdapterGroupName = new SpinnerSimpleAdapter(eLearningActivity, eLearningGroupList);
                                    spGroupName.setAdapter(spinnerAdapterGroupName);
                                    llEnrollToGroup.setVisibility(View.VISIBLE);
                                } else {
                                    llEnrollToGroup.setVisibility(View.GONE);
                                    llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception ex) {
                                llEnrollToGroup.setVisibility(View.GONE);
                                llEnrollToGroupProgressbar.setVisibility(View.GONE);
                                llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<StudentWiseLearningGroupPojo> call, Throwable t) {
                            llEnrollToGroup.setVisibility(View.GONE);
                            llEnrollToGroupProgressbar.setVisibility(View.GONE);
                            llNoDataFoundEnrollToGroup.setVisibility(View.VISIBLE);
                            Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkIsELearningGrpIsCompulsoryOrNot(String selectedYearId, String grpId) {
        if (connectionDetector.isConnectingToInternet()) {
            DialogUtil.showProgressDialogNotCancelable(eLearningActivity, "");
            ApiImplementer.checkIsELearningGroupIsCompulsoryOrNotApiImplementer(grpId, new Callback<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>>() {
                @Override
                public void onResponse(Call<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> call, Response<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> response) {
                    try {
//                        DialogUtil.hideProgressDialog();
                        String fromDate = edtFromDateEnrollToGroup.getText().toString().trim() == null ? "" : edtFromDateEnrollToGroup.getText().toString().trim();
                        String toDate = edtToDateEnrollToGroup.getText().toString().trim() == null ? "" : edtToDateEnrollToGroup.getText().toString().trim();
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().size() > 0) {
                            if (response.body().get(0).getGrpType() == 1) {
                                llSubjectName.setVisibility(View.GONE);
                                SUBJECT_ID = "0";
                                getEnrollToGroupExpandedListApiCall(false, true, grpId, selectedYearId, fromDate, toDate, SUBJECT_ID);
                            } else if (response.body().get(0).getGrpType() == 2) {
                                getGroupWiseSubjectListApiCall(false, false, selectedYearId, grpId);
                            } else {
                                SUBJECT_ID = "0";
                                llSubjectName.setVisibility(View.GONE);
                                getEnrollToGroupExpandedListApiCall(false, true, grpId, selectedYearId, fromDate, toDate, SUBJECT_ID);
                            }
                        } else {
                            SUBJECT_ID = "0";
                            DialogUtil.hideProgressDialog();
                            getEnrollToGroupExpandedListApiCall(false, true, grpId, selectedYearId, fromDate, toDate, SUBJECT_ID);
                        }
                    } catch (Exception ex) {
                        DialogUtil.hideProgressDialog();
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<CheckIsELearningManagementGroupIsCompulsoryOrNot>> call, Throwable t) {
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
            eLearningActivity.finish();
        }
    }

    private void getGroupWiseSubjectListApiCall(boolean isPdShow, boolean isPdHide, String selectedYearId, String grp_id) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(eLearningActivity, "");
            }
            ApiImplementer.groupWiseLearningManagementSubjectListApiImplementer(mySharedPreferences.getStudentId(), mySharedPreferences.getSmId(),
                    selectedYearId, grp_id, new Callback<ArrayList<GroupWiseSubjectlistPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<GroupWiseSubjectlistPojo>> call, Response<ArrayList<GroupWiseSubjectlistPojo>> response) {
                            if (isPdHide) {
                                DialogUtil.hideProgressDialog();
                            }
                            SUBJECT_ID = "0";
                            String fromDate = edtFromDateEnrollToGroup.getText().toString().trim() == null ? "" : edtFromDateEnrollToGroup.getText().toString().trim();
                            String toDate = edtToDateEnrollToGroup.getText().toString().trim() == null ? "" : edtToDateEnrollToGroup.getText().toString().trim();
                            if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                                subjectNameArrayList = new ArrayList<>();
                                subjectNameArrayList.add(SELECT_SUBJECT);
                                subjectNameAndIdHashMap = new HashMap<>();

                                for (int i = 0; i < response.body().size(); i++) {
                                    if (!CommonUtil.checkIsEmptyOrNullCommon(response.body().get(i).getSubName())) {
                                        String subName = response.body().get(i).getSubName() + "";
                                        subjectNameArrayList.add(subName);
                                        subjectNameAndIdHashMap.put(subName, response.body().get(i).getSubId() + "");
                                    }
                                }
                                spinnerAdapterSubject = new SpinnerSimpleAdapter(eLearningActivity, subjectNameArrayList);
                                spSubjectName.setAdapter(spinnerAdapterSubject);
                                getEnrollToGroupExpandedListApiCall(false, true, grp_id, selectedYearId, fromDate, toDate, SUBJECT_ID);

                                llSubjectName.setVisibility(View.VISIBLE);
                            } else {
                                getEnrollToGroupExpandedListApiCall(false, true, grp_id, selectedYearId, fromDate, toDate, SUBJECT_ID);
                                llSubjectName.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<GroupWiseSubjectlistPojo>> call, Throwable t) {
                            SUBJECT_ID = "0";
                            DialogUtil.hideProgressDialog();
                            llSubjectName.setVisibility(View.GONE);
                            Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEnrollToGroupExpandedListApiCall(boolean isPdShow, boolean isPdHide, String grp_id, String selectedYearId, String from_date,
                                                     String to_date, String sub_id) {
        if (connectionDetector.isConnectingToInternet()) {
            if (isPdShow) {
                DialogUtil.showProgressDialogNotCancelable(eLearningActivity, "");
            }
            ApiImplementer.groupWiseLearningManagementDocumentListApiImplementer(grp_id, mySharedPreferences.getStudentId(),
                    mySharedPreferences.getSmId(), selectedYearId, from_date, to_date, sub_id, new Callback<ArrayList<LearningManagementGroupDetailsPojo>>() {
                        @Override
                        public void onResponse(Call<ArrayList<LearningManagementGroupDetailsPojo>> call, Response<ArrayList<LearningManagementGroupDetailsPojo>> response) {
                            try {
                                if (isPdHide) {
                                    DialogUtil.hideProgressDialog();
                                }
                                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
//                                    flStudentExaminationSchedule.setVisibility(View.VISIBLE);
                                    elvGroupWiseSubjectDetails.setVisibility(View.VISIBLE);
                                    llFromDateToDateFilterOption.setVisibility(View.VISIBLE);
                                    LearningManagementGroupDetailsPojo learningManagementGroupDetailsPojo;

                                    ArrayList<String> headerNameArrayList = new ArrayList<>();
                                    ArrayList<LearningManagementGroupDetailsPojo.GroupDetailArray> childArray = new ArrayList<>();
                                    HashMap<String, ArrayList<LearningManagementGroupDetailsPojo.GroupDetailArray>> childListDetailsHashMap = new HashMap<>();

                                    for (int i = 0; i < response.body().size(); i++) {
                                        learningManagementGroupDetailsPojo = response.body().get(i);
                                        if (!CommonUtil.checkIsEmptyOrNullCommon(learningManagementGroupDetailsPojo.getGroupName())) {
                                            headerNameArrayList.add(learningManagementGroupDetailsPojo.getGroupName());

                                            for (int k = 0; k < learningManagementGroupDetailsPojo.getGroupDetailArray().size(); k++) {
                                                childArray.add(learningManagementGroupDetailsPojo.getGroupDetailArray().get(k));
                                            }

                                            childListDetailsHashMap.put(learningManagementGroupDetailsPojo.getGroupName(),
                                                    childArray);
                                        }
                                    }
                                    elvGroupWiseSubjectDetails.setAdapter(new GroupWiseSubjectDetailsAdapter(eLearningActivity, headerNameArrayList, childListDetailsHashMap));
//                                    if (efabApplyFilterEnrollToGroup.getVisibility() != View.VISIBLE &&
//                                            efabClearFilterEnrollToGroup.getVisibility() != View.VISIBLE) {
//                                        efabApplyFilterEnrollToGroup.setVisibility(View.VISIBLE);
////                                    rvEnrollToGroupList.setAdapter(new GroupWiseSubjectDetailsAdapter(eLearningActivity, response.body()))
//                                    }
                                    //;
                                } else {
//                                    flStudentExaminationSchedule.setVisibility(View.GONE);
                                    elvGroupWiseSubjectDetails.setVisibility(View.GONE);
                                    llFromDateToDateFilterOption.setVisibility(View.GONE);
//                                    efabApplyFilterEnrollToGroup.setVisibility(View.VISIBLE);
//                                    efabClearFilterEnrollToGroup.setVisibility(View.GONE);
                                    Toast.makeText(eLearningActivity, "No Data Found!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<LearningManagementGroupDetailsPojo>> call, Throwable t) {
                            DialogUtil.hideProgressDialog();
//                            flStudentExaminationSchedule.setVisibility(View.GONE);
                            elvGroupWiseSubjectDetails.setVisibility(View.GONE);
                            llFromDateToDateFilterOption.setVisibility(View.GONE);
                            Toast.makeText(eLearningActivity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(eLearningActivity, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

//    private void showApplyFilterDialog() {
//        TextInputEditText tilFromDate, tilToDate;
////        Spinner spSubjectFilter;
//        TextViewRegularFont btnCancel;
//        AppCompatButton btnApplyFilter;
////        LinearLayout llSpSubjectList;
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(eLearningActivity);
//        dialogBuilder.setCancelable(false);
//
//        LayoutInflater layoutInflater = LayoutInflater.from(eLearningActivity);
//        View dialogView = layoutInflater.inflate(R.layout.layout_for_enroll_togroup_filter_opetion, null);
//
//        tilFromDate = dialogView.findViewById(R.id.tilFromDate);
//        tilToDate = dialogView.findViewById(R.id.tilToDate);
////        spSubjectFilter = dialogView.findViewById(R.id.spSubjectFilter);
//        btnCancel = dialogView.findViewById(R.id.btnCancel);
//        btnApplyFilter = dialogView.findViewById(R.id.btnApplyFilter);
////        llSpSubjectList = dialogView.findViewById(R.id.llSpSubjectList);
//
//        tilFromDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showFromDateDialog(tilFromDate);
//            }
//        });
//
//        tilToDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showToDateDialog(tilToDate);
//            }
//        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                applyFilterDialog.dismiss();
//            }
//        });
//
//
//        btnApplyFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (validateFromDateAndToDate(tilFromDate, tilToDate)) {
//                    if (!CommonUtil.checkIsEmptyOrNullCommon(tilFromDate.getText().toString())) {
//                        applyFilterDialog.dismiss();
//                        efabApplyFilterEnrollToGroup.setVisibility(View.GONE);
//                        efabClearFilterEnrollToGroup.setVisibility(View.VISIBLE);
//                        String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition())) + "";
//                        String groupId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition())) + "";
//
//                        if (subjectNameArrayList != null &&
//                                subjectNameArrayList.size() > 1 && spSubjectName.getSelectedItemPosition() > 0) {
//                            SUBJECT_ID = subjectNameAndIdHashMap.get(subjectNameArrayList.get(spSubjectName.getSelectedItemPosition()));
//                        }
//
//                        getEnrollToGroupExpandedListApiCall(true, true, groupId, selectedYearId, FROM_DATE, TO_DATE, SUBJECT_ID);
//                    }
//                }
//            }
//        });
//
//        dialogBuilder.setView(dialogView);
//        applyFilterDialog = dialogBuilder.create();
//        if (!applyFilterDialog.isShowing()) {
//            applyFilterDialog.show();
//        }
//    }

    private boolean validateFromDateAndToDate(String edtFromDate,
                                              String edtToDate) {
        boolean isValid = true;
//        boolean isDateEntered = !CommonUtil.checkIsEmptyOrNullCommon(edtFromDate.getText().toString()) ||
//                !CommonUtil.checkIsEmptyOrNullCommon(edtToDate.getText().toString());

        if (CommonUtil.checkIsEmptyOrNullCommon(edtFromDate)) {
            Toast.makeText(eLearningActivity, "Please select from date.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (CommonUtil.checkIsEmptyOrNullCommon(edtToDate)) {
            Toast.makeText(eLearningActivity, "Please select to date.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (!CommonUtil.checkIsFromDateGraterThanToDate(edtFromDate,
                edtToDate, "dd/MM/yyyy")) {
            Toast.makeText(eLearningActivity, "From date can not be greater than to date.", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else {
            isValid = true;
        }

        return isValid;
    }

    private void showFromDateDialog(AppCompatEditText edtFromDate) {
        DatePickerDialog datePickerDialogFromDate = new DatePickerDialog(eLearningActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        myCalendarFromDate.set(Calendar.YEAR, year);
                        myCalendarFromDate.set(Calendar.MONTH, monthOfYear);
                        myCalendarFromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd/MM/yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
                        Date fromDate = null;
                        try {
                            fromDate = simpleDateFormat.parse(simpleDateFormat.format(myCalendarFromDate.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        String selectedFromDate = simpleDateFormat.format(fromDate);
                        String selectedToDate = edtToDateEnrollToGroup.getText().toString().trim();
                        edtFromDate.setText(selectedFromDate);
                        getOnlyFilteredDateList(selectedFromDate, selectedToDate);
                    }
                }, myCalendarFromDate.get(Calendar.YEAR), myCalendarFromDate.get(Calendar.MONTH), myCalendarFromDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialogFromDate.show();
    }

    private void showToDateDialog(AppCompatEditText edtToDate) {
        DatePickerDialog datePickerDialogToDate = new DatePickerDialog(eLearningActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        myCalendarToDate.set(Calendar.YEAR, year);
                        myCalendarToDate.set(Calendar.MONTH, monthOfYear);
                        myCalendarToDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd/MM/yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);
                        Date toDate = null;
                        try {
                            toDate = simpleDateFormat.parse(simpleDateFormat.format(myCalendarToDate.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        String selectedFromDate = edtFromDateEnrollToGroup.getText().toString();
                        String selectedToDate = simpleDateFormat.format(toDate);
                        edtToDate.setText(selectedToDate);
                        getOnlyFilteredDateList(selectedFromDate, selectedToDate);
                    }
                }, myCalendarToDate.get(Calendar.YEAR), myCalendarToDate.get(Calendar.MONTH), myCalendarToDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialogToDate.show();
    }


    private void getOnlyFilteredDateList(String selectedFromDate, String selectedToDate) {
        if (validateFromDateAndToDate(selectedFromDate, selectedToDate)) {
            if (subjectNameArrayList != null &&
                    subjectNameArrayList.size() > 1 && spSubjectName.getSelectedItemPosition() > 0) {
                SUBJECT_ID = subjectNameAndIdHashMap.get(subjectNameArrayList.get(spSubjectName.getSelectedItemPosition()));
            } else {
                SUBJECT_ID = "0";
            }
            if (isValidWithoutToast()) {
                String selectedYearId = yearNameAndIdHashMap.get(yearArrayList.get(spYear.getSelectedItemPosition())) + "";
                String groupId = groupNameAndIdHashMap.get(eLearningGroupList.get(spGroupName.getSelectedItemPosition())) + "";
                getEnrollToGroupExpandedListApiCall(true, true, groupId, selectedYearId, selectedFromDate, selectedToDate, SUBJECT_ID);
            }
        }
    }


}