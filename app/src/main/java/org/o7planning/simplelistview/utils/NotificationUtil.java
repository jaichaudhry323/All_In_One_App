package org.o7planning.simplelistview.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import org.o7planning.simplelistview.Service2.NotificationReminderBroadcast;

public class NotificationUtil  {

    public static void setNotification(Context context,String title,String content,long delay)
    {
        Global.getInstance().setContent(content);
        Global.getInstance().setTitle(title);

        Intent intent = new Intent(context, NotificationReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, pendingIntent);
    }

}
