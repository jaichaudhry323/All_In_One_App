package org.o7planning.simplelistview.Service4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import org.o7planning.simplelistview.utils.PreferencesManager;

public class Restarter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Broadcast Listened", "Service tried to stop");
//        Toast.makeText(context, "Service restarted", Toast.LENGTH_SHORT).show();

        PreferencesManager.init(context);

        if(PreferencesManager.getInstance()==null)
        {
            return;
        }
        if (PreferencesManager.getInstance().getBoolean("track_screen")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, YourService.class));
            } else {
                context.startService(new Intent(context, YourService.class));
            }
        }
    }
}

