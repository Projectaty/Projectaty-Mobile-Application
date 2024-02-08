package com.projectaty.config;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public class LangugaeManager {
    /*
        Creating this class to set the langaugae of the UI
     */
     public static void setLocale(Context context, String language) {
            Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale);
            } else {
                configuration.locale = locale;
            }
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
     }
}
