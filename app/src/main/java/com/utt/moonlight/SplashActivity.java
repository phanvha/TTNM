package com.utt.moonlight;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.paypal.android.sdk.payments.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView txtAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtAppName = (TextView) findViewById(R.id.txtAppName);
        lottieAnimationView = findViewById(R.id.logoAnim);

        txtAppName.animate().translationX(1400).setDuration(1000).setStartDelay(5000);
        //lottieAnimationView.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        checkLogin();

    }
    private void checkLogin(){
//        final boolean status = CheckGoogleAccountStatus.getcheckDataAccount(this);
//        startProgcessBar();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }, 6400);
    }
}
