package org.o7planning.simplelistview.Service2;

import android.app.Notification;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

import org.o7planning.simplelistview.MainActivity;
import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.utils.Global;
import org.o7planning.simplelistview.utils.NotificationUtil;
import org.o7planning.simplelistview.utils.ToastUtil;

import java.util.logging.LogRecord;


//https://www.youtube.com/watch?v=nl-dheVpt8o
public class NotificationReminderBroadcast extends BroadcastReceiver {

    private NotificationManager mNotificationManager;

    // Notification ID.
    private static final int NOTIFICATION_ID = 0;

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel_133";

    @Override
    public void onReceive(Context context, Intent intent) {

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        androidx.core.app.NotificationCompat.Builder builder = new androidx.core.app.NotificationCompat.Builder(context,"notifyLemubit");

        // Create the content intent for the notification, which launches
        // this activity
        Intent contentIntent = new Intent(context, MainActivity.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent
                        .FLAG_UPDATE_CURRENT);

        String title = Global.getInstance().getTitle();
        String content = Global.getInstance().getContent();

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_star)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        // Deliver the notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}

