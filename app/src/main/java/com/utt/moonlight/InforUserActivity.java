package com.example.moonlightapplication.moonlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.moonlightapplication.R;
import com.example.moonlightapplication.fragments.AccountFragment;

public class InforUserActivity extends AppCompatActivity {
    private ImageView imgBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_user);
        imgBackToMenu = (ImageView)findViewById(R.id.imgBackToMenu);
//        getFragmentManager().beginTransaction().replace(R.id.imgBackToMenu, InforUserActivity.this).addToBackStack(AccountFragment.class.getSimpleName()).commit();

    }
}