package com.infinity.infoway.rkuniversity.utils;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class AppController extends Application {

    private static Context context;
    public static final String NOTIFICATION_CHANNEL_NAME = "atmiya_push_notification";
    public static final String NOTIFICATION_CHANNEL_ID = "com.infinity.infoway.rku";

    @Override
    public void onCreate() {
        super.onCreate();
        AppController.context = getApplicationContext();
        createNotificationChannel();
    }

    public static Context getAppContext() {
        return AppController.context;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
