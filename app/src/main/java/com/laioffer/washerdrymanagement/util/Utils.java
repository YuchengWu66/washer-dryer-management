package com.laioffer.washerdrymanagement.util;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
    public static Toast constructToast(Context context, String msg) {
        return Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }
}
