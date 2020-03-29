package com.example.android.applicationtaskhi2020.sync;

import android.content.Context;

import com.example.android.applicationtaskhi2020.utilities.NotificationUtils;

public class ReminderTask {

    public static final String ACTION_GO_TO_REGISTER = "go-to-register";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    public static final String ACTION_REMINDER = "reminder";

    public static void executeTask(Context context, String action){
        if (ACTION_GO_TO_REGISTER.equals(action)){
            gotoRegister(context);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)){
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_REMINDER.equals(action)){
            NotificationUtils.remindUsertoRegister(context);
        }
    }

    private static void gotoRegister(Context context){
        NotificationUtils.contentIntent(context);
    }


}
