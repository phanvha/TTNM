package com.utt.moonlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.utt.utils.BlurBuilder;

public class WaitActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;

    Button btn_sign_in, btn_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        relativeLayout = (RelativeLayout) findViewById(R.id.mContent);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_lg2);
        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );
        relativeLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaitActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
