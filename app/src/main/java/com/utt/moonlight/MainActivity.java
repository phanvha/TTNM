package com.utt.moonlight;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.utt.config.Config;
import com.utt.fragments.AccountFragment;
import com.utt.fragments.HomeFragment;
import com.utt.fragments.OrderFragment;
import com.utt.fragments.StoreFragment;
import com.utt.paypal.PaymentDetails;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    ImageButton imbtnHome, imbtnOrder, imbtnShop, imbtnAccount;
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imbtnHome = (ImageButton) findViewById(R.id.imbtnHome);
        imbtnOrder = (ImageButton) findViewById(R.id.imbtnOrder);
        imbtnShop = (ImageButton) findViewById(R.id.imbtnShop);
        imbtnAccount = (ImageButton) findViewById(R.id.imbtnAccount);
        imbtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new HomeFragment();
            }
        });

        imbtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new OrderFragment();
            }
        });

        imbtnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new StoreFragment();
            }
        });

        imbtnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new AccountFragment();
            }
        });
    }



}