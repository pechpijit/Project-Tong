package com.chatchai.project;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectAPI {
    public static String URL = "http://192.168.1.51/project-tong/public";
    String TAG = "ConnectAPI:";

    public void checkHn(final Context mContext, final String hn) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/checkHn/"+hn)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d(TAG, "checkHn " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not") ) {
                    ((LoginActivity) mContext).onLoginFaild();
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    ((LoginActivity) mContext).onLoginFaild();
                    dialogNotfound(mContext);
                }  else if (string.equals("not found")) {
                    ((LoginActivity) mContext).onLoginFaild();
                    dialogNotfound(mContext);
                } else {
                    ((LoginActivity) mContext).onLoginSuccess(string);
                }
            }
        }.execute();
    }



    private static void dialogErrorNoIntent(final Context mContext, String string) {
        new AlertDialog.Builder(mContext)
                .setTitle("The system temporarily")
                .setMessage("ไม่สามารถเข้าใช้งานได้ กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", null)
                .show();
    }

    private static void dialogNotfound(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Not Found")
                .setMessage("ไม่พบข้อมูล")
                .setNegativeButton("OK", null)
                .show();
    }
}
