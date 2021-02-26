package org.o7planning.simplelistview.Service3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import org.o7planning.simplelistview.Service4.YourService;
import org.o7planning.simplelistview.utils.PreferencesManager;

// User Present works even when app killed
public class ScreenReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

//        Intent service = new Intent(context,MyService.class);
//        context.startActivity(service);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService(new Intent(context, YourService.class));
//        } else {
//            context.startService(new Intent(context, YourService.class));
//        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("ReminderBroadcast", "onReceive" + intent.getAction());
                Log.i("ReminderBroadcast", "onReceive" + intent.getAction());

                if (PreferencesManager.getInstance().getBoolean("track_screen")) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startForegroundService(new Intent(context, YourService.class));
                    } else {
                        context.startService(new Intent(context, YourService.class));
                    }
                }

//                ToastUtil.makeLongToast(context,"ScreenReminderBroadcast"+intent.getAction());
            }
        }, 5000);

//        ToastUtil.makeLongToast(context,"ScreenReminderBroadcast"+intent.getAction());


        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
////            ToastUtil.makeLongToast(context,"Screen ON");
////            PreferencesManager.getInstance().putLong("last_screen_on", System.currentTimeMillis());
//            NotificationUtil.setNotification(context,"Screen ON onReceive ScreenReminderBroadcast","Screen ON",5000);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
//            NotificationUtil.setNotification(context,"Screen OFF onReceive ScreenReminderBroadcast","Screen OFF",5000);
////            PreferencesManager.getInstance().putLong("last_screen_off",System.currentTimeMillis());
        }

//        NotificationUtil.setNotification(context, "m" + intent.getAction() + intent.getAction().equals(Intent.ACTION_SCREEN_OFF) ,"Screen ",4000);
//        ToastUtil.makeLongToast(context,"Screen Receiver Called" + Long.toString(System.currentTimeMillis()));
    }

}

