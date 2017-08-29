package com.chatchai.project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chatchai.project.model.ModelProfile;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    Button _loginButton;
    TextView _code;
    String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
     ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _code = (TextView) findViewById(R.id._code);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        _loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String hn = _code.getText().toString();

        // TODO: Implement your own authentication logic here.

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        onLoginSuccess();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
        new ConnectAPI().checkHn(this,hn);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(String json) {

        Gson gson = new Gson();
        ModelProfile profile = gson.fromJson(json, ModelProfile.class);

        SharedPreferences sp = getSharedPreferences("Preferences_tong", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("login", true);
        edit.putString("firstname", profile.getFirstname());
        edit.putString("lastname", profile.getLastname());
        edit.putInt("numberHN", profile.getNumberHN());
        edit.putString("phone", profile.getPhone());
        edit.putInt("age", profile.getAge());
        edit.putString("idCard", profile.getIdCard());
        edit.putString("image", profile.getImage());
        edit.putString("AppointmentToCheck", profile.getAppointmentToCheck());
        edit.commit();

        _loginButton.setEnabled(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onLoginFaild() {
        _loginButton.setEnabled(true);
        progressDialog.dismiss();
    }

}
