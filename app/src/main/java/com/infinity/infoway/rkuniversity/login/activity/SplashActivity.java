package com.infinity.infoway.rkuniversity.login.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.infinity.infoway.rkuniversity.BuildConfig;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.api.ApiImplementer;
import com.infinity.infoway.rkuniversity.common_activity.NoInternetConnectionActivity;
import com.infinity.infoway.rkuniversity.login.pojo.CheckVersionApiPojo;
import com.infinity.infoway.rkuniversity.student.assignment.AssignmentActivity;
import com.infinity.infoway.rkuniversity.student.attendance.activity.StudentAttendanceActivity;
import com.infinity.infoway.rkuniversity.student.e_learning.activity.ELearningActivity;
import com.infinity.infoway.rkuniversity.student.exam.activity.StudentResultActivity;
import com.infinity.infoway.rkuniversity.student.fee_details.activity.FeeDetailsActivity;
import com.infinity.infoway.rkuniversity.student.fee_details.activity.PayFeeActivity;
import com.infinity.infoway.rkuniversity.student.holiday.HolidayActivity;
import com.infinity.infoway.rkuniversity.student.home_work.activity.StudentHomeWorkActivity;
import com.infinity.infoway.rkuniversity.student.leave_application.activity.LeaveApplicationActivity;
import com.infinity.infoway.rkuniversity.student.lesson_plan.StudentLessonPlanActivity;
import com.infinity.infoway.rkuniversity.student.news_or_notification.ViewAllNewsOrNotificationStudentActivity;
import com.infinity.infoway.rkuniversity.student.student_activity.StudentActivity;
import com.infinity.infoway.rkuniversity.student.student_syllabus.StudentSyllabusActivity;
import com.infinity.infoway.rkuniversity.student.student_timetable.activity.StudentTimeTableActivity;
import com.infinity.infoway.rkuniversity.utils.ConnectionDetector;
import com.infinity.infoway.rkuniversity.utils.DialogUtil;
import com.infinity.infoway.rkuniversity.utils.DynamicActivityName;
import com.infinity.infoway.rkuniversity.utils.IntentConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

//    AppCompatImageView ivSplashLogo;

//    private final String[] RunTimePerMissions = {
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.CAMERA};
private final String[] RunTimePerMissions = {
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA};

//    private final String[] BACKGROUND_LOCATION_RUN_TIME_PERMISSION = {Manifest.permission.ACCESS_BACKGROUND_LOCATION};
    private static final int MY_PERMISSIONS_REQUEST_READ_WRITE_STATE = 100;
//    private static final int REQUEST_CODE_BACKGROND_LOCATION_PERMISSION_FOR_ANDROID_Q = 1002;

    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();

        if (getIntent().getAction() != null && !getIntent().getAction().isEmpty()) {
            Intent intent = null;
            String clickAction = getIntent().getAction().trim();
            if (clickAction.equalsIgnoreCase(DynamicActivityName.FEES_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, PayFeeActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.ATTENDANCE_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, StudentAttendanceActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.LESSON_PLAN_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, StudentLessonPlanActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.NEWS_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, ViewAllNewsOrNotificationStudentActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, StudentActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.HOMEWORK_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, StudentHomeWorkActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.SYLLABUS_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, StudentSyllabusActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.RESULT_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, StudentResultActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.ASSIGNMENT_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, AssignmentActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.EXAM_TIMETABLE_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, StudentTimeTableActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.FEE_CIRCULAR_ACTIVITY_FOR_STUDENt)) {
                intent = new Intent(SplashActivity.this, FeeDetailsActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.LEAVE_APPLICATION_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, LeaveApplicationActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.E_LEARNING_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, ELearningActivity.class);
                startActivity(intent);
                finish();
            } else if (clickAction.equalsIgnoreCase(DynamicActivityName.HOLIDAY_LIST_ACTIVITY_FOR_STUDENT)) {
                intent = new Intent(SplashActivity.this, HolidayActivity.class);
                startActivity(intent);
                finish();
            } else {
                loadSplashScreenAnimationAndAskForPermission();
            }
        } else {
            loadSplashScreenAnimationAndAskForPermission();
        }
    }

    private void initView() {
        connectionDetector = new ConnectionDetector(SplashActivity.this);
//        ivSplashLogo = findViewById(R.id.ivSplashLogo);
    }

    private void loadSplashScreenAnimationAndAskForPermission() {
//        Animation slide_up = AnimationUtils.loadAnimation(SplashActivity.this,
//                R.anim.slide_up);
//        ivSplashLogo.startAnimation(slide_up);
//        Thread timer = new Thread() {
//            public void run() {
//                try {
//                    sleep(1600);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermissions(SplashActivity.this, RunTimePerMissions)) {
                ActivityCompat.requestPermissions(SplashActivity.this, RunTimePerMissions, MY_PERMISSIONS_REQUEST_READ_WRITE_STATE);
            } else {
//                permissionForBackgroundLocationAndroidQ();
                checkVersionInfoApiCall();
            }
        } else {
            checkVersionInfoApiCall();
        }
