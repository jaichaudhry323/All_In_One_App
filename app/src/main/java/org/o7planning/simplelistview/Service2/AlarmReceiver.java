package org.o7planning.simplelistview.Service2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import org.o7planning.simplelistview.MainActivity;
import org.o7planning.simplelistview.R;

//https://github.com/google-developer-training/android-fundamentals-apps-v2/blob/master/StandUp/app/src/main/java/com/android/fundamentals/standup/AlarmReceiver.java
//https://developer.android.com/codelabs/android-training-alarm-manager#5

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager mNotificationManager;

    // Notification ID.
    private static final int NOTIFICATION_ID = 0;

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    /**
     * Called when the BroadcastReceiver receives an Intent broadcast.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Deliver the notification.
        deliverNotification(context);
    }

    private void deliverNotification(Context context) {
        // Create the content intent for the notification, which launches
        // this activity
        Intent contentIntent = new Intent(context, MainActivity.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent
                        .FLAG_UPDATE_CURRENT);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_star)
                .setContentTitle("AlarmReceiver")
                .setContentText("Google Dev Code")
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        // Deliver the notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}