package org.o7planning.simplelistview.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void makeLongToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void makeShortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}