package com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.custom_class.SpinnerSimpleAdapter;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.adapter.HODPendingLeaveApprovalListAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODGetCollegePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODGetCoursePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODGetDepartmentPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODGetSemesterPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.pojo.HODStudentLeaveApproveApplicationAllPojo;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HODPendingLeaveFragment extends Fragment {

    private static final String LEAVE_APPROVAL_PENDING = "1";
    private static final String LEAVE_APPROVAL_VIEW_ALL = "0";

    private MySharedPreferences mySharedPreferences;
    private static final String SELECT_COLLEGE = "Select College";
    private static final String SELECT_DEPARTMENT = "Select Department";
    private static final String SELECT_COURSE = "Select Course";
    private static final String SELECT_SEMESTER = "Select Semester";

    private Activity activity;
    private static HODPendingLeaveFragment hodPendingLeaveFragment = null;

    private Spinner spCollege, spDepartment, spCourse, spSemester;
    private SpinnerSimpleAdapter spinnerAdapterCollege, spinnerAdapterDepartment, spinnerAdapterCourse, spinnerAdapterSemester;
    private RecyclerView rvHodLeave;
    private LinearLayout llNoDataFound;

    private ArrayList<String> collegesArrayList;
    private HashMap<String, String> hashMapColleges;
    private ArrayList<String> departmentArrayList;
    private HashMap<String, String> hashMapDepartment;
    private ArrayList<String> courseArrayList;
    private HashMap<String, String> hashMapCourse;
    private ArrayList<String> semesterArrayList;
    private HashMap<String, String> hashMapSemester;
    private Dialog m_Dialog = null;
    private FloatingActionButton fabFilter;


    public HODPendingLeaveFragment() {
        // Required empty public constructor
    }

    public static HODPendingLeaveFragment newInstance() {
        if (hodPendingLeaveFragment == null) {
            hodPendingLeaveFragment = new HODPendingLeaveFragment();
        }
        return hodPendingLeaveFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        activity = (Activity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_h_o_d_pending_leave, container, false);
        initView(view);
        getHODLeaveApiCall(false,true, true, true, "0", "0", "0", "0");
        return view;
    }

    private void initView(View view) {
        mySharedPreferences = new MySharedPreferences(activity);
        fabFilter = view.findViewById(R.id.fabFilter);
        llNoDataFound = view.findViewById(R.id.llNoDataFound);
        rvHodLeave = view.findViewById(R.id.rvHodLeave);
        initFilterDialog();
        fabFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterDialog();
            }
        });
        spCollege.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String collegeId = hashMapColleges.get(collegesArrayList.get(spCollege.getSelectedItemPosition()).trim());
                    getDepartmentApiCall(true, false, collegeId, true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spCollege.getSelectedItemPosition() > 0 && position > 0) {
                    String collegeId = hashMapColleges.get(collegesArrayList.get(spCollege.getSelectedItemPosition()).trim());
                    String departmentId = hashMapDepartment.get(departmentArrayList.get(spDepartment.getSelectedItemPosition()).trim());
                    getCourseApiCall(true, false, collegeId, departmentId, true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spCollege.getSelectedItemPosition() > 0 && spDepartment.getSelectedItemPosition() > 0 && position > 0) {
                    String collegeId = hashMapColleges.get(collegesArrayList.get(spCollege.getSelectedItemPosition()).trim());
                    String departmentId = hashMapDepartment.get(departmentArrayList.get(spDepartment.getSelectedItemPosition()).trim());
                    String courseId = hashMapCourse.get(courseArrayList.get(spCourse.getSelectedItemPosition()).trim());
                    getSemesterApiCall(true, true, collegeId, departmentId, courseId, true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
//                    callHODLeaveApprovalApi();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void getCollegeApiCall(boolean isPdShow, boolean isPdHide, boolean isSemProDialogHide) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(activity, "");
        }
        ApiImplementer.getHODCollegeApiImplementer(mySharedPreferences.getEmpInstituteId(), mySharedPreferences.getEmpId(), mySharedPreferences.getEmpUserStatus(), new Callback<ArrayList<HODGetCollegePojo>>() {
            @Override
            public void onResponse(Call<ArrayList<HODGetCollegePojo>> call, Response<ArrayList<HODGetCollegePojo>> response) {
                if (isPdHide) {
                    DialogUtil.hideProgressDialog();
                }
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    collegesArrayList = new ArrayList<>();
                    collegesArrayList.add(SELECT_COLLEGE);
                    hashMapColleges = new HashMap<>();

                    ArrayList<HODGetCollegePojo> hodGetCollegePojos = response.body();

                    for (int i = 0; i < hodGetCollegePojos.size(); i++) {
                        if (!CommonUtil.checkIsEmptyOrNullCommon(hodGetCollegePojos.get(i).getCollegeName())) {
                            collegesArrayList.add(hodGetCollegePojos.get(i).getCollegeName() + "");
                            hashMapColleges.put(hodGetCollegePojos.get(i).getCollegeName(), hodGetCollegePojos.get(i).getAcId() + "");
                        }
                    }
                    spinnerAdapterCollege = new SpinnerSimpleAdapter(activity, collegesArrayList);
                    spCollege.setAdapter(spinnerAdapterCollege);

                    String collegeId = hashMapColleges.get(collegesArrayList.get(1).trim());
                    getDepartmentApiCall(false, false, collegeId, isSemProDialogHide);

                } else {
                    Toast.makeText(activity, "College not found!", Toast.LENGTH_SHORT).show();
//                    if (isPdShow) {
                    DialogUtil.hideProgressDialog();
//                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HODGetCollegePojo>> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(activity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDepartmentApiCall(boolean isPdShow, boolean isPdHide, String collegeId, boolean isSemProDialogHide) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(activity, "");
        }
        ApiImplementer.getHODDepartmentApiImplementer(mySharedPreferences.getEmpInstituteId(), collegeId, mySharedPreferences.getEmpId(), mySharedPreferences.getEmpUserStatus(), new Callback<ArrayList<HODGetDepartmentPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<HODGetDepartmentPojo>> call, Response<ArrayList<HODGetDepartmentPojo>> response) {
                if (isPdHide) {
                    DialogUtil.hideProgressDialog();
                }
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    departmentArrayList = new ArrayList<>();
                    departmentArrayList.add(SELECT_DEPARTMENT);
                    hashMapDepartment = new HashMap<>();

                    ArrayList<HODGetDepartmentPojo> hodGetDepartmentPojos = response.body();

                    for (int i = 0; i < hodGetDepartmentPojos.size(); i++) {
                        if (!CommonUtil.checkIsEmptyOrNullCommon(hodGetDepartmentPojos.get(i).getDmFullName())) {
                            departmentArrayList.add(hodGetDepartmentPojos.get(i).getDmFullName() + "");
                            hashMapDepartment.put(hodGetDepartmentPojos.get(i).getDmFullName(), hodGetDepartmentPojos.get(i).getDmId() + "");
                        }
                    }
                    spinnerAdapterDepartment = new SpinnerSimpleAdapter(activity, departmentArrayList);
                    spDepartment.setAdapter(spinnerAdapterDepartment);

                    String departmentId = hashMapDepartment.get(departmentArrayList.get(1).trim());
                    getCourseApiCall(false, false, collegeId, departmentId, isSemProDialogHide);

                } else {
                    departmentArrayList = new ArrayList<>();
                    departmentArrayList.add(SELECT_DEPARTMENT);
                    spinnerAdapterDepartment = new SpinnerSimpleAdapter(activity, departmentArrayList);
                    spDepartment.setAdapter(spinnerAdapterDepartment);
                    Toast.makeText(activity, "Department not found!", Toast.LENGTH_SHORT).show();
                    DialogUtil.hideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HODGetDepartmentPojo>> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(activity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCourseApiCall(boolean isPdShow, boolean isPdHide, String collegeId, String deptId, boolean isSemProDialogHide) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(activity, "");
        }
        ApiImplementer.getHODCoursesApiImplementer(mySharedPreferences.getEmpInstituteId(), collegeId, deptId,
                mySharedPreferences.getEmpId(), mySharedPreferences.getEmpUserStatus(), new Callback<ArrayList<HODGetCoursePojo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<HODGetCoursePojo>> call, Response<ArrayList<HODGetCoursePojo>> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            courseArrayList = new ArrayList<>();
                            courseArrayList.add(SELECT_COURSE);
                            hashMapCourse = new HashMap<>();

                            ArrayList<HODGetCoursePojo> hodGetCoursePojos = response.body();

                            for (int i = 0; i < hodGetCoursePojos.size(); i++) {
                                if (!CommonUtil.checkIsEmptyOrNullCommon(hodGetCoursePojos.get(i).getCourseName())) {
                                    courseArrayList.add(hodGetCoursePojos.get(i).getCourseName() + "");
                                    hashMapCourse.put(hodGetCoursePojos.get(i).getCourseName(), hodGetCoursePojos.get(i).getCourseId() + "");
                                }
                            }
                            spinnerAdapterCourse = new SpinnerSimpleAdapter(activity, courseArrayList);
                            spCourse.setAdapter(spinnerAdapterCourse);

                            String courseId = hashMapCourse.get(courseArrayList.get(1).trim());
                            getSemesterApiCall(false, true, collegeId, deptId, courseId, isSemProDialogHide);
                        } else {
                            courseArrayList = new ArrayList<>();
                            courseArrayList.add(SELECT_COURSE);
                            spinnerAdapterCourse = new SpinnerSimpleAdapter(activity, courseArrayList);
                            spCourse.setAdapter(spinnerAdapterCourse);
                            Toast.makeText(activity, "Course not found!", Toast.LENGTH_SHORT).show();
                            DialogUtil.hideProgressDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<HODGetCoursePojo>> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        Toast.makeText(activity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getSemesterApiCall(boolean isPdShow, boolean isPdHide, String collegeId, String deptId, String courseId, boolean isSemProDialogHide) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(activity, "");
        }
        ApiImplementer.getHODSemesterApiImplementer(mySharedPreferences.getEmpInstituteId(), collegeId, deptId, courseId, new Callback<ArrayList<HODGetSemesterPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<HODGetSemesterPojo>> call, Response<ArrayList<HODGetSemesterPojo>> response) {
                if (isPdHide && isSemProDialogHide) {
                    DialogUtil.hideProgressDialog();
                }
                if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                    semesterArrayList = new ArrayList<>();
                    semesterArrayList.add(SELECT_SEMESTER);
                    hashMapSemester = new HashMap<>();

                    ArrayList<HODGetSemesterPojo> hodGetSemesterPojos = response.body();

                    for (int i = 0; i < hodGetSemesterPojos.size(); i++) {
                        if (!CommonUtil.checkIsEmptyOrNullCommon(hodGetSemesterPojos.get(i).getSmName())) {
                            semesterArrayList.add(hodGetSemesterPojos.get(i).getSmName() + "");
                            hashMapSemester.put(hodGetSemesterPojos.get(i).getSmName(), hodGetSemesterPojos.get(i).getSmId() + "");
                        }
                    }
                    spinnerAdapterSemester = new SpinnerSimpleAdapter(activity, semesterArrayList);
                    spSemester.setAdapter(spinnerAdapterSemester);
                } else {
                    semesterArrayList = new ArrayList<>();
                    semesterArrayList.add(SELECT_SEMESTER);
                    spinnerAdapterSemester = new SpinnerSimpleAdapter(activity, semesterArrayList);
                    spSemester.setAdapter(spinnerAdapterSemester);
                    Toast.makeText(activity, "Semester not found!", Toast.LENGTH_SHORT).show();
                    DialogUtil.hideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HODGetSemesterPojo>> call, Throwable t) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(activity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getHODLeaveApiCall(boolean isFromApplyFilter, boolean isDropDownApiCalled, boolean isPdShow, boolean isPdHide, String collegeId, String deptId, String courseId, String semId) {
        if (isPdShow) {
            DialogUtil.showProgressDialogNotCancelable(activity, "");
        }
        rvHodLeave.setVisibility(View.GONE);
        llNoDataFound.setVisibility(View.VISIBLE);
        fabFilter.setVisibility(View.GONE);
        ApiImplementer.getStudentLeaveApprovedApplicationAllApiImplementer(mySharedPreferences.getEmpId(), collegeId, deptId, courseId, semId,
                mySharedPreferences.getEmpYearId(), mySharedPreferences.getEmpInstituteId(), "", LEAVE_APPROVAL_PENDING, new Callback<ArrayList<HODStudentLeaveApproveApplicationAllPojo>>() {
                    @Override
                    public void onResponse(Call<ArrayList<HODStudentLeaveApproveApplicationAllPojo>> call, Response<ArrayList<HODStudentLeaveApproveApplicationAllPojo>> response) {
                        if (isPdHide) {
                            DialogUtil.hideProgressDialog();
                        }
                        fabFilter.setVisibility(View.VISIBLE);
                        rvHodLeave.setVisibility(View.VISIBLE);
                        llNoDataFound.setVisibility(View.GONE);
                        if (isDropDownApiCalled) {
                            getCollegeApiCall(false, false, false);
                        }
                        if (response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                            HODPendingLeaveApprovalListAdapter hodPendingLeaveApprovalListAdapter = new HODPendingLeaveApprovalListAdapter(activity, response.body(),
                                    new HODPendingLeaveApprovalListAdapter.IApproveOrRejectLeave() {
                                        @Override
                                        public void onLeaveApprove() {
                                            callHODLeaveApprovalApi(false);
                                        }
                                    });
                            rvHodLeave.setAdapter(hodPendingLeaveApprovalListAdapter);
                        } else {
                            if (!isFromApplyFilter){
                                fabFilter.setVisibility(View.GONE);
                            }
                            rvHodLeave.setVisibility(View.GONE);
                            llNoDataFound.setVisibility(View.VISIBLE);
                            Toast.makeText(activity, "No Data Found!", Toast.LENGTH_SHORT).show();
//                    if (isPdShow) {
                            DialogUtil.hideProgressDialog();
//                    }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<HODStudentLeaveApproveApplicationAllPojo>> call, Throwable t) {
                        DialogUtil.hideProgressDialog();
                        rvHodLeave.setVisibility(View.GONE);
                        llNoDataFound.setVisibility(View.VISIBLE);
                        if (!isFromApplyFilter){
                            fabFilter.setVisibility(View.GONE);
                        }
                        Toast.makeText(activity, "Request Failed:- " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void callHODLeaveApprovalApi(boolean isFromApplyFilter) {
        if (hashMapColleges != null && hashMapColleges.size() > 0 &&
                hashMapDepartment != null && hashMapDepartment.size() > 0 &&
                hashMapCourse != null && hashMapCourse.size() > 0 &&
                hashMapSemester != null && hashMapSemester.size() > 0 &&
                spCollege.getSelectedItemPosition() > 0 && spDepartment.getSelectedItemPosition() > 0 &&
                spCourse.getSelectedItemPosition() > 0 && spSemester.getSelectedItemPosition() > 0) {
            String collegeId = hashMapColleges.get(collegesArrayList.get(spCollege.getSelectedItemPosition()).trim());
            String departmentId = hashMapDepartment.get(departmentArrayList.get(spDepartment.getSelectedItemPosition()).trim());
            String courseId = hashMapCourse.get(courseArrayList.get(spCourse.getSelectedItemPosition()).trim());
            String semesterId = hashMapSemester.get(semesterArrayList.get(spSemester.getSelectedItemPosition()).trim());
            getHODLeaveApiCall(isFromApplyFilter,false, true, true, collegeId, departmentId, courseId, semesterId);
        }
    }


    private void initFilterDialog() {
        m_Dialog = new Dialog(activity);
        m_Dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_shape_for_custom_dialog);
        m_Dialog.setCancelable(false);
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_for_hod_leave_filter, null);
        spCollege = view.findViewById(R.id.spCollege);
        spDepartment = view.findViewById(R.id.spDepartment);
        spCourse = view.findViewById(R.id.spCourse);
        spSemester = view.findViewById(R.id.spSemester);
        TextViewRegularFont btnCancel = view.findViewById(R.id.btnCancel);
        TextViewRegularFont btnApply = view.findViewById(R.id.btnApply);
        m_Dialog.setContentView(view);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideHodLeaveDialog();
                getHODLeaveApiCall(false,false, true, true, "0", "0", "0", "0");
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideHodLeaveDialog();
                callHODLeaveApprovalApi(true);
            }
        });
    }

    public void showFilterDialog() {
        if (m_Dialog != null) {
            if (!m_Dialog.isShowing()) {
                m_Dialog.show();
            }
        }
    }

    public void hideHodLeaveDialog() {
        if (m_Dialog != null) {
            if (m_Dialog.isShowing()) {
                m_Dialog.dismiss();
            }
        }
    }


}