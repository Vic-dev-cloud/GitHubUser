package com.example.githubuser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class Utilities {
    public static final String URL_GITHUB_USER_LIST = "https://api.github.com/users?since=0&page=0&per_page=100";

    public static String exceptionToString(Exception ex) {
        Writer writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
            Toast.makeText(context, "network is not available", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return false;
        }
    }
}