//                }
//            }
//        };
//        timer.start();
    }

    private void redirectToLoginActivity() {
        Intent openMainActivity = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(openMainActivity);
        finish();
    }


    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

//    private static boolean hasPermissionsForBackgroundLocation(Context context, String... permissions) {
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && context != null && permissions != null) {
//            for (String permission : permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

//    private void permissionForBackgroundLocationAndroidQ() {
////
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
////            if (!hasPermissionsForBackgroundLocation(SplashActivity.this, BACKGROUND_LOCATION_RUN_TIME_PERMISSION)) {
////                ActivityCompat.requestPermissions(SplashActivity.this, BACKGROUND_LOCATION_RUN_TIME_PERMISSION,
////                        REQUEST_CODE_BACKGROND_LOCATION_PERMISSION_FOR_ANDROID_Q);
////            } else {
////                checkVersionInfoApiCall();
////            }
////        } else {
////            checkVersionInfoApiCall();
////        }
////    }

    private void alertAlert(String msg) {
        new MaterialAlertDialogBuilder(SplashActivity.this)
                .setTitle(Html.fromHtml("<b>" + getResources().getString(R.string.permission_request) + " </b>"))
                .setMessage(msg + " So Reopen the app and grant permission for well uses of app")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_WRITE_STATE) {

            if (grantResults.length == 3 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED) {
//                permissionForBackgroundLocationAndroidQ();
                checkVersionInfoApiCall();
            } else {
//                loadSplashScreenAnimationAndAskForPermission();
                alertAlert(getResources().getString(R.string.permissions_has_not_grant));
            }
        }
//        else if (requestCode == REQUEST_CODE_BACKGROND_LOCATION_PERMISSION_FOR_ANDROID_Q) {
//            if (grantResults.length == 1 &&
//                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                checkVersionInfoApiCall();
//            } else {
//                alertAlert(getResources().getString(R.string.permissions_has_not_grant));
//            }
//        }
    }

    private void checkVersionInfoApiCall() {
        if (connectionDetector.isConnectingToInternet()) {
            PackageInfo pInfo = null;
            int appVersionCode = 0;
            try {
                pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                appVersionCode = pInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (pInfo == null) {
                appVersionCode = BuildConfig.VERSION_CODE;
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogUtil.showProgressDialogNotCancelable(SplashActivity.this, "");
                }
            });

            ApiImplementer.checkVersionInfoApiImplementer(appVersionCode, new Callback<CheckVersionApiPojo>() {
                @Override
                public void onResponse(Call<CheckVersionApiPojo> call, Response<CheckVersionApiPojo> response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DialogUtil.hideProgressDialog();
                        }
                    });
                    if (response.isSuccessful()) {
                        if (Integer.parseInt(response.body().getStatus()) == 1) {//1 For Optional Update

                            Intent intent = new Intent(SplashActivity.this, UpdateAppActivity.class);
                            intent.putExtra(IntentConstants.IS_FORCE_UPDATE, false);
                            intent.putExtra(IntentConstants.APP_UPDATE_TYPE, IntentConstants.OPTIONAL_UPDATE);
                            startActivity(intent);
                            finish();
                        } else if (Integer.parseInt(response.body().getStatus()) == 2) {//2 For Force Update

                            Intent intent = new Intent(SplashActivity.this, UpdateAppActivity.class);
                            intent.putExtra(IntentConstants.IS_FORCE_UPDATE, true);
                            intent.putExtra(IntentConstants.APP_UPDATE_TYPE, IntentConstants.FORCE_UPDATE);
                            startActivity(intent);
                            finish();
                        } else {// 0 for app is already up to date redirect to login activity
                            redirectToLoginActivity();
                        }
                    } else {
                        redirectToLoginActivity();
                    }
                }

                @Override
                public void onFailure(Call<CheckVersionApiPojo> call, Throwable t) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DialogUtil.hideProgressDialog();
                        }
                    });
                    redirectToLoginActivity();
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, NoInternetConnectionActivity.class);
                    startActivityForResult(intent, IntentConstants.REQUEST_CODE_FACULTY_NO_INTERNET_CONNECTION);
//                    Toast.makeText(/*SplashActivity.this, "No internet connection,Please try again later.", Toast.LENGTH_SHORT).show();
//                    finish();*/
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IntentConstants.REQUEST_CODE_FACULTY_NO_INTERNET_CONNECTION) {
            this.recreate();
        }
    }
}