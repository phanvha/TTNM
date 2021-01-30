package com.utt.moonlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.moonlightapplication.R;

public class SettingActivity extends AppCompatActivity {
    private ImageView imgConnectAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        imgConnectAccount = (ImageView)findViewById(R.id.imgConnectAccount);
        imgConnectAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, AccountConnectActivity.class);
                startActivity(intent);
            }
        });
    }
}