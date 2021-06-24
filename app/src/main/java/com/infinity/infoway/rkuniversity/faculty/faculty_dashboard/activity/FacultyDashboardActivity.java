package com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiClientForStudentAndEmployeeFcmApi;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.api.Urls;
import com.infinity.infoway.rkuniversity.custom_class.TextViewBoldFont;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.faculty.faculty_announcement.FacultyAnnouncementActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_attendance.FacultyAttendanceActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_calendar.FacultyCalendarActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_co_ordinator_leave_approval.FacultyCoOrdinatorLeaveApprovalActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.adapter.FacultyAnnouncementAdapter;
import com.infinity.infoway.rkuniversity.faculty.faculty_dashboard.pojo.UpdateFaultyFCMTokenPojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_direct_login_to_student.FacultyDirectLoginToStudentActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_extra_lecture_approval.FacultyExtraLectureApprovalActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_hod_leave_approval.FacultyHODLeaveApprovalActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_leave.FacultyLeaveActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_lecture_plan.FacultyLecturePlanActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_news.FacultyNewsActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_pending_attendance.FacultyPendingAttendanceActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_profile.FacultyProfileActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_profile.FacultyProfilePojo;
import com.infinity.infoway.rkuniversity.faculty.faculty_teaching_update.FacultyTeachingUpdateActivity;
import com.infinity.infoway.rkuniversity.faculty.faculty_timetable.activity.FacultyTimeTableActivity;
import com.infinity.infoway.rkuniversity.faculty.student_messages.StudentMessagesActivity;
import com.infinity.infoway.rkuniversity.login.activity.LoginActivity;
import com.infinity.infoway.rkuniversity.login.pojo.CommonNewImageSliderPojo;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.MainActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.DialogUtils;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.URLS;
import com.infinity.infoway.rkuniversity.rku_old_app.Pojo.LoginPojo;
import com.infinity.infoway.rkuniversity.student.news_or_notification.FacultyOrStudentNewsOrNotificationsPojo;
import com.infinity.infoway.rkuniversity.student.student_dashboard.activity.StudentDashboardActivity;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import net.seifhadjhassen.recyclerviewpager.PagerModel;
import net.seifhadjhassen.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacultyDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;
    private MySharedPreferences mySharedPreferences;
    private ConnectionDetector connectionDetector;
    private LinearLayout llGoToHR;
    private CircleImageView cImgProfileFacultySide;
    private RecyclerViewPager recyclerViewPagerFacultySideBanner;

    //    AppCompatImageView imgNotificationBellFacultySide;ST
    RequestQueue queue;
    TextViewBoldFont tvFacultyName;
    TextViewRegularFont tvFacultyDesignation;

    //    LinearLayout llRemAttendanceFacultySide;
    LinearLayout llAttendanceFacultySide;
    LinearLayout llPendingAttendanceFacultySide;
    LinearLayout llLeaveFacultySide;
    LinearLayout llTimetableFacultySide;
    LinearLayout llLecturePlanFacultySide;
    LinearLayout llNewsFacultySide;
    LinearLayout llFacultyProfile;
    LinearLayout llFacultyAnnouncement;
    LinearLayout llFacultyTeachingUpdate;
    LinearLayout llDirectLoginToStudentFacultySide;
    LinearLayout llLeaveApprovalForHOD;
    LinearLayout llLeaveApprovalForCoOrdinator;
    LinearLayout llExtraLectureApproval;
    LinearLayout llStudentMessages;


    AppCompatButton btnViewAllAnnouncementFacultySide;
    RecyclerView rvAnnouncementFacultySide;

    ScrollView svFacultyDashboard;
    LinearLayout llFacultyDashboardProgressbar;
    LinearLayout llAnnouncementFacultyDashboard;
    LinearLayout llFacultyEventCalender;

    private Boolean exit = false;
    FrameLayout flNotification;
    TextViewRegularFont tvNotificationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        initView();
        sendFacultyFCMTokenToServer();
        getFacultyProfileDetailsApiCall();
    }


    private void initView() {
        queue = Volley.newRequestQueue(FacultyDashboardActivity.this);
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(FacultyDashboardActivity.this);
        mySharedPreferences = new MySharedPreferences(FacultyDashboardActivity.this);
        connectionDetector = new ConnectionDetector(FacultyDashboardActivity.this);
        llGoToHR = findViewById(R.id.llGoToHR);
        llGoToHR.setOnClickListener(this);
        cImgProfileFacultySide = findViewById(R.id.cImgProfileFacultySide);
        cImgProfileFacultySide.setOnClickListener(this);
        recyclerViewPagerFacultySideBanner = findViewById(R.id.recyclerViewPagerFacultySideBanner);

        tvFacultyName = findViewById(R.id.tvFacultyName);
        tvFacultyDesignation = findViewById(R.id.tvFacultyDesignation);

        svFacultyDashboard = findViewById(R.id.svFacultyDashboard);
        llFacultyDashboardProgressbar = findViewById(R.id.llFacultyDashboradProgressbar);
        llAnnouncementFacultyDashboard = findViewById(R.id.llAnnouncementFacultyDashboard);
        llFacultyEventCalender = findViewById(R.id.llFacultyEventCalender);
        llFacultyEventCalender.setOnClickListener(this);

//        imgNotificationBellFacultySide = findViewById(R.id.imgNotificationBellFacultySide);
//        imgNotificationBellFacultySide.setOnClickListener(this);

//        llRemAttendanceFacultySide = findViewById(R.id.llRemAttendanceFacultySide);
//        llRemAttendanceFacultySide.setOnClickListener(this);
        llAttendanceFacultySide = findViewById(R.id.llAttendanceFacultySide);
        llAttendanceFacultySide.setOnClickListener(this);
        llPendingAttendanceFacultySide = findViewById(R.id.llPendingAttendanceFacultySide);
        llPendingAttendanceFacultySide.setOnClickListener(this);
        llLeaveFacultySide = findViewById(R.id.llLeaveFacultySide);
        llLeaveFacultySide.setOnClickListener(this);
        llTimetableFacultySide = findViewById(R.id.llTimetableFacultySide);
        llTimetableFacultySide.setOnClickListener(this);
        llLecturePlanFacultySide = findViewById(R.id.llLecturePlanFacultySide);
        llLecturePlanFacultySide.setOnClickListener(this);
        llNewsFacultySide = findViewById(R.id.llNewsFacultySide);
        llNewsFacultySide.setOnClickListener(this);
        llDirectLoginToStudentFacultySide = findViewById(R.id.llDirectLoginToStudentFacultySide);
        llDirectLoginToStudentFacultySide.setOnClickListener(this);

        llLeaveApprovalForHOD = findViewById(R.id.llLeaveApprovalForHOD);
        llLeaveApprovalForHOD.setOnClickListener(this);
        llLeaveApprovalForCoOrdinator = findViewById(R.id.llLeaveApprovalForCoOrdinator);
        llLeaveApprovalForCoOrdinator.setOnClickListener(this);

        btnViewAllAnnouncementFacultySide = findViewById(R.id.btnViewAllAnnouncementFacultySide);
        btnViewAllAnnouncementFacultySide.setOnClickListener(this);
        rvAnnouncementFacultySide = findViewById(R.id.rvAnnouncementFacultySide);

        flNotification = findViewById(R.id.flNotification);
        flNotification.setOnClickListener(this);
        tvNotificationCount = findViewById(R.id.tvNotificationCount);

        llFacultyProfile = findViewById(R.id.llFacultyProfile);
        llFacultyProfile.setOnClickListener(this);
        llFacultyAnnouncement = findViewById(R.id.llFacultyAnnouncement);
        llFacultyAnnouncement.setOnClickListener(this);
        llFacultyTeachingUpdate = findViewById(R.id.llFacultyTeachingUpdate);
        llFacultyTeachingUpdate.setOnClickListener(this);
        llExtraLectureApproval = findViewById(R.id.llExtraLectureApproval);
        llExtraLectureApproval.setOnClickListener(this);
        llStudentMessages = findViewById(R.id.llStudentMessages);
        llStudentMessages.setOnClickListener(this);

        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getEmpIsAdminOrNot())) {
            if (mySharedPreferences.getEmpIsAdminOrNot().equalsIgnoreCase("1")) {
                llDirectLoginToStudentFacultySide.setVisibility(View.VISIBLE);
            } else {
                llDirectLoginToStudentFacultySide.setVisibility(View.GONE);
            }
        } else {
            llDirectLoginToStudentFacultySide.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.llFacultyProfile) {
            Intent profileActivityStudentSide = new Intent(this, FacultyProfileActivity.class);
            startActivityForResult(profileActivityStudentSide, IntentConstants.REQUEST_CODE_FOR_FACULTY_LOGOUT);
            overridePendingTransition(R.anim.slide_in_left, 0);
        } else if (v.getId() == R.id.llFacultyAnnouncement) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyAnnouncementActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llFacultyTeachingUpdate) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyTeachingUpdateActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cImgProfileFacultySide) {
            Intent profileActivityStudentSide = new Intent(this, FacultyProfileActivity.class);
            startActivityForResult(profileActivityStudentSide, IntentConstants.REQUEST_CODE_FOR_FACULTY_LOGOUT);
            overridePendingTransition(R.anim.slide_in_left, 0);
        } else if (v.getId() == R.id.llAttendanceFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyAttendanceActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llPendingAttendanceFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyPendingAttendanceActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llLeaveFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyLeaveActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llTimetableFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyTimeTableActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llLecturePlanFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyLecturePlanActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llNewsFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyNewsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnViewAllAnnouncementFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyAnnouncementActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.flNotification) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyAnnouncementActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llDirectLoginToStudentFacultySide) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyDirectLoginToStudentActivity.class);
            startActivityForResult(intent, IntentConstants.REQUEST_CODE_FOR_FACULTY_STUDENT_DIRECT_LOGIN);
        } else if (v.getId() == R.id.llGoToHR) {
            LoginAPICall();
        } else if (v.getId() == R.id.llLeaveApprovalForHOD) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyHODLeaveApprovalActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llLeaveApprovalForCoOrdinator) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyCoOrdinatorLeaveApprovalActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.llExtraLectureApproval) {
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyExtraLectureApprovalActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.llFacultyEventCalender){
            Intent intent = new Intent(FacultyDashboardActivity.this, FacultyCalendarActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.llStudentMessages){
            Intent intent = new Intent(FacultyDashboardActivity.this, StudentMessagesActivity.class);
            startActivity(intent);
        }
    }

    private void getSliderImagesApiCall() {
        ApiImplementer.getImageSliderNewApiImplementer(Urls.DOMAIN_NAME, mySharedPreferences.getEmpInstituteId(),
                mySharedPreferences.getEmpAcId(), new Callback<CommonNewImageSliderPojo>() {
                    @Override
                    public void onResponse(Call<CommonNewImageSliderPojo> call, Response<CommonNewImageSliderPojo> response) {
                        try {
                            if (response.isSuccessful() && response.body().getUrl().size() > 0) {
                                ArrayList<String> bannerUrls = (ArrayList<String>) response.body().getUrl();
                                ArrayList<String> sequencedBannerUrls = new ArrayList<>();
                                for (int i = 0; i < bannerUrls.size(); i++) {
                                    if (bannerUrls.get(i) != null && !bannerUrls.get(i).isEmpty() && bannerUrls.get(i).length() > 7) {
                                        String imgUrl = bannerUrls.get(i);
                                        String imgUrlWithoutNameExtension = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.lastIndexOf("."));
                                        String[] sequenceAndName = imgUrlWithoutNameExtension.split("_");
                                        sequencedBannerUrls.add(Integer.parseInt(sequenceAndName[0]) - 1, imgUrl);
                                    }
                                }

                                for (int i = 0; i < sequencedBannerUrls.size(); i++) {
                                    if (sequencedBannerUrls.get(i) != null && !sequencedBannerUrls.get(i).isEmpty() && sequencedBannerUrls.get(i).length() > 7) {
                                        recyclerViewPagerFacultySideBanner.addItem(new PagerModel(bannerUrls.get(i)));
                                    }
                                }
                                recyclerViewPagerFacultySideBanner.start();
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonNewImageSliderPojo> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    private void getFacultyProfileDetailsApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llFacultyDashboardProgressbar.setVisibility(View.VISIBLE);
            svFacultyDashboard.setVisibility(View.GONE);
            ApiImplementer.getFacultyProfileDetailsApiImplementer(mySharedPreferences.getEmpId(), new Callback<ArrayList<FacultyProfilePojo>>() {
                @Override
                public void onResponse(Call<ArrayList<FacultyProfilePojo>> call, Response<ArrayList<FacultyProfilePojo>> response) {
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            llFacultyDashboardProgressbar.setVisibility(View.GONE);
                            FacultyProfilePojo facultyProfilePojo = response.body().get(0);

                            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getUnread_notif_count())) {
                                tvNotificationCount.setText(facultyProfilePojo.getUnread_notif_count() + "");
                            }

                            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getName())) {
                                tvFacultyName.setText(facultyProfilePojo.getName() + "");
                            }

                            if (!CommonUtil.checkIsEmptyOrNullCommon(facultyProfilePojo.getEdName())) {
                                tvFacultyDesignation.setText(facultyProfilePojo.getEdName() + "");
                            }

                            if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getEmpPhotoUrl())) {
                                Glide.with(FacultyDashboardActivity.this)
                                        .asBitmap()
                                        .load(mySharedPreferences.getEmpPhotoUrl().trim())
                                        .override(70, 70)
                                        .placeholder(R.drawable.person_img)
                                        .error(R.drawable.person_img)
                                        .into(cImgProfileFacultySide);
                            }
                            svFacultyDashboard.setVisibility(View.VISIBLE);
                            getSliderImagesApiCall();
                            getFacultyAnnouncementApiCall();
                        } else {
                            Toast.makeText(FacultyDashboardActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FacultyProfilePojo>> call, Throwable t) {
                    llFacultyDashboardProgressbar.setVisibility(View.GONE);
                    svFacultyDashboard.setVisibility(View.GONE);
                    Toast.makeText(FacultyDashboardActivity.this, "Request Failed,Please try again later", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendFacultyFCMTokenToServer() {
        if (!CommonUtil.checkIsEmptyOrNullCommon(mySharedPreferences.getFCMToken())) {
            if (connectionDetector.isConnectingToInternet()) {
                ApiImplementer.updateFacultyFcmTokenApiImplementer(mySharedPreferences.getEmpId(), mySharedPreferences.getFCMToken(),
                        ApiClientForStudentAndEmployeeFcmApi.ENCODED_KEY_FOR_FACULTY_FCM_REGISTRATION, new Callback<UpdateFaultyFCMTokenPojo>() {
                            @Override
                            public void onResponse(Call<UpdateFaultyFCMTokenPojo> call, Response<UpdateFaultyFCMTokenPojo> response) {

                            }

                            @Override
                            public void onFailure(Call<UpdateFaultyFCMTokenPojo> call, Throwable t) {

                            }
                        });
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_FOR_FACULTY_LOGOUT) {
            Intent intent = new Intent(FacultyDashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_FOR_VIEW_ALL_NEWS_OR_NOTIFICATION_FACULTY_SIDE) {
            getFacultyAnnouncementApiCall();
        } else if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_FOR_FACULTY_STUDENT_DIRECT_LOGIN) {
            Intent intent = new Intent(FacultyDashboardActivity.this, StudentDashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void getFacultyAnnouncementApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            llAnnouncementFacultyDashboard.setVisibility(View.GONE);
            ApiImplementer.getFacultyOrStudentNewsOrNotificationImplementer(mySharedPreferences.getLoginUserType() + "",
                    mySharedPreferences.getEmpUserStatus(), mySharedPreferences.getEmpId(), "0", "0",
                    "0", "0", mySharedPreferences.getEmpInstituteId(),
                    mySharedPreferences.getEmpYearId(), "8", new Callback<FacultyOrStudentNewsOrNotificationsPojo>() {
                        @Override
                        public void onResponse(Call<FacultyOrStudentNewsOrNotificationsPojo> call, Response<FacultyOrStudentNewsOrNotificationsPojo> response) {
                            if (response.isSuccessful() && response.body() != null &&
                                    response.body().getTable().size() > 0) {
                                llAnnouncementFacultyDashboard.setVisibility(View.VISIBLE);
                                rvAnnouncementFacultySide.setLayoutManager(new LinearLayoutManager(FacultyDashboardActivity.this, LinearLayoutManager.HORIZONTAL, false));
                                rvAnnouncementFacultySide.setAdapter(new FacultyAnnouncementAdapter(FacultyDashboardActivity.this, (ArrayList<FacultyOrStudentNewsOrNotificationsPojo.Data>) response.body().getTable()));
                            }
                        }

                        @Override
                        public void onFailure(Call<FacultyOrStudentNewsOrNotificationsPojo> call, Throwable t) {
                            llAnnouncementFacultyDashboard.setVisibility(View.GONE);
                        }
                    });
        } else {
            Toast.makeText(this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    private void LoginAPICall() {
        DialogUtils.showProgressDialog(FacultyDashboardActivity.this, "");
//        String url = URLS.LoginCheck + "&userName=" + "gaurang.vyas@rku.ac.in" + "&passWord=" + "Rku@12345" + "";
        String url = URLS.LoginCheck + "&userName=" + mySharedPreferences.getEmpUserName() + "&passWord=" + mySharedPreferences.getEmpPassword() + "";
        url.replace(" ", "%20");

        System.out.println("LoginCheck URL " + url + "");
        StringRequest request = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DialogUtils.hideProgressDialog();

                System.out.println("response of LoginCheck !!!!!!!!!!! " + response);
                response = response + "";
                if (response.length() > 5) {
                    response = "{\"Data\":" + response + "}";

                    System.out.println("success response LoginCheck !!!!!!!!!!!!!!!!!!!" + response + "");
                    Gson gson = new Gson();
                    LoginPojo loginPojo = gson.fromJson(response, LoginPojo.class);
                    if (loginPojo != null) {
                        if (loginPojo.getData() != null) {
                            if (loginPojo.getData().get(0) != null) {
                                if (loginPojo.getData().size() > 0) {
                                    if (loginPojo.getData().get(0).getStatus().contentEquals("1")) {
                                        DialogUtils.Show_Toast(FacultyDashboardActivity.this, "Login Successfully");
                                        //********* store login data of user ****************
                                        mySharedPreferecesRKUOLD.storeLoginData(loginPojo.getData().get(0).getStatus() + "", loginPojo.getData().get(0).getUsrm_id() + "", loginPojo.getData().get(0).getEmp_code() + "", loginPojo.getData().get(0).getUsrm_name() + "", loginPojo.getData().get(0).getUsrm_dis_name() + "", loginPojo.getData().get(0).getComp_id() + "", loginPojo.getData().get(0).getUsrm_brm_id() + "", loginPojo.getData().get(0).getCom_name() + "", loginPojo.getData().get(0).getFin_year() + "", loginPojo.getData().get(0).getFin_id() + "", loginPojo.getData().get(0).getFin_start_date() + "", loginPojo.getData().get(0).getFin_end_date() + "", loginPojo.getData().get(0).getEmp_id() + "", loginPojo.getData().get(0).getDepartment() + "", loginPojo.getData().get(0).getReportingto() + "", loginPojo.getData().get(0).getUserphoto() + "", loginPojo.getData().get(0).getDesignation() + "", loginPojo.getData().get(0).getBranch() + "", loginPojo.getData().get(0).getFullName() + "", loginPojo.getData().get(0).getPersonal_email() + "", loginPojo.getData().get(0).getOffice_email() + "", loginPojo.getData().get(0).getMobileno() + "", loginPojo.getData().get(0).getJoining_date() + "");


                                        Intent intent = new Intent(FacultyDashboardActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        DialogUtils.Show_Toast(FacultyDashboardActivity.this, "Invalid UserName/Password");
                                    }
                                }


                            }
                        }
                    }
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtils.Show_Toast(FacultyDashboardActivity.this, "Please Try Again Later");
                DialogUtils.hideProgressDialog();
                System.out.println("errorrrrrrrrrr " + error);
                System.out.println("errorrrrrrrrrr in api" + error.networkResponse);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

}