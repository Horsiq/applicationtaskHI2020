package com.example.android.applicationtaskhi2020.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.android.applicationtaskhi2020.RegisterActivity;
import com.example.android.applicationtaskhi2020.R;
import com.example.android.applicationtaskhi2020.sync.ReminderIntentService;
import com.example.android.applicationtaskhi2020.sync.ReminderTask;

public class NotificationUtils {

    private static final int ACTION_REMINDER_NOTIFICATION_ID = 1100;
    private static final int ACTION_REMINDER_PENDING_INTENT_ID = 2200;
    private static final String ACTION_REMINDER_NOTIFICATION_CANNEL_ID = "reminder_notification_channel";
    private static final int ACTION_ACTION_PENDING_INTENT_ID = 1;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 22;

    public static void clearAllNotifications(Context context){
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public static void remindUsertoRegister(Context context){
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel mChannel = new NotificationChannel(ACTION_REMINDER_NOTIFICATION_CANNEL_ID,
                    context.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, ACTION_REMINDER_NOTIFICATION_CANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_launcher_logotyp)
                .setContentTitle(context.getString(R.string.notification_reminder_title))
                .setContentText(context.getString(R.string.notification_reminder_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.notification_reminder_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(goToRegisterAction(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(ACTION_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
    }

    private static NotificationCompat.Action ignoreReminderAction(Context context){
        Intent ignoreReminderIntent = new Intent(context, ReminderTask.class);
        ignoreReminderIntent.setAction(ReminderTask.ACTION_DISMISS_NOTIFICATION);
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_launcher_logotyp, "No, thanks", ignoreReminderPendingIntent);
        return ignoreReminderAction;
    }

    private static NotificationCompat.Action goToRegisterAction(Context context) {
        Intent goToRegisterActIntent = new Intent(context, ReminderIntentService.class);
        goToRegisterActIntent.setAction(ReminderTask.ACTION_GO_TO_REGISTER);
        PendingIntent goToRegisterActPendingIntent = PendingIntent.getService(
                context,
                ACTION_ACTION_PENDING_INTENT_ID,
                goToRegisterActIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Action registerAction = new NotificationCompat.Action(R.drawable.ic_launcher_logotyp, "Go to register", goToRegisterActPendingIntent);
        return registerAction;
    }

    public static PendingIntent contentIntent(Context context){
        Intent startActivityIntent = new Intent(context, RegisterActivity.class);
        return PendingIntent.getActivity(
                context,
                ACTION_ACTION_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
