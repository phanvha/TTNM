package com.example.moonlightapplication.moonlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.moonlightapplication.R;

public class LoginPageActivity extends AppCompatActivity {
    private TextView txtsupport, txtsetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        txtsupport = (TextView)findViewById(R.id.txtSupport1);
        txtsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageActivity.this, SupportActivity.class);
                startActivity(intent);
            }
        });
        txtsetting = (TextView)findViewById(R.id.txtSetting1);
        txtsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}