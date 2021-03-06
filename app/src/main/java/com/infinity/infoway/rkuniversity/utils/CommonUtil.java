package com.infinity.infoway.rkuniversity.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CommonUtil {

    //don't change this param if need to change than ask to backed developer regarding login type pram value 1 or 2 etc..
    // and also check that value in both login api student and faculty(employee).
    public static boolean IS_EVENT_CAL_LOADED_FIRST_TIME = true;
    public static final int LOGIN_TYPE_FACULTY = 1;
    public static final int LOGIN_TYPE_STUDENT = 2;

    public static final int ROW_PER_PAGE = 25;
    public static final String FOLDER_NAME = "Rku";


    public static String generateUniqueFileName(String fileName) {
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    public static boolean checkIsEmptyOrNullCommon(Object object) {
        boolean isNullOrEmpty = false;
        if (object == null) {
            isNullOrEmpty = true;
        } else if (object.getClass() == String.class ||
                object.getClass() == CharSequence.class) {
            if (object.toString().trim().isEmpty()) {
                isNullOrEmpty = true;
            }
        } else if (object == "") {
            isNullOrEmpty = true;
        }

        return isNullOrEmpty;
    }

    public static String getMonthNameFromNumber(int monthNo) {
        String monthName = "";
        switch (monthNo) {
            case 0:
                monthName = "JANUARY";
                break;
            case 1:
                monthName = "FEBRUARY";
                break;
            case 2:
                monthName = "MARCH";
                break;
            case 3:
                monthName = "APRIL";
                break;
            case 4:
                monthName = "MAY";
                break;
            case 5:
                monthName = "JUNE";
                break;
            case 6:
                monthName = "JULY";
                break;
            case 7:
                monthName = "AUGUST";
                break;
            case 8:
                monthName = "SEPTEMBER";
                break;
            case 9:
                monthName = "OCTOBER";
                break;
            case 10:
                monthName = "NOVEMBER";
                break;
            case 11:
                monthName = "DECEMBER";
                break;
        }
        return monthName;
    }


    public static String getMonthSortNameFromNumber(int monthNo) {
        String monthName = "";
        switch (monthNo) {
            case 1:
                monthName = "Jan";
                break;
            case 2:
                monthName = "Feb";
                break;
            case 3:
                monthName = "Mar";
                break;
            case 4:
                monthName = "Apr";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "Jun";
                break;
            case 7:
                monthName = "Jul";
                break;
            case 8:
                monthName = "Aug";
                break;
            case 9:
                monthName = "Sep";
                break;
            case 10:
                monthName = "Oct";
                break;
            case 11:
                monthName = "Nov";
                break;
            case 12:
                monthName = "Dec";
                break;
        }
        return monthName;
    }


    public static String getWeekDayName(int currentMonth, int dayOfMonth, String currentDate) {

        SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inFormat.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        String goal = outFormat.format(date).substring(0, 3);
        return goal;


//        if (dayOfMonth != 10 && String.valueOf(dayOfMonth).contains("0")) {
//            dayOfMonth = Integer.parseInt(String.valueOf(dayOfMonth).replace("0", ""));
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MONTH, currentMonth);
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
//
//        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
//
//        return days[dayIndex - 1];
    }

    public static void hideKeyboardCommon(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int convertDpToPxe(Context context, int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public static byte[] readByteFromFile(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        } finally {
            try {
                if (ous != null)
                    ous.close();
            } catch (IOException e) {
            }

            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return ous.toByteArray();
    }


    public static boolean checkIsFromDateGraterThanToDate(String fromDate, String toDate, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        boolean isValid = true;
        try {
            if (simpleDateFormat.parse(fromDate).after(simpleDateFormat.parse(toDate))) {
                isValid = false;//If start date is before end date
            } else if (simpleDateFormat.parse(fromDate).equals(simpleDateFormat.parse(toDate))) {
                isValid = true;//If two dates are equal
            } else {
                isValid = true; //If start date is after the end date
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isValid;
    }


    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }
        return isInBackground;
    }

    public static String getRandom6DigitOTP() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

}
