package com.chatchai.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class SplashActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        SharedPreferences sp = getSharedPreferences("Preferences_tong", Context.MODE_PRIVATE);
        boolean user = sp.getBoolean("login", false);

        if (user) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else {
//            isNetworkConnected();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                            finish();
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    }, 3000);
        }
    }

    public void NotConnectInternet() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("เกิดข้อผิดพลาด");
        builder.setCancelable(true);
        builder.setMessage("ไม่สามารถเชื่อมต่ออินเทอร์เน็ตได้ กรุณาลองใหม่อีกครั้ง");
        builder.setPositiveButton("ลองอีกครั้ง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

//    public void isNetworkConnected() {
//        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... voids) {
//                OkHttpClient okHttpClient = new OkHttpClient();
//
//                Request.Builder builder = new Request.Builder();
//                Request request = builder.url("http://www.google.com").build();
//
//                try {
//                    OkHttpClient copy = okHttpClient.newBuilder()
//                            .readTimeout(30000, TimeUnit.MILLISECONDS)
//                            .build();
//                    Response response = copy.newCall(request).execute();
//                    if (response.isSuccessful()) {
//                        return response.body().string();
//                    } else {
//                        return "Not Success - code : " + response.code();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return "Error - " + e.getMessage();
//                }
//            }
//
//            @Override
//            protected void onPostExecute(String string) {
//                super.onPostExecute(string);
//
//                Log.d("SplashActivity", string);
//                String[] temp = string.split(" ");
//
//                if (temp[0].equals("Error") || temp[0].equals("Not")) {
//                    NotConnectInternet();
//                } else {
//                    btnView.setVisibility(View.VISIBLE);
//                    proView.setVisibility(View.INVISIBLE);
//                }
//            }
//        }.execute();
//    }
}