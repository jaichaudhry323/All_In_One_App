package org.o7planning.simplelistview.utils;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.snackbar.Snackbar;

public class SnackbarUtil {

    public static void makeLongSnack(View view , String text) {
        Snackbar snackbar =  Snackbar.make(view , text , Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public static void makeShortSnack(View view , String text) {
        Snackbar snackbar =  Snackbar.make(view , text , Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
    public static void makeLongSnack(CoordinatorLayout coordinatorLayoutlayout , String text) {
        Snackbar snackbar =  Snackbar.make(coordinatorLayoutlayout , text , Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public static void makeShortSnack(CoordinatorLayout coordinatorLayout , String text) {
        Snackbar snackbar =  Snackbar.make(coordinatorLayout , text , Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

}
